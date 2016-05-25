package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by arafat on 1/11/2016.
 */
public class Information_UpdateActivity extends Activity {
    ImageView close,kivabejabejob;
    TextView close_tv;
    ImageButton Feedback;
    /**
     * Following components are only for LegalAid
     * For other categories this components may vary
     * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
     * */

    private static final String REGISTER_URL = "http://www.kolorob.net/volley.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_USEREMAIL = "email";


    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;

    private EditText email;
    private EditText name;
    private TextView fb,openTime,close_Time,breakTIme,jobName,road,block,landmark;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    JobServiceProviderItem jobServiceProviderItem;
private Context con;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_update);


        name  = (EditText)findViewById(R.id.name_id);
        email = (EditText)findViewById(R.id.email_id);

        con = this;

    }

    public void submit(View v){

        final String nametext = name.getText().toString();
        final String emailtext = name.getText().toString();

        if(nametext.length()<3) {
            AlertMessage.showMessage(con, "Sorry", "Name can not be too small.");
        }else if(emailtext.length()<12) {
            AlertMessage.showMessage(con, "Sorry", "Email is not correct.");
        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Information_UpdateActivity.this).create();

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
                            final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Information_UpdateActivity.this).create();

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
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_USERNAME, nametext);
                    params.put(KEY_USEREMAIL, emailtext);

                    return params;
                }

            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
