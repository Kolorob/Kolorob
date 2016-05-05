package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by israt.jahan on 5/5/2016.
 */
public class MapFragmentOSM extends Fragment {
    private ArrayList<EducationServiceProviderItem> educationServiceProvider=null;
MapView mapView;
    MyLocationOverlay mMyLocationOverlay;
    public void setEducationServiceProvider(ArrayList<EducationServiceProviderItem> et)
    {
        educationServiceProvider=et;
    }

    public static final GeoPoint BauniaBadh = new GeoPoint(23.8196826, 90.3806812);
    ArrayList<OverlayItem> anotherOverlayItemArray;
    public MapFragmentOSM()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_map, container,
                false);
        MapView mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);
        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
        mMyLocationOverlay = new MyLocationOverlay(getActivity(), mapView);
        mapView.getOverlays().add(mMyLocationOverlay);
        IMapController mapViewController = mapView.getController();
        mapViewController.setZoom(18);
        mapViewController.setCenter(BauniaBadh);
        anotherOverlayItemArray = new ArrayList<OverlayItem>();

        if(educationServiceProvider!=null) {
            Log.d("---edu name ","....subcategory..");
            for (EducationServiceProviderItem et : educationServiceProvider) {
                LatLng location = new LatLng(Double.parseDouble(et.getLatitude()), Double.parseDouble(et.getLongitude()));
                Log.d("---edu name "+location,"....subcategory..");
                Log.d("---edu name "+et.getEduNameEng(),"....subcategory.."+et.getEduSubCategoryId());
                drawMarkerEdu(location, et.getEduNameEng(), et.getEduSubCategoryId());
            }
        }

        anotherOverlayItemArray.add(new OverlayItem(
                "0, 0", "0, 0", new GeoPoint(23.821387, 90.377895)));
        anotherOverlayItemArray.add(new OverlayItem(
                "US", "US", new GeoPoint(23.822849, 90.379322)));
        anotherOverlayItemArray.add(new OverlayItem(
                "China", "China", new GeoPoint(23.821397, 90.381575)));
        anotherOverlayItemArray.add(new OverlayItem(
                "United Kingdom", "United Kingdom", new GeoPoint(23.818894, 90.380899)));
        anotherOverlayItemArray.add(new OverlayItem(
                "Germany", "Germany", new GeoPoint(52.516667, 13.383333)));
        anotherOverlayItemArray.add(new OverlayItem(
                "Korea", "Korea", new GeoPoint(38.316667, 127.233333)));
        anotherOverlayItemArray.add(new OverlayItem(
                "India", "India", new GeoPoint(28.613333, 77.208333)));
        anotherOverlayItemArray.add(new OverlayItem(
                "Russia", "Russia", new GeoPoint(55.75, 37.616667)));
        anotherOverlayItemArray.add(new OverlayItem(
                "France", "France", new GeoPoint(48.856667, 2.350833)));
        anotherOverlayItemArray.add(new OverlayItem(
                "Canada", "Canada", new GeoPoint(45.4, -75.666667)));

        ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay;
        anotherItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(
        getActivity(), anotherOverlayItemArray, null);
        mapView.getOverlays().add(anotherItemizedIconOverlay);
        //---

        //Add Scale Bar
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(getActivity());
        mapView.getOverlays().add(myScaleBarOverlay);


        return rootView;
    }
    private void drawMarkerEdu(LatLng point,String title,int subcategotyId){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);

        if(subcategotyId>=1&&subcategotyId<=12)

        {
            Drawable drawable = this.getResources().getDrawable(
                    R.drawable.advertising_icon);
        }
//        else if(subcategotyId>=13&&subcategotyId<=17)
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[2]));
//        else if(subcategotyId>=18&&subcategotyId<=19)
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[3]));
//        else if(subcategotyId>=20&&subcategotyId<=21)
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[4]));
//        else if(subcategotyId>=22&&subcategotyId<=26)
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(AppConstants.MARKER_HUE_COLOR[5]));
        //mapView.getOverlays().add(markerOptions);
    }


}
