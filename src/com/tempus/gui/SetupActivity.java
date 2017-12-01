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
 * ���ò����ࣺ���û����õĲ���������ʱ����䷽����������䷽�����������б�ȣ�
 * ��¼���������ṩ�޸ı��棬�ָ�Ĭ�ϵȹ��ܡ�
 */
public class SetupActivity extends Activity
{
	//�������ַ���
	private String strMotto;
	//ʱ�����ѡ��������;
	private Spinner spnTimeSelect;
	//ʱ�����ѡ��������
	private ArrayAdapter<String> adpTimeSelect;
	//ʱ�����ѡ���б�
	private List<String> listTimeSelect;
	//ʱ�����ѡ���ַ���
	private String[] strTimeSpliter = 
	{
			"����ʱ����䷨��25���ӹ����� + 5������Ϣ��  + ...�� �Ƽ�",
			"��ͨʱ����䷨�����������ʼʱ�仮��ʱ��Σ����Զ������Ϣʱ�䣩"
	};
	//��ǰѡ��ʱ����䷽��
	private int posTimeSpliter;
	//������䷽��������
	private Spinner spnTaskGenerator;
	//������䷽��������
	private ArrayAdapter<String> adpTaskGenerator;
	//������䷽���б�
	private List<String> listTaskGenerator;
	//������䷽���ַ���
	private String[] strTaskGenerator = 
	{
			"Ĭ�����Ż����������ڱ����㷨��",
			"�������"
	};
	//��ǰѡ��������䷽��
	private int posTaskGenerator;
	//�������ð�ť
	private Button btnSaveData;
	//�ָ�Ĭ�ϰ�ť
	private Button btnRecover;
	//��ǰ����������
	public static Context context;
	//�������б�
	private ListView listView;
	//��ǰѡ��������
	private int cntMotto = 1000;
	//�����������ť
	private Button btnAddMotto;
	//ɾ����������ť
	private Button btnSubMotto;
	//�༭���������ֿ�
	private EditText edtMotto;
	//�б���ͼ�Զ���������
	private MyListAdapter adpListView = new MyListAdapter();
	
	/*
	 * (non-Javadoc)���洴��������ʵ�ֶ԰�ť���������ı���ListView���ַ����������Ķ��壬
	 * ���ݺ�̨��¼��setupSchem������ϵͳ�ؼ�����������Ӧ�ļ����¼���
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
        spnTimeSelect.setPrompt("��ѡ��ʱ����䷨");
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
        spnTaskGenerator.setPrompt("��ѡ�����������㷨");
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
	 * ˢ�º�����ʵ�ֶ�����������֮���ˢ�¹��ܼ���������ӻ�ɾ��֮���ˢ�¹��ܡ�
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
	 * ȷ�ϱ�����ʾ�������������óɹ����ڡ�
	 */
	public void acknowledge()
	{
		new AlertDialog.Builder(this).setTitle("СT").setMessage("���óɹ��ˣ��Ͽ�ȥ���԰�~").setPositiveButton("ȷ��",null).show();
	}
	/*
	 * �ָ�Ĭ����ʾ�����������ָ�Ĭ�ϳɹ����ڡ�
	 */
	public void acknowledgerecover()
	{
		new AlertDialog.Builder(this).setTitle("СT").setMessage("�ָ�Ĭ�ϳɹ���").setPositiveButton("ȷ��",null).show();
	}
	/*
	 * �����������ʾ������������ӳɹ�����
	 */
	public void acknowledgeadd()
	{
		new AlertDialog.Builder(this).setTitle("СT").setMessage("��ӳɹ���").setPositiveButton("ȷ��",null).show();
	}
	/*
	 * ɾ����������ʾ����������ɾ���ɹ�����
	 */
	public void acknowledgesub()
	{
		new AlertDialog.Builder(this).setTitle("СT").setMessage("ɾ���ɹ���").setPositiveButton("ȷ��",null).show();
	}
	/*
	 * �����������ʾ������������������Ϊ���ַ����������������ȷ���ݴ��ڡ�
	 */
	public void acknowledgenull()
	{
		new AlertDialog.Builder(this).setTitle("СT").setMessage("�����ǿյİ��ף�").setPositiveButton("ȷ��",null).show();
	}
	/*
	 * ListView�������࣬ΪListVIew�ṩ�������ĳ�ʼ���ͻ�������������
	 */
	private class MyListAdapter extends BaseAdapter
	{
		/*
		 * (non-Javadoc)����������������������������Ԫ�ظ�����
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return GlobalData.setupScheme.getLstMotto().size();
		}
		/*
		 * (non-Javadoc)����λ�÷�����Ŀ��
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Object getItem(int position) 
		{
			// TODO Auto-generated method stub
			return position;
		}
		/*
		 * (non-Javadoc)����λ�÷�����ĿId
		 * @see android.widget.Adapter#getItemId(int)
		 */
		@Override
		public long getItemId(int position) 
		{
			// TODO Auto-generated method stub
			return position;
		}
		/*
		 * (non-Javadoc)����ÿ����Ŀ�е����ݣ������ݶ�Ӧλ���趨position��ӦViewHolder������
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
		//��Ҫ���ĵ��ı���Ϣ
		TextView tv_msg;
	}
}


