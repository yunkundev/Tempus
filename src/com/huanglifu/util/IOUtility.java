package com.huanglifu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 工具类，提供用于从/到制定文件读入/写出对象信息的便捷方法
 * @author Lifu.Huang
 *
 */
public class IOUtility 
{
	
	private final static String planFile;
	private final static String setupFile;
	private final static String historyFile;
	
	/**
	 * 初始化各配置文件位置
	 */
	static 
	{
		 planFile = "plan.bin";
		 setupFile = "setup.bin";
		 historyFile = "history.bin";
	}
	
	/**
	 * 将指定 {@link Serializable}类型的对象串行化到指定路径中
	 * @param path 指定路径
	 * @param obj 待串行化的对象
	 * @throws IOException 文件输入输出错误
	 */
	public static void storeData(String path, Serializable obj) throws IOException 
	{

		File f = new File(path);

		FileOutputStream fos = new FileOutputStream(f);
		try  
		{
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			try  
			{
				oos.writeObject(obj);
			} 
			finally 
			{
				oos.close();
			}
		}
		finally 
		{
			fos.close();
		}
		
	}

	/**
	 * 从指定路径中读取串行化数据并将其复原成对象
	 * @param path 串行化数据文件所在路径
	 * @return 复原后的对象
	 * @throws IOException	文件输入输出错误
	 * @throws ClassNotFoundException 没有找到对应的类
	 */
	public static Object readData(String path) throws IOException, ClassNotFoundException 
	{
		Object obj = null;
		File f = new File(path);
		FileInputStream fis = new FileInputStream(f);
		try  
		{
			ObjectInputStream ois = new ObjectInputStream(fis);
			try 
			{
				obj = ois.readObject();
			}
			finally 
			{
				ois.close();
			}
		} 
		finally
		{
			fis.close();
		}
		return obj;
	}

	public static String getPlanfile()
	{
		return planFile;
	}

	public static String getSetupfile() 
	{
		return setupFile;
	}
	
	public static String getHistoryfile() 
	{
		return historyFile;
	}

}
