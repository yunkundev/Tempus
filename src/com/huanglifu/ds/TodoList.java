package com.huanglifu.ds;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.huanglifu.strategy.TimeSpliter;

/**
 * ��������ڱ��� {@link TimeBlock} ���������ݽṹ
 * �ñ���Ա� {@link TimeSpliter}�ָ����� {@link TimeBlockList}���� {@link DoFrame}��ִ�к͹���
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
