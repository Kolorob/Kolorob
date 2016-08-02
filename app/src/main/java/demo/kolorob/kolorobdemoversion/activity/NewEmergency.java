package demo.kolorob.kolorobdemoversion.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import demo.kolorob.kolorobdemoversion.R;

public class NewEmergency extends Activity {

	private EmergencyAdapter adapter;
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



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.emergency_list_view);
		
		setupListViewAdapter();
		
		//setupAddPaymentButton();
		for(int i=0;i<10;i++)
		adapter.insert(new Emergency("", 0), 0);
	}

	public void removeAtomPayOnClickHandler(View v) {
		Emergency itemToRemove = (Emergency)v.getTag();
		adapter.remove(itemToRemove);
	}

	private void setupListViewAdapter() {
		adapter = new EmergencyAdapter(NewEmergency.this, R.layout.emergencylistitem_new, new ArrayList<Emergency>());
		ListView atomPaysListView = (ListView)findViewById(R.id.EnterPays_atomPaysList);
		atomPaysListView.setAdapter(adapter);
	}

	public void close(View v) {
		this.finish();
	}
	
//	private void setupAddPaymentButton() {
//		findViewById(R.id.EnterPays_addAtomPayment).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				adapter.insert(new Emergency("", 0), 0);
//			}
//		});
//	}
}
