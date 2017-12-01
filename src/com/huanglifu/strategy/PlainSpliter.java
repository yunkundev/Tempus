package com.huanglifu.strategy;

import com.huanglifu.ds.TimeBlock;
import com.huanglifu.ds.TimeBlockList;
import com.huanglifu.ds.TodoItem;
import com.huanglifu.ds.TodoList;

/**
 * 时间分段器
 * 将按照各任务项的原始长度，整块的生成时间段，不自动安排休息时间
 * @author Lifu.Huang
 *
 */
public class PlainSpliter implements TimeSpliter
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6303521623578871339L;

	@Override
	public void Split(TimeBlockList tbl, TodoList todo) 
	{
		for(TodoItem t : todo.values()) 
		{
			tbl.push_back(new TimeBlock(t.getPlan(), t.getTime()));
		}
	}
	
	private PlainSpliter()
	{
		
	}
	
	private static PlainSpliter instance = new PlainSpliter();
	
	public static PlainSpliter getInstance() 
	{
		return instance;
	}
}
