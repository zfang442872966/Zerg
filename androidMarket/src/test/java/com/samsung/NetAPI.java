package com.samsung;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.ByteArrayBuffer;

public class NetAPI {

	@SuppressWarnings("deprecation")
	private HttpClient createHttpClient(boolean paramBoolean) {
		BasicHttpParams localBasicHttpParams = new BasicHttpParams();
		localBasicHttpParams.setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
		int i = 50;
		if (paramBoolean)
			i = 90;
		localBasicHttpParams.setParameter("http.route.default-proxy", new HttpHost(ConnRouteParams.NO_HOST));
		HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, i * 1000);
		HttpConnectionParams.setSoTimeout(localBasicHttpParams, 50000);
		HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
		HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, true);
		HttpClientParams.setRedirecting(localBasicHttpParams, true);
		localBasicHttpParams.setParameter("http.conn-manager.max-per-route", new a(this));
		try {
			SSLSocketFactory localSSLSocketFactory = SSLSocketFactory.getSocketFactory();
			// Document.getInstance().getSAConfig().getStageDataHostURL();
			// Document.getInstance().getDataExchanger().writeAllowAllHost(false);
			SchemeRegistry localSchemeRegistry = new SchemeRegistry();
			localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			localSchemeRegistry.register(new Scheme("https", localSSLSocketFactory, 443));
			DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(
					localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
			return localDefaultHttpClient;
		} catch (Exception localException) {
		}
		return null;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// post1
		String mRequestXML1 = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><SamsungProtocol networkType='0' version2='3' lang='EN' openApiVersion='15' deviceModel='GT-N7000' mcc='460' mnc='01' csc='TGY' odcVersion='14040104.21.007.0' version='4.0' filter='1'><request name='androidManifest' id='2081' numParam='1' transactionId='0'><param name='productID'>000000436135</param></request></SamsungProtocol>";
		// post2
		String mRequestXML2 = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><SamsungProtocol networkType='0' version2='3' lang='EN' openApiVersion='15' deviceModel='GT-N7000' mcc='460' mnc='01' csc='TGY' odcVersion='14040104.21.007.0' version='4.0' filter='1'><request name='EasybuyPurchase' id='3010' numParam='7' transactionId='0'><param name='testPurchaseYn'>N</param><param name='guid'>com.cootek.smartinputv5</param><param name='couponIssuedSEQ'></param><param name='userID'>s80kcsqaa4</param><param name='paymentAmountPrice'></param><param name='productID'>000000436135</param><param name='imei'>352167059063785</param></request></SamsungProtocol>";
		// post3
		String mRequestXML3 = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><SamsungProtocol networkType='0' version2='3' lang='EN' openApiVersion='15' deviceModel='GT-N7000' mcc='460' mnc='01' csc='TGY' odcVersion='14040104.21.007.0' version='4.0' filter='1'><request name='productDetailMain' id='2280' numParam='7' transactionId='0'><param name='unifiedPaymentYN'>Y</param><param name='orderID'></param><param name='source'></param><param name='productID'>000000436135</param><param name='productImgHeight'>135</param><param name='productImgWidth'>135</param><param name='imei'>352167059063785</param></request></SamsungProtocol>";
		// post4
		String mRequestXML4 = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><SamsungProtocol networkType='0' version2='3' lang='EN' openApiVersion='15' deviceModel='GT-N7000' mcc='460' mnc='01' csc='TGY' odcVersion='14040104.21.007.0' version='4.0' filter='1'><request name='productDetailOverview' id='2281' numParam='4' transactionId='0'><param name='screenImgWidth'>480</param><param name='productID'>000000436135</param><param name='screenImgHeight'>800</param><param name='orderID'>2014061908C21920860</param></request></SamsungProtocol>";

		NetAPI netAPI = new NetAPI();
		HttpClient mHttpClient = netAPI.createHttpClient(false);

		HttpHost proxy = new HttpHost("192.168.4.40", 8888);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

		HttpPost localHttpPost = new HttpPost("https://cn-ms.samsungapps.com/ods.as");
		localHttpPost.setConfig(config);
		localHttpPost.setHeader("User-Agent", "SAMSUNG-Android");
		localHttpPost.setHeader("Content-Type", "text/xml");
		localHttpPost.setEntity(new StringEntity(mRequestXML2, "UTF-8"));
		System.out.println("request:" + mRequestXML2);
		HttpResponse mResponse = mHttpClient.execute(localHttpPost);
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
	}
}
