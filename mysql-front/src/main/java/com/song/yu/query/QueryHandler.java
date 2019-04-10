package com.song.yu.query;
/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 下午3:17
 */

import com.song.yu.protocol.ErrorPacket;
import com.song.yu.protocol.OKPacket;
import com.song.yu.protocol.QuitPacket;
import com.song.yu.threadpool.MysqlThreadPool;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import static com.song.yu.protocol.constant.ServerStatus.SERVER_STATUS_AUTOCOMMIT;


public class QueryHandler{

	private static final QueryHandler instance = new QueryHandler();
	private Map<QueryType, AbstractQuery> map = new HashMap<QueryType, AbstractQuery>();

	public QueryHandler() {
		map.put(QueryType.SELECT, new SelectQuery());
		map.put(QueryType.CREATE_DB, new CreateDatabaseQuery());
	}

	public static QueryHandler getInstance() {
		return instance;
	}


	public void handle(QueryInfo queryInfo) throws Exception {
		String sql = new String(queryInfo.getQueryPacket().message);
		String db = queryInfo.getDb();
		SocketChannel channel = queryInfo.getSocketChannel();

		sql = sql.replace("\n", " ");

		String[] queryArray = sql.split(" ");

		if (queryArray.length < 2) {
			//do nothing
			return;
		}
		final AbstractQuery query = map.get(QueryType.getTypeByFirstAndSecond(queryArray[0], queryArray[1]));

		if (query == null) {
			//throw new IllegalArgumentException("can't find handler for '" + sql + "'");
			//just return okPackage now

			ErrorPacket errorPacket = new ErrorPacket();
			errorPacket.errno = 1;
			errorPacket.message = "can't handle this sql".getBytes();

			ByteBuffer errorBuffer = ByteBuffer.allocate(1000);
			errorPacket.write(errorBuffer);

			channel.write(errorBuffer);



			/**
			try {
				OKPacket okPacket = new OKPacket();
				okPacket.affectedRows = 0;
				okPacket.serverStatus = SERVER_STATUS_AUTOCOMMIT;
				okPacket.message = "Query Ok".getBytes();
				ByteBuffer byteBuffer = ByteBuffer.allocate(128);
				if (channel.isConnected()) {
					channel.write(byteBuffer);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			 */

		}

		MysqlThreadPool.FRONT_QUERY.execute(() -> {
			query.query(queryInfo);
		});
	}



}
