package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;
import info.hoang8f.widget.FButton;

/**
 * Created by israt.jahan on 10/30/2016.
 */

public class OfferActivity extends Activity implements View.OnClickListener {
    TextView time,claimtext,offertext;
    FButton claim;
    LinearLayout offer;
    long counthead=0;
    ImageView backpack;
    ImageButton fb,wb;
    String usernames = "kolorobapp";
    String password = "2Jm!4jFe3WgBZKEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_offer);
        SharedPreferences settings = OfferActivity.this.getSharedPreferences("prefs", 0);
        time=(TextView)findViewById(R.id.counter);
        claimtext=(TextView)findViewById(R.id.countertext);
        offer=(LinearLayout)findViewById(R.id.loweroffer);
        offertext=(TextView)findViewById(R.id.offertext);
        backpack=(ImageView)findViewById(R.id.backpack);
        claim=(FButton) findViewById(R.id.claim);
        wb = (ImageButton) findViewById(R.id.btnw);
        fb = (ImageButton) findViewById(R.id.btnf);
        claim.setShadowEnabled(false);
        claim.setButtonColor(getResources().getColor(R.color.gray));
        claim.setTextColor(getResources().getColor(R.color.fbutton_color_silver));
        Date date2 = new Date(settings.getLong("time", 0));
        Date today=new Date();
        long diffInMillisec = today.getTime() - date2.getTime();
fb.setOnClickListener(this);
        wb.setOnClickListener(this);
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        long remaining=counthead-diffInDays;
        if (remaining<=0)
        {
            claim.setOnClickListener(this);
            time.setText("0");
            claim.setButtonColor(getResources().getColor(R.color.orange));
            claim.setShadowEnabled(true);
            claim.setShadowHeight(5);
            claim.setShadowColor(getResources().getColor(R.color.colorAccent));
            claim.setTextColor(getResources().getColor(R.color.white));
        }
        else
        {
               String remaininginbn= EtoB(Long.toString(remaining));
           time.setText(remaininginbn);
        }

        if(SharedPreferencesHelper.isTabletDevice(OfferActivity.this))
        {

            time.setTextSize(150);
            claim.setTextSize(30);
            claimtext.setTextSize(40);
            offertext.setTextSize(25);
            offertext.setPadding(50,20,30,30);
            backpack.getLayoutParams().height = 400;
            backpack.getLayoutParams().width = 400;
            backpack.requestLayout();
            offer.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
            offer.requestLayout();
        }
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
                sendRequest();
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

    public void sendRequest() {
        String username = SharedPreferencesHelper.getUser(OfferActivity.this);

        String phone = SharedPreferencesHelper.getNumber(OfferActivity.this);


            String url = "http://kolorob.net/demo/api/mb_request?phone=" + phone +  "&username=" + this.usernames + "&password=" + this.password;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //   ToastMessageDisplay.ShowToast(PlaceSelectionActivity.this,"ধন্যবাদ");


                            try {
                                ToastMessageDisplay.setText(OfferActivity.this,"ধন্যবাদ");
                                ToastMessageDisplay.showText(OfferActivity.this);
//                                if(response.toString().trim().equalsIgnoreCase("true"))
//                                {
//
//                                    AlertMessage.showMessage(PlaceSelectionActivity.this, "মন্তব্যটি পাঠানো হয়ছে",
//                                            "মন্তব্য করার জন্য আপনাকে ধন্যবাদ");
//                                }
//                                else
//                                    AlertMessage.showMessage(PlaceSelectionActivity.this, "মন্তব্য পাঠানো সফল হয়নি",
//                                            "মন্তব্য করার জন্য আপনাকে ধন্যবাদ");


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ToastMessageDisplay.setText(OfferActivity.this,error.toString());
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
