package com.sumit.testclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.SupportMapFragment;

public class TestActivity extends Activity {
private GoogleMap googleMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_act);
	/*	 MapView mapView = (MapView) findViewById(R.id.map);
	        ((Object) mapView).setBuiltInZoomControls(true);
	        
	        CameraPosition cameraPosition = new CameraPosition.Builder().target(
					new LatLng(17.385044, 78.486671)).zoom(12).build();

	googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	         
	*/
		
		
	try{
			initializeMap();
			int NumberOfPoints=5;
			double[] latitudes=new double [NumberOfPoints];
			double[] longitudes=new double [NumberOfPoints];
			
//			for(int pointIndex=0; pointIndex<NumberOfPoints; pointIndex++){
//				   GeoPoint point = new GeoPoint((int)(latitudes[pointIndex]* 1E6),(int)(longitudes[pointIndex]* 1E6));
//				   OverlayItem overlayitem = new OverlayItem(point, "title", "snipet");
//				   youOverlayClassHolder.addOverlay(overlayitem);
//				   mapOverlays.add(itemizedoverlay);
//				}
		String values[]={"29.8687", "77.8947","28.9074"," 77.9147"," 77.9513","29.88013"};
			Bundle extras = getIntent().getExtras();
			
			if (extras != null) {
			    String value44 = extras.getString("EXTRA_SESSION_ID");
			    System.out.println("44- "+value44);
			   values = value44.split(",");
			  
			   
			  String a= values[values.length-1];
			  int len=a.length();
			  a = a.substring(0,a.length()-1);
			  System.out.println("eff"+(int)a.charAt(a.length()-1)+"d");
			  values[values.length-1]=a;
			  for(int i=0;i<values.length;i++){

				   System.out.println("d"+values[i]+"d");
				   }
			}
			
			

			System.out.println("v1+");
             for(int it=0;it<values.length;it++)
        {

            	 
        //    String s[] = values[i].split(",");
            	String v0= values[it++];
            	String v2=values[it++];
            	String v1=values[it];
            	double d1=Double.parseDouble(v1);
            	double d2=Double.parseDouble(v2);
            	System.out.println("fd"+d1+"d");
            	System.out.println("fd"+d2+"d");
         //   String v1 = s[0];
         //   String v2 = s[1];
            
        googleMap.addMarker(new MarkerOptions()
        .position(
                new LatLng(d1,d2))
        		
        		.title(v0)
        .icon(BitmapDescriptorFactory
                .fromResource(R.drawable.marker)));
        System.out.println(d1);
        }
             
		//	googleMap.setMyLocationEnabled(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	/*	MapController mc = mapView.getController();
		double lat = Double.parseDouble("48.85827758964043"); // latitude
		double lon = Double.parseDouble("2.294543981552124"); // longitude
		GeoPoint geoPoint = new GeoPoint((int)(lat * 1E6), (int)(lon * 1E6));
		mc.animateTo(geoPoint);
		mc.setZoom(15);
		mapView.invalidate(); 
		*/
		

	}
	
    private void initializeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	
}