
package demo.kolorob.kolorobdemoversion.helpers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by arafat on 28/05/2016.
 */


public class AlertMessage {

    public static void showMessage(final Context c, final String title, final String s) {
        final AlertDialog.Builder aBuilder = new AlertDialog.Builder(c);
        aBuilder.setTitle(title);
        aBuilder.setIcon(R.drawable.info);
        aBuilder.setMessage(s);

        aBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }

        });

        aBuilder.show();
    }

    public static void showProgress(final Context c, ProgressDialog progressDialog) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(c);
        }
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog = ProgressDialog.show(c, "Please wait...", "Buffering...", true, true);
        }

    }

    public static void cancelProgress(final Context c, final ProgressDialog progressDialog) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();

        }
    }
}
