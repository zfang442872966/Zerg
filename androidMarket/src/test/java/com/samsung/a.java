package com.samsung;

import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;

public class a implements ConnPerRoute {
	final NetAPI a;

	a(NetAPI netapi) {
		a = netapi;
	}

	@Override
	public int getMaxForRoute(HttpRoute route) {
		return 10;
	}

}
