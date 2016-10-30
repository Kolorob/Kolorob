package demo.kolorob.kolorobdemoversion.utils;


/**
 * Created by arafat on 28/05/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.PhoneRegActivity;

public class AlertMessage {
	public static int width;
	public static int height;


	public static void ShowMsgCommentView(final Context c, final String title,
										final String body) {

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
				alertDialog.cancel();
				((Activity)c).finish();
			}
		});

		alertDialog.setCancelable(true);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
		alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

	}



	public static void showAskToRegister(final Context c, final String title,
								   final String body) {

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
		final ImageView no=(ImageView)promptView.findViewById(R.id.no);

		header.setText(title);
		bodys.setText(body);

		okay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(c, PhoneRegActivity.class);
				c.startActivity(intent);
			}
		});

		no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});

		alertDialog.setCancelable(true);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
		alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);




	}


	public static void showMessage(final Context c, final String title,
			final String body) {

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
				alertDialog.cancel();
			}
		});

		alertDialog.setCancelable(true);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
		alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

	}


	public static void showMessageClose(final Context c, final String title,
								   final String body) {

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
				alertDialog.cancel();
				((Activity)c).finish();
			}
		});

		alertDialog.setCancelable(true);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
		alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

	}

	public static void showProgress(final Context c,
			ProgressDialog progressDialog) {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(c);
		}
		if (progressDialog != null && !progressDialog.isShowing()) {
			progressDialog = ProgressDialog.show(c, "Please wait...",
					"Buffering...", true, true);
		}

	}

	public static void cancelProgress(final Context c,
			final ProgressDialog progressDialog) {

		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();

		}
	}
}
