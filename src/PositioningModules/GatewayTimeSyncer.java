package PositioningModules;

import java.util.ArrayList;

import Datas.Beacon;
import Datas.ConnectedGatewayInfo;
import Datas.ConnectedGatewayInfo.OnUpdateGatewayList;
import Datas.GatewayInfo;

/**
 * Gateway의 동기화 된 비콘들을 관리하기 위한 Class
 * Gateway 연결에 반응하여 receivedList를 추가한다.
 * 선택한 beacon을 리스트에서 찾아 beaon list와 gateway 이름을 반환한다.
 * @author DWARF
 *
 */

public class GatewayTimeSyncer {
	private OnFoundBeaconInList listener;
	
	public interface OnFoundBeaconInList {
		public void OnFound(ArrayList<Beacon> list, ArrayList<String> gatewayName);
	}
	
	private ConnectedGatewayInfo info;
	
	private ArrayList<Beacon> [] recivedList;
	private String[] gatewayNameList;
	
	public GatewayTimeSyncer(ConnectedGatewayInfo info, OnFoundBeaconInList listener) {
		this.info = info;
		this.listener = listener;
		
		setReceiveGatewayList();
		
		this.info.setUpdateGatewayList(new OnUpdateGatewayList() {
			@Override
			public void OnListChanged(ArrayList<GatewayInfo> list) {
				setReceiveGatewayList();
			}
		});
	}
	
	private void setReceiveGatewayList() {
		if(recivedList != null)
			recivedList = null;
			
		recivedList = new ArrayList[info.getGatewayListSize()];
		
		for(int i = 0; i < recivedList.length; i++) {
			recivedList[i] = new ArrayList<>();
			
			gatewayNameList = info.getGatewayListName();
		}
	}
	
	private String getGatewayIndexName(int index) {
		return gatewayNameList[index];
	}
	
	private void updateBeaconData(int index, ArrayList<Beacon> beacons) {
		recivedList[index] = beacons;
	}
	
	private void getSelectBeaconInfo(Beacon beacon) {
		FoundBeaconThread t = new FoundBeaconThread(beacon);
		t.start();
	}
	
	/*
	 * 검색 비동기 처리 Class
	 */
	class FoundBeaconThread extends Thread {
		private ArrayList<Beacon> beaconArray;
		private ArrayList<String> nameList;
		
		private Beacon beacon;
		
		public FoundBeaconThread(Beacon b) {
			// TODO Auto-generated constructor stub
			beaconArray = new ArrayList<>();
			nameList = new ArrayList<>();
			this.beacon = b;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0; i < recivedList.length; i++) {
				ArrayList<Beacon> b = recivedList[i];
				
				for(Beacon beacon: b) {
					if(beacon.getMac().equals(beacon.getMac())) {
						beaconArray.add(beacon);
						nameList.add(gatewayNameList[i]);
					}
				}
			}
			
			if(listener != null)
				listener.OnFound(beaconArray, nameList);
			super.run();
		}
	}
}
