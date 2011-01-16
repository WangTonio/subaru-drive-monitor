package com.sdm;

import java.util.HashMap;

import com.sdm.manager.DataManager;
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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SuabruDriveMonitor extends Activity {
	
	private DataManager appService=null;
	private TextView TempText =  null;
	private Integer TempAddress = new Integer(5);
	private String cycki = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TempText = new TextView(this);
        
        setContentView(TempText);
        DataManager dataManager = new DataManager(this);
        dataManager.execute();    
        while(true){
        	TempText.setText(cycki);
        }
    }
	public void message(HashMap<Integer, Integer> data) {
		cycki = Integer.toString(data.get(1500));
        
//		TempText.setText(Integer.toString(data.get(1500)));
	}
}