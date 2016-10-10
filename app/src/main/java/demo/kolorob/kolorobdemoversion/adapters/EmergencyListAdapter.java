package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazharul.Islam1 on 9/19/2016.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

public class EmergencyListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, ArrayList<String>> _listDataChild;
    private int width;
    private int height;
    int k=0;

    public EmergencyListAdapter(Context context, List<String> listDataHeader,
                                HashMap<String, ArrayList<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon)
                ;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.emergency_child, null);
        }

        Log.d("childText","======"+childText);
//        Log.d("k","======"+k++);
//        Log.d("groupPosition","======"+groupPosition);
//        Log.d("isLastChild","======"+isLastChild);


        TextView phone = (TextView) convertView
                .findViewById(R.id.phone);
        TextView address = (TextView) convertView
                .findViewById(R.id.address);

        ImageView phone_call =(ImageView)convertView.findViewById(R.id.phone_call);
        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("you Clicked","==="+childText);
            }
        });

       // txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.emergency_parent, null);
        }

        TextView emergency_name = (TextView) convertView
                .findViewById(R.id.emergency_name);
        emergency_name.setTypeface(null, Typeface.BOLD);
        emergency_name.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
