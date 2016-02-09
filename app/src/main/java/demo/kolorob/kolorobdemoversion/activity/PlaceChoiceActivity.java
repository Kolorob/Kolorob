package demo.kolorob.kolorobdemoversion.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class PlaceChoiceActivity extends BaseActivity implements View.OnClickListener {
    private static final int DELAY_PLACE_DETAILS_LAUNCH_ANIM = 300;
    private AnimationDrawable frAnimBaunia;
    private AnimationDrawable frAnimParisRoad;
    ImageButton Feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice);
        Feedback = (ImageButton) findViewById(R.id.button2);
        Feedback.setOnClickListener(this);

        ImageView kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);
        ImageView ivBauniaBandh = (ImageView) findViewById(R.id.iv_baunia);
        ImageView ivParisRoad = (ImageView) findViewById(R.id.iv_parise);

        ivBauniaBandh.setOnClickListener(this);
        ivParisRoad.setOnClickListener(this);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        LinearLayout boy = (LinearLayout) findViewById(R.id.boy);
        LinearLayout girl = (LinearLayout) findViewById(R.id.girl);
        LinearLayout shadowBoy = (LinearLayout) findViewById(R.id.boy_shadow);
        LinearLayout shadowGirl = (LinearLayout) findViewById(R.id.girl_shadow);

        RelativeLayout.LayoutParams kolorob_logo = new RelativeLayout.LayoutParams(width / 3, height / 6);
        RelativeLayout.LayoutParams baunia_img = new RelativeLayout.LayoutParams((int) (2 * width / 3.2), (int) (height / 2.5));
        RelativeLayout.LayoutParams parise_img = new RelativeLayout.LayoutParams((int) (2 * width / 3.2), (int) (height / 2.5));
        RelativeLayout.LayoutParams boy_layout = new RelativeLayout.LayoutParams(width / 3, height / 2);
        RelativeLayout.LayoutParams girl_layout = new RelativeLayout.LayoutParams(width / 3, height / 2 - height / 15);
        RelativeLayout.LayoutParams boy_shadow = new RelativeLayout.LayoutParams(width / 3, height / 15);
        RelativeLayout.LayoutParams girl_shadow = new RelativeLayout.LayoutParams(width / 3, height / 15);

        kolorob_logo.addRule(RelativeLayout.CENTER_HORIZONTAL);
        kolorob_logo.addRule(RelativeLayout.CENTER_VERTICAL);
        parise_img.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        parise_img.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        girl_shadow.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_shadow.addRule(RelativeLayout.CENTER_VERTICAL);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        boy_shadow.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        boy_shadow.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        int m = height / 2 - height / 15;
        girl_layout.setMargins(0, 0, 0, m);

        boy.setLayoutParams(boy_layout);
        girl.setLayoutParams(girl_layout);
        boy.bringToFront();
        girl.bringToFront();
        shadowBoy.setLayoutParams(boy_shadow);
        shadowGirl.setLayoutParams(girl_shadow);
        kolorobLogo.setLayoutParams(kolorob_logo);
        ivBauniaBandh.setLayoutParams(baunia_img);
        ivParisRoad.setLayoutParams(parise_img);


        ivBauniaBandh.setBackgroundResource(R.drawable.frame_animation_list);
        ivParisRoad.setBackgroundResource(R.drawable.frame_animation_list1);
        frAnimBaunia = (AnimationDrawable) ivBauniaBandh.getBackground();
        frAnimParisRoad = (AnimationDrawable) ivParisRoad.getBackground();
        frAnimBaunia.setOneShot(true);
        frAnimParisRoad.setOneShot(true);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.iv_baunia:
                frAnimBaunia.start();
                gotoPlaceDetailsView(AppConstants.PLACE_BAUNIABADH);
                break;

            case R.id.iv_parise:
                frAnimParisRoad.start();
                gotoPlaceDetailsView(AppConstants.PLACE_PARIS_ROAD);
                break;
            case R.id.button2:
                Intent a = new Intent(PlaceChoiceActivity.this, FeedbackActivity.class);
                startActivity(a);
                finish();
                break;

            default:
                break;

        }

    }

    private void gotoPlaceDetailsView(final int placeId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PlaceChoiceActivity.this, PlaceDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(AppConstants.KEY_PLACE, placeId);
                startActivity(intent);
            }
        }, DELAY_PLACE_DETAILS_LAUNCH_ANIM);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Close")
                .setMessage("Are you sure you want to close Kolorob")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
