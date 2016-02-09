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
import android.widget.Toast;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.EducationCourseAdapter;
import demo.kolorob.kolorobdemoversion.database.Education.EducationCourseTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Education.EducationCourseItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivity extends Activity  {

    ImageView close,edukivabejaben;
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
    ImageButton Feedback;
    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    EducationServiceProviderItem educationServiceProviderItem;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);
        Intent intent = getIntent();
        Feedback = (ImageButton) findViewById(R.id.button2);
        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DetailsInfoActivity.this, FeedbackActivity.class);
                startActivity(a);
                finish();
            }
        });

        if (null != intent)
        {
            educationServiceProviderItem = (EducationServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_VIEW);

        }
        /**
        *following codes only for education. This may vary for different category.
        * */

        ArrayList<EducationCourseItem> educationCourseItems;



        EducationCourseTable educationCourseTable = new EducationCourseTable(DetailsInfoActivity.this);
        educationCourseItems=educationCourseTable.getEduCourse(educationServiceProviderItem.getIdentifierId());



        itemName = (TextView) findViewById(R.id.tv_header);
        itemAddress = (TextView) findViewById(R.id.tv_item_location);
        itemType = (TextView) findViewById(R.id.tv_item_type);
        itemContact = (TextView) findViewById(R.id.tv_item_contact);
       totalStudents = (TextView) findViewById(R.id.tv_total_students);
       totalClasses = (TextView) findViewById(R.id.tv_total_class);
       totalTeachers = (TextView) findViewById(R.id.tv_total_teachers);
       playground = (TextView) findViewById(R.id.tv_playground);
       hostel = (TextView) findViewById(R.id.tv_hostel_fac);
        transport = (TextView) findViewById(R.id.tv_transport_facility);
       email = (TextView) findViewById(R.id.tv_email);
       website = (TextView) findViewById(R.id.tv_website);
        fb = (TextView) findViewById(R.id.tv_fb);
        edukivabejaben=(ImageView)findViewById(R.id.kivabejabenedu);
        listView=(ListView)findViewById(R.id.listView5);

        itemName.setText(educationServiceProviderItem.getEduNameEng());
       itemAddress.setText("ঠিকানা ঃ  "+educationServiceProviderItem.getArea());
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




        if(educationCourseItems!=null) {

            int k=0;
            int f= educationCourseItems.size();

            String[] course_name=new String[f];
            String[] course_duration_list=new String[f];
            String[] admission_time=new String[f];
            String[] course_cost=new String[f];
            String[] course_type=new String[f];


            for (EducationCourseItem et : educationCourseItems) {







                course_name[k]=et.getEducoursename();
                course_duration_list[k]=et.getEducourseduration();
                admission_time[k]= et.getEducourseadmissiontime();
                course_cost[k]=et.getEducoursecost();
                course_type[k]=et.getEducoursetype();


                //  lat = lat+"\n"+ " Node_id: "+et.getNodeId()+"\n Doctor_id: "+ et.getDocId() + "\nPhermacy Fee:" + et.getPharmacyFee() + "\n Doctor Name: " +et.getPharmacyDoctorName()+"\n";
                // phermacy.setText("Doc id"+et.getDocId()+"Pharmacy Fee"+et.getPharmacyFee()+"Doctor_name"+et.getPharmacyDoctorName());
                k++;
            }
            EducationCourseAdapter adapter=new EducationCourseAdapter(this,course_name,course_duration_list,admission_time,
                    course_cost,course_type);

            listView.setAdapter(adapter);

            Helpes.getListViewSize(listView);



            // phermacy.setText(lat);

        }


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

        edukivabejaben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String lat= educationServiceProviderItem.getLatitude().toString();
                // double latitude = Double.parseDouble(lat);
                String lon = educationServiceProviderItem.getLongitude().toString();
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

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
