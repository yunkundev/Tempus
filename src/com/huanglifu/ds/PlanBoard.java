package com.huanglifu.ds;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * �ƻ��壬���ڴ�� {@link Plan}��������
 * ���� {@link LinkedHashMap}ʵ��
 * ���������ͬ���ƻ�
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