package com.cjj.volley.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cjj.volley.R;
import com.cjj.volley.VolleyError;
import com.cjj.volley.callback.CallBackDataListener;
import com.cjj.volley.constants.Constants;
import com.cjj.volley.dao.UserDao;
import com.cjj.volley.me.CjjVolley;
import com.cjj.volley.utils.VolleyErrorHelper;
/**
 * 自定义XmlRequest的使用
 * @author cjj
 *
 */
public class XmlRequestActivity extends ActionBarActivity implements OnClickListener{
	private Button btn_xml_request;
	private TextView tv_xml_result ;
	private ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.xmlRequest));
		setContentView(R.layout.activity_xml_request);
		findView();
	}

	private void findView() 
	{
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		this.btn_xml_request = (Button) this.findViewById(R.id.btn_xml_request);
		this.btn_xml_request.setOnClickListener(this);
		this.tv_xml_result = (TextView) this.findViewById(R.id.tv_XmlRequest);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.btn_xml_request:
			solveXmlRequest();
			break;
		}
	}

	private void solveXmlRequest() 
	{
		UserDao.getInstance().getXmlResultFormNet(new CallBackDataListener() 
		{

			@Override
			public void callBack(Object data) {
				if(data instanceof String)
				{
					String result = (String) data;
					tv_xml_result.setText(result);
				}
			}

			@Override
			public void error(VolleyError error) {
				tv_xml_result.setText(VolleyErrorHelper.getMessage(error, XmlRequestActivity.this));
			}
			

			
		});
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
	protected void onStop() {
		CjjVolley.cancelAll(Constants.TAG_REQUEST_XML);
		super.onStop();
	}
}
