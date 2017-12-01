package com.huanglifu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * �����࣬�ṩ���ڴ�/���ƶ��ļ�����/д��������Ϣ�ı�ݷ���
 * @author Lifu.Huang
 *
 */
public class IOUtility 
{
	
	private final static String planFile;
	private final static String setupFile;
	private final static String historyFile;
	
	/**
	 * ��ʼ���������ļ�λ��
	 */
	static 
	{
		 planFile = "plan.bin";
		 setupFile = "setup.bin";
		 historyFile = "history.bin";
	}
	
	/**
	 * ��ָ�� {@link Serializable}���͵Ķ����л���ָ��·����
	 * @param path ָ��·��
	 * @param obj �����л��Ķ���
	 * @throws IOException �ļ������������
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
	 * ��ָ��·���ж�ȡ���л����ݲ����临ԭ�ɶ���
	 * @param path ���л������ļ�����·��
	 * @return ��ԭ��Ķ���
	 * @throws IOException	�ļ������������
	 * @throws ClassNotFoundException û���ҵ���Ӧ����
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
