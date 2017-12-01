package com.huanglifu.strategy;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.huanglifu.ds.Plan;
import com.huanglifu.ds.PlanBoard;
import com.huanglifu.ds.PlanHasExistedException;
import com.huanglifu.ds.TodoItem;
import com.huanglifu.ds.TodoList;
import com.huanglifu.ds.ZeroValueException;

/**
 * ���ڸ����û��ṩ�ļƻ������������ʱ�䣬�������ʱ�������ŵ�ʱ�����÷���������������ʽ����
 * ���ڱ����������������㷨������ɢ��Ԥ�����01�����㷨ʵ�֡����Ա�֤���û�����ʱ���ں͸�����Ȩ�ر�׼�����ɼ�ֵ��ߵ�ʱ�����÷���
 * �㷨ʱ�临�Ӷ�ΪO(n * v)�� ���� n Ϊ���ֵ�ʱ�������vΪ���ܵ��ܼ�ֵ�����ڵ����������ȡֵΪ50��
 * ���ڽ������Ӷȣ�n��v�ĵ�λѡȡ��Ӱ��������������ʵ�ʲ��ԣ����ڿ��ܳ��ֵĽϸ�ʹ��Ҫ��(��:n = 100)�����㷨��ȫ�����ڿɺ��Ե�ʱ��������ɼ�������
 * �㷨�ռ临�Ӷ�ΪO(n * v)������ʵ�ʲ��ԣ����ڿ��ܳ��ֵĽϸ�ʹ��Ҫ��(��:n = 100)�����㷨�Ŀռ������ں���Χ��
 * ���⣬���������Singleton���ģʽ��ֻ�������һ��ʵ������ʵ������ͨ�� getInstance() ������ȡ 
 * @author Lifu.Huang
 *
 */
public class OptimalGenerator implements TaskGenerator 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4775657601924734618L;
	// unit time length of a TodoItem, below which optimization will be practically meaningless 
	private static final int DEFAULT_UNIT = 10 * 60;	 
	private int wtUrg;
	private int wtImpt;
	private final TodoList todo = new TodoList();
	private List<Thing> lst;
	private static OptimalGenerator instance = new OptimalGenerator();
	
	//privatize the constructor
	private OptimalGenerator() 
	{
		
	}
	
	/**
	 * ���������Ҫ���һ�� {@link Plan} �������м�ֵ����
	 * @param t ����ֵ�ļƻ�
	 * @return ���������ļ�ֵ
	 */
	private int calculateUnitValue(Plan t) 
	{
		return wtUrg * t.getUrgency() + wtImpt * t.getImportance();
	}
	
	/**
	 * ��ø���ʵ��
	 * @return ���� {@link OptimalGenerator} ��һ��ʵ��
	 */
	public static OptimalGenerator getInstance() 
	{
		return instance;
	}
	
	/**
	 * �ӿں��������øú������������Ż�����������ɽ������ͨ�� getResult()����
	 */
	@Override
	public void generateTask(PlanBoard PlanBoard, int time, int pref) 
	{
		wtImpt = 6 + pref;
		wtUrg = 6 - pref;
		todo.clear();
		knapsack(PlanBoard, time, todo);
	}
	

	public TodoList getResult() 
	{
		return todo;
	}
	/**
	 * �����ɽ������������� {@link TodoList}��
	 * @param out ������� {@link TodoList}
	 */
	public void output(TodoList out)
	{
		out.clear();
		for(TodoItem t : todo.values()) 
		{
			try 
			{
				out.addItem(t);
			} 
			catch (PlanHasExistedException e) 
			{
				e.printStackTrace();
				throw new RuntimeException();
			} 
			catch (ZeroValueException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ;
	}
	
	/**
	 * ���ĺ���������01�����㷨�Ӹ����ƻ�����ѡ�����ʱ��Ҫ�������ƻ�������������ص����� {@link TodoList}��
	 * @param PlanBoard �����ļƻ���
	 * @param time ������ʱ�䣬��λΪ��
	 * @param res ������
	 */
	private void knapsack(PlanBoard PlanBoard, int time, TodoList res) 
	{

		lst = new ArrayList<Thing>();
		lst.add(new Thing(0, 0, null));	//used to enable lst be counted from 1 rather than zero, WILL NOT BE USED IN CALCULATION
		for(Plan t : PlanBoard.values())
		{
			if(t.isSplitable()) 
			{
				int timeRest = t.getRestTime();
				int uv = calculateUnitValue(t);	//unit value
				while(timeRest > 0) 
				{
					int weight = Math.min(DEFAULT_UNIT, timeRest);
					lst.add(new Thing(uv * weight, weight, t));
					timeRest -= weight;
				}
			} 
			else
			{
				int timeRest = t.getRestTime();
				int uv = calculateUnitValue(t);	//unit value
				lst.add(new Thing(uv * timeRest, timeRest, t));	
			}
		}
		
		/*
		 * dp stands for Dynamic Programming, 
		 * dp[i][j] stores the maximal value obtainable 
		 * with the limit of only choose among the first i things and the knapsack is sized j 
		 * record[i][j] is used
		 */
		int dp[][] = new int[lst.size()][time + 1];
		boolean record[][] = new boolean[lst.size()][time + 1];
		
		for(int j = 0; j < dp.length; ++j) 
		{
			dp[0][j] = 0;
		}
		for(int i = 1; i < lst.size(); ++i) 
		{
			for(int j = lst.get(i).weight; j <= time; ++j) 
			{
				if(dp[i-1][j - lst.get(i).weight] + lst.get(i).value > dp[i-1][j])
				{
					dp[i][j] = dp[i-1][j - lst.get(i).weight] + lst.get(i).value;
					record[i][j] = true; 
				} 
				else 
				{
					dp[i][j] = dp[i-1][j];
					record[i][j] = false;
				}
			}
		}
		
		List<Thing> stack = new ArrayList<Thing>();
		for(int i = lst.size() - 1, j = time; i >= 0; --i)
		{
			if(record[i][j] == true) 
			{
				if(!stack.isEmpty() && stack.get(stack.size()-1).Plan == lst.get(i).Plan)
				{
					stack.get(stack.size()-1).weight += lst.get(i).weight;
				} 
				else
				{
					stack.add(lst.get(i).clone());
				}
				j -= lst.get(i).weight;
			}
		}
		for(Thing t : stack) 
		{
			try 
			{
				res.addItem(new TodoItem(t.Plan, t.weight));
			} 
			catch (PlanHasExistedException e)
			{
				e.printStackTrace();
				throw new RuntimeException();
			}
			catch (ZeroValueException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ;
	}
	
	//test
	public static void main(String args[]) 
	{
		
		PlanBoard PlanBoard = new PlanBoard();
		try 
		{
			PlanBoard.addPlan(new Plan("a","", 9, 9, 10000, true));
			PlanBoard.addPlan(new Plan("b","", 1, 10, 30000, true));
			PlanBoard.addPlan(new Plan("c","", 10, 1, 30000, true));
			PlanBoard.addPlan(new Plan("d","", 0, 0, 3000, true));
			PlanBoard.addPlan(new Plan("e","", 10, 10, 3500, true));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//no preference
		TaskGenerator pg1 = new OptimalGenerator();
		pg1.generateTask(PlanBoard, 50000, 0);
		for(TodoItem t : pg1.getResult().values()) 
		{
			System.out.println(t.getPlan().getName() + " " + t.getTime());
		}
		System.out.println("------------------------");
		//prefer importance of Plan
		TaskGenerator pg2 = new OptimalGenerator();
		pg2.generateTask(PlanBoard, 50000, 4);
		for(TodoItem t : pg2.getResult().values()) 
		{
			System.out.println(t.getPlan().getName() + " " + t.getTime());
		}
		System.out.println("------------------------");
		//prefer urgency of Plan
		TaskGenerator pg3 = new OptimalGenerator();
		pg3.generateTask(PlanBoard, 50000, -4);
	
		for(TodoItem t : pg3.getResult().values())
		{
			System.out.println(t.getPlan().getName() + " " + t.getTime());
		}
	}
	
	/**
	 * ���ڱ�ʾ�����㷨����Ʒ��Ƕ����
	 * @author Lifu.Huang
	 *
	 */
	static class Thing implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -6742172899128514782L;
		int value;
		int weight;
		Plan Plan;
		Thing(int v, int w, Plan t) 
		{
			value = v;
			weight = w;
			Plan = t;
		}
		public Thing clone()
		{
			return new Thing(value, weight, Plan);
		}
	}
}
