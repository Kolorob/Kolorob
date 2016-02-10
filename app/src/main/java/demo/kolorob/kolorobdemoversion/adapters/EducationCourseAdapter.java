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

public class EducationCourseAdapter extends BaseAdapter
{
    Activity context;
    String course_name[];
    String course_duration[];
    String course_admission_time[];
    String edu_course_cost[];
    String edu_course_type[];

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
            holder.c_duration = (TextView) convertView.findViewById(R.id.c_duration);
            holder.s_admission_time = (TextView) convertView.findViewById(R.id.c_admissionTime);
            holder.c_cost = (TextView) convertView.findViewById(R.id.c_cost);
            holder.s_type = (TextView) convertView.findViewById(R.id.c_type);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }





        holder.c_name.setText("কোর্সের নাম : " +course_name[position]);
        holder.c_duration.setText(" কোর্সের সময়সীমা: "+course_duration[position]);
        holder.s_admission_time.setText("ভর্তির মৌসুম : "+course_admission_time[position]);
        holder.c_cost.setText(" ভর্তি হতে খরচ : "+edu_course_cost[position]);
        holder.s_type.setText("কোর্সের ধরন: "+edu_course_type[position]);



        return convertView;
    }

}



