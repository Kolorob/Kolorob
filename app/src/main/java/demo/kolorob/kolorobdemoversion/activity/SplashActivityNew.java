package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;

import demo.kolorob.kolorobdemoversion.BuildConfig;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;


public class SplashActivityNew extends ActionBarActivity {

    Context context;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 175;
    View view=null;
    String IMEINumber=null;
    //user and pass
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    Boolean location=false,storage=false,smsstate=false,phonestate=false,accountstate=false,permission=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //start download now

        //now make the early request just in case
        if ((AppUtils.isNetConnected(getApplicationContext()) )&&(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED )) {
            getRequest(SplashActivityNew.this, "http://kolorob.net/demo/api/getsql?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {
                                DatabaseHelper.sql = apiContent;
                            }
                        }
                    }
            );
        }





        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashnew);

        context = this;



            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (BuildConfig.DEBUG) {
                        System.gc();
                    }
                                /* start the activity */
                    startActivity(new Intent(SplashActivityNew.this, DataLoadingActivity.class));
                    //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    //overridePendingTransition(0, 0);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    finish();
                }
            }, 3000);


            //  setsongName();
        }
    }




