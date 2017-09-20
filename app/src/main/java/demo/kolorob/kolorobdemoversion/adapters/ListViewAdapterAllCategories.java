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
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainmentNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealthNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityLegalNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityNGO;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityReligious;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutEducation;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutFinance;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutGovernment;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class ListViewAdapterAllCategories extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;

	Activity activity;
	private List<AllHolder> worldpopulationlist = null;
	private ArrayList<AllHolder> arraylist;
	FinancialNewDBModel nullfin;
	ArrayList<EduNewModel> nulledu=new ArrayList<>();
	EntertainmentNewDBModel nullent;
	LegalAidNewDBModel nullleg;
	HealthNewDBModelMain nullhel;
	GovernmentNewDBModel nullgov;
	NGONewDBModel nullNgo;
	ReligiousNewDBModel nullReligious;

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
				if (catt==60000) {
					// Send single item click data to SingleItemView Class
					FinNewDBTable financialServiceProviderTable = new FinNewDBTable(ListViewAdapterAllCategories.this.mContext);
					nullfin = financialServiceProviderTable.getfinNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsLayoutFinance.class);
					iient.putExtra(AppConstants.KEY_DETAILS_FINANCIALNEW, nullfin);
					activity.startActivity(iient);
//				Toast.makeText(mContext, "rank " + worldpopulationlist.get(position).getNodeid(), Toast.LENGTH_LONG).show();
				}
				else if (catt==10000) {
					EduNewDBTableMain educationServiceProviderTable=new EduNewDBTableMain(ListViewAdapterAllCategories.this.mContext);
					nulledu=educationServiceProviderTable.geteduNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsLayoutEducation.class);
					iient.putExtra(AppConstants.KEY_DETAILS_EDU, nulledu);
					activity.startActivity(iient);
				}
				else if (catt==20000) {
					HealthNewDBTableMain healthServiceProviderTable=new HealthNewDBTableMain(ListViewAdapterAllCategories.this.mContext);
					nullhel=healthServiceProviderTable.gethelNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityHealthNew.class);
					iient.putExtra(AppConstants.KEY_DETAILS_HEALTH_NEW, nullhel);
					activity.startActivity(iient);
				}
				else if (catt==30000) {
					EntNewDBTable entertainmentServiceProviderTable=new EntNewDBTable(ListViewAdapterAllCategories.this.mContext);

					nullent=entertainmentServiceProviderTable.getentNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityEntertainmentNew.class);
					iient.putExtra(AppConstants.KEY_DETAILS_ENT, nullent);
					activity.startActivity(iient);

//				Toast.makeText(mContext, "rank " + worldpopulationlist.get(position).getNodeid(), Toast.LENGTH_LONG).show();
				}
				else if (catt==50000) {
					LegalAidNewDBTable legalAidServiceProviderTable=new LegalAidNewDBTable(ListViewAdapterAllCategories.this.mContext);
					nullleg=legalAidServiceProviderTable.getlegNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityLegalNew.class);
					iient.putExtra(AppConstants.KEY_DETAILS_LEGAL, nullleg);
					activity.startActivity(iient);
							}

				else if (catt==40000) {
					GovNewDBTable governmentNewTable=new GovNewDBTable(ListViewAdapterAllCategories.this.mContext);
					nullgov=governmentNewTable.getgovNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsLayoutGovernment.class);
					iient.putExtra(AppConstants.KEY_DETAILS_GOV, nullgov);
					activity.startActivity(iient);
				}

				else if (catt == 70000) {
					NGONewDBTable ngoNewDBTable = new NGONewDBTable(ListViewAdapterAllCategories.this.mContext);
					nullNgo = ngoNewDBTable.getngoNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityNGO.class);
					iient.putExtra(AppConstants.KEY_DETAILS_NGO, nullNgo);
					activity.startActivity(iient);
				}

				else if (catt == 80000) {
					ReligiousNewDBTable religiousNewDBTable = new ReligiousNewDBTable(ListViewAdapterAllCategories.this.mContext);
					nullReligious = religiousNewDBTable.getreligiousNode2(worldpopulationlist.get(position).getNodeid());
					Intent iient = new Intent(getActivity(), DetailsInfoActivityReligious.class);
					iient.putExtra(AppConstants.KEY_DETAILS_RELIGIOUS, nullReligious);
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
