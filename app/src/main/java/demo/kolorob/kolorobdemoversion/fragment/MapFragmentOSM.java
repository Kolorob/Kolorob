package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.util.ArrayList;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivity;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by israt.jahan on 5/5/2016.
 */
public class MapFragmentOSM extends Fragment implements View.OnClickListener,MapEventsReceiver {
    Drawable newMarker;
    Marker marker;
    EducationServiceProviderItem nulledu;
    GeoPoint pp;
    int ind=0;
    List<String> listData=new ArrayList<String>();
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    private String locationName;
    private int locationNameId;

    public int getLocationNameId() {
        return locationNameId;
    }

    public void setLocationNameId(int locationNameId) {
        this.locationNameId = locationNameId;
        if(locationNameId== AppConstants.PLACE_BAUNIABADH)
        {
            listData.add(AppConstants.BAUNIABADH);
            listData.add(AppConstants.PARIS_ROAD);
        }
        else {
            listData.add(AppConstants.PARIS_ROAD);
            listData.add(AppConstants.BAUNIABADH);
        }
    }
    private ArrayList<HealthServiceProviderItem> healthServiceProvider=null;

    private ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider=null;
    private ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider=null;
    private ArrayList<JobServiceProviderItem> jobServiceProvider=null;
    private ArrayList<FinancialServiceProviderItem> financialServiceProvider=null;
    private ArrayList<EducationServiceProviderItem> educationServiceProvider=null;
    MapView mapView;
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

    public void setEducationServiceProvider(ArrayList<EducationServiceProviderItem> et)
    {
        educationServiceProvider=et;
    }
    int subcategotyId;
    View rootView;
    ArrayList<OverlayItem> anotherOverlayItemArray,anotherOverlayItemArrayfinal,anotherOverlayItemArray2,anotherOverlayItemArray3,anotherOverlayItemArray4,anotherOverlayItemArray7,anotherOverlayItemArray8,anotherOverlayItemArray5,anotherOverlayItemArray6;
    public MapFragmentOSM()
    {

    }
    ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay,anotherItemizedIconOverlay2,anotherItemizedIconOverlay7,anotherItemizedIconOverlay8,anotherItemizedIconOverlay3,anotherItemizedIconOverlay4,anotherItemizedIconOverlay5,anotherItemizedIconOverlay6;
    ArrayList<ItemizedIconOverlay> overlayholder=null;
    public ArrayList<HealthServiceProviderItem> getHealthServiceProvider() {
        return healthServiceProvider;
    }

    public void setHealthServiceProvider(ArrayList<HealthServiceProviderItem> et) {
        this.healthServiceProvider = et;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        double latDouble,longDouble;
        int i=0;
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_map, container,
                false);

        mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);


        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
        //mapView.setTilesScaledToDpi(true);
        // mMyLocationOverlay = new MyLocationOverlay(getActivity(), mapView);
        //    mapView.getOverlays().add(mMyLocationOverlay);
        IMapController mapViewController = mapView.getController();

        anotherOverlayItemArray = new ArrayList<OverlayItem>();
        anotherOverlayItemArray2 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray3 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray4 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray5 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray6 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray7 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray8 = new ArrayList<OverlayItem>();


        if(locationNameId==1) {

            mapViewController.setZoom(18);
            mapViewController.setCenter(AppConstants.BAUNIA1);
        }
        else if(locationNameId==2)
        {
            mapViewController.setZoom(17);
            mapViewController.setCenter(AppConstants.PARIS1);
        }
        switch (categoryId)
        {
            case AppConstants.EDUCATION:
                if(educationServiceProvider!=null) {
                    for (EducationServiceProviderItem et : educationServiceProvider) {
                        //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        subcategotyId = et.getEduSubCategoryId();
                        latDouble=Double.parseDouble(et.getLatitude());
                        longDouble=Double.parseDouble(et.getLongitude());
                        GeoPoint point=  new GeoPoint(latDouble,longDouble);
                        drawMarkerEdu(point, et.getEduNameEng(),et.getAddress(),et.getContactNo(),et.getIdentifierId(), et.getEduSubCategoryId());
                    }
                }
                break;
            case AppConstants.HEALTH:
                break;
            case AppConstants.ENTERTAINMENT:
                //TODO write necessary codes for entertainment
                break;
            case AppConstants.GOVERNMENT:
                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:

                break;
            case AppConstants.FINANCIAL:

                break;
            case AppConstants.JOB:

                break;
            default:
                break;



        }








        //---
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(mapView);
        mapView.getOverlays().add(myScaleBarOverlay);


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

    private void drawMarkerEdu(GeoPoint point,String title,String address,String contact, String node,int subcategotyId){
        Marker marker= new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);


        if(subcategotyId>=1&&subcategotyId<=12)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_feroza));
        else if(subcategotyId>=13&&subcategotyId<=17)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_blue));
        else if(subcategotyId>=18&&subcategotyId<=19)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_pink));
        else if(subcategotyId>=20&&subcategotyId<=21)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_green));
        else if(subcategotyId>=22&&subcategotyId<=26)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_yellow));
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView,point,title,contact,node);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
        //marker.setTitle("Title of the marker");

    }


    @Override
    public void onClick(View v) {

    }
    private class MyInfoWindow extends InfoWindow{
        String titlemarker,contact2,node;
        GeoPoint pp;
        public MyInfoWindow(int layoutResId, MapView mapView,GeoPoint point,String title,String contact,String Node) {
            super(layoutResId, mapView);
            this.pp=point;
            this.titlemarker=title;
            this.contact2=contact;
            this.node=Node;
        }
        public void onClose() {
        }

        public void onOpen(Object arg0) {
            final LinearLayout layout = (LinearLayout) mView.findViewById(R.id.bubble_layout);
            Button btnMoreInfo = (Button) mView.findViewById(R.id.bubble_moreinfo);
            TextView txtTitle = (TextView) mView.findViewById(R.id.bubble_title);
           // TextView contact=(TextView) mView.findViewById(R.id.contact_no);
            TextView adddescription = (TextView) mView.findViewById(R.id.bubble_description);
            TextView txtSubdescription = (TextView) mView.findViewById(R.id.bubble_subdescription);
txtTitle.setText(titlemarker);
           // contact.setText(contact2);
            adddescription.setText(node);
            layout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Override Marker's onClick behaviour here
                    Toast.makeText(getActivity(), "Tap on (" + pp.getLatitude() + "," + pp.getLongitude() + ")", Toast.LENGTH_SHORT).show();
               layout.setVisibility(View.VISIBLE);
                    EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(getActivity());
                    nulledu = educationServiceProviderTable.geteduNode2(node);
                    Intent iient = new Intent(getActivity(), DetailsInfoActivity.class);
                    iient.putExtra(AppConstants.KEY_DETAILS_VIEW, nulledu);
                    getActivity().startActivity(iient);
                    layout.setVisibility(View.GONE);
                }
            });
        }
    }

}

