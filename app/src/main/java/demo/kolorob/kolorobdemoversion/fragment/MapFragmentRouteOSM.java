package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;

import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by israt.jahan on 5/5/2016.

 */
public class MapFragmentRouteOSM extends Fragment implements View.OnClickListener, MapEventsReceiver, LocationListener {
    Drawable newMarker;
    Marker marker;
    MyLocationNewOverlay mylocation;
    private LinearLayout subcatlistholder;
    GeoPoint pp;
    int ind = 0;
    List<String> listData = new ArrayList<String>();

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

    public int getLocationNameId() {
        return locationNameId;
    }

    public void setLocationNameId(int locationNameId) {
        this.locationNameId = locationNameId;
        if (locationNameId == AppConstants.PLACE_BAUNIABADH) {
            listData.add(AppConstants.BAUNIABADH);
            listData.add(AppConstants.PARIS_ROAD);
        } else {
            listData.add(AppConstants.PARIS_ROAD);
            listData.add(AppConstants.BAUNIABADH);
        }
    }

    private ArrayList<HealthServiceProviderItem> healthServiceProvider = null;

    private ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider = null;
    private ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider = null;
    private ArrayList<JobServiceProviderItem> jobServiceProvider = null;
    private ArrayList<FinancialServiceProviderItem> financialServiceProvider = null;
    private ArrayList<EducationServiceProviderItem> educationServiceProvider = null;
    MapView mapView, mapp;
    private int categoryId;

    ArrayAdapter arrayAdapter;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public void setEntertainmentServiceProvider(ArrayList<EntertainmentServiceProviderItem> et) {
        this.entertainmentServiceProvider = et;
    }

    public void setLegalaidServiceProvider(ArrayList<LegalAidServiceProviderItem> et) {
        this.legalaidServiceProvider = et;
    }

    public void setFinancialServiceProvider(ArrayList<FinancialServiceProviderItem> et) {
        this.financialServiceProvider = et;
    }

    public void setEducationServiceProvider(ArrayList<EducationServiceProviderItem> et) {
        educationServiceProvider = et;
    }

    int subcategotyId;
    View rootView;
    ArrayList<OverlayItem> anotherOverlayItemArray, anotherOverlayItemArrayfinal, anotherOverlayItemArray2, anotherOverlayItemArray3, anotherOverlayItemArray4, anotherOverlayItemArray7, anotherOverlayItemArray8, anotherOverlayItemArray5, anotherOverlayItemArray6;

    public MapFragmentRouteOSM() {

    }

    LocationManager locationManager ;
    String provider;
    GeoPoint userlocation;
    ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay, anotherItemizedIconOverlay2, anotherItemizedIconOverlay7, anotherItemizedIconOverlay8, anotherItemizedIconOverlay3, anotherItemizedIconOverlay4, anotherItemizedIconOverlay5, anotherItemizedIconOverlay6;
    ArrayList<ItemizedIconOverlay> overlayholder = null;

    public ArrayList<HealthServiceProviderItem> getHealthServiceProvider() {
        return healthServiceProvider;
    }

    public void setHealthServiceProvider(ArrayList<HealthServiceProviderItem> et) {
        this.healthServiceProvider = et;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        LayoutInflater li = LayoutInflater.from(getActivity());
        double latDouble, longDouble;
        int i = 0;

        super.onCreate(savedInstanceState);


        rootView = inflater.inflate(R.layout.fragment_map, container,
                false);
        VIEW_WIDTH = AppUtils.getScreenWidth(getActivity()) * AppConstants.CAT_LIST_LG_WIDTH_PERC;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.80);
        mapView = (MapView) rootView.findViewById(R.id.mapview);
        setMapView(mapView);

        mapView.setClickable(true);


        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);


        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
        mapView.setTilesScaledToDpi(true);

        IMapController mapViewController = mapView.getController();

        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);


        if(provider!=null && !provider.equals("")){

            // Get the location from the given provider
            Location location = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(provider, 20000, 1, this);


            if(location!=null)
                onLocationChanged(location);
            else
                Toast.makeText(getActivity(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getActivity(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }


        if (locationNameId == 1) {

            mapViewController.setZoom(18);
            mapViewController.setCenter(AppConstants.BAUNIA1);
        } else if (locationNameId == 2) {
            mapViewController.setZoom(17);
            mapViewController.setCenter(AppConstants.PARIS1);
        }


        //---
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(mapView);
        mapView.getOverlays().add(myScaleBarOverlay);




        ImageButton curButton=(ImageButton) rootView.findViewById(R.id.currlocation);
        curButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mylocation = new MyLocationNewOverlay( mapView);
                mylocation.enableMyLocation();
                mylocation.enableFollowLocation();
                IMyLocationProvider s= mylocation.getMyLocationProvider();

                mylocation.getMyLocation();
                mapView.getOverlays().add(mylocation);
                Toast.makeText(getActivity(),
                        "latitude = " + mylocation.getLastFix().getLatitude() + " longitude = " + mylocation.getLastFix().getLongitude() ,
                        Toast.LENGTH_SHORT).show();
            }
        });
        mapViewController.setZoom(18);
        mapViewController.setCenter(AppConstants.BAUNIA1);
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String Latitude = pref.getString("Latitude", null);
        String Longitude = pref.getString("Longitude", null);

        double lat=Double.parseDouble(Latitude);
        double lon=Double.parseDouble(Longitude);
        GeoPoint markerlocation=new GeoPoint(lat,lon);
        Marker centermarker=new Marker(mapView);
        centermarker.setPosition(markerlocation);
        centermarker.setTitle("Destination");

        mapView.getOverlays().add(centermarker);
        RoadManager roadManager = new OSRMRoadManager(getActivity());
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();

        waypoints.add(userlocation);
        waypoints.add(markerlocation);
        Road road = roadManager.getRoad(waypoints);
        if (road.mStatus != Road.STATUS_OK)
            Toast.makeText(getActivity(), "Error when loading the road - status=" + road.mStatus, Toast.LENGTH_SHORT).show();

        Polyline roadOverlay = RoadManager.buildRoadOverlay(road, getActivity());
        roadOverlay.setColor(Color.YELLOW);
        mapView.getOverlays().add(roadOverlay);

        //3. Showing the Route steps on the map
        FolderOverlay roadMarkers = new FolderOverlay(getActivity());
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
            nodeMarker.setSubDescription(Road.getLengthDurationText(getActivity(), node.mLength, node.mDuration));
            Drawable iconContinue = getResources().getDrawable(R.drawable.ic_continue);
            nodeMarker.setImage(iconContinue);
            //4. end

            roadMarkers.add(nodeMarker);
        }
        return rootView;
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        Toast.makeText(getActivity(), "Tap on (" + p.getLatitude() + "," + p.getLongitude() + ")", Toast.LENGTH_SHORT).show();
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
        // Getting reference to TextView tv_longitude
        Toast.makeText(getActivity(), "Tap on (" + location.getLatitude() + "," + location.getLongitude() + ")", Toast.LENGTH_SHORT).show();
        Marker usermarker=new Marker(mapView);
        double laat=location.getLatitude();
        double longg=location.getLongitude();
       userlocation=new GeoPoint(laat,longg);
        usermarker.setPosition(userlocation);
        mapView.getOverlays().add(usermarker);
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
}

