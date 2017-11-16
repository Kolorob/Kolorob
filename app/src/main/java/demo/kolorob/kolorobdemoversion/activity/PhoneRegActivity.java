package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.appevents.AppEventsLogger;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;
import info.hoang8f.widget.FButton;

/**
 * Created by israt.jahan on 09/30/2016.
 */

/*
* In this activity user registration been done. To make sure each user is using individual device we are storing IMEI number
* so for marshmallow device permission needs to be given by user to get IMEI number.*/
    /*accesstoken been initialized in application class. So if user already registered or used facebook account kit it does not appear second
    * time; if not we are validating number using account kit. Here the activities are in control of facebook. So sometimes it takes
    * time to receive the code but unfortunately it could not be traced.*/


public class PhoneRegActivity extends Activity {


    public static int width;
    public static int height;
    private String phoneNumber, uname;

    String phoneNumberString = "";
    private EditText phone, name;

    String username = "kolorobapp";
    String password = "!2Jm4jFe3WgBZKEN";


    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 175;
    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    String gotname;
    TextView helname;
    Boolean registered;
    private Context con;
    String IMEINumber, IMEI = null, PHN = null;
    FButton Submit;
    TextView phoneheader;
    String s;
    AccessToken accessToken = null;
    public static int APP_REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AccountKit.initialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        Log.e("SDK Initialized: ", "SDK: " + FacebookSdk.isInitialized() + "AccountKit: " + AccountKit.isInitialized());

        setContentView(R.layout.phone_reg);
        LinearLayout first = (LinearLayout) findViewById(R.id.firstreg);
        LinearLayout second = (LinearLayout) findViewById(R.id.secondreg);
        con = this;

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        IMEI = settings.getString("IMEI", null);
        PHN = settings.getString("PHN", null);
        registered = settings.getBoolean("IFREGISTERED", false);
        accessToken = AccountKit.getCurrentAccessToken();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (IMEI == null) checkPermissions();
            else if (accessToken == null) goToLogin(true); //user is using app for the first time

        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            doPermissionGrantedStuffs();

        }
        if (registered) /* so that if user taps on registration page while using he/she can see an welcome page only*/ {
            first.setVisibility(View.GONE);
            second.setVisibility(View.VISIBLE);
            helname = (TextView) findViewById(R.id.hellonaame);
            helname.setText(SharedPreferencesHelper.getUname(PhoneRegActivity.this));
        }

        phone = (EditText) findViewById(R.id.phone_id);

        phone.setHintTextColor(getResources().getColor(R.color.blue));
        phone.setTextColor(getResources().getColor(R.color.gray));
        phone.setEnabled(false);
        if (accessToken != null) {
            String pnumber = SharedPreferencesHelper.getNumber(con); //get number from sharedpref and set that value in phone edittext.
            //we are not giving option to user to write down number second time once thats been validated via account kit.
            if (pnumber.length() > 0) {
                phone.setText(pnumber.toString());
            } else {
                PHN = settings.getString("PHN", null);
                if (PHN != null) {
                    phone.setText(PHN.toString());
                }

            }
        }

        phoneheader = (TextView) findViewById(R.id.phoneheader);

        s = String.valueOf(phone.getText().toString().trim());
        name = (EditText) findViewById(R.id.userid);
        Submit = (FButton) findViewById(R.id.submittoserver);


        if (SharedPreferencesHelper.isTabletDevice(con)) {
            phoneheader.setTextSize(45);
        }

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                PHN = settings.getString("PHN", null);
                if (PHN != null || !phoneNumberString.equals("")) {

                    phoneNumber = phone.getText().toString().trim();
                    uname = name.getText().toString().trim();
                    if (uname.equals("")) {


                        name.setError(getString(R.string.write_name));

                    } else if (uname.length() <= 50 && !uname.equals("") && (AppUtils.isNetConnected(getApplicationContext()))) {
                        sendPhoneNumberToServer(phoneNumber);
                    } else {
                        ToastMessageDisplay.setText(PhoneRegActivity.this, getString(R.string.connect_to_internet));
//                    Toast.makeText(this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...",
//                            Toast.LENGTH_LONG).show();
                        ToastMessageDisplay.showText(PhoneRegActivity.this);
                    }
                } else {

                    goToLogin(true);
                }
            }
        });
    }

    /*marshmallow permission check. These permissions are very important to make app work perfectly*/
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
            // Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            String[] params = permissions.toArray(new String[permissions.size()]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(params, REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        } // else: We already have permissions, so handle as normal
    }

    public void doPermissionGrantedStuffs() {
        //Have an  object of TelephonyManager
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        IMEINumber = tm.getDeviceId();
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("IMEI", IMEINumber);
        editor.apply();

        IMEI = settings.getString("IMEI", null);
        if (accessToken == null) {


            goToLogin(true);
        }

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
                Boolean location = perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                Boolean storage = perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                Boolean phonestate = perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
                Boolean smsstate = perms.get(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
                Boolean accountstate = perms.get(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED;
                if (location && storage && phonestate && smsstate && accountstate) {
                    // All Permissions Granted
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    IMEINumber = tm.getDeviceId();
                    SharedPreferences settings = getSharedPreferences("prefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("IMEI", IMEINumber);
                    editor.apply();
                    IMEI=settings.getString("IMEI",null);
                    if(accessToken == null){
                        goToLogin(true);
                    }
                    //Toast.makeText(PhoneRegActivity.this, "Thanks for permission", Toast.LENGTH_SHORT).show();
                } else if (location) {
                   // Toast.makeText(this, "Storage permission is required to store map tiles to reduce data usage and for offline usage.", Toast.LENGTH_LONG).show();
                } else if (storage) {
                   // Toast.makeText(this, "Location permission is required to show the user's location on map.", Toast.LENGTH_LONG).show();
                }
                else if (phonestate) {
                 //   Toast.makeText(this, "App requires device information to proceed further.", Toast.LENGTH_LONG).show();
                }
                else if (smsstate) {
           //         Toast.makeText(this, "Reading SMS permission is required.", Toast.LENGTH_LONG).show();
                }
                else if (accountstate) {
         //           Toast.makeText(this, "Account information is required", Toast.LENGTH_LONG).show();
                }else { // !location && !storage case
                    // Permission Denied
                   //         "\nLocation permission is required to show the user's location on map."+"\nPhone state permission is required to show the user's location on map.", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void goToLogin(boolean isSMSLogin) {

        LoginType loginType = isSMSLogin ? LoginType.PHONE : LoginType.EMAIL;

        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        loginType,
                        AccountKitActivity.ResponseType.TOKEN);
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        this.startActivityForResult(intent, APP_REQUEST_CODE);
    }



    public void sendPhoneNumberToServer(final String phone)
    {
        try {
            gotname = URLEncoder.encode(uname.replace(" ", "%20"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

            SharedPreferences settings = getSharedPreferences("prefs", 0);

            IMEINumber = settings.getString("IMEI", null);

            if(IMEINumber == null || IMEINumber.equals("0")) {
                IMEINumber = gotname.concat(String.valueOf(randomBox()));
            }

        RequestQueue requestQueue = Volley.newRequestQueue(PhoneRegActivity.this);
        // http://192.168.43.57/demo/api/customer_reg?phone=01711310912

        String url = "http://kolorob.net/kolorob-new-demo/api/customer_reg4?username="+username+"&password="+password+"/"+"&phone="+phone+"&name="+gotname+"&deviceid="+IMEINumber+"" ;
        //  String url = "http://kolorob.net/demo/api/customer_reg?username="+username+"&password="+password+"/" ;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(PhoneRegActivity.this,response,Toast.LENGTH_SHORT).show();
                        // Log.d(">>>>>","status "+response);

                        String notvalid=response;

                        try {

                            //

                            if(response.contains("true")) /* by parsing server response we are storing the registration time of user*/
                            {
                                List<String> responses = Arrays.asList(response.split(","));
                                String dateserver=responses.get(1);

                                SharedPreferences settings = getSharedPreferences("prefs", 0);
                                SharedPreferences.Editor editor = settings.edit();
                                String check=settings.getString("timesfirstinstall","2");
                                if(check.equals("2")) {
                                    settings.edit().putString("timesfirstinstall", dateserver).apply();
                                }
                                SharedPreferencesHelper.setNumber(con,phoneNumber);

                                SharedPreferencesHelper.setUname(con,uname);

                                editor.putBoolean("IFREGISTERED", true);
                                editor.apply();
                                showMessageExisting(PhoneRegActivity.this, getString(R.string.registered_successfully),
                                        getString(R.string.thanks_for_registering), 1);



                            }


                            else if(response.equals("\"Invalid Phone Number\""))
                            {
                                AlertMessage.showMessage(PhoneRegActivity.this, getString(R.string.invalid_number),
                                        getString(R.string.enter_correct_number));                            }
                            else if(response.equals("\"deviceid not found\""))
                            {
                                showMessageExisting(PhoneRegActivity.this, getString(R.string.sorry),
                                        getString(R.string.request_for_imei), 4);
                            }

                            else if(response.contains("already registered")) {

                                List<String> responses = Arrays.asList(response.split(","));
                                String serverusername= responses.get(3);
                                String serverusernamechanged = StringEscapeUtils.unescapeJava(serverusername).replace("%20"," " );

                                SharedPreferences settings = getSharedPreferences("prefs", 0);
                                SharedPreferences.Editor editor = settings.edit();

                                SharedPreferencesHelper.setNumber(con, phoneNumber);
                                SharedPreferencesHelper.setUname(con, serverusernamechanged);


                                editor.putBoolean("IFREGISTERED", true);
                                editor.apply();


                                showMessageExisting(PhoneRegActivity.this,
                                        getString(R.string.already_registered), getString(R.string.your_username) + " " + serverusernamechanged + " " + getString(R.string.and_number) + " " + phoneNumber, 2);
                            }

                            else if(response.contains("EXISTING")) /*if user is already in our db; then we are replacing new number and user name in application*/
                            {
                                List<String> responses = Arrays.asList(response.split(","));
                                String thisdate=responses.get(10);

                                String serverusername= responses.get(3);
                                String serverusernamechanged = StringEscapeUtils.unescapeJava(serverusername).replace("%20"," " );


                                SharedPreferences settings = getSharedPreferences("prefs", 0);
                                SharedPreferences.Editor editor = settings.edit();

                                SharedPreferencesHelper.setNumber(con, phoneNumber);
                                SharedPreferencesHelper.setUname(con, serverusernamechanged);
                                String check=settings.getString("timesfirstinstall","2");
                                if(check.equals("2")) {
                                    settings.edit().putString("timesfirstinstall", thisdate).apply();
                                }

                                editor.putBoolean("IFREGISTERED", true);
                                editor.apply();

                                showMessageExisting(PhoneRegActivity.this, getString(R.string.new_install),
                                        getString(R.string.your_username) + " " + serverusernamechanged + getString(R.string.and_number) + " " + phoneNumber, 2);

                            }

                            else {
                                AlertMessage.showMessage(PhoneRegActivity.this, getString(R.string.sorry),
                                        getString(R.string.try_later));
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastMessageDisplay.setText(PhoneRegActivity.this,error.toString());
                        ToastMessageDisplay.showText(PhoneRegActivity.this);

                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
/*

                params.put("phone",phone);

                params.put("deviceid",IMEINumber);

                params.put("name", uname.replace(' ','+'));
*/
                return params;
            }

        };

// Adding request to request queue


        requestQueue.add(stringRequest);



    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                showErrorActivity(loginResult.getError());
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.

                // Success! Start your next activity...
                setUserInformation();
                return;
            }
        }
    }

    private void showErrorActivity(final AccountKitError error) {
        Log.println(Log.ASSERT, "AccountKit", error.toString());
    }
    public void setUserInformation(){
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                // Get Account Kit ID
                String accountKitId = account.getId();
                Log.println(Log.ASSERT, "AccountKit", "ID: " + accountKitId);

                boolean SMSLoginMode = false;

                // Get phone number
                PhoneNumber phoneNumber = account.getPhoneNumber();

                if (phoneNumber != null) {
                    phoneNumberString = phoneNumber.toString();
                    phone.setText(phoneNumberString.toString());
                    Log.println(Log.ASSERT, "AccountKit", "Phone: " + phoneNumberString);
                    SMSLoginMode = true;
                    SharedPreferences settings = getSharedPreferences("prefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("PHN", phoneNumberString);
                    editor.apply();
                }



            }

            @Override
            public void onError(final AccountKitError error) {
                Log.println(Log.ASSERT, "AccountKit", "Error: " + error.toString());
            }
        });

    }
    public void showMessageExisting(final Context c, final String title,
                                    final String body, final int from) {

        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View promptView = layoutInflater.inflate(R.layout.default_alert, null);


        final Dialog alertDialog = new Dialog(c);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final TextView header = (TextView) promptView.findViewById(R.id.headers);
        final TextView bodys = (TextView) promptView.findViewById(R.id.body);
        final ImageView okay=(ImageView)promptView.findViewById(R.id.okay);

        header.setText(title);
        bodys.setText(body);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(from==1)
                {
                    Intent newIntent = new Intent(PhoneRegActivity.this, DataLoadingActivity.class);
                    newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(newIntent);
                    finish();
                }
                else if (from==2)
                {
                    Intent newIntent = new Intent(PhoneRegActivity.this, DataLoadingActivity.class);
                    newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(newIntent);
                    finish();
                }

                else if (from==3)
                {
                    goToLogin(true);
                }
                else if (from==4)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&IMEI==null) {

                        checkPermissions();

                    }
                    else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

                        doPermissionGrantedStuffs();

                    }
                }


                alertDialog.cancel();
            }
        });

        alertDialog.setCancelable(false);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

    }
    public static int randomBox() {

        Random rand = new Random();
        int pickedNumber = rand.nextInt(1000);
        return pickedNumber;

    }
}
