package demo.kolorob.kolorobdemoversion.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivity;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class ListViewAdapterEdu extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	Activity activity;
	int size;
	private List<PopulatedfromDBEdu> worldpopulationlist = null;
	private ArrayList<PopulatedfromDBEdu> arraylist;
EducationServiceProviderItem nullfin;
	public ListViewAdapterEdu(Activity act, List<PopulatedfromDBEdu> worldpopulationlist) {
		this.activity = act;
		this.worldpopulationlist = worldpopulationlist;

		inflater = act.getLayoutInflater();
		this.arraylist = new ArrayList<PopulatedfromDBEdu>();
		this.arraylist.addAll(worldpopulationlist);
	}



	public class ViewHolder {
		TextView rank;

	}



	@Override
	public int getCount() {

			size = worldpopulationlist.size();

		return size;

	}






	public PopulatedfromDB getItem(int position) {
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
		String rank=worldpopulationlist.get(position).getRank().toString();
		if (holder.rank.getText()!=rank)
		holder.rank.setText(rank);
		//holder.country.setText(worldpopulationlist.get(position).getCountry());
		//holder.population.setText(worldpopulationlist.get(position).getPopulation());

		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Send single item click data to SingleItemView Class
				EducationServiceProviderTable educationServiceProviderTable=new EducationServiceProviderTable(ListViewAdapterEdu.this.mContext);
				nullfin=educationServiceProviderTable.geteduNode2(worldpopulationlist.get(position).getNodeid());
				Intent iient = new Intent(getActivity(), DetailsInfoActivity.class);
				iient.putExtra(AppConstants.KEY_DETAILS_VIEW, nullfin);
				activity.startActivity(iient);
//				Toast.makeText(mContext, "rank " + worldpopulationlist.get(position).getNodeid(), Toast.LENGTH_LONG).show();

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
			for (PopulatedfromDBEdu wp : arraylist)
			{
				if (wp.getRank().toLowerCase(Locale.getDefault()).contains(charText))
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
