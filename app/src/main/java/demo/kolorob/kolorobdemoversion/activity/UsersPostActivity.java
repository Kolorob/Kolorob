package demo.kolorob.kolorobdemoversion.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.BazarToolAdapter;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.BazarItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class UsersPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);


        getRequest(UsersPostActivity.this, "http://kolorob.net/demo/api/getadvsql?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {

                        if (status == AppConstants.SUCCESS_CODE) {
                            //ge the db instance
                            SQLiteDatabase db = DatabaseManager.getInstance(PlaceDetailsActivityNewLayout.this).openDatabase();
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

                            //

                            String bazarData= "বিবরন: "+bazarItem.description+"@"+
                                    bazarItem.price+"@"+
                                    "তারিখ: "+bazarItem.date+"@"+

                                    bazarItem.condition+"@"+
                                    "এলাকা: "+bazarItem.address+"@"+
                                    bazarItem.phone+"@"+
                                    "যোগাযোগ নম্বর: "+bazarItem.contact+"@"+
                                    "পোস্ট দিয়েছেন: "+bazarItem.contact_person+"@"+ "v"
                                    ;


                            Log.d("Bazar Data","============="+bazarData);

                            String group_data= bazarItem.product_name+"@"+
                                    bazarItem.type+"@"+"v";

                            bazar_data.add(bazar_counter,bazarData);




                            listDataHeader.add(group_data);

                            // myList.add(bazar_counter,bazar_data);
                            // myList.get(bazar_counter).add(bazarData);
                            //             myList.add(bazar_data);

                            Log.d("myList","######"+myList);





                            bazar_counter++;


                        }
                        ArrayList<String > temp= new ArrayList<String>();


                        for(int k=0;k<bazar_counter;k++)
                        {

                            myList.add(k,bazar_data);
                            // myList.get(0).set(k,bazar_data.get(k));
                            //                      myList.add(k,temp);

                            temp.clear();
                        }




                        for(int i=0;i<bazar_counter;i++)
                        {
                            listDataChild.put(listDataHeader.get(i),myList.get(i));

                        }
                        dialog.cancel();

                        expListView = (ExpandableListView) findViewById(R.id.bazar_list);
                        bazarToolAdapter = new BazarToolAdapter(context, listDataHeader, listDataChild);
                        expListView.setAdapter(bazarToolAdapter);


                        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) expListView
                                .getLayoutParams();
                        layoutParams.setMargins(0, 0, 0, buttonHeights);//

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


                        Log.d("Button Heights","%%%%%%"+buttonHeights);
                        SlidingUpPanelLayout slidingUpPanelLayout;
                        slidingUpPanelLayout=(SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
                        RelativeLayout.LayoutParams slidingp= (RelativeLayout.LayoutParams) slidingUpPanelLayout.getLayoutParams();


                    }
                }
        );


    }
}
