package com.song.yu.procotol;
/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/20 上午11:11
 */

import com.song.yu.protocol.HandshakePacket;
import com.song.yu.protocol.Versions;
import com.song.yu.protocol.constant.ServerConstant;

import java.util.concurrent.atomic.AtomicLong;

import static com.song.yu.protocol.Versions.PROTOCOL_VERSION;
import static com.song.yu.protocol.constant.ServerConstant.REST_OF_SCRAMBLE;
import static com.song.yu.protocol.constant.ServerStatus.SERVER_STATUS_AUTOCOMMIT;

public class ProtocolUtil {

	public static final AtomicLong PACKET_ID = new AtomicLong(0);


	public static HandshakePacket createHandShakePacket() {
		HandshakePacket handshakePacket = new HandshakePacket();

		handshakePacket.protocolVersion = PROTOCOL_VERSION;
		handshakePacket.serverVersion = Versions.SERVER_VERSION;
		handshakePacket.threadId = Thread.currentThread().getId();

		handshakePacket.seed = ServerConstant.SEED;
		handshakePacket.serverCapabilities = 63487;
		handshakePacket.serverCharsetIndex = 8;
		handshakePacket.serverStatus = SERVER_STATUS_AUTOCOMMIT;
		handshakePacket.restOfScrambleBuff = REST_OF_SCRAMBLE;
		handshakePacket.packetId = (byte) PACKET_ID.getAndIncrement();

		//
		//协议
		return handshakePacket;
	}



}
