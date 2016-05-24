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
    String result_concate;




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

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        if(!event_cost[position].equals(""))
            concateBasic("",event_cost[position]);
        if(!playground_cost[position].equals(""))
            concateBasic("",playground_cost[position]);
        if(!remark[position].equals(""))
            concateBasic("",remark[position]);
        if(!event_cost_ffp[position].equals(""))
            concateBasic("",event_cost_ffp[position]);
        if(!event_cost_foc[position].equals(""))
            concateBasic("",event_cost_foc[position]);
        if(!playground_cost_ffp[position].equals(""))
            concateBasic("",playground_cost_ffp[position]);
        if(!playground_cost_foc[position].equals(""))
            concateBasic("",playground_cost_foc[position]);


        concateBasic("","");
        holder.e_cost.setText(result_concate);

        result_concate="";

//        holder.p_cost.setText("মাঠের খরচ: "+playground_cost[position]);
//        holder.remarks.setText("মন্তব্য : " +remark[position]);
//        holder.e_cost_ffp.setText("ইভেন্ট খরচ : "+event_cost_ffp[position]);
//        holder.e_cost_foc.setText("ইভেন্ট খরচ: " +event_cost_foc[position]);
//        holder.p_cost_ffp.setText("মাঠের খরচ : "+playground_cost_ffp[position]);
//        holder.p_cost_foc.setText("মাঠের খরচ : " +playground_cost_foc[position]);

        return convertView;
    }

    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        result_concate= result_concate+value + "\n";




        return result_concate;
    }



}

