package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazharul.Islam1 on 9/19/2016.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

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
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(groupPosition)
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
        final String[] split= childText.split("#");
        final String longLat= split[2];

        TextView phone = (TextView) convertView
                .findViewById(R.id.phone);
        phone.setText(split[0]);
        TextView address = (TextView) convertView
                .findViewById(R.id.address);

        ImageView email = (ImageView)convertView.findViewById(R.id.right_side_email);
        address.setText(split[1]);

        ImageView distance_left=(ImageView)convertView.findViewById(R.id.distance_left);
        ImageView phone_call =(ImageView)convertView.findViewById(R.id.phone_call);

        width= AppUtils.getScreenWidth(_context)/10;

        phone_call.getLayoutParams().height=width;
        phone_call.getLayoutParams().width=width;



        distance_left.getLayoutParams().height = width;
        distance_left.getLayoutParams().width = width;

        email.getLayoutParams().height =  width;
        email.getLayoutParams().width =  width;



        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!split[3].equals(""))
                {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{split[3]});
                    i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                    i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                    try {
                        _context.startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        ToastMessageDisplay.setText(_context,"দুঃখিত! ইমেইল করা যাচ্ছে না");
                        ToastMessageDisplay.showText(_context);

                    }
                }

                else {
                    AlertMessage.showMessage(_context,"দুঃখিত!","ইমেইল করা যাচ্ছে না");
                }

            }
        });

        distance_left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(longLat.equals("not found"))
                {
                    AlertMessage.showMessage(_context, " দুঃখিত!",
                            "পথ পাওয়া যায় নি ");
                }
                else {
                    String [] splitStr;
                    splitStr = longLat.split(" ");
                    SharedPreferences pref = _context.getSharedPreferences("MyPref", _context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Latitude", splitStr[0]);
                    editor.putString("Longitude", splitStr[1]);
                    editor.putString("Name", split[0]);
                    editor.apply();
                    Intent intentJ = new Intent(_context, MapFragmentRouteOSM.class);
                    _context.startActivity(intentJ);
                }
                //  PresentActivity.this.startActivity(activityChangeIntent);
            }
        });



        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!split[0].equals("")) {

                    callIntent1.setData(Uri.parse("tel:" + split[0]));
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





       // txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
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
