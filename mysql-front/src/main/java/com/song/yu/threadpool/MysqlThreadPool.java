package com.song.yu.threadpool;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 下午4:06
 */

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MysqlThreadPool {
	public static final ThreadPoolExecutor FRONT_QUERY = new ThreadPoolExecutor(
			20,
			40,
			120,
			TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>(100)
	);


}
