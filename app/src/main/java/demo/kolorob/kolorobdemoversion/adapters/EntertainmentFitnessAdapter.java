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
    String result_concate;




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

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        if(!year_of_establishment[position].equals(""))
            concateBasic("প্রতিষ্ঠার সময় : ",year_of_establishment[position]);
        if(!num_workers[position].equals(""))
            concateBasic("কর্মীর সংখ্যা: ",num_workers[position]);
        if(!offers[position].equals(""))
            concateBasic("অফার: ",offers[position]);
        if(!offer_details[position].equals(""))
            concateBasic("অফার বিস্তারিত : ",offer_details[position]);
        if(!service_type[position].equals(""))
            concateBasic("সেবার ধরন: ",service_type[position]);
        if(!type[position].equals(""))
            concateBasic("ধরন : ",type[position]);
        if(!service_details[position].equals(""))
            concateBasic("সেবার বিবরন: ",service_details[position]);



           concateBasic("","");



        holder.year_of_establishment.setText(result_concate);


        result_concate="";

        return convertView;
    }


    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        result_concate= result_concate+value + "\n";

        return result_concate;
    }


}



