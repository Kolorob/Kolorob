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
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTrainingDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTuitionDetailsTable;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.CommentItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTrainingDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTuitionDetailsItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/**
 * Created by israt.jahan on 7/17/2016.
 */
public class DetailsLayoutEducation extends AppCompatActivity {
    Dialog dialog;
    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";
    LinearLayout upperHand, upperText, left_way, middle_phone, right_email, bottom_bar, linearLayout;
    ImageView left_image, middle_image, right_image, email_btn;
    ArrayList<CommentItem> commentItems;
    ImageView comments;
    int inc;
    int width, height;
    TextView ups_text;
    String[] key;
    String[] value;
    int increment=0;
    ListView alldata;

    Context con;
    EducationNewItem educationNewItem;
    RatingBar ratingBar;
    int compareValue;
    String previous_node;
    ArrayList<EducationTuitionDetailsItem> educationTuitionDetailsItems;
    ArrayList<EducationTrainingDetailsItem> educationTrainingDetailsItems;
    ArrayList<EducationResultItemNew> educationResultItemNews;

    String exams,Exam=null;
    private TextView ratingText;

    private ImageView close_button, distance_left, feedback, top_logo;
    RadioGroup feedRadio;
    RadioButton rb1;
    String status = "", phone_num = "", registered = "",uname="";

    private CheckBox checkBox;
    EditText feedback_comment;
    ArrayList<String>examname=new ArrayList<>();
    String datevalue,datevaluebn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout_education);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        con = this;

        Intent intent = getIntent();


        if (null != intent) {
            educationNewItem = (EducationNewItem) intent.getSerializableExtra(AppConstants.KEY_DETAILS_EDU);
            // Log.d("CheckDetailsHealth","======"+healthServiceProviderItemNew);
        }



        EducationTuitionDetailsTable educationTuitionDetailsTable = new EducationTuitionDetailsTable(DetailsLayoutEducation.this);
        EducationTrainingDetailsTable educationTrainingDetailsTable = new EducationTrainingDetailsTable(DetailsLayoutEducation.this);
        EducationResultDetailsTable educationResultDetailsTable = new EducationResultDetailsTable(DetailsLayoutEducation.this);



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

        close_button = (ImageView) findViewById(R.id.cross_jb);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        key = new String[600];
        setRatingBar();
        value = new String[600];
        alldata=(ListView)findViewById(R.id.allData);

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) alldata
                .getLayoutParams();

        mlp.setMargins(width/100,0,width/990,width/8);

        top_logo = (ImageView) findViewById(R.id.top_logo);




        distance_left = (ImageView) findViewById(R.id.distance_left);
        email_btn = (ImageView) findViewById(R.id.right_side_email);
        feedback = (ImageView) findViewById(R.id.feedback);
        checkBox = (CheckBox) findViewById(R.id.compare);


        CheckConcate("ঠিকানা", educationNewItem.getAddress());

        CheckConcate("রাস্তা", EtoB(educationNewItem.getRoad()));
        CheckConcate("লাইন ", EtoB(educationNewItem.getLine()));
        CheckConcate("এভিনিউ", EtoB(educationNewItem.getAvenue()));
        CheckConcate("পোস্ট অফিস", educationNewItem.getPostoffice());
        CheckConcate("পুলিশ স্টেশন", educationNewItem.getPolicestation());

        CheckConcate("বাড়ির নাম", EtoB(educationNewItem.getHousename()));
        CheckConcate("ফ্লোর", EtoB(educationNewItem.getFloor()));
        CheckConcate("যোগাযোগ", educationNewItem.getNode_contact());
        CheckConcate("যোগাযোগ", educationNewItem.getNode_contact2());
        CheckConcate("ইমেইল", educationNewItem.getNode_email());
        CheckConcate("ওয়েব সাইট", educationNewItem.getNode_website());
        CheckConcate("ফেসবুক", educationNewItem.getNode_facebook());
        CheckConcate("পরিচিত স্থান", educationNewItem.getLandmark());
        timeProcessing("খোলার সময়", educationNewItem.getOpeningtime());
        timeProcessing("বন্ধের সময়", educationNewItem.getClosetime());
        if(!educationNewItem.getBreaktime().equals("null")&&!educationNewItem.getBreaktime().equals(""))
            breakTimeProcessing("বিরতির সময়", educationNewItem.getBreaktime());
        CheckConcate("কবে বন্ধ থাকে", educationNewItem.getOffday());
        CheckConcate("রেজিস্ট্রেশন নাম্বার", educationNewItem.getRegisterednumber());
        CheckConcate("কাদের সাথে রেজিস্টার্ড ", educationNewItem.getRegisteredwith());
        CheckConcate("প্রতিষ্ঠানের ধরণ ", educationNewItem.getEdtype());
        CheckConcate("শাখা", educationNewItem.getShift());
        CheckConcate("ছাত্রছাত্রী সংখ্যা", EtoB(educationNewItem.getStudentno())+" জন");
        CheckConcate("শিক্ষক সংখ্যা",  EtoB(educationNewItem.getTeachersno())+" জন");
        CheckConcate("ক্লাস সংখ্যা",  EtoB(educationNewItem.getClassno())+" টি");
        CheckConcate("অন্যান্য তথ্য", educationNewItem.getAdditional());
        CheckConcate("ছাত্র সংখ্যা",  EtoB(educationNewItem.getMalestudent())+" জন");
        CheckConcate("ছাত্রী সংখ্যা",  EtoB(educationNewItem.getFemalestudent())+" জন");




        educationResultItemNews = educationResultDetailsTable.getResultInfo(educationNewItem.getEduId());
        int result_size = educationResultItemNews.size();

        if (result_size != 0) {
            for (EducationResultItemNew educationResultItemNew : educationResultItemNews) {
                //result_concate="";
               /* if (educationResultItemNew.getStudentno() != "") {
                    int student_number = Integer.parseInt(educationResultItemNew.getStudentno());
                    if (student_number > 0) {
                    } else examname.add(educationResultItemNew.getExamname());
                } else examname.add(educationResultItemNew.getExamname());
*/              CheckConcate("পরীক্ষা নাম", educationResultItemNew.getExamname());
                CheckConcate("ছাত্রছাত্রী সংখ্যা", EtoB(educationResultItemNew.getStudentno()));
                CheckConcate("পাশ করেছে এমন ছাত্রছাত্রী", EtoB(educationResultItemNew.getPassed()));
                CheckConcate("গোল্ডেন এ", EtoB(educationResultItemNew.getGoldena()));
                CheckConcate("জিপিএ ৫", EtoB(educationResultItemNew.getAplus()));
            }
        }
        for(int i=0;i<examname.size();i++)
        {
            if(examname.size()==0)
            {
                Exam=examname.get(i);
            }
            else
            {
                exams=examname.get(i);
                Exam.concat(exams+",");
            }

        }

        SharedPreferences settings = DetailsLayoutEducation.this.getSharedPreferences("prefs", 0);
        Date date2 = new Date(settings.getLong("time", 0));
        Date today=new Date();
        long diffInMillisec = today.getTime() - date2.getTime();

        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        if (diffInDays==0) datevalue=" আজকের তথ্য";
        else
        {
            datevaluebn=EtoB(String.valueOf(diffInDays));
            datevalue=""+ datevaluebn + " দিন আগের তথ্য";
        }
        ToastMessageDisplay.setText(this,datevalue);
        ToastMessageDisplay.showText(this);
        //Exam.length();


        educationTrainingDetailsItems = educationTrainingDetailsTable.gettrainingInfo(educationNewItem.getEduId());
        int training_size = educationTrainingDetailsItems.size();
        if (training_size != 0) {
            for (EducationTrainingDetailsItem educationTrainingDetailsItem : educationTrainingDetailsItems) {


                CheckConcate("কত মাসের কোর্স", EtoB(educationTrainingDetailsItem.getCourseduration()));
                CheckConcate("ভর্তি (মাস)", educationTrainingDetailsItem.getAdmissionmonth());
                CheckConcate("খরচ", educationTrainingDetailsItem.getCost()+" টাকা");
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
                boolean tuitioncost= Boolean.parseBoolean(educationTuitionDetailsItem.getTuitionfree());
                if (tuitioncost)
                {
                    CheckConcate("বিনা বেতনে পড়ার সুযোগ", "আছে");
                }
                else {
                    CheckConcate("বিনা বেতনে পড়ার সুযোগ", "নাই");
                }

                CheckConcate("বৃত্তি সুবিধা দান", educationTuitionDetailsItem.getTuitionstipendfacility());
                CheckConcate("বৃত্তি সুবিধার ধরন", educationTuitionDetailsItem.getTuitionstipendtype());
                CheckConcate("পড়া সম্পর্কিত তথ্যি", educationTuitionDetailsItem.getTuitiondetails());
                CheckConcate("সর্বোচ্চ খরচ( ক্লাসের) ", EtoB(educationTuitionDetailsItem.getTuitionmaxfee()));
                CheckConcate("সর্বনিম্ন খরচ( ক্লাসের) ", EtoB(educationTuitionDetailsItem.getTuitionminfee()));


                CheckConcate("সর্বনিম্ন খরচ( কোচিং) ", EtoB(educationTuitionDetailsItem.getTuitionmincoaching()));
                CheckConcate("সর্বোচ্চ খরচ( কোচিং)", EtoB(educationTuitionDetailsItem.getTuitionmaxcoaching()));
                CheckConcate("অন্যান্য তথ্য", educationTuitionDetailsItem.getTuitionadditional());


            }


        }
        CheckConcate("বিশেষ সুবিধা", educationNewItem.getSpecialneeds());
        CheckConcate("বাথরুম সংখ্যা",  EtoB((educationNewItem.getWashroom_no()))+" টি");
        CheckConcate("ছেলেদের বাথরুম", EtoB( educationNewItem.getWashroom_male()));
        CheckConcate("মেয়েদের বাথরুম ",  EtoB(educationNewItem.getWashroomfemale()));
        CheckConcate("বাথরুমের অবস্থা", educationNewItem.getWashroomcleanliness());
        CheckConcate("খাবার পানির অবস্থা", educationNewItem.getWatercondition());
        CheckConcate("খাবার পানির উৎস", educationNewItem.getWatersource());
        CheckConcate("গড় ছাত্রছাত্রী",  EtoB(educationNewItem.getAveragestudent()));


        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (educationNewItem.getNode_email().equals("")||educationNewItem.getNode_email().equals("null")) {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                }
                else{
                    Helpes.sendEmail(DetailsLayoutEducation.this, educationNewItem.getNode_email());
                }
            }
        });

        compareValue = SharedPreferencesHelper.getComapreValueEdu(DetailsLayoutEducation.this);
        previous_node = SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
        String multipule[]= previous_node.split(",");

        if(compareValue==1&&previous_node.equals(String.valueOf(educationNewItem.getEduId())))
        {

            checkBox.setChecked(true);
        }
        else if(compareValue==2&&(multipule[0].equals(String.valueOf(educationNewItem.getEduId()))||multipule[1].equals(String.valueOf(educationNewItem.getEduId()))))
        {

            checkBox.setChecked(true);
        }



        comments = (ImageView)findViewById(R.id.comments);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width/8, width/8);
        lp.setMargins(width/24, 0, 0, 0);
        comments.setLayoutParams(lp);
        CommentTable commentTable = new CommentTable(DetailsLayoutEducation.this);


        commentItems=commentTable.getAllFinancialSubCategoriesInfo(String.valueOf(educationNewItem.getEduId()));
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
                comment[inc]= EtoB(commentItem.getDate());
                rating[inc]= commentItem.getRating();
                inc++;
            }

        }



        final Comment_layout_adapter comment_layout_adapter = new Comment_layout_adapter(this,phone,date,comment,rating);


        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreferencesHelper.getifcommentedalready(DetailsLayoutEducation.this, String.valueOf(educationNewItem.getEduId()),uname).equals("yes")||inc>0) {
                    if (SharedPreferencesHelper.getifcommentedalready(DetailsLayoutEducation.this, String.valueOf(educationNewItem.getEduId()), uname).equals("yes")&&inc==0) {
                        AlertMessage.showMessage(con, "দুঃখিত",
                                "কমেন্ট দেখতে দয়া করে তথ্য আপডেট করুন");

                    } else {
                        if (SharedPreferencesHelper.getifcommentedalready(DetailsLayoutEducation.this, String.valueOf(educationNewItem.getEduId()), uname).equals("yes") ) {
                            ToastMessageDisplay.setText(con,
                                    "আপনার করা কমেন্ট দেখতে দয়া করে তথ্য আপডেট করুন");
                            ToastMessageDisplay.showText(con);
                        }
                        LayoutInflater layoutInflater = LayoutInflater.from(DetailsLayoutEducation.this);
                        final View promptView = layoutInflater.inflate(R.layout.comment_popup, null);
                        final Dialog alertDialog = new Dialog(DetailsLayoutEducation.this);
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
                            int ratings = Integer.parseInt(educationNewItem.getRating());

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


                            review.setText(EtoB(Integer.toString(inc)) + " রিভিউ");

                        Double screenSize = AppUtils.ScreenSize(DetailsLayoutEducation.this);
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
                else
                if(inc==0)
                {
                    LayoutInflater layoutInflater = LayoutInflater.from(DetailsLayoutEducation.this);
                    View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
                    final Dialog alertDialog = new Dialog(DetailsLayoutEducation.this);
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

                    if(SharedPreferencesHelper.isTabletDevice(DetailsLayoutEducation.this))
                        textAsk.setTextSize(23);
                    else
                        textAsk.setTextSize(17);
                    //  alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                            String  register = SharedPreferencesHelper.getNumber(DetailsLayoutEducation.this);
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
                    alertDialog.show();                }



            }
        });




        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String node = String.valueOf(educationNewItem.getEduId());
                compareValue = SharedPreferencesHelper.getComapreValueEdu(DetailsLayoutEducation.this);
//                if (compareValue >= 2)
//                    AlertMessage.showMessage(con, "নতুন তথ্য নেয়া সম্ভব হচ্ছে না",
//                            "আপনি ইতিমধ্যে দুটি সেবা নির্বাচিত করেছেন তুলনার জন্য");
//                else if (compareValue == 0) {
//                    Log.d("compareValue", "====" + compareValue);
//                    SharedPreferencesHelper.setCompareData(DetailsLayoutEducation.this, node, 1);
//                } else if (compareValue == 1) {
//
//                    previous_node = SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
//                    previous_node = previous_node + "," + node;
//                    SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, previous_node, 2);
//                }









                if (compareValue >= 2)
                {
                    if(isChecked)
                    {

                        String compare_Data="";
                        compare_Data=SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
                        String multipule[]= compare_Data.split(",");
                        compare_Data = multipule[1]+","+String.valueOf(educationNewItem.getEduId());
                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, compare_Data, 2);
                    }
                    else
                    {
                        String compare_Data="";
                        String new_compare_Data="";
                        compare_Data=SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
                        String multipule[]= compare_Data.split(",");
                        new_compare_Data = multipule[0];
                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, new_compare_Data, 1);
                    }

                }
                else if (compareValue == 0) {
                    if(isChecked)
                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, String.valueOf(educationNewItem.getEduId()), 1);

                }
                else if (compareValue == 1) {

                    if(isChecked)
                    {

                        String previous_node;
                        previous_node = SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
                        previous_node = previous_node + "," + String.valueOf(educationNewItem.getEduId());
                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, previous_node, 2);

                    }
                    else
                    {

                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this,"",0);

                    }

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




        LinearLayout.LayoutParams params_middle_phone = (LinearLayout.LayoutParams) middle_phone.getLayoutParams();
        int vx = params_middle_phone.height = (height * 3) / 24;
        params_middle_phone.width = width / 3;
        middle_phone.setLayoutParams(params_middle_phone);


        LinearLayout.LayoutParams params_right_email = (LinearLayout.LayoutParams) right_email.getLayoutParams();
        int vc = params_right_email.height = (height * 3) / 24;
        params_right_email.width = width / 3;
        right_email.setLayoutParams(params_right_email);

        ups_text = (TextView) findViewById(R.id.ups_text);

        ups_text.setTextSize(23);
        ratingText.setTextSize(23);
        ups_text.setText(educationNewItem.getNamebn());

        LinearLayout.LayoutParams feedbacks = (LinearLayout.LayoutParams) feedback.getLayoutParams();
        feedbacks.height = width / 8;
        feedbacks.width = width / 8;
        feedback.setLayoutParams(feedbacks);


        DefaultAdapter defaultAdapter= new DefaultAdapter(this,key,value,increment);
        alldata.setAdapter(defaultAdapter);
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

                    }
                } else {

                    AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                            "ফোন নম্বর পাওয়া যায়নি");

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

                    editor.commit();


                    String Longitude = pref.getString("Longitude", null);
                    String Latitude = pref.getString("Latitude", null);

                    if (Latitude != null && Longitude != null) {
                        Double Lon = Double.parseDouble(Longitude);
                        Double Lat = Double.parseDouble(Latitude);
                        // implementFragment();
                        //username and password are present, do your stuff
                    }


                    Intent intentJ = new Intent(DetailsLayoutEducation.this,MapFragmentRouteOSM.class);
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

//                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsLayoutEducation.this, AlertDialog.THEME_HOLO_LIGHT).create();
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
        String  uname2 = SharedPreferencesHelper.getUname(DetailsLayoutEducation.this);
        uname=uname2.replace(' ','+');;
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
        final Dialog alertDialog = new Dialog(DetailsLayoutEducation.this);
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
                if(AppUtils.isNetConnected(getApplicationContext()))
                {
                    sendReviewToServer();
                    alertDialog.cancel();
                }
                else {
                    ToastMessageDisplay.setText(DetailsLayoutEducation.this,"দয়া করে ইন্টারনেট চালু করুন।");
//                    Toast.makeText(this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...",
//                            Toast.LENGTH_LONG).show();
                    ToastMessageDisplay.showText(DetailsLayoutEducation.this);
                }

            }
        });
        alertDialog.setCancelable(false);


        alertDialog.show();
    }


    public void sendReviewToServer()
    {
        int rating= getRating(status);;

        String comment="",comment2="";
        comment=feedback_comment.getText().toString().trim();
        try {
            comment2=   URLEncoder.encode(comment.replace(" ", "%20"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://kolorob.net/demo/api/sp_rating/"+educationNewItem.getEduId()+"?"+"phone=" +phone_num +"&name=" +uname +"&review=" +comment2+ "&rating="+rating+"&username="+username+"&password="+password+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("========", "status " + response);
                        try {


                            if (response.equals("true")) {
                                SharedPreferencesHelper.setifcommentedalready(DetailsLayoutEducation.this,String.valueOf(educationNewItem.getEduId()) ,uname,"yes");

                                AlertMessage.showMessage(DetailsLayoutEducation.this, "মতামতটি গ্রহন করা হয়েছে",
                                        "মতামত প্রদান করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                AlertMessage.showMessage(DetailsLayoutEducation.this, "মতামতটি গ্রহন করা হয় নি",
                                        "অনুগ্রহ পূর্বক পুনরায় চেস্টা করুন।");


                        } catch (Exception e) {
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


    public void requestToRegister() {
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsLayoutEducation.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);


        final Dialog alertDialog = new Dialog(DetailsLayoutEducation.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final ImageView yes = (ImageView) promptView.findViewById(R.id.yes);
        final ImageView no = (ImageView) promptView.findViewById(R.id.no);
        final TextView textAsk=(TextView)promptView.findViewById(R.id.textAsk);
        String text="  মতামত দেয়ার আগে আপনাকে"+"\n"+"       রেজিস্ট্রেশন করতে হবে"+"\n"+"আপনি কি রেজিস্ট্রেশন করতে চান?";
        textAsk.setText(text);
        if(SharedPreferencesHelper.isTabletDevice(DetailsLayoutEducation.this))
            textAsk.setTextSize(23);
        else
            textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration = new Intent(DetailsLayoutEducation.this, PhoneRegActivity.class);
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

    public String EtoB(String english_number) {
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
            else if (english_number.charAt(i) == '-')
                concatResult = concatResult + "-";
            else {
                return english_number;
            }

        }
        return concatResult;
    }


    public void setRatingBar()
    {
//        getRequest(DetailsLayoutEducation.this, "http://kolorob.net/demo/api/get_sp_rating/education?username=kolorobapp&password=2Jm!4jFe3WgBZKEN", new VolleyApiCallback() {
//                    @Override
//                    public void onResponse(int status, String apiContent) {
//                        if (status == AppConstants.SUCCESS_CODE) {
//                            try {
//                                JSONArray jo = new JSONArray(apiContent);
//                                int size= jo.length();
//                                for(int i=0;i<size;i++)
//                                {
//                                    JSONObject ratingH=jo.getJSONObject(i);
//                                    String id= ratingH.getString("id");
//                                    if(id.equals(String.valueOf(educationNewItem.getEduId())))
//                                    {
//
//                                        Float rating;
//                                        rating=Float.parseFloat(ratingH.getString("avg"));
        try {
            ratingBar.setRating(Float.parseFloat(educationNewItem.getRating()));
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
                timeInBengali = "সকাল " + EtoB(String.valueOf(hour));
            else if (hour == 12)
                timeInBengali = "দুপুর  " + EtoB(String.valueOf(hour));
            else if (hour > 12 && hour < 16)
                timeInBengali = "দুপুর  " + EtoB(String.valueOf(hour - 12));
            else if (hour > 15 && hour < 18)
                timeInBengali = "বিকেল " + EtoB(String.valueOf(hour - 12));
            else if (hour > 17 && hour < 20)
                timeInBengali = "সন্ধ্যা " + EtoB(String.valueOf(hour - 12));
            else if (hour > 20)
                timeInBengali = "রাত " + EtoB(String.valueOf(hour - 12));
            if (times != 0)
                timeInBengali = timeInBengali + " টা " + EtoB(String.valueOf(times)) + " মিনিট";
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


        if (!value2.equals("null") && !value2.equals("")&& !value2.equals(" টি")&& !value2.equals(" জন")&& !value2.equals(" টাকা") && !value2.equals("No") && !value2.equals("0")&& !value2.equals("০")&& !value2.equals("০ টি")&& !value2.equals("০ জন")) {
            key[increment] = value1;
            value[increment] = value2;
            increment++;

        }





    }
    public int getRating(String status)
    {

        if(status.equals(getString(R.string.feedback1)))
           return 1;
        else if(status.equals(getString(R.string.feedback2)))
            return 2;
        else if(status.equals(getString(R.string.feedback3)))
            return 3;
        else if(status.equals(getString(R.string.feedback4)))
            return 4;
        else
            return 5;
    }
}
