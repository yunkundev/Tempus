package com.huanglifu.ds;

import java.util.Calendar;
import java.util.Locale;

/**
 * 用于记录单次任务执行情况的，继承自{@link DataRecorder}
 * @author Lifu.Huang
 *
 */
public class TaskRecorder extends DataRecorder
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 497998477059132310L;
	private Calendar DateTime = Calendar.getInstance(Locale.CHINA);
	
	/**
	 * getter, 返回任务是否完成
	 * @return 任务是否完成
	 */
	public boolean isFinished()
	{
		return this.getTimeExp() == this.getTimeEff();
	}
	/**
	 * getter, 返回DateTime
	 * @return DateTime, 任务记录器的开始时间
	 */
	
	public Calendar getDateTime() 
	{
		return DateTime;
	}
	
	public void setDateTime()
	{
		DateTime = Calendar.getInstance(Locale.CHINA);
	}

	/**
	 * 将任务有效时间累加1
	 */
	public void incTimeEff()
	{
		++this.timeEff;
	}

	/**
	 * 将任务期望时间累加 delta
	 * @param delta 增加的量
	 */
	public void addTimeExp(int delta) 
	{
		this.timeExp += delta;
	}
	
	/**
	 * 将任务实际执行时间累加1
	 */
	public void incTimeAct()
	{
		++this.timeAct;
	}
}