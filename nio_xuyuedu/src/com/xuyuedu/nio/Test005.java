package com.xuyuedu.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class Test005 {

	public static void main(String[] args) throws CharacterCodingException {
		// 获取编码器
		Charset cs1 = Charset.forName("GBK");
		// 获取编码器
		CharsetEncoder ce = cs1.newEncoder();
		// 获取解码器
		CharsetDecoder cd = cs1.newDecoder();
		CharBuffer cBuf = CharBuffer.allocate(1024);
		cBuf.put("须臾断点内郡!");
		cBuf.flip();
		// 编码加密
		ByteBuffer bBuf = ce.encode(cBuf);
		for (int i = 0; i < 12; i++) {
			System.out.println(bBuf.get());
		}
		//编码解密
		bBuf.flip();
		CharBuffer cBuf2 = cd.decode(bBuf);
		System.out.println(cBuf2.toString());
		System.out.println("-------------------------------------");
		Charset cs2 = Charset.forName("GBK");
		bBuf.flip();
		CharBuffer cbeef = cs2.decode(bBuf);
		System.out.println(cbeef.toString());
	}

}
