package Sockets;

import java.io.IOException;
import java.net.Socket;

public class RemoteControllerSocketServer extends SocketServerModule {

	public RemoteControllerSocketServer(SocketListener listener, int port) {
		super(listener, port, true);
	}
}
