package demo.kolorob.kolorobdemoversion.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DetailsFinancialActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainment;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealth;
import demo.kolorob.kolorobdemoversion.activity.DetailsLegalActivity;
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

public class AlphabetListAdapter extends BaseAdapter {
    private static final String TAG = AlphabetListAdapter.class.getSimpleName();
    Context mContext;
    LayoutInflater inflater;
    Activity activity;
    int size;
    private List<PopulatedfromDBEdu> worldpopulationlist = null;
    private ArrayList<PopulatedfromDBEdu> arraylist;
    EducationServiceProviderItem nulledu;
    HealthServiceProviderItem nullhel;
    EntertainmentServiceProviderItem nullent;
    LegalAidServiceProviderItem nullleg;
    FinancialServiceProviderItem nullfin;
    List<Integer> sectionvalues = new ArrayList<Integer>();

    public AlphabetListAdapter(Activity activity) {
        this.activity=activity;
    }

    public static abstract class Row {}
    
    public static final class Section extends Row {
        public final String text;


        public Section(String text) {
            this.text = text;

        }
    }

    public HashMap<String, Integer> getSections() {
        return sections;
    }

    public void setSections(HashMap<String, Integer> sections) {
        this.sections = sections;
    }

    private HashMap<String, Integer> sections;
    public static final class Item extends Row {
        public final String name;
        public final String id;
        public final int cattid;

        public Item(String name,String id,int cattid) {
            this.name = name;
            this.id=id;
            this.cattid=cattid;
        }
    }
    
    private List<Row> rows;
    
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public Row getItem(int position) {
        return rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    
    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Section) {
            return 1;
        } else {
            return 0;
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        
        if (getItemViewType(position) == 0) { // Item
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = (LinearLayout) inflater.inflate(R.layout.listrow_details, parent, false);
            }
            
            Item item = (Item) getItem(position);

            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(item.name);

        } else { // Section
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = (LinearLayout) inflater.inflate(R.layout.row_section, parent, false);  
            }
            
            Section section = (Section) getItem(position);
            sectionvalues.add(position);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(section.text);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size=sectionvalues.size();

               if(sectionvalues.contains(position)){
                /*   final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(activity).create();

                alertDialog.setMessage("Please tap properly");
                alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();

                            }
                        });
                alertDialog.getWindow().setLayout(200, 300);
                alertDialog.show();
               */} else {
                int valcheck=((Item) getItem(position)).cattid;

                if (valcheck==1)
                {
                    EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(AlphabetListAdapter.this.mContext);
                nulledu = educationServiceProviderTable.geteduNode2(((Item) getItem(position)).id);
                Intent iient = new Intent(activity, DetailsInfoActivity.class);
                iient.putExtra(AppConstants.KEY_DETAILS_VIEW, nulledu);
                activity.startActivity(iient);
                }
                if (valcheck==2)
                {
                    HealthServiceProviderTable healthServiceProviderTable=new HealthServiceProviderTable(AlphabetListAdapter.this.mContext);
                    nullhel=healthServiceProviderTable.gethelNode2(((Item) getItem(position)).id);
                    Intent iient = new Intent(activity, DetailsInfoActivityHealth.class);
                    iient.putExtra(AppConstants.KEY_DETAILS_HEALTH, nullhel);
                    activity.startActivity(iient);
                }
                if (valcheck==3)
                {
                    EntertainmentServiceProviderTable entertainmentServiceProviderTable=new EntertainmentServiceProviderTable(AlphabetListAdapter.this.mContext);

                    nullent=entertainmentServiceProviderTable.getentNode2(((Item) getItem(position)).id);
                    Intent iient = new Intent(activity, DetailsInfoActivityEntertainment.class);
                    iient.putExtra(AppConstants.KEY_DETAILS_ENT, nullent);
                    activity.startActivity(iient);
                }
                if (valcheck==5)
                {
                    LegalAidServiceProviderTable legalAidServiceProviderTable=new LegalAidServiceProviderTable(AlphabetListAdapter.this.mContext);
                    nullleg=legalAidServiceProviderTable.getlegNode2(((Item) getItem(position)).id);
                    Intent iient = new Intent(activity, DetailsLegalActivity.class);
                    iient.putExtra(AppConstants.KEY_DETAILS_LEGAL, nullleg);
                    activity.startActivity(iient);
                }
                if (valcheck==6)
                {
                    FinancialServiceProviderTable financialServiceProviderTable=new FinancialServiceProviderTable(AlphabetListAdapter.this.mContext);
                    nullfin=financialServiceProviderTable.getfinNode2(worldpopulationlist.get(position).getNodeid());
                    Intent iient = new Intent(activity, DetailsFinancialActivity.class);
                    iient.putExtra(AppConstants.KEY_DETAILS_FINANCIAL, nullfin);
                    activity.startActivity(iient);
                }

            }
            }
        });

        return view;
    }


}
