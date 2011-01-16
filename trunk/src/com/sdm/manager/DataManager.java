package com.sdm.manager;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import com.sdm.SuabruDriveMonitor;
import com.sdm.obdapi.*;

public class DataManager extends AsyncTask<String, Void, Boolean>  {
	public static final String BROADCAST_ACTION="com.sdm.SuabruDriveMonitor";
	
	
	private ArrayList<Integer> addressList = new ArrayList<Integer>();
	private HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
	private OBDInterface OBD = FakeOBD.getInstance();
	
	private Activity activity;
	public DataManager(Activity activity){
		this.activity = activity;
		addressList.add(1500);
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		while(true){
	        for(Integer address : addressList){
	        	data.put(address, OBD.getData(address));
	        	 ((SuabruDriveMonitor) activity).message(data);
	        }
		}
	}
}
