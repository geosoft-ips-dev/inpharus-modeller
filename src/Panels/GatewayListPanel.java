package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Datas.GatewayInfo;

public class GatewayListPanel {
	private JPanel gatewayPanel;
	private JList gatewayList;
	private JScrollPane scrolled;
	private DefaultListModel model;
	
	public GatewayListPanel(Dimension d, int loc_x, int loc_y) {
		System.out.println("loc > " + loc_x + ", " + loc_y);
		// TODO Auto-generated constructor stub
		gatewayPanel = new JPanel();
		gatewayPanel.setSize(d.width / 6, d.height - 300);
		
		gatewayPanel.setBackground(new Color(255, 0, 0));
		gatewayPanel.setLayout(null);
		
		gatewayList = new JList();
		
		scrolled = new JScrollPane();
		scrolled.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		scrolled.add(gatewayList);
		
		gatewayPanel.add(scrolled);
		
		gatewayPanel.setLocation(new Point(loc_x, loc_y));
	}
	
	public JPanel getGatewayPanel() {
		return gatewayPanel;
	}
	
	public void updateGatewayList(ArrayList<GatewayInfo> gateways) {
		//gatewayList.setListData(gateways);
	}
}
