package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Arafat 2 August 2016
 */

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

public class CompareAdapter extends BaseAdapter
{
    Activity context;
    String left[];
    String right[];
    String p="";
    String  header[];




    public CompareAdapter(Activity context, String[] left,String[] right,String header[]) {
        super();
        this.context = context;
        this.left = left;
        this.right = right;
        this.header=header;


    }

    public int getCount() {
        // TODO Auto-generated method stub
        return header.length;
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
        TextView left ;
        TextView right;
        TextView header;
        LinearLayout compare_box;


    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.compare_adapter, null);
            holder = new ViewHolder();

            holder.left = (TextView) convertView.findViewById(R.id.left_part);
            holder.right = (TextView) convertView.findViewById(R.id.right_part);
            holder.header = (TextView) convertView.findViewById(R.id.compare_header);
            holder.compare_box=(LinearLayout)convertView.findViewById(R.id.compare_box);

            Double screenSize = AppUtils.ScreenSize(context);
            int height= AppUtils.getScreenHeight(context);
            LinearLayout.LayoutParams compare_boxes= (LinearLayout.LayoutParams) holder.compare_box.getLayoutParams();
            compare_boxes.height=height/17;
            holder.compare_box.setLayoutParams(compare_boxes);
            if(screenSize>6.5)
            {
                holder.left.setTextSize(20);
                holder.right.setTextSize(20);
                holder.header.setTextSize(20);
            }
            else {
                holder.left.setTextSize(14);
                holder.right.setTextSize(14);
                holder.header.setTextSize(14);
            }
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.d("header","########"+left[position]);

        if(!left[position].equals("null")&&!left[position].equals(""))
        {
            holder.left.setText(left[position]);
        }
        else
        {holder.left.setText("শীঘ্রই আসছে");
        }

        if(!right[position].equals("null")&&!right[position].equals(""))
        {
            holder.right.setText(right[position]);
        }
        else
        {
            holder.right.setText("শীঘ্রই আসছে");
        }




        holder.header.setText(header[position]);






//            }
//        else
//            {
//
//            }


//        }
//        catch (Exception e)
//        {
//
//        }








        return convertView;
    }



}

