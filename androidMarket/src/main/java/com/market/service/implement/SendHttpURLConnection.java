package com.market.service.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * oppo
 * 
 * @author zfang
 * 
 */
public class SendHttpURLConnection {
	public static void main(String[] args) throws IOException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.4.69", 8888));

		URL server = new URL("http://www.baidu.com");
		HttpURLConnection conn = (HttpURLConnection) server.openConnection(proxy);
		conn.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String lines;
		while ((lines = reader.readLine()) != null) {
			System.out.println(lines);
		}
		reader.close();
		conn.disconnect();
	}
}