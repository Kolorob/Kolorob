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

public class DefaultAdapter extends BaseAdapter
{
    Activity context;
    String key[];
    String value[];
    String p="";
    int increment;




    public DefaultAdapter(Activity context, String[] key,String[] value,int increment) {
        super();
        this.context = context;
        this.key = key;
        this.value = value;
        this.increment=increment;


    }

    public int getCount() {
        // TODO Auto-generated method stub
        return increment;
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
        TextView key ;
        TextView value;


    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_vaccine, null);
            holder = new ViewHolder();
            holder.key = (TextView) convertView.findViewById(R.id.key);
            holder.value = (TextView) convertView.findViewById(R.id.value);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

         p= key[position];

        Log.d("Postion St","$$$$$$$"+p);

        if( p == null )
        {
            holder.key.setText("");
            holder.value.setText("");
        }
        else
        {
            holder.key.setText(key[position] + ":");
            holder.value.setText(value[position]);
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

