package com.huanglifu.ds;

import java.io.Serializable;

/**
 * ���ڼ�¼����ִ������ĳ����࣬�������� {@link TaskRecorder} �� {@link OverallRecorder} ���Էֱ������ڼ�¼����ִ�����
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

