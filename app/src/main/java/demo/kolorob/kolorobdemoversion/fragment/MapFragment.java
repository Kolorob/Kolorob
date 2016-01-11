package demo.kolorob.kolorobdemoversion.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealth;
import demo.kolorob.kolorobdemoversion.activity.PlaceDetailsActivity;
import demo.kolorob.kolorobdemoversion.helpers.MapInfoWindowAdapter;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class MapFragment extends Fragment implements
        GoogleMap.OnInfoWindowClickListener{

    MapView mMapView;

    private static final int MAP_ZOOM_AMOUNT=17;
    private TextView locName;
    private TextView mapIndicator;
    private GoogleMap googleMap;
    private String locationName;
    private String mapIndicatorText;
    private int categoryId;
    private int locationNameId;

    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.
    private ArrayList<EducationServiceProviderItem> educationServiceProvider=null;
    private ArrayList<HealthServiceProviderItem> healthServiceProvider=null;

    private ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider=null;

    public MapFragment()
    {

    }
    public void setLocationName(String locName)
    {
        locationName = locName;
    }
    public void setMapIndicatorText(String mapInd)
    {
        mapIndicatorText = "<font color='#FEFE00'>"+mapInd+"</font>";
    }
    public void setCategoryId(int id)
    {
        categoryId = id;
    }
    public void setLocationNameId(int i)
    {
        locationNameId = i;
    }


    //TODO Implement the set function for all subcategory item.

    /********************set function for Education subcategory***********************/

    public void setEducationServiceProvider(ArrayList<EducationServiceProviderItem> et)
    {
        educationServiceProvider=et;
    }

    /********************set function for Health subcategory***********************/

    public void setHealthServiceProvider(ArrayList<HealthServiceProviderItem> et)
    {
        healthServiceProvider=et;
    }

    /********************set function for Entertainment subcategory*****************/

    public void setEntertainmentServiceProvider(ArrayList<EntertainmentServiceProviderItem> et)
    {
        entertainmentServiceProvider=et;
    }

    /********************set function for Government subcategory********************/



    /********************set function for Legal subcategory***********************/



    /********************set function for Financial subcategory***********************/



    /********************set function for Job subcategory***********************/





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container,
                false);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        locName = (TextView) rootView.findViewById(R.id.tv_location_name);
        mapIndicator = (TextView) rootView.findViewById(R.id.tv_map_indicator);
        locName.setText(locationName);
        mapIndicator.setText(Html.fromHtml("সব " + mapIndicatorText + " এর স্থান ম্যাপ এ দেখানো হয়েছে"));
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        googleMap.setOnInfoWindowClickListener(this);
        if(locationNameId==1) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(AppConstants.BAUNIA).zoom(MAP_ZOOM_AMOUNT).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        }
        else if(locationNameId==2)
        {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(AppConstants.PARIS).zoom(MAP_ZOOM_AMOUNT).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        }

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                PlaceDetailsActivity.subCatInsGone();
            }
        });

        switch (categoryId)
        {
            case AppConstants.EDUCATION:
                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(getActivity(),categoryId,educationServiceProvider));
                if(educationServiceProvider!=null) {
                    for (EducationServiceProviderItem et : educationServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarker(location, et.getEduNameEng());
                    }
                }
                break;
            case AppConstants.HEALTH:

                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(categoryId,getActivity(),healthServiceProvider));
                if(healthServiceProvider!=null) {
                    for (HealthServiceProviderItem et : healthServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarker(location, et.getNodeName());
                    }
                }



                break;
            case AppConstants.ENTERTAINMENT:

                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(getActivity(),entertainmentServiceProvider,categoryId));
                if(entertainmentServiceProvider!=null) {
                    for (EntertainmentServiceProviderItem et : entertainmentServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarker(location, et.getNodeName());
                    }
                }


                //TODO write necessary codes for entertainment
                break;
            case AppConstants.GOVERNMENT:
                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:
                //TODO write necessary codes for legal
                break;
            case AppConstants.FINANCIAL:
                //TODO write necessary codes for financial
                break;
            case AppConstants.JOB:
                //TODO write necessary codes for job
                break;
            default:
                break;
        }


        return rootView;
    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        LatLng loc = marker.getPosition();
        switch (categoryId)
        {
            case AppConstants.EDUCATION:
                for(EducationServiceProviderItem et:educationServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {
                        Intent ii = new Intent(getActivity(),DetailsInfoActivity.class);
                        ii.putExtra(AppConstants.KEY_DETAILS_VIEW,et);
                        startActivity(ii);
                        break;
                    }
                }
                break;
            case AppConstants.HEALTH:

                for(HealthServiceProviderItem et:healthServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {
                        Intent hel = new Intent(getActivity(),DetailsInfoActivityHealth.class);
                        hel.putExtra(AppConstants.KEY_DETAILS_VIEW,et);
                        startActivity(hel);
                        break;
                    }
                }
                //TODO write necessary codes for health
                break;
            case AppConstants.ENTERTAINMENT:

                for(EntertainmentServiceProviderItem et:entertainmentServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {
                        Intent ii = new Intent(getActivity(),DetailsInfoActivity.class);
                        ii.putExtra(AppConstants.KEY_DETAILS_VIEW,et);
                        startActivity(ii);
                        break;
                    }
                }

                //TODO write necessary codes for entertainment
                break;
            case AppConstants.GOVERNMENT:
                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:
                //TODO write necessary codes for legal
                break;
            case AppConstants.FINANCIAL:
                //TODO write necessary codes for financial
                break;
            case AppConstants.JOB:
                //TODO write necessary codes for job
                break;
            default:
                break;
        }
    }
    private void drawMarker(LatLng point,String title){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[categoryId]));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}