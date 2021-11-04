package Datas;

import java.util.ArrayList;

public class PositioningBeacons {
	private static PositioningBeacons beacons;
	
	private static ArrayList<Beacon> list;
	
	private BeaconListUpdate updateListener;
	
	public interface BeaconListUpdate {
		public void updatedList(ArrayList<Beacon> list);
	}
	
	public static PositioningBeacons getInstance() {
		if(beacons == null) {
			beacons = new PositioningBeacons();
			list = new ArrayList<>();
		}
		return beacons;
	}
	
	public void notifyListUpdate() {
		if(updateListener != null)
			updateListener.updatedList(list);
	}
	
	public void insertBeacon(Beacon b) {
		if(list == null)
			return;
		
		list.add(b);
		
		notifyListUpdate();
	}
	
	public void insertBeacon(String type, String mac) {
		if(list == null)
			return;
		
		Beacon b = new Beacon("", type, mac);
		
		list.add(b);
		
		notifyListUpdate();
	}
	
	public void deleteBeacon(Beacon b) {
		for(int i = 0; i < list.size(); i++) {
			Beacon obj = (Beacon)list.get(i);
			
			if(obj.getMac().equals(b.getMac())) 
				list.remove(i);		
		}
		
		notifyListUpdate();
	}
	
	public void deleteBeacon(String mac) {
		for(int i = 0; i < list.size(); i++) {
			Beacon obj = (Beacon)list.get(i);
			
			if(obj.getMac().equals(mac))
				list.remove(i);
		}
		
		notifyListUpdate();
	}
	
	public void setBeaconListUpdateListener(BeaconListUpdate u) {
		updateListener = u;
	}
	
	public ArrayList<Beacon> getPositionBeaconList() {
		return list;
	}
}
