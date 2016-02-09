package demo.kolorob.kolorobdemoversion.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
        ArrayList<String> age = new ArrayList<String>();
        age.add("5-15");
        age.add("16-35");
        age.add("36-50");
        age.add("50>");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, age);

        Spinner spinYear = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinYear.setAdapter(adapter);
        con=this;
        editTextUsername = (EditText) findViewById(R.id.editText4);
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

        loadSpinnerDataforcat();
        ArrayList<String> issue = new ArrayList<String>();

        issue.add("Bug");
        issue.add("Wrong Information");
        issue.add("Suggestion");

        ArrayAdapter<String> adapterissue = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,issue);
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(FeedbackActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FeedbackActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
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
    private void loadSpinnerDataforcat() {
        // database handler
        CategoryTable cattable =new CategoryTable(con);

        // Spinner Drop down elements
        ArrayList<String> cat = cattable.getAllCatNames();


        // Creating adapter for spinner
        ArrayAdapter<String> adapterr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cat);
        spinner2.setAdapter(adapterr);
        // attaching data adapter to spinner


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryname = spinner2.getSelectedItem().toString();
                if (categoryname.equals("Education")) setCat_id(1);
                else if (categoryname.equals("Health")) setCat_id(2);
                else if (categoryname.equals("Entertainment")) setCat_id(3);
                else if (categoryname.equals("Government")) setCat_id(4);
                else if (categoryname.equals("Legal")) setCat_id(5);
                else if (categoryname.equals("Financial")) setCat_id(6);
                else if (categoryname.equals("Job")) setCat_id(7);

                loadSpinnerDataforsubcat();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

    }
    private void loadSpinnerDataforsubcat() {
        // database handler
        SubCategoryTable subCategoryTable =new SubCategoryTable(con);

        // Spinner Drop down elements
        ArrayList<String> subcat = subCategoryTable.getcatSubCategories(cat_id);


        // Creating adapter for spinner
        ArrayAdapter<String> adapterrr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subcat);
        spinner3.setAdapter(adapterrr);
        // attaching data adapter to spinner
        subcategoryname = spinner3.getSelectedItem().toString();
    }



}