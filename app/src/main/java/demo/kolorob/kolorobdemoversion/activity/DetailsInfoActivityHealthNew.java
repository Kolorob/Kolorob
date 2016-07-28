package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTableDetails;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccineTableDetails;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItemDetails;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccineItemDetails;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

/**
 * Created by arafat on 28/05/2016.
 */

public class DetailsInfoActivityHealthNew extends Activity {
    Dialog dialog;
    LinearLayout upperHand, upperText, left_way, middle_phone, right_email, bottom_bar, linearLayout;
    ImageView left_image, middle_image, right_image, email_btn;
    TextView address_text, phone_text, email_text;
    int width, height;
    TextView ups_text;
    ListView courseListView, listView;
    Context con;
    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";
    Float rating;
    HealthServiceProviderItemNew healthServiceProviderItemNew;
    ArrayList<HealthServiceProviderItem> healthServiceProviderItems;
    ArrayList<HealthServiceProviderItem> healthServiceProviderItemsz;
    ArrayList<HealthSpecialistItemDetails> healthSpecialistItemDetailses;
    ArrayList<HealthVaccineItemDetails> healthVaccineItemDetailses;
    private TextView totalStudents;
    private TextView totalClasses;
    private TextView totalTeachers;
    private TextView playground;
    private TextView hostel;
    private TextView transport;
    private TextView ratingText;
    private TextView serviceDetails, specialist, health_vaccine;
    private ImageView close_button, phone_mid, distance_left, feedback, top_logo, cross, school_logo_default;
    private RadioGroup feedRadio;
    RadioButton rb1, rb2, rb3,rb4,rb5;
    String status = "", phone_num = "", registered = "";
    String result_concate = "";
    private CheckBox checkBox;
    EditText feedback_comment;
    RatingBar ratingBar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_health_new);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        con = this;


        Intent intent = getIntent();
      //  declareRadiobutton();

        if (null != intent) {
            healthServiceProviderItemNew = (HealthServiceProviderItemNew) intent.getSerializableExtra(AppConstants.KEY_DETAILS_HEALTH_NEW);
            // Log.d("CheckDetailsHealth","======"+healthServiceProviderItemNew);
        }


        HealthSpecialistTableDetails healthSpecialistTableDetails = new HealthSpecialistTableDetails(DetailsInfoActivityHealthNew.this);


        courseListView = (ListView) findViewById(R.id.courseListView);
        listView = (ListView) findViewById(R.id.listView5);
        linearLayout = (LinearLayout) findViewById(R.id.lll);
        upperHand = (LinearLayout) findViewById(R.id.upper_part);
        upperText = (LinearLayout) findViewById(R.id.upperText);
        left_way = (LinearLayout) findViewById(R.id.left_go_process);
        middle_phone = (LinearLayout) findViewById(R.id.middle_phone);
        right_email = (LinearLayout) findViewById(R.id.right_email);
        left_image = (ImageView) findViewById(R.id.distance_left);
        bottom_bar = (LinearLayout) findViewById(R.id.bottom_bar);
        middle_image = (ImageView) findViewById(R.id.phone_middl);
        right_image = (ImageView) findViewById(R.id.right_side_email);
        address_text = (TextView) findViewById(R.id.address_text);
        phone_text = (TextView) findViewById(R.id.phone_text);
        email_text = (TextView) findViewById(R.id.email_text);
        totalStudents = (TextView) findViewById(R.id.tv_total_students);
        totalClasses = (TextView) findViewById(R.id.tv_total_class);
        totalTeachers = (TextView) findViewById(R.id.tv_total_teachers);
        playground = (TextView) findViewById(R.id.tv_playground);
        hostel = (TextView) findViewById(R.id.tv_hostel_fac);
        transport = (TextView) findViewById(R.id.tv_transport_facility);
        ratingText = (TextView) findViewById(R.id.ratingText);
        serviceDetails = (TextView) findViewById(R.id.serviceDetails);
        close_button = (ImageView) findViewById(R.id.close_buttonc);
        specialist = (TextView) findViewById(R.id.specialist);
        health_vaccine = (TextView) findViewById(R.id.health_vaccine);



        top_logo = (ImageView) findViewById(R.id.top_logo);
        //cross=(ImageView)findViewById(R.id.cross_jb);
        school_logo_default = (ImageView) findViewById(R.id.service_logo);


        distance_left = (ImageView) findViewById(R.id.distance_left);
        email_btn = (ImageView) findViewById(R.id.right_side_email);
        feedback = (ImageView) findViewById(R.id.feedback);
        checkBox = (CheckBox) findViewById(R.id.compare);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        if(width<500)
        ratingBar = new RatingBar(this, null, android.R.attr.ratingBarStyleSmall);
//        RatingBar ratingBar = new RatingBar(context, null, android.R.attr.ratingBarStyleSmall);

        setRatingBar();

        CheckConcate("প্রতিস্টানের ধরন", healthServiceProviderItemNew.getInstitute_type());
        CheckConcate("ধারন ক্ষমতা", healthServiceProviderItemNew.getCapacity());
        CheckConcate("পুরুষ ডাক্তার", healthServiceProviderItemNew.getMale_doctors());
        CheckConcate("মহিলা ডাক্তার", healthServiceProviderItemNew.getFemale_doctors());
        CheckConcate("রোগী এবং ডাক্তারের অনুপাত", healthServiceProviderItemNew.getPatient_doctor_ratio());
        CheckConcate("পুরুষ ডাক্তার", healthServiceProviderItemNew.getMale_doctors());
        CheckConcate("মহিলা ডাক্তার", healthServiceProviderItemNew.getFemale_doctors());
        CheckConcate("রোগী নার্সের অনুপাত", healthServiceProviderItemNew.getPatient_nurse_ratio());
        CheckConcate("পরিচিত স্থান", healthServiceProviderItemNew.getLandmark());
        CheckConcate("ব্লক", healthServiceProviderItemNew.getBlock());
        CheckConcate("ফ্লোর", healthServiceProviderItemNew.getFloor());
        CheckConcate("বাড়ির নাম", healthServiceProviderItemNew.getHouse_name());
        CheckConcate("রাস্তা", healthServiceProviderItemNew.getRoad());
        CheckConcate("লাইন ", healthServiceProviderItemNew.getLine());
        CheckConcate("এভিনিউ", healthServiceProviderItemNew.getAvenue());
        CheckConcate("পোস্ট অফিস", healthServiceProviderItemNew.getPost_office());
        CheckConcate("পুলিশ স্টেশন", healthServiceProviderItemNew.getPolice_station());
        CheckConcate("বিনামূল্যে সেবা", healthServiceProviderItemNew.getGeneral_free_for());
        CheckConcate("বিনামূল্যে সেবার ধরন", healthServiceProviderItemNew.getGeneral_free_services());


        CheckConcate("ফার্মেসি চিকিৎসা সেবা", healthServiceProviderItemNew.getPharmacy_speciality());
        CheckConcate("ফার্মেসি ফি", healthServiceProviderItemNew.getPharmacy_fee());

        CheckConcate("বিনামূল্যে সেবা", healthServiceProviderItemNew.getGeneral_free_services());
        CheckConcate("সাধারন খরচ", healthServiceProviderItemNew.getGeneral_cost());
        CheckConcate("সাধারন অনন্য তথ্য", healthServiceProviderItemNew.getGeneral_remark());
        CheckConcate("এ্যাম্বুলেন্সের খরচ", healthServiceProviderItemNew.getAmbulance_cost());
        CheckConcate("এ্যাম্বুলেন্সের অনন্য তথ্য", healthServiceProviderItemNew.getAmbulance_remark());
        CheckConcate("মাতৃত্ব জনিত সেবার খরচ", healthServiceProviderItemNew.getMaternity_cost());
        CheckConcate("মাতৃত্ব জনিত সেবার অন্যন্য তথ্য", healthServiceProviderItemNew.getMaternity_remark());
        CheckConcate("জরুরী সেবার খরচ", healthServiceProviderItemNew.getEmergency_cost());
        CheckConcate("জরুরী সেবার অনন্য খরচ", healthServiceProviderItemNew.getEmergency_remark());



        timeProcessing("খোলার সময়", healthServiceProviderItemNew.getOpening_time());
        timeProcessing("বন্ধ করার সময়", healthServiceProviderItemNew.getClosing_time());
        if(!healthServiceProviderItemNew.getBreak_time().equals("null")&&!healthServiceProviderItemNew.getBreak_time().equals(""))
        breakTimeProcessing("বিরতির সময়", healthServiceProviderItemNew.getBreak_time());
        CheckConcate("ছুটির দিন", healthServiceProviderItemNew.getOff_day());
        healthSpecialistItemDetailses = healthSpecialistTableDetails.getHealthSpecialistData(healthServiceProviderItemNew.getId());
        int specialist_size = healthSpecialistItemDetailses.size();

        if (specialist_size != 0) {
            for (HealthSpecialistItemDetails healthSpecialistItemDetails : healthSpecialistItemDetailses) {
                Log.d("It's Specialist", "====" + specialist_size);
             //   result_concate = "";
            //    specialist.setVisibility(View.VISIBLE);
                CheckConcate("বিশেষজ্ঞ ডাক্তারের ধরন ", healthSpecialistItemDetails.getSpecialisttype());
                CheckConcate("ডাক্তারের সংখা", healthSpecialistItemDetails.getSpecialistId());
                CheckConcate("প্রথম ভিজিট ফি", healthSpecialistItemDetails.getSpecialistfees());
                CheckConcate("এক সপ্তাহের মধ্যে ভিজিট ফি", healthSpecialistItemDetails.getWeek_fee());
                CheckConcate("এক মাসের মধ্যে ভিজিট ফি", healthSpecialistItemDetails.getMonth_fee());
                CheckConcate("রিপোর্ট ফি", healthSpecialistItemDetails.getReport_fee());

                specialist.setText(result_concate);

            }


        }
        HealthVaccineTableDetails healthVaccineTableDetails = new HealthVaccineTableDetails(DetailsInfoActivityHealthNew.this);
        healthVaccineItemDetailses = healthVaccineTableDetails.getHealthVaccineData(healthServiceProviderItemNew.getId());
        int healthVaccineSize = healthVaccineItemDetailses.size();
        if (healthVaccineSize != 0) {
            Log.d("It's Vaccine", "====" + healthVaccineSize);
          //  health_vaccine.setVisibility(View.VISIBLE);
           // result_concate = "";

            for (HealthVaccineItemDetails healthVaccineItemDetails : healthVaccineItemDetailses) {
                CheckConcate("ভ্যাকসিনের নাম", healthVaccineItemDetails.getVaccinename());
                CheckConcate("ভ্যাকসিনের ফী", healthVaccineItemDetails.getVaccinefee());
                CheckConcate("ভ্যাকসিনের অন্যন্য তথ্য", healthVaccineItemDetails.getVaccineremarks());

            }

            health_vaccine.setText(result_concate);

        }




        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int compareValue;
                compareValue = SharedPreferencesHelper.getComapreValueHealth(DetailsInfoActivityHealthNew.this);
                if (compareValue >= 2)
                    AlertMessage.showMessage(con, "নতুন তথ্য নেয়া সম্ভব হচ্ছে না",
                            "আপনি ইতিমধ্যে দুটি সেবা নির্বাচিত করেছেন তুলনার জন্য");
                else if (compareValue == 0) {

                    SharedPreferencesHelper.setCompareDataHealth(DetailsInfoActivityHealthNew.this, healthServiceProviderItemNew.getId(), 1);
                } else if (compareValue == 1) {
                    String previous_node;
                    previous_node = SharedPreferencesHelper.getComapreDataHealth(DetailsInfoActivityHealthNew.this);
                    previous_node = previous_node + " " + healthServiceProviderItemNew.getId();
                    SharedPreferencesHelper.setCompareDataHealth(DetailsInfoActivityHealthNew.this, previous_node, 2);
                }


            }
        });

        serviceDetails.setText(result_concate);


        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) upperHand.getLayoutParams();
        //int upperhad_height=params2.height = height/6;

        upperHand.setLayoutParams(params2);


       LinearLayout.LayoutParams params_upperText = (LinearLayout.LayoutParams) upperText.getLayoutParams();
//        // int  vd=params_upperText.height = height/24;
//        // params_upperText.width = width;
        upperText.setLayoutParams(params_upperText);

        LinearLayout.LayoutParams params_left_way = (LinearLayout.LayoutParams) left_way.getLayoutParams();
        int lett_img = params_left_way.height = (height * 3) / 24;
        int right_img = params_left_way.width = width / 3;
        left_way.setLayoutParams(params_left_way);


        top_logo.getLayoutParams().height = width / 8;
        top_logo.getLayoutParams().width = width / 8;

        middle_image.getLayoutParams().height = width / 8;
        middle_image.getLayoutParams().width = width / 8;

        close_button.getLayoutParams().height = width / 13;
        close_button.getLayoutParams().width = width / 13;

        right_image.getLayoutParams().height = width / 8;
        right_image.getLayoutParams().width = width / 8;

        left_image.getLayoutParams().height = width / 8;
        left_image.getLayoutParams().width = width / 8;

        school_logo_default.getLayoutParams().height = width / 5;
        school_logo_default.getLayoutParams().width = width / 5;


        LinearLayout.LayoutParams params_middle_phone = (LinearLayout.LayoutParams) middle_phone.getLayoutParams();
        int vx = params_middle_phone.height = (height * 3) / 24;
        params_middle_phone.width = width / 3;
        middle_phone.setLayoutParams(params_middle_phone);


        LinearLayout.LayoutParams params_right_email = (LinearLayout.LayoutParams) right_email.getLayoutParams();
        int vc = params_right_email.height = (height * 3) / 24;
        params_right_email.width = width / 3;
        right_email.setLayoutParams(params_right_email);

        ups_text = (TextView) findViewById(R.id.ups_text);
        ups_text.setTextSize(width / 25);
        ratingText.setTextSize(width / 25);
        ups_text.setText(healthServiceProviderItemNew.getNode_bn());

        LinearLayout.LayoutParams feedbacks = (LinearLayout.LayoutParams) feedback.getLayoutParams();
        feedbacks.height = width / 8;
        feedbacks.width = width / 8;
        feedback.setLayoutParams(feedbacks);
        feedbacks.setMargins(0, 0, width / 30, 0);

        checkBox.setTextSize(width/25);

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


        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (healthServiceProviderItemNew.getNode_contact2().equals("")) {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                }
            }
        });

        middle_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!healthServiceProviderItemNew.getNode_contact().equals("")) {
                    callIntent1.setData(Uri.parse("tel:" + healthServiceProviderItemNew.getNode_contact()));
                    if (checkPermission())
                        startActivity(callIntent1);
                    else {
                        AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                                "ফোন নম্বর পাওয়া যায়নি");
                        Toast.makeText(getApplicationContext(),
                                "Sorry, Phone call is not possible now. ", Toast.LENGTH_LONG)
                                .show();
                    }
                } else {

                    AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                            "ফোন নম্বর পাওয়া যায়নি");
                    Toast.makeText(getApplicationContext(),
                            "Sorry, Phone call is not possible now. ", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
//        distance_left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {
//
//
//                    String lat = educationServiceProviderItem.getLatitude().toString();
//                    // double latitude = Double.parseDouble(lat);
//                    String lon = educationServiceProviderItem.getLongitude().toString();
//                    // double longitude = Double.parseDouble(lon);
//                    String name= educationServiceProviderItem.getEduNameBan().toString();
//                    String node=educationServiceProviderItem.getIdentifierId();
//                    boolean fromornot=true;
//                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putString("Latitude", lat);
//                    editor.putString("Longitude", lon);
//                    editor.putString("Name", name);
//                    editor.putBoolean("Value", fromornot);
//                    editor.putString("nValue", node);
//                    editor.commit();
//
//
//                    String Longitude = pref.getString("Longitude", null);
//                    String Latitude = pref.getString("Latitude", null);
//
//                    if (Latitude != null && Longitude != null) {
//                        Double Lon = Double.parseDouble(Longitude);
//                        Double Lat = Double.parseDouble(Latitude);
//                        // implementFragment();
//                        //username and password are present, do your stuff
//                    }
//
//
//                    finish();
//
//                }
//                else if(!AppUtils.displayGpsStatus(getApplicationContext())){
//
//                    AppUtils.showSettingsAlert(DetailsInfoActivityEducation.this);
//
//                }
//
//                else
//                {
//
//                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityEducation.this, AlertDialog.THEME_HOLO_LIGHT).create();
//                    alertDialog.setTitle("ইন্টারনেট সংযোগ বিচ্চিন্ন ");
//                    alertDialog.setMessage(" দুঃখিত আপনার ইন্টারনেট সংযোগটি সচল নয়। \n পথ দেখতে চাইলে অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি সচল করুন।  ");
//                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    alertDialog.show();
//
//                }
//            }
//        });
//
//
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void setRatingBar()
    {
        getRequest(DetailsInfoActivityHealthNew.this, "http://kolorob.net/demo/api/get_sp_rating/health", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {
                        if (status == AppConstants.SUCCESS_CODE) {
                            try {
                                JSONArray jo = new JSONArray(apiContent);
                                int size= jo.length();
                                for(int i=0;i<size;i++)
                                {
                                    JSONObject ratingH=jo.getJSONObject(i);
                                    String id= ratingH.getString("id");
                                    if(id.equals(healthServiceProviderItemNew.getId()))
                                    {


                                        rating=Float.parseFloat(ratingH.getString("avg"));
                                        ratingBar.setRating(rating);
                                        break;

                                    }


                                }





                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }

    public void verifyRegistration(View v) {

        String  register = SharedPreferencesHelper.getNumber(DetailsInfoActivityHealthNew.this);
        phone_num=register;

        if (register.equals("")) {
            requestToRegister();
        } else {

            feedBackAlert();
          //  sendReviewToServer();
        }


    }

    public void feedBackAlert() {

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityHealthNew.this);
        final View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityHealthNew.this);
        alertDialogBuilder.setView(promptView);


        final Button submit = (Button) promptView.findViewById(R.id.submit);


        final AlertDialog alert;
        alert = alertDialogBuilder.create();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               feedback_comment=(EditText)promptView.findViewById(R.id.feedback_comment);
                feedRadio=(RadioGroup)promptView.findViewById(R.id.feedRadio);
                int selected = feedRadio.getCheckedRadioButtonId();
                rb1 = (RadioButton)promptView.findViewById(selected);
                status = rb1.getText().toString();
              //  declareRadiobutton();
                sendReviewToServer();

                alert.cancel();

            }
        });
        alertDialogBuilder.setCancelable(false);


        alert.show();
    }


    public void sendReviewToServer() {
        int rating=0;
        if (status.equals("খুবই অসন্তুষ্ট"))
            rating = 1;
        else if (status.equals("অসন্তুষ্ট"))
            rating = 2;
        else if (status.equals("বিশেষ অনুভূতি নেই"))

            rating = 3;
        else if (status.equals("সন্তুষ্ট "))

            rating =4;
        else if (status.equals("খুবই সন্তুষ্ট"))

            rating = 5;

        String comment="";
        comment=feedback_comment.getText().toString();
        Log.d("status ","======"+status);
        String url = "http://kolorob.net/demo/api/sp_rating/"+healthServiceProviderItemNew.getId()+"?"+"phone=" +phone_num +"&review=" +comment+ "&rating="+rating+"&username="+username+"&password="+password+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsInfoActivityHealthNew.this, response, Toast.LENGTH_SHORT).show();
                        Log.d("========", "status " + response);
                        try {


                            if (response.equals("true")) {
                                AlertMessage.showMessage(DetailsInfoActivityHealthNew.this, "মতামতটি গ্রহন করা হয়েছে",
                                        "মতামত প্রদান করার জন্য আপনাকে ধন্যবাদ করার জন্য আপনাকে ধন্যবাদ");
                            } else
                                AlertMessage.showMessage(DetailsInfoActivityHealthNew.this, "মতামতটি গ্রহন করা হয় নি",
                                        "অনুগ্রহ পূর্বক পুনরায় চেস্টা করুন।");


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsInfoActivityHealthNew.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(DetailsInfoActivityHealthNew.this);
        requestQueue.add(stringRequest);
    }


    public void requestToRegister() {
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityHealthNew.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityHealthNew.this);
        alertDialogBuilder.setView(promptView);


        final ImageView yes = (ImageView) promptView.findViewById(R.id.yes);
        final ImageView no = (ImageView) promptView.findViewById(R.id.no);

        final AlertDialog alert = alertDialogBuilder.create();


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration = new Intent(DetailsInfoActivityHealthNew.this, PhoneRegActivity.class);
                alert.cancel();
                startActivity(intentPhoneRegistration);

            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();

            }
        });
     //   setup a dialog window
        alertDialogBuilder.setCancelable(false);


        alert.show();
    }



    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }

    }

    public String English_to_bengali_number_conversion(String english_number) {
        int v = english_number.length();
        String concatResult = "";
        for (int i = 0; i < v; i++) {
            if (english_number.charAt(i) == '1')
                concatResult = concatResult + "১";
            else if (english_number.charAt(i) == '2')
                concatResult = concatResult + "২";
            else if (english_number.charAt(i) == '3')
                concatResult = concatResult + "৩";
            else if (english_number.charAt(i) == '4')
                concatResult = concatResult + "৪";
            else if (english_number.charAt(i) == '5')
                concatResult = concatResult + "৫";
            else if (english_number.charAt(i) == '6')
                concatResult = concatResult + "৬";
            else if (english_number.charAt(i) == '7')
                concatResult = concatResult + "৭";
            else if (english_number.charAt(i) == '8')
                concatResult = concatResult + "৮";
            else if (english_number.charAt(i) == '9')
                concatResult = concatResult + "৯";
            else if (english_number.charAt(i) == '0')
                concatResult = concatResult + "০";
        }
        return concatResult;
    }

    public Boolean RegisteredOrNot() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
       // editor.putString("registered", lat);
        registered = pref.getString("registered", null);
       // phone_num = pref.getString("phone", null);
        editor.commit();
        if (registered.equals("yes"))
            return true;
        else
            return true;
    }

    private String timeConverter(String time) {


        String timeInBengali = "";

        try
        {

            String[] separated = time.split(":");


            int hour = Integer.valueOf(separated[0]);
            int times = Integer.valueOf(separated[1]);

            if (hour >= 6 && hour < 12)
                timeInBengali = "সকাল " + English_to_bengali_number_conversion(String.valueOf(hour));
            else if (hour == 12)
                timeInBengali = "দুপুর  " + English_to_bengali_number_conversion(String.valueOf(hour));
            else if (hour > 12 && hour < 16)
                timeInBengali = "দুপুর  " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 15 && hour < 18)
                timeInBengali = "বিকেল " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 17 && hour < 20)
                timeInBengali = "সন্ধ্যা " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 20)
                timeInBengali = "রাত " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            if (times != 0)
                timeInBengali = timeInBengali + " টা " + English_to_bengali_number_conversion(String.valueOf(times)) + " মিনিট";
            else
                timeInBengali = timeInBengali + " টা";
        }
        catch (Exception e)
        {

        }

        return timeInBengali;

    }
    private void breakTimeProcessing(String value1, String value2) {
        if (!value2.equals("null") || !value2.equals(", ")) {

            String timeInBengali = "";
            try {
                value2 = value2 + ",";

                String[] breakTIme = value2.split(",");


                String[] realTIme = breakTIme[0].split("-");


                value2 = timeConverter(realTIme[0]) + " থেকে " + timeConverter(realTIme[1]);
                CheckConcate(value1, value2);
            }
            catch (Exception e)
            {
                //result_concate="n/a";
            }

        }
    }


    private void timeProcessing(String value1, String value2) {
        if (!value2.equals("null") || value2.equals("")) {
            String GetTime = timeConverter(value2);
            CheckConcate(value1, GetTime);

        }
    }

    private void CheckConcate(String value1, String value2) {


        if (!value2.equals("null") && !value2.equals("")) {

            String value = "      " + value1 + ":  " + value2;
            result_concate = result_concate + value + "\n";
        }


    }


}
