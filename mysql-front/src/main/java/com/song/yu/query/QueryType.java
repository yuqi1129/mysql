package com.song.yu.query;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 下午3:47
 */

public enum QueryType {
	SELECT("SELECT", null,  0),
	CREATE_DB("CREATE", "DATABASE", 1),
	CREATE_TABLE("CREATE", "TABLE", 2);

	//more to add

	private String first;
	private String second;
	private int type;

	QueryType(String first, String second, int type) {
		this.first = first;
		this.second = second;
		this.type = type;
	}

	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}

	public int getType() {
		return type;
	}

	public static QueryType getTypeByFirstAndSecond(String first, String second) {
		for (QueryType queryType : QueryType.values()) {

			if (queryType.first.equals(first) && (second == null || queryType.second == null || queryType.second.equals(second))) {
				return queryType;
			}
		}

		return null;
	}
}
