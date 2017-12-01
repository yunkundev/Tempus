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
 * 用于根据用户提供的计划表和期望工作时间，声称这段时间内最优的时间利用方案并以任务表的形式返回
 * 基于本任务生成器生成算法基于离散化预处理和01背包算法实现。可以保证在用户给定时间内和给定的权重标准下生成价值最高的时间利用方案
 * 算法时间复杂度为O(n * v)， 其中 n 为划分的时间段数，v为可能的总价值（对于单个任务，最大取值为50）
 * 对于渐进复杂度，n，v的单位选取不影响分析结果。经过实际测试，对于可能出现的较高使用要求(如:n = 100)，本算法完全可以在可忽略的时间内内完成计算任务
 * 算法空间复杂度为O(n * v)，经过实际测试，对于可能出现的较高使用要求(如:n = 100)，本算法的空间消耗在合理范围内
 * 另外，本类采用了Singleton设计模式，只允许存在一个实例，该实例可以通过 getInstance() 方法获取 
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
	 * 按照输入的要求对一个 {@link Plan} 类对象进行价值评估
	 * @param t 待估值的计划
	 * @return 返回评估的价值
	 */
	private int calculateUnitValue(Plan t) 
	{
		return wtUrg * t.getUrgency() + wtImpt * t.getImportance();
	}
	
	/**
	 * 获得该类实例
	 * @return 返回 {@link OptimalGenerator} 的一个实例
	 */
	public static OptimalGenerator getInstance() 
	{
		return instance;
	}
	
	/**
	 * 接口函数，调用该函数以生成最优化的任务表，生成结果可以通过 getResult()返回
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
	 * 将生成结果输出到给定的 {@link TodoList}中
	 * @param out 待输出的 {@link TodoList}
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
	 * 核心函数，采用01背包算法从给定计划表中选择符合时间要求的任务计划，并将结果返回到给定 {@link TodoList}中
	 * @param PlanBoard 给定的计划表
	 * @param time 待分配时间，单位为秒
	 * @param res 输出结果
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
	 * 用于表示背包算法中物品的嵌套类
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
