package com.song.yu.protocol;

import com.song.yu.protocol.util.BufferUtil;

import java.nio.ByteBuffer;

/**
 * 
 * <pre><b>column count packet.</b></pre>
 * @author 
 */
public class ColumnCountPacket extends MysqlPacket {

	public int columnCount;

	public void read(byte[] data) {
		MysqlMessage mm = new MysqlMessage(data);
		this.packetLength = mm.readUB3();
		this.packetId = mm.read();
		this.columnCount = (int) mm.readLength();
	}

	@Override
	public void write(ByteBuffer buffer) {
		int size = calcPacketSize();
		BufferUtil.writeUB3(buffer, size);
		buffer.put(packetId);
		BufferUtil.writeLength(buffer, columnCount);
	}

	@Override
	public int calcPacketSize() {
		int size = BufferUtil.getLength(columnCount);
		return size;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Column Count Packet";
	}

}
