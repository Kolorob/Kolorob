package demo.kolorob.kolorobdemoversion.adapters;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by Mazharul.Islam1 on 5/10/2016.
 */
public class BazarListAdapter extends BaseAdapter
{
    Activity context;
    String item_name[];
    String price[];
    String condition[];
    String description[];
    String contact[];
    String date[];
    String postedby[];
    LinearLayout titlePart,salaryPart,addressPart,numberpart,remainingdate_lin,salary_range_lin;
    int height,width;
    View top,bottom;


    public BazarListAdapter(Activity context, String[] item_name, String[] price, String[] condition, String[] description, String[] contact, String[] date, String[] postedby) {
        super();
        this.context = context;
        this.item_name = item_name;
        this.price = price;
        this.condition = condition;
        this.description =description;
        this.contact=contact;
        this.date=date;
        this.postedby=postedby;




    }

    public int getCount() {
        // TODO Auto-generated method stub
        return item_name.length;
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
        TextView first;
        TextView  second;
        TextView third;
        TextView fourth;
        TextView fifth;
        TextView sixth;
        TextView seventh;


        TextView first1;
        TextView  second1;
        TextView third1;
        TextView fourth1;
        TextView fifth1;
        TextView sixth1;
        TextView seventh1;


    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.bazar_list, null);
            holder = new ViewHolder();
            holder.first = (TextView) convertView.findViewById(R.id.first);
            holder.second = (TextView) convertView.findViewById(R.id.second);
            holder.third = (TextView) convertView.findViewById(R.id.third);
            holder.fourth = (TextView) convertView.findViewById(R.id.fourth);
            holder.fifth = (TextView) convertView.findViewById(R.id.fifth);
            holder.sixth = (TextView) convertView.findViewById(R.id.sixth);
            holder.seventh = (TextView) convertView.findViewById(R.id.seventh);

            holder.first1 = (TextView) convertView.findViewById(R.id.first1);
            holder.second1 = (TextView) convertView.findViewById(R.id.second1);
            holder.third1 = (TextView) convertView.findViewById(R.id.third1);
            holder.fourth1 = (TextView) convertView.findViewById(R.id.fourth1);
            holder.fifth1 = (TextView) convertView.findViewById(R.id.fifth1);
            holder.sixth1 = (TextView) convertView.findViewById(R.id.sixth1);
            holder.seventh1 = (TextView) convertView.findViewById(R.id.seventh1);


            convertView.setTag(holder);
        }

        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;




        holder.first.setText("");
        holder.second.setText("");;
        holder.third.setText("স্যালারি : ");
        holder.fourth.setText("শেষ সময়: ");
        holder.fifth.setText("ঠিকানা: " );
        holder.sixth.setText("যোগাযোগের নম্বর: ");
        holder.seventh.setText("কোম্পানি: " );



        holder.first1.setText(item_name[position]);
        holder.second1.setText(price[position]);;
        holder.third1.setText( condition[position]);
        holder.fourth1.setText(description[position]);
        holder.fifth1.setText(contact[position]);
        holder.sixth1.setText( date[position]);
        holder.seventh1.setText(postedby[position]);

        return convertView;
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

