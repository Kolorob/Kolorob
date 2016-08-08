package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.Job.JobAdvertisementTable;
import demo.kolorob.kolorobdemoversion.model.Job.JobAdvertisementItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;

public class DetailsJobActivityNew extends Activity {
    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar,linearLayout;
    ImageView left_image,middle_image,right_image,school_logo_default;
    TextView address_text,phone_text,email_text,itemopeningTime;
    int width,height;
    LinearLayout right_cross_button;
    ImageView left_job_icon;
    TextView ups_text,time;
    int timer=0;
    String result_concate="",phone_number="";
    private ImageView close_button;
    ListView navlist,navlist1,navlist2;
    private LinearLayout ll3,scrollingPart;
    TextView opening_time,closing_time,break_time,off_day,job_type,post_type,
             job_responsibility, required_experience, other_benefits,application_medium,
             application_post_date,application_last_date,reference_person, collector_name,
             salary_range;
    int position;
    ArrayList<JobAdvertisementItem> jobAdvertisementItems;
    private Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_details_job_activity_new);

        setContentView(R.layout.activity_job_details);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;

        Intent intent = getIntent();
        position =intent.getIntExtra("position",0)+1;

        con = this;

        linearLayout=(LinearLayout)findViewById(R.id.lll);
        //upperHand=(LinearLayout)findViewById(R.id.upper_part);
        upperText=(LinearLayout)findViewById(R.id.upperText);
//        left_way=(LinearLayout)findViewById(R.id.left_go_process);
        middle_phone=(LinearLayout)findViewById(R.id.middle_phone);
        right_email=(LinearLayout)findViewById(R.id.right_email);
        left_image=(ImageView)findViewById(R.id.distance_left);
        bottom_bar=(LinearLayout)findViewById(R.id.bottom_bar);
        middle_image=(ImageView)findViewById(R.id.phone_middl);
        right_image=(ImageView)findViewById(R.id.right_side_email);

        close_button=(ImageView)findViewById(R.id.close_button);
        school_logo_default=(ImageView)findViewById(R.id.school_logo_default);
        break_time=(TextView)findViewById(R.id.break_time);
        // off_day=(TextView)findViewById(R.id.off_day);
        opening_time=(TextView)findViewById(R.id.opening_time);
        left_job_icon=(ImageView) findViewById(R.id.left_job_icon);
        right_cross_button=(LinearLayout) findViewById(R.id.right_cross_button);

        school_logo_default.getLayoutParams().height=width/7;
        school_logo_default.getLayoutParams().width=width/7;
        time=(TextView)findViewById(R.id.time);




        int p=left_job_icon.getLayoutParams().width=width/11;
        left_job_icon.getLayoutParams().height=(p*5)/6;

        close_button.getLayoutParams().height=width/11;
        close_button.getLayoutParams().width=width/11;
        middle_image.getLayoutParams().height= width/11;
        middle_image.getLayoutParams().width=width/11;

        left_image.getLayoutParams().height= width/11;
        left_image.getLayoutParams().width=width/11;

        right_image.getLayoutParams().height= width/11;
        right_image.getLayoutParams().width=width/11;



        job_type=(TextView)findViewById(R.id.job_type);
       // job_responsibility=(TextView)findViewById(R.id.job_responsibility);
       // required_experience=(TextView)findViewById(R.id.required_experience);
       // other_benefits=(TextView)findViewById(R.id.other_benefits);
       // application_medium=(TextView)findViewById(R.id.application_medium);
        application_post_date=(TextView)findViewById(R.id.post_date);
      //  application_last_date=(TextView)findViewById(R.id.last_date);
        reference_person=(TextView)findViewById(R.id.reference_person);
        //collector_name=(TextView)findViewById(R.id.collector_name);
        salary_range=(TextView)findViewById(R.id.salary_range);

        Log.d("position","===="+position);

        JobAdvertisementTable jobAdvertisementTable= new JobAdvertisementTable(DetailsJobActivityNew.this);
        jobAdvertisementItems=jobAdvertisementTable.getSpecificJobElement(position);

        for(JobAdvertisementItem jobAdvertisementItem: jobAdvertisementItems)
        {
            ups_text=(TextView)findViewById(R.id.ups_text);
            ups_text.setTextSize(width/25);
            ups_text.setText(jobAdvertisementItem.getInstitute_name_bangla());


            if(!jobAdvertisementItem.getJob_type().equals(""))
            {
                concateBasic("চাকরির ধরণ: ", jobAdvertisementItem.getJob_type());
            }

            if(!jobAdvertisementItem.getPost_type().equals(""))
            {
                concateBasic("পদের ধরণ : ", jobAdvertisementItem.getPost_type());
            }

            if(!jobAdvertisementItem.getJob_responsibility().equals(""))
                concateBasic("কাজের বিবরণ: ",jobAdvertisementItem.getJob_responsibility());

            if(!jobAdvertisementItem.getRequired_experience().equals(""))
                concateBasic("প্রয়োজনীয় অভিজ্ঞতা: ", jobAdvertisementItem.getRequired_experience());

            if(!jobAdvertisementItem.getApplication_medium().equals(""))
                concateBasic("আবেদনের মাধ্যম: ",jobAdvertisementItem.getApplication_medium());

            job_type.setText(result_concate);
            result_concate="";


            if(!jobAdvertisementItem.getOpening().equals("0 : 00"))
                concateBasic("খোলার সময় : ",jobAdvertisementItem.getOpening());
            else
            timer++;
            Log.d("TImer1","%%%%%%"+timer);

            if(!jobAdvertisementItem.getClosing().equals("0 : 00"))
                concateBasic("বন্ধের সময় : ", jobAdvertisementItem.getClosing());
            else
                timer++;
            Log.d("TImer2","%%%%%%"+timer);
            if(!jobAdvertisementItem.getBreaks().equals("0 : 00"))
                concateBasic("বিরতির সময় : ",jobAdvertisementItem.getBreaks());
            else
                timer++;
            Log.d("TImer3","%%%%%%"+timer);
            if(!jobAdvertisementItem.getOff_day().equals(""))
                concateBasic("বন্ধের দিন : ", jobAdvertisementItem.getOff_day());
            else
                timer++;

            Log.d("TImer4","%%%%%%"+timer);
            if(timer>=4)
                time.setVisibility(View.GONE);



            opening_time.setText(result_concate);
            result_concate="";

            if(!jobAdvertisementItem.getPost_date().equals(""))
                concateBasic("আবেদন প্রকাশের সময় : ", jobAdvertisementItem.getPost_date());

            if(!jobAdvertisementItem.getApplication_last_date().equals(""))
                concateBasic("আবেদনের শেষ সময় : ", jobAdvertisementItem.getApplication_last_date());

            application_post_date.setText(result_concate);
            result_concate="";


            salary_range.setText("বেতন সীমা : " + jobAdvertisementItem.getStart_salary() + " - "+ jobAdvertisementItem.getEnd_salary());


           if(!jobAdvertisementItem.getReference_person().equals(""))
           {
               concateBasic(" যার সাথে যোগাযোগ করা হবে :",jobAdvertisementItem.getReference_person());

           }
           if(!jobAdvertisementItem.getCollector_name().equals(""))
               concateBasic(" সংগ্রহকারীর নাম : ",jobAdvertisementItem.getCollector_name());
            reference_person.setText(result_concate);
            result_concate="";

            phone_number=jobAdvertisementItem.getMobile1();
           }




//        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) upperHand.getLayoutParams();
//        int upperhad_height=params2.height = height/6;
//        upperHand.setLayoutParams(params2);

        LinearLayout.LayoutParams params_upperText = (LinearLayout.LayoutParams) upperText.getLayoutParams();
        int  vd=params_upperText.height = height/12;
        params_upperText.width = width;
        upperText.setLayoutParams(params_upperText);

//        LinearLayout.LayoutParams params_left_way = (LinearLayout.LayoutParams) left_way.getLayoutParams();
//        int lett_img=params_left_way.height = (height*3)/24;
//        int right_img=params_left_way.width = width/3;
//        left_way.setLayoutParams(params_left_way);


//        left_image.getLayoutParams().height= (lett_img*2)/3;
//        left_image.getLayoutParams().width=right_img/2;


//        LinearLayout.LayoutParams params_middle_phone = (LinearLayout.LayoutParams) middle_phone.getLayoutParams();
//        int  vx=params_middle_phone.height = (height*3)/24;
//        params_middle_phone.width = width/3;
//        middle_phone.setLayoutParams(params_middle_phone);

//
//
//        right_image.getLayoutParams().height= (lett_img*2)/3;
//        right_image.getLayoutParams().width=right_img/2;


          scrollingPart=(LinearLayout)findViewById(R.id.scrollingPart);
//        LinearLayout.LayoutParams params_right_email = (LinearLayout.LayoutParams) right_email.getLayoutParams();
//        int  vc=params_right_email.height = (height*3)/24;
//        params_right_email.width = width/3;
//        right_email.setLayoutParams(params_right_email);

        middle_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!phone_number.equals("")) {
                    callIntent1.setData(Uri.parse("tel:" + phone_number));
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

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



//        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
//        int  vcc=params_bottom_bar.height = height/13;
//        params_bottom_bar.width = width;
//        bottom_bar.setLayoutParams(params_bottom_bar);
//        LinearLayout.LayoutParams expnlist = (LinearLayout.LayoutParams) scrollingPart.getLayoutParams();
//        expnlist.setMargins(0,0,0,vcc);


    }


    public void showdirection(View v){
        AlertMessage.showMessage(con,"দুঃখিত","দিকনির্দেশনা পাওয়া যায়নি");
    }

    public void sendemail(View v){
        AlertMessage.showMessage(con,"দুঃখিত","ইমেইল আইডি পাওয়া যায়নি");
    }
    public void phonecall(View v){
        AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                "ফোন নম্বর পাওয়া যায়নি");
    }

//    public void closepage(View v){
//        this.finish();
//    }
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

    @Override
    protected void onResume() {
        timer=0;
        super.onResume();
    }

    private String concateBasic(String value1, String value2){

        if(!value2.equals("null")&&!value2.equals(""))
        {
            String value= value1+value2;
            result_concate= result_concate+value + "\n";
        }




        return result_concate;
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }

    }
}
