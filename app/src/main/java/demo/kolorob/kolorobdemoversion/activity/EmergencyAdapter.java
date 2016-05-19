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
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

public class EmergencyAdapter extends ArrayAdapter<Emergency> {



	String[] values = new String[] { "Bangladesh Fire Service",
			"Police Control Room",
			"Pllabi Police Station",
			"RAB – 4",
			"DESCO - Electricity",
			"Ministry of Disaster Management","Titas Gas","Dhaka WASA","Blood Bank","Dhaka North City Corporation","Violence against Women & Children",""
	};

	String[] address = new String[] { "Mirpur 10, Dhaka",
			"",
			"পল্লবী থানা",
			"",
			"DESCO, Pallabi, Dhaka",
			"দুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয","Pllabi, Dhaka","","Red Crescent","","","",
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
			"",
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
		holder.removePaymentButton = (ImageButton)row.findViewById(R.id.call_id);
	//	holder.removePaymentButton.setTag(holder.atomPayment);

		holder.name = (ImageButton)row.findViewById(R.id.distance_id);
		//setNameTextChangeListener(holder);
		holder.name.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 // Perform action on click   

               //  Intent activityChangeIntent = new Intent(PresentActivity.this, NextActivity.class);

                 // currentContext.startActivity(activityChangeIntent);
Log.d("========", "-----------------"+i);

               //  PresentActivity.this.startActivity(activityChangeIntent);
             }
         });
		
		holder.value = (ImageButton)row.findViewById(R.id.call_id);
		//setNameTextChangeListener(holder);
		holder.value.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {

				Log.d("========", "-----------------"+i);

				 Intent callIntent1 = new Intent(Intent.ACTION_CALL);
				 callIntent1.setData(Uri.parse("tel:" + phone_no[i]));
				 if(AppUtils.checkPermission(context))
					 context.startActivity(callIntent1);
				 else{
					 Toast.makeText(context,
							 "Sorry, Phone call is not possible now. ", Toast.LENGTH_LONG)
							 .show();
				 }

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
		ImageButton name;
		ImageButton value;
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
