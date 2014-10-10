package com.android.fukuro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
	
	private DBHelper dbHelper = new DBHelper(this);
	
	public static SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//読み書き可能なデータベースをオープン
		// 読み取り専用の場合はgetReadableDatabase()を用いる
		db = dbHelper.getWritableDatabase();
		
        Button btnjump=(Button)findViewById(R.id.btnjump);
        btnjump.setOnClickListener(this);
        
        FileOutputStream fo;

        File newfile = new File("/data/data/com.android.fukuro/Item");
        File newfile2 = new File("/data/data/com.android.fukuro/Thambnail");

           if (newfile.mkdir()){
             //System.out.println("ディレクトリの作成に成功しました");
             Log.d("ファイル作成","ディレクトリの作成に成功しました");

           }else{
             //System.out.println("ディレクトリの作成に失敗しました");
             Log.d("ファイル作成","ディレクトリの作成に失敗しました");
           }

           if (newfile2.mkdir()){
             //System.out.println("ディレクトリの作成に成功しました");
             Log.d("ファイル作成","ディレクトリの作成に成功しました");

           }else{
             //System.out.println("ディレクトリの作成に失敗しました");
             Log.d("ファイル作成","ディレクトリの作成に失敗しました");
           }

           try{
           File f = new File("/data/data/com.android.fukuro/maketest/test2.txt");
               File parent = f.getParentFile();
               if (parent != null && parent.canWrite()) { parent.mkdirs(); }
               fo = new FileOutputStream(f);

           } catch (IOException e) {
               Log.e("text作成","text作成に失敗しました");
           }
	}
	
	@Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, MyPage.class);
        startActivity(intent);
   }
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		dbHelper.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
