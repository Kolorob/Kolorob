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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.DatabaseHelper;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;


public class SplashActivityNew extends ActionBarActivity {

    Context context;

    View view=null;

    //user and pass
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";



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

    //    TextView text = (TextView)findViewById(R.id.falsetice_id);

     //   rotateImage = (ImageView) findViewById(R.id.rotate_image);
     //   rotateImage.setBackgroundResource(R.drawable.glow);
        Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.android_rotate_animation);
    //    rotateImage.startAnimation(startRotateAnimation);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                                /* start the activity */
                startActivity(new Intent(SplashActivityNew.this, OpeningActivity.class));
                //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                //overridePendingTransition(0, 0);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }
        }, 3000);


      //  setsongName();

    }

}
