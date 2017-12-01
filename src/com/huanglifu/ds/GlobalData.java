package com.huanglifu.ds;


import java.io.IOException;
import java.util.Locale;

import com.huanglifu.strategy.OptimalGenerator;
import com.huanglifu.strategy.PlainSpliter;
import com.huanglifu.strategy.TaskGenerator;
import com.huanglifu.strategy.TimeSpliter;
import com.huanglifu.util.IOUtility;
import com.tempus.gui.CensusActivity;
import com.tempus.gui.PlanActivity;
import com.tempus.gui.SetupActivity;
import com.tempus.gui.StartActivity;

public class GlobalData 
{
	static public PlanBoard planBoard = null;
	static public SetupScheme setupScheme = null;
	static public TaskRecorder taskRecorder = null;
	
	static public TaskGenerator taskGenerator = OptimalGenerator.getInstance();
	static public TodoList todoList = new TodoList();
	static public TimeBlockList timeBlockList = null;
	static public TimeSpliter timeSpliter = PlainSpliter.getInstance();
	
	static 
	{
		// Load Data from files
		readData();
		// for test
		/* for test
		
		try 
		{
			planBoard.addPlan(new Plan("word", "", 10, 1, 120, true));
			planBoard.addPlan(new Plan("review", "", 1, 10, 120, true));
			planBoard.addPlan(new Plan("fitness", "", 1, 1, 120, false));
			planBoard.addPlan(new Plan("Kill Heipang", "", 10, 10, 60, true));
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	*/
		
	}
	
	static public String secToString(int sec)
	{
		return String.format(Locale.CHINA, "%dСʱ%d����%d��", sec/3600,sec%3600/60,sec%60);
	}
	
	static public void refresh()
	{
		if(CensusActivity.context != null)
		{
			((CensusActivity)CensusActivity.context).refresh();
		}
		if(PlanActivity.context != null)
		{
			((PlanActivity)PlanActivity.context).refresh();
		}
		
		if(StartActivity.context != null) 
		{
			((StartActivity)StartActivity.context).refresh();
		}
		
		if(SetupActivity.context != null)
		{
			((SetupActivity)SetupActivity.context).refresh();
		}
	}
	public static void storeData()
	{
		try
		{
			IOUtility.storeData(IOUtility.getPlanfile(), planBoard);
		} 
		catch (IOException e1) 
		{
			//JOptionPane.showMessageDialog(MainFrame.this, "��ŷ���˼Ҹո������Ű�������ƻ������ǲ�֪��Ϊʲôʧ���ˡ�");
			e1.printStackTrace();
		} 
		
		try 
		{
			IOUtility.storeData(IOUtility.getSetupfile(), setupScheme);
		} 
		catch (IOException e1)
		{
			//JOptionPane.showMessageDialog(MainFrame.this, "��ŷ���˼Ҹո������Ű����������ã����ǲ�֪��Ϊʲôʧ���ˡ�");
			e1.printStackTrace();
		}
		
		try 
		{
			IOUtility.storeData(IOUtility.getHistoryfile(), taskRecorder);
		} 
		catch (IOException e1)
		{
			//JOptionPane.showMessageDialog(MainFrame.this, "��ŷ���˼Ҹո������Ű��������ϴεļƻ�ִ�м�¼�����ǲ�֪��Ϊʲôʧ���ˡ�");
			e1.printStackTrace();
		}
		
	}

	/**
	 * �������ļ��ж�ȡ��������
	 */
	public static void readData() 
	{
		try 
		{
			setupScheme = (SetupScheme)IOUtility.readData(IOUtility.getSetupfile());
		}
		catch (ClassNotFoundException e)
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "��ŷ�������������ļ�����򲻿���������������ʹ��Ĭ�������ˡ��������Ϣ���������߰ɣ�");
//			e.printStackTrace();
			setupScheme = new SetupScheme();
			setupScheme.setDefault();
			System.out.println("ϵͳû�ҵ��ƻ��ļ������ڽ���ʱ����");
		}
		catch (IOException e) 
		{
			setupScheme = new SetupScheme();
			setupScheme.setDefault();
			System.out.println("ϵͳû�ҵ��ƻ��ļ������ڽ���ʱ����");
		}
		
		try 
		{
			planBoard = (PlanBoard)IOUtility.readData(IOUtility.getPlanfile());
		} 
		catch (ClassNotFoundException e) 
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "��ŷ�������Ҳ��ã���ԭ���ļƻ��ļ��Ҳ����ˡ��������Ϣ���������߰ɣ�");
//			e.printStackTrace();
			planBoard = new PlanBoard();
			System.out.println("ϵͳû�ҵ��ƻ��ļ������ڽ���ʱ����");
		}
		catch (IOException e)
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "��ŷ�������Ҳ��ã���ԭ���ļƻ��ļ��Ҳ����ˡ��������Ϣ���������߰ɣ�");
//			e.printStackTrace();
			planBoard = new PlanBoard();
			System.out.println("ϵͳû�ҵ��ƻ��ļ������ڽ���ʱ����");
		}
		
		try 
		{
			taskRecorder = (TaskRecorder)IOUtility.readData(IOUtility.getHistoryfile());
		} 
		catch (ClassNotFoundException e)
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "��ŷ�������Ҳ��ã���ԭ���ļƻ��ļ��Ҳ����ˡ��������Ϣ���������߰ɣ�");
//			e.printStackTrace();
			taskRecorder = new TaskRecorder();
			System.out.println("ϵͳû�ҵ���ʷ��¼�ļ������ڽ���ʱ����");
		}
		catch (IOException e) 
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "��ŷ�������Ҳ��ã���ԭ���ļƻ��ļ��Ҳ����ˡ��������Ϣ���������߰ɣ�");
//			e.printStackTrace();
			taskRecorder = new TaskRecorder();
			System.out.println("ϵͳû�ҵ���ʷ��¼�ļ������ڽ���ʱ����");
		}

	}
}

