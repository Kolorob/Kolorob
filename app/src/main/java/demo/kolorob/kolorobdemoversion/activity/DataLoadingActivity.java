package demo.kolorob.kolorobdemoversion.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterArea;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterCityCorporation;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterWard;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.CityCorporationTable;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableHospital;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.database.StoredAreaTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.database.WardTable;
import demo.kolorob.kolorobdemoversion.interfaces.ItemClickSupport;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
import demo.kolorob.kolorobdemoversion.model.Ward;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class DataLoadingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    private static int NUMBER_OF_TASKS = 6;
    View view = null, areaview, wardview, cityview;
    Button submit;
    Boolean firstRun, firstRunUpdate, permission = false;
    int countofDb = 0;
    JSONObject allData;
    String AreaNameBn, Location, keyword;
    private static RecyclerView recyclerViewWard, recyclerViewArea, recyclerViewCity;
    ImageView rotateImage;
    TextView ward, area, city;
    private GridLayoutManager lLayout, lLayout2;

    private Animation mEnterAnimation, mExitAnimation;
    private int pos, posAreaInt = -1;

    ArrayList <CityCorporation> ccList = new ArrayList<>();
    ArrayList <Ward> wardList = new ArrayList<>();
    ArrayList <Area> areaList = new ArrayList<>();

    String cityClicked, wardClicked, areaClicked;



    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPosAreaInt() {
        return posAreaInt;
    }

    public void setPosAreaInt(int posAreaInt) {
        this.posAreaInt = posAreaInt;
    }

    public View getCityview() {
        return cityview;
    }

    public void setCityview(View cityview) {
        this.cityview = cityview;
    }

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


    public String getAreaNameBn() {
        return AreaNameBn;
    }

    public void setAreaNameBn(String areaNameBn) {
        AreaNameBn = areaNameBn;
    }


    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }





    private AnimationDrawable frameAnimation;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //start download now

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_selection_activity);
        ward = (TextView) findViewById(R.id.chooseward);
        area = (TextView) findViewById(R.id.choosearea);
        city = (TextView) findViewById(R.id.ccorporation);
        submit = (Button) findViewById(R.id.submittoserverarea);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        SharedPreferences settings = getSharedPreferences("prefs", 0);
        firstRun = settings.getBoolean("firstRunUp", false);
        firstRunUpdate = settings.getBoolean("new_update_first_run", true);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (getPosAreaInt() == -1) {
                    ToastMessageDisplay.setText(DataLoadingActivity.this, "এলাকা নির্বাচন করুন");
                    ToastMessageDisplay.showText(DataLoadingActivity.this);
                } else {

                    keyword = areaList.get(getPosAreaInt()).getArea_keyword();
                    String lat = areaList.get(getPosAreaInt()).getLat();

                    Log.e("", "Keyword: " + keyword + "Lat: " +lat);

                    if (lat.length() < 1 || keyword.length() < 1) { //no data available for these areas
                        ToastMessageDisplay.setText(DataLoadingActivity.this, "তথ্য পাওয়া যায় নি");
                        ToastMessageDisplay.showText(DataLoadingActivity.this);
                    } else {

                        StoredAreaTable storedAreaTable = new StoredAreaTable(DataLoadingActivity.this);

                        if(storedAreaTable.isAreaStored(wardClicked, areaClicked)){

                            SharedPreferences settings = getSharedPreferences("prefs", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("_ward", wardClicked); // store ward and area from stored area in pref
                            //to use in next activity
                            editor.putString("areakeyword", areaClicked);
                            editor.apply();
                            Intent intent = new Intent(DataLoadingActivity.this, PlaceDetailsActivityNewLayout.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            if (AppUtils.isNetConnected(getApplicationContext())) {
                                Servercall();
                            } else {
                                AlertMessage.showMessage(DataLoadingActivity.this, " দুঃখিত", "আপনার ডিভাইসের ইন্টারনেট চালু করুন");
                            }
                        }
                    }
                }


            }
        });
        initViews();
        mEnterAnimation = new AlphaAnimation(0f, 1f);
        mEnterAnimation.setDuration(600);
        mEnterAnimation.setFillAfter(true);

        mExitAnimation = new AlphaAnimation(1f, 0f);
        mExitAnimation.setDuration(600);
        mExitAnimation.setFillAfter(true);

        populateRecyclerViewCity();  // city
        populateRecyclerViewWard(1); // initially, populating wards for DNCC
        populateRecyclerViewArea(1); // initially, populating areas of first ward of DNCC


        if (firstRun == false || firstRunUpdate == true)
            runOverlay_ContinueMethod(); //run tutorial only if user is using the app for first time

        pos = 0;


        //first recyclerview for city

        ItemClickSupport.addTo(recyclerViewCity)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerViewCity, int position, View v) {
                         cityClicked = ccList.get(position).getCityCorporation_keyword();
                         recyclerViewArea.setAdapter(null);
                         Log.d("tasks", "position: " + position);

                         populateRecyclerViewWard(ccList.get(position).getId());

                        if (getCityview() == null) {
                            setCityview(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getCityview() != v) {
                            ((CardView) getCityview()).setCardBackgroundColor(Color.parseColor("#7f000000"));

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setCityview(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));

                    }
                });
//area cards


//first recyclerview for wards
        ItemClickSupport.addTo(recyclerViewWard)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        wardClicked = wardList.get(position).getWard_keyword();
                        setPos(position);

                        populateRecyclerViewArea(wardList.get(position).getId());

                        if (getWardview() == null) {
                            setWardview(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getWardview() != v) {
                            ((CardView) getWardview()).setCardBackgroundColor(Color.parseColor("#7f000000"));

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setWardview(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));

                    }
                });

//area cards
        ItemClickSupport.addTo(recyclerViewArea)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        ((CardView) v).setCardBackgroundColor(Color.WHITE);
                        setPosAreaInt(position);
                        areaClicked = areaList.get(position).getArea_keyword();

                        if (getAreaview() == null) {
                            setAreaview(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getAreaview() != v) {
                            ((CardView) getAreaview()).setCardBackgroundColor(Color.WHITE);

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setAreaview(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));

                }
                });
        context = this;

    }

    private void initViews() {

        recyclerViewCity = (RecyclerView)
                findViewById(R.id.cityrecycler_view);

        recyclerViewWard = (RecyclerView)
                findViewById(R.id.recycler_view);

        recyclerViewArea = (RecyclerView)
                findViewById(R.id.recycler_view2);


        //Set RecyclerView type according to intent value
        lLayout = new GridLayoutManager(DataLoadingActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerViewCity.setHasFixedSize(true);
        recyclerViewCity.setLayoutManager(lLayout);


    }

    private void runOverlay_ContinueMethod() {
        // the return handler is used to manipulate the cleanup of all the tutorial elements

        ChainTourGuide tourGuideCity = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("১ম ধাপ")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("১ম লিস্ট থেকে আপনার সিটি কর্পোরেশন খুঁজে নিন")
                        .setGravity(Gravity.BOTTOM)
                )
                // note that there is no Overlay here, so the default one will be used
                .playLater(city);

        ChainTourGuide tourGuideWard = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("২য় ধাপ")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("২য় লিস্টকে ডানে/বায়ে সরিয়ে আপনার ওয়ার্ড খুঁজে নিন")
                        .setGravity(Gravity.BOTTOM)
                )
                .playLater(ward);

        ChainTourGuide tourGuideArea = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("৩য় ধাপ")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("ওয়ার্ড অনুযায়ী এলাকার লিস্ট থেকে এলাকা খুজে নিচের 'জমা দিন' বাটনটি ক্লিক করুন।")
                        .setGravity(Gravity.BOTTOM)
                )
                .playLater(area);


        Sequence sequence = new Sequence.SequenceBuilder()
                .add(tourGuideCity, tourGuideWard, tourGuideArea)
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

    private void populateRecyclerViewCity() {


        CityCorporationTable ccTable = new CityCorporationTable();
        ccList = ccTable.getAllData();

        RecyclerView_AdapterCityCorporation adapter = new RecyclerView_AdapterCityCorporation(DataLoadingActivity.this, ccList);
        recyclerViewCity.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void populateRecyclerViewWard(int cc_id) {


        WardTable wardTable = new WardTable();
        wardList = wardTable.getDataFromForeignKey(cc_id);

        if (wardList.size() >= 4) {
            lLayout2 = new GridLayoutManager(DataLoadingActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        } else {
            lLayout2 = new GridLayoutManager(DataLoadingActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
        }
        recyclerViewWard.setHasFixedSize(false);
        recyclerViewWard.setLayoutManager(lLayout2);
        RecyclerView_AdapterWard adapter = new RecyclerView_AdapterWard(DataLoadingActivity.this, wardList);
        recyclerViewWard.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void populateRecyclerViewArea(int ward_id) {



        AreaTable areaTable = new AreaTable();
        areaList = areaTable.getDataFromForeignKey(ward_id);

        if (areaList.size() >= 4) {
            lLayout2 = new GridLayoutManager(DataLoadingActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        } else {
            lLayout2 = new GridLayoutManager(DataLoadingActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
        }
        recyclerViewArea.setHasFixedSize(false);
        recyclerViewArea.setLayoutManager(lLayout2);
        RecyclerView_AdapterArea adapter = new RecyclerView_AdapterArea(DataLoadingActivity.this, areaList);
        recyclerViewArea.setAdapter(adapter);


        adapter.notifyDataSetChanged();
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


    /*
    this function runs data loading task in asynctask
     */
     abstract class GenericSaveDBTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
        protected Context context;


        public GenericSaveDBTask(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected void onPostExecute(Result result) {
            if (((Long) result).longValue() == 0.0 && countofDb < NUMBER_OF_TASKS) { // Means the task is successful
                countofDb++;
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("KValue", countofDb);
                editor.apply();
                Log.d("tasks", "Tasks remaining: " + (NUMBER_OF_TASKS - countofDb));//number of tasks equivalent to how many api data is being stored
                ToastMessageDisplay.setText(DataLoadingActivity.this.context, "তথ্য সংগ্রহ চলছে");
                ToastMessageDisplay.showText(DataLoadingActivity.this.context);
            }
        }

    }

    void Servercall() {

        if (firstRun == false || firstRunUpdate == true) //we store category and and subcategories only for first time. Thus number_of_tasks been incremented when firstRun is false
        {
            NUMBER_OF_TASKS = 8;
        }
        LayoutInflater layoutInflater = LayoutInflater.from(DataLoadingActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.activity_waiting, null);
        final Dialog alertDialog = new Dialog(DataLoadingActivity.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final Handler handler = new Handler();
        Runnable runner = new Runnable() {
            int timeCounter = 0;

            @Override
            public void run() {


                if (DataLoadingActivity.this.countofDb >= NUMBER_OF_TASKS || timeCounter > 120000) {
                    overridePendingTransition(0, 0);

                    handler.removeCallbacks(this);
                    SharedPreferencesHelper.setIfCommentedAlready(DataLoadingActivity.this, null, SharedPreferencesHelper.getUname(DataLoadingActivity.this), "no");
                    Intent a = new Intent(DataLoadingActivity.this, PlaceDetailsActivityNewLayout.class); // Default Activity

                    frameAnimation.stop();
                    alertDialog.cancel();
                    startActivity(a);
                    return;

                }
                //Create a loop
                handler.postDelayed(this, 1000);
                timeCounter += 1000;

            }

        };
        handler.postDelayed(runner, 1000);

        rotateImage = (ImageView) promptView.findViewById(R.id.rotate_image);
        rotateImage.setBackgroundResource(R.drawable.frame_animation_list);
        frameAnimation = (AnimationDrawable) rotateImage.getBackground();
        frameAnimation.setOneShot(false);
        frameAnimation.start();

        if (firstRun == false || firstRunUpdate == true) {
            getRequest(DataLoadingActivity.this, "http://kolorob.net/kolorob-new-demo/api/categories_new?", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {

                                    JSONArray jo = new JSONArray(apiContent);

                                    Log.d("Category", "********" + apiContent);

                                    new SaveCategoryListTask(DataLoadingActivity.this).execute(jo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(DataLoadingActivity.this, "http://kolorob.net/kolorob-new-demo/api/refs? ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {


                                try {
                                    JSONArray jo = new JSONArray(apiContent);
                                    Log.d("AllData", "********" + jo);
                                    new SaveSubCategoryNewListTask(DataLoadingActivity.this).execute(jo);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );


            if(firstRunUpdate == true){
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("new_update_first_run", false).apply();
            }
        }
        getRequest(DataLoadingActivity.this, "http://kolorob.net/kolorob-new-demo/api/getspbyarea?ward=" + wardClicked + "&area=" + areaClicked, new VolleyApiCallback() {
            @Override
            public void onResponse(int status, String apiContent) {
                if (status == AppConstants.SUCCESS_CODE) {

                    try {

                        allData = new JSONObject(apiContent);
                        Log.d("AllData", "*********" + allData);
                        if (allData.length() == 0) {
                            ToastMessageDisplay.setText(DataLoadingActivity.this, "তথ্য নেই. দয়া করে অন্য  এলাকা নির্বাচন করুন");
                            ToastMessageDisplay.showText(DataLoadingActivity.this);
                        } else { //checking category label and parsing in different threads so that parsing time get minimised
                            if (allData.has("Education")) {
                                new SavenewEduTask(DataLoadingActivity.this).execute(allData.getJSONArray("Education"));
                            }

                            if (allData.has("Finance")) {
                                new SavenewFinanceTask(DataLoadingActivity.this).execute(allData.getJSONArray("Finance"));

                            }

                            if (allData.has("Health")) {
                                new SaveHealthtDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Health"));

                            }

                            if (allData.has("Legal")) {
                                new SaveLegalDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Legal"));

                            }


                            if (allData.has("Government")) {
                                new SavenewGovTask(DataLoadingActivity.this).execute(allData.getJSONArray("Government"));
                            }


                            if (allData.has("NGO")) {
                                new SaveNgoDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("NGO"));
                            }


                            if (allData.has("Entertainment")) {
                                new SaveEntertainmentDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Entertainment"));
                            }


                            if (allData.has("Religious Shelter")) {

                                new SaveReligiousDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Religious Shelter"));
                            }

                        }
                        int p = allData.length();
                        Log.d("Doneall", String.valueOf(p));
                        StoredAreaTable storedAreaTable = new StoredAreaTable(DataLoadingActivity.this);
                        WardTable wardTable = new WardTable(DataLoadingActivity.this);
                        Area area  = areaList.get(posAreaInt);

                        storedAreaTable.insertItem(area.getId(), wardTable.getNodeInfo(area.getWard_id()).getWard_keyword(), area.getArea_keyword(), area.getArea_bn(), area.getParentArea(), area.getLat(), area.getLon());
                        SharedPreferences settings = getSharedPreferences("prefs", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("_ward", wardClicked);
                        editor.putString("areakeyword", areaClicked);
                        editor.apply();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        });


    }


    class SavenewEduTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewEduTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonObjects) {
            JSONArray Edu = jsonObjects[0];
            CommonDBTable commonDBTable = new CommonDBTable(DataLoadingActivity.this);
            EduNewDBTableMain eduNewDBTableMain = new EduNewDBTableMain(DataLoadingActivity.this);
            EduNewDBTableTraining eduNewDBTableTraining = new EduNewDBTableTraining(DataLoadingActivity.this);
            EduNewDBTableSchool eduNewDBTableSchool = new EduNewDBTableSchool(DataLoadingActivity.this);
            EducationResultDetailsTable resultDetailsTable = new EducationResultDetailsTable(DataLoadingActivity.this);

            int eduCount = Edu.length();


            for (int i = 0; i < eduCount; i++) {
                try {
                    if (!Edu.isNull(i)) {
                        JSONObject jsonObject2 = Edu.getJSONObject(i);
                        EduNewModel eduNewModel = EduNewModel.parseEduNewModel(jsonObject2);
                        commonDBTable.insertItem(eduNewModel.getCommonModel());
                        eduNewDBTableMain.insertItem(eduNewModel);
                        if (jsonObject2.has("training_details")) {

                            JSONArray edutrain = jsonObject2.getJSONArray("training_details");
                            int lenoftrain = edutrain.length();
                            for (int ii = 0; ii < lenoftrain; ii++) {
                                JSONObject train = edutrain.getJSONObject(ii);
                                EduTrainingModel eduTrainingModel = EduTrainingModel.parseEduTrainingModel(train);
                                eduNewDBTableTraining.insertItem(eduTrainingModel);
                            }
                        }
                        if (jsonObject2.has("result_details")) {

                            JSONArray eduResult = jsonObject2.getJSONArray("result_details");
                            int lenofresult = eduResult.length();
                            for (int ii = 0; ii < lenofresult; ii++) {
                                JSONObject result = eduResult.getJSONObject(ii);
                                EducationResultItemNew educationResultItemNew = EducationResultItemNew.parseEducationResultItemNew(result);
                                resultDetailsTable.insertItem(educationResultItemNew);
                            }
                        }
                        if (jsonObject2.has("education_school")) {
                            JSONObject school = jsonObject2.getJSONObject("education_school");
                            EduNewSchoolModel eduNewSchoolModel = EduNewSchoolModel.parseEduNewSchoolModel(school);
                            eduNewDBTableSchool.insertItem(eduNewSchoolModel);
                        }
                        /*if(jsonObject2.has("result_details")){
                            JSONObject result = jsonObject2.getJSONObject("result_details");
                            EducationResultItemNew educationResultItemNew = EducationResultItemNew.parseEducationResultItemNew(result);
                            resultDetailsTable.insertItem(educationResultItemNew);
                        }*/
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    class SaveEntertainmentDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveEntertainmentDataTask(Context ctx) {
            super(ctx);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Ent = jsonArrays[0];
            CommonDBTable commonDBTable = new CommonDBTable(DataLoadingActivity.this);
            EntNewDBTable entNewDBTable = new EntNewDBTable(DataLoadingActivity.this);

            int Entcount = Ent.length();

            for (int i = 0; i < Entcount; i++) {
                try {
                    if (!Ent.isNull(i)) {
                        JSONObject jsonObject2 = Ent.getJSONObject(i);
                        EntertainmentNewDBModel entertainmentNewDBModel = EntertainmentNewDBModel.parseEntertainmentNewDBModel(jsonObject2);
                        commonDBTable.insertItem(entertainmentNewDBModel.getCommonModel());
                        entNewDBTable.insertItem(entertainmentNewDBModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }


    }

    class SavenewGovTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewGovTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Gov = jsonArrays[0];
            CommonDBTable commonDBTable = new CommonDBTable(DataLoadingActivity.this);
            GovNewDBTable govNewDBTable = new GovNewDBTable(DataLoadingActivity.this);


            int Govcount = Gov.length();
            Log.d("GovData", "********" + Govcount);
            for (int i = 0; i < Govcount; i++) {
                try {
                    if (!Gov.isNull(i)) {
                        JSONObject jsonObject2 = Gov.getJSONObject(i);
                        GovernmentNewDBModel governmentNewDBModel = GovernmentNewDBModel.parseGovernmentNewDBModel(jsonObject2);
                        commonDBTable.insertItem(governmentNewDBModel.getCommonModel());
                        govNewDBTable.insertItem(governmentNewDBModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    class SaveLegalDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveLegalDataTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Legal = jsonArrays[0];
            CommonDBTable commonDBTable = new CommonDBTable(DataLoadingActivity.this);
            LegalAidNewDBTable legalAidNewDBTable = new LegalAidNewDBTable(DataLoadingActivity.this);

            int Legalcount = Legal.length();
            Log.d("LegalData", "********" + Legalcount);
            for (int i = 0; i < Legalcount; i++) {
                try {
                    if (!Legal.isNull(i)) {
                        JSONObject jsonObject2 = Legal.getJSONObject(i);
                        LegalAidNewDBModel legalAidNewDBModel = LegalAidNewDBModel.parseLegalAidNewDBModel(jsonObject2);
                        commonDBTable.insertItem(legalAidNewDBModel.getCommonModel());
                        legalAidNewDBTable.insertItem(legalAidNewDBModel);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }


    }

    class SavenewFinanceTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewFinanceTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Fin = jsonArrays[0];
            CommonDBTable commonDBTable = new CommonDBTable(DataLoadingActivity.this);
            FinNewDBTable finNewDBTable = new FinNewDBTable(DataLoadingActivity.this);


            int Fincount = Fin.length();
            Log.d("FinData", "********" + Fincount);

            for (int i = 0; i < Fincount; i++) {
                try {
                    if (!Fin.isNull(i)) {
                        JSONObject jsonObject2 = Fin.getJSONObject(i);
                        FinancialNewDBModel financialNewDBModel = FinancialNewDBModel.parseFinancialNewDBModel(jsonObject2);
                        commonDBTable.insertItem(financialNewDBModel.getCommonModel());
                        finNewDBTable.insertItem(financialNewDBModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    class SaveHealthtDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveHealthtDataTask(Context ctx) {
            super(ctx);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Hel = jsonArrays[0];
            CommonDBTable commonDBTable = new CommonDBTable(DataLoadingActivity.this);
            HealthNewDBTableMain healthNewDBTable = new HealthNewDBTableMain(DataLoadingActivity.this);
            HealthNewDBTablePharma healthNewDBTablePharma = new HealthNewDBTablePharma(DataLoadingActivity.this);
            HealthNewDBTableHospital healthNewDBTableHospital = new HealthNewDBTableHospital(DataLoadingActivity.this);

            int Helcount = Hel.length();
            Log.d("HealthData", "********" + Helcount);

            for (int i = 0; i < Helcount; i++) {
                try {
                    if (!Hel.isNull(i)) {
                        JSONObject jsonObject2 = Hel.getJSONObject(i);
                        HealthNewDBModelMain healthNewDBModelMain = HealthNewDBModelMain.parseHealthNewDBModelMain(jsonObject2);
                        commonDBTable.insertItem(healthNewDBModelMain.getCommonModel());
                        healthNewDBTable.insertItem(healthNewDBModelMain);

                        if (jsonObject2.has("health_pharmacy")) {
                            JSONObject pharmacy = jsonObject2.getJSONObject("health_pharmacy");
                            HealthNewDBModelPharmacy healthNewDBModelPharmacy = HealthNewDBModelPharmacy.parseHealthNewDBModelPharmacy(pharmacy, jsonObject2.getInt("id"));
                            healthNewDBTablePharma.insertItem(healthNewDBModelPharmacy);
                        }
                        if (jsonObject2.has("health_hospital")) {
                            JSONObject hospital = jsonObject2.getJSONObject("health_hospital");
                            HealthNewDBModelHospital healthNewDBModelHospital = HealthNewDBModelHospital.parseHealthNewDBModelHospital(hospital, jsonObject2.getInt("id"));
                            healthNewDBTableHospital.insertItem(healthNewDBModelHospital);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    class SaveNgoDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveNgoDataTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Ngo = jsonArrays[0];
            CommonDBTable commonDBTable = new CommonDBTable(DataLoadingActivity.this);
            NGONewDBTable ngoNewDBTable = new NGONewDBTable(DataLoadingActivity.this);

            int Ngocount = Ngo.length();
            Log.d("NgoData", "********" + Ngocount);
            for (int i = 0; i < Ngocount; i++) {
                try {
                    if (!Ngo.isNull(i)) {
                        JSONObject jsonObject2 = Ngo.getJSONObject(i);
                        NGONewDBModel ngoNewDBModel = NGONewDBModel.parseNgoNewDBModel(jsonObject2);
                        commonDBTable.insertItem(ngoNewDBModel.getCommonModel());
                        ngoNewDBTable.insertItem(ngoNewDBModel);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }


    }

    class SaveReligiousDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveReligiousDataTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Religious = jsonArrays[0];
            CommonDBTable commonDBTable = new CommonDBTable(DataLoadingActivity.this);
            ReligiousNewDBTable religiousNewDBTable = new ReligiousNewDBTable(DataLoadingActivity.this);

            int Religiouscount = Religious.length();
            Log.d("ReligiousData", "********" + Religiouscount);
            for (int i = 0; i < Religiouscount; i++) {
                try {
                    if (!Religious.isNull(i)) {
                        JSONObject jsonObject2 = Religious.getJSONObject(i);
                        ReligiousNewDBModel religiousNewDBModel = ReligiousNewDBModel.parseReligiousNewDBModel(jsonObject2);
                        commonDBTable.insertItem(religiousNewDBModel.getCommonModel());
                        religiousNewDBTable.insertItem(religiousNewDBModel);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }


    }

    class SaveCategoryListTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveCategoryListTask(Context ctx) {
            super(ctx);
        }

        @Override
        protected Long doInBackground(JSONArray... categoryArrays) {
            JSONArray categoryArray = categoryArrays[0];
            CategoryTable catTable = new CategoryTable(DataLoadingActivity.this);
            catTable.dropTable();
            int catCount = categoryArray.length();
            for (int i = 0; i < catCount; i++) {
                try {
                    JSONObject jo = categoryArray.getJSONObject(i);
                    CategoryItem ci = CategoryItem.parseCategoryItem(jo);
                    catTable.insertItem(ci);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    class SaveSubCategoryNewListTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveSubCategoryNewListTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... categoryArrays) {
            JSONArray subcat = categoryArrays[0];
            SubCategoryTableNew subCategoryTableNew = new SubCategoryTableNew(DataLoadingActivity.this);
            subCategoryTableNew.dropTable();

            int count = subcat.length();

            for (int i = 0; i < count; i++) {
                try {
                    JSONObject jo = subcat.getJSONObject(i);
                    SubCategoryItemNew et = SubCategoryItemNew.parseSubCategoryItem(jo);
                    subCategoryTableNew.insertItem(et);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }


            return new Long(0);
        }
    }
}