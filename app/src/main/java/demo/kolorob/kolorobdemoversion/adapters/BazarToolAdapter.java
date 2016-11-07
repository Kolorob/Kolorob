package demo.kolorob.kolorobdemoversion.adapters;


/**
 * Created by arafat on 19 September 2016.
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

public class BazarToolAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, ArrayList<String>> _listDataChild;
    private int width;
    private int height;
    private String type;

    public BazarToolAdapter(Context context, List<String> listDataHeader,
                            HashMap<String, ArrayList<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, groupPosition);
        Log.d("MyList","%%%%%%"+childText);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.bazar_child_view, null);
        }

        DisplayMetrics displayMetrics = _context.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;


        final String[] Children = childText.split("@");

        TextView desctiption = (TextView) convertView.findViewById(R.id.description);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView condition = (TextView) convertView.findViewById(R.id.condition);
        TextView area = (TextView) convertView.findViewById(R.id.area);
        TextView post_person = (TextView) convertView.findViewById(R.id.post_person);
        TextView another_num = (TextView) convertView.findViewById(R.id.another_num);
        ImageView phone_call=(ImageView)convertView.findViewById(R.id.phone_call);
        ImageView email = (ImageView)convertView.findViewById(R.id.email);

        Log.d("Tutionn","============"+type);

        if(type.equals("Tution")||type.equals("টিউশন"))
        {
            price.setVisibility(View.GONE);
            condition.setVisibility(View.GONE);
        }
        else
        {
            price.setVisibility(View.VISIBLE);
            condition.setVisibility(View.VISIBLE);
        }

        Double screenSize= AppUtils.ScreenSize(_context);

        if(screenSize>6.5)
        {
            desctiption.setTextSize(20);
            price.setTextSize(20);
            date.setTextSize(20);
            condition.setTextSize(20);
            area.setTextSize(20);
            post_person.setTextSize(20);
            another_num.setTextSize(20);

        }
        else {
            desctiption.setTextSize(15);
            price.setTextSize(15);
            date.setTextSize(15);
            condition.setTextSize(15);
            area.setTextSize(15);
            post_person.setTextSize(15);
            another_num.setTextSize(15);
        }


        phone_call.getLayoutParams().height=width/11;
        phone_call.getLayoutParams().width=width/11;
        email.getLayoutParams().height=width/11;
        email.getLayoutParams().width=width/11;
        desctiption.setText(Children[0]);

        Character ZERO= '0';
        Character one = '1';

        Character price_s = Children[1].charAt(Children[1].length()-1);
        int p= Integer.valueOf(price_s);
        Log.d("price_s","=============="+Children[1]);
        Log.d("price_s2","=============="+price_s);

        if(Children[1].length()==1&& price_s==one)
        {
            price.setText("মূল্য: আলোচনা সাপেক্ষে");
        }
          else if(price_s==one)
           {
               price.setText("মূল্য: "+English_to_bengali_number_conversion(AppUtils.removeLastChar(Children[1]))+ " টাকা (আলোচনা সাপেক্ষে)");

           }
        else if(price_s == ZERO)
               price.setText("মূল্য: "+English_to_bengali_number_conversion(AppUtils.removeLastChar(Children[1]))+ " টাকা");

     //   price.setText(Children[1].replace("%",""));
        date.setText(Children[2]);

        if(Children[3].equals("New"))
            condition.setText("কন্ডিশন: নতুন");
        else if(Children[3].equals("Refurbished"))
            condition.setText("কন্ডিশন: ব্যবহৃত");
        else
            condition.setText("ব্যবহৃত");
        area.setText(Children[4]);

        Log.d("condition","############"+Children[6]);
        if(Children[6].equals("যোগাযোগ নম্বর: "))
        {
            another_num.setVisibility(View.GONE);
        }
        else {
            another_num.setVisibility(View.VISIBLE);
            another_num.setText(Children[6]);
        }

        post_person.setText(Children[7]);

        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                if (!Children[5].equals("")) {
                    callIntent1.setData(Uri.parse("tel:" + Children[5]));
                    callIntent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (checkPermission(_context))
                        _context.startActivity(callIntent1);
                    else {
                        AlertMessage.showMessage(_context, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                                "ফোন নম্বর পাওয়া যায়নি");
                    }
                } else {

                    AlertMessage.showMessage(_context, "ফোনে কল দেয়া সম্ভব হচ্ছে না",
                            "ফোন নম্বর পাওয়া যায়নি");
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertMessage.showMessage(_context, "ই মেইল করা সম্ভব হচ্ছে না",
                        "ই মেইল আই ডি পাওয়া যায়নি");
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.bazar_parent_view, null);
        }



        String[] groupElement= headerTitle.split("@");

        TextView product_name = (TextView) convertView
                .findViewById(R.id.p_name);
        TextView sell = (TextView) convertView
                .findViewById(R.id.sell);

        product_name.setTypeface(null, Typeface.BOLD);

        product_name.setText(groupElement[0]);
        if(groupElement[1].equals("Exchange"))
        {
            sell.setText("বিনিময়");
        }
       else if(groupElement[1].equals("Sell"))
        {
            sell.setText("বিক্রয়");
        }

       else if(groupElement[1].equals("Tution"))
        {
            sell.setText("টিউশন");
        }

        else if(groupElement[1].equals("Buy"))
        {
            sell.setText("ক্রয়");
        }

        else if(groupElement[1].equals("To_Let"))
        {
            sell.setText("টু লেট");
        }

   //     sell.setText(groupElement[1]);
        type = groupElement[1];
        Double screenSize= AppUtils.ScreenSize(_context);

        if(screenSize>6.5)
        {
            product_name.setTextSize(25);
            sell.setTextSize(16);

        }
        else {
            product_name.setTextSize(18);
            sell.setTextSize(12);
        }

        return convertView;
    }

    private boolean checkPermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
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
        }
        return concatResult;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
