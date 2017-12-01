package com.huanglifu.ds;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.huanglifu.strategy.TimeSpliter;


/**
 * 用于保存一些列 {@link TodoItem}的 链表，基于{@link LinkedHashMap}实现
 * 可以由 {@link TimeSpliter}根据 {@link PlanBoard}直接生成
 * @author Lifu.Huang
 *
 */
public class TimeBlockList
{
	private List<TimeBlock> lst = new ArrayList<TimeBlock>();

	private int index;
	
	private TimeBlockList() 
	{
	}
	public int size() 
	{
		return lst.size();
	}
	public boolean isEmpty() 
	{
		return lst.isEmpty();
	}
	
	//must invoke this method after lst is initialize to set all arguments right
	public void reset()
	{
		index = 0;
	}
	
	public void next() 
	{
		if(index < lst.size() - 1) 
		{
			++index;
			
		}
	}
	
	public void push_back(TimeBlock e) 
	{
		lst.add(e);
	}
	
	//warning: might affect the normal function of iterator(current),
	//			should only be used within construction
	public void pop_back() 
	{
		lst.remove(lst.size() - 1);
	}
	
	static public TimeBlockList createTimeBlockList(TodoList todo, TimeSpliter td)
	{
		TimeBlockList tbl = new TimeBlockList();
		td.Split(tbl, todo);
		tbl.reset();
		return tbl;
	}
	
	public int getTotalTime() 
	{
		int sum = new Integer(0);
		for(TimeBlock t : lst) 
		{
			sum += t.getTotalTime();
		}
		return sum;
	}
	
	public int getPastTime() 
	{
		int sum = new Integer(0);
		for(int i = 0; i <= index; ++i)
		{
			sum += lst.get(i).getPastTime();
		}
		return sum;
	}
	
	public void increment() throws UnincrementableException 
	{
		getCurrent().increment();
	}
	
	public TimeBlock getCurrent() 
	{
		return lst.get(index);
	}
	
	public boolean done() 
	{
		return lst.size() - 1 == index && getCurrent().done();
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public boolean atBeginning()
	{
		return getCurrent().getPastTime() == 0;
	}
	
	public int getRestTime()
	{
		int sum = new Integer(0);
		for(int i = index; i < lst.size(); ++i) 
		{
			sum += lst.get(i).getRestTime();
		}
		return sum;
	}

}
