package com.dmdev.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class DatagramRunner {
	public static void main(String[] args) throws IOException {
		try (DatagramSocket datagram = new DatagramSocket()) {
			InetAddress inetAddress = InetAddress.getByName("localhost");
			byte[] bytes = "Hello from UDP client".getBytes();
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
			datagram.send(packet);
		}
	}
}
