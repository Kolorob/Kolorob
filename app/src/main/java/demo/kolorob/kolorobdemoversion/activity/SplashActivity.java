package demo.kolorob.kolorobdemoversion.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Spinner;

import demo.kolorob.kolorobdemoversion.R;


public class SplashActivity extends ActionBarActivity {

    Context context;
    Spinner spinner2,spinner3,spinner4;
    ImageView rotateImage;
    private Handler handler;
    int in = 0;
    View view=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);
        context = this;

    //    TextView text = (TextView)findViewById(R.id.falsetice_id);

        rotateImage = (ImageView) findViewById(R.id.rotate_image);
        rotateImage.setBackgroundResource(R.drawable.glow);
        Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.android_rotate_animation);
        rotateImage.startAnimation(startRotateAnimation);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                                /* start the activity */
                startActivity(new Intent(SplashActivity.this, OpeningActivity.class));
                //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                //overridePendingTransition(0, 0);
//                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }
        }, 3000);


      //  setsongName();

    }

}
