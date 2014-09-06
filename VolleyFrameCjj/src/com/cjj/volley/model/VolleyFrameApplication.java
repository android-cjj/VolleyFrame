package com.cjj.volley.model;

import android.app.Application;

import com.cjj.volley.me.CjjVolley;
import com.cjj.volley.utils.LogUtil;

public class VolleyFrameApplication extends Application{
	private static VolleyFrameApplication mVolleyFrameApplication;

	@Override
	public void onCreate() 
	{
		super.onCreate();
		
		LogUtil.LogMsg_I("---------------------onCreate start-------------");
		
		initVolley();
		
	}

	private void initVolley() {
		CjjVolley.init(this);
	}

	public VolleyFrameApplication() 
	{
		VolleyFrameApplication.mVolleyFrameApplication = this;
		LogUtil.LogMsg_I("---------------------VolleyFrameApplication start-------------");
	}

}
