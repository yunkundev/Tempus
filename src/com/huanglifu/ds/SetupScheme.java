package com.huanglifu.ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.huanglifu.strategy.OptimalGenerator;
import com.huanglifu.strategy.PlainSpliter;
import com.huanglifu.strategy.TaskGenerator;
import com.huanglifu.strategy.TimeSpliter;

/**
 * ��������÷��������������ĸ���������Ϣ
 * ���������ʱ�����л����ļ��Ա����û����ã�����ÿ���������ʱ��ȡ��Ӧ�õ������
 * @author Lifu.Huang
 *
 */
public class SetupScheme implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6550634799013527963L;
	
	public SetupScheme() 
	{
		this.setDefault();
	}
	public String getFileBreakAudio() 
	{
		return fileBreakAudio;
	}
	public void setFileBreakAudio(String fileBreakAudio) 
	{
		this.fileBreakAudio = fileBreakAudio;
	}
	public String getFileDoneAudio()
	{
		return fileDoneAudio;
	}
	public void setFileDoneAudio(String fileDoneAudio)
	{
		this.fileDoneAudio = fileDoneAudio;
	}
	public String getFileStartAudio()
	{
		return fileStartAudio;
	}
	public void setFileStartAudio(String fileStartAudio) 
	{
		this.fileStartAudio = fileStartAudio;
	}
	public String getFileEndAudio() 
	{
		return fileEndAudio;
	}
	public void setFileEndAudio(String fileEndAudio) 
	{
		this.fileEndAudio = fileEndAudio;
	}
	public TimeSpliter getTimeSpliter() 
	{
		return timeSpliter;
	}
	public void setTimeSpliter(TimeSpliter timeSpliter) 
	{
		this.timeSpliter = timeSpliter;
	}
	public TaskGenerator getTaskGenerator() 
	{
		return taskGenerator;
	}
	public void setTaskGenerator(TaskGenerator taskGenerator) 
	{
		this.taskGenerator = taskGenerator;
	}
	public List<String> getLstMotto() 
	{
		return lstMotto;
	}
	public String getFileFailAudio() 
	{
		return fileFailAudio;
	}
	public void setFileFailAudio(String fileFailAudio) 
	{
		this.fileFailAudio = fileFailAudio;
	}
	public void setDefault() 
	{
		fileBreakAudio = "/res/audio/break.wav";
		fileDoneAudio = "/res/audio/done.wav";
//		fileStartAudio = null;
//		fileEndAudio = null;
//		fileFailAudio = null;
		timeSpliter = PlainSpliter.getInstance();
		taskGenerator = OptimalGenerator.getInstance();
		lstMotto = new ArrayList<String>(Arrays.asList("��־�ߣ��¾��ɣ�",
										"10000Сʱ���ۣ�һ����˵��һ����ֻҪ��ĳ�������ۼ�ѧϰ10000��Сʱ�����ܳ�Ϊ�Ǹ������ר�ҡ�", 
										"��Ȼѡ����Զ������ֻ�˷�����",
										"�˿̴���㽫����; �˿̼�֣��㽫Բ��",
										"���˱���ǿ�Ⲣ�����£����µ�����Щ����ǿ���˶�����Ŭ����"
										));
	}
	String fileBreakAudio;
	String fileDoneAudio;
	String fileStartAudio;
	String fileEndAudio;
	String fileFailAudio;

	TimeSpliter timeSpliter;
	TaskGenerator taskGenerator;
	List<String> lstMotto = new ArrayList<String>();
	
}
