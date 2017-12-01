package com.huanglifu.ds;

import java.util.Calendar;
import java.util.Locale;

/**
 * ���ڼ�¼��������ִ������ģ��̳���{@link DataRecorder}
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
	 * getter, ���������Ƿ����
	 * @return �����Ƿ����
	 */
	public boolean isFinished()
	{
		return this.getTimeExp() == this.getTimeEff();
	}
	/**
	 * getter, ����DateTime
	 * @return DateTime, �����¼���Ŀ�ʼʱ��
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
	 * ��������Чʱ���ۼ�1
	 */
	public void incTimeEff()
	{
		++this.timeEff;
	}

	/**
	 * ����������ʱ���ۼ� delta
	 * @param delta ���ӵ���
	 */
	public void addTimeExp(int delta) 
	{
		this.timeExp += delta;
	}
	
	/**
	 * ������ʵ��ִ��ʱ���ۼ�1
	 */
	public void incTimeAct()
	{
		++this.timeAct;
	}
}