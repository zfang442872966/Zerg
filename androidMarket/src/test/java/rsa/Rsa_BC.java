package rsa;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

public class Rsa_BC {

	private static int a(char paramChar, int paramInt) throws IllegalArgumentException {
		int i = Character.digit(paramChar, 16);
		if (i == -1)
			throw new IllegalArgumentException("Illegal hexadecimal character " + paramChar + " at index "
					+ paramInt);
		return i;
	}

	public static byte[] a(char[] paramArrayOfChar) throws IllegalArgumentException {
		int i = 0;
		int j = paramArrayOfChar.length;
		if ((j & 0x1) != 0)
			throw new IllegalArgumentException("Odd number of characters.");
		byte[] arrayOfByte = new byte[j >> 1];
		for (int k = 0;; k++) {
			if (i >= j) {
				return arrayOfByte;
			}
			int m = a(paramArrayOfChar[i], i) << 4;
			int n = i + 1;
			int i1 = m | a(paramArrayOfChar[n], n);
			i = n + 1;
			arrayOfByte[k] = ((byte) i1);
		}
	}

	private static byte[] a(Key key, byte abyte0[]) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA", "BC");
		cipher.init(1, key);
		int l = abyte0.length;
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		int i1 = 0;
		int j1 = 0;
		do {
			if (l - j1 <= 0) {
				byte abyte2[] = bytearrayoutputstream.toByteArray();
				bytearrayoutputstream.close();
				return abyte2;
			}
			byte abyte1[];
			int k1;
			if (l - j1 > 117)
				abyte1 = cipher.doFinal(abyte0, j1, 117);
			else
				abyte1 = cipher.doFinal(abyte0, j1, l - j1);
			bytearrayoutputstream.write(abyte1, 0, abyte1.length);
			k1 = i1 + 1;
			j1 = k1 * 117;
			i1 = k1;
		} while (true);
	}

	/**
	 * RSA
	 * 
	 * @param key
	 * @param abyte0
	 * @return
	 * @throws Exception
	 */
	public static byte[] a2(Key key, byte abyte0[]) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA", "BC");
		cipher.init(1, key);
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		byte abyte1[] = cipher.doFinal(abyte0, 0, abyte0.length);
		bytearrayoutputstream.write(abyte1, 0, abyte1.length);
		byte abyte2[] = bytearrayoutputstream.toByteArray();
		bytearrayoutputstream.close();
		return abyte2;
	}

	public static void main(String[] args) {
		// String str3 = UUID.randomUUID().toString().replaceAll("-", "");
		String str3 = "89cbeb0073bf4e2b91a227f211e6b62b";
		// String str3 = "popozh RSA test";
		System.out.println("randomUUID:" + str3);
		String rsa_key = "30819F300D06092A864886F70D010101050003818D00308189028181008F757553E3217531CF77B6FA1F5A9548256FED127CB14CA89402057350F4C4F2FC17E4B7FE3420BDBB598BC7F3D01F5F85B81C6F618913A372C3F3E656D31A5B1517B695E176124EEBB9BA18BF29611CCC70AB91CFB64352824442CBDBBF359FE8CAB635F0566A7E1819664968358E38A1AA1247231BFEB6807E3154265081FD0203010001";
		byte[] arrayOfByte = a(rsa_key.toCharArray());

		try {
			// RSA加密
			byte[] b = a(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arrayOfByte)),
					str3.getBytes());
			System.out.println(Arrays.toString(b));
			System.out.println(new String(Base64.encodeBase64(b), "UTF-8"));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
