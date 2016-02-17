package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazharul.Islam1 on 2/9/2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

public class HealthVaccineAdapter extends BaseAdapter
{
    Activity context;
    String vaccine_name[];
    String vaccine_fee[];



    public HealthVaccineAdapter(Activity context, String[] vaccine_name,String[] vaccine_fee) {
        super();
        this.context = context;
        this.vaccine_name = vaccine_name;
        this.vaccine_fee = vaccine_fee;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return vaccine_name.length;
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
        TextView VaccineName ;
        TextView VaccineFee;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_vaccine, null);
            holder = new ViewHolder();
            holder.VaccineName = (TextView) convertView.findViewById(R.id.v_name);
            holder.VaccineFee = (TextView) convertView.findViewById(R.id.v_fee);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }





        holder.VaccineName.setText("ভ্যাকসিন নাম : " +vaccine_name[position]);
        holder.VaccineFee.setText("ভ্যাকসিন ফি : "+vaccine_fee[position]);



        return convertView;
    }

}
