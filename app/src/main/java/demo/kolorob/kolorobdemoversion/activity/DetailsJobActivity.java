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
 * Created by arafat on 1/11/2016.
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

        openTime=(TextView)findViewById(R.id.opening_time);
        close_Time=(TextView)findViewById(R.id.closingtime);
        breakTIme=(TextView)findViewById(R.id.break_time);
        jobName=(TextView)findViewById(R.id.job_name);
        road=(TextView)findViewById(R.id.road);
        block=(TextView)findViewById(R.id.block);
        landmark=(TextView)findViewById(R.id.landmark);





        itemName.setText("Plumber Needed");
        itemAddress.setText(" ঠিকানা:  বাড়ি নং: ১০ বাস্তা নম্বর: 4 বাউনিয়াবাদ ");
        itemType.setText(" রেজিস্ট্রেশন :  বাউনিয়াবাদ");

        itemContact.setText("  মোবাইল/মুঠোফোন নম্বর:  01988009755");

        email.setText(" সংবাদ দাতা: অন্তরা ");
        fb.setText(" ফেইসবুক: https://www.facebook.com/mazharul.islamarafat");
        website.setText(" ওয়েবসাইট: http://www.kolorob.info/");
        jobName.setText(" স্যালারি : ১০০০০ টাকা ");
        road.setText("আবেদনের শেষ সময় : ১১ মে ২০১৬");
        block.setText(" চাকুরির ধরন:  পার্ট টাইম");

        openTime.setText(" দায়িত্ব ঃ  পানির লাইন ফেটে গেছে। এটা মেরামত করা দরকার। ");
        close_Time.setText(" তথ্য সংগ্রহের সময়ঃ ২ মে ২০১৬ ");
        breakTIme.setText(" ইমেইলঃ  mazharul.islam1@savethechildren.org" );
        landmark.setText(" কাছাকাছি পরিচিত স্থান: বাউনিয়াবাদ ঈদ গাহ মাঠ");


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
