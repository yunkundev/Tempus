package com.huanglifu.ds;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 计划板，用于存放 {@link Plan}的容器类
 * 基于 {@link LinkedHashMap}实现
 * 不允许出现同名计划
 * @author Lifu.Huang
 *
 */
public class PlanBoard extends LinkedHashMap<String, Plan> implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1113913889628480225L;

	public void addPlan(Plan t) throws PlanHasExistedException
	{
		if(this.containsKey(t.getName()) == true)
		{
			throw new PlanHasExistedException();
		} 
		else 
		{
			this.put(t.getName(), t);
		}
	}
}