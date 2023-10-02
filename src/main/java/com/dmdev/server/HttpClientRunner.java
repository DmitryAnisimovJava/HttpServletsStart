package com.dmdev.server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

public class HttpClientRunner {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		HttpClient client = HttpClient.newBuilder()
				.version(Version.HTTP_1_1)
				.build();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:9000"))
				.header("content-type", "application/json")
				.POST(BodyPublishers.ofFile(Path.of("C:\\Users\\Anisi\\git\\JavaNetworking\\src\\main\\resources\\first.json")))
				.build();
		var response  = client.sendAsync(request, BodyHandlers.ofString());
		var response1  = client.sendAsync(request, BodyHandlers.ofString());
		var response2  = client.sendAsync(request, BodyHandlers.ofString());
	//	System.out.println(response.headers());
	//	System.out.println(response.body());
		System.out.println(response2.get().body());
	}

}
