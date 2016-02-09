package demo.kolorob.kolorobdemoversion.activity;

/**
 * Created by Mazharul.Islam1 on 1/11/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.HealthDetailsAdapter;
import demo.kolorob.kolorobdemoversion.adapters.HealthSpecialistAdapter;
import demo.kolorob.kolorobdemoversion.adapters.HealthVaccineAdapter;
import demo.kolorob.kolorobdemoversion.adapters.PharmacyAdapter;
import demo.kolorob.kolorobdemoversion.database.Health.HealthPharmacyTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccinesTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Health.HealthPharmacyItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccinesItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivityHealth extends Activity  {

    ImageView close,kivabejabenHealth;
    TextView close_tv;
    ListView lv1,lv2,lv3;


    /**
     * Following components are only for education
     * For other categories this components may vary
     * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
     * */


    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;
    private int k;
    private TextView email;
    private TextView website;
    private TextView fb;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    HealthServiceProviderItem healthServiceProviderItem;
    HealthPharmacyItem healthPharmacyItem;
    HealthVaccinesItem healthVaccinesItem;
    ListView navlist,navlist1,navlist2;
    String TAG= "nothing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_details_info1);
        Intent intent = getIntent();
        if (null != intent)
        {
            healthServiceProviderItem = (HealthServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_HEALTH);

        }
        k=1;
        String node_id = healthServiceProviderItem.getNodeId();
//        int node_ids = Integer.parseInt(node_id.toString());

        ArrayList<HealthVaccinesItem> healthPharmacyItemArrayList;
        ArrayList<HealthSpecialistItem> healthSpecialistItems;
        ArrayList<HealthPharmacyItem> healthPharmacyItems;
        navlist = (ListView) findViewById(R.id.listView2);
        navlist1 = (ListView) findViewById(R.id.listView3);
        navlist2 = (ListView) findViewById(R.id.listView4);





        HealthSpecialistTable healthSpecialistTable =new HealthSpecialistTable(DetailsInfoActivityHealth.this);
        HealthPharmacyTable healthPharmacyTable1 =new HealthPharmacyTable(DetailsInfoActivityHealth.this);




        HealthVaccinesTable healthVaccinesTable=new HealthVaccinesTable(DetailsInfoActivityHealth.this);
        healthPharmacyItemArrayList=healthVaccinesTable.getVaccinesforNode(healthServiceProviderItem.getNodeId());
        healthPharmacyItems=healthPharmacyTable1.getPharmacyforNode(healthServiceProviderItem.getNodeId());

        healthSpecialistItems=healthSpecialistTable.getSpecialistforNode(healthServiceProviderItem.getNodeId());
        // if(healthPharmacyItemArrayList!=null)
        // k=2;
        // Toast.makeText(getApplicationContext(), "Doc Id is " +  healthPharmacyItemArrayList.getVaccinefee(), Toast.LENGTH_SHORT).show();

        // if(healthPharmacyItem!=null)
        //  Toast.makeText(getApplicationContext(), "Doc Id is " +  healthPharmacyItem, Toast.LENGTH_SHORT).show();




        if(healthPharmacyItems!=null) {
            String lat="";
            int k=0;
           int f= healthPharmacyItems.size();

            String[] doc_id_list=new String[f];
            String[] Phermacy_doc_list=new String[f];
            String[] doc_fee_list=new String[f];
            String[] pharmacy_time_list=new String[f];
            String[] pharmacy_no_degree_list=new String[f];
            String[] Pharmacy_lmaf_list=new String[f];
            String[] Pharmacy_mbbs_list=new String[f];
            String[] pharmacy_speciallist_list=new String[f];
            String[] refnum_list=new String[f];

            for (HealthPharmacyItem et : healthPharmacyItems) {



                int docId=et.getDocId();
                String docString = String.valueOf(docId);


                doc_id_list[k]=(docString);
                Phermacy_doc_list[k]=et.getPharmacyDoctorName();
                doc_fee_list[k]=et.getPharmacyFee();
                pharmacy_time_list[k]= et.getPharmacyTime();
                pharmacy_no_degree_list[k]=et.getPharmacyNoDegree();
                Pharmacy_lmaf_list[k]=et.getPharmacyLMAF();
                Pharmacy_mbbs_list[k]=et.getPharmacyMBBS();
                pharmacy_speciallist_list[k]= et.getPharmacySpecialist();
                refnum_list[k]=et.getRefNumber();

              //  lat = lat+"\n"+ " Node_id: "+et.getNodeId()+"\n Doctor_id: "+ et.getDocId() + "\nPhermacy Fee:" + et.getPharmacyFee() + "\n Doctor Name: " +et.getPharmacyDoctorName()+"\n";
               // phermacy.setText("Doc id"+et.getDocId()+"Pharmacy Fee"+et.getPharmacyFee()+"Doctor_name"+et.getPharmacyDoctorName());
                k++;
            }
            HealthDetailsAdapter adapter=new HealthDetailsAdapter(this,doc_id_list,Phermacy_doc_list,doc_fee_list,
                    pharmacy_time_list,pharmacy_no_degree_list,Pharmacy_lmaf_list,
                    Pharmacy_mbbs_list,pharmacy_speciallist_list,refnum_list );

            navlist.setAdapter(adapter);

            Helpes.getListViewSize(navlist);



           // phermacy.setText(lat);

        }


        if(healthPharmacyItemArrayList!=null) {

           int g= healthPharmacyItemArrayList.size();
            String[] vaccine_name=new String[g];
            String[] pharmacy_time_list=new String[g];
            k=0;
            for (HealthVaccinesItem et : healthPharmacyItemArrayList) {

                vaccine_name[k]=et.getVaccinename();
                pharmacy_time_list[k]=et.getVaccinefee();



                k++;
            }


            HealthVaccineAdapter adapter=new HealthVaccineAdapter(this,vaccine_name,pharmacy_time_list );
            navlist1.setAdapter(adapter);
            Helpes.getListViewSize(navlist1);
        }


        if(healthSpecialistItems!=null) {

            int g= healthSpecialistItems.size();
            String[] specialist_name=new String[g];
            String[] specialist_fee=new String[g];
            String[] remarks=new String[g];


            k=0;
            for (HealthSpecialistItem et : healthSpecialistItems) {

                specialist_name[k]=et.getSpecialisttype();
                specialist_fee[k]=et.getSpecialistfees();
                remarks[k]=et.getSpecialistremarks();
                k++;
            }


            HealthSpecialistAdapter adapter=new HealthSpecialistAdapter(this,specialist_name,specialist_fee,remarks );
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);


        }




        /**
         *following codes only for education. This may vary for different category.
         * */
        itemName = (TextView) findViewById(R.id.tv_header_entertainment);
        itemAddress = (TextView) findViewById(R.id.tv_item_location_entertainment);
        itemType = (TextView) findViewById(R.id.tv_item_type_entertainment);
        itemContact = (TextView) findViewById(R.id.tv_item_contact_entertainment);

        email = (TextView) findViewById(R.id.tv_email_entertainment);
        website = (TextView) findViewById(R.id.tv_website_entertainment);
        fb = (TextView) findViewById(R.id.tv_fb_entertainment);
        kivabejabenHealth=(ImageView)findViewById(R.id.kivabejabenhealth);



        itemName.setText(healthServiceProviderItem.getNodeName());
        itemAddress.setText("ঠিকানা ঃ  "+healthServiceProviderItem.getArea());
        itemType.setText("ধরন ঃ  "+healthServiceProviderItem.getNodeType());
        itemContact.setText("যোগাযোগের উপায় ঃ  "+healthServiceProviderItem.getNodeContact());

        email.setText("ইমেইল ঃ  "+healthServiceProviderItem.getNodeEmail());
        website.setText("ওয়েবসাইট ঃ  "+healthServiceProviderItem.getNodeWebsite());
        fb.setText("ফেসবুক ঃ  "+healthServiceProviderItem.getNodeFacebook());

        kivabejabenHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat= healthServiceProviderItem.getLatitude().toString();
                // double latitude = Double.parseDouble(lat);
                String lon = healthServiceProviderItem.getLongitude().toString();
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
