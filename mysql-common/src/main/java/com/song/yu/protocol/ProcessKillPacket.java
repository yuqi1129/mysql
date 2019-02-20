package com.song.yu.protocol;

import com.song.yu.protocol.util.BufferUtil;

import java.nio.ByteBuffer;

/**
 * 
 * <pre><b>mysql process kill packet.</b></pre>
 * @author 
 */
public class ProcessKillPacket extends MysqlPacket {

	public byte flag = (byte) 0xfe;
	public int connectionId;

	@Override
	public void read(byte[] data) {
		MysqlMessage mm = new MysqlMessage(data);
		packetLength = mm.readUB3();
		packetId = mm.read();
		flag = mm.read();
		connectionId = mm.readInt();
	}

	@Override
	public void write(ByteBuffer buffer) {
		int size = calcPacketSize();
		BufferUtil.writeUB3(buffer, size);
		buffer.put(packetId);
		buffer.put(COM_PROCESS_KILL);
		BufferUtil.writeInt(buffer, connectionId);
	}

	@Override
	public int calcPacketSize() {
		return 5;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Process Kill Packet";
	}

}
