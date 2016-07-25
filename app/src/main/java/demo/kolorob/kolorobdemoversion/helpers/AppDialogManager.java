package demo.kolorob.kolorobdemoversion.helpers;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.interfaces.RetryCallBackForNoInternet;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by touhid on 12/19/15.
 * @author touhid
 */
public class AppDialogManager {

    private static final String TAG = AppDialogManager.class.getSimpleName();

    public static void showNoInternetDialog(final Context context,
                                            final RetryCallBackForNoInternet rc4NoInt) {
    try {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setMessage("আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");
        b.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        b.setPositiveButton("পুনরায় চেস্টা করুন", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                rc4NoInt.retry();
            }
        });
        AlertDialog d = b.create();
        d.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        d.show();
    } catch (Exception e) {
        Lg.e(TAG, "Exception during showing the No-Internet-Dialog.", e);
    }
}
}
