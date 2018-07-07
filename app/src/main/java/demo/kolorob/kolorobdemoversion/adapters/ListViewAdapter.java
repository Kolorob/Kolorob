package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.WardTable;
import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.Ward;

/**
 * Created by shamima.brishti on 2/13/18.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Area> areaList = null;
    private ArrayList<Area> arraylist;
    private Area area;
    private Ward ward;

    public ListViewAdapter(Context context, List<Area> areaList) {
        mContext = context;
        this.areaList = areaList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(areaList);
    }

    public String getString() {
        return String.format("%s (%s)", area.getArea_bn(), ward.getWard_bn());
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return areaList.size();
    }

    @Override
    public Area getItem(int position) {
        return areaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder areaHolder;
        if (view == null) {
            areaHolder = new ViewHolder();

            view = inflater.inflate(R.layout.list_view_item, null);
            areaHolder.name = (TextView) view.findViewById(R.id.areaName);

            view.setTag(areaHolder);
        } else {
            areaHolder = (ViewHolder) view.getTag();
        }

        area = areaList.get(position);
        ward = new WardTable(mContext).getNodeInfo(area.getWard_id());

        areaHolder.name.setText(String.format("%s (%s)", area.getArea_bn(), ward.getWard_bn()));

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        areaList.clear();
        if (charText.length() == 0) {
            areaList.addAll(arraylist);
        } else {
            for (Area area : arraylist) {
                if (area.getArea_name().toLowerCase(Locale.getDefault()).contains(charText) || area.getArea_bn().toLowerCase(Locale.getDefault()).contains(charText)) {
                    areaList.add(area);
                }
            }
        }
        notifyDataSetChanged();
    }

}