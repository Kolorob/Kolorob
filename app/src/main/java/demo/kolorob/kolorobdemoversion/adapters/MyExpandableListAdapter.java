package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by mity on 1/17/16.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DetailsFinancialActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainment;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealth;
import demo.kolorob.kolorobdemoversion.activity.DetailsJobActivity;
import demo.kolorob.kolorobdemoversion.activity.DetailsLegalActivity;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private final Vector<Group> groups;
    private Object object;
    private long longs;
    private int size;


    public LayoutInflater inflater;
    public Activity activity;
    private int catid;
    private Context ctx;

    public MyExpandableListAdapter(Activity act, Vector<Group> groups,int categoryid) {
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
        if (catid==AppConstants.HEALTH)
            object= groups.get(groupPosition).getChildrenhea().get(childPosition);
        else if(catid==AppConstants.FINANCIAL)
            object= groups.get(groupPosition).getChildrenfin().get(childPosition);
        if (catid==AppConstants.LEGAL)
            object= groups.get(groupPosition).getChildrenleg().get(childPosition);
        else if(catid==AppConstants.JOB)
            object= groups.get(groupPosition).getChildrenjob().get(childPosition);
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
        else if(catid==AppConstants.JOB)
            longs= groups.get(groupPosition).getChildrenjob().get(childPosition).hashCode();
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
        switch (catid) {
            case AppConstants.EDUCATION:
            final EducationServiceProviderItem det = groups.get(groupPosition).getchildren().get(childPosition);
            text = (TextView) v.findViewById(R.id.textView1);
            text.setText(det.getEduNameEng());
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent ii = new Intent(getActivity(), DetailsInfoActivity.class);
                    ii.putExtra(AppConstants.KEY_DETAILS_VIEW, det);
                    activity.startActivity(ii);


                }


            });
        break;
            case AppConstants.ENTERTAINMENT:
                final EntertainmentServiceProviderItem detent = groups.get(groupPosition).getChildrenent().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detent.getNodeName());
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iient = new Intent(getActivity(), DetailsInfoActivityEntertainment.class);
                        iient.putExtra(AppConstants.KEY_DETAILS_ENT, detent);
                        activity.startActivity(iient);


                    }
                });
                break;
            case AppConstants.HEALTH:
                final HealthServiceProviderItem dethea = groups.get(groupPosition).getChildrenhea().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(dethea.getNodeName());
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iihea = new Intent(getActivity(), DetailsInfoActivityHealth.class);
                        iihea.putExtra(AppConstants.KEY_DETAILS_HEALTH, dethea);
                        activity.startActivity(iihea);


                    }


                });
                break;
            case AppConstants.FINANCIAL:
                final FinancialServiceProviderItem detfin = groups.get(groupPosition).getChildrenfin().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detfin.getNodeName());
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iifin = new Intent(getActivity(), DetailsFinancialActivity.class);
                        iifin.putExtra(AppConstants.KEY_DETAILS_FINANCIAL, detfin);
                        activity.startActivity(iifin);

                    }
                });
                break;
            case AppConstants.LEGAL:
                final LegalAidServiceProviderItem detleg = groups.get(groupPosition).getChildrenleg().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detleg.getLegalaidNameEng());
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iileg = new Intent(getActivity(), DetailsLegalActivity.class);
                        iileg.putExtra(AppConstants.KEY_DETAILS_LEGAL, detleg);
                        activity.startActivity(iileg);


                    }


                });
                break;
            case AppConstants.JOB:
                final JobServiceProviderItem detjob = groups.get(groupPosition).getChildrenjob().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detjob.getAddress());
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iijob = new Intent(getActivity(), DetailsJobActivity.class);
                        iijob.putExtra(AppConstants.KEY_DETAILS_JOB, detjob);
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
        if (catid==AppConstants.HEALTH)
            size= groups.get(groupPosition).childrenhea.size();
        else if(catid==AppConstants.FINANCIAL)
            size= groups.get(groupPosition).childrenfin.size();
        if (catid==AppConstants.LEGAL)
            size= groups.get(groupPosition).childrenleg.size();
        else if(catid==AppConstants.JOB)
            size= groups.get(groupPosition).childrenjob.size();
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