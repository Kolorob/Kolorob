package demo.kolorob.kolorobdemoversion.adapters;


/**
 * Created by arafat on 19 September 2016.
 */
import java.util.HashMap;
import java.util.List;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;

public class BazarToolAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;
    private int width;
    private int height;

    public BazarToolAdapter(Context context, List<String> listDataHeader,
                                   HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
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
            convertView = infalInflater.inflate(R.layout.bazar_child_view, null);
        }

        DisplayMetrics displayMetrics = _context.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        final String[] Children = childText.split("@");
        LinearLayout job_item = (LinearLayout)convertView.findViewById(R.id.job_item);
        TextView desctiption = (TextView) convertView.findViewById(R.id.description);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView condition = (TextView) convertView.findViewById(R.id.condition);
        TextView area = (TextView) convertView.findViewById(R.id.area);
        ImageView phone_call=(ImageView)convertView.findViewById(R.id.phone_call);
        ImageView email = (ImageView)convertView.findViewById(R.id.email);


        phone_call.getLayoutParams().height=width/11;
        phone_call.getLayoutParams().width=width/11;
        email.getLayoutParams().height=width/11;
        email.getLayoutParams().width=width/11;
        desctiption.setText(Children[0]);
        price.setText(Children[1]);
        type.setText(Children[2]);
        condition.setText(Children[3]);
        area.setText(Children[4]);

        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!Children[5].equals("")) {
                    callIntent1.setData(Uri.parse("tel:" + Children[5]));
                    if (checkPermission(_context))
                        _context.startActivity(callIntent1);
                    else {
                        AlertMessage.showMessage(_context, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                                "ফোন নম্বর পাওয়া যায়নি");
                    }
                } else {

                    AlertMessage.showMessage(_context, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                            "ফোন নম্বর পাওয়া যায়নি");
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertMessage.showMessage(_context, "ই মেইল করা সম্ভব হচ্ছে না",
                        "ই মেইল আই ডি পাওয়া যায়নি");
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
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
            convertView = infalInflater.inflate(R.layout.job_parent_view, null);
        }

        LinearLayout parent_view=(LinearLayout)convertView.findViewById(R.id.parent_view);

        String[] groupElement= headerTitle.split("@");

        TextView job_company = (TextView) convertView
                .findViewById(R.id.job_company);
        TextView job_position = (TextView) convertView
                .findViewById(R.id.job_position);
        TextView job_salary = (TextView) convertView
                .findViewById(R.id.job_salary);
        job_company.setTypeface(null, Typeface.BOLD);


        if(groupPosition%2==0)
        {
            parent_view.setBackgroundColor(ContextCompat.getColor(_context,R.color.white));
            job_company.setTextColor(ContextCompat.getColor(_context,R.color.job_portal));
            job_position.setTextColor(ContextCompat.getColor(_context,R.color.job_portal));
            job_salary.setTextColor(ContextCompat.getColor(_context,R.color.job_portal));


        }

        else
        {
            parent_view.setBackgroundColor(ContextCompat.getColor(_context,R.color.job_portal));
            job_company.setTextColor(ContextCompat.getColor(_context,R.color.white));
            job_position.setTextColor(ContextCompat.getColor(_context,R.color.white));
            job_salary.setTextColor(ContextCompat.getColor(_context,R.color.white));

        }
        job_company.setText(groupElement[0]);
        job_position.setText(groupElement[1]);
        job_salary.setText(groupElement[2]);

        return convertView;
    }

    private boolean checkPermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
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
