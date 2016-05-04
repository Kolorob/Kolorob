package demo.kolorob.kolorobdemoversion.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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


    public RefreshHandler mRedrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //SplashActivity.this.setsongName();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

    private void setsongName() {
        try {
            in++;

//            if(in == 5) {
//                rotateImage.setBackgroundResource(R.drawable.glow);
//                Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.glow_animation);
//                rotateImage.startAnimation(startRotateAnimation);
//            }
            // Main.songtitle.setText(Main.songname);
        } catch (final Exception e) {


        }

        if(in ==1){
            rotateImage.setBackgroundResource(R.drawable.glow);
            System.out.println("-----okkkkk1--------");
           // startActivity(new Intent(SplashActivity.this, OpeningActivity.class));
        }
        else if(in ==2){
            rotateImage.setBackgroundResource(R.drawable.kolorob_logo_first_page);
            System.out.println("-----okkkkk2--------");

        }
        else if(in ==3){
            rotateImage.setBackgroundResource(R.drawable.glow);
            System.out.println("-----okkkkk2--------");

        }
        else if(in ==4){
            rotateImage.setBackgroundResource(R.drawable.kolorob_logo_first_page);
            System.out.println("-----okkkkk2--------");

        }
        else if(in ==5){
            rotateImage.setBackgroundResource(R.drawable.glow);
            System.out.println("-----okkkkk2--------");

        }

        else if(in == 7){
            rotateImage.setBackgroundResource(R.drawable.glow);
            startActivity(new Intent(SplashActivity.this, PlaceChoiceActivity.class));
            mRedrawHandler.removeMessages(0);
            finish();
            System.out.println("-----okkkkk74--------" );
           // in=0;
        }

        mRedrawHandler.sleep(1000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

       // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        context = this;
        //getActionBar().setBackgroundDrawable((getResources().getDrawable(R.drawable.actionbar)));

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
                startActivity(new Intent(SplashActivity.this, PlaceChoiceActivity.class));
                //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                overridePendingTransition(0, 0);
                finish();
            }
        }, 3000);


      //  setsongName();

    }

}
