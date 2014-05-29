package com.oppo.test;

import com.example.tutorial.ListCategoryProductProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.market.service.implement.Bytes2HexString;

public class Oppo_test {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		String hos_game = "08FFFFFFFFFFFFFFFFFF01100F181420002A085A54452055393330300238A62A400350017801";
		ListCategoryProductProtocol.ListCategoryProductItem localpCategoryProductItem = ListCategoryProductProtocol.ListCategoryProductItem.parseFrom(Bytes2HexString.hexString2Bytes(hos_game));
		System.out.println(localpCategoryProductItem.toString());
	}

}
