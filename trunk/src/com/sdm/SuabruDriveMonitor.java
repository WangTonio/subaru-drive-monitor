package com.sdm;

import java.util.HashMap;

import com.graphics.Chart;
import com.sdm.manager.DataManager;
import com.sdm.manager.Sensor;
import com.sdm.obdapi.FakeOBD;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

public class SuabruDriveMonitor extends Activity {
	
	public DataManager appService=null;
	private Chart TempText =  null;
	private Integer TempAddress = new Integer(5);
	private String cycki = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TempText = new Chart(this);
        Log.i("cycki","dupa");
        setContentView(TempText);
        DataManager dataManager = new DataManager(TempText);
        final Sensor sensor = new Sensor(1500);
        dataManager.registerSensor(sensor);
        
        dataManager.execute();
        new Thread(new Runnable() {
            public void run() {
            	for (int i = 10; i < 500; i++) {
					
				
            	try {
    				Thread.sleep(10);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    		        	
    				TempText.setValues(sensor.getValue());
    				
            }
            }
          }).start();
    
//        dataManager.logError();
      //n  this.finish();
//        while(true){
//        	TempText.setText(cycki);
//        }
    }
	public void message(HashMap<Integer, Integer> data) {
		//cycki = Integer.toString(data.get(1500));
        //Log.i("cycyki",Integer.toString(data.get(1500)));
//		TempText.setText(Integer.toString(data.get(1500)));
	}
}