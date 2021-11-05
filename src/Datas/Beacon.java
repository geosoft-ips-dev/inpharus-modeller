package Datas;

/**
 * 
 * @author DWARF
 * 비콘 부모 클래스
 */

public class Beacon {
	public static String TYPE_GATEWAY = "gateway";
	public static String TYPE_S1 = "S1";
	public static String TYPE_S2 = "S2";
	public static String TYPE_S3 = "S3";
	public static String TYPE_S4 = "S4";
	public static String TYPE_IBEACON = "iBeacon";
	public static String TYPE_UNKNOWN = "Unknown";
	
	private String timestamp = "";
	private String type = "";
	private String mac = "";
	
	public Beacon(String time, String type, String mac) {
		// TODO Auto-generated constructor stub
		this.timestamp = time;
		this.type = type;
		this.mac = mac;
	}
	
	public String getTimeStamp() {
		return timestamp;
	}
	
	public String getType() {
		return type;
	}
	
	public String getMac() {
		return mac;
	}
}
