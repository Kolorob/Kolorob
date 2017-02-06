package demo.kolorob.kolorobdemoversion.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import demo.kolorob.kolorobdemoversion.BuildConfig;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AreaHolder;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_Adapter;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterArea;
import demo.kolorob.kolorobdemoversion.database.Education.EducationNewTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTrainingDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTuitionDetailsTable;
import demo.kolorob.kolorobdemoversion.interfaces.ItemClickSupport;
import demo.kolorob.kolorobdemoversion.model.DataModel;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTrainingDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTuitionDetailsItem;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;


public class DataLoadingActivity extends AppCompatActivity {

    Context context;
    private static int NUMBER_OF_TASKS = 10;
    View view=null;
    String IMEINumber=null;
    //user and pass
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    Boolean location=false,storage=false,smsstate=false,phonestate=false,accountstate=false,permission=false;
    int countofDb=0;
    JSONObject allData;
    private static RecyclerView recyclerView,recyclerViewarea;
    AreaHolder areaHolder;

    //String and Integer array for Recycler View Items
    public static final String[] TITLES= {"Ward 2","Ward 3","Ward 4","Ward 5","Ward 6","Ward 7","Ward 8","Ward 9","Ward 10","Ward 11","Ward 12","Ward 13","Ward 14","Ward 15",
            "Ward 16"};
public static final int[] wardid={2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    public static final String[] AREANAMES={"Mirpur 12:Mirpur DOHS",
            "Mirpur 10:Mirpur 11",
            "Mirpur 13:Mirpur 14:Baishteki",
            "Mirpur 11:Bauniabadh:Palashnagar",
    "Mirpur 6:Mirpur 7:Eastern Housing:Albodi:Pallabi:Albodi Rupnagar Tinshed:Duaripara",
            "Mirpur 2:Mirpur 6:Rupnagar:Government Housing Estate",
            "Mirpur 1:North Bishil:Baksnagar:Nobabaer Bag:BISF Staff Quarter:Botanical Garden Residential Area",
            "Bagbari:Harirampur:Jahurabad:Bazarpara:Bordhonbari:Golartek:Choto Diabari:Coat Bari:Anandanagar",
            "Gabtoli Jamidarbari:Gabtoli 1st Colony:Gabtoli 2nd Colony:Gabtoli 3rd Colony:Goidartek:Darus Salam",
            "Kallyanpur:Paikpara",
    "Ahammed Nagar:South Bishil:Shah Ali Bag:Kalwala Para:Paikpara Staff Quarter:Educatiob Board Staff Quarter:Tolarbag:BADC Staff Quarter",
            "Borobag:Pirer Bag:Monipur",
            "Kazi para:Shewrapara:Senpara Parbata"
    ,"Vashantek:Albodortek:Damalkot:Lalasorai:Matikata:Manikdi:Balughat:Baigertek:Barontek",
            "Ibrahimpur:Kafrul"};
    public static final String[] AREANAMESBN={"মিরপুর ১২: মিরপুর ডিওএইচএস",
            "মিরপুর ১০: মিরপুর ১১",
            "মিরপুর ১৩:  মিরপুর ১৪: বাইশটেকি",
            "মিরপুর ১১: বাউনিয়াবাঁধ: পলাশ নগর",
            "মিরপুর ৬: মিরপুর ৭:  পল্লবী:  আলবদী: আলবদী রূপনগর টিনশেড: দুয়ারী পাড়া:  ইস্টার্ন হাউজিং",
            "মিরপুর ২: মিরপুর ৬: রূপনগর: সরকারী হাউজিং এষ্টেট",
            "মিরপুর ১: উত্তর বিশিল: বকস নগর: বোটনিক্যাল গার্ডেন আবাসিক এলাকা: নবাবের বাগ: বি: আই: এস: এফ ষ্টাফ কোয়ার্টার ",
            "বাগবাড়ী: হরিরামপুর: জহরাবাদ: বাজার পাড়া: বর্ধনবাড়ী: গোলারটেক: ছোটদিয়াবাড়ী: কোটবাড়ী: আনন্দ নগর",
            "গাবতলী জমিদারবাড়ী : গাবতলী ১ম: ২য় ও ৩য় কলোনী: গাবতলী ২য় কলোনী: গাবতলী ৩য় কলোনী: গৈদারটেক: দারুস সালাম",
            "কল্যাণপুর: পাইক পাড়া",
            "আহম্মেদ নগর: দক্ষিণ বিশিল: শাহআলী বাগ: কালওয়ালা পাড়া: পাইকপাড়া ষ্টাফ কোয়ার্টার: শিক্ষা বোর্ড ষ্টাফ কোয়ার্টার: টোলারবাগ: বিএডিসি ষ্টাফ কোয়ার্টার",
            "বড় বাগ: পীরের বাগ: মনীপুর",
            "কাজী পাড়া: শেওড়া পাড়া: সেনপাড়া পর্বতা"
            ,"ভাসান টেক: আলবদিরটেক: দামালকোট: লালাসরাই: মাটি কাটা: মানিকদি: বালুঘাট: বাইগারটেক: বারনটেক",
            "ইব্রাহীমপুর: কাফরুল"};
int Pos;

    public int getPos() {
        return Pos;
    }

    public void setPos(int pos) {
        Pos = pos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //start download now

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_loading);
        initViews();
        populatRecyclerView();
        populatRecyclerView2();
        Pos=0;
        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        setPos(position);
                        populatRecyclerView2();
                        //Toast.makeText(DataLoadingActivity.this,"Existing areas are : "+AREANAMESBN[position].replace(':',','), Toast.LENGTH_SHORT).show();
                    }
                });
        /*
        //now make the early request just in case
        if ((AppUtils.isNetConnected(getApplicationContext()) )&&(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED )) {
            getRequest(DataLoadingActivity.this, "http://kolorob.net/NewStructure.json" , new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {

                                   allData = new JSONObject(apiContent);


                                    //                                          frameAnimation.stop();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(allData.has("Finance"))
                                    new SaveDataArrayFinance(DataLoadingActivity.this).execute(allData);
                            }
                        }
                    }
            );
        }
*/





        context = this;






        //  setsongName();
    }
    private void initViews() {


        recyclerView = (RecyclerView)
                findViewById(R.id.recycler_view);
        recyclerViewarea = (RecyclerView)
                findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        recyclerViewarea.setHasFixedSize(true);

        //Set RecyclerView type according to intent value

            recyclerView.setLayoutManager(new LinearLayoutManager(DataLoadingActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewarea.setLayoutManager(new LinearLayoutManager(DataLoadingActivity.this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView.smoothScrollToPosition(14);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (BuildConfig.DEBUG) {
                    System.gc();
                }
                recyclerView.smoothScrollToPosition(0);
            }
        }, 3000);




    }



    // populate the list view by adding data to arraylist
    private void populatRecyclerView() {
        ArrayList<AreaHolder> arrayList = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            AreaHolder areaHolder1=new AreaHolder(wardid[i],TITLES[i],AREANAMES[i],AREANAMESBN[i]);
            arrayList.add(areaHolder1);
        }
        RecyclerView_Adapter  adapter = new RecyclerView_Adapter(DataLoadingActivity.this, arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview


        adapter.notifyDataSetChanged();// Notify the adapter


    }
    private void populatRecyclerView2() {
        ArrayList<DataModel> arrayList2 = new ArrayList<>();


            ArrayList<String> list = new ArrayList<String>(Arrays.asList(AREANAMESBN[getPos()].split(":")));
        for (int i = 0; i < list.size(); i++) {
            DataModel areaHolder1=new DataModel(list.get(i));
            arrayList2.add(areaHolder1);
        }
        RecyclerView_AdapterArea  adapter2 = new RecyclerView_AdapterArea(DataLoadingActivity.this,arrayList2);
        recyclerViewarea.setAdapter(adapter2);// set adapter on recyclerview



        adapter2.notifyDataSetChanged();// Notify the adapter

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    class SaveDataArrayFinance extends GenericSaveDBTask<JSONObject, Integer, Long> {
        public SaveDataArrayFinance(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonObjects) {
            JSONArray edu = jsonObjects[0];
            EducationNewTable educationNewTable = new EducationNewTable(DataLoadingActivity.this);
            EducationResultDetailsTable educationResultDetailsTable = new EducationResultDetailsTable(DataLoadingActivity.this);
            EducationTrainingDetailsTable educationTrainingDetailsTable = new EducationTrainingDetailsTable(DataLoadingActivity.this);
            EducationTuitionDetailsTable educationTuitionDetailsTable = new EducationTuitionDetailsTable(DataLoadingActivity.this);
            educationNewTable.dropTable();
            educationResultDetailsTable.dropTable();
            educationTrainingDetailsTable.dropTable();
            educationTuitionDetailsTable.dropTable();


            int eduServiceProviderCount = edu.length();

            for (int i = 0; i < eduServiceProviderCount; i++) {
                try {
                    JSONObject jo = edu.getJSONObject(i);
                    EducationNewItem et = EducationNewItem.parseEducationNewItem(jo);
                    educationNewTable.insertItem(et);


                    if (jo.has("tution_details"))//
                    {
                        JSONArray service_details = jo.getJSONArray("tution_details");
                        for (int j = 0; j < service_details.length(); j++) {
                            JSONObject joes = service_details.getJSONObject(j);
                            EducationTuitionDetailsItem educationTuitionDetailsItem = EducationTuitionDetailsItem.parseEducationTuitionDetailsItem(joes);
                            educationTuitionDetailsTable.insertItem(educationTuitionDetailsItem);

                        }

                        countofDb++;

                    }


                    if (jo.has("result_details"))//
                    {
                        JSONArray service_details = jo.getJSONArray("result_details");
                        for (int j = 0; j < service_details.length(); j++) {
                            JSONObject joes = service_details.getJSONObject(j);
                            EducationResultItemNew educationResultItemNew = EducationResultItemNew.parseEducationResultItemNew(joes);
                            educationResultDetailsTable.insertItem(educationResultItemNew);

                        }

                    }
                    if (jo.has("training_details"))//
                    {
                        JSONArray service_details = jo.getJSONArray("training_details");
                        for (int j = 0; j < service_details.length(); j++) {
                            JSONObject joes = service_details.getJSONObject(j);
                            EducationTrainingDetailsItem educationTrainingDetailsItem = EducationTrainingDetailsItem.parseEducationTrainingDetailsItem(joes);
                            educationTrainingDetailsTable.insertItem(educationTrainingDetailsItem);
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }

        @Override
        protected Long doInBackground(JSONObject... params) {
            return null;
        }
    }

    abstract class GenericSaveDBTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
        private Context ctx;

        public GenericSaveDBTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPostExecute(Result result) {
            if (((Long) result).longValue() == 0.0 && countofDb < NUMBER_OF_TASKS)  { // Means the task is successful
                countofDb++;
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("KValue", countofDb);
                editor.apply();
                Log.d("tasks", "Tasks remaining: " + (NUMBER_OF_TASKS - countofDb));
                ToastMessageDisplay.setText(DataLoadingActivity.this.context,"তথ্য সংগ্রহ চলছে");
                ToastMessageDisplay.showText(DataLoadingActivity.this.context);
            }
        }

    }
}




