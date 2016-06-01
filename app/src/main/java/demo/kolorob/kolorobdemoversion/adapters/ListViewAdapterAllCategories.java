package demo.kolorob.kolorobdemoversion.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEducation;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainmentNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityFinancialNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealthNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityLegalNew;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class ListViewAdapterAllCategories extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;

	Activity activity;
	private List<AllHolder> worldpopulationlist = null;
	private ArrayList<AllHolder> arraylist;
FinancialServiceProviderItem nullfin;
	EducationServiceProviderItem nulledu;
	EntertainmentServiceProviderItem nullent;
	LegalAidServiceProviderItem nullleg;
	HealthServiceProviderItem nullhel;

	public ListViewAdapterAllCategories(Activity act, List<AllHolder> worldpopulationlist) {
		this.activity = act;
		this.worldpopulationlist = worldpopulationlist;

		inflater = act.getLayoutInflater();
		this.arraylist = new ArrayList<AllHolder>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder {
		TextView rank;

	}

	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public AllHolder getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listrow_details, null);
			// Locate the TextViews in listview_item.xml
			holder.rank = (TextView) view.findViewById(R.id.textView1);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		String rank=worldpopulationlist.get(position).getNamebn().toString();
		if (holder.rank.getText()!=rank)
		holder.rank.setText(rank);
		//holder.country.setText(worldpopulationlist.get(position).getCountry());
		//holder.population.setText(worldpopulationlist.get(position).getPopulation());
		
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
int catt=worldpopulationlist.get(position).getCatid();
				if (catt==6) {
					// Send single item click data to SingleItemView Class
					FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(ListViewAdapterAllCategories.this.mContext);
					nullfin = financialServiceProviderTable.getfinNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityFinancialNew.class);
					iient.putExtra(AppConstants.KEY_DETAILS_FINANCIAL, nullfin);
					activity.startActivity(iient);
//				Toast.makeText(mContext, "rank " + worldpopulationlist.get(position).getNodeid(), Toast.LENGTH_LONG).show();
				}
				if (catt==1) {
					EducationServiceProviderTable educationServiceProviderTable=new EducationServiceProviderTable(ListViewAdapterAllCategories.this.mContext);
					nulledu=educationServiceProviderTable.geteduNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityEducation.class);
					iient.putExtra(AppConstants.KEY_DETAILS_VIEW, nulledu);
					activity.startActivity(iient);
				}
				if (catt==2) {
					HealthServiceProviderTable healthServiceProviderTable=new HealthServiceProviderTable(ListViewAdapterAllCategories.this.mContext);
					nullhel=healthServiceProviderTable.gethelNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityHealthNew.class);
					iient.putExtra(AppConstants.KEY_DETAILS_HEALTH, nullhel);
					activity.startActivity(iient);
				}
				if (catt==3) {
					EntertainmentServiceProviderTable entertainmentServiceProviderTable=new EntertainmentServiceProviderTable(ListViewAdapterAllCategories.this.mContext);

					nullent=entertainmentServiceProviderTable.getentNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityEntertainmentNew.class);
					iient.putExtra(AppConstants.KEY_DETAILS_ENT, nullent);
					activity.startActivity(iient);

//				Toast.makeText(mContext, "rank " + worldpopulationlist.get(position).getNodeid(), Toast.LENGTH_LONG).show();
				}
				if (catt==5) {
					LegalAidServiceProviderTable legalAidServiceProviderTable=new LegalAidServiceProviderTable(ListViewAdapterAllCategories.this.mContext);
					nullleg=legalAidServiceProviderTable.getlegNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityLegalNew.class);
					iient.putExtra(AppConstants.KEY_DETAILS_LEGAL, nullleg);
					activity.startActivity(iient);
							}
				SharedPreferences pref = getActivity().getSharedPreferences("MyPref", mContext.MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				editor.putBoolean("Search", true);
				editor.commit();
			}
		});

		return view;
	}

	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		worldpopulationlist.clear();
		if (charText.length() == 0) {
			worldpopulationlist.addAll(arraylist);
		} 
		else 
		{
			for (AllHolder wp : arraylist)
			{
				if (wp.getNameen().toLowerCase(Locale.getDefault()).contains(charText))
				{
					worldpopulationlist.add(wp);
				}
				else if (wp.getNamebn().toLowerCase(Locale.getDefault()).contains(charText))
				{
					worldpopulationlist.add(wp);
				}

			}
		}
		notifyDataSetChanged();
	}

	public Activity getActivity() {
		return activity;
	}

}
