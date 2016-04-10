package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.EducationCourseAdapter;
import demo.kolorob.kolorobdemoversion.adapters.EducationCourseFee;
import demo.kolorob.kolorobdemoversion.database.Education.EducationCourseTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationFeeTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Education.EducationCourseItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationFeeItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

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
    LinearLayout linearLayout;
    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    EducationServiceProviderItem educationServiceProviderItem;
    ListView listView,courseListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);
        Intent intent = getIntent();
        Feedback = (ImageButton) findViewById(R.id.button2);


        linearLayout=(LinearLayout)findViewById(R.id.lll);

        if (null != intent)
        {
            educationServiceProviderItem = (EducationServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_VIEW);

        }
        /**
        *following codes only for education. This may vary for different category.
        * */

        ArrayList<EducationCourseItem> educationCourseItems;
        ArrayList<EducationFeeItem>educationFeeItems;



        EducationCourseTable educationCourseTable = new EducationCourseTable(DetailsInfoActivity.this);
        EducationFeeTable educationFeeTable = new EducationFeeTable(DetailsInfoActivity.this);
        educationCourseItems=educationCourseTable.getEduCourse(educationServiceProviderItem.getIdentifierId());
        educationFeeItems = educationFeeTable.getEduFee(educationServiceProviderItem.getIdentifierId());

        courseListView=(ListView)findViewById(R.id.courseListView);
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

        itemName.setText(" "+educationServiceProviderItem.getEduNameBan());
        itemAddress.setText(" ঠিকানা: "+educationServiceProviderItem.getArea());
        itemType.setText(" শিক্ষার ধরন: "+educationServiceProviderItem.getEduType());
        itemContact.setText("  মোবাইল/মুঠোফোন নম্বর: "+educationServiceProviderItem.getContactNo());
        totalStudents.setText(" মোট ছাত্র সংখ্যা: "+educationServiceProviderItem.getTotalStudents()+ " জন");
        totalClasses.setText(" মোট শ্রেণি সংখ্যা: "+educationServiceProviderItem.getTotalClasses()+ " টি");
        totalTeachers.setText(" মোট শিক্ষক সংখ্যা: "+educationServiceProviderItem.getTotalTeachers()+ " জন");
        playground.setText(" খেলার মাঠ: "+educationServiceProviderItem.getPlayground());
        hostel.setText(" আবাসন/হোস্টেল সুবিধা : "+educationServiceProviderItem.getHostelFacility());
        transport.setText(" যাতায়াত সুবিধা: "+educationServiceProviderItem.getHostelFacility());
        email.setText(" ইমেইল: "+educationServiceProviderItem.getEmailAddress());
        website.setText(" ওয়েবসাইট: "+educationServiceProviderItem.getWebsiteLink());
        fb.setText(" ফেইসবুক: "+educationServiceProviderItem.getFbLink());

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DetailsInfoActivity.this, FeedbackActivity.class);
                a.putExtra("NodeId", educationServiceProviderItem.getIdentifierId());
                a.putExtra("CatId", educationServiceProviderItem.getCategoryId());
                startActivity(a);
                finish();
            }
        });

        if(educationFeeItems!=null) {

            int k=0;
            int f= educationFeeItems.size();

            String[] pre_school_free=new String[f];
            String[] pre_school_stipend_speciality=new String[f];
            String[] pre_school_stipend_type=new String[f];
            String[] pre_school_stipend_details=new String[f];
            String[] pre_school_max_fee=new String[f];


            String[] pre_school_min_fee=new String[f];
            String[] pre_school_coaching_fee=new String[f];
            String[] pre_school_additional_fee=new String[f];
            String[] i_v_free=new String[f];
            String[] i_v_stipend_speciality=new String[f];

            String[] i_v_stipend_type=new String[f];
            String[] i_v_stipend_details=new String[f];
            String[] i_v_max_fee=new String[f];
            String[] i_v_min_fee=new String[f];
            String[] i_v_additional_fee=new String[f];

            String[] i_v_coaching_fee=new String[f];
            String[] vi_x_free=new String[f];
            String[] vi_x_stipend_speciality=new String[f];
            String[] vi_x_stipend_type=new String[f];
            String[] vi_x_stipend_details=new String[f];

            String[] vi_x_max_fee=new String[f];
            String[] vi_x_min_fee=new String[f];
            String[] vi_x_coaching_fee=new String[f];
            String[] vi_x_additional_fee=new String[f];
            String[] xi_xii_free=new String[f];


            String[] xi_xii_stipend_speciality=new String[f];
            String[] xi_xii_stipend_type=new String[f];
            String[] xi_xii_stipend_details=new String[f];
            String[] xi_xii_max_fee=new String[f];
            String[] xi_xii_min_fee=new String[f];


            String[] xi_xii_coaching_fee=new String[f];
            String[] xi_xii_additional_fee=new String[f];
            String[] uni_free=new String[f];
            String[] uni_stipend_speciality=new String[f];
            String[] uni_stipend_details=new String[f];


            String[] uni_stipend_type=new String[f];
            String[] uni_max_fee=new String[f];
            String[] uni_min_fee=new String[f];
            String[] uni_coaching_fee=new String[f];
            String[] uni_additional_fee=new String[f];


            for (EducationFeeItem et : educationFeeItems) {


                pre_school_free[k]=et.getPre_school_free();
                pre_school_stipend_speciality[k]=et.getPre_school_stipend_speciality();
                pre_school_stipend_type[k]= et.getPre_school_stipend_type();
                pre_school_stipend_details[k]=et.getPre_school_stipend_details();
                pre_school_max_fee[k]=et.getPre_school_max_fee();

                pre_school_min_fee[k]=et.getPre_school_min_fee();
                pre_school_coaching_fee[k]=et.getPre_school_coaching_fee();
                pre_school_additional_fee[k]= et.getPre_school_additional_fee();
                i_v_free[k]=et.getI_v_free();
                i_v_stipend_speciality[k]=et.getI_v_stipend_speciality();

                i_v_stipend_type[k]=et.getI_v_stipend_type();
                i_v_stipend_details[k]=et.getI_v_stipend_details();
                i_v_max_fee[k]= et.getI_v_max_fee();
                i_v_min_fee[k]=et.getI_v_min_fee();
                i_v_additional_fee[k]=et.getI_v_additional_fee();

                i_v_coaching_fee[k]=et.getI_v_coaching_fee();
                vi_x_free[k]=et.getVi_x_free();
                vi_x_stipend_speciality[k]= et.getVi_x_stipend_speciality();
                vi_x_stipend_type[k]=et.getVi_x_stipend_type();
                vi_x_stipend_details[k]=et.getVi_x_stipend_details();

                vi_x_max_fee[k]=et.getVi_x_max_fee();
                vi_x_min_fee[k]=et.getVi_x_min_fee();
                vi_x_coaching_fee[k]= et.getVi_x_coaching_fee();
                vi_x_additional_fee[k]=et.getVi_x_additional_fee();
                xi_xii_free[k]=et.getXi_xii_free();

                xi_xii_stipend_speciality[k]=et.getXi_xii_stipend_speciality();
                xi_xii_stipend_type[k]=et.getXi_xii_stipend_type();
                xi_xii_stipend_details[k]= et.getXi_xii_stipend_details();
                xi_xii_max_fee[k]=et.getXi_xii_max_fee();
                xi_xii_min_fee[k]=et.getXi_xii_min_fee();

                xi_xii_coaching_fee[k]=et.getXi_xii_coaching_fee();
                xi_xii_additional_fee[k]=et.getXi_xii_additional_fee();
                uni_free[k]= et.getUni_free();
                uni_stipend_speciality[k]=et.getUni_stipend_speciality();
                uni_stipend_details[k]=et.getUni_stipend_type();

                uni_stipend_type[k]=et.getUni_stipend_details();
                uni_max_fee[k]=et.getUni_stipend_details();
                uni_min_fee[k]= et.getUni_max_fee();
                uni_coaching_fee[k]=et.getUni_min_fee();
                uni_additional_fee[k]=et.getUni_coaching_fee();





                linearLayout.setVisibility(View.VISIBLE);

                //  lat = lat+"\n"+ " Node_id: "+et.getNodeId()+"\n Doctor_id: "+ et.getDocId() + "\nPhermacy Fee:" + et.getPharmacyFee() + "\n Doctor Name: " +et.getPharmacyDoctorName()+"\n";
                // phermacy.setText("Doc id"+et.getDocId()+"Pharmacy Fee"+et.getPharmacyFee()+"Doctor_name"+et.getPharmacyDoctorName());
                k++;
            }
            EducationCourseFee adapter=new EducationCourseFee(
                    this,
                    pre_school_free,
                    pre_school_stipend_speciality,
                    pre_school_stipend_type,
                    pre_school_stipend_details,
                    pre_school_max_fee,
                    pre_school_min_fee,
                    pre_school_coaching_fee,
                    pre_school_additional_fee,
                    i_v_free,
                    i_v_stipend_speciality,
                    i_v_stipend_type,
                    i_v_stipend_details,
                    i_v_max_fee,
                    i_v_min_fee,
                    i_v_additional_fee,
                    i_v_coaching_fee,
                    vi_x_free,
                    vi_x_stipend_speciality,
                    vi_x_stipend_type,
                    vi_x_stipend_details,
                    vi_x_max_fee,
                    vi_x_min_fee,
                    vi_x_coaching_fee,
                    vi_x_additional_fee,
                    xi_xii_free,
                    xi_xii_stipend_speciality,
                    xi_xii_stipend_type,
                    xi_xii_stipend_details,
                    xi_xii_max_fee,
                    xi_xii_min_fee,
                    xi_xii_coaching_fee,
                    xi_xii_additional_fee,
                    uni_free,
                    uni_stipend_speciality,
                    uni_stipend_details,
                    uni_stipend_type,
                    uni_max_fee,
                    uni_min_fee,
                    uni_coaching_fee,
                    uni_additional_fee);

            listView.setAdapter(adapter);

            Helpes.getListViewSize(listView);



            // phermacy.setText(lat);

        }




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
                linearLayout.setVisibility(View.VISIBLE);

                //  lat = lat+"\n"+ " Node_id: "+et.getNodeId()+"\n Doctor_id: "+ et.getDocId() + "\nPhermacy Fee:" + et.getPharmacyFee() + "\n Doctor Name: " +et.getPharmacyDoctorName()+"\n";
                // phermacy.setText("Doc id"+et.getDocId()+"Pharmacy Fee"+et.getPharmacyFee()+"Doctor_name"+et.getPharmacyDoctorName());
                k++;
            }
            EducationCourseAdapter adapter=new EducationCourseAdapter(this,course_name,course_duration_list,admission_time,
                    course_cost,course_type);

            courseListView.setAdapter(adapter);

            Helpes.getListViewSize(courseListView);



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


                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {


                    String lat = educationServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = educationServiceProviderItem.getLongitude().toString();
                    // double longitude = Double.parseDouble(lon);
                    String name= educationServiceProviderItem.getEduNameBan().toString();
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

                        // implementFragment();

                        //username and password are present, do your stuff
                    }


                    finish();

                }
                else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                    AppUtils.showSettingsAlert(DetailsInfoActivity.this);

                }

                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivity.this, AlertDialog.THEME_HOLO_LIGHT).create();
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
