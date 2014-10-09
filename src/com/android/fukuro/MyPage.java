package com.android.fukuro;

	import android.content.Context;
	import android.content.res.Resources;
	import android.graphics.Bitmap;
	import android.graphics.BitmapFactory;
	import android.graphics.Canvas;
	import android.graphics.Paint;
	import android.view.View;

	public class MyPage extends View {
	    Paint paint = new Paint();
	    
	    //�摜�ǂݍ���
	    Resources res = this.getContext().getResources();
	    Bitmap grass = BitmapFactory.decodeResource(res, R.drawable.back);
	    Bitmap goburin = BitmapFactory.decodeResource(res, R.drawable.coordinate);
	    
	    public MyPage(Context context) {
	        super(context);
	    }
	    
	    @Override
	    public void onDraw(Canvas c) {
	        //�`�揈��
	        c.drawBitmap(grass, 0, 0, paint);
	        c.drawBitmap(goburin, 250, 150, paint);
	    }
	}

