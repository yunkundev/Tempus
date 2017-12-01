package com.tempus.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.huanglifu.ds.GlobalData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class lvButtonAdapter extends BaseAdapter 
{
	 /**
     * 
     * @author Richard
     *		
     *	Listview中每一项包含的内容
     *
     */
    private class buttonViewHolder 
    {
        ImageView appIcon;
        TextView appName;
        TextView appTime;
        ImageButton buttonClose;
    }
    
    private ArrayList<HashMap<String, Object>> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String[] keyString;
    private int[] valueViewID;
    private buttonViewHolder holder;
    
    /**
    * 
    * @author Richard
    *	设置点击的监听事件	
    *	lvButtonAdapter的初始化函数
    *
    */
    
    public lvButtonAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource,
            String[] from, int[] to) 
    {
        mAppList = appList;
        mContext = c;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        keyString = new String[from.length];
        valueViewID = new int[to.length];
        System.arraycopy(from, 0, keyString, 0, from.length);
        System.arraycopy(to, 0, valueViewID, 0, to.length);
    }
    
    @Override
    public int getCount() 
    {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) 
    {
        return position;
    }

    public void removeItem(int position)
    {
        mAppList.remove(position);
        this.notifyDataSetChanged();
    }
    
    
    /**
     * 
     * @author Richard
     *	获得Listview	
     *
     *
     */
    
    @SuppressLint("InflateParams")
	@SuppressWarnings("deprecation")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        if (convertView != null) 
        {
            holder = (buttonViewHolder) convertView.getTag();
        } 
        else 
        {
            convertView = mInflater.inflate(R.layout.listview, null);
            holder = new buttonViewHolder();
            holder.appIcon = (ImageView)convertView.findViewById(valueViewID[0]);
            holder.appName = (TextView)convertView.findViewById(valueViewID[1]);
            holder.appTime = (TextView)convertView.findViewById(valueViewID[2]);
            holder.buttonClose = (ImageButton)convertView.findViewById(valueViewID[3]);
            convertView.setTag(holder);
        }
        
        HashMap<String, Object> appInfo = mAppList.get(position);
        if (appInfo != null) 
        {
            String aname = (String) appInfo.get(keyString[1]);
            String time =(String) appInfo.get(keyString[2]);
            int mid = (Integer)appInfo.get(keyString[0]);
            int bid = (Integer)appInfo.get(keyString[3]);
            holder.appName.setText(aname);
            holder.appTime.setText(time);
            holder.appIcon.setImageDrawable(holder.appIcon.getResources().getDrawable(mid));
            holder.buttonClose.setImageDrawable(holder.buttonClose.getResources().getDrawable(bid));
            holder.buttonClose.setOnClickListener(new lvButtonListener(position));
        }        
        return convertView;
    }
    
    
    /**
     * 
     * @author Richard
     *	设置点击的监听事件	
     *
     *
     */
    class lvButtonListener implements OnClickListener 
    {
        private int position;

        lvButtonListener(int pos) 
        {
            position = pos;
        }
        
        @Override
        public void onClick(View v) 
        {
            int vid=v.getId();
            if (vid == holder.buttonClose.getId())
            {
                //removeItem(position);
				@SuppressWarnings("unchecked")
				Map<String,String> map=(Map<String, String>) getItem(position);
            	String deleteName = map.get("ItemWinName");
            	GlobalData.planBoard.remove(deleteName);
            	System.out.println(deleteName);
            	//PlanActivity.refresh();
            	GlobalData.refresh();
            }
        }
    }

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) 
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
}