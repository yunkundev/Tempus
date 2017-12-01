package com.huanglifu.strategy;

import com.huanglifu.ds.RestPlan;
import com.huanglifu.ds.TimeBlock;
import com.huanglifu.ds.TimeBlockList;
import com.huanglifu.ds.TodoItem;
import com.huanglifu.ds.TodoList;

/**
 * 按照近段时间在世界范围内极为受欢迎的“番茄工作法”划分时间段
 * 经过科学研究表明，人注意力保持高度集中的时间上限大致为25分钟，根据这一法则制订的番茄工作法能较好的提高工作效率
 * 分配方法是“25分钟工作时间” + “5分钟休息时间” + 。。循环，对于最后不足25分钟的工作按实际时间添加
 * @author Lifu.Huang
 *
 */
public class TomatoSpliter implements TimeSpliter 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6730864027021018611L;
	private final static int TOMATO_LEN = 25 * 60;
	private final static int REST_LEN = 5 * 60;
	private static TomatoSpliter instance = new TomatoSpliter();
	@Override
	public void Split(TimeBlockList tbl, TodoList todo) 
	{
		for(TodoItem t : todo.values()) 
		{
			int tmp = t.getTime();
			while(tmp > 0) {
				if(tmp > TOMATO_LEN) 
				{
					tbl.push_back(new TimeBlock(t.getPlan(), TOMATO_LEN));
					tmp -= TOMATO_LEN;
				} 
				else
				{
					tbl.push_back(new TimeBlock(t.getPlan(), tmp));
					tmp = 0;
				}
				try 
				{
					tbl.push_back(new TimeBlock(new RestPlan(REST_LEN), REST_LEN));
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		if(!tbl.isEmpty()) 
		{
			tbl.pop_back();
		}
	}
	
	private TomatoSpliter()
	{
		
	}
	
	public static TomatoSpliter getInstance() 
	{
		return instance;
	}
}