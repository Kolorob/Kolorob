package demo.kolorob.kolorobdemoversion.activity;

/**
 * Created by Mazharul.Islam1 on 1/10/2016.
 */

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
import demo.kolorob.kolorobdemoversion.adapters.EntertainmentBookshopAdapter;
import demo.kolorob.kolorobdemoversion.adapters.EntertainmentFieldAdapter;
import demo.kolorob.kolorobdemoversion.adapters.EntertainmentFitnessAdapter;
import demo.kolorob.kolorobdemoversion.adapters.EntertainmentTheatreAdapter;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentBookTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentFieldTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentFitnessTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentTheatreTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentBookShopItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentFieldItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentFitnessItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTheatreItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

public class DetailsInfoActivityEntertainment extends Activity  {

    ImageView close,kivabejaben;
    TextView close_tv;


    /**
     * Following components are only for entertainment
     * For other categories this components may vary
     * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
     * */


    private TextView itemName;
    private TextView itemAddress;
    private TextView itemContact;
    private TextView nodeRegistrationNumber;
    private TextView itemarea;
    private TextView itemopeningTime;
    private TextView itemClosingTIme;
    private TextView itemBreakTime;
    ImageButton Feedback;
    //  private TextView nodeRegistedwith;
    private TextView email;
    private TextView website;
    private TextView fb;
    private TextView road;
    private TextView block;
    private TextView landmark;
    ListView navlist,navlist1,navlist2,navlist3;
    LinearLayout first,second,third,fourth;

    ArrayList<EntertainmentBookShopItem> entertainmentBookShopItems;
    ArrayList<EntertainmentFieldItem> entertainmentFieldItems;
    ArrayList<EntertainmentFitnessItem> entertainmentFitnessItems;
    ArrayList<EntertainmentTheatreItem> entertainmentTheatreItems;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    EntertainmentServiceProviderItem entertainmentServiceProviderItem;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_entertainment);
        Intent intent = getIntent();
        Feedback = (ImageButton) findViewById(R.id.button2);




        navlist = (ListView) findViewById(R.id.listView7);
        navlist1 = (ListView) findViewById(R.id.listView8);
        navlist2 = (ListView) findViewById(R.id.listView9);
        navlist3 = (ListView) findViewById(R.id.listView10);



        first= (LinearLayout)findViewById(R.id.first_list);
        second = (LinearLayout)findViewById(R.id.second_list);
        third = (LinearLayout)findViewById(R.id.third_list);
        fourth =(LinearLayout)findViewById(R.id.fourth_list);

        if (null != intent)
        {
            entertainmentServiceProviderItem = (EntertainmentServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_ENT);

        }
        /**
         *following codes only for education. This may vary for different category.
         * */



        itemName = (TextView) findViewById(R.id.tv_header);
        itemAddress = (TextView) findViewById(R.id.tv_item_type_ent);

        itemContact = (TextView) findViewById(R.id.tv_item_contact_ent);

        //nodeRegistrationNumber=(TextView)findViewById(R.id.tv_item_registration_nunmber);
        itemarea=(TextView)findViewById(R.id.tv_item_location_ent);
        itemopeningTime=(TextView)findViewById(R.id.opening_time);
        itemClosingTIme=(TextView)findViewById(R.id.closing_time);
        itemBreakTime=(TextView)findViewById(R.id.break_time);

        email = (TextView) findViewById(R.id.tv_email);
        website = (TextView) findViewById(R.id.tv_website);
        fb = (TextView) findViewById(R.id.tv_fb);
        road=(TextView)findViewById(R.id.road);
        block=(TextView)findViewById(R.id.block);
        landmark=(TextView)findViewById(R.id.landmark);


        kivabejaben=(ImageView)findViewById(R.id.ent);

        itemName.setText(" "+entertainmentServiceProviderItem.getNodeNameBn());
        itemAddress.setText("  ঠিকানা: "+ entertainmentServiceProviderItem.getAddress());
        itemarea.setText("  এলাকা: "+entertainmentServiceProviderItem.getArea());
        itemContact.setText("   মোবাইল/মুঠোফোন নম্বর: "+entertainmentServiceProviderItem.getNodeAdditional());
       // nodeRegistrationNumber.setText("রেজিস্ট্রেশন নম্ব: "+entertainmentServiceProviderItem.getNodeFacebook());


        itemopeningTime.setText("  খোলার সময়: " +entertainmentServiceProviderItem.getOpeningtime() );
        itemClosingTIme.setText("  বন্ধের সময়: " +entertainmentServiceProviderItem.getClosingtime() );
        itemBreakTime.setText("  বিরতির সময়: "+entertainmentServiceProviderItem.getBreaktime());

        email.setText(" ইমেইল : "+entertainmentServiceProviderItem.getNodeEmail());
        website.setText(" ওয়েবসাইট : "+entertainmentServiceProviderItem.getNodeWebsite());
        fb.setText(" ফেইসবুক: "+entertainmentServiceProviderItem.getNodeFacebook());
        road.setText(" রাস্তা : " +entertainmentServiceProviderItem.getBlock());
        block.setText(" ব্লক : "+entertainmentServiceProviderItem.getLandmark());
        landmark.setText(" কাছাকাছি পরিচিত স্থান: "+entertainmentServiceProviderItem.getRoad());
        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DetailsInfoActivityEntertainment.this, FeedbackActivity.class);
                a.putExtra("NodeId", entertainmentServiceProviderItem.getNodeId());
                a.putExtra("CatId", entertainmentServiceProviderItem.getCategoryId());
                startActivity(a);
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


        EntertainmentBookTable entertainmentBookTable =new EntertainmentBookTable(DetailsInfoActivityEntertainment.this);
        EntertainmentFieldTable entertainmentFieldTable = new EntertainmentFieldTable(DetailsInfoActivityEntertainment.this);
        EntertainmentFitnessTable entertainmentFitnessTable = new EntertainmentFitnessTable(DetailsInfoActivityEntertainment.this);
        EntertainmentTheatreTable entertainmentTheatreTable =new EntertainmentTheatreTable(DetailsInfoActivityEntertainment.this);


        entertainmentBookShopItems= entertainmentBookTable.getBookshop(entertainmentServiceProviderItem.getNodeId());
        entertainmentFieldItems= entertainmentFieldTable.getField(entertainmentServiceProviderItem.getNodeId());
        entertainmentFitnessItems= entertainmentFitnessTable.getFitness(entertainmentServiceProviderItem.getNodeId());
        entertainmentTheatreItems= entertainmentTheatreTable.getTheatre(entertainmentServiceProviderItem.getNodeId());

        if(entertainmentBookShopItems!=null) {

           //
            int g= entertainmentBookShopItems.size();
            String[] borrow_cost=new String[g];
            String[] lending_allowed=new String[g];
            String[] membership_cost=new String[g];
            String[] offers=new String[g];
            String[] offer_details=new String[g];
            String[] membership_cost_ffp=new String[g];
            String[] membership_cost_foc=new String[g];
           int  k=0;
            for (EntertainmentBookShopItem et : entertainmentBookShopItems) {

                borrow_cost[k]=et.getBorrowCost();
                lending_allowed[k]=et.getLending();
                membership_cost[k]=et.getMemcost();
                offers[k]=et.getOffers();
                offer_details[k]=et.getOfferdetails();
                membership_cost_ffp[k]=et.getMemcostffp();
                membership_cost_foc[k]=et.getMemcostfoc();

                first.setVisibility(View.VISIBLE);

                k++;
            }


            EntertainmentBookshopAdapter adapter=new EntertainmentBookshopAdapter(this,borrow_cost,lending_allowed,
                    membership_cost,offers,offer_details,membership_cost_ffp,membership_cost_foc);
            navlist.setAdapter(adapter);
            Helpes.getListViewSize(navlist);
        }



        if(entertainmentFieldItems!=null) {


            int g= entertainmentFieldItems.size();
            String[] event_cost=new String[g];
            String[] playground_cost=new String[g];
            String[] remark=new String[g];
            String[] event_cost_ffp=new String[g];
            String[] event_cost_foc=new String[g];
            String[] playground_cost_ffp=new String[g];
            String[] playground_cost_foc=new String[g];

           int  k=0;
            for (EntertainmentFieldItem et : entertainmentFieldItems) {

                event_cost[k]=et.getEventCost();
                playground_cost[k]=et.getPlaygroundcost();
                remark[k]=et.getRemark();
                event_cost_ffp[k]=et.getEventcostffp();
                event_cost_foc[k]=et.getEventcostfoc();
                playground_cost_ffp[k]=et.getPlaygroundcostffp();
                playground_cost_foc[k]=et.getPlaygroundcostfoc();
                k++;
                second.setVisibility(View.VISIBLE);
            }
            EntertainmentFieldAdapter adapter=new EntertainmentFieldAdapter(this,event_cost,playground_cost,
                    remark,event_cost_ffp,event_cost_foc,playground_cost_ffp,playground_cost_foc);
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);
        }



        if(entertainmentTheatreItems!=null) {

            int g= entertainmentTheatreItems.size();

            String[] event_type=new String[g];
            String[] event_fee=new String[g];
            String[] event_date=new String[g];
            String[] remarks=new String[g];
            String[] event_fee_ffp=new String[g];
            String[] event_fee_foc=new String[g];

            int  k=0;
            for (EntertainmentTheatreItem et : entertainmentTheatreItems) {


                event_type[k]=et.getEventtype();
                event_fee[k]=et.getEventfee();
                event_date[k]=et.getEventdate();
                remarks[k]=et.getRemarks();
                event_fee_ffp[k]=et.getEventfee();
                event_fee_foc[k]=et.getEventfeefoc();
                k++;
                third.setVisibility(View.VISIBLE);
            }
            EntertainmentTheatreAdapter adapter=new EntertainmentTheatreAdapter(this,event_type,event_fee,
                    event_date,remarks,event_fee_ffp,event_fee_foc);
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);
        }


        if(entertainmentFitnessItems!=null) {

            int g= entertainmentFitnessItems.size();

            String[] year_of_establishment=new String[g];
            String[] num_workers=new String[g];
            String[] offers=new String[g];
            String[] offer_details=new String[g];
            String[] service_type=new String[g];
            String[] type=new String[g];
            String[] service_details=new String[g];

            int  k=0;
            for (EntertainmentFitnessItem et : entertainmentFitnessItems) {

                int year=et.getYearofestablishment();
                String docString = String.valueOf(year);


                int years=et.getWorkers();
                String docStrings = String.valueOf(years);

                year_of_establishment[k]=docString;
                num_workers[k]=docStrings;
                offers[k]=et.getOffers();
                offer_details[k]=et.getOfferdetails();
                service_type[k]=et.getServicetype();
                type[k]=et.getType();
                service_details[k]=et.getServicedetails();
                k++;

                fourth.setVisibility(View.VISIBLE);

            }
            EntertainmentFitnessAdapter adapter=new EntertainmentFitnessAdapter(this,year_of_establishment,num_workers,
                    offers,offer_details,service_type,type,service_details);
            navlist3.setAdapter(adapter);
            Helpes.getListViewSize(navlist3);
        }








        kivabejaben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {

                    String lat = entertainmentServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = entertainmentServiceProviderItem.getLongitude().toString();
                    String name= entertainmentServiceProviderItem.getNodeNameBn().toString();
                    // double longitude = Double.parseDouble(lon);
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Latitude", lat);
                    editor.putString("Longitude", lon);
                    editor.putString("Name", name);

                    editor.commit();

                    // Toast.makeText(getApplicationContext(), "Your Longitude is " + lon, Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(getApplicationContext(), "Your Latitude is " + lat,Toast.LENGTH_SHORT).show();

                    String Longitude = pref.getString("Latitude", null);
                    String Latitude = pref.getString("Longitude", null);

                    if (Latitude != null && Longitude != null) {
                        Double Lon = Double.parseDouble(Longitude);
                        Double Lat = Double.parseDouble(Latitude);
                        //  Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat,Toast.LENGTH_SHORT).show();
                        // implementFragment();

                        //username and password are present, do your stuff
                    }


                    finish();
                }
                else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                    AppUtils.showSettingsAlert(DetailsInfoActivityEntertainment.this);

                }
                else
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityEntertainment.this, AlertDialog.THEME_HOLO_LIGHT).create();
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

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}