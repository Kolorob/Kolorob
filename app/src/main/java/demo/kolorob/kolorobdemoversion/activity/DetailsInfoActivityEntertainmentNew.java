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
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
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
import demo.kolorob.kolorobdemoversion.adapters.EntertainmentBookshopAdapter;
import demo.kolorob.kolorobdemoversion.adapters.EntertainmentFieldAdapter;
import demo.kolorob.kolorobdemoversion.adapters.EntertainmentFitnessAdapter;
import demo.kolorob.kolorobdemoversion.adapters.EntertainmentTheatreAdapter;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentBookTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentFieldTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentFitnessTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentTheatreTable;
import demo.kolorob.kolorobdemoversion.helpers.AlertMessage;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Education.EducationCourseItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationFeeItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentBookShopItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentFieldItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentFitnessItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTheatreItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

public class DetailsInfoActivityEntertainmentNew extends Activity {


    String address_textx="";
    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar,linearLayout;
    ImageView left_image,middle_image,right_image,call_btn;
    TextView address_text,phone_text,email_text;
    int width,height;
    TextView ups_text;
    ListView courseListView,listView;
    ArrayList<EntertainmentBookShopItem> entertainmentBookShopItems;
    ArrayList<EntertainmentFieldItem> entertainmentFieldItems;
    ArrayList<EntertainmentFitnessItem> entertainmentFitnessItems;
    ArrayList<EntertainmentTheatreItem> entertainmentTheatreItems;
    ListView navlist,navlist1,navlist2,navlist3;
    LinearLayout first,second,third,fourth;
    private String result_concate="";
    private TextView itemopeningTime;
    private Context con;


    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    EntertainmentServiceProviderItem entertainmentServiceProviderItem;
    private TextView totalStudents;
    private TextView totalClasses;
    private TextView totalTeachers;
    private TextView playground;
    private TextView hostel;
    private TextView transport;
    private ImageView close_button,distance_btn;

    /**
     * Created by arafat on 28/05/2016.
     */
    String status="",phone_num="",registered="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_entertainment_new);
        con=this;

        Intent intent = getIntent();
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;


        if (null != intent)
        {
            entertainmentServiceProviderItem = (EntertainmentServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_ENT);

        }


        navlist = (ListView) findViewById(R.id.listView7);
        navlist1 = (ListView) findViewById(R.id.listView8);
        navlist2 = (ListView) findViewById(R.id.listView9);
        navlist3 = (ListView) findViewById(R.id.listView10);
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
        distance_btn=(ImageView)findViewById(R.id.distance_left);
        call_btn=(ImageView)findViewById(R.id.phone_middl);




        first= (LinearLayout)findViewById(R.id.first_list);
        second = (LinearLayout)findViewById(R.id.second_list);
        third = (LinearLayout)findViewById(R.id.third_list);
        fourth =(LinearLayout)findViewById(R.id.fourth_list);
        itemopeningTime=(TextView)findViewById(R.id.opening_time);

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
        ups_text.setText(entertainmentServiceProviderItem.getNodeNameBn());
        phone_text.setText(entertainmentServiceProviderItem.getNodeContact());
        email_text.setText(entertainmentServiceProviderItem.getNodeEmail());








        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
        int  vcc=params_bottom_bar.height = height/13;
        params_bottom_bar.width = width;
        bottom_bar.setLayoutParams(params_bottom_bar);
        result_concate="";

        if(!entertainmentServiceProviderItem.getOpeningtime().equals("") )
            concateBasic("  খোলার সময়: ",entertainmentServiceProviderItem.getOpeningtime() );

        if(!entertainmentServiceProviderItem.getClosingtime().equals("") )
            concateBasic("  বন্ধের সময়: ",entertainmentServiceProviderItem.getClosingtime() );

        if(!entertainmentServiceProviderItem.getBreaktime().equals(""))
            concateBasic("  বিরতির সময়: ",entertainmentServiceProviderItem.getBreaktime());
        itemopeningTime.setText(result_concate);

        result_concate="";


        if(!entertainmentServiceProviderItem.getRoad().equals(""))
            concateBasic("রাস্তা: ", entertainmentServiceProviderItem.getRoad());

        if(!entertainmentServiceProviderItem.getBlock().equals(""))
            concateBasic("ব্লক: ",entertainmentServiceProviderItem.getBlock());



        if(!entertainmentServiceProviderItem.getAddress().equals(""))
            concateBasic("",entertainmentServiceProviderItem.getAddress());


        if(!entertainmentServiceProviderItem.getLandmark().equals(""))
            concateBasic(entertainmentServiceProviderItem.getLandmark(), "  এর নিকটে");

        Log.d("===","final Address"+result_concate);



        address_text.setText(result_concate);


        EntertainmentBookTable entertainmentBookTable =new EntertainmentBookTable(DetailsInfoActivityEntertainmentNew.this);
        EntertainmentFieldTable entertainmentFieldTable = new EntertainmentFieldTable(DetailsInfoActivityEntertainmentNew.this);
        EntertainmentFitnessTable entertainmentFitnessTable = new EntertainmentFitnessTable(DetailsInfoActivityEntertainmentNew.this);
        EntertainmentTheatreTable entertainmentTheatreTable =new EntertainmentTheatreTable(DetailsInfoActivityEntertainmentNew.this);


        entertainmentBookShopItems= entertainmentBookTable.getBookshop(entertainmentServiceProviderItem.getNodeId());
        entertainmentFieldItems= entertainmentFieldTable.getField(entertainmentServiceProviderItem.getNodeId());
        entertainmentFitnessItems= entertainmentFitnessTable.getFitness(entertainmentServiceProviderItem.getNodeId());
        entertainmentTheatreItems= entertainmentTheatreTable.getTheatre(entertainmentServiceProviderItem.getNodeId());

        if(entertainmentBookShopItems!=null) {

            //
            int g= entertainmentBookShopItems.size();
            String[] borrow_cost=new String[g];
            String[] lending_allowed=new String[g];
            String[] membership_cost=new String[g];
            String[] offers=new String[g];
            String[] offer_details=new String[g];
            String[] membership_cost_ffp=new String[g];
            String[] membership_cost_foc=new String[g];
            int  k=0;
            for (EntertainmentBookShopItem et : entertainmentBookShopItems) {

                borrow_cost[k]=et.getBorrowCost();
                lending_allowed[k]=et.getLending();
                membership_cost[k]=et.getMemcost();
                offers[k]=et.getOffers();
                offer_details[k]=et.getOfferdetails();
                membership_cost_ffp[k]=et.getMemcostffp();
                membership_cost_foc[k]=et.getMemcostfoc();

                first.setVisibility(View.VISIBLE);

                k++;
            }


            EntertainmentBookshopAdapter adapter=new EntertainmentBookshopAdapter(this,borrow_cost,lending_allowed,
                    membership_cost,offers,offer_details,membership_cost_ffp,membership_cost_foc);
            navlist.setAdapter(adapter);
            Helpes.getListViewSize(navlist);
        }



        if(entertainmentFieldItems!=null) {


            int g= entertainmentFieldItems.size();
            String[] event_cost=new String[g];
            String[] playground_cost=new String[g];
            String[] remark=new String[g];
            String[] event_cost_ffp=new String[g];
            String[] event_cost_foc=new String[g];
            String[] playground_cost_ffp=new String[g];
            String[] playground_cost_foc=new String[g];

            int  k=0;
            for (EntertainmentFieldItem et : entertainmentFieldItems) {

                event_cost[k]=et.getEventCost();
                playground_cost[k]=et.getPlaygroundcost();
                remark[k]=et.getRemark();
                event_cost_ffp[k]=et.getEventcostffp();
                event_cost_foc[k]=et.getEventcostfoc();
                playground_cost_ffp[k]=et.getPlaygroundcostffp();
                playground_cost_foc[k]=et.getPlaygroundcostfoc();
                k++;
                second.setVisibility(View.VISIBLE);
            }
            EntertainmentFieldAdapter adapter=new EntertainmentFieldAdapter(this,event_cost,playground_cost,
                    remark,event_cost_ffp,event_cost_foc,playground_cost_ffp,playground_cost_foc);
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);
        }



        if(entertainmentTheatreItems!=null) {

            int g= entertainmentTheatreItems.size();

            String[] event_type=new String[g];
            String[] event_fee=new String[g];
            String[] event_date=new String[g];
            String[] remarks=new String[g];
            String[] event_fee_ffp=new String[g];
            String[] event_fee_foc=new String[g];

            int  k=0;
            for (EntertainmentTheatreItem et : entertainmentTheatreItems) {


                event_type[k]=et.getEventtype();
                event_fee[k]=et.getEventfee();
                event_date[k]=et.getEventdate();
                remarks[k]=et.getRemarks();
                event_fee_ffp[k]=et.getEventfee();
                event_fee_foc[k]=et.getEventfeefoc();
                k++;
                third.setVisibility(View.VISIBLE);
            }
            EntertainmentTheatreAdapter adapter=new EntertainmentTheatreAdapter(this,event_type,event_fee,
                    event_date,remarks,event_fee_ffp,event_fee_foc);
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);
        }


        if(entertainmentFitnessItems!=null) {

            int g= entertainmentFitnessItems.size();

            String[] year_of_establishment=new String[g];
            String[] num_workers=new String[g];
            String[] offers=new String[g];
            String[] offer_details=new String[g];
            String[] service_type=new String[g];
            String[] type=new String[g];
            String[] service_details=new String[g];

            int  k=0;
            for (EntertainmentFitnessItem et : entertainmentFitnessItems) {

                int year=et.getYearofestablishment();
                String docString = String.valueOf(year);


                int years=et.getWorkers();
                String docStrings = String.valueOf(years);

                year_of_establishment[k]=docString;
                num_workers[k]=docStrings;
                offers[k]=et.getOffers();
                offer_details[k]=et.getOfferdetails();
                service_type[k]=et.getServicetype();
                type[k]=et.getType();
                service_details[k]=et.getServicedetails();
                k++;

                fourth.setVisibility(View.VISIBLE);

            }
            EntertainmentFitnessAdapter adapter=new EntertainmentFitnessAdapter(this,year_of_establishment,num_workers,
                    offers,offer_details,service_type,type,service_details);
            navlist3.setAdapter(adapter);
            Helpes.getListViewSize(navlist3);
        }



        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        distance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {

                    String lat = entertainmentServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = entertainmentServiceProviderItem.getLongitude().toString();
                    String name= entertainmentServiceProviderItem.getNodeNameBn().toString();
                    // double longitude = Double.parseDouble(lon);
                    boolean fromornot=true;
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Latitude", lat);
                    editor.putString("Longitude", lon);
                    editor.putBoolean("Value", fromornot);
                    editor.putString("Name", name);

                    editor.commit();

                    // Toast.makeText(getApplicationContext(), "Your Longitude is " + lon, Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(getApplicationContext(), "Your Latitude is " + lat,Toast.LENGTH_SHORT).show();

                    String Longitude = pref.getString("Latitude", null);
                    String Latitude = pref.getString("Longitude", null);

                    if (Latitude != null && Longitude != null) {
                        Double Lon = Double.parseDouble(Longitude);
                        Double Lat = Double.parseDouble(Latitude);
                        //  Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat,Toast.LENGTH_SHORT).show();
                        // implementFragment();

                        //username and password are present, do your stuff
                    }


                    finish();
                }
                else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                    AppUtils.showSettingsAlert(DetailsInfoActivityEntertainmentNew.this);

                }
                else
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsInfoActivityEntertainmentNew.this, AlertDialog.THEME_HOLO_LIGHT).create();
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

        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entertainmentServiceProviderItem.getNodeEmail().equals(""))
                {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                }
            }
        });

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if(!entertainmentServiceProviderItem.getNodeContact().equals(""))
                {
                    callIntent1.setData(Uri.parse("tel:" + entertainmentServiceProviderItem.getNodeContact()));
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

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityEntertainmentNew.this);
        View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityEntertainmentNew.this);
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

    public void declareRadiobutton()
    {
        // int selected = feedRadio.getCheckedRadioButtonId();
        // RadioButton rb1 = (RadioButton) findViewById(selected);
        //  status = rb1.getText().toString();

        // Arafat, change this codes;

        status = "1";
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
        String url = "http://www.kolorob.net/KolorobApi/api/rating/save_feedback?phone="+phone_num+"&node="+entertainmentServiceProviderItem.getNodeId()+"&service="+"1"+"&rating="+rating;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailsInfoActivityEntertainmentNew.this,response,Toast.LENGTH_SHORT).show();
                        // Log.d(">>>>>","status "+response);
                        try {
                            JSONObject jo = new JSONObject(response);
                            String forms;
                            forms = jo.getString("status");
                            Log.d(">>>>>","status "+forms);
                            //Log.d(">>>>>","status ");

                            if(forms.equals("true"))
                            {
                                AlertMessage.showMessage(DetailsInfoActivityEntertainmentNew.this, "রেজিস্টেশনটি সফলভাবে সম্পন্ন হয়েছে",
                                        "েজিস্টেশন করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                demo.kolorob.kolorobdemoversion.helpers.AlertMessage.showMessage(DetailsInfoActivityEntertainmentNew.this, "রেজিস্টেশনটি সফলভাবে সম্পন্ন হয়ে নি",
                                        "আপনি ইতিপূর্বে রেজিস্ট্রেশন করে ফেলেছেন");







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsInfoActivityEntertainmentNew.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(DetailsInfoActivityEntertainmentNew.this);
        requestQueue.add(stringRequest);
    }


    public void requestToRegister()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityEntertainmentNew.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsInfoActivityEntertainmentNew.this);
        alertDialogBuilder.setView(promptView);


        final ImageView yes= (ImageView)promptView.findViewById(R.id.yes);
        final ImageView no= (ImageView)promptView.findViewById(R.id.no);

        final AlertDialog alert = alertDialogBuilder.create();


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration= new Intent(DetailsInfoActivityEntertainmentNew.this,PhoneRegActivity.class);
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

        Log.d("....>>>", "Values   " + result_concate);


        return result_concate;
    }
}
