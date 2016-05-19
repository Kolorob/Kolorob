package demo.kolorob.kolorobdemoversion.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class NewEmergency extends Activity {

	private EmergencyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		setupListViewAdapter();
		
		setupAddPaymentButton();
		for(int i=0;i<10;i++)
		adapter.insert(new Emergency("", 0), 0);
	}

	public void removeAtomPayOnClickHandler(View v) {
		Emergency itemToRemove = (Emergency)v.getTag();
		adapter.remove(itemToRemove);
	}

	private void setupListViewAdapter() {
		adapter = new EmergencyAdapter(NewEmergency.this, R.layout.atom_pay_list_item2, new ArrayList<Emergency>());
		ListView atomPaysListView = (ListView)findViewById(R.id.EnterPays_atomPaysList);
		atomPaysListView.setAdapter(adapter);
	}
	
	private void setupAddPaymentButton() {
		findViewById(R.id.EnterPays_addAtomPayment).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.insert(new Emergency("", 0), 0);
			}
		});
	}
}
