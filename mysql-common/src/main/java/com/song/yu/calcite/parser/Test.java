package com.song.yu.calcite.parser;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/22 下午11:36
 */

import com.google.common.collect.Lists;
import org.apache.calcite.avatica.util.TimeUnit;
import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.rex.RexBuilder;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		RexBuilder builder = new RexBuilder(new JavaTypeFactoryImpl());
		List<RexNode> op = Lists.newArrayList();
		op.add(builder.makeFlag(TimeUnit.MONTH));
		//op.add(builder.makeL)
		builder.makeCall(
				SqlStdOperatorTable.TIMESTAMP_ADD
		);
	}
}
