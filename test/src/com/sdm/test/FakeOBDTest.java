package com.sdm.test;

import com.sdm.obdapi.FakeOBD;


import android.test.AndroidTestCase;

public class FakeOBDTest extends AndroidTestCase{
	
	private FakeOBD fODB;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		fODB = FakeOBD.getInstance();
	}
	public void testGetData(){
        for(int i=1;i<100;i++){
        	int data = 0;
        	for(int j=1;j<100;j++){
        		int data2 = fODB.getData(i);
        		assertTrue(data2 == data + 1 || data2 == data - 1);
        		assertTrue(data2 >= 0);
        		data = data2;
        	}
        }
	}
}
