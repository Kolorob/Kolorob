package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.DefaultAdapter;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTableDetails;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccineTableDetails;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItemDetails;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccineItemDetails;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

/**
 * Created by arafat on 28/05/2016.
 */

public class DetailsInfoActivityHealthNew extends AppCompatActivity {
    Dialog dialog;
    LinearLayout upperHand, upperText, left_way, middle_phone, right_email, bottom_bar, linearLayout;
    ImageView left_image, middle_image, right_image, email_btn;
    TextView address_text, phone_text, email_text;
    int width, height;
    TextView ups_text;
    ListView courseListView, listView;
    Context con;
    int increment=0;
    String[] key;
    String[] value;
    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";
    Float rating;
    HealthServiceProviderItemNew healthServiceProviderItemNew;

    ArrayList<HealthSpecialistItemDetails> healthSpecialistItemDetailses;
    ArrayList<HealthVaccineItemDetails> healthVaccineItemDetailses;

    private TextView ratingText;

    private ImageView close_button, phone_mid, distance_left, feedback, top_logo, cross, school_logo_default;
    private RadioGroup feedRadio;
    RadioButton rb1;
    String status = "", phone_num = "", registered = "";

    private CheckBox checkBox;
    EditText feedback_comment;
    ListView alldata;
    RatingBar ratingBar;
    private String compare_Data="";
    int compareValue;
    private Double screenSize;
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
    String datevalue,datevaluebn;
    long dateval;
    TextView toastMessage;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_health_new);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        int densityDpi = (displayMetrics.densityDpi);
        con = this;

        screenSize = AppUtils.ScreenSize(this);

        Log.d("Screen Size ", "&&&&&&" + screenSize);


        Intent intent = getIntent();
        //  declareRadiobutton();

        if (null != intent) {
            healthServiceProviderItemNew = (HealthServiceProviderItemNew) intent.getSerializableExtra(AppConstants.KEY_DETAILS_HEALTH_NEW);
            // Log.d("CheckDetailsHealth","======"+healthServiceProviderItemNew);
        }


        key = new String[600];

        value = new String[600];
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

        ratingText = (TextView) findViewById(R.id.ratingText);

        close_button = (ImageView) findViewById(R.id.close_buttonc);




        top_logo = (ImageView) findViewById(R.id.top_logo);
        //cross=(ImageView)findViewById(R.id.cross_jb);
//        school_logo_default = (ImageView) findViewById(R.id.service_logo);


        distance_left = (ImageView) findViewById(R.id.distance_left);
        email_btn = (ImageView) findViewById(R.id.right_side_email);
        feedback = (ImageView) findViewById(R.id.feedback);
        checkBox = (CheckBox) findViewById(R.id.compare);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        if(width<500)
            ratingBar = new RatingBar(this, null, android.R.attr.ratingBarStyleSmall);
        float k=ratingBar.getRating();
//        RatingBar ratingBar = new RatingBar(context, null, android.R.attr.ratingBarStyleSmall);

        setRatingBar();
        CheckConcate("Road", healthServiceProviderItemNew.getRoad());
        CheckConcate("Line ", healthServiceProviderItemNew.getLine());
        CheckConcate("Avenue", healthServiceProviderItemNew.getAvenue());
        CheckConcate("Block", healthServiceProviderItemNew.getBlock());
        CheckConcate("Address", healthServiceProviderItemNew.getAddress());

        CheckConcate("House Name", healthServiceProviderItemNew.getHouse_name());
        CheckConcate("Floor", healthServiceProviderItemNew.getFloor());
        CheckConcate("Closest Landmark ", healthServiceProviderItemNew.getLandmark());
        CheckConcate("Opening Time", healthServiceProviderItemNew.getOpening_time());
        CheckConcate("Break Time", healthServiceProviderItemNew.getBreak_time());

        CheckConcate("Closing Time", healthServiceProviderItemNew.getClosing_time());

        CheckConcate("Closed on", healthServiceProviderItemNew.getOff_day());
        CheckConcate("Health Center Type", healthServiceProviderItemNew.getInstitute_type());
        CheckConcate("Number of beds available", healthServiceProviderItemNew.getCapacity());
        CheckConcate("Number of Male doctors", healthServiceProviderItemNew.getMale_doctors());
        CheckConcate("Number of Female doctors", healthServiceProviderItemNew.getFemale_doctors());
        CheckConcate("Doctor-Patient Ratio", healthServiceProviderItemNew.getPatient_doctor_ratio());
        CheckConcate("Patient-Nurse Ratio", healthServiceProviderItemNew.getPatient_nurse_ratio());



        CheckConcate("Free Medical Service", healthServiceProviderItemNew.getGeneral_free_for());
        CheckConcate("Free Medical Service Type", healthServiceProviderItemNew.getGeneral_free_services());



        CheckConcate("Pharmacy Speciality", healthServiceProviderItemNew.getPharmacy_speciality());
        CheckConcate("Pharmacy Fee", healthServiceProviderItemNew.getPharmacy_fee()+" BDT");

        CheckConcate("Free Service", healthServiceProviderItemNew.getGeneral_free_services());

        CheckConcate("General Cost", healthServiceProviderItemNew.getGeneral_cost()+" BDT");
        CheckConcate("Other Information", healthServiceProviderItemNew.getGeneral_remark());
        CheckConcate("Ambulance Cost", healthServiceProviderItemNew.getAmbulance_cost()+" BDT");
        CheckConcate("Ambulance Remark", healthServiceProviderItemNew.getAmbulance_remark());
        CheckConcate("Maternity Cost", healthServiceProviderItemNew.getMaternity_cost()+" BDT");
        CheckConcate("Maternity Remarks", healthServiceProviderItemNew.getMaternity_remark());
        CheckConcate("Emergency Cost", healthServiceProviderItemNew.getEmergency_cost()+" BDT");
        CheckConcate("Emergency Remarks", healthServiceProviderItemNew.getEmergency_remark());




        healthSpecialistItemDetailses = healthSpecialistTableDetails.getHealthSpecialistData(healthServiceProviderItemNew.getId());
        int specialist_size = healthSpecialistItemDetailses.size();

        if (specialist_size != 0) {
            for (HealthSpecialistItemDetails healthSpecialistItemDetails : healthSpecialistItemDetailses) {

                //   result_concate = "";
                //    specialist.setVisibility(View.VISIBLE);
                CheckConcate("Specialist Type", healthSpecialistItemDetails.getSpecialisttype());
                CheckConcate("Doctor Number", healthSpecialistItemDetails.getSpecialistId());
                CheckConcate("Specialist Fees",healthSpecialistItemDetails.getSpecialistfees()+ " BDT");
                CheckConcate("Visit Fee(within first week)", healthSpecialistItemDetails.getWeek_fee()+" BDT");
                CheckConcate("Visit Fee(within first month)", healthSpecialistItemDetails.getMonth_fee()+" BDT");
                CheckConcate("Report Fee", healthSpecialistItemDetails.getReport_fee()+" BDT");



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
                CheckConcate("Vaccine Name", healthVaccineItemDetails.getVaccinename());
                CheckConcate("Vaccine Cost", healthVaccineItemDetails.getVaccinefee()+" BDT");
                CheckConcate("Vaccine Remarks", healthVaccineItemDetails.getVaccineremarks());

            }



        }




        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        compare_Data=SharedPreferencesHelper.getComapreDataHealth(DetailsInfoActivityHealthNew.this);
        compareValue = SharedPreferencesHelper.getComapreValueHealth(DetailsInfoActivityHealthNew.this);
        String multipule[]= compare_Data.split(",");


        if(compareValue==1&&compare_Data.equals(healthServiceProviderItemNew.getId()))
        {

            checkBox.setChecked(true);
        }
        else if(compareValue==2&&(multipule[0].equals(healthServiceProviderItemNew.getId())||multipule[1].equals(healthServiceProviderItemNew.getId())))
        {

            checkBox.setChecked(true);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                compareValue = SharedPreferencesHelper.getComapreValueHealth(DetailsInfoActivityHealthNew.this);



                if (compareValue >= 2)
                {
                    if(isChecked)
                    {

                        String new_compare_Data="";
                        compare_Data=SharedPreferencesHelper.getComapreDataHealth(DetailsInfoActivityHealthNew.this);
                        String multipule[]= compare_Data.split(",");
                        new_compare_Data = multipule[1]+","+healthServiceProviderItemNew.getId();
                        SharedPreferencesHelper.setCompareDataHealth(DetailsInfoActivityHealthNew.this, new_compare_Data, 2);
                    }
                    else
                    {
                        String compare_Data="";
                        String new_compare_Data="";
                        compare_Data=SharedPreferencesHelper.getComapreDataHealth(DetailsInfoActivityHealthNew.this);
                        String multipule[]= compare_Data.split(",");
                        new_compare_Data = multipule[0];
                        SharedPreferencesHelper.setCompareDataHealth(DetailsInfoActivityHealthNew.this, new_compare_Data, 1);
                    }

                }
                else if (compareValue == 0) {
                    if(isChecked)
                        SharedPreferencesHelper.setCompareDataHealth(DetailsInfoActivityHealthNew.this, healthServiceProviderItemNew.getId(), 1);

                }
                else if (compareValue == 1) {

                    if(isChecked)
                    {
                        Log.d("Delete in 1 value","$$$$$$"+compareValue);
                        String previous_node;
                        previous_node = SharedPreferencesHelper.getComapreDataHealth(DetailsInfoActivityHealthNew.this);
                        previous_node = previous_node + "," + healthServiceProviderItemNew.getId();
                        SharedPreferencesHelper.setCompareDataHealth(DetailsInfoActivityHealthNew.this, previous_node, 2);

                    }
                    else
                    {
                        Log.d("Delete in 1 value","$$$$$$"+compareValue);
                        SharedPreferencesHelper.setCompareDataHealth(DetailsInfoActivityHealthNew.this,"",0);

                    }

                }


            }
        });




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

//        school_logo_default.getLayoutParams().height = width / 5;
//        school_logo_default.getLayoutParams().width = width / 5;
        SharedPreferences settings = DetailsInfoActivityHealthNew.this.getSharedPreferences("prefs", 0);
        Date date2 = new Date(settings.getLong("time", 0));
        Date today=new Date();
        long diffInMillisec = today.getTime() - date2.getTime();

        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        if (diffInDays==0) datevalue=" (Updated Today)";
        else
        {
            dateval=diffInDays;
            if (dateval>30) datevalue=" ( Old information)";
            else
                datevalue=" ( Information of" + datevaluebn + " days ago)";
        }
        LayoutInflater inflater = getLayoutInflater();

        View toastView = inflater.inflate(R.layout.toast_view,null);
        toast = new Toast(this);
        // Set the Toast custom layout
        toast.setView(toastView);


        //   View toastView = toast.getView(); //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */



       toastMessage = (TextView) toastView.findViewById(R.id.toasts);
        toastMessage.setTextSize(25);
        toastMessage.setText(datevalue);


        toastMessage.setTextColor(getResources().getColor(R.color.orange));
        //  toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.kolorob_logo, 0, 0, 0);
        // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        toastMessage.setGravity(Gravity.CENTER);
        toastMessage.setCompoundDrawablePadding(26);
        //  toastView.setBackgroundColor(getResources().getColor(R.color.orange));
        toast.show();

        LinearLayout.LayoutParams params_middle_phone = (LinearLayout.LayoutParams) middle_phone.getLayoutParams();
        int vx = params_middle_phone.height = (height * 3) / 24;
        params_middle_phone.width = width / 3;
        middle_phone.setLayoutParams(params_middle_phone);


        LinearLayout.LayoutParams params_right_email = (LinearLayout.LayoutParams) right_email.getLayoutParams();
        int vc = params_right_email.height = (height * 3) / 24;
        params_right_email.width = width / 3;
        right_email.setLayoutParams(params_right_email);

        ups_text = (TextView) findViewById(R.id.ups_text);
        ups_text.setTextSize(25);
        ratingText.setTextSize(23);
        ups_text.setText(healthServiceProviderItemNew.getNode_bn());

        LinearLayout.LayoutParams feedbacks = (LinearLayout.LayoutParams) feedback.getLayoutParams();
        feedbacks.height = width / 8;
        feedbacks.width = width / 8;
        feedback.setLayoutParams(feedbacks);
        feedbacks.setMargins(0, 0, width / 30, 0);

        checkBox.setTextSize(width/25);

//
        alldata=(ListView)findViewById(R.id.allData);

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) alldata.getLayoutParams();

        mlp.setMargins(width/100,0,width/990,width/8);

        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (healthServiceProviderItemNew.getNode_contact2().equals("")) {
                    AlertMessage.showMessage(con, "Not possible to e-mail",
                            "Email-id not found");
                }
                else{
                    Helpes.sendEmail(DetailsInfoActivityHealthNew.this, healthServiceProviderItemNew.getNode_contact2());
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

                        Toast.makeText(getApplicationContext(),
                                "Sorry, Phone call is not possible now. ", Toast.LENGTH_LONG)
                                .show();
                    }
                } else {

                    AlertMessage.showMessage(con, "Sorry",
                            "Phone call is not possible now");

                }
            }
        });


        DefaultAdapter defaultAdapter= new DefaultAdapter(this,key,value,increment);
        alldata.setAdapter(defaultAdapter);
        distance_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {


                    String lat = healthServiceProviderItemNew.getLat().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = healthServiceProviderItemNew.getLon().toString();
                    // double longitude = Double.parseDouble(lon);
                    String name= healthServiceProviderItemNew.getNode_bn().toString();
                    String node=String.valueOf(healthServiceProviderItemNew.getId());
                    boolean fromornot=true;
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Latitude", lat);
                    editor.putString("Longitude", lon);
                    editor.putString("Name", name);
                    editor.putBoolean("Value", fromornot);
                    editor.putString("nValue", node);

                    editor.commit();


                    String Longitude = pref.getString("Longitude", null);
                    String Latitude = pref.getString("Latitude", null);

                    if (Latitude != null && Longitude != null) {
                        Double Lon = Double.parseDouble(Longitude);
                        Double Lat = Double.parseDouble(Latitude);
                        // implementFragment();
                        //username and password are present, do your stuff
                    }


                    Intent intentJ = new Intent(DetailsInfoActivityHealthNew.this,MapFragmentRouteOSM.class);
                    startActivity(intentJ);

                }
                else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                    AppUtils.showMessage(con, "GPS is off!",
                            "Do you want to activate GPS?");


                }

                else
                {

                    AlertMessage.showMessage(con, "Sorry।",
                            "Please activate your internet to see route");
//
//                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityHealthNew.this, AlertDialog.THEME_HOLO_LIGHT).create();
//                    alertDialog.setTitle("ইন্টারনেট সংযোগ বিচ্চিন্ন ");
//                    alertDialog.setMessage(" দুঃখিত আপনার ইন্টারনেট সংযোগটি সচল নয়। \n পথ দেখতে চাইলে অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি সচল করুন।  ");
//                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    alertDialog.show();

                }


            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }


    public void setRatingBar()
    {
//        getRequest(DetailsInfoActivityHealthNew.this, "http://kolorob.net/demo/api/get_sp_rating/health?username=kolorobapp&password=2Jm!4jFe3WgBZKEN", new VolleyApiCallback() {
//                    @Override
//                    public void onResponse(int status, String apiContent) {
//                        if (status == AppConstants.SUCCESS_CODE) {
//                            try {
//                                JSONArray jo = new JSONArray(apiContent);
//                                int size= jo.length();
//
//                                for(int i=0;i<size;i++)
//                                {
//                                    JSONObject ratingH=jo.getJSONObject(i);
//                                    String id= ratingH.getString("id");
//                                    if(id.equals(healthServiceProviderItemNew.getId()))
//                                    {
//                                        Log.d("$$$$$$", "id " + id);
//
//                                        rating=Float.parseFloat(ratingH.getString("avg"));
        try {
            ratingBar.setRating(Float.parseFloat(healthServiceProviderItemNew.getRating()));
        }
        catch (Exception e)
        {

        }
//                                        break;
//
//                                    }
//
//
//                                }
//
//
//
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//        );
    }

    public void verifyRegistration(View v) {

            feedBackAlert();
            //  sendReviewToServer();



    }

    public void feedBackAlert() {

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityHealthNew.this);
        final View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        final Dialog alertDialog = new Dialog(DetailsInfoActivityHealthNew.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();



        final Button submit = (Button) promptView.findViewById(R.id.submit);
        final Button close = (Button) promptView.findViewById(R.id.btnclose);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback_comment=(EditText)promptView.findViewById(R.id.feedback_comment);
                feedRadio=(RadioGroup)promptView.findViewById(R.id.feedRadio);
                int selected = feedRadio.getCheckedRadioButtonId();
                rb1 = (RadioButton)promptView.findViewById(selected);
                status = rb1.getText().toString();
                //  declareRadiobutton();
                //sendReviewToServer();
                toastMessage.setText("This is dummy feedback. This wont be submitted to server.Thanks!");


                toastMessage.setTextColor(getResources().getColor(R.color.orange));

                //  toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.kolorob_logo, 0, 0, 0);
                // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

                toastMessage.setGravity(Gravity.CENTER);
                toastMessage.setCompoundDrawablePadding(26);
                //  toastView.setBackgroundColor(getResources().getColor(R.color.orange));
                toast.show();
                alertDialog.cancel();

            }
        });
        alertDialog.setCancelable(false);


        alertDialog.show();
    }


    public void sendReviewToServer() {
        int rating;
        if(status.equals(getString(R.string.feedback1)))
            rating= 1;
        else if(status.equals(getString(R.string.feedback2)))
            rating=  2;
        else if(status.equals(getString(R.string.feedback3)))
            rating= 3;
        else if(status.equals(getString(R.string.feedback4)))
            rating=  4;
        else
            rating= 5;


        String comment="";
        comment=feedback_comment.getText().toString();
        Log.d("status ","======"+status);
        String url = "http://kolorob.net/demo/api/sp_rating/"+healthServiceProviderItemNew.getId()+"?"+"phone=" +phone_num +"&review=" +comment.replace(' ','+')+ "&rating="+rating+"&username="+username+"&password="+password+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("========", "status " + response);
                        try {


                            if (response.equals("true")) {
                                AlertMessage.showMessage(DetailsInfoActivityHealthNew.this, "মতামতটি গ্রহন করা হয়েছে",
                                        "মতামত প্রদান করার জন্য আপনাকে ধন্যবাদ");
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


        final Dialog alertDialog = new Dialog(DetailsInfoActivityHealthNew.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final ImageView yes = (ImageView) promptView.findViewById(R.id.yes);
        final ImageView no = (ImageView) promptView.findViewById(R.id.no);
        final TextView textAsk=(TextView)promptView.findViewById(R.id.textAsk);
        String text="    You need to      "+"\n"+"     Register first      "+"\n"+"   Do you want to?    ";
        textAsk.setText(text);
        if(SharedPreferencesHelper.isTabletDevice(DetailsInfoActivityHealthNew.this))
            textAsk.setTextSize(23);
        else
            textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration = new Intent(DetailsInfoActivityHealthNew.this, PhoneRegActivity.class);
                alertDialog.cancel();
                startActivity(intentPhoneRegistration);

            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

            }
        });
        //   setup a dialog window
        alertDialog.setCancelable(false);


        alertDialog.show();
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
        if(english_number.equals("null")||english_number.equals(""))
            return english_number;
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
            else if (english_number.charAt(i) == '.')
                concatResult = concatResult + ".";
            else if(english_number.charAt(i) == '/')
                concatResult = concatResult + "/";
            else {
                return english_number;
            }

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

            if (hour ==0 && times==0)
                timeInBengali = "12 AM";
            else if (hour >= 6 && hour < 12)
                timeInBengali = String.valueOf(hour)+" AM";
            else if (hour == 12)
                timeInBengali = String.valueOf(hour)+" Noon";
            else if (hour > 12 && hour < 16)
                timeInBengali = String.valueOf(hour - 12)+" PM (Noon)";
            else if (hour > 15 && hour < 18)
                timeInBengali = String.valueOf(hour - 12) + " PM (Afternoon)";
            else if (hour > 17 && hour < 20)
                timeInBengali = String.valueOf(hour - 12)+" PM (Evening)";
            else if (hour > 20)
                timeInBengali = String.valueOf(hour - 12)+" PM(Night)";
            if (times != 0)
                timeInBengali = timeInBengali + " O clock and " + String.valueOf(times) + " Minutes";
            else
                timeInBengali = timeInBengali + " ";
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


                value2 = timeConverter(realTIme[0]) + " to " + timeConverter(realTIme[1]);
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
    private void CheckConcate(String value1,String value2){

        if(value1.equals("Email")||value1.equals("Web site"))
        {
            key[increment] = value1;
            value[increment] = value2;
        }
        else {
            if (!value2.equals("null") && !value2.equals("")&&!value2.equals(" BDT")) {

                    {
                        key[increment] = value1;
                        value[increment] = AppUtils.Check_Capitalization(value2);
                    }


                increment++;

            }
        }
    }


}