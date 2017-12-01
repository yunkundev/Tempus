package com.huanglifu.ds;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.huanglifu.strategy.TimeSpliter;

/**
 * 任务表，用于保存 {@link TimeBlock} 的容器数据结构
 * 该表可以被 {@link TimeSpliter}分割生成 {@link TimeBlockList}用于 {@link DoFrame}的执行和管理
 * @author Lifu.Huang
 *
 */
public class TodoList extends LinkedHashMap<String, TodoItem> implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4455010654501921431L;

	public void addItem(TodoItem t) throws PlanHasExistedException, ZeroValueException{
		if(this.containsKey(t.getPlan().getName()) == true)
		{
			throw new PlanHasExistedException();
		}
		if(t.getTime() <= 0)
		{
			throw new ZeroValueException();
		}
		this.put(t.getPlan().getName(), t);
		
	}
}
