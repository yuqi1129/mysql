package com.song.yu.query;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 下午3:25
 */

import com.song.yu.protocol.QueryPacket;

import java.nio.channels.SocketChannel;

public class QueryInfo {
	private QueryPacket queryPacket;
	private SocketChannel socketChannel;

	private String db;

	public QueryInfo(QueryPacket queryPacket, SocketChannel socketChannel, String db) {
		this.queryPacket = queryPacket;
		this.socketChannel = socketChannel;
		this.db = db;
	}

	public QueryPacket getQueryPacket() {
		return queryPacket;
	}

	public SocketChannel getSocketChannel() {
		return socketChannel;
	}

	public String getDb() {
		return db;
	}
}
