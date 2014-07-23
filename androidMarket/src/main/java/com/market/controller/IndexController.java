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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.market.service.RandomService;
import com.market.service.SendHttpRequest;

@Controller
public class IndexController {
	@Autowired
	private SendHttpRequest sendHttpRequest;
	@Autowired
	private RandomService randomService;
	@Autowired
	private JavaMailSender sender;

	@RequestMapping("/getRequest")
	public ModelAndView getRequest(@RequestParam("frequency") int frequency) {
		int successes = 0;
		for (int i = 0; i < frequency; i++) {
			// Send get request
			List<NameValuePair> prarmeters = new ArrayList<NameValuePair>();
			prarmeters.add(new BasicNameValuePair("device", "android"));
			prarmeters.add(new BasicNameValuePair("per_page", "40"));
			prarmeters.add(new BasicNameValuePair("category", "all"));
			prarmeters.add(new BasicNameValuePair("type", "album"));
			prarmeters.add(new BasicNameValuePair("page", "1"));
			Header[] headers = {
					new BasicHeader("User-Agent", "ting_2.0.51(ZTE U930,Android15)"),
					new BasicHeader("Accept", "*/*"),
					new BasicHeader("Host", "mobile.ximalaya.com"),
					new BasicHeader("Cookie",
							"1&_device=android&ffffffff-e734-d3b0-de75-99ce0037d7ef&2.0.51; impl=standard"),
					new BasicHeader("Cookie2", "$Version=1"), new BasicHeader("Accept-Encoding", "") };
			try {
				sendHttpRequest
						.sendGet("http://mobile.ximalaya.com/m/category_tag_list", prarmeters, headers);
				successes += 1;
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("get", "get the request is successful:" + successes);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/postRequest")
	public ModelAndView postRequest(@RequestParam("frequency") int frequency) {
		int successes = 0;
		for (int i = 0; i < frequency; i++) {
			// Send post request
			List<NameValuePair> postprar = new ArrayList<NameValuePair>();
			// postprar.add(new BasicNameValuePair("device", "android"));
			// postprar.add(new BasicNameValuePair("pageSize", "37"));
			// postprar.add(new BasicNameValuePair("isDown", "false"));
			// postprar.add(new BasicNameValuePair("key", "0"));
			Header[] psotheaders = { new BasicHeader("Connection", "Close"),
					new BasicHeader("User-Agent", "ZTE+U930/4.0.3/Market/V3.3.0"),
					new BasicHeader("Ext-System", "ZTE U930/4.0.3/0/2/2/V3.3.0/20"),
					new BasicHeader("Ext-User", "-1/863994013262092/0"),
					new BasicHeader("Content-Type", "application/octet-stream"),
					new BasicHeader("Accept-Encoding", "gzip"), new BasicHeader("Screen", "960#540"),
					new BasicHeader("VersionCode", "330"), new BasicHeader("brand", "generic"),
					new BasicHeader("rom", "2"), new BasicHeader("desktop", "desktop_other"),
					new BasicHeader("SourcePath", "PAHA-PHYY"), new BasicHeader("ImgType", "webp"),
					new BasicHeader("Host", "i3.store.nearme.com.cn"), };
			try {
				sendHttpRequest.sendPost("http://i3.store.nearme.com.cn/client/get_hot_app.pb", postprar,
						psotheaders);
				successes += 1;
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("post", "post the request is successful:" + successes);
		mav.setViewName("index");
		return mav;
	}

	/**
	 * 发邮件功能
	 */
	@RequestMapping("/sendEmail")
	public ModelAndView testMail() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("zfang@ximalaya.com");
		msg.setTo("zfang@ximalaya.com");
		msg.setSubject("test");
		msg.setText("test");
		sender.send(msg);
		ModelAndView mav = new ModelAndView();
		mav.addObject("email", "Mail sent successfully");
		mav.setViewName("index");
		return mav;
	}
}
