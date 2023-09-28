package com.dmdev.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class SocketRunner {
	
	public static void main(String[] args) throws IOException {
		InetAddress adress = Inet4Address.getByName("localhost");
		try (Socket socket = new Socket(adress, 7777);
				DataInputStream inputStream = new DataInputStream(socket.getInputStream());
				DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
			outputStream.writeUTF("Hello World!");
			System.out.println("Response from server" + inputStream.readUTF());
		}
	}
}
