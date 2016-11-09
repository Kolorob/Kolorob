package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;
import info.hoang8f.widget.FButton;



/**
 * Created by israt.jahan on 10/30/2016.
 */

public class OfferActivity extends Activity implements View.OnClickListener {
    TextView time,claimtext,offertext,lowdisclaimertext,updisclaimer,lowdis2;
    FButton claim;
    LinearLayout offer;
    long counthead=30;
    long credithead=15;
    String refno="a";
    ImageView backpack;
    public static int width;
    public static int height;
    ImageButton fb,wb;
    FloatingActionButton credit;
    String usernames = "kolorobapp";
    long remaincredit;
    String password = "2Jm!4jFe3WgBZKEN";
    boolean c,cred;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        SharedPreferences settings = OfferActivity.this.getSharedPreferences("prefs", 0);
        time=(TextView)findViewById(R.id.counter);
        claimtext=(TextView)findViewById(R.id.countertext);
        offer=(LinearLayout)findViewById(R.id.loweroffer);
        offertext=(TextView)findViewById(R.id.offertext);
        lowdis2=(TextView)findViewById(R.id.distext2);
        lowdisclaimertext=(TextView)findViewById(R.id.distext);
        backpack=(ImageView)findViewById(R.id.backpack);
        claim=(FButton) findViewById(R.id.claim);
        wb = (ImageButton) findViewById(R.id.btnw);
        fb = (ImageButton) findViewById(R.id.btnf);
        updisclaimer=(TextView)findViewById(R.id.offerdisclaimer);
        credit=(FloatingActionButton)findViewById(R.id.creditbutton);

        claim.setShadowEnabled(false);
        claim.setButtonColor(getResources().getColor(R.color.gray));
        claim.setTextColor(getResources().getColor(R.color.fbutton_color_silver));

        Date date2 = new Date(settings.getLong("timefirstinstall", 0));
        Date today=new Date();
        long diffInMillisec = today.getTime() - date2.getTime();
        fb.setOnClickListener(this);
        wb.setOnClickListener(this);
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        long remaining=counthead-diffInDays;
        remaincredit=credithead-diffInDays;
        cred=settings.getBoolean("RefProvided",false);
        if(remaincredit<=0 &&cred==false)
        {
            credit.setColorNormalResId(R.color.line_draw);
            credit.setIcon(R.drawable.smile);

        }
        else
        {
            credit.setColorNormalResId(R.color.gray);
            credit.setIcon(R.drawable.smile2);

        }
        credit.setOnClickListener(this);
        c=settings.getBoolean("MBRequest",false);
        if (remaining<=0 &&c==false)
        {
            claim.setOnClickListener(this);
            time.setText("0");
            claim.setButtonColor(getResources().getColor(R.color.colorAccent));
            claim.setShadowEnabled(true);
            claim.setShadowHeight(8);
            claim.setShadowColor(getResources().getColor(R.color.gray));
            claim.setTextColor(getResources().getColor(R.color.white));
        }
        else if (c)
        {
            time.setText("0");
            claim.setTextColor(getResources().getColor(R.color.fbutton_color_silver));
            claim.setText("দয়া করে অপেক্ষা করুন");
            claim.setOnClickListener(null);
        }
        else {

            String remaininginbn = EtoB(Long.toString(remaining));
            time.setText(remaininginbn);
        }

        if(SharedPreferencesHelper.isTabletDevice(OfferActivity.this))
        {

            time.setTextSize(130);
            claim.setTextSize(23);
            claimtext.setTextSize(40);
            offertext.setTextSize(25);
            offertext.setPadding(50,20,30,30);
            backpack.getLayoutParams().height = 400;
            backpack.getLayoutParams().width = 308;
            backpack.requestLayout();
            offer.setGravity(Gravity.CENTER);
            offer.requestLayout();
            credit.setSize(FloatingActionButton.SIZE_NORMAL);
            lowdisclaimertext.setTextSize(14);
            updisclaimer.setTextSize(14);
            lowdis2.setTextSize(14);
        }
        else credit.setSize(FloatingActionButton.SIZE_MINI);
    }
    public String EtoB(String english_number) {
        if(english_number.equals("null")||english_number.equals(""))
            return english_number;
        int v = english_number.length();
        String concatResult = "";
        for (int i = 0; i < v; i++) {
            if (english_number.charAt(i) == '0')
                concatResult = concatResult + "০";
            else if (english_number.charAt(i) == '1')
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnf:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.facebook.com/scib.kolorob"));
                startActivity(intent);
                break;
            case R.id.claim:

                if (AppUtils.isNetConnected(getApplicationContext()))
                {
                    sendRequest();
                }
                else {
                    ToastMessageDisplay.setText(OfferActivity.this,"দয়া করে ইন্টারনেট চালু করুন।");
//                    Toast.makeText(this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...",
//                            Toast.LENGTH_LONG).show();
                    ToastMessageDisplay.showText(OfferActivity.this);
                }
                break;
            case R.id.creditbutton:
                if(remaincredit<=0 &&cred==false)
                {
                    showbox(OfferActivity.this);
                }
                else if (!cred)
                {
                    String bnremain=EtoB(Long.toString(remaincredit));
                    ToastMessageDisplay.setText(OfferActivity.this,bnremain+" দিন পর চেষ্টা করুন");

                    ToastMessageDisplay.showText(OfferActivity.this);
                }

                break;


            case R.id.btnw:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@kolorob.net"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    ToastMessageDisplay.setText(OfferActivity.this,"দুঃখিত! ইমেইল করা যাচ্ছে না");
                    ToastMessageDisplay.showText(OfferActivity.this);

                }
                break;

            default:break;
        }

    }

    public void showbox(Context c) {

        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View promptView = layoutInflater.inflate(R.layout.credit_layout, null);


        final Dialog alertDialog = new Dialog(c);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final TextView header=(TextView)promptView.findViewById(R.id.bodyofcredit);
        final MaterialEditText refernumber = (MaterialEditText) promptView.findViewById(R.id.creditno);
        final ImageView okay=(ImageView)promptView.findViewById(R.id.okay);
        if(SharedPreferencesHelper.isTabletDevice(c)) {
            header.setTextSize(25);
        }


        okay.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                refno=refernumber.getText().toString().trim();
                if (!refno.equals(""))
                {
                    if (AppUtils.isNetConnected(getApplicationContext()))
                    {
                        sendRequesttocredit();
                        alertDialog.cancel();
                    }
                    else {
                        ToastMessageDisplay.setText(OfferActivity.this,"দয়া করে ইন্টারনেট চালু করুন।");
//                    Toast.makeText(this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...",
//                            Toast.LENGTH_LONG).show();
                        ToastMessageDisplay.showText(OfferActivity.this);
                    }


                }
                else
                {

                    ToastMessageDisplay.setText(OfferActivity.this,"মোবাইল নাম্বার লিখুন।");
//                    Toast.makeText(this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...",
//                            Toast.LENGTH_LONG).show();
                    ToastMessageDisplay.showText(OfferActivity.this);
                }

            }
        });

        alertDialog.setCancelable(true);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

    }
    public void sendRequest() {


        String phone = SharedPreferencesHelper.getNumber(OfferActivity.this);


        String url = "http://kolorob.net/demo/api/mb_request?phone=" + phone + "&username=" + this.usernames + "&password=" + this.password;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   ToastMessageDisplay.ShowToast(PlaceSelectionActivity.this,"ধন্যবাদ");


                        try {
                            if (response.toString().trim().equalsIgnoreCase("true")) {
                                SharedPreferences settings = OfferActivity.this.getSharedPreferences("prefs", 0);
                                settings.edit().putBoolean("MBRequest", true).apply();
                                c = settings.getBoolean("MBRequest", false);
                                claim.setShadowEnabled(false);
                                claim.setButtonColor(getResources().getColor(R.color.gray));
                                claim.setTextColor(getResources().getColor(R.color.fbutton_color_silver));
                                claim.setText("দয়া করে অপেক্ষা করুন");
                                claim.setOnClickListener(null);
                                AlertMessage.showMessage(OfferActivity.this, "অভিনন্দন!",
                                        "কিছুদিনের মাঝেই আপনার রেজিস্টার করা মোবাইল নাম্বারে আপনি ফ্রি ইন্টারনেট পেয়ে যাবেন।" +
                                                "পরবর্তী অফারের জন্য কলরবের সাথেই থাকুন!");
                            } else {
                                AlertMessage.showMessage(OfferActivity.this, "দুঃখিত", "কলরব সার্ভারে বর্তমানে কাজ চলছে। দয়া করে কিছুক্ষণ" +
                                        "পর আবার চেষ্টা করুন");
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastMessageDisplay.setText(OfferActivity.this, error.toString());
                        ToastMessageDisplay.showText(OfferActivity.this);
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(OfferActivity.this);
        requestQueue.add(stringRequest);



    }

    public void sendRequesttocredit() {



        String phone = SharedPreferencesHelper.getNumber(OfferActivity.this);


        String url = "http://kolorob.net/demo/api/give_credit?phone=" + phone +"&reffno=" + refno +"&username=" + this.usernames + "&password=" + this.password;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   ToastMessageDisplay.ShowToast(PlaceSelectionActivity.this,"ধন্যবাদ");


                        try {
                            if (response.toString().trim().equalsIgnoreCase("true")) {
                                SharedPreferences settings = OfferActivity.this.getSharedPreferences("prefs", 0);
                                settings.edit().putBoolean("RefProvided", true).apply();
                                cred = settings.getBoolean("RefProvided", false);
                                credit.setColorNormalResId(R.color.gray);
                                credit.setIcon(R.drawable.smile2);
                                credit.setOnClickListener(null);
                                AlertMessage.showMessage(OfferActivity.this, "ধন্যবাদ!","কলরব ও আপনার বন্ধুর পক্ষ থেকে আপনার প্রতি শুভেচ্ছা।" +
                                        "কলরবের সাথেই থাকুন");
                            } else {
                                AlertMessage.showMessage(OfferActivity.this, "দুঃখিত", "কলরব সার্ভারে বর্তমানে কাজ চলছে। দয়া করে কিছুক্ষণ" +
                                        "পর আবার চেষ্টা করুন");
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastMessageDisplay.setText(OfferActivity.this, error.toString());
                        ToastMessageDisplay.showText(OfferActivity.this);
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(OfferActivity.this);
        requestQueue.add(stringRequest);



    }

}
