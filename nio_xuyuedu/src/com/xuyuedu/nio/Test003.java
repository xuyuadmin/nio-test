package com.xuyuedu.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class Test003 {
	@Test
	//直接缓冲区
	public void test002() throws Exception {
		long startTime=System.currentTimeMillis();
		//创建管道
		FileChannel inChannel = FileChannel.open(Paths.get("F:\\第八节(多线程运行状态).mp4"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("F:\\第1节(多线程运行状态).mp4"),StandardOpenOption.READ, StandardOpenOption.WRITE,StandardOpenOption.CREATE);
		MappedByteBuffer inMap = inChannel.map(MapMode.READ_ONLY,0, inChannel.size());
		MappedByteBuffer outMap = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		//直接对缓冲区操作
		byte[] dsf=new byte [inMap.limit()];
		inMap.get(dsf);
		outMap.put(dsf);
		inChannel.close();
		outChannel.close();
		long endTime=System.currentTimeMillis();
		System.out.println("直接操作直接缓冲区耗时时间："+(endTime-startTime)+"ms");
	}
	@Test
	//非直接缓冲区
	public void test001() throws Exception{
		long startTime=System.currentTimeMillis();
		//读入流
		FileInputStream fst = new FileInputStream("F:\\第八节(多线程运行状态).mp4");
		//写入流
		FileOutputStream fos = new FileOutputStream("F:\\第1节(多线程运行状态).mp4");
		//创建读入流通道
		FileChannel inChannel = fst.getChannel();
		//创建写入流通道
		FileChannel outChannel = fos.getChannel();
		//分配指定大小缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while(inChannel.read(buffer)!=-1) {
			//开启读取模式
			buffer.flip();
			//将数据写入到通道中
			outChannel.write(buffer);
			buffer.clear();
		}
		//关闭通道，关闭连接
		inChannel.close();
		outChannel.close();
		fos.close();
		fst.close();
		long endTime=System.currentTimeMillis();
		System.out.println("非直接操作直接缓冲区耗时时间："+(endTime-startTime)+"ms");
	}
}
