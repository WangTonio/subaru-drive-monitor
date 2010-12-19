package com.sdm.obdapi;

import java.util.Random;

public class FakeOBD implements OBDInterface{

	private static final FakeOBD INSTANCE = new FakeOBD();
	private int[] data = new int[256];
	private boolean[] tred = new boolean[256];
	
    private FakeOBD() {
    }
 
    public static FakeOBD getInstance() {
        return INSTANCE;
    }

	public int getData(int address) {
		Random rn = new Random();
		if(rn.nextInt(100) > 90){
			tred[address] = !tred[address];
		}
		if(data[address] == 0){
			tred[address] = true;
		} 
		if(tred[address] == false){
			data[address]--;
		} else {
			data[address]++;
		}
		return data[address];
	}

}
