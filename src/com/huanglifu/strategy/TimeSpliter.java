package com.huanglifu.strategy;

import java.io.Serializable;

import com.huanglifu.ds.TimeBlockList;
import com.huanglifu.ds.TodoItem;
import com.huanglifu.ds.TodoList;

/**
 * ʱ��ֶ����Ľӿ��࣬�ṩ��Split�������ڽ������� {@link TimeBlockList}����һ���Ĺ���ָ�� {@link TodoItem}���ŵ�һ�� {@link TodoList}��
 * @author Lifu.Huang
 *
 */
public interface TimeSpliter extends Serializable 
{
	/**
	 * ���ڽ������� {@link TimeBlockList}����һ���Ĺ���ָ�� {@link TodoItem}���ŵ�һ�� {@link TodoList}��
	 * @param tbl ���ָ�� {@link TimeBlockList}
	 * @param todo �������� {@link TodoList}
	 */
	void Split(TimeBlockList tbl, TodoList todo); 
}