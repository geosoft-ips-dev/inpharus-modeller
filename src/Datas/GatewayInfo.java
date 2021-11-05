package Datas;

import java.net.Socket;

public class GatewayInfo {
	private String gatewayName;
	private Socket socket;
	
	public GatewayInfo(String gatewayName, Socket socket) {
		this.gatewayName = gatewayName;
		this.socket = socket;
	}
	
	public String getGatewayName() {
		return gatewayName;
	}
	
	public Socket getSocket() {
		return socket;
	}

}
