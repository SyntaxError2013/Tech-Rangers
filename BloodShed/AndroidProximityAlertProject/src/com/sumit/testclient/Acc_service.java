package com.sumit.testclient;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.hardware.*;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Acc_service extends Service implements SensorEventListener {
	
	private SensorManager mSensorManager;
	private Sensor Acc,gravity;
	 Toast toast;	
	private float acc_x,acc_y,acc_z;
	private String Acc_x,Acc_y,Acc_z;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        
        //setting basic initialization of sensors
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
			  // Success! There's a accelerometerr.
          Acc=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
          mSensorManager.registerListener(this, Acc, SensorManager.SENSOR_DELAY_UI);
			  }
			else {
			  // Failure! No accelerometer
				Toast msg=Toast.makeText(this,"Accelerometer not found",Toast.LENGTH_LONG);
				msg.show();
			  }
		
		
		if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
			  // Success! There's a gravity sensor
        gravity=mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_UI);
			  }
			else {
			  // Failure! No accelerometer
				Toast msg=Toast.makeText(this,"Gravity sensor not found",Toast.LENGTH_LONG);
				msg.show();
			  }
		
		
		
		
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
		
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	 
    	
        return super.onStartCommand(intent, flags, startId);
    }
    
    
    
@Override
public void onAccuracyChanged(Sensor arg0, int arg1) {
	// TODO Auto-generated method stub
	
}

@Override
public void onSensorChanged(SensorEvent event) {
	// TODO Auto-generated method stub
        acc_x = event.values[0];
	    acc_y = event.values[1];
	    acc_z = event.values[2];
	    // Do something with this sensor value
	    
	    Acc_x=String.format("%.2g%n",acc_x);
	    Acc_y=String.format("%.2g%n",acc_y);
	    Acc_z=String.format("%.2g%n",acc_z);
	    
	    if(acc_x>10)
	    {
	    	asynchttp.flag=1;
		toast = Toast.makeText(this,Acc_x+"xx", Toast.LENGTH_SHORT);
		toast.show(); 
	    }		
	    
}



}