package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazhaul Islam on 9/2/2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

public class EntertainmentFieldAdapter extends BaseAdapter
{
    Activity context;
    String event_cost[];
    String playground_cost[];
    String remark[];
    String event_cost_ffp[];
    String event_cost_foc[];
    String playground_cost_ffp[];
    String playground_cost_foc[];




    public EntertainmentFieldAdapter(Activity context, String[] event_cost,String[] playground_cost,String[] remark,String[] event_cost_ffp,String[] event_cost_foc,String[] playground_cost_ffp,String[] playground_cost_foc) {
        super();
        this.context = context;
        this.event_cost = event_cost;
        this.playground_cost = playground_cost;
        this.remark = remark;
        this.event_cost_ffp = event_cost_ffp;
        this.event_cost_foc = event_cost_foc;
        this.playground_cost_ffp = playground_cost_ffp;
        this.playground_cost_foc = playground_cost_foc;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return event_cost.length;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView e_cost ;
        TextView p_cost ;
        TextView remarks;
        TextView e_cost_ffp ;
        TextView e_cost_foc;
        TextView p_cost_ffp ;
        TextView p_cost_foc;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_entertainment_field, null);
            holder = new ViewHolder();
            holder.e_cost = (TextView) convertView.findViewById(R.id.event_cost);
            holder.p_cost = (TextView) convertView.findViewById(R.id.playground_cost);
            holder.remarks = (TextView) convertView.findViewById(R.id.remarks);
            holder.e_cost_ffp = (TextView) convertView.findViewById(R.id.e_cost_ffp);
            holder.e_cost_foc = (TextView) convertView.findViewById(R.id.e_cost_foc);
            holder.p_cost_ffp = (TextView) convertView.findViewById(R.id.p_c_ffp);
            holder.p_cost_foc = (TextView) convertView.findViewById(R.id.p_cost_foc);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.e_cost.setText("ভ্যাকসিন নাম : " +event_cost[position]);
        holder.p_cost.setText("ভ্যাকসিন ফি : "+playground_cost[position]);
        holder.remarks.setText("ভ্যাকসিন নাম : " +remark[position]);
        holder.e_cost_ffp.setText("ভ্যাকসিন ফি : "+event_cost_ffp[position]);
        holder.e_cost_foc.setText("ভ্যাকসিন নাম : " +event_cost_foc[position]);
        holder.p_cost_ffp.setText("ভ্যাকসিন ফি : "+playground_cost_ffp[position]);
        holder.p_cost_foc.setText("ভ্যাকসিন নাম : " +playground_cost_foc[position]);

        return convertView;
    }

}

