package demo.kolorob.kolorobdemoversion.fragment;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;

/**
 * Created by israt.jahan on 5/5/2016.
 */
public class MapFragmentOSM extends Fragment {
    Drawable newMarker;
    private ArrayList<EducationServiceProviderItem> educationServiceProvider=null;
MapView mapView;
    ResourceProxyImpl resProxyImp;
    MyLocationOverlay mMyLocationOverlay;
    public void setEducationServiceProvider(ArrayList<EducationServiceProviderItem> et)
    {
        educationServiceProvider=et;
    }
int subcategotyId;
    public static final GeoPoint BauniaBadh = new GeoPoint(23.8196826, 90.3806812);
    ArrayList<OverlayItem> anotherOverlayItemArray,anotherOverlayItemArray2,anotherOverlayItemArray3,anotherOverlayItemArray4,anotherOverlayItemArray5;
    public MapFragmentOSM()
    {

    }
    ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay,anotherItemizedIconOverlay2,anotherItemizedIconOverlay3,anotherItemizedIconOverlay4,anotherItemizedIconOverlay5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
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
        mapViewController.setZoom(18);
        mapViewController.setCenter(BauniaBadh);
        anotherOverlayItemArray = new ArrayList<OverlayItem>();
        anotherOverlayItemArray2 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray3 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray4 = new ArrayList<OverlayItem>();
        anotherOverlayItemArray5 = new ArrayList<OverlayItem>();
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
}
