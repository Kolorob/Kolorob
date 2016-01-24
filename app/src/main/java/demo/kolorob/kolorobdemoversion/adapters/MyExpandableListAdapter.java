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
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivity;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private final Vector<Group> groups;

    public LayoutInflater inflater;
    public Activity activity;
    private int catid;
    ArrayList<EducationServiceProviderItem>eduall=null;
    private Context ctx;

    public MyExpandableListAdapter(Activity act, Vector<Group> groups,int categoryid) {
        activity = act;
        this.groups = groups;

        inflater = act.getLayoutInflater();
        this.catid=categoryid;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getchildren().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getchildren().get(childPosition).hashCode();
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
        final EducationServiceProviderItem det = groups.get(groupPosition).getchildren().get(childPosition);
        text = (TextView) v.findViewById(R.id.textView1);
        text.setText(det.getEduNameEng());
        v.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (catid)
                {
                    case AppConstants.EDUCATION:
                       Intent ii = new Intent(getActivity(), DetailsInfoActivity.class);
                       ii.putExtra(AppConstants.KEY_DETAILS_VIEW, det);
                       activity.startActivity(ii);
                        break;

                }

            }
        });



        return v;
    }




    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
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