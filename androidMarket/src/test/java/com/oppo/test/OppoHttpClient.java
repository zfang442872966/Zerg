package com.oppo.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * 
 * @author zfang
 *
 */
public class OppoHttpClient {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		Header[] psotheaders = { 
				new BasicHeader("Connection", "Close"),
				new BasicHeader("User-Agent", "ZTE+U930/4.0.3/Market/V3.3.0"),
				new BasicHeader("Ext-System", "ZTE U930/4.0.3/0/2/2/V3.3.0/20"),
				new BasicHeader("Ext-User", "-1/863994013262092/0"),
				new BasicHeader("Content-Type", "application/octet-stream"),
				new BasicHeader("Accept-Encoding", "gzip"),
				new BasicHeader("Screen", "960#540"),
				new BasicHeader("VersionCode", "330"), 
				new BasicHeader("brand", "generic"),
				new BasicHeader("rom", "2"),
				new BasicHeader("desktop", "desktop_other"),
				new BasicHeader("SourcePath", "PAHA-PHYY"),
				new BasicHeader("ImgType", "webp"),
				new BasicHeader("Host", "i3.store.nearme.com.cn"),
		};
		
		List<NameValuePair> postprar = new ArrayList<NameValuePair>();
		postprar.add(new BasicNameValuePair("device", "android"));

		String responseBody = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost proxy = new HttpHost("192.168.4.69", 8888);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		try {
			HttpPost httppost = new HttpPost("http://i3.store.nearme.com.cn/client/get_hot_app.pb");
			httppost.setConfig(config);
			httppost.setHeaders(psotheaders);
			httppost.setEntity(new UrlEncodedFormEntity(postprar, "UTF-8"));
			System.out.println("Executing post request " + httppost.getRequestLine());
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			responseBody = httpclient.execute(httppost, responseHandler);
			System.out.println(responseBody);
		} finally {
			httpclient.close();
		}
	}
}
