// Import necessary Android packages
package com.example.hi.googlemaptest; 

// Import Android packages for location and map services
import android.Manifest; 
import android.content.Context; 
import android.content.pm.PackageManager; 
import android.location.Location; 
import android.location.LocationListener; 
import android.location.LocationManager; 
import android.support.v4.app.ActivityCompat; 
import android.support.v4.content.ContextCompat; 
import android.support.v7.app.AppCompatActivity; 
import android.os.Bundle; 
import android.widget.TextView; 

// Import Google Maps packages
import com.google.android.gms.maps.CameraUpdateFactory; 
import com.google.android.gms.maps.GoogleMap; 
import com.google.android.gms.maps.OnMapReadyCallback; 
import com.google.android.gms.maps.SupportMapFragment; 
import com.google.android.gms.maps.model.BitmapDescriptorFactory; 
import com.google.android.gms.maps.model.LatLng; 
import com.google.android.gms.maps.model.MarkerOptions; 

// MainActivity class declaration
public class MainActivity extends AppCompatActivity { 

    // Declare variables for map fragment and text views
    SupportMapFragment smFragment; 
    TextView latitude, longitude; 

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main); 
        
        // Initialize text views for latitude and longitude
        latitude = (TextView)findViewById(R.id.tv_latitude); 
        longitude = (TextView)findViewById(R.id.tv_longitude); 

        // Check for location permissions
        int coarse_loc_status = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION); 
        int fine_loc_status = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION); 

        // Request permissions if not granted
        if(coarse_loc_status == PackageManager.PERMISSION_GRANTED && fine_loc_status == PackageManager.PERMISSION_GRANTED) { 
            // Permissions granted
        } else { 
            // Request permissions
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 123); 
        } 
        
        // Initialize map fragment
        smFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment); 
        smFragment.getMapAsync(new OnMapReadyCallback() { 
            @Override 
            public void onMapReady(final GoogleMap googleMap) { 
                // Get location manager
                final LocationManager lManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
                lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); 
                
                // Request location updates
                lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, new LocationListener() { 
                    @Override 
                    public void onLocationChanged(Location location) { 
                        // Update latitude and longitude text views
                        double lati = location.getLatitude(); 
                        double longi = location.getLongitude(); 
                        latitude.setText(String.valueOf(lati)); 
                        longitude.setText(String.valueOf(longi)); 
                        
                        // Create marker options
                        MarkerOptions mOption = new MarkerOptions(); 
                        mOption.position(new LatLng(lati, longi)); 
                        mOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.car)); 
                        mOption.title("madhu-7780312615");               // titles
                        
                        // Add marker to the map
                        googleMap.addMarker(mOption); 
                        
                        // Animate camera to new location
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 15f)); 
                    } 
                    
                    // Other overridden methods for LocationListener
                    @Override 
                    public void onStatusChanged(String provider, int status, Bundle extras) { } 
                    @Override 
                    public void onProviderEnabled(String provider) { } 
                    @Override 
                    public void onProviderDisabled(String provider) { } 
                }); 
            } 
        }); 
    } 
}
