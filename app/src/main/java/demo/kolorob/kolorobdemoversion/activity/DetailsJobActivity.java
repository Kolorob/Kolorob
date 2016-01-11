package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by israt.jahan on 1/11/2016.
 */
public class DetailsJobActivity extends Activity {

    ImageView close;
    TextView close_tv;

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
    JobServiceProviderItem jobServiceProviderItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_job);
        Intent intent = getIntent();
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


        itemName.setText(jobServiceProviderItem.getAddress());
        itemAddress.setText("ঠিকানাঃ "+jobServiceProviderItem.getArea());
        itemType.setText("যোগাযোগঃ  "+jobServiceProviderItem.getContactNo());

        itemContact.setText("রেজিস্ট্রেশঃন "+jobServiceProviderItem.getRegisteredWith());

        email.setText("সংবাদ দাতাঃ "+jobServiceProviderItem.getContactPersonDesignation());
        website.setText("ওয়েবসাইটঃ "+jobServiceProviderItem.getWebsiteLink());
        fb.setText("ফেসবুকঃ "+jobServiceProviderItem.getFbLink());



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
