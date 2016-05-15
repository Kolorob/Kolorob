package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
import demo.kolorob.kolorobdemoversion.helpers.MyInfoWindow;
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
public class MapFragmentOSM extends Fragment implements View.OnClickListener,MapEventsReceiver {
    Drawable newMarker;
    Marker marker;
private LinearLayout subcatlistholder;
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
    private static double VIEW_WIDTH;
    private int primaryIconWidth;
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
        LayoutInflater li = LayoutInflater.from(getActivity());
        double latDouble,longDouble;
        int i=0;
        View v;
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_map, container,
                false);
        VIEW_WIDTH = AppUtils.getScreenWidth(getActivity()) * AppConstants.CAT_LIST_LG_WIDTH_PERC;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.80);
        mapView = (MapView) rootView.findViewById(R.id.mapview);



        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);


        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
        mapView.setTilesScaledToDpi(true);
      /*  mapView.setTilesScaledToDpi(true);
        // Test code
        float density = mapView.isTilesScaledToDpi() ? mapView.getResources().getDisplayMetrics().density : 1;
        density *= 1.5;
        ITileSource aTileSource = mapView.getTileProvider().getTileSource();
        TileSystem.setTileSize((int) (aTileSource.getTileSizePixels() * density));
        System.out.println("density: " + density);*/
        // mMyLocationOverlay = new MyLocationOverlay(getActivity(), mapView);
        //    mapView.getOverlays().add(mMyLocationOverlay);
        IMapController mapViewController = mapView.getController();


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
                for (HealthServiceProviderItem et : healthServiceProvider) {
                    //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                    subcategotyId = et.getRefNum();
                    latDouble = Double.parseDouble(et.getLatitude());
                    longDouble = Double.parseDouble(et.getLongitude());
                    GeoPoint point = new GeoPoint(latDouble, longDouble);
                    drawMarkerHealth(point, et.getNodeName(), et.getAddress(), et.getNodeContact(), et.getNodeId(), et.getRefNum());
                }
                    break;
            case AppConstants.ENTERTAINMENT:
                for (EntertainmentServiceProviderItem et : entertainmentServiceProvider) {
                    //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                    subcategotyId = et.getEntSubCategoryId();
                    latDouble = Double.parseDouble(et.getLatitude());
                    longDouble = Double.parseDouble(et.getLongitude());
                    GeoPoint point = new GeoPoint(latDouble, longDouble);
                    drawMarkerEnt(point, et.getNodeName(), et.getAddress(), et.getNodeContact(), et.getNodeId(), et.getEntSubCategoryId());
                }
                break;
            case AppConstants.GOVERNMENT:
                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:
                for (LegalAidServiceProviderItem et : legalaidServiceProvider) {
                    //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                    subcategotyId = et.getLegalaidSubCategoryId();
                    latDouble = Double.parseDouble(et.getLatitude());
                    longDouble = Double.parseDouble(et.getLongitude());
                    GeoPoint point = new GeoPoint(latDouble, longDouble);
                    drawMarkerLeg(point, et.getLegalaidNameEng(), et.getAddress(), et.getContactNo(), et.getIdentifierId(), et.getLegalaidSubCategoryId());
                }
                break;
            case AppConstants.FINANCIAL:
                for (FinancialServiceProviderItem et : financialServiceProvider) {
                    //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                    subcategotyId = et.getRefNum();
                    latDouble = Double.parseDouble(et.getLatitude());
                    longDouble = Double.parseDouble(et.getLongitude());
                    GeoPoint point = new GeoPoint(latDouble, longDouble);
                    drawMarkerFin(point, et.getNodeName(), et.getAddress(), et.getNodeContact(), et.getNodeId(), et.getRefNum());
                }
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
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_blue));
        else if(subcategotyId>=13&&subcategotyId<=17)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_red));
        else if(subcategotyId>=18&&subcategotyId<=19)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_pink));
        else if(subcategotyId>=20&&subcategotyId<=21)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_green));
        else if(subcategotyId>=22&&subcategotyId<=26)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_brown));
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView,MapFragmentOSM.this.getActivity() ,point,title,contact,node,categoryId);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
        //marker.setTitle("Title of the marker");

    }
    private void drawMarkerHealth(GeoPoint point,String title,String address,String contact, String node,int subcategotyId){
        Marker marker= new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        if(subcategotyId>=1&&subcategotyId<=7)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_blue));
        else if(subcategotyId>=8&&subcategotyId<=12)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_red));
        else if(subcategotyId>=13&&subcategotyId<=15)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_pink));
        else if(subcategotyId>=16&&subcategotyId<=20)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_green));
        else if(subcategotyId==21)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_brown));
        else if(subcategotyId==22)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_feroza));

        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView,MapFragmentOSM.this.getActivity() ,point,title,contact,node,categoryId);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }

    private void drawMarkerEnt(GeoPoint point,String title,String address,String contact, String node,int subcategotyId){


        Marker marker= new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        if(subcategotyId==1)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_blue));

        else if(subcategotyId==2||subcategotyId==5||subcategotyId==21)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_red));
        else if(subcategotyId==3)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_pink));
        else if(subcategotyId==4||subcategotyId==6||subcategotyId==7||subcategotyId==8)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_green));
        else if(subcategotyId>=9&&subcategotyId<=11)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_brown));
        else if(subcategotyId==12)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_feroza));
        else if(subcategotyId==13||subcategotyId==14||subcategotyId==16||subcategotyId==19||subcategotyId==20)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_yellow));
        else if(subcategotyId==15||subcategotyId==17||subcategotyId==18)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_orange));


        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView,MapFragmentOSM.this.getActivity() ,point,title,contact,node,categoryId);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }



    private void drawMarkerLeg(GeoPoint point,String title,String address,String contact, String node,int subcategotyId){
        Marker marker= new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        if(subcategotyId==1)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_blue));

        else if(subcategotyId>=2&&subcategotyId<=5)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_red));
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView,MapFragmentOSM.this.getActivity() ,point,title,contact,node,categoryId);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }
    private void drawMarkerFin(GeoPoint point,String title,String address,String contact, String node,int subcategotyId){
        Marker marker= new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        if(subcategotyId>=1&&subcategotyId<=4)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_blue));
        else if(subcategotyId>=5&&subcategotyId<=8)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_red));
        else if(subcategotyId>=20&&subcategotyId<=21)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_pink));
        else if(subcategotyId>=9&&subcategotyId<=12)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_green));
        else if(subcategotyId>=13&&subcategotyId<=15)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_brown));
        else if(subcategotyId>=16&&subcategotyId<=18)
            marker.setIcon( this.getResources().getDrawable(R.drawable.pin_feroza));
   
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView,MapFragmentOSM.this.getActivity() ,point,title,contact,node,categoryId);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }

    @Override
    public void onClick(View v) {

    }

}

