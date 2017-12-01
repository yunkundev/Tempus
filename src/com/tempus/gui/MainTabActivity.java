package com.tempus.gui;

import com.huanglifu.ds.GlobalData;
import com.tempus.gui.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainTabActivity extends TabActivity implements OnCheckedChangeListener
{
	
	private TabHost mTabHost;
	private Intent mAIntent;
	private Intent mBIntent;
	private Intent mCIntent;
	private Intent mDIntent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.maintabs);
        /**
         * 
         * @author Richard
         *	跳转到计划界面的Intent	
         *
         *
         */
        mAIntent = new Intent(this, PlanActivity.class);
        /**
         * 
         * @author Richard
         *	跳转到统计界面的Intent	
         *
         *
         */
        mBIntent = new Intent(this, CensusActivity.class);
        /**
         * 
         * @author Richard
         *	跳转到设计界面的Intent	
         *
         *
         */
        mCIntent = new Intent(this, SetupActivity.class);
        /**
         * 
         * @author Richard
         *	跳转到执行界面的Intent	
         *
         *
         */
        mDIntent = new Intent(this, StartActivity.class);
        
		((RadioButton) findViewById(R.id.radio_button0))
		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button1))
		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button2))
		.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button3))
		.setOnCheckedChangeListener(this);
        	
        setupIntent();
    }
    
   
    /**
     * 
     * @author Richard
     *	退出时注意保存数据值
     *
     *
     */
    @Override
    protected void onDestroy() 
    {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	GlobalData.storeData();
    }

    	
    /**
     * 
     * @author Richard
     *	点击时发生跳转
     *
     *
     */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		if(isChecked)
		{
			switch (buttonView.getId()) 
			{
			case R.id.radio_button0:
				this.mTabHost.setCurrentTabByTag("A_TAB");
				break;
			case R.id.radio_button1:
				this.mTabHost.setCurrentTabByTag("B_TAB");
				break;
			case R.id.radio_button2:
				this.mTabHost.setCurrentTabByTag("C_TAB");
				break;
			case R.id.radio_button3:
				this.mTabHost.setCurrentTabByTag("D_TAB");
				break;
			}
		}
		
	}
	
	 /**
     * 
     * @author Richard
     *	初始化Intent
     *
     *
     */
	
	private void setupIntent() 
	{
		this.mTabHost = getTabHost();
		TabHost localTabHost = this.mTabHost;

		localTabHost.addTab(buildTabSpec("A_TAB", R.string.main_plan,
				R.drawable.plan, this.mAIntent));
		localTabHost.addTab(buildTabSpec("B_TAB", R.string.main_census,
				R.drawable.execute, this.mBIntent));
		localTabHost.addTab(buildTabSpec("C_TAB", R.string.main_setup, 
				R.drawable.analysis, this.mCIntent));
		localTabHost.addTab(buildTabSpec("D_TAB", R.string.main_setup,
				R.drawable.setup, this.mDIntent));


	}
	
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) 
	{
		return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel),
				getResources().getDrawable(resIcon)).setContent(content);
	}
}