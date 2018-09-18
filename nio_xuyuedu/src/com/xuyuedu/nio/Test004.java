package com.xuyuedu.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * 分散读取，聚集写入
分散读取：将通道中的数据分散到多个缓冲区。
聚集写入：将多个缓冲区的数据聚集到通道中。
 * @author DELL
 *
 */
public class Test004 {

	public static void main(String[] args) throws Exception {
		//随机访问
		RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
		//获取NIO通道
		FileChannel channel = raf.getChannel();
		//分配指定大小缓冲区
		ByteBuffer buffer1 = ByteBuffer.allocate(100);
		ByteBuffer buffer2 = ByteBuffer.allocate(1024);
		//分散读取
		ByteBuffer[]buffers= {buffer1,buffer2};
		channel.read(buffers);
		for(ByteBuffer byteBuffer:buffers) {
			//切换成读模式
			byteBuffer.flip();
		}
		System.out.println(new String(buffers[0].array(),0,buffers[0].limit()));
		System.out.println("*****************");
		System.out.println(new String(buffers[1].array(),1,buffers[1].limit()));
		System.out.println("-----聚集读取-----");
		RandomAccessFile randomAccessFile=new RandomAccessFile("test2.txt", "rw");
		//获取通道
		FileChannel channel2 = randomAccessFile.getChannel();
		channel2.write(buffers);
		//关闭
		randomAccessFile.close();
		raf.close();
	}
}
