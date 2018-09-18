package com.xuyuedu.nio;

import java.nio.ByteBuffer;

public class Test002 {

	public static void main(String[] args) {
		ByteBuffer byteBuffer  = ByteBuffer.allocate(10);
		String string="abcd";
		byteBuffer.put(string.getBytes());
		//开启读的模式
		byteBuffer.flip();
		byte[] bytes=new byte[byteBuffer.limit()];
		byteBuffer.get(bytes,0,2);
		//标记 打印标记
		byteBuffer.mark();
		System.out.println(new String(bytes,0,2));
		System.out.println("缓冲区正在操作的位置，默认从0开始:"+byteBuffer.position());
		System.out.println("------------------------");
		
		System.out.println("缓冲区正在操作的位置，默认从0开始:"+byteBuffer.position());
		byteBuffer.get(bytes,2,2);
		System.out.println(new String(bytes,2,2));
		//还原到mark位置
		byteBuffer.reset();
		System.out.println("缓冲区正在操作的位置，默认从0开始:"+byteBuffer.position());
	}
}
