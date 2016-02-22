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
 * @author mity
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
        setContentView(R.layout.activity_opening);

        ImageView kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);//need to add bengali

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        width=displayMetrics.widthPixels;
        height=displayMetrics.heightPixels;


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


            getRequest(OpeningActivity.this, "healthSpecialist", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {

                        try {
                            JSONObject jo = new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveHealthSpecialist(jo.getJSONArray(AppConstants.KEY_DATA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


            getRequest(OpeningActivity.this, "healthPharmacy", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        savePharmacyServiceProvider(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );




            getRequest(OpeningActivity.this, "healthVaccines", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {

                        try {
                            JSONObject jo = new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveHealthVaccines(jo.getJSONArray(AppConstants.KEY_DATA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


            getRequest(OpeningActivity.this, "get_edu_service_provider", new VolleyApiCallback() {
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
            getRequest(OpeningActivity.this, "get_edu_course", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveEducationCourse(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "get_edu_fees", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveEducationFee(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );

            getRequest(OpeningActivity.this, "entertainment", new VolleyApiCallback() {
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
            getRequest(OpeningActivity.this, "entertainment/bookshop", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {

                        try {
                            JSONObject jo = new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveEntertainmentBookshop(jo.getJSONArray(AppConstants.KEY_DATA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            getRequest(OpeningActivity.this, "entertainment/theatre", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {

                        try {
                            JSONObject jo = new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveEntertainmentTheatre(jo.getJSONArray(AppConstants.KEY_DATA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            getRequest(OpeningActivity.this, "entertainment/fitness_beauty", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {

                        try {
                            JSONObject jo = new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveEntertainmentFitness(jo.getJSONArray(AppConstants.KEY_DATA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            getRequest(OpeningActivity.this, "entertainment/field", new VolleyApiCallback() {
                @Override
                public void onResponse(int status, String apiContent) {
                    if (status == AppConstants.SUCCESS_CODE) {

                        try {
                            JSONObject jo = new JSONObject(apiContent);
                            String apiSt = jo.getString(AppConstants.KEY_STATUS);
                            if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                saveEntertainmentField(jo.getJSONArray(AppConstants.KEY_DATA));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


            getRequest(OpeningActivity.this, "health", new VolleyApiCallback() {
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
            getRequest(OpeningActivity.this, "legal_aid", new VolleyApiCallback() {
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
            getRequest(OpeningActivity.this, "salishi", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveLegalaidSalishi(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "advice", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveLegalaidAdvice(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );

            getRequest(OpeningActivity.this, "job", new VolleyApiCallback() {
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
            getRequest(OpeningActivity.this, "finance", new VolleyApiCallback() {
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
            getRequest(OpeningActivity.this, "tuition", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialTuition(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "bills", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialBills(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "insurance", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialInsurance(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "loan", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialLoan(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "payment", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialPayment(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "social", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialSocial(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "tax", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialTax(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "jobtype", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveJobType(jo.getJSONArray(AppConstants.KEY_DATA));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(OpeningActivity.this, "transaction", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                try {
                                    JSONObject jo = new JSONObject(apiContent);
                                    String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                    if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                        saveFinancialTransaction(jo.getJSONArray(AppConstants.KEY_DATA));
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


    /**
     * Written by : israt.jahan
     */
    private void saveHealthSpecialist(JSONArray healthSpecialist) {
        HealthSpecialistTable healthSpecialistTable  = new  HealthSpecialistTable (OpeningActivity.this);
        healthSpecialistTable.dropTable();
        int healthSpecialistCount = healthSpecialist.length();

        for (int i = 0; i < healthSpecialistCount; i++) {
            try {
                JSONObject jo = healthSpecialist.getJSONObject(i);
                HealthSpecialistItem et =  HealthSpecialistItem.parseHealthSpecialistItem(jo);
                healthSpecialistTable.insertItemHealth(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveJobType(JSONArray jobType) {
        JobTypeServiceProviderTable jobTypeServiceProviderTable  = new  JobTypeServiceProviderTable (OpeningActivity.this);
        jobTypeServiceProviderTable.dropTable();
        int jobTypeCount = jobType.length();

        for (int i = 0; i < jobTypeCount; i++) {
            try {
                JSONObject jo = jobType.getJSONObject(i);
                JobTypeServiceProviderItem et =  JobTypeServiceProviderItem.parseJobTypeServiceProvider(jo);
                jobTypeServiceProviderTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }



    private void saveEducationServiceProvider(JSONArray educationServiceProvider) {
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(OpeningActivity.this);
        educationServiceProviderTable.dropTable();
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
    private void saveEducationCourse(JSONArray educationCourse) {
        EducationCourseTable educationCourseTable = new EducationCourseTable(OpeningActivity.this);
        educationCourseTable.dropTable();
        int educationCourseCount = educationCourse.length();


        for (int i = 0; i <educationCourseCount; i++) {
            try {

                JSONObject jo = educationCourse.getJSONObject(i);
                EducationCourseItem et = EducationCourseItem.parseEducationCourseItem(jo);
                educationCourseTable.insertItem(et);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveEducationFee(JSONArray educationFee) {
        EducationFeeTable educationFeeTable = new EducationFeeTable(OpeningActivity.this);
        educationFeeTable.dropTable();
        int educationFeeCount = educationFee.length();


        for (int i = 0; i <educationFeeCount; i++) {
            try {

                JSONObject jo = educationFee.getJSONObject(i);
                EducationFeeItem et = EducationFeeItem.parseEducationFeeItem(jo);
                educationFeeTable.insertItem(et);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void saveHealthVaccines(JSONArray healthVaccines) {
        HealthVaccinesTable healthVaccinesTable = new  HealthVaccinesTable(OpeningActivity.this);
        healthVaccinesTable.dropTable();
        int healthVaccinesCount = healthVaccines.length();

        for (int i = 0; i < healthVaccinesCount; i++) {
            try {
                JSONObject jo = healthVaccines.getJSONObject(i);
                HealthVaccinesItem et =  HealthVaccinesItem.parseHealthVaccinesItem(jo);
                healthVaccinesTable.insertItemHealth(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }



    private void savePharmacyServiceProvider(JSONArray pharmacyServiceProvider) {
        HealthPharmacyTable healthPharmacyTable = new HealthPharmacyTable(OpeningActivity.this);
        healthPharmacyTable.dropTable();
        int pharmacyserviceprovidercount = pharmacyServiceProvider.length();

        for (int i = 0; i < pharmacyserviceprovidercount; i++) {
            try {

                JSONObject jo = pharmacyServiceProvider.getJSONObject(i);
                HealthPharmacyItem et = HealthPharmacyItem.parseHealthPharmacyItem(jo);
                healthPharmacyTable.insertItemHealthPharmacy(et);

                //     Toast.makeText(this, "Internet permission granted", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }



    private void saveHealthServiceProvider(JSONArray healthServiceProvider) {
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(OpeningActivity.this);
        healthServiceProviderTable.dropTable();
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





    private void saveEntertainmentServiceProvider(JSONArray entertainmentServiceProvider) {
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(OpeningActivity.this);
        entertainmentServiceProviderTable.dropTable();
        int entServiceProviderCount = entertainmentServiceProvider.length();

        for (int i = 0; i < entServiceProviderCount; i++) {
            try {
                JSONObject jo = entertainmentServiceProvider.getJSONObject(i);
                EntertainmentServiceProviderItem et = EntertainmentServiceProviderItem.parseEntertainmentServiceProviderItem(jo);
                entertainmentServiceProviderTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
    private void saveEntertainmentBookshop(JSONArray entertainmentBookshop) {
        EntertainmentBookTable entertainmentBookTable = new EntertainmentBookTable(OpeningActivity.this);
        entertainmentBookTable.dropTable();
        int entertainmentBookshopCount = entertainmentBookshop.length();

        for (int i = 0; i < entertainmentBookshopCount; i++) {
            try {
                JSONObject jo = entertainmentBookshop.getJSONObject(i);
               EntertainmentBookShopItem et = EntertainmentBookShopItem.parseEntertainmentBookShopItem(jo);
                entertainmentBookTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
    private void saveEntertainmentField(JSONArray entertainmentField) {
        EntertainmentFieldTable entertainmentFieldTable = new EntertainmentFieldTable(OpeningActivity.this);
        entertainmentFieldTable.dropTable();
        int entertainmentFieldCount = entertainmentField.length();

        for (int i = 0; i < entertainmentFieldCount; i++) {
            try {
                JSONObject jo = entertainmentField.getJSONObject(i);
                EntertainmentFieldItem et = EntertainmentFieldItem.parseEntertainmentFieldItem(jo);
                entertainmentFieldTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
    private void saveEntertainmentTheatre(JSONArray entertainmentTheatre) {
        EntertainmentTheatreTable entertainmentTheatreTable = new EntertainmentTheatreTable(OpeningActivity.this);
        entertainmentTheatreTable.dropTable();
        int entertainmentTheatreCount = entertainmentTheatre.length();

        for (int i = 0; i < entertainmentTheatreCount; i++) {
            try {
                JSONObject jo = entertainmentTheatre.getJSONObject(i);
                EntertainmentTheatreItem et = EntertainmentTheatreItem.parseEntertainmentTheatreItem(jo);
                entertainmentTheatreTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
    private void saveEntertainmentFitness(JSONArray entertainmentFitness) {
        EntertainmentFitnessTable entertainmentFitnessTable = new EntertainmentFitnessTable(OpeningActivity.this);
        entertainmentFitnessTable.dropTable();
        int entertainmentFitnessCount = entertainmentFitness.length();

        for (int i = 0; i < entertainmentFitnessCount; i++) {
            try {
                JSONObject jo = entertainmentFitness.getJSONObject(i);
                EntertainmentFitnessItem et = EntertainmentFitnessItem.parseEntertainmentFitnessItem(jo);
                entertainmentFitnessTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }



    private void saveLegalaidServiceProvider(JSONArray legalaidServiceProvider) {
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(OpeningActivity.this);
        legalAidServiceProviderTable.dropTable();
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
    private void saveLegalaidAdvice(JSONArray legalaidAdvice) {
       LegalAidtypeServiceProviderLegalAdviceTable legalAidtypeServiceProviderLegalAdviceTable = new LegalAidtypeServiceProviderLegalAdviceTable(OpeningActivity.this);
       legalAidtypeServiceProviderLegalAdviceTable.dropTable();
        int legalaidAdviceCount = legalaidAdvice.length();

        for (int i = 0; i < legalaidAdviceCount; i++) {
            try {
                JSONObject jo = legalaidAdvice.getJSONObject(i);
                LegalAidLegalAdviceItem et = LegalAidLegalAdviceItem.parseLegalAidLegalAdviceItem(jo);
                legalAidtypeServiceProviderLegalAdviceTable.insertItem(et);
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
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(OpeningActivity.this);
        financialServiceProviderTable.dropTable();
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
    }
    private void saveFinancialBills(JSONArray financialBills) {
        FinancialBillsTable financialBillsTable = new FinancialBillsTable(OpeningActivity.this);
        financialBillsTable.dropTable();
        int financialBillsCount = financialBills.length();

        for (int i = 0; i < financialBillsCount; i++) {
            try {
                JSONObject jo = financialBills.getJSONObject(i);
                FinancialBillsItem et = FinancialBillsItem.parseFinancialBillsItem(jo);
                financialBillsTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveFinancialInsurance(JSONArray financialInsurance) {
        FinancialInsuranceTable financialInsuranceTable = new FinancialInsuranceTable(OpeningActivity.this);
        financialInsuranceTable.dropTable();
        int financialInsuranceCount = financialInsurance.length();

        for (int i = 0; i < financialInsuranceCount; i++) {
            try {
                JSONObject jo = financialInsurance.getJSONObject(i);
                FinancialInsuranceItem et = FinancialInsuranceItem.parseFinancialInsuranceItem(jo);
                financialInsuranceTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveFinancialTax(JSONArray financialTax) {
        FinancialTaxTable financialTaxTable = new FinancialTaxTable(OpeningActivity.this);
        financialTaxTable.dropTable();
        int financialTaxCount = financialTax.length();

        for (int i = 0; i < financialTaxCount; i++) {
            try {
                JSONObject jo = financialTax.getJSONObject(i);
                FinancialTaxItem et =  FinancialTaxItem.parseFinancialTaxItem(jo);
                financialTaxTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveFinancialTransaction(JSONArray financialTransaction) {
        FinancialTransactionTable financialTransactionTable = new FinancialTransactionTable(OpeningActivity.this);
        financialTransactionTable.dropTable();
        int financialTransactionCount = financialTransaction.length();

        for (int i = 0; i < financialTransactionCount; i++) {
            try {
                JSONObject jo = financialTransaction.getJSONObject(i);
                FinancialTransactionItem et = FinancialTransactionItem.parseFinancialTransactionItem(jo);
                financialTransactionTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveFinancialTuition(JSONArray financialTuition) {
        FinancialTuitionTable financialTuitionTable = new FinancialTuitionTable(OpeningActivity.this);
        financialTuitionTable.dropTable();
        int financialTuitionCount = financialTuition.length();

        for (int i = 0; i < financialTuitionCount; i++) {
            try {
                JSONObject jo = financialTuition.getJSONObject(i);
                FinancialTuitionItem et = FinancialTuitionItem.parseFinancialTuitionItem(jo);
                financialTuitionTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveFinancialSocial(JSONArray financialSocial) {
        FinancialSocialTable financialSocialTable = new FinancialSocialTable(OpeningActivity.this);
        financialSocialTable.dropTable();
        int financialSocialCount = financialSocial.length();

        for (int i = 0; i < financialSocialCount; i++) {
            try {
                JSONObject jo = financialSocial.getJSONObject(i);
                FinancialSocialItem et = FinancialSocialItem.parseFinancialSocialItem(jo);
                financialSocialTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveFinancialLoan(JSONArray financialLoan) {
        FinancialLoanTable financialLoanTable = new FinancialLoanTable(OpeningActivity.this);
        financialLoanTable.dropTable();
        int financialLoanCount = financialLoan.length();

        for (int i = 0; i < financialLoanCount; i++) {
            try {
                JSONObject jo = financialLoan.getJSONObject(i);
                FinancialLoanItem et = FinancialLoanItem.parseFinancialLoanItem(jo);
                financialLoanTable.insertItem(et);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveFinancialPayment(JSONArray financialPayment) {
        FinancialPaymentTable financialPaymentTable = new FinancialPaymentTable(OpeningActivity.this);
        financialPaymentTable.dropTable();
        int financialPaymentCount = financialPayment.length();

        for (int i = 0; i < financialPaymentCount; i++) {
            try {
                JSONObject jo = financialPayment.getJSONObject(i);
                FinancialPaymentItem et = FinancialPaymentItem.parseFinancialPaymentItem(jo);
                financialPaymentTable.insertItem(et);
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
        //setContentView(R.layout.activity_main); //we don't need this line

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
