/*
 * Created by Storm Zhang, Feb 11, 2014.
 */

package com.cjj.volley.activity;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cjj.volley.R;
import com.cjj.volley.model.ClassModels;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView mListView;
	private List<ClassModels> mData = Arrays.asList(new ClassModels("StringRequest的用法", StringRequestActivity.class)
													,new ClassModels("JsonRequest的用法", JsonRequestActivity.class)
													,new ClassModels("imageRequest的用法", ImageRequestActivity.class)
													,new ClassModels("ImagLoaderRequest的用法", ImageLoaderActivity.class)
													,new ClassModels("NetworkImageViewRequest的用法", NetworkImageViewActivity.class)
													,new ClassModels("XMLRequest的用法", XmlRequestActivity.class)
													,new ClassModels("GsonRequest的用法", GsonRequestActivity.class));
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) this.findViewById(R.id.listView);
		mListView.setOnItemClickListener(this);
		mListView.setAdapter(new ArrayAdapter<ClassModels>(this, android.R.layout.simple_list_item_1, mData));
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this, mData.get(position).name);
		startActivity(intent);
	}


}
