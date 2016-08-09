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
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.database.Education.EducationNewTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTrainingDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTuitionDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmetTypeTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceNewTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovernmentNewTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovernmentServiceDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthPharmacyTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTableDetails;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccineTableDetails;
import demo.kolorob.kolorobdemoversion.database.Health.HealthVaccinesTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidDetailsTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.RatingTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.helpers.AppDialogManager;
import demo.kolorob.kolorobdemoversion.interfaces.RetryCallBackForNoInternet;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTrainingDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTuitionDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentTypeItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentServiceDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthPharmacyItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItemDetails;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccineItemDetails;
import demo.kolorob.kolorobdemoversion.model.Health.HealthVaccinesItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LeagalAidDetailsItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.RatingModel;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;


/**
 * Created by Yeakub Hassan Rafi on 27-Dec-15.
 * @author mity,arafat
 */

public class OpeningActivity extends Activity {
    public static final String DB_NAME = "kolorob.db";
    private final static int SPLASH_TIME_OUT = 500;
    private static final int INTERNET_PERMISSION = 1;
    private static final int NUMBER_OF_TASKS = 9;
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    String app_ver="";
    Boolean drop=false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    AlertDialog alertDialog;
    private GoogleApiClient client;
    private AnimationDrawable frameAnimation;
    private Context ctx;
    public static final String EDU_PROVIDER_TABLE = "edu_provider";
    public  SQLiteDatabase db3;
    ProgressDialog pd;
    public int height,width;
    Boolean  firstRun;
    private int EntDataSize,HealthDatSize;
    private static final int ANIM_INTERVAL = 200;
    int countofDb;
    ArrayList<SubCategoryItem>si2=new ArrayList<>();
    ArrayList<RatingModel>si22=new ArrayList<>();
    ArrayList<SubCategoryItemNew>si3=new ArrayList<>();
    HealthVaccineTableDetails healthVaccineTableDetails;
    HealthSpecialistTableDetails healthVaccineTableDetails1;
    LegalAidDetailsTable legalAidDetailsTable;
    // LM's variables
    private Toast t = null;
    static int countOfDBLocal = 0;


    public int getCountofDb() {
        return countofDb;
    }
    // ArrayList<Trialholder>holdertrial=new ArrayList<>();
    public void setCountofDb(int countofDb) {
        this.countofDb = countofDb;
    }



    Context context;
    ImageView rotateImage;
    private Handler handler;
    int in = 0;
    View view=null;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_opening);

        ImageView kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);//need to add bengali
        try
        {
            app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;

            Float currentVersion= Float.parseFloat(app_ver);
            Float previousVersion=Float.parseFloat(SharedPreferencesHelper.getVersion(OpeningActivity.this));

            if(currentVersion>=previousVersion)
                drop=true;
            else
                drop=false;

        }
        catch (Exception e)
        {

        }


        context = this;

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        width=displayMetrics.widthPixels;
        height=displayMetrics.heightPixels;



        SharedPreferences settings = getSharedPreferences("prefs", 0);
        firstRun = settings.getBoolean("firstRun", false);
        if (firstRun == false)//if running for first time
        {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", true);
            editor.commit();

            if(!AppUtils.isNetConnected(getApplicationContext())) {
                alertDialog = new AlertDialog.Builder(OpeningActivity.this).create();
                alertDialog.setTitle("ইন্টারনেট সংযোগ বিচ্ছিন্ন");
                alertDialog.setCanceledOnTouchOutside(false);
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

                settings.edit().putLong("time", System.currentTimeMillis()).commit();
// get the time and make a date out of it

//                pd = new ProgressDialog(OpeningActivity.this, ProgressDialog.STYLE_SPINNER);
//                pd.setIndeterminate(true);
//                pd.show(OpeningActivity.this, AppConstants.WAITTAG, AppConstants.WAITDET);
                LoadData();

                //   pd.dismiss();
            }
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(OpeningActivity.this).create();
            alertDialog.setTitle("আপনি কি তথ্য আপডেট করতে চান? ");
            alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "না",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Log.e("open4",String.valueOf(getCountofDb()));
                            Intent i = new Intent(OpeningActivity.this, PlaceSelectionActivity.class);
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
                                countofDb=0 ;
                                SharedPreferences settings = getSharedPreferences("prefs", 0);
                                SharedPreferences.Editor editor = settings.edit();
                                settings.edit().putLong("time", System.currentTimeMillis()).commit();

                               editor.putInt("KValue", countofDb);
                                editor.commit();
//                                pd = new ProgressDialog(OpeningActivity.this, ProgressDialog.STYLE_SPINNER);
//                                pd.setIndeterminate(true);
//                                pd.show(OpeningActivity.this, AppConstants.WAITTAG, AppConstants.WAITDET);


                                LoadData();
                            } else {
                                AlertDialog alertDialog = new AlertDialog.Builder(OpeningActivity.this).create();

                                alertDialog.setTitle("ইন্টারনেট সংযোগ বিচ্ছিন্ন");
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.setMessage(" দুঃখিত আপনার ইন্টারনেট সংযোগটি সচল নয়।  ");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                Log.e("open3",String.valueOf(countofDb));
                                                Intent i = new Intent(OpeningActivity.this, PlaceSelectionActivity.class);
                                                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
            alertDialog.setCanceledOnTouchOutside(false);
        }
    }
    public void LoadData()
    {

        /*
        @@@@ arafat, you have to control wheel from here
        moving wheel while loading data into local database
         */







//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                                  /* start the activity */
//
////                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                overridePendingTransition(0, 0);
//                Intent a = new Intent(OpeningActivity.this, PlaceSelectionActivity.class); // Default Activity
//                startActivity(a);
//
//                //  finish();
//            }
//        }, 60000);
//        //setImage();
//
        final Handler handler = new Handler();
        Runnable runner = new Runnable() {
            int timeCounter = 0;
            @Override
            public void run() {
                if (OpeningActivity.this.countofDb >= NUMBER_OF_TASKS || timeCounter > 60000) {
                    overridePendingTransition(0, 0);
                    handler.removeCallbacks(this);
                    Intent a = new Intent(OpeningActivity.this, PlaceSelectionActivity.class); // Default Activity
                    frameAnimation.stop();
                    startActivity(a);
                    return;
                }
                //Create a loop
                handler.postDelayed(this, 1000);
                timeCounter += 1000;
            }

        };
        handler.postDelayed(runner, 1000);

        rotateImage = (ImageView) findViewById(R.id.rotate_image);
        rotateImage.setBackgroundResource(R.drawable.frame_animation_list);
        frameAnimation = (AnimationDrawable) rotateImage.getBackground();
        frameAnimation.setOneShot(false);
        frameAnimation.start();


        if ((AppUtils.isNetConnected(getApplicationContext()) )&&(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED )
                ) {

            //get All using sql api
            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/getsql?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                new SaveSQL(OpeningActivity.this).execute(apiContent);
                            }
                        }
                    }
            );


            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/sp/health?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {
                        try {

                            JSONArray allData = new JSONArray(apiContent);
                            healthVaccineTableDetails= new HealthVaccineTableDetails(OpeningActivity.this);
                            healthVaccineTableDetails.dropTable();
                            healthVaccineTableDetails1 = new HealthSpecialistTableDetails(OpeningActivity.this);
                            healthVaccineTableDetails1.dropTable();
                            new SaveHealthtDataTask(OpeningActivity.this).execute(allData);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/get_sp_rating?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {


                                try {
                                    JSONArray jo = new JSONArray(apiContent);
                                    new SaveRatingTask(OpeningActivity.this).execute(jo);
                                    // String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    // if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                    //     new SaveSubCategoryListTask(OpeningActivity.this).execute(jo.getJSONArray(AppConstants.KEY_DATA));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/refs_old?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {


                                try {
                                    JSONArray jo = new JSONArray(apiContent);
                                    new SaveSubCategoryListTask(OpeningActivity.this).execute(jo);
                                    // String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    // if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                    //     new SaveSubCategoryListTask(OpeningActivity.this).execute(jo.getJSONArray(AppConstants.KEY_DATA));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );

            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/categories?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {

                                    JSONArray jo = new JSONArray(apiContent);

                                    new SaveCategoryListTask(OpeningActivity.this).execute(jo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            //
            //

            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/refs?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {


                                try {
                                    JSONArray jo = new JSONArray(apiContent);

                                    new SaveSubCategoryNewListTask(OpeningActivity.this).execute(jo);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            /*getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/sp/education?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {

                                    JSONArray allData = new JSONArray(apiContent);
                                    new SavenewEduTask(OpeningActivity.this).execute(allData);

//                                          frameAnimation.stop();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );*/


            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/sp/entertainment?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {


                    try {

                        JSONArray allData = new JSONArray(apiContent);
                        new SaveEntertainmentDataTask(OpeningActivity.this).execute(allData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/sp/financial?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                try {

                                    JSONArray jo = new JSONArray(apiContent);
                                    new SavenewFinanceTask(OpeningActivity.this).execute(jo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            /*getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/sp/government?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                try {

                                    JSONArray jo = new JSONArray(apiContent);
                                    new SavenewGovTask(OpeningActivity.this).execute(jo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );*/


            getRequest(OpeningActivity.this, "http://kolorob.net/demo/api/sp/legal?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            try {

                                JSONArray legal_array = new JSONArray(apiContent);
                                legalAidDetailsTable = new LegalAidDetailsTable(OpeningActivity.this);
                                legalAidDetailsTable.dropTable();
                                new SaveLegaltDataTask(OpeningActivity.this).execute(legal_array);

                            } catch (JSONException e) {
                                e.printStackTrace();
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
                    Log.e("open1",String.valueOf(countofDb));
                    Intent a = new Intent(getApplicationContext(),PlaceSelectionActivity.class);//Default Activity
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //getApplicationContext().startActivity(a);
                    (getApplicationContext()).startActivity(a);

                    this.finish();
                }
                else {
                    Toast.makeText(this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...",
                            Toast.LENGTH_LONG).show();
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
    private Animation slideInFromRightAnim() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromRight.setDuration(ANIM_INTERVAL *
                (int) (200 *
                        (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                )
        );
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
        }

    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        slideInFromRightAnim();
        super.onStart();

        System.out.println("----main activity---onStart---");


        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
    public void makeToastWithShortbread(String message) {
        if (t != null) {
            t.cancel();
            t = null;
        }
        t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        if (t != null)
            t.show();
    }

    // ASYNC TASKS
    abstract class GenericSaveDBTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
        private Context ctx;

        public GenericSaveDBTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPostExecute(Result result) {
            if (((Long) result).longValue() == 0.0 && countofDb < NUMBER_OF_TASKS)  { // Means the task is successful
                countofDb++;
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("KValue", countofDb);
                editor.commit();
                Log.d("tasks", "Tasks remaining: " + (NUMBER_OF_TASKS - countofDb));
                makeToastWithShortbread("Tasks remaining: " + (NUMBER_OF_TASKS - countofDb));
            }
        }

    }

    class SaveCategoryListTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveCategoryListTask(Context ctx) {
            super(ctx);
        }

        @Override
        protected Long doInBackground(JSONArray... categoryArrays) {
            JSONArray categoryArray = categoryArrays[0];
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
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    class SaveSubCategoryNewListTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveSubCategoryNewListTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... categoryArrays) {
            JSONArray subcat = categoryArrays[0];
            SubCategoryTableNew subCategoryTableNew = new SubCategoryTableNew(OpeningActivity.this);
            subCategoryTableNew.dropTable();

            int legalaidServiceProviderCount = subcat.length();

            for (int i = 0; i < legalaidServiceProviderCount; i++) {
                try {
                    JSONObject jo = subcat.getJSONObject(i);
                    SubCategoryItemNew et = SubCategoryItemNew.parseSubCategoryItem(jo);
                    subCategoryTableNew.insertItem(et);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }

            si3 = subCategoryTableNew.getAllSubCat();
            si3.size();

            return new Long(0);
        }
    }

    class SaveSubCategoryListTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveSubCategoryListTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonObjects) {
            JSONArray subCategoryArray = jsonObjects[0];
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
                    return new Long(-1);
                }
            }
            si2 = subCatTable.getAllSubCategories(1);
            si2.size();
            return new Long(0);
        }
    }


    class SaveHealthtDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveHealthtDataTask(Context ctx) {
            super(ctx);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray allData = jsonArrays[0];
            HealthServiceProviderTableNew healthServiceProviderTableNew = new HealthServiceProviderTableNew(OpeningActivity.this);
                healthServiceProviderTableNew.dropTable();

            HealthDatSize = allData.length();

            Log.d("HealthData","********"+HealthDatSize);

            for (int i = 0; i < HealthDatSize; i++) {
                try {

                    JSONObject jsonObject = allData.getJSONObject(i);
                    HealthServiceProviderItemNew healthServiceProviderItemNew = HealthServiceProviderItemNew.parseHealthServiceProviderItem(jsonObject);
                    healthServiceProviderTableNew.insertItemHealth(healthServiceProviderItemNew);
                    if (jsonObject.has("health_vaccine_details")) {
                        JSONArray healthVaccine = jsonObject.getJSONArray("health_vaccine_details");
                        int HealthVaccineDataSize = healthVaccine.length();

                        for (int v = 0; v < HealthVaccineDataSize; v++) {
                            JSONObject healthVaccineItem = healthVaccine.getJSONObject(v);
                            SaveHealthVaccineData(healthVaccineItem, jsonObject.getInt("id"));
                        }
                    }
                    if (jsonObject.has("health_specialist_details")) {
                        JSONArray health_specialist_details = jsonObject.getJSONArray("health_specialist_details");
                        int HealthVaccineDataSize = health_specialist_details.length();

                        for (int v = 0; v < HealthVaccineDataSize; v++) {
                            JSONObject healthSpecialistItem = health_specialist_details.getJSONObject(v);
                            SaveSpecialistData(healthSpecialistItem, jsonObject.getInt("id"));
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }

        private void SaveSpecialistData(JSONObject jsonObject, int foreign_key) {

            try {
                HealthSpecialistItemDetails healthSpecialistItemDetails = HealthSpecialistItemDetails.parseHealthSpecialistItem(jsonObject, foreign_key);

                healthVaccineTableDetails1.insertItemHealth(healthSpecialistItemDetails);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        private void SaveHealthVaccineData(JSONObject jsonObject, int foreign_key) {


            try {
                HealthVaccineItemDetails healthVaccineItemDetails = HealthVaccineItemDetails.parseHealthVaccinesItem(jsonObject, foreign_key);
                healthVaccineTableDetails.insertItemHealth(healthVaccineItemDetails);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    class SaveEntertainmentDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveEntertainmentDataTask(Context ctx) {
            super(ctx);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray allData = jsonArrays[0];
            int entDataSize = allData.length();
            EntertainmentServiceProviderTableNew entertainmentServiceProviderTableNew = new EntertainmentServiceProviderTableNew(OpeningActivity.this);
            EntertainmetTypeTable entertainmetTypeTable = new EntertainmetTypeTable(OpeningActivity.this);
            entertainmetTypeTable.dropTable();
                entertainmentServiceProviderTableNew.dropTable();

            for (int i = 0; i < entDataSize; i++) {
                try {
                    JSONObject jsonObject = allData.getJSONObject(i);

                    EntertainmentServiceProviderItemNew entertainmentServiceProviderItemNew = EntertainmentServiceProviderItemNew.parseEntertainmentServiceProviderItem(jsonObject, i);
                    entertainmentServiceProviderTableNew.insertItem(entertainmentServiceProviderItemNew);

                    if (jsonObject.has("rspot_details")) {
                        JSONArray rspot_details = jsonObject.getJSONArray("rspot_details");
                        int rspot_detailsSize = rspot_details.length();

                        for (int v = 0; v < rspot_detailsSize; v++) {
                            JSONObject rspot_detailsSizeItem = rspot_details.getJSONObject(v);

                            EntertainmentTypeItem entertainmentTypeItem = EntertainmentTypeItem.parseEntertainmentTypeItem(jsonObject.getInt("id"), rspot_detailsSizeItem);
                            entertainmetTypeTable.insertItem(entertainmentTypeItem);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }


    }

    class SaveRatingTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveRatingTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonObjects) {
            JSONArray RatingArray = jsonObjects[0];
            RatingTable subCatTable = new RatingTable(OpeningActivity.this);
            subCatTable.dropTable();
            int subCatCount = RatingArray.length();
            for (int i = 0; i < subCatCount; i++) {
                try {
                    JSONObject jo = RatingArray.getJSONObject(i);
                    RatingModel si = RatingModel.parseRatingModel(jo);
                    subCatTable.insertItem(si);

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            si22 = subCatTable.getAllCategories();
            si22.size();
            return new Long(0);
        }
    }

    class SavenewFinanceTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewFinanceTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray financial = jsonArrays[0];
            FinancialServiceNewTable financialServiceNewTable = new FinancialServiceNewTable(OpeningActivity.this);

            FinancialServiceDetailsTable financialServiceDetailsTable = new FinancialServiceDetailsTable(OpeningActivity.this);

            financialServiceDetailsTable.dropTable();

            financialServiceNewTable.dropTable();


            int legalaidServiceProviderCount = financial.length();

            for (int i = 0; i < legalaidServiceProviderCount; i++) {
                try {
                    JSONObject jo = financial.getJSONObject(i);
                    FinancialNewItem et = FinancialNewItem.parseFinancialMapInfoItem(jo);
                    financialServiceNewTable.insertItem(et);


                    if (jo.has("fin_service_details"))// need id in fin_service_details
                    {
                        JSONArray service_details = jo.getJSONArray("fin_service_details");
                        for (int j = 0; j < service_details.length(); j++) {
                            JSONObject joes = service_details.getJSONObject(j);
                            FinancialServiceDetailsItem financialServiceDetailsItem = FinancialServiceDetailsItem.parseFinancialServiceDetailsItem(joes);
                            financialServiceDetailsTable.insertItem(financialServiceDetailsItem);

                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }



    //this class gets the entire sql string breaks it down and executes
    class SaveSQL extends GenericSaveDBTask<String, Integer, Long> {
        public SaveSQL(Context ctx) {
            super(ctx);
        }


        protected Long doInBackground(String... sqls) {
            //ge the db instance
            SQLiteDatabase db = DatabaseManager.getInstance(OpeningActivity.this).openDatabase();

            //split into single sql queries
            String[] sql = sqls[0].split(";");

            //block db
            //db.beginTransaction();

            //run the sqls one by one
            for (int i = 0; i<sql.length;i++)
            {
                db.execSQL(sql[i]);
            }

            //unblock db
            //db.endTransaction();

            //close db
            DatabaseManager.getInstance(OpeningActivity.this).closeDatabase();

            return new Long(0);
        }

    }

    class SavenewGovTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewGovTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Gov = jsonArrays[0];
            GovernmentNewTable governmentNewTable = new GovernmentNewTable(OpeningActivity.this);

            GovernmentServiceDetailsTable governmentServiceDetailsTable = new GovernmentServiceDetailsTable(OpeningActivity.this);

            governmentServiceDetailsTable.dropTable();

            governmentNewTable.dropTable();


            int Govcount = Gov.length();

            for (int i = 0; i < Govcount; i++) {
                try {
                    JSONObject jo = Gov.getJSONObject(i);
                    GovernmentNewItem et = GovernmentNewItem.parseGovernmentNewItem(jo);
                    governmentNewTable.insertItem(et);

                    if (jo.has("govservice_details"))// need id in fin_service_details
                    {
                        JSONArray service_details = jo.getJSONArray("govservice_details");
                        for (int j = 0; j < service_details.length(); j++) {
                            JSONObject joes = service_details.getJSONObject(j);
                            GovernmentServiceDetailsItem governmentServiceDetailsItem = GovernmentServiceDetailsItem.parseGovernmentServiceDetailsItem(joes);
                            governmentServiceDetailsTable.insertItem(governmentServiceDetailsItem);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }



    class SaveLegaltDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveLegaltDataTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray legal_array = jsonArrays[0];
            int p = legal_array.length();
            LegalAidServiceProviderTableNew legalAidServiceProviderTableNew = new LegalAidServiceProviderTableNew(OpeningActivity.this);
                legalAidServiceProviderTableNew.dropTable();

            for (int i = 0; i < p; i++) {
                try {
                    JSONObject jsonObject = legal_array.getJSONObject(i);
                    LegalAidServiceProviderItemNew legalAidServiceProviderItemNew = LegalAidServiceProviderItemNew.parseLegalAidServiceProviderItemNew(jsonObject);

                    legalAidServiceProviderTableNew.insertItem(legalAidServiceProviderItemNew);
                    if (jsonObject.has("lservice_details")) {
                        JSONArray lservice_details = jsonObject.getJSONArray("lservice_details");
                        int lservice_detailsSize = lservice_details.length();

                        for (int v = 0; v < lservice_detailsSize; v++) {
                            JSONObject lservice_detailsSizeItem = lservice_details.getJSONObject(v);
                            SaveLegalDetailsData(lservice_detailsSizeItem, jsonObject.getInt("id"));
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }

        private void SaveLegalDetailsData(JSONObject jsonObject, int foreign_key) {

            try {
                LeagalAidDetailsItem leagalAidDetailsItem = LeagalAidDetailsItem.parseLegalAidDetailsItem(jsonObject, foreign_key);
                legalAidDetailsTable.insertItem(leagalAidDetailsItem);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }




    class SaveHealthServiceProviderTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveHealthServiceProviderTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonObjects) {
            JSONArray healthServiceProvider = jsonObjects[0];
            HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(OpeningActivity.this);
            healthServiceProviderTable.dropTable();
            HealthVaccinesTable healthVaccinesTable = new HealthVaccinesTable(OpeningActivity.this);
            healthVaccinesTable.dropTable();
            HealthSpecialistTable healthSpecialistTable = new HealthSpecialistTable(OpeningActivity.this);
            healthSpecialistTable.dropTable();
            HealthPharmacyTable healthPharmacyTable = new HealthPharmacyTable(OpeningActivity.this);
            healthPharmacyTable.dropTable();
            int healthServiceProviderCount = healthServiceProvider.length();
            for (int i = 0; i < healthServiceProviderCount; i++) {
                try {
                    JSONObject jo = healthServiceProvider.getJSONObject(i);
                    HealthServiceProviderItem et = HealthServiceProviderItem.parseHealthServiceProviderItem(jo);
                    // healthServiceProviderTable.insertItemHealth(et);

                    if (jo.has("specialist")) {
                        JSONArray specialist = jo.getJSONArray("specialist");

                        for (int m = 0; m < specialist.length(); m++) {
                            JSONObject joes = specialist.getJSONObject(m);

                            HealthSpecialistItem ets = HealthSpecialistItem.parseHealthSpecialistItem(joes);
                            healthSpecialistTable.insertItemHealth(ets);

                        }

                    }
                    if (jo.has("vaccine")) {
                        JSONArray vaccine = jo.getJSONArray("vaccine");

                        for (int n = 0; n < vaccine.length(); n++) {
                            JSONObject joes = vaccine.getJSONObject(n);

                            HealthVaccinesItem etd = HealthVaccinesItem.parseHealthVaccinesItem(joes);
                            healthVaccinesTable.insertItemHealth(etd);

                        }

                    }
                    if (jo.has("pharmacy")) {
                        JSONArray pharmacy = jo.getJSONArray("pharmacy");

                        for (int k = 0; k < pharmacy.length(); k++) {
                            JSONObject joes = pharmacy.getJSONObject(k);
                            HealthPharmacyItem etl = HealthPharmacyItem.parseHealthPharmacyItem(joes);
                            healthPharmacyTable.insertItemHealthPharmacy(etl);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return new Long(0);
        }


        private void savesubcat(JSONArray subcat) {
            SubCategoryTableNew subCategoryTableNew = new SubCategoryTableNew(OpeningActivity.this);


            subCategoryTableNew.dropTable();

            int legalaidServiceProviderCount = subcat.length();

            for (int i = 0; i < legalaidServiceProviderCount; i++) {
                try {
                    JSONObject jo = subcat.getJSONObject(i);
                    SubCategoryItemNew et = SubCategoryItemNew.parseSubCategoryItem(jo);
                    subCategoryTableNew.insertItem(et);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }

    }







    class SavenewEduTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewEduTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonObjects) {
            JSONArray edu = jsonObjects[0];
            EducationNewTable educationNewTable = new EducationNewTable(OpeningActivity.this);
            EducationResultDetailsTable educationResultDetailsTable = new EducationResultDetailsTable(OpeningActivity.this);
            EducationTrainingDetailsTable educationTrainingDetailsTable = new EducationTrainingDetailsTable(OpeningActivity.this);
            EducationTuitionDetailsTable educationTuitionDetailsTable = new EducationTuitionDetailsTable(OpeningActivity.this);
            educationNewTable.dropTable();
            educationResultDetailsTable.dropTable();
            educationTrainingDetailsTable.dropTable();
            educationTuitionDetailsTable.dropTable();


            int eduServiceProviderCount = edu.length();

            for (int i = 0; i < eduServiceProviderCount; i++) {
                try {
                    JSONObject jo = edu.getJSONObject(i);
                    EducationNewItem et = EducationNewItem.parseEducationNewItem(jo);
                    educationNewTable.insertItem(et);


                    if (jo.has("tution_details"))//
                    {
                        JSONArray service_details = jo.getJSONArray("tution_details");
                        for (int j = 0; j < service_details.length(); j++) {
                            JSONObject joes = service_details.getJSONObject(j);
                            EducationTuitionDetailsItem educationTuitionDetailsItem = EducationTuitionDetailsItem.parseEducationTuitionDetailsItem(joes);
                            educationTuitionDetailsTable.insertItem(educationTuitionDetailsItem);

                        }

                        countofDb++;

                    }


                    if (jo.has("result_details"))//
                    {
                        JSONArray service_details = jo.getJSONArray("result_details");
                        for (int j = 0; j < service_details.length(); j++) {
                            JSONObject joes = service_details.getJSONObject(j);
                            EducationResultItemNew educationResultItemNew = EducationResultItemNew.parseEducationResultItemNew(joes);
                            educationResultDetailsTable.insertItem(educationResultItemNew);

                        }

                    }
                    if (jo.has("training_details"))//
                    {
                        JSONArray service_details = jo.getJSONArray("training_details");
                        for (int j = 0; j < service_details.length(); j++) {
                            JSONObject joes = service_details.getJSONObject(j);
                            EducationTrainingDetailsItem educationTrainingDetailsItem = EducationTrainingDetailsItem.parseEducationTrainingDetailsItem(joes);
                            educationTrainingDetailsTable.insertItem(educationTrainingDetailsItem);
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

}