package demo.kolorob.kolorobdemoversion.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.helpers.AlertMessage;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;

public class FeedBackActivityNew extends AppCompatActivity {

    private String nodeId;
    private String CategoryId;
    private EditText name,email,phone_no;
    private Button send;
    private Spinner ratingSpinner;
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_activity_new);

        Intent intent= getIntent();

        nodeId=intent.getStringExtra("id");
        CategoryId=intent.getStringExtra("categoryId");
        name=(EditText)findViewById(R.id.name_userx);
        email=(EditText)findViewById(R.id.email_userx);
        phone_no=(EditText)findViewById(R.id.phone_userx);
        send =(Button)findViewById(R.id.send);
        con=this;
        ArrayList<String> age = new ArrayList<String>();
//        age.add("ভাল");
//        age.add("মোটামোটি");
//        age.add("ভাল না");

        age.add("খুবই অসন্তুষ্ট ");
        age.add("অসন্তুষ্ট ");
        age.add("বিশেষ অনুভূতি নেই");
        age.add("সন্তুষ্ট ");
        age.add("খুবই সন্তুষ্ট");




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.dropdown_text_survey, age);
        ratingSpinner = (Spinner)findViewById(R.id.RatingSpinner);
        ratingSpinner.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.kolorob.net/KolorobApi/api/rating/",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(">>>>","response");
                                try
                                {
                                    JSONObject jsonObject= new JSONObject(response);
                                    String success= jsonObject.getString("message");



                                    if(success.equals("Failed"))
                                    {

                                        AlertMessage.showMessage(con, "সাবমিট সফল হয়নি",
                                                "অনুগ্রহ পূর্বক কিছুক্ষণ প্রে চেস্টা করুন");
                                    }
                                    else
                                        AlertMessage.showMessage(con, "সাবমিট সফল হয়েছে",
                                                "মতামত প্রদান করার জন্য আপনাকে ধন্যবাদ");
                                }

                                catch (Exception e)
                                {

                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })

                {
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("name",name.getText().toString());

                        params.put("email",email.getText().toString());

                        params.put("phone", phone_no.getText().toString());

                        params.put("node",nodeId);

                        params.put("service",CategoryId);

                        if(ratingSpinner.getSelectedItem().toString().equals("ভাল"))
                        params.put("rating","1");
                        else if(ratingSpinner.getSelectedItem().toString().equals("মোটামোট"))
                            params.put("rating","2");
                        else
                            params.put("rating","3");
//                        Log.d(">>>>","Button is clicked " +ratingSpinner.getSelectedItem().toString());
                            return params;

                    }

                };



                RequestQueue requestQueue = Volley.newRequestQueue(con);
                requestQueue.add(stringRequest);
            }
        });



    }

}
