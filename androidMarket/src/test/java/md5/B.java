package md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 * @author zfang
 *
 */
public class B {
	
	public static String a(byte[] paramArrayOfByte, String paramString, boolean paramBoolean) {
		StringBuilder localStringBuilder = new StringBuilder();
		int i = paramArrayOfByte.length;
		for (int j = 0; j < i; j++) {
			String str = Integer.toHexString(0xFF & paramArrayOfByte[j]);
			if (paramBoolean)
				str = str.toUpperCase();
			if (str.length() == 1)
				localStringBuilder.append("0");
			localStringBuilder.append(str).append(paramString);
		}
		return localStringBuilder.toString();
	}

	public static String a(byte[] paramArrayOfByte, boolean paramBoolean) {
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.reset();
			localMessageDigest.update(paramArrayOfByte);
			String str = a(localMessageDigest.digest(), "", paramBoolean);
			return str;
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			throw new RuntimeException(localNoSuchAlgorithmException);
		}
	}

	public static void main(String[] args) {
		//F2A4850FF85358E2F1B39A669986F2C2
		System.out.println(B.a("com.baidu863994013262092948f5a62bad643b1".getBytes(), true));
	}

}
