package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivity extends Activity  {

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
    private TextView totalStudents;
    private TextView totalClasses;
    private TextView totalTeachers;
    private TextView playground;
    private TextView hostel;
    private TextView transport;
    private TextView email;
    private TextView website;
    private TextView fb;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    EducationServiceProviderItem educationServiceProviderItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);
        Intent intent = getIntent();
        if (null != intent)
        {
            educationServiceProviderItem = (EducationServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_VIEW);

        }
        /**
        *following codes only for education. This may vary for different category.
        * */
        itemName = (TextView) findViewById(R.id.tv_header_health);
        itemAddress = (TextView) findViewById(R.id.tv_item_location_health);
        itemType = (TextView) findViewById(R.id.tv_item_type_health);
        itemContact = (TextView) findViewById(R.id.tv_item_contact_health);
        totalStudents = (TextView) findViewById(R.id.tv_total_students);
        totalClasses = (TextView) findViewById(R.id.tv_total_class);
        totalTeachers = (TextView) findViewById(R.id.tv_total_teachers);
        playground = (TextView) findViewById(R.id.tv_playground);
        hostel = (TextView) findViewById(R.id.tv_hostel_fac);
        transport = (TextView) findViewById(R.id.tv_transport_facility);
        email = (TextView) findViewById(R.id.tv_email_health);
        website = (TextView) findViewById(R.id.tv_website_health);
        fb = (TextView) findViewById(R.id.tv_fb_health);


        itemName.setText(educationServiceProviderItem.getEduNameEng());
        itemAddress.setText("ঠিকানা ঃ  "+AppConstants.BAUNIABADH);
        itemType.setText("ধরন ঃ  "+educationServiceProviderItem.getEduType());
        itemContact.setText("যোগাযোগের উপায় ঃ  "+educationServiceProviderItem.getContactNo());
        totalStudents.setText("মোট ছাত্র সংখ্যা ঃ  "+educationServiceProviderItem.getTotalStudents());
        totalClasses.setText("মোট শ্রেণি সংখ্যা ঃ  "+educationServiceProviderItem.getTotalClasses());
        totalTeachers.setText("মোট শিক্ষক সংখ্যা ঃ  "+educationServiceProviderItem.getTotalTeachers());
        playground.setText("খেলার মাঠ ঃ  "+educationServiceProviderItem.getPlayground());
        hostel.setText("হোস্টেল ঃ  "+educationServiceProviderItem.getHostelFacility());
        transport.setText("যাতায়াত ঃ  "+educationServiceProviderItem.getHostelFacility());
        email.setText("ইমেইল ঃ  "+educationServiceProviderItem.getEmailAddress());
        website.setText("ওয়েবসাইট ঃ  "+educationServiceProviderItem.getWebsiteLink());
        fb.setText("ফেসবুক ঃ  "+educationServiceProviderItem.getFbLink());



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
