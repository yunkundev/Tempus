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
 * ����ͳ���ࣺ�ı��������ʾ�ĸ���ͳ����Ϣ������̨������ͳ����ʾ����Ļ����ִ��ʱ�䡢ִ�����ڡ��Ƿ��������ȡ�
 * ��Ҫ˼�����ַ����洢��̨�ṩ�������Ϣ������writeTextViewд����档
 */
public class CensusActivity extends Activity
{
	//�ƻ�ִ��ʱ���ı�
	private TextView textViewPlanTime;
	//ʵ��ִ��ʱ���ı�
	private TextView textViewDoTime;
	//��Чִ��ʱ���ı�
	private TextView textViewValueTime;
	//��Ч�����ı�
	private TextView textViewPercentage;
	//�Ƿ���������ı�
	private TextView textViewTaskComplete;
	//ִ�������ı�
	private TextView textViewDate;
	//�ƻ�ִ��ʱ���ַ���
	private String strPlanTime;
	//ʵ��ִ��ʱ���ַ���
	private String strDoTime;
	//��Чִ��ʱ���ַ���
	private String strValueTime;
	//��Ч�����ַ���
	private String strPercentage;
	//�Ƿ�����ַ���
	private String strTaskComplete;
//	ִ�������ַ���
	private String strDate;
	//����������
	static public Context context;
	/*
	 * ���洴��������������textView���ı�����������ϵ������ˢ�º�����ˢ���ı����ݡ�(non-Javadoc)
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
	 * ��ʼ���ַ�����������ȫ��û��taskRecorder��¼������г�ʼ�������������ַ�����ֵ��
	 */
	public void initTextView()
	{
		strPlanTime = new String("������:��");
		strDoTime = new String("������:��");
		strValueTime = new String("������:��");
		strPercentage = new String("��������");
		strTaskComplete = new String("������");
		strDate = new String("������");
	}
	/*
	 * �ַ���ˢ�º�������ȫ��û��taskRecorder��¼�������initTextView���ַ������г�ʼ����
	 * �����taskRecorder�л����Ӧ��Ϣ��д���ַ����У�������writeTextView������д������ı���
	 */
	public void refresh()
	{
		if(GlobalData.taskRecorder!=null)
		{
			strPlanTime = "��" + GlobalData.secToString(GlobalData.taskRecorder.getTimeExp());
			strDoTime = "��" + GlobalData.secToString(GlobalData.taskRecorder.getTimeAct());
			strValueTime = "��" + GlobalData.secToString(GlobalData.taskRecorder.getTimeEff());
			double ratio = ((double)GlobalData.taskRecorder.getTimeEff())*100/GlobalData.taskRecorder.getTimeAct();
			
			strPercentage = "��" + Math.round(ratio) + "%";
			if(GlobalData.taskRecorder.isFinished())
			{
				strTaskComplete = "��" + "��";
			}
			else
			{
				strTaskComplete = "��" + "��";
			}
			SimpleDateFormat formatter = new SimpleDateFormat("��yyyy��MM��dd��    HH:mm:ss",Locale.CHINA);     
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
	 * д�����ı�����������ӦStringд���ı����ڽ��������ʾ��
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
