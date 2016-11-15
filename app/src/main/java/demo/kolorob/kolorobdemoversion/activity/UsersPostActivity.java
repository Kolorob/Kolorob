package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.BazarToolAdapter;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.BazarItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class UsersPostActivity extends AppCompatActivity {

    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    ArrayList<String> bazar_data;
    ArrayList<BazarItem> allBazar = new ArrayList<BazarItem>();
    List<String> listDataHeader;
    HashMap<String, ArrayList<String>> listDataChild;
    private int bazar_counter=0;
    ArrayList<ArrayList<String>> myList;
    ExpandableListView expListView;
    BazarToolAdapter bazarToolAdapter;
    private int lastExpandedPosition = -1;
    ProgressDialog dialog;
    String userPhoneNumeber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);

        if ((AppUtils.isNetConnected(getApplicationContext()) )&&(ContextCompat.checkSelfPermission(UsersPostActivity.this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED ))
        {
            dialog = new ProgressDialog(UsersPostActivity.this);
            dialog.setMessage("দয়া করে অপেক্ষা করুন");
            dialog.setCancelable(false);
            dialog.show();


            getRequest(UsersPostActivity.this, "http://kolorob.net/demo/api/getadvsql?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                //ge the db instance
                                SQLiteDatabase db = DatabaseManager.getInstance(UsersPostActivity.this).openDatabase();
                                //split into single sql queries
                                String[] sql = apiContent.split("~");
                                //run the sqls one by one
                                for (int i = 0; i<sql.length;i++)
                                {
                                    Log.d("SQL[i]","%%%%%%"+sql[i]);
                                    db.execSQL(sql[i]);
                                }
                                //now reload the data taht has beed saved
                                //get all data from db
                                Cursor cursor =  db.rawQuery("select * from custom_advertisement", null);
                                allBazar = new ArrayList<BazarItem>();
                                int vf=0;
                                while (cursor.moveToNext()) {
                                    allBazar.add(new BazarItem(cursor));
                                    vf++;
                                }
                                Log.d("vvff","%%%%%%"+vf);
                                //tester. You may delete this portion
                                Context context = getApplicationContext();
//                            CharSequence text = allBazar.get(0).toString();
//                            int duration = Toast.LENGTH_SHORT;
//                            Toast toast = Toast.makeText(context, text, duration);
//                            toast.show();
                                //tester ends======
                            }


                            listDataHeader = new ArrayList<String>();
                            listDataChild = new HashMap<String, ArrayList<String>>();
                            bazar_data=new ArrayList<String>();
                            bazar_data.clear();
                            bazar_counter=0;
                            myList = new ArrayList<ArrayList<String>>(allBazar.size());
                            int size= allBazar.size();
                            for(BazarItem bazarItem: allBazar)
                            {
                                String bazarData= "বিবরন: "+bazarItem.description+"@"+
                                        bazarItem.price+"@"+
                                        "তারিখ: "+bazarItem.date+"@"+

                                        bazarItem.condition+"@"+
                                        "এলাকা: "+bazarItem.address+"@"+
                                        bazarItem.phone+"@"+
                                        "যোগাযোগ নম্বর: "+bazarItem.contact+"@"+
                                        "পোস্ট দিয়েছেন: "+bazarItem.contact_person+"@"+ "v"
                                        ;
                                String group_data= bazarItem.product_name+"@"+
                                        bazarItem.type+"@"+"v";
                                bazar_data.add(bazar_counter,bazarData);
                                listDataHeader.add(group_data);
                                bazar_counter++;
                            }
                            ArrayList<String > temp= new ArrayList<String>();
                            for(int k=0;k<bazar_counter;k++)
                            {
                                myList.add(k,bazar_data);
                                temp.clear();
                            }
                            for(int i=0;i<bazar_counter;i++)
                            {
                                listDataChild.put(listDataHeader.get(i),myList.get(i));
                            }
                            dialog.cancel();

                            expListView = (ExpandableListView) findViewById(R.id.lvExp);
                            bazarToolAdapter = new BazarToolAdapter(UsersPostActivity.this, listDataHeader, listDataChild);
                            expListView.setAdapter(bazarToolAdapter);

                            expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                                @Override
                                public void onGroupExpand(int groupPosition) {
                                    if (lastExpandedPosition != -1
                                            && groupPosition != lastExpandedPosition) {
                                        expListView.collapseGroup(lastExpandedPosition);
                                    }
                                    lastExpandedPosition = groupPosition;


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


//                        Log.d("Button Heights","%%%%%%"+buttonHeights);



                        }
                    }
            );
        }
        else {
            AlertMessage.showMessage(UsersPostActivity.this,"আপনার ফোনে ইন্টারনেট সংযোগ নেই।","অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");

        }



    }
}
