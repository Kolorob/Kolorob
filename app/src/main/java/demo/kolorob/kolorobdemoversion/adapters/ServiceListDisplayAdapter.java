package demo.kolorob.kolorobdemoversion.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainmentNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealthNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityLegalNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutEducation;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutFinance;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutGovernment;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class ServiceListDisplayAdapter extends BaseExpandableListAdapter {

    private final Vector<Group> groups;
    private Object object;
    private long longs;
    private int size;


    public LayoutInflater inflater;
    public Activity activity;
    private int catid;
    private Context ctx;
    private LinearLayout linearLayout;

    public ServiceListDisplayAdapter(Activity act, Vector<Group> groups,int categoryid) {
        activity = act;
        this.groups = groups;

        inflater = act.getLayoutInflater();
        this.catid=categoryid;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        if (catid==AppConstants.EDUCATION)
            object= groups.get(groupPosition).getchildren().get(childPosition);
        else if(catid==AppConstants.ENTERTAINMENT)
            object= groups.get(groupPosition).getChildrenent().get(childPosition);
        else if (catid==AppConstants.HEALTH)
            object= groups.get(groupPosition).getChildrenhea().get(childPosition);
        else if(catid==AppConstants.FINANCIAL)
            object= groups.get(groupPosition).getChildrenfin().get(childPosition);
        else if (catid==AppConstants.LEGAL)

            object= groups.get(groupPosition).getChildrenleg().get(childPosition);

        else if(catid==AppConstants.GOVERNMENT)
            object= groups.get(groupPosition).getChildrengov().get(childPosition);
        return object;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if (catid==AppConstants.EDUCATION)
            longs= groups.get(groupPosition).getchildren().get(childPosition).hashCode();
        else if(catid==AppConstants.ENTERTAINMENT)
            longs= groups.get(groupPosition).getChildrenent().get(childPosition).hashCode();
        if (catid==AppConstants.HEALTH)
            longs= groups.get(groupPosition).getChildrenhea().get(childPosition).hashCode();
        else if(catid==AppConstants.FINANCIAL)
            longs= groups.get(groupPosition).getChildrenfin().get(childPosition).hashCode();
        if (catid==AppConstants.LEGAL)
            longs= groups.get(groupPosition).getChildrenleg().get(childPosition).hashCode();

        else if(catid==AppConstants.GOVERNMENT)
            longs= groups.get(groupPosition).getChildrengov().get(childPosition).hashCode();
        return longs;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
//        final String children = (String) getChild(groupPosition, childPosition);
        final String name;
        TextView text = null;
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.listrow_details, null);
        }
        linearLayout=(LinearLayout)v.findViewById(R.id.row_view);
        switch (catid) {
            case AppConstants.EDUCATION:
                final EducationNewItem det = groups.get(groupPosition).getchildren().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(det.getNameen());
                linearLayout.setBackgroundResource(R.color.education_color);


                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent ii = new Intent(getActivity(), DetailsLayoutEducation.class);
                        ii.putExtra(AppConstants.KEY_DETAILS_EDU, det);
                        activity.startActivity(ii);


                    }


                });
                break;
            case AppConstants.ENTERTAINMENT:
                final EntertainmentServiceProviderItemNew detent = groups.get(groupPosition).getChildrenent().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detent.getNodeName());
                linearLayout.setBackgroundResource(R.color.entertainment_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iient = new Intent(getActivity(), DetailsInfoActivityEntertainmentNew.class);
                        iient.putExtra(AppConstants.KEY_DETAILS_ENT, detent);
                        activity.startActivity(iient);


                    }
                });
                break;
            case AppConstants.HEALTH:
                final HealthServiceProviderItemNew dethea = groups.get(groupPosition).getChildrenhea().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(dethea.getNode_name());
                linearLayout.setBackgroundResource(R.color.health_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iihea = new Intent(getActivity(), DetailsInfoActivityHealthNew.class);
                        iihea.putExtra(AppConstants.KEY_DETAILS_HEALTH_NEW, dethea);
                        activity.startActivity(iihea);


                    }


                });
                break;
            case AppConstants.FINANCIAL:
                final FinancialNewItem detfin = groups.get(groupPosition).getChildrenfin().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detfin.getNameen());
                linearLayout.setBackgroundResource(R.color.financial_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iifin = new Intent(getActivity(), DetailsLayoutFinance.class);
                        iifin.putExtra(AppConstants.KEY_DETAILS_FINANCIALNEW, detfin);
                        activity.startActivity(iifin);

                    }
                });
                break;
            case AppConstants.LEGAL:
                final LegalAidServiceProviderItemNew detleg = groups.get(groupPosition).getChildrenleg().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detleg.getLegalaidNameEng());
                linearLayout.setBackgroundResource(R.color.legal_aid_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iileg = new Intent(getActivity(), DetailsInfoActivityLegalNew.class);
                        iileg.putExtra(AppConstants.KEY_DETAILS_LEGAL, detleg);
                        activity.startActivity(iileg);


                    }


                });
                break;


            case AppConstants.GOVERNMENT:
                final GovernmentNewItem detgov = groups.get(groupPosition).getChildrengov().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detgov.getNameen());
                linearLayout.setBackgroundResource(R.color.government_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iijob = new Intent(getActivity(), DetailsLayoutGovernment.class);
                        iijob.putExtra(AppConstants.KEY_DETAILS_GOV, detgov);
                        activity.startActivity(iijob);

                    }
                });
                break;
            default:break;
        }

        return v;
    }




    @Override
    public int getChildrenCount(int groupPosition) {
        if (catid==AppConstants.EDUCATION)
            size= groups.get(groupPosition).children.size();
        else if(catid==AppConstants.ENTERTAINMENT)
            size= groups.get(groupPosition).childrenent.size();
        else if (catid==AppConstants.HEALTH)
            size= groups.get(groupPosition).childrenhea.size();
        else if(catid==AppConstants.FINANCIAL)
            size= groups.get(groupPosition).childrenfin.size();
        else  if (catid==AppConstants.LEGAL)
            size= groups.get(groupPosition).childrenleg.size();

        else if(catid==AppConstants.GOVERNMENT)
            size= groups.get(groupPosition).childrengov.size();
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_groups, null);
        }
        Group group = (Group) getGroup(groupPosition);
        Log.d(">>>>>>","Group Value "+catid);

        if(catid==1)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.education_color);
        else if(catid==2)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.health_color);
        else if(catid==3)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.entertainment_color);
        else if(catid==5)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.legal_aid_color);
        else if(catid==6)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.financial_color);
        else if(catid==4)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.government_color);


        //










            ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public Activity getActivity() {
        return activity;
    }
}