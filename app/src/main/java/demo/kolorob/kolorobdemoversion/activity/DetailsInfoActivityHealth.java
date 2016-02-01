package demo.kolorob.kolorobdemoversion.activity;

/**
 * Created by Mazharul.Islam1 on 1/11/2016.
 */

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import demo.kolorob.kolorobdemoversion.R;

import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivityHealth extends Activity  {
    Button kivabejabenHealth;
    ImageView close;
    TextView close_tv;

    /**
     * Following components are only for education
     * For other categories this components may vary
     * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
     * */


    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;

    private TextView email;
    private TextView website;
    private TextView fb;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    HealthServiceProviderItem healthServiceProviderItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_details_info);
        Intent intent = getIntent();
        if (null != intent)
        {
            healthServiceProviderItem = (HealthServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_HEALTH);

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
        kivabejabenHealth=(Button)findViewById(R.id.kivabejabenhealth);


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
                    Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat,Toast.LENGTH_SHORT).show();
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
