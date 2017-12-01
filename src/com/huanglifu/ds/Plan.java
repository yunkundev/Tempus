package com.huanglifu.ds;

import java.io.Serializable;

/**
 * �ƻ��࣬�����ڡ��ҵļƻ�����ҳ���д������༭��ɾ������
 * �����ƻ����ơ��ƻ����ݡ��ƻ�ʱ�䡢�ƻ���Ҫ�̶ȡ��ƻ����ȳ̶ȡ��ƻ��Ƿ�ɷָ�ȶ�������
 * ���û����и��˹滮�Ļ����߼���λ
 * @author Lifu.Huang
 *
 */
public class Plan implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 564013157384316215L;
	private String name;
	private String description;
	private int importance;				//0 to 10, increasingly important
	private int urgency;				//0 to 10, increasingly urgent
	private int totalTime;				//all time scheduled for the Plan
	private int usedTime;				//time has been used so fat
	private boolean splitable;			//whether Plan can be split
	
	public boolean isSplitable() 
	{
		return splitable;
	}

	public void setSplitable(boolean splitable) 
	{
		this.splitable = splitable;
	}
//	private LocalTime timeEstblsh;		//establish time of the Plan

	public Plan(String nm, String dscrp, int impt, int urg, int tot, boolean spl) throws EmptyValueException, ZeroValueException 
	{
		if(nm.length() == 0) 
		{
			throw new EmptyValueException();
		}
		if(tot <= 0)
		{
			throw new ZeroValueException();
		}
		name = nm;
		description = dscrp;
		importance = impt;
		urgency = urg;
		totalTime = tot;
		splitable = spl;
	}
	
	
	
	public Plan(String nm, String dscrp, String string, int urg, int tot,
			int i, boolean spl) 
	{
		// TODO Auto-generated constructor stub
	}

	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public int getImportance() 
	{
		return importance;
	}
	public void setImportance(int importance) 
	{
		this.importance = importance;
		
	}
	public int getUrgency() 
	{
		return urgency;
	}
	public void setUrgency(int urgency)
	{
		this.urgency = urgency;
	}
	public int getTotalTime() 
	{
		return totalTime;
	}
	public void setTotalTime(int totalTime) 
	{
		this.totalTime = totalTime;
	}

	public int getUsedTime() 
	{
		return usedTime;
	}

	public void setUsedTime(int usedTime) 
	{
		this.usedTime = usedTime;
		
	}
	public boolean finished()
	{
		return usedTime == totalTime;
	}

	public int getRestTime() 
	{
		return totalTime - usedTime;
	}
}


