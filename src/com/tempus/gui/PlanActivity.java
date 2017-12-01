package com.tempus.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huanglifu.ds.EmptyValueException;
import com.huanglifu.ds.GlobalData;
import com.huanglifu.ds.Plan;
import com.huanglifu.ds.PlanHasExistedException;
import com.huanglifu.ds.UnmatchedDataException;
import com.huanglifu.ds.ZeroValueException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ViewSwitcher;

public class PlanActivity extends Activity
{
    int nowImportantNumber;
	int nowTensionNumber;
	String planName;
	String planDescribe;
	boolean mChecked;
	
	int selectHour;
	int selectMinute;
	int selectTime;
	int mode;	// 0 for creation , 1 for editing
	public static Context context;
	public  ListView vncListView;
	private ViewSwitcher vs;
	private int curPage = 0;
	Plan p;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan);
		Button btnInsert = (Button) findViewById(R.id.BtnStart);
		btnInsert.setOnClickListener(new OnClickListener() 
        {
        	
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				mode = 0;
				 /**
			     * 
			     * @author Richard
			     *	�����½��Ի���
			     *
			     *
			     */
				createCustomDialog();
			}		
		});	
		
		// ����Layout�е�ListView	
        vncListView = (ListView)findViewById(android.R.id.list);
        vs = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
        //vncListView.setDivider(null);
        //vncListView.setBackgroundResource(R.drawable.listview_background);
        /**
         * 
         * @author Richard
         *	ͼƬ��ť��������������½��Ի���
         *
         *
         */
        ImageButton btnIncrease = (ImageButton) findViewById(R.id.imageButton1);
        btnIncrease.setOnClickListener(new OnClickListener() 
        {
        	
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				mode = 0;
				 /**
			     * 
			     * @author Richard
			     *	�����½��Ի���
			     *
			     *
			     */
				createCustomDialog();
			}		
		});	
        context = this;
        refresh();
//     // ���ɶ�̬���飬�������� 
//        ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String,Object>>();
//        for(int i=0;i<10;i++)
//        {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemImage", R.drawable.icon_5_d);//ͼ����Դ��ID 
//            map.put("ItemWinName", "Window ID "+i);
//            map.put("ItemCloseWin",android.R.drawable.ic_menu_close_clear_cancel);
//            remoteWindowItem.add(map);
//        }
//        
//        // ������������Item�Ͷ�̬�����Ӧ��Ԫ�� 
//        lvButtonAdapter listItemAdapter = new lvButtonAdapter
//        (
//            this,
//            remoteWindowItem,//����Դ 
//            R.layout.listview,//ListItem��XMLʵ��
//            //��̬������ImageItem��Ӧ������ 
//            new String[] {"ItemImage","ItemWinName", "ItemCloseWin"},
//            //ImageItem��XML�ļ������һ��ImageView,����TextView ID 
//            new int[] {R.id.ItemImage,R.id.ItemWinName,R.id.ItemCloseWin}
//        );
//        vncListView.setAdapter(listItemAdapter);
        /**
         * 
         * @author Richard
         *	vncListView,�����ʱ�����޸�����
         *
         *
         */ 
        vncListView.setOnItemClickListener(new OnItemClickListener() 
        {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				// TODO Auto-generated method stub
				//����
				Adapter adapter=parent.getAdapter();
				@SuppressWarnings("unchecked")
				Map<String,String> map = (Map<String, String>)adapter.getItem(position);
				System.out.println(map.get("ItemWinName"));
				String deleteName = map.get("ItemWinName");
				System.out.println(deleteName);
				p = GlobalData.planBoard.get(deleteName);
				mode = 1;
				createCustomDialog();	
				refresh();
			}
		});     

        refresh();
	}
	
	private void createCustomDialog() 
	{
		// TODO Auto-generated method stub
		selectHour = 0;
		selectMinute = 0;
        int seekBarMax = 9;
		int seekBarInit = 0;
		int maxHour = 10;
		int maxMinute = 59;
		final Dialog dialog = new Dialog(PlanActivity.this, R.style.CustomDialog);
        dialog.setTitle("�½�һ���ƻ�");
		dialog.setContentView(R.layout.dialog);// Ϊ�Ի��������Զ��岼��
        dialog.show();
        //���ƺ�����
        final EditText editTextName = (EditText) dialog.findViewById(R.id.txtPlanName);
        final EditText editTextDescribe = (EditText) dialog.findViewById(R.id.txtPlanDesc);
		//������
       
        
        final SeekBar skbImportance = (SeekBar) dialog.findViewById(R.id.seekbarImportance);
		skbImportance.setMax(seekBarMax);
        skbImportance.setProgress(seekBarInit);
        
        final SeekBar skbTension = (SeekBar) dialog.findViewById(R.id.seekbarUrgency);
        skbTension.setMax(seekBarMax);
        skbTension.setProgress(seekBarInit);
        
        
        List<String>listHour = new ArrayList<String>();
        for(int i=0;i<=maxHour;i++){
        	listHour.add(""+i);
        }
        List<String>listMinute = new ArrayList<String>();
        for(int i=0;i<=maxMinute;i++){
        	listMinute.add(""+i);
        }      
        final Spinner spnHour = (Spinner) dialog.findViewById(R.id.spinnerPlanHour);        
        final Spinner spnMinute = (Spinner) dialog.findViewById(R.id.spinnerPlanMinute); 
        
        
        final ArrayAdapter<String>adpHour = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listHour);
        adpHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String>adpMinute = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listMinute);
        adpMinute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spnHour.setAdapter(adpHour);
        spnMinute.setAdapter(adpMinute);
        spnHour.setPrompt("Сʱ");
        spnMinute.setPrompt("����");
        final CheckBox cb = (CheckBox) dialog.findViewById(R.id.checkboxSplitable);
        
        
        
        if(mode == 1)
        {
	        dialog.setTitle("�޸���ļƻ�");
	        spnHour.setSelection(p.getTotalTime()/3600);
	        spnMinute.setSelection(p.getTotalTime()%3600/60);
	        cb.setChecked(p.isSplitable());
	        editTextName.setText(p.getName());
	        editTextDescribe.setText(p.getDescription());
	        skbImportance.setProgress(p.getImportance()-1);
	        skbTension.setProgress(p.getUrgency()-1);
        }
        /**
         * 
         * @author Richard
         *	spnHour,�����ʱ����Сʱ��ѡ���¼�
         *
         *
         */ 
        spnHour.setOnItemSelectedListener(new OnItemSelectedListener() 
        {

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
        /**
         * 
         * @author Richard
         *	spnMinute,�����ʱ�������ӵ�ѡ���¼�
         *
         *
         */ 
        spnMinute.setOnItemSelectedListener(new OnItemSelectedListener() 
        {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) 
			{
				// TODO Auto-generated method stub
				selectMinute = Integer.parseInt(adpMinute.getItem(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) 
			{
				// TODO Auto-generated method stub
				
			}
		});
        
    
		Button okBtn = (Button) dialog.findViewById(R.id.btn_ok);
		    
        Button canclebtn = (Button) dialog.findViewById(R.id.btn_cancle);
        /**
         * 
         * @author Richard
         *	okBtn,ȷ�ϵ�ѡ��ť�������ȷ��ʱ�ռ�����е���Ϣ
         *
         *
         */ 
        okBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            	nowImportantNumber = skbImportance.getProgress()+1;
            	nowTensionNumber = skbTension.getProgress()+1;
            	planName = editTextName.getText().toString();
            	planDescribe = editTextDescribe.getText().toString();
            	mChecked = cb.isChecked();
                
                
                selectTime=selectHour*3600+selectMinute*60;
                
                
                System.out.println(planName);
                System.out.println(planDescribe);
                System.out.println(nowImportantNumber);
                System.out.println(nowTensionNumber);
                System.out.println(selectTime);
                System.out.println(mChecked);
            
                try
                {
                	//edit
	            	if(mode == 1)
	            	{
	            		if(p.getUsedTime() > p.getTotalTime())
	            		{
	            			throw new UnmatchedDataException();
	            		} 
	            		if(!p.getName().equals(planName)){
	                    	new AlertDialog.Builder(context)
	                		.setTitle("СT")
	                		.setMessage("��Ҫ�޸��ҵ����ֺò��ã�")
	                		.setPositiveButton("��֪����",null).show();
	    				
	            		}
	            		p.setDescription(planDescribe);
	            		p.setImportance(nowImportantNumber);
	            		p.setUrgency(nowTensionNumber);
	            		p.setTotalTime(selectTime);
	            		p.setSplitable(mChecked);
	  
	            	}
	            	else
	            	{
	            		Plan newPlan = new Plan(planName, planDescribe, nowImportantNumber, nowTensionNumber, selectTime, mChecked);
	            		//newPlan.setUsedTime(p.getUsedTime());
	            		GlobalData.planBoard.addPlan(newPlan);
	            	}
	                refresh();
	                dialog.dismiss();
	                
                }
                catch(EmptyValueException e)
                {
                	//�ռƻ���
                	new AlertDialog.Builder(context)
            		.setTitle("СT")
            		.setMessage("�����Ĳ����ƻ�ȡ�������")
            		.setPositiveButton("��֪����",null).show();
					e.printStackTrace();
                }
                catch(ZeroValueException e)
                {
                	//time == 0
                	new AlertDialog.Builder(context)
            		.setTitle("СT")
            		.setMessage("ʱ����0������Ҫ͵���ò���~")
            		.setPositiveButton("��֪����",null).show();
					e.printStackTrace();
                } catch (UnmatchedDataException e) {
					// �Ѿ�ִ��ʱ�� > ��ʱ��
                	new AlertDialog.Builder(context)
            		.setTitle("СT")
            		.setMessage("�����õ�ʱ���ִ��ʱ�仹Ҫ��~")
            		.setPositiveButton("��֪����",null).show();
					e.printStackTrace();
				} catch (PlanHasExistedException e) {
					// �ƻ����ظ�
					new AlertDialog.Builder(context)
            		.setTitle("СT")
            		.setMessage("�Լ��������ƻ��ˣ������ж�ϲ��������ְ�~")
            		.setPositiveButton("��֪����",null).show();
					e.printStackTrace();
				}
                
            }
        });
        /**
         * 
         * @author Richard
         *	okBtn,ȡ����ѡ��ť
         *
         *
         */
        
        canclebtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });				
	}
	 /**
     * 
     * @author Richard
     *	���´�ʱ�˿̵�listview
     *  ����ȡ�ļƻ�������뵽listview��
     *
     *
     */
	public void refresh() 
	{
		// TODO Auto-generated method stub
	     // ���ɶ�̬���飬�������� 
		
        ArrayList<HashMap<String, Object>> remoteWindowItem = new ArrayList<HashMap<String,Object>>();
        for(Plan p : GlobalData.planBoard.values())
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.icon_5_d);//ͼ����Դ��ID 
            map.put("ItemWinName", p.getName());
            map.put("ItemTime","�ƻ�ʱ��:"+p.getTotalTime()/3600+"Сʱ"+p.getTotalTime()%3600/60+"����"+"��ʣ��ʱ��:"+p.getRestTime()/3600+"Сʱ"+p.getRestTime()%3600/60+"����");
            map.put("ItemCloseWin",android.R.drawable.ic_menu_close_clear_cancel);
            remoteWindowItem.add(map);
            
        }
        
        
        // ������������Item�Ͷ�̬�����Ӧ��Ԫ�� 
        lvButtonAdapter listItemAdapter = new lvButtonAdapter
        (
        		
        	context,
            remoteWindowItem,//����Դ 
            R.layout.listview,//ListItem��XMLʵ��
            //��̬������ImageItem��Ӧ������ 
            new String[] {"ItemImage","ItemWinName","ItemTime", "ItemCloseWin"},
            //ImageItem��XML�ļ������һ��ImageView,����TextView ID 
            new int[] {R.id.ItemImage,R.id.ItemWinName,R.id.ItemTime,R.id.ItemCloseWin}
        );
        vncListView.setAdapter(listItemAdapter);
		if(GlobalData.planBoard.isEmpty())
		{
			if(curPage == 0)
			{
				vs.showNext();
				curPage = 1;
			}
		} 
		else 
		{
			if(curPage == 1)
			{
				vs.showPrevious();
				curPage = 0;
			}
		}
	}
}
