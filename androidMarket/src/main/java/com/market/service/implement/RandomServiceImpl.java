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

	/**
	 * 获取随机的Android系统版本号和api版本
	 * 
	 * @return
	 */
	public static String getAndroidSystem() {
		List<String> list = new ArrayList<String>();
		list.add("1.5,3");
		list.add("1.6,4");
		list.add("2.1,7");
		list.add("2.2,8");
		list.add("2.3,10");
		list.add("3.0,11");
		list.add("3.1,12");
		list.add("3.2,13");
		list.add("4.0,15");
		list.add("4.1,16");
		list.add("4.2,17");
		list.add("4.3,18");
		list.add("4.4,19");
		return list.get(new Random().nextInt(list.size()));
	}

	/**
	 * 获取随机的Android设备Model
	 * 
	 * @return
	 */
	public static String getAndroidModel() {
		List<String> list = new ArrayList<String>();
		list.add("Samsung,GT-I9300");
		list.add("Samsung,GT-N7100");
		list.add("Xiaomi,M2");
		list.add("Samsung,GT-I9100");
		list.add("Xiaomi,MI 1S");
		list.add("Samsung,GT-i9100g");
		list.add("Xiaomi,MI-ONE Plus");
		list.add("Samsung,GT-N7000");
		list.add("Samsung,GT-I9308");
		list.add("Meizu,M040");
		list.add("HTC,HTC Incredible S");
		list.add("Samsung,GT-S5830i");
		list.add("Samsung,GT-N7102");
		list.add("Sony Ericsson,LT18i");
		list.add("Samsung,GT-N7108");
		list.add("Samsung,GT-S5830");
		list.add("Samsung,GT-I9220");
		list.add("Huawei,HUAWEI C8812");
		list.add("Samsung,GT-S7562");
		list.add("HTC,HTC Sensation XE");
		list.add("Xiaomi,1S");
		list.add("Huawei,HUAWEI C8813");
		list.add("HTC,HTC One X");
		list.add("Samsung,GT-S7568");
		list.add("motorola,ME525+");
		list.add("HTC,HTC T328W");
		list.add("Samsung,GT-I9082");
		list.add("Lenovo,Lenovo A288t");
		list.add("Samsung,GT-N719");
		list.add("Huawei,HUAWEI U8825D");
		list.add("ZTE,ZTE U795");
		list.add("Huawei,HUAWEI T8951");
		list.add("Lenovo,Lenovo A298t");
		list.add("Lenovo,Lenovo A278t");
		list.add("Xiaomi,MI-ONE C1");
		list.add("Meizu,MX");
		list.add("HTC,HTC Desire S");
		list.add("Google,Galaxy Nexus");
		list.add("Samsung,GT-S7562i");
		list.add("HTC,HTC Sensation");
		list.add("HTC,HTC T328t");
		list.add("OPPO,X909");
		list.add("Lenovo,Lenovo A789");
		list.add("Lenovo,Lenovo A798t");
		list.add("K-TOUCH,K-TOUCH W619");
		list.add("ZTE,ZTE U880");
		list.add("HTC,HTC Sensation XL");
		list.add("Samsung,GT-S5660");
		list.add("Samsung,GT-I699");
		list.add("Google,Nexus 4");
		list.add("Samsung,GT-S7572");
		list.add("Huawei,HUAWEI U9508");
		list.add("Huawei,HUAWEI G520-5000");
		list.add("motorola,MB525");
		list.add("Sony,LT29i");
		list.add("Sony,LT26i");
		list.add("Samsung,GT-I8262D");
		list.add("Xiaomi,M1");
		list.add("Huawei,HUAWEI Y 220T");
		list.add("OPPO,X907");
		list.add("ZTE,ZTE V880");
		list.add("Coolpad,Coolpad 7295");
		list.add("Meizu,M9");
		list.add("vivo,vivo X1");
		list.add("Sony,LT26ii");
		list.add("Lenovo,Lenovo A60");
		list.add("ZTE,ZTE U807");
		list.add("Google,I9100");
		list.add("Huawei,HUAWEI C8650");
		list.add("HTC,HTC T328d");
		list.add("Samsung,GT-i9000");
		list.add("Lenovo,Lenovo S720");
		list.add("HTC,HTC EVO 3D");
		list.add("OPPO,U705T");
		list.add("Lenovo,Lenovo S890");
		list.add("Samsung,GT-I9260");
		list.add("OPPO,R811");
		list.add("Samsung,GT-i9003");
		list.add("Samsung,GT-I8160");
		list.add("HTC,HTC T329t");
		list.add("Sony Ericsson,MT15i");
		list.add("Samsung,GT-N7105");
		list.add("HTC,HTC DesireHD");
		list.add("Samsung,GT-i9108");
		list.add("Samsung,GT-I939");
		list.add("Samsung,GT-I9228");
		list.add("ZTE,ZTE U790");
		list.add("vivo,vivo S7");
		list.add("Lenovo,Lenovo A65");
		list.add("Huawei,HUAWEI G510-0010");
		list.add("Samsung,GT-I9070");
		list.add("HTC,HTC Wildfire S");
		list.add("Sony,C6603");
		list.add("HTC,HTC T528t");
		list.add("Lenovo,Lenovo A820");
		list.add("HTC,HTC T528w");
		list.add("Samsung,GT-S6352");
		list.add("Lenovo,Lenovo A520");
		list.add("Coolpad,Coolpad 8190");
		list.add("GiONEE,GN700W");
		list.add("Samsung,GT-I8150");
		list.add("Meizu,M032");
		list.add("Samsung,GT-P3100");
		list.add("Sony,LT22i");
		list.add("Samsung,GT-I9268");
		list.add("Samsung,GT-I9305");
		list.add("Samsung,GT-I9050");
		list.add("Coolpad,Coolpad 7260");
		list.add("ZTE,ZTE V889D");
		list.add("Huawei,HUAWEI Y310-5000");
		list.add("Lenovo,Lenovo A366t");
		list.add("Samsung,GT-N8000");
		list.add("vivo,vivo Y3t");
		list.add("Lenovo,Lenovo A780");
		return list.get(new Random().nextInt(list.size()));
	}

	/**
	 * 获取随机的Android UserAgent
	 * 
	 * @return
	 */
	public static String getAndroidUserAgent() {
		List<String> list = new ArrayList<String>();
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-I9003 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; vivo X1 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; GT-I9100G Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-S7572 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9308 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9300 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328d Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; OCCO V6 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HTC Z560e Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; GN168T Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LT18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328w Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.3; 9220 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I919U Build/GINGERBREAD) ");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9108 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A65 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; K-Touch_E7 Build/K-Touch_E7_V00.32)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-S5830 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I699 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; vivo X1 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9300 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7100 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328w Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9308 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; LT22i Build/6.2.A.0.400)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC Sensation XE with Beats Audio Z715e Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-i919 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7102 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I8552 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; tetc Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5830i Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.1; GN708T Build/JOP40D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-W999 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; MI-ONE Plus MIUI/3.6.7)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI-ONE Plus MIUI/3.3.8)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5830i Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC Sensation XE with Beats Audio Build/AnZhi_04_057)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7105 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7100 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-I9103 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MI-ONE Plus Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; PULID F6 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; XT928 Build/6.7.2_GC-203-DNRCG-9)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; E15i Build/2.1.1.A.0.6)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT26i Build/6.1.A.2.45)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2SC MIUI/3.6.7)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; ZTE U880s Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; DROIDX Build/4.5.1_57_DX5-35)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; 9120 Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; C8650 Build/HuaweiC8650)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; C8650 Build/HuaweiC8650)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; Lenovo A60 Build/GRI40)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-I9300 Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; HTC Incredible S Build/GRI40)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A798t Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; C8650 Build/HuaweiC8650)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; JY-G2 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; MI-ONE C1 MIUI/3.5.3)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; HTC EVO 3D X515m Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; DOOV_D2 Build/DOOV)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-S5830 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; EBEST V5 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; LT22i Build/6.0.B.3.184)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S7500 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; HUAWEI T8600 Build/FRF91)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; Lenovo S720i Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-S5830 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB15.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I8262D Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; HUAWEI C8813Q Build/HuaweiC8813Q)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 8150D Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9300 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; W806 Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; 8150 Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE U790 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZOYE-V18 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HS-E910 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; U701T Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A278t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC Sensation Z710e Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MI-ONE C1 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; ST25i Build/6.0.B.1.564)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; S8600 Build/HuaweiS8600)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo A580 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9508 Build/JDQ39)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; M032 Build/JRO03H)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; K-Touch E806 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9300 Build/RUISI.ORG)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; C800 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9100G Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE V955 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9100G Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SCH-N719 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9105P Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; 8020 Build/GWK74)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-N7000 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; R817 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9082 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo A698t Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI C8810 Build/C8810V100R001C92B862)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo S880 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; M032 Build/JRO03H)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-I9100G Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI U8825D Build/HuaweiU8825D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9500 Build/JDQ39)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT18i MIUI/2.10.19)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; C8500 Build/ERE27)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 8190 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI C8650+ Build/V100R001C92B861)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5830i Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; M9 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; HTC Incredible S Build/GRI40)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo Y3t Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; T-smart G18 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-W999 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo S850e Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-W2013 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I8160 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HUAWEI C8812 Build/HuaweiC8812)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GN700T Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Galaxy Nexus Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A60 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7100 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; C01 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC EVO 3D X515m Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; PG86100 Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9103 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; U701 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7105 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2C MIUI/JLB16.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S7500 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A288t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R803 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5830i Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-P5110 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; ZTE U880s2 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC One X Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; DROID2 GLOBAL Build/4.5.1_57_D2GA-59)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HUAWEI C8813 Build/HuaweiC8813)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; TCLW989 Build/REL_2.3.3.1)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7100 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC EVO 3D X515m Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7102 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HUAWEI Y310-5000 Build/HuaweiY310-5000)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE N881E Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; U705T Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-S5830 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S720 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Desire S Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9103 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.1; GN708T Build/JOP40D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT18i Build/IMM76L)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-S5838 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2S MIUI/JLB16.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; XT390 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; GT-I9100 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328t Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7100 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; IdeaTab A2107A-H Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HTC T528t Build/JRO03H)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; WT19i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HUAWEI Y 220T Build/HuaweiY220T)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; R811 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; U820A Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I829 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI U8825D Build/HuaweiU8825D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo S7 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC Desire HD A9191 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A690 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Lenovo A820 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9500 Build/JDQ39)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; T700 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; vivo S3 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SGH-I897 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT26i Build/6.1.A.2.45)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-S5660 Build/FROYO)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; L336 Build/GRK39F)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I8160 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GN105 Build/GRJ22)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI T8950 Build/HuaweiT8950)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5360 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SHV-E210K Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC T528t Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MI-ONE Plus Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB16.0)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-i579 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; X907 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9300 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; CR888 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-W999 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; T8520 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-N719 Build/JZO54K)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2; HTC Desire Build/FRF91)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.3; OKA13 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MI-ONE C1 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A750 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GN106 Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A288t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9308 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N5100 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2SC MIUI/JLB13.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; M1 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; T29 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI C8650+ Build/V100R001C92B861)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; 7500 Build/FRG83G)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; vivo E1 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A288t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC T528t Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo E3 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; V926B Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; vivo S3 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7100 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; LT29i Build/9.1.B.0.411)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; K-Touch T619 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Coolpad 7020 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; HTC 802d Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A520 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; Galaxy Nexus Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I8262D Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-N7000 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 7260 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9300 Build/JZO54K)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-S5660 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC Incredible S Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2; ZTE-C N700 Build/FRF91)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1SC MIUI/ICS21.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A750 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9300 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I939D Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328w Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A278t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S MIUI/ICS22.0)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 4.0; MT6515M Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo S7 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S6102E Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9100 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Coolpad8050 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-W899 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-S5670 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A298t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; K-Touch U81t Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI-ONE Plus MIUI/3.1.25)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7108 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; W2013 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9103 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; HTC Z510d Build/GRJ22)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7102 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; HTC Wildfire S A510e Build/GRI40)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9300 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; Lenovo A60 Build/GRI40)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Coolpad 7019 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GN800 Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; R800i Build/4.0.2.A.0.58)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-B9062 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; R813T Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; 8150 Build/GWK74)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HTC T329t Build/JRO03H)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7100 Build/JZO54K)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5830i Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; T8830Pro Build/HuaweiT8830Pro)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-I9003 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 8190 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-N719 Build/JZO54K)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-T U880 Build/FRG83G)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9502 Build/JDQ39)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328d Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A798t Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I939 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9100G Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5690 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R801 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328w Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; U701T Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; SCH-i579 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-i909 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI C8860E Build/HuaweiC8860E)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; XT910 Build/6.7.2_GC-384-SPDU-801)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-i929 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562i Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9050 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-i509 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; HUAWEI G520-5000 Build/HuaweiG520-5000)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-I9300 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HUAWEI Y310-5000 Build/HuaweiY310-5000)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S6102E Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; SPHS on Hsdroid Build/MocorDroid4.0.4)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; DK P120 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GXQ-G3 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; MB525 Build/MIUI)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; MT870 Build/04.30.62)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HUAWEI Y310-5000 Build/HuaweiY310-5000)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; ST18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; K-Touch T789 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-S5570 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; Coolpad W706+ Build/FRG83G)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I739 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; AMOI_N806 Build/GRJ90)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; 5855 Build/MASTER)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC X515d Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-W2013 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-S5660 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.1; R815T Build/JOP40D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Coolpad 7019 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; DOOV_D600 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HUAWEI C8813 Build/HuaweiC8813)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SHV-E160S Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R801 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S MIUI/ICS21.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; T910 Build/IMM76D)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; C8500 Build/ERE27)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI T8833 Build/HuaweiT8833)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2; LG-P503 Build/FRF91)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; Lenovo A60 Build/GRI40)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-i929 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-I9003 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC A510e Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R805 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7102 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R805 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-N7000 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I699 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; lepad_001n Build/PQXU100.4.0073.021511)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MI-ONE Plus Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI-ONE Plus MIUI/2.9.7)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A800 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; T29 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; 5910 Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android unknown; SOP-W168 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0; 958 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Coolpad7295 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-N7000 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9505 Build/JDQ39)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.3; SCH-i809 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A288t Build/MocorDroid2.3.5)");
		list.add("Dalvik/0.0.0 (Linux; U; Android 1.5.1.16-RT-20120531.214856; K-Touch E619 Build/AliyunOs-2012)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; MT917 Build/6.7.2_GC-402)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; M040 Build/JRO03H)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9100G Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9308 Build/IMM76D)");
		list.add("Dalvik/0.0.0 (Linux; U; Android 1.6.1.11-RT-20120418.141734; W806 Build/AliyunOs-2012)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; K-Touch T6 Build/920510_8640_V1218)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S6352 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A65 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; 5870 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7108 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; GT-I9100 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9228 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ratech77_ics2 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5360 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I8160 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; GT-I9008L Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-P3100 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-N7102 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9082 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I919U Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-i919 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Coolpad 7295+ Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC S710d Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; Lenovo S720i Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; Nexus 7 Build/JDQ39)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI U8661 Build/HuaweiU8661)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; i6 Build/BOWAYBOWAY_i6)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; vivo E5 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A530 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9500 Build/JDQ39)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo S6T Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1SC MIUI/ICS24.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZOYE V16 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9003 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android Android4.1.1; SUNZIP TD998A Build/MocorDroid4.1.1)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo S6 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; MT15i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A789 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; ZTE U880s Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R807 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; AOLE 616 B Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328d Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; XT885 Build/6.7.2_GC-385)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9300 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; XT615 Build/V1.540)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; XT910 Build/6.7.3-94_SPI-324)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-W2013 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Coolpad 7019 Build/GINGERBREAD)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; X10i Build/2.0.A.0.504)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI C8812E Build/HuaweiC8812E)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U795 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; I9220 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; DOOV_D710 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I739 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5830i Build/GINGERBREAD)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; MT716 Build/ECLAIR)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; V02B Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; TD818 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; WP-W16 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; IdeaTabA2207A-H Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-I9000 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; TCL_A919 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; HTC C510e Build/GRI40)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; E909B+ Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9082 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MI-ONE Plus Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC Explorer A310e Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U960E Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2; GT-I5508 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; LT22i Build/6.2.A.0.400)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A288t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9220 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; MI-ONE Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; LNV-Lenovo_A560e Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC Incredible S Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562i Build/IMM76I)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.3; SCH-i809 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HUAWEI C8813D Build/HuaweiC8813D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9305 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; ChangHong V7 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-N7000 Build/GINGERBREAD)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; GT-I5508 Build/ERE27)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; CoolPad8028 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-N7000 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SC-06D Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 5860A Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9228 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; hexing15_6626_gb2 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; YEPEN P617 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; W102 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5830i Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.3; SCH-i909 Build/FROYO)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; Lenovo A390e Build/FRG83G)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7102 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo P700 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; DOOV_D300 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7000 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HUAWEI G510-0010 Build/HuaweiG510-0010)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7108 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-T U830 Build/FRF91)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A520 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.9; GT-I9300 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; GT-I9100G Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; vivo V2 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; DOOV_D9 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HTC T329w Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LT18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SHW-M240S Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-W999 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; R811 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; ONDA MID Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.1; Galaxy Nexus Build/JOP40D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; vivo V2 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; HTC S710d Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; i66 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.1; XREPIA Build/MocorDroid4.0.1)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S MIUI/ICS18.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; CoolPad8020+ Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; M032 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo S880 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; Liberty Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7108 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI Y210-2010 Build/HuaweiY210-2010)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GN135 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; XT390 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; TOOKY T1982 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-W2013 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S MIUI/ICS24.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HTC T329t Build/JRO03H)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9500 Build/JDQ39)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HUAWEI Y 220T Build/HuaweiY220T)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; YOORD D599 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U795 Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-i809 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; XT928 Build/6.7.2_GC-203-DNRCG-9)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S6352 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-S5670 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; YL-Coolpad 5210S Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Galaxy Note DK9500 B Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328w Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; YZ950 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; Blade Build/GWK74)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9300 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9082 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.9; GT-N7I02 Build/JRO03C)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.3; GT-I9008L Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S720 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC HD2 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Coolpad8050 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; HTC Z715e Build/GRJ22)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-I9003 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328w Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; EBEST V7 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9508 Build/JDQ39)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9300 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; W88 Build/YusunW88)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SHV-E210K Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; XT882 Build/SWDFS_M7_4.97.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; I5015 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; 9900 Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9000 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I879 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-B9388 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U880F1 Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-i909 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5360 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9300 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 8720 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; L36h Build/10.1.A.1.350)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7105 Build/JRO03C)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; GT-I9008L Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; VANO i600 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; SCH-I959 Build/JDQ39)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I699 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9300 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI C8812E Build/HuaweiC8812E)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; R813T Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; X909 Build/JRO03L)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE N880F Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GN135 Build/IMM76D)");
		list.add("Dalvik/0.0.0 (Linux; U; Android 1.5.1.22-RT-20120716.184214; K-Touch E619 Build/AliyunOs-2012)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.1.1; UOOGOU_Q7 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; KAVA V900 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC Rhyme S510b Build/GRJ90)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; T92 Build/FRG83G)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R801 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 8720 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; 8020 Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; YL-Coolpad 5210S Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S MIUI/ICS22.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-N7000 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB14.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; C600 Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC S510b Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I779 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; R817 Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-i589 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 8190 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SCH-N719 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LA-I Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9001 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; T703 Build/GRJ22)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2S MIUI/JLB14.0)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-i909 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-B9388 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; U1203 Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HUAWEI Y 220T Build/HuaweiY220T)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GN100 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; HUAWEI T8828 Build/HuaweiT8828)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-I9268 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/3.5.31)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I659 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; 5860S Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SCH-I939D Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I619 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R805 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; R817 Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7 Root Version; LT22i Build/unknown)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-I9100 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SK5 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2C MIUI/JLB16.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1SC MIUI/ICS21.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-N719 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC One S Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; ZTE-T U880 Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; GT-S5570 Build/GRI40)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; XD-US910 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I779 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S MIUI/ICS24.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Dorado-D18 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A798t Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.8; I9009A Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo S7t Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC A510e Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.1.1; eidolon15_nand_gb2 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 4G W4 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9100 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; LG-F180 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE V889M Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9100G Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HUAWEI C8812 Build/HuaweiC8812)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-N7000 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.0; U910B Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT28h Build/6.1.E.0.233)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; 7266 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7108 Build/JRO03C)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; C8600 Build/ERE27)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 5860 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC S710d Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI C8825D Build/HuaweiC8825D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; i1258_meiboer_i1268b Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; ST18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; vivo E5 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A60+ Build/LenovoLePhone)");
		list.add("Dalvik/1.4.0 (Linux; U; Android ??-4.0.0; IBL Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2S MIUI/JLB13.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; TSC Z3A Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI U8950D Build/HuaweiU8950D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HUAWEI C8813 Build/HuaweiC8813)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SHV-E120L Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; T900 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; MOT-XT788 Build/IRPMCT_B_02.74.00RPD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SCH-N719 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; IdeaTabA2207A-H Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; TD50 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; A301C Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; C868 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A630t Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; XT800+ Build/TTUPG_M7_2.45.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; MT680 Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC A510e Build/GRJ90)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-T U880 Build/FRG83G)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; XT390 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0; M2 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S880i Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE V889M Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I739 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-S7572 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9100 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 9900 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R803 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9105P Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; TCL S800 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S6358 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9100 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/3.6.7)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5360 Build/GINGERBREAD)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; C8500 Build/ERE27)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; 8810 Build/FRG83G)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; MOFOTO T6860 Build/MOFOTOMOFOTO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R807 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9300 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; V8 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9100 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.3; P150 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; T858 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; IS12S Build/6.1.D.1.91)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; TCL Y900 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HUAWEI Y 220T Build/HuaweiY220T)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7100 MIUI/3.5.3)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9220 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A780 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB16.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GN100 Build/GRK39F)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-T U880 Build/FRG83G)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; BF9200 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 7260 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; X909 Build/JRO03L)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.1; 9989 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; M2 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S686 Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; WT19i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; SCH-i579 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MOT-XT788 Build/IRPMCT_B_02.64.00RPD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I8150 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; M9 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HUAWEI C8812 Build/HuaweiC8812)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; HTC Wildfire S A510e Build/GRI40)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; MOFUT T5 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC J Z321e Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9100G Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; ZTE V987 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; A720 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE N880E Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; YP V610 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.2.1; T6198 Build/MocorDroid4.0.1)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A60+ Build/LenovoLePhone)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GHONG T618 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; D5 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; ZTE N909 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S720 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC A310e Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GN106 Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; TCL_A919 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HS-EG950 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; X907 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC One X Build/IML74K)");
		list.add("Dalvik/1.1.0 (Linux; U; Android 2.1-update1; HTC Wildfire Build/ERE27)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; GT-I9008L Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7568 Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2; GT-P1000 Build/FROYO)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-C N880S Build/FRF91)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE V970 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562i Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HS-U8 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 4G W4 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-i929 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GN700T Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Wishway V626 Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; T9500 Build/MocorDroid4.0.4)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-W2013 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; EBEST V5 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A750 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S890 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; AMOI N820 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; ZTE U887 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562i Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HUAWEI C8813 Build/HuaweiC8813)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC Desire S Build/GRJ90)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-T U880 Build/FRG83G)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9268 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I8268 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE N790 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 4G W4 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; dkb Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S MIUI/ICS24.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MI-ONE Plus Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A60+ Build/LenovoLePhone)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; K-Touch W619 Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A690 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 4G W4 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U960s3 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-I9300 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.1.1; uphone Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7000 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SHV-E160K Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; KliTON I50f Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Coolpad7295 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I8150 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE V788D Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC Desire HD Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A65 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-W2013 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LT18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo S11t Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328t Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MALATA Z100A Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; ALCATEL one touch 986+ Build/C986-2SALCN1)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB7.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; TCL S820 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HUAWEI Y300-0000 Build/HuaweiY300-0000)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GN106 Build/GRJ22)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.1; Galaxy Nexus Build/JOP40D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; HTC Sensation Z710e Build/GRJ22)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SCH-I939D Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; vivo X1 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U795 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HS-T930 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Coolpad 8056 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7568 Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-i809 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; TSC Z Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-I9300 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LT18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9228 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB15.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Coolpad7295 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-I9100 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI-ONE Plus MIUI/ICS24.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo S868t Build/S-2-02)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HS-EG909 Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5830i Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7568 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A298t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9103 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U880F1 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SCH-I939D Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I779 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI T8951 Build/HuaweiT8951)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; HUAWEI G520-0000 Build/HuaweiG520-0000)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7568 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A65 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Coolpad 5930 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI T8951 Build/HuaweiT8951)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9100G Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9220 Build/IMM76D)");
		list.add("Dalvik/0.0.0 (Linux; U; Android 1.5.1.23-RT-20120725.213712; K-Touch E619 Build/AliyunOs-2012)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S MIUI/ICS22.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; PC36100 Build/MIUI)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; S8300 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; C600 Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A278t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; ST25i Build/KA02 Xperia SSpeed)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2; LG-P503 Build/FRF91)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; LT22i Build/6.0.B.1.564)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-W999 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9003 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; U701T Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562i Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LT18i Build/4.0.2.A.0.42)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; X10i Build/3.0.1.G.0.75)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC S510b Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC C510e Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 5860A Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9050 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-P3100 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; X907 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Lenovo K860i Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SC-02E Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S720 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A789 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I779 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-C N700 Build/FRF91)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A298t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; KliTON I51 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE V788D Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LG-P970 Build/GRJ22)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9228 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Coolpad 5890 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; H500 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; HTC Desire Build/MIUI)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1S Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-N7000 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; T858 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SGH-I897 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HTC T528d Build/JRO03H)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MI-ONE C1 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; C8650 Build/HuaweiC8650)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LT18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE N880E Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; E5670 Build/GRJ22)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2; GT-P1000 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-W2013 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC T528t Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5660 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HDL_V8 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; IM-A760S Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MOFUT F1 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI Y210-0010 Build/HuaweiY210-0010)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-S5570 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; LT26i Build/6.0.A.3.67)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 4.0; U570B Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; ST18i Build/4.0.2.A.0.42)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A366t Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; R813T Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GN777 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HOOW A6 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; M030 Build/JRO03H)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GN205 Build/GRJ22)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo S12 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9220 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; YEPEN P666 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9228 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; n916 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; I5015C Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Haier HW-N86W Build/GRK39F)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; Skyworth EG6188 Build/HERA_00.04.76.15.48.H1016)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GOBO I7 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I879 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.8; I9300 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; MB855 Build/4.5.1A-1_SUN-198_7)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; DOOV_D720 Build/GRJ90)");
		list.add("Dalvik/0.0.0 (Linux; U; Android 1.5.2.0-RT-20120926.155001; K-Touch W619 Build/AliyunOs-2012)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC Incredible S Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT18i Build/4.1.B.0.587)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A800 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7102 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SCH-I939 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI C8860E Build/HuaweiC8860E)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; LG-P705 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S890 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; 8150 Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; S8600 Build/HuaweiS8600)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; GT-S5570 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-I939 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; MI-ONE Plus MIUI/3.5.24)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I8250 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A326 Build/GRK39F)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-i569 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; C868 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I919U Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7108 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; LT22i Build/6.0.B.1.564)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI C8812E Build/HuaweiC8812E)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-W899 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; U910 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; SCH-N719 Build/JZO54K)");
		list.add("Dalvik/0.0.0 (Linux; U; Android 2.0.0-R-20130514.1501; G9 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; M031 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE U790 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI C8825D Build/HuaweiC8825D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; SCH-N719 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo P70 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE V889D Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC Sensation XL with Beats Audio X315e Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; Coolpad 5930 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC Sensation XE with Beats Audio Z715e Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE N855D Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U807 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo S12 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC One V Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S686 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; VB S7 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I779 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC T528d Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-I619 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT26i Build/6.1.A.2.45)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; ST18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7 Root Version of Baidu Tieba Xperia?; ST25i Build/6.0.B.1.564 of DaveRocky in Xperia community.  Authorize?Tieba?Gfan?IT168)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HS-T912 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; Nexus 4 Build/JDQ39E)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GN708W Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; Desire HD Build/GRI40)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HTC T528t Build/JRO03H)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.1.1; T9688 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; NC700 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A60+ Build/LenovoLePhone)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.1; vivo X1St Build/JOP40D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ME525 Build/JDGC_2.10.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; M040 Build/JRO03H)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9001 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GN180 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB5.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HMI A5 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; W830 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo P700 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Q8 Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; K210v Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; AMOI N821 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE N880F Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; ME860 Build/4.5.3-118_OLY-14)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ME525+ Build/4.5.3-109_DPP-14)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GN135 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I8552 Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; LG-F160 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SHV-E210S Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; Galaxy Nexus Build/JDQ39)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; n903e Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android Android4.1.1; SUNZIP TD998A Build/MocorDroid4.1.1)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB10.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; Lenovo A68e Build/Lenovo_A68e_S016_111012)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; CoolPad8026 Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A278t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; HTC One X Build/JDQ39)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9228 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; SK17i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI C8650+ Build/V100R001C92B861)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-i929 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; AOLE 616 B Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; HTC Z510d Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A278t Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3; HTC A6390 Build/GRI40)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S5368 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U807 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.3; Y99 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; vivo X1 Build/JRO03C)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.1; SCH-i559 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; Lenovo S720i Build/JRO03C)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ME811 Build/SHDWR_X6_2.200.33)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A798t Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2S MIUI/JLB14.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; XT910 Build/6.7.2_GC-384)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9220 Build/GINGERBREAD)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; C8500S Build/FRG83G)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-B9120 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HUAWEI C8812 Build/HuaweiC8812)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LT18i Build/4.0.2.A.0.62)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9128V Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo S680 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; HUAWEI C8813 Build/HuaweiC8813)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7100 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; T29 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; K-Touch W619 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A300 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Lenovo A780 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; M040 Build/JRO03H)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U960s3 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; vivo Y3t Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; T8830Pro Build/HuaweiT8830Pro)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HS-U950 Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MI 1SC MIUI/ICS22.0)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2C MIUI/JLB15.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-W899 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-i929 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0; SPHS on Hsdroid Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9502 Build/JDQ39)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; MT15i Build/4.0.2.A.0.42)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A750 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HUAWEI Y310-5000 Build/HuaweiY310-5000)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; HTC Sensation Z710e Build/GRJ22)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; K-Touch W806+ Build/IMM76I)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT26ii Build/6.1.A.2.45)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.4; T9500 Build/MocorDroid4.0.4)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.8; I9009A Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; LT18i Build/4.0.2.A.0.42)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2S MIUI/JLB13.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9003 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; 5832 Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; N505 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0; U7 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9220 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; Desire S Build/MIUI)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC Sensation XL with Beats Audio X315e Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A789 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; A900 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; MASTONE G3 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-B9388 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; ZTE U880E Build/GWK74)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE N880E Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GN180 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; Coolpad 8056 Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; L-07C Build/GRI40)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 8720 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI C8950D Build/HuaweiC8950D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; 9120 Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HUAWEI Y210-2010 Build/HuaweiY210-2010)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; 8710 Build/GWK74)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-T U880 Build/FRG83G)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-S5660 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI T8951 Build/HuaweiT8951)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; SCH-i589 Build/FROYO)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; M040 Build/JRO03H)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-I9003 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; MOT-XT788 Build/IRPMCT_B_02.74.00RPD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; S2005A-H Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; HTC Desire HD A9191 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ING800 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; ST18i Build/4.0.2.A.0.69)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Lenovo A690 Build/GRK39F)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.3; SCH-i909 Build/FROYO)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-W899 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R803 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; ZTE U960s3 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; 9200I Build/GRK39F)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; 7500 Build/FRG83G)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; SHT-H539 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; GT-N7000 Build/GINGERBREAD)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; CoolPad8020+ Build/MocorDroid2.3.5)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.4; HTC Incredible S Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; D118 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; HUAWEI U9508 Build/HuaweiU9508)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; Lenovo P700 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-S7562i Build/IMM76I)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-B9120 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.2.2; GT-I9505 Build/JDQ39)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; HTC Sensation Z710e Build/GRJ22)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; SCH-i909 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo S720 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; ZTE V889D Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; XT928 Build/6.7.2_GC-203-DNRCG-9)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; MT917 Build/6.7.2_GC-131-DNRTD-20)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SHV-E210S Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A789 Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; LT26i Build/6.1.A.0.452)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A630t Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI C8826D Build/HuaweiC8826D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Android Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; MB525 Build/GWK74)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2S MIUI/JLB15.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; M9 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; ST25i Build/6.0.B.3.162)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-N7105T Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC T528d Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC T328t Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.0.3; DATANG S11 Build/V11_DATANG_2__D0_GT818X_nM-V1127B1842-usr)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; IdeaTab A2107A-H Build/IML74K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; 7230 Build/GRJ90)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9000 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; SCH-i929 Build/IMM76D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; R807 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A630t Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HTC Incredible S Build/IMM76D)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/JLB10.0)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-I9108 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; HUAWEI C8825D Build/HuaweiC8825D)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; DOOV_D770 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9108 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 4.1.1; E9220 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S6102 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2 MIUI/3.6.7)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; Haier HW-N86W Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-I9300 Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; HS-U8 Build/GRK39F)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9082 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.4; GT-I9100G Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; GT-I9070 Build/JZO54K)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; GT-S6108 Build/GINGERBREAD)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2S MIUI/3.6.7)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; U8800+ Build/HuaweiU8800Pro)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.3; C8650 Build/HuaweiC8650)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.7; LT26i Build/6.0.A.3.76)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; Lenovo A798t Build/IMM76D)");
		list.add("Dalvik/1.2.0 (Linux; U; Android 2.2.2; ZTE-T U880 Build/FRG83G)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; GT-N7105T Build/JRO03C)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.6; QX222 Build/GRK39F)");
		list.add("Dalvik/1.4.0 (Linux; U; Android 2.3.5; M9 Build/GRJ90)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; HTC EVO 3D X515m Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.1; vivo E5 Build/JRO03C)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.3; 8720 Build/IML74K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.1.2; HTC 802w Build/JZO54K)");
		list.add("Dalvik/1.6.0 (Linux; U; Android 4.0.4; GT-I9300 Build/IMM76D)");
		return list.get(new Random().nextInt(list.size()));
	}

	public static void main(String[] args) {
	}
}
