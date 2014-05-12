package com.oppo.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * 
 * @author zfang
 *
 */
public class OppoHttpURLConnection {
	/**
	 * gZip压缩方法
	 * */
	public static byte[] compressGzip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/**
	 * gZip解压方法
	 * */
	public static byte[] decompressGzip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public static void main(String[] args) throws IOException {
		byte abyte0[] = { 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 0, 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 56, 7, 80, 1, 120, 1 };
		System.out.println(new String(abyte0));
		System.out.println("-----------------------------------------------------------------");
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.4.69", 8888));

		HttpURLConnection connection = (HttpURLConnection) new URL("http://i3.store.nearme.com.cn/client/get_hot_app.pb").openConnection(proxy);
		connection.setDoOutput(true);
		connection.setReadTimeout(30000);
		connection.setConnectTimeout(30000);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Connection", "Close");
		connection.setRequestProperty("User-Agent", "ZTE+U930/4.0.3/Market/V3.3.0");
		connection.setRequestProperty("Ext-System", "ZTE U930/4.0.3/0/2/2/V3.3.0/20");
		connection.setRequestProperty("Ext-User", "-1/863994013262092/0");
		connection.setRequestProperty("Content-Type", "application/octet-stream");
		connection.setRequestProperty("Accept-Encoding", "gzip");
		connection.setRequestProperty("Screen", "960#540");
		connection.setRequestProperty("VersionCode", "330");
		connection.setRequestProperty("brand", "generic");
		connection.setRequestProperty("rom", "2");
		connection.setRequestProperty("desktop", "desktop_other");
		connection.setRequestProperty("SourcePath", "PAHA-PHYY");
		connection.setRequestProperty("ImgType", "webp");
		connection.setRequestProperty("Host", "i3.store.nearme.com.cn");
		connection.connect();
		connection.getOutputStream().write(abyte0);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int number;
		while ((number = connection.getInputStream().read()) != -1) {
			baos.write(number);
		}
		System.out.println(new String(decompressGzip(baos.toByteArray())));
		baos.close();

		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(connection.getInputStream()));
		// String lines;
		// while ((lines = reader.readLine()) != null) {
		// System.out.println(lines);
		// }
		// reader.close();
		connection.disconnect();
	}
}
