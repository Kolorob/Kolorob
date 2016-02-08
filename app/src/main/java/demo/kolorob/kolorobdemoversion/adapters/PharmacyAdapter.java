package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazharul.Islam1 on 2/8/2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

public class PharmacyAdapter extends ArrayAdapter<String> {

    private final Activity context;

    private final String[] doc_id;
    private final String[] doc_name;
    private final String[] doc_fee;
    private final String[] pharmacyTime;
    private final String[] phar_deg;
    private final String[] phar_lmaf;
    private final String[] phar_mbbs;
    private final String[] phar_speciallist;
    private final String[] phar_remarks;



    public PharmacyAdapter(Activity context, String[] doc_id,String[] doc_name, String[] doc_fee, String[] pharmacyTime, String[] phar_deg, String[] phar_lmaf, String[] phar_mbbs,String[] phar_speciallist,String[] phar_remarks) {
        super(context, R.layout.listview_phermacy, doc_id);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.doc_id=doc_id;
        this.doc_name=doc_name;
        this.doc_fee=doc_fee;
        this.pharmacyTime=pharmacyTime;
        this.phar_deg=phar_deg;
        this.phar_lmaf=phar_lmaf;
        this.phar_mbbs=phar_mbbs;
        this.phar_speciallist=phar_speciallist;
        this.phar_remarks=phar_remarks;


    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_phermacy, null, true);

        TextView docId = (TextView) rowView.findViewById(R.id.doc_id);
        TextView docName = (TextView) rowView.findViewById(R.id.doc_name);
        TextView docfee = (TextView) rowView.findViewById(R.id.doc_fee);
        TextView pharmacy_time = (TextView) rowView.findViewById(R.id.pharmacy_time);
        TextView pharmacy_degree = (TextView) rowView.findViewById(R.id.phar_deg);
        TextView pharmacy_lmaf = (TextView) rowView.findViewById(R.id.phar_lmaf);
        TextView pharmacy_mbbs = (TextView) rowView.findViewById(R.id.phar_mbbs);
        TextView pharmacy_specialist = (TextView) rowView.findViewById(R.id.phar_speciallist);
        TextView pharmacy_remarks = (TextView) rowView.findViewById(R.id.phar_remarks);

        //   TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        docId.setText(doc_id[position]);
        docName.setText(doc_name[position]);
        docfee.setText(doc_fee[position]);
        pharmacy_time.setText(pharmacyTime[position]);
        pharmacy_degree.setText(phar_deg[position]);
        pharmacy_lmaf.setText(phar_lmaf[position]);
        pharmacy_mbbs.setText(phar_mbbs[position]);
        pharmacy_specialist.setText(phar_speciallist[position]);
        pharmacy_remarks.setText(phar_remarks[position]);


        //    extratxt.setText("Description "+itemname[position]);
        return rowView;

    };
}

