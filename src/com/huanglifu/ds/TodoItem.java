package com.huanglifu.ds;

import java.io.Serializable;

/**
 * todo项，在生成任务表时作为单个任务项的基本单位被添加到任务表中
 * 可以被装入 {@link TimeBlockList}中
 * 每个计划在 {@link TimeBlockList}只能对应一个 {@link TodoItem}，否则将抛出 {@link PlanHasExistedException}异常
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