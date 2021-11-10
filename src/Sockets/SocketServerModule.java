package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketServerModule {
	private int PORT = 2960;
	
	private ServerSocket server;
	private Thread t;
	private boolean isOperate = false;
	
	public SocketListener listener;
	private boolean isReadLine = true;
	
	public interface SocketListener {
		public void receivedMsg(String ip, String msg);
		public void connectedUser(Socket socket);
	}
	
	public SocketServerModule(SocketListener listener, int port, boolean isReadLine) {
		try {
			this.listener = listener;
			this.PORT = port;
			server = new ServerSocket(PORT);
			//socket accept Thread
			this.isReadLine = isReadLine;
			
			t = new Thread(connRunnable);
			t.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Receiver receiver;
	private Runnable connRunnable = new Runnable() {
		@Override
		public void run() {
			isOperate = true;
			System.out.println("connRunnable is Operate");
			while(isOperate) {
				try {
					Socket socket = server.accept();
					
					System.out.println("Accepted > " + socket.getInetAddress());
					
					if(listener != null)
						listener.connectedUser(socket);
					
					receiver = new Receiver(socket);
					receiver.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	protected int getPort() {
		return PORT;
	}
	
	protected void closeRecever() {
		receiver.closeStream();
	}
	
	public class Receiver extends Thread {
		public Socket socket;
		public BufferedReader reader;
		public OutputStream os;
		
		public Receiver(Socket socket) {
			this.socket = socket;
			
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				os = socket.getOutputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(reader != null) {
				String received = "";
				try {
					if(isReadLine) {
						received = reader.readLine();
						System.out.println("received >> " + received);
						
						String msg  = "OK\n";
					}
					else {
						int count = 0;
						while(reader.ready()) {
							count++;
							
							received += (char)reader.read();
							//System.out.println(i + " > data " + (char)reader.read());
						}

						if(!received.equals("")) {
							//System.out.println(count + " > " + received);
							count = 0;
						}		
					}
					
					if(!received.equals("")) {
						if(listener != null) {
							listener.receivedMsg(socket.getInetAddress().toString() , received);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("run out");
		}
		
		public void closeStream() {
			System.out.println("closeStream");
			try {
				os.close();
				os = null;
				reader.close();
				reader = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void disposeSocket() {
		if(isOperate)
			isOperate = false;
		
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected String getHostIPAddr() {
		String ip = "";
		
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ip;
	}
}
