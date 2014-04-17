package com.market.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.market.service.RandomService;
import com.market.service.SendHttpRequest;

@Controller
public class IndexController {
	@Autowired
	private SendHttpRequest sendHttpRequest;
	@Autowired
	private RandomService randomService;

	@RequestMapping("/getRequest")
	public ModelAndView getRequest() {
		ModelAndView mav = new ModelAndView();
		// Send get request
		List<NameValuePair> prarmeters = new ArrayList<NameValuePair>();
		prarmeters.add(new BasicNameValuePair("device", "android"));
		prarmeters.add(new BasicNameValuePair("per_page", "40"));
		prarmeters.add(new BasicNameValuePair("category", "all"));
		prarmeters.add(new BasicNameValuePair("type", "album"));
		prarmeters.add(new BasicNameValuePair("page", "1"));
		Header[] headers = { new BasicHeader("User-Agent", "ting_2.0.51(ZTE U930,Android15)"), new BasicHeader("Accept", "*/*"), new BasicHeader("Host", "mobile.ximalaya.com"),
				new BasicHeader("Cookie", "1&_device=android&ffffffff-e734-d3b0-de75-99ce0037d7ef&2.0.51; impl=standard"), new BasicHeader("Cookie2", "$Version=1"),
				new BasicHeader("Accept-Encoding", "") };
		try {
			mav.addObject("get", sendHttpRequest.sendGet("http://mobile.ximalaya.com/m/category_tag_list", prarmeters, headers));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/postRequest")
	public ModelAndView postRequest() {
		ModelAndView mav = new ModelAndView();
		// Send post request
		List<NameValuePair> postprar = new ArrayList<NameValuePair>();
		postprar.add(new BasicNameValuePair("device", "android"));
		postprar.add(new BasicNameValuePair("pageSize", "37"));
		postprar.add(new BasicNameValuePair("isDown", "false"));
		postprar.add(new BasicNameValuePair("key", "0"));
		Header[] psotheaders = { new BasicHeader("user-agent", "ting_2.0.51(ZTE U930,Android15)"), new BasicHeader("Accept", "*/*"),
				new BasicHeader("Content-Type", "application/x-www-form-urlencoded"), new BasicHeader("Host", "mobile.ximalaya.com"), new BasicHeader("Expect", "100-continue"),
				new BasicHeader("Cookie", "1&_device=android&ffffffff-e734-d3b0-de75-99ce0037d7ef&2.0.51; 1&_token=4236734&0200634403da904d310af4407ee4b969f1f7; impl=standard"),
				new BasicHeader("Cookie2", "$Version=1"), new BasicHeader("Accept-Encoding", "") };
		try {
			mav.addObject("post", sendHttpRequest.sendPost("http://mobile.ximalaya.com/mobile/message/in", postprar, psotheaders));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mav.setViewName("index");
		return mav;
	}
}
