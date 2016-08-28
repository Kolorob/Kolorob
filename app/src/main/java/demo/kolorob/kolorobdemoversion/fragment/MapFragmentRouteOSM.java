package demo.kolorob.kolorobdemoversion.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.RoadNode;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by israt.jahan on 5/5/2016.
 */
public class MapFragmentRouteOSM extends Activity implements View.OnClickListener, MapEventsReceiver, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    Drawable newMarker;
    Marker marker;
    MyLocationNewOverlay mylocation;
    private LinearLayout subcatlistholder;
    String stlat, stlong,centername;
    int ind = 0;
    Polyline roadOverlay;
    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    List<String> listData = new ArrayList<String>();
    double laat,longg;
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    private String locationName;
    private int locationNameId;
    private static double VIEW_WIDTH;
    private int primaryIconWidth;
    double roadlength;


    private ArrayList<HealthServiceProviderItem> healthServiceProvider = null;
    GeoPoint markerlocation, userlocation;
    Marker usermarker;

    MapView mapView;
    private int categoryId;



    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }




    int subcategotyId,height,width;
    View rootView;

    public MapFragmentRouteOSM() {

    }

    LocationManager locationManager;
    String provider;
    boolean havePolyLine;

    Marker curposition;
    boolean statusofservice = false;
    Location location;
    ProgressDialog dialog;
    IMapController mapViewController;

    @Override
     public void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        dialog = new ProgressDialog(MapFragmentRouteOSM.this);
        dialog.setMessage("দয়া করে অপেক্ষা করুন");
        dialog.show();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        LayoutInflater li = LayoutInflater.from(this);

        double latDouble, longDouble;
        int i = 0;

        setContentView(R.layout.fragment_map1);
        super.onCreate(savedInstanceState);


        VIEW_WIDTH = AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC;

        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.80);
        mapView = (MapView)findViewById(R.id.mapview);

        havePolyLine = false;
        if (havePolyLine) {
            mapView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        setMapView(mapView);

        mapView.setClickable(true);


        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);


        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setTilesScaledToDpi(true);

        mapViewController = mapView.getController();
        SharedPreferences pref = this.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String Latitude = pref.getString("Latitude", null);
        String Longitude = pref.getString("Longitude", null);
        centername = pref.getString("Name", null);
        locationNameId = pref.getInt("LocationNameId", 0);
        double lat = Double.parseDouble(Latitude);
        double lon = Double.parseDouble(Longitude);
        markerlocation = new GeoPoint(lat,lon);
        Marker centermarker = new Marker(mapView);
        curposition = new Marker(mapView);
        centermarker.setPosition(markerlocation);
        centermarker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
        centermarker.setTitle("     গন্তব্য");

        mapView.getOverlays().add(centermarker);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        final int gpsVersion = getResources().getInteger(com.google.android.gms.R.integer.google_play_services_version);

        // Showing status
        if (gpsVersion >= 8400000) {

            statusofservice = true;
            buildGoogleApiClient();
        } else {

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();//dialog needs to be modified more of an alert dialog
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            // Creating an empty criteria object
            Criteria criteria = new Criteria();

            // Getting the name of the provider that meets the criteria
            provider = locationManager.getBestProvider(criteria, false);


            if (provider != null && !provider.equals("")) {

                // Get the location from the given provider
                location = locationManager.getLastKnownLocation(provider);


                locationManager.requestLocationUpdates(provider, 60000, 0.0f, this);


                if (location != null) {
                    onLocationChanged(location);
                    Drawroute(userlocation, markerlocation);
                } else
                    Toast.makeText(this, "Location can't be retrieved", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "No Provider Found", Toast.LENGTH_SHORT).show();
            }
        }
        mapViewController.setZoom(18);
        mapViewController.setCenter(markerlocation);



        //---
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(mapView);
        mapView.getOverlays().add(myScaleBarOverlay);


        ImageButton curButton = (ImageButton) findViewById(R.id.imageButton);
        curButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mapView.getOverlays().remove(curposition);
                if (mLastLocation != null|| location!=null) {
                    Location setloc;
                    if (mLastLocation!=null) {
                        setloc=mLastLocation;
                        onLocationChanged(mLastLocation);



                    }
                    else {
                        setloc=location;
                        onLocationChanged(location);}


                    curposition.setPosition(new GeoPoint(setloc));
                    curposition.setIcon(getApplicationContext().getResources().getDrawable(R.drawable.my_location_2_icon));
                    mapViewController.animateTo(new GeoPoint(setloc));
                    mapView.getOverlays().add(curposition);
                    curposition.setTitle("আপনি এখন এখানে");
                }
            }
        });



    }
    public void calltransportlayout()
    {
        RelativeLayout trlayout,headlayout;
        TextView disttext,Bustext,Ricksawtext,Cngtext,Walkingtext,headtext;
        //trlayout=(RelativeLayout)rootView.findViewById(R.id.transportdetailslayout);

      //  trlayout.setVisibility(View.VISIBLE);
        headlayout=(RelativeLayout)findViewById(R.id.headerlayout);
        headlayout.setVisibility(View.VISIBLE);
        headtext=(TextView)findViewById(R.id.headtext);
        headtext.setText(centername);
        String distance= String.format("%.2f", roadlength);
        disttext=(TextView)findViewById(R.id.textView16);
        disttext.setVisibility(View.GONE);
        Cngtext=(TextView)findViewById(R.id.cngtext);
        Bustext=(TextView)findViewById(R.id.bustext);
        Ricksawtext=(TextView)findViewById(R.id.ricksawtext);
        Walkingtext=(TextView)findViewById(R.id.walkingtext);
      String bdistance= EtoBconversion(distance);
        disttext.setText(getString(R.string.distance) +": " +bdistance+ " কি.মি" );
        disttext.setVisibility(View.VISIBLE);
        String Busfare= EtoBconversion(String.valueOf((int) Math.round(roadlength*1.7)));
        String Busfare2= EtoBconversion(String.valueOf((int) Math.round(roadlength*3.7)));
        String bustime= EtoBconversion(String.valueOf((int) Math.round((roadlength/15)*60)));
        if (Integer.parseInt(Busfare) <=7.00)Bustext.setText( "৭ "+ "টাকা এবং খুব কম সময় লাগার কথা");
        else {

            Bustext.setText("আনুমানিক "+Busfare + "- "+Busfare2+" টাকা এবং "  + bustime+ " মিনিট সময় লাগতে পারে"  );
        }
        String CNGfare= EtoBconversion(String.valueOf((int) Math.round((roadlength-2)*12+40)));
        String CNGfare2= EtoBconversion(String.valueOf((int) Math.round((roadlength-2)*12+70)));
        String CNGtime= EtoBconversion(String.valueOf((int) Math.round((roadlength/13)*60)));
        if (Integer.parseInt(CNGfare) <=40.00)Cngtext.setText( "৪০ " + "টাকা এবং খুব কম সময় লাগার কথা");
        else {

            Cngtext.setText("আনুমানিক "+CNGfare + "- "+CNGfare2+" টাকা এবং " + CNGtime+ " মিনিট সময় লাগতে পারে"  );
        }
        String rickfare= EtoBconversion(String.valueOf((int) Math.round((roadlength)*20)));
        String ricktime=EtoBconversion( String.valueOf((int) Math.round((roadlength/10)*60)));
        if (Integer.parseInt(rickfare) <=10.00)Ricksawtext.setText( "১০ " + "টাকা এবং খুব কম সময় লাগার কথা");
        else {

            Ricksawtext.setText("আনুমানিক "+rickfare + " টাকা এবং " + ricktime+ " মিনিট সময় লাগতে পারে"  );
        }

        String wtime=EtoBconversion( String.valueOf((int) Math.round((roadlength/8)*60)));


        Walkingtext.setText( wtime+ " মিনিট সময় লাগতে পারে"  );

    }
    public void Drawroute(GeoPoint Ulocation, GeoPoint Mlocation) {
        dialog.dismiss();
        mapView.getOverlays().remove(roadOverlay);


        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(userlocation);
        waypoints.add(markerlocation);

        RoadManager roadManager=new OSRMRoadManager(this);


        Road road = roadManager.getRoad(waypoints);
        if (road.mStatus != Road.STATUS_OK) {

            AlertMessage.showMessage(this,"দুঃখিত!"," যান্ত্রিক গোলযোগের কারনে পথ দেখানো সম্ভব  হচ্ছে না। দয়া করে কিছুক্ষণ পরে চেষ্টা করুন!");

//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("দুঃখিত! যান্ত্রিক গোলযোগের কারনে পথ দেখানো সম্ভব  হচ্ছে না। দয়া করে কিছুক্ষণ পরে চেষ্টা করুন!")
//                    .setCancelable(false)
//                    .setPositiveButton("ঠিক আছে", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //do things
//                        }
//                    });
//            AlertDialog alert = builder.create();
//            alert.show();

        }
        else {
            roadOverlay = roadManager.buildRoadOverlay(road, this);
            roadOverlay.setColor(getResources().getColor(R.color.orange));
            roadlength = road.mLength;
            mapView.getOverlays().add(roadOverlay);
            havePolyLine = true;
            if (havePolyLine) {
                mapView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            //3. Showing the Route steps on the map
            FolderOverlay roadMarkers = new FolderOverlay(this);
            mapView.getOverlays().add(roadMarkers);
            Drawable nodeIcon = getResources().getDrawable(R.drawable.marker_node);
            for (int ii = 0; ii < road.mNodes.size(); ii++) {
                RoadNode node = road.mNodes.get(ii);
                Marker nodeMarker = new Marker(mapView);
                nodeMarker.setPosition(node.mLocation);
                nodeMarker.setIcon(nodeIcon);

                //4. Filling the bubbles
                nodeMarker.setTitle("Step " + ii);
                nodeMarker.setSnippet(node.mInstructions);
                nodeMarker.setSubDescription(Road.getLengthDurationText(this, node.mLength, node.mDuration));
                Drawable iconContinue = getResources().getDrawable(R.drawable.ic_continue);
                nodeMarker.setImage(iconContinue);
                //4. end

                roadMarkers.add(nodeMarker);
                mapView.invalidate();
            }
            calltransportlayout();
        }
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        // Toast.makeText(getActivity(), "Tap on (" + p.getLatitude() + "," + p.getLongitude() + ")", Toast.LENGTH_SHORT).show();
        InfoWindow.closeAllInfoWindowsOn(mapView);
        return true;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }


    @Override
    public void onClick(View v) {

    }


    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public void onLocationChanged(Location location) {
        dialog.dismiss();
        // Getting reference to TextView tv_longitude
        if (statusofservice == false) {
            mapView.getOverlays().remove(usermarker);
          //  Toast.makeText(this, "Tap on locationmanager (" + location.getLatitude() + "," + location.getLongitude() + ")", Toast.LENGTH_SHORT).show();
            usermarker = new Marker(mapView);
            laat = location.getLatitude();
            longg = location.getLongitude();
            userlocation = new GeoPoint(laat,longg);
            usermarker.setPosition(userlocation);
            usermarker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            usermarker.setTitle("     গন্তব্য");
            mapView.getOverlays().add(usermarker);
        } else {
            mapView.getOverlays().remove(usermarker);
            stlat = String.valueOf(location.getLatitude());
            stlong = String.valueOf(location.getLongitude());
            laat = location.getLatitude();
            longg = location.getLongitude();
            usermarker = new Marker(mapView);
            userlocation = new GeoPoint(laat, longg);
            usermarker.setPosition(userlocation);
            usermarker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            usermarker.setTitle("১ম অবস্থান");
            mapView.getOverlays().add(usermarker);
          //  Toast.makeText(this, "Tap on (" + stlat + "," + stlong + ")", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(100); // Update location every second

//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, getActivity());


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            stlat = String.valueOf(mLastLocation.getLatitude());
            stlong = String.valueOf(mLastLocation.getLongitude());
            double laat = mLastLocation.getLatitude();
            double longg = mLastLocation.getLongitude();
            userlocation=new GeoPoint(laat,longg);
            usermarker=new Marker(mapView);
            usermarker.setPosition(userlocation);
            usermarker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            mapView.getOverlays().add(usermarker);
            Drawroute(userlocation, markerlocation);

        }
       // Toast.makeText(this, "Tap on (" + stlat + "," + stlong + ")", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d("s", "onStart fired ..............");
        if(statusofservice==true)
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {

        if(statusofservice==true) {
            mGoogleApiClient.disconnect();
            Log.d("s", "isConnected ...............: " + mGoogleApiClient.isConnected());
        }


        super.onStop();
        Log.d("s", "onStop fired ..............");
    }


    public String EtoBconversion(String english_number) {
        int v = english_number.length();
        String concatResult = "";
        for (int i = 0; i < v; i++) {
            if (english_number.charAt(i) == '1')
                concatResult = concatResult + "১";
            else if (english_number.charAt(i) == '2')
                concatResult = concatResult + "২";
            else if (english_number.charAt(i) == '3')
                concatResult = concatResult + "৩";
            else if (english_number.charAt(i) == '4')
                concatResult = concatResult + "৪";
            else if (english_number.charAt(i) == '5')
                concatResult = concatResult + "৫";
            else if (english_number.charAt(i) == '6')
                concatResult = concatResult + "৬";
            else if (english_number.charAt(i) == '7')
                concatResult = concatResult + "৭";
            else if (english_number.charAt(i) == '8')
                concatResult = concatResult + "৮";
            else if (english_number.charAt(i) == '9')
                concatResult = concatResult + "৯";
            else if (english_number.charAt(i) == '0')
                concatResult = concatResult + "০";
            else if (english_number.charAt(i) == '.')
                concatResult = concatResult + ".";
        }
        return concatResult;
    }

   /* @Override
    public void onBackPressed() {
       ;
        List<Fragment> fragmentList =  getActivity().getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            //TODO: Perform your logic to pass back press here
            for(Fragment fragment : fragmentList){
                if(fragment instanceof OnBackPressedListener){
                    ((OnBackPressedListener)fragment).onBackPressed();
                }
            }
        }
    }
*/
}
