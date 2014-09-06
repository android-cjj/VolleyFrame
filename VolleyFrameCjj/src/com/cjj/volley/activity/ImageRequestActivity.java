package com.cjj.volley.activity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.cjj.volley.R;
import com.cjj.volley.Response;
import com.cjj.volley.VolleyError;
import com.cjj.volley.constants.Constants;
import com.cjj.volley.me.CjjVolley;
import com.cjj.volley.toolbox.ImageRequest;
/**
 * ImageRequest的使用
 * @author cjj
 *
 */
public class ImageRequestActivity extends ActionBarActivity implements OnClickListener{
	private ActionBar actionBar;
	private Button btn_imageRequest;
	private ImageView iv_iamge;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.ImageRequest));
		setContentView(R.layout.activity_image_request);
		findView();
	}

	private void findView() 
	{
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		this.btn_imageRequest = (Button) this.findViewById(R.id.btn_image_request);
		this.btn_imageRequest.setOnClickListener(this);
		this.iv_iamge = (ImageView) this.findViewById(R.id.iv_iamge);
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
		case R.id.btn_image_request:
			solveImageRequest();
			break;
		}
	}

	private void solveImageRequest() 
	{
		ImageRequest imageRequest = new ImageRequest(  
		        Constants.PIC_PATH,
		        new Response.Listener<Bitmap>() {  
		            @Override  
		            public void onResponse(Bitmap response) {  
		            	iv_iamge.setImageBitmap(response);  
		            }  
		        }, 0, 0, Config.RGB_565, new Response.ErrorListener() {  
		            @Override  
		            public void onErrorResponse(VolleyError error) {  
		            	iv_iamge.setImageResource(R.drawable.ic_launcher);  
		            }  
		        });  
		
		CjjVolley.addRequest(imageRequest, null);
	}
	
	
}
