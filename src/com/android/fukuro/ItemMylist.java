package com.android.fukuro;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ItemMylist extends Activity implements OnItemClickListener {
	// 要素をArrayListで設定
	private List<String> imgList = new ArrayList<String>();
	private List<Integer> itemid = new ArrayList<Integer>();
	private DBHelper dbHelper = new DBHelper(this);
	public static SQLiteDatabase db;

	GridView gridview;
	GridAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemlist);

		db = dbHelper.getReadableDatabase();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		imgList = new ArrayList<String>();
		itemid = new ArrayList<Integer>();
		gridview = (GridView) findViewById(R.id.gridview);
		// BaseAdapter を継承したGridAdapterのインスタンスを生成
		// 子要素のレイアウトファイル grid_items.xml を main.xml に inflate するためにGridAdapterに引数として渡す
		adapter = new GridAdapter(this.getApplicationContext(), R.layout.grid_items, imgList);
		// gridViewにadapterをセット
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(this);
		// それのパスを取り出す method
		getImagePath();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ
		Intent vIntent = null;
		Intent iIntent = null;
		//startActivity(vIntent);
		//startActivity(iIntent);

		vIntent = new Intent(this, itemDetails.class);
		vIntent.putExtra("ID", itemid.get(position));
		iIntent = new Intent(this, itemDetails.class);
		iIntent.putExtra("itemname", imgList.get(position));
		startActivity(vIntent);
		startActivity(iIntent);
	}

	private void getImagePath() {  //プラス画像とDBに格納してある画像のパスをimgListに挿入
		String destPath = null;

		Cursor cr = db.rawQuery("SELECT item, item_id FROM Item WHERE NOT category_id = \"7\" ORDER BY category_id DESC", null);
		cr.moveToFirst();
		
		for(int cnt = 0; cnt < cr.getCount(); cnt++){
			destPath = "/data/data/"+this.getPackageName()+"/Item/" + cr.getString(0);
			Log.d("TAG","id" + cr.getString(0));
			System.out.println(cr.getString(0));

			// List<String> imgList にはファイルのパスを入れる
			imgList.add(destPath);
			itemid.add(cnt,cr.getInt(0));
			cr.moveToNext();
		}

	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		dbHelper.close();
	}

	class ViewHolder {
		ImageView imageView;
	}

	class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private int layoutId;

		public GridAdapter(Context context,  int layoutId, List<String> imgList) {
			super();
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.layoutId = layoutId;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String mFilepath = imgList.get(position);

			ViewHolder holder;
			if (convertView == null) {
				// main.xml の <GridView .../> に grid_items.xml を inflate して convertView とする
				convertView = inflater.inflate(layoutId, parent, false);
				// ViewHolder を生成
				holder = new ViewHolder();
				holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder) convertView.getTag();
			}


			//if(position != 0){  //プラスボタン以外の画像読み出し
				Bitmap bmp = BitmapFactory.decodeFile(mFilepath);
				bmp = Bitmap.createScaledBitmap(bmp, 120, 160, true);
				holder.imageView.setImageBitmap(bmp);
			//}
			return convertView;
		}

		@Override
		public int getCount() {
			// List<String> imgList の全要素数を返す
			return imgList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	}
}