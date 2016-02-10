package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazhaul Islam on 10/2/2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

public class EntertainmentFitnessAdapter extends BaseAdapter
{
    Activity context;

    String year_of_establishment[];
    String num_workers[];
    String offers[];
    String offer_details[];
    String service_type[];
    String type[];
    String service_details[];




    public EntertainmentFitnessAdapter(Activity context, String[] year_of_establishment,String[] num_workers,String[] offers,String[] offer_details,String[] service_type,String[] type,String[] service_details) {
        super();
        this.context = context;

        this.year_of_establishment = year_of_establishment;
        this.num_workers = num_workers;
        this.offers = offers;
        this.offer_details = offer_details;
        this.service_type = service_type;
        this.type = type;
        this.service_details = service_details;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return service_details.length;
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

        TextView year_of_establishment ;
        TextView num_workers;
        TextView offers ;
        TextView offer_details;
        TextView service_type ;
        TextView type;
        TextView service_details;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_fitness, null);
            holder = new ViewHolder();

            holder.year_of_establishment = (TextView) convertView.findViewById(R.id.year_of_establishment);
            holder.num_workers = (TextView) convertView.findViewById(R.id.num_workers);
            holder.offers = (TextView) convertView.findViewById(R.id.offers);
            holder.offer_details = (TextView) convertView.findViewById(R.id.offer_details);
            holder.service_type = (TextView) convertView.findViewById(R.id.service_type);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.service_details = (TextView) convertView.findViewById(R.id.service_details);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.year_of_establishment.setText("ভ্যাকসিন ফি : "+year_of_establishment[position]);
        holder.num_workers.setText("ভ্যাকসিন নাম : " +num_workers[position]);
        holder.offers.setText("ভ্যাকসিন ফি : "+offers[position]);
        holder.offer_details.setText("ভ্যাকসিন নাম : " +offer_details[position]);
        holder.service_type.setText("ভ্যাকসিন ফি : "+service_type[position]);
        holder.type.setText("ভ্যাকসিন নাম : " +type[position]);
        holder.service_details.setText("ভ্যাকসিন নাম : " +service_details[position]);


        return convertView;
    }

}



