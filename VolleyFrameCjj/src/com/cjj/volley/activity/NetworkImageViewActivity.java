package com.cjj.volley.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cjj.volley.R;
import com.cjj.volley.constants.Constants;
import com.cjj.volley.me.CjjVolley;
import com.cjj.volley.toolbox.NetworkImageView;
/**
 * NetworkImageView的使用
 * @author cjj
 *
 */
public class NetworkImageViewActivity extends ActionBarActivity implements OnClickListener{
	private ActionBar actionBar;
	private Button btn_network_image_request;
	private NetworkImageView niv_image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.netImageLoader));
		setContentView(R.layout.activity_network_image_view);
		findView();
	}

	private void findView() 
	{
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		this.btn_network_image_request = (Button) this.findViewById(R.id.btn_network_image_request);
		this.btn_network_image_request.setOnClickListener(this);
		this.niv_image = (NetworkImageView) this.findViewById(R.id.niv);
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
		case R.id.btn_network_image_request:
			solveNetImageViewRequest();
			break;
		}
	}

	private void solveNetImageViewRequest() 
	{
		niv_image.setDefaultImageResId(R.drawable.ic_launcher);  
		niv_image.setErrorImageResId(R.drawable.ic_launcher);  
		niv_image.setImageUrl(Constants.PIC_PATH2,  
		                CjjVolley.getImageLoader());  
	}
	
	
}
