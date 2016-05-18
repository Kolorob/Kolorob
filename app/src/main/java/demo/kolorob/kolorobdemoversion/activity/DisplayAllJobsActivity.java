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
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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


                        JobAdvertisementItem jobAdvertisementItem;
                        ArrayList<JobAdvertisementItem> jobAdvertisementItems;
                        ArrayList<JobAdvertisementItem> jobAdvertisementItems1;

                        JobAdvertisementTable jobAdvertisementTable =new JobAdvertisementTable(DisplayAllJobsActivity.this);

                        jobAdvertisementItems= jobAdvertisementTable.jobAdvertisementItems();
                        jobAdvertisementItems1= jobAdvertisementTable.jobAdvertisementItems();




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




        context=this;

        String[] tittle = new String[] { "Plumber Needed",
                "Electrician Needed",
                "Babysitter Needed",

        };

        String[] salary_range = new String[] { "10,000 Taka",
                "30,000 Taka",
                "20,000 Taka",

        };

        String[] remaining_date = new String[] { "3 days",
                "4 days",
                "1 day",

        };

        String[] address = new String[] { "Bauniabad",
                "Paris Road",
                "Bauniabad",

        };

        String[] contact_number = new String[] { "01988009755",
                "01790615263",
                "01558409186",

        };


        joblist=(ListView)findViewById(R.id.jobList);

        DisplayAllJobList displayAllJobList= new DisplayAllJobList(this,tittle, salary_range, remaining_date, address, contact_number);
        joblist.setAdapter(displayAllJobList);


        joblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Intent intent = new Intent(DisplayAllJobsActivity.this,DetailsJobActivity.class);
                startActivity(intent);
            }
        });



    }


    private void SaveNewJobs(JSONArray joblistArray) {
        JobAdvertisementTable jobAdvertisementTable = new JobAdvertisementTable(DisplayAllJobsActivity.this);
        jobAdvertisementTable.dropTable();
        int joblistCount = joblistArray.length();

        Log.d(">>>","Total Joblist  "+joblistCount);
        for (int i = 0; i < joblistCount; i++) {
            try {
                JSONObject jo = joblistArray.getJSONObject(i);
                JobAdvertisementItem si = JobAdvertisementItem.parseJobServiceProviderItem(jo);
                Log.d(">>>","Insert Item  "+si);
                JobAdvertisementItem six = JobAdvertisementItem.parseJobServiceProviderItem(jo);
                jobAdvertisementTable.insertItem(si);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
