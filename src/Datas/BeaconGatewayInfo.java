package Datas;

/**
 * Gateway Beacon class
 * @author DWARF
 *
 */

public class BeaconGatewayInfo extends Beacon {
	private String gatewayFree = "";
	private String gatewayLoad = "";
	
	public BeaconGatewayInfo(String time, String type, String mac, String gatewayFree, String gatewayLoad) {
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
