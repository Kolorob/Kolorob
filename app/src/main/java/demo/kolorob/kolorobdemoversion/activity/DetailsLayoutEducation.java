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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTrainingDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTuitionDetailsTable;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTrainingDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTuitionDetailsItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

/**
 * Created by israt.jahan on 7/17/2016.
 */
public class DetailsLayoutEducation extends Activity {
    Dialog dialog;
    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";
    LinearLayout upperHand, upperText, left_way, middle_phone, right_email, bottom_bar, linearLayout;
    ImageView left_image, middle_image, right_image, email_btn;
    TextView address_text, phone_text, email_text;
    int width, height;
    TextView ups_text;

    ListView courseListView, listView;
    Context con;
    EducationNewItem educationNewItem;
    RatingBar ratingBar;

    ArrayList<EducationTuitionDetailsItem> educationTuitionDetailsItems;
    ArrayList<EducationTrainingDetailsItem> educationTrainingDetailsItems;
    ArrayList<EducationResultItemNew> educationResultItemNews;
    private TextView totalStudents;
    private TextView totalClasses;
    private TextView totalTeachers;
    private TextView playground;
    private TextView hostel;
    private TextView transport;
    private TextView ratingText;
    private TextView serviceDetails, result, training, tuition;
    private ImageView close_button, phone_mid, distance_left, feedback, top_logo, cross, school_logo_default;
    RadioGroup feedRadio;
    RadioButton rb1, rb2, rb3;
    String status = "", phone_num = "", registered = "";
    String result_concate = "";
    private CheckBox checkBox;
    EditText feedback_comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout_education);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        con = this;
        setRatingBar();

        Intent intent = getIntent();


        if (null != intent) {
            educationNewItem = (EducationNewItem) intent.getSerializableExtra(AppConstants.KEY_DETAILS_EDU);
            // Log.d("CheckDetailsHealth","======"+healthServiceProviderItemNew);
        }


        EducationTuitionDetailsTable educationTuitionDetailsTable = new EducationTuitionDetailsTable(DetailsLayoutEducation.this);
        EducationTrainingDetailsTable educationTrainingDetailsTable = new EducationTrainingDetailsTable(DetailsLayoutEducation.this);
        EducationResultDetailsTable educationResultDetailsTable = new EducationResultDetailsTable(DetailsLayoutEducation.this);


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
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);


        top_logo = (ImageView) findViewById(R.id.top_logo);

        school_logo_default = (ImageView) findViewById(R.id.service_logo);


        distance_left = (ImageView) findViewById(R.id.distance_left);
        email_btn = (ImageView) findViewById(R.id.right_side_email);
        feedback = (ImageView) findViewById(R.id.feedback);
        checkBox = (CheckBox) findViewById(R.id.compare);


        CheckConcate("প্রতিষ্ঠানের ধরণ ", educationNewItem.getEdtype());
        CheckConcate("শাখা", educationNewItem.getShift());
        CheckConcate("ছাত্রছাত্রী সংখ্যা", educationNewItem.getStudentno());
        CheckConcate("শিক্ষক সংখ্যা", educationNewItem.getTeachersno());
        CheckConcate("ক্লাস সংখ্যা", educationNewItem.getClassno());
        CheckConcate("অন্যান্য তথ্য", educationNewItem.getAdditional());
        CheckConcate("ছাত্র সংখ্যা", educationNewItem.getMalestudent());
        CheckConcate("ছাত্রী সংখ্যা", educationNewItem.getFemalestudent());

        CheckConcate("বিশেষ সুবিধা", educationNewItem.getSpecialneeds());
        CheckConcate("বাথরুম সংখ্যা", educationNewItem.getWashroom_no());
        CheckConcate("ছেলেদের বাথরুম", educationNewItem.getWashroom_male());
        CheckConcate("বাথরুমের অবস্থা", educationNewItem.getWashroomcleanliness());
        CheckConcate("খাবার পানির অবস্থা", educationNewItem.getWatercondition());
        CheckConcate("খাবার পানির উৎস", educationNewItem.getWatersource());
        CheckConcate("গড় ছাত্রছাত্রী", educationNewItem.getAveragestudent());
        CheckConcate("মেয়েদের বাথরুম ", educationNewItem.getWashroomfemale());

        CheckConcate("পরিচিত স্থান", educationNewItem.getLandmark());
        CheckConcate("ঠিকানা", educationNewItem.getAddress());
        CheckConcate("ফ্লোর", educationNewItem.getFloor());
        CheckConcate("বাড়ির নাম", educationNewItem.getHousename());
        CheckConcate("রাস্তা", educationNewItem.getRoad());
        CheckConcate("লাইন ", educationNewItem.getLine());
        CheckConcate("এভিনিউ", educationNewItem.getAvenue());
        CheckConcate("পোস্ট অফিস", educationNewItem.getPostoffice());
        CheckConcate("পুলিশ স্টেশন", educationNewItem.getPolicestation());

        CheckConcate("যোগাযোগ", educationNewItem.getNode_contact());
        CheckConcate("যোগাযোগ", educationNewItem.getNode_contact2());
        CheckConcate("ইমেইল", educationNewItem.getNode_email());
        CheckConcate("ওয়েব সাইট", educationNewItem.getNode_website());
        CheckConcate("ফেসবুক", educationNewItem.getNode_facebook());
        CheckConcate("তথ্যপ্রদান কারীর পদবী", educationNewItem.getNode_designation());


        timeProcessing("খোলার সময়", educationNewItem.getOpeningtime());
        timeProcessing("বন্ধে সময়", educationNewItem.getClosetime());
        if(!educationNewItem.getBreaktime().equals("null")&&!educationNewItem.getBreaktime().equals(""))
        breakTimeProcessing("বিরতির সময়", educationNewItem.getBreaktime());
        CheckConcate("কবে বন্ধ থাকে", educationNewItem.getOffday());
        CheckConcate("রেজিস্ট্রেশন নাম্বার", educationNewItem.getRegisterednumber());
        CheckConcate("কাদের সাথে রেজিস্টার্ড ", educationNewItem.getRegisteredwith());
        educationResultItemNews = educationResultDetailsTable.getResultInfo(educationNewItem.getEduId());
        int result_size = educationResultItemNews.size();
        if (result_size != 0) {
            for (EducationResultItemNew educationResultItemNew : educationResultItemNews) {
                //result_concate="";

                CheckConcate("পরীক্ষা নাম", educationResultItemNew.getExamname());
                CheckConcate("ছাত্রছাত্রী সংখ্যা", educationResultItemNew.getStudentno());
                CheckConcate("পাশ করেছে এমন ছাত্রছাত্রী", educationResultItemNew.getPassed());
                CheckConcate("গোল্ডেন এ", educationResultItemNew.getGoldena());
                CheckConcate("জিপিএ ৫", educationResultItemNew.getAplus());


            }


        }
        educationTrainingDetailsItems = educationTrainingDetailsTable.gettrainingInfo(educationNewItem.getEduId());
        int training_size = educationTrainingDetailsItems.size();
        if (training_size != 0) {
            for (EducationTrainingDetailsItem educationTrainingDetailsItem : educationTrainingDetailsItems) {


                CheckConcate("কত মাসের কোর্স", educationTrainingDetailsItem.getCourseduration());
                CheckConcate("ভর্তি (মাস)", educationTrainingDetailsItem.getAdmissionmonth());
                CheckConcate("খরচ", educationTrainingDetailsItem.getCost());
                CheckConcate("ধরন", educationTrainingDetailsItem.getTrainingnametype());
                CheckConcate("ট্রেনিং এর নাম", educationTrainingDetailsItem.getTrainingnamesubtype());


            }
        }

        educationTuitionDetailsItems = educationTuitionDetailsTable.gettuitionInfo(educationNewItem.getEduId());
        int tuition_size = educationTuitionDetailsItems.size();
        if (tuition_size != 0) {
            for (EducationTuitionDetailsItem educationTuitionDetailsItem : educationTuitionDetailsItems) {
                //result_concate="";

                CheckConcate("কোন ক্লাস পড়ান হয়", educationTuitionDetailsItem.getTuitionlevel());
                CheckConcate("খরচ", educationTuitionDetailsItem.getTuitionfree());
                CheckConcate("বৃত্তি সুবিধা", educationTuitionDetailsItem.getTuitionstipendfacility());
                CheckConcate("বৃত্তি সুবিধার ধরন", educationTuitionDetailsItem.getTuitionstipendtype());
                CheckConcate("পড়া সম্পর্কিত তথ্যি", educationTuitionDetailsItem.getTuitiondetails());
                CheckConcate("সর্বনিম্ন খরচ( ক্লাসের) ", educationTuitionDetailsItem.getTuitionminfee());

                CheckConcate("সর্বোচ্চ খরচ( ক্লাসের) ", educationTuitionDetailsItem.getTuitionmaxfee());
                CheckConcate("সর্বনিম্ন খরচ( কোচিং) ", educationTuitionDetailsItem.getTuitionmincoaching());
                CheckConcate("সর্বোচ্চ খরচ( কোচিং)", educationTuitionDetailsItem.getTuitionmaxcoaching());
                CheckConcate("অন্যান্য তথ্য", educationTuitionDetailsItem.getTuitionadditional());


            }


        }


        serviceDetails.setText(result_concate);

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!educationNewItem.getNode_contact2().equals("")) {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                }
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int compareValue;
                String node = String.valueOf(educationNewItem.getEduId());
                compareValue = SharedPreferencesHelper.getComapreValueEdu(DetailsLayoutEducation.this);
                if (compareValue >= 2)
                    AlertMessage.showMessage(con, "নতুন তথ্য নেয়া সম্ভব হচ্ছে না",
                            "আপনি ইতিমধ্যে দুটি সেবা নির্বাচিত করেছেন তুলনার জন্য");
                else if (compareValue == 0) {
                    Log.d("compareValue", "====" + compareValue);
                    SharedPreferencesHelper.setCompareData(DetailsLayoutEducation.this, node, 1);
                } else if (compareValue == 1) {
                    String previous_node;
                    previous_node = SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
                    previous_node = previous_node + "," + node;
                    SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, previous_node, 2);
                }


            }
        });


        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) upperHand.getLayoutParams();
        //int upperhad_height=params2.height = height/6;

        upperHand.setLayoutParams(params2);


//        LinearLayout.LayoutParams params_upperText = (LinearLayout.LayoutParams) upperText.getLayoutParams();
//        // int  vd=params_upperText.height = height/24;
//        // params_upperText.width = width;
//        upperText.setLayoutParams(params_upperText);

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
        ups_text.setText(educationNewItem.getNamebn());

        LinearLayout.LayoutParams feedbacks = (LinearLayout.LayoutParams) feedback.getLayoutParams();
        feedbacks.height = width / 8;
        feedbacks.width = width / 8;
        feedback.setLayoutParams(feedbacks);
        feedbacks.setMargins(0, 0, width / 30, 0);

        middle_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!educationNewItem.getNode_contact().equals("")) {
                    callIntent1.setData(Uri.parse("tel:" + educationNewItem.getNode_contact()));
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



        distance_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {


                    String lat = educationNewItem.getLat().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = educationNewItem.getLon().toString();
                    // double longitude = Double.parseDouble(lon);
                    String name= educationNewItem.getNamebn().toString();
                    String node=String.valueOf(educationNewItem.getEduId());
                    boolean fromornot=true;
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Latitude", lat);
                    editor.putString("Longitude", lon);
                    editor.putString("Name", name);
                    editor.putBoolean("Value", fromornot);
                    editor.putString("nValue", node);
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


                    finish();

                }
                else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                    AppUtils.showSettingsAlert(DetailsLayoutEducation.this);

                }

                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsLayoutEducation.this, AlertDialog.THEME_HOLO_LIGHT).create();
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
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }

    }

    public void verifyRegistration(View v){

        String  register = SharedPreferencesHelper.getNumber(DetailsLayoutEducation.this);
        phone_num=register;

        if (register.equals("")) {
            requestToRegister();
        } else {

            feedBackAlert();
            //  sendReviewToServer();
        }



    }

    public void feedBackAlert()
    {

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsLayoutEducation.this);
        final View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsLayoutEducation.this);
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


    public void sendReviewToServer()
    {
        int rating;
        if(status.equals(R.string.feedback1))
            rating=1;
        else if(status.equals(R.string.feedback2))
            rating=2;
        else if(status.equals(R.string.feedback3))
            rating=3;
        else if(status.equals(R.string.feedback4))
            rating=4;
        else
            rating=5;
        String comment="";
        comment=feedback_comment.getText().toString();
        String url = "http://kolorob.net/demo/api/sp_rating/"+educationNewItem.getEduId()+"?"+"phone=" +phone_num +"&review=" +comment+ "&rating="+rating+"&username="+username+"&password="+password+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsLayoutEducation.this,response,Toast.LENGTH_SHORT).show();
                        // Log.d(">>>>>","status "+response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            String forms;
                            forms = jo.getString("status");
                            Log.d(">>>>>","status "+forms);
                            //Log.d(">>>>>","status ");


                            if(forms.equals("true"))
                            {
                                AlertMessage.showMessage(DetailsLayoutEducation.this, "আপনার মতামত দেয়া হয়েছে",
                                        "আপনার মতামতের জন্য আপনাকে ধন্যবাদ!");
                            }
                            else
                                AlertMessage.showMessage(DetailsLayoutEducation.this, "আপনার মতামত দেয়া  হয় নি",
                                        "দয়া করে আবার চেষ্টা করুন");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsLayoutEducation.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

        //Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(DetailsLayoutEducation.this);
        requestQueue.add(stringRequest);
    }


    public void requestToRegister()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsLayoutEducation.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsLayoutEducation.this);
        alertDialogBuilder.setView(promptView);


        final ImageView yes= (ImageView)promptView.findViewById(R.id.yes);
        final ImageView no= (ImageView)promptView.findViewById(R.id.no);

        final AlertDialog alert = alertDialogBuilder.create();


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration= new Intent(DetailsLayoutEducation.this,PhoneRegActivity.class);
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
//
//



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


    public void setRatingBar()
    {
        getRequest(DetailsLayoutEducation.this, "http://kolorob.net/demo/api/get_sp_rating/education", new VolleyApiCallback() {
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
                                    if(id.equals(educationNewItem.getEduId()))
                                    {

                                        Float rating;
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

                }
            }
        }
    }


    private void timeProcessing(String value1, String value2) {
        if (!value2.equals("null") && !value2.equals("")) {
            String GetTime = timeConverter(value2);
            CheckConcate(value1, GetTime);

        }
    }


    private void CheckConcate(String value1,String value2){


        if(!value2.equals("null")&&!value2.equals("")) {


            String value ="      "+ value1 +":  "+ value2;
            result_concate = result_concate + value + "\n";
        }





    }
}

