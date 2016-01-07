package demo.kolorob.kolorobdemoversion.helpers;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Education.Entertainment;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by Yeakub Hassan Rafi on 26-Dec-15.
 */
public class MapInfoWindowAdapter implements InfoWindowAdapter {

    private final View myContentsView ;
    private static LayoutInflater inflater=null;
    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;
    ImageView close;
    private int categoryId;
    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.
    private ArrayList<EducationServiceProviderItem> educationServiceProvider;
    private ArrayList<EntertainmentServiceProviderItem>entertainmentServiceProvider;



    /********Override the MapInfoWindowAdapter method for Education with getting Education Item object***********/

    public MapInfoWindowAdapter(final Context context,int cat_id,ArrayList<EducationServiceProviderItem> et){
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        educationServiceProvider = et;//setting the entertainment object

        categoryId=cat_id;
        myContentsView = inflater.inflate(R.layout.small_info, null);
        myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        close = (ImageView) myContentsView.findViewById(R.id.iv_close);
        itemName = (TextView) myContentsView.findViewById(R.id.tv_heading);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }

    /********Override the MapInfoWindowAdapter method for Health with getting Health Item object***********/



    /********Override the MapInfoWindowAdapter method for Entertainment with getting Entertainment Item object***********/

    public MapInfoWindowAdapter(final Context context,ArrayList<EntertainmentServiceProviderItem> et,int cat_id){
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        entertainmentServiceProvider = et;//setting the education object

        categoryId=cat_id;
        myContentsView = inflater.inflate(R.layout.small_info, null);
        myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        close = (ImageView) myContentsView.findViewById(R.id.iv_close);
        itemName = (TextView) myContentsView.findViewById(R.id.tv_heading);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }

    /********Override the MapInfoWindowAdapter method for Government with getting Government Item object***********/



    /********Override the MapInfoWindowAdapter method for Legal with getting Legal Item object***********/



    /********Override the MapInfoWindowAdapter method for Financial with getting Financial Item object***********/



    /********Override the MapInfoWindowAdapter method for Job with getting Job Item object***********/



    @Override
    public View getInfoContents(Marker marker) {
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
                        itemName.setText(et.getEduNameEng());
                        itemAddress.setText("ঠিকানা ঃ  "+AppConstants.BAUNIABADH);
                        itemType.setText("ধরন ঃ  "+et.getEduType());
                        itemContact.setText("যোগাযোগের উপায় ঃ  " + et.getContactNo());
                    }
                }
                break;
            case AppConstants.HEALTH:
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
                        itemName.setText(et.getNodeNameBn());
                        itemAddress.setText("ঠিকানা ঃ  "+AppConstants.BAUNIABADH);
                        itemType.setText("ধরন ঃ  "+et.getDataName());
                        itemContact.setText("যোগাযোগের উপায় ঃ  " + et.getNodeContact());
                    }
                }

                //TODO write necessary codes for entertainment
                break;
            case AppConstants.GOVERNMENT:
                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:
                //TODO write necessary codes for legal
                break;
            case AppConstants.FINANCIAL:
                //TODO write necessary codes for financial
                break;
            case AppConstants.JOB:
                //TODO write necessary codes for job
                break;
            default:
                break;
        }
        return myContentsView;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }

}
