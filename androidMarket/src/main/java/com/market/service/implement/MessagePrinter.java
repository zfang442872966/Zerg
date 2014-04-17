package com.market.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.market.service.MessageService;

@Component
public class MessagePrinter {
	@Autowired
	private MessageService service;

	public void printMessage() {
		System.out.println(service.getMessage());
	}

}
