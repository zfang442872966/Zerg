package test;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IProtocol {

	public static void main(String[] args) {
		try {
			System.out.println(InetAddress.getLocalHost());
			System.out.println(Inet4Address.getLocalHost());
			System.out.println(Inet6Address.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
