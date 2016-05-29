package demo.kolorob.kolorobdemoversion.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;

public class FeedBackActivityNew extends AppCompatActivity {

    private String nodeId;
    private String CategoryId;
    private EditText name,email,phone_no;
    private Button send;
    private Spinner ratingSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_activity_new);

        Intent intent= new Intent();

        nodeId=intent.getStringExtra("id");
        CategoryId=intent.getStringExtra("categoryId");
        name=(EditText)findViewById(R.id.name_userx);
        email=(EditText)findViewById(R.id.email_userx);
        phone_no=(EditText)findViewById(R.id.phone_userx);
        send =(Button)findViewById(R.id.send);

        ArrayList<String> age = new ArrayList<String>();
        age.add("ভাল");
        age.add("মোটামোটি");
        age.add("ভাল না");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.dropdown_text_survey, age);
        ratingSpinner = (Spinner)findViewById(R.id.RatingSpinner);
        ratingSpinner.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "rating",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

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

                            return params;
                    }

                };



                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }
        });



    }

}
