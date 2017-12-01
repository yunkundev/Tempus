package com.huanglifu.strategy;

import java.io.Serializable;

import com.huanglifu.ds.PlanBoard;
import com.huanglifu.ds.TodoList;

/**
 * 接口类，任务生成器，用于根据用户需求生成最优的时间利用方案（任务表）
 * @author Lifu.Huang
 *
 */
public interface TaskGenerator extends Serializable 
{
	void generateTask(PlanBoard PlanBoard, int time, int pref);
	void output(TodoList out);
	TodoList getResult();
}
