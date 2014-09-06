package com.cjj.volley.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cjj.volley.R;
import com.cjj.volley.Request.Method;
import com.cjj.volley.AuthFailureError;
import com.cjj.volley.Response;
import com.cjj.volley.VolleyError;
import com.cjj.volley.constants.Constants;
import com.cjj.volley.me.CjjVolley;
import com.cjj.volley.toolbox.StringRequest;
import com.cjj.volley.utils.LogUtil;
import com.cjj.volley.utils.VolleyErrorHelper;
/**
 * StringRequest的用法
 * @author cjj
 */
public class StringRequestActivity extends ActionBarActivity implements OnClickListener{
	private ActionBar actionBar;
	private Button btn_string_request_get;
	private Button btn_string_request_post;
	private TextView tv_StringRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.stringRequest));
		setContentView(R.layout.acitivity_string_request);
		findView();
	}

	private void findView() {
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		this.tv_StringRequest = (TextView) this.findViewById(R.id.tv_StringRequest);
		
		this.btn_string_request_get = (Button) this.findViewById(R.id.btn_string_request_get);
		this.btn_string_request_get.setOnClickListener(this);
		
		this.btn_string_request_post = (Button) this.findViewById(R.id.btn_string_request_post);
		this.btn_string_request_post.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.btn_string_request_get:
			solveStringRequest_get();
			break;
			
		case R.id.btn_string_request_post:
			solveStringRequest_post();
			break;
		}
	}

	private void solveStringRequest_post() {
		StringRequest stringRequest = new StringRequest(Method.POST, Constants.POST_TEST, 
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						LogUtil.LogMsg_I("post = "+response);
                    	tv_StringRequest.setText(response);
					}
				}, 
				new Response.ErrorListener() 
				{
					@Override
					public void onErrorResponse(VolleyError error) 
					{
						LogUtil.LogMsg_I("post = "+error.getMessage());  
                    	tv_StringRequest.setText(error.getMessage());
					}
				})
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> param = new HashMap<String, String>();
				param.put("param1", "12");
				param.put("param2", "22");
				return param;
			}
		};
		
		CjjVolley.addRequest(stringRequest, Constants.STRING_REQUEST_POST);
		
	}

	private void solveStringRequest_get()
	{
		StringRequest stringRequest = new StringRequest(Method.GET,Constants.BAIDU,  
                new Response.Listener<String>() {  
                    @Override  
                    public void onResponse(String response) {  
                    	LogUtil.LogMsg_I("BAIDU = "+response);
                    	tv_StringRequest.setText(response);
                    }  
                }, new Response.ErrorListener() {  
                    @Override  
                    public void onErrorResponse(VolleyError error) {  
                    	LogUtil.LogMsg_I("BAIDU = "+VolleyErrorHelper.getMessage(error, StringRequestActivity.this));  
                    	tv_StringRequest.setText(VolleyErrorHelper.getMessage(error, StringRequestActivity.this));
                    }  
                });  
		
		CjjVolley.addRequest(stringRequest, Constants.STRING_REQUEST_GET);
	}
	
	
	@Override
	protected void onStop() {
		CjjVolley.cancelAll(Constants.STRING_REQUEST_GET);
		CjjVolley.cancelAll(Constants.STRING_REQUEST_POST);
		super.onStop();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case android.R.id.home:
			this.finish();
			break;
		}
		return true;
	}
	
	
}
