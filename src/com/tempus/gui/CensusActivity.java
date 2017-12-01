package com.tempus.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.huanglifu.ds.GlobalData;
/*
 * 界面统计类：改变界面上显示的各种统计信息，将后台做出的统计显示到屏幕包括执行时间、执行日期、是否完成任务等。
 * 主要思想以字符串存储后台提供的相关信息，调用writeTextView写入界面。
 */
public class CensusActivity extends Activity
{
	//计划执行时间文本
	private TextView textViewPlanTime;
	//实际执行时间文本
	private TextView textViewDoTime;
	//有效执行时间文本
	private TextView textViewValueTime;
	//有效比例文本
	private TextView textViewPercentage;
	//是否完成任务文本
	private TextView textViewTaskComplete;
	//执行日期文本
	private TextView textViewDate;
	//计划执行时间字符串
	private String strPlanTime;
	//实际执行时间字符串
	private String strDoTime;
	//有效执行时间字符串
	private String strValueTime;
	//有效比例字符串
	private String strPercentage;
	//是否完成字符串
	private String strTaskComplete;
//	执行日期字符串
	private String strDate;
	//界面上下文
	static public Context context;
	/*
	 * 界面创建函数，将所有textView与文本变量建立联系并调用刷新函数，刷新文本内容。(non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_census);
		textViewPlanTime = (TextView) findViewById(R.id.textViewPlanTime);
		textViewDoTime = (TextView) findViewById(R.id.textViewDoTime);
		textViewValueTime = (TextView) findViewById(R.id.textViewValueTime);
		textViewPercentage = (TextView) findViewById(R.id.textViewPercentage);
		textViewTaskComplete = (TextView) findViewById(R.id.textViewTaskComplete);
		textViewDate = (TextView) findViewById(R.id.textViewDate);
		refresh();
	}
	/*
	 * 初始化字符串函数，若全局没有taskRecorder记录，则进行初始化工作，赋予字符串初值。
	 */
	public void initTextView()
	{
		strPlanTime = new String("　　－:－");
		strDoTime = new String("　　－:－");
		strValueTime = new String("　　－:－");
		strPercentage = new String("　　－％");
		strTaskComplete = new String("　　－");
		strDate = new String("　　－");
	}
	/*
	 * 字符串刷新函数，若全局没有taskRecorder记录，则调用initTextView对字符串进行初始化，
	 * 否则从taskRecorder中获得相应信息填写在字符串中，并调用writeTextView函数，写入界面文本。
	 */
	public void refresh()
	{
		if(GlobalData.taskRecorder!=null)
		{
			strPlanTime = "　" + GlobalData.secToString(GlobalData.taskRecorder.getTimeExp());
			strDoTime = "　" + GlobalData.secToString(GlobalData.taskRecorder.getTimeAct());
			strValueTime = "　" + GlobalData.secToString(GlobalData.taskRecorder.getTimeEff());
			double ratio = ((double)GlobalData.taskRecorder.getTimeEff())*100/GlobalData.taskRecorder.getTimeAct();
			
			strPercentage = "　" + Math.round(ratio) + "%";
			if(GlobalData.taskRecorder.isFinished())
			{
				strTaskComplete = "　" + "是";
			}
			else
			{
				strTaskComplete = "　" + "否";
			}
			SimpleDateFormat formatter = new SimpleDateFormat("　yyyy年MM月dd日    HH:mm:ss",Locale.CHINA);     
			Date curDate = GlobalData.taskRecorder.getDateTime().getTime();  
			strDate = formatter.format(curDate);  
		}
		else
		{
			initTextView();
		}
		writeTextView();   	

	}
	/*
	 * 写界面文本函数，将对应String写入文本，在界面进行显示。
	 */
	public void writeTextView()
	{
		textViewPlanTime.setText(strPlanTime);
		textViewDoTime.setText(strDoTime);
		textViewValueTime.setText(strValueTime);
		textViewPercentage.setText(strPercentage);
		textViewTaskComplete.setText(strTaskComplete);
		textViewDate.setText(strDate);
	}
	

	
}
