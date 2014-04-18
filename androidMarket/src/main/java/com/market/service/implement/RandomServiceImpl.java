package com.market.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.market.service.RandomService;

public class RandomServiceImpl implements RandomService {

	public void init() {
		System.out.println("RandomServiceImpl init()...");
	}

	/**
	 * 获得随机字符串
	 * 
	 * @param string
	 *            NUMBER（数字）LETTER（数字字符大小写）CHARACTER_LETTER（小写+大写）
	 *            LOWERCASE_LETTERS（ 小写）UPPERCASE_LETTERS（大写）
	 * @param length
	 * @return
	 */
	public static String getRandomString(String string, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = new Random().nextInt(string.length());
			sb.append(string.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取非0的16进制字符串，每个bit会产生两个随机字符
	 * 
	 * @param byteLength
	 * @return
	 */
	public static String getHexString(int bitLength) {
		byte[] bytes = new byte[bitLength];
		new Random().nextBytes(bytes);
		return bytesToHexString(bytes);
	}

	/**
	 * 获取随机的Mac地址
	 * 
	 * @return
	 */
	public static String getMacAddress() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			list.add(getHexString(1));
		}
		return String.join(":", list);
	}

	/**
	 * 获取随机的Guid
	 * 
	 * @return
	 */
	public static String getGuid() {
		List<String> list = new ArrayList<String>();
		list.add(getHexString(4));
		list.add(getHexString(2));
		list.add(getHexString(2));
		list.add(getHexString(2));
		list.add(getHexString(6));
		return String.join("-", list);
	}

	/**
	 * 获取随机数列表
	 * 
	 * @param length列表大小
	 * @param randomRange随机范围
	 * @return
	 */
	public static List<Integer> getNumberList(int length, int randomRange) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			list.add(new Random().nextInt(randomRange));
		}
		return list;
	}

	/**
	 * SIM卡序列号长度20位或者12位
	 * 
	 * @return
	 */
	public static String getICCID() {
		List<String> list = new ArrayList<String>();
		list.add("00");
		list.add("01");
		list.add("02");
		list.add("03");
		StringBuffer sb = new StringBuffer();
		sb.append("8986").append(list.get(new Random().nextInt(4))).append(getRandomString(NUMBER, 14));
		return sb.toString();
	}

	/**
	 * 获取IMSI 15位数字<br>
	 * 唯一地识别移动客户所属的国家，我国为460；
	 * MNC为网络id，由2位数字组成，用于识别移动客户所归属的移动网络，中国移动为00、02，中国联通为01，中国电信为03；
	 * MSIN为移动客户识别码，采用等长11位数字构成。
	 * 
	 * @return
	 */
	public static String getIMSI() {
		List<String> list = new ArrayList<String>();
		list.add("00");
		list.add("01");
		list.add("02");
		list.add("03");
		StringBuffer sb = new StringBuffer();
		sb.append("460").append(list.get(new Random().nextInt(4))).append(getRandomString(NUMBER, 10));
		return sb.toString();
	}

	/**
	 * 获取IMEI 15位数字，末位为识别码
	 * 
	 * @return
	 */
	public static String getIMEI() {
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(8);
		arr.add(6);
		arr.addAll(getNumberList(12, 10));
		arr.add(getIMEILastNum(arr));
		StringBuffer sb = new StringBuffer();
		for (Integer integer : arr) {
			sb.append(integer);
		}
		return sb.toString();
	}

	/**
	 * 获取IMEI验证码
	 * 
	 * @param arr
	 * @return
	 */
	public static int getIMEILastNum(List<Integer> arr) {
		if (arr.size() < 14)
			return -1;
		int sum = 0;
		for (int i = 0; i < 14; i++) {
			int n = arr.get(i) * (i % 2 == 0 ? 1 : 2);
			sum += n / 10 + n % 10;
		}
		return (10 - sum % 10) % 10;
	}

	/**
	 * byte数组转换成16进制字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 16进制字符串转换成byte数组
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static void main(String[] args) {
		System.out.println(getIMEI());
	}
}
