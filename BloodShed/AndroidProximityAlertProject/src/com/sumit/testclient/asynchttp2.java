package com.sumit.testclient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class asynchttp2 extends Activity {
private GoogleMap googleMap;
TextView result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynchlay);
		result = (TextView) findViewById(R.id.resulta2);
		Button con=(Button) findViewById(R.id.asynchgo);
		con.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TestAsync testhttp= new TestAsync();
				testhttp.execute();
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	class TestAsync extends AsyncTask<String, Void, String>
	 {
		
		 protected String doInBackground(String... params) {
		        try {
		            HttpClient httpclient = new DefaultHttpClient();
		            HttpPost httppost = new HttpPost("http://172.17.9.209:8080/hackathon1/server_new");

		            // Add your datasasklj
		           List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		            nameValuePairs.add(new BasicNameValuePair("ver", "1234"));
		            nameValuePairs.add(new BasicNameValuePair("x_co", "21.5"));
		            nameValuePairs.add(new BasicNameValuePair("y_co", "39.5"));
		            nameValuePairs.add(new BasicNameValuePair("help", "H"));
		            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		            // Execute HTTP Post Request
		            System.out.println("fg");
		            HttpResponse response = httpclient.execute(httppost);
		          /* 
		            //storing session cookies
		            cookies = ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
		            System.out.println("Cookies"+cookies);
		           */ 
		            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "iso-8859-1"), 8);
		            StringBuffer stringBuffer = new StringBuffer("");
					String line = "";
					String LineSeparator = System.getProperty("line.separator");
					while ((line = reader.readLine()) != null) {
						stringBuffer.append(line + LineSeparator);
					}
					System.out.println("Cookies"+stringBuffer);
					reader.close();

				//	result.setText(stringBuffer.toString());

				//	Toast.makeText(asynchttp.this, "Finished",
				//			Toast.LENGTH_LONG).show();
		            return stringBuffer+"";
		        } catch (Exception e) {
		            e.printStackTrace();
		            return null;
		        }
		    }

		    @Override
		    protected void onPostExecute(String result) {
		       System.out.println("10114040 " + result + "successful connect");
		      
		       Intent mpo = new Intent(getBaseContext(), TestActivity.class);
		       mpo.putExtra("EXTRA_SESSION_ID", result);
		       startActivity(mpo);
		      
		    }
	
}
	
}