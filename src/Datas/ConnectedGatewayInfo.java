package Datas;

import java.net.Socket;
import java.util.ArrayList;

/**
 * TCP/IP 로 연결된 Gateway 관리 Class
 * @author DWARF
 *
 */
public class ConnectedGatewayInfo {
	private ArrayList<GatewayInfo> gateways;

	private OnUpdateGatewayList listener;
	
	public interface OnUpdateGatewayList {
		public void OnListChanged(ArrayList<GatewayInfo> list);
	}
	
	public void setUpdateGatewayList(OnUpdateGatewayList listener) {
		System.out.println("setUpdateGatewayList > "+ listener);
		this.listener = listener;
	}
	
	public ConnectedGatewayInfo(OnUpdateGatewayList listener) {
		gateways = new ArrayList<>();
		this.listener = listener;
	}

	public void addConnGateway(String name, Socket socket) {
		GatewayInfo info = new GatewayInfo(name, socket);
		
		gateways.add(info);
		
		notifyListChanged();
	}
	
	public void removeConnGateway(String name) {
		for(int i = 0; i < gateways.size(); i++) {
			if(gateways.get(i).getGatewayName().equals("name"))
				gateways.remove(i);
		}
		
		notifyListChanged();
	}
	
	private void notifyListChanged() {
		if(listener != null) {
			listener.OnListChanged(gateways);
			System.out.println("--- notifyListChanged --- > " + listener);
		}
	}
	
	public ArrayList<GatewayInfo> getGatewayList() {
		return gateways;
	}
	
	public String[] getGatewayListName() {
		String [] names = new String[gateways.size()];
		
		for(int i = 0; i < gateways.size(); i++) {
			names[0] = gateways.get(i).getGatewayName();
		}
		
		return names;
	}
	
	public String[] getGatewayListAddr() {
		String [] addr = new String[gateways.size()];
		
		for(int i = 0; i < gateways.size(); i++) {
			addr[i] = gateways.get(i).getSocket().getInetAddress().toString();
		}
		
		return addr;
	}
	
	public int getGatewayListSize() {
		return gateways.size();
	}
}
