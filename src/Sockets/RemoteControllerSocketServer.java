package Sockets;

import java.io.IOException;
import java.net.Socket;

public class RemoteControllerSocketServer extends SocketServerModule {

	public RemoteControllerSocketServer(SocketListener listener, int port) {
		super(listener, port);
	}
	
	/*
	public class RemoteReceiver extends SocketServerModule.Receiver {

		public RemoteReceiver(Socket socket) {
			super(socket);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void run() {
			while(reader != null) {
				try {
					String received = reader.readLine();
					System.out.println("received > " + received);
					String msg  = "OK";
					os.write(msg.getBytes("UTF-8"));

					if(listener != null)
						listener.receivedMsg(received);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	*/
}
