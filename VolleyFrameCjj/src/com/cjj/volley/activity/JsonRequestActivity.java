package com.cjj.volley.activity;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cjj.volley.R;
import com.cjj.volley.Response;
import com.cjj.volley.VolleyError;
import com.cjj.volley.constants.Constants;
import com.cjj.volley.me.CjjVolley;
import com.cjj.volley.toolbox.JsonObjectRequest;
import com.cjj.volley.utils.LogUtil;
import com.cjj.volley.utils.VolleyErrorHelper;
/**
 * jsonRequest的使用
 * @author cjj
 *
 */
public class JsonRequestActivity extends ActionBarActivity implements OnClickListener{
	private ActionBar actionBar;
	private TextView tv_jsonRequest;
	private Button btn_jsonRequest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.JsonRequest));
		setContentView(R.layout.activity_json_request);
		findView();
	}

	private void findView() 
	{
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		this.tv_jsonRequest = (TextView) this.findViewById(R.id.tv_JsonRequest);
		this.btn_jsonRequest = (Button) this.findViewById(R.id.btn_json_request);
		this.btn_jsonRequest.setOnClickListener(this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case android.R.id.home:
			this.finish();
			break;
		}
		return true;
	}

	@Override
	public void onClick(View arg0)
	{
		switch(arg0.getId())
		{
		case R.id.btn_json_request:
			solveJsonRequest();
			break;
		}
	}

	private void solveJsonRequest() 
	{
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constants.JSON_TEST, null,  
		        new Response.Listener<JSONObject>() 
		        {  
		            @Override  
		            public void onResponse(JSONObject response) {  
		            	LogUtil.LogMsg_I(response.toString());
		            	tv_jsonRequest.setText(response.toString());
		            }  
		        }, new Response.ErrorListener() {  
		            @Override  
		            public void onErrorResponse(VolleyError error) {  
		            	tv_jsonRequest.setText(VolleyErrorHelper.getMessage(error, JsonRequestActivity.this));
		            }  
		        }); 
		
		CjjVolley.addRequest(jsonObjectRequest, Constants.TAG_REQUEST_JSON);
	}
	
	@Override
	protected void onStop() {
		CjjVolley.cancelAll( Constants.TAG_REQUEST_JSON);
		super.onStop();
	}
}
