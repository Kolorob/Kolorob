package demo.kolorob.kolorobdemoversion.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.PlaceChoiceActivity;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class MapInfoWindowAdapter implements InfoWindowAdapter {

    private final View myContentsView ;
    private static LayoutInflater inflater=null;
    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;
    ImageView close,img1;
    private int height;
    private int categoryId;
    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.
    private ArrayList<EducationServiceProviderItem> educationServiceProvider;
    private ArrayList<EntertainmentServiceProviderItem>entertainmentServiceProvider;
    private ArrayList<HealthServiceProviderItem>healthServiceProvider;
    private ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
    private ArrayList<JobServiceProviderItem> jobServiceProvider;
    private ArrayList<FinancialServiceProviderItem> financialServiceProvider;


    /********Override the MapInfoWindowAdapter method for Education with getting Education Item object***********/

    public MapInfoWindowAdapter(int cat_id,ArrayList<EducationServiceProviderItem> et,final Context context){
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PlaceChoiceActivity pc=new PlaceChoiceActivity();
        int h=pc.getHeight();
        int w=pc.getWidth();
        educationServiceProvider = et;//setting the entertainment object
        categoryId=cat_id;
        myContentsView = inflater.inflate(R.layout.small_info, null);
        myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        close = (ImageView) myContentsView.findViewById(R.id.iv_close);
        img1=(ImageView)myContentsView.findViewById(R.id.img1);
        itemName = (TextView) myContentsView.findViewById(R.id.tv_heading);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }

    /********Override the MapInfoWindowAdapter method for Health with getting Health Item object***********/

    public MapInfoWindowAdapter(int cat_id,final Context context,ArrayList<HealthServiceProviderItem> et){
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        healthServiceProvider = et;//setting the entertainment object

        categoryId=cat_id;
        myContentsView = inflater.inflate(R.layout.small_info, null);
        myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        close = (ImageView) myContentsView.findViewById(R.id.iv_close);
        itemName = (TextView) myContentsView.findViewById(R.id.tv_heading);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        img1=(ImageView)myContentsView.findViewById(R.id.img1);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }

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
        img1=(ImageView)myContentsView.findViewById(R.id.img1);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }

    /********Override the MapInfoWindowAdapter method for Government with getting Government Item object***********/



    /********Override the MapInfoWindowAdapter method for Legal with getting Legal Item object***********/


    public  MapInfoWindowAdapter(final Context context,int cat_id,ArrayList<LegalAidServiceProviderItem> et){
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        legalaidServiceProvider = et;//setting the legal object

        categoryId=cat_id;
        myContentsView= inflater.inflate(R.layout.small_info, null);
        myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        close = (ImageView) myContentsView.findViewById(R.id.iv_close);
        itemName = (TextView) myContentsView.findViewById(R.id.tv_heading);
        img1=(ImageView)myContentsView.findViewById(R.id.img1);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }
    /********Override the MapInfoWindowAdapter method for Financial with getting Financial Item object***********/
    public  MapInfoWindowAdapter(ArrayList<FinancialServiceProviderItem> et,int cat_id,final Context context){
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        financialServiceProvider = et;//setting the financial object

        categoryId=cat_id;
        myContentsView= inflater.inflate(R.layout.small_info, null);
        myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        close = (ImageView) myContentsView.findViewById(R.id.iv_close);
        itemName = (TextView) myContentsView.findViewById(R.id.tv_heading);
        img1=(ImageView)myContentsView.findViewById(R.id.img1);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }


    /********Override the MapInfoWindowAdapter method for Job with getting Job Item object***********/

    public  MapInfoWindowAdapter(ArrayList<JobServiceProviderItem> et,final Context context,int cat_id){
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        jobServiceProvider = et;//setting the job object

        categoryId=cat_id;

        myContentsView= inflater.inflate(R.layout.small_info, null);
        myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        close = (ImageView) myContentsView.findViewById(R.id.iv_close);
        img1=(ImageView)myContentsView.findViewById(R.id.img1);
        itemName = (TextView) myContentsView.findViewById(R.id.tv_heading);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }



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
                        img1.setBackgroundResource(AppConstants.ALL_CAT_ICONS[categoryId - 1]);

                        itemName.setText(et.getEduNameBan());
                        itemAddress.setText("ঠিকানা: "+et.getAddress());
                        itemType.setText("ধরন: "+et.getEduType());
                        itemContact.setText("যোগাযোগের উপায়: " + et.getContactNo());
                    }
                }
                break;
            case AppConstants.HEALTH:

                for(HealthServiceProviderItem et:healthServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {

                        img1.setBackgroundResource(AppConstants.ALL_CAT_ICONS[categoryId - 1]);
                        itemName.setText(et.getNameBn());
                        itemAddress.setText("ঠিকানা: "+et.getArea());
                        itemContact.setText("মোবাইল নম্বর: " + et.getNodeContact());
                        itemType.setText("পরিচিত জায়গা: "+et.getLandmark());
                    }
                }

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
                        img1.setBackgroundResource(AppConstants.ALL_CAT_ICONS[categoryId - 1]);
                        itemName.setText(et.getNodeNameBn());
                        itemType.setText("পরিচিত জায়গা: "+et.getBlock());
                        itemAddress.setText("ঠিকানা: "+et.getAddress());
                        itemContact.setText("মোবাইল নম্বর: " + et.getNodeAdditional());
                    }
                }

                //TODO write necessary codes for entertainment
                break;
            case AppConstants.GOVERNMENT:

                //TODO write necessary codes for government
                break;
            case AppConstants.LEGAL:
                for(LegalAidServiceProviderItem et:legalaidServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {
                        img1.setBackgroundResource(AppConstants.ALL_CAT_ICONS[categoryId - 1]);
                        itemName.setText(et.getLegalaidNameBan());
                        itemAddress.setText("ঠিকানা:  "+et.getArea());
                        itemContact.setText("মোবাইল নম্বর: " + et.getContactNo());
                        itemType.setText("পরিচিত জায়গা: "+et.getLandmark());

                    }
                }
                break;
            case AppConstants.FINANCIAL:
                for(FinancialServiceProviderItem et:financialServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat +"  "+loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {

                        img1.setBackgroundResource(AppConstants.ALL_CAT_ICONS[categoryId - 1]);
                        itemName.setText(et.getNodeName());
                        itemAddress.setText("ঠিকানা: "+et.getArea());
                        itemContact.setText("মোবাইল নম্বর: " + et.getNodeContact());
                        itemType.setText("পরিচিত জায়গা: "+et.getLandmark());


                    }
                }
                break;
            case AppConstants.JOB:
                for(JobServiceProviderItem et:jobServiceProvider)
                {
                    Double lat = Double.parseDouble(et.getLatitude());
                    Double lon = Double.parseDouble(et.getLongitude());
                    System.out.println(lat + "  " + loc.latitude);
                    if(loc.latitude== lat && loc.longitude==lon)
                    {
                        img1.setBackgroundResource(AppConstants.ALL_CAT_ICONS[categoryId - 1]);
                        itemName.setText(et.getAddress());
                        itemAddress.setText("ঠিকানা: "+et.getArea());
                        itemContact.setText("মোবাইল নম্বর: " + et.getContactNo());


                    }
                }
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
