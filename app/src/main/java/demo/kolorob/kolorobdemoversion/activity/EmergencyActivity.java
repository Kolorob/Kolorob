package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.EmergencyListAdapter;

public class EmergencyActivity extends AppCompatActivity {

    List<String> listDataHeader;
    HashMap<String, ArrayList<String>> listDataChild;
    ExpandableListView expListView;
    String[] name_bangla;
    String[] phone_no;
    String[] address_bangla;
    String[] location;
    String[] email;
    Context context;
    ImageView close_button;
    private int lastExpandedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        expListView = (ExpandableListView)findViewById(R.id.lvExp);
        close_button=(ImageView)findViewById(R.id.iv_close);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        context =this;
        name_bangla = new String[] { "বাংলাদেশ ফায়ার সার্ভিস অ্যান্ড সিভিল ডিফেন্স ",
                "পুলিশ কন্ট্রোল রুম",
                "পল্লবী থানা",
                "র‍্যাব - ৪",
                "ডেসকো – ইলেক্ট্রিসিটি ",
                "দুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয়","তিতাস গ্যাস ","ঢাকা ওয়াসা","ব্লাড ব্যাংক ","ঢাকা উত্তর সিটি কর্পোরেশন","নারী ও শিশু নির্যাতন প্রতিরোধ","শিশু হেল্পলাইন","মহিলা ও শিশু বিষয়ক মন্ত্রণালয়, নারী ও শিশু নির্যাতন প্রতিরোধে হেল্পলাইন","বাংলাদেশ সাইবার নিরাপত্তা হেল্পলাইন",""
        };

        phone_no = new String[] { "029555555",
                "029555555",
                "027124000",
                "02-9015922",
                "029015922",
                "01777910499","02-9014291","16162","029139940","16364","10921","1098","10921","01766678888",""
        };

        email = new String[] { "",
                "",
                "",
                "",
                "",
                "","","","","","","","","info@cybernirapotta.net",
        };

        address_bangla = new String[] { "ঢাকা",
                "মিরপুর ১০, ঢাকা",
                "ঢাকা",
                "ঢাকা",
                "পল্লবী, ঢাকা",
                "পল্লবী, ঢাকা","ঢাকা","ঢাকা","ঢাকা","ঢাকা","ঢাকা","ঢাকা","ঢাকা","ঢাকা",
        };

        location = new String[] { "23.8069959 90.3665992",
                "not found",
                "23.8260387 90.366459",
                "23.78613 90.3570516",
                "23.8320088 90.4187671",
                "23.7296558 90.4090196","23.7505538 90.3934337","23.7531737 90.3925594","23.753432 90.4045906","23.7932742 90.4080312","23.7475818 90.399754","not found","not found","not found"
        };

        prepareListData(this);
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;


//                    expListView.setChildDivider(ContextCompat.getDrawable(context,R.color.white));
//                    expListView.setDivider(ContextCompat.getDrawable(context,R.color.white));
//                }
//                else {
//                    expListView.setChildDivider(ContextCompat.getDrawable(context,R.color.job_portal));
//                    expListView.setDivider(ContextCompat.getDrawable(context,R.color.job_portal));
//                }
            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                expListView.collapseGroup(lastExpandedPosition);

                return false;
            }
        });


    }



    private void prepareListData(Context context) {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<String>>();
        ArrayList<String> temps= new ArrayList<String>();
        ArrayList<ArrayList<String>> datasending = new ArrayList<ArrayList<String >>();



        for(int i=0;i<location.length;i++)
        {
            for(int j=0;j<1;j++)
            {
                String temp="ফোন নম্বর: "+phone_no[i]+"#"+"ঠিকানা: "+address_bangla[i]+"#"+location[i]+"#"+email[i]+"#"+"v";
                temps.add(i,temp);
            }
            listDataChild.put(name_bangla[i], temps);
            listDataHeader.add(i,name_bangla[i]);


        }

        EmergencyListAdapter emergencyAdapter = new EmergencyListAdapter(context,listDataHeader,listDataChild);
        expListView.setAdapter(emergencyAdapter);


    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }

    }
}
