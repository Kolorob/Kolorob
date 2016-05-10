package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazhaul Islam on 10/2/2016.
 */

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by Mazhaul Islam on 10/2/2016.
 */
public class FinancilaLoadAdapter extends BaseAdapter
{
    Activity context;

    String service_name[];
    String yes_no[];
    String costs[];
    String remark[];
    String result_concate;






    public FinancilaLoadAdapter(Activity context, String[] service_name,String[] yes_no,String[] costs,String[] remark) {
        super();
        this.context = context;

        this.service_name = service_name;
        this.yes_no = yes_no;
        this.costs = costs;
        this.remark = remark;



    }

    public int getCount() {
        // TODO Auto-generated method stub
        return service_name.length;
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

        TextView service_name ;
        TextView yes_no;
        TextView costs ;
        TextView remark;



    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_loan, null);
            holder = new ViewHolder();

            holder.service_name = (TextView) convertView.findViewById(R.id.service_name);
            holder.yes_no = (TextView) convertView.findViewById(R.id.yes_no);
            holder.costs = (TextView) convertView.findViewById(R.id.costs);
            holder.remark = (TextView) convertView.findViewById(R.id.remark);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        if(!service_name[position].equals(""))
            concateBasic("সেবার নাম : ",service_name[position]);

        if(!yes_no[position].equals(""))
            concateBasic("সেবা পাওয়া যাবে কিনা? : ",yes_no[position]);

        if(!costs[position].equals(""))
            concateBasic("খরচ: ",costs[position]);

        if(!remark[position].equals(""))
            concateBasic("মন্তব্য: ",remark[position]);
        concateBasic("","");
        holder.service_name.setText(result_concate);



        result_concate="";

        return convertView;
    }

    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        result_concate= result_concate+value + "\n";

        Log.d("....>>>", "Values   " + result_concate);


        return result_concate;
    }


}




