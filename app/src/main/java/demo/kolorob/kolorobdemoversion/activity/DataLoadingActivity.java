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
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;


import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapter;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterArea;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterCityCorporation;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterDistrict;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterWard;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.CityCorporationTable;
import demo.kolorob.kolorobdemoversion.database.DistrictTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableChamber;
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
import demo.kolorob.kolorobdemoversion.model.District;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthModelChamber;
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
import demo.kolorob.kolorobdemoversion.utils.Lg;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class DataLoadingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private AnimationDrawable frameAnimation;

    private RecyclerView recyclerViewWard, recyclerViewArea, recyclerViewCity, recyclerViewDistrict;

    int NUMBER_OF_TASKS = 8;
    Context context;
    private View areaView, wardView, cityView, districtView;
    //Button submit;
    Boolean firstRun, firstRunUpdate;
    JSONObject allData;
    String Location;
    ImageView rotateImage;
    TextView ward, area, city, district;


    ListView listView;
    ListViewAdapter listViewAdapter;
    ArrayList<Area> areaArrayList = new ArrayList<>();


    ArrayList<District> districtList = new ArrayList<>();
    ArrayList<CityCorporation> ccList = new ArrayList<>();
    ArrayList<Ward> wardList = new ArrayList<>();
    ArrayList<Area> areaList = new ArrayList<>();

    String districtClicked, cityClicked, wardClicked, areaClicked;

    private Integer counter = new Integer(0);
    private GridLayoutManager lLayout2;
    private Animation mEnterAnimation, mExitAnimation;
    private int pos, posAreaInt = -1, ward_position = -1;
    boolean downloaded = false;
    private LinearLayout seletctionLayout, districtLayout, ccLayout, wardLayout, areaLayout;
    private RelativeLayout searchLayout;
    private SearchView searchView;


    public void setCounter(int counter){
        this.counter = counter;
    }

    public Integer getCounter(){
        return counter;
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

    public View getDistrictView() {
        return districtView;
    }

    public void setDistrictView(View districtView) {
        this.districtView = districtView;
    }

    public View getCityView() {
        return cityView;
    }

    public void setCityView(View cityView) {
        this.cityView = cityView;
    }

    public View getAreaView() {
        return areaView;
    }

    public void setAreaView(View areaView) {
        this.areaView = areaView;
    }

    public View getWardView() {
        return wardView;
    }

    public void setWardView(View wardView) {
        this.wardView = wardView;
    }


    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public void onBackPressed() {

        Lg.e("back", "pressed");
        super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode != KeyEvent.KEYCODE_BACK && super.onKeyDown(keyCode, event);
    }




        /*if (doubleBackToExitPressedOnce) {


            super.onBackPressed();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
        }




        ToastMessageDisplay.setText(context, getString(R.string.press_back_to_exit));
        ToastMessageDisplay.showText(this);
        Log.d("In on Back Pressed","==========");
        this.doubleBackToExitPressedOnce = true;


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //start download now

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.place_selection_activity);

        searchLayout = (RelativeLayout) findViewById(R.id.searchLayout);
        seletctionLayout = (LinearLayout) findViewById(R.id.selectionLayout);


        districtLayout = (LinearLayout) findViewById(R.id.districtLayout);
        ccLayout = (LinearLayout) findViewById(R.id.ccLayout);
        wardLayout = (LinearLayout) findViewById(R.id.wardLayout);
        areaLayout = (LinearLayout) findViewById(R.id.areaLayout);

// start


        listView = (ListView) findViewById(R.id.listview);
        areaArrayList = new AreaTable(context).getAllData();


        listViewAdapter = new ListViewAdapter(this, areaArrayList);

        // Binds the Adapter to the ListView
        listView.setAdapter(listViewAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Area areaSelected = areaArrayList.get(i);
                Ward wardSelected = new WardTable(context).getNodeInfo(areaSelected.getWard_id());

                wardClicked = wardSelected.getWard_keyword();
                areaClicked = areaSelected.getArea_keyword();

                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("_ward", wardClicked);
                editor.putString("areakeyword", areaClicked);
                editor.putInt("areaID", areaSelected.getId());

                editor.apply();


                StoredAreaTable storedAreaTable = new StoredAreaTable(context);

                if (storedAreaTable.isAreaStored(wardClicked, areaClicked)) {

                    Intent intent = new Intent(context, PlaceDetailsActivityNewLayout.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (AppUtils.isNetConnected(getApplicationContext())) {
                        serverCall();
                    } else {
                        AlertMessage.showMessage(context, getString(R.string.sorry), getString(R.string.connect_to_internet));
                    }
                }
            }
        });

        // Locate the EditText in listview_main.xml
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);


        district = (TextView) findViewById(R.id.chooseDistrict);
        ward = (TextView) findViewById(R.id.chooseward);
        area = (TextView) findViewById(R.id.choosearea);
        city = (TextView) findViewById(R.id.ccorporation);
        //submit = (Button) findViewById(R.id.submittoserverarea);

        searchView = (SearchView) findViewById(R.id.searchView); // inititate a search view
        CharSequence query = searchView.getQuery(); // get the query string currently in the text field


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        SharedPreferences settings = getSharedPreferences("prefs", 0);
        firstRun = settings.getBoolean("firstRunUp", false);
        firstRunUpdate = settings.getBoolean("update_first_run", true);

        initViews();

        mEnterAnimation = new AlphaAnimation(0f, 1f);
        mEnterAnimation.setDuration(600);
        mEnterAnimation.setFillAfter(true);

        mExitAnimation = new AlphaAnimation(1f, 0f);
        mExitAnimation.setDuration(600);
        mExitAnimation.setFillAfter(true);

        populateRecyclerViewDistrict(); // district
        /*populateRecyclerViewCity(1);  // initially, populating CCs of Dhaka
        populateRecyclerViewWard(1); // initially, populating wards for DNCC
        populateRecyclerViewArea(1); // initially, populating areas of first ward of DNCC
        */

        if (!firstRun || firstRunUpdate)
            // runOverlay_ContinueMethod(); //run tutorial only if user is using the app for first time

            pos = 0;


        ItemClickSupport.addTo(recyclerViewDistrict)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        districtClicked = districtList.get(position).getDistrict_keyword();

                        recyclerViewWard.setAdapter(null);
                        recyclerViewArea.setAdapter(null);
                        Log.d("tasks", "position: " + position);


                        if (districtClicked.equals(AppConstants.DISTRICT_2)) {

                            ccLayout.setVisibility(View.GONE);
                            populateRecyclerViewWard(4);
                        } else {
                            ccLayout.setVisibility(View.VISIBLE);
                            populateRecyclerViewCity(districtList.get(position).getId());
                        }

                        if (getDistrictView() == null) {
                            setDistrictView(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getDistrictView() != v) {
                            ((CardView) getDistrictView()).setCardBackgroundColor(Color.parseColor("#7f000000"));

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setDistrictView(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));

                    }
                });


        //first recyclerview for city

        ItemClickSupport.addTo(recyclerViewCity)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        cityClicked = ccList.get(position).getCityCorporation_keyword();
                        recyclerViewArea.setAdapter(null);
                        Log.d("tasks", "position: " + position);

                        populateRecyclerViewWard(ccList.get(position).getId());

                        if (getCityView() == null) {
                            setCityView(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getCityView() != v) {
                            ((CardView) getCityView()).setCardBackgroundColor(Color.parseColor("#7f000000"));

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setCityView(v);
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

                        if (districtClicked.equals(AppConstants.DISTRICT_2)) {
                            ward_position = position;
                            areaLayout.setVisibility(View.GONE);
                            //submit.setVisibility(View.VISIBLE);
                        } else {
                            areaLayout.setVisibility(View.VISIBLE);
                            populateRecyclerViewArea(wardList.get(position).getId());
                        }


                        if (getWardView() == null) {
                            setWardView(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getWardView() != v) {
                            ((CardView) getWardView()).setCardBackgroundColor(Color.parseColor("#7f000000"));

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setWardView(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));


                        if (districtClicked.equals(AppConstants.DISTRICT_2)) {
                            download();
                        }

                    }
                });

//area cards
        ItemClickSupport.addTo(recyclerViewArea)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        //submit.setVisibility(View.VISIBLE);

                        ((CardView) v).setCardBackgroundColor(Color.WHITE);
                        setPosAreaInt(position);
                        areaClicked = areaList.get(position).getArea_keyword();

                        if (getAreaView() == null) {
                            setAreaView(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getAreaView() != v) {
                            ((CardView) getAreaView()).setCardBackgroundColor(Color.WHITE);

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setAreaView(v);
                        } else {
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        }


                        download();
                    }

                });

        /*submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int areaId;
                Area areaSelected;


                if (getPosAreaInt() == -1 && !districtClicked.equals(AppConstants.DISTRICT_2)) {
                    ToastMessageDisplay.setText(context, getString(R.string.select_area));
                    ToastMessageDisplay.showText(context);
                } else {

                    SharedPreferences settings = getSharedPreferences("prefs", 0);
                    SharedPreferences.Editor editor = settings.edit();

                    if (wardClicked == null) wardClicked = wardList.get(0).getWard_keyword();


                    if (districtClicked.equals(AppConstants.DISTRICT_2)) {
                        areaSelected = new AreaTable(context).getDataListFromForeignKey(wardList.get(ward_position).getId()).get(0);
                        areaClicked = areaSelected.getArea_keyword();
                        areaId = areaSelected.getId();
                    } else {
                        areaSelected = areaList.get(getPosAreaInt());
                        areaId = areaList.get(getPosAreaInt()).getId();
                    }


                    editor.putString("_ward", wardClicked);
                    editor.putString("areakeyword", areaClicked);
                    editor.putInt("areaID", areaId);

                    editor.apply();


                    String lat = areaSelected.getLat();


                    if (lat.length() <= 1 || areaClicked.length() < 1 || lat.equalsIgnoreCase("null")) { //no data available for these areas
                        ToastMessageDisplay.setText(context, getString(R.string.info_not_found));
                        ToastMessageDisplay.showText(context);
                    } else {

                        StoredAreaTable storedAreaTable = new StoredAreaTable(context);

                        if (storedAreaTable.isAreaStored(wardClicked, areaClicked)) {

                            Intent intent = new Intent(context, PlaceDetailsActivityNewLayout.class);
                            startActivity(intent);
                            finish();
                        } else {
                            if (AppUtils.isNetConnected(getApplicationContext())) {
                                serverCall();
                            } else {
                                AlertMessage.showMessage(context, getString(R.string.sorry), getString(R.string.connect_to_internet));
                            }
                        }
                    }
                }


            }
        });*/

    }

    private void download() {
        int areaId;
        Area areaSelected;


        if (getPosAreaInt() == -1 && !districtClicked.equals(AppConstants.DISTRICT_2)) {
            ToastMessageDisplay.setText(context, getString(R.string.select_area));
            ToastMessageDisplay.showText(context);
        } else {

            SharedPreferences settings = getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings.edit();

            if (wardClicked == null)
                wardClicked = wardList.get(0).getWard_keyword();


            if (districtClicked.equals(AppConstants.DISTRICT_2)) {
                areaSelected = new AreaTable(context).getDataListFromForeignKey(wardList.get(ward_position).getId()).get(0);
                areaClicked = areaSelected.getArea_keyword();
                areaId = areaSelected.getId();
            } else {
                areaSelected = areaList.get(getPosAreaInt());
                areaId = areaList.get(getPosAreaInt()).getId();
            }


            editor.putString("_ward", wardClicked);
            editor.putString("areakeyword", areaClicked);
            editor.putInt("areaID", areaId);

            editor.apply();


            String lat = areaSelected.getLat();


            if (lat.length() <= 1 || areaClicked.length() < 1 || lat.equalsIgnoreCase("null")) { //no data available for these areas
                ToastMessageDisplay.setText(context, getString(R.string.info_not_found));
                ToastMessageDisplay.showText(context);
            } else {

                StoredAreaTable storedAreaTable = new StoredAreaTable(context);

                if (storedAreaTable.isAreaStored(wardClicked, areaClicked)) {

                    Intent intent = new Intent(context, PlaceDetailsActivityNewLayout.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (AppUtils.isNetConnected(getApplicationContext())) {
                        serverCall();
                    } else {
                        AlertMessage.showMessage(context, getString(R.string.sorry), getString(R.string.connect_to_internet));
                    }
                }
            }
        }
    }

    private void initViews() {

        recyclerViewDistrict = (RecyclerView) findViewById(R.id.districtRecycler_view);
        recyclerViewCity = (RecyclerView) findViewById(R.id.cityrecycler_view);
        recyclerViewWard = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewArea = (RecyclerView) findViewById(R.id.recycler_view2);


        //Set RecyclerView type according to intent value
        GridLayoutManager lLayout = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerViewDistrict.setHasFixedSize(true);
        recyclerViewDistrict.setLayoutManager(lLayout);


    }

    private void runOverlay_ContinueMethod() {
        // the return handler is used to manipulate the cleanup of all the tutorial elements

        ChainTourGuide tourGuideDistrict = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.step1))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_select_district))
                        .setGravity(Gravity.BOTTOM)
                )
                // note that there is no Overlay here, so the default one will be used
                .playLater(district);


        ChainTourGuide tourGuideCity = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.step2))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_select_cc))
                        .setGravity(Gravity.BOTTOM)
                )
                // note that there is no Overlay here, so the default one will be used
                .playLater(city);

        ChainTourGuide tourGuideWard = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.step3))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_select_ward))
                        .setGravity(Gravity.BOTTOM)
                )
                .playLater(ward);

        ChainTourGuide tourGuideArea = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.step4))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_select_area))
                        .setGravity(Gravity.BOTTOM)
                )
                .playLater(area);


        Sequence sequence = new Sequence.SequenceBuilder()
                .add(tourGuideDistrict, tourGuideCity, tourGuideWard, tourGuideArea)
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


    private void populateRecyclerViewDistrict() {


        districtLayout.setVisibility(View.VISIBLE);
        ccLayout.setVisibility(View.GONE);
        wardLayout.setVisibility(View.GONE);
        areaLayout.setVisibility(View.GONE);
        // submit.setVisibility(View.GONE);

        DistrictTable districtTable = new DistrictTable(context);
        districtList = districtTable.getAllData();

        if (districtList.size() >= 4) {
            lLayout2 = new GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false);
        } else {
            lLayout2 = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);
        }
        recyclerViewCity.setLayoutManager(lLayout2);

        recyclerViewDistrict.setHasFixedSize(false);
        RecyclerView_AdapterDistrict adapter = new RecyclerView_AdapterDistrict(context, districtList);
        recyclerViewDistrict.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void populateRecyclerViewCity(int district_id) {


        Log.e("District Id", " " + district_id);
        ccLayout.setVisibility(View.VISIBLE);
        wardLayout.setVisibility(View.GONE);
        areaLayout.setVisibility(View.GONE);
        //submit.setVisibility(View.GONE);

        CityCorporationTable ccTable = new CityCorporationTable(context);
        ccList = ccTable.getDataListFromForeignKey(district_id);

        if (ccList.size() >= 4) {
            lLayout2 = new GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false);
        } else {
            lLayout2 = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);
        }
        recyclerViewCity.setLayoutManager(lLayout2);
        recyclerViewCity.setHasFixedSize(false);
        RecyclerView_AdapterCityCorporation adapter = new RecyclerView_AdapterCityCorporation(context, ccList);
        recyclerViewCity.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void populateRecyclerViewWard(int cc_id) {


        wardLayout.setVisibility(View.VISIBLE);
        areaLayout.setVisibility(View.GONE);
        //submit.setVisibility(View.GONE);

        WardTable wardTable = new WardTable(context);
        wardList = wardTable.getDataListFromForeignKey(cc_id);

        if (wardList.size() >= 4) {
            lLayout2 = new GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false);
        } else {
            lLayout2 = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);
        }
        recyclerViewWard.setHasFixedSize(false);
        recyclerViewWard.setLayoutManager(lLayout2);
        RecyclerView_AdapterWard adapter = new RecyclerView_AdapterWard(context, wardList);
        recyclerViewWard.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void populateRecyclerViewArea(int ward_id) {


        areaLayout.setVisibility(View.VISIBLE);
        //submit.setVisibility(View.GONE);
        AreaTable areaTable = new AreaTable(context);
        areaList = areaTable.getDataListFromForeignKey(ward_id);

        if (areaList.size() >= 4) {
            lLayout2 = new GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false);
        } else {
            lLayout2 = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);
        }
        recyclerViewArea.setHasFixedSize(false);
        recyclerViewArea.setLayoutManager(lLayout2);
        RecyclerView_AdapterArea adapter = new RecyclerView_AdapterArea(context, areaList);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String text) {
        if (text.length() > 0) {
            seletctionLayout.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listViewAdapter.filter(text);
        } else {
            listView.setVisibility(View.GONE);
            seletctionLayout.setVisibility(View.VISIBLE);
        }

        return false;
    }//end


    void serverCall() {

        if(!firstRun || firstRunUpdate){
            NUMBER_OF_TASKS = NUMBER_OF_TASKS + 2;
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


                /*if (!firstRun || firstRunUpdate) {
                    if (counter == NUMBER_OF_TASKS_FIRST_RUN) downloaded = true;
                } else {
                    if (counter == NUMBER_OF_TASKS) downloaded = true;
                }*/

                if (counter >= NUMBER_OF_TASKS || timeCounter > 120000) {

                    Lg.e("downloaded: ", "" + (counter >= NUMBER_OF_TASKS));
                    Lg.e("timeCounter", "" + (timeCounter > 120000));
                    overridePendingTransition(0, 0);

                    handler.removeCallbacks(this);
                    SharedPreferencesHelper.setIfCommentedAlready(context, null, SharedPreferencesHelper.getUname(context), "no");
                    Intent a = new Intent(context, PlaceDetailsActivityNewLayout.class); // Default Activity

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
            getRequest(context, "http://kolorob.net/kolorob-new-live/api/categories_new?", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {
                                    new SaveCategoryTask(DataLoadingActivity.this).execute(new JSONArray(apiContent));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
            );
            getRequest(context, "http://kolorob.net/kolorob-new-live/api/refs? ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {
                                    new SaveSubCategoryTask(DataLoadingActivity.this).execute(new JSONArray(apiContent));

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


        getRequest(context, "http://kolorob.net/kolorob-new-live/api/getspbyarea?ward=" + wardClicked + "&area=" + areaClicked, new VolleyApiCallback() {
            @Override
            public void onResponse(int status, String apiContent) {
                if (status == AppConstants.SUCCESS_CODE) {

                    try {

                        allData = new JSONObject(apiContent);
                        Log.d("AllData", "*********" + allData);
                        if (allData.length() == 0) {
                            ToastMessageDisplay.setText(context, getString(R.string.select_another_area));
                            ToastMessageDisplay.showText(context);
                        } else { //checking category label and parsing in different threads so that parsing time get minimised

                            if (allData.has(AppConstants.EDU_API)) {
                                //counter += new SaveEducationDBTask(context, allData.getJSONArray(AppConstants.EDU_API)).saveItem();
                                new SaveEducationTask(DataLoadingActivity.this).execute(allData.getJSONArray(AppConstants.EDU_API));
                            }
                            if (allData.has(AppConstants.HEALTH_API)) {
                                new SaveHealthTask(DataLoadingActivity.this).execute(allData.getJSONArray(AppConstants.HEALTH_API));
                            }
                            if (allData.has(AppConstants.ENTERTAINMENT_API)) {
                                new SaveEntertainmentTask(DataLoadingActivity.this).execute(allData.getJSONArray(AppConstants.ENTERTAINMENT_API));
                            }

                            if (allData.has(AppConstants.GOVERNMENT_API)) {
                                new SaveGovernmentTask(DataLoadingActivity.this).execute(allData.getJSONArray(AppConstants.GOVERNMENT_API));
                            }
                            if (allData.has(AppConstants.LEGAL_API)) {
                                new SaveLegalTask(DataLoadingActivity.this).execute(allData.getJSONArray(AppConstants.LEGAL_API));
                            }
                            if (allData.has(AppConstants.FINANCE_API)) {
                                new SaveFinancialTask(DataLoadingActivity.this).execute(allData.getJSONArray(AppConstants.FINANCE_API));
                            }
                            if (allData.has(AppConstants.NGO_API)) {
                                new SaveNgoTask(DataLoadingActivity.this).execute(allData.getJSONArray(AppConstants.NGO_API));
                            }
                            if (allData.has(AppConstants.SHELTER_API)) {
                                new SaveShelterTask(DataLoadingActivity.this).execute(allData.getJSONArray(AppConstants.SHELTER_API));
                            }


                        }

                        /*int p = allData.length();
                        Log.d("Doneall", String.valueOf(p));
                        StoredAreaTable storedAreaTable = new StoredAreaTable(DataLoadingActivity.this);
                        String A = getAreaNameBn();
                        String LOC = getLocation();
                        storedAreaTable.insertItem();
                        Log.e("ward area ", String.valueOf(wardid[getPos()]));
                        SharedPreferences settings = getSharedPreferences("prefs", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("ward", wardid[getPos()]);
                        editor.putString("areakeyword", keyword);
                        editor.apply();*/

                        StoredAreaTable storedAreaTable = new StoredAreaTable(context);
                        WardTable wardTable = new WardTable(context);

                        SharedPreferences settings = context.getSharedPreferences("prefs", 0);
                        Area area = new AreaTable(context).getNodeInfo(settings.getInt("areaID", 0));

                        storedAreaTable.insertItem(new StoredArea(area.getId(), wardTable.getNodeInfo(area.getWard_id()).getWard_keyword(), area.getArea_keyword(), area.getArea_bn(), area.getParentArea(), area.getLat(), area.getLon()));

                        overridePendingTransition(0, 0);

                        //DataLoadingActivity.handler.removeCallbacks(context);
                        SharedPreferencesHelper.setIfCommentedAlready(context, null, SharedPreferencesHelper.getUname(context), "no");


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }

            }

        });


    }



    /*public static <SaveTask extends GenericSaveTask> void downloadData(JSONObject json, String api){
        if (json.has(api)) {
            counter += new SaveTask(DataLoadingActivity.this, allData.getJSONArray("Education")).saveItem();
        }
    }*/


    private static abstract class GenericSaveTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

        WeakReference<DataLoadingActivity> activityReference;


        GenericSaveTask(DataLoadingActivity activity) {
            activityReference = new WeakReference<>(activity);
        }


        /*@Override
        protected void onPostExecute(Result result) {

            boolean done = false;

            DataLoadingActivity activity = activityReference.get();

            if (activity == null) return;

            if (!activity.firstRun || activity.firstRunUpdate) {
                done = ((Long) result).longValue() == 0.0 && activity.getCounter() == activity.NUMBER_OF_TASKS;
            } else {
                done = ((Long) result).longValue() == 0.0 && activity.getCounter() == activity.NUMBER_OF_TASKS + 2;
            }

            if (done) { // Means the task is successful
                activity.setCounter(activity.getCounter()+1);
                Lg.e("Class", "" + getClass());
                Lg.e("Counter", "" + activity.getCounter());
                SharedPreferences settings = activity.getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("KValue", activity.getCounter());
                editor.apply();
                // Log.d("tasks", "Tasks remaining: " + (activity.NUMBER_OF_TASKS - activity.counter));//number of tasks equivalent to how many api data is being stored
                ToastMessageDisplay.setText(activity.context, activity.getString(R.string.downloading_data));
                ToastMessageDisplay.showText(activity.context);
            }
        }*/

        @Override
        protected void onPostExecute(Result result) {

            DataLoadingActivity activity = activityReference.get();

            if (activity == null) return;

            if (((Long) result).longValue() == 0.0 && activity.getCounter() < activity.NUMBER_OF_TASKS) { // Means the task is successful
                activity.setCounter(activity.getCounter()+1);

                Log.d("tasks", "Tasks remaining: " + (activity.NUMBER_OF_TASKS - activity.getCounter()));//number of tasks equivalent to how many api data is being stored
                /*ToastMessageDisplay.setText(activity.context, activity.context.getString(R.string.downloading_data));
                ToastMessageDisplay.showText(activity.context);*/
            }
        }

    }


    private static class SaveEducationTask extends GenericSaveTask<JSONArray, Integer, Long> {

        SaveEducationTask(DataLoadingActivity activity) {
            super(activity);
        }


        protected Long doInBackground(JSONArray... jsonObjects) {

            JSONArray jsonArray = jsonObjects[0];
            DataLoadingActivity activity = activityReference.get();
            EduNewDBTableMain eduNewDBTableMain = new EduNewDBTableMain(activity.context);


            for (int i = 0; i < jsonArray.length(); i++) {

                try {
                    if (!jsonArray.isNull(i)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        EduNewModel edu = new EduNewModel().parse(jsonObject);
                        eduNewDBTableMain.insertItem(edu);

                        if (jsonObject.has(AppConstants.TRAINING_API)) {

                            Log.e(" Edu : ", "training details");

                            JSONArray trainings = jsonObject.getJSONArray(AppConstants.TRAINING_API);

                            for (int j = 0; j < trainings.length(); j++) {
                                JSONObject training = trainings.getJSONObject(j);
                                new EduNewDBTableTraining(activity.context).insertItem(new EduTrainingModel().parse(training, edu.getId()));
                            }
                        }

                        if (jsonObject.has(AppConstants.RESULT_API)) {

                            Log.e(" Edu : ", "result details");

                            JSONArray results = jsonObject.getJSONArray(AppConstants.RESULT_API);

                            for (int j = 0; j < results.length(); j++) {
                                JSONObject result = results.getJSONObject(j);
                                new EducationResultDetailsTable(activity.context).insertItem(new EducationResultItemNew().parse(result, edu.getId()));
                            }
                        }

                        if (jsonObject.has(AppConstants.SCHOOL_API)) {

                            Log.e(" Edu : ", "school details");

                            JSONObject school = jsonObject.getJSONObject(AppConstants.SCHOOL_API);
                            new EduNewDBTableSchool(activity.context).insertItem(new EduNewSchoolModel().parse(school, edu.getId()));
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

    private static class SaveHealthTask extends GenericSaveTask<JSONArray, Integer, Long> {
        SaveHealthTask(DataLoadingActivity activity) {
            super(activity);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {

            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = jsonArrays[0];


            for (int i = 0; i < jsonArray.length(); i++) {

                try {
                    if (!jsonArray.isNull(i)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        HealthNewDBModelMain health = new HealthNewDBModelMain().parse(jsonObject);
                        new HealthNewDBTableMain(activity.context).insertItem(health);

                        if (jsonObject.has(AppConstants.PHARMACY_API)) {
                            Log.e(" Health : ", "pharmacy");
                            JSONObject pharmacy = jsonObject.getJSONObject(AppConstants.PHARMACY_API);
                            new HealthNewDBTablePharma(activity.context).insertItem(new HealthNewDBModelPharmacy().parse(pharmacy, health.getId()));
                        }
                        if (jsonObject.has(AppConstants.HOSPITAL_API)) {
                            Log.e(" Health : ", "hospital");
                            JSONObject hospital = jsonObject.getJSONObject(AppConstants.HOSPITAL_API);
                            new HealthNewDBTableHospital(activity.context).insertItem(new HealthNewDBModelHospital().parse(hospital, health.getId()));
                        }
                        if (jsonObject.has(AppConstants.CHAMBER_API)) {
                            Log.e("Health : ", "chamber");
                            JSONObject chamber = jsonObject.getJSONObject(AppConstants.CHAMBER_API);
                            new HealthNewDBTableChamber(activity.context).insertItem(new HealthModelChamber().parse(chamber, health.getId()));
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

    private static class SaveEntertainmentTask extends GenericSaveTask<JSONArray, Integer, Long> {

        SaveEntertainmentTask(DataLoadingActivity activity) {
            super(activity);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {


            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = jsonArrays[0];

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    if (!jsonArray.isNull(i)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        new EntNewDBTable(activity.context).insertItem(new EntertainmentNewDBModel().parse(jsonObject));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }

    }

    private static class SaveGovernmentTask extends GenericSaveTask<JSONArray, Integer, Long> {

        SaveGovernmentTask(DataLoadingActivity activity) {
            super(activity);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = jsonArrays[0];

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    if (!jsonArray.isNull(i)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        new GovNewDBTable(activity.context).insertItem(new GovernmentNewDBModel().parse(jsonObject));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    private static class SaveLegalTask extends GenericSaveTask<JSONArray, Integer, Long> {

        SaveLegalTask(DataLoadingActivity activity) {
            super(activity);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {

            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = jsonArrays[0];

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    if (!jsonArray.isNull(i)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        new LegalAidNewDBTable(activity.context).insertItem(new LegalAidNewDBModel().parse(jsonObject));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    private static class SaveFinancialTask extends GenericSaveTask<JSONArray, Integer, Long> {
        SaveFinancialTask(DataLoadingActivity activity) {
            super(activity);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {

            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = jsonArrays[0];

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    if (!jsonArray.isNull(i)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        new FinNewDBTable(activity.context).insertItem(new FinancialNewDBModel().parse(jsonObject));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    private static class SaveNgoTask extends GenericSaveTask<JSONArray, Integer, Long> {
        SaveNgoTask(DataLoadingActivity activity) {
            super(activity);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {

            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = jsonArrays[0];

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    if (!jsonArray.isNull(i)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        new NGONewDBTable(activity.context).insertItem(new NGONewDBModel().parse(jsonObject));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }

    }

    private static class SaveShelterTask extends GenericSaveTask<JSONArray, Integer, Long> {
        SaveShelterTask(DataLoadingActivity activity) {
            super(activity);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {

            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = jsonArrays[0];

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    if (!jsonArray.isNull(i)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        new ReligiousNewDBTable(activity.context).insertItem(new ReligiousNewDBModel().parse(jsonObject));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }


    private static class SaveCategoryTask extends GenericSaveTask<JSONArray, Integer, Long> {

        SaveCategoryTask(DataLoadingActivity activity) {
            super(activity);
        }

        @Override
        protected Long doInBackground(JSONArray... categoryArrays) {

            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = categoryArrays[0];
            CategoryTable categoryTable = new CategoryTable(activity.context);
            categoryTable.dropTable();

            for (int i = 0; i < jsonArray.length(); i++) {

                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    categoryTable.insertItem(new CategoryItem().parse(jsonObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    private static class SaveSubCategoryTask extends GenericSaveTask<JSONArray, Integer, Long> {

        SaveSubCategoryTask(DataLoadingActivity activity) {
            super(activity);
        }

        protected Long doInBackground(JSONArray... categoryArrays) {

            DataLoadingActivity activity = activityReference.get();
            JSONArray jsonArray = categoryArrays[0];
            SubCategoryTableNew subCategoryTableNew = new SubCategoryTableNew(activity.context);
            subCategoryTableNew.dropTable();


            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    subCategoryTableNew.insertItem(new SubCategoryItemNew().parse(jsonObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }


            return new Long(0);
        }
    }


}