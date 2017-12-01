package com.tempus.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.huanglifu.ds.GlobalData;
import com.huanglifu.strategy.OptimalGenerator;
import com.huanglifu.strategy.PlainSpliter;
import com.huanglifu.strategy.TomatoSpliter;
/*
 * 设置参数类：将用户设置的参数，包括时间分配方案，任务分配方案，座右铭列表等，
 * 记录下来，并提供修改保存，恢复默认等功能。
 */
public class SetupActivity extends Activity
{
	//座右铭字符串
	private String strMotto;
	//时间分配选择下拉框;
	private Spinner spnTimeSelect;
	//时间分配选择适配器
	private ArrayAdapter<String> adpTimeSelect;
	//时间分配选择列表
	private List<String> listTimeSelect;
	//时间分配选择字符串
	private String[] strTimeSpliter = 
	{
			"番茄时间分配法（25分钟工作段 + 5分钟休息段  + ...） 推荐",
			"普通时间分配法（按任务项初始时间划分时间段，不自动添加休息时间）"
	};
	//当前选择时间分配方案
	private int posTimeSpliter;
	//任务分配方案下拉框
	private Spinner spnTaskGenerator;
	//任务分配方案适配器
	private ArrayAdapter<String> adpTaskGenerator;
	//任务分配方案列表
	private List<String> listTaskGenerator;
	//任务分配方案字符串
	private String[] strTaskGenerator = 
	{
			"默认最优化方案（基于背包算法）",
			"随机方案"
	};
	//当前选择任务分配方案
	private int posTaskGenerator;
	//保存设置按钮
	private Button btnSaveData;
	//恢复默认按钮
	private Button btnRecover;
	//当前界面上下文
	public static Context context;
	//座右铭列表
	private ListView listView;
	//当前选中座右铭
	private int cntMotto = 1000;
	//添加座右铭按钮
	private Button btnAddMotto;
	//删除座右铭按钮
	private Button btnSubMotto;
	//编辑座右铭文字框
	private EditText edtMotto;
	//列表视图自定义适配器
	private MyListAdapter adpListView = new MyListAdapter();
	
	/*
	 * (non-Javadoc)界面创建函数，实现对按钮、下拉框、文本框、ListView、字符串适配器的定义，
	 * 根据后台记录的setupSchem，填充各系统控件，并设置相应的监听事件。
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_setup);
        System.out.println("~~~~~1~~~~~");
        btnAddMotto = (Button) findViewById(R.id.buttonAddMotto);
        System.out.println(findViewById(R.id.buttonSubMotto));
        btnSubMotto = (Button) findViewById(R.id.buttonSubMotto);
        System.out.println("~~~~~3~~~~~");
        edtMotto = (EditText) findViewById(R.id.editTextMotto);
        btnAddMotto.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(edtMotto.getText()!=null)
				{
					strMotto = edtMotto.getText().toString();
					if(strMotto.length() == 0)
					{
						acknowledgenull();
						return ;
					}
					System.out.println("~~~"+strMotto);
					GlobalData.setupScheme.getLstMotto().add(strMotto);
					adpListView.notifyDataSetChanged();
					acknowledgeadd();
				}
			}
		});
        btnSubMotto.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(cntMotto != 1000)
				{
					GlobalData.setupScheme.getLstMotto().remove(cntMotto);
					cntMotto = 1000;
					adpListView.notifyDataSetChanged();
					acknowledgesub();
				}
			}
		});
        listView = (ListView) findViewById(R.id.listMotto);
        listView.setAdapter(adpListView);
        //listView.add
        listView.setBackgroundResource(R.drawable.listview_background);
  
        //TextView tvTimeSelect = (TextView) findViewById(R.id.textView1);
        spnTimeSelect = (Spinner) findViewById(R.id.spinnerTime);
        listTimeSelect = new ArrayList<String>();
        for(String S:strTimeSpliter)
        {
        	listTimeSelect.add(S);
        }
        adpTimeSelect = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listTimeSelect);
        adpTimeSelect.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTimeSelect.setAdapter(adpTimeSelect);
        spnTimeSelect.setPrompt("请选择时间分配法");
        System.out.println("~~~~~~~~~~~");
        
        
        spnTaskGenerator = (Spinner) findViewById(R.id.spinnerTask);
        listTaskGenerator = new ArrayList<String>();
        for(String S:strTaskGenerator)
        {
        	listTaskGenerator.add(S);
        }
        adpTaskGenerator = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listTaskGenerator);
        adpTaskGenerator.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTaskGenerator.setAdapter(adpTaskGenerator);
        spnTaskGenerator.setPrompt("请选择任务生产算法");
        System.out.println("!!!!!!!!!");
        btnSaveData = (Button) findViewById(R.id.buttonsave);
        btnSaveData.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				int pos = spnTimeSelect.getSelectedItemPosition();
				if(pos != posTimeSpliter) 
				{
					switch(pos){
						case 0:
							GlobalData.setupScheme.setTimeSpliter(TomatoSpliter.getInstance());
							break;
						case 1:
							GlobalData.setupScheme.setTimeSpliter(PlainSpliter.getInstance());
							break;
					}					
				}
				pos = spnTaskGenerator.getSelectedItemPosition();
				if(pos != posTaskGenerator) 
				{
					switch(pos)
					{
						case 0:
							GlobalData.setupScheme.setTaskGenerator(OptimalGenerator.getInstance());
							break;
						case 1:
							GlobalData.setupScheme.setTaskGenerator(OptimalGenerator.getInstance());			
							break;
					}					
				}
				refresh();
				acknowledge();
			}
		});
        System.out.println("@@@@@@@@@@");
        btnRecover = (Button) findViewById(R.id.buttonrecover);
        btnRecover.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				GlobalData.setupScheme.setTimeSpliter(TomatoSpliter.getInstance());
				GlobalData.setupScheme.setTaskGenerator(OptimalGenerator.getInstance());
				GlobalData.setupScheme.setDefault();
				adpListView.notifyDataSetChanged();
				refresh();
				acknowledgerecover();
			}
		});
	    refresh();
	}
	/*
	 * 刷新函数，实现对下拉框设置之后的刷新功能及座右铭添加或删除之后的刷新功能。
	 */
	public void refresh() {
        if(GlobalData.setupScheme.getTimeSpliter() instanceof TomatoSpliter)
        {
        	posTimeSpliter = 0;
        }
        else
        {
        	posTimeSpliter = 1;
        }
        spnTimeSelect.setSelection(posTimeSpliter);
        
        if(GlobalData.setupScheme.getTaskGenerator() instanceof OptimalGenerator)
        {
        	posTaskGenerator = 0;
        }
        else
        {
        	posTaskGenerator = 1;
        }
        spnTaskGenerator.setSelection(posTaskGenerator);

	}
	

	/*
	 * 确认保存提示函数，弹出设置成功窗口。
	 */
	public void acknowledge()
	{
		new AlertDialog.Builder(this).setTitle("小T").setMessage("设置成功了，赶快去试试吧~").setPositiveButton("确定",null).show();
	}
	/*
	 * 恢复默认提示函数，弹出恢复默认成功窗口。
	 */
	public void acknowledgerecover()
	{
		new AlertDialog.Builder(this).setTitle("小T").setMessage("恢复默认成功！").setPositiveButton("确定",null).show();
	}
	/*
	 * 添加座右铭提示函数，弹出添加成功窗口
	 */
	public void acknowledgeadd()
	{
		new AlertDialog.Builder(this).setTitle("小T").setMessage("添加成功！").setPositiveButton("确定",null).show();
	}
	/*
	 * 删除座右铭提示函数，弹出删除成功窗口
	 */
	public void acknowledgesub()
	{
		new AlertDialog.Builder(this).setTitle("小T").setMessage("删除成功！").setPositiveButton("确定",null).show();
	}
	/*
	 * 添加座右铭提示函数，若设置座右铭为空字符串，弹出请添加正确内容窗口。
	 */
	public void acknowledgenull()
	{
		new AlertDialog.Builder(this).setTitle("小T").setMessage("明明是空的啊亲！").setPositiveButton("确定",null).show();
	}
	/*
	 * ListView适配器类，为ListVIew提供适配器的初始化和基本函数操作。
	 */
	private class MyListAdapter extends BaseAdapter
	{
		/*
		 * (non-Javadoc)适配器计数函数，返回适配器中元素个数。
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return GlobalData.setupScheme.getLstMotto().size();
		}
		/*
		 * (non-Javadoc)根据位置返回条目。
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Object getItem(int position) 
		{
			// TODO Auto-generated method stub
			return position;
		}
		/*
		 * (non-Javadoc)根据位置返回条目Id
		 * @see android.widget.Adapter#getItemId(int)
		 */
		@Override
		public long getItemId(int position) 
		{
			// TODO Auto-generated method stub
			return position;
		}
		/*
		 * (non-Javadoc)设置每个条目中的内容，并根据对应位置设定position对应ViewHolder的内容
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) 
		{
			// TODO Auto-generated method stub
			ViewHolder holder ;
			if(convertView == null)
			{
				holder = new ViewHolder();
				convertView = View.inflate(getApplicationContext(), R.layout.list_item, null);
				holder.tv_msg = (TextView) convertView.findViewById(R.id.TextMotto);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv_msg.setText(GlobalData.setupScheme.getLstMotto().get(position));
			
			convertView.setOnClickListener(new OnClickListener() 
			{
				
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					cntMotto = position;
					edtMotto.setText(GlobalData.setupScheme.getLstMotto().get(position));
				}
			});
			return convertView;
		}
		
	}
	private class ViewHolder
	{
		//需要更改的文本信息
		TextView tv_msg;
	}
}


