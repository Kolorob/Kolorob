package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
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
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.DisplayAllJobList;
import demo.kolorob.kolorobdemoversion.adapters.Job_expand_list_adapter;
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
    private LinearLayout list_part;
    List<String> listDataHeader;
    ArrayList<String> job_data;
    HashMap<String, List<String>> listDataChild;
    ExpandableListView expListView;
    private int lastExpandedPosition = -1;
    Job_expand_list_adapter listAdapter;
    private int job_counter=0;
    ArrayList<ArrayList<String>> job_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_jobs);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;
        list_part=(LinearLayout)findViewById(R.id.list_part);





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
                if ((AppUtils.isNetConnected(getApplicationContext()) )&&(ContextCompat.checkSelfPermission(DisplayAllJobsActivity.this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED ))
                {
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

                else {
                    AlertMessage.showMessageClose(DisplayAllJobsActivity.this,"আপনার ফোনে ইন্টারনেট সংযোগ নেই।","অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");
                }


            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                displayData();

            }
        });
        alertDialog.setCancelable(false);


        alertDialog.show();


        close_button=(ImageView)findViewById(R.id.iv_close);
        iv_kolorob_logo=(ImageView)findViewById(R.id.iv_kolorob_logo);
        close_button.getLayoutParams().height=width/13;
        close_button.getLayoutParams().width=width/13;

        int p=iv_kolorob_logo.getLayoutParams().width=width/11;
        iv_kolorob_logo.getLayoutParams().height=(p*5)/6;

        context=this;

        close_button.setOnClickListener(new View.OnClickListener() {
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

        if(joblistCount!=0)
        {
            for (int i = 0; i < joblistCount; i++) {
                try {
                    JSONObject jo = joblistArray.getJSONObject(i);


                    JobAdvertisementItem si = JobAdvertisementItem.parseJobServiceProviderItem(i+1,jo);

                    //   JobAdvertisementItem six = JobAdvertisementItem.parseJobServiceProviderItem(jo);
                    jobAdvertisementTable.insertItem(si);
                    // Log.d(">>>","Insert Item  "+jo.getString("institute_name"));
                    //  Log.d(">>>","start_salary  "+jo.getString("start_salary"));
                    progress.dismiss();
                    displayData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else
            AlertMessage.showMessage(this,"নতুন জব পাওয়া যায়নি","কিছুক্ষন পরে পুনরায় চেস্টা করুন");
    }


    public void displayData()
    {


        jobAdvertisementItems= jobAdvertisementTable.jobAdvertisementItems();

        int size= jobAdvertisementItems.size();

        if(size==0)
        {
            AlertMessage.showMessage(this,"চাকুরীর তালিকা সম্পূর্ণ খালি","অনুগ্রহ পূর্বক আপডেট করুন");
        }

        else {
            job_list = new ArrayList<ArrayList<String>>(size);
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();
            job_data=new ArrayList<String>();
            for(JobAdvertisementItem jobAdvertisementItem: jobAdvertisementItems)
            {
                //job_data.clear();
                String jobdata= "আবেদনের শেষ সময়: "+jobAdvertisementItem.getApplication_last_date()+"@"+
                        "ঠিকানা: "+jobAdvertisementItem.getAddress_area()+" "+jobAdvertisementItem.getAddress_city()+"@"+
                        "অভিজ্ঞতা: "+"নাই"+"@"+
                        jobAdvertisementItem.getMobile1()+"@"+
                        jobAdvertisementItem.getEmail()+"v";

                String group_data= jobAdvertisementItem.getInstitute_name_bangla()+"@"+
                        "পজিশন: "+jobAdvertisementItem.getPosition()+"@"+
                        "বেতন: "+English_to_bengali_number_conversion(jobAdvertisementItem.getStart_salary())+" থেকে "+English_to_bengali_number_conversion(jobAdvertisementItem.getEnd_salary())+"@"+"v";
                job_data.add(jobdata);

                listDataHeader.add(group_data);
            //    listDataChild.put(group_data,job_data);
                job_counter++;
            }

            for(int k=0;k<size;k++)
            {

                job_list.add(k,job_data);
                // myList.get(0).set(k,bazar_data.get(k));
                //                      myList.add(k,temp);


            }


            for(int i=0;i<size;i++)
            {
                listDataChild.put(listDataHeader.get(i),job_list.get(i));

            }


            expListView = (ExpandableListView) findViewById(R.id.lvExp);
            listAdapter = new Job_expand_list_adapter(this, listDataHeader, listDataChild);
            expListView.setAdapter(listAdapter);
            expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {

                    return false;
                }
            });

            if(job_counter%2==0)
            {
                list_part.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
            }
            else {
                list_part.setBackgroundColor(ContextCompat.getColor(this,R.color.job_portal));

            }
            expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (lastExpandedPosition != -1
                            && groupPosition != lastExpandedPosition) {
                        expListView.collapseGroup(lastExpandedPosition);
                    }
                    lastExpandedPosition = groupPosition;

                    if(groupPosition%2==0)
                    {
                        expListView.setChildDivider(ContextCompat.getDrawable(context,R.color.white));
                        expListView.setDivider(ContextCompat.getDrawable(context,R.color.white));
                    }
                    else {
                        expListView.setChildDivider(ContextCompat.getDrawable(context,R.color.job_portal));
                        expListView.setDivider(ContextCompat.getDrawable(context,R.color.job_portal));
                    }
                }
            });
            expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                @Override
                public void onGroupCollapse(int groupPosition) {


                }
            });
            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    // TODO Auto-generated method stub
                    expListView.collapseGroup(lastExpandedPosition);

                    return false;
                }
            });

        }


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
