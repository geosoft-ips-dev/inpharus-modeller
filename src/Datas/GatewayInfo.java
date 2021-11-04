package Datas;

public class GatewayInfo extends Beacon {
	private String gatewayFree = "";
	private String gatewayLoad = "";
	
	public GatewayInfo(String time, String type, String mac, String gatewayFree, String gatewayLoad) {
		super(time, type, mac);
		// TODO Auto-generated constructor stub
	}
	
	public String getGateFree() {
		return gatewayFree;
	}
	
	public String gatewayLoad() {
		return gatewayLoad;
	}
}
