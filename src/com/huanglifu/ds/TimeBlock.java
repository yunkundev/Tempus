package com.huanglifu.ds;



/**
 * ��ʾ����ʱ��ε���
 * ��������� {@link TodoList}��
 * @author Lifu.Huang
 *
 */
public class TimeBlock 
{
	
	private int totalTime;
	private int restTime;
	private int pastTime;
	public void setRestTime(int restTime) 
	{
		this.restTime = restTime;
	}
	private Plan Plan;
	
	public int getTotalTime() 
	{
		return totalTime;
	}
	public void setTotalTime(int totalTime) 
	{
		this.totalTime = totalTime;
	}
	public int getPastTime() 
	{
		return pastTime;
	}
	public void setPastTime(int pastTime)
	{
		this.pastTime = pastTime;
	}
	
	public void increment() throws UnincrementableException 
	{
		if(!done())
		{
			Plan.setUsedTime(Plan.getUsedTime() + 1);
			++pastTime;
			--restTime;
		}
	}
	
	public TimeBlock(Plan Plan, int tot) 
	{
		this.Plan = Plan;
		totalTime = tot;
		pastTime = 0;
		restTime = totalTime;
	}
	
	public boolean done() 
	{
		return restTime == 0;
	}
	public Plan getPlan()
	{
		return Plan;
	}
	public int getRestTime() 
	{
		return restTime;
	}
}
