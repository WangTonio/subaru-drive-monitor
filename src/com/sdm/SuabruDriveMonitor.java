package com.sdm;

import com.sdm.obdapi.FakeOBD;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SuabruDriveMonitor extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        FakeOBD fODB = FakeOBD.getInstance();
        for(int i=1;i<100;i++){
        	tv.setText(Integer.toString(fODB.getData(0)));
        	setContentView(tv);
        }

    }
}