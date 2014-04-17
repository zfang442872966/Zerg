package com.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.market.utility.RandomUtility;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public ModelAndView helloWorld() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("message", RandomUtility.getRandomString(RandomUtility.LETTER, 10));
		return mav;
	}
}
