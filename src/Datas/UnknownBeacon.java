package Datas;

public class UnknownBeacon extends Beacon {
	private String rssi = "";
	private String rawData = "";
	public UnknownBeacon(String time, String type, String mac, String rssi, String rawData) {
		super(time, type, mac);
		
		this.rssi = rssi;
		this.rawData = rawData;
	}

	public int getRssi() {
		return Integer.parseInt(rssi);
	}
	
	public String getRawData() {
		return rawData;
	}
}
