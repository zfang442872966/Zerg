package com.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Main {

	/**
	 * 发送get请求
	 * 
	 * @param geturl
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void sendGet(String geturi, List<NameValuePair> prarmeters, Header[] headers) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost proxy = new HttpHost("192.168.4.69", 8888);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		try {
			URI uri = new URIBuilder(geturi).addParameters(prarmeters).build();
			HttpGet httpget = new HttpGet(uri);
			httpget.setConfig(config);
			httpget.setHeaders(headers);
			System.out.println("Executing request " + httpget.getRequestLine());
			// Create a custom response handler
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
			String responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println(responseBody);
		} finally {
			httpclient.close();
		}
	}

	public static void sendPost(String posturi, List<NameValuePair> prarmeters, Header[] headers) throws IOException, URISyntaxException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost proxy = new HttpHost("192.168.4.69", 8888);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		try {
			HttpPost httppost = new HttpPost(posturi);
			httppost.setConfig(config);
			httppost.setHeaders(headers);
			httppost.setEntity(new UrlEncodedFormEntity(prarmeters, "UTF-8"));
			System.out.println("Executing request " + httppost.getRequestLine());
			// Create a custom response handler
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
			String responseBody = httpclient.execute(httppost, responseHandler);
			System.out.println(responseBody);
		} finally {
			httpclient.close();
		}
	}

	public final static void main(String[] args) throws Exception {
		for (int i = 0; i < 1000; i++) {
			System.out.println(i);
			Thread.sleep(5000);
		}
		//GET
		// List<NameValuePair> prarmeters = new ArrayList<NameValuePair>();
		// prarmeters.add(new BasicNameValuePair("device", "android"));
		// prarmeters.add(new BasicNameValuePair("per_page", "40"));
		// prarmeters.add(new BasicNameValuePair("category", "all"));
		// prarmeters.add(new BasicNameValuePair("type", "album"));
		// prarmeters.add(new BasicNameValuePair("page", "1"));
		// Header[] headers = { new BasicHeader("User-Agent",
		// "ting_2.0.51(ZTE U930,Android15)"), new BasicHeader("Accept", "*/*"),
		// new BasicHeader("Host", "mobile.ximalaya.com"),
		// new BasicHeader("Cookie",
		// "1&_device=android&ffffffff-e734-d3b0-de75-99ce0037d7ef&2.0.51; impl=standard"),
		// new BasicHeader("Cookie2", "$Version=1"),
		// new BasicHeader("Accept-Encoding", "") };
		// sendGet("http://mobile.ximalaya.com/m/category_tag_list", prarmeters,
		// headers);

		// POST
		List<NameValuePair> postprar = new ArrayList<NameValuePair>();
		postprar.add(new BasicNameValuePair("device", "android"));
		postprar.add(new BasicNameValuePair("pageSize", "37"));
		postprar.add(new BasicNameValuePair("isDown", "false"));
		postprar.add(new BasicNameValuePair("key", "0"));
		Header[] psotheaders = { new BasicHeader("user-agent", "ting_2.0.51(ZTE U930,Android15)"), new BasicHeader("Accept", "*/*"),
				new BasicHeader("Content-Type", "application/x-www-form-urlencoded"), new BasicHeader("Host", "mobile.ximalaya.com"), new BasicHeader("Expect", "100-continue"),
				new BasicHeader("Cookie", "1&_device=android&ffffffff-e734-d3b0-de75-99ce0037d7ef&2.0.51; 1&_token=4236734&0200634403da904d310af4407ee4b969f1f7; impl=standard"),
				new BasicHeader("Cookie2", "$Version=1"), new BasicHeader("Accept-Encoding", "") };
		sendPost("http://mobile.ximalaya.com/mobile/message/in", postprar, psotheaders);
	}
}
