package demo.kolorob.kolorobdemoversion.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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

/**
 * Created by israt.jahan on 1/7/2016.
 */
public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String REGISTER_URL = "http://www.kolorob.net/volley.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_USERAGE = "userage";
    public static final String KEY_CONTACT = "usercontact";
    public static final String KEY_ISSUETYPE = "issuetype";
    public static final String KEY_ISSUEDETAILS = "issuedetails";
    public static final String KEY_CATEGORYNAME = "categoryname";
    public static final String KEY_SUBCATEGORYNAME = "subcategoryname";
    int cat_id;
    ImageView close;
    TextView close_tv;

    String userage,categoryname,subcategoryname,issuetype;
    private Context con;
    Spinner spinner2,spinner3,spinner4;
    private ImageButton SubmitFeedback;
    private EditText editTextUsername;
    private EditText editTextIssuedetails;
    private EditText editTextContactNo;
    int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
        String nodeid=getIntent().getExtras().getString("NodeId");
        subcategoryname=nodeid;
        int cattid=getIntent().getExtras().getInt("CatId");
        ArrayList<String> age = new ArrayList<String>();
        age.add("5-15 (৫ থেকে ১৫)");
        age.add("16-35 (১৬ থেকে ৩৫)");
        age.add("36-50 (৩৬ থেকে ৫০)");
        age.add("50> (৫০ এর উপরে)");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.dropdown_text_survey, age);

        Spinner spinYear = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinYear.setAdapter(adapter);
        con=this;
        editTextUsername = (EditText) findViewById(R.id.editText4);
        TextView textView =(TextView)findViewById(R.id.textView14);

        textView.setText("সমস্যার বিস্তারিত \n বিবরন: ");
        editTextUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)editTextUsername.setBackgroundColor(Color.LTGRAY);


            }
        });
        editTextIssuedetails = (EditText) findViewById(R.id.editText3);
        editTextIssuedetails.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)editTextIssuedetails.setBackgroundColor(Color.LTGRAY);


            }
        });

        editTextContactNo= (EditText) findViewById(R.id.editText);
        editTextContactNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)editTextContactNo.setBackgroundColor(Color.LTGRAY);


            }
        });
        userage=spinYear.getSelectedItem().toString();

        loadSpinnerDataforcat(cattid);
        ArrayList<String> issue = new ArrayList<String>();

        issue.add("Bug (সফটওয়্যার গত ত্রুটি)");
        issue.add("Wrong Information (ভুল তথ্য)");
        issue.add("Suggestion (পরামর্শ)");

        ArrayAdapter<String> adapterissue = new ArrayAdapter<String>(this, R.layout.dropdown_text_survey,issue);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setAdapter(adapterissue);
        issuetype= spinner4.getSelectedItem().toString();
        SubmitFeedback = (ImageButton) findViewById(R.id.Submitfeedback);
        SubmitFeedback.setOnClickListener(this);
        close = (ImageView) findViewById(R.id.iv_close);
        close_tv = (TextView) findViewById(R.id.tv_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(con, PlaceChoiceActivity.class);//Default Activity
                startActivity(a);
                finish();
            }
        });
        close_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(con, PlaceChoiceActivity.class);//Default Activity
                startActivity(a);
                finish();
            }
        });
    }

    private void submitFeedback(){
        final String username =  editTextUsername.getText().toString().trim();
        final String issuedetails =  editTextIssuedetails.getText().toString().trim();
        final String usercontact = editTextContactNo.getText().toString().trim();
        try {
            int num = Integer.parseInt(usercontact);
          status=1;
        } catch (NumberFormatException e) {
            status =0;
        }
        if(username.matches("")|| status==0)
        {
            final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(FeedbackActivity.this).create();

            alertDialog.setMessage("Please enter information properly");
            alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();

                        }
                    });
            alertDialog.getWindow().setLayout(200, 300);
            alertDialog.show();
        }
        else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(FeedbackActivity.this).create();

                            alertDialog.setMessage("Information submitted Successfully");
                            alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            alertDialog.dismiss();
                                            finish();
                                        }
                                    });
                            alertDialog.getWindow().setLayout(200, 300);
                            alertDialog.show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(FeedbackActivity.this).create();

                            alertDialog.setMessage("Wrong input!");
                            alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            alertDialog.dismiss();
                                        }
                                    });
                            alertDialog.getWindow().setLayout(200, 300);
                            alertDialog.show();
                        }
                    })

        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,username);
                params.put(KEY_USERAGE,userage);
                params.put(KEY_CONTACT, usercontact);
                params.put(KEY_ISSUETYPE,issuetype);
                params.put(KEY_ISSUEDETAILS,issuedetails);
                params.put(KEY_CATEGORYNAME, categoryname);
                params.put(KEY_SUBCATEGORYNAME, subcategoryname);
                return params;
            }

        };



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    }
    @Override
    public void onClick(View v) {
        if(v ==  SubmitFeedback){
            submitFeedback();
        }
    }
    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
    private void loadSpinnerDataforcat(int cattid) {
        // database handler

if (cattid==1)
    categoryname="Education";
        else if (cattid==2)
    categoryname="Health";
else if (cattid==3)
    categoryname="Entertainment";
else if (cattid==4)
    categoryname="Government";
else if (cattid==5)
    categoryname="Legal";
else if (cattid==6)
    categoryname="Financial";
else if (cattid==7)
    categoryname="Job";



    }



}