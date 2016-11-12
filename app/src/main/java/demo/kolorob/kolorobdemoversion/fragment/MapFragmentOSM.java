package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.BuildConfig;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
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
 */
public class MapFragmentOSM extends Fragment implements View.OnClickListener, MapEventsReceiver {


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
    boolean firstRun;
    public int getLocationNameId() {
        return locationNameId;
    }

    public void setLocationNameId(int locationNameId) {
        this.locationNameId = locationNameId;
        if (locationNameId == AppConstants.PLACE_BAUNIABADH) {
            listData.add(AppConstants.BAUNIABADH);
            listData.add(AppConstants.PARIS_ROAD);
            listData.add(AppConstants.MIRPUR_TWELVE);
        } else if (locationNameId == AppConstants.PLACE_PARIS_ROAD)  {
            listData.add(AppConstants.PARIS_ROAD);
            listData.add(AppConstants.BAUNIABADH);
            listData.add(AppConstants.MIRPUR_TWELVE);
        }
        else {
            listData.add(AppConstants.MIRPUR_TWELVE);
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
    private  ArrayList<AllHolder>allitems=null;
    MapView mapView,mapp;
    String datevalue,datevaluebn;
    private int categoryId;
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    String first;
    final ArrayList<Marker> items = new ArrayList<Marker>();
    final ArrayList<Marker> items1 = new ArrayList<Marker>();
    final ArrayList<Marker> items2 = new ArrayList<Marker>();
    final ArrayList<Marker> items3 = new ArrayList<Marker>();
    final ArrayList<Marker> items4 = new ArrayList<Marker>();
    final ArrayList<Marker> items5 = new ArrayList<Marker>();
    final ArrayList<Marker> items6 = new ArrayList<Marker>();
    final ArrayList<Marker> items7 = new ArrayList<Marker>();
    final ArrayList<Marker> items8 = new ArrayList<Marker>();
    ArrayList<RatingModel>rating=new ArrayList<>();
    public ArrayList<GovernmentNewItem> getGovernmentNewItems() {
        return governmentNewItems;
    }

    public void setGovernmentNewItems(ArrayList<GovernmentNewItem> governmentNewItems) {
        this.governmentNewItems = governmentNewItems;
    }

    ArrayAdapter arrayAdapter;

    public ArrayList<AllHolder> getAllitems() {
        return allitems;
    }

    public void setAllitems(ArrayList<AllHolder> allitems) {
        this.allitems = allitems;
    }

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
    byte[] bytes;
    public void setFinancialServiceProvider(ArrayList<FinancialNewItem> et) {
        this.financialServiceProvider = et;
    }

    public void setEducationServiceProvider(ArrayList<EducationNewItem> et) {


        educationServiceProvider = et;
    }
    double latDouble, longDouble;
    int i = 0;
    Date today = Calendar.getInstance().getTime();
    int subcategotyId;
    String subcategotyId2;
    View rootView;

    public MapFragmentOSM() {

    }

    LocationManager locationManager;

    public ArrayList<HealthServiceProviderItemNew> getHealthServiceProvider() {
        return healthServiceProvider;
    }
    String ratingavg,ratingavgbn;
    public void setHealthServiceProvider(ArrayList<HealthServiceProviderItemNew> et) {
        this.healthServiceProvider = et;
    }
    SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());


        super.onCreate(savedInstanceState);
        SharedPreferences settings = MapFragmentOSM.this.getActivity().getSharedPreferences("prefs", 0);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        rootView = inflater.inflate(R.layout.fragment_map, container,
                false);
        VIEW_WIDTH = AppUtils.getScreenWidth(getActivity()) * AppConstants.CAT_LIST_LG_WIDTH_PERC;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.80);
        mapView = (MapView) rootView.findViewById(R.id.mapview);
        setMapView(mapView);

        mapView.setClickable(true);


        mapView.setBuiltInZoomControls(false);
        mapView.setMultiTouchControls(true);




        mapView.setTilesScaledToDpi(true);
        firstRun = settings.getBoolean("firstRun", false);
        if (firstRun==false)//if running for first time
        {   Log.d("ss","********"+first);
           /* File path = MapFragmentOSM.this.getActivity().getExternalFilesDir(null);

            File file = new File(path, "kolorob.txt");

            try
            {
                int length = (int) file.length();

                bytes = new byte[length];

                FileInputStream in = null;
                try {
                    in = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    try { if(in!=null){
                        int b=in.read(bytes);}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } finally {
                    try {
                       if(in!=null) in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                String contents = new String(bytes);
                String delims = "[,]";
                String[] tokens = contents.split(delims);
                String text= tokens[0]+",no";

                FileOutputStream fOut = new FileOutputStream(file);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                myOutWriter.write("");
                myOutWriter.append(text);
                myOutWriter.close();
                fOut.close();
            } catch(Exception e)
            {

            }
*/
            mapView.setUseDataConnection(true);
            OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
            mapView.setTileSource(TileSourceFactory.MAPNIK);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", true);
            editor.apply();
        }
        else {
            //OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
            mapView.setUseDataConnection(false);
            //mapView.setTileSource(TileSourceFactory.MAPNIK);
        }
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
        else if (locationNameId == 3) {
            mapViewController.setZoom(16);
            mapViewController.setCenter(AppConstants.TWELVE);
        }


// get the time and make a date out of it
        Date date2 = new Date(settings.getLong("time", 0));
        Date today=new Date();
        long diffInMillisec = today.getTime() - date2.getTime();

        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        if (diffInDays==0) datevalue=" (আজকের তথ্য)";
        else
        {
            datevaluebn=EtoBconversion(String.valueOf(diffInDays));
            datevalue=" ( "+ datevaluebn + " দিন আগের তথ্য)";
        }




        //---

        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(mapView);
        mapView.getOverlays().add(myScaleBarOverlay);


      /*   ImageButton curButton=(ImageButton) rootView.findViewById(R.id.currlocation);
       curButton.setOnClickListener(new View.OnClickListener() {
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

public void eduicons()
{
   mapView.removeAllViewsInLayout();
mapView.getOverlays().clear();
    MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
    mapView.getOverlays().add(0, mapEventsOverlay);
    mapView.invalidate();
    items.clear();
    items1.clear();
    items2.clear();
    items3.clear();
    items4.clear();
    items5.clear();
    if (educationServiceProvider != null) {


        for (EducationNewItem et : educationServiceProvider) {

            //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
            subcategotyId2 = et.getRefnumm();
            latDouble = Double.parseDouble(et.getLat());
            ratingavg=et.getRating();


            if((ratingavg.equals("null"))||(ratingavg.equals("")))
            {
                ratingavg="পাওয়া যায় নি";

            }
            else  {
                ratingavgbn=EtoBconversion(ratingavg);

                ratingavg=ratingavgbn.concat(datevalue);
            }
            longDouble = Double.parseDouble(et.getLon());
            GeoPoint point = new GeoPoint(latDouble, longDouble);
            drawMarkerEdu(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getEduId(),subcategotyId2);
        }
    }


}
    public void healthicons()
    {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();

        mapView.invalidate();
        items.clear();
        items1.clear();
        items2.clear();
        items3.clear();
        items4.clear();
        items5.clear();
        items6.clear();
        if (healthServiceProvider != null) {

            for (HealthServiceProviderItemNew et : healthServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                String subcategotyId = et.getReferences();
                //Log.d("subcategotyId_Legal","=======");

                ratingavg=et.getRating();




                if((ratingavg.equals("null"))||(ratingavg.equals("")))
                {
                    ratingavg="পাওয়া যায় নি";
                }
                else  {
                    ratingavgbn=EtoBconversion(ratingavg);

                    ratingavg=ratingavgbn.concat(datevalue);
                }
                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerHealth(point, et.getNode_bn(), ratingavg, et.getNode_contact(), et.getId(), subcategotyId);
            }
        }


    }

    public void enticons()
    {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();
        items.clear();
        items1.clear();
        items2.clear();
        items3.clear();
        items4.clear();
        items5.clear();
        items6.clear();
        items7.clear();
        items8.clear();

        if (entertainmentServiceProvider != null) {


            for (EntertainmentServiceProviderItemNew et : entertainmentServiceProvider) {
                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                String subcategotyId = et.getNodeAdditional();
                latDouble = Double.parseDouble(et.getLatitude());
                longDouble = Double.parseDouble(et.getLongitude());
                ratingavg=et.getRating();


                if((ratingavg.equals("null"))||(ratingavg.equals("")))
                {
                    ratingavg="পাওয়া যায় নি";

                }
                else  {
                    ratingavgbn=EtoBconversion(ratingavg);

                    ratingavg=ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerEnt(point, et.getNodeNameBn(), ratingavg, et.getNodeContact(), et.getNodeId(), subcategotyId);
            }
        }


    }
//for government
public void govicons()
{
    mapView.removeAllViewsInLayout();
    mapView.getOverlays().clear();

    mapView.invalidate();
    items.clear();
    items1.clear();
    items2.clear();
    items3.clear();
    items4.clear();
    items5.clear();
    items6.clear();
    if (governmentNewItems != null) {

        for (GovernmentNewItem et : governmentNewItems) {

            //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
            subcategotyId2 = et.getRefnumm();
            latDouble = Double.parseDouble(et.getLat());
            longDouble = Double.parseDouble(et.getLon());
            ratingavg=et.getRating();

            if((ratingavg.equals("null"))||(ratingavg.equals("")))
            {
                ratingavg="পাওয়া যায় নি";

            }
            else  {
                ratingavgbn=EtoBconversion(ratingavg);

                ratingavg=ratingavgbn.concat(datevalue);
            }
            GeoPoint point = new GeoPoint(latDouble, longDouble);
            drawMarkerGov(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getFinId(),subcategotyId2);
        }
    }


}

    //method for legal
    public void legicons()
    {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();

        mapView.invalidate();
        items.clear();
        items1.clear();
        items2.clear();

        if (legalaidServiceProvider != null) {

            for (LegalAidServiceProviderItemNew et : legalaidServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                String subcategotyId = et.getCategoryId();

                latDouble = Double.parseDouble(et.getLatitude());
                longDouble = Double.parseDouble(et.getLongitude());
                ratingavg=et.getRating();


                if((ratingavg.equals("null"))||(ratingavg.equals("")))
                {
                    ratingavg="পাওয়া যায় নি";

                }
                else  {
                    ratingavgbn=EtoBconversion(ratingavg);

                    ratingavg=ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerLeg(point, et.getLegalaidNameBan(), ratingavg, et.getContactNo(), et.getIdentifierId(), subcategotyId);
            }
        }


    }

    //for financial
    public void finicons()
    {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();

        mapView.invalidate();
        items.clear();
        items1.clear();
        items2.clear();
        items3.clear();
        items4.clear();
        items5.clear();
        items6.clear();
        if (financialServiceProvider != null) {

            for (FinancialNewItem et : financialServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                subcategotyId2 = et.getRefnumm();
                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                ratingavg=et.getRating();

                if((ratingavg.equals("null"))||(ratingavg.equals("")))
                {
                    ratingavg="পাওয়া যায় নি";

                }
                else  {
                    ratingavgbn=EtoBconversion(ratingavg);

                    ratingavg=ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerFin(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getFinId(), subcategotyId2);
            }
        }


    }
    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {

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
        Marker marker1 = new Marker(mapView);
        Marker marker2 = new Marker(mapView);
        Marker marker3 = new Marker(mapView);
        Marker marker4 = new Marker(mapView);
        Marker marker5 = new Marker(mapView);


        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker4.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker5.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().clear();

        for(int i=0;i<tokens.length;i++) {
            if (tokens[i] == "") continue;
            subcategotyId = Integer.parseInt(tokens[i]);

            if (subcategotyId == 179 || subcategotyId == 163 || subcategotyId == 161 || subcategotyId == 123 || subcategotyId == 145
                    || subcategotyId == 108 || subcategotyId == 100 || subcategotyId == 79 || subcategotyId == 50 || subcategotyId == 49
                    || subcategotyId == 44 || subcategotyId == 43) {

                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, add);
                marker1.setInfoWindow(infoWindow);
                marker1.setPosition(point);
                marker1.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                items1.add(marker1);
                items.add(marker1);
            } else if (subcategotyId == 147 || subcategotyId == 146 || subcategotyId == 62 || subcategotyId == 59 || subcategotyId == 52) {
                marker2.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                marker2.setPosition(point);
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, add);
                marker2.setInfoWindow(infoWindow);
                items2.add(marker2);
                items.add(marker2);
            } else if (subcategotyId == 164 || subcategotyId == 93) {
                marker3.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
                marker3.setPosition(point);
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, add);
                marker3.setInfoWindow(infoWindow);
                items3.add(marker3);
                items.add(marker3);
            } else if (subcategotyId == 150 || subcategotyId == 149) {
                marker4.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
                marker4.setPosition(point);
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, add);
                marker4.setInfoWindow(infoWindow);
                items4.add(marker4);
                items.add(marker4);
            } else if (subcategotyId == 128 || subcategotyId == 94 || subcategotyId == 64 || subcategotyId == 63 || subcategotyId == 7
                    || subcategotyId == 165 || subcategotyId == 104 || subcategotyId == 91 || subcategotyId == 32 || subcategotyId == 26
                    || subcategotyId == 180 || subcategotyId == 78 || subcategotyId == 77) {
                marker5.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
                marker5.setPosition(point);
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, add);
                marker5.setInfoWindow(infoWindow);
                items5.add(marker5);
                items.add(marker5);
            }
        }



    }

    public void Drawedu(int edid,boolean state)
    {

        mapView.invalidate();
        if(edid==-1) {
            for (Marker m : items) {


                mapView.getOverlays().add(m);

            }
        }
        if (state==false)  {
            if (edid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().remove(m);


                }
            } else if (edid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().remove(m);

                }
            } else if (edid == 2) {
                for (Marker m : items3) {


                    mapView.getOverlays().remove(m);

                }
            } else if (edid == 3) {
                for (Marker m : items4) {


                    mapView.getOverlays().remove(m);

                }
            } else if (edid == 4) {
                for (Marker m : items5) {


                    mapView.getOverlays().remove(m);

                }
            }
        }
        else {
            if (edid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().add(m);


                }
            } else if (edid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().add(m);

                }
            } else if (edid == 2) {
                for (Marker m : items3) {


                    mapView.getOverlays().add(m);

                }
            } else if (edid == 3) {
                for (Marker m : items4) {


                    mapView.getOverlays().add(m);

                }
            } else if (edid == 4) {
                for (Marker m : items5) {


                    mapView.getOverlays().add(m);

                }
            }
        }

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
    }
//for health
    public void Drawhel(int helid,boolean state)
    {

        mapView.invalidate();
        if(helid==-1) {
            for (Marker m : items) {


                mapView.getOverlays().add(m);

            }
        }
        if (state==false)  {
            if (helid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().remove(m);


                }
            } else if (helid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 2) {
                for (Marker m : items3) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 3) {
                for (Marker m : items4) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 4) {
                for (Marker m : items5) {


                    mapView.getOverlays().remove(m);

                }
            }
            else if (helid ==5 ) {
                for (Marker m : items6) {


                    mapView.getOverlays().remove(m);

                }
            }
        }
        else {
            if (helid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().add(m);


                }
            } else if (helid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 2) {
                for (Marker m : items3) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 3) {
                for (Marker m : items4) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 4) {
                for (Marker m : items5) {


                    mapView.getOverlays().add(m);

                }
            }
                else if (helid ==5 ) {
                    for (Marker m : items6) {


                        mapView.getOverlays().add(m);

                    }
            }
        }

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
    }
//for entertainment
public void Drawent(int edid,boolean state)
{

    mapView.invalidate();
    if(edid==-1) {
        for (Marker m : items) {


            mapView.getOverlays().add(m);

        }
    }
    if (state==false)  {
        if (edid == 0) {
            for (Marker m : items1) {


                mapView.getOverlays().remove(m);


            }
        } else if (edid == 1) {
            for (Marker m : items2) {


                mapView.getOverlays().remove(m);

            }
        } else if (edid == 2) {
            for (Marker m : items3) {


                mapView.getOverlays().remove(m);

            }
        } else if (edid == 3) {
            for (Marker m : items4) {


                mapView.getOverlays().remove(m);

            }
        } else if (edid == 4) {
            for (Marker m : items5) {


                mapView.getOverlays().remove(m);

            }
        }
        else if (edid == 5) {
            for (Marker m : items6) {


                mapView.getOverlays().remove(m);

            }
        }
        else if (edid == 6) {
            for (Marker m : items7) {


                mapView.getOverlays().remove(m);

            }
        }
        else if (edid == 7) {
            for (Marker m : items8) {


                mapView.getOverlays().remove(m);

            }
        }
    }
    else {
        if (edid == 0) {
            for (Marker m : items1) {


                mapView.getOverlays().add(m);


            }
        } else if (edid == 1) {
            for (Marker m : items2) {


                mapView.getOverlays().add(m);

            }
        } else if (edid == 2) {
            for (Marker m : items3) {


                mapView.getOverlays().add(m);

            }
        } else if (edid == 3) {
            for (Marker m : items4) {


                mapView.getOverlays().add(m);

            }
        } else if (edid == 4) {
            for (Marker m : items5) {


                mapView.getOverlays().add(m);

            }
        }
        else if (edid == 5) {
            for (Marker m : items6) {


                mapView.getOverlays().add(m);

            }
        }
        else if (edid == 6) {
            for (Marker m : items7) {


                mapView.getOverlays().add(m);

            }
        }
        else if (edid == 7) {
            for (Marker m : items8) {


                mapView.getOverlays().add(m);

            }
        }
    }

    MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
    mapView.getOverlays().add(0, mapEventsOverlay);
}


    //for government
    public void Drawgov(int helid,boolean state)
    {

        mapView.invalidate();
        if(helid==-1) {
            for (Marker m : items) {


                mapView.getOverlays().add(m);

            }
        }
        if (state==false)  {
            if (helid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().remove(m);


                }
            } else if (helid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 2) {
                for (Marker m : items3) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 3) {
                for (Marker m : items4) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 4) {
                for (Marker m : items5) {


                    mapView.getOverlays().remove(m);

                }
            }
            else if (helid ==5 ) {
                for (Marker m : items6) {


                    mapView.getOverlays().remove(m);

                }
            }
        }
        else {
            if (helid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().add(m);


                }
            } else if (helid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 2) {
                for (Marker m : items3) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 3) {
                for (Marker m : items4) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 4) {
                for (Marker m : items5) {


                    mapView.getOverlays().add(m);

                }
            }
            else if (helid ==5 ) {
                for (Marker m : items6) {


                    mapView.getOverlays().add(m);

                }
            }
        }

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
    }

    //method for legal
    public void Drawleg(int helid,boolean state)
    {

        mapView.invalidate();
        if(helid==-1) {
            for (Marker m : items) {


                mapView.getOverlays().add(m);

            }
        }
        if (state==false)  {
            if (helid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().remove(m);


                }
            } else if (helid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().remove(m);

                }
            }
        }
        else {
            if (helid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().add(m);


                }
            } else if (helid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().add(m);

                }
            }
        }

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
    }

    //method for financial
    public void Drawfin(int helid,boolean state)
    {

        mapView.invalidate();
        if(helid==-1) {
            for (Marker m : items) {


                mapView.getOverlays().add(m);

            }
        }
        if (state==false)  {
            if (helid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().remove(m);


                }
            } else if (helid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 2) {
                for (Marker m : items3) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 3) {
                for (Marker m : items4) {


                    mapView.getOverlays().remove(m);

                }
            } else if (helid == 4) {
                for (Marker m : items5) {


                    mapView.getOverlays().remove(m);

                }
            }
            else if (helid ==5 ) {
                for (Marker m : items6) {


                    mapView.getOverlays().remove(m);

                }
            }
        }
        else {
            if (helid == 0) {
                for (Marker m : items1) {


                    mapView.getOverlays().add(m);


                }
            } else if (helid == 1) {
                for (Marker m : items2) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 2) {
                for (Marker m : items3) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 3) {
                for (Marker m : items4) {


                    mapView.getOverlays().add(m);

                }
            } else if (helid == 4) {
                for (Marker m : items5) {


                    mapView.getOverlays().add(m);

                }
            }
            else if (helid ==5 ) {
                for (Marker m : items6) {


                    mapView.getOverlays().add(m);

                }
            }
        }

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
    }
    private void drawMarkerGov(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2) {

        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);
mapView.getOverlays().clear();
        Marker marker1 = new Marker(mapView);
        Marker marker2 = new Marker(mapView);
        Marker marker3 = new Marker(mapView);
        Marker marker4 = new Marker(mapView);
        Marker marker5 = new Marker(mapView);
        Marker marker6 = new Marker(mapView);
        mapView.getOverlays().clear();
        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker4.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker5.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker6.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        for(int i=0;i<tokens.length;i++) {
            if (tokens[i]=="")continue;
            subcategotyId=Integer.parseInt(tokens[i]);

            if (subcategotyId ==170 ||subcategotyId ==143 ||subcategotyId ==135 ||subcategotyId ==117 ||subcategotyId ==48
                    ||subcategotyId ==47 ||subcategotyId ==151 ||subcategotyId ==80 ||subcategotyId ==70 ||subcategotyId ==69
                    ||subcategotyId ==44 ||subcategotyId ==43)
            {
                marker1.setPosition(point);
                marker1.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker1.setInfoWindow(infoWindow);
                items1.add(marker1);
                items.add(marker1);
            }
            else if (subcategotyId ==172 ||subcategotyId ==171 ||subcategotyId ==120 ||subcategotyId ==119
                    ||subcategotyId ==118 ||subcategotyId ==105 ||subcategotyId ==103 ||subcategotyId ==56 ||subcategotyId ==41
                    ||subcategotyId ==40 )
            {
                marker2.setPosition(point);
                marker2.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker2.setInfoWindow(infoWindow);
                items2.add(marker2);
                items.add(marker2);
            }
            else if (subcategotyId ==99 ||subcategotyId ==98 )
            {
                marker3.setPosition(point);
                marker3.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker3.setInfoWindow(infoWindow);
                items3.add(marker3);
                items.add(marker3);
            }
            else if (subcategotyId ==122)
            {
                marker4.setPosition(point);
                marker4.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker4.setInfoWindow(infoWindow);
                items4.add(marker4);
                items.add(marker4);
            }
            else if (subcategotyId ==35)
            {
                marker5.setPosition(point);
                marker5.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker5.setInfoWindow(infoWindow);
                items5.add(marker5);
                items.add(marker5);
            }
            else if (subcategotyId ==174)
            {
                marker6.setPosition(point);
                marker6.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker6.setInfoWindow(infoWindow);
                items6.add(marker6);
                items.add(marker6);
            }



            //marker.setTitle("Title of the marker");
        }
    }
    private void drawMarkerHealth(GeoPoint point, String title, String address, String contact, String node, String subcategotyId2) {

        Marker marker1 = new Marker(mapView);
        Marker marker2 = new Marker(mapView);
        Marker marker3 = new Marker(mapView);
        Marker marker4 = new Marker(mapView);
        Marker marker5 = new Marker(mapView);
        Marker marker6 = new Marker(mapView);
        mapView.getOverlays().clear();
        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker4.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker5.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker6.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        String subcategory;
        subcategory=subcategotyId2.substring(1);
        String CurrentString = subcategory;
        String[] separated = CurrentString.split(",");



        for (int i=0;i<separated.length;i++)
        {
            subcategotyId= Integer.parseInt(separated[i]);
            if (subcategotyId == 3||subcategotyId == 4||subcategotyId == 8||subcategotyId == 84||subcategotyId == 95||subcategotyId == 96||subcategotyId == 162)

            {
                marker1.setPosition(point);
                marker1.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker1.setInfoWindow(infoWindow);
                items1.add(marker1);
                items.add(marker1);
            }
            else if (subcategotyId == 81||subcategotyId == 88||subcategotyId == 109||subcategotyId == 10||subcategotyId == 127)
            {
                marker2.setPosition(point);
                marker2.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker2.setInfoWindow(infoWindow);
                items2.add(marker2);
                items.add(marker2);
            }
            else if (subcategotyId == 90||subcategotyId == 142||subcategotyId == 176)
            {
                marker3.setPosition(point);
                marker3.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker3.setInfoWindow(infoWindow);
                items3.add(marker3);
                items.add(marker3);
            }
            else if (subcategotyId == 26||subcategotyId == 32||subcategotyId == 91||subcategotyId == 104||subcategotyId == 165)
            {
                marker4.setPosition(point);
                marker4.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker4.setInfoWindow(infoWindow);
                items4.add(marker4);
                items.add(marker4);
            }
            else if (subcategotyId == 134)
            {
                marker5.setPosition(point);
                marker5.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker5.setInfoWindow(infoWindow);
                items5.add(marker5);
                items.add(marker5);
            }
            else if (subcategotyId == 21)
            {
                marker6.setPosition(point);
                marker6.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker6.setInfoWindow(infoWindow);
                items6.add(marker6);
                items.add(marker6);
            }


        }




    }

    private void drawMarkerLeg(GeoPoint point, String title, String address, String contact, String node, String subcategotyId2) {
        Marker marker1 = new Marker(mapView);
        Marker marker2 = new Marker(mapView);
        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().clear();
        String subcategory;
        subcategory=subcategotyId2.substring(1);
        String CurrentString = subcategory;
        String[] separated = CurrentString.split(",");
        for (int i=0;i<separated.length;i++)
        {
            subcategotyId= Integer.parseInt(separated[i]);
            if (subcategotyId == 30)
            {
                marker1.setPosition(point);
                marker1.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker1.setInfoWindow(infoWindow);
                items1.add(marker1);
                items.add(marker1);
            }

            else if (subcategotyId ==36)
            {
                marker2.setPosition(point);
                marker2.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker2.setInfoWindow(infoWindow);
                items2.add(marker2);
                items.add(marker2);
            }

        }



    }
    private void drawMarkerEnt(GeoPoint point, String title, String address, String contact, String node, String subcategotyId23) {


        Marker marker1 = new Marker(mapView);
        Marker marker2 = new Marker(mapView);
        Marker marker3 = new Marker(mapView);
        Marker marker4 = new Marker(mapView);
        Marker marker5 = new Marker(mapView);
        Marker marker6 = new Marker(mapView);
        Marker marker7 = new Marker(mapView);
        Marker marker8 = new Marker(mapView);
        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker4.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker5.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker6.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker7.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker8.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
mapView.getOverlays().clear();
        String subcategory;
        subcategory=subcategotyId23.substring(1);
        String CurrentString = subcategory;
        String[] separated = CurrentString.split(",");
        for (int i=0;i<separated.length;i++) {
            subcategotyId= Integer.parseInt(separated[i]);
            if (subcategotyId == 130) {
                marker1.setPosition(point);
                marker1.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker1.setInfoWindow(infoWindow);
                items1.add(marker1);
                items.add(marker1);
            }
            else if (subcategotyId == 19||subcategotyId == 22||subcategotyId == 114)
            {
                marker2.setPosition(point);
                marker2.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker2.setInfoWindow(infoWindow);
                items2.add(marker2);
                items.add(marker2);
            }
            else if (subcategotyId == 113)
            {
                marker3.setPosition(point);
                marker3.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker3.setInfoWindow(infoWindow);
                items3.add(marker3);
                items.add(marker3);
            }
            else if (subcategotyId == 24||subcategotyId == 111||subcategotyId == 115||subcategotyId == 116||subcategotyId == 141)
            {
                marker4.setPosition(point);
                marker4.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker4.setInfoWindow(infoWindow);
                items4.add(marker4);
                items.add(marker4);
            }
            else if (subcategotyId == 28||subcategotyId == 45||subcategotyId == 168)
            {
                marker5.setPosition(point);
                marker5.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker5.setInfoWindow(infoWindow);
                items5.add(marker5);
                items.add(marker5);
            }
            else if (subcategotyId == 132||subcategotyId == 166||subcategotyId == 169)
            {
                marker6.setPosition(point);
                marker6.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker6.setInfoWindow(infoWindow);
                items6.add(marker6);
                items.add(marker6);
            }
            else if (subcategotyId == 107)
            {
                marker7.setPosition(point);
                marker7.setIcon(this.getResources().getDrawable(R.drawable.pin_map_7));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker7.setInfoWindow(infoWindow);
                items7.add(marker7);
                items.add(marker7);
            }
            else if (subcategotyId == 16||subcategotyId == 17||subcategotyId == 110||subcategotyId == 167)

            {
                marker8.setPosition(point);
                marker8.setIcon(this.getResources().getDrawable(R.drawable.pin_map_8));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker8.setInfoWindow(infoWindow);
                items8.add(marker8);
                items.add(marker8);
            }
        }




    }



    private void drawMarkerFin(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2) {
        Marker marker1 = new Marker(mapView);
        Marker marker2 = new Marker(mapView);
        Marker marker3 = new Marker(mapView);
        Marker marker4 = new Marker(mapView);
        Marker marker5 = new Marker(mapView);
        Marker marker6 = new Marker(mapView);
        mapView.getOverlays().clear();
        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker4.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker5.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker6.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);


        for(int i=0;i<tokens.length;i++) {
            if (tokens[i]=="")continue;
            subcategotyId=Integer.parseInt(tokens[i]);

            if (subcategotyId ==177||subcategotyId ==125||subcategotyId ==124||subcategotyId ==13)
            {
                marker1.setPosition(point);
                marker1.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker1.setInfoWindow(infoWindow);
                items1.add(marker1);
                items.add(marker1);
            }


            else if (subcategotyId ==151||subcategotyId ==80||subcategotyId ==70||subcategotyId ==69)
            {
                marker2.setPosition(point);
                marker2.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker2.setInfoWindow(infoWindow);
                items2.add(marker2);
                items.add(marker2);
            }
            else if (subcategotyId ==87||subcategotyId ==152||subcategotyId ==158)
            {
                marker3.setPosition(point);
                marker3.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker3.setInfoWindow(infoWindow);
                items3.add(marker3);
                items.add(marker3);
            }

            else if (subcategotyId ==159||subcategotyId ==153||subcategotyId ==72)
            {
                marker4.setPosition(point);
                marker4.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker4.setInfoWindow(infoWindow);
                items4.add(marker4);
                items.add(marker4);
            }
            else if (subcategotyId ==174)
            {
                marker5.setPosition(point);
                marker5.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker5.setInfoWindow(infoWindow);
                items5.add(marker5);
                items.add(marker5);
            }
            else if (subcategotyId == 66||subcategotyId == 67||subcategotyId == 140||subcategotyId == 154||subcategotyId == 156||subcategotyId == 157)
            {
                marker6.setPosition(point);
                marker6.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
                InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId,address);
                marker6.setInfoWindow(infoWindow);
                items6.add(marker6);
                items.add(marker6);
            }



            //marker.setTitle("Title of the marker");
        }
    }


    @Override
    public void onClick(View v) {

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


    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
}
