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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidtypeServiceProviderLegalAdviceTable;
import demo.kolorob.kolorobdemoversion.helpers.AlertMessage;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidLegalAdviceItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidSalishiItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

public class DetailsInfoActivityLegalNew extends Activity {

    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar,linearLayout;
    ImageView left_image,middle_image,right_image;
    TextView address_text,phone_text,email_text;
    int width,height;
    String basic_part;
    TextView ups_text,itemopeningTime;
    private ImageView close_button,distance_left;
    private LinearLayout ll3,scrollingPart;
    LinearLayout ll1,ll2;
    ImageView close,legal,phone_mid;
    TextView close_tv;
    ImageButton Feedback;
    ListView lv11,lv2;
    String result_concate="";
    ArrayList<LegalAidtypeServiceProviderLegalAdviceTable> legalAidtypeServiceProviderLegalAdviceTables;
    ArrayList<LegalAidLegalAdviceItem> legalAidLegalAdviceItems;
    ArrayList<LegalAidSalishiItem> legalAidSalishiItems;
    LegalAidServiceProviderItem legalAidServiceProviderItem;
    private Context con;

    /**
     * Created by arafat on 28/05/2016.
     */
    String status="",phone_num="",registered="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_legal_new);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;

        Intent intent = getIntent();
        con=this;

        if (null != intent)
        {
            legalAidServiceProviderItem = (LegalAidServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_LEGAL);

        }






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
        close_button=(ImageView)findViewById(R.id.close_button);
        scrollingPart=(LinearLayout)findViewById(R.id.scrollingPart);
        lv11 = (ListView) findViewById(R.id.listView7);
        lv2 = (ListView) findViewById(R.id.listView8);
        ll1=(LinearLayout)findViewById(R.id.first_list);
        ll2=(LinearLayout)findViewById(R.id.second_list);
        itemopeningTime=(TextView)findViewById(R.id.opening_time);
        distance_left=(ImageView)findViewById(R.id.distance_left);

        phone_mid=(ImageView)findViewById(R.id.phone_middl);

        basic_part="";


        if(!legalAidServiceProviderItem.getRoad().equals(""))
            concateBasic("রাস্তা: ", legalAidServiceProviderItem.getRoad());

        if(!legalAidServiceProviderItem.getBlock().equals(""))
            concateBasic("ব্লক: ",legalAidServiceProviderItem.getBlock());



        if(!legalAidServiceProviderItem.getAddress().equals(""))
            concateBasic("",legalAidServiceProviderItem.getAddress());


        if(!legalAidServiceProviderItem.getLandmark().equals(""))
            concateBasic(legalAidServiceProviderItem.getLandmark(), "  এর নিকটে");
        address_text.setText(basic_part);
        basic_part="";

        if(!legalAidServiceProviderItem.getOpeningtime().equals(""))
            concateBasic(" খোলার সময়: ",legalAidServiceProviderItem.getOpeningtime());
        if(!legalAidServiceProviderItem.getAdditionaltime().equals(""))
            concateBasic(" অতিরিক্ত সময়: ",legalAidServiceProviderItem.getAdditionaltime());

        if(!legalAidServiceProviderItem.getClosingtime().equals(""))
            concateBasic(" বন্ধ করার সময়: ",legalAidServiceProviderItem.getClosingtime());


        itemopeningTime.setText(result_concate);
        result_concate="";





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
        ups_text.setText(legalAidServiceProviderItem.getLegalaidNameBan());
        phone_text.setText(legalAidServiceProviderItem.getContactNo());
        email_text.setText(legalAidServiceProviderItem.getEmailAddress());


        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
        int  vcc=params_bottom_bar.height = height/13;
        params_bottom_bar.width = width;
        bottom_bar.setLayoutParams(params_bottom_bar);

        LinearLayout.LayoutParams expnlist = (LinearLayout.LayoutParams) scrollingPart.getLayoutParams();
        expnlist.setMargins(0,0,0,vcc);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        phone_mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if(!legalAidServiceProviderItem.getContactNo().equals(""))
                {
                    callIntent1.setData(Uri.parse("tel:" + legalAidServiceProviderItem.getContactNo()));
                    if(checkPermission())
                        startActivity(callIntent1);
                    else{
                        Toast.makeText(getApplicationContext(),
                                "Sorry, Phone call is not possible now. ", Toast.LENGTH_LONG)
                                .show();
                    }
                }
                else {
                    AlertMessage.showMessage(con, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                            "ফোন নম্বর পাওয়া যায়নি");
                }
            }
        });

        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(legalAidServiceProviderItem.getEmailAddress().equals(""))
                {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                }
            }
        });

        distance_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {

                    String lat = legalAidServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = legalAidServiceProviderItem.getLongitude().toString();
                    String name= legalAidServiceProviderItem.getLegalaidNameBan().toString();
                    // double longitude = Double.parseDouble(lon);
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
                        // Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat,Toast.LENGTH_SHORT).show();
                        // implementFragment();

                        //username and password are present, do your stuff
                    }


                    finish();
                }
                else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                    AppUtils.showSettingsAlert(DetailsInfoActivityLegalNew.this);

                }

                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityLegalNew.this, AlertDialog.THEME_HOLO_LIGHT).create();
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

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityLegalNew.this);
        View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityLegalNew.this);
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
        String url = "http://www.kolorob.net/KolorobApi/api/rating/save_feedback?phone="+phone_num+"&node="+legalAidServiceProviderItem.getIdentifierId() +"&service="+"1"+"&rating="+rating;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsInfoActivityLegalNew.this,response,Toast.LENGTH_SHORT).show();
                        // Log.d(">>>>>","status "+response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            String forms;
                            forms = jo.getString("status");
                            Log.d(">>>>>", "status " + forms);
                            //Log.d(">>>>>","status ");

                            if(forms.equals("true"))
                            {
                                AlertMessage.showMessage(DetailsInfoActivityLegalNew.this, "রেজিস্টেশনটি সফলভাবে সম্পন্ন হয়েছে",
                                        "েজিস্টেশন করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                demo.kolorob.kolorobdemoversion.helpers.AlertMessage.showMessage(DetailsInfoActivityLegalNew.this, "রেজিস্টেশনটি সফলভাবে সম্পন্ন হয়ে নি",
                                        "আপনি ইতিপূর্বে রেজিস্ট্রেশন করে ফেলেছেন");







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsInfoActivityLegalNew.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(DetailsInfoActivityLegalNew.this);
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
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityLegalNew.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityLegalNew.this);
        alertDialogBuilder.setView(promptView);


        final ImageView yes= (ImageView)promptView.findViewById(R.id.yes);
        final ImageView no= (ImageView)promptView.findViewById(R.id.no);

        final AlertDialog alert = alertDialogBuilder.create();


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration= new Intent(DetailsInfoActivityLegalNew.this,PhoneRegActivity.class);
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

    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        result_concate= result_concate+value + "\n";
        return result_concate;
    }
}
