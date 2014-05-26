package com.oppo.test;

import java.io.IOException;

import com.example.tutorial.PublishProductProtocol;

public class Oppo_font_products {

	public static void main(String[] args) throws IOException {
		// com.nearme.market.common.protobuf.PublishProductProtocol$PublishProductList$Builder
		byte[] bytes = { 42, 8, 90, 84, 69, 32, 85, 57, 51, 48 };
		System.out.println(new String(bytes));
		System.out.println(PublishProductProtocol.PublishProductList.parseFrom(bytes).toString());
		System.out.println("---------------------------------------------------------------");
		byte[] result = OppoHttpURLConnection.httpURLConnection("http://i3.store.nearme.com.cn/client/get_font_products.pb", bytes);
		System.out.println(new String(result));
		System.out.println("---------------------------------------------------------------");
		System.out.println(PublishProductProtocol.PublishProductList.parseFrom(result).toString());
		
	}
}
