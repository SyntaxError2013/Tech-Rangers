package test.app.syn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class LbsGeocodingActivity extends Activity {
	
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
	
	
	protected LocationManager locationManager;
	protected Location currentLocation;
	
	protected Button retrieveLocationButton;
	protected Button reverseGeocodingButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps1);

        retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button);
        reverseGeocodingButton = (Button) findViewById(R.id.reverse_geocoding_button);
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        locationManager.requestLocationUpdates(
        		LocationManager.GPS_PROVIDER, 
        		MINIMUM_TIME_BETWEEN_UPDATES, 
        		MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
        		new MyLocationListener()
        );
        
		retrieveLocationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCurrentLocation();
			}
		});
		
		
        
    }    


	protected void showCurrentLocation() {

		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (currentLocation != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					currentLocation.getLongitude(), currentLocation.getLatitude()
			);
			Toast.makeText(LbsGeocodingActivity.this, message,
					Toast.LENGTH_LONG).show();
		}

	}   

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
			String message = String.format(
					"New Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude()
			);
			Toast.makeText(LbsGeocodingActivity.this, message, Toast.LENGTH_LONG).show();
		}

		public void onStatusChanged(String s, int i, Bundle b) {
			Toast.makeText(LbsGeocodingActivity.this, "Provider status changed",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderDisabled(String s) {
			Toast.makeText(LbsGeocodingActivity.this,
					"Provider disabled by the user. GPS turned off",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderEnabled(String s) {
			Toast.makeText(LbsGeocodingActivity.this,
					"Provider enabled by the user. GPS turned on",
					Toast.LENGTH_LONG).show();
		}

	}
	
	
}