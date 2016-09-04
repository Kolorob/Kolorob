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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.DefaultAdapter;
import demo.kolorob.kolorobdemoversion.database.Government.GovernmentServiceDetailsTable;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentServiceDetailsItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

/**
 * Created by israt.jahan on 7/17/2016.
 */
public class DetailsLayoutGovernment extends AppCompatActivity {
    Dialog dialog;
    LinearLayout upperHand, upperText, left_way, middle_phone, right_email, bottom_bar, linearLayout;
    ImageView left_image, middle_image, right_image, email_btn;
    TextView address_text, phone_text, email_text;
    int width, height;
    TextView ups_text;
    EditText feedback_comment;
    ListView courseListView, listView;
    Context con;
    String[] key;
    String[] value;
    int increment=0;
    ListView alldata;
    GovernmentNewItem governmentNewItem;
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    String datevalue,datevaluebn;
    ArrayList<GovernmentServiceDetailsItem> governmentServiceDetailsItems;

    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";
    private TextView ratingText;
    private TextView result, training, tuition;
    private ImageView close_button, phone_mid, distance_left, feedback, top_logo, cross, school_logo_default;
    RadioGroup feedRadio;
    RadioButton rb1, rb2, rb3;
    String status = "", phone_num = "", registered = "";
    String result_concate = "";
    private CheckBox checkBox;
    RatingBar ratingBar;
    Float rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout_government);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        con = this;


        Intent intent = getIntent();


        if (null != intent) {
            governmentNewItem = (GovernmentNewItem) intent.getSerializableExtra(AppConstants.KEY_DETAILS_GOV);
            // Log.d("CheckDetailsHealth","======"+healthServiceProviderItemNew);
        }


        GovernmentServiceDetailsTable governmentServiceDetailsTable = new GovernmentServiceDetailsTable(DetailsLayoutGovernment.this);


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



        key = new String[600];

        value = new String[600];
        alldata=(ListView)findViewById(R.id.allData);

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) alldata
                .getLayoutParams();

        mlp.setMargins(width/100,0,width/990,width/8);

        distance_left = (ImageView) findViewById(R.id.distance_left);
        email_btn = (ImageView) findViewById(R.id.right_side_email);
        feedback = (ImageView) findViewById(R.id.feedback);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        if(width<500)
            ratingBar = new RatingBar(this, null, android.R.attr.ratingBarStyleSmall);


        setRatingBar();

        CheckConcate("পরিচিত স্থান  ", governmentNewItem.getLandmark());
        CheckConcate("ঠিকানা  ", governmentNewItem.getAddress());
        CheckConcate("ফ্লোর  ", governmentNewItem.getFloor());
        CheckConcate("বাড়ির নাম  ", governmentNewItem.getHousename());
        CheckConcate("রাস্তা   ", governmentNewItem.getRoad());
        CheckConcate("লাইন    ", governmentNewItem.getLine());
        CheckConcate("এভিনিউ  ", governmentNewItem.getAvenue());
        CheckConcate("পোস্ট অফিস  ", governmentNewItem.getPostoffice());
        CheckConcate("পুলিশ স্টেশন ", governmentNewItem.getPolicestation());

        CheckConcate("যোগাযোগ  ", governmentNewItem.getNode_contact());
        CheckConcate("যোগাযোগ  ", governmentNewItem.getNode_contact2());
        CheckConcate("ইমেইল  ", governmentNewItem.getNode_email());
        CheckConcate("ওয়েব সাইট  ", governmentNewItem.getNode_website());
        CheckConcate("ফেসবুক  ", governmentNewItem.getNode_facebook());
        CheckConcate("দায়িত্বপ্রাপ্ত ব্যাক্তি   ", governmentNewItem.getNode_designation());


        timeProcessing("খোলার সময়  ", governmentNewItem.getOpeningtime());
        timeProcessing("বন্ধে সময়  ", governmentNewItem.getClosetime());
        if(!governmentNewItem.getBreaktime().equals("null")&&!governmentNewItem.getBreaktime().equals(""))
            breakTimeProcessing("বিরতির সময়  ", governmentNewItem.getBreaktime());
        CheckConcate("বন্ধের দিন   ", governmentNewItem.getOffday());
        CheckConcate("রেজিস্ট্রেশন নাম্বার ", governmentNewItem.getRegisterednumber());
        CheckConcate("কাদের সাথে রেজিস্টার্ড   ", governmentNewItem.getRegisteredwith());

        governmentServiceDetailsItems = governmentServiceDetailsTable.getgovinfo(governmentNewItem.getFinId());
        int tuition_size = governmentServiceDetailsItems.size();
        if (tuition_size != 0) {
            for (GovernmentServiceDetailsItem governmentServiceDetailsItem : governmentServiceDetailsItems) {
                //result_concate="";
                CheckConcate("সুবিধার ধরন ", governmentServiceDetailsItem.getServicetype());
                CheckConcate("সুবিধার নাম ", governmentServiceDetailsItem.getServicesubtype());
                CheckConcate("খরচ  ", governmentServiceDetailsItem.getServicecost()+ "টাকা");
                CheckConcate("মন্তব্য ", governmentServiceDetailsItem.getDetailstep());
            }
        }



        DefaultAdapter defaultAdapter= new DefaultAdapter(this,key,value,increment);
        alldata.setAdapter(defaultAdapter);

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (governmentNewItem.getNode_email().equals("")||governmentNewItem.getNode_email().equals("")) {
                    AlertMessage.showMessage(con, "ই মেইল করা সম্ভব হচ্ছে না",
                            "ই মেইল আই ডি পাওয়া যায়নি");
                }
                else{
                    Helpes.sendEmail(DetailsLayoutGovernment.this, governmentNewItem.getNode_email());
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

        SharedPreferences settings = DetailsLayoutGovernment.this.getSharedPreferences("prefs", 0);
        Date date2 = new Date(settings.getLong("time", 0));
        Date today=new Date();
        long diffInMillisec = today.getTime() - date2.getTime();

        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        if (diffInDays==0) datevalue=" আজকের তথ্য ";
        else
        {
            datevaluebn=English_to_bengali_number_conversion(String.valueOf(diffInDays));
            datevalue=""+ datevaluebn + " দিন আগের তথ্য";
        }

      //  Toast toast = Toast.makeText(this, datevalue, Toast.LENGTH_LONG);
        LayoutInflater inflater = getLayoutInflater();

        View toastView = inflater.inflate(R.layout.toast_view,null);
        Toast toast = new Toast(this);
        // Set the Toast custom layout
        toast.setView(toastView);


     //   View toastView = toast.getView(); //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */



        TextView toastMessage = (TextView) toastView.findViewById(R.id.toasts);
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

        ups_text.setTextSize(23);
        ratingText.setTextSize(23);
        ups_text.setText(governmentNewItem.getNamebn());

        LinearLayout.LayoutParams feedbacks = (LinearLayout.LayoutParams) feedback.getLayoutParams();
        feedbacks.height = width / 8;
        feedbacks.width = width / 8;
        feedback.setLayoutParams(feedbacks);

        middle_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!governmentNewItem.getNode_contact().equals("")) {
                    callIntent1.setData(Uri.parse("tel:" + governmentNewItem.getNode_contact()));
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


                    String lat = governmentNewItem.getLat().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = governmentNewItem.getLon().toString();
                    // double longitude = Double.parseDouble(lon);
                    String name= governmentNewItem.getNamebn().toString();
                    String node=String.valueOf(governmentNewItem.getFinId());
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


                    Intent intentJ = new Intent(DetailsLayoutGovernment.this,MapFragmentRouteOSM.class);
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

//                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsLayoutGovernment.this, AlertDialog.THEME_HOLO_LIGHT).create();
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

        String  register = SharedPreferencesHelper.getNumber(DetailsLayoutGovernment.this);
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

        LayoutInflater layoutInflater = LayoutInflater.from(DetailsLayoutGovernment.this);
        final View promptView = layoutInflater.inflate(R.layout.give_feedback_dialogue, null);
        final Dialog alertDialog = new Dialog(DetailsLayoutGovernment.this);
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
                sendReviewToServer();

                alertDialog.cancel();

            }
        });
        alertDialog.setCancelable(false);


        alertDialog.show();
    }


    public void sendReviewToServer()
    {
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
        String url = "http://kolorob.net/demo/api/sp_rating/"+governmentNewItem.getFinId()+"?"+"phone=" +phone_num +"&review=" +comment.replace(' ','+')+ "&rating="+rating+"&username="+username+"&password="+password+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)  {

                        Log.d("========", "status " + response);
                        try {


                            if (response.equals("true")) {
                                AlertMessage.showMessage(DetailsLayoutGovernment.this, "মতামতটি গ্রহন করা হয়েছে",
                                        "মতামত প্রদান করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                AlertMessage.showMessage(DetailsLayoutGovernment.this, "মতামতটি গ্রহন করা হয় নি",
                                        "অনুগ্রহ পূর্বক পুনরায় চেস্টা করুন।");


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailsLayoutGovernment.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

        //Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(DetailsLayoutGovernment.this);
        requestQueue.add(stringRequest);
    }


    public void requestToRegister() {
        LayoutInflater layoutInflater = LayoutInflater.from(DetailsLayoutGovernment.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);


        final Dialog alertDialog = new Dialog(DetailsLayoutGovernment.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final ImageView yes = (ImageView) promptView.findViewById(R.id.yes);
        final ImageView no = (ImageView) promptView.findViewById(R.id.no);
        final TextView textAsk=(TextView)promptView.findViewById(R.id.textAsk);
        String text="  মতামত দেয়ার আগে আপনাকে"+"\n"+"       রেজিস্ট্রেশন করতে হবে"+"\n"+"আপনি কি রেজিস্ট্রেশন করতে চান?";
        textAsk.setText(text);
        if(SharedPreferencesHelper.isTabletDevice(DetailsLayoutGovernment.this))
            textAsk.setTextSize(23);
        else
            textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPhoneRegistration = new Intent(DetailsLayoutGovernment.this, PhoneRegActivity.class);
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
    //
//
    public void setRatingBar()
    {
//    getRequest(DetailsLayoutGovernment.this, "http://kolorob.net/demo/api/get_sp_rating/government?username=kolorobapp&password=2Jm!4jFe3WgBZKEN", new VolleyApiCallback() {
//                @Override
//                public void onResponse(int status, String apiContent) {
//                    if (status == AppConstants.SUCCESS_CODE) {
//                        try {
//                            JSONArray jo = new JSONArray(apiContent);
//                            int size= jo.length();
//                            Log.d("$$$$$$", "size " + size);
//                            for(int i=0;i<size;i++)
//                            {
//                                JSONObject ratingH=jo.getJSONObject(i);
//                                String id= ratingH.getString("id");
//                                Log.d("$$$$$$", "getFinId " + governmentNewItem.getFinId());
//                                Log.d("$$$$$$", "id " + id);
//                                if(id.equals(String.valueOf(governmentNewItem.getFinId())))
//                                {
//                                    Log.d("$$$$$$", "size ");
        try {
            ratingBar.setRating(Float.parseFloat(governmentNewItem.getRating()));
        }
        catch (Exception e)
        {

        }
//                                    ratingBar.setRating(rating);
//                                    break;
//
//                                }
//
//
//                            }
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//    );
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



        if (!value2.equals("null") && !value2.equals("") &&!value2.equals(" টাকা")) {
            key[increment] = value1;
            value[increment] = value2;
            increment++;

        }





    }
}