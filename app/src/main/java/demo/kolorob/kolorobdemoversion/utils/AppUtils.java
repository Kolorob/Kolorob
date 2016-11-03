package demo.kolorob.kolorobdemoversion.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.PublicKey;
import java.util.Collections;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.OpeningActivity;
import demo.kolorob.kolorobdemoversion.helpers.AndroidUnicodedFontSupport;

/**
 * Created by touhid on 10/29/15.
 * @author touhid
 */
public class AppUtils {
    public static int width;
    public static int height;
    private static OpeningActivity open;
    private static Typeface bengaliFontNormalSolmn, bengaliFontBoldSolmn;

    public static void showMessage(final Context c, final String title,
                                   final String body) {

        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View promptView = layoutInflater.inflate(R.layout.gps_alert, null);


        final Dialog alertDialog = new Dialog(c);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final TextView header = (TextView) promptView.findViewById(R.id.headers);
        final TextView bodys = (TextView) promptView.findViewById(R.id.body);
        final ImageView okay=(ImageView)promptView.findViewById(R.id.okay);
        final ImageView close=(ImageView)promptView.findViewById(R.id.close);
        header.setText(title);
        bodys.setText(body);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                c.startActivity(intent);
                alertDialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              alertDialog.dismiss();
            }
        });

        alertDialog.setCancelable(false);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    public static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
    public static void showSettingsAlert(Context con) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(con);
        final Context cont;
        cont = con;
        // Setting Dialog Title
        alertDialog.setTitle("জিপিএস বন্ধ করা রয়েছে!");

        // Setting Dialog Message
        alertDialog
                .setMessage(" আপনি কি আপনার মোবাইলের জিপিএস টি চালু করতে চান?");

        // On pressing Settings button
        alertDialog.setPositiveButton("সেটিংস",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        cont.startActivity(intent);
                    }
                });

        // on pressing cancel button
        alertDialog.setNegativeButton("দরকার নেই",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Showing Alert Message
        alertDialog.show();
    }

    public static void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean checkPermission(Context con){
        int result = ContextCompat.checkSelfPermission(con, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }


    public static boolean mobile_number_verification(String number)
    {
        if(number.equals(""))
            return false;
        else if(number.length()!=11)
            return true;
        else if(number.charAt(0)!='0')
        {
            return true;
        }
        else if(number.charAt(1)!='1')
        {
            return true;
        }
        else if(number.charAt(2)=='2'||number.charAt(2)=='3'||number.charAt(2)=='4')
            return true;
        else
            return false;


    }

    public static boolean displayGpsStatus(Context context) {
        // context = open.getApplicationContext();;
        ContentResolver contentResolver = context.getApplicationContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }

    public static SpannableString getUnicodedFormat(AssetManager assetManager, String str) {
        if (bengaliFontNormalSolmn == null)
            bengaliFontNormalSolmn = Typeface.createFromAsset(assetManager,
                    "fonts/solaimanlipinormal.ttf");
        return AndroidUnicodedFontSupport.getCorrectedBengaliFormat(str, bengaliFontNormalSolmn, -1);
    }

    public static SpannableString getUnicodedFormatBold(AssetManager assetManager, String str) {
        if (bengaliFontBoldSolmn == null)
            bengaliFontBoldSolmn = Typeface.createFromAsset(assetManager,
                    "fonts/solaimanlipibold.ttf");
        return AndroidUnicodedFontSupport.getCorrectedBengaliFormat(str, bengaliFontBoldSolmn, -1);
    }


    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * @return Screen width or height, depending on which is minimum
     */
    public static int getScreenMinHW(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        return w < h ? w : h;
    }

    public static float getDeviceDpi(Context context) {
        return context.getResources().getDisplayMetrics().density * 160.0f;
    }

    public static String getDeviceDetailsJSONString(Context ctx) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("os", Build.VERSION.SDK_INT);
            jo.put("model", Build.MODEL);
            jo.put("manufacturer", Build.MANUFACTURER);
            jo.put("ip", getDeviceIp(true));
            jo.put("imei", getDeviceIMEI(ctx));
            jo.put("mac", getDeviceMAC(ctx));
            jo.put("dpi", AppUtils.getDeviceDpi(ctx));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     */
    @SuppressLint("DefaultLocale")
    public static String getDeviceIp(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            // for now eat exceptions
        }
        return "";
    }

    public static String getDeviceMAC(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        return wInfo.getMacAddress();
    }

    public static String getDeviceIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId() + "";
    }

    public static boolean isDevicePortrait(Resources res) {
        return res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    //Created by Arafat

    public static Double ScreenSize(Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int densityDpi = (displayMetrics.densityDpi);

        Double diagonal;

        diagonal =  Math.sqrt(width*width+height*height)/densityDpi;
        return diagonal;
    }



    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo.isConnected();
    }
}
