package com.oppo.test;

import java.io.IOException;

import com.example.tutorial.ListCategoryProductProtocol;
import com.example.tutorial.RecommendProductProtocol;
import com.example.tutorial.RecommendProductProtocol.AdvertisementList;
import com.example.tutorial.RecommendProductProtocol.HomeRecommend;
import com.market.service.implement.Bytes2HexString;
import com.market.service.implement.CompressGzip;

public class Oppo_recommend {

	public static void main(String[] args) throws IOException {
		// 精品推荐
		System.out.println("-----------------------------精品推荐request-------------------------------------------------");
		String recommend = "08FFFFFFFFFFFFFFFFFF01100F182720002A085A5445205539333040035001780192010F383633393934303133323632303932";
		byte[] recommendByte = Bytes2HexString.hexString2Bytes(recommend);
		System.out.println(new String(recommendByte));
		System.out.println(ListCategoryProductProtocol.ListCategoryProductItem.parseFrom(Bytes2HexString.hexString2Bytes(recommend)).toString());

		System.out.println("------------------------------精品推荐response------------------------------------------------");
		byte[] recommendResult = OppoHttpURLConnection.httpURLConnection("http://i3.store.nearme.com.cn/client/get_recommend_product.pb", recommendByte);
		System.out.println(new String(CompressGzip.decompressGzip(recommendResult)));
		System.out.println("------------------------------response------------------------------------------------");
		HomeRecommend homeRecommend = RecommendProductProtocol.HomeRecommend.parseFrom(CompressGzip.decompressGzip(recommendResult));
		AdvertisementList advertisementList = homeRecommend.getAdList();
	}
}
