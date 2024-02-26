Create an android application to display current location on Google maps by 
using Google-Maps Service. 
1) Create a project, add google-play-services:maps as a library project. 
Right click APP folder-openmoduleSetting-dependacy, select( +) symbol.     
Click on + symbol and choose (Library dependency) select     
“com.google.android.gms:play-service-maps:16.1.0”  
2) Create a fragment UI component in Activity xml with the following name. 
<fragment  
android:name="com.google.android.gms.maps.SupportMapFragment" 
………………./> 
3) Use the following code in Activity to get the SupportMapFragment into Activity.  
SupportMapFragment  frag=(SupportMapFragment) 
getSupportFragmentManager().findFragmentById(R.id.XXX); 
4) Get the GoogleMap object from SupportMapFragment. 
frag.getMapAsync(new OnMapReadyCallback() { 
@Override 
public void onMapReady(GoogleMap googleMap) { 
//write the logic, what you want to perform on GoogleMap 
} 
}); 
5) To work with any Google-API we have to get an API key from Google, go through 
the following URL to get an API key. 
http://code.google.com/apis/console 
API Key:  
Example Like: 
AIzaSyAOeIcUosQIJD-FuZNCU0TkA-oQNWSfeZg 
AIzaSyAAIzaSyAXQpjHUvOxg97SutBJN2itPpcCBd7IwkY JN2itPpcCBd7IwkY 
AIzaSyAXQpjHUvOxg97SutBJN2itPpcCBd7Iwk 
Prepared by: Dept. of CSE, RGMCET 
Page 150 
ANDROID PROGRAMMING 
6) Configure the API in manifest.xml with the following tag inside                
<application> tag.  
<meta-data 
android:name=“” 
android:value=“AIzaSyAOeIcUosQIJD-FuZNCU0TkA-oQNWSfeZg”/> 
7)  Set the following method to GoogleMap to change the Map style. 
googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); 
8) Use the following code to place a marker (location) on a Map.  
MarkerOptions   mOption=new MarkerOptions(); 
mOption.position(new LatLng(lati, longi)); 
googleMap.addMarker(mOption); 
9) Use the following method to apply Zoom & move the goole map to a specific 
location. 
googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new    
LatLng(lati, longi),15f)); 
10) Use the following code to customize the icon, set the title 
mOption.icon (BitmapDescriptorFactory.fromResource(R.drawable.car)); 
mOption.title(“title here”);
