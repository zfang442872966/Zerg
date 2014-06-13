package com.market.service.implement;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.http.util.EntityUtils;

import com.market.service.SendHttpRequest;

public class SendHttpRequestImpl implements SendHttpRequest {

	public void init() {
		System.out.println("SendHttpRequestImpl init()...");
	}

	public String sendGet(String geturi, List<NameValuePair> prarmeters, Header[] headers) throws URISyntaxException, ClientProtocolException, IOException {
		String responseBody = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost proxy = new HttpHost("192.168.4.40", 8888);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		try {
			URI uri = new URIBuilder(geturi).addParameters(prarmeters).build();
			HttpGet httpget = new HttpGet(uri);
			httpget.setConfig(config);
			httpget.setHeaders(headers);
			System.out.println("Executing get request " + httpget.getRequestLine());
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
			responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println(responseBody);
		} finally {
			httpclient.close();
		}
		return responseBody;
	}

	public String sendPost(String posturi, List<NameValuePair> prarmeters, Header[] headers) throws ClientProtocolException, IOException {
		String responseBody = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost proxy = new HttpHost("192.168.4.40", 8888);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		try {
			HttpPost httppost = new HttpPost(posturi);
			httppost.setConfig(config);
			httppost.setHeaders(headers);
			httppost.setEntity(new UrlEncodedFormEntity(prarmeters, "UTF-8"));
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
		return responseBody;
	}

}
