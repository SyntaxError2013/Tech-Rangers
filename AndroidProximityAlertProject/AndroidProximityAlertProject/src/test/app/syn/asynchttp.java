package test.app.syn;

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
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class asynchttp extends Activity {
	private GoogleMap googleMap;
	TextView result;
	EditText id;
	EditText message;
	String idst="1";
	String messt="help";
	

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in
																	// Milliseconds

	protected LocationManager locationManager;
	protected Location currentLocation;

	protected Button retrieveLocationButton;
	protected Button reverseGeocodingButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynchlay);
		id = (EditText) findViewById(R.id.giveid);
		message = (EditText) findViewById(R.id.givemessage);
		retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button1);
        reverseGeocodingButton = (Button) findViewById(R.id.reverse_geocoding_button1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        locationManager.requestLocationUpdates(
        		LocationManager.GPS_PROVIDER, 
        		MINIMUM_TIME_BETWEEN_UPDATES, 
        		MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
        		new MyLocationListener()
        );
        showCurrentLocation();
		retrieveLocationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCurrentLocation();
			}
		});
		
		
		result = (TextView) findViewById(R.id.resulta2);

		Button con = (Button) findViewById(R.id.asynchgo);
		con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				idst=id.getText().toString();
				messt=message.getText().toString();
				TestAsync testhttp = new TestAsync();
				testhttp.execute();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	class TestAsync extends AsyncTask<String, Void, String> {
		
		

		protected String doInBackground(String... params) {
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://172.17.9.209:8080/hackathon1/server_new");

				// Add your datasasklj
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("ver", idst));
				nameValuePairs.add(new BasicNameValuePair("message", messt));
				nameValuePairs.add(new BasicNameValuePair("x_co", currentLocation.getLongitude()+""));
				nameValuePairs.add(new BasicNameValuePair("y_co", currentLocation.getLatitude()+""));
				nameValuePairs.add(new BasicNameValuePair("help", "H"));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				System.out.println("fg");
				HttpResponse response = httpclient.execute(httppost);
				/*
				 * //storing session cookies cookies = ((AbstractHttpClient)
				 * httpclient).getCookieStore().getCookies();
				 * System.out.println("Cookies"+cookies);
				 */
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "iso-8859-1"),
						8);
				StringBuffer stringBuffer = new StringBuffer("");
				String line = "";
				String LineSeparator = System.getProperty("line.separator");
				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line + LineSeparator);
				}
				System.out.println("Cookies" + stringBuffer);
				reader.close();

				// result.setText(stringBuffer.toString());

				// Toast.makeText(asynchttp.this, "Finished",
				// Toast.LENGTH_LONG).show();
				return stringBuffer + "";
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
	
	protected void showCurrentLocation() {

		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (currentLocation != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					currentLocation.getLongitude(), currentLocation.getLatitude()
			);
			Toast.makeText(asynchttp.this, message,
					Toast.LENGTH_LONG).show();
		}

	}  
	
	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
			String message = String.format(
					"New Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude()
			);
			Toast.makeText(asynchttp.this, message, Toast.LENGTH_LONG).show();
		}

		public void onStatusChanged(String s, int i, Bundle b) {
			Toast.makeText(asynchttp.this, "Provider status changed",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderDisabled(String s) {
			Toast.makeText(asynchttp.this,
					"Provider disabled by the user. GPS turned off",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderEnabled(String s) {
			Toast.makeText(asynchttp.this,
					"Provider enabled by the user. GPS turned on",
					Toast.LENGTH_LONG).show();
		}

	}
}