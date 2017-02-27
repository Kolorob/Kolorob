package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import demo.kolorob.kolorobdemoversion.BuildConfig;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;


public class SplashActivityNew extends ActionBarActivity {

    Context context;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 175;
    View view=null;
    String IMEINumber=null;
    //user and pass
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    RelativeLayout dataload;
    Boolean location=false,storage=false,smsstate=false,phonestate=false,accountstate=false,permission=false;
    String app_ver="";
    Boolean drop=false;
    long install=0;
    long install2=0;
    File filesDir;
    public final static int PERM_REQUEST_CODE_DRAW_OVERLAYS = 1234;
    Boolean  firstRun,firstRunup;
    public int height,width;
    Boolean registered=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //start download now
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashnew);

        context = this;
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        width=displayMetrics.widthPixels;
        height=displayMetrics.heightPixels;
        dataload=(RelativeLayout)findViewById(R.id.splash);
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Snackbar snackbar = Snackbar.make(dataload,"Please allow all the permission so that app works smoothly",Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        String state = Environment.getExternalStorageState();
        registered=settings.getBoolean("IFREGISTERED",false);

// Make sure it's available
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            filesDir = getExternalFilesDir(null);
        } else {
            // Load another directory, probably local memory
            filesDir = getFilesDir();
        }
        try {
            app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
            SharedPreferencesHelper.setVersion(SplashActivityNew.this,app_ver);
        } catch (PackageManager.NameNotFoundException e) {
            // Log.e(tag, e.getMessage());


        }
        try /*
        to fix issue with backward date device time in some version*/
        {
            app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;

            Float currentVersion= Float.parseFloat(app_ver);
            Float previousVersion=Float.parseFloat(SharedPreferencesHelper.getVersion(SplashActivityNew.this));
            //Float previousVersion=Float.parseFloat("2.03");
            if(currentVersion >previousVersion)
            {
                SharedPreferences.Editor editor = settings.edit();

                if(previousVersion.floatValue() < Float.parseFloat("2.03")) {
                    long check=settings.getLong("timefirstinstall",Long.valueOf(2));
                    if(check!=2) {
                        settings.edit().putString("timesfirstinstall", String.valueOf(check)).apply();
                    }
                    else
                    {
                        install2=System.currentTimeMillis();
                        settings.edit().putString("timesfirstinstall",  String.valueOf(install)).apply();
                    }
                }
                else if(previousVersion.floatValue()==Float.parseFloat("2.03"))
                {
                    String check2=settings.getString("timefirstinstall","2");
                    if(!check2.equals("2")) {
                        settings.edit().putString("timesfirstinstall", check2).apply();
                    }
                }

            }
        }
        catch (Exception e)
        {

        }
        firstRun = settings.getBoolean("firstRunUp", false);

        if (firstRun==false)//if running for first time
        {
            SharedPreferences.Editor editor = settings.edit();



            if(!AppUtils.isNetConnected(getApplicationContext())) {


                LayoutInflater layoutInflater = LayoutInflater.from(this);
                View promptView = layoutInflater.inflate(R.layout.default_alert, null);


                final Dialog alertDialog = new Dialog(this);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(promptView);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();


                final TextView header = (TextView) promptView.findViewById(R.id.headers);
                final TextView bodys = (TextView) promptView.findViewById(R.id.body);
                final ImageView okay=(ImageView)promptView.findViewById(R.id.okay);

                header.setText("ইন্টারনেট সংযোগ সচল নয়");
                header.setTextColor(getResources().getColor(R.color.Black));
                bodys.setText(" কলরব প্রথমবারের মত শুরু হতে যাচ্ছে। অনুগ্রহ পূর্বক ইন্টারনেট সংযোগটি চালু করুন ।  ");
                bodys.setTextColor(getResources().getColor(R.color.Black));
                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        SharedPreferences settings = getSharedPreferences("prefs", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("firstRunUp", false);
                        editor.apply();
                        finish();
                    }
                });

                alertDialog.setCancelable(false);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
                WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
                lp.dimAmount=0.0f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
                alertDialog.getWindow().setAttributes(lp);
//		else
//			textAsk.setTextSize(17);
                alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            else

            {   install=System.currentTimeMillis();
                settings.edit().putLong("time", System.currentTimeMillis()).apply();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (BuildConfig.DEBUG) {
                            System.gc();
                        }
                                /* start the activity */
                        if(registered) {
                            //actually dataloadingactivity hobe
                            startActivity(new Intent(SplashActivityNew.this, DataLoadingActivity.class));
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                        }
                        else {
                            startActivity(new Intent(SplashActivityNew.this, PhoneRegActivity.class));
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            finish();
                        }
                    }
                }, 2000);

            }
        } else
        {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (BuildConfig.DEBUG) {
                        System.gc();
                    }
                                /* start the activity */
if(registered==true) {
    startActivity(new Intent(SplashActivityNew.this, PlaceDetailsActivityNewLayout.class));
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    finish();
}
                    else {
    startActivity(new Intent(SplashActivityNew.this, DataLoadingActivity.class));
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    finish();
                    }
                    //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    //overridePendingTransition(0, 0);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    finish();
                }
            }, 3000);



                }


        }
    public void permissionToDrawOverlays() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {   //Android M Or Over
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, PERM_REQUEST_CODE_DRAW_OVERLAYS);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PERM_REQUEST_CODE_DRAW_OVERLAYS) {
            if (android.os.Build.VERSION.SDK_INT >= 23) {   //Android M Or Over
                if (!Settings.canDrawOverlays(this)) {
                    // ADD UI FOR USER TO KNOW THAT UI for SYSTEM_ALERT_WINDOW permission was not granted earlier...
                }
            }
        }
    }
    private void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        String message=null;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.GET_ACCOUNTS);
            message += "\n access to read sms.";
            //requestReadPhoneStatePermission();
        }
        if (!permissions.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            String[] params = permissions.toArray(new String[permissions.size()]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(params, REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        } // else: We already have permissions, so handle as normal
    }



            //  setsongName();
        }





