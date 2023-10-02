package com.dmdev.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {
	
	public static void main(String[] args) throws IOException {
		URL url = new URL("file:\\Users\\Anisi\\git\\JavaNetworking\\src\\com\\dmdev\\socket\\DatagramRunner.java");
		URLConnection urlConnection = url.openConnection();
		checkGoogle();
		
		System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
		
	}

	private static void checkGoogle() throws MalformedURLException, IOException {
		URL url = new URL("https://www.google.com");
		URLConnection urlConnectionUrl = url.openConnection();
		urlConnectionUrl.setDoOutput(true);
		
		try (OutputStream outputStream = urlConnectionUrl.getOutputStream()) {
		}
		System.out.println();
	}

}
