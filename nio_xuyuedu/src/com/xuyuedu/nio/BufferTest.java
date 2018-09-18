package com.xuyuedu.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * 缓冲区NIO 提高传输文件和通道一起配合使用，存储数据
 * Buffer
 * ByteBuffer最多
 * Long Buffer
 * IntegerBuffer
 * FloatBuffer
 * DubboBuffer
 * @author DELL
 *
 */
public class BufferTest {

	/**
	 * position缓冲区正在操作的位置，默认从0开始
	 * limit：界面(缓冲区可用大小)
	 * capacity:缓冲区最大容量，一旦声明不能改变
	 * 
	 * 核心方法：
	 * put()往buffer存放数据
	 * get()获取数据
	 */
	@Test
	public void test001() {
		try {
			//初始化byteBuffer大小
			ByteBuffer allocate = ByteBuffer.allocate(1024);
			System.out.println("缓冲区正在操作的位置，默认从0开始:"+allocate.position());
			System.out.println("界面(缓冲区可用大小):"+allocate.limit());
			System.out.println("缓冲区最大容量，一旦声明不能改变:"+allocate.capacity());
			System.out.println("-----------------");
			System.out.println("往buffer存放数据。。。");
			allocate.put("asss".getBytes());
			System.out.println("缓冲区正在操作的位置，默认从0开始:"+allocate.position());
			System.out.println("界面(缓冲区可用大小):"+allocate.limit());
			System.out.println("缓冲区最大容量，一旦声明不能改变:"+allocate.capacity());
			System.out.println("-----------------");
			System.out.println("读取值。。。。。。。。。");
			//开启读取模式,读取完毕后，还原
			allocate.flip();
			System.out.println("缓冲区正在操作的位置，默认从0开始:"+allocate.position());
			byte[] bytes=new byte[allocate.limit()];
			allocate.get(bytes);
			System.out.println(new String(bytes,0,bytes.length));
			System.out.println("重复读。。。。。。。。。");
			//重复读取
			allocate.rewind();
			System.out.println("缓冲区正在操作的位置，默认从0开始:"+allocate.position());
			System.out.println("界面(缓冲区可用大小):"+allocate.limit());
			System.out.println("缓冲区最大容量，一旦声明不能改变:"+allocate.capacity());
			byte[] bytes2=new byte[allocate.limit()];
			allocate.get(bytes2);
			System.out.println(new String(bytes2,0,bytes2.length));
			System.out.println("清空缓存区。。。。。。。。。");
			//清空缓存区，数据被遗忘，值不清空，只是下标清空
			allocate.clear();
			System.out.println("缓冲区正在操作的位置，默认从0开始:"+allocate.position());
			System.out.println("界面(缓冲区可用大小):"+allocate.limit());
			System.out.println("缓冲区最大容量，一旦声明不能改变:"+allocate.capacity());
			System.out.println((char)allocate.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test002() {
		System.out.println("test002");
	}
}
