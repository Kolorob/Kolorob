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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.Comment_layout_adapter;
import demo.kolorob.kolorobdemoversion.adapters.DefaultAdapter;
import demo.kolorob.kolorobdemoversion.database.CommentTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTableDetails;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccineTableDetails;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.CommentItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItemDetails;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccineItemDetails;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/**
 * Created by arafat on 28/05/2016.
 */

public class DetailsInfoActivityHealthNew extends AppCompatActivity {
    Dialog dialog;
    LinearLayout upperHand, upperText, left_way, middle_phone, right_email, bottom_bar;
    ImageView left_image, middle_image, right_image, email_btn;

    int width, height;
    TextView ups_text;

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

    private ImageView close_button, distance_left, feedback, top_logo;
    private RadioGroup feedRadio;
    RadioButton rb1;
    String status = "", phone_num = "", registered = "",uname="";

    private CheckBox checkBox;
    EditText feedback_comment;
    ListView alldata;
    RatingBar ratingBar;
    private String compare_Data="";
    int compareValue;
    private Double screenSize;
    ArrayList<CommentItem> commentItems;
    ImageView comments;
    RatingBar ratingBars;
    int inc=0;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_health_new);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

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
        final HealthSpecialistTableDetails healthSpecialistTableDetails = new HealthSpecialistTableDetails(DetailsInfoActivityHealthNew.this);


        upperHand = (LinearLayout) findViewById(R.id.upper_part);
        upperText = (LinearLayout) findViewById(R.id.upperText);
        left_way = (LinearLayout) findViewById(R.id.left_go_process);
        middle_phone = (LinearLayout) findViewById(R.id.middle_phone);
        right_email = (LinearLayout) findViewById(R.id.right_email);
        left_image = (ImageView) findViewById(R.id.distance_left);
        bottom_bar = (LinearLayout) findViewById(R.id.bottom_bar);
        middle_image = (ImageView) findViewById(R.id.phone_middl);
        right_image = (ImageView) findViewById(R.id.right_side_email);

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
        CheckConcate("রাস্তা", healthServiceProviderItemNew.getRoad());
        CheckConcate("লাইন ", healthServiceProviderItemNew.getLine());
        CheckConcate("এভিনিউ", healthServiceProviderItemNew.getAvenue());
        CheckConcate("পোস্ট অফিস", healthServiceProviderItemNew.getPost_office());
        CheckConcate("পুলিশ স্টেশন", healthServiceProviderItemNew.getPolice_station());
        CheckConcate("ব্লক", healthServiceProviderItemNew.getBlock());
        CheckConcate("বাড়ির নাম", healthServiceProviderItemNew.getHouse_name());
        CheckConcate("ফ্লোর", healthServiceProviderItemNew.getFloor());
        CheckConcate("পরিচিত স্থান", healthServiceProviderItemNew.getLandmark());
        CheckConcate("প্রতিষ্ঠানের ধরন", healthServiceProviderItemNew.getInstitute_type());
        timeProcessing("খোলার সময়", healthServiceProviderItemNew.getOpening_time());
        timeProcessing("বন্ধ করার সময়", healthServiceProviderItemNew.getClosing_time());
        if(!healthServiceProviderItemNew.getBreak_time().equals("null")&&!healthServiceProviderItemNew.getBreak_time().equals(""))
            breakTimeProcessing("বিরতির সময়", healthServiceProviderItemNew.getBreak_time());
        CheckConcate("ছুটির দিন", healthServiceProviderItemNew.getOff_day());



        CheckConcate("বিনামূল্যে সেবা", healthServiceProviderItemNew.getGeneral_free_for());
        CheckConcate("বিনামূল্যে সেবার ধরন", healthServiceProviderItemNew.getGeneral_free_services());
        CheckConcate("ধারন ক্ষমতা", healthServiceProviderItemNew.getCapacity());
        CheckConcate("পুরুষ ডাক্তার", healthServiceProviderItemNew.getMale_doctors());
        CheckConcate("মহিলা ডাক্তার", healthServiceProviderItemNew.getFemale_doctors());
        CheckConcate("রোগী এবং ডাক্তারের অনুপাত", healthServiceProviderItemNew.getPatient_doctor_ratio());
        CheckConcate("পুরুষ ডাক্তার", healthServiceProviderItemNew.getMale_doctors());
        CheckConcate("মহিলা ডাক্তার", healthServiceProviderItemNew.getFemale_doctors());
        CheckConcate("রোগী নার্সের অনুপাত", healthServiceProviderItemNew.getPatient_nurse_ratio());


        CheckConcate("ফার্মেসি চিকিৎসা সেবা", healthServiceProviderItemNew.getPharmacy_speciality());
        CheckConcate("ফার্মেসি ফি", English_to_bengali_number_conversion(healthServiceProviderItemNew.getPharmacy_fee())+" টাকা");

        CheckConcate("বিনামূল্যে সেবা", healthServiceProviderItemNew.getGeneral_free_services());
        CheckConcate("সাধারন খরচ", English_to_bengali_number_conversion(healthServiceProviderItemNew.getGeneral_cost())+" টাকা");
        CheckConcate("অন্যান্য তথ্য", healthServiceProviderItemNew.getGeneral_remark());
        CheckConcate("এ্যাম্বুলেন্সের খরচ", English_to_bengali_number_conversion(healthServiceProviderItemNew.getAmbulance_cost())+" টাকা");
        CheckConcate("অনন্য তথ্য", healthServiceProviderItemNew.getAmbulance_remark());
        CheckConcate("মাতৃত্বজনিত সেবার খরচ", English_to_bengali_number_conversion(healthServiceProviderItemNew.getMaternity_cost())+" টাকা");
        CheckConcate("মাতৃত্বজনিত অন্যন্য তথ্য", healthServiceProviderItemNew.getMaternity_remark());
        CheckConcate("জরুরী সেবার খরচ", English_to_bengali_number_conversion(healthServiceProviderItemNew.getEmergency_cost())+" টাকা");
        CheckConcate("অনন্য খরচ", English_to_bengali_number_conversion(healthServiceProviderItemNew.getEmergency_remark())+" টাকা");




        healthSpecialistItemDetailses = healthSpecialistTableDetails.getHealthSpecialistData(healthServiceProviderItemNew.getId());
        int specialist_size = healthSpecialistItemDetailses.size();

        if (specialist_size != 0) {
            for (HealthSpecialistItemDetails healthSpecialistItemDetails : healthSpecialistItemDetailses) {

                //   result_concate = "";
                //    specialist.setVisibility(View.VISIBLE);
                CheckConcate("বিশেষজ্ঞ ডাক্তারের ধরন ", healthSpecialistItemDetails.getSpecialisttype());
                CheckConcate("ডাক্তারের সংখা", English_to_bengali_number_conversion(healthSpecialistItemDetails.getSpecialistId())+" জন");
                CheckConcate("প্রথম ভিজিট ফি", English_to_bengali_number_conversion(healthSpecialistItemDetails.getSpecialistfees())+ " টাকা");
                CheckConcate("এক সপ্তাহের মধ্যে ভিজিট ফি", English_to_bengali_number_conversion(healthSpecialistItemDetails.getWeek_fee())+" টাকা");
                CheckConcate("এক মাসের মধ্যে ভিজিট ফি", English_to_bengali_number_conversion(healthSpecialistItemDetails.getMonth_fee())+" টাকা");
                CheckConcate("রিপোর্ট ফি", English_to_bengali_number_conversion(healthSpecialistItemDetails.getReport_fee())+" টাকা");



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
                CheckConcate("ভ্যাকসিনের ফী", English_to_bengali_number_conversion(healthVaccineItemDetails.getVaccinefee())+" টাকা");
                CheckConcate("ভ্যাকসিনের অন্যন্য তথ্য", healthVaccineItemDetails.getVaccineremarks());

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



        comments = (ImageView)findViewById(R.id.comments);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width/8, width/8);
        lp.setMargins(width/24, 0, 0, 0);
        comments.setLayoutParams(lp);
        CommentTable commentTable = new CommentTable(DetailsInfoActivityHealthNew.this);

        Log.d("Node Id","======="+healthServiceProviderItemNew.getId());
        commentItems=commentTable.getAllFinancialSubCategoriesInfo(healthServiceProviderItemNew.getId());
        int size= commentItems.size();
        String[] phone = new String[size];
        String[] date = new String[size];
        String[] comment = new String[size];
        final String[] rating = new String[size];


        for (CommentItem commentItem:commentItems)
        {
            Log.d("Rating","$$$$$$"+commentItem.getRating());

            if(!commentItem.getRating().equals(""))
            {
                phone[inc]= commentItem.getUser_name();
                if(commentItem.getComment().equals(""))date[inc]="কমেন্ট করা হয় নি ";
                else {date[inc]= commentItem.getComment();}
                comment[inc]= English_to_bengali_number_conversion(commentItem.getDate());
                rating[inc]= commentItem.getRating();
                inc++;
            }

        }



        final Comment_layout_adapter comment_layout_adapter = new Comment_layout_adapter(this,phone,date,comment,rating);


        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityHealthNew.this,healthServiceProviderItemNew.getId(),uname).equals("yes")||inc>0) {
                    if (SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityHealthNew.this, healthServiceProviderItemNew.getId(), uname).equals("yes")&&inc==0) {
                        AlertMessage.showMessage(con, "দুঃখিত",
                                "কমেন্ট দেখতে দয়া করে তথ্য আপডেট করুন");

                    } else {
                        if (SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityHealthNew.this, healthServiceProviderItemNew.getId(), uname).equals("yes") ) {
                            ToastMessageDisplay.setText(con,
                                    "আপনার করা কমেন্ট দেখতে দয়া করে তথ্য আপডেট করুন");
                            ToastMessageDisplay.showText(con);
                        }
                        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityHealthNew.this);
                        final View promptView = layoutInflater.inflate(R.layout.comment_popup, null);
                        final Dialog alertDialog = new Dialog(DetailsInfoActivityHealthNew.this);
                        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        alertDialog.setContentView(promptView);
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();
                        Log.d("Value of Inc1", "======");


//                    final TextView textView=(TextView)promptView.findViewById(R.id.header);
                        final ListView listView = (ListView) promptView.findViewById(R.id.comment_list);

                        final ImageView close = (ImageView) promptView.findViewById(R.id.closex);
                        // ratingBars = (RatingBar)promptView.findViewById(R.id.ratingBar_dialogue);
                        final TextView review = (TextView) promptView.findViewById(R.id.review);

                        final ImageView ratingbarz = (ImageView) promptView.findViewById(R.id.ratingBarz);

                        try {
                            int ratings = Integer.parseInt(healthServiceProviderItemNew.getRating());

                            if (ratings == 1) {
                                ratingbarz.setBackgroundResource(R.drawable.one);
                            } else if (ratings == 2)
                                ratingbarz.setBackgroundResource(R.drawable.two);

                            else if (ratings == 3)
                                ratingbarz.setBackgroundResource(R.drawable.three);

                            else if (ratings == 4)
                                ratingbarz.setBackgroundResource(R.drawable.four);

                            else if (ratings == 5)
                                ratingbarz.setBackgroundResource(R.drawable.five);
                        } catch (Exception e) {

                        }


                        review.setText(English_to_bengali_number_conversion(Integer.toString(inc)) + " রিভিউ");
                        Double screenSize = AppUtils.ScreenSize(DetailsInfoActivityHealthNew.this);
                        if (screenSize > 6.5) {
                            review.setTextSize(20);
                        } else {
                            review.setTextSize(16);


                        }


                        listView.setAdapter(comment_layout_adapter);
//                    textView.setVisibility(View.GONE);

                        alertDialog.getWindow().setLayout((width * 5) / 6, (height * 2) / 3);

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });


                        alertDialog.setCancelable(false);


                        alertDialog.show();

                    }
                }
                else if(inc==0)
                {
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
                    String text="এই সেবা সম্পর্কে কেউ এখনো মন্তব্য করেনি "+"\n"+"আপনি কি আপনার মতামত জানাতে চান?";
                    textAsk.setText(text);
                    alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

                    if(SharedPreferencesHelper.isTabletDevice(DetailsInfoActivityHealthNew.this))
                        textAsk.setTextSize(23);
                    else
                        textAsk.setTextSize(17);
                  //  alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          alertDialog.cancel();
                            String  register = SharedPreferencesHelper.getNumber(DetailsInfoActivityHealthNew.this);
                            phone_num=register;

                            if (register.equals("")) {
                                requestToRegister();
                            } else {

                                feedBackAlert();
                                //  sendReviewToServer();
                            }

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




                   // AlertMessage.showMessage(DetailsInfoActivityHealthNew.this,"দুঃখিত কমেন্ট দেখানো সম্ভব হচ্ছে না","এখন পর্যন্ত কেউ কমেন্ট করে নি");
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
        if (diffInDays==0) datevalue=" আজকের তথ্য";
        else
        {
            datevaluebn=English_to_bengali_number_conversion(String.valueOf(diffInDays));
            datevalue=""+ datevaluebn + " দিন আগের তথ্য";
        }
        ToastMessageDisplay.setText(this,datevalue);
        ToastMessageDisplay.showText(this);

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
        alldata=(ListView)findViewById(R.id.allData);

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) alldata
                .getLayoutParams();

        mlp.setMargins(width/100,0,width/990,width/8);

        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (healthServiceProviderItemNew.getNode_contact2().equals("")) {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
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
                        AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                                "ফোন নম্বর পাওয়া যায়নি");

                    }
                } else {

                    AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                            "ফোন নম্বর পাওয়া যায়নি");

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

                    AppUtils.showMessage(con, "জিপিএস বন্ধ করা রয়েছে!",
                            "আপনি কি আপনার মোবাইলের জিপিএস টি চালু করতে চান?");

                }

                else
                {

                    AlertMessage.showMessage(con, "দুঃখিত আপনার ইন্টারনেট সংযোগটি সচল নয়।",
                            "দিকনির্দেশনা দেখতে চাইলে অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন।  ");


                }


            }
        });


    }


    public void setRatingBar()
    {

        try {
            ratingBar.setRating(Float.parseFloat(healthServiceProviderItemNew.getRating()));
        }
        catch (Exception e)
        {

        }

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
                if(AppUtils.isNetConnected(getApplicationContext()))
                {
                    sendReviewToServer();
                    alertDialog.cancel();
                }
                else {
                    ToastMessageDisplay.setText(DetailsInfoActivityHealthNew.this,"দয়া করে ইন্টারনেট চালু করুন।");
//                    Toast.makeText(this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...",
//                            Toast.LENGTH_LONG).show();
                    ToastMessageDisplay.showText(DetailsInfoActivityHealthNew.this);
                }
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


        String comment="",comment2="";
        String  uname2 = SharedPreferencesHelper.getUname(DetailsInfoActivityHealthNew.this);
        uname=uname2.replace(' ','+');

        comment=feedback_comment.getText().toString().trim();
        try {
            comment2=   URLEncoder.encode(comment.replace(" ", "%20"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://kolorob.net/demo/api/sp_rating/"+healthServiceProviderItemNew.getId()+"?"+"phone=" +phone_num +"&name=" +uname +"&review=" +comment2+ "&rating="+rating+"&username="+username+"&password="+password+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("========", "status " + response);
                        try {


                            if (response.equals("true")) {
                                SharedPreferencesHelper.setifcommentedalready(DetailsInfoActivityHealthNew.this,healthServiceProviderItemNew.getId(),uname,"yes");
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
        String text="  মতামত দেয়ার আগে আপনাকে"+"\n"+"       রেজিস্ট্রেশন করতে হবে"+"\n"+"আপনি কি রেজিস্ট্রেশন করতে চান?";
        textAsk.setText(text);
        if(SharedPreferencesHelper.isTabletDevice(DetailsInfoActivityHealthNew.this))
            textAsk.setTextSize(23);
        else
            textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);
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
            else if(english_number.charAt(i) == '-')
                concatResult = concatResult + "-";
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
                timeInBengali = "রাত ১২";
            else if (hour >= 6 && hour < 12)
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



        if (!value2.equals("null") && !value2.equals("")&&!value2.equals(" টাকা")&&!value2.equals(" টা")) {
            key[increment] = value1;
            value[increment] = value2;
            increment++;
        }
    }

}