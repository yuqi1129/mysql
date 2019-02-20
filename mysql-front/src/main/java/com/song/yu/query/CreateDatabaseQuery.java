package com.song.yu.query;
/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 下午4:11
 */

import com.song.yu.protocol.QueryPacket;

public class CreateDatabaseQuery extends AbstractQuery {

	@Override
	public void query(QueryInfo info) {
		QueryPacket packet = info.getQueryPacket();

		String sql = new String(packet.message);
		return;
	}
}
