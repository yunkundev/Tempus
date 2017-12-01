package com.huanglifu.strategy;

import com.huanglifu.ds.TimeBlock;
import com.huanglifu.ds.TimeBlockList;
import com.huanglifu.ds.TodoItem;
import com.huanglifu.ds.TodoList;

/**
 * ʱ��ֶ���
 * �����ո��������ԭʼ���ȣ����������ʱ��Σ����Զ�������Ϣʱ��
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
