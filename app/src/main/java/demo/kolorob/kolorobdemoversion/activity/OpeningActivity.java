package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Job.JobServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.parser.VolleyApiParser;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class OpeningActivity extends BaseActivity {

    private final static int SPLASH_TIME_OUT = 500;
    private static final int INTERNET_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        ImageView kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        LinearLayout boy = (LinearLayout) findViewById(R.id.boy);
        LinearLayout girl = (LinearLayout) findViewById(R.id.girl);
        LinearLayout shadowBoy = (LinearLayout) findViewById(R.id.shadow_boy);
        LinearLayout shadowGirl = (LinearLayout) findViewById(R.id.shadow_girl);


        RelativeLayout.LayoutParams kolorob_logo = new RelativeLayout.LayoutParams(width, height / 3);
        RelativeLayout.LayoutParams boy_layout = new RelativeLayout.LayoutParams(width / 2, (2 * height) / 3);
        RelativeLayout.LayoutParams girl_layout = new RelativeLayout.LayoutParams(width / 2, (2 * height) / 3 - height / 15);
        RelativeLayout.LayoutParams boy_shadow = new RelativeLayout.LayoutParams(width / 2, height / 12);
        RelativeLayout.LayoutParams girl_shadow = new RelativeLayout.LayoutParams(width / 2, height / 12);

        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        boy_shadow.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        girl_shadow.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_shadow.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        boy.setLayoutParams(boy_layout);
        girl.setLayoutParams(girl_layout);

        boy.bringToFront();
        girl.bringToFront();

        shadowBoy.setLayoutParams(boy_shadow);
        shadowGirl.setLayoutParams(girl_shadow);

        kolorob_logo.setMargins(0, 15, 0, 0);
        kolorobLogo.setLayoutParams(kolorob_logo);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED) {

            VolleyApiParser.getRequest(OpeningActivity.this, "get_categories", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveCategoryList(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );

            VolleyApiParser.getRequest(OpeningActivity.this, "get_sub_categories", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveSubCategoryList(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );

            VolleyApiParser.getRequest(OpeningActivity.this, "get_edu_service_provider", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveEducationServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );

            VolleyApiParser.getRequest(OpeningActivity.this, "entertainment", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if(status == AppConstants.SUCCESS_CODE){

                        try{
                            JSONObject jo= new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if(apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveEntertainmentServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            VolleyApiParser.getRequest(OpeningActivity.this, "health", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if(status == AppConstants.SUCCESS_CODE){

                        try{
                            JSONObject jo= new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if(apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveHealthServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            VolleyApiParser.getRequest(OpeningActivity.this, "legal_aid", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveLegalaidServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );

            VolleyApiParser.getRequest(OpeningActivity.this, "job", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveJobServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            VolleyApiParser.getRequest(OpeningActivity.this, "finance", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );



        }
        else
        {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    INTERNET_PERMISSION);
        }

       /* new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

            }
        }, SPLASH_TIME_OUT);*/

    }

    /** Written by : Touhid */
    private void saveCategoryList(JSONArray categoryArray) {
        CategoryTable catTable = new CategoryTable(OpeningActivity.this);
        int catCount = categoryArray.length();
        for (int i = 0; i < catCount; i++) {
            try {
                JSONObject jo = categoryArray.getJSONObject(i);
                CategoryItem ci = CategoryItem.parseCategoryItem(jo);
                catTable.insertItem(ci);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveSubCategoryList(JSONArray subCategoryArray) {
        SubCategoryTable subCatTable = new SubCategoryTable(OpeningActivity.this);
        int subCatCount = subCategoryArray.length();
        for (int i = 0; i < subCatCount; i++) {
            try {
                JSONObject jo = subCategoryArray.getJSONObject(i);
                SubCategoryItem si = SubCategoryItem.parseSubCategoryItem(jo);
                subCatTable.insertItem(si);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveEducationServiceProvider(JSONArray educationServiceProvider)
    {
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(OpeningActivity.this);
        int eduServiceProviderCount = educationServiceProvider.length();

        for (int i = 0; i < eduServiceProviderCount; i++) {
            try {
                JSONObject jo = educationServiceProvider.getJSONObject(i);
                EducationServiceProviderItem et = EducationServiceProviderItem.parseEducationServiceProviderItem(jo);
                educationServiceProviderTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

    private void saveEntertainmentServiceProvider(JSONArray entertainmentServiceProvider)
    {
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(OpeningActivity.this);
        int entServiceProviderCount = entertainmentServiceProvider.length();

        for (int i = 0; i < entServiceProviderCount; i++) {
            try {
                JSONObject jo = entertainmentServiceProvider.getJSONObject(i);
                EntertainmentServiceProviderItem et = EntertainmentServiceProviderItem.parseEntertainmentServiceProviderItem(jo);
                entertainmentServiceProviderTable.insertItemEntertainment(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

    private void saveHealthServiceProvider(JSONArray healthServiceProvider)
    {
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(OpeningActivity.this);
        int healthServiceProviderCount = healthServiceProvider.length();

        for (int i = 0; i < healthServiceProviderCount; i++) {
            try {
                JSONObject jo = healthServiceProvider.getJSONObject(i);
                HealthServiceProviderItem et = HealthServiceProviderItem.parseHealthServiceProviderItem(jo);
                healthServiceProviderTable.insertItemHealth(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveLegalaidServiceProvider(JSONArray legalaidServiceProvider) {
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(OpeningActivity.this);
        int legalaidServiceProviderCount = legalaidServiceProvider.length();

        for (int i = 0; i < legalaidServiceProviderCount; i++) {
            try {
                JSONObject jo = legalaidServiceProvider.getJSONObject(i);
                LegalAidServiceProviderItem et = LegalAidServiceProviderItem.parseLegalAidServiceProviderItem(jo);
                legalAidServiceProviderTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveJobServiceProvider(JSONArray jobServiceProvider) {
        JobServiceProviderTable jobServiceProviderTable = new JobServiceProviderTable(OpeningActivity.this);
        int jobServiceProviderCount = jobServiceProvider.length();

        for (int i = 0; i < jobServiceProviderCount; i++) {
            try {
                JSONObject jo = jobServiceProvider.getJSONObject(i);
                JobServiceProviderItem et = JobServiceProviderItem.parseJobServiceProviderItem(jo);
                jobServiceProviderTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveFinancialServiceProvider(JSONArray financialServiceProvider) {
        FinancialServiceProviderTable financialServiceProviderTable = new  FinancialServiceProviderTable(OpeningActivity.this);
        int financialServiceProviderCount = financialServiceProvider.length();

        for (int i = 0; i < financialServiceProviderCount; i++) {
            try {
                JSONObject jo = financialServiceProvider.getJSONObject(i);
                FinancialServiceProviderItem et = FinancialServiceProviderItem.parseFinancialServiceProviderItem(jo);
                financialServiceProviderTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Intent i = new Intent(OpeningActivity.this, LocationAskActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == INTERNET_PERMISSION) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Internet permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Inter permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
