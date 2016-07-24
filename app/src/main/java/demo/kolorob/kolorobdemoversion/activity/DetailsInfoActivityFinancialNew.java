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
import android.util.DisplayMetrics;
import android.util.Log;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import demo.kolorob.kolorobdemoversion.adapters.FInancialBilsAdapter;
import demo.kolorob.kolorobdemoversion.adapters.FInancialInsuranceAdapter;
import demo.kolorob.kolorobdemoversion.adapters.FinancialPaymentAdapter;
import demo.kolorob.kolorobdemoversion.adapters.FinancialSocialAdapter;
import demo.kolorob.kolorobdemoversion.adapters.FinancilaLoadAdapter;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialBillsTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialInsuranceTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialLoanTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialPaymentTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialSocialTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTaxTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTransactionTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTuitionTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialBillsItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialInsuranceItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialLoanItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialPaymentItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialSocialItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTaxItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTransactionItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTuitionItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by arafat on 28/05/2016.
 */

public class DetailsInfoActivityFinancialNew extends Activity {

    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar,linearLayout;
    ImageView left_image,middle_image,right_image,phone_mid;
    TextView address_text,phone_text,email_text;
    int width,height;
    String basic_part;
    TextView ups_text;
    private ImageView close_button,distance_left;
    private LinearLayout ll3,scrollingPart;
    FinancialServiceProviderItem financialServiceProviderItem;
    ArrayList<FinancialServiceProviderItem> finfromsearch;
    ArrayList<FinancialBillsItem> financialBillsItems;
    ArrayList<FinancialInsuranceItem> financialInsuranceItems;
    ArrayList<FinancialLoanItem> financialLoanItems;
    ArrayList<FinancialPaymentItem> financialPaymentItems;
    ArrayList<FinancialSocialItem> financialSocialItems;
    ArrayList<FinancialTaxItem> financialTaxItems;
    ArrayList<FinancialTransactionItem> financialTransactionItems;
    ArrayList<FinancialTuitionItem> financialTuitionItems;
    ListView navlist,navlist1,navlist2,navlist3,navlist4,navlist5,navlist6,navlist7;
    LinearLayout l1,l2,l3,l4,l5,l6,l7,l8;
    private TextView open;
    private Context con;
    String status="",phone_num="",registered="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_financial_new);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;

        Intent intent = getIntent();

        if (null != intent)
        {

            financialServiceProviderItem = (FinancialServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_FINANCIAL);


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
        l1=(LinearLayout)findViewById(R.id.first_list);
        l2=(LinearLayout)findViewById(R.id.second_list);
        l3=(LinearLayout)findViewById(R.id.third_list);
        l4=(LinearLayout)findViewById(R.id.fourth_list);
        l5=(LinearLayout)findViewById(R.id.fifth_list);
        l6=(LinearLayout)findViewById(R.id.sixth_list);
        l7=(LinearLayout)findViewById(R.id.seventh_list);
        l8=(LinearLayout)findViewById(R.id.eighth_list);
        navlist = (ListView) findViewById(R.id.listView7s);
        navlist1 = (ListView) findViewById(R.id.listView8s);
        navlist2 = (ListView) findViewById(R.id.listView9s);
        navlist3 = (ListView) findViewById(R.id.listView10s);
        navlist4 = (ListView) findViewById(R.id.listView7ss);
        navlist5 = (ListView) findViewById(R.id.listView8ss);
        navlist6 = (ListView) findViewById(R.id.listView9ss);
        navlist7 = (ListView) findViewById(R.id.listView10ss);
        open=(TextView)findViewById(R.id.opening_time);
        distance_left=(ImageView)findViewById(R.id.distance_left);
        phone_mid=(ImageView)findViewById(R.id.phone_middl);
        con=this;


        if(!financialServiceProviderItem.getOpeningtime().equals(""))
        {
            concateBasic(" খোলার সময়: ",financialServiceProviderItem.getOpeningtime());
        }

        if(!financialServiceProviderItem.getBreaktime2().equals(""))
            concateBasic(" বিরতির সময়: ",financialServiceProviderItem.getBreaktime2());

        if(!financialServiceProviderItem.getClosingtime().equals(""))
            concateBasic(" বন্ধের সময়: ",financialServiceProviderItem.getClosingtime());

        if(!financialServiceProviderItem.getAdditionaltime().equals(""))
            concateBasic(" অতিরিক্ত সময়: ",financialServiceProviderItem.getAdditionaltime());





        open.setText(basic_part);




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
        ups_text.setText(financialServiceProviderItem.getNamebn());
        phone_text.setText(financialServiceProviderItem.getNodeContact());
        email_text.setText(financialServiceProviderItem.getNodeEmail());


        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
        int  vcc=params_bottom_bar.height = height/13;
        params_bottom_bar.width = width;
        bottom_bar.setLayoutParams(params_bottom_bar);

        LinearLayout.LayoutParams expnlist = (LinearLayout.LayoutParams) scrollingPart.getLayoutParams();
        expnlist.setMargins(0,0,0,vcc);


        FinancialBillsTable financialBillsTable = new FinancialBillsTable(DetailsInfoActivityFinancialNew.this);
        FinancialInsuranceTable financialInsuranceTable= new FinancialInsuranceTable(this);
        FinancialLoanTable financialLoanTable = new FinancialLoanTable(this);
        FinancialPaymentTable financialPaymentTable= new FinancialPaymentTable(this);
        FinancialSocialTable financialSocialTable = new FinancialSocialTable(this);
        FinancialTaxTable financialTaxTable =new FinancialTaxTable(this);
        FinancialTransactionTable financialTransactionTable = new FinancialTransactionTable(this);
        FinancialTuitionTable financialTuitionTable = new FinancialTuitionTable(this);

        String fs=financialServiceProviderItem.getNodeId();
        financialBillsItems= financialBillsTable.getFinancialBills(financialServiceProviderItem.getNodeId());
        financialInsuranceItems = financialInsuranceTable.getInsurance(financialServiceProviderItem.getNodeId());
        financialLoanItems = financialLoanTable.getFinancialLoan(financialServiceProviderItem.getNodeId());
        financialPaymentItems = financialPaymentTable.getFinancialPayment(financialServiceProviderItem.getNodeId());
        financialSocialItems = financialSocialTable.getFinancialSocial(fs);
        financialTaxItems = financialTaxTable.getFinancialTax(fs);
        financialTransactionItems= financialTransactionTable.getFinancialTransaction(fs);
        financialTuitionItems =financialTuitionTable.getFinancialTuition(fs);

        basic_part="";


        if(!financialServiceProviderItem.getRoad().equals(""))
            concateBasic("রাস্তা: ", financialServiceProviderItem.getRoad());

        if(!financialServiceProviderItem.getBlock().equals(""))
            concateBasic("ব্লক: ",financialServiceProviderItem.getBlock());



        if(!financialServiceProviderItem.getAddress().equals(""))
            concateBasic("",financialServiceProviderItem.getAddress());


        if(!financialServiceProviderItem.getLandmark().equals(""))
            concateBasic(financialServiceProviderItem.getLandmark(), "  এর নিকটে");
        address_text.setText(basic_part);

        Log.d("===","final Address"+basic_part);

        if(financialBillsItems!=null) {

            int g= financialBillsItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];
            String[] ref_num=new String[g];

            int  k=0;
            for (FinancialBillsItem et : financialBillsItems) {



                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                ref_num[k]=et.getRefNum();
                k++;
                l1.setVisibility(View.VISIBLE);
            }
            FInancialBilsAdapter adapter=new FInancialBilsAdapter(this,service_name,yes_no,
                    costs,remark,ref_num);
            navlist.setAdapter(adapter);
            Helpes.getListViewSize(navlist);
        }



        if(financialInsuranceItems!=null) {

            int g= financialInsuranceItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialInsuranceItem et : financialInsuranceItems) {

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l2.setVisibility(View.VISIBLE);

                k++;
            }
            FInancialInsuranceAdapter adapter=new FInancialInsuranceAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist1.setAdapter(adapter);
            Helpes.getListViewSize(navlist1);
        }



        if(financialLoanItems!=null) {

            int g= financialLoanItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];
            l1.setVisibility(View.VISIBLE);



            int  k=0;
            for (FinancialLoanItem et : financialLoanItems) {

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l3.setVisibility(View.VISIBLE);


                k++;
            }
            FinancialPaymentAdapter adapter=new FinancialPaymentAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);
        }
        if(financialPaymentItems!=null) {

            int g= financialPaymentItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialPaymentItem et : financialPaymentItems) {

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l4.setVisibility(View.VISIBLE);

                k++;
            }
            FinancilaLoadAdapter adapter=new FinancilaLoadAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist3.setAdapter(adapter);
            Helpes.getListViewSize(navlist3);
        }




        if(financialSocialItems!=null) {

            int g= financialSocialItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialSocialItem et : financialSocialItems) {


                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l5.setVisibility(View.VISIBLE);
                k++;
            }
            FinancialSocialAdapter adapter=new FinancialSocialAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist4.setAdapter(adapter);
            Helpes.getListViewSize(navlist4);
        }



        if(financialTaxItems!=null) {

            int g= financialTaxItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialTaxItem et : financialTaxItems) {

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l6.setVisibility(View.VISIBLE);

                k++;
            }
            FinancialSocialAdapter adapter=new FinancialSocialAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist5.setAdapter(adapter);
            Helpes.getListViewSize(navlist5);
        }



        if(financialTransactionItems!=null) {

            int g= financialTransactionItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialTransactionItem et : financialTransactionItems) {


                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l7.setVisibility(View.VISIBLE);
                k++;
            }
            FinancialSocialAdapter adapter=new FinancialSocialAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist6.setAdapter(adapter);
            Helpes.getListViewSize(navlist6);
        }



        if(financialTuitionItems!=null) {

            int g= financialTuitionItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialTuitionItem et : financialTuitionItems) {
                l8.setVisibility(View.VISIBLE);

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();

                k++;
            }
            FinancialSocialAdapter adapter=new FinancialSocialAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist7.setAdapter(adapter);
            Helpes.getListViewSize(navlist7);
        }



        distance_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {

                    String lat = financialServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = financialServiceProviderItem.getLongitude().toString();
                    String name= financialServiceProviderItem.getNamebn().toString();
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
                        //  Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat, Toast.LENGTH_SHORT).show();
                        // implementFragment();

                        //username and password are present, do your stuff
                    }


                    finish();

                }
                else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                    AppUtils.showSettingsAlert(DetailsInfoActivityFinancialNew.this);

                }
                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityFinancialNew.this, AlertDialog.THEME_HOLO_LIGHT).create();
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


//        right_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!financialServiceProviderItem.getEmailAddress().equals(""))
//                {
//                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
//                            "ই মেইল আই ডি পাওয়া যায়নি");
//                }
//            }
//        });

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(financialServiceProviderItem.getNodeEmail().equals(""))
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
                if(!financialServiceProviderItem.getNodeContact().equals(""))
                {
                    callIntent1.setData(Uri.parse("tel:" + financialServiceProviderItem.getNodeContact()));
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

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityFinancialNew.this);
        View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityFinancialNew.this);
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
        String url = "http://www.kolorob.net/KolorobApi/api/rating/save_feedback?phone="+phone_num+"&node="+financialServiceProviderItem.getNodeId() +"&service="+"1"+"&rating="+rating;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsInfoActivityFinancialNew.this,response,Toast.LENGTH_SHORT).show();
                        // Log.d(">>>>>","status "+response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            String forms;
                            forms = jo.getString("status");
                            Log.d(">>>>>", "status " + forms);
                            //Log.d(">>>>>","status ");

                            if(forms.equals("true"))
                            {
                                AlertMessage.showMessage(DetailsInfoActivityFinancialNew.this, "রেজিস্টেশনটি সফলভাবে সম্পন্ন হয়েছে",
                                        "েজিস্টেশন করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                AlertMessage.showMessage(DetailsInfoActivityFinancialNew.this, "রেজিস্টেশনটি সফলভাবে সম্পন্ন হয়ে নি",
                                        "আপনি ইতিপূর্বে রেজিস্ট্রেশন করে ফেলেছেন");







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsInfoActivityFinancialNew.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(DetailsInfoActivityFinancialNew.this);
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
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityFinancialNew.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityFinancialNew.this);
        alertDialogBuilder.setView(promptView);


        final ImageView yes= (ImageView)promptView.findViewById(R.id.yes);
        final ImageView no= (ImageView)promptView.findViewById(R.id.no);

        final AlertDialog alert = alertDialogBuilder.create();


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration= new Intent(DetailsInfoActivityFinancialNew.this,PhoneRegActivity.class);
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


    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        basic_part= basic_part+value + "\n";

        Log.d("....>>>", "Values   " + basic_part);


        return basic_part;
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        }
        else {

            return false;

        }
    }
}
