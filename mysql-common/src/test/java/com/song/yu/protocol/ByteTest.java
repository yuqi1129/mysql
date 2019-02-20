package com.song.yu.protocol;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 上午11:52
 */

public class ByteTest {
	public static void main(String[] args) {
		byte[] bytes = new byte[] {
				53, 46, 54, 46, 49, 54, 45, 108, 111, 103
		};

		System.out.println(new String(bytes));

		byte[] bs = "5.6.16-log".getBytes();

		for (int i = 0; i < bs.length; i++) {
			System.out.print(bs[i]);
		}
	}
}
