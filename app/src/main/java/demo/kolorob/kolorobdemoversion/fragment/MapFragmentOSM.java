package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.helpers.MyInfoWindow;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.model.RatingModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

/**
 * Created by israt.jahan on 5/5/2016.
 */
public class MapFragmentOSM extends Fragment implements View.OnClickListener, MapEventsReceiver {


    List<String> listData = new ArrayList<String>();
    String concatened;
    private String locationName;
    private int locationNameId;
    private static double VIEW_WIDTH;
    private int primaryIconWidth;
    boolean firstRun;
    String refid2;
    private ArrayList<HealthNewDBModelMain> healthServiceProvider = null;
    IMapController mapViewController;
    private ArrayList<EntertainmentNewDBModel> entertainmentServiceProvider = null;
    private ArrayList<LegalAidNewDBModel> legalaidServiceProvider = null;
    private ArrayList<JobServiceProviderItem> jobServiceProvider = null;
    private ArrayList<FinancialNewDBModel> financialServiceProvider = null;
    private ArrayList<EduNewModel> educationServiceProvider = null;
    private ArrayList<GovernmentNewDBModel> governmentNewItems = null;
    private ArrayList<NGONewDBModel> ngoServiceProvider = null;
    private ArrayList<ReligiousNewDBModel> religiousServiceProvider = null;
    private ArrayList<AllHolder> allitems = null;
    MapView mapView;
    String datevalue, datevaluebn;
    private int categoryId;
    String first;
    private Animation mEnterAnimation, mExitAnimation;
    ArrayList<SubCategoryItemNew> subCategoryItemNews = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    String lat, lon;
    double latDouble, longDouble;
    int i = 0;
    Date today = Calendar.getInstance().getTime();
    int subcategotyId;
    String subcategotyId2;
    View rootView;
    LocationManager locationManager;
    StringBuilder result;

    String ratingavg, ratingavgbn, refid, service_type, religion, services;







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
        if (locationNameId == AppConstants.PLACE_BAUNIABADH) {
            listData.add(AppConstants.BAUNIABADH);
            listData.add(AppConstants.PARIS_ROAD);
            listData.add(AppConstants.MIRPUR_TWELVE);
        } else if (locationNameId == AppConstants.PLACE_PARIS_ROAD) {
            listData.add(AppConstants.PARIS_ROAD);
            listData.add(AppConstants.BAUNIABADH);
            listData.add(AppConstants.MIRPUR_TWELVE);
        } else {
            listData.add(AppConstants.MIRPUR_TWELVE);
            listData.add(AppConstants.PARIS_ROAD);
            listData.add(AppConstants.BAUNIABADH);
        }
    }

    public ArrayList<GovernmentNewDBModel> getGovernmentNewItems() {
        return governmentNewItems;
    }


    public void setGovernmentNewItems(ArrayList<GovernmentNewDBModel> governmentNewItems) {
        this.governmentNewItems = governmentNewItems;
    }


    /*public String getLOCATIONFROMMAP() {
        return LOCATIONFROMMAP;
    }

    public void setLOCATIONFROMMAP(String LOCATIONFROMMAP) {
        this.LOCATIONFROMMAP = LOCATIONFROMMAP;
    }*/

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

    public void setEntertainmentServiceProvider(ArrayList<EntertainmentNewDBModel> et) {
        this.entertainmentServiceProvider = et;
    }

    public void setLegalaidServiceProvider(ArrayList<LegalAidNewDBModel> et) {
        this.legalaidServiceProvider = et;
    }

    public void setNgoServiceProvider(ArrayList<NGONewDBModel> et) {
        this.ngoServiceProvider = et;
    }

    public void setReligiousServiceProvider(ArrayList<ReligiousNewDBModel> et) {
        this.religiousServiceProvider = et;
    }


    byte[] bytes;

    public void setFinancialServiceProvider(ArrayList<FinancialNewDBModel> et) {
        this.financialServiceProvider = et;
    }

    public void setEducationServiceProvider(ArrayList<EduNewModel> et) {


        educationServiceProvider = et;
    }


    public MapFragmentOSM() {

    }


    public void setHealthServiceProvider(ArrayList<HealthNewDBModelMain> et) {
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
            //OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);

            mapView.setUseDataConnection(true);
            OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
            mapView.setTileSource(TileSourceFactory.MAPNIK);

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
        mapViewController = mapView.getController();
        mapViewController.setZoom(15);
        //String[] partlocation = getLOCATIONFROMMAP().split(":");
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
        subCategoryItemNews = subCategoryTableNew.getAllSubCategories(categoryId);


        return rootView;
    }

    public void Setsubcategories(int id) {
        SubCategoryTableNew subCategoryTableNew = new SubCategoryTableNew(getActivity());
        subCategoryItemNews = subCategoryTableNew.getAllSubCat();
        subCategoryItemNews = subCategoryTableNew.getAllSubCategories(id);

    }

    public void eduicons() {

        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();

        if (educationServiceProvider != null) {


            for (EduNewModel et : educationServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                subcategotyId2 = et.getSubcat();
                latDouble = Double.parseDouble(et.getLat());
                ratingavg = et.getRatings();
                refid = et.getRefnumm();
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
                }catch (StringIndexOutOfBoundsException  e)
                {
                    refid2 = "পাওয়া যায় নি";
                }

                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";

                } else {
                    ratingavgbn = EtoBconversion(ratingavg);

                    ratingavg = ratingavgbn.concat(datevalue);
                }
                longDouble = Double.parseDouble(et.getLon());
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerEdu(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getEduId(), subcategotyId2, refid2);
            }
        }


    }

    public void healthicons() {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();
        if (healthServiceProvider != null) {

            for (HealthNewDBModelMain et : healthServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                String subcategotyId = et.getSubcat();
                //Log.d("subcategotyId_Legal","=======");

                ratingavg = et.getRatings();
                refid = et.getRefnumm();
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
                }catch (StringIndexOutOfBoundsException  e)
                {
                    refid2 = "পাওয়া যায় নি";
                }


                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";
                } else {
                    ratingavgbn = EtoBconversion(ratingavg);

                    ratingavg = ratingavgbn.concat(datevalue);
                }
                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerHealth(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getHealthid(), subcategotyId, refid2);
            }
        }


    }

    public void enticons() {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();
        if (entertainmentServiceProvider != null) {


            for (EntertainmentNewDBModel et : entertainmentServiceProvider) {
                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                String subcategotyId = et.getSubcat();
                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                ratingavg = et.getRatings();
                refid = et.getRefnumm();
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
                }catch (StringIndexOutOfBoundsException  e)
                {
                   refid2 = "পাওয়া যায় নি";
                }

                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";

                } else {
                    ratingavgbn = EtoBconversion(ratingavg);

                    ratingavg = ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerEnt(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getEntid(), subcategotyId, refid2);
            }
        }


    }

    //for government
    public void govicons() {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();
        if (governmentNewItems != null) {

            for (GovernmentNewDBModel et : governmentNewItems) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                subcategotyId2 = et.getSubcat();
                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                ratingavg = et.getRatings();
                refid = et.getRefnumm();
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
                }catch (StringIndexOutOfBoundsException  e)
                {
                    refid2 = "পাওয়া যায় নি";
                }
                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";

                } else {
                    ratingavgbn = EtoBconversion(ratingavg);

                    ratingavg = ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerGov(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getGovid(), subcategotyId2, refid2);
            }
        }


    }

    //method for legal
    public void legicons() {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();

        if (legalaidServiceProvider != null) {

            for (LegalAidNewDBModel et : legalaidServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                String subcategotyId = et.getSubcat();

                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                ratingavg = et.getRatings();
                refid = et.getRefnumm();
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
                }catch (StringIndexOutOfBoundsException  e)
                {
                    refid2 = "পাওয়া যায় নি";
                }
                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";

                } else {
                    ratingavgbn = EtoBconversion(ratingavg);

                    ratingavg = ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerLeg(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getLegalid(), subcategotyId, refid2);
            }
        }


    }

    /////// NGO ////

    public void ngoicons() {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();

        if (ngoServiceProvider != null) {

            for (NGONewDBModel et : ngoServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                String subcategotyId = et.getSubcat();
                Log.e("Sub Cat NGO", subcategotyId);
                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                ratingavg = et.getRatings();
                refid = et.getRefnumm();
                service_type = et.getNgo_service_type();
                services = et.getNgo_services();
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
                } catch (StringIndexOutOfBoundsException e) {
                    refid2 = "পাওয়া যায় নি";
                }
                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";

                } else {
                    ratingavgbn = EtoBconversion(ratingavg);

                    ratingavg = ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerNGO(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getNgoid(), subcategotyId, refid2);
            }
        }


    }

    ///// NGO end ///


    ///////  Religious ////////
    public void religiousicons() {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();

        if (religiousServiceProvider != null) {

            for (ReligiousNewDBModel et : religiousServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                String subcategotyId = et.getSubcat();
                Log.e("Sub Cat Religious", subcategotyId);
                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                ratingavg = et.getRatings();
                refid = et.getRefnumm();
                religion = et.getRs_religion();
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
                } catch (StringIndexOutOfBoundsException e) {
                    refid2 = "পাওয়া যায় নি";
                }
                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";

                } else {
                    ratingavgbn = EtoBconversion(ratingavg);

                    ratingavg = ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerReligious(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getReligousid(), subcategotyId, refid2, et.getRs_religion());
            }
        }


    }

    //////// Religious ////////



    //for financial
    public void finicons() {
        mapView.removeAllViewsInLayout();
        mapView.getOverlays().clear();
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        mapView.getOverlays().add(0, mapEventsOverlay);
        mapView.invalidate();
        if (financialServiceProvider != null) {

            for (FinancialNewDBModel et : financialServiceProvider) {

                //    LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                subcategotyId2 = et.getSubcat();
                latDouble = Double.parseDouble(et.getLat());
                longDouble = Double.parseDouble(et.getLon());
                ratingavg = et.getRatings();
                refid = et.getRefnumm();
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
                }catch (StringIndexOutOfBoundsException  e)
                {
                    refid2 = "পাওয়া যায় নি";
                }
                if ((ratingavg.equals("null")) || (ratingavg.equals(""))) {
                    ratingavg = "পাওয়া যায় নি";

                } else {
                    ratingavgbn = EtoBconversion(ratingavg);

                    ratingavg = ratingavgbn.concat(datevalue);
                }
                GeoPoint point = new GeoPoint(latDouble, longDouble);
                drawMarkerFin(point, et.getNamebn(), ratingavg, et.getNode_contact(), et.getFinid(), subcategotyId2, refid2);
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

    private void drawMarkerEdu(GeoPoint point, String title, String add, String contact, int node, String subcategotyId2, String ref) {
        Marker marker=new Marker(mapView);

        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);


        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);


        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == "") continue;
            subcategotyId = Integer.parseInt(tokens[i]);

            if (subcategotyId == 10100 || subcategotyId == 10200) {

                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            } else if (subcategotyId == 10300) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            } else if (subcategotyId == 10400) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
            } else if (subcategotyId == 10500) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
            } else if (subcategotyId == 19900) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));

            }
        }
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, add, ref);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);


    }

    private void drawMarkerGov(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2, String ref) {

        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);
        Marker marker =new Marker(mapView);
        marker.setPosition(point);


        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == "") continue;
            subcategotyId = Integer.parseInt(tokens[i]);

            if (subcategotyId == 40100) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            } else if (subcategotyId == 40200) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            } else if (subcategotyId == 40500) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_8));
            } else if (subcategotyId == 40300) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
            } else if (subcategotyId == 49900) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
            }


            //marker.setTitle("Title of the marker");
        }
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, address, ref);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }

    private void drawMarkerHealth(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2, String ref) {

        Marker marker =new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);


        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == "") continue;
            subcategotyId = Integer.parseInt(tokens[i]);
            if (subcategotyId == 20100)

            {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            } else if (subcategotyId == 20200) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            } else if (subcategotyId == 20500) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
            } else if (subcategotyId == 20400) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
            } else if (subcategotyId == 29900) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
            }


        }

        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, address, ref);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);


    }

    private void drawMarkerLeg(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2, String ref) {
        Marker marker =new Marker(mapView);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setPosition(point);
        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == "") continue;
            subcategotyId = Integer.parseInt(tokens[i]);
            if (subcategotyId == 50100) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            } else if (subcategotyId == 50200) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            } else if (subcategotyId == 59900) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
            }
        }

        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, address, ref);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);

    }

    private void drawMarkerEnt(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2, String ref) {

        Marker marker =new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);


        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == "") continue;
            subcategotyId = Integer.parseInt(tokens[i]);
            if (subcategotyId == 30100) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            } else if (subcategotyId == 30200) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            } else if (subcategotyId == 30300) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
            } else if (subcategotyId == 30400) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
            } else if (subcategotyId == 39900) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
            }

        }


        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, address, ref);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);

    }


    private void drawMarkerFin(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2, String ref) {
        Marker marker =new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        ;
        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);


        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == "") continue;
            subcategotyId = Integer.parseInt(tokens[i]);

            if (subcategotyId == 60300) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
            } else if (subcategotyId == 60400) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
            } else if (subcategotyId == 60500) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
            } else if (subcategotyId == 60100) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
            } else if (subcategotyId == 60200) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
            } else if (subcategotyId == 69900) {
                marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
            }


            //marker.setTitle("Title of the marker");
        }
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, address, ref);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }

    private void drawMarkerNGO(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2, String ref) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);


        /*for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == "") continue;
            subcategotyId = Integer.parseInt(tokens[i]);*/
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


        //marker.setTitle("Title of the marker");
        //}
        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, address, ref, service_type, services);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }


    ////// religious////
    private void drawMarkerReligious(GeoPoint point, String title, String address, String contact, int node, String subcategotyId2, String ref, String rs_religion) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        /*String delims = "[,]";
        String[] tokens = subcategotyId2.split(delims);




        if (Arrays.asList(tokens).contains("80100")) {
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_2));
        } else if (Arrays.asList(tokens).contains("80200")) {
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_4));
        } else if (Arrays.asList(tokens).contains("80300")) {
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_5));
        } else if (Arrays.asList(tokens).contains("80400")) {
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_1));
        } else if (Arrays.asList(tokens).contains("80500")) {
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_3));
        } else if (Arrays.asList(tokens).contains("80900")) {
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_6));
        }
        else{
            marker.setIcon(this.getResources().getDrawable(R.drawable.pin_map_8));
        }

        */

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




        InfoWindow infoWindow = new MyInfoWindow(religion, R.layout.bonuspack_bubble_black, mapView, MapFragmentOSM.this.getActivity(), point, title, contact, node, categoryId, address, ref);
        marker.setInfoWindow(infoWindow);

        mapView.getOverlays().add(marker);
    }

    /////// religious end ////





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

