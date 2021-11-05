package Utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Datas.*;

public class GatewayDataParser {
	
	public ArrayList<Beacon> getBeaconData(String data) {
		ArrayList<Beacon> beacons = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		try {
			Object o = (JSONObject)parser.parse(data);
			
			if(o instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray)o;
				
				for(int i = 0; i < jsonArray.size(); i++) {
					Beacon b = getBeaconInfo((JSONObject)jsonArray.get(i));
					
					if(b == null)
						continue;
					
					beacons.add(b);
					
					return beacons;
				}
			} 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return beacons;  
	}
	
	private Beacon getBeaconInfo(JSONObject o) {
		Beacon beacon = null;
		
		String timestamp = (String) o.get("timestamp");
		String type = (String)o.get("type");
		String mac = (String)o.get("mac");
		String gatewayFree = (String)o.get("gatewayFree");
		String gatewayLoad = (String)o.get("gatewayLoad");
		String bleName = (String)o.get("bleName");
		String ibeaconUuid = (String)o.get("ibeaconUuid");
		String ibeaconMajor = (String)o.get("ibeaconMajor");
		String ibeaconMinor = (String)o.get("ibeaconMinor");
		String rssi = (String)o.get("rssi");
		String ibeaconTxPower = (String)o.get("ibeaconTxPower");
		String battery = (String)o.get("battery");
		String temperature = (String)o.get("temperature");
		String humidity = (String)o.get("humidity");
		String unlocked = (String)o.get("unlocked");
		String uninstalled = (String)o.get("uninstalled");
		String triggered = (String)o.get("triggered");
		String rawData = (String)o.get("rawData");
		
		if(type.equals(Beacon.TYPE_GATEWAY)) {
			beacon = new BeaconGatewayInfo(timestamp, type, mac, gatewayFree, gatewayLoad);
		} else if(type.equals(Beacon.TYPE_IBEACON)) {
			beacon = new iBeaconInfo(timestamp, type, mac, bleName, ibeaconUuid, ibeaconMajor, ibeaconMinor, rssi, ibeaconTxPower, battery);
		} else if(type.equals(Beacon.TYPE_S1) || type.equals(Beacon.TYPE_S2) || type.equals(Beacon.TYPE_S3) || type.equals(Beacon.TYPE_S4)) {
			beacon = new MinewBeacon(timestamp, type, mac, bleName, rssi, battery, temperature, humidity, unlocked, uninstalled, triggered);
		} else if(type.equals(Beacon.TYPE_UNKNOWN)) {
			beacon = new UnknownBeacon(timestamp, type, mac, bleName, rssi);
		}
		
		return beacon;
	}
}
