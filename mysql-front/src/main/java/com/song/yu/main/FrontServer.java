package com.song.yu.main;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/19 下午8:22
 */

import com.song.yu.server.MysqlServer;

public class FrontServer {
	public static void main(String[] args) {
		int port = 3307;
		new MysqlServer(port).start();

	}
}
