package com.song.yu.protocol;

import java.nio.ByteBuffer;

import com.song.yu.protocol.util.BufferUtil;

/**
 * 
 * <pre><b>statistics command packet.</b></pre>
 * @see http://dev.mysql.com/doc/internals/en/com-statistics.html
 */
public class StatisticsPacket extends MysqlPacket {

	public byte payload;
	
	@Override
	public int calcPacketSize() {
		return 1;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Statistics Packet";
	}

	@Override
	public void read(byte[] data) {
		MysqlMessage mm = new MysqlMessage(data);
		packetLength = mm.readUB3();
		packetId = mm.read();
		payload = mm.read();
	}

	@Override
	public void write(ByteBuffer buffer) {
		int size = calcPacketSize();
		BufferUtil.writeUB3(buffer, size);
		buffer.put(packetId);
		buffer.put(COM_STATISTICS);
	}

}
