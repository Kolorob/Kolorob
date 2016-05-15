package demo.kolorob.kolorobdemoversion.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class PlaceChoiceActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener  {


    private static final int DELAY_PLACE_DETAILS_LAUNCH_ANIM = 300;
    private AnimationDrawable frAnimBaunia;
    private AnimationDrawable frAnimParisRoad;
    public int height,width;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_place_choice);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        setWidth(displayMetrics.widthPixels);
        setHeight(displayMetrics.heightPixels);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        ImageView kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);
        ImageView ivBauniaBandh = (ImageView) findViewById(R.id.iv_baunia);
        ImageView ivParisRoad = (ImageView) findViewById(R.id.iv_parise);

        ivBauniaBandh.setOnClickListener(this);
        ivParisRoad.setOnClickListener(this);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        LinearLayout boy = (LinearLayout) findViewById(R.id.boy);
        LinearLayout girl = (LinearLayout) findViewById(R.id.girl);

        RelativeLayout.LayoutParams kolorob_logo = new RelativeLayout.LayoutParams(width / 3, height / 6);
        RelativeLayout.LayoutParams baunia_img = new RelativeLayout.LayoutParams((int) (2 * width / 3.2), (int) (height / 2.5));
        RelativeLayout.LayoutParams parise_img = new RelativeLayout.LayoutParams((int) (2 * width / 3.2), (int) (height / 2.5));
        RelativeLayout.LayoutParams boy_layout = new RelativeLayout.LayoutParams(width / 3, height / 2);
        RelativeLayout.LayoutParams girl_layout = new RelativeLayout.LayoutParams(width / 3, height / 2 - height / 15);

        kolorob_logo.addRule(RelativeLayout.CENTER_HORIZONTAL);
        kolorob_logo.addRule(RelativeLayout.CENTER_VERTICAL);
        parise_img.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        parise_img.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        int m = height / 2 - height / 15;
        girl_layout.setMargins(0, 0, 0, m);

        boy.setLayoutParams(boy_layout);
        girl.setLayoutParams(girl_layout);
        boy.bringToFront();
        girl.bringToFront();

        kolorobLogo.setLayoutParams(kolorob_logo);
        ivBauniaBandh.setLayoutParams(baunia_img);
        ivParisRoad.setLayoutParams(parise_img);


        ivBauniaBandh.setBackgroundResource(R.drawable.frame_animation_list);
        ivParisRoad.setBackgroundResource(R.drawable.frame_animation_list1);
        frAnimBaunia = (AnimationDrawable) ivBauniaBandh.getBackground();
        frAnimParisRoad = (AnimationDrawable) ivParisRoad.getBackground();
        frAnimBaunia.setOneShot(false);
        frAnimParisRoad.setOneShot(false);
        frAnimBaunia.setOneShot(true);
        frAnimParisRoad.setOneShot(true);



    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_emergency) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
