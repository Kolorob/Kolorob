package demo.kolorob.kolorobdemoversion.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import demo.kolorob.kolorobdemoversion.activity.DetailsFinancialActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainment;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealth;
import demo.kolorob.kolorobdemoversion.activity.DetailsJobActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsLegalActivity;
import demo.kolorob.kolorobdemoversion.activity.PlaceDetailsActivity;
import demo.kolorob.kolorobdemoversion.helpers.MapInfoWindowAdapter;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class MapFragment extends Fragment implements
        GoogleMap.OnInfoWindowClickListener{

    MapView mMapView;


    private TextView locName;
    private TextView mapIndicator;
    private GoogleMap googleMap;
    private String locationName;
    private String mapIndicatorText;
    private int categoryId;
    private int locationNameId;
    LinearLayout ll;

    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.
    private ArrayList<EducationServiceProviderItem> educationServiceProvider=null;
    private ArrayList<HealthServiceProviderItem> healthServiceProvider=null;

    private ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider=null;
    private ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider=null;
    private ArrayList<JobServiceProviderItem> jobServiceProvider=null;
    private ArrayList<FinancialServiceProviderItem> financialServiceProvider=null;
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


    //TODO Implement the set function for all subcategory item.ি
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

    public void setLegalaidServiceProvider(ArrayList<LegalAidServiceProviderItem> et)
    {
        legalaidServiceProvider=et;
    }

    /********************set function for Financial subcategory***********************/


    public void setFinancialServiceProvider(ArrayList<FinancialServiceProviderItem> et)
    {
        financialServiceProvider=et;
    }

    /********************set function for Job subcategory***********************/

    public void setJobServiceProvider(ArrayList<JobServiceProviderItem> et)
    {
        jobServiceProvider=et;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        View rootView;
        if(height>1700)
            rootView = inflater.inflate(R.layout.fragment_map1, container,
                    false);

       else if(height>1000)
            rootView = inflater.inflate(R.layout.fragment_map, container,
                    false);
        else
            rootView = inflater.inflate(R.layout.fragment_map1, container,
                    false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);

        mapIndicator = (TextView) rootView.findViewById(R.id.tv_map_indicator);

        mapIndicator.setText(Html.fromHtml("সব " + mapIndicatorText + " এর স্থান ম্যাপ এ দেখানো হয়েছে"));
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        if(googleMap!=null)
            googleMap.setOnInfoWindowClickListener(this);


        // LinearLayout ll=(LinearLayout) mMapView.findViewById(R.id.ll);



        int MAP_ZOOM_AMOUNT;
        if(height>1000){
            MAP_ZOOM_AMOUNT = 17;

        }

        else {
            MAP_ZOOM_AMOUNT = 16;

        }
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
      /* BitmapDescriptor image = BitmapDescriptorFactory.fromResource(R.drawable.trial);
        LatLngBounds bounds = new LatLngBounds (new LatLng(23.815122, 90.376780), new LatLng(23.824740, 90.384038));
        GroundOverlayOptions groundOverlay = new GroundOverlayOptions()
                .image(image)
                .positionFromBounds(bounds)
                .transparency(0.5f);
        googleMap.addGroundOverlay(groundOverlay);*/
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
                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(categoryId,educationServiceProvider,getActivity()));
                if(educationServiceProvider!=null) {
                    for (EducationServiceProviderItem et : educationServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarkerEdu(location, et.getEduNameEng(), et.getEduSubCategoryId());
                    }
                }
                break;
            case AppConstants.HEALTH:

                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(categoryId,getActivity(),healthServiceProvider));
                if(healthServiceProvider!=null) {
                    for (HealthServiceProviderItem et : healthServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarkerHel(location, et.getNodeName(), et.getRefNum());
                    }
                }



                break;
            case AppConstants.ENTERTAINMENT:

                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(getActivity(),entertainmentServiceProvider,categoryId));
                if(entertainmentServiceProvider!=null) {
                    for (EntertainmentServiceProviderItem et : entertainmentServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarkerEnt(location, et.getNodeName(), et.getEntSubCategoryId());
                    }
                }


                //TODO write necessary codes for entertainment
                break;
            case AppConstants.GOVERNMENT:
                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:
                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(getActivity(), categoryId, legalaidServiceProvider));
                if(legalaidServiceProvider!=null) {
                    for (LegalAidServiceProviderItem et :legalaidServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarkerLeg(location, et.getLegalaidNameEng(), et.getLegalaidSubCategoryId());
                    }
                }
                break;
            case AppConstants.FINANCIAL:
                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(financialServiceProvider,categoryId ,getActivity() ));
                if(financialServiceProvider!=null) {
                    for (FinancialServiceProviderItem et :financialServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarkerFin(location, et.getNodeName(), et.getRefNum());
                    }
                }
                break;
            case AppConstants.JOB:
                googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(jobServiceProvider,getActivity(), categoryId ));
                if(jobServiceProvider!=null) {
                    for (JobServiceProviderItem et :jobServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        drawMarkerJob(location, et.getIdentifierId(),et.getJobSubCategoryId());
                    }
                }
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
                       // getActivity().finish();
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
                        hel.putExtra(AppConstants.KEY_DETAILS_HEALTH,et);
                        startActivity(hel);
                      //  getActivity().finish();
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
                        Intent ent = new Intent(getActivity(),DetailsInfoActivityEntertainment.class);
                        ent.putExtra(AppConstants.KEY_DETAILS_ENT,et);
                        startActivity(ent);
                      //  getActivity().finish();
                        break;
                    }
                }




                //TODO write necessary codes for entertainment
                break;
            case AppConstants.GOVERNMENT:
                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:

                for(LegalAidServiceProviderItem et:legalaidServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {
                        Intent legal = new Intent(getActivity(),DetailsLegalActivity.class);
                        legal.putExtra(AppConstants.KEY_DETAILS_LEGAL,et);
                        startActivity(legal);
                       // getActivity().finish();
                        break;
                    }
                }
                break;
            case AppConstants.FINANCIAL:

                for(FinancialServiceProviderItem et:financialServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {
                        Intent iij = new Intent(getActivity(),DetailsFinancialActivity.class);
                        iij.putExtra(AppConstants.KEY_DETAILS_FINANCIAL,et);
                        startActivity(iij);
                      //  getActivity().finish();
                        break;
                    }
                }
                break;
            case AppConstants.JOB:
                for(JobServiceProviderItem et:jobServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {
                        Intent iijob = new Intent(getActivity(), DetailsJobActivity.class);
                        iijob.putExtra(AppConstants.KEY_DETAILS_JOB,et);
                        startActivity(iijob);
                      //  getActivity().finish();
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }
    private void drawMarkerEdu(LatLng point,String title,int subcategotyId){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);

        if(subcategotyId>=1&&subcategotyId<=12)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[1]));
        else if(subcategotyId>=13&&subcategotyId<=17)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[2]));
        else if(subcategotyId>=18&&subcategotyId<=19)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[3]));
        else if(subcategotyId>=20&&subcategotyId<=21)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[4]));
        else if(subcategotyId>=22&&subcategotyId<=26)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[5]));
        googleMap.addMarker(markerOptions);
    }

    private void drawMarkerHel(LatLng point,String title,int subcategotyId){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);

        if(subcategotyId>=1&&subcategotyId<=7)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[1]));
        else if(subcategotyId>=8&&subcategotyId<=12)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[2]));
        else if(subcategotyId>=13&&subcategotyId<=15)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[3]));
        else if(subcategotyId>=16&&subcategotyId<=20)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[4]));
        else if(subcategotyId==21)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[5]));
        else if(subcategotyId==22)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[6]));



        googleMap.addMarker(markerOptions);
    }
    private void drawMarkerEnt(LatLng point,String title,int subcategotyId){


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);

        if(subcategotyId==1)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[1]));

        else if(subcategotyId==2||subcategotyId==5||subcategotyId==21)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[2]));

        else if(subcategotyId==3)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[3]));
        else if(subcategotyId==4||subcategotyId==6||subcategotyId==7||subcategotyId==8)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[4]));
        else if(subcategotyId>=9&&subcategotyId<=11)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[5]));
        else if(subcategotyId==12)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[6]));
        else if(subcategotyId==13||subcategotyId==14||subcategotyId==16||subcategotyId==19||subcategotyId==20)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[7]));
        else if(subcategotyId==15||subcategotyId==17||subcategotyId==18)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[8]));




        googleMap.addMarker(markerOptions);
    }


    private void drawMarker(LatLng point,String title){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);




        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[categoryId]));
        googleMap.addMarker(markerOptions);
    }
    private void drawMarkerLeg(LatLng point,String title, int subcategotyId){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);

        if(subcategotyId==1)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[1]));

        else if(subcategotyId>=2&&subcategotyId<=5)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[2]));


        googleMap.addMarker(markerOptions);
    }
    private void drawMarkerFin(LatLng point,String title, int subcategotyId){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);
        if(subcategotyId>=1&&subcategotyId<=4)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[1]));
        else if(subcategotyId>=5&&subcategotyId<=8)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[2]));
        else if(subcategotyId>=20&&subcategotyId<=21)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[2]));
        else if(subcategotyId>=9&&subcategotyId<=12)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[3]));
        else if(subcategotyId>=13&&subcategotyId<=15)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[4]));
        else if(subcategotyId>=16&&subcategotyId<=18)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[5]));
        else if(subcategotyId==19)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[6]));

        googleMap.addMarker(markerOptions);
    }
    private void drawMarkerJob(LatLng point,String title, int subcategotyId){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);
        if(subcategotyId==1)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[1]));
        else if(subcategotyId==2||subcategotyId==3)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[2]));
        else if(subcategotyId>=4&&subcategotyId<=9)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[3]));
        else if(subcategotyId>=10&&subcategotyId<=12)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[4]));
        else if(subcategotyId>=13&&subcategotyId<=16)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[5]));
        else if(subcategotyId>=17&&subcategotyId<=19)
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[6]));
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