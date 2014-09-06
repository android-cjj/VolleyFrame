package com.cjj.volley.activity;

import com.cjj.volley.R;
import com.cjj.volley.VolleyError;
import com.cjj.volley.callback.CallBackDataListener;
import com.cjj.volley.constants.Constants;
import com.cjj.volley.dao.UserDao;
import com.cjj.volley.me.CjjVolley;
import com.cjj.volley.model.Weather;
import com.cjj.volley.model.WeatherInfo;
import com.cjj.volley.utils.VolleyErrorHelper;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GsonRequestActivity extends ActionBarActivity implements OnClickListener{
	private Button btn_gson_request;
	private ActionBar actionBar;
	private TextView tv_result ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.GsonRequest));
		setContentView(R.layout.activity_xml_request);
		findView();
	}

	private void findView() 
	{
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		btn_gson_request = (Button) this.findViewById(R.id.btn_xml_request);
		btn_gson_request.setText(getString(R.string.GsonRequest));
		btn_gson_request.setOnClickListener(this);
		tv_result = (TextView) this.findViewById(R.id.tv_XmlRequest); 
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
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.btn_xml_request:
			solveGsonResult();
			break;
		}
	}

	private void solveGsonResult() {
		UserDao.getInstance().getGsonDataFromNet(new CallBackDataListener() {
			
			@Override
			public void error(VolleyError error) {
				tv_result.setText(VolleyErrorHelper.getMessage(error, GsonRequestActivity.this));
			}
			
			@Override
			public void callBack(Object data) {
				if(data instanceof Weather)
				{
					Weather weather = (Weather) data;
					WeatherInfo weatherInfo = weather.getWeatherinfo();
					StringBuffer sb = new StringBuffer();
					sb.append(weatherInfo.getCity()+" "+weatherInfo.getTemp()+"  "+weatherInfo.getTime());
					tv_result.setText(sb.toString());
				}
			}
		});
	}
	
	@Override
	protected void onStop() {
		CjjVolley.cancelAll(Constants.GSON_TEST);
		super.onStop();
	}
}
