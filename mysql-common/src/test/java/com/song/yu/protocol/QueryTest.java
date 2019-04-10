package com.song.yu.protocol;

/**
 * Created by yuqi on 2019/02/20.
 */
public class QueryTest {

    public static void main(String args[]) throws Exception{
        Query query = new Query();
        String host = "127.0.0.1";
        int port = 3307;
        String user = "root";
        String password = "tdlab401";
        String dataBase = "data";
        String sqlStr = "SELECT name,author FROM `paper` limit 0,2;";
    }

}
