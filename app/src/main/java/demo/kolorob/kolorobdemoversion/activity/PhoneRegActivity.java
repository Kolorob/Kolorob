package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

/**
 * Created by arafat on 1/11/2016.
 */
public class PhoneRegActivity extends Activity {

    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";
    /**
     * Following components are only for LegalAid
     * For other categories this components may vary
     * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
     * */

    private String phoneNumber,uname,emailaddress;


    private EditText phone,email,name;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    JobServiceProviderItem jobServiceProviderItem;

    private Context con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_reg);



        con = this;
        phone  = (EditText)findViewById(R.id.phone_id);
        name=(EditText)findViewById(R.id.userid) ;
        email=(EditText)findViewById(R.id.emailid) ;

    }

    public void submit(View v) {

       AlertMessage.showMessage(con,"Sorry","this page is for dummy purpose only");
    }


    public void sendPhoneNumberToServer(final String phone)
    {

       // http://192.168.43.57/demo/api/customer_reg?phone=01711310912
        String url = "http://kolorob.net/demo/api/customer_reg?phone="+phone+"email="+emailaddress+"name="+uname+"&username="+username+"&password="+password+"" ;

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
                                AlertMessage.showMessage(PhoneRegActivity.this, "Successful!",
                                        " Thanks for completing registration with Kolorob");


                            }

                            else if(response.equals("\"Invalid Phone Number\""))
                            {
                                AlertMessage.showMessage(PhoneRegActivity.this, "Sorry!",
                                        "Please give your number in the correct format");                        }
                            else
                            {

                                AlertMessage.showMessage(PhoneRegActivity.this, "Sorry",
                                        "You've already completed your registration");
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
                        Toast.makeText(PhoneRegActivity.this,error.toString(),Toast.LENGTH_LONG).show();
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
    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }



    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
