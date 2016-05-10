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

/**
 * Created by Mazhaul Islam on 10/2/2016.
 */
public class LegalAidAdviceAdapter extends BaseAdapter
{
    Activity context;

    String service_name[];
    String legal_aid_free[];
    String legal_aid_cost[];
    String legal_aid_person_authority[];
    String legal_aid_remark[];
    String result_concate;




    public LegalAidAdviceAdapter(Activity context, String[] service_name,String[] legal_aid_free,String[] legal_aid_cost,String[] legal_aid_person_authority,String[] legal_aid_remark) {
        super();
        this.context = context;

        this.service_name = service_name;
        this.legal_aid_free = legal_aid_free;
        this.legal_aid_cost = legal_aid_cost;
        this.legal_aid_person_authority = legal_aid_person_authority;
        this.legal_aid_remark = legal_aid_remark;


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
        TextView legal_aid_free;
        TextView legal_aid_cost ;
        TextView legal_aid_person_authority;
        TextView legal_aid_remark ;


    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_bills, null);
            holder = new ViewHolder();

            holder.service_name = (TextView) convertView.findViewById(R.id.service_name);
            holder.legal_aid_free = (TextView) convertView.findViewById(R.id.yes_no);
            holder.legal_aid_cost = (TextView) convertView.findViewById(R.id.costs);
            holder.legal_aid_person_authority = (TextView) convertView.findViewById(R.id.remark);
            holder.legal_aid_remark = (TextView) convertView.findViewById(R.id.ref_num);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        if(service_name[position].equals(""))
            concateBasic("সেবার নাম : ",service_name[position]);
        if(legal_aid_free[position].equals(""))
            concateBasic("সেবা বিনামূল্যে কিনা : ",legal_aid_free[position]);
        if(!legal_aid_cost[position].equals(""))
            concateBasic("সেবার বিনিময় খরচ: ",legal_aid_cost[position]);

        if(!legal_aid_person_authority[position].equals(""))
            concateBasic("যিনি সেবা দিবেন: ",legal_aid_person_authority[position]);

        if(!legal_aid_remark[position].equals(""))
            concateBasic("",legal_aid_remark[position]);

        concateBasic("","");
        holder.service_name.setText(result_concate);
//        holder.legal_aid_free.setText("সেবা বিনামূল্যে কিনা : " +legal_aid_free[position]);
//        holder.legal_aid_cost.setText("সেবার বিনিময় খরচ: "+legal_aid_cost[position]);
//        holder.legal_aid_person_authority.setText("যিনি সেবা দিবেন: " +legal_aid_person_authority[position]);
//        holder.legal_aid_remark.setText("মন্তব্য : " + legal_aid_remark[position]);



        result_concate="";

        return convertView;
    }


    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        result_concate= result_concate+value + "\n";
        return result_concate;
    }

}



