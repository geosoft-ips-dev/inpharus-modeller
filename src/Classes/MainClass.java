package Classes;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import Datas.Beacon;
import Datas.GatewayInfo;
import Datas.PositioningBeacons;
import Datas.PositioningBeacons.BeaconListUpdate;
import Datas.RecordingInfo;
import Panels.GatewayListPanel;
import Sockets.GatewaySocketServer;
import Sockets.RemoteControllerSocketServer;
import Sockets.SocketServerModule.SocketListener;
import Utils.DisplayUtil;
import Utils.GatewayDataParser;
import Utils.LogUtil;

public class MainClass extends JFrame implements LogUtil {
	private ArrayList<GatewayInfo> gateways = new ArrayList<>();
	
	// GUI Panel
	private GatewayListPanel gatewayPanel;
	private GatewayListPanel [] gatewayBeaconPanel;
	
	private GatewayDataParser dataParser;
	private PositioningBeacons authBeacons;
	
	private ArrayList<Beacon> authBeaconList;
	
	public MainClass() {
		initJFrame();
		addPanels();
		init();
		openGatewaySocketServer();
		openRemteSocketServer();
	}
	
	private void init() {
		dataParser = new GatewayDataParser();
		
		authBeacons = PositioningBeacons.getInstance();
		authBeacons.setBeaconListUpdateListener(new BeaconListUpdate() {
			@Override
			public void updatedList(ArrayList<Beacon> list) {
				// TODO Auto-generated method stub
				if(authBeaconList != null)
					authBeaconList.clear();
				authBeaconList = list;
			}
		});
	}
	
	private void initJFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setAlwaysOnTop(true);
		setTitle("비콘관제시스템 v1.0");
		
		Dimension screenSize = DisplayUtil.getInstance().getScreenSize();
		
		setSize(screenSize.width, screenSize.height);
		setLocation(0, 0);
		setExtendedState(JFrame.NORMAL);
		setVisible(true);
	}
	
	private void addPanels() {
		Dimension d = DisplayUtil.getInstance().getScreenSize();
		
		gatewayPanel = new GatewayListPanel(d, d.width - 300, 100);
		
		add(gatewayPanel.getGatewayPanel());
		
		gatewayBeaconPanel = new GatewayListPanel[4];
		
		for(int i = 0; i < 4; i++) {
			gatewayBeaconPanel[i] = new GatewayListPanel(DisplayUtil.getInstance().getScreenSize(), (10 + (i * 400)), 100);
			
			add(gatewayBeaconPanel[i].getGatewayPanel());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainClass();
	}
	
	private GatewaySocketServer gateway;
	private RemoteControllerSocketServer remoteController;
	private void openRemteSocketServer() {
		remoteController = new RemoteControllerSocketServer(new SocketListener() {
			
			@Override
			public void receivedMsg(String msg) {
				// TODO Auto-generated method stub
				showLog(msg);
				
				String [] packet = divideMsgToPacket(msg);
				RecordingInfo info = RecordingInfo.getInstance();
				
				if(packet[0] == "1") {
					info.startRecording(packet[1], packet[2]);
				} else if(packet[0] == "0") {
					info.stopRecording();
				}
			}
		}, 2549);
	}
	
	private String[] divideMsgToPacket(String msg) {
		String[] packet = msg.split(",");
		return packet;
	}
	
	private void openGatewaySocketServer() {
		gateway = new GatewaySocketServer(new SocketListener() {		
			@Override
			public void receivedMsg(String msg) {
				// TODO Auto-generated method stub
				showLog(msg);
				
				dataParser.getBeaconData(msg);
			}
		}, 8211);
	}

	@Override
	public void showLog(String TAG, String msg) {
		// TODO Auto-generated method stub
		System.out.println(TAG + " > " + msg);
	}

	@Override
	public void showLog(String msg) {
		// TODO Auto-generated method stub
		System.out.println("MainClass > " + msg);
	}
}
