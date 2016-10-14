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




    public CompareAdapter(Activity context, String[] key,String[] value,String header[]) {
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

            Double screenSize = AppUtils.ScreenSize(context);
            if(screenSize>6.5)
            {
                holder.left.setTextSize(20);
                holder.right.setTextSize(20);
                holder.header.setTextSize(20);
            }
            else {
                holder.left.setTextSize(16);
                holder.right.setTextSize(16);
                holder.header.setTextSize(16);
            }
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }






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

