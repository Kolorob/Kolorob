package demo.kolorob.kolorobdemoversion.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.osmdroid.util.GeoPoint;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
import demo.kolorob.kolorobdemoversion.adapters.CompareAdapter;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterAllCategories;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;

import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.database.StoredAreaTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentOSM;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.StoredArea;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

import static demo.kolorob.kolorobdemoversion.R.id.compare_layoutedu;


/**
 *
 *
 * @author israt,arafat
 */


/*
* Debug to understand this activity. It has all the codes no fragment been used for search/bazar or compare or map.Since earlier structure
* got changed multiple times so it would be wise to check which part is doing what using debug*/


public class PlaceDetailsActivityNewLayout extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private Context con;

    String username = "kolorobapp", password = "2Jm!4jFe3WgBZKEN";

    ArrayList <StoredArea> storedAreaArrayList = new ArrayList<>();
    ArrayList <StoredArea> storedAreaArrayListall = new ArrayList<>();
    ArrayList <StoredArea> storedAreas = new ArrayList<>();
    ArrayList <EduNewModel> firstDataSetEdu, secondDataSetEdu;
    ArrayList <HealthNewDBModelMain> firstDataSetHealth, secondDataSetHealth;
    ArrayList <CategoryItem> categoryList;

    ArrayList <String> headerHolder = new ArrayList<>();
    ArrayList <String> clicked = new ArrayList<>();
    ArrayList <String> filter = new ArrayList<>();
    ArrayList <String> filter2 = new ArrayList<>();

    ArrayList <AllHolder> allHolders = new ArrayList<>();
    ArrayList <AllHolder> catHolders = new ArrayList<>();
    ArrayList <AllHolder> subcatHolders = new ArrayList<>();
    private ArrayList <Subcatholder> tagHolders = new ArrayList<>();

    private ArrayList <FinancialNewDBModel> fetchedFin;
    private ArrayList <EduNewModel> fetchedEdu;
    private ArrayList <LegalAidNewDBModel> fetchedLegal;
    private ArrayList <EntertainmentNewDBModel> fetchedEnt;
    private ArrayList <HealthNewDBModelMain> fetchedHealth;
    public ArrayList <GovernmentNewDBModel> fetchedGov;
    private ArrayList <NGONewDBModel> fetchedNgo;
    private ArrayList <ReligiousNewDBModel> fetchedReligious;


    TextView welcomeText, upText;
    TextView health_name1, health_name2, edu_name1, edu_name2;
    TextView tvName;

    CheckedTextView changeArea;

    private int primaryIconWidth;
    private int subcategory;

    String [] left_part, right_part, health_header;

    boolean doubleBackToExitPressedOnce, reviewGiven, selected, inCompare;
    boolean eduClicked, healthClicked, entClicked, finClicked, govClicked, legalClicked, ngoClicked, shelterClicked;
    Boolean searchClicked, listClicked, compareClicked, mapClicked = true;
    boolean filterClicked, catStatus;
    Boolean firstRun, firstRunUpdate;
    Boolean navigationCalled, navigationCalledOnce;

    LinearLayout wholeLayout, compare_layout, compare_layout_edu;
    LinearLayout fHolder, fLeft, fRight;
    private LinearLayout llCatListHolder, svHolder;

    RelativeLayout catHolder, searchViewHolder, filterHolder;

    private ListView health_compare_list, education_compare_list;
    ListView allItemList;

    ToggleButton toggleButton;
    Double screenSize;
    private static final int ANIM_INTERVAL = 150;
    private static double VIEW_WIDTH;

    private static boolean mapcalledstatus;


    private static FrameLayout map;
    ScrollView sv;
    private String comapareData, firstData = "", secondData = "";


    CheckBox checkBox1, checkBox2, checkLeft, checkRight;
    CheckBox negotiable;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    GeoPoint location;


    int[] flag2 = new int[15];

    public static int currentCategoryID;
    private String placeChoice, filterWord, checkNum;
    private ImageButton mapButton, listButton, searchButton, compareButton;


    EditText searchAll, filterText;
    ListViewAdapterAllCategories adapter;

    private int smal;
    int value, height, width, sNumber, buttonHeight, buttonWidth;


    String id1, id2, id3, id4;

    Boolean flag;
    boolean mapFirst = true;

    String comment = "";
    MapFragmentOSM mapFragment;

    String wardId, areaKeyword, lat, lon, areaName;
    View view, view2;

    ActionBarDrawerToggle toggle;

    RadioGroup leftGroup, rightGroup;
    NavigationView navigationView;

    private Animation mEnterAnimation, mExitAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("prefs", 0);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("Value", false);
        editor.putInt("ValueD", 23);
        editor.apply();
        firstRun = settings.getBoolean("firstRunUp", false);
        firstRunUpdate = settings.getBoolean("new_update_first_run", true);

        wardId = settings.getString("_ward", null);
        Areakeyword=settings.getString("areakeyword",null);
        Log.e("","Ward ID: " + wardId + "Area: " + Areakeyword);
        storedAreaTable=new StoredAreaTable(PlaceDetailsActivityNewLayout.this);
        storedAreaArrayListall= storedAreaTable.getAllData();

        storedAreas = RetriveLocation(wardId,Areakeyword);
        setLat(storedAreas.get(0).getLat());
        setLon(storedAreas.get(0).getLon());
        if(storedAreaArrayListall.size()==0)
        {


            Intent em = new Intent(this, DataLoadingActivity.class);
            startActivity(em);
            finish();
        }
        else if (storedAreas.size()==0)
        {
            Intent em = new Intent(this, AreaUpgrade.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }
        else {

            setAreaName(storedAreas.get(0).getAreaBn());
            //String[] locsplit = mergedLocation.split(":");
            setLocation(new GeoPoint(Double.parseDouble(storedAreas.get(0).getLat()), Double.parseDouble(storedAreas.get(0).getLon())));

            navigationCalled = false;
            navigationCalledOnce = false;

            value = settings.getInt("KValue", 0);
            Log.e("ASinplaceDetails", String.valueOf(value));
            DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

            width = displayMetrics.widthPixels;
            height = displayMetrics.heightPixels;
            setContentView(R.layout.activity_place_detailnew);
            fHolder = (LinearLayout) findViewById(R.id.LinearLayoutfilter);
            con = this;
            MapButton = (ImageButton) findViewById(R.id.mapbutton);
            ListButton = (ImageButton) findViewById(R.id.listbutton);
            SearchButton = (ImageButton) findViewById(R.id.searchbutton);
            CompareButton = (ImageButton) findViewById(R.id.compare);
            searchViewHolder = (RelativeLayout) findViewById(R.id.searchholder);


            buttonWidth = width / 4;
            int buttonHeight = height / 20;
            allitemList = (ListView) findViewById(R.id.allitem);
            checkBox = (CheckBox) findViewById(R.id.compared);
            checkBox2 = (CheckBox) findViewById(R.id.compared2);

            health_compare_list = (ListView) findViewById(R.id.health_compare_list);
            education_compare_list = (ListView) findViewById(R.id.education_compare_list);


            checkLeft = (CheckBox) findViewById(R.id.checkLeft);
            checkRight = (CheckBox) findViewById(R.id.checkRight);


            // frameLayout.setClickable(false);
            // frameLayouts.setEnabled(false);

            // explist=(LinearLayout)findViewById(R.id.explist);
            catHolder = (RelativeLayout) findViewById(R.id.categoryfilterholder);
            // SearchButton.setLayoutParams(new RelativeLayout.LayoutParams(buttonWidth, buttonHeight));
            //  CompareButton.setLayoutParams(new RelativeLayout.LayoutParams(buttonWidth, buttonHeight));
            final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) MapButton.getLayoutParams();
            params.weight = 1;
            params.width = buttonWidth;
            double d = buttonWidth * 0.56;
            double large = buttonWidth * 0.69;
            final int larg = (int) Math.round(large);
            smal = (int) Math.round(d);
            params.height = larg;
            compare_layout = (LinearLayout) findViewById(R.id.compare_layout);

//        scrolling_part=(ScrollView)findViewById(R.id.scrolling_part);
 /*           ImageView compare_logo_imagex = (ImageView) findViewById(R.id.compare_logo_imagex);
            compare_logo_imagex.getLayoutParams().width = width / 20;

            compare_logo_imagex.getLayoutParams().height = height / 20;
            compare_logo_image = (ImageView) findViewById(R.id.compare_logo_images);
            compare_logo_image.getLayoutParams().width = width / 20;
            Log.d("Test width Height", "=======");
            compare_logo_image.getLayoutParams().height = height / 20;*/
//        LinearLayout.LayoutParams scrolling_partc= (LinearLayout.LayoutParams) scrolling_part.getLayoutParams();
//        scrolling_partc.setMargins(0,0,0,smal);


            LinearLayout.LayoutParams com_layout = (LinearLayout.LayoutParams) compare_layout.getLayoutParams();
            com_layout.setMargins(0, 0, 0, smal);

            compare_layout.setLayoutParams(com_layout);


            MapButton.setLayoutParams(params);

            final LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) SearchButton.getLayoutParams();
            params2.weight = 1;
            params2.width = buttonWidth;
            params2.height = (int) Math.round(d);
            buttonHeights = (int) Math.round(d);
            SearchButton.setLayoutParams(params2);
            Picasso.with(this)
                    .load(R.drawable.search)
                    .resize(buttonWidth, smal)
                    .into(SearchButton);
            final LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) ListButton.getLayoutParams();
            params3.weight = 1;
            params3.width = buttonWidth;
            params3.height = (int) Math.round(d);
            ListButton.setLayoutParams(params3);

            Picasso.with(this)
                    .load(R.drawable.job_unselectedtab)
                    .resize(buttonWidth, smal)
                    .into(ListButton);
            ListButton.getHeight();
            final LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) CompareButton.getLayoutParams();
            params4.weight = 1;
            params4.width = buttonWidth;
            params4.height = (int) Math.round(d);
            CompareButton.setLayoutParams(params4);

            Picasso.with(this)
                    .load(R.drawable.compare)
                    .resize(buttonWidth, smal)
                    .into(CompareButton);


            mapcalledstatus = false;
            toolbar = (Toolbar) findViewById(R.id.categorytoolbar);

            welcometext=(TextView)findViewById(R.id.toptext);
            SharedPreferencesHelper.setCompareData(PlaceDetailsActivityNewLayout.this, "", 0);
            Searchall = (EditText) findViewById(R.id.searchall);


            filterHolder = (RelativeLayout) findViewById(R.id.filterholder);
            uptext=(TextView)findViewById(R.id.textView15);
            ChangeArea=(CheckedTextView)findViewById(R.id.changearea);
            uptext.setText("মেনু");
            ChangeArea.setText("এলাকা পাল্টান");
            ChangeArea.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    /*Intent em = new Intent(PlaceDetailsActivityNewLayout.this, AreaUpgrade.class);
                    startActivity(em);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);*/

                    storedAreaArrayListall=storedAreaTable.getAllData();

                    Intent em = new Intent(PlaceDetailsActivityNewLayout.this, DataLoadingActivity.class);
                    startActivity(em);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    //}
                    //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
                }
            });
            // toolbar.setBackgroundResource(android.R.color.transparent);
            setSupportActionBar(toolbar);
            ActionBar ab = getSupportActionBar();
            ab.setHomeAsUpIndicator(R.drawable.menu_icon);
            ab.setDisplayHomeAsUpEnabled(true);

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

                /** Called when a drawer has settled in a completely open state. */
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                 view= navigationView.getTouchables().get(2);
                     view2=    navigationView.getTouchables().get(4);
                    if(firstRun==false || firstRunUpdate==true)runOverlay_ContinueMethodnavigation();
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
            //toggle.syncState();

             navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


            svholder = (LinearLayout) findViewById(R.id.llCategoryListHolderback);

            svholder.setVisibility(View.VISIBLE);

            sv = (ScrollView) findViewById(R.id.svCategoryListHolder);

            sv.setVisibility(View.VISIBLE);
            screenSize = AppUtils.ScreenSize(PlaceDetailsActivityNewLayout.this);


//        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
            wholeLayout = (LinearLayout) findViewById(R.id.wholeLayout);
            mEnterAnimation = new AlphaAnimation(0f, 1f);
            mEnterAnimation.setDuration(600);
            mEnterAnimation.setFillAfter(true);

            mExitAnimation = new AlphaAnimation(1f, 0f);
            mExitAnimation.setDuration(600);
            mExitAnimation.setFillAfter(true);
            String AREA=getAreaName();
            AREA= AREA.replace(' ','\n');
            welcometext.setText(AREA);
            health_name1 = (TextView) findViewById(R.id.health_name3);
            health_name2 = (TextView) findViewById(R.id.health_name2);
            edu_name1 = (TextView) findViewById(R.id.edu_name_ban3);
            edu_name2 = (TextView) findViewById(R.id.edu_name_ban22);
            int size_b = 20;
            int size_s = 14;
            if (screenSize > 6.5) {
                health_name1.setTextSize(size_b);
                edu_name1.setTextSize(size_b);
                edu_name2.setTextSize(size_b);
                health_name2.setTextSize(size_b);
            } else {
                health_name1.setTextSize(size_s);
                edu_name1.setTextSize(size_s);
                edu_name2.setTextSize(size_s);
                health_name2.setTextSize(size_s);
            }
            compare_layout = (LinearLayout) findViewById(R.id.compare_layout);
            compare_layoutedu = (LinearLayout) findViewById(compare_layoutedu);
            map = (FrameLayout) findViewById(R.id.map_fragment);
            map.setVisibility(View.VISIBLE);
            VIEW_WIDTH = AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC;

            primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.97); // 80% of the view width

            fLeft = (LinearLayout) findViewById(R.id.linearLayout1);
            fRight = (LinearLayout) findViewById(R.id.linearLayout2);

            //  svCatList = (ScrollView) findViewById(R.id.svCategoryListHolder);
            llCatListHolder = (LinearLayout) findViewById(R.id.llCategoryListHolder);

            llCatListHolder.setVisibility(View.VISIBLE);
            //rlSubCatHolder.setVisibility(View.VISIBLE);

            ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();

            final int s = lp.width = (int) (VIEW_WIDTH);


            /**
             * constructing category list
             **/
            CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivityNewLayout.this);
            categoryList = categoryTable.getAllData();
            constructCategoryList(categoryList);
            //rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
            //rlSubCatHolder.setVisibility(View.INVISIBLE);


            Populateholder(getPlaceChoice());
            try {
                callMapFragment(lat, lon);
            } catch (Exception e) {

            }
     if(firstRun==false || firstRunUpdate == true)runOverlay_ContinueMethod();

        /*Lower four buttons action are here. Since selected buttons size changes so others been marked not clicked one been marked clicked
        * and so on. Please DEBUG. Subcategory panels wont be visible in case of SearchButton Clicked.Category/subcategory/toggle wont be
        * shown if compare/bazar clicked(ListClicked)*/
            MapButton.setBackgroundResource(R.drawable.map_selected);

            Picasso.with(this)
                    .load(R.drawable.map_selected)
                    .resize(buttonWidth, larg)
                    .into(MapButton);


            SearchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchClicked = true;
                    MapClicked = false;
                    ListClicked = false;
                    CompareClicked = false;
                    InCompare = false;
                    wholeLayout.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                    toolbar.setVisibility(View.VISIBLE);


                    populateSearch();
                    if (CompareClicked == false || MapClicked == false || ListClicked == false) {

                        Picasso.with(getApplicationContext())
                                .load(R.drawable.map)
                                .resize(buttonWidth, smal)
                                .into(MapButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.compare)
                                .resize(buttonWidth, smal)
                                .into(CompareButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.job_unselectedtab)
                                .resize(buttonWidth, smal)
                                .into(ListButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.search_selected)
                                .resize(buttonWidth, larg)
                                .into(SearchButton);

                        params2.height = larg;
                        SearchButton.setLayoutParams(params2);
                        params.height = smal;
                        MapButton.setLayoutParams(params);
                        params3.height = smal;
                        ListButton.setLayoutParams(params3);
                        params4.height = smal;
                        CompareButton.setLayoutParams(params4);

                        map.setVisibility(View.GONE);
                        svholder.setVisibility(View.GONE);
                        sv.setVisibility(View.GONE);

                        toggleButton.setVisibility(View.VISIBLE);
                        compare_layout.setVisibility(View.GONE);
                        compare_layoutedu.setVisibility(View.GONE);
                        searchViewHolder.setVisibility(View.VISIBLE);
                    }
                    if (educlicked == true || helclicked == true || entclicked == true || legclicked == true || finclicked == true || govclicked == true || ngoclicked == true|| religiousclicked == true) {

                        filterHolder.setVisibility(View.VISIBLE);
                        toggleButton.setVisibility(View.VISIBLE);
                    } else {
                        filterHolder.setVisibility(View.GONE);
                    }
                    svholder.setVisibility(View.VISIBLE);
                    sv.setVisibility(View.VISIBLE);

                    llCatListHolder.setVisibility(View.VISIBLE);
                    toggleButton.setVisibility(View.VISIBLE);

                }
            });
            MapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchClicked = false;
                    MapClicked = true;
                    ListClicked = false;
                    CompareClicked = false;
                    if (InCompare == false) {
                        //  callMapFragment(locationNameId);

                    }

                    if (CompareClicked == false || SearchClicked == false || ListClicked == false) {
                        Picasso.with(getApplicationContext())
                                .load(R.drawable.map_selected)
                                .resize(buttonWidth, larg)
                                .into(MapButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.compare)
                                .resize(buttonWidth, smal)
                                .into(CompareButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.job_unselectedtab)
                                .resize(buttonWidth, smal)
                                .into(ListButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.search)
                                .resize(buttonWidth, smal)
                                .into(SearchButton);
                        params.height = larg;
                        MapButton.setLayoutParams(params);

                        params2.height = smal;
                        SearchButton.setLayoutParams(params2);
                        params3.height = smal;
                        ListButton.setLayoutParams(params3);
                        params4.height = smal;
                        CompareButton.setLayoutParams(params4);

//                    subCatItemList.setVisibility(View.GONE);

                        searchViewHolder.setVisibility(View.GONE);
                        compare_layout.setVisibility(View.GONE);
                        compare_layoutedu.setVisibility(View.GONE);
                    }
                    toggleButton.setVisibility(View.VISIBLE);
                    map.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.VISIBLE);


                }
            });

            ListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //if ((AppUtils.isNetConnected(getApplicationContext())) && (ContextCompat.checkSelfPermission(PlaceDetailsActivityNewLayout.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {

                        SearchClicked = false;
                        MapClicked = false;
                        InCompare = false;
                        ListClicked = true;
                        CompareClicked = false;
                        searchViewHolder.setVisibility(View.GONE);
                        llCatListHolder.setVisibility(View.VISIBLE);

                        if (MapClicked == false || SearchClicked == false || CompareClicked == false) {
                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.map)
                                    .resize(buttonWidth, smal)
                                    .into(MapButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.compare)
                                    .resize(buttonWidth, smal)
                                    .into(CompareButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.job_selectedtab)
                                    .resize(buttonWidth, larg)
                                    .into(ListButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.search)
                                    .resize(buttonWidth, smal)
                                    .into(SearchButton);
                            params3.height = larg;
                            ListButton.setLayoutParams(params3);
                            params2.height = smal;
                            SearchButton.setLayoutParams(params2);
                            params.height = smal;
                            MapButton.setLayoutParams(params);
                            params4.height = smal;
                            CompareButton.setLayoutParams(params4);

                            map.setVisibility(View.VISIBLE);
                        }

                        searchViewHolder.setVisibility(View.GONE);
                        compare_layout.setVisibility(View.GONE);
                        compare_layoutedu.setVisibility(View.GONE);

                        LayoutInflater layoutInflater = LayoutInflater.from(PlaceDetailsActivityNewLayout.this);
                        View promptView = layoutInflater.inflate(R.layout.default_alert, null);


                        final Dialog alertDialog = new Dialog(PlaceDetailsActivityNewLayout.this);
                        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        alertDialog.setContentView(promptView);
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.show();


                        final TextView header = (TextView) promptView.findViewById(R.id.headers);
                        final TextView bodys = (TextView) promptView.findViewById(R.id.body);
                        final ImageView okay=(ImageView)promptView.findViewById(R.id.okay);

                        header.setText("!!!");
                        header.setTextColor(getResources().getColor(R.color.Black));
                        bodys.setText("কলরব জব পোর্টাল নতুন রূপে আসছে");
                        bodys.setTextColor(getResources().getColor(R.color.Black));
                        okay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });

                        //alertDialog.setCancelable(false);

                        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
                        lp.dimAmount=0.0f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
                        alertDialog.getWindow().setAttributes(lp);

                        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);

                       // Intent intentJ = new Intent(PlaceDetailsActivityNewLayout.this, DisplayAllJobsActivity.class);
                       // startActivity(intentJ);
                      //  map.setVisibility(View.GONE);
                   // } else {
                     //   AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই।", "অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");

                  //  }


                }

            });
            CompareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (currentCategoryID == 10000 || currentCategoryID == 20000) {
                        SearchClicked = false;
                        MapClicked = false;
                        ListClicked = false;
                        CompareClicked = true;
                        InCompare = true;

                        if (currentCategoryID == 10000 && SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this) == 0) {
                            AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                                    "আপনি কোন সেবা নির্বাচিত করেননি তুলনা করার জন্য");
                        } else if (currentCategoryID == 20000 && SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this) == 0) {
                            AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                                    "আপনি কোন সেবা নির্বাচিত করেননি তুলনা করার জন্য");
                        } else if (currentCategoryID == 10000 && SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this) == 1) {
                            AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                                    "আপনি একটি সেবা নির্বাচিত করেছেন। তুলনা করার জন্য দুটি সেবা নির্বাচন করুন");
                        } else if (currentCategoryID == 20000 && SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this) == 1) {
                            AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                                    "আপনি একটি সেবা নির্বাচিত করেছেন। তুলনা করার জন্য দুটি সেবা নির্বাচন করুন");
                        } else {


                            if (MapClicked == false || SearchClicked == false || ListClicked == false) {

                                params4.height = larg;
                                CompareButton.setLayoutParams(params4);

                                params2.height = smal;
                                SearchButton.setLayoutParams(params2);
                                params.height = smal;
                                MapButton.setLayoutParams(params);
                                params.height = smal;
                                ListButton.setLayoutParams(params);


                            }
                            toolbar.setVisibility(View.GONE);
                            // compare_layout.setVisibility(View.VISIBLE);

                            // @@@@arafat
                            // need to add condition for health and add color code for health,
                            // else educaton color code is okay
                            toggleButton.setVisibility(View.GONE);
                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.map)
                                    .resize(buttonWidth, smal)
                                    .into(MapButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.compare_selected)
                                    .resize(buttonWidth, larg)
                                    .into(CompareButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.job_unselectedtab)
                                    .resize(buttonWidth, smal)
                                    .into(ListButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.search)
                                    .resize(buttonWidth, smal)
                                    .into(SearchButton);
                            map.setVisibility(View.GONE);
                            llCatListHolder.setVisibility(View.GONE);
                            searchViewHolder.setVisibility(View.GONE);

                            sv.setVisibility(View.GONE);
                            svholder.setVisibility(View.GONE);
                            spinCounter = 0;
                            spinCounter1 = 0;
                            compareTool();
                        }
                    } else
                        AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                                "বর্তমান ক্যাটাগরির জন্য তুলনা সম্ভব নয়");


                }
            });

            toggleButton = (ToggleButton) findViewById(R.id.toggle);
            toggleButton.setVisibility(View.VISIBLE);
            toggleButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (toggleButton.isChecked()) {

                        sv.setVisibility(View.GONE);

                        svholder.setVisibility(View.GONE);

                        llCatListHolder.setVisibility(View.GONE);

                    } else {

                        sv.setVisibility(View.VISIBLE);
                        svholder.setVisibility(View.VISIBLE);
                        llCatListHolder.setVisibility(View.VISIBLE);

                    }

                    //Button is OFF
                    // Do Something
                }
            });
        }
    }

    public void compareTool()
    {
        if(currentCategoryID==10000)
        { //compare_layout.setBackgroundColor(Color.parseColor("#2F7281"));
            comapareData = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
            String delims = "[,]";
            String[] tokens = comapareData.split(delims);
            firstData=tokens[0];
            SecondData=tokens[1];
            compare_layout.setVisibility(View.GONE);
            compare_layoutedu.setVisibility(View.VISIBLE);
            compare_layoutedu.setBackgroundColor(Color.parseColor("#FFFFFF"));
            compareEducation();
            }

        else {
            compare_layout.setVisibility(View.VISIBLE);
            comapareData = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
            int size= comapareData.length();
            String DataSet[]= comapareData.split(",");
            firstData=DataSet[0];
            SecondData=DataSet[1];
            compareHealth();
        }
    }


    public void compareHealth() {

        //compare will be selected from detailspage and displayed here
        compare_layout.setVisibility(View.VISIBLE);
        compare_layoutedu.setVisibility(View.GONE);
        checkBox.setChecked(true);
        checkBox2.setChecked(true);

        healthServiceProviderTableNew = new HealthNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        firstDataSetHealth = healthServiceProviderTableNew.getDataFromId(Integer.parseInt(firstData));
        secondDataSetHealth = healthServiceProviderTableNew.getDataFromId(Integer.parseInt(SecondData));
        health_header=new String[]{"খোলার সময়","সেবার ধরন","বন্ধের দিন","ডাক্তার আছে?","বিশেষত্ব","ভ্যাক্সিন সুবিধা"};
        HealthNewDBTablePharma healthSpecialistTable = new HealthNewDBTablePharma(PlaceDetailsActivityNewLayout.this);
        ArrayList<HealthNewDBModelPharmacy> healthSpecialistItemDetailses;
        ArrayList<HealthNewDBModelPharmacy> healthSpecialistItemDetailses2;
        healthSpecialistItemDetailses = healthSpecialistTable.getDataFromId((Integer.parseInt(firstData));
        healthSpecialistItemDetailses2 = healthSpecialistTable.getDataFromId(Integer.parseInt(SecondData));

        String firstSpecialistItemdoc = "";
        String secondSpecialistItemdoc = "";
        String firstSpecialistItemspe = "";
        String secondSpecialistItemspe = "";
        String firstSpecialistItemvac = "";
        String secondSpecialistItemvac = "";

        if (!healthSpecialistItemDetailses.equals("")) {
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses) {
                if(healthSpecialistItemDetails.getDocavailability().equals("false"))
                {
                    firstSpecialistItemdoc = firstSpecialistItemdoc + "ডাক্তার বসেন না";
                }
                else
                firstSpecialistItemdoc = firstSpecialistItemdoc +  "ডাক্তার বসেন" ;
            }
        }

        if (!healthSpecialistItemDetailses2.equals("")){
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses2) {
                if(healthSpecialistItemDetails.getDocavailability().equals("false"))
                {
                    secondSpecialistItemdoc = secondSpecialistItemdoc + "ডাক্তার বসেন না";
                }
                else secondSpecialistItemdoc = secondSpecialistItemdoc + "ডাক্তার বসেন" ;
            }
        }

        if (!healthSpecialistItemDetailses.equals("")) {
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses) {
                firstSpecialistItemspe = firstSpecialistItemspe + healthSpecialistItemDetails.getSpeciality() ;
            }
        }

        if (!healthSpecialistItemDetailses2.equals("")){
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses2) {
                secondSpecialistItemspe = secondSpecialistItemspe + healthSpecialistItemDetails.getSpeciality() ;
            }
        }

        if (!healthSpecialistItemDetailses.equals("")) {
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses) {
                if(healthSpecialistItemDetails.getVaccineavailability().equals("false"))
                {
                    firstSpecialistItemvac=firstSpecialistItemvac + "ভ্যাকসিন সুবিধা নেই ";
                }
                else firstSpecialistItemvac = firstSpecialistItemvac +  "ভ্যাকসিন সুবিধা আছে "  ;
            }
        }

        if (!healthSpecialistItemDetailses2.equals("")){
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses2) {
                if(healthSpecialistItemDetails.getVaccineavailability().equals("false"))
                {
                    secondSpecialistItemvac=secondSpecialistItemvac + "ভ্যাকসিন সুবিধা নেই ";
                }
                else secondSpecialistItemvac = secondSpecialistItemvac +  "ভ্যাকসিন সুবিধা আছে " ;
            }
        }

        String healthService1="";
        String health_service_data1="";

        for (final HealthNewDBModelMain healthServiceProviderItemNew: firstDataSetHealth)
        {

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue = SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this);
                    if(compareValue==2) // if their is two value for compare
                    {
                        if(!isChecked)
                        {
                            String compare_Datas="";
                            compare_Datas=SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            String multipule[]= compare_Datas.split(",");
                            compare_Datas = multipule[1];
                            id1 =multipule[0];
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datas, 1);
                        }
                    }

                    else if(compareValue==1) /// if their is one value for compare
                    {
                        if(!isChecked)
                        {
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this,"",0);
                        }

                        else {
                            String compare_Datac="";
                            compare_Datac=SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            compare_Datac = compare_Datac+","+ id1;
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datac, 2);
                        }
                    }

                    else if (compareValue == 0) { /// if their no item for compare
                        if(isChecked)
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, String.valueOf(healthServiceProviderItemNew.getHealthid()), 1);
                    }



                }
            });


          /*  healthService1=healthServiceProviderItemNew.getFamily_privacy();
            if(!healthService1.equals(""))
            {
                for (int i=0;i<healthService1.length();i++)
                {
                    if(healthService1.charAt(i)=='1')
                    {
                        health_service_data1=health_service_data1+"Emergency Service,";
                    }
                    else if(healthService1.charAt(i)=='2')
                    {
                        health_service_data1=health_service_data1 +" Ambulance Service,";
                    }
                    else
                        health_service_data1=health_service_data1 +" Maternity Service";

                }
            }*/
            if(!healthServiceProviderItemNew.getCommonModel().getNameBn().equalsIgnoreCase("null")&&!healthServiceProviderItemNew.getCommonModel().getNameBn().equals(""))
                health_name2.setText(healthServiceProviderItemNew.getCommonModel().getNameBn());
            else
                health_name2.setText("তথ্য পাওয়া যায় নি ");

            String time2="";
            time2=timeConverter(healthServiceProviderItemNew.getCommonModel().getOpeningTime());
            left_part=new String[]{time2,healthServiceProviderItemNew.getInstituteType()
                    ,healthServiceProviderItemNew.getCommonModel().getOffDay(),firstSpecialistItemdoc,firstSpecialistItemspe,firstSpecialistItemvac
                    };
        }


                               //this is for right side item
        String healthService2="";
        String health_service_data2="";
        for (final HealthNewDBModelMain healthServiceProviderItemNewx: secondDataSetHealth)
        {
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue = SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this);
                    if(compareValue==2)
                    {
                        if(!isChecked)
                        {
                            String compare_Datas="";
                            compare_Datas=SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            String multipule[]= compare_Datas.split(",");
                            compare_Datas = multipule[0];
                            id2 =multipule[1];
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datas, 1);
                        }

                    }
                    else if(compareValue==1)
                    {
                        if(!isChecked)
                        {
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this,"",0);
                        }
                        else {

                            String compare_Data="";
                            compare_Data=SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            compare_Data = id2 +","+ compare_Data;
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Data, 2);
                        }
                    }

                    else if (compareValue == 0) {
                        if(isChecked)
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, String.valueOf(healthServiceProviderItemNewx.getCommonModel().getId()), 1);
                    }
                }
            });




            if(!healthServiceProviderItemNewx.getCommonModel().getNameBn().equalsIgnoreCase("null")&&!healthServiceProviderItemNewx.getCommonModel().getNameBn().equals(""))
                health_name1.setText(healthServiceProviderItemNewx.getCommonModel().getNameBn());
            else
                health_name1.setText("তথ্য পাওয়া যায় নি ");



            String time1="";
            time1=timeConverter(healthServiceProviderItemNewx.getCommonModel().getOpeningTime()); //convert the time
            right_part=new String[]{time1,healthServiceProviderItemNewx.getInstituteType()
                    ,healthServiceProviderItemNewx.getCommonModel().getOffDay(),secondSpecialistItemdoc,
                    secondSpecialistItemspe,secondSpecialistItemvac
            };
        }
        CompareAdapter compareAdapter= new CompareAdapter(this,left_part,right_part,health_header);
        health_compare_list.setAdapter(compareAdapter);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) health_compare_list
                .getLayoutParams();
        layoutParams.setMargins(0, 0, 0, smal);//
    }






//    public Boolean BazarPostValidation(Context c, String message, )


    public void compareEducation()
    {
        checkLeft.setChecked(true);
        checkRight.setChecked(true);

        educationNewTable = new EduNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        firstDataSet=educationNewTable.getDataFromId(Integer.parseInt(firstData));
        secondDataSet=educationNewTable.getDataFromId(Integer.parseInt(SecondData));
        for (final EduNewModel educationNewItem: firstDataSet)
        {
            checkLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue=0;
                    compareValue = SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this);
                    if(compareValue==2)
                    {
                        if(!isChecked)
                        {
                            String compare_Data="";
                            compare_Data=SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
                            String multipule[]= compare_Data.split(",");
                            compare_Data = multipule[1];
                            id3 =multipule[0];
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 1);
                        }

                    }
                    else if(compareValue==1)
                    {
                        if(!isChecked)
                        {
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this,"",0);
                        }
                        else {

                            String compare_Data="";
                            compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);

                            compare_Data = compare_Data+","+ id3;
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 2);
                        }
                    }

                    else if (compareValue == 0) {
                        if(isChecked)
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, String.valueOf(educationNewItem.getEduId()), 1);

                    }
                }
            });

            health_header=new String []{ "প্রতিষ্ঠানের ধরন", "গড় ছাত্র ছাত্রী সংখ্যা ","ছাত্র ছাত্রী সংখ্যা","শিক্ষক সংখ্যা ","শাখা","রেটিং"
            };
            if(educationNewItem.getCommonModel().getNameBn()==null || educationNewItem.getCommonModel().getNameBn().equalsIgnoreCase("null")|| educationNewItem.getCommonModel().getNameBn().equals(""))
                edu_name2.setText("তথ্য পাওয়া যায় নি ");
            else
                edu_name2.setText(educationNewItem.getCommonModel().getNameBn());

            left_part = new String []{educationNewItem.getEducationType(),English_to_bengali_number_conversion(educationNewItem.getAverageStudentPerClass()),
                    English_to_bengali_number_conversion(String.valueOf(educationNewItem.getStudentNo())),
                    English_to_bengali_number_conversion(educationNewItem.getTeachersNo()),educationNewItem.getShift(),
                    educationNewItem.getCommonModel().getRatings()};
        }
        for ( final EduNewModel educationNewItem: secondDataSet)
        {
            checkRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue=0;
                    compareValue = SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this);
                    if(compareValue==2)
                    {
                        if(!isChecked)
                        {
                            String compare_Data="";
                            compare_Data=SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
                            String multipule[]= compare_Data.split(",");
                            compare_Data = multipule[0];
                            id4 =multipule[1];
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 1);
                        }

                    }
                    else if(compareValue==1)
                    {
                        if(!isChecked)
                        {
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this,"",0);
                        }
                        else {
                            String compare_Data="";
                            compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
                            compare_Data = compare_Data+","+ id4;
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 2);
                        }
                    }

                    else if (compareValue == 0) {
                        if(isChecked)
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, String.valueOf(educationNewItem.getEduId()), 1);
                    }
                }
            });


            right_part = new String []{educationNewItem.getEducationType(),English_to_bengali_number_conversion(educationNewItem.getAverageStudentPerClass())),
                    English_to_bengali_number_conversion(String.valueOf(educationNewItem.getStudentNo())),
                    English_to_bengali_number_conversion(educationNewItem.getTeachersNo()),
                    educationNewItem.getShift(),educationNewItem.getCommonModel().getRatings()};
            if(educationNewItem.getCommonModel().getNameBn()==null || educationNewItem.getCommonModel().getNameBn().equalsIgnoreCase("null")|| educationNewItem.getCommonModel().getNameBn().equals(""))
                edu_name1.setText("তথ্য পাওয়া যায় নি ");
            else
                edu_name1.setText(educationNewItem.getCommonModel().getNameBn());
        }

        CompareAdapter compareAdapter= new CompareAdapter(this,left_part,right_part,health_header);
        education_compare_list.setAdapter(compareAdapter);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) education_compare_list
                .getLayoutParams();
        layoutParams.setMargins(0, 0, 0, smal);//
        education_compare_list.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this,R.color.white));
    }

    public void populateSearch()
    {

        ImageButton more=(ImageButton)findViewById(R.id.morebutton);
        catHolder.setVisibility(View.VISIBLE);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterclicked=true;
                fHolder.setVisibility(View.VISIBLE);
                populatefilterwords(currentCategoryID);

            }
        });

    }



/*
* this populate filter keywords based on category id from db*/
    public void populatefilterwords(int filcatid)
    {
        SubCategoryTableNew subCategoryTable = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        tagHolders.clear();
        tagHolders =subCategoryTable.getcatSubCategories(filcatid);

        int upto = tagHolders.size()/2;
        filter.clear();
        filter2.clear();
        fLeft.removeAllViews();
        fRight.removeAllViews();
        for (int f=0;f<tagHolders.size();f++)
        {
            if (f>=upto)
                filter2.add(tagHolders.get(f).getSubcatname());
            else
            {
                filter.add(tagHolders.get(f).getSubcatname());
            }
        }
        final RadioButton[] rb = new RadioButton[30];
        leftGroup = new RadioGroup(this); //create the RadioGroup
        leftGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<filter.size(); i++){
            rb[i]  = new RadioButton(this);
            leftGroup.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filter.get(i).toString());
            rb[i].setTextColor(Color.WHITE);
        }
        rightGroup = new RadioGroup(this); //create the RadioGroup
        rightGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<filter2.size(); i++){
            rb[i]  = new RadioButton(this);
            rightGroup.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filter2.get(i).toString());
            rb[i].setTextColor(Color.WHITE);

        }
        fLeft.addView(leftGroup);
        fRight.addView(rightGroup);//you add the w

        leftGroup.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        rightGroup.clearCheck();
        leftGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId != -1) {
                    fun2();
                }
            }
        });

        rightGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId != -1) {
                    fun1();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.phone_reg) {
            // Handle the camera action
            Intent em = new Intent(this, PhoneRegActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }  else if (id == R.id.emergency_info) {

            //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
            Intent em = new Intent(this, EmergencyActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
        else if (id == R.id.new_place) {
            storedAreaArrayListall=storedAreaTable.getAllData();
                /*if(storedAreaArrayListall.size()>=5)
                {

                    AlertMessage.showMessagesize(PlaceDetailsActivityNewLayout.this,"দুঃখিত","আপনি ৫টি এলাকার বেশি তথ্য একবারে নামাতে পারবেন না। আগের এলাকা বাতিল করতে" +
                            "'তথ্য আপডেট/ডিলিট করুন' অপশন টি ব্যাবহার করুন",20,15);

                }*/
            //else {
                    Intent em = new Intent(this, DataLoadingActivity.class);
                    startActivity(em);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
               //}
            //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();

        }
        else if (id == R.id.old_place) {

            //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
            Intent em = new Intent(this, AreaUpgrade.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
        else if (id == R.id.about_us) {

            Intent em = new Intent(this, AboutUs.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }

        else if (id == R.id.disclaimer) {

            Intent em = new Intent(this, Disclaimer.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }
        else if (id == R.id.tutorial) {

            Intent intent3 = new Intent();
            intent3.setAction(Intent.ACTION_VIEW);
            intent3.addCategory(Intent.CATEGORY_BROWSABLE);
            intent3.setData(Uri.parse("http://www.kolorob.info/learn-more/tutorial"));
            startActivity(intent3);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void runOverlay_ContinueMethod() {
        // the return handler is used to manipulate the cleanup of all the tutorial elements

        ChainTourGuide tourGuide1 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("ক্যাটাগরি ")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(" সেবার ধরণ অনুযায়ী তথ্য পেতে এখান থেকে সেবা নির্বাচন করুন")
                        .setGravity(Gravity.RIGHT)
                )
                // note that there is no Overlay here, so the default one will be used
                .playLater(llCatListHolder);

        ChainTourGuide tourGuide2 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("চাকরির তথ্য")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(" এলাকার চাকরির খবর পেতে এখানে ক্লিক করুন")
                        .setGravity(Gravity.TOP)
                )
                .playLater(ListButton);
        ChainTourGuide tourGuide3 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("সেবা খুঁজুন")

                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("আপনার পরিচিত সেবা প্রতিষ্ঠানের অথবা সকল প্রতিষ্ঠানের তথ্য লিস্ট অনুসারে দেখতে ক্লিক করুন ")
                        .setGravity(Gravity.TOP)
                )
                .playLater(SearchButton);
        ChainTourGuide tourGuide4 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("তুলনা ")

                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("শিক্ষার বিস্তারিত পেজ থেকে 'তুলনা করুন' নির্বাচন করে আপনি এখানে ক্লিক করে তুলনা করতে পারবেন")
                        .setGravity(Gravity.TOP)
                )
                .playLater(CompareButton);
        ChainTourGuide tourGuide5 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("মেনু")

                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("ইমারজেন্সি সুবিধা, টিউটোরিয়াল সহ অন্যান্য বিষয় জানতে বাঁয়ের মেনুতে ক্লিক করুন")
                        .setGravity(Gravity.RIGHT)
                )
                .playLater(uptext);
        ChainTourGuide tourGuide6 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("এলাকা পাল্টান")

                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(" এখান থেকে নতুন এলাকার তথ্য নামাতে পারবেন")
                        .setGravity(Gravity.RIGHT)
                )
                .playLater(ChangeArea);

        Sequence sequence = new Sequence.SequenceBuilder()
                .add(tourGuide1, tourGuide2,tourGuide3,tourGuide4,tourGuide5,tourGuide6)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(mEnterAnimation)
                        .setExitAnimation(mExitAnimation)
                )
                .setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();


        ChainTourGuide.init(this).playInSequence(sequence);
    }

    private void runOverlay_ContinueMethodnavigation() {
        // the return handler is used to manipulate the cleanup of all the tutorial elements

        ChainTourGuide tourGuide1 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("নতুন এলাকার তথ্য")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("'নতুন এলাকার তথ্য নামান' থেকে আপনি অন্যান্য এলাকার তথ্য নামাতে পারবেন")
                        .setGravity(Gravity.RIGHT)
                )
                // note that there is no Overlay here, so the default one will be used
               .playLater( view);

     ChainTourGuide tourGuide2 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("নামানো তথ্য পরিবর্তন")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("নামানো এলাকার তথ্য বাতিল অথবা আপডেট করতে ব্যবহার করুন")
                        .setGravity(Gravity.TOP)
                )
                .playLater(view2);



        Sequence sequence2 = new Sequence.SequenceBuilder()
                .add(tourGuide1,tourGuide2)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(mEnterAnimation)
                        .setExitAnimation(mExitAnimation)
                )
                .setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();


        ChainTourGuide.init(this).playInSequence(sequence2);
    }



    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {


            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
        }
        SharedPreferences settings = PlaceDetailsActivityNewLayout.this.getSharedPreferences("prefs", 0);

   /*    if(settings.getBoolean("Reviewsent",false)==true && diffInDays>=30)
       {
           SharedPreferences.Editor editor = settings.edit();
           editor.putBoolean("Reviewsent", false);
           Reviewgiven=false;
           editor.apply();
       }*/
        if (!settings.getBoolean("Reviewsent", false)) help();
        ToastMessageDisplay.setText(PlaceDetailsActivityNewLayout.this,"এখান থেকে বের হতে চাইলে আরেকবার চাপ দিন");
        ToastMessageDisplay.showText(this);
        Log.d("In on Back Pressed","==========");
        this.doubleBackToExitPressedOnce = true;


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void help() {
        LayoutInflater layoutInflater = LayoutInflater.from(PlaceDetailsActivityNewLayout.this);
        View promptView = layoutInflater.inflate(R.layout.app_feedback_dialog, null);
        //   final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlaceSelectionActivity.this);
        //  final AlertDialog alert = alertDialogBuilder.create();

        //   alertDialogBuilder.setView(promptView);

        final Dialog alertDialog = new Dialog(PlaceDetailsActivityNewLayout.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        alertDialog.getWindow().setLayout((width*4)/5, WindowManager.LayoutParams.WRAP_CONTENT);




        final RatingBar ratingBar = (RatingBar) promptView.findViewById(R.id.ratingBar);
        final EditText submit_review = (EditText) promptView.findViewById(R.id.submit_review);
        final Button btnSubmit = (Button) promptView.findViewById(R.id.btnSubmit);
        final Button btnclose = (Button) promptView.findViewById(R.id.btnclose);
//        Dialog mDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
//        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));




        ViewGroup.LayoutParams params = btnSubmit.getLayoutParams();
//        params.height=width/9;
//        params.width=width/9;
        btnSubmit.setLayoutParams(params);

        ViewGroup.LayoutParams params1 = btnSubmit.getLayoutParams();
//        params1.height=width/9;
//        params1.width=width/9;
        btnclose.setLayoutParams(params1);


        btnclose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alertDialog.cancel();
            }
        });



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = SharedPreferencesHelper.getUser(PlaceDetailsActivityNewLayout.this);
                String testUser = SharedPreferencesHelper.getFeedback(PlaceDetailsActivityNewLayout.this);

                Float  ratings;
                ratings = ratingBar.getRating();
                comment=submit_review.getText().toString();
                if(ratings==0)ratings = (float) 0.0001;

                sendDataToserver(ratings, comment);
                SharedPreferences settings = PlaceDetailsActivityNewLayout.this.getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("Reviewsent", true);
                editor.apply();
                Reviewgiven=true;
                alertDialog.cancel();
                finish();
                //   back();


            }

        });
//        prebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                PlaceDetailsActivityNewLayout.this.onBackPressed();
//
//            }
//        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // setup a dialog window
        alertDialog.setCancelable(false);

        // create an alert dialog
        alertDialog.show();
        //   alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
//        alerts.getWindow().setLayout((height*3/7), (height*4/7));


    }
    public void sendDataToserver(Float rating, String comment) {
        String username = SharedPreferencesHelper.getUser(PlaceDetailsActivityNewLayout.this);
        SharedPreferencesHelper.setFeedback(PlaceDetailsActivityNewLayout.this, username);
        String phone = SharedPreferencesHelper.getNumber(PlaceDetailsActivityNewLayout.this);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);


        if (phone.equals(""))phone.replace("","0");
        else {
            String comment2="";
            try {
                comment2=   URLEncoder.encode(comment.replace(" ", "%20"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url = "http://kolorob.net/kolorob-new-demo/api/app_rating?phone=" + phone + "&review=" + comment2 + "&rating=" + rating + "&username=" + this.usernames + "&password=" + this.password;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //   ToastMessageDisplay.ShowToast(PlaceSelectionActivity.this,"ধন্যবাদ");


                            try {
                                ToastMessageDisplay.setText(PlaceDetailsActivityNewLayout.this,"ধন্যবাদ");
                                ToastMessageDisplay.showText(PlaceDetailsActivityNewLayout.this);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ToastMessageDisplay.setText(PlaceDetailsActivityNewLayout.this,error.toString());
                            ToastMessageDisplay.showText(PlaceDetailsActivityNewLayout.this);
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<>();

                    return params;
                }

            };

// Adding request to request queue

            RequestQueue requestQueue = Volley.newRequestQueue(PlaceDetailsActivityNewLayout.this);
            requestQueue.add(stringRequest);


        }


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){



            default:
                break;

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Ignoring the device orientation change (always on portrait ensured) :: #HARD_CODED_(:()
        // super.onConfigurationChanged(newConfig);
    }




    private void constructCategoryList(ArrayList<CategoryItem> categoryList2) {
        constructCategoryList(categoryList2, 1.0);
    }

    private void constructCategoryList(ArrayList<CategoryItem> categoryList2, double dwPercentage) {
        llCatListHolder.removeAllViews();
        Collections.sort(categoryList2);
        for ( CategoryItem ci : categoryList2) {
            setCategoryItem(ci);
            llCatListHolder.addView(getCategoryListItemView(ci, dwPercentage));



        }
    }

    private View getCategoryListItemView(final CategoryItem ci, double dwPercentage) {
        LayoutInflater li = LayoutInflater.from(this);
        View v;
//        if(dpi>300)
//            v = li.inflate(R.layout.cat_list_mobile, llCatListHolder, false);
//        else





        v = li.inflate(R.layout.cat_side_list_item, llCatListHolder, false);
        final ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconCatList);


        TextView tvName = (TextView) v.findViewById(R.id.cat_name);




        // BE CAREFUL :: Category ID is being mapped as to the icon serial no.
        // in the AppConstants.ALL_CAT_ICONS array
        int modded_id=ci.getId()/10000;
        ivIcon.setImageResource(AppConstants.ALL_CAT_ICONS_NEW[modded_id - 1]);
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        lpIv.width = (int) (primaryIconWidth * dwPercentage);
        ivIcon.setLayoutParams(lpIv);

           tvName.setText(translateCategory(ci.getCatName()));
           tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));

/**************************
 *
 *
 *
 *
 *
 *This OnClickListener will be called for clicking category items from the left side list
 *
 *
 *
 *
 * ************************/

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(flag2,1);
                clicked.clear();
                filterclicked=false;
                fHolder.setVisibility(View.GONE);
                headerHolder.clear();
                currentCategoryID = ci.getId();

                for(int i= 0; i < llCatListHolder.getChildCount(); i++){
                    ImageView iv = (ImageView) ((ViewGroup)llCatListHolder.getChildAt(i)).getChildAt(0);

                    // new background because something has changed
                    // check if it's not the imageView you just clicked because you don't want to change its background
                    iv.setImageResource(0);

                    iv.setImageResource(AppConstants.ALL_CAT_ICONS_NEW[i]);
                }

                /*code for category*/
                /*following code will be different for each category*/
                /*category id 1 means education.
                * category id 2 means health
                * category id 3 means entertainment
                * category id 4 means government
                * category id 5 means legal
                * category id 6 means financial
                * category id 7 means job*/
                switch (currentCategoryID) {/*
                for clicking in category panel of left*/

                    case AppConstants.EDUCATION:


                     //   EDD.clear();
                        educlicked=true;
                        helclicked=false;
                        entclicked=false;
                        legclicked=false;
                        finclicked=false;
                        govclicked=false;
                        jobclicked=false;
                        catStatus =true;
                        setFilcatid(currentCategoryID);
                        calladapter(catStatus);



                        ArrayList<EduNewModel> educationServiceProvider;
                    educationServiceProvider = constructEducationListItem();
                        mapcalledstatus=true;
                     callMapFragmentWithEducation(educationServiceProvider,true);


                        ivIcon.setImageResource(R.drawable.ic_education);


                        if(SearchClicked)
                        {
                            filterHolder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_education);


                        break;
                    case AppConstants.HEALTH:

                        //HEL.clear();
                        helclicked=true;
                        educlicked=false;
                        entclicked=false;
                        legclicked=false;
                        finclicked=false;
                        govclicked=false;
                        jobclicked=false;
                        catStatus =true;
                        setFilcatid(currentCategoryID);
                        calladapter(catStatus);
                        if(SearchClicked)
                        {
                            filterHolder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_health);


                        ArrayList<HealthNewDBModelMain> healthServiceProvider;
                        healthServiceProvider = constructHealthListItem();
                        mapcalledstatus=true;

                        callMapFragmentWithHealth(healthServiceProvider,true);






                        break;

                    //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:

                     //   ENT.clear();
                        entclicked=true;
                        educlicked=false;
                        helclicked=false;
                        legclicked=false;
                        finclicked=false;
                        govclicked=false;
                        jobclicked=false;
                        setFilcatid(currentCategoryID);
                        catStatus =true;
                        calladapter(catStatus);
                        if(SearchClicked)
                        {
                            filterHolder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }


                        ivIcon.setImageResource(0);

                        ArrayList<EntertainmentNewDBModel> entertainmentServiceProvider;
                        entertainmentServiceProvider = constructEntertainmentListItem();
                        mapcalledstatus=true;
                        callMapFragmentWithEntertainment(entertainmentServiceProvider,true);

                        ivIcon.setImageResource(R.drawable.ic_entertainment);









                        break;


                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:

                        govclicked=true;
                     //   GOV.clear();
                        entclicked=false;
                        educlicked=false;
                        helclicked=false;
                        legclicked=false;
                        finclicked=false;

                        jobclicked=false;
                        setFilcatid(currentCategoryID);

                        catStatus =true;
                        calladapter(catStatus);
                        if(SearchClicked)
                        {
                            filterHolder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_government);


                        mapcalledstatus=true;
                        ArrayList<GovernmentNewDBModel> governmentNewItems;
                        governmentNewItems = constructgovListItem();
                        callMapFragmentWithGovernment(governmentNewItems,true);













//                        toolbar.setVisibility(View.VISIBLE);
                       // if (governmentNewItems.size()==0) {

                          //  AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this,"দুঃখিত! তথ্য পাওয়া যায় নি","");
//                            final android.app.AlertDialog alertDialog2 = new android.app.AlertDialog.Builder(PlaceDetailsActivityNewLayout.this).create();
//
//                            alertDialog2.setMessage("");
//                            alertDialog2.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                       alertDialog2.dismiss();
//                                        }
//                                    });
//                            alertDialog2.getWindow().setLayout(200, 300);
//                            alertDialog2.show();তঃ//////
                        break;
                    case AppConstants.LEGAL:

                      //  LEG.clear();
                        legclicked=true;
                        entclicked=false;
                        educlicked=false;
                        helclicked=false;
                        finclicked=false;
                        govclicked=false;
                        jobclicked=false;
                        setFilcatid(currentCategoryID);
                        catStatus =true;
                        calladapter(catStatus);
                        if(SearchClicked)
                        {
                            filterHolder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }


                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_legal);



                        ArrayList<LegalAidNewDBModel> legalaidServiceProvider;
                        mapcalledstatus=true;
                        legalaidServiceProvider = constructlegalaidListItem();
                        callMapFragmentWithLegal(legalaidServiceProvider,true);
                        break;
                    case AppConstants.FINANCIAL:

                     //   FIN.clear();
                        finclicked=true;
                        entclicked=false;
                        educlicked=false;
                        helclicked=false;
                        legclicked=false;
                        govclicked=false;
                        jobclicked=false;
                        setFilcatid(currentCategoryID);
                        catStatus =true;
                        calladapter(catStatus);
                        if(SearchClicked)
                        {
                            filterHolder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_finance);


                        ArrayList<FinancialNewDBModel> financialNewItems;
                        financialNewItems = constructfinancialListItem();
                        callMapFragmentWithFinancial(financialNewItems,true);
                        mapcalledstatus=true;
                        break;


                    /////// NGO ////
                    case AppConstants.NGO:

                        //   FIN.clear();
                        ngoclicked = true;
                        entclicked = false;
                        educlicked = false;
                        helclicked = false;
                        legclicked = false;
                        govclicked = false;
                        jobclicked = false;
                        setFilcatid(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);
                        if (SearchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_ngos);


                        ArrayList<NGONewDBModel> NGOServiceProviderItemNew;
                        NGOServiceProviderItemNew = constructngoListItem();
                        callMapFragmentWithNgo(NGOServiceProviderItemNew, true);
                        mapcalledstatus = true;
                        break;
                    ///// Ngo///


                    ////// Religious /////

                    case AppConstants.RELIGIOUS:

                        //   FIN.clear();
                        religiousclicked= true;
                        ngoclicked = false;
                        entclicked = false;
                        educlicked = false;
                        helclicked = false;
                        legclicked = false;
                        govclicked = false;
                        jobclicked = false;
                        setFilcatid(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);
                        if (SearchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.shelter);


                        ArrayList<ReligiousNewDBModel> ReligiousServiceProviderItemNew;
                        ReligiousServiceProviderItemNew = constructreligiousListItem();
                        callMapFragmentWithReligious(ReligiousServiceProviderItemNew, true);
                        mapcalledstatus = true;
                        break;

                    ////// Religious end /////




                    default:
                        break;
                }
            }
        });

        return v;
    }
    public void constructSubCategoryItemList(int cat_id,String header)
    {

        ArrayList<SubCategoryItem> subCategoryItems;
        subCategoryItems = constructSubCategoryListItem(cat_id,header);




        ArrayList<String> itemName = new ArrayList<String>();

        for(SubCategoryItem si : subCategoryItems)
        {
            itemName.add(si.getSubCatName());
        }
        int i=0;





    }
    private ArrayList<SubCategoryItem> constructSubCategoryListItem(int cat_id, String header)
    {
        ArrayList<SubCategoryItem> subCategoryItems;
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
        subCategoryItems=subCategoryTable.getAllSubCategoriesHeader(cat_id,header);


        return subCategoryItems;
    }


    /*private void constructSubCategoryList(ArrayList<SubCategoryItem> subCategoryList, double dwPercentage, int cat_id) {
        llSubCatListHolder.removeAllViews();
        ArrayList<String> header = new ArrayList<>();
        subcategory=0;
        for (SubCategoryItem si : subCategoryList) {

            if(!header.contains(si.getSubcatHeader()))
            {
                header.add(si.getSubcatHeader());
                llSubCatListHolder.addView(getSubCategoryListItemView(si,dwPercentage,cat_id));
            }
        }
    }
*/
    private void categoryListBuildUp(int currentCategoryID)
    {


    }

    private View getSubCategoryListItemView(final SubCategoryItem si, double dwPercentage, final int cat_id)
    {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();


        View v;
        LayoutInflater li = LayoutInflater.from(this);


        v = li.inflate(R.layout.subcatholderlist, null, false);

        final ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconSCatList);
        tvName = (TextView) v.findViewById(R.id.tv_sub_cat_name);
        ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONSBUTTON2[ subcategory++]);



        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();

        lpIv.width = (int) (primaryIconWidth * dwPercentage);


        ivIcon.setLayoutParams(lpIv);
        tvName.setTextColor(Color.WHITE);

        tvName.setText(si.getSubCatHeaderBn());
        tvName.setTextSize(12);
        flag=true;
        //tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));
        unknown =0;
/**************************
 **
 *
 *
 *This OnClickListener will be called for clicking subcategory items from the right list
 *
 *
 *
 *
 * ************************/


        return v;
    }

    private ArrayList<SubCategoryItem> getSubCategoryList(int id) {
        // TODO Get sub-categories from the SUB_CATEGORY local table : NEXT PHASE
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
        return subCategoryTable.getAllSubCategories(id);
    }

/*
    private void showAnimatedSubcategories(final ArrayList<SubCategoryItem> subCatList, double dwPerc, int iconId, final int cat_id) {
        isCatExpandedOnce = true;


        // TODO Inflate the sub-category list from right

        if(subCatShowFlag==1&&showList!=1)
        {
            svsholder.setVisibility(View.VISIBLE);
            svs.setVisibility(View.VISIBLE);
            llSubCatListHolder.setVisibility(View.VISIBLE);
        }
        subCatShowFlag=1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(showList!=1)
                    svsholder.setVisibility(View.VISIBLE);
                svs.setVisibility(View.VISIBLE);
                llSubCatListHolder.setVisibility(View.VISIBLE);


                llSubCatListHolder.startAnimation(slideInFromRightAnim());
                constructSubCategoryList(subCatList, 1.0, cat_id);
            }
        }, ANIM_INTERVAL *
                (int) (50 *
                        (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                ));

    }


*/


    private Animation slideInFromRightAnim() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromRight.setDuration(ANIM_INTERVAL *
                (int) (100 *
                        (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                )
        );
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }




    /*********************************************************methods for education**********************************************/


    //  need to fix later
    private ArrayList<EduNewModel> constructEducationListItem()
    {
        ArrayList<EduNewModel> educationServiceProvider;
        EduNewDBTableMain educationNewTable = new EduNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        educationServiceProvider = educationNewTable.getAllEducationSubCategoriesInfo(wardId,Areakeyword);
        return educationServiceProvider;
    }



/*
* call mapfragment functions load fragments of map. based on location */
    private void callMapFragmentWithEducation(ArrayList<EduNewModel> educationServiceProviderItems,boolean s)
    {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        /*if(locationNameId==1)  fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);

        else if(locationNameId==2) fragment.getMapViewController().setCenter(AppConstants.PARIS1);
        else fragment.getMapViewController().setCenter(AppConstants.TWELVE);*/
        fragment.getMapViewController().setZoom(15);
          fragment.getMapViewController().setCenter(getLocation());

            fragment.setCategoryId(currentCategoryID);
            fragment.Setsubcategories(currentCategoryID);
            fragment.setEducationServiceProvider(educationServiceProviderItems);
            fragment.eduicons();
           // fragment.Drawedu(edid,s);
            mainedcalled=true;


      /*  else {
            if(mainedcalled)
            {

                mainedcalled=false;
            }
            fragment.Drawedu(edid,s);
        }*/
        // EDD.clear();
    }
    /*
    * this is the default map view based on intent location name.If user change from spinner; this is also called*/
    private void callMapFragment(String lat, String lon) {

        FragmentManager fragmentManager = getFragmentManager();
        if(mapfirst) {


            mapFragment = new MapFragmentOSM();
            mapFragment.setLocationName(Areakeyword);
            mapFragment.setLat(lat);
            mapFragment.setLon(lon);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.map_fragment, mapFragment, "MAP");
            fragmentTransaction.addToBackStack("MAP");
            fragmentManager.executePendingTransactions();
            fragmentTransaction.commit();
            mapfirst=false;
        }

/*
        MapFragmentOSM prev_fragment = (MapFragmentOSM) getFragmentManager().findFragmentByTag("MAP");
        if(prev_fragment!=null&&prev_fragment.getMapViewController() != null)
        {
            if(locationNameId==1)  prev_fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);
            else  if(locationNameId==2) prev_fragment.getMapViewController().setCenter(AppConstants.PARIS1);
            else prev_fragment.getMapViewController().setCenter(AppConstants.TWELVE);

            prev_fragment.getMapViewController().setZoom(16);
            if (mapcalledstatus) {
                Arrays.fill(flag2,1);
                if(currentCategoryID==1){
                    educlicked=false;
                    prev_fragment.setCategoryId(1);
                    ArrayList<EduNewModel> educationServiceProviderItems;
                    educationServiceProviderItems = constructEducationListItem();
                    prev_fragment.setEducationServiceProvider(educationServiceProviderItems);
                    prev_fragment.eduicons();
                    prev_fragment.Drawedu(-1,true);

                }
                else if(currentCategoryID==2){
                    helclicked=false;
                    mapFragment.setCategoryId(2);
                    ArrayList<HealthNewDBModelMain> healthServiceProviderItems;
                    healthServiceProviderItems = constructHealthListItem();
                    mapFragment.setHealthServiceProvider(healthServiceProviderItems);
                    prev_fragment.healthicons();
                    prev_fragment.Drawhel(-1,true);
                }
                else if(currentCategoryID==3){
                    entclicked=false;
                    mapFragment.setCategoryId(3);
                    ArrayList<EntertainmentNewDBModel> entertainmentServiceProviderItems;
                    entertainmentServiceProviderItems = constructEntertainmentListItem();
                    mapFragment.setEntertainmentServiceProvider(entertainmentServiceProviderItems);
                    prev_fragment.enticons();
                    prev_fragment.enticons();
                    prev_fragment.Drawent(-1,true);
                }
                else if(currentCategoryID==4) {
                    govclicked = false;
                    mapFragment.setCategoryId(4);
                    ArrayList<GovernmentNewItem> governmentNewItems;
                    governmentNewItems = constructgovListItem();
                    if (governmentNewItems.size() == 0) {

                        AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this,"দুঃখিত! তথ্য পাওয়া যায় নি","");


//                        final android.app.AlertDialog alertDialog2 = new android.app.AlertDialog.Builder(PlaceDetailsActivityNewLayout.this).create();
//
//                        alertDialog2.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
//                        alertDialog2.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        alertDialog2.dismiss();
//                                    }
//                                });
//                        alertDialog2.getWindow().setLayout(200, 300);
//                        alertDialog2.show();
                    }
                    mapFragment.setGovernmentNewItems(governmentNewItems);
                    prev_fragment.govicons();
                    prev_fragment.Drawgov(-1,true);

                }
                else if(currentCategoryID==5){
                    legclicked=false;
                    mapFragment.setCategoryId(5);
                    ArrayList<LegalAidServiceProviderItemNew> legalAidServiceProviderItems;
                    legalAidServiceProviderItems = constructlegalaidListItem(5);
                    mapFragment.setLegalaidServiceProvider(legalAidServiceProviderItems);
                    prev_fragment.legicons();
                    prev_fragment.Drawleg(-1,true);
                }
                else if(currentCategoryID==6){
                    finclicked=false;
                    mapFragment.setCategoryId(6);
                    ArrayList<FinancialNewItem> financialNewItems;
                    financialNewItems = constructfinancialListItem();
                    mapFragment.setFinancialServiceProvider(financialNewItems);
                    prev_fragment.finicons();
                    prev_fragment.Drawfin(-1,true);
                }
                ArrayList<SubCategoryItem> subCatList = getSubCategoryList(currentCategoryID);
              }

        }*/
    }

    /***********************************************************Methods for Health*************************************************/

    // need to fix later
    //
     private ArrayList<HealthNewDBModelMain> constructHealthListItem()
    {
        /*ArrayList<HealthNewDBModelMain> healthServiceProvider;
        HealthNewDBTableMain healthServiceProviderTable = new HealthNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        healthServiceProvider = healthServiceProviderTable.getAllHealth(wardId,Areakeyword);
        return healthServiceProvider; */
        return null;
    }

    private void callMapFragmentWithHealth(ArrayList<HealthNewDBModelMain> healthServiceProviderItemNews,boolean s)
    {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapViewController().setCenter(getLocation());
        fragment.getMapViewController().setZoom(15);
        fragment.Setsubcategories(currentCategoryID);
            fragment.setCategoryId(currentCategoryID);
            fragment.setHealthServiceProvider(healthServiceProviderItemNews);
            fragment.healthicons();

            mainedcalled=true;

        // EDD.clear();
    }


    /**********************************************************Methods for entertainment*******************************************/

    private ArrayList<EntertainmentNewDBModel> constructEntertainmentListItem()
    {
        /*ArrayList<EntertainmentNewDBModel> entertainmentServiceProviderItemNews;
        EntNewDBTable entertainmentServiceProviderTableNew = new EntNewDBTable(PlaceDetailsActivityNewLayout.this);
        entertainmentServiceProviderItemNews = entertainmentServiceProviderTableNew.getAllEntertainmentinfo(wardId,Areakeyword);
        return entertainmentServiceProviderItemNews;*/
        return null;
    }

    private void callMapFragmentWithEntertainment(ArrayList<EntertainmentNewDBModel> entertainmentServiceProviderItemNews,boolean s)
    {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);

        fragment.getMapViewController().setZoom(15);
        fragment.getMapViewController().setCenter(getLocation());


            fragment.setCategoryId(currentCategoryID);
        fragment.Setsubcategories(currentCategoryID);
            fragment.setEntertainmentServiceProvider(entertainmentServiceProviderItemNews);
            fragment.enticons();

            mainedcalled=true;


    }




    /**********************************************************Methods for government**********************************************/

    private ArrayList<GovernmentNewDBModel> constructgovListItem()
    {
        /*ArrayList<GovernmentNewDBModel> governmentNewItems;
        GovNewDBTable governmentNewTable = new GovNewDBTable(PlaceDetailsActivityNewLayout.this);
        governmentNewItems = governmentNewTable.getAllGov(wardId,Areakeyword);
        return governmentNewItems;*/
        return null;
    }

    private void callMapFragmentWithGovernment(ArrayList<GovernmentNewDBModel> governmentNewItems,boolean s)
    {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapViewController().setCenter(getLocation());
        fragment.getMapViewController().setZoom(15);


            fragment.setCategoryId(currentCategoryID);
            fragment.setGovernmentNewItems(governmentNewItems);
        fragment.Setsubcategories(currentCategoryID);
            fragment.govicons();
            mainedcalled=true;


    }



    /**********************************************************Methods for legal***************************************************/

    private ArrayList<LegalAidNewDBModel> constructlegalaidListItem()
    {
        /*ArrayList<LegalAidNewDBModel> legalaidServiceProvider;
        LegalAidNewDBTable legalAidServiceProviderTable = new LegalAidNewDBTable(PlaceDetailsActivityNewLayout.this);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegal(wardId,Areakeyword);


        return legalaidServiceProvider;*/
        return null;
    }

    private void callMapFragmentWithLegal(ArrayList<LegalAidNewDBModel> legalAidServiceProviderItemNews,boolean s)
    {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapViewController().setCenter(getLocation());
        fragment.getMapViewController().setZoom(15);


            fragment.setCategoryId(currentCategoryID);
            fragment.setLegalaidServiceProvider(legalAidServiceProviderItemNews);
        fragment.Setsubcategories(currentCategoryID);
            fragment.legicons();

            mainedcalled=true;


    }




    /**********************************************************Methods for financial**********************************************/
    private ArrayList<FinancialNewDBModel> constructfinancialListItem()
    {
        /*ArrayList<FinancialNewDBModel> financialNewItems;
        FinNewDBTable financialServiceNewTable = new FinNewDBTable(PlaceDetailsActivityNewLayout.this);
        financialNewItems = financialServiceNewTable.getAllFinancial(wardId,Areakeyword);
        return financialNewItems;*/
        return null;
    }
    private void callMapFragmentWithFinancial(ArrayList<FinancialNewDBModel> financialNewItems,boolean s)
    {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapViewController().setCenter(getLocation());
        fragment.getMapViewController().setZoom(15);


            fragment.setCategoryId(currentCategoryID);
            fragment.Setsubcategories(currentCategoryID);
            fragment.setFinancialServiceProvider(financialNewItems);
            fragment.finicons();

            mainedcalled=true;

    }


    /**********************************************************Methods for NGO***************************************************/

    private ArrayList<NGONewDBModel> constructngoListItem() {
        /*ArrayList<NGONewDBModel> ngoServiceProvider;
        NGONewDBTable ngoServiceProviderTable = new NGONewDBTable(PlaceDetailsActivityNewLayout.this);
        ngoServiceProvider = ngoServiceProviderTable.getAllNGO(wardId, Areakeyword);


        return ngoServiceProvider;*/
    }

    private void callMapFragmentWithNgo(ArrayList<NGONewDBModel> ngoServiceProviderItemNews, boolean s) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapViewController().setCenter(getLocation());
        fragment.getMapViewController().setZoom(15);


        fragment.setCategoryId(currentCategoryID);
        fragment.setNgoServiceProvider(ngoServiceProviderItemNews);
        fragment.Setsubcategories(currentCategoryID);
        fragment.ngoicons();

        mainedcalled = true;


    }



    /**********************************************************Methods for Religious***************************************************/

    private ArrayList<ReligiousNewDBModel> constructreligiousListItem() {
        /*ArrayList<ReligiousNewDBModel> religiousServiceProvider;
        ReligiousNewDBTable religiousServiceProviderTable = new ReligiousNewDBTable(PlaceDetailsActivityNewLayout.this);
        religiousServiceProvider = religiousServiceProviderTable.getAllReligious(wardId, Areakeyword);


        return religiousServiceProvider;*/
    }

    private void callMapFragmentWithReligious(ArrayList<ReligiousNewDBModel> religiousServiceProviderItemNews, boolean s) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapViewController().setCenter(getLocation());
        fragment.getMapViewController().setZoom(15);


        fragment.setCategoryId(currentCategoryID);
        fragment.setReligiousServiceProvider(religiousServiceProviderItemNews);
        fragment.Setsubcategories(currentCategoryID);
        fragment.religiousicons();

        mainedcalled = true;


    }





    /**********************************************************Methods for Bazar Loading*****************************************************/



/*
* load all the data in arraylist; then store all info in another adapter. This adapter is then set in search
* so that when user taps on education; it can just sort in arraylist rather than querying in database all the time*/


    public void Populateholder(String place)
    {
        filterText = (EditText)findViewById(R.id.searchall);
        filterText.setTextColor(getResources().getColor(R.color.white));
        EduNewDBTableMain educationServiceProviderTable=new EduNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        EntNewDBTable entertainmentServiceProviderTable=new EntNewDBTable(PlaceDetailsActivityNewLayout.this);
        HealthNewDBTableMain healthServiceProviderTable = new HealthNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        FinNewDBTable financialServiceProviderTable = new FinNewDBTable(PlaceDetailsActivityNewLayout.this);
        GovNewDBTable governmentNewTable=new GovNewDBTable(PlaceDetailsActivityNewLayout.this);
        LegalAidNewDBTable legalAidServiceProviderTable = new LegalAidNewDBTable(PlaceDetailsActivityNewLayout.this);
        NGONewDBTable ngoServiceProviderTable = new NGONewDBTable(PlaceDetailsActivityNewLayout.this);
        ReligiousNewDBTable religiousServiceProviderTable = new ReligiousNewDBTable(PlaceDetailsActivityNewLayout.this);

        //  need to fix later

        fetchedEnt =entertainmentServiceProviderTable.getAllEntertainmentinfo(wardId,Areakeyword);
        fetchedFin =financialServiceProviderTable.getAllFinancial(wardId,Areakeyword);
        fetchedLegal =legalAidServiceProviderTable.getAllLegal(wardId,Areakeyword);
        fetchedHealth =healthServiceProviderTable.getAllHealth(wardId,Areakeyword);
        fetchedEdu =educationServiceProviderTable.getAllEducationSubCategoriesInfo(wardId,Areakeyword);
        fetchedGov =governmentNewTable.getAllGov(wardId,Areakeyword);
        fetchedNgo = ngoServiceProviderTable.getAllNGO(wardId, Areakeyword);
        fetchedReligious = religiousServiceProviderTable.getAllReligious(wardId, Areakeyword);


        String nameen;
        String namebn;

        int node;
        String refname;

        // need to fix later


        for (int i = 0; i< fetchedEdu.size(); i++)
        {

            nameen= fetchedEdu.get(i).getNameen();
            node= fetchedEdu.get(i).getEduId();
            refname= fetchedEdu.get(i).getRefnumm();
            namebn= fetchedEdu.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,10000);
            allHolders.add(all);
        }


        for (int i = 0; i< fetchedHealth.size(); i++)
        {

            nameen= fetchedHealth.get(i).getNameen();
            node= fetchedHealth.get(i).getHealthid();
            refname= fetchedHealth.get(i).getRefnumm();
            namebn= fetchedHealth.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,20000);
            allHolders.add(all);
        }


        for (int i = 0; i< fetchedLegal.size(); i++)
        {

            nameen= fetchedLegal.get(i).getNameen();
            node= fetchedLegal.get(i).getLegalid();
            refname= fetchedLegal.get(i).getRefnumm();
            namebn= fetchedLegal.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,50000);
            allHolders.add(all);
        }
        for (int i = 0; i< fetchedEnt.size(); i++)
        {

            nameen= fetchedEnt.get(i).getNameen();
            node= fetchedEnt.get(i).getEntid();
            refname= fetchedEnt.get(i).getRefnumm();
            namebn= fetchedEnt.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,30000);
            allHolders.add(all);
        }
        for (int i = 0; i< fetchedFin.size(); i++)
        {

            nameen= fetchedFin.get(i).getNameen();
            node= fetchedFin.get(i).getFinid();
            refname= fetchedFin.get(i).getRefnumm();
            namebn= fetchedFin.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,60000);
            allHolders.add(all);


        }

        for (int i = 0; i< fetchedGov.size(); i++)
        {

            nameen= fetchedGov.get(i).getNameen();
            node= fetchedGov.get(i).getGovid();
            refname= fetchedGov.get(i).getRefnumm();
            namebn= fetchedGov.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,40000);
            allHolders.add(all);


        }

        /////// ngo /////
        for (int i = 0; i < fetchedNgo.size(); i++) {

            nameen = fetchedNgo.get(i).getNameen();
            node = fetchedNgo.get(i).getNgoid();
            refname = fetchedNgo.get(i).getRefnumm();
            namebn = fetchedNgo.get(i).getNamebn();

            AllHolder all = new AllHolder(node, refname, nameen, namebn, 70000);
            allHolders.add(all);
        }

        /////// ngo /////




        /////// religious /////
        for (int i = 0; i < fetchedReligious.size(); i++) {

            nameen = fetchedReligious.get(i).getNameen();
            node = fetchedReligious.get(i).getReligousid();
            refname = fetchedReligious.get(i).getRefnumm();
            namebn = fetchedReligious.get(i).getNamebn();

            AllHolder all = new AllHolder(node, refname, nameen, namebn, 80000);
            allHolders.add(all);
        }

        /////// religious /////



        calladapter(false);

    }

    private String timeConverter(String time) {


        String timeInBengali = "";

        try
        {

            String[] separated = time.split(":");


            int hour = Integer.valueOf(separated[0]);
            int times = Integer.valueOf(separated[1]);

            if (hour >= 6 && hour < 12)
                timeInBengali = "সকাল " + English_to_bengali_number_conversion(String.valueOf(hour));
            else if (hour == 12)
                timeInBengali = "দুপুর  " + English_to_bengali_number_conversion(String.valueOf(hour));
            else if (hour > 12 && hour < 16)
                timeInBengali = "দুপুর  " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 15 && hour < 18)
                timeInBengali = "বিকেল " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 17 && hour < 20)
                timeInBengali = "সন্ধ্যা " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            else if (hour > 20)
                timeInBengali = "রাত " + English_to_bengali_number_conversion(String.valueOf(hour - 12));
            if (times != 0)
                timeInBengali = timeInBengali + " টা " + English_to_bengali_number_conversion(String.valueOf(times)) + " মিনিট";
            else
                timeInBengali = timeInBengali + " টা";
        }
        catch (Exception e)
        {

        }

        return timeInBengali;

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

    private static String translateCategory(String catName){
        String banglaName = "";
        switch(catName){
            case "Education": banglaName += "শিক্ষা";
                break;
            case "Health": banglaName += "স্বাস্থ্য";
                break;
            case "Entertainment": banglaName += "বিনোদন";
                break;
            case "Government": banglaName += "সরকারী সেবা";
                break;
            case "Legal": banglaName += "আইনী";
                break;
            case "Finance" : banglaName += "অর্থনৈতিক";
                break;
            case "NGO": banglaName += "এনজিও";
                break;
            case "Religious Shelter": banglaName += "আশ্রয়কেন্দ্র";
                break;
            default: banglaName += "অন্যান্য";
        }

        return banglaName;
    }

    public static boolean moreThanOnce(ArrayList<Integer> list, int searched) {

        int numCount = 0;
        boolean more = false;

        for (int thisNum : list) {
            if (thisNum == searched) {
                numCount ++ ;
            }
        }

        if (numCount > 1) {
            more = true;
        }

        return more;
    }

    /*if user has already selected any category; then allHolders been filtered using the category id/sub cat id*/
    private void calladapter(boolean status)
    {
        boolean instatus=status;
        if(instatus==true)
        {
            int gotcatid=getFilcatid();
            catHolders.clear();
            for(int ii=0;ii<allHolders.size();ii++)
            {
                if(allHolders.get(ii).getCatid()==gotcatid)
                {
                    catHolders.add(allHolders.get(ii));
                }
            }
            if(filterclicked)
            {
                checknum= String.valueOf(getSnumber());
            }
            else  checknum= String.valueOf(0);
            if(Integer.parseInt(checknum)!=0)
            {
                subcatHolders.clear();
                for(int iii=0;iii<catHolders.size();iii++)
                {
                    if(catHolders.get(iii).getRefnum().contains(checknum))
                    {
                        subcatHolders.add(catHolders.get(iii));
                    }
                }
                adapter = new ListViewAdapterAllCategories(this, subcatHolders);

                allitemList.setAdapter(adapter);
            }
            else if (Integer.parseInt(checknum)==0){
                adapter = new ListViewAdapterAllCategories(this, catHolders);

                allitemList.setAdapter(adapter);
            }
        }
        else {
            adapter = new ListViewAdapterAllCategories(this, allHolders);

            allitemList.setAdapter(adapter);
        }

        int[] colors = {0, 0xFFFF0000, 0}; // red for the example
        allitemList.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        allitemList.setDividerHeight(1);

        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = filterText.getText().toString().toLowerCase(Locale.getDefault());

                adapter.filter(text);
                //calladapter(true);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }

        });
        allitemList.setFastScrollEnabled(false);
        allitemList.setFastScrollEnabled(true);
    }
    /*fun1 and fun2 is for selecting only one item from two radiogroup to get the filter id.*/
    public void fun1() {
        rightGroup.setOnCheckedChangeListener(null);
        rightGroup.clearCheck();
        rightGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fun2();
                int buttonId= rightGroup.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)findViewById(buttonId);
                setFilterword((String) radioButton.getText());
                int num=Findsubcatid(filterword);
                calladapter(true);

                Log.v("Inside fun1",String.valueOf(num));
            }
        });
    }





    public void fun2() {
        leftGroup.setOnCheckedChangeListener(null);
        leftGroup.clearCheck();
        leftGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                fun1();
                int buttonId= leftGroup.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)findViewById(buttonId);
                setFilterword((String) radioButton.getText());
                int num=Findsubcatid(filterword);
                calladapter(true);

                Log.v("Inside fun2","fun1");

            }
        });
    }
    private int Findsubcatid(String filterword){

        for (int s=0;s<=>>tagHolders.size();s++)
        {
            if (>>tagHolders.get(s).getSubcatname().equals(filterword))
            {
                setSnumber(>> tagHolders.get(s).getSubcatid());
                break;
            }
        }

        return snumber;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return super.dispatchKeyShortcutEvent(event);
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // callMapFragment(getMergedLocation());

        //   toggleButton.setVisibility(View.VISIBLE);

//
//            map.setVisibility(View.GONE);




        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();







    }




    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        slideInFromRightAnim();
        super.onStart();

        System.out.println("----main activity---onStart---");


        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void supportNavigateUpTo(Intent upIntent) {
        super.supportNavigateUpTo(upIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

public ArrayList<StoredArea> RetriveLocation(String ward,String area)
{
 storedAreaArrayList=storedAreaTable.getstoredlocation(ward,area);
    return storedAreaArrayList;
}

}