package com.sdm;

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
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TempText = new TextView(this);
        setContentView(TempText);
        Intent cycki = new Intent(this, DataManager.class);
        cycki.putExtra("address", TempAddress);
        bindService(cycki,onService, BIND_AUTO_CREATE);

    
    
    }

    @Override
    public void onResume() {
    	super.onResume();
    	registerReceiver(receiver,  new IntentFilter(DataManager.BROADCAST_ACTION));
    }

    @Override
    public void onPause() {
    	super.onPause();
    	unregisterReceiver(receiver);
    }

    @Override
    public void onDestroy() {
    	super.onDestroy();
    	unbindService(onService);
    }

    
    private void updateContent() {
    	TempText.setText(Integer.toString(appService.getData(TempAddress)));
    }

 

    private BroadcastReceiver receiver=new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	     updateContent();
	    }
    };

    private ServiceConnection onService=new ServiceConnection() {
	    public void onServiceConnected(ComponentName className, IBinder rawBinder) {
	    	appService=((DataManager.LocalBinder)rawBinder).getService();
	    }
	
	    public void onServiceDisconnected(ComponentName className) {
	    	appService=null;
	    }
    };
}