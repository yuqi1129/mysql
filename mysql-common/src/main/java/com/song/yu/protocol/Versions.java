package com.song.yu.protocol;

/**
 * 
 * <pre><b>proxy's version.</b></pre>
 * @author 
 * @version 1.0
 */
public interface Versions {

	byte PROTOCOL_VERSION = 10;
	byte[] SERVER_VERSION = "m2o-proxy-5.6.0-snapshot".getBytes();
}