package com.song.yu.protocol;

import com.song.yu.protocol.util.BufferUtil;

import java.nio.ByteBuffer;

/**
 * 
 * <pre><b>quit command packet.</b></pre>
 * @author 
 */
public class QuitPacket extends MysqlPacket {

	public byte payload;
	
	@Override
	public int calcPacketSize() {
		return 1;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Quit Packet";
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
		buffer.put(COM_QUIT);
	}

}
