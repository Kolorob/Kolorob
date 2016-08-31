package demo.kolorob.kolorobdemoversion.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by arafat 31 August.
 */
public class Comment_layout_adapter extends BaseAdapter
{
    Activity context;
    String mobile[];
    String comment[];
    String date[];
    int height;
    int width;



    public Comment_layout_adapter(Activity context, String[] mobile,String[] comment,String[] date) {
        super();
        this.context = context;
        this.mobile = mobile;
        this.comment = comment;
        this.date = date;





    }

    public int getCount() {
        // TODO Auto-generated method stub
        return date.length;
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
        TextView mobile;
        TextView  comment;
        TextView date;



    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.comment_layout, null);
            holder = new ViewHolder();
            holder.mobile = (TextView) convertView.findViewById(R.id.mobile);
            holder.comment = (TextView) convertView.findViewById(R.id.comment);
            holder.date = (TextView) convertView.findViewById(R.id.date);



            convertView.setTag(holder);
        }

        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;








        holder.mobile.setText(mobile[position]);
        holder.comment.setText(comment[position]);
        holder.date.setText(DateConverter(date[position]));


        return convertView;
    }


    public String DateConverter(String date)
    {
        String [] fff= date.split("-");

        String year= fff[0];
        String month =fff[1];
        String day= fff [2];
        if(month.equals("01"))
            return English_to_bengali_number_conversion(day)+" "+"জানুয়ারী "+ English_to_bengali_number_conversion(year);
        else if(month.equals("02"))
            return English_to_bengali_number_conversion(day)+" "+"ফেব্রুয়ারি "+ English_to_bengali_number_conversion(year);
        else if(month.equals("03"))
            return English_to_bengali_number_conversion(day)+" "+"মার্চ"+ English_to_bengali_number_conversion(year);
        else if(month.equals("04"))
            return English_to_bengali_number_conversion(day)+" "+" এপ্রিল "+ English_to_bengali_number_conversion(year);
        else if(month.equals("05"))
            return English_to_bengali_number_conversion(day)+" "+" মে "+ English_to_bengali_number_conversion(year);
        else if(month.equals("06"))
            return English_to_bengali_number_conversion(day)+" "+"জুন "+ English_to_bengali_number_conversion(year);
        else if(month.equals("07"))
            return English_to_bengali_number_conversion(day)+" "+"জুলাই "+ English_to_bengali_number_conversion(year);
        else if(month.equals("08"))
            return English_to_bengali_number_conversion(day)+" "+"আগস্ট "+ English_to_bengali_number_conversion(year);
        else if(month.equals("09"))
            return English_to_bengali_number_conversion(day)+" "+"সেপ্টেম্বর"+ English_to_bengali_number_conversion(year);
        else if(month.equals("10"))
            return English_to_bengali_number_conversion(day)+" "+" অক্টোবর"+ English_to_bengali_number_conversion(year);
        else if(month.equals("11"))
            return English_to_bengali_number_conversion(day)+" "+" নভেম্বর "+ English_to_bengali_number_conversion(year);
        else if(month.equals("12"))
            return English_to_bengali_number_conversion(day)+" "+" ডিসেম্বর "+ English_to_bengali_number_conversion(year);
        else
            return English_to_bengali_number_conversion(day)+" "+" ডিসেম্বর "+ English_to_bengali_number_conversion(year);
    }

    public String English_to_bengali_number_conversion(String english_number) {
        int v = english_number.length();
        String concatResult = "";
        for (int i = 0; i < v; i++) {
            if (english_number.charAt(i) == '1')
                concatResult = concatResult + "১";
            else if (english_number.charAt(i) == '2')
                concatResult = concatResult + "২";
            else if (english_number.charAt(i) == '3')
                concatResult = concatResult + "৩";
            else if (english_number.charAt(i) == '4')
                concatResult = concatResult + "৪";
            else if (english_number.charAt(i) == '5')
                concatResult = concatResult + "৫";
            else if (english_number.charAt(i) == '6')
                concatResult = concatResult + "৬";
            else if (english_number.charAt(i) == '7')
                concatResult = concatResult + "৭";
            else if (english_number.charAt(i) == '8')
                concatResult = concatResult + "৮";
            else if (english_number.charAt(i) == '9')
                concatResult = concatResult + "৯";
            else if (english_number.charAt(i) == '0')
                concatResult = concatResult + "০";
            else{
                concatResult = concatResult + english_number.charAt(i);
            }
        }
        return concatResult;
    }

}

