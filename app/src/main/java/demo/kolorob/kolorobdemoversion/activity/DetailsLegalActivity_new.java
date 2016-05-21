package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.LegalAidAdviceAdapter;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidtypeServiceProviderLegalAdviceTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidtypeServiceProviderSalishiTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidLegalAdviceItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidSalishiItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by israt.jahan on 1/10/2016.
 */



    public class DetailsLegalActivity_new extends Activity {

        ImageView close,legal;
        TextView close_tv;
    ImageButton Feedback;
         ListView lv11,lv2;
    String result_concate="";
    ArrayList<LegalAidtypeServiceProviderLegalAdviceTable> legalAidtypeServiceProviderLegalAdviceTables;
    ArrayList<LegalAidLegalAdviceItem> legalAidLegalAdviceItems;
    ArrayList<LegalAidSalishiItem> legalAidSalishiItems;
        /**
         * Following components are only for LegalAid
         * For other categories this components may vary
         * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
         * */
        private TextView itemName;
        private TextView itemAddress;
        private TextView itemType;
        private TextView itemContact;
        private TextView itemdesignation;
        private TextView itemarea;
        private TextView email;
        private TextView website;
        private TextView fb;
        private TextView opentime,closetime,breaktime,road,block,landmark,additional_time;
        LinearLayout ll1,ll2;

        //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
        LegalAidServiceProviderItem legalAidServiceProviderItem;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details_legal_new);
            Intent intent = getIntent();
            Feedback = (ImageButton) findViewById(R.id.button2);

            if (null != intent)
            {
                legalAidServiceProviderItem = (LegalAidServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_LEGAL);

            }
            LegalAidtypeServiceProviderLegalAdviceTable legalAidtypeServiceProviderLegalAdviceTable= new LegalAidtypeServiceProviderLegalAdviceTable(this);

            fb = (TextView) findViewById(R.id.tv_fb);

            lv11 = (ListView) findViewById(R.id.listView7);
            lv2 = (ListView) findViewById(R.id.listView8);
            ll1=(LinearLayout)findViewById(R.id.first_list);
            ll2=(LinearLayout)findViewById(R.id.second_list);


            /**
             *
             *following codes only for legal. This may vary for different category.
             * */

            opentime=(TextView)findViewById(R.id.opening_time);
            breaktime=(TextView)findViewById(R.id.breaktime);
            closetime=(TextView)findViewById(R.id.close_time);
            road=(TextView)findViewById(R.id.road);
            block=(TextView)findViewById(R.id.block);
            landmark=(TextView)findViewById(R.id.landmark);
            additional_time=(TextView)findViewById(R.id.additionalTime);

            itemName = (TextView) findViewById(R.id.tv_header);
            itemAddress = (TextView) findViewById(R.id.tv_item_location);
            itemType = (TextView) findViewById(R.id.tv_item_type);
            itemContact = (TextView) findViewById(R.id.tv_item_contact);
            email = (TextView) findViewById(R.id.tv_email);
            website = (TextView) findViewById(R.id.tv_website);

            legal=(ImageView)findViewById(R.id.legal);


            itemName.setText(" "+legalAidServiceProviderItem.getLegalaidNameBan());



            if(!legalAidServiceProviderItem.getArea().equals(""))
                concateBasic("  ঠিকানা: ",legalAidServiceProviderItem.getArea());
            if(!legalAidServiceProviderItem.getAddress().equals(""))
                concateBasic("  এলাকা : ",legalAidServiceProviderItem.getAddress());
            itemAddress.setText(result_concate);
            result_concate="";

            if(!legalAidServiceProviderItem.getOpeningtime().equals(""))
                concateBasic(" খোলার সময়: ",legalAidServiceProviderItem.getOpeningtime());
            if(!legalAidServiceProviderItem.getAdditionaltime().equals(""))
                concateBasic(" অতিরিক্ত সময়: ",legalAidServiceProviderItem.getAdditionaltime());

            if(!legalAidServiceProviderItem.getClosingtime().equals(""))
                concateBasic(" বন্ধ করার সময়: ",legalAidServiceProviderItem.getClosingtime());


            opentime.setText(result_concate);
            result_concate="";
//            closetime.setText(" বন্ধ করার সময়: "+legalAidServiceProviderItem.getClosingtime());


            if(!legalAidServiceProviderItem.getContactNo().equals(""))
                concateBasic("  মোবাইল নম্বর:  ",legalAidServiceProviderItem.getContactNo());
//            itemType.setText("  এলাকা : "+legalAidServiceProviderItem.getAddress());
//
           // itemContact.setText("  মোবাইল নম্বর:  "+legalAidServiceProviderItem.getContactNo());

            if(!legalAidServiceProviderItem.getRoad().equals(""))
                concateBasic(" রাস্তা: ",legalAidServiceProviderItem.getRoad());

            if(!legalAidServiceProviderItem.getLandmark().equals(""))
                concateBasic(" ব্লক:",legalAidServiceProviderItem.getLandmark());

            if(!legalAidServiceProviderItem.getFbLink().equals(""))
                concateBasic(" ফেইসবুক: ",legalAidServiceProviderItem.getFbLink());

            if(!legalAidServiceProviderItem.getWebsiteLink().equals(""))
                concateBasic(" ওয়েবসাইট: ",legalAidServiceProviderItem.getWebsiteLink());

            if(!legalAidServiceProviderItem.getEmailAddress().equals(""))
                concateBasic(" ইমেইল: ",legalAidServiceProviderItem.getEmailAddress());

            if(!legalAidServiceProviderItem.getLandmark().equals(""))
                concateBasic(" কাছাকাছি পরিচিত স্থান: ",legalAidServiceProviderItem.getLandmark());
            itemContact.setText(result_concate);

//            breaktime.setText(" বিরতির সময়: "+ legalAidServiceProviderItem.getBreaktime());
//            road.setText(" রাস্তা: "+ legalAidServiceProviderItem.getRoad());
//            block.setText(" ব্লক:"+legalAidServiceProviderItem.getLandmark());
//            fb.setText(" ফেইসবুক: "+ legalAidServiceProviderItem.getFbLink());
//            website.setText(" ওয়েবসাইট: " +legalAidServiceProviderItem.getWebsiteLink());
//            email.setText(" ইমেইল: "+legalAidServiceProviderItem.getEmailAddress());
//            landmark.setText(" কাছাকাছি পরিচিত স্থান: " +legalAidServiceProviderItem.getLandmark());
          //  itemarea.setText("এলাকা: " +legalAidServiceProviderItem.getAddress());
            String la= legalAidServiceProviderItem.getIdentifierId();
            Feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a = new Intent(DetailsLegalActivity_new.this, FeedbackActivity.class);
                    a.putExtra("NodeId", legalAidServiceProviderItem.getIdentifierId());
                    a.putExtra("CatId", legalAidServiceProviderItem.getCategoryId());
                    startActivity(a);
                    finish();
                }
            });


            LegalAidtypeServiceProviderLegalAdviceTable legalAidtypeServiceProviderLegalAdviceTable1=new LegalAidtypeServiceProviderLegalAdviceTable(this);
            LegalAidtypeServiceProviderSalishiTable legalAidtypeServiceProviderSalishiTable = new LegalAidtypeServiceProviderSalishiTable(this);

            legalAidLegalAdviceItems=legalAidtypeServiceProviderLegalAdviceTable.getLegalAdvice(la);
            legalAidSalishiItems =  legalAidtypeServiceProviderSalishiTable.getLegalSalishi(la);

            if(legalAidLegalAdviceItems!=null) {

                int g= legalAidLegalAdviceItems.size();
                String[] service_name=new String[g];
                String[] legal_aid_free=new String[g];
                String[] legal_aid_cost=new String[g];
                String[] legal_aid_person_authority=new String[g];
                String[] legal_aid_remark=new String[g];

                int  k=0;
                for (LegalAidLegalAdviceItem et : legalAidLegalAdviceItems) {

                    service_name[k]=et.getLegalaidcost();
                    legal_aid_free[k]=et.getLegalaidfree();
                    legal_aid_cost[k]=et.getLegalaidcost();
                    legal_aid_person_authority[k]=et.getLegalaidpersonauthority();
                    legal_aid_remark[k]=et.getLegalaidremark();

                    ll1.setVisibility(View.VISIBLE);



                    k++;
                }


                LegalAidAdviceAdapter adapter=new LegalAidAdviceAdapter(this,service_name,legal_aid_free,
                        legal_aid_cost,legal_aid_person_authority,legal_aid_remark);
                lv11.setAdapter(adapter);
                Helpes.getListViewSize(lv11);
            }

            if(legalAidSalishiItems!=null) {

                int g= legalAidSalishiItems.size();
                String[] service_name=new String[g];
                String[] legal_aid_free=new String[g];
                String[] legal_aid_cost=new String[g];
                String[] legal_aid_person_authority=new String[g];
                String[] legal_aid_remark=new String[g];

                int  k=0;
                for (LegalAidSalishiItem et : legalAidSalishiItems) {

                    service_name[k]=et.getSservicename();
                    legal_aid_free[k]=et.getSfree();
                    legal_aid_cost[k]=et.getScost();
                    legal_aid_person_authority[k]=et.getSpersonauthority();
                    legal_aid_remark[k]=et.getSremark();

                    ll2.setVisibility(View.VISIBLE);





                    k++;
                }


                LegalAidAdviceAdapter adapter=new LegalAidAdviceAdapter(this,service_name,legal_aid_free,
                        legal_aid_cost,legal_aid_person_authority,legal_aid_remark);
                lv2.setAdapter(adapter);
                Helpes.getListViewSize(lv2);
            }


            // email.setText("ইমেইলঃ "+legalAidServiceProviderItem.getEmailAddress());
           // website.setText("ওয়েবসাইটঃ "+legalAidServiceProviderItem.getWebsiteLink());
           // fb.setText("ফেসবুকঃ "+legalAidServiceProviderItem.getFbLink());
            legal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {

                        String lat = legalAidServiceProviderItem.getLatitude().toString();
                        // double latitude = Double.parseDouble(lat);
                        String lon = legalAidServiceProviderItem.getLongitude().toString();
                        String name= legalAidServiceProviderItem.getLegalaidNameBan().toString();
                        // double longitude = Double.parseDouble(lon);
                        boolean fromornot=true;
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("Latitude", lat);
                        editor.putString("Longitude", lon);
                        editor.putString("Name", name);
                        editor.putBoolean("Value", fromornot);
                        editor.commit();


                        String Longitude = pref.getString("Latitude", null);
                        String Latitude = pref.getString("Longitude", null);

                        if (Latitude != null && Longitude != null) {
                            Double Lon = Double.parseDouble(Longitude);
                            Double Lat = Double.parseDouble(Latitude);
                            // Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                            //  Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat,Toast.LENGTH_SHORT).show();
                            // implementFragment();

                            //username and password are present, do your stuff
                        }


                        finish();
                    }
                    else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                        AppUtils.showSettingsAlert(DetailsLegalActivity_new.this);

                    }

                    else
                    {

                        AlertDialog alertDialog = new AlertDialog.Builder(DetailsLegalActivity_new.this, AlertDialog.THEME_HOLO_LIGHT).create();
                        alertDialog.setTitle("ইন্টারনেট সংযোগ বিচ্চিন্ন ");
                        alertDialog.setMessage(" দুঃখিত আপনার ইন্টারনেট সংযোগটি সচল নয়। \n পথ দেখতে চাইলে অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি সচল করুন।  ");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();



                    }
                }
            });



            //common for all category
            close = (ImageView) findViewById(R.id.iv_close);
            close_tv = (TextView) findViewById(R.id.tv_close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            close_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }



    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        result_concate= result_concate+value + "\n";




        return result_concate;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
