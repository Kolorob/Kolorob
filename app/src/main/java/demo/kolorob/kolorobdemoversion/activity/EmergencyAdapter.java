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



	String[] values = new String[] { "Bangladesh Fire Service, \nবাংলাদেশ ফায়ার সার্ভিস অ্যান্ড সিভিল ডিফেন্স ",
			"Police Control Room, \nপুলিশ কন্ট্রোল রুম",
			"Pllabi Police Station, পল্লবী থানা",
			"RAB – 4, র্যাব - ৪",
			"DESCO - Electricity, \nডেসকো – ইলেক্ট্রিসিটি ",
			"Ministry of Disaster Management, \nদুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয়","Titas Gas, তিতাস গ্যাস ","Dhaka WASA, ঢাকা ওয়াসা",
			"Blood Bank,  ব্লাড ব্যাংক ","Dhaka North City Corporation, \nঢাকা উত্তর সিটি কর্পোরেশন","Violence against Women & Children, \nনারী ও শিশু নির্যাতন প্রতিরোধ",""
	};

	String[] address = new String[] { "Mirpur 10, Dhaka",
			"Dhaka",
			"পল্লবী থানা",
			"Dhaka",
			"DESCO, Pallabi, Dhaka",
			"Dhaka","Pllabi, Dhaka","Dhaka","Red Crescent","Dhaka","","",
	};
	String[] name_bangla = new String[] { "বাংলাদেশ ফায়ার সার্ভিস অ্যান্ড সিভিল ডিফেন্",
			"পুলিশ কন্ট্রোল রুম",
			"পল্লবী থানা",
			"র্যাব - ৪",
			"ডেসকো – ইলেক্ট্রিসিটি ",
			"দুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয়","তিতাস গ্যাস ","ঢাকা ওয়াসা","ব্লাড ব্যাংক ","ঢাকা উত্তর সিটি কর্পোরেশন","নারী ও শিশু নির্যাতন প্রতিরোধ",""
	};


	String[] phone_no = new String[] { "029555555",
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

		holder.header = (TextView)row.findViewById(R.id.header_name);

		holder.header.setText(values[i]);


		holder.title = (TextView)row.findViewById(R.id.title_name);

		//holder.title.setText(address[i]);
		holder.title.setText("Contact: "+phone_no[i]);
		holder.add_text = (TextView)row.findViewById(R.id.add_text);

		//holder.title.setText(address[i]);
		holder.add_text.setText("Location: "+address[i]);

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
			//	 AlertMessage.showMessage(context,"Location ","Sorry, Location is not found!");
				 AlertMessage.showMessage(context, "ডিরেকশন দেয়া সম্ভব হচ্ছে না",
						 "ডিরেকশন খুজে পাওয়া যায় নি ");

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

					 AlertMessage.showMessage(context, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
							 "ফোন নম্বর পাওয়া যায়নি");
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
				AlertMessage.showMessage(context, "ই মেইল করা সম্ভব হচ্ছে না",
						"ই মেইল আই ডি পাওয়া যায়নি");


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
		TextView header;
		TextView add_text;
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
