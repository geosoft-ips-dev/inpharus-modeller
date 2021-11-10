package Utils;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Datas.*;

public class GatewayDataParser {
	
	public ArrayList<Beacon> getBeaconData(String data) {
		ArrayList<Beacon> beacons = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		JSONArray jsonArray;
		try {
			jsonArray = (JSONArray)parser.parse(data);
			
			//startParseTimer();
			for(Object obj : jsonArray) {
				Beacon b = getBeaconInfo((JSONObject)obj);
				if(b == null)
					continue;
				
				beacons.add(b);
			}
			
			//stopParseTimer();
			System.out.println("beacons > " + beacons.size());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		showBeaconList(beacons);
		return beacons;  
	}
	
	private void showBeaconList(ArrayList<Beacon> beacon) {
		for (Beacon b : beacon) 
			System.out.println(b.getTimeStamp() + ", " + b.getType() + ", " + b.getMac());
	}
	
	private long startTime;
	private long endTime;
	
	private void startParseTimer() {
		Calendar cal = Calendar.getInstance();
		
		startTime = cal.getTimeInMillis();
	}
	
	private void stopParseTimer() {
		Calendar cal = Calendar.getInstance();
		
		endTime = cal.getTimeInMillis();

		System.out.println("Duration > " + (endTime - startTime));
	}
	
	private Beacon getBeaconInfo(JSONObject o) {
		Beacon beacon = null;
		
		String timestamp = (String) o.get("timestamp");
		String type = (String)o.get("type");
		String mac = (String)o.get("mac");
		
		if(type.equals(Beacon.TYPE_GATEWAY)) {
			String gatewayFree = (Long)o.get("gatewayFree") + "";
			String gatewayLoad = (Double)o.get("gatewayLoad") + "";
			
			beacon = new BeaconGatewayInfo(timestamp, type, mac, gatewayFree, gatewayLoad);
		} else if(type.equals(Beacon.TYPE_IBEACON)) {
			String bleName = (String)o.get("bleName");
			String ibeaconUuid = (String)o.get("ibeaconUuid");
			String ibeaconMajor = (Long)o.get("ibeaconMajor") + "";
			String ibeaconMinor = (Long)o.get("ibeaconMinor") + "";
			String rssi = (Long)o.get("rssi") + "";
			String ibeaconTxPower = (Long)o.get("ibeaconTxPower") + "";
			String battery = (Long)o.get("battery") + "";
			
			beacon = new iBeaconInfo(timestamp, type, mac, bleName, ibeaconUuid, ibeaconMajor, ibeaconMinor, rssi, ibeaconTxPower, battery);
		} else if(type.equals(Beacon.TYPE_S1) || type.equals(Beacon.TYPE_S2) || type.equals(Beacon.TYPE_S3) || type.equals(Beacon.TYPE_S4)) {
			String bleName = (String)o.get("bleName");
			String rssi = (Long)o.get("rssi") + "";
			String battery = (Long)o.get("battery") + "";
			String temperature = (Double)o.get("temperature") + "";
			String humidity = (Double)o.get("humidity") + "";
			String unlocked = (String)o.get("unlocked");
			String uninstalled = (String)o.get("uninstalled");
			String triggered = (String)o.get("triggered");
			
			beacon = new MinewBeacon(timestamp, type, mac, bleName, rssi, battery, temperature, humidity, unlocked, uninstalled, triggered);
		} else if(type.equals(Beacon.TYPE_UNKNOWN)) {
			String rssi = (String)o.get("rssi");
			String rawData = (String)o.get("rawData");
			
			beacon = new UnknownBeacon(timestamp, type, mac, rssi, rawData);
		}
		
		
		
		return beacon;
	}
}
