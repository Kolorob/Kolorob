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

public class EducationCourseFee extends BaseAdapter
{
    Activity context;
    String pre_school_free[];
    String pre_school_stipend_speciality[];

    String pre_school_stipend_type[];
    String pre_school_stipend_details[];
    String pre_school_max_fee[];
    String pre_school_min_fee[];

    String pre_school_coaching_fee[];
    String pre_school_additional_fee[];
    String i_v_free[];
    String i_v_stipend_speciality[];

    String i_v_stipend_type[];
    String i_v_stipend_details[];
    String i_v_max_fee[];
    String i_v_min_fee[];
    String i_v_additional_fee[];


    String i_v_coaching_fee[];


    String vi_x_free[];
    String vi_x_stipend_speciality[];
    String vi_x_stipend_type[];
    String vi_x_stipend_details[];
    String vi_x_max_fee[];
    String vi_x_min_fee[];
    String vi_x_coaching_fee[];
    String vi_x_additional_fee[];

    String xi_xii_free[];
    String xi_xii_stipend_speciality[];
    String xi_xii_stipend_type[];
    String xi_xii_stipend_details[];
    String xi_xii_max_fee[];
    String xi_xii_min_fee[];
    String xi_xii_coaching_fee[];
    String xi_xii_additional_fee[];


    String uni_free[];
    String uni_stipend_speciality[];
    String uni_stipend_details[];
    String uni_stipend_type[];
    String uni_max_fee[];
    String uni_min_fee[];
    String uni_coaching_fee[];
    String uni_additional_fee[];





    public EducationCourseFee(Activity context, String[] pre_school_free,String[] pre_school_stipend_speciality,String[] pre_school_stipend_type,String[] pre_school_stipend_details,
                                  String[] pre_school_max_fee, String[] pre_school_min_fee,String[] pre_school_coaching_fee,String[] pre_school_additional_fee,
                                  String[] i_v_free,String[] i_v_stipend_speciality,String[] i_v_stipend_type,String[] i_v_stipend_details,String[] i_v_max_fee,
                                  String[] i_v_min_fee,String[] i_v_coaching_fee,String[] i_v_additional_fee,String[] vi_x_free,String[] vi_x_stipend_speciality,
                                  String[] vi_x_stipend_type,String[] vi_x_stipend_details ,String[] vi_x_max_fee,String[] vi_x_min_fee,String[] vi_x_coaching_fee,
                                  String[] vi_x_additional_fee,String[]xi_xii_free,String[] xi_xii_stipend_speciality,String[] xi_xii_stipend_type,String[] xi_xii_stipend_details,
                                  String[] xi_xii_max_fee,String[] xi_xii_min_fee,String[] xi_xii_coaching_fee,String[] xi_xii_additional_fee,String[] uni_free,String[]uni_stipend_speciality,
                                  String[] uni_stipend_type,String[] uni_stipend_details,String[] uni_max_fee,String[] uni_min_fee,String[] uni_coaching_fee,String[] uni_additional_fee
    ) {
        super();
        this.context = context;
        this.pre_school_free = pre_school_free;
        this.pre_school_stipend_speciality = pre_school_stipend_speciality;
        this.pre_school_stipend_type = pre_school_stipend_type;
        this.pre_school_stipend_details = pre_school_stipend_details;
        this.pre_school_max_fee = pre_school_max_fee;
        this.pre_school_min_fee = pre_school_min_fee;
        this.pre_school_coaching_fee = pre_school_coaching_fee;
        this.pre_school_additional_fee = pre_school_additional_fee;

        this.i_v_free = i_v_free;
        this.i_v_coaching_fee = i_v_coaching_fee;
        this.i_v_stipend_speciality = i_v_stipend_speciality;
        this.i_v_stipend_type = i_v_stipend_type;
        this.i_v_stipend_details = i_v_stipend_details;
        this.i_v_max_fee = i_v_max_fee;
        this.i_v_min_fee = i_v_min_fee;
        this.i_v_additional_fee = i_v_additional_fee;

        this.xi_xii_free = xi_xii_free;
        this.xi_xii_stipend_speciality = xi_xii_stipend_speciality;
        this.xi_xii_stipend_type = xi_xii_stipend_type;
        this.xi_xii_stipend_details = xi_xii_stipend_details;
        this.xi_xii_max_fee = xi_xii_max_fee;
        this.xi_xii_min_fee = xi_xii_min_fee;
        this.xi_xii_coaching_fee = xi_xii_coaching_fee;
        this.xi_xii_additional_fee = xi_xii_additional_fee;

        this.vi_x_free = vi_x_free;
        this.vi_x_stipend_speciality = vi_x_stipend_speciality;
        this.vi_x_stipend_type = vi_x_stipend_type;
        this.vi_x_stipend_details = vi_x_stipend_details;
        this.vi_x_max_fee = vi_x_max_fee;
        this.vi_x_min_fee = vi_x_min_fee;
        this.vi_x_coaching_fee = vi_x_coaching_fee;
        this.vi_x_additional_fee = vi_x_additional_fee;

        this.uni_free = uni_free;
        this.uni_stipend_type = uni_stipend_type;
        this.uni_stipend_details = uni_stipend_details;
        this.uni_stipend_speciality = uni_stipend_speciality;

        this.uni_max_fee = uni_max_fee;
        this.uni_min_fee = uni_min_fee;
        this.uni_coaching_fee = uni_coaching_fee;
        this.uni_additional_fee = uni_additional_fee;


    }

    public int getCount() {
        // TODO Auto-generated method stub
        return pre_school_stipend_speciality.length;
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
        TextView a ;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f ;
        TextView g;
        TextView h;
        TextView i;
        TextView j;
        TextView k ;
        TextView l;
        TextView m;
        TextView n;
        TextView o;
        TextView p ;
        TextView q;
        TextView r;
        TextView s;
        TextView t;
        TextView u ;
        TextView v;
        TextView w;
        TextView x;
        TextView y;

        TextView z ;
        TextView aa;
        TextView ab;
        TextView ac;
        TextView ad;
        TextView ae ;
        TextView af;
        TextView ag;
        TextView ah;
        TextView ai;
        TextView aj ;
        TextView ak;
        TextView al;
        TextView am;
        TextView an;



    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.educationcoursefee, null);
            holder = new ViewHolder();
            holder.a = (TextView) convertView.findViewById(R.id.a);
            holder.b = (TextView) convertView.findViewById(R.id.b);
            holder.c = (TextView) convertView.findViewById(R.id.c);
            holder.d = (TextView) convertView.findViewById(R.id.d);
            holder.e = (TextView) convertView.findViewById(R.id.e);

            holder.f = (TextView) convertView.findViewById(R.id.f);
            holder.g = (TextView) convertView.findViewById(R.id.g);
            holder.h = (TextView) convertView.findViewById(R.id.h);
            holder.i = (TextView) convertView.findViewById(R.id.i);
            holder.j =(TextView) convertView.findViewById(R.id.j);

            holder.k = (TextView) convertView.findViewById(R.id.k);
            holder.l = (TextView) convertView.findViewById(R.id.l);
            holder.m = (TextView) convertView.findViewById(R.id.m);
            holder.n = (TextView) convertView.findViewById(R.id.n);
            holder.o = (TextView) convertView.findViewById(R.id.o);

            holder.p = (TextView) convertView.findViewById(R.id.p);
            holder.q = (TextView) convertView.findViewById(R.id.q);
            holder.r = (TextView) convertView.findViewById(R.id.r);
            holder.s = (TextView) convertView.findViewById(R.id.s);
            holder.t = (TextView) convertView.findViewById(R.id.t);

            holder.u = (TextView) convertView.findViewById(R.id.u);
            holder.v = (TextView) convertView.findViewById(R.id.v);
            holder.w = (TextView) convertView.findViewById(R.id.w);
            holder.x = (TextView) convertView.findViewById(R.id.x);
            holder.y = (TextView) convertView.findViewById(R.id.y);

            holder.z = (TextView) convertView.findViewById(R.id.z);
            holder.aa = (TextView) convertView.findViewById(R.id.aa);
            holder.ab = (TextView) convertView.findViewById(R.id.ab);
            holder.ac = (TextView) convertView.findViewById(R.id.ac);
            holder.ad = (TextView) convertView.findViewById(R.id.ad);

            holder.ae = (TextView) convertView.findViewById(R.id.ae);
            holder.af = (TextView) convertView.findViewById(R.id.af);
            holder.ag = (TextView) convertView.findViewById(R.id.ag);
            holder.ah = (TextView) convertView.findViewById(R.id.ah);
            holder.ai = (TextView) convertView.findViewById(R.id.ai);

            holder.aj = (TextView) convertView.findViewById(R.id.aj);
            holder.ak = (TextView) convertView.findViewById(R.id.ak);
            holder.al = (TextView) convertView.findViewById(R.id.al);
            holder.am = (TextView) convertView.findViewById(R.id.am);
            holder.an = (TextView) convertView.findViewById(R.id.an);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }





        if(!pre_school_free[position].equals(""))
        {
            holder.a.setVisibility(View.VISIBLE);
            holder.a.setText("প্রি স্কুল ফী : " +pre_school_free[position]);
        }

        if(!pre_school_stipend_speciality[position].equals(""))
        {

            holder.b.setText("প্রি স্কুল বৃত্তি বিশেষত্বঃ "+pre_school_stipend_speciality[position]);
        }
        if(!pre_school_stipend_details[position].equals(""))
        {
            holder.c.setText("প্রি স্কুল স্কুল বৃত্তি টাইপঃ "+pre_school_stipend_details[position]);
        }

        if(!pre_school_max_fee[position].equals(""))
        {
            holder.d.setText("প্রি স্কুল বৃত্তি বৃত্তি বিস্তারিতঃ "+pre_school_max_fee[position]+ " টাকা ");
        }

        if(!pre_school_min_fee[position].equals(""))
        {
            holder.e.setText("প্রি স্কুল সর্বোচ্চ ফিঃ "+pre_school_min_fee[position]);
        }

        if(!pre_school_stipend_type[position].equals(""))
        {
            holder.f.setText("প্রি স্কুল সর্বনিম্ন ফিঃ "+pre_school_stipend_type[position]+ " টাকা ");
        }

        if(!pre_school_coaching_fee[position].equals(""))
        {
            holder.g.setText("প্রি স্কুল কোচিং ফিঃ " +pre_school_coaching_fee[position]);
        }

        if(!pre_school_additional_fee[position].equals(""))
        {
            holder.h.setText("প্রি স্কুল অতিরিক্ত ফিঃ "+pre_school_additional_fee[position]);
        }



        if(!i_v_free[position].equals(""))
        {
            holder.i.setText("চতুর্থ পঞ্চম শ্রেনীর ফিঃ "+i_v_free[position]);
        }

        if(!i_v_stipend_speciality[position].equals(""))
        {
            holder.j.setText("চতুর্থ পঞ্চম শ্রেনীর বৃত্তি বিশেষত্ব "+i_v_stipend_speciality[position]);
        }

        if(!i_v_stipend_type[position].equals(""))
        {
            holder.k.setText("চতুর্থ পঞ্চম শ্রেনীর বৃত্তি টাইপঃ  " +i_v_stipend_type[position]);
        }

        if(!i_v_stipend_details[position].equals(""))
        {
            holder.l.setText("চতুর্থ পঞ্চম শ্রেনীর বৃত্তি বিস্তারিতঃ  "+i_v_stipend_details[position]);
        }

        if(!i_v_max_fee[position].equals(""))
        {
            holder.m.setText("চতুর্থ পঞ্চম শ্রেনীর সর্বোচ্চ ফিঃ  "+i_v_max_fee[position]);
        }


        if(!i_v_min_fee[position].equals(""))
        {
            holder.n.setText("চতুর্থ পঞ্চম শ্রেনীর সর্বনিম্ন ফিঃ  "+i_v_min_fee[position]+ " টাকা ");
        }

        if(!i_v_additional_fee[position].equals(""))
        {
            holder.o.setText("চতুর্থ পঞ্চম শ্রেনীর অতিরিক্ত ফিঃ "+i_v_additional_fee[position]);
        }

        if(!i_v_coaching_fee[position].equals(""))
        {
            holder.p.setText("চতুর্থ পঞ্চম শ্রেনীর কোচিং ফিঃ" +i_v_coaching_fee[position]);
        }


        if(vi_x_free[position].equals(""))
        {
            holder.ag.setText("ইউনিভার্সিটির ফিঃ "+vi_x_free[position]);
        }

        if(vi_x_stipend_speciality[position].equals(""))
        {
            holder.ah.setText("ইউনিভার্সিটির বৃত্তি বিশেষত্ব "+vi_x_stipend_speciality[position]);
        }

        if(vi_x_stipend_type[position].equals(""))
        {
            holder.ai.setText("ইউনিভার্সিটির বৃত্তি টাইপঃ "+vi_x_stipend_type[position]+ " টাকা ");
        }


        if(!vi_x_stipend_details[position].equals(""))
        {
            holder.aj.setText("ইউনিভার্সিটির বৃত্তি বিস্তারিতঃ "+vi_x_stipend_details[position]);
        }

        if(!vi_x_max_fee[position].equals(""))
        {
            holder.ak.setText("ইউনিভার্সিটির সর্বোচ্চ ফিঃ " +vi_x_max_fee[position]);
        }

        if(!vi_x_min_fee[position].equals(""))
        {
            holder.al.setText("ইউনিভার্সিটির সর্বনিম্ন ফিঃ "+vi_x_min_fee[position]);
        }

        if(!vi_x_coaching_fee[position].equals(""))
        {
            holder.am.setText("ইউনিভার্সিটির কোচিং ফিঃ "+vi_x_coaching_fee[position]);
        }

        if(!vi_x_additional_fee[position].equals(""))
        {
            holder.an.setText("ইউনিভার্সিটির অতিরিক্ত ফিঃ "+vi_x_additional_fee[position]+ " টাকা ");
        }


        if(!xi_xii_free[position].equals(""))
        {
            holder.q.setText("ষষ্ঠ থেকে দশম শ্রেনীর ফিঃ: "+xi_xii_free[position]);
        }

        if(!xi_xii_stipend_speciality[position].equals(""))
        {
            holder.r.setText("ষষ্ঠ থেকে দশম শ্রেনীর বৃত্তি বিশেষত্ব " +xi_xii_stipend_speciality[position]);
        }

        if(!xi_xii_stipend_type[position].equals(""))
        {
            holder.s.setText("ষষ্ঠ থেকে দশম শ্রেনীর বৃত্তি টাইপঃ "+xi_xii_stipend_type[position]);
        }

        if(!xi_xii_stipend_details[position].equals(""))
        {
            holder.t.setText("ষষ্ঠ থেকে দশম শ্রেনীর বৃত্তি বিস্তারিতঃ "+xi_xii_stipend_details[position]);
        }

        if(!xi_xii_max_fee[position].equals(""))
        {
            holder.u.setText("ষষ্ঠ থেকে দশম শ্রেনীর সর্বোচ্চ ফিঃ "+xi_xii_max_fee[position]+ " টাকা ");
        }

        if(!xi_xii_min_fee[position].equals(""))
        {
            holder.v.setText("ষষ্ঠ থেকে দশম শ্রেনীর সর্বনিম্ন ফিঃ "+xi_xii_min_fee[position]);
        }

        if(xi_xii_coaching_fee[position].equals(""))
        {
            holder.w.setText("ষষ্ঠ থেকে দশম শ্রেনীর কোচিং ফিঃ" +xi_xii_coaching_fee[position]);
        }


        if(!xi_xii_additional_fee[position].equals("")){
            holder.x.setText("ষষ্ঠ থেকে দশম শ্রেনীর অতিরিক্ত ফিঃ "+xi_xii_additional_fee[position]);
        }


        if(!uni_free[position].equals(""))
        {
            holder.y.setText("একাদশ দ্বাদশ শ্রেনীর ফিঃ "+uni_free[position]);
        }

        if(!uni_stipend_speciality[position].equals(""))
        {
            holder.z.setText("একাদশ দ্বাদশ শ্রেনীর বৃত্তি বিশেষত্ব "+uni_stipend_speciality[position]+ " টাকা ");
        }


        if(!uni_stipend_details[position].equals(""))
        {
            holder.aa.setText("একাদশ দ্বাদশ শ্রেনীর বৃত্তি টাইপঃ "+uni_stipend_details[position]);
        }

        if(!uni_stipend_type[position].equals(""))
        {
            holder.ab.setText("একাদশ দ্বাদশ শ্রেনীর বৃত্তি বিস্তারিতঃ " +uni_stipend_type[position]);
        }


        if(!uni_max_fee[position].equals(""))
        {
            holder.ac.setText("একাদশ দ্বাদশ শ্রেনীর সর্বোচ্চ ফিঃ "+uni_max_fee[position]);
        }

        if(!uni_min_fee[position].equals(""))
        {
            holder.ad.setText("একাদশ দ্বাদশ শ্রেনীর সর্বনিম্ন ফিঃ "+uni_min_fee[position]);
        }

        if(!uni_coaching_fee[position].equals(""))
        {
            holder.ae.setText("একাদশ দ্বাদশ শ্রেনীর কোচিং ফিঃ "+uni_coaching_fee[position]+ " টাকা ");
        }

        if(!uni_additional_fee[position].equals(""))
        {
            holder.af.setText("একাদশ দ্বাদশ শ্রেনীর অতিরিক্ত ফিঃ "+uni_additional_fee[position]);
        }






        return convertView;
    }

}



