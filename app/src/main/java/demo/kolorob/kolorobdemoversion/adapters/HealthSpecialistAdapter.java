package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazharul.Islam1 on 2/9/2016.
 */
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

public class HealthSpecialistAdapter extends BaseAdapter
{
    Activity context;
    String specialist_name[];
    String specialist_fee[];
    String specialist_remarks[];
    String basic_part;


    public HealthSpecialistAdapter(Activity context, String[] specialist_name,String[] specialist_fee,String[] specialist_remarks) {
        super();
        this.context = context;
        this.specialist_name = specialist_name;
        this.specialist_fee = specialist_fee;
        this.specialist_remarks = specialist_remarks;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return specialist_name.length;
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
        TextView s_name ;
        TextView s_fee;
        TextView s_remarks;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_specialist, null);
            holder = new ViewHolder();
            holder.s_name = (TextView) convertView.findViewById(R.id.s_name);
            holder.s_fee = (TextView) convertView.findViewById(R.id.s_fee);
            holder.s_remarks = (TextView) convertView.findViewById(R.id.s_remarks);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }






        if(specialist_name[position].equals(""))
            concateBasic("বিশেষজ্ঞের  ধরন : ",specialist_name[position]);

        if(specialist_fee[position].equals(""))
            concateBasic(" বিশেষজ্ঞের  ফি  : ",specialist_fee[position]);

        if(specialist_remarks[position].equals(""))
            concateBasic("মন্তব্য : ",specialist_remarks[position]);


        holder.s_name.setText(basic_part);
        basic_part="";

        return convertView;
    }



    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        basic_part= basic_part+value + "\n";

        Log.d("....>>>", "Values   " + basic_part);


        return basic_part;
    }
}


