package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.DisplayAllJobList;
import demo.kolorob.kolorobdemoversion.database.Job.JobAdvertisementTable;
import demo.kolorob.kolorobdemoversion.database.Job.JobServiceProviderTable;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.Job.JobAdvertisementItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class DisplayAllJobsActivity extends Activity {

    private ImageView close_button;
    private ProgressDialog progress;
    private int width;
    private int height;
    private ImageView iv_kolorob_logo;
    private TextView tv_button;
    ArrayList<JobAdvertisementItem> jobAdvertisementItems;
    JobAdvertisementTable jobAdvertisementTable =new JobAdvertisementTable(DisplayAllJobsActivity.this);
    Context context;
    ListView joblist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_jobs);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;





        LayoutInflater layoutInflater = LayoutInflater.from(DisplayAllJobsActivity.this);
        View promptView = layoutInflater.inflate(R.layout.verify_reg_dialog, null);


        final Dialog alertDialog = new Dialog(DisplayAllJobsActivity.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final ImageView yes = (ImageView) promptView.findViewById(R.id.yes);
        final ImageView no = (ImageView) promptView.findViewById(R.id.no);
        final TextView textAsk=(TextView)promptView.findViewById(R.id.textAsk);
        String text="আপনি কি নতুন চাকুরি খুজতে চান? ";
        textAsk.setText(text);
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount=0.0f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);
        if(SharedPreferencesHelper.isTabletDevice(DisplayAllJobsActivity.this))
            textAsk.setTextSize(23);
        else
            textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
                progress = ProgressDialog.show(DisplayAllJobsActivity.this, "চাকুরীর তালিকা আপডেট হচ্ছে",
                        "অনুগ্রহ পূর্বক অপেক্ষা করুন", true);

                getRequest(DisplayAllJobsActivity.this, "job/all", new VolleyApiCallback() {
                            @Override
                            public void onResponse(int status, String apiContent) {
                                if (status == AppConstants.SUCCESS_CODE) {
                                    try {
                                        JSONObject jo = new JSONObject(apiContent);
                                        String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                        if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                            SaveNewJobs(jo.getJSONArray(AppConstants.KEY_DATA));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                );

            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                displayData();

            }
        });
        //   setup a dialog window
        alertDialog.setCancelable(false);


        alertDialog.show();





//        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle("আপনি কি নতুন চাকুরি খুজতে চান? ");
//
//        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "না",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//                    }
//                });
//        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "হ্যাঁ",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//
//
//                    }
//                });
//
//        alertDialog.show();

        close_button=(ImageView)findViewById(R.id.iv_close);
        iv_kolorob_logo=(ImageView)findViewById(R.id.iv_kolorob_logo);
        close_button.getLayoutParams().height=width/11;
        close_button.getLayoutParams().width=width/11;
        tv_button=(TextView)findViewById(R.id.tv_close);
        int p=iv_kolorob_logo.getLayoutParams().width=width/11;
        iv_kolorob_logo.getLayoutParams().height=(p*5)/6;

        context=this;

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void SaveNewJobs(JSONArray joblistArray) {
        JobAdvertisementTable jobAdvertisementTable = new JobAdvertisementTable(DisplayAllJobsActivity.this);
        jobAdvertisementTable.dropTable();
        int joblistCount = joblistArray.length();


        for (int i = 0; i < joblistCount; i++) {
            try {
                JSONObject jo = joblistArray.getJSONObject(i);

                if(!jo.equals(null))
                {
                    JobAdvertisementItem si = JobAdvertisementItem.parseJobServiceProviderItem(i+1,jo);

                    //   JobAdvertisementItem six = JobAdvertisementItem.parseJobServiceProviderItem(jo);
                    jobAdvertisementTable.insertItem(si);
                    // Log.d(">>>","Insert Item  "+jo.getString("institute_name"));
                    //  Log.d(">>>","start_salary  "+jo.getString("start_salary"));
                    progress.dismiss();
                    displayData();
                }
                else
                    AlertMessage.showMessage(this,"নতুন জব পাওয়া যায়নি","কিছুক্ষন পরে পুনরায় চেস্টা করুন");






            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void displayData()
    {


        jobAdvertisementItems= jobAdvertisementTable.jobAdvertisementItems();

        int size= jobAdvertisementItems.size();

        String[] tittle = new String[size];

        String[] salary_range = new String[size];

        String[] remaining_date = new String[size];

        String[] address = new String[size];

        String[] contact_number = new String[size];

        String[] positions = new String[size];

        int increment= 0;


        for(JobAdvertisementItem jobAdvertisementItem: jobAdvertisementItems)
        {

            tittle[increment]=jobAdvertisementItem.getInstitute_name_bangla();
            salary_range[increment]=English_to_bengali_number_conversion(jobAdvertisementItem.getStart_salary())+" থেকে "+English_to_bengali_number_conversion(jobAdvertisementItem.getEnd_salary());
            remaining_date[increment]= jobAdvertisementItem.getApplication_last_date();
            address[increment]=jobAdvertisementItem.getAddress_area()+" "+jobAdvertisementItem.getAddress_city();
            contact_number[increment] = jobAdvertisementItem.getMobile1();
            positions[increment] = jobAdvertisementItem.getPosition();
            increment++;

        }




        joblist=(ListView)findViewById(R.id.jobList);

        DisplayAllJobList displayAllJobList= new DisplayAllJobList(this, tittle, salary_range, remaining_date, address, contact_number,positions);
        joblist.setAdapter(displayAllJobList);



        joblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(DisplayAllJobsActivity.this,DetailsJobActivityNew.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }


    private long remaining_date(String lastDate)
    {
        String Datetime;
        Calendar c = Calendar.getInstance();

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Datetime = dateformat.format(c.getTime());
        //  Log.d(">>>>>","today "+Datetime);
        Date enddate=new Date();
        Date today=new Date();

        try{
            enddate=dateformat.parse(lastDate);
            today= dateformat.parse(Datetime);

            //  Log.d(">>>>>","today "+today);
            //  Log.d(">>>>>","endDay "+enddate);


        }
        catch (Exception e)
        {

        }
        long diff = enddate.getTime() - today.getTime();

        return diff/ (24 * 60 * 60 * 1000);
    }

    public String English_to_bengali_number_conversion(String english_number) {
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
        }
        return concatResult;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_all_jobs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
