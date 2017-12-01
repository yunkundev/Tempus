package com.huanglifu.strategy;

import com.huanglifu.ds.RestPlan;
import com.huanglifu.ds.TimeBlock;
import com.huanglifu.ds.TimeBlockList;
import com.huanglifu.ds.TodoItem;
import com.huanglifu.ds.TodoList;

/**
 * ���ս���ʱ�������緶Χ�ڼ�Ϊ�ܻ�ӭ�ġ����ѹ�����������ʱ���
 * ������ѧ�о���������ע�������ָ߶ȼ��е�ʱ�����޴���Ϊ25���ӣ�������һ�����ƶ��ķ��ѹ������ܽϺõ���߹���Ч��
 * ���䷽���ǡ�25���ӹ���ʱ�䡱 + ��5������Ϣʱ�䡱 + ����ѭ�������������25���ӵĹ�����ʵ��ʱ�����
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