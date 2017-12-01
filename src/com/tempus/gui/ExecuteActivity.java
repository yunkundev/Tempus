package com.tempus.gui;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huanglifu.ds.GlobalData;
import com.huanglifu.ds.UnincrementableException;
/*
 * 执行任务程序界面类：使用了多线程，实现执行界面，将TimeBlockList中的任务一一执行，包含全部任务进度，剩余时间，当前任务进度，当前任务剩余时间，开始按钮，暂停按钮，退出按钮，励志座右铭。使用多线程在描绘相应控件的同时进行时间记录，每隔一段时间改变座右铭内容。
 */
@SuppressLint("HandlerLeak")
public class ExecuteActivity extends Activity
{
	private ProgressBar progressbarTotal;
//总任务进度条。
	private ProgressBar progressbarNow;
//当前任务进度条。
	private TextView textviewNow;
//当前任务剩余时间
	private TextView textviewTotal;
//总任务剩余时间
	private Button btnGo;
//开始按钮
    private Button btnNext;
//暂停按钮
    private Button btnOut;
//退出按钮
	private boolean isPaused = false;
//记录是否暂停，初始非暂停
	private String stringTotal;
//总任务剩余时间字符串
    private String stringNow;
//当前任务剩余时间字符串
    private int cal = 0;
//记录多少秒刷新
    private int numcount = 0;
//计数第几个座右铭
    private TextView tvMotto;
//座右铭文本。
/*
 * (non-Javadoc)界面创建函数，主要实现进度条、按钮、文本信息的初始化，及每个按钮的监听事件，能否使用的设置等等	
 * @see android.app.Activity#onCreate(android.os.Bundle)
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_execute);
		
        progressbarTotal = (ProgressBar) findViewById(R.id.pbOverallTask);
        progressbarNow = (ProgressBar) findViewById(R.id.pbCurrentTask);
        initOverall();
		initCurrent();
		
		
        textviewNow =(TextView)findViewById(R.id.textViewNow);
        textviewTotal =(TextView) findViewById(R.id.textViewTotal);
        tvMotto= (TextView) findViewById(R.id.textViewMotto);
        
        
        //******************************
        
       
        
        
        //button
        btnGo=(Button)findViewById(R.id.button_go);
        btnNext=(Button)findViewById(R.id.button_next);
        btnOut=(Button)findViewById(R.id.button_out);
		
        btnGo.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				GlobalData.taskRecorder.addTimeExp(GlobalData.timeBlockList.getTotalTime());
				GlobalData.taskRecorder.setDateTime();
				start(); 
				btnGo.setEnabled(false);
				btnNext.setEnabled(true);
			}
		});
        btnNext.setEnabled(false);
        btnNext.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				if(isPaused == false)
				{
					isPaused = true;
				}
				else 
				{
					isPaused = false;
				}
				
			}
		});
		
		btnOut.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) {
					// TODO Auto-generated method stub
				ExecuteActivity.this.finish();
				isPaused = true;
				//PlanActivity.refresh();
				//StartActivity.refresh();
				GlobalData.refresh();
	        }
		});

   }  
	//初始化总任务时间函数，对总任务进度条最大值进行设置。	
    void initOverall()
    {
    	int totTime = GlobalData.timeBlockList.getTotalTime();
    	progressbarTotal.setMax(totTime);
    }
    //初始化当前任务时间函数，对当前任务进度条最大值进行设置。
    void initCurrent()
    {
    	int totTime = GlobalData.timeBlockList.getCurrent().getTotalTime();
    	progressbarNow.setMax(totTime);
    }
    //更新总任务时间函数，对总任务进度执行时间进行设置。
    void updateOverall()
    {
    	initOverall();
    	int pastTime = GlobalData.timeBlockList.getPastTime();
    	progressbarTotal.setProgress(pastTime);
    }
    //更新当前任务时间函数，对当前任务进度执行时间进行设置。
    void updateCurrent()
    {
    	initCurrent();
    	int pastTime  = GlobalData.timeBlockList.getCurrent().getPastTime();
    	progressbarNow.setProgress(pastTime);
    }
    
    public Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
        	
            switch(msg.what) 
            {
            case 0:
            	//取出参数更新控件
            	textviewTotal.setText((String)msg.getData().get("stringT"));
    	        textviewNow.setText((String)msg.getData().get("stringN"));
    	        
            	break;
            case 1:
            	new AlertDialog.Builder(ExecuteActivity.this)    
				.setTitle("小T" )  
				.setMessage("恭喜你~任务全部完成啦~~" )  
				.setPositiveButton("我知道了" ,   new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) 
					{
					//确定的话就表示退出，此时我们结束我们程序
					//使用我们Activity提供的finish方法
						ExecuteActivity.this.finish();
						isPaused = true;
//						PlanActivity.refresh();
//						StartActivity.refresh();
						GlobalData.refresh();
						
					}
				})
				.show();  
            	break;
            case 2:
            	new  AlertDialog.Builder(ExecuteActivity.this)    
				 .setTitle("小T" )  
			     .setMessage("当前时间段完成了，休息一下，赶快开始下一段吧~" )  
			     .setPositiveButton("我知道了" ,  new DialogInterface.OnClickListener()
			     {
					public void onClick(DialogInterface dialog, int which) 
					{
					//确定的话就表示退出，此时我们结束我们程序
					//使用我们Activity提供的finish方法
						isPaused = false;
						
					}
			     })  
				.show();  
            	break;
            case 3:	
            	try
            	{
            		String mot = GlobalData.setupScheme.getLstMotto().get(numcount%(GlobalData.setupScheme.getLstMotto().size()));
                	System.out.println(mot);
                	tvMotto.setText(mot);
                	GlobalData.refresh();
            	}
            	catch(Exception e)
            	{
            		// Do nothing
            	}
            	break;
            default:
            	break;
            }                                            
            super.handleMessage(msg);
        }
         
    };
    //开始函数，点击开始按钮执行此函数，函数的操作包括多线程的创建，计时器的创建，并每隔一段时间更改进度条信息、剩余时间信息、座右铭信息。
	void start() 
	{
		
		GlobalData.timeBlockList.reset();
		TimerTask task = new TimerTask() 
		{  
				public void run() 
				{  
					
					GlobalData.taskRecorder.incTimeAct();
					
					if(!isPaused) 
					{	
						GlobalData.taskRecorder.incTimeEff();
						if( cal != 0)
						{
							cal --;
						}
						else
						{
							
							cal = 5;
							Message msg = new Message();
							msg.what = 3;
							handler.sendMessage(msg);
							numcount++;
						}
						
						
						try 
						{
							GlobalData.timeBlockList.increment();
							updateOverall();
							updateCurrent();
							//		updateMotto();
							stringTotal = "   剩余时间：" + GlobalData.secToString(GlobalData.timeBlockList.getRestTime());
					
							stringNow="  任务："+GlobalData.timeBlockList.getCurrent().getPlan().getName()+"\n"+"  剩余时间:"
											+GlobalData.secToString(GlobalData.timeBlockList.getCurrent().getRestTime());
							
			                //该部分是传参并更新控件
			                Message msg = new Message();
			                msg.what = 0;
			                Bundle bundle = new Bundle();
			                bundle.putString("stringT", stringTotal);
			                bundle.putString("stringN", stringNow);
			                msg.setData(bundle);
			                //发送消息到Handler
			                handler.sendMessage(msg);
	
							if(GlobalData.timeBlockList.done() && !isPaused) {
								isPaused = true;
								Message msg2 = new Message();
								msg2.what = 1;
								handler.sendMessage(msg2);
								
								return ;
								
							} 
							else if(GlobalData.timeBlockList.getCurrent().done())
							{
								isPaused = true;
								GlobalData.timeBlockList.next();
								Message msg3 = new Message();
								msg3.what = 2;
								handler.sendMessage(msg3);
							}
						} 
						catch (UnincrementableException e1) 
						{
							e1.printStackTrace();
							throw new RuntimeException();
						}
					}
				}
		};
		Timer timer = new Timer(true);
		timer.schedule(task, 0, 1000);
	}
}
