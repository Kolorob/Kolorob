package demo.kolorob.kolorobdemoversion.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by Mazharul.Islam1 on 2/18/2016.
 */
public class EducationCourseAdapter extends BaseAdapter
{
    Activity context;
    String course_name[];
    String course_duration[];
    String course_admission_time[];
    String edu_course_cost[];
    String edu_course_type[];
    String result_concate;

    public EducationCourseAdapter(Activity context, String[] course_name,String[] course_duration,String[] course_admission_time,String[] edu_course_cost,String[] edu_course_type) {
        super();
        this.context = context;
        this.course_name = course_name;
        this.course_duration = course_duration;
        this.course_admission_time = course_admission_time;
        this.edu_course_cost = edu_course_cost;
        this.edu_course_type = edu_course_type;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return course_name.length;
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
        TextView c_name ;
        TextView c_duration;
        TextView s_admission_time;
        TextView c_cost;
        TextView s_type;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_course_time, null);
            holder = new ViewHolder();
            holder.c_name = (TextView) convertView.findViewById(R.id.c_name);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }




       if(!course_name[position].equals(""))
           concateBasic("কোর্সের নাম: ",course_name[position]);
        if(!course_duration[position].equals(""))
            concateBasic("কোর্সের সময়সীমা: ",course_duration[position]);
        if(!course_admission_time[position].equals(""))
            concateBasic("ভর্তির মৌসুম : ",course_admission_time[position]);
        if(!edu_course_cost[position].equals(""))
            concateBasic("ভর্তি হতে খরচ : ",edu_course_cost[position]);
        if(!edu_course_type[position].equals(""))
            concateBasic("কোর্সের ধরন: ",edu_course_type[position]);

        concateBasic("","");




        holder.c_name.setText(result_concate);
        result_concate="";

        return convertView;
    }


    private String concateBasic(String value1,String value2){
        String value= value1+value2;
        result_concate= result_concate+value + "\n";
        return result_concate;
    }

}

