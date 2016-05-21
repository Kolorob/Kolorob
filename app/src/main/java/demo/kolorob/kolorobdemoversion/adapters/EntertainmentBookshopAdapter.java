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

public class EntertainmentBookshopAdapter extends BaseAdapter
{
    Activity context;
    String borrow_cost[];
    String lending_allowed[];
    String membership_cost[];
    String offers[];
    String offer_details[];
    String membership_cost_ffp[];
    String membership_cost_foc[];
    String result_concate;




    public EntertainmentBookshopAdapter(Activity context, String[] borrow_cost,String[] lending_allowed,String[] membership_cost,String[] offers,String[] offer_details,String[] membership_cost_ffp,String[] membership_cost_foc) {
        super();
        this.context = context;
        this.borrow_cost = borrow_cost;
        this.lending_allowed = lending_allowed;
        this.membership_cost = membership_cost;
        this.offers = offers;
        this.offer_details = offer_details;
        this.membership_cost_ffp = membership_cost_ffp;
        this.membership_cost_foc = membership_cost_foc;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return borrow_cost.length;
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
        TextView bor_cost ;
        TextView lending_allowed ;
        TextView mem_cost;
        TextView offers ;
        TextView offer_details;
        TextView mem_cost_ffp ;
        TextView mem_cost_foc;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_bookshop, null);
            holder = new ViewHolder();
            holder.bor_cost = (TextView) convertView.findViewById(R.id.b_cost);
            holder.lending_allowed = (TextView) convertView.findViewById(R.id.l_allowed);
            holder.mem_cost = (TextView) convertView.findViewById(R.id.mem_cost);
            holder.offers = (TextView) convertView.findViewById(R.id.offer);
            holder.offer_details = (TextView) convertView.findViewById(R.id.o_details);
            holder.mem_cost_ffp = (TextView) convertView.findViewById(R.id.memb_cost);
            holder.mem_cost_foc = (TextView) convertView.findViewById(R.id.mem_cost_foc);



            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }





        if(!borrow_cost[position].equals(""))
            concateBasic("ধার নেয়ার খরচ: ",borrow_cost[position]);
        if(!lending_allowed[position].equals(""))
            concateBasic("ধার নেয়া যাবে কিনা: ",lending_allowed[position]);
        if(!membership_cost[position].equals(""))
            concateBasic("সদস্য খরচ : ",membership_cost[position]);
        if(!offers[position].equals(""))
            concateBasic("অফার: ",offers[position]);
        if(!offer_details[position].equals(""))
            concateBasic("অফার বিস্তারিত: ",offer_details[position]);
        if(!membership_cost_ffp[position].equals(""))
            concateBasic("সদস্য ফী: ",membership_cost_ffp[position]);
        if(!membership_cost_foc[position].equals(""))
            concateBasic("সদস্য ফী : ",membership_cost_foc[position]);


        concateBasic("","");
        holder.bor_cost.setText(result_concate);

        result_concate="";
//        holder.lending_allowed.setText("ধার নেয়া যাবে কিনা: "+lending_allowed[position]);
//        holder.mem_cost.setText("সদস্য খরচ : " +membership_cost[position]);
//        holder.offers.setText("অফার: "+offers[position]);
//        holder.offer_details.setText("অফার বিস্তারিত: " +offer_details[position]);
//        holder.mem_cost_ffp.setText("সদস্য ফী: "+membership_cost_ffp[position]);
//        holder.mem_cost_foc.setText("সদস্য ফী : " +membership_cost_foc[position]);

        return convertView;
    }

    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        result_concate= result_concate+value + "\n";




        return result_concate;
    }

}


