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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterArea;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterCityCorporation;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterWard;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.CityCorporationTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
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
import demo.kolorob.kolorobdemoversion.interfaces.RecyclerViewHolder;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.model.StoredArea;
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
    View view = null, areaview, wardview, cityview;;
    Button submit;
    Boolean firstRun, new_categories_on_update, permission = false;
    int countofDb = 0;
    JSONObject allData;
    String AreaNameBn, Location, keyword;
    private static RecyclerView recyclerViewWard, recyclerViewArea, recyclerViewCity;
    ImageView rotateImage;
    TextView ward, area, city;
    private GridLayoutManager lLayout, lLayout2;
    ArrayList<SubCategoryItemNew> si3 = new ArrayList<>();
    private Animation mEnterAnimation, mExitAnimation;
    private int height, width, pos, posAreaInt = -1;

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
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_selection_activity);
        ward = (TextView) findViewById(R.id.chooseward);
        area = (TextView) findViewById(R.id.choosearea);
        city = (TextView) findViewById(R.id.ccorporation);
        submit = (Button) findViewById(R.id.submittoserverarea);

        //submit1 = (Button) findViewById(R.id.submittoserverarea);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        firstRun = settings.getBoolean("firstRunUp", false);
        new_categories_on_update = settings.getBoolean("new_categories_on_update", true);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (getPosAreaInt() == -1) {
                    ToastMessageDisplay.setText(DataLoadingActivity.this, "এলাকা নির্বাচন করুন");
                    ToastMessageDisplay.showText(DataLoadingActivity.this);
                } else {

                    keyword = areaList.get(getPosAreaInt()).getArea_keyword();
                    String lat = areaList.get(getPosAreaInt()).getLat();

                    if (lat.equals(null) || keyword.equals(null)) { //no data available for these two area so
                        ToastMessageDisplay.setText(DataLoadingActivity.this, "তথ্য পাওয়া যায় নি");
                        ToastMessageDisplay.showText(DataLoadingActivity.this);
                    } else {
                        //setLocation(areaList.get(getPosAreaInt()).getLat() + ":" + areaList.get(getPosAreaInt()).getLon());
                        if (AppUtils.isNetConnected(getApplicationContext())) {
                            Servercall();
                        } else {
                            AlertMessage.showMessage(DataLoadingActivity.this, " দুঃখিত", "আপ্নার ডিভাইসের ইন্টারনেট চালু করুন");
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

        populatRecyclerViewCity();  // city
        populatRecyclerViewWard(1); // ward
        populatRecyclerViewArea(1); // area


        if (firstRun == false || new_categories_on_update == true)
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

                         populatRecyclerViewWard(ccList.get(position).getId());

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

                        populatRecyclerViewArea(wardList.get(position).getId());

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

    private void populatRecyclerViewCity() {


        CityCorporationTable ccTable = new CityCorporationTable();
        ccList = ccTable.getAllCCs();

        RecyclerView_AdapterCityCorporation adapter = new RecyclerView_AdapterCityCorporation(DataLoadingActivity.this, ccList);
        recyclerViewCity.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void populatRecyclerViewWard(int cc_id) {


        WardTable wardTable = new WardTable();
        wardList = wardTable.getAllWards(cc_id);

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

    private void populatRecyclerViewArea(int ward_id) {



        AreaTable areaTable = new AreaTable();
        areaList = areaTable.getAllAreas(ward_id);

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
                Log.d("tasks", "Tasks remaining: " + (NUMBER_OF_TASKS - countofDb));//number of tasks equivalent to how many api data is being stored
                ToastMessageDisplay.setText(DataLoadingActivity.this.context, "তথ্য সংগ্রহ চলছে");
                ToastMessageDisplay.showText(DataLoadingActivity.this.context);
            }
        }

    }

    void Servercall() {

        if (firstRun == false || new_categories_on_update == true) //we store category and and subcategories only for first time. Thus number_of_tasks been incremented when firstRun is false
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
                    SharedPreferences settings = getSharedPreferences("prefs", 0);
                    //SharedPreferences.Editor editor = settings.edit();
                    //  editor.putString("First", first);

                    //  editor.apply();
                    handler.removeCallbacks(this);
                    SharedPreferencesHelper.setifcommentedalready(DataLoadingActivity.this, null, SharedPreferencesHelper.getUname(DataLoadingActivity.this), "no");
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
        if (firstRun == false || new_categories_on_update == true) {
            getRequest(DataLoadingActivity.this, "http://kolorob.net/kolorob-new-demo/api/categories?", new VolleyApiCallback() {
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



            if(new_categories_on_update == true){
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("new_categories_on_update", false).apply();
            }
        }
        getRequest(DataLoadingActivity.this, "http://kolorob.net/kolorob-new-demo/api/getspbyarea?ward=" + wardClicked + "&area=" + areaClicked, new VolleyApiCallback() {
            @Override
            public void onResponse(int status, String apiContent) {
                if (status == AppConstants.SUCCESS_CODE) {

                    try {

                        allData = new JSONObject(apiContent);
                        Log.d("AllData", "********" + allData);
                        if (allData.length() == 0) {
                            ToastMessageDisplay.setText(DataLoadingActivity.this, "তথ্য নেই. দয়া করে অন্য  এলাকা নির্বাচন করুন");
                            ToastMessageDisplay.showText(DataLoadingActivity.this);
                        } else { //checking category label and parsing in different threads so that parsing time get minimised
                            if (allData.has("Education")) {
                                new SavenewEduTask(DataLoadingActivity.this).execute(allData.getJSONArray("Education"));
                                //   SavenewEdu(allData.getJSONArray("Education"));
                            }

                            if (allData.has("Finance")) {
                                new SavenewFinanceTask(DataLoadingActivity.this).execute(allData.getJSONArray("Finance"));
                                //SavenewFin(allData.getJSONArray("Finance"));

                            }

                            if (allData.has("Health")) {
                                new SaveHealthtDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Health"));
                                //SavenewHealth(allData.getJSONArray("Health"));

                            }

                            if (allData.has("Legal")) {
                                new SaveLegalDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Legal"));
                                // SavenewLegal(allData.getJSONArray("Legal"));
                            }


                            if (allData.has("Government")) {
                                new SavenewGovTask(DataLoadingActivity.this).execute(allData.getJSONArray("Government"));
                                // SavenewGov(allData.getJSONArray("Government"));
                            }


                            if (allData.has("NGO")) {
                                new SaveNgoDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("NGO"));
                                // SavenewGov(allData.getJSONArray("Government"));
                            }


                            if (allData.has("Entertainment")) {
                                // SavenewEntertainment(allData.getJSONArray("Entertainment"));
                                new SaveEntertainmentDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Entertainment"));
                            }


                            if (allData.has("Religious Shelter")) {
                                // SavenewEntertainment(allData.getJSONArray("Entertainment"));
                                new SaveReligiousDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Religious Shelter"));
                            }

                        }
                        int p = allData.length();
                        Log.d("Doneall", String.valueOf(p));
                        StoredAreaTable storedAreaTable = new StoredAreaTable(DataLoadingActivity.this);
                        String A = areaList.get(posAreaInt).getArea_bn();
         //               Log.e("Get Area Name with Data", A);
                        //String LOC = getLocation();
                        storedAreaTable.insertItem(wardClicked, keyword, A, areaList.get(getPosAreaInt()).getLat(), areaList.get(getPosAreaInt()).getLon());

                        SharedPreferences settings = getSharedPreferences("prefs", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("ward", wardClicked);
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
            EduNewDBTableMain eduNewDBTableMain = new EduNewDBTableMain(DataLoadingActivity.this);
            EduNewDBTableTraining eduNewDBTableTraining = new EduNewDBTableTraining(DataLoadingActivity.this);
            EduNewDBTableSchool eduNewDBTableSchool = new EduNewDBTableSchool(DataLoadingActivity.this);

            int Govcount = Edu.length();


            for (int i = 0; i < Govcount; i++) {
                try {
                    if (!Edu.isNull(i)) {
                        JSONObject jsonObject2 = Edu.getJSONObject(i);
                        EduNewModel eduNewModel = EduNewModel.parseEduNewModel(jsonObject2);
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
                        if (jsonObject2.has("education_school")) {
                            JSONObject school = jsonObject2.getJSONObject("education_school");
                            EduNewSchoolModel eduNewSchoolModel = EduNewSchoolModel.parseEduNewSchoolModel(school, jsonObject2.getInt("id"));
                            eduNewDBTableSchool.insertItem(eduNewSchoolModel);
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

    class SaveEntertainmentDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveEntertainmentDataTask(Context ctx) {
            super(ctx);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Ent = jsonArrays[0];
            EntNewDBTable entNewDBTable = new EntNewDBTable(DataLoadingActivity.this);

            int Entcount = Ent.length();

            for (int i = 0; i < Entcount; i++) {
                try {
                    if (!Ent.isNull(i)) {
                        JSONObject jsonObject2 = Ent.getJSONObject(i);
                        EntertainmentNewDBModel entertainmentNewDBModel = EntertainmentNewDBModel.parseEntertainmentNewDBModel(jsonObject2);
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
            GovNewDBTable govNewDBTable = new GovNewDBTable(DataLoadingActivity.this);


            int Govcount = Gov.length();
            Log.d("GovData", "********" + Govcount);
            for (int i = 0; i < Govcount; i++) {
                try {
                    if (!Gov.isNull(i)) {
                        JSONObject jsonObject2 = Gov.getJSONObject(i);
                        GovernmentNewDBModel governmentNewDBModel = GovernmentNewDBModel.parseGovernmentNewDBModel(jsonObject2);
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
            LegalAidNewDBTable legalAidNewDBTable = new LegalAidNewDBTable(DataLoadingActivity.this);

            int Legalcount = Legal.length();
            Log.d("LegalData", "********" + Legalcount);
            for (int i = 0; i < Legalcount; i++) {
                try {
                    if (!Legal.isNull(i)) {
                        JSONObject jsonObject2 = Legal.getJSONObject(i);
                        LegalAidNewDBModel legalAidNewDBModel = LegalAidNewDBModel.parseLegalAidNewDBModel(jsonObject2);
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
            FinNewDBTable finNewDBTable = new FinNewDBTable(DataLoadingActivity.this);


            int Fincount = Fin.length();
            Log.d("FinData", "********" + Fincount);

            for (int i = 0; i < Fincount; i++) {
                try {
                    if (!Fin.isNull(i)) {
                        JSONObject jsonObject2 = Fin.getJSONObject(i);
                        FinancialNewDBModel financialNewDBModel = FinancialNewDBModel.parseFinancialNewDBModel(jsonObject2);
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
            HealthNewDBTableMain govNewDBTable = new HealthNewDBTableMain(DataLoadingActivity.this);
            HealthNewDBTablePharma healthNewDBTablePharma = new HealthNewDBTablePharma(DataLoadingActivity.this);
            HealthNewDBTableHospital healthNewDBTableHospital = new HealthNewDBTableHospital(DataLoadingActivity.this);

            int Helcount = Hel.length();
            Log.d("HealthData", "********" + Helcount);

            for (int i = 0; i < Helcount; i++) {
                try {
                    if (!Hel.isNull(i)) {
                        JSONObject jsonObject2 = Hel.getJSONObject(i);
                        HealthNewDBModelMain healthNewDBModelMain = HealthNewDBModelMain.parseHealthNewDBModelMain(jsonObject2);
                        govNewDBTable.insertItem(healthNewDBModelMain);
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
            NGONewDBTable ngoNewDBTable = new NGONewDBTable(DataLoadingActivity.this);

            int Ngocount = Ngo.length();
            Log.d("NgoData", "********" + Ngocount);
            for (int i = 0; i < Ngocount; i++) {
                try {
                    if (!Ngo.isNull(i)) {
                        JSONObject jsonObject2 = Ngo.getJSONObject(i);
                        NGONewDBModel ngoNewDBModel = NGONewDBModel.parseNgoNewDBModel(jsonObject2);
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
            ReligiousNewDBTable religiousNewDBTable = new ReligiousNewDBTable(DataLoadingActivity.this);

            int Religiouscount = Religious.length();
            Log.d("ReligiousData", "********" + Religiouscount);
            for (int i = 0; i < Religiouscount; i++) {
                try {
                    if (!Religious.isNull(i)) {
                        JSONObject jsonObject2 = Religious.getJSONObject(i);
                        ReligiousNewDBModel religiousNewDBModel = ReligiousNewDBModel.parseReligiousNewDBModel(jsonObject2);
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

            int legalaidServiceProviderCount = subcat.length();

            for (int i = 0; i < legalaidServiceProviderCount; i++) {
                try {
                    JSONObject jo = subcat.getJSONObject(i);
                    SubCategoryItemNew et = SubCategoryItemNew.parseSubCategoryItem(jo);
                    subCategoryTableNew.insertItem(et);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }

            si3 = subCategoryTableNew.getAllSubCat();
            si3.size();

            return new Long(0);
        }
    }
}