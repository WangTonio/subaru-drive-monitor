package com.graphics;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class Chart extends View {
	private int beginX = 10;
	private int beginY = 10;
	private int screenWidth = 1;
	private int screenHeight = 1;
	private int gridX = 10;
	private int gridY = 10;
	private int maxValue = 100;
	private int minValue = 0;
	
	int width = 0;
	int height = 0;
	int frame = 10;
	int grid = 50;
	public Queue<Integer> values = new LinkedList<Integer>();
    public Chart(Context context) {
        super(context);
        
        width = getWidth();
        height = getHeight();
        
    };
    
    protected void onDraw(Canvas canvas) {
    	drawOX(canvas);
    	drawGrid(canvas);
    	drawGraph(canvas);
    	try {  
            Thread.sleep(100);  
         } catch (InterruptedException e) { }
         
         invalidate();  // Force a re-draw
    }
    
    private void drawOX(Canvas canvas){
    	Paint paint = getPaint(Color.BLUE,3, 255);
    	//pozioma
    	canvas.drawLine(beginX - 5 , screenHeight - beginY, screenWidth - beginX, screenHeight - beginY, paint);
        //pionowa
    	canvas.drawLine(beginX, screenHeight - beginY + 5, beginX, beginY, paint);
    }

    public void drawGraph(Canvas canvas){
    	Paint paint = getPaint(Color.GREEN,2, 200);
        int end = frame;
        int begin = frame;
        int current = frame;
        for(Integer value : values){
        	end = value;
        	canvas.drawLine(current, begin, current + 1, end, paint);
        	current += 1;
        	begin = end;
        	
        }
    }
    private void drawGrid(Canvas canvas){
    	Paint paint = getPaint(Color.RED,(float) 0.3, 200);
    	//pozioma
    	for(int i = beginY + gridY; i < screenHeight; i += gridY){
    		canvas.drawLine(beginX, screenHeight - i, screenWidth, screenHeight - i, paint);
    	}
    	//pionowa
    	for(int i = beginX + gridX; i < screenWidth;i +=gridX){
    		canvas.drawLine(i, screenHeight - beginY, i, beginY, paint);
    	}
    }
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
    	screenHeight =h;
    	screenWidth = w;
    	 width = w;
         height = h;
    }
    
    private Paint getPaint(int Color, float width, int alpha){
    	Paint paint = new Paint();
    	paint.setColor(Color);
        paint.setStrokeWidth(width);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAlpha(alpha);
        return paint;
    }

	public void setValues(int i) {
		values.add(i);
		Log.i("value",Integer.toString(i));// TODO Auto-generated method stub
		
	}
}