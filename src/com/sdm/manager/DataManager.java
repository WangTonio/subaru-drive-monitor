package com.sdm.manager;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.sdm.obdapi.*;

public class DataManager extends Service {
	public static final String BROADCAST_ACTION="com.sdm.SuabruDriveMonitor";
	
	private final Binder binder=new LocalBinder();
	private Intent broadcast=new Intent(BROADCAST_ACTION);
	
	private ArrayList<Integer> addressList = new ArrayList<Integer>();
	private HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
	private OBDInterface OBD = FakeOBD.getInstance();
	
	public void onCreate() {
		super.onCreate();
		this.requestData();
	}
	public Integer getData(Integer address){
		return data.get(address);
	}
	private void requestData() {
		while(true){
	        for(Integer address : addressList){
	        	data.put(address, OBD.getData(address));
	        	sendBroadcast(broadcast);
	        }
		}
    }
	@Override
	public IBinder onBind(Intent intent) {
		addressList.add(intent.getIntExtra("address", 5));
		return(binder);
	}
	
	public class LocalBinder extends Binder {
		public DataManager getService() {
			return(DataManager.this);
		}
	}
}
