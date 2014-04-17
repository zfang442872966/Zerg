package com.market.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

public interface SendHttpRequest {
	/**
	 * Send get request
	 * 
	 * @param geturi
	 * @param prarmeters
	 * @param headers
	 */
	public String sendGet(String geturi, List<NameValuePair> prarmeters, Header[] headers) throws URISyntaxException, ClientProtocolException, IOException;

	/**
	 * Send post request
	 * 
	 * @param posturi
	 * @param prarmeters
	 * @param headers
	 */
	public String sendPost(String posturi, List<NameValuePair> prarmeters, Header[] headers) throws ClientProtocolException, IOException;
}
