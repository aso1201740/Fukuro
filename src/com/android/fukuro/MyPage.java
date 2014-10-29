package com.android.fukuro;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

	public class MyPage extends Activity {
		
		private static final String TAG = "myTag";
		private int selectedIndex = 0;
		private Timer timer;
		private MyHandler handler;
		private DBHelper dbHelper = new DBHelper(this);
		public static SQLiteDatabase db;
	    private ArrayList<String> coordename = new ArrayList<String>();
	    /** Called when the activity is first created. */
	    private boolean firstflg = false;
	    
	    @Override
	    protected void onStop(){
	    	super.onStop();
	    	try{
	    		timer.cancel();
	    		TimeUnit.SECONDS.sleep(10);
	    		Log.d(TAG,"停止しました。");
	    	}catch(InterruptedException e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mypage);
	        
	        db = dbHelper.getReadableDatabase();
	        
	        String sql01 = "select item from Item where item_id = (select MAX(item_id) from Item)";
	        Cursor c1 = db.rawQuery(sql01, null);
	       // Log.d("test","sql=" + sql); //検査用
	       // Log.d("test","cursor" + c.getCount()); //検査用
	        c1.moveToFirst();
	       // Log.d("test","cursor" + c.getCount()); //検査用

	        for (int i = 0; i < c1.getCount() ; i++) {
                coordename.add(c1.getString(0));
                Log.d("test","name=" + coordename);
                c1.moveToNext();
	        }
	        
	        String sql02 = "select thambnail from Mylist order by maked DESC";
	        Cursor c2 = db.rawQuery(sql02, null);
	       // Log.d("test","sql=" + sql); //検査用
	       // Log.d("test","cursor" + c.getCount()); //検査用
	        c2.moveToFirst();
	       // Log.d("test","cursor" + c.getCount()); //検査用

	        for (int i = 0; i < c2.getCount() ; i++) {
                coordename.add(c2.getString(0));
                Log.d("test","name=" + coordename);
                c2.moveToNext();
            }
	        
	        handler = new MyHandler();
	        
	        TimerTask task = new TimerTask(){
	        	@Override
	        	public void run(){
	        		Log.d(TAG,"Timer task");
	        		if(firstflg){
	        		selectedIndex = selectedIndex + 3;
	        		}else{
	        		firstflg = true;
	        		}
	        		selectedIndex = selectedIndex % (coordename.size() - 1) + 1;
	        		Log.d(TAG, "selectedIndex = " + selectedIndex);
	        		Message msg1 = new Message();
	        		msg1.what = selectedIndex;
	        		handler.sendMessage(msg1);
	        		}
	    	};
	        timer = new Timer();
	        timer.schedule(task, 5000,10000);
	    }
	    
	        class MyHandler extends Handler{
	        	@Override
	        	public void handleMessage(Message msg1){
	        		Log.d(TAG, "handleMessage:" + msg1.what);
	        		
	        		File dir = new File("/data/data/com.android.fukuro/Item");
	    	        if(dir.exists()){
	    	        	//Log.d(TAG,"arraylist参照" + coordename);
	    	            File file = new File(dir.getAbsolutePath()+ "/" + coordename.get(0));
	    	           // Log.d(TAG,"1banImage");
	    	            if (file.exists()) {
	    	            	//Log.d(TAG,"arraylist" + file);
	    	                    Bitmap _bm1 = BitmapFactory.decodeFile(file.getPath());
	    	                    _bm1 = Bitmap.createScaledBitmap(_bm1, 500, 700, false);
	    	                    ((ImageView)findViewById(R.id.imageView1)).setImageBitmap(_bm1); 
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }  
	    	        int index1 = msg1.what;
	        		index1 = index1 % (coordename.size()-1)+1;
	        		Log.d(TAG, "number=" + index1);
	    	        if(dir.exists()){
	    	            File file = new File(dir.getAbsolutePath()+ "/" + coordename.get(index1));
	    	            if (file.exists()) {
	    	                    Bitmap _bm2 = BitmapFactory.decodeFile(file.getPath());
	    	                    _bm2 = Bitmap.createScaledBitmap(_bm2, 230, 250, false);
	    	                    ((ImageView)findViewById(R.id.imageView3)).setImageBitmap(_bm2); 
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }  
	    	        int index2 = msg1.what;
	    	        index2 = index2 + 1;
	        		index2 = index2 % (coordename.size()-1)+2;
	    	        if(dir.exists()){
	    	            File file = new File(dir.getAbsolutePath()+ "/" + coordename.get(index2));
	    	            if (file.exists()) {
	    	                    Bitmap _bm3 = BitmapFactory.decodeFile(file.getPath());
	    	                    _bm3 = Bitmap.createScaledBitmap(_bm3, 230, 250, false);
	    	                    ((ImageView)findViewById(R.id.imageView4)).setImageBitmap(_bm3); 
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }  
	    	        int index3 = msg1.what;
	        		index3 = index3 + 2;
	        		index3 = index3 % (coordename.size()-1)+3;
	    	        if(dir.exists()){
	    	        	File file = new File(dir.getAbsolutePath()+ "/" + coordename.get(index3));
	    	            if (file.exists()) {
	    	                    Bitmap _bm4 = BitmapFactory.decodeFile(file.getPath());
	    	                    _bm4 = Bitmap.createScaledBitmap(_bm4, 230, 250, false);
	    	                    ((ImageView)findViewById(R.id.imageView2)).setImageBitmap(_bm4); 
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }
	        	}
	        }
	    
		    
}

