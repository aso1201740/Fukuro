package com.android.fukuro;

import java.io.File;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	        
	        setContentView(R.layout.mypage);
	        
	       // ImageView iv = new ImageView(this);
	        //iv.setImageResource(R.drawable.coordinate);
	        //setContentView(iv);
	        
	        File dir = new File("/data/data/com.android.fukuro/Item");
	        if(dir.exists()){
	            File file = new File(dir.getAbsolutePath()+"/coordinate.jpg");
	            if (file.exists()) {
	                    Bitmap _bm = BitmapFactory.decodeFile(file.getPath());
	                    ((ImageView)findViewById(R.id.imageView1)).setImageBitmap(_bm); 
	            }else{
	                //存在しない
	            }
	        }      
	    }    
	}

