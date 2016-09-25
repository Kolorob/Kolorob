package demo.kolorob.kolorobdemoversion.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
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
import demo.kolorob.kolorobdemoversion.helpers.KOLOROBRoadManager;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
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
    TextView toastMessage;
    Toast toast;
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
        dialog.setMessage("Please wait for a while..");
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
        centermarker.setTitle("     Destination");

        mapView.getOverlays().add(centermarker);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        final int gpsVersion = getResources().getInteger(com.google.android.gms.R.integer.google_play_services_version);

        double lat2 = Double.parseDouble("23.791519");
        double lon2 = Double.parseDouble("90.411485");
        userlocation = new GeoPoint(lat2,lon2);
        Marker usermarker = new Marker(mapView);
        usermarker.setPosition(userlocation);
        usermarker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
        usermarker.setTitle("     Save the Children, Bangladesh");
        mapView.getOverlays().add(usermarker);
        Drawroute(userlocation, markerlocation);

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
                LayoutInflater inflater = getLayoutInflater();

                View toastView = inflater.inflate(R.layout.toast_view,null);
                toast = new Toast(MapFragmentRouteOSM.this);
                // Set the Toast custom layout
                toast.setView(toastView);


                //   View toastView = toast.getView(); //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */



                toastMessage = (TextView) toastView.findViewById(R.id.toasts);
                toastMessage.setTextSize(20);

                toastMessage.setText("Current position won't work Since static location is used in this version!");


                toastMessage.setTextColor(getResources().getColor(R.color.orange));

                //  toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.kolorob_logo, 0, 0, 0);
                // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

                toastMessage.setGravity(Gravity.CENTER);
                toastMessage.setCompoundDrawablePadding(26);
                toast.setDuration(Toast.LENGTH_LONG);
                //  toastView.setBackgroundColor(getResources().getColor(R.color.orange));
                toast.show();
                    mapViewController.animateTo(new GeoPoint(userlocation));
                    mapView.getOverlays().add(curposition);
                    curposition.setTitle("Your current position");
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
        String bdistance= distance;
        disttext.setText("Distance: " +bdistance+ " km" );
        disttext.setVisibility(View.VISIBLE);
        String Busfare= String.valueOf((int) Math.round(roadlength*1.7));
        String Busfare2= String.valueOf((int) Math.round(roadlength*3.7));
        String bustime= String.valueOf((int) Math.round((roadlength/15)*60));
        if (Integer.parseInt(Busfare) <=7.00)Bustext.setText( " 7 "+ " BDT & should take minimal time");
        else {

            Bustext.setText("Approximately "+Busfare + "- "+Busfare2+" BDT & might take "  + bustime+ " minutes "  );
        }
        String CNGfare= String.valueOf((int) Math.round((roadlength-2)*12+40));
        String CNGfare2= String.valueOf((int) Math.round((roadlength-2)*12+70));
        String CNGtime= String.valueOf((int) Math.round((roadlength/13)*60));
        if (Integer.parseInt(CNGfare) <=40.00)Cngtext.setText( " 40 " + " BDT & should take minimal time");
        else {

            Cngtext.setText("Approximately "+CNGfare + "- "+CNGfare2+" BDT & might take " + CNGtime+ " minutes"  );
        }
        String rickfare= String.valueOf((int) Math.round((roadlength)*20));
        String ricktime= String.valueOf((int) Math.round((roadlength/10)*60));
        if (Integer.parseInt(rickfare) <=10.00)Ricksawtext.setText( " 10 " + " BDT & should take minimal time");
        else {

            Ricksawtext.setText("Approximately "+rickfare + " BDT & might take " + ricktime+ " minutes"  );
        }

        String wtime=String.valueOf((int) Math.round((roadlength/8)*60));


        Walkingtext.setText("Might take "+ wtime+ " minutes"  );

    }
    public void Drawroute(GeoPoint Ulocation, GeoPoint Mlocation) {
        dialog.dismiss();
        mapView.getOverlays().remove(roadOverlay);


        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(userlocation);
        waypoints.add(markerlocation);

        RoadManager roadManager=new KOLOROBRoadManager(this);


        Road road = roadManager.getRoad(waypoints);
        if (road.mStatus != Road.STATUS_OK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Sorry! Its not possible to show routes this time.Please try again later")
                    .setCancelable(false)
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

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
