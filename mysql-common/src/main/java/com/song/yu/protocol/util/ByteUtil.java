package com.song.yu.protocol.util;

import com.song.yu.protocol.MysqlPacket;

/**
 * Created by 64669 on 2019/02/20.
 */
public class ByteUtil {

    /**
     *
     * @param mysqlPacket 利用多态使用mysqlPacket获得packetLength
     * @param bytes 接受到的字节内容
     */
    public static void bytesCut(MysqlPacket mysqlPacket ,byte[] bytes){
        System.arraycopy(bytes, mysqlPacket.packetLength+4, bytes, 0, bytes.length-mysqlPacket.packetLength-4);
    }

}
