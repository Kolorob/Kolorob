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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.helpers.MyInfoWindow;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;


/**
 * Created by israt.jahan on 5/5/2016.
 */
public class MapFragmentOSM <ModelType extends CommonModel> extends Fragment implements View.OnClickListener, MapEventsReceiver {


    List<String> listData = new ArrayList<String>();
    String concatened;
    private String locationName;
    private int locationNameId;


    boolean firstRun;
    String refid2;

    IMapController mapViewController;

    private ArrayList<AllHolder> allitems = null;
    MapView mapView;
    String datevalue, datevaluebn;
    private int categoryId;
    String first;
    private Animation mEnterAnimation, mExitAnimation;
    ArrayList<SubCategoryItemNew> subCategoryItemNews = new ArrayList<>();

    String lat, lon;
    View rootView;
    LocationManager locationManager;
    StringBuilder result;



    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getLocationNameId() {
        return locationNameId;
    }

    public void setLocationNameId(int locationNameId) {
        this.locationNameId = locationNameId;
    }




    public List<String> getListData() {
        return listData;
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

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



    public MapFragmentOSM() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());


        super.onCreate(savedInstanceState);
        SharedPreferences settings = MapFragmentOSM.this.getActivity().getSharedPreferences("prefs", 0);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        rootView = inflater.inflate(R.layout.fragment_map, container,
                false);


        mapView = (MapView) rootView.findViewById(R.id.mapview);
        setMapView(mapView);
        mEnterAnimation = new AlphaAnimation(0f, 1f);
        mEnterAnimation.setDuration(600);
        mEnterAnimation.setFillAfter(true);

        mExitAnimation = new AlphaAnimation(1f, 0f);
        mExitAnimation.setDuration(600);
        mExitAnimation.setFillAfter(true);
        mapView.setClickable(true);
        concatened = "";

        mapView.setBuiltInZoomControls(false);
        mapView.setMultiTouchControls(true);


        mapView.setTilesScaledToDpi(true);
        firstRun = settings.getBoolean("firstRunUp", false);
        if (firstRun == false)//if running for first time
        {
            Log.d("ss", "********" + first);

            mapView.setUseDataConnection(true);
            OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);

            mapView.setTileSource(TileSourceFactory.MAPNIK);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRunUp", true);
            editor.apply();



        } else {

            mapView.setUseDataConnection(true);
            OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
            mapView.setTileSource(TileSourceFactory.MAPNIK);


        }

        mapViewController = mapView.getController();
        mapViewController.setZoom(15);
      
        Log.e("", "Lat: " + getLat() +" " + "Lon: " + getLon());
        mapViewController.setCenter(new GeoPoint(Float.parseFloat(getLat()), Float.parseFloat(getLon())));
        result = new StringBuilder();

        // get the time and make a date out of it
        Date date2 = new Date(settings.getLong("time", 0));
        Date today = new Date();
        long diffInMillisec = today.getTime() - date2.getTime();

        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        if (diffInDays == 0) datevalue = " (আজকের তথ্য)";
        else {
            datevaluebn = EtoBconversion(String.valueOf(diffInDays));
            datevalue = " ( " + datevaluebn + " দিন আগের তথ্য)";
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

        SubCategoryTableNew subCategoryTableNew = new SubCategoryTableNew(getActivity());
        subCategoryItemNews = subCategoryTableNew.getDataListFromForeignKey(categoryId);


        return rootView;
    }

    public void Setsubcategories(int id) {
        SubCategoryTableNew subCategoryTableNew = new SubCategoryTableNew(getActivity());
        subCategoryItemNews = subCategoryTableNew.getDataListFromForeignKey(id);

    }

    public void populateIcons(ArrayList <ModelType> list) {

        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();

        if (list != null) {

            for (CommonModel cm : list) {

                double latDouble = Double.parseDouble(cm.getLat());
                double longDouble = Double.parseDouble(cm.getLon());
                String ratingavg = cm.getRatings();
                String refid = cm.getRefNum();
                String refid2;

                result.delete(0, result.length());

                String[] references = refid.split(",");

                for (int k = 0; k < references.length; k++) {
                    for (int i = 0; i < subCategoryItemNews.size(); i++) {
                        int value = subCategoryItemNews.get(i).getRefId();
                        if (value == Integer.parseInt(references[k])) {
                            result.append(subCategoryItemNews.get(i).getRefLabelBn());
                            result.append(",");
                        }
                    }
                }

                try {
                    result.setLength(result.length() - 1);
                    refid2 = String.valueOf(result);
                }
                catch (StringIndexOutOfBoundsException  e) {
                    refid2 = "পাওয়া যায় নি";
                }

                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";

                } else {
                    ratingavg = EtoBconversion(ratingavg).concat(datevalue);
                }

                GeoPoint point = new GeoPoint(latDouble, longDouble);
                String rs_religion = (cm instanceof ReligiousNewDBModel) ? ((ReligiousNewDBModel) cm).getRsReligion() : null;
                drawMarker(point, cm.getNameBn(), ratingavg, cm.getNodeContact(), cm.getId(), cm.getSubcat(), refid2, rs_religion);
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

    private void drawMarker(GeoPoint point, String title, String add, String contact, int node, String subcategotyId2, String ref, String rs_religion) {
        Marker marker = new Marker(mapView);

        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);


        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);


        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == "") continue;

            if(tokens[i].charAt(0) == '8'){
                switch(rs_religion){
                    case "ইসলাম": marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                        break;
                    case "হিন্দু": marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
                        break;
                    case "খ্রিস্টান": marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                        break;
                    case "বৌদ্ধ": marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
                        break;
                    case "জৈন": marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
                        break;
                    default: marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_8));
                        break;

                }
            }
            else if(tokens[i].charAt(0) == '7'){
                if (Arrays.asList(tokens).contains("70100")){
                    marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                } else if (Arrays.asList(tokens).contains("70200")) {
                    marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
                } else if (Arrays.asList(tokens).contains("70300")) {
                    marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
                } else if (Arrays.asList(tokens).contains("70400")) {
                    marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                } else if (Arrays.asList(tokens).contains("70500")) {
                    marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
                } else if (Arrays.asList(tokens).contains("70900")) {
                    marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
                }
                else{
                    marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_8));
                }

            }

            else{

                switch(tokens[i].charAt(2)) {
                    case '1':
                        marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
                        break;
                    case '2':
                        marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
                        break;
                    case '3':
                        marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
                        break;
                    case '4':
                        marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
                        break;
                    case '5':
                        marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
                        break;
                    case '6':
                        marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_8));
                        break;
                    case '7':
                        marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_7));
                        break;

                    default:
                        marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
                }

            }
        }
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, add, ref);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);


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

