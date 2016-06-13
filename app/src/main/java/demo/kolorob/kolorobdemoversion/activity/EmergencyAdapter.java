package demo.kolorob.kolorobdemoversion.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

public class EmergencyAdapter extends ArrayAdapter<Emergency> {



	String[] values = new String[] { "Bangladesh Fire Service, \n বাংলাদেশ ফায়ার সার্ভিস অ্যান্ড সিভিল ডিফেন্স ",
			"Police Control Room, \nপুলিশ কন্ট্রোল রুম",
			"Pllabi Police Station, \n পল্লবী থানা",
			"RAB – 4, \n র্যাব - ৪",
			"DESCO - Electricity, \n ডেসকো – ইলেক্ট্রিসিটি ",
			"Ministry of Disaster Management, \nদুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয়","Titas Gas, \n তিতাস গ্যাস ","Dhaka WASA, \n ঢাকা ওয়াসা",
			"Blood Bank,  \n ব্লাড ব্যাংক ","Dhaka North City Corporation, \n ঢাকা উত্তর সিটি কর্পোরেশন","Violence against Women & Children, \n নারী ও শিশু নির্যাতন প্রতিরোধ",""
	};

	String[] address = new String[] { "Mirpur 10, Dhaka",
			"Dhaka",
			"পল্লবী থানা",
			"Dhaka",
			"DESCO, Pallabi, Dhaka",
			"","Pllabi, Dhaka","Dhaka","Red Crescent","Dhaka","","",
	};
	String[] name_bangla = new String[] { "029555555",
			"পুলিশ কন্ট্রোল রুম",
			"পল্লবী থানা",
			"র্যাব - ৪",
			"ডেসকো – ইলেক্ট্রিসিটি ",
			"দুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয়","তিতাস গ্যাস ","ঢাকা ওয়াসা","ব্লাড ব্যাংক ","ঢাকা উত্তর সিটি কর্পোরেশন","নারী ও শিশু নির্যাতন প্রতিরোধ",""
	};


	String[] phone_no = new String[] { "বাংলাদেশ ফায়ার সার্ভিস অ্যান্ড সিভিল ডিফেন্স ",
			"029555555",
			"027124000",
			"02-9015922",
			"029015922",
			"01777910499","02-9014291","16162","029139940","16364","10921",""
	};


	String[] address_bangla = new String[] { "",
			"মিরপুর ১০, ঢাকা",
			"Not Found",
			"Dhaka",
			"পল্লবী, ঢাকা",
			"পল্লবী, ঢাকা","","","","","","",
	};


	String[] phone_no_bangla = new String[] { "০২৯৫৫৫৫৫৫",
			"০২৭১২৪০০০",
			"০২-৯০১৫৯২২",
			"০১৭৭৭৯১০৪৯৯",
			"০২৯০০১০৫১",
			"১০৯৪১","০২৯০১৪২৯১","১৬১৬২","০২৯১৩৯৯৪০","১৬৩৬৪","১০৯২১","১০৯৪১"
	};


	protected static final String LOG_TAG = EmergencyAdapter.class.getSimpleName();
	
	private List<Emergency> items;
	private int layoutResourceId;
	private Context context;

	public EmergencyAdapter(Context context, int layoutResourceId, List<Emergency> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		AtomPaymentHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new AtomPaymentHolder();
		holder.emergency = items.get(position);
		final int i = position;

		holder.title = (TextView)row.findViewById(R.id.emergency_name);

		holder.title.setText(values[i]+","+" "+address[i]);
		holder.removePaymentButton = (ImageButton)row.findViewById(R.id.call_id);
	//	holder.removePaymentButton.setTag(holder.atomPayment);

		holder.loc = (ImageButton)row.findViewById(R.id.distance_id);
		//setNameTextChangeListener(holder);
		holder.loc.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 // Perform action on click   

               //  Intent activityChangeIntent = new Intent(PresentActivity.this, NextActivity.class);

                 // currentContext.startActivity(activityChangeIntent);
Log.d("========", "-----------------"+i);
//				 Toast.makeText(context,
//						 "Sorry, Location is not found! ", Toast.LENGTH_LONG)
//						 .show();
				 AlertMessage.showMessage(context,"Location ","Sorry, Location is not found!");

               //  PresentActivity.this.startActivity(activityChangeIntent);
             }
         });
		
		holder.call = (ImageButton)row.findViewById(R.id.call_id);
		//setNameTextChangeListener(holder);
		holder.call.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {

				Log.d("========", "-----------------"+i);

				 Intent callIntent1 = new Intent(Intent.ACTION_CALL);
				 callIntent1.setData(Uri.parse("tel:" + phone_no[i]));
				 if(AppUtils.checkPermission(context))
					 context.startActivity(callIntent1);
				 else{
//					 Toast.makeText(context,
//							 "Sorry, Phone call is not possible now.", Toast.LENGTH_LONG)
//							 .show();
					 AlertMessage.showMessage(context, "Phone ", "Sorry, Phone call is not possible now.");
				 }

             }
         });

		holder.email = (ImageButton)row.findViewById(R.id.email_id);
		//setNameTextChangeListener(holder);
		holder.email.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {


//					Toast.makeText(context,
//							"Sorry, Email is not found! ", Toast.LENGTH_LONG)
//							.show();
				AlertMessage.showMessage(context, "Email ", "Sorry, Email ID is not found!");


			}
		});
		
		
	//	holder.value = (TextView)row.findViewById(R.id.atomPay_value);
//		setValueTextListeners(holder);

		row.setTag(holder);

		setupItem(holder);
		return row;
	}

	private void setupItem(AtomPaymentHolder holder) {
	//	holder.name.setText(holder.atomPayment.getName());
	//	holder.value.setText(String.valueOf(holder.atomPayment.getValue()));
		
		//holder.value.setBackgroundResource(R.drawable.ic_launcher);
	}

	public static class AtomPaymentHolder {
		Emergency emergency;
		ImageButton loc;
		ImageButton call;
		ImageButton email;
		TextView title;
		ImageButton removePaymentButton;
	}
	
//	private void setNameTextChangeListener(final AtomPaymentHolder holder) {
//		holder.name.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				holder.atomPayment.setName(s.toString());
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//
//			@Override
//			public void afterTextChanged(Editable s) { }
//		});
//	}

//	private void setValueTextListeners(final AtomPaymentHolder holder) {
//		holder.value.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				try{
//					holder.atomPayment.setValue(Double.parseDouble(s.toString()));
//				}catch (NumberFormatException e) {
//					Log.e(LOG_TAG, "error reading double value: " + s.toString());
//				}
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//
//			@Override
//			public void afterTextChanged(Editable s) { }
//		});
//	}
}
