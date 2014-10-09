package com.android.fukuro;

	import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.widget.ImageView;

	public class MyPage extends Activity {
	     
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        TextView tv = new TextView(this);
	        tv.setText("This is SubActivity!");
	        setContentView(tv);
	        ImageView iv = new ImageView(this);
	        iv.setImageResource(R.drawable.coordinate);
	        setContentView(iv);
	    }    
	}

