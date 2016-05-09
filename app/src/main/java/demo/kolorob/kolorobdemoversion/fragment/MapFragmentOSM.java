package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.ResourceProxyImpl;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

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

/**
 * Created by israt.jahan on 5/5/2016.
 */
public class MapFragmentOSM extends Fragment implements View.OnClickListener {
    Drawable newMarker;
    private Spinner spItems;
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

    ResourceProxyImpl resProxyImp;
    MyLocationOverlay mMyLocationOverlay;

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
    ArrayList<OverlayItem> anotherOverlayItemArray,anotherOverlayItemArray2,anotherOverlayItemArray3,anotherOverlayItemArray4,anotherOverlayItemArray7,anotherOverlayItemArray8,anotherOverlayItemArray5,anotherOverlayItemArray6;
    public MapFragmentOSM()
    {

    }
    ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay,anotherItemizedIconOverlay2,anotherItemizedIconOverlay7,anotherItemizedIconOverlay8,anotherItemizedIconOverlay3,anotherItemizedIconOverlay4,anotherItemizedIconOverlay5,anotherItemizedIconOverlay6;

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

        MapView mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);


        resProxyImp = new
                ResourceProxyImpl(getActivity());
        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
        mMyLocationOverlay = new MyLocationOverlay(getActivity(), mapView);
        mapView.getOverlays().add(mMyLocationOverlay);
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
                    for (  EducationServiceProviderItem et : educationServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        subcategotyId=et.getEduSubCategoryId();
                        latDouble=Double.parseDouble(et.getLatitude());
                        longDouble=Double.parseDouble(et.getLongitude());


                        if(subcategotyId>=1&&subcategotyId<=12){

                            anotherOverlayItemArray.clear();
                            anotherOverlayItemArray.add(new OverlayItem(et.getEduNameEng(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_green);
                            anotherItemizedIconOverlay
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay);
                        }
                        else if(subcategotyId>=13&&subcategotyId<=17) {
                            anotherOverlayItemArray2.clear();
                            anotherOverlayItemArray2.add(new OverlayItem(et.getEduNameEng(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_brown);
                            anotherItemizedIconOverlay2
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray2,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay2);
                        }
                        else if(subcategotyId>=18&&subcategotyId<=19){
                            anotherOverlayItemArray3.clear();
                            anotherOverlayItemArray3.add(new OverlayItem(et.getEduNameEng(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_feroza);
                            anotherItemizedIconOverlay3
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray3,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay3);
                        }
                        else if(subcategotyId>=20&&subcategotyId<=21){
                            anotherOverlayItemArray4.clear();
                            newMarker = this.getResources().getDrawable(R.drawable.pin_orange);
                            anotherOverlayItemArray4.add(new OverlayItem(et.getEduNameEng(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            anotherItemizedIconOverlay4
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray4,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay4);
                        }
                        else if(subcategotyId>=22&&subcategotyId<=26){
                            anotherOverlayItemArray5.clear();
                            anotherOverlayItemArray5.add(new OverlayItem(et.getEduNameEng(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_pink);
                            anotherItemizedIconOverlay5
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray5,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay5);
                        }
                    }
                }
                break;
            case AppConstants.HEALTH:
                if(healthServiceProvider!=null) {
                    for (  HealthServiceProviderItem et : healthServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        subcategotyId=et.getRefNum();
                        latDouble=Double.parseDouble(et.getLatitude());
                        longDouble=Double.parseDouble(et.getLongitude());


                        if(subcategotyId>=1&&subcategotyId<=7){

                            anotherOverlayItemArray.clear();
                            anotherOverlayItemArray.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_green);
                            anotherItemizedIconOverlay
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay);
                        }
                        else if(subcategotyId>=8&&subcategotyId<=12) {
                            anotherOverlayItemArray2.clear();
                            anotherOverlayItemArray2.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_brown);
                            anotherItemizedIconOverlay2
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray2,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay2);
                        }
                        else if(subcategotyId>=13&&subcategotyId<=15){
                            anotherOverlayItemArray3.clear();
                            anotherOverlayItemArray3.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_feroza);
                            anotherItemizedIconOverlay3
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray3,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay3);
                        }
                        else if(subcategotyId>=16&&subcategotyId<=20){
                            anotherOverlayItemArray4.clear();
                            newMarker = this.getResources().getDrawable(R.drawable.pin_orange);
                            anotherOverlayItemArray4.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            anotherItemizedIconOverlay4
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray4,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay4);
                        }
                        else if(subcategotyId==21){
                            anotherOverlayItemArray5.clear();
                            anotherOverlayItemArray5.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_pink);
                            anotherItemizedIconOverlay5
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray5,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay5);
                        }
                        else if(subcategotyId==22){
                            anotherOverlayItemArray6.clear();
                            anotherOverlayItemArray6.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_yellow);
                            anotherItemizedIconOverlay6
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray6,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay6);
                        }
                    }
                }



                break;
            case AppConstants.ENTERTAINMENT:
                if(entertainmentServiceProvider!=null) {
                    for (  EntertainmentServiceProviderItem et : entertainmentServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        subcategotyId=et.getEntSubCategoryId();
                        latDouble=Double.parseDouble(et.getLatitude());
                        longDouble=Double.parseDouble(et.getLongitude());


                        if(subcategotyId==1){

                            anotherOverlayItemArray.clear();
                            anotherOverlayItemArray.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_green);
                            anotherItemizedIconOverlay
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay);
                        }
                        else if(subcategotyId==2||subcategotyId==5||subcategotyId==21) {
                            anotherOverlayItemArray2.clear();
                            anotherOverlayItemArray2.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_brown);
                            anotherItemizedIconOverlay2
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray2,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay2);
                        }
                        else if(subcategotyId==3){
                            anotherOverlayItemArray3.clear();
                            anotherOverlayItemArray3.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_feroza);
                            anotherItemizedIconOverlay3
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray3,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay3);
                        }
                        else if(subcategotyId==4||subcategotyId==6||subcategotyId==7||subcategotyId==8){
                            anotherOverlayItemArray4.clear();
                            newMarker = this.getResources().getDrawable(R.drawable.pin_orange);
                            anotherOverlayItemArray4.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            anotherItemizedIconOverlay4
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray4,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay4);
                        }
                        else if(subcategotyId>=9&&subcategotyId<=11){
                            anotherOverlayItemArray5.clear();
                            anotherOverlayItemArray5.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_pink);
                            anotherItemizedIconOverlay5
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray5,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay5);
                        }
                        else if(subcategotyId==12){
                            anotherOverlayItemArray6.clear();
                            anotherOverlayItemArray6.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_yellow);
                            anotherItemizedIconOverlay6
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray6,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay6);
                        }
                        else if(subcategotyId==13||subcategotyId==14||subcategotyId==16||subcategotyId==19||subcategotyId==20){
                            anotherOverlayItemArray7.clear();
                            anotherOverlayItemArray7.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_red);
                            anotherItemizedIconOverlay7
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray7,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay7);
                        }
                        else if(subcategotyId==15||subcategotyId==17||subcategotyId==18){
                            anotherOverlayItemArray8.clear();
                            anotherOverlayItemArray8.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_blue);
                            anotherItemizedIconOverlay8
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray8,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay8);
                        }
                    }
                }



                //TODO write necessary codes for entertainment
                break;
            case AppConstants.GOVERNMENT:
                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:
                if(legalaidServiceProvider!=null) {
                    for (  LegalAidServiceProviderItem et : legalaidServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        subcategotyId=et.getLegalaidSubCategoryId();
                        latDouble=Double.parseDouble(et.getLatitude());
                        longDouble=Double.parseDouble(et.getLongitude());


                        if(subcategotyId==1){

                            anotherOverlayItemArray.clear();
                            anotherOverlayItemArray.add(new OverlayItem(et.getLegalaidNameEng(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_green);
                            anotherItemizedIconOverlay
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay);
                        }
                      else if(subcategotyId>=2&&subcategotyId<=5){
                            anotherOverlayItemArray2.clear();
                            anotherOverlayItemArray2.add(new OverlayItem(et.getLegalaidNameEng(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_brown);
                            anotherItemizedIconOverlay2
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray2,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay2);
                        }

                    }
                }

                break;
            case AppConstants.FINANCIAL:
                if(financialServiceProvider!=null) {
                    for (  FinancialServiceProviderItem et : financialServiceProvider) {
                        LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        subcategotyId=et.getRefNum();
                        latDouble=Double.parseDouble(et.getLatitude());
                        longDouble=Double.parseDouble(et.getLongitude());


                        if(subcategotyId>=1&&subcategotyId<=4){

                            anotherOverlayItemArray.clear();
                            anotherOverlayItemArray.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_green);
                            anotherItemizedIconOverlay
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay);
                        }
                        else if(subcategotyId>=5&&subcategotyId<=8) {
                            anotherOverlayItemArray2.clear();
                            anotherOverlayItemArray2.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_brown);
                            anotherItemizedIconOverlay2
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray2,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay2);
                        }
                        else if(subcategotyId>=20&&subcategotyId<=21){
                            anotherOverlayItemArray3.clear();
                            anotherOverlayItemArray3.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_feroza);
                            anotherItemizedIconOverlay3
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray3,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay3);
                        }
                        else if(subcategotyId>=9&&subcategotyId<=12){
                            anotherOverlayItemArray4.clear();
                            newMarker = this.getResources().getDrawable(R.drawable.pin_orange);
                            anotherOverlayItemArray4.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            anotherItemizedIconOverlay4
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray4,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay4);
                        }
                        else if(subcategotyId>=13&&subcategotyId<=15){
                            anotherOverlayItemArray5.clear();
                            anotherOverlayItemArray5.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_pink);
                            anotherItemizedIconOverlay5
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray5,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay5);
                        }
                        else if(subcategotyId>=16&&subcategotyId<=18){
                            anotherOverlayItemArray6.clear();
                            anotherOverlayItemArray6.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_yellow);
                            anotherItemizedIconOverlay6
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray6,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay6);
                        }
                        else if(subcategotyId==19){
                            anotherOverlayItemArray7.clear();
                            anotherOverlayItemArray7.add(new OverlayItem(et.getNodeName(),et.getArea(),new GeoPoint(latDouble,longDouble)));
                            newMarker = this.getResources().getDrawable(R.drawable.pin_red);
                            anotherItemizedIconOverlay7
                                    = new ItemizedIconOverlay<OverlayItem>(
                                    anotherOverlayItemArray7,newMarker, myOnItemGestureListener,resProxyImp);
                            mapView.getOverlays().add(anotherItemizedIconOverlay7);
                        }
                    }
                }

                break;
            case AppConstants.JOB:

                break;
            default:
                break;
        }








        //---

        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(getActivity());
        mapView.getOverlays().add(myScaleBarOverlay);


        return rootView;
    }
    private void drawMarkerEdu(LatLng point,String title,int subcategotyId){


    }

    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener
            = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){

        @Override
        public boolean onItemLongPress(int arg0, OverlayItem arg1) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            Toast.makeText(getActivity(),
                    item.getTitle() + "\n"
                            + item.getSnippet()+ "\n"
                            + item.getPoint().getLatitudeE6() + " : " + item.getPoint().getLongitudeE6(),
                    Toast.LENGTH_LONG).show();
            return true;
        }

    };


    @Override
    public void onClick(View v) {

    }
}
