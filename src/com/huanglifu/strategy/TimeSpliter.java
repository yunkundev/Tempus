package com.huanglifu.strategy;

import java.io.Serializable;

import com.huanglifu.ds.TimeBlockList;
import com.huanglifu.ds.TodoItem;
import com.huanglifu.ds.TodoList;

/**
 * 时间分段器的接口类，提供的Split方法用于将给定的 {@link TimeBlockList}按照一定的规则分割成 {@link TodoItem}并放到一个 {@link TodoList}中
 * @author Lifu.Huang
 *
 */
public interface TimeSpliter extends Serializable 
{
	/**
	 * 用于将给定的 {@link TimeBlockList}按照一定的规则分割成 {@link TodoItem}并放到一个 {@link TodoList}中
	 * @param tbl 待分割的 {@link TimeBlockList}
	 * @param todo 保存结果的 {@link TodoList}
	 */
	void Split(TimeBlockList tbl, TodoList todo); 
}