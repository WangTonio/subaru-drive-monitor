package com.sdm.manager;

import java.util.HashSet;
import java.util.Set;
import android.os.AsyncTask;
import android.util.Log;

import com.graphics.Chart;
import com.sdm.obdapi.*;

public class DataManager extends AsyncTask<String, Void, Boolean>  {
	
	public Set<Sensor> sensors = new HashSet<Sensor>();
	private OBDInterface OBD = FakeOBD.getInstance();
	private Chart chart;

	public DataManager(Chart chart) {
		this.chart = chart;
	}
	public void registerSensor(Sensor sensor){
		sensors.add(sensor);
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		for(int i = 10 ; i < 2000 ; i += 10){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        for(Sensor sensor : sensors){
	        	sensor.setValue(OBD.getData(sensor.getAdress()));
	        	
	        }
	      
	     
		}
		return true;
	}
	public void logError(){
		Log.e("thread", "log");
	}
}
