package com.huanglifu.ds;

/**
 * ���ڼ�¼��ʷִ������ļ�¼�����̳��� {@link DataRecorder}
 * @author Lifu.Huang
 *
 */
public class OverallRecorder extends DataRecorder
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5046673657768678569L;
	private int nAttempt;
	private int nFinished;
	
	public int getnAttempt() 
	{
		return nAttempt;
	}
	public int getnFinished() 
	{
		return nFinished;
	}
	
	public void add(TaskRecorder rhs) 
	{
		timeExp += rhs.timeExp;
		timeAct += rhs.timeAct;
		timeEff += rhs.timeEff;
		
		++nAttempt;
		if(rhs.isFinished()) 
		{
			++nFinished;
		}
	}
	public void clear() 
	{	
		timeExp = 0;
		timeAct = 0;
		timeEff = 0;
		nAttempt = 0;
		nFinished = 0;
		
	}
}