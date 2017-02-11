package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AreaHolder;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_Adapter;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterArea;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationNewTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTrainingDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationTuitionDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableHospital;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.interfaces.ItemClickSupport;
import demo.kolorob.kolorobdemoversion.interfaces.RecyclerViewHolder;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.DataModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTrainingDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationTuitionDetailsItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;
import info.hoang8f.widget.FButton;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class DataLoadingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    Context context;
    private static int NUMBER_OF_TASKS = 10;
    View view = null;
    Button submit;
    String IMEINumber = null;
    //user and pass
    String user = "kolorobapp";
    String pass = "2Jm!4jFe3WgBZKEN";
    Boolean location = false, storage = false, smsstate = false, phonestate = false, accountstate = false, permission = false;
    int countofDb = 0;
    JSONObject allData;

    public View getAreaview() {
        return areaview;
    }

    public void setAreaview(View areaview) {
        this.areaview = areaview;
    }

    public View getWardview() {
        return wardview;
    }

    public void setWardview(View wardview) {
        this.wardview = wardview;
    }

    View areaview, wardview;
    private static RecyclerView recyclerView, recyclerViewarea;
    AreaHolder areaHolder;
    public TourGuide mTourGuideHandler;
    public Activity mActivity;
    private Button mButton1, mButton2, mButton3;
    private Animation mEnterAnimation, mExitAnimation;
    //String and Integer array for Recycler View Items
    public static final String[] TITLES = {"ওয়ার্ড ২", "ওয়ার্ড ৩", "ওয়ার্ড ৪", "ওয়ার্ড ৫", "ওয়ার্ড ৬", "ওয়ার্ড ৭", "ওয়ার্ড ৮", "ওয়ার্ড ৯", "ওয়ার্ড ১০", "ওয়ার্ড ১১", "ওয়ার্ড ১২", "ওয়ার্ড ১৩", "ওয়ার্ড ১৪", "ওয়ার্ড ১৫",
            "ওয়ার্ড ১৬"};
    public static final int[] wardid = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    public static final String[] AREANAMES = {"Mirpur 12:Mirpur DOHS",
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
            , "Vashantek:Albodortek:Damalkot:Lalasorai:Matikata:Manikdi:Balughat:Baigertek:Barontek",
            "Ibrahimpur:Kafrul"};
    public static final String[] AREANAMESBN = {"মিরপুর ১২: মিরপুর ডিওএইচএস",
            "মিরপুর ১০: মিরপুর ১১",
            "মিরপুর ১৩:  মিরপুর ১৪: বাইশটেকি",
            "মিরপুর ১১: বাউনিয়াবাঁধ: পলাশ নগর",
            "মিরপুর ৬: মিরপুর ৭:  পল্লবী:  আলবদী: দুয়ারী পাড়া: ইস্টার্ন হাউজিং:আলবদী রূপনগর টিনশেড",
            "মিরপুর ২: মিরপুর ৬: রূপনগর: সরকারী হাউজিং এষ্টেট",
            "মিরপুর ১: উত্তর বিশিল: বকস নগর:নবাবের বাগ : বোটনিক্যাল গার্ডেন আবাসিক এলাকা: বিআইএসএফ ষ্টাফ কোয়ার্টার ",
            "বাগবাড়ী: হরিরামপুর: জহরাবাদ: বাজার পাড়া: বর্ধনবাড়ী: গোলারটেক: ছোটদিয়াবাড়ী: কোটবাড়ী: আনন্দ নগর",
            "গাবতলী জমিদারবাড়ী : গাবতলী ১ম কলোনী: গাবতলী ২য় কলোনী: গাবতলী ৩য় কলোনী: গৈদারটেক: দারুস সালাম",
            "কল্যাণপুর: পাইক পাড়া",
            "আহম্মেদ নগর: দক্ষিণ বিশিল: শাহআলী বাগ: কালওয়ালা পাড়া: পাইকপাড়া ষ্টাফ কোয়ার্টার: শিক্ষা বোর্ড ষ্টাফ কোয়ার্টার: টোলারবাগ: বিএডিসি ষ্টাফ কোয়ার্টার",
            "বড় বাগ: পীরের বাগ: মনীপুর",
            "কাজী পাড়া: শেওড়া পাড়া: সেনপাড়া পর্বতা"
            , "ভাসান টেক: আলবদিরটেক: দামালকোট: লালাসরাই: মাটি কাটা: মানিকদি: বালুঘাট: বাইগারটেক: বারনটেক",
            "ইব্রাহীমপুর: কাফরুল"};
    int Pos, Posa = 0;

    public int getPosa() {
        return Posa;
    }

    public void setPosa(int posa) {
        Posa = posa;
    }

    String posWard = null;

    public String getPosWard() {
        return posWard;
    }

    public void setPosWard(String posWard) {
        this.posWard = posWard;
    }

    String posArea = null;
    TextView ward, area;
    ArrayList<DataModel> arrayList2 = new ArrayList<>();
    RecyclerViewHolder holder2;

    public int getPos() {
        return Pos;
    }

    public void setPos(int pos) {
        Pos = pos;
    }

    public String getPosArea() {
        return posArea;
    }

    public void setPosArea(String posArea) {
        this.posArea = posArea;
    }

    private GridLayoutManager lLayout, lLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //start download now

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_selection_activity);
        ward = (TextView) findViewById(R.id.chooseward);
        area = (TextView) findViewById(R.id.choosearea);
        submit = (Button) findViewById(R.id.submittoserverarea);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Servercall();
            }
        });

        initViews();
        mEnterAnimation = new AlphaAnimation(0f, 1f);
        mEnterAnimation.setDuration(600);
        mEnterAnimation.setFillAfter(true);

        mExitAnimation = new AlphaAnimation(1f, 0f);
        mExitAnimation.setDuration(600);
        mExitAnimation.setFillAfter(true);
        populatRecyclerView();
        populatRecyclerView2();
        runOverlay_ContinueMethod();
        Pos = 0;

        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        setPos(position);

                        populatRecyclerView2();
                        // ((CardView)v).setCardBackgroundColor(Color.WHITE);
                        // ((TextView)v).setBackgroundColor(Color.parseColor("#ff8800"));
                        if (getWardview() == null) {
                            setWardview(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getWardview() != v) {
                            ((CardView) getWardview()).setCardBackgroundColor(Color.parseColor("#7f000000"));

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setWardview(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        //Toast.makeText(DataLoadingActivity.this,"Existing areas are : "+AREANAMESBN[position].replace(':',','), Toast.LENGTH_SHORT).show();
                    }
                });

        ItemClickSupport.addTo(recyclerViewarea)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        ((CardView) v).setCardBackgroundColor(Color.WHITE);
                        if (getAreaview() == null) {
                            setAreaview(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getAreaview() != v) {
                            ((CardView) getAreaview()).setCardBackgroundColor(Color.WHITE);

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setAreaview(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        Toast.makeText(getApplicationContext(), arrayList2.get(position).getTitle().toString() + " is selected!", Toast.LENGTH_SHORT).show();


                        //Toast.makeText(DataLoadingActivity.this,"Existing areas are : "+AREANAMESBN[position].replace(':',','), Toast.LENGTH_SHORT).show();
                    }
                });
        /*
         ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        setPos(position);

                        populatRecyclerView2();
                        //Toast.makeText(DataLoadingActivity.this,"Existing areas are : "+AREANAMESBN[position].replace(':',','), Toast.LENGTH_SHORT).show();
                    }
                });
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


        //Set RecyclerView type according to intent value
        lLayout = new GridLayoutManager(DataLoadingActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        else
//           toolbar = (Toolbar) findViewById(R.id.toolbars);


        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        ab.setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //  getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void runOverlay_ContinueMethod() {
        // the return handler is used to manipulate the cleanup of all the tutorial elements

        ChainTourGuide tourGuide1 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("১ম ধাপ")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("১ম লিস্টকে ডানে/বায়ে সরিয়ে আপনার ওয়ার্ড খুজে নিন")
                        .setGravity(Gravity.BOTTOM)
                )
                // note that there is no Overlay here, so the default one will be used
                .playLater(ward);

        ChainTourGuide tourGuide2 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("২য় ধাপ")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("ওয়ার্ড অনুযায়ী এলাকার লিস্ট থেকে এলাকা খুজে নিচের 'জমা দিন' বাটনটি ক্লিক করুন।")
                        .setGravity(Gravity.BOTTOM)
                )
                .playLater(area);


        Sequence sequence = new Sequence.SequenceBuilder()
                .add(tourGuide1, tourGuide2)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(mEnterAnimation)
                        .setExitAnimation(mExitAnimation)
                )
                .setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();


        ChainTourGuide.init(this).playInSequence(sequence);
    }


    // populate the list view by adding data to arraylist
    private void populatRecyclerView() {
        ArrayList<AreaHolder> arrayList = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            AreaHolder areaHolder1 = new AreaHolder(wardid[i], TITLES[i], AREANAMES[i], AREANAMESBN[i]);
            arrayList.add(areaHolder1);
        }
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(DataLoadingActivity.this, arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview


        adapter.notifyDataSetChanged();// Notify the adapter


    }

    private void populatRecyclerView2() {

        arrayList2.clear();

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(AREANAMESBN[getPos()].split(":")));
        for (int i = 0; i < list.size(); i++) {
            DataModel areaHolder1 = new DataModel(list.get(i));
            arrayList2.add(areaHolder1);
        }
        if (arrayList2.size() >= 4) {
            lLayout2 = new GridLayoutManager(DataLoadingActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        } else {
            lLayout2 = new GridLayoutManager(DataLoadingActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
        }
        recyclerViewarea.setHasFixedSize(false);
        recyclerViewarea.setLayoutManager(lLayout2);
        RecyclerView_AdapterArea adapter2 = new RecyclerView_AdapterArea(DataLoadingActivity.this, arrayList2);
        recyclerViewarea.setAdapter(adapter2);// set adapter on recyclerview


        adapter2.notifyDataSetChanged();// Notify the adapter

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
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
            if (((Long) result).longValue() == 0.0 && countofDb < NUMBER_OF_TASKS) { // Means the task is successful
                countofDb++;
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("KValue", countofDb);
                editor.apply();
                Log.d("tasks", "Tasks remaining: " + (NUMBER_OF_TASKS - countofDb));
                ToastMessageDisplay.setText(DataLoadingActivity.this.context, "তথ্য সংগ্রহ চলছে");
                ToastMessageDisplay.showText(DataLoadingActivity.this.context);
            }
        }

    }

    void Servercall() {
        getRequest(DataLoadingActivity.this, "http://kolorob.net/demo_new/api/sp/education?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {
                        if (status == AppConstants.SUCCESS_CODE) {
                            try {

                                JSONArray jo = new JSONArray(apiContent);
                                SavenewEdu(jo);
                                Log.d("done","as");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }

    void SavenewEdu(JSONArray jo) {
        JSONArray Edu = jo;
        EduNewDBTableMain eduNewDBTableMain = new EduNewDBTableMain(DataLoadingActivity.this);
        EduNewDBTableTraining eduNewDBTableTraining = new EduNewDBTableTraining(DataLoadingActivity.this);

        int Govcount = Edu.length();

        for (int i = 0; i < Govcount; i++) {
            try {

                JSONObject jsonObject2 = Edu.getJSONObject(i);
                EduNewModel eduNewModel =EduNewModel.parseEduNewModel(jsonObject2);
                eduNewDBTableMain.insertItem(eduNewModel);
                if (jsonObject2.has("training_details")) {
                    JSONArray edutrain=jsonObject2.getJSONArray("training_details");
                    int lenoftrain=edutrain.length();
                    for (int ii=0;ii<lenoftrain;ii++) {
                        JSONObject train = edutrain.getJSONObject(ii);


                        EduTrainingModel eduTrainingModel = EduTrainingModel.parseEduTrainingModel(train);
                        eduNewDBTableTraining.insertItem(eduTrainingModel);
                    }

                }



            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("count", String.valueOf(i));
        }
    }
    void SavenewHealth(JSONArray jo) {
        JSONArray Gov = jo;
        HealthNewDBTableMain govNewDBTable = new HealthNewDBTableMain(DataLoadingActivity.this);
        HealthNewDBTablePharma healthNewDBTablePharma = new HealthNewDBTablePharma(DataLoadingActivity.this);
        HealthNewDBTableHospital healthNewDBTableHospital=new HealthNewDBTableHospital(DataLoadingActivity.this);
        int Govcount = Gov.length();

        for (int i = 0; i < Govcount; i++) {
            try {

                JSONObject jsonObject2 = Gov.getJSONObject(i);
                HealthNewDBModelMain healthNewDBModelMain =HealthNewDBModelMain.parseHealthNewDBModelMain(jsonObject2);
                govNewDBTable.insertItem(healthNewDBModelMain);
                if (jsonObject2.has("health_pharmacy")) {
                    JSONObject pharmacy = jsonObject2.getJSONObject("health_pharmacy");


                    HealthNewDBModelPharmacy healthNewDBModelPharmacy = HealthNewDBModelPharmacy.parseHealthNewDBModelPharmacy(pharmacy, jsonObject2.getInt("id"));
                    healthNewDBTablePharma.insertItem(healthNewDBModelPharmacy);


                }
                else  if (jsonObject2.has("health_hospital")) {
                    JSONObject hospital = jsonObject2.getJSONObject("health_hospital");


                    HealthNewDBModelHospital healthNewDBModelHospital = HealthNewDBModelHospital.parseHealthNewDBModelHospital(hospital, jsonObject2.getInt("id"));
                    healthNewDBTableHospital.insertItem(healthNewDBModelHospital);


                }


            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("count", String.valueOf(i));
        }
    }
}




