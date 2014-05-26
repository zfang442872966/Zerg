package com.oppo.test;

import java.io.IOException;

import com.example.tutorial.ListCategoryProductProtocol;
import com.example.tutorial.PublishProductProtocol;
import com.market.service.implement.Bytes2HexString;
import com.market.service.implement.CompressGzip;

/**
 * 分类
 * @author zfang
 *
 */
public class Oppo_fstcate {

	public static void main(String[] args) throws IOException {
		String fstcate = "08FFFFFFFFFFFFFFFFFF01100F2A085A54452055393330380750017801";
		System.out.println(new String(Bytes2HexString.hexString2Bytes(fstcate)).toString());
		System.out.println(ListCategoryProductProtocol.ListCategoryProductItem.parseFrom(Bytes2HexString.hexString2Bytes(fstcate)).toString());
		System.out.println("---------------------------------------------------------------");
		byte[] result = OppoHttpURLConnection.httpURLConnection("http://i3.store.nearme.com.cn/client/get_fstcate_recommends.pb", Bytes2HexString.hexString2Bytes(fstcate));
		System.out.println(new String(CompressGzip.decompressGzip(result)));
		System.out.println("---------------------------------------------------------------");
		System.out.println(PublishProductProtocol.PublishProductList.parseFrom(CompressGzip.decompressGzip(result)).toString());
//		PublishProductProtocol.PublishProductList localPublishProductList = PublishProductProtocol.PublishProductList.parseFrom(CompressGzip.decompressGzip(result));
//		System.out.println("------------------------------------------------------------------------------");
//		if (localPublishProductList.getPublishProductList().size() > 0) {
//			for (int i = 0; i < localPublishProductList.getPublishProductList().size(); i++) {
//				System.out.println((i + 1) + "	" + localPublishProductList.getPublishProduct(i).getAppName() + "	" + localPublishProductList.getPublishProduct(i).getPackageName());
//			}
//		}
	}
}
