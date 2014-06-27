package com.samsung;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

public class SamsungTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		String mRequestXML = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><SamsungProtocol networkType='0' version2='3' lang='EN' openApiVersion='15' deviceModel='GT-N7000' mcc='460' mnc='01' csc='TGY' 	odcVersion='14040104.21.007.0' version='4.0' filter='1'><request name='androidManifest' id='2081' numParam='1' transactionId='0'><param 	name='productID'>000000644653</param></request></SamsungProtocol>";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// HttpHost proxy = new HttpHost("192.168.4.40", 8888);
		// RequestConfig config =
		// RequestConfig.custom().setProxy(proxy).build();
		try {
			HttpPost httppost = new HttpPost("https://cn-ms.samsungapps.com/ods.as");
			// httppost.setConfig(config);
			httppost.setHeader("User-Agent", "SAMSUNG-Android");
			httppost.setHeader("Content-Type", "text/xml");
			httppost.setEntity(new StringEntity(mRequestXML, "UTF-8"));
			System.out.println("request " + httppost.getRequestLine());
			HttpResponse mResponse = httpclient.execute(httppost);
			if (mResponse != null) {
				BufferedInputStream bufferedinputstream = new BufferedInputStream(mResponse.getEntity()
						.getContent(), 2048);
				ByteArrayBuffer bytearraybuffer = new ByteArrayBuffer(2048);
				ByteBuffer bytebuffer = ByteBuffer.allocate(2048);
				byte abyte0[] = bytebuffer.array();
				int i = 0;
				for (;;) {
					i = bufferedinputstream.read(abyte0, 0, 2048);
					if (i == -1)
						break;
					bytearraybuffer.append(abyte0, 0, 2048);
				}
				String response = new String(bytearraybuffer.toByteArray());
				System.out.println("response:" + response);
			}
		} finally {
			httpclient.close();
		}
	}
}
