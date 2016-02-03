package demo.kolorob.kolorobdemoversion.activity;

/**
 * Created by Mazharul.Islam1 on 1/10/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivityEntertainment extends Activity  {
    Button kivabejaben;
    ImageView close;
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

    //  private TextView nodeRegistedwith;
    private TextView email;
    private TextView website;
    private TextView fb;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    EntertainmentServiceProviderItem entertainmentServiceProviderItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_entertainment);
        Intent intent = getIntent();
        if (null != intent)
        {
            entertainmentServiceProviderItem = (EntertainmentServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_ENT);

        }
        /**
         *following codes only for education. This may vary for different category.
         * */
        itemName = (TextView) findViewById(R.id.tv_header_entertainment);
        itemAddress = (TextView) findViewById(R.id.tv_item_type_ent);

        itemContact = (TextView) findViewById(R.id.tv_item_contact_ent);

        nodeRegistrationNumber=(TextView)findViewById(R.id.tv_item_registration_nunmber);
        itemarea=(TextView)findViewById(R.id.tv_item_location_ent);
        itemopeningTime=(TextView)findViewById(R.id.opening_time);
        itemClosingTIme=(TextView)findViewById(R.id.closing_time);
        itemBreakTime=(TextView)findViewById(R.id.break_time);

        email = (TextView) findViewById(R.id.tv_email);
        website = (TextView) findViewById(R.id.tv_website);
        fb = (TextView) findViewById(R.id.tv_fb);


        kivabejaben=(Button)findViewById(R.id.kivabejabenedu);

        itemName.setText(entertainmentServiceProviderItem.getNodeNameBn());
        itemAddress.setText("ঠিকানা :"+ entertainmentServiceProviderItem.getAddress());
        itemarea.setText("এলাকা : "+entertainmentServiceProviderItem.getArea());
        itemContact.setText("যোগাযোগের উপায় :"+entertainmentServiceProviderItem.getNodeContact());
        nodeRegistrationNumber.setText("রেজিস্ট্রেশন নম্বর: "+entertainmentServiceProviderItem.getNodeFacebook());


        itemopeningTime.setText("খোলার সময়: " +entertainmentServiceProviderItem.getOpeningTime() );
        itemClosingTIme.setText("বন্ধের সময়: " +entertainmentServiceProviderItem.getClosingTime() );
        itemBreakTime.setText("বিরতির সময়: "+entertainmentServiceProviderItem.getBreakTime());

        email.setText("ইমেইল : "+entertainmentServiceProviderItem.getNodeEmail());
        website.setText("ওয়েবসাইট : "+entertainmentServiceProviderItem.getNodeWebsite());
        fb.setText("ফেসবুক : "+entertainmentServiceProviderItem.getNodeFacebook());



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


        kivabejaben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat= entertainmentServiceProviderItem.getLatitude().toString();
                // double latitude = Double.parseDouble(lat);
                String lon = entertainmentServiceProviderItem.getLongitude().toString();
                // double longitude = Double.parseDouble(lon);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("Latitude",lat);
                editor.putString("Longitude",lon);
                editor.commit();

               // Toast.makeText(getApplicationContext(), "Your Longitude is " + lon, Toast.LENGTH_SHORT).show();
              //  Toast.makeText(getApplicationContext(), "Your Latitude is " + lat,Toast.LENGTH_SHORT).show();

                String Longitude=pref.getString("Latitude", null);
                String Latitude=pref.getString("Longitude", null);

                if (Latitude != null && Longitude != null )
                {
                    Double Lon= Double.parseDouble(Longitude);
                    Double Lat= Double.parseDouble(Latitude);
                  //  Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat,Toast.LENGTH_SHORT).show();
                    // implementFragment();

                    //username and password are present, do your stuff
                }





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