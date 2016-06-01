package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.EducationCourseAdapter;
import demo.kolorob.kolorobdemoversion.adapters.EducationCourseFee;
import demo.kolorob.kolorobdemoversion.adapters.LegalAidAdviceAdapter;
import demo.kolorob.kolorobdemoversion.database.Education.EducationCourseTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationFeeTable;
import demo.kolorob.kolorobdemoversion.helpers.AlertMessage;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Education.EducationCourseItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationFeeItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by arafat on 28/05/2016.
 */

public class DetailsInfoActivityEducation extends Activity {
    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar,linearLayout;
    ImageView left_image,middle_image,right_image,email_btn;
    TextView address_text,phone_text,email_text;
    int width,height;
    TextView ups_text;
    ListView courseListView,listView;
    Context con;
    EducationServiceProviderItem educationServiceProviderItem;
    ArrayList<EducationCourseItem> educationCourseItems;
    ArrayList<EducationFeeItem>educationFeeItems;
    private TextView totalStudents;
    private TextView totalClasses;
    private TextView totalTeachers;
    private TextView playground;
    private TextView hostel;
    private TextView transport;
    private ImageView close_button,phone_mid,distance_left,feedback;
    RadioGroup feedRadio;
    RadioButton rb1,rb2,rb3;
    String status="",phone_num="",registered="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_education);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;
        con=this;


        Intent intent = getIntent();


        if (null != intent)
        {
            educationServiceProviderItem = (EducationServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_VIEW);

        }


        EducationCourseTable educationCourseTable = new EducationCourseTable(DetailsInfoActivityEducation.this);
        EducationFeeTable educationFeeTable = new EducationFeeTable(DetailsInfoActivityEducation.this);
        educationCourseItems=educationCourseTable.getEduCourse(educationServiceProviderItem.getIdentifierId());
        educationFeeItems = educationFeeTable.getEduFee(educationServiceProviderItem.getIdentifierId());
        courseListView=(ListView)findViewById(R.id.courseListView);
        listView=(ListView)findViewById(R.id.listView5);
        linearLayout=(LinearLayout)findViewById(R.id.lll);
        upperHand=(LinearLayout)findViewById(R.id.upper_part);
        upperText=(LinearLayout)findViewById(R.id.upperText);
        left_way=(LinearLayout)findViewById(R.id.left_go_process);
        middle_phone=(LinearLayout)findViewById(R.id.middle_phone);
        right_email=(LinearLayout)findViewById(R.id.right_email);
        left_image=(ImageView)findViewById(R.id.distance_left);
        bottom_bar=(LinearLayout)findViewById(R.id.bottom_bar);
        middle_image=(ImageView)findViewById(R.id.phone_middl);
        right_image=(ImageView)findViewById(R.id.right_side_email);
        address_text=(TextView)findViewById(R.id.address_text);
        phone_text=(TextView)findViewById(R.id.phone_text);
        email_text=(TextView)findViewById(R.id.email_text);
        totalStudents = (TextView) findViewById(R.id.tv_total_students);
        totalClasses = (TextView) findViewById(R.id.tv_total_class);
        totalTeachers = (TextView) findViewById(R.id.tv_total_teachers);
        playground = (TextView) findViewById(R.id.tv_playground);
        hostel = (TextView) findViewById(R.id.tv_hostel_fac);
        transport = (TextView) findViewById(R.id.tv_transport_facility);
        close_button=(ImageView)findViewById(R.id.close_button);
        phone_mid=(ImageView)findViewById(R.id.phone_middl);
        phone_text.setText(educationServiceProviderItem.getContactNo());
        email_text.setText(educationServiceProviderItem.getEmailAddress());
        distance_left=(ImageView)findViewById(R.id.distance_left);
        email_btn=(ImageView) findViewById(R.id.right_side_email);
        feedback=(ImageView)findViewById(R.id.feedback);




        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) upperHand.getLayoutParams();
        int upperhad_height=params2.height = height/6;

        upperHand.setLayoutParams(params2);




        LinearLayout.LayoutParams params_upperText = (LinearLayout.LayoutParams) upperText.getLayoutParams();
        int  vd=params_upperText.height = height/24;
        params_upperText.width = width;
        upperText.setLayoutParams(params_upperText);

        LinearLayout.LayoutParams params_left_way = (LinearLayout.LayoutParams) left_way.getLayoutParams();
        int lett_img=params_left_way.height = (height*3)/24;
        int right_img=params_left_way.width = width/3;
        left_way.setLayoutParams(params_left_way);


        left_image.getLayoutParams().height= (lett_img*2)/3;
        left_image.getLayoutParams().width=right_img/2;


        LinearLayout.LayoutParams params_middle_phone = (LinearLayout.LayoutParams) middle_phone.getLayoutParams();
        int  vx=params_middle_phone.height = (height*3)/24;
        params_middle_phone.width = width/3;
        middle_phone.setLayoutParams(params_middle_phone);

        middle_image.getLayoutParams().height= (lett_img*2)/3;
        middle_image.getLayoutParams().width=right_img/2;

        right_image.getLayoutParams().height= (lett_img*2)/3;
        right_image.getLayoutParams().width=right_img/2;

        LinearLayout.LayoutParams params_right_email = (LinearLayout.LayoutParams) right_email.getLayoutParams();
        int  vc=params_right_email.height = (height*3)/24;
        params_right_email.width = width/3;
        right_email.setLayoutParams(params_right_email);

        ups_text=(TextView)findViewById(R.id.ups_text);
        ups_text.setTextSize(width/25);
        ups_text.setText(educationServiceProviderItem.getEduNameEng());


        if(educationServiceProviderItem.getTotalStudents()!=0)
        {
            totalStudents.setVisibility(View.VISIBLE);
            totalStudents.setText(" মোট ছাত্র সংখ্যা: "+educationServiceProviderItem.getTotalStudents()+ " জন");
        }
        if(educationServiceProviderItem.getTotalClasses()!=0)
        {
            totalClasses.setVisibility(View.VISIBLE);
            totalClasses.setText(" মোট শ্রেণি সংখ্যা: "+educationServiceProviderItem.getTotalClasses()+ " টি");
        }

        if(educationServiceProviderItem.getTotalTeachers()!=0)
        {
            totalTeachers.setVisibility(View.VISIBLE);
            totalTeachers.setText(" মোট শিক্ষক সংখ্যা: "+educationServiceProviderItem.getTotalTeachers()+ " জন");
        }

        if(!educationServiceProviderItem.getPlayground().equals(""))
        {
            playground.setVisibility(View.VISIBLE);
            playground.setText(" খেলার মাঠ: "+educationServiceProviderItem.getPlayground());
        }

        if(!educationServiceProviderItem.getHostelFacility().equals(""))
        {
            hostel.setVisibility(View.VISIBLE);
            hostel.setText(" আবাসন/হোস্টেল সুবিধা : "+educationServiceProviderItem.getHostelFacility());
        }

        if(!educationServiceProviderItem.getHostelFacility().equals(""))
        {
            transport.setVisibility(View.VISIBLE);
            transport.setText(" যাতায়াত সুবিধা: "+educationServiceProviderItem.getHostelFacility());
        }


//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent feedIntent = new Intent(DetailsInfoActivityEducation.this,FeedBackActivityNew.class);
//                feedIntent.putExtra("id",educationServiceProviderItem.getIdentifierId());
//                feedIntent.putExtra("categoryId","1");
//                Log.d(">>>>","Button is clicked1 " +educationServiceProviderItem.getIdentifierId());
//
//                startActivity(feedIntent);
//
//            }
//        });




        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
        int  vcc=params_bottom_bar.height = height/13;
        params_bottom_bar.width = width;
        bottom_bar.setLayoutParams(params_bottom_bar);



        Log.d(">>>>>","Uppertext Height "+lett_img);


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

            right_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(educationServiceProviderItem.getEmailAddress().equals(""))
                    {
                        AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                                "ই মেইল আই ডি পাওয়া যায়নি");
                    }
                }
            });

            phone_mid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                    if(!educationServiceProviderItem.getContactNo().equals(""))
                    {
                        callIntent1.setData(Uri.parse("tel:" + educationServiceProviderItem.getContactNo()));
                        if(checkPermission())
                            startActivity(callIntent1);
                        else{
                            AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                                    "ফোন নম্বর পাওয়া যায়নি");
                            Toast.makeText(getApplicationContext(),
                                    "Sorry, Phone call is not possible now. ", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                    else {

                        AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                                "ফোন নম্বর পাওয়া যায়নি");
                        Toast.makeText(getApplicationContext(),
                                "Sorry, Phone call is not possible now. ", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });








            // phermacy.setText(lat);

        }

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        distance_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {


                    String lat = educationServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = educationServiceProviderItem.getLongitude().toString();
                    // double longitude = Double.parseDouble(lon);
                    String name= educationServiceProviderItem.getEduNameBan().toString();
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
                        // implementFragment();
                        //username and password are present, do your stuff
                    }


                    finish();

                }
                else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                    AppUtils.showSettingsAlert(DetailsInfoActivityEducation.this);

                }

                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityEducation.this, AlertDialog.THEME_HOLO_LIGHT).create();
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

    public void verifyRegistration(View v){

        Boolean register=RegisteredOrNot();

        if(register.equals(false))
        {
            requestToRegister();
        }

        else {

            feedBackAlert();
            sendReviewToServer();
        }


    }

    public void feedBackAlert()
    {

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityEducation.this);
        View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityEducation.this);
        alertDialogBuilder.setView(promptView);


        final Button submit= (Button) promptView.findViewById(R.id.submit);


        final AlertDialog alert = alertDialogBuilder.create();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declareRadiobutton();


                alert.cancel();

            }
        });
        // setup a dialog window
        alertDialogBuilder.setCancelable(false);



        alert.show();
    }


    public void sendReviewToServer()
    {
        int rating;
        if(status.equals("ভাল"))
            rating=1;
        else if(status.equals("মোটামোট"))
            rating=2;
        else
            rating=3;
        String url = "http://www.kolorob.net/KolorobApi/api/rating/save_feedback?phone="+phone_num+"&node="+educationServiceProviderItem.getIdentifierId()+"&service="+"1"+"&rating="+rating;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsInfoActivityEducation.this,response,Toast.LENGTH_SHORT).show();
                        // Log.d(">>>>>","status "+response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            String forms;
                            forms = jo.getString("status");
                            Log.d(">>>>>","status "+forms);
                            //Log.d(">>>>>","status ");

                            if(forms.equals("true"))
                            {
                                AlertMessage.showMessage(DetailsInfoActivityEducation.this, "রেজিস্টেশনটি সফলভাবে সম্পন্ন হয়েছে",
                                        "েজিস্টেশন করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                demo.kolorob.kolorobdemoversion.helpers.AlertMessage.showMessage(DetailsInfoActivityEducation.this, "রেজিস্টেশনটি সফলভাবে সম্পন্ন হয়ে নি",
                                        "আপনি ইতিপূর্বে রেজিস্ট্রেশন করে ফেলেছেন");







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsInfoActivityEducation.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(DetailsInfoActivityEducation.this);
        requestQueue.add(stringRequest);
    }

    public void declareRadiobutton()
    {
       // int selected = feedRadio.getCheckedRadioButtonId();
       // RadioButton rb1 = (RadioButton) findViewById(selected);
      //  status = rb1.getText().toString();

        // Arafat, i set it as static 1, pls change this codes;

        status = "1";
    }

    public void requestToRegister()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityEducation.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityEducation.this);
        alertDialogBuilder.setView(promptView);


        final ImageView yes= (ImageView)promptView.findViewById(R.id.yes);
        final ImageView no= (ImageView)promptView.findViewById(R.id.no);

        final AlertDialog alert = alertDialogBuilder.create();


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration= new Intent(DetailsInfoActivityEducation.this,PhoneRegActivity.class);
                startActivity(intentPhoneRegistration);

            }
        });



        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();

            }
        });
        // setup a dialog window
        alertDialogBuilder.setCancelable(false);



        alert.show();
    }


    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }

    public Boolean RegisteredOrNot()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
      //  editor.putString("registered", lat);
        registered = pref.getString("registered", null);
        phone_num = pref.getString("phone",null);
       // editor.commit();
      //  if(registered.equals("yes"))
            return true;
      //  else
         //   return true;



    }
}
