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
		return String.format(Locale.CHINA, "%d小时%d分钟%d秒", sec/3600,sec%3600/60,sec%60);
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
			//JOptionPane.showMessageDialog(MainFrame.this, "啊欧，人家刚刚在试着帮您保存计划表，但是不知道为什么失败了。");
			e1.printStackTrace();
		} 
		
		try 
		{
			IOUtility.storeData(IOUtility.getSetupfile(), setupScheme);
		} 
		catch (IOException e1)
		{
			//JOptionPane.showMessageDialog(MainFrame.this, "啊欧，人家刚刚在试着帮您保存设置，但是不知道为什么失败了。");
			e1.printStackTrace();
		}
		
		try 
		{
			IOUtility.storeData(IOUtility.getHistoryfile(), taskRecorder);
		} 
		catch (IOException e1)
		{
			//JOptionPane.showMessageDialog(MainFrame.this, "啊欧，人家刚刚在试着帮您保存上次的计划执行记录，但是不知道为什么失败了。");
			e1.printStackTrace();
		}
		
	}

	/**
	 * 从数据文件中读取各项数据
	 */
	public static void readData() 
	{
		try 
		{
			setupScheme = (SetupScheme)IOUtility.readData(IOUtility.getSetupfile());
		}
		catch (ClassNotFoundException e)
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "啊欧，您保存设置文件好像打不开了诶，想在智能使用默认设置了。把这个消息告诉我作者吧！");
//			e.printStackTrace();
			setupScheme = new SetupScheme();
			setupScheme.setDefault();
			System.out.println("系统没找到计划文件，将在结束时创捷");
		}
		catch (IOException e) 
		{
			setupScheme = new SetupScheme();
			setupScheme.setDefault();
			System.out.println("系统没找到计划文件，将在结束时创捷");
		}
		
		try 
		{
			planBoard = (PlanBoard)IOUtility.readData(IOUtility.getPlanfile());
		} 
		catch (ClassNotFoundException e) 
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "啊欧，都是我不好，您原来的计划文件找不到了。把这个消息告诉我作者吧！");
//			e.printStackTrace();
			planBoard = new PlanBoard();
			System.out.println("系统没找到计划文件，将在结束时创捷");
		}
		catch (IOException e)
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "啊欧，都是我不好，您原来的计划文件找不到了。把这个消息告诉我作者吧！");
//			e.printStackTrace();
			planBoard = new PlanBoard();
			System.out.println("系统没找到计划文件，将在结束时创捷");
		}
		
		try 
		{
			taskRecorder = (TaskRecorder)IOUtility.readData(IOUtility.getHistoryfile());
		} 
		catch (ClassNotFoundException e)
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "啊欧，都是我不好，您原来的计划文件找不到了。把这个消息告诉我作者吧！");
//			e.printStackTrace();
			taskRecorder = new TaskRecorder();
			System.out.println("系统没找到历史记录文件，将在结束时创建");
		}
		catch (IOException e) 
		{
//			JOptionPane.showMessageDialog(MainFrame.this, "啊欧，都是我不好，您原来的计划文件找不到了。把这个消息告诉我作者吧！");
//			e.printStackTrace();
			taskRecorder = new TaskRecorder();
			System.out.println("系统没找到历史记录文件，将在结束时创建");
		}

	}
}

