package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class DisplayAllJobsActivity extends Activity {

    private ImageView close_button;
    private TextView tv_button;
        ArrayList<JobAdvertisementItem> jobAdvertisementItems;
        JobAdvertisementTable jobAdvertisementTable =new JobAdvertisementTable(DisplayAllJobsActivity.this);
        Context context;
        ListView joblist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_jobs);



        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Do you want to Update Job Portal?");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


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

        alertDialog.show();

        close_button=(ImageView)findViewById(R.id.iv_close);
        tv_button=(TextView)findViewById(R.id.tv_close);


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
                JobAdvertisementItem si = JobAdvertisementItem.parseJobServiceProviderItem(jo);

             //   JobAdvertisementItem six = JobAdvertisementItem.parseJobServiceProviderItem(jo);
                jobAdvertisementTable.insertItem(si);
              // Log.d(">>>","Insert Item  "+jo.getString("institute_name"));
              //  Log.d(">>>","start_salary  "+jo.getString("start_salary"));

                displayData();





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

        long[] remaining_date = new long[size];

        String[] address = new String[size];

        String[] contact_number = new String[size];

        int increment= 0;


        for(JobAdvertisementItem jobAdvertisementItem: jobAdvertisementItems)
        {

            tittle[increment]=jobAdvertisementItem.getInstitute_name();
            salary_range[increment]=jobAdvertisementItem.getStart_salary()+" "+jobAdvertisementItem.getEnd_salary();
            remaining_date[increment]= remaining_date(jobAdvertisementItem.getApplication_last_date());
            address[increment]=jobAdvertisementItem.getAddress_area()+" "+jobAdvertisementItem.getAddress_city();
            contact_number[increment] = jobAdvertisementItem.getMobile1();
            increment++;

        }




        joblist=(ListView)findViewById(R.id.jobList);

        DisplayAllJobList displayAllJobList= new DisplayAllJobList(this, tittle, salary_range, remaining_date, address, contact_number);
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
