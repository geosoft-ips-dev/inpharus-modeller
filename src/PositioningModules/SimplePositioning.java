package PositioningModules;

import java.util.ArrayList;

import Datas.Beacon;

public class SimplePositioning {
	private PositionResultListener listener;
	
	public interface PositionResultListener {
		public void onResult(Beacon b, int posX, int posY);
	}
	
	public SimplePositioning(Beacon b, ArrayList<Beacon> list, ArrayList<String> gatewayLNames, PositionResultListener listener) {
		this.listener = listener;
		
		PositioningThread calcPosition = new PositioningThread(b, list, gatewayLNames);
		calcPosition.start();
	}
	
	
	class PositioningThread extends Thread {
		ArrayList<Beacon> list;
		ArrayList<String> names;
		Beacon b;
		public PositioningThread(Beacon b, ArrayList<Beacon> beacons, ArrayList<String> gatewayNames) {
			this.list = beacons;
			this.names = gatewayNames;
			this.b = b;
		}
		
		@Override
		public void run() {
			int posX = -1, posY = -1;
			
			if(listener != null)
				listener.onResult(b, posX, posY);
		}
	}
}
