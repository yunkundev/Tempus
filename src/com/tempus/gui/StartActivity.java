package com.tempus.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.huanglifu.ds.GlobalData;
import com.huanglifu.ds.Plan;
import com.huanglifu.ds.PlanHasExistedException;
import com.huanglifu.ds.TimeBlockList;
import com.huanglifu.ds.TodoItem;
import com.huanglifu.ds.ZeroValueException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class StartActivity extends Activity
{
	private Button btnInsert;
	private Button btnAuto;
	private Button btnCancel;
	private Button btnExecute;
	private int cntTaskMaxHour;
	private int cntTaskMin;
	private int cntTaskMaxMin;
	private int cntTaskSec;
	private int cntTaskMaxSec;
	private int selectHour;
	private int selectMin;
	private int selectSec;
	private int maxHour;
	private int maxMin;
	private int maxSec;
	private List<String>listHour;
	private List<String>listMin;
	private List<String>listSec;
	private Spinner spnHour;
	private ArrayAdapter<String>adpHour;
	private Spinner spnMin;
	private ArrayAdapter<String>adpMin;
	private Spinner spnSec;
	private ArrayAdapter<String>adpSec;
    int nowPriorityNumber;
    private String selectName;
    public ListView todoListView;
    public static Context context; 
    
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		maxHour = 10;
		maxMin = 59;
		maxSec = 59;
		todoListView = (ListView) findViewById(R.id.todolist);
		//todoListView.setBackgroundResource(R.drawable.listview_background);
		context = this;
		
		btnInsert=(Button) findViewById(R.id.btnManualAdd);
		btnAuto=(Button) findViewById(R.id.btnAutoAdd);
		btnCancel=(Button) findViewById(R.id.btnClearTodoList);
		btnExecute=(Button) findViewById(R.id.btnExecute);
		//vs = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
	//	vs.setDisplayedChild(1);
		//frameLayout = (frameLayout) findViewById(R.id.);
		refresh();
		
		 /**
         * 
         * @author Richard
         * �ֶ���Ӱ�ť���趨����¼�		
         *
         *
         */
		btnInsert.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// �ֶ����
				 /**
		         * 
		         * @author Richard
		         * �����ֶ���ӶԻ���	
		         *
		         *
		         */
				createCustomDialog_manual();
			}
			
			
			private void createCustomDialog_manual() 
			{
				
				if(GlobalData.planBoard.size() - GlobalData.todoList.size() == 0)
				{
					//��������������������������������
					new AlertDialog.Builder(context)
            		.setTitle("СT")
            		.setMessage("û��������Լ��뵽ִ���б��У�")
            		.setPositiveButton("��֪����",null).show();
					return ;
				}
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(StartActivity.this,R.style.CustomDialog);
		        dialog.setTitle("�ֶ���Ӽƻ�");
				dialog.setContentView(R.layout.dialog_manual);// Ϊ�Ի��������Զ��岼��
		        dialog.show();
		        

		        List<String>listTask = new ArrayList<String>();		        
		        for(Plan p : GlobalData.planBoard.values()) 
		        {
		        	if(!GlobalData.todoList.containsKey(p.getName()))
		        	{
		        		listTask.add(p.getName());
		        	}
		        }
		        
		        final Spinner spnPlanSelect = (Spinner) dialog.findViewById(R.id.spinnerPlanSelect);
		        final ArrayAdapter<String>adpTask = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listTask);
		        adpTask.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        spnPlanSelect.setAdapter(adpTask);
			    spnPlanSelect.setPrompt("��ѡ������");
			    // �ֶ����
				 /**
		         * 
		         * @author Richard
		         * ѡ��ƻ�
		         *
		         *
		         */
		        spnPlanSelect.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id)
					{
						// TODO Auto-generated method stub
						 selectName = adpTask.getItem(position);
						System.out.println(selectName);
						
						int time = GlobalData.planBoard.get(selectName).getRestTime();
						cntTaskMaxHour = time/3600;
						cntTaskMaxMin = time%3600/60;
						cntTaskMaxSec = time%60;
						listHour = new ArrayList<String>();
				        for(int i=0;i<=cntTaskMaxHour;i++)
				        {
				        	//cntTaskMaxHour
				        	listHour.add(""+i);
				        }	
				        adpHour  = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listHour);
				        adpHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        spnHour.setAdapter(adpHour);
				        spnHour.setPrompt("Сʱ");

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) 
					{
						// TODO Auto-generated method stub

					}
				});
		        		        

		        if(listHour == null) 
		        {
		        	 listHour = new ArrayList<String>();
		        }
		        
		        spnHour  = (Spinner) dialog.findViewById(R.id.spnManualTaskHour);
		        adpHour  = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listHour);
		        adpHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        spnHour.setAdapter(adpHour);
			    spnHour.setPrompt("Сʱ");
			 
				 /**
		         * 
		         * @author Richard
		         * ѡ��ʱ�䣬Сʱ
		         *
		         *
		         */
		        spnHour.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) 
					{
						// TODO Auto-generated method stub
						selectHour = Integer.parseInt(adpHour.getItem(position));
						if(selectHour == cntTaskMaxHour)
						{
							cntTaskMin = cntTaskMaxMin;
						}
						else
						{
							cntTaskMin = maxMin;
						}
						listMin.clear();
				        for(int i=0;i<=cntTaskMin;i++)
				        {
				        	//cntTaskMin
				        	listMin.add(""+i);
				        }		 
				        
				        adpMin = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listMin);
				        adpMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        spnMin.setAdapter(adpMin);
					    spnMin.setPrompt("����");
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent)
					{
						// TODO Auto-generated method stub

					}
				});
			    
			    

			    
		        if(listMin == null)
		        {
		        	listMin = new ArrayList<String>();		
		        }
		        
		        spnMin = (Spinner) dialog.findViewById(R.id.spnManualTaskMin);
		        adpMin = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listMin);
		        adpMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        spnMin.setAdapter(adpMin);
			    spnMin.setPrompt("����");
			    /**
		         * 
		         * @author Richard
		         * ѡ��ʱ�䣬����
		         *
		         *
		         */
			    spnMin.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) 
					{
						// TODO Auto-generated method stub
						selectMin = Integer.parseInt(adpMin.getItem(position));
						
						if(selectMin == cntTaskMaxMin)
						{
							cntTaskSec = cntTaskMaxSec;
						}
						else
						{
							cntTaskSec = maxSec;
						}
						listSec.clear();
				        for(int i=0;i<=cntTaskSec;i++)
				        {
				        	//cntTaskSec
				        	listSec.add(""+i);
				        }		 
				        
				        adpSec = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listSec);
				        adpSec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        spnSec.setAdapter(adpSec);
					    spnSec.setPrompt("��");
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent)
					{
						// TODO Auto-generated method stub
						//selectMin = 0;
					}
				});
			    
		        if(listSec == null) 
		        {
		        	listSec = new ArrayList<String>();	
		        }
		        spnSec = (Spinner) dialog.findViewById(R.id.spnManualTaskSec);
		        adpSec = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listSec);
		        adpSec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        spnSec.setAdapter(adpSec);
			    spnSec.setPrompt("��");
			    /**
		         * 
		         * @author Richard
		         * ѡ��ʱ�䣬��
		         *
		         *
		         */
			    spnSec.setOnItemSelectedListener(new OnItemSelectedListener() 
			    {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id)
					{
						// TODO Auto-generated method stub
						selectSec = Integer.parseInt(adpSec.getItem(position));
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) 
					{
						// TODO Auto-generated method stub
						//selectMin = 0;
					}
				});
			    
			    Button yesButton = (Button) dialog.findViewById(R.id.button_yes_manual);
				Button noButton = (Button) dialog.findViewById(R.id.button_no_manual);
				 /**
		         * 
		         * @author Richard
		         * ȷ���¼�
		         *
		         *
		         */
				yesButton.setOnClickListener( new OnClickListener() 
			    {
					
					@Override
					public void onClick(View v) 
					{
						// TODO Auto-generated method stub
						System.out.println(selectName);
						int selectTime = selectHour *3600+ selectMin*60 + selectSec;
						System.out.println(selectTime);
						TodoItem t = new TodoItem(GlobalData.planBoard.get(selectName),selectTime);
						//���TodoList
						try 
						{
							GlobalData.todoList.addItem(t);
						} 
						catch (PlanHasExistedException e)
						{
							// TODO Auto-generated catch block
							new AlertDialog.Builder(context)
		            		.setTitle("СT")
		            		.setMessage("�Լ��������ƻ��ˣ������ж�ϲ��������ְ�~")
		            		.setPositiveButton("��֪����",null).show();
							e.printStackTrace();
						} 
						catch (ZeroValueException e) {
							// TODO Auto-generated catch block
							new AlertDialog.Builder(context)
		            		.setTitle("СT")
		            		.setMessage("ʱ����0������Ҫ��͵���ò���~")
		            		.setPositiveButton("��֪����",null).show();
							e.printStackTrace();
						}
						refresh();
						dialog.dismiss();
					}
				});
				 /**
		         * 
		         * @author Richard
		         * ȡ���¼�
		         *
		         *
		         */
			   noButton.setOnClickListener( new OnClickListener()
			   {
					
					@Override
					public void onClick(View v)
					{
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				
				
				
				
			}

			
		});
		/**
         * 
         * @author Richard
         * �Զ����ɰ�ť���趨����¼�		
         *
         *
         */
		btnAuto.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v)
			{
				/**
		         * 
		         * @author Richard
		         * 	���Զ����ɶԻ���		
		         *
		         *
		         */
				createCustomDialog_auto();
			}

			private void createCustomDialog_auto() 
			{
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(StartActivity.this,R.style.CustomDialog);
		        dialog.setTitle("�Զ����ɼƻ�");
				dialog.setContentView(R.layout.dialog_auto);// Ϊ�Ի��������Զ��岼��
		        dialog.show();
		        
		        int seekBarMax = 10;
				int seekBarInit = 5;
				maxHour = 10;
				maxMin = 59;
				
		        final SeekBar skbImportance = (SeekBar) dialog.findViewById(R.id.seekbarUrgency);
				skbImportance.setMax(seekBarMax);
		        skbImportance.setProgress(seekBarInit);
		        
		        
		        List<String>listHour = new ArrayList<String>();
		        for(int i=0;i<=maxHour;i++)
		        {
		        	listHour.add(""+i);
		        }
		        List<String>listMin = new ArrayList<String>();
		        for(int i=0;i<=maxMin;i++)
		        {
		        	listMin.add(""+i);
		        }      
		        final Spinner spnHour = (Spinner) dialog.findViewById(R.id.spnAutoTaskHour);        
		        final Spinner spnMin = (Spinner) dialog.findViewById(R.id.spnAutoTaskMin); 
		        final ArrayAdapter<String>adpHour = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listHour);
		        adpHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        final ArrayAdapter<String>adpMin = new ArrayAdapter<String>(StartActivity.this,android.R.layout.simple_spinner_item,listMin);
		        adpMin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        
		        spnHour.setAdapter(adpHour);
		        spnMin.setAdapter(adpMin);
		        spnHour.setPrompt("Сʱ");
		        spnMin.setPrompt("����");
		        
		        spnHour.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) 
					{
						// TODO Auto-generated method stub
						selectHour = Integer.parseInt(adpHour.getItem(position));
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) 
					{
						// TODO Auto-generated method stub
						
					}
				});
		       
		        spnMin.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) 
					{
						// TODO Auto-generated method stub
						selectMin = Integer.parseInt(adpMin.getItem(position));
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent)
					{
						// TODO Auto-generated method stub
						
					}
				});
		      //Button
		       
				Button yesButton = (Button) dialog.findViewById(R.id.button_yes_auto);
				Button noButton = (Button) dialog.findViewById(R.id.button_no_auto);
				
				yesButton.setOnClickListener(new OnClickListener()
		        {
		            @Override
		            public void onClick(View v)
		            {
		            	nowPriorityNumber = skbImportance.getProgress() - 5;
		            	System.out.println("~~~~~~~~~");
		            	System.out.println(selectHour);
		            	System.out.println(selectMin);
		            	System.out.println(nowPriorityNumber);
		            	int selectTime = selectHour*3600 + selectMin * 60;
		            	if(selectTime <= 0)
		            	{
		            		//����
		            		new AlertDialog.Builder(context)
		            		.setTitle("СT")
		            		.setMessage("ʱ����0�ļƻ��������������~")
		            		.setPositiveButton("��֪����",null).show();
		            		return;
		            	}
		            	GlobalData.todoList.clear();
		            	//�Զ�����TodoList
		            	for(Plan p : GlobalData.planBoard.values())
		            	{
		            		System.out.println(p.getTotalTime());
		            		System.out.println(p.getRestTime());
		            	}
		            	GlobalData.taskGenerator.generateTask(GlobalData.planBoard, selectTime, nowPriorityNumber);
		            	GlobalData.taskGenerator.output(GlobalData.todoList);
		            	
		            	for(TodoItem i : GlobalData.todoList.values()) 
		            	{
		            		System.out.println(i.getPlan().getName());
		            	}
		            	refresh();
		                dialog.dismiss();
		            }
		        });		
				
				
				noButton.setOnClickListener(new OnClickListener()
		        {
		            @Override
		            public void onClick(View v)
		            {
		                dialog.dismiss();
		            }
		        });		
				
				
				
				
			}
			
			
			
			
			
		});
		/**
         * 
         * @author Richard
         *ȡ����ť�����õ���¼�	
         *
         *
         */
		btnCancel.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// ���
				
				GlobalData.todoList.clear();
				refresh();
			}
		});
		/**
         * 
         * @author Richard
         * ִ�а�ť�����õ���¼�	
         *
         *
         */
		btnExecute.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				if(GlobalData.todoList.size() == 0)
				{
					//��������������������������������
					new AlertDialog.Builder(context)
            		.setTitle("СT")
            		.setMessage("��ȥ��ִ���嵥�м�������ɣ�")
            		.setPositiveButton("��֪����",null).show();
					return ;
				}
				GlobalData.timeBlockList = TimeBlockList.createTimeBlockList(GlobalData.todoList, GlobalData.timeSpliter);
				GlobalData.todoList.clear();
				refresh();
				Intent intent = new Intent();
				intent.setClass(StartActivity.this, ExecuteActivity.class);
				startActivity(intent);				
			}

		});
		
	}
 	/**
     * 
     * @author Richard
     * ˢ��todoListView
     *
     *
     */
	public void refresh() 
	{
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String,Object>>();
        for(TodoItem p : GlobalData.todoList.values())
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.icon_5_d);//ͼ����Դ��ID 
            map.put("ItemWinName", p.getPlan().getName());
            map.put("ItemTime", "����ʱ��:"+p.getTime()/3600+"Сʱ"+p.getTime()%3600/60+"��"+p.getTime()%60 + "��");
            map.put("ItemCloseWin",android.R.drawable.ic_menu_close_clear_cancel);
            remoteWindowItem.add(map);
        }
        
        
        // ������������Item�Ͷ�̬�����Ӧ��Ԫ�� 
        todoButtonAdapter listItemAdapter = new todoButtonAdapter
        (
        		
        	context,
            remoteWindowItem,//����Դ 
            R.layout.listview,//ListItem��XMLʵ��
            //��̬������ImageItem��Ӧ������ 
            new String[] {"ItemImage","ItemWinName","ItemTime","ItemCloseWin"},
            //ImageItem��XML�ļ������һ��ImageView,����TextView ID 
            new int[] {R.id.ItemImage,R.id.ItemWinName,R.id.ItemTime,R.id.ItemCloseWin}
        );
        todoListView.setAdapter(listItemAdapter);
        
		
		
	}
	
	
}
