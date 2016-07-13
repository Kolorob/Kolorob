package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Arafat 12 July 2016
 */

import android.app.Activity;
import android.util.Log;
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
    String vaccine_remarks[];
    String basic_part;



    public HealthVaccineAdapter(Activity context, String[] vaccine_name,String[] vaccine_fee,String[] vaccine_remarks) {
        super();
        this.context = context;
        this.vaccine_name = vaccine_name;
        this.vaccine_fee = vaccine_fee;
        this.vaccine_remarks=vaccine_remarks;

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
        TextView VaccineRemark;

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
            holder.VaccineRemark=(TextView) convertView.findViewById(R.id.v_remark);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }




        if(!vaccine_name[position].equals(""))
            concateBasic("ভ্যাকসিনের নাম : ",vaccine_name[position]);

        if(!vaccine_fee[position].equals(""))
            concateBasic("ভ্যাকসিনের খরচ : ",vaccine_fee[position]);


    //    holder.VaccineName.setText("ভ্যাকসিন নাম : " +);
      //  holder.VaccineFee.setText(""+vaccine_fee[position]);

        if(!vaccine_remarks[position].equals(""))
            concateBasic("বিস্তারিত: ",vaccine_remarks[position]);
     //
     //
     // holder.VaccineRemark.setText("মন্তব্য: "+vaccine_remarks[position]);
             concateBasic("","");
             holder.VaccineName.setText(basic_part);

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

