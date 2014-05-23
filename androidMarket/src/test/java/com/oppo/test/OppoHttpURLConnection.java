package com.oppo.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.example.tutorial.ListCategoryProductProtocol;
import com.example.tutorial.PublishProductProtocol;
import com.market.service.implement.CompressGzip;

/**
 * 
 * @author zfang
 * 
 */
public class OppoHttpURLConnection {

	/**
	 * HttpURLConnection
	 * 
	 * @throws IOException
	 */
	public static byte[] httpURLConnection(String url, byte[] abyte0) throws IOException {
		byte[] result;
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.4.69", 8888));

		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection(proxy);
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
		result = baos.toByteArray();
		// System.out.println(new String(decompressGzip(baos.toByteArray())));
		baos.close();
		connection.disconnect();
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(connection.getInputStream()));
		// String lines;
		// while ((lines = reader.readLine()) != null) {
		// System.out.println(lines);
		// }
		// reader.close();
		return result;
	}

	public static void main(String[] args) throws IOException {
		// 应用排行
		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 0, 42,
		// 8, 90, 84, 69, 32, 85, 57, 51, 48, 56, 7, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 20,
		// 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 56, 7, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 40,
		// 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 56, 7, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 60,
		// 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 56, 7, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 80,
		// 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 64, 3, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 100,
		// 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 64, 3, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 120,
		// 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 64, 3, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, -116,
		// 1, 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 64, 3, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, -96,
		// 1, 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 64, 3, 80, 1, 120, 1]

		// [8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, -76,
		// 1, 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 64, 3, 80, 1, 120, 1]

		// byte[] abyte0 = { 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15,
		// 24, 20, 32, 0, 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 56, 7, 80, 1,
		// 120, 1 };
		// System.out.println(new String(abyte0));

		// 应用排行榜
		System.out.println("-------------------------------应用排行榜request-----------------------------------------------");
		ListCategoryProductProtocol.ListCategoryProductItem.Builder builder = ListCategoryProductProtocol.ListCategoryProductItem.newBuilder();
		builder.setUserId(-1);
		builder.setOs(15);
		builder.setSize(20);
		builder.setStart(0);
		builder.setMobile("ZTE U930");
		builder.setCategoryId(7);
		builder.setCompress(1);
		builder.setSource(1);
		System.out.println(new String(builder.build().toByteArray()));
		// 应用排行榜反序列化request
		ListCategoryProductProtocol.ListCategoryProductItem localpCategoryProductItem = ListCategoryProductProtocol.ListCategoryProductItem.parseFrom(builder.build().toByteArray());
		System.out.println(localpCategoryProductItem.toString());

		System.out.println("------------------------------应用排行榜response------------------------------------------------");
		byte[] result = httpURLConnection("http://i3.store.nearme.com.cn/client/get_hot_app.pb", builder.build().toByteArray());
		System.out.println(new String(CompressGzip.decompressGzip(result)));
		PublishProductProtocol.PublishProductList localPublishProductList = PublishProductProtocol.PublishProductList.parseFrom(CompressGzip.decompressGzip(result));
		System.out.println("------------------------------------------------------------------------------");
		if (localPublishProductList.getPublishProductList().size() > 0) {
			for (int i = 0; i < localPublishProductList.getPublishProductList().size(); i++) {
				System.out.println((i + 1) + "	" + localPublishProductList.getPublishProduct(i).getAppName() + "	" + localPublishProductList.getPublishProduct(i).getPackageName());
			}
		}
	}
}
