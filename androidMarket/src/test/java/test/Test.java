package test;

import java.util.Arrays;

import com.oppo.test.OppoHttpURLConnection;

public class Test {

	public static void main(String[] args) {
		byte[] b = { 8, 7, 16, 1, 50, 8, 90, 84, 69, 32, 85, 57, 51, 48, 72,
				15, -128, 1, 20, -120, 1, 1, -112, 1, 0, -96, 1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, 1 };
		System.out.println(Arrays.toString(OppoHttpURLConnection
				.compressGzip(b)));
	}
}
