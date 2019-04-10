package com.song.yu.server;
/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/19 下午8:24
 */

import com.song.yu.procotol.ProtocolUtil;
import com.song.yu.protocol.*;
import com.song.yu.protocol.util.BufferUtil;
import com.song.yu.query.QueryHandler;
import com.song.yu.query.QueryInfo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static com.song.yu.protocol.constant.ServerStatus.SERVER_STATUS_AUTOCOMMIT;

public class MysqlServer extends Thread {
	private int port;
	private volatile boolean isRunning = true;
	private static final AtomicInteger i = new AtomicInteger(1);


	private Set<SocketChannel> channels = new HashSet<SocketChannel>();


	public MysqlServer(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);

			Selector selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			while (isRunning) {
				int keys = selector.select();
				if (keys == 0) {
					continue;
				}

				Set<SelectionKey> selectedKeys = selector.selectedKeys();

				Iterator<SelectionKey> it = selectedKeys.iterator();

				while (it.hasNext()) {
					SelectionKey key = it.next();
					it.remove();

					if (key.isAcceptable()) {
						SocketChannel client = serverSocketChannel.accept();
						HandshakePacket hsp = ProtocolUtil.createHandShakePacket();
						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						hsp.write(byteBuffer);

						byteBuffer.flip();
						client.configureBlocking(false);
						client.write(byteBuffer);
						//now write package to

						client.register(selector, SelectionKey.OP_READ);

					} if (key.isReadable()) {
						SocketChannel socketChannel = (SocketChannel) key.channel();
						//read byte_buffer and get key()


						ByteBuffer buffer;
						if (channels.contains(socketChannel)) {
							//this is a query/package
							buffer = ByteBuffer.allocate(1024 * 1024);
							//now this is a normal query;
							socketChannel.read(buffer);
							QueryPacket queryPacket = new QueryPacket();

							buffer.flip();
							int remaining = buffer.remaining();
							byte[] content = new byte[remaining];
							for (int i = 0; i < remaining; i++) {
								content[i] = buffer.get();
							}

							try {
								queryPacket.read(content);

								QueryInfo queryInfo = new QueryInfo(queryPacket, socketChannel, null);

								QueryHandler.getInstance().handle(queryInfo);
							} catch (Exception e) {
								ByteBuffer b = ByteBuffer.allocate(1000);

								OKPacket okPacket = new OKPacket();
								okPacket.affectedRows = 0;
								okPacket.serverStatus = SERVER_STATUS_AUTOCOMMIT;
								okPacket.insertId = i.getAndIncrement();
								okPacket.packetId = (byte) i.getAndIncrement ();
								okPacket.message = "Connect success".getBytes();

								okPacket.write(b);
								socketChannel.write(b);
							}

						} else {
							buffer = ByteBuffer.allocate(1024);

							socketChannel.read(buffer);
							buffer.flip();
							AuthPacket authPacket = new AuthPacket();
							authPacket.read(buffer.array());

							//do Auth
							////-------------------------->



							//do process
							buffer.clear();
							OKPacket okPacket = new OKPacket();
							okPacket.affectedRows = 0;
							okPacket.serverStatus = SERVER_STATUS_AUTOCOMMIT;
							okPacket.insertId = i.getAndIncrement();
							okPacket.packetId = (byte) i.getAndIncrement ();
							okPacket.message = "Connect success".getBytes();
							okPacket.write(buffer);
							buffer.flip();
							socketChannel.write(buffer);

							if (socketChannel.isOpen() && socketChannel.isConnected()) {
								channels.add(socketChannel);
							}

						}

//						socketChannel.read(buffer);
//						buffer.flip();


					}
				}

			}


		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
