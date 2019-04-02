package com.song.yu.calcite.sqlnode;

import org.apache.calcite.sql.SqlCall;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlOperator;
import org.apache.calcite.sql.parser.SqlParserPos;
import org.apache.calcite.util.Litmus;

import java.util.List;
import java.util.Objects;

/**
 * Author yuqi
 * Time 1/4/19 21:32
 **/
public class MySQLCreateDatabase extends SqlCall {
    private String databaseName;
    private SqlParserPos parserPos;

    public MySQLCreateDatabase(String databaseName, SqlParserPos pos) {
        super(pos);
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public SqlOperator getOperator() {
        return null;
    }

    @Override
    public List<SqlNode> getOperandList() {
        return null;
    }
}
