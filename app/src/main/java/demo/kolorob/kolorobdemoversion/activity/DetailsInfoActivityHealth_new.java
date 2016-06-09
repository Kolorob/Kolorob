package demo.kolorob.kolorobdemoversion.activity;

/**
 * Created by Mazharul.Islam1 on 1/11/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.HealthDetailsAdapter;
import demo.kolorob.kolorobdemoversion.adapters.HealthSpecialistAdapter;
import demo.kolorob.kolorobdemoversion.adapters.HealthVaccineAdapter;
import demo.kolorob.kolorobdemoversion.database.Health.HealthPharmacyTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccinesTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Health.HealthPharmacyItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccinesItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

public class DetailsInfoActivityHealth_new extends Activity  {

    ImageView close,kivabejabenHealth;
    TextView close_tv;
    ListView lv1,lv2,lv3;
    ImageButton Feedback;
    String basic_part="",timeStamp,communicationway;

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
    private TextView opening_time;
    private TextView breaktime;
    private TextView close_time;
    private TextView road;
    private TextView block;
    private TextView landmark;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
Activity con;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    HealthServiceProviderItem healthServiceProviderItem;
    HealthPharmacyItem healthPharmacyItem;
    HealthVaccinesItem healthVaccinesItem;
    ListView navlist,navlist1,navlist2;
    String TAG= "nothing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_details_info12);
        Intent intent = getIntent();
        Feedback = (ImageButton) findViewById(R.id.button2);

        if (null != intent)
        {
            healthServiceProviderItem = (HealthServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_HEALTH);

        }
        k=1;
        con=this;
        String node_id = healthServiceProviderItem.getNodeId();
//        int node_ids = Integer.parseInt(node_id.toString());

        ArrayList<HealthVaccinesItem> healthVaccinesItemArrayList;
        ArrayList<HealthSpecialistItem> healthSpecialistItems;
        ArrayList<HealthPharmacyItem> healthPharmacyItems;
        navlist = (ListView) findViewById(R.id.listView2);
        navlist1 = (ListView) findViewById(R.id.listView3);
        navlist2 = (ListView) findViewById(R.id.listView4);
        opening_time=(TextView)findViewById(R.id.opening_time);
        breaktime = (TextView)findViewById(R.id.break_time);
        close_time=(TextView)findViewById(R.id.closing_time);
        road=(TextView)findViewById(R.id.road);
        block=(TextView)findViewById(R.id.block);
        landmark=(TextView)findViewById(R.id.landmark);

        ll1=(LinearLayout)findViewById(R.id.second_list);
        ll2=(LinearLayout)findViewById(R.id.third_list);
        ll3=(LinearLayout)findViewById(R.id.fourth_list);





        HealthSpecialistTable healthSpecialistTable =new HealthSpecialistTable(DetailsInfoActivityHealth_new.this);
        HealthPharmacyTable healthPharmacyTable1 =new HealthPharmacyTable(DetailsInfoActivityHealth_new.this);




        HealthVaccinesTable healthVaccinesTable=new HealthVaccinesTable(DetailsInfoActivityHealth_new.this);
        healthVaccinesItemArrayList=healthVaccinesTable.getVaccinesforNode(healthServiceProviderItem.getNodeId());
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
            String[] pharmacy_remarks=new String[f];
            String[] pharmacy_docremarks=new String[f];
            for (HealthPharmacyItem et : healthPharmacyItems) {
                ll1.setVisibility(View.VISIBLE);


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
                pharmacy_remarks[k]=et.getRemarks();
                pharmacy_docremarks[k]=et.getpharmacyDocRemarks();
              //  lat = lat+"\n"+ " Node_id: "+et.getNodeId()+"\n Doctor_id: "+ et.getDocId() + "\nPhermacy Fee:" + et.getPharmacyFee() + "\n Doctor Name: " +et.getPharmacyDoctorName()+"\n";
               // phermacy.setText("Doc id"+et.getDocId()+"Pharmacy Fee"+et.getPharmacyFee()+"Doctor_name"+et.getPharmacyDoctorName());
                k++;
            }
            HealthDetailsAdapter adapter=new HealthDetailsAdapter(this,doc_id_list,Phermacy_doc_list,doc_fee_list,
                    pharmacy_time_list,pharmacy_no_degree_list,Pharmacy_lmaf_list,
                    Pharmacy_mbbs_list,pharmacy_speciallist_list,pharmacy_remarks,pharmacy_docremarks );

            navlist.setAdapter(adapter);

           Helpes.getListViewSize(navlist);



           // phermacy.setText(lat);

        }


        if(healthVaccinesItemArrayList!=null) {

           int g= healthVaccinesItemArrayList.size();
            String[] vaccine_name=new String[g];
            String[] vaccine_fee=new String[g];
            String[] vaccine_remark=new String[g];
            k=0;

            for (HealthVaccinesItem et : healthVaccinesItemArrayList) {

                vaccine_name[k]=et.getVaccinename();
                vaccine_fee[k]=et.getVaccinefee();
                vaccine_remark[k]=et.getVaccineremarks();
                ll2.setVisibility(View.VISIBLE);


                k++;
            }


            HealthVaccineAdapter adapter=new HealthVaccineAdapter(this,vaccine_name,vaccine_fee,vaccine_remark );
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

                ll3.setVisibility(View.VISIBLE);
            }


            HealthSpecialistAdapter adapter=new HealthSpecialistAdapter(this,specialist_name,specialist_fee,remarks );
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);


        }




        /**
         *following codes only for education. This may vary for different category.
         * */
        itemName = (TextView) findViewById(R.id.tv_header);
        itemAddress = (TextView) findViewById(R.id.tv_item_location_entertainment);
        itemType = (TextView) findViewById(R.id.tv_item_type_entertainment);
        itemContact = (TextView) findViewById(R.id.tv_item_contact_entertainment);

        email = (TextView) findViewById(R.id.tv_email_entertainment);
        website = (TextView) findViewById(R.id.tv_website_entertainment);
        fb = (TextView) findViewById(R.id.tv_fb_entertainment);
        kivabejabenHealth=(ImageView)findViewById(R.id.kivabejabenhealth);



        itemName.setText(" "+healthServiceProviderItem.getNameBn());

        if(!healthServiceProviderItem.getArea().equals(""))
        {
            concateBasic("   ঠিকানা: ", healthServiceProviderItem.getArea());
        }

        if(!healthServiceProviderItem.getNodeType().equals(""))
        {
             concateBasic("  ধরন: ",healthServiceProviderItem.getNodeType());
        }

        itemAddress.setText(basic_part);
        basic_part="";

       // itemType.setText("  ধরন: ");

        if(!healthServiceProviderItem.getOpeningtime().equals(""))
        {

            concateBasic("  খোলার সময়: ", healthServiceProviderItem.getOpeningtime());

        }

        if(!healthServiceProviderItem.getBreaktime().equals(""))
        {

            concateBasic("  বিরতির সময়: ", healthServiceProviderItem.getBreaktime());
            //opening_time.setText("  খোলার সময়: "+healthServiceProviderItem.getOpeningtime());
        }

        if(!healthServiceProviderItem.getOpeningtime().equals(""))
        {

            concateBasic("  বন্ধ করার সময়: ",healthServiceProviderItem.getClosingtime());
            //opening_time.setText("  খোলার সময়: "+healthServiceProviderItem.getOpeningtime());
        }

        opening_time.setText(basic_part);

        basic_part="";

        if(!healthServiceProviderItem.getNodeContact().equals(""))
            concateBasic("  মোবাইল/মুঠোফোন নম্বর: ",healthServiceProviderItem.getNodeContact());

        if(!healthServiceProviderItem.getNodeEmail().equals(""))
            concateBasic("  ইমেইল: ", healthServiceProviderItem.getNodeEmail());

        if(!healthServiceProviderItem.getNodeWebsite().equals(""))
            concateBasic("  ওয়েবসাইট: ",healthServiceProviderItem.getNodeWebsite());

        if(!healthServiceProviderItem.getNodeFacebook().equals(""))
            concateBasic("  ফেসবুক: ",healthServiceProviderItem.getNodeFacebook());

        if(!healthServiceProviderItem.getRoad().equals(""))
            concateBasic("  রাস্তা : ",healthServiceProviderItem.getRoad());

        if(!healthServiceProviderItem.getBlock().equals(""))
            concateBasic("  ব্লক: ",healthServiceProviderItem.getBlock());

        if(!healthServiceProviderItem.getLandmark().equals(""))
            concateBasic("  কাছাকাছি পরিচিত স্থান: ",healthServiceProviderItem.getLandmark());

        itemContact.setText(basic_part);
//        road.setText("");
//        block.setText(""+healthServiceProviderItem.getBlock());
//        landmark.setText("  কাছাকাছি পরিচিত স্থান: "+healthServiceProviderItem.getLandmark());
//        itemContact.setText("" +);
//        email.setText("" +);
//        website.setText(""+);
//        fb.setText("  ফেসবুক: ");
        /*Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DetailsInfoActivityHealth_new.this, FeedbackActivity.class);
                a.putExtra("NodeId", healthServiceProviderItem.getNodeId());
                a.putExtra("CatId", healthServiceProviderItem.getCategoryId());
                startActivity(a);
                finish();
            }
        });*/
        kivabejabenHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (AppUtils.isNetConnected(getApplicationContext()) && AppUtils.displayGpsStatus(getApplicationContext())) {

                    String lat = healthServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);

                    String name= healthServiceProviderItem.getNameBn();
                    String lon = healthServiceProviderItem.getLongitude().toString();
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

                    AppUtils.showSettingsAlert(DetailsInfoActivityHealth_new.this);
//                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityHealth.this, AlertDialog.THEME_HOLO_LIGHT).create();
//                    alertDialog.setTitle("GPS Disabled ");
//                    alertDialog.setMessage(" GPS সচল করুন।  ");
//                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    alertDialog.show();
                }

                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityHealth_new.this, AlertDialog.THEME_HOLO_LIGHT).create();
                    alertDialog.setTitle("ইন্টারনেট সংযোগ্ন বিচ্ছিন্ন ");
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



    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(con);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is Disabled!");

        // Setting Dialog Message
        alertDialog
                .setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        con.startActivity(intent);
                    }
                });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Showing Alert Message
        alertDialog.show();
    }


    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        basic_part= basic_part+value + "\n";

        Log.d("....>>>", "Values   " + basic_part);


        return basic_part;
    }





    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
