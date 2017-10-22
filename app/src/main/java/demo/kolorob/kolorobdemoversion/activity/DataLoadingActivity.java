package demo.kolorob.kolorobdemoversion.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
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
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveCategoryDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveEducationDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveEntertainmentDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveFinancialDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveGovernmentDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveHealthDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveLegalDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveNgoDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveReferenceDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveShelterDBTask;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterArea;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterCityCorporation;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterWard;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.database.CityCorporationTable;
import demo.kolorob.kolorobdemoversion.database.StoredAreaTable;
import demo.kolorob.kolorobdemoversion.database.WardTable;
import demo.kolorob.kolorobdemoversion.interfaces.ItemClickSupport;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;
import demo.kolorob.kolorobdemoversion.model.StoredArea;
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

    static int countofDb = 0;
    private static int NUMBER_OF_TASKS = 6;
    private static RecyclerView recyclerViewWard, recyclerViewArea, recyclerViewCity;
    Context context;
    View view = null, areaview, wardview, cityview;
    Button submit;
    Boolean firstRun, firstRunUpdate;
    JSONObject allData;
    String AreaNameBn, Location, keyword;
    ImageView rotateImage;
    TextView ward, area, city;
    ArrayList<CityCorporation> ccList = new ArrayList<>();
    ArrayList<Ward> wardList = new ArrayList<>();
    ArrayList<Area> areaList = new ArrayList<>();
    String cityClicked, wardClicked, areaClicked;
    private GridLayoutManager lLayout, lLayout2;
    private Animation mEnterAnimation, mExitAnimation;
    private int pos, posAreaInt = -1;
    private AnimationDrawable frameAnimation;


    public static int getCountofDb() {
        return countofDb;
    }

    public static void setCountofDb(int count) {
        countofDb = count;
    }

    public static int getNumberOfTasks() {
        return NUMBER_OF_TASKS;
    }

    public static void setNumberOfTasks(int numberOfTasks) {
        NUMBER_OF_TASKS = numberOfTasks;
    }

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
        firstRunUpdate = settings.getBoolean("update_first_run", true);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (getPosAreaInt() == -1) {
                    ToastMessageDisplay.setText(DataLoadingActivity.this, "এলাকা নির্বাচন করুন");
                    ToastMessageDisplay.showText(DataLoadingActivity.this);
                } else {

                    keyword = areaList.get(getPosAreaInt()).getArea_keyword();
                    String lat = areaList.get(getPosAreaInt()).getLat();

                    Log.e("", "Keyword: " + keyword + "Lat: " + lat);

                    if (lat.length() < 1 || keyword.length() < 1) { //no data available for these areas
                        ToastMessageDisplay.setText(DataLoadingActivity.this, "তথ্য পাওয়া যায় নি");
                        ToastMessageDisplay.showText(DataLoadingActivity.this);
                    } else {

                        StoredAreaTable storedAreaTable = new StoredAreaTable(DataLoadingActivity.this);

                        if (storedAreaTable.isAreaStored(wardClicked, areaClicked)) {

                            SharedPreferences settings = getSharedPreferences("prefs", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("_ward", wardClicked); // store ward and area from stored area in pref
                            //to use in next activity
                            editor.putString("areakeyword", areaClicked);
                            editor.apply();
                            Intent intent = new Intent(DataLoadingActivity.this, PlaceDetailsActivityNewLayout.class);
                            startActivity(intent);
                            finish();
                        } else {
                            if (AppUtils.isNetConnected(getApplicationContext())) {
                                serverCall();
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


        if (!firstRun || firstRunUpdate)
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


        CityCorporationTable ccTable = new CityCorporationTable(DataLoadingActivity.this);
        ccList = ccTable.getAllData();

        RecyclerView_AdapterCityCorporation adapter = new RecyclerView_AdapterCityCorporation(DataLoadingActivity.this, ccList);
        recyclerViewCity.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void populateRecyclerViewWard(int cc_id) {


        WardTable wardTable = new WardTable(DataLoadingActivity.this);
        wardList = wardTable.getDataListFromForeignKey(cc_id);

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


        AreaTable areaTable = new AreaTable(DataLoadingActivity.this);
        areaList = areaTable.getDataListFromForeignKey(ward_id);

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


    void serverCall() {

        if (!firstRun || firstRunUpdate) //we store category and and subcategories only for first time. Thus number_of_tasks been incremented when firstRun is false
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


                if (countofDb >= NUMBER_OF_TASKS || timeCounter > 120000) {
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

        if (!firstRun || firstRunUpdate) {
            getRequest(DataLoadingActivity.this, "http://kolorob.net/kolorob-new-demo/api/categories_new?", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {

                                    JSONArray jo = new JSONArray(apiContent);

                                    new SaveCategoryDBTask(DataLoadingActivity.this).execute(jo);

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
                                    new SaveReferenceDBTask(DataLoadingActivity.this).execute(jo);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );


            if (firstRunUpdate) {
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("update_first_run", false).apply();
            }
        }

        if(wardClicked == null) wardClicked = wardList.get(0).getWard_keyword();

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
                                new SaveEducationDBTask(DataLoadingActivity.this).execute(allData.getJSONArray("Education"));
                            }

                            if (allData.has("Finance")) {
                                new SaveFinancialDBTask(DataLoadingActivity.this).execute(allData.getJSONArray("Finance"));

                            }

                            if (allData.has("Health")) {
                                new SaveHealthDBTask(DataLoadingActivity.this).execute(allData.getJSONArray("Health"));

                            }

                            if (allData.has("Legal")) {
                                new SaveLegalDBTask(DataLoadingActivity.this).execute(allData.getJSONArray("Legal"));

                            }


                            if (allData.has("Government")) {
                                new SaveGovernmentDBTask(DataLoadingActivity.this).execute(allData.getJSONArray("Government"));
                            }


                            if (allData.has("NGO")) {
                                new SaveNgoDBTask(DataLoadingActivity.this).execute(allData.getJSONArray("NGO"));
                            }


                            if (allData.has("Entertainment")) {
                                new SaveEntertainmentDBTask(DataLoadingActivity.this).execute(allData.getJSONArray("Entertainment"));
                            }


                            if (allData.has("Religious Shelter")) {

                                new SaveShelterDBTask(DataLoadingActivity.this).execute(allData.getJSONArray("Religious Shelter"));
                            }

                        }
                        int p = allData.length();
                        Log.d("Doneall", String.valueOf(p));
                        StoredAreaTable storedAreaTable = new StoredAreaTable(DataLoadingActivity.this);
                        WardTable wardTable = new WardTable(DataLoadingActivity.this);
                        Area area = areaList.get(posAreaInt);

                        storedAreaTable.insertItem(new StoredArea(area.getId(), wardTable.getNodeInfo(area.getWard_id()).getWard_keyword(), area.getArea_keyword(), area.getArea_bn(), area.getParentArea(), area.getLat(), area.getLon()));
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

}