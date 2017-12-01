package com.huanglifu.ds;


/**
 * {@link Plan}的派生类，表示休息
 * @author Lifu.Huang
 *
 */
public class RestPlan extends Plan 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870935149648930411L;
	
	public RestPlan(int tot) throws EmptyValueException, ZeroValueException
	{
		super("休息时间", "别再待在电脑前了，活动身体，看看远处，身体是革命的本钱！", 0, 0, tot, false);
		// TODO Auto-generated constructor stub
	}
}
