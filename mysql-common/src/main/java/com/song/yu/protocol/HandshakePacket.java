package com.song.yu.protocol;

import com.song.yu.protocol.util.BufferUtil;

import java.nio.ByteBuffer;


/**
 * also see alchemystar.lancelot.common.net.proto.mysql.HandshakePacket
 * <pre><b>AuthPacket means mysql initial handshake packet.</b></pre>
 * @author 
 */
public class HandshakePacket extends MysqlPacket {
	private static final byte[] FILLER_13 = new byte[] { 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0 };

	public byte protocolVersion;
	public byte[] serverVersion;
	public long threadId;
	public byte[] seed;
	public int serverCapabilities;
	public byte serverCharsetIndex;
	public int serverStatus;
	public byte[] restOfScrambleBuff;//这个其实就是seed2

	@Override
	public void read(byte[] data) {
		MysqlMessage mm = new MysqlMessage(data);
		packetLength = mm.readUB3();
		packetId = mm.read();
		protocolVersion = mm.read();
		serverVersion = mm.readBytesWithNull();
		threadId = mm.readUB4();
		seed = mm.readBytesWithNull();
		serverCapabilities = mm.readUB2();
		serverCharsetIndex = mm.read();
		serverStatus = mm.readUB2();
		mm.move(13);
		restOfScrambleBuff = mm.readBytesWithNull();
	}

	@Override
	public int calcPacketSize() {
		int size = 1;
		size += serverVersion.length;
		size += 5;
		size += seed.length;
		size += 19;
		size += restOfScrambleBuff.length;
		size += 1;
		return size;
	}

	@Override
	public void write(ByteBuffer buffer) {
		BufferUtil.writeUB3(buffer, calcPacketSize());
		buffer.put(packetId);
		buffer.put(protocolVersion);
		BufferUtil.writeWithNull(buffer, serverVersion);
		BufferUtil.writeUB4(buffer, threadId);
		BufferUtil.writeWithNull(buffer, seed);
		BufferUtil.writeUB2(buffer, serverCapabilities);
		buffer.put(serverCharsetIndex);
		BufferUtil.writeUB2(buffer, serverStatus);
		buffer.put(FILLER_13);
		BufferUtil.writeWithNull(buffer, restOfScrambleBuff);
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Handshake Packet";
	}

}