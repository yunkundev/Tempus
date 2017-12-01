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
 * 软件的设置方案，保存的软件的各项设置信息
 * 在软件结束时被串行化到文件以保存用户设置，并在每次软件启动时读取并应用到软件中
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
		lstMotto = new ArrayList<String>(Arrays.asList("有志者，事竟成！",
										"10000小时理论：一般来说，一个人只要在某个领域累计学习10000个小时，就能成为那个领域的专家。", 
										"既然选择了远方，就只顾风雨兼程",
										"此刻打盹，你将做梦; 此刻坚持，你将圆梦",
										"有人比你强这并不可怕，可怕的是那些比你强的人都比你努力。"
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
