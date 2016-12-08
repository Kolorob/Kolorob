package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazharul.Islam1 on 12/8/2016.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

package onmobile.app.test.expandablelistviewwithsearchfilter;

/**
 * Created by Mazharul.Islam1 on 12/8/2016.
 */
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.AreaItem;
import demo.kolorob.kolorobdemoversion.model.WardItem;

public class PlaceListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<WardItem> wardList;
    private ArrayList<WardItem> originalList;

    public PlaceListAdapter(Context context, ArrayList<WardItem> wardlist) {
        this.context = context;
        this.wardList = new ArrayList<WardItem>();
        this.wardList.addAll(wardlist);
        this.originalList = new ArrayList<WardItem>();
        this.originalList.addAll(wardlist);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<AreaItem> countryList = wardList.get(groupPosition).getArealist();
        return countryList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        AreaItem areaItem = (AreaItem) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.area_row, null);
        }


        TextView name = (TextView) view.findViewById(R.id.name);


        name.setText(areaItem.getName().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<AreaItem> arealist = wardList.get(groupPosition).getArealist();
        return arealist.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return wardList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return wardList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        WardItem wardItem = (WardItem) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.ward_row, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(wardItem.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(wardList.size()));
        wardList.clear();

        if(query.isEmpty()){
            wardList.addAll(originalList);
        }
        else {

            for(Continent continent: originalList){

                ArrayList<Country> countryList = continent.getCountryList();
                ArrayList<Country> newList = new ArrayList<Country>();
                for(Country country: countryList){
                    if(country.getCode().toLowerCase().contains(query) ||
                            country.getName().toLowerCase().contains(query)){
                        newList.add(country);
                    }
                }
                if(newList.size() > 0){
                    Continent nContinent = new Continent(continent.getName(),newList);
                    continentList.add(nContinent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        notifyDataSetChanged();

    }

}

