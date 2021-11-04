package Datas;

public class iBeaconInfo extends Beacon{
	private String bleName = "";
	private String ibeaconMajor = "";
	private String ibeaconMinor = "";
	private String rssi;
	private String ibeaconTxPower;
	private String battery;
	
	public iBeaconInfo(String timestamp, String type, String mac, String bleName, String ibeaconUuid, String ibeaconMajor, String ibeaconMinor, String rssi, String ibeaconTxPower, String battery) {
		// TODO Auto-generated constructor stub
		super(timestamp, type, mac);
	}
	
	public String getBleName() {
		return bleName;
	}
	
	public String getIbeaconMajor() {
		return ibeaconMajor;
	}
	
	public String getIbeaconMinor() {
		return ibeaconMinor;
	}
	
	public int getRssi() {
		return Integer.parseInt(rssi);
	}
	
	public int getIBeaconTxPower() {
		return Integer.parseInt(ibeaconTxPower);
	}
	
	public int getBattery() {
		return Integer.parseInt(battery);
	}
}
