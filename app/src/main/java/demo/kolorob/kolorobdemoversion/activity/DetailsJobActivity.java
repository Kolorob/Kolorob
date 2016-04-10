package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by israt.jahan on 1/11/2016.
 */
public class DetailsJobActivity extends Activity {
    ImageView close,kivabejabejob;
    TextView close_tv;
    ImageButton Feedback;
    /**
     * Following components are only for LegalAid
     * For other categories this components may vary
     * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
     * */
    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;

    private TextView email;
    private TextView website;
    private TextView fb,openTime,close_Time,breakTIme,jobName,road,block,landmark;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    JobServiceProviderItem jobServiceProviderItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_job);
        Intent intent = getIntent();
        Feedback = (ImageButton) findViewById(R.id.button2);

        if (null != intent)
        {
            jobServiceProviderItem = (JobServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_JOB);

        }
        /**
         *following codes only for job. This may vary for different category.
         * */
        itemName = (TextView) findViewById(R.id.tv_header);
        itemAddress = (TextView) findViewById(R.id.tv_item_location);
        itemType = (TextView) findViewById(R.id.tv_item_type);
        itemContact = (TextView) findViewById(R.id.tv_item_contact);
        email = (TextView) findViewById(R.id.tv_email);
        website = (TextView) findViewById(R.id.tv_website);
        fb = (TextView) findViewById(R.id.tv_fb);
        kivabejabejob=(ImageView)findViewById(R.id.kivabejabenJob);
        openTime=(TextView)findViewById(R.id.opening_time);
        close_Time=(TextView)findViewById(R.id.closingtime);
        breakTIme=(TextView)findViewById(R.id.break_time);
        jobName=(TextView)findViewById(R.id.job_name);
        road=(TextView)findViewById(R.id.road);
        block=(TextView)findViewById(R.id.block);
        landmark=(TextView)findViewById(R.id.landmark);





        itemName.setText(" "+jobServiceProviderItem.getAddress());
        itemAddress.setText(" ঠিকানা: "+jobServiceProviderItem.getArea());
        itemType.setText(" রেজিস্ট্রেশন :  "+jobServiceProviderItem.getRegisteredWith());

        itemContact.setText("  মোবাইল/মুঠোফোন নম্বর: "+jobServiceProviderItem.getContactNo());

        email.setText(" সংবাদ দাতা: "+jobServiceProviderItem.getContactPersonDesignation());
        fb.setText(" ফেইসবুক: "+jobServiceProviderItem.getFbLink());
        website.setText(" ওয়েবসাইট: "+jobServiceProviderItem.getWebsiteLink());
        jobName.setText(" জবের নাম: ");
        road.setText(" রাস্তা :");
        block.setText(" ব্লক :");

        openTime.setText(" খোলার সময়: ");
        close_Time.setText(" বন্ধ করার সময়: ");
        breakTIme.setText(" বিরতির সময়: ");
        landmark.setText(" কাছাকাছি পরিচিত স্থান: ");

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DetailsJobActivity.this, FeedbackActivity.class);
                a.putExtra("NodeId", jobServiceProviderItem.getIdentifierId());
                a.putExtra("CatId", jobServiceProviderItem.getCategoryId());
                startActivity(a);
                finish();
            }
        });

       // website.setText("ওয়েবসাইটঃ "+jobServiceProviderItem.getWebsiteLink());
       /// fb.setText("ফেসবুকঃ "+jobServiceProviderItem.getFbLink());


        kivabejabejob.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {

                            String lat = jobServiceProviderItem.getLatitude().toString();
                            // double latitude = Double.parseDouble(lat);
                            String lon = jobServiceProviderItem.getLongitude().toString();
                            String name= jobServiceProviderItem.getAddress().toString();
                            // double longitude = Double.parseDouble(lon);
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Latitude", lat);
                            editor.putString("Longitude", lon);
                            editor.putString("Name", name);
                            editor.commit();


                            String Longitude = pref.getString("Latitude", null);
                            String Latitude = pref.getString("Longitude", null);

                            if (Latitude != null && Longitude != null) {
                                Double Lon = Double.parseDouble(Longitude);
                                Double Lat = Double.parseDouble(Latitude);
                                // Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat, Toast.LENGTH_SHORT).show();
                                // implementFragment();

                                //username and password are present, do your stuff
                            }


                            finish();
                        }
                        else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                            AppUtils.showSettingsAlert(DetailsJobActivity.this);

                        }
                        else
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(DetailsJobActivity.this, AlertDialog.THEME_HOLO_LIGHT).create();
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
                }
        );

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
