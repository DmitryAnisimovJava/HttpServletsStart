package com.dmdev.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
	
	private final ExecutorService pool;
	private final int port;
	private boolean stopped;

	public HttpServer(int port, int poolSize) {
		this.port = port;
		this.pool = Executors.newFixedThreadPool(poolSize);
	}
	
	public void run() {
		try {
			ServerSocket server = new ServerSocket(port);
			while (!stopped) {
				Socket socket = server.accept();
				System.out.println("Socket accepted");
				pool.submit(()-> proccessSocket(socket));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void proccessSocket(Socket socket) {
			try (socket;
				DataInputStream inputStream = new DataInputStream(socket.getInputStream());
				OutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
				System.out.println("Request: " + new String(inputStream.readNBytes(400)));
				Thread.sleep(10000);
			    byte[] body = Files.readAllBytes(Path.of("C:\\Users\\Anisi\\git\\JavaNetworking\\src\\main\\resources\\example.html"));
			    String headers = """
			    		HTTP/1.1 200 OK
			    		content-type: text/html
			    		content-length: %s
			    		""".formatted(body.length);
			    outputStream.write(headers.getBytes());
			    outputStream.write(System.lineSeparator().getBytes());
			    outputStream.write(body);
			} catch (IOException | InterruptedException e) {
				// TODO: 29.09.2023 log error message
				e.printStackTrace();
			}
	}
	
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
}
