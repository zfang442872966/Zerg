package com.market.service.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * oppo
 * 
 * @author zfang
 * 
 */
public class SendHttpURLConnection {
	public static void main(String[] args) throws IOException {
		// Properties systemProperties = System.getProperties();
		// systemProperties.setProperty("http.proxyHost", "192.168.4.69");
		// systemProperties.setProperty("http.proxyPort", "8888");

		URL server = new URL("http://www.baidu.com");
		HttpURLConnection connection = (HttpURLConnection) server.openConnection();
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		System.out.println("Contents of get request");
		String lines;
		while ((lines = reader.readLine()) != null) {
			System.out.println(lines);
		}
		reader.close();
		connection.disconnect();
		System.out.println("Contents of get request ends");
	}
}