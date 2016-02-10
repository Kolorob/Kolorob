package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

/**
 * Created by israt.jahan on 1/10/2016.
 */



    public class DetailsLegalActivity extends Activity {

        ImageView close,legal;
        TextView close_tv;
    ImageButton Feedback;
         ListView lv11,lv2;
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

        //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
        LegalAidServiceProviderItem legalAidServiceProviderItem;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details_legal);
            Intent intent = getIntent();
            Feedback = (ImageButton) findViewById(R.id.button2);
            Feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a = new Intent(DetailsLegalActivity.this, FeedbackActivity.class);
                    startActivity(a);
                    finish();
                }
            });

            if (null != intent)
            {
                legalAidServiceProviderItem = (LegalAidServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_LEGAL);

            }
            LegalAidtypeServiceProviderLegalAdviceTable legalAidtypeServiceProviderLegalAdviceTable= new LegalAidtypeServiceProviderLegalAdviceTable(this);

            fb = (TextView) findViewById(R.id.tv_fb);

            lv11 = (ListView) findViewById(R.id.listView7);
            lv2 = (ListView) findViewById(R.id.listView8);


            /**
             *
             *following codes only for legal. This may vary for different category.
             * */
            itemName = (TextView) findViewById(R.id.tv_header);
            itemAddress = (TextView) findViewById(R.id.tv_item_location);
            itemType = (TextView) findViewById(R.id.tv_item_type);
            itemContact = (TextView) findViewById(R.id.tv_item_contact);
            email = (TextView) findViewById(R.id.tv_email);
            website = (TextView) findViewById(R.id.tv_website);

            legal=(ImageView)findViewById(R.id.legal);


            itemName.setText(legalAidServiceProviderItem.getLegalaidNameEng());
            itemAddress.setText("ঠিকানাঃ "+ legalAidServiceProviderItem.getArea());
            itemType.setText("যোগাযোগঃ  "+legalAidServiceProviderItem.getIdentifierId());

            itemContact.setText("যোগাযোগের উপায়ঃ "+legalAidServiceProviderItem.getContactNo());
            String la= legalAidServiceProviderItem.getIdentifierId();

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
                    String lat= legalAidServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = legalAidServiceProviderItem.getLongitude().toString();
                    // double longitude = Double.parseDouble(lon);
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Latitude",lat);
                    editor.putString("Longitude",lon);
                    editor.commit();


                    String Longitude=pref.getString("Latitude", null);
                    String Latitude=pref.getString("Longitude", null);

                    if (Latitude != null && Longitude != null )
                    {
                        Double Lon= Double.parseDouble(Longitude);
                        Double Lat= Double.parseDouble(Latitude);
                       // Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat,Toast.LENGTH_SHORT).show();
                        // implementFragment();

                        //username and password are present, do your stuff
                    }





                    finish();
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

        @Override
        public void onBackPressed() {
            finish();
            super.onBackPressed();
        }
}
