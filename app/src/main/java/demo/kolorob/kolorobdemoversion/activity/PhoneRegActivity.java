package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/**
 * Created by arafat on 1/11/2016.
 */
public class PhoneRegActivity extends Activity {

    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";

    private String phoneNumber,uname,emailaddress;


    private EditText phone,email,name;



    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.


    private Context con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_reg);


        phone  = (EditText)findViewById(R.id.phone_id);
        name=(EditText)findViewById(R.id.userid) ;
        email=(EditText)findViewById(R.id.emailid) ;


        con = this;
    }

    public void submit(View v) {

        phoneNumber=phone.getText().toString().trim();
        uname=name.getText().toString().trim();
        emailaddress=email.getText().toString().trim();
        int size = phoneNumber.length();

        if (size != 11) {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        }
        else if (phoneNumber.charAt(0) != '0') {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        }
        else if (phoneNumber.charAt(1) != '1') {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        } else if (phoneNumber.charAt(2) == '2' || phoneNumber.charAt(2) == '3' || phoneNumber.charAt(2) == '4') {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        }
        if( uname.equals("")){


            name.setError( "name is required!" );

        }
        else if (uname.length()<=15&&!uname.equals("")) {
            sendPhoneNumberToServer(phoneNumber);
        }
    }


    public void sendPhoneNumberToServer(final String phone)
    {

       // http://192.168.43.57/demo/api/customer_reg?phone=01711310912
        String url = "http://kolorob.net/demo/api/customer_reg?phone="+phone+"&email="+emailaddress+"&name="+uname+"&username="+username+"&password="+password+"" ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(PhoneRegActivity.this,response,Toast.LENGTH_SHORT).show();
                       // Log.d(">>>>>","status "+response);

                        String notvalid=response;

                        try {

                            //

                            if(response.equals("true"))
                            {
                                SharedPreferencesHelper.setNumber(con,phoneNumber);
                                AlertMessage.showMessage(PhoneRegActivity.this, "রেজিস্ট্রেশন সফলভাবে সম্পন্ন হয়েছে",
                                        " রেজিস্ট্রেশন করার জন্য আপনাকে ধন্যবাদ");


                            }

                            else if(response.equals("\"Invalid Phone Number\""))
                            {
                                AlertMessage.showMessage(PhoneRegActivity.this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                                        "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");                            }
                            else
                            {




                               // SharedPreferencesHelper.setNumber(con,phoneNumber); this has been commented to prevent
                                // user using same number in multiple device

                                AlertMessage.showMessage(PhoneRegActivity.this, "দুঃখিত",
                                        "আপনি ইতিপূর্বে  রেজিস্ট্রেশন করে ফেলেছেন");
                            }

                                        //  finish();






                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastMessageDisplay.setText(PhoneRegActivity.this,error.toString());
                        ToastMessageDisplay.showText(PhoneRegActivity.this);

                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(PhoneRegActivity.this);
        requestQueue.add(stringRequest);



    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
