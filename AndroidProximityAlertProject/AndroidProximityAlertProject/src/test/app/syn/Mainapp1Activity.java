package test.app.syn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Mainapp1Activity extends Activity implements OnClickListener {

	Button btngps;
	Button btnproximity;
	Button btnpost;
	Button btnasynch;
	Button btnmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainapp1);
		btngps = (Button) findViewById(R.id.gps);
	    btngps.setOnClickListener(this);
	    btnproximity = (Button) findViewById(R.id.prox);
	    btnproximity.setOnClickListener(this);
	    btnpost = (Button) findViewById(R.id.post);
	    btnpost.setOnClickListener(this);
	    btnasynch = (Button) findViewById(R.id.asynch);
	    btnasynch.setOnClickListener(this);
	    btnmap = (Button) findViewById(R.id.map);
	    btnmap.setOnClickListener(this);
	    
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mainapp1, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
	    case R.id.gps:
	    	Intent ig = new Intent(getBaseContext(), LbsGeocodingActivity.class);
	    	startActivity(ig);
	      break;
	    case R.id.prox:
	    	Intent ip = new Intent(getBaseContext(), ProxAlertActivity.class);
	    	startActivity(ip);
	      break;
	    case R.id.post:
	    	Intent ipo = new Intent(getBaseContext(), AndroidHttpPostActivity.class);
	    	startActivity(ipo);
	      break;
	    case R.id.asynch:
	    	Intent apo = new Intent(getBaseContext(), asynchttp.class);
	    	startActivity(apo);
	      break;
	    case R.id.map:
	    	Intent mpo = new Intent(getBaseContext(), TestActivity.class);
	    	startActivity(mpo);
	      break;
	    default:
	      break;
	    }
	}

}
