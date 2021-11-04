package Datas;

public class MinewBeacon extends Beacon {
	private String bleName = "";
	private String rssi = "";
	private String battery = "";
	private String temperature = "";
	private String humidity = "";
	private String unlocked = "";
	private String uninstalled = "";
	private String triggered = "";
	
	public MinewBeacon(String time, String type, String mac, String bleName, String rssi, String battery, String temperature, String humidity, String unlocked, String uninstalled, String triggered) {
		super(time, type, mac);
		this.bleName = bleName;
		this.rssi = rssi;
		this.battery = battery;
		this.temperature = temperature;
		this.humidity = humidity;
		this.unlocked = unlocked;
		this.uninstalled = uninstalled;
		this.triggered = triggered;
	}
	
	public String getBleName() {
		return bleName;
	}
	
	public int getRssi() {
		return Integer.parseInt(rssi);
	}
	
	public int getBattery() {
		return Integer.parseInt(battery);
	}
	
	public float getTemperature() {
		return Float.parseFloat(temperature);
	}
	
	public float getHumidity() {
		return Float.parseFloat(humidity);
	}
	
	public Boolean getUnLock() {
		if(unlocked.equals(""))
			unlocked = "false";
		return Boolean.parseBoolean(unlocked);
	}
	
	public Boolean getUnInstalled() {
		if(uninstalled.equals(""))
			uninstalled = "false";
		return Boolean.parseBoolean(uninstalled);
	}
	
	public Boolean getTriggered() {
		if(triggered.equals(""))
			triggered = "false";
		
		return Boolean.parseBoolean(triggered);
	}
}
