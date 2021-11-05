package PositioningModules;

import Datas.Beacon;

public class SimplePositioning {
	private PositionResultListener listener;
	
	public interface PositionResultListener {
		public void onResult(int posX, int posY);
	}
	
	public SimplePositioning(Beacon b, PositionResultListener listener) {
		this.listener = listener;
		
		PositioningThread calcPosition = new PositioningThread(b);
		calcPosition.start();
	}
	
	
	class PositioningThread extends Thread {
		Beacon b;
		
		public PositioningThread(Beacon b) {
			this.b = b;
		}
		
		@Override
		public void run() {
			
			if(listener != null)
				listener.onResult(0, 0);
		}
	}
}
