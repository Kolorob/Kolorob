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
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmetTypeTable;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.CommentItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTypeItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/**
 * Created by arafat on 28/05/2016.
 */

public class DetailsInfoActivityEntertainmentNew extends AppCompatActivity {
    Dialog dialog;
    LinearLayout upperHand,service_center_name,left_way,middle_phone,right_email,bottom_bar;
    ImageView route_icon,phone_icon,email_icon,email_btn;
    ImageView comment_icon;
    ArrayList<CommentItem> commentItems;
    int inc;
    int width,height;
    String datevalue,datevaluebn;

    TextView service_name;
    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";
    Context con;
    String[] key;
    String[] value;
    int increment=0;
    EntertainmentNewDBModel entertainmentServiceProviderItemNew;
    ArrayList<EntertainmentTypeItem> entertainmentTypeItems;

    private TextView ratingText;
    private ImageView routing_icon,feedback,service_logo,cross;
    RadioGroup feedRadio;
    RadioButton rb1;
    String status="",phone_num="",uname="";
    String result_concate="";
    EditText feedback_comment;
    RatingBar ratingBar;
    ListView service_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_entertainment_new);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        con = this;
        Intent intent = getIntent();


        if (null != intent) {
            //Receive an arrayList of (EntertainmentServiceProviderItemNew) type from previous activity.
            entertainmentServiceProviderItemNew = (EntertainmentNewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_ENT);

        }


        upperHand = (LinearLayout) findViewById(R.id.upper_part); //service center name and icon set will be here
        service_center_name = (LinearLayout) findViewById(R.id.upperText);// service center name will be here
        left_way = (LinearLayout) findViewById(R.id.left_go_process);
        middle_phone = (LinearLayout) findViewById(R.id.middle_phone);
        right_email = (LinearLayout) findViewById(R.id.right_email);
        bottom_bar = (LinearLayout) findViewById(R.id.bottom_bar);
        phone_icon = (ImageView) findViewById(R.id.phone_middl);
        email_icon = (ImageView) findViewById(R.id.right_side_email);
        route_icon = (ImageView) findViewById(R.id.distance_left);
        ratingText=(TextView)findViewById(R.id.ratingText);
        service_data=(ListView)findViewById(R.id.allData); //service_data will hold the main information of a service center
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) service_data
                .getLayoutParams();
        mlp.setMargins(width/100,0,width/990,width/8); //set margin in main info block
        service_logo=(ImageView)findViewById(R.id.top_logo);
        cross=(ImageView)findViewById(R.id.cross_jb); // cross icon in the right-top section
        routing_icon = (ImageView) findViewById(R.id.distance_left); //routing icon
        email_btn = (ImageView) findViewById(R.id.right_side_email);  //email icon
        feedback = (ImageView) findViewById(R.id.feedback);          //feedback icon
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        if(width<=400)
            ratingBar = new RatingBar(this, null, android.R.attr.ratingBarStyleSmall);// if ratingBar's height less than 400 then style will be different
        setRatingBar();


            // set width and height of the LinearLayouts programetically
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) upperHand.getLayoutParams();
        upperHand.setLayoutParams(params2);
        LinearLayout.LayoutParams params_service_center_name = (LinearLayout.LayoutParams) service_center_name.getLayoutParams();
        service_center_name.setLayoutParams(params_service_center_name);
        LinearLayout.LayoutParams params_left_way = (LinearLayout.LayoutParams) left_way.getLayoutParams();
        int lett_img = params_left_way.height = (height * 3) / 24;
        int right_img = params_left_way.width = width / 3;
        left_way.setLayoutParams(params_left_way);
        SharedPreferences settings = DetailsInfoActivityEntertainmentNew.this.getSharedPreferences("prefs", 0);
        LinearLayout.LayoutParams params_middle_phone = (LinearLayout.LayoutParams) middle_phone.getLayoutParams();
        params_middle_phone.height = (height * 3) / 24;
        params_middle_phone.width = width / 3;
        middle_phone.setLayoutParams(params_middle_phone);
        LinearLayout.LayoutParams params_right_email = (LinearLayout.LayoutParams) right_email.getLayoutParams();
        int vc = params_right_email.height = (height * 3) / 24;
        params_right_email.width = width / 3;
        right_email.setLayoutParams(params_right_email);
        LinearLayout.LayoutParams feedbacks = (LinearLayout.LayoutParams) feedback.getLayoutParams();
        feedbacks.height = width / 8;
        feedbacks.width = width / 8;
        feedback.setLayoutParams(feedbacks);


            // set height and width of the icons
        service_logo.getLayoutParams().height = width / 8;
        service_logo.getLayoutParams().width = width / 8;
        cross.getLayoutParams().height=width/13;
        cross.getLayoutParams().width=width/13;
        phone_icon.getLayoutParams().height=width/8;
        phone_icon.getLayoutParams().width=width/8;
        email_icon.getLayoutParams().height = width/8;
        email_icon.getLayoutParams().width = width/8;
        route_icon.getLayoutParams().height =  width/8;
        route_icon.getLayoutParams().width =  width/8;




            // Last date of updating data will be displayed here via toast message
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

              //set properties of service center name
        service_name = (TextView) findViewById(R.id.ups_text);
        service_name.setTextSize(27);
        service_name.setText(entertainmentServiceProviderItemNew.getNamebn());
        ratingText.setTextSize(23);


        result_concate ="";
        key = new String[600];
        value = new String[600];

        CheckConcate("প্রতিষ্ঠানের  ধরণ",  entertainmentServiceProviderItemNew.getEnttype());

       if(entertainmentServiceProviderItemNew.getServicetype().equals(true)) CheckConcate("সার্ভিস চার্জ আছে কিনা ",  "আছে");

        CheckConcate("সার্ভিস চার্জ আছে কিনা ",  "নাই");
        CheckConcate("রাস্তা", English_to_bengali_number_conversion(entertainmentServiceProviderItemNew.getRoad()));
        CheckConcate("ব্লক", English_to_bengali_number_conversion(entertainmentServiceProviderItemNew.getBlock()));
        CheckConcate("এলাকা", entertainmentServiceProviderItemNew.getAreabn());
        CheckConcate("ওয়ার্ড", English_to_bengali_number_conversion(entertainmentServiceProviderItemNew.getWard()));
        // CheckConcate("পোস্ট অফিস", educationNewItem.getp());
        CheckConcate("পুলিশ স্টেশন", entertainmentServiceProviderItemNew.getPolicestation());

        CheckConcate("বাড়ির নাম্বার", English_to_bengali_number_conversion(entertainmentServiceProviderItemNew.getHouseno()));

        CheckConcate("যোগাযোগ", entertainmentServiceProviderItemNew.getNode_contact());

        CheckConcate("ইমেইল", entertainmentServiceProviderItemNew.getNode_email());

        timeProcessing("খোলার সময়", entertainmentServiceProviderItemNew.getOpeningtime());
        timeProcessing("বন্ধের সময়", entertainmentServiceProviderItemNew.getClosetime());

        CheckConcate("কবে বন্ধ থাকে", entertainmentServiceProviderItemNew.getOffday());


        CheckConcate("অন্যান্য তথ্য ", entertainmentServiceProviderItemNew.getOtherinfo());

               //checkConcate method will check null data and concat

             // Default Adapter will show the details info of a service
        DefaultAdapter defaultAdapter= new DefaultAdapter(this,key,value,increment);
        service_data.setAdapter(defaultAdapter);



        
                
        email_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entertainmentServiceProviderItemNew.getNode_email().equals("null")||entertainmentServiceProviderItemNew.getNode_email().equals("")) {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                }
                else{
                     //Helpes method will be used to send Email
                    Helpes.sendEmail(DetailsInfoActivityEntertainmentNew.this, entertainmentServiceProviderItemNew.getNode_email());
                }
            }
        });


        comment_icon = (ImageView)findViewById(R.id.comments); //this icon will be used to show comment_icon
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width/8, width/8);
        lp.setMargins(width/26, 0, 0, 0);
        comment_icon.setLayoutParams(lp);
        CommentTable commentTable = new CommentTable(DetailsInfoActivityEntertainmentNew.this);


        commentItems=commentTable.getAllFinancialSubCategoriesInfo(String.valueOf(entertainmentServiceProviderItemNew.getEntid()));
        int size= commentItems.size();
        String[] phone = new String[size];
        String[] date = new String[size];
        final String[] comment = new String[size];
        final String[] rating = new String[size];



        for (CommentItem commentItem:commentItems)
        {
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


        comment_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityEntertainmentNew.this, String.valueOf(entertainmentServiceProviderItemNew.getEntid()),uname).equals("yes")||inc>0) {
                    if (SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityEntertainmentNew.this, String.valueOf(entertainmentServiceProviderItemNew.getEntid()), uname).equals("yes")&&inc==0) {
                        AlertMessage.showMessage(con, "দুঃখিত",
                                "কমেন্ট দেখতে দয়া করে তথ্য আপডেট করুন");

                    } else {
                        if (SharedPreferencesHelper.getifcommentedalready(DetailsInfoActivityEntertainmentNew.this, String.valueOf(entertainmentServiceProviderItemNew.getEntid()), uname).equals("yes") ) {
                           ToastMessageDisplay.setText(con,
                                    "আপনার করা কমেন্ট দেখতে দয়া করে তথ্য আপডেট করুন");
                           ToastMessageDisplay.showText(con);
                        }
                            LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityEntertainmentNew.this);
                            final View promptView = layoutInflater.inflate(R.layout.comment_popup, null);
                            final Dialog alertDialog = new Dialog(DetailsInfoActivityEntertainmentNew.this);
                            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            alertDialog.setContentView(promptView);
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alertDialog.show();
                        
                            final ListView listView = (ListView) promptView.findViewById(R.id.comment_list);
                            final ImageView close_icon = (ImageView) promptView.findViewById(R.id.closex);
                            final TextView review = (TextView) promptView.findViewById(R.id.review);
                            final ImageView ratingbarz = (ImageView) promptView.findViewById(R.id.ratingBarz);

                            try {
                                int ratings = Integer.parseInt(entertainmentServiceProviderItemNew.getRatings());
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
                            Double screenSize = AppUtils.ScreenSize(DetailsInfoActivityEntertainmentNew.this);
                        //Check ScreenSize
                            if (screenSize > 6.5) {
                                review.setTextSize(20);
                            } else {
                                review.setTextSize(16);
                            }


                            listView.setAdapter(comment_layout_adapter);
                            alertDialog.getWindow().setLayout((width * 5) / 6, (height * 2) / 3);

                            close_icon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });


                            alertDialog.setCancelable(false);
                            alertDialog.show();


                    }
                }
               else if(inc==0)  //if inc= o means no one commented
                {
                    LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityEntertainmentNew.this);
                    View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);
                    final Dialog alertDialog = new Dialog(DetailsInfoActivityEntertainmentNew.this);
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

                    if(SharedPreferencesHelper.isTabletDevice(DetailsInfoActivityEntertainmentNew.this))
                        textAsk.setTextSize(23);
                    else
                        textAsk.setTextSize(17);
                    //  alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                            String  register = SharedPreferencesHelper.getNumber(DetailsInfoActivityEntertainmentNew.this);
                            phone_num=register;
                            //if no number is set it will request to register
                            if (register.equals("")) {
                                requestToRegister();
                            } else {

                                feedBackAlert();
                            }

                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });
                    alertDialog.setCancelable(false);
                    alertDialog.show();

                }




            }
        });






        phone_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!entertainmentServiceProviderItemNew.getNode_contact().equals("null")&&!entertainmentServiceProviderItemNew.getNode_contact().equals("")) {
                    Log.d("Entertainment Parsing","......."+entertainmentServiceProviderItemNew.getNode_contact());
                    callIntent1.setData(Uri.parse("tel:" + entertainmentServiceProviderItemNew.getNode_contact()));
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


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        routing_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {


                    String lat = entertainmentServiceProviderItemNew.getLat();
                    // double latitude = Double.parseDouble(lat);
                    String lon = entertainmentServiceProviderItemNew.getLon();
                    // double longitude = Double.parseDouble(lon);
                    String name= entertainmentServiceProviderItemNew.getNamebn();
                    String node=String.valueOf(entertainmentServiceProviderItemNew.getEntid());
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

                        // implementFragment();
                        //username and password are present, do your stuff
                    }

                    Intent intentJ = new Intent(DetailsInfoActivityEntertainmentNew.this,MapFragmentRouteOSM.class);
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
             //encode breaktime in details format
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

            }
        }
    }

            //clicked from xml
    public void verifyRegistration(View v) {

        String  register = SharedPreferencesHelper.getNumber(DetailsInfoActivityEntertainmentNew.this);
        phone_num=register;
        if (register.equals("")) {
            requestToRegister();
        } else {

            feedBackAlert();
        }
    }

    public void feedBackAlert() {

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityEntertainmentNew.this);
        final View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        final Dialog alertDialog = new Dialog(DetailsInfoActivityEntertainmentNew.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final Button submit = (Button) promptView.findViewById(R.id.submit);
        final Button close_icon = (Button) promptView.findViewById(R.id.btnclose);
        close_icon.setOnClickListener(new View.OnClickListener() {
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
                   ToastMessageDisplay.setText(DetailsInfoActivityEntertainmentNew.this,"দয়া করে ইন্টারনেট চালু করুন।");
                   ToastMessageDisplay.showText(DetailsInfoActivityEntertainmentNew.this);
               }



            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    public void sendReviewToServer() {
        String  uname2 = SharedPreferencesHelper.getUname(DetailsInfoActivityEntertainmentNew.this);
        uname=uname2.replace(' ','+');;
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
        comment=feedback_comment.getText().toString().trim();
        try {
            comment2=   URLEncoder.encode(comment.replace(" ", "%20"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("status ","======"+status);
        String url = "http://kolorob.net/kolorob-live/api/sp_rating/"+entertainmentServiceProviderItemNew.getEntid()+"?"+"phone=" +phone_num +"&name=" +uname +"&review=" +comment2+ "&rating="+rating+"&username="+username+"&password="+password+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Response is true or false
                        try {
                            if (response.equals("true")) {
                                SharedPreferencesHelper.setifcommentedalready(DetailsInfoActivityEntertainmentNew.this,String.valueOf(entertainmentServiceProviderItemNew.getEntid()),uname,"yes");
                                AlertMessage.showMessage(DetailsInfoActivityEntertainmentNew.this, "মতামতটি গ্রহন করা হয়েছে",
                                        "মতামত প্রদান করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                AlertMessage.showMessage(DetailsInfoActivityEntertainmentNew.this, "মতামতটি গ্রহন করা হয় নি",
                                        "অনুগ্রহ পূর্বক পুনরায় চেস্টা করুন।");


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsInfoActivityEntertainmentNew.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(DetailsInfoActivityEntertainmentNew.this);
        requestQueue.add(stringRequest);
    }

    public void setRatingBar()
    {
        try {
            float f= Float.parseFloat(entertainmentServiceProviderItemNew.getRatings());
            ratingBar.setRating(f);
            ratingBar.setIsIndicator(true);
        }
        catch (Exception e)
        {

        }


    }

    public void requestToRegister() {
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsInfoActivityEntertainmentNew.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);


        final Dialog alertDialog = new Dialog(DetailsInfoActivityEntertainmentNew.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final ImageView yes = (ImageView) promptView.findViewById(R.id.yes);
        final ImageView no = (ImageView) promptView.findViewById(R.id.no);
        final TextView textAsk=(TextView)promptView.findViewById(R.id.textAsk);
        String text="  মতামত দেয়ার আগে আপনাকে"+"\n"+"       রেজিস্ট্রেশন করতে হবে"+"\n"+"আপনি কি রেজিস্ট্রেশন করতে চান?";
        textAsk.setText(text);
        if(SharedPreferencesHelper.isTabletDevice(DetailsInfoActivityEntertainmentNew.this))
            textAsk.setTextSize(23);
        else
            textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                Intent intentPhoneRegistration = new Intent(DetailsInfoActivityEntertainmentNew.this, PhoneRegActivity.class);

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


    private void timeProcessing(String value1, String value2) {
        if (!value2.equals("null") || value2.equals("")) {
            String GetTime = timeConverter(value2);
            CheckConcate(value1, GetTime);

        }
    }



    private String English_to_bengali_number_conversion(String english_number) {
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
            // check null and concat
    private void CheckConcate(String value1, String value2) {
        if (!value2.equals("null") && !value2.equals("")&& !value2.equals(" টাকা")) {
            key[increment] = value1;
            value[increment] = value2;
            increment++;
        }
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