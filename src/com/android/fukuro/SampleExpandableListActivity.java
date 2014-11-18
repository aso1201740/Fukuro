package com.android.fukuro;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
 
public class SampleExpandableListActivity extends Activity {
	
    /** Called when the activity is first created. */
	private DBHelper dbHelper = new DBHelper(this);
	public static SQLiteDatabase db;
	
    private ArrayList<String> itemname = new ArrayList<String>();
    private ArrayList<String> itemname2 = new ArrayList<String>();
    private ArrayList<String> itemname3 = new ArrayList<String>();
    private ArrayList<String> itemname4 = new ArrayList<String>();
    private ArrayList<String> itemname5 = new ArrayList<String>();
    private ArrayList<String> itemname6 = new ArrayList<String>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
         
        db = dbHelper.getReadableDatabase();
        
        ExpandableListView listView = (ExpandableListView)findViewById(R.id.sample_list);
        int[] rowId = {0,1,2,3,4,5};
        listView.setAdapter(new SampleExpandableListAdapter(this, rowId, createGroupItemList(), createChildrenItemList()));
    }
    
    public class ItemDto {
        private String name = "";                           // 名前
        private String resourceId = null;   // アイコンのResource ID. DefaultはLauncherアイコン
         
        public ItemDto(String name, String string) {
            this.name = name;
            this.resourceId = string;
        }
         
        public ItemDto(String name) {
            this.name = name;
        }
         
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getResourceId() {
            return resourceId;
        }
        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }
    }
     
    /**
     * 
     * @return
     */
    private List<List<ItemDto>> createChildrenItemList() {
    	
        List<ItemDto> child = new ArrayList<ItemDto>();
        List<ItemDto> child2 = new ArrayList<ItemDto>();
        List<ItemDto> child3 = new ArrayList<ItemDto>();
        List<ItemDto> child4 = new ArrayList<ItemDto>();
        List<ItemDto> child5 = new ArrayList<ItemDto>();
        List<ItemDto> child6 = new ArrayList<ItemDto>();
        
        List<List<ItemDto>> result = new ArrayList<List<ItemDto>>();
        
        File dir = new File("/data/data/com.android.fukuro/Item");
        
        for(int j = 1; j < 7; j++){
        	
        	if(j == 1){
        		String sql01 = "select item from Item where category_id = \"1\"";
    	        Cursor c1 = db.rawQuery(sql01, null);
    	        c1.moveToFirst();
    	        for (int i = 0; i < c1.getCount() ; i++) {
                    itemname.add(c1.getString(0));
                    Log.d("test","name=" + itemname);
                    c1.moveToNext();
    	        }
	    	        if(dir.exists()){
	    	            File file = new File(dir.getAbsolutePath()+ "/" + itemname.get(0));
	    	            if (file.exists()) {
	    	            	for(int k = 0;k<itemname.size();k++)
	    	            	{
	    	            		child.add(new ItemDto("カテゴリー１", dir.getAbsolutePath()+ "/" + itemname.get(k)));
	    	            	}
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }  
    	        
        	}else if(j == 2){
        		String sql02 = "select item from Item where category_id = \"2\"";
    	        Cursor c2 = db.rawQuery(sql02, null);
    	        c2.moveToFirst();
    	        for (int i = 0; i < c2.getCount() ; i++) {
                    itemname2.add(c2.getString(0));
                    Log.d("test","name=" + itemname2);
                    c2.moveToNext();
    	        }
	    	        if(dir.exists()){
	    	            File file = new File(dir.getAbsolutePath()+ "/" + itemname2.get(0));
	    	            if (file.exists()) {
	    	            	for(int k = 0;k<itemname2.size();k++)
	    	            	{
	    	            		child2.add(new ItemDto("カテゴリー2", dir.getAbsolutePath()+ "/" + itemname2.get(k)));
	    	            	}
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }
        	}else if(j == 3){
        		String sql03 = "select item from Item where category_id = \"3\"";
    	        Cursor c3 = db.rawQuery(sql03, null);
    	        c3.moveToFirst();
    	        for (int i = 0; i < c3.getCount() ; i++) {
                    itemname3.add(c3.getString(0));
                    Log.d("test","name=" + itemname3);
                    c3.moveToNext();
    	        }
	    	        if(dir.exists()){
	    	            File file = new File(dir.getAbsolutePath()+ "/" + itemname3.get(0));
	    	            if (file.exists()) {
	    	            	for(int k = 0;k<itemname3.size();k++)
	    	            	{
	    	            		child3.add(new ItemDto("カテゴリー3", dir.getAbsolutePath()+ "/" + itemname3.get(k)));
	    	            	}
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }
        	}else if(j == 4){
        		String sql04 = "select item from Item where category_id = \"4\"";
    	        Cursor c4 = db.rawQuery(sql04, null);
    	        c4.moveToFirst();
    	        for (int i = 0; i < c4.getCount() ; i++) {
                    itemname4.add(c4.getString(0));
                    Log.d("test","name=" + itemname4);
                    c4.moveToNext();
    	        }
	    	        if(dir.exists()){
	    	            File file = new File(dir.getAbsolutePath()+ "/" + itemname4.get(0));
	    	            if (file.exists()) {
	    	            	for(int k = 0;k<itemname4.size();k++)
	    	            	{
	    	            		child4.add(new ItemDto("カテゴリー4", dir.getAbsolutePath()+ "/" + itemname4.get(k)));
	    	            	}
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }
        	}else if(j == 5){
        		String sql05 = "select item from Item where category_id = \"5\"";
    	        Cursor c5 = db.rawQuery(sql05, null);
    	        c5.moveToFirst();
    	        for (int i = 0; i < c5.getCount() ; i++) {
                    itemname5.add(c5.getString(0));
                    Log.d("test","name=" + itemname5);
                    c5.moveToNext();
    	        }
	    	        if(dir.exists()){
	    	            File file = new File(dir.getAbsolutePath()+ "/" + itemname5.get(0));
	    	            if (file.exists()) {
	    	            	for(int k = 0;k<itemname5.size();k++)
	    	            	{
	    	            		child5.add(new ItemDto("カテゴリー5", dir.getAbsolutePath()+ "/" + itemname5.get(k)));
	    	            	}
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }
        	}else if(j == 6){
        		String sql06 = "select item from Item where category_id = \"6\"";
    	        Cursor c6 = db.rawQuery(sql06, null);
    	        c6.moveToFirst();
    	        for (int i = 0; i < c6.getCount() ; i++) {
                    itemname6.add(c6.getString(0));
                    Log.d("test","name=" + itemname6);
                    c6.moveToNext();
    	        }
	    	        if(dir.exists()){
	    	            File file = new File(dir.getAbsolutePath()+ "/" + itemname6.get(0));
	    	            if (file.exists()) {
	    	            	for(int k = 0;k<itemname6.size();k++)
	    	            	{
	    	            		child6.add(new ItemDto("カテゴリー6", dir.getAbsolutePath()+ "/" + itemname6.get(k)));
	    	            	}
	    	            }else{
	    	                //存在しない
	    	            }
	    	        }
        	  }
        }
        	
        result.add(child);
        result.add(child2);
        result.add(child3);
        result.add(child4);
        result.add(child5);
        result.add(child6);
        return result;
    }
     
    /**
     * 
     * @return
     */
    private List<String> createGroupItemList() {
        List<String> groups = new ArrayList<String>();
        groups.add("T-シャツ");
        groups.add("シャツ");
        groups.add("ニット・カーディガン");
        groups.add("ジャケット・コート");
        groups.add("パンツ");
        groups.add("ショートパンツ");
        return groups;
    }
}
