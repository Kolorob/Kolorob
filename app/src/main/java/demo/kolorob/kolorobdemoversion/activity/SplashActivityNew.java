package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.splashlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Snackbar snackbar = Snackbar.make(layout,"Please allow all the permission so that app works",Snackbar.LENGTH_LONG);
            snackbar.show();


            checkPermissions();
            if(permission)

            {
                startActivity(new Intent(SplashActivityNew.this, OpeningActivity.class));
                //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                //overridePendingTransition(0, 0);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }
            else {
                finish();
            }


        }

        else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (BuildConfig.DEBUG) {
                        System.gc();
                    }
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

    private void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        String message = "osmdroid permissions:";
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            message += "\nLocation to show user location.";
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            message += "\nStorage access to store map tiles.";
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
            message += "\n access to read phone state.";
            //requestReadPhoneStatePermission();
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.RECEIVE_SMS);
            message += "\n access to receive sms.";
            //requestReadPhoneStatePermission();
        }
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
    public void doPermissionGrantedStuffs() {
        //Have an  object of TelephonyManager
        TelephonyManager tm =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
        IMEINumber=tm.getDeviceId();
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("IMEI", IMEINumber);
        editor.apply();


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initial
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.RECEIVE_SMS, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.GET_ACCOUNTS, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION and WRITE_EXTERNAL_STORAGE
                 location = perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
               storage = perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                phonestate = perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
               smsstate = perms.get(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
                accountstate = perms.get(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED;
                if (location && storage&& phonestate &&smsstate&&accountstate) {
                    // All Permissions Granted
                    permission=true;
                    doPermissionGrantedStuffs();
                    Toast.makeText(SplashActivityNew.this, "Thanks for permission", Toast.LENGTH_SHORT).show();
                } else if (location) {
                    Toast.makeText(this, "Storage permission is required to store map tiles to reduce data usage and for offline usage.", Toast.LENGTH_LONG).show();
                } else if (storage) {
                    Toast.makeText(this, "Location permission is required to show the user's location on map.", Toast.LENGTH_LONG).show();
                }
                else if (phonestate) {
                    Toast.makeText(this, "App requires device information to proceed further.", Toast.LENGTH_LONG).show();
                }
                else if (smsstate) {
                    Toast.makeText(this, "Reading SMS permission is required.", Toast.LENGTH_LONG).show();
                }
                else if (accountstate) {
                    Toast.makeText(this, "Account information is required", Toast.LENGTH_LONG).show();
                }else { // !location && !storage case
                    // Permission Denied
                    Toast.makeText(SplashActivityNew.this, "Storage permission is required to store map tiles to reduce data usage and for offline usage." +
                            "\nLocation permission is required to show the user's location on map."+"\nPhone state permission is required to show the user's location on map.", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
