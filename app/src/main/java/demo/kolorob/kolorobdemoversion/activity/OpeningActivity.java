package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.Education.EducationCourseTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationFeeTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentBookTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentFieldTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentFitnessTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentTheatreTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialBillsTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialInsuranceTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialLoanTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialPaymentTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialSocialTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTaxTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTransactionTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTuitionTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthPharmacyTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccinesTable;
import demo.kolorob.kolorobdemoversion.database.Job.JobServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Job.JobTypeServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidtypeServiceProviderLegalAdviceTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidtypeServiceProviderSalishiTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.helpers.AppDialogManager;
import demo.kolorob.kolorobdemoversion.interfaces.RetryCallBackForNoInternet;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationCourseItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationFeeItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentBookShopItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentFieldItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentFitnessItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTheatreItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialBillsItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialInsuranceItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialLoanItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialPaymentItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialSocialItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTaxItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTransactionItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTuitionItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthPharmacyItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccinesItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobTypeServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidLegalAdviceItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidSalishiItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;
/**
 * Created by Yeakub Hassan Rafi on 27-Dec-15.
 * @author mity,arafat
 */

public class OpeningActivity extends Activity {
    public static final String DB_NAME = "kolorob.db";
    private final static int SPLASH_TIME_OUT = 500;
    private static final int INTERNET_PERMISSION = 1;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Context ctx;
    public static final String EDU_PROVIDER_TABLE = "edu_provider";
    public  SQLiteDatabase db3;
    ProgressDialog pd;
    public int height,width;
    Boolean  firstRun;



    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_opening);

        ImageView kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);//need to add bengali

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        width=displayMetrics.widthPixels;
        height=displayMetrics.heightPixels;


        LinearLayout boy = (LinearLayout) findViewById(R.id.boy);
        LinearLayout girl = (LinearLayout) findViewById(R.id.girl);

        RelativeLayout.LayoutParams kolorob_logo = new RelativeLayout.LayoutParams(width, height / 3);
        RelativeLayout.LayoutParams boy_layout = new RelativeLayout.LayoutParams(width / 2, (2 * height) / 3);
        RelativeLayout.LayoutParams girl_layout = new RelativeLayout.LayoutParams(width / 2, (2 * height) / 3 - height / 15);

        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);


        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        boy.setLayoutParams(boy_layout);
        girl.setLayoutParams(girl_layout);

        boy.bringToFront();
        girl.bringToFront();

        kolorob_logo.setMargins(0, 15, 0, 0);
        kolorobLogo.setLayoutParams(kolorob_logo);



        SharedPreferences settings = getSharedPreferences("prefs", 0);
        firstRun = settings.getBoolean("firstRun", false);
        if (firstRun == false)//if running for first time
        {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", true);
            editor.commit();

            if(!AppUtils.isNetConnected(getApplicationContext())) {
                AlertDialog alertDialog = new AlertDialog.Builder(OpeningActivity.this).create();
                alertDialog.setTitle("ইন্টারনেট সংযোগ বিচ্ছিন্ন");
                alertDialog.setMessage(" কলরব প্রথমবারের মত শুরু হতে যাচ্ছে। অনুগ্রহ পূর্বক ইন্টারনেট সংযোগটি চালু করুন ।  ");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                SharedPreferences settings = getSharedPreferences("prefs", 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putBoolean("firstRun", false);
                                editor.commit();
                                finish();
                            }
                        });
                alertDialog.show();
            }
            else



            {
                pd = new ProgressDialog(OpeningActivity.this, ProgressDialog.STYLE_SPINNER);
                pd.setIndeterminate(true);
                pd.show(OpeningActivity.this, AppConstants.WAITTAG, AppConstants.WAITDET);
                LoadData();
                pd.dismiss();
                Intent i = new Intent(OpeningActivity.this, LocationAskActivity.class);
            }
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(OpeningActivity.this).create();
            alertDialog.setTitle("আপনি কি তথ্য হালনাগাদ করতে চান? ");

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "না",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Intent i = new Intent(OpeningActivity.this, PlaceChoiceActivity.class);
                            startActivity(i);
                            dialog.dismiss();
                            finish();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "হ্যাঁ",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            if (AppUtils.isNetConnected(getApplicationContext())) {

                                pd = new ProgressDialog(OpeningActivity.this, ProgressDialog.STYLE_SPINNER);
                                pd.setIndeterminate(true);
                                pd.show(OpeningActivity.this, AppConstants.WAITTAG, AppConstants.WAITDET);


                                LoadData();
                            } else {
                                AlertDialog alertDialog = new AlertDialog.Builder(OpeningActivity.this).create();
                                alertDialog.setTitle("ইন্টারনেট সংযোগ বিচ্ছিন্ন");
                                alertDialog.setMessage(" দুঃখিত আপনার ইন্টারনেট সংযোগটি সচল নয়।  ");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                Intent i = new Intent(OpeningActivity.this, PlaceChoiceActivity.class);
                                                startActivity(i);
                                                dialog.dismiss();
                                                finish();
                                            }
                                        });
                                alertDialog.show();


                            }

                        }
                    });

            alertDialog.show();
        }
    }
    public void LoadData()
    {

        if ((AppUtils.isNetConnected(getApplicationContext()) )&&(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED )
                ) {
            getRequest(OpeningActivity.this, "get_categories", new VolleyApiCallback() {
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

            getRequest(OpeningActivity.this, "get_sub_categories", new VolleyApiCallback() {
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
            getRequest(OpeningActivity.this, "education/all", new VolleyApiCallback() {
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


            getRequest(OpeningActivity.this, "entertainment/all", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {

                        try {
                            JSONObject jo = new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveEntertainmentServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            getRequest(OpeningActivity.this, "health/all", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {

                        try {
                            JSONObject jo = new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveHealthServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            getRequest(OpeningActivity.this, "legal/all", new VolleyApiCallback() {
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
//

//            getRequest(OpeningActivity.this, "job", new VolleyApiCallback() {
//                        @Override
//                        public void onResponse(int status, String apiContent) {
//
//                            if (status == AppConstants.SUCCESS_CODE) {
//                                try {
//                                    JSONObject jo = new JSONObject(apiContent);
//                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
//                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
//                                        saveJobServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//            );
            getRequest(OpeningActivity.this, "finance/all", new VolleyApiCallback() {
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








        } else {
            if (!AppUtils.isNetConnected(getApplicationContext())) {

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db3=db.getReadableDatabase();
                if (db.isTableExists(db3,EDU_PROVIDER_TABLE)){
                    pd.dismiss();
                    Intent a = new Intent(getApplicationContext(),PlaceChoiceActivity.class);//Default Activity
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //getApplicationContext().startActivity(a);
                    (getApplicationContext()).startActivity(a);

                    this.finish();
                }
                else {
                    AppDialogManager.showNoInternetDialog(this, new RetryCallBackForNoInternet() {
                        @Override
                        public void retry() {
                            LoadData();
                        }
                    });
                }

            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED )
            {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                            INTERNET_PERMISSION);
                }
            }

        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, SPLASH_TIME_OUT);*/



    /**
     * Written by : Touhid
     */
    private void saveCategoryList(JSONArray categoryArray) {
        CategoryTable catTable = new CategoryTable(OpeningActivity.this);
        catTable.dropTable();
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
        subCatTable.dropTable();
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



    private void saveEducationServiceProvider(JSONArray educationServiceProvider) {
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(OpeningActivity.this);
        educationServiceProviderTable.dropTable();
        EducationFeeTable educationFeeTable = new EducationFeeTable(OpeningActivity.this);
        EducationCourseTable educationCourseTable = new EducationCourseTable(OpeningActivity.this);
        educationCourseTable.dropTable();
        educationFeeTable.dropTable();
        int eduServiceProviderCount = educationServiceProvider.length();
        for (int i = 0; i < eduServiceProviderCount; i++) {
            try {
                JSONObject jo = educationServiceProvider.getJSONObject(i);
                EducationServiceProviderItem et = EducationServiceProviderItem.parseEducationServiceProviderItem(jo);
                educationServiceProviderTable.insertItem(et);
                if(jo.has("EducationServiceProviderCourse"))
                {
                    JSONArray eduCourse = jo.getJSONArray("EducationServiceProviderCourse");
                    for( int k=0;k<eduCourse.length();k++)
                    {
                        JSONObject joesCourse= eduCourse.getJSONObject(k);

                        EducationCourseItem Eci = EducationCourseItem.parseEducationCourseItem(joesCourse);
                        educationCourseTable.insertItem(Eci);
                    }
                }
                if(jo.has("EduExamFees"))
                {
                    JSONArray eduExamFees = jo.getJSONArray("EduExamFees");

                    for( int j=0;j<eduExamFees.length();j++)
                    {
                        JSONObject joes= eduExamFees.getJSONObject(j);

                        EducationFeeItem Etf = EducationFeeItem.parseEducationFeeItem(joes);
                        educationFeeTable.insertItem(Etf);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Written by : arafat
     */
    private void saveHealthServiceProvider(JSONArray healthServiceProvider) {
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(OpeningActivity.this);
        healthServiceProviderTable.dropTable();
        HealthVaccinesTable healthVaccinesTable = new  HealthVaccinesTable(OpeningActivity.this);
        healthVaccinesTable.dropTable();
        HealthSpecialistTable healthSpecialistTable  = new  HealthSpecialistTable (OpeningActivity.this);
        healthSpecialistTable.dropTable();
        HealthPharmacyTable healthPharmacyTable = new HealthPharmacyTable(OpeningActivity.this);
        healthPharmacyTable.dropTable();
        int healthServiceProviderCount = healthServiceProvider.length();
        for (int i = 0; i < healthServiceProviderCount; i++) {
            try {
                JSONObject jo = healthServiceProvider.getJSONObject(i);
                HealthServiceProviderItem et = HealthServiceProviderItem.parseHealthServiceProviderItem(jo);
                healthServiceProviderTable.insertItemHealth(et);
                if(jo.has("specialist"))
                {
                    JSONArray specialist = jo.getJSONArray("specialist");

                    for( int m=0;m<specialist.length();m++)
                    {
                        JSONObject joes= specialist.getJSONObject(m);

                        HealthSpecialistItem ets =  HealthSpecialistItem.parseHealthSpecialistItem(joes);
                        healthSpecialistTable.insertItemHealth(ets);
                    }
                }
                if(jo.has("vaccine"))
                {
                    JSONArray vaccine = jo.getJSONArray("vaccine");

                    for( int n=0;n<vaccine.length();n++)
                    {
                        JSONObject joes= vaccine.getJSONObject(n);

                        HealthVaccinesItem etd =  HealthVaccinesItem.parseHealthVaccinesItem(joes);
                        healthVaccinesTable.insertItemHealth(etd);
                    }
                }
                if(jo.has("pharmacy"))
                {
                    JSONArray pharmacy = jo.getJSONArray("pharmacy");

                    for( int k=0;k<pharmacy.length();k++)
                    {
                        JSONObject joes= pharmacy.getJSONObject(k);
                        HealthPharmacyItem etl = HealthPharmacyItem.parseHealthPharmacyItem(joes);
                        healthPharmacyTable.insertItemHealthPharmacy(etl);
                    }
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveEntertainmentServiceProvider(JSONArray entertainmentServiceProvider) {
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(OpeningActivity.this);
        entertainmentServiceProviderTable.dropTable();
        EntertainmentBookTable entertainmentBookTable = new EntertainmentBookTable(OpeningActivity.this);
        entertainmentBookTable.dropTable();
        EntertainmentFieldTable entertainmentFieldTable = new EntertainmentFieldTable(OpeningActivity.this);
        entertainmentFieldTable.dropTable();
        EntertainmentTheatreTable entertainmentTheatreTable = new EntertainmentTheatreTable(OpeningActivity.this);
        entertainmentTheatreTable.dropTable();
        EntertainmentFitnessTable entertainmentFitnessTable = new EntertainmentFitnessTable(OpeningActivity.this);
        entertainmentFitnessTable.dropTable();
        int entServiceProviderCount = entertainmentServiceProvider.length();
        for (int i = 0; i < entServiceProviderCount; i++) {
            try {
                JSONObject jo = entertainmentServiceProvider.getJSONObject(i);
                EntertainmentServiceProviderItem et = EntertainmentServiceProviderItem.parseEntertainmentServiceProviderItem(jo);
                entertainmentServiceProviderTable.insertItem(et);

                if(jo.has("EntFitnessBeauty"))
                {
                    JSONArray EntFitnessBeauty = jo.getJSONArray("EntFitnessBeauty");

                    for( int m=0;m<EntFitnessBeauty.length();m++)
                    {
                        JSONObject joes= EntFitnessBeauty.getJSONObject(m);
                        EntertainmentFitnessItem ets = EntertainmentFitnessItem.parseEntertainmentFitnessItem(joes);
                        entertainmentFitnessTable.insertItem(ets);
                    }
                }

                if(jo.has("EntBookShop"))
                {
                    JSONArray EntBookShop = jo.getJSONArray("EntBookShop");
                    for( int j=0;j<EntBookShop.length();j++)
                    {
                        JSONObject joes= EntBookShop.getJSONObject(j);
                        EntertainmentBookShopItem ets = EntertainmentBookShopItem.parseEntertainmentBookShopItem(joes);
                        entertainmentBookTable.insertItem(ets);
                    }
                }

                if(jo.has("EntField"))
                {
                    JSONArray EntField = jo.getJSONArray("EntField");
                    for( int k=0;k<EntField.length();k++)
                    {
                        JSONObject joes= EntField.getJSONObject(k);
                        EntertainmentFieldItem ets = EntertainmentFieldItem.parseEntertainmentFieldItem(joes);
                        entertainmentFieldTable.insertItem(ets);
                    }
                }

                if(jo.has("EntTheatre"))
                {
                    JSONArray EntTheatre = jo.getJSONArray("EntTheatre");
                    for( int l=0;l<EntTheatre.length();l++)
                    {
                        JSONObject joes= EntTheatre.getJSONObject(l);
                        EntertainmentTheatreItem etc = EntertainmentTheatreItem.parseEntertainmentTheatreItem(joes);
                        entertainmentTheatreTable.insertItem(etc);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveLegalaidServiceProvider(JSONArray legalaidServiceProvider) {
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(OpeningActivity.this);
        legalAidServiceProviderTable.dropTable();
        int legalaidServiceProviderCount = legalaidServiceProvider.length();
        LegalAidtypeServiceProviderSalishiTable legalAidtypeServiceProviderSalishiTable = new LegalAidtypeServiceProviderSalishiTable(OpeningActivity.this);
        legalAidtypeServiceProviderSalishiTable.dropTable();
        LegalAidtypeServiceProviderLegalAdviceTable legalAidtypeServiceProviderLegalAdviceTable = new LegalAidtypeServiceProviderLegalAdviceTable(OpeningActivity.this);
        legalAidtypeServiceProviderLegalAdviceTable.dropTable();
        for (int i = 0; i < legalaidServiceProviderCount; i++) {
            try {
                JSONObject jo = legalaidServiceProvider.getJSONObject(i);
                LegalAidServiceProviderItem et = LegalAidServiceProviderItem.parseLegalAidServiceProviderItem(jo);
                legalAidServiceProviderTable.insertItem(et);
                if(jo.has("LegalAdvice"))
                {
                    JSONArray LegalAdvice = jo.getJSONArray("LegalAdvice");
                    for( int j=0;j<LegalAdvice.length();j++)
                    {
                        JSONObject joes= LegalAdvice.getJSONObject(j);
                        LegalAidLegalAdviceItem lasi = LegalAidLegalAdviceItem.parseLegalAidLegalAdviceItem(joes);
                        legalAidtypeServiceProviderLegalAdviceTable.insertItem(lasi);
                    }
                }
                if(jo.has("Salishi"))
                {
                    JSONArray Salishi = jo.getJSONArray("Salishi");
                    for( int j=0;j<Salishi.length();j++)
                    {
                        JSONObject joes= Salishi.getJSONObject(j);
                        LegalAidSalishiItem legalAidSalishiItem = LegalAidSalishiItem.parseLegalAidSalishiItem(joes);
                        legalAidtypeServiceProviderSalishiTable.insertItem(legalAidSalishiItem);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveLegalaidSalishi(JSONArray legalaidtypeServiceProvider) {
        LegalAidtypeServiceProviderSalishiTable legalAidtypeServiceProviderSalishiTable = new LegalAidtypeServiceProviderSalishiTable(OpeningActivity.this);
        legalAidtypeServiceProviderSalishiTable.dropTable();
        int legalaidtypeServiceProviderCount = legalaidtypeServiceProvider.length();
        for (int i = 0; i < legalaidtypeServiceProviderCount; i++) {
            try {
                JSONObject jo = legalaidtypeServiceProvider.getJSONObject(i);
                LegalAidSalishiItem et = LegalAidSalishiItem.parseLegalAidSalishiItem(jo);
                legalAidtypeServiceProviderSalishiTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }



    private void saveFinancialServiceProvider(JSONArray financialServiceProvider) {
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(OpeningActivity.this);
        financialServiceProviderTable.dropTable();
        FinancialBillsTable financialBillsTable = new FinancialBillsTable(OpeningActivity.this);
        financialBillsTable.dropTable();
        FinancialInsuranceTable financialInsuranceTable = new FinancialInsuranceTable(OpeningActivity.this);
        financialInsuranceTable.dropTable();
        FinancialTaxTable financialTaxTable = new FinancialTaxTable(OpeningActivity.this);
        financialTaxTable.dropTable();
        FinancialTransactionTable financialTransactionTable = new FinancialTransactionTable(OpeningActivity.this);
        financialTransactionTable.dropTable();
        FinancialTuitionTable financialTuitionTable = new FinancialTuitionTable(OpeningActivity.this);
        financialTuitionTable.dropTable();
        FinancialSocialTable financialSocialTable = new FinancialSocialTable(OpeningActivity.this);
        financialSocialTable.dropTable();
        FinancialLoanTable financialLoanTable = new FinancialLoanTable(OpeningActivity.this);
        financialLoanTable.dropTable();
        FinancialPaymentTable financialPaymentTable = new FinancialPaymentTable(OpeningActivity.this);
        financialPaymentTable.dropTable();
        int financialServiceProviderCount = financialServiceProvider.length();
        for (int i = 0; i < financialServiceProviderCount; i++) {
            try {
                JSONObject jo = financialServiceProvider.getJSONObject(i);
                FinancialServiceProviderItem et = FinancialServiceProviderItem.parseFinancialServiceProviderItem(jo);
                financialServiceProviderTable.insertItem(et);
                if(jo.has("Bills"))
                {
                    JSONArray Bills = jo.getJSONArray("Bills");

                    for( int k=0;k<Bills.length();k++)
                    {
                        JSONObject joes= Bills.getJSONObject(k);

                        FinancialBillsItem ets = FinancialBillsItem.parseFinancialBillsItem(joes);
                        financialBillsTable.insertItem(ets);
                    }
                }

                if(jo.has("Insurance"))
                {
                    JSONArray Insurance = jo.getJSONArray("Insurance");
                    for( int l=0;l<Insurance.length();l++)
                    {
                        JSONObject joes= Insurance.getJSONObject(l);
                        FinancialInsuranceItem etx = FinancialInsuranceItem.parseFinancialInsuranceItem(joes);
                        financialInsuranceTable.insertItem(etx);
                    }
                }

                if(jo.has("Tax"))
                {
                    JSONArray Tax = jo.getJSONArray("Tax");
                    for( int k=0;k<Tax.length();k++)
                    {
                        JSONObject joes= Tax.getJSONObject(k);

                        FinancialTaxItem etd =  FinancialTaxItem.parseFinancialTaxItem(joes);
                        financialTaxTable.insertItem(etd);
                    }
                }

                if(jo.has("Transaction"))
                {
                    JSONArray Transaction = jo.getJSONArray("Transaction");
                    for( int k=0;k<Transaction.length();k++)
                    {
                        JSONObject joes= Transaction.getJSONObject(k);

                        FinancialTransactionItem etc = FinancialTransactionItem.parseFinancialTransactionItem(joes);
                        financialTransactionTable.insertItem(etc);
                    }
                }

                if(jo.has("Tuition"))
                {
                    JSONArray Tuition = jo.getJSONArray("Tuition");
                    for( int k=0;k<Tuition.length();k++)
                    {
                        JSONObject joes= Tuition.getJSONObject(k);
                        FinancialTuitionItem etk = FinancialTuitionItem.parseFinancialTuitionItem(joes);
                        financialTuitionTable.insertItem(etk);
                    }
                }


                if(jo.has("Social"))
                {
                    JSONArray Social = jo.getJSONArray("Social");
                    for( int k=0;k<Social.length();k++)
                    {
                        JSONObject joes= Social.getJSONObject(k);
                        FinancialSocialItem ect = FinancialSocialItem.parseFinancialSocialItem(joes);
                        financialSocialTable.insertItem(ect);
                    }
                }


                if(jo.has("Loan"))
                {
                    JSONArray Loan = jo.getJSONArray("Loan");
                    for( int k=0;k<Loan.length();k++)
                    {
                        JSONObject joes= Loan.getJSONObject(k);
                        FinancialLoanItem cet = FinancialLoanItem.parseFinancialLoanItem(joes);
                        financialLoanTable.insertItem(cet);
                    }
                }

                if(jo.has("PaymentDocs"))
                {
                    JSONArray PaymentDocs = jo.getJSONArray("PaymentDocs");
                    for( int k=0;k<PaymentDocs.length();k++)
                    {
                        JSONObject joes= PaymentDocs.getJSONObject(k);
                        FinancialPaymentItem etm = FinancialPaymentItem.parseFinancialPaymentItem(joes);
                        financialPaymentTable.insertItem(etm);
                    }
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        if (firstRun == false)//if running for first time
        {
            pd.dismiss();
            Intent i = new Intent(OpeningActivity.this, LocationAskActivity.class);//Activity to be     launched For the First time
            // Intent i = new Intent(OpeningActivity.this, FeedbackActivity.class);//Activity to be     launched For the First time
            startActivity(i);

        } else {
            pd.dismiss();
            Intent a = new Intent(OpeningActivity.this, PlaceChoiceActivity.class);//Default Activity
            //Intent a = new Intent(OpeningActivity.this, FeedbackActivity.class);
            startActivity(a);

        }

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