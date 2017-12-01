package com.huanglifu.ds;

import java.io.Serializable;

/**
 * 用于记录任务执行情况的抽象类，派生出的 {@link TaskRecorder} 和 {@link OverallRecorder} 可以分别适用于记录具体执行情况
 * @author Lifu.Huang
 *
 */
public abstract class DataRecorder implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 871510024522182532L;
	protected int timeExp;
	protected int timeAct;
	protected int timeEff;
	
	public int getTimeExp() 
	{
		return timeExp;
	}
	
	public int getTimeAct() 
	{
		return timeAct;
	}

	public int getTimeEff() 
	{
		return timeEff;
	}
}

