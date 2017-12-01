package com.huanglifu.strategy;

import java.io.Serializable;

import com.huanglifu.ds.PlanBoard;
import com.huanglifu.ds.TodoList;

/**
 * �ӿ��࣬���������������ڸ����û������������ŵ�ʱ�����÷����������
 * @author Lifu.Huang
 *
 */
public interface TaskGenerator extends Serializable 
{
	void generateTask(PlanBoard PlanBoard, int time, int pref);
	void output(TodoList out);
	TodoList getResult();
}
