package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.RatingTable;
import demo.kolorob.kolorobdemoversion.helpers.MyInfoWindow;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.RatingModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by israt.jahan on 5/5/2016.
 * Modified By Arafat

 */
public class MapFragmentOSM extends Fragment implements View.OnClickListener, MapEventsReceiver {
    Drawable newMarker;
    Marker marker;
    org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay mylocation;
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

    private ArrayList<HealthServiceProviderItemNew> healthServiceProvider = null;
    IMapController mapViewController;
    private ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProvider = null;
    private ArrayList<LegalAidServiceProviderItemNew> legalaidServiceProvider = null;
    private ArrayList<JobServiceProviderItem> jobServiceProvider = null;
    private ArrayList<FinancialNewItem> financialServiceProvider = null;
    private ArrayList<EducationNewItem> educationServiceProvider = null;
    private ArrayList<GovernmentNewItem> governmentNewItems = null;
    MapView mapView,mapp;
    private int categoryId;
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    ArrayList<RatingModel>rating=new ArrayList<>();
    public ArrayList<GovernmentNewItem> getGovernmentNewItems() {
        return governmentNewItems;
    }

    public void setGovernmentNewItems(ArrayList<GovernmentNewItem> governmentNewItems) {
        this.governmentNewItems = governmentNewItems;
    }

    ArrayAdapter arrayAdapter;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public IMapController getMapViewController() {
        return mapViewController;
    }

    public void setMapViewController(IMapController mapViewController) {
        this.mapViewController = mapViewController;
    }

    public void setEntertainmentServiceProvider(ArrayList<EntertainmentServiceProviderItemNew> et) {
        this.entertainmentServiceProvider = et;
    }

    public void setLegalaidServiceProvider(ArrayList<LegalAidServiceProviderItemNew> et) {
        this.legalaidServiceProvider = et;
    }

    public void setFinancialServiceProvider(ArrayList<FinancialNewItem> et) {
        this.financialServiceProvider = et;
    }

    public void setEducationServiceProvider(ArrayList<EducationNewItem> et) {


        educationServiceProvider = et;
    }

    Date today = Calendar.getInstance().getTime();
    int subcategotyId;
    String subcategotyId2;
    View rootView;
    ArrayList<OverlayItem> anotherOverlayItemArray, anotherOverlayItemArrayfinal, anotherOverlayItemArray2, anotherOverlayItemArray3, anotherOverlayItemArray4, anotherOverlayItemArray7, anotherOverlayItemArray8, anotherOverlayItemArray5, anotherOverlayItemArray6;

    public MapFragmentOSM() {

    }

    LocationManager locationManager;
    ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay, anotherItemizedIconOverlay2, anotherItemizedIconOverlay7, anotherItemizedIconOverlay8, anotherItemizedIconOverlay3, anotherItemizedIconOverlay4, anotherItemizedIconOverlay5, anotherItemizedIconOverlay6;
    ArrayList<ItemizedIconOverlay> overlayholder = null;

    public ArrayList<HealthServiceProviderItemNew> getHealthServiceProvider() {
        return healthServiceProvider;
    }
    String ratingavg;
    public void setHealthServiceProvider(ArrayList<HealthServiceProviderItemNew> et) {
        this.healthServiceProvider = et;
    }
    SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        double latDouble, longDouble;
        int i = 0;

        super.onCreate(savedInstanceState);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
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


        mapView.setTileSource(TileSourceFactory.MAPNIK);
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
        mapViewController= mapView.getController();


        if (locationNameId == 1) {

            mapViewController.setZoom(16);
            mapViewController.setCenter(AppConstants.BAUNIA1);
        } else if (locationNameId == 2) {
            mapViewController.setZoom(16);
            mapViewController.setCenter(AppConstants.PARIS1);
        }

        RatingTable ratingTable=new RatingTable(MapFragmentOSM.this.getActivity());
        switch (categoryId) {
            case AppConstants.EDUCATION:
                if (educationServiceProvider != null) {
                    for (EducationNewItem et : educationServiceProvider) {
                        //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        subcategotyId2 = et.getRefnumm();
                        latDouble = Double.parseDouble(et.getLat());

                     ratingavg =  ratingTable.getavg(et.getEduId());
                     if(ratingavg==null)
                     {
                         ratingavg="পাওয়া যায় নি";

                     }
                        else {


                        String ratingdate=ratingTable.getdate(et.getEduId());
                        String current_date=simpleDateFormat.format(today);

                     }
                        longDouble = Double.parseDouble(et.getLon());
                      GeoPoint point = new GeoPoint(latDouble, longDouble);
                        drawMarkerEdu(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getEduId(),subcategotyId2);
                    }
                }
                break;
            case AppConstants.HEALTH:
                for (HealthServiceProviderItemNew et : healthServiceProvider) {
                    //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                    String subcategotyId = et.getCategory();
                    //Log.d("subcategotyId_Legal","=======");
                    ratingavg =  ratingTable.getavg(Integer.parseInt(et.getId()));
                    if(ratingavg==null)
                    {
                        ratingavg="পাওয়া যায় নি";

                    }
                    else {


                        String ratingdate=ratingTable.getdate(Integer.parseInt(et.getId()));
                        String current_date=simpleDateFormat.format(today);

                    }
                    latDouble = Double.parseDouble(et.getLat());
                    longDouble = Double.parseDouble(et.getLon());
                    GeoPoint point = new GeoPoint(latDouble, longDouble);
                    drawMarkerHealth(point, et.getNode_bn(), ratingavg, et.getNode_contact(), et.getId(), subcategotyId);
                }
                break;
            case AppConstants.ENTERTAINMENT:
                for (EntertainmentServiceProviderItemNew et : entertainmentServiceProvider) {
                    //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                     String subcategotyId = et.getCategoryId();
                    latDouble = Double.parseDouble(et.getLatitude());
                    longDouble = Double.parseDouble(et.getLongitude());
                    ratingavg =  ratingTable.getavg(Integer.parseInt(et.getNodeId()));
                    if(ratingavg==null)
                    {
                        ratingavg="পাওয়া যায় নি";

                    }
                    else {


                        String ratingdate=ratingTable.getdate(Integer.parseInt(et.getNodeId()));
                        String current_date=simpleDateFormat.format(today);

                    }
                    GeoPoint point = new GeoPoint(latDouble, longDouble);
                    drawMarkerEnt(point, et.getNodeName(), ratingavg, et.getNodeContact(), et.getNodeId(), subcategotyId);
                }
                break;
            case AppConstants.GOVERNMENT:
                if (governmentNewItems != null) {
                    for (GovernmentNewItem et : governmentNewItems) {
                        //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                        subcategotyId2 = et.getRefnumm();
                        latDouble = Double.parseDouble(et.getLat());
                        longDouble = Double.parseDouble(et.getLon());
                        ratingavg =  ratingTable.getavg(et.getFinId());
                        if(ratingavg==null)
                        {
                            ratingavg="পাওয়া যায় নি";

                        }
                        else {


                            String ratingdate=ratingTable.getdate(et.getFinId());
                            String current_date=simpleDateFormat.format(today);

                        }
                        GeoPoint point = new GeoPoint(latDouble, longDouble);
                        drawMarkerGov(point, et.getNameen(), ratingavg, et.getNode_contact(), et.getFinId(),subcategotyId2);
                    }
                }
                break;
            case AppConstants.LEGAL:
                for (LegalAidServiceProviderItemNew et : legalaidServiceProvider) {
                    //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                    String subcategotyId = et.getCategoryId();

                    latDouble = Double.parseDouble(et.getLatitude());
                    longDouble = Double.parseDouble(et.getLongitude());
                    ratingavg =  ratingTable.getavg(Integer.parseInt(et.getIdentifierId()));
                    if(ratingavg==null)
                    {
                        ratingavg="পাওয়া যায় নি";

                    }
                    else {


                        String ratingdate=ratingTable.getdate(Integer.parseInt(et.getIdentifierId()));
                        String current_date=simpleDateFormat.format(today);

                    }
                    GeoPoint point = new GeoPoint(latDouble, longDouble);
                    drawMarkerLeg(point, et.getLegalaidNameBan(), ratingavg, et.getContactNo(), et.getIdentifierId(), subcategotyId);
                }
                break;
            case AppConstants.FINANCIAL:
                for (FinancialNewItem et : financialServiceProvider) {
                    //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                    subcategotyId2 = et.getRefnumm();
                    latDouble = Double.parseDouble(et.getLat());
                    longDouble = Double.parseDouble(et.getLon());
                    ratingavg =  ratingTable.getavg(et.getFinId());
                    if(ratingavg==null)
                    {
                        ratingavg="পাওয়া যায় নি";

                    }
                    else {


                        String ratingdate=ratingTable.getdate(et.getFinId());
                        String current_date=simpleDateFormat.format(today);

                    }
                    GeoPoint point = new GeoPoint(latDouble, longDouble);
                    drawMarkerFin(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getFinId(), subcategotyId2);
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


        ImageButton curButton=(ImageButton) rootView.findViewById(R.id.currlocation);
      /*  curButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mylocation = new MyLocationNewOverlay(mapView);
                mylocation.enableMyLocation();

                IMyLocationProvider s= mylocation.getMyLocationProvider();


                mylocation.getMyLocation();
                mapView.getOverlays().add(mylocation);
            }
        });*/



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

    private void drawMarkerEdu(GeoPoint point, String title, String add, String contact, int node, String subcategotyId2) {

        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);

        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
    for(int i=0;i<tokens.length;i++) {
        if (tokens[i]=="")continue;
        subcategotyId=Integer.parseInt(tokens[i]);

        if (subcategotyId ==179 ||subcategotyId ==163 ||subcategotyId ==161 ||subcategotyId ==123 ||subcategotyId ==145
        ||subcategotyId ==108 ||subcategotyId ==100 ||subcategotyId ==79 ||subcategotyId ==50 ||subcategotyId ==49
        ||subcategotyId ==44 ||subcategotyId ==43)
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
        else if (subcategotyId ==147 ||subcategotyId ==146 ||subcategotyId ==62 ||subcategotyId ==59|| subcategotyId ==52)
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
        else if (subcategotyId ==164 ||subcategotyId ==93)
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
        else if (subcategotyId ==150 ||subcategotyId ==149)
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
        else if (subcategotyId ==128 ||subcategotyId ==94 ||subcategotyId ==64 ||subcategotyId ==63 ||subcategotyId ==7
        ||subcategotyId ==165|| subcategotyId ==104||subcategotyId ==91||subcategotyId ==32||subcategotyId ==26
        ||subcategotyId ==180 ||subcategotyId ==78||subcategotyId ==77)
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));

        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,add);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
        //marker.setTitle("Title of the marker");
    }
    }
    private void drawMarkerGov(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2) {

        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);

        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        for(int i=0;i<tokens.length;i++) {
            if (tokens[i]=="")continue;
            subcategotyId=Integer.parseInt(tokens[i]);

            if (subcategotyId ==170 ||subcategotyId ==143 ||subcategotyId ==135 ||subcategotyId ==117 ||subcategotyId ==48
                    ||subcategotyId ==47 ||subcategotyId ==151 ||subcategotyId ==80 ||subcategotyId ==70 ||subcategotyId ==69
                    ||subcategotyId ==44 ||subcategotyId ==43)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            else if (subcategotyId ==172 ||subcategotyId ==171 ||subcategotyId ==120 ||subcategotyId ==119
                    ||subcategotyId ==118 ||subcategotyId ==105 ||subcategotyId ==103 ||subcategotyId ==56 ||subcategotyId ==41
                    ||subcategotyId ==40 )
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            else if (subcategotyId ==99 ||subcategotyId ==98 )
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
            else if (subcategotyId ==122)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
            else if (subcategotyId ==35)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
            else if (subcategotyId ==174)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));

            InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
            marker.setInfoWindow(infoWindow);

            mapView.getOverlays().add(marker);
            //marker.setTitle("Title of the marker");
        }
    }
    private void drawMarkerHealth(GeoPoint point, String title, String address, String contact, String node, String subcategotyId2) {

        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        Log.d("HealthSub","======="+String.valueOf(subcategotyId2));
        String subcategory;
        subcategory=subcategotyId2.substring(1);
        String CurrentString = subcategory;
        String[] separated = CurrentString.split(",");



     //   Log.d("node","========="+node);
        for (int i=0;i<separated.length;i++)
        {
            subcategotyId= Integer.parseInt(separated[i]);
            if (subcategotyId == 2)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            else if (subcategotyId == 9)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            else if (subcategotyId == 89)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
            else if (subcategotyId == 25)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
            else if (subcategotyId == 20)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
            else if (subcategotyId == 133)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));

            InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
            marker.setInfoWindow(infoWindow);

            mapView.getOverlays().add(marker);
        }




    }

    private void drawMarkerLeg(GeoPoint point, String title, String address, String contact, String node, String subcategotyId2) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Log.d("LegSub","======="+String.valueOf(subcategotyId2));

        String subcategory;
        subcategory=subcategotyId2.substring(1);
        String CurrentString = subcategory;
        String[] separated = CurrentString.split(",");
        for (int i=0;i<separated.length;i++)
        {
            subcategotyId= Integer.parseInt(separated[i]);
            if (subcategotyId == 30)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));

            else if (subcategotyId ==36)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
            marker.setInfoWindow(infoWindow);

            mapView.getOverlays().add(marker);
        }



    }
    private void drawMarkerEnt(GeoPoint point, String title, String address, String contact, String node, String subcategotyId23) {


        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Log.d("subcategotyId23","******" +subcategotyId23);
        String subcategory;
        subcategory=subcategotyId23.substring(1);
        String CurrentString = subcategory;
        String[] separated = CurrentString.split(",");
        for (int i=0;i<separated.length;i++) {
            subcategotyId= Integer.parseInt(separated[i]);
            if (subcategotyId == 129)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            else if (subcategotyId == 131)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            else if (subcategotyId == 106)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
            else if (subcategotyId == 15)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
            else if (subcategotyId == 18)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
            else if (subcategotyId == 112)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
            else if (subcategotyId == 23)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_7));
            else if (subcategotyId == 27)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_8));
        }

        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }



    private void drawMarkerFin(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2) {
        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);

        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        for(int i=0;i<tokens.length;i++) {
            if (tokens[i]=="")continue;
            subcategotyId=Integer.parseInt(tokens[i]);

            if (subcategotyId ==177||subcategotyId ==125||subcategotyId ==124||subcategotyId ==13)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            else if (subcategotyId ==157||subcategotyId ==156||subcategotyId ==154||subcategotyId ==140||subcategotyId ==67||subcategotyId ==66)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            else if (subcategotyId ==151||subcategotyId ==80||subcategotyId ==70||subcategotyId ==69)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
            else if (subcategotyId ==158||subcategotyId ==152||subcategotyId ==87)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
            else if (subcategotyId ==159||subcategotyId ==153||subcategotyId ==72)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
            else if (subcategotyId ==74)
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
            InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
            marker.setInfoWindow(infoWindow);

            mapView.getOverlays().add(marker);
            //marker.setTitle("Title of the marker");
        }
    }
public void DrawRoute(String lat, String lon)
{

    Double servicelat=Double.parseDouble(lat);
    Double servicelon=Double.parseDouble(lon);
    GeoPoint servicepoint=new GeoPoint(servicelat,servicelon);

    Double endlat=Double.parseDouble("23.791902");
    Double endlon=Double.parseDouble("90.411343");
mapp=getMapView();
   // double latt=mylocation.getMyLocation().getLatitude();

    Marker destinationmarker=new Marker(mapp);
    destinationmarker.setPosition(servicepoint);
    mapp.getOverlays().add(destinationmarker);

    GeoPoint my=new GeoPoint(endlat,endlon);

 //   double latitude = my.getLatitudeE6() / 1E6;
//
 //   double longitude = my.getLongitudeE6() / 1E6;

    //2. Playing with the RoadManager
    //RoadManager roadManager = new MapQuestRoadManager("YOUR_API_KEY");
    //roadManager.addRequestOption("routeType=bicycle");
    ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
    waypoints.add(my);
    waypoints.add(servicepoint);


    //GeoPoint endPoint = new GeoPoint(48.4, -1.9);


    RoadManager roadManager = new OSRMRoadManager(getActivity());
    Road road = roadManager.getRoad(waypoints);
    if (road.mStatus != Road.STATUS_OK)
        Toast.makeText(getActivity(), "Error when loading the road - status=" + road.mStatus, Toast.LENGTH_SHORT).show();

    Polyline roadOverlay = RoadManager.buildRoadOverlay(road, getActivity());
    mapp.getOverlays().add(roadOverlay);

    //3. Showing the Route steps on the map
    FolderOverlay roadMarkers = new FolderOverlay(getActivity());
    mapp.getOverlays().add(roadMarkers);
    Drawable nodeIcon = getResources().getDrawable(R.drawable.map_marker);
    for (int i = 0; i < road.mNodes.size(); i++) {
        RoadNode node = road.mNodes.get(i);
        Marker nodeMarker = new Marker(mapp);
        nodeMarker.setPosition(node.mLocation);
        nodeMarker.setIcon(nodeIcon);

        //4. Filling the bubbles
        nodeMarker.setTitle("Step " + i);
        nodeMarker.setSnippet(node.mInstructions);
        nodeMarker.setSubDescription(Road.getLengthDurationText(getActivity(), node.mLength, node.mDuration));
        Drawable iconContinue = getResources().getDrawable(R.drawable.map_marker);
        nodeMarker.setImage(iconContinue);
        //4. end

        roadMarkers.add(nodeMarker);
    }


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
}

