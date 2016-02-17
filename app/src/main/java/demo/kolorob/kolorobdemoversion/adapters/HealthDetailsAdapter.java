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

public class HealthDetailsAdapter extends BaseAdapter
{
    Activity context;
    String doc_id[];
    String doc_name[];
    String doc_fee[];
    String pharmacyTime[];
    String phar_deg[];
    String phar_lmaf[];
    String phar_mbbs[];
    String phar_speciallist[];
    String phar_remarks[];
 String phar_docremarks[];

    public HealthDetailsAdapter(Activity context, String[] doc_id,String[] doc_name, String[] doc_fee, String[] pharmacyTime, String[] phar_deg, String[] phar_lmaf, String[] phar_mbbs,String[] phar_speciallist,String[] phar_remarks,String[] phar_docremarks) {
        super();
        this.context = context;
        this.doc_id = doc_id;
        this.doc_name = doc_name;
        this.doc_fee = doc_fee;
        this.doc_id = doc_id;
        this.pharmacyTime = pharmacyTime;
        this.phar_deg = phar_deg;
        this.phar_lmaf = phar_lmaf;
        this.phar_mbbs = phar_mbbs;
        this.phar_speciallist = phar_speciallist;
        this.phar_remarks = phar_remarks;
        this.phar_docremarks=phar_docremarks;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return doc_id.length;
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
        TextView docId ;
        TextView docName;
        TextView docfee;
        TextView pharmacy_time;
        TextView pharmacy_degree;
        TextView pharmacy_lmaf;
        TextView pharmacy_mbbs;
        TextView pharmacy_specialist;
        TextView pharmacy_remarks ;
        TextView pharmacy_docremarks ;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_phermacy, null);
            holder = new ViewHolder();
            holder.docId = (TextView) convertView.findViewById(R.id.doc_id);
            holder.docName = (TextView) convertView.findViewById(R.id.doc_name);
            holder.docfee = (TextView) convertView.findViewById(R.id.doc_fee);
            holder.pharmacy_time = (TextView) convertView.findViewById(R.id.pharmacy_time);
            holder.pharmacy_degree = (TextView) convertView.findViewById(R.id.phar_deg);
            holder.pharmacy_lmaf = (TextView) convertView.findViewById(R.id.phar_lmaf);
            holder.pharmacy_mbbs = (TextView) convertView.findViewById(R.id.phar_mbbs);
            holder.pharmacy_specialist = (TextView) convertView.findViewById(R.id.phar_speciallist);
            holder.pharmacy_remarks = (TextView) convertView.findViewById(R.id.phar_remarks);
            holder.pharmacy_docremarks = (TextView) convertView.findViewById(R.id.phar_docremarks);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.docId.setText("ডাক্তারের আইডি: " +doc_id[position]);
        holder.docName.setText("ডাক্তারের নাম: "+doc_name[position]);
        holder.docfee.setText("ডাক্তারের ফি: "+doc_fee[position]);
        holder.pharmacy_time.setText("ফার্মেসি টাইম: "+ pharmacyTime[position]);
        holder.pharmacy_degree.setText("ফার্মেসি ডিগ্রী : "+phar_deg[position]);
        holder.pharmacy_lmaf.setText("ফার্মেসি এল এম এ এফ: "+phar_lmaf[position]);
        holder.pharmacy_mbbs.setText("ফার্মেসি এম বি বি এস : "+phar_mbbs[position]);
        holder.pharmacy_specialist.setText("ফার্মেসি বিশশজ্ঞ: "+phar_speciallist[position]);
        holder.pharmacy_remarks.setText("ফার্মেসি মন্তব্য:  "+phar_remarks[position]);
        holder.pharmacy_docremarks.setText(":ডাক্তারের বিবরণঃ  "+phar_docremarks[position]);

        return convertView;
    }

}

