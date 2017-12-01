package com.huanglifu.ds;

import java.io.Serializable;

/**
 * todo������������ʱ��Ϊ����������Ļ�����λ����ӵ��������
 * ���Ա�װ�� {@link TimeBlockList}��
 * ÿ���ƻ��� {@link TimeBlockList}ֻ�ܶ�Ӧһ�� {@link TodoItem}�������׳� {@link PlanHasExistedException}�쳣
 *
 * @author Lifu.Huang
 *
 */
public class TodoItem implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6257020577167053924L;
	private Plan Plan;
	private int time;
	public TodoItem(Plan Plan, int seconds) 
	{
		this.Plan = Plan;
		time = seconds;
	}
	public Plan getPlan() 
	{
		return Plan;
	}
	public void setPlan(Plan Plan) 
	{
		this.Plan = Plan;
	}
	public int getTime() 
	{
		return time;
	}
	public void setTime(int time) 
	{
		this.time = time;
	}
	
}