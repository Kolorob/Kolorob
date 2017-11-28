package demo.kolorob.kolorobdemoversion.activity;

import android.annotation.SuppressLint;
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
import android.support.design.widget.TabLayout;
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
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.database.StoredAreaTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.fragment.CommonFragment;
import demo.kolorob.kolorobdemoversion.fragment.CompareFragment;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentNew;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentOSM;
import demo.kolorob.kolorobdemoversion.fragment.SearchFragment;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.model.StoredArea;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
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


public class PlaceDetailsActivity <ModelType extends CommonModel> extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    static TabLayout.Tab mapTab;
    Context context;
    MapFragmentNew mapFragment;

    private static final int ANIM_INTERVAL = 150;
    public static int currentCategoryID;
    private static double VIEW_WIDTH;
    private static boolean mapcalledstatus;
    private static FrameLayout map;
    String username = "kolorobapp", password = "2Jm!4jFe3WgBZKEN";
    ArrayList<StoredArea> storedAreaArrayList = new ArrayList<>();
    ArrayList<StoredArea> storedAreaArrayListall = new ArrayList<>();
    ArrayList<StoredArea> storedAreas = new ArrayList<>();
    ArrayList<EduNewModel> firstDataSetEdu, secondDataSetEdu;
    ArrayList<HealthNewDBModelMain> firstDataSetHealth, secondDataSetHealth;
    ArrayList<CategoryItem> categoryList;
    ArrayList<String> headerHolder = new ArrayList<>();
    ArrayList<String> clicked = new ArrayList<>();
    ArrayList<String> filter = new ArrayList<>();
    ArrayList<String> filter2 = new ArrayList<>();
    ArrayList<AllHolder> allHolders = new ArrayList<>();
    ArrayList<AllHolder> catHolders = new ArrayList<>();
    ArrayList<AllHolder> subcatHolders = new ArrayList<>();
    TextView welcomeText, upText;

    TextView tvName;
    CheckedTextView changeArea;
    int spinCount1, spinCount2;
    int filterCategoryId;
    String[] left_part, right_part, health_header;
    boolean doubleBackToExitPressedOnce, reviewGiven, selected, inCompare;
    boolean eduClicked, healthClicked, entClicked, finClicked, govClicked, legalClicked, ngoClicked, shelterClicked;

    boolean catStatus, called;
    Boolean firstRun, firstRunUpdate;
    Boolean navigationCalled, navigationCalledOnce;

    LinearLayout fHolder, fLeft, fRight;
    RelativeLayout catHolder, searchViewHolder, filterHolder;
    ListView allItemList;
    ToggleButton toggleButton;
    Double screenSize;
    ScrollView sv;
    CheckBox checkBox1, checkBox2, checkLeft, checkRight;

    GeoPoint location;
    int[] flag2 = new int[15];
    EditText searchAll, filterText;
    ListViewAdapterAllCategories adapter;
    int value, height, width, sNumber;
    String id1, id2, id3, id4;
    Boolean flag;
    boolean mapFirst = true;
    String comment = "";
    String wardId, areaKeyword, lat, lon, areaName;
    View view, view2;
    ActionBarDrawerToggle toggle;

    NavigationView navigationView;
    StoredAreaTable storedAreaTable;
    CategoryItem categoryItem;
    private Context con;
    private ArrayList<Subcatholder> tagHolders = new ArrayList<>();
    private int primaryIconWidth;
    private int subcategory;
    private LinearLayout llCatListHolder, svHolder;


    private Toolbar toolbar;
    private DrawerLayout drawer;
    private String placeChoice, filterWord, checkNum;

    private int small;
    private Animation mEnterAnimation, mExitAnimation;
    private LinearLayout wholeLayout;

    public Context getContext() {
        return context;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public void setCategoryItem(CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
    }

    public int getFilterCategoryId() {
        return filterCategoryId;
    }

    public void setFilterCategoryId(int filterCategoryId) {
        this.filterCategoryId = filterCategoryId;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.activity_place_details);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        tabLayout.setRotationX(180);

        mapTab = tabLayout.newTab();

        View mapIconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        mapIconView.findViewById(R.id.icon).setBackgroundResource(R.drawable.map);
        tabLayout.addTab(mapTab.setCustomView(mapIconView));

        View searchIconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        searchIconView.findViewById(R.id.icon).setBackgroundResource(R.drawable.search);
        tabLayout.addTab(tabLayout.newTab().setCustomView(searchIconView));

        View jobIconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        jobIconView.findViewById(R.id.icon).setBackgroundResource(R.drawable.job_unselectedtab);
        tabLayout.addTab(tabLayout.newTab().setCustomView(jobIconView));

        View compareIconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        compareIconView.findViewById(R.id.icon).setBackgroundResource(R.drawable.compare);
        tabLayout.addTab(tabLayout.newTab().setCustomView(compareIconView));


        LinearLayout tabs = ((LinearLayout) tabLayout.getChildAt(0));

        for (int i = 0; i < tabs.getChildCount(); i++) {
            LinearLayout item = ((LinearLayout) tabs.getChildAt(i));
            item.setRotationX(180);
        }

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.simpleFrameLayout, new SearchFragment());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        CommonFragment fragment = null;

                        switch (tab.getPosition()) {

                            case 0:
                                fragment = new MapFragmentNew();
                                break;

                            case 1:
                                fragment = new SearchFragment();
                                break;

                            case 2:
                                showJobDialog();
                                break;

                            case 3:
                                fragment = new CompareFragment();
                                break;
                        }

                        if (tab.getPosition() != 2) {
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);

                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fragmentTransaction.commit();

                        }


                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );*/


        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("prefs", 0);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("Value", false);
        editor.putInt("ValueD", 23);
        editor.apply();
        firstRun = settings.getBoolean("firstRunUp", false);
        firstRunUpdate = settings.getBoolean("update_first_run", true);

        wardId = settings.getString("_ward", null);
        areaKeyword = settings.getString("areakeyword", null);

        Log.e("", "Ward ID: " + wardId + "Area: " + areaKeyword);

        storedAreaTable = new StoredAreaTable(PlaceDetailsActivity.this);
        storedAreaArrayListall = storedAreaTable.getAllData();


        if (storedAreaArrayListall.size() == 0) {

            Intent em = new Intent(this, DataLoadingActivity.class);
            startActivity(em);
            finish();
        }
        /*else if (storedAreas.size()==0) {

            Intent em = new Intent(this, AreaUpgrade.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }*/
        else {

            storedAreas = storedAreaTable.getStoredLocation(wardId, areaKeyword);
            setLat(storedAreas.get(0).getLat());
            setLon(storedAreas.get(0).getLon());

            setAreaName(storedAreas.get(0).getAreaBn());
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

            searchViewHolder = (RelativeLayout) findViewById(R.id.searchholder);


            allItemList = (ListView) findViewById(R.id.allitem);
            checkBox1 = (CheckBox) findViewById(R.id.compared);
            checkBox2 = (CheckBox) findViewById(R.id.compared2);



            checkLeft = (CheckBox) findViewById(R.id.checkLeft);
            checkRight = (CheckBox) findViewById(R.id.checkRight);

            catHolder = (RelativeLayout) findViewById(R.id.categoryfilterholder);


            mapcalledstatus = false;
            toolbar = (Toolbar) findViewById(R.id.categorytoolbar);

            welcomeText = (TextView) findViewById(R.id.toptext);
            SharedPreferencesHelper.setCompareData(PlaceDetailsActivity.this, "", 0);
            searchAll = (EditText) findViewById(R.id.searchall);


            filterHolder = (RelativeLayout) findViewById(R.id.filterholder);
            upText = (TextView) findViewById(R.id.textView15);
            changeArea = (CheckedTextView) findViewById(R.id.changearea);
            upText.setText(R.string.menu);
            changeArea.setText(R.string.change_area);
            changeArea.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    storedAreaArrayListall = storedAreaTable.getAllData();

                    Intent em = new Intent(PlaceDetailsActivity.this, DataLoadingActivity.class);
                    startActivity(em);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                }
            });


            setSupportActionBar(toolbar);
            ActionBar ab = getSupportActionBar();
            ab.setHomeAsUpIndicator(R.drawable.menu_icon);
            ab.setDisplayHomeAsUpEnabled(true);

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

                /** Called when a drawer has settled in a completely open state. */
                @SuppressLint("RestrictedApi")
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    view = navigationView.getTouchables().get(2);
                    view2 = navigationView.getTouchables().get(4);

                    if (!firstRun || firstRunUpdate) runOverlay_ContinueMethodnavigation();
                    //  getSupportActionBar().setTitle("Navigation!");
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }

                /** Called when a drawer has settled in a completely closed state. */

                @SuppressLint("RestrictedApi")
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


            svHolder = (LinearLayout) findViewById(R.id.llCategoryListHolderback);

            svHolder.setVisibility(View.VISIBLE);

            sv = (ScrollView) findViewById(R.id.svCategoryListHolder);

            sv.setVisibility(View.VISIBLE);
            screenSize = AppUtils.ScreenSize(PlaceDetailsActivity.this);


//        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
            wholeLayout = (LinearLayout) findViewById(R.id.wholeLayout);
            mEnterAnimation = new AlphaAnimation(0f, 1f);
            mEnterAnimation.setDuration(600);
            mEnterAnimation.setFillAfter(true);

            mExitAnimation = new AlphaAnimation(1f, 0f);
            mExitAnimation.setDuration(600);
            mExitAnimation.setFillAfter(true);
            String AREA = getAreaName();
            AREA = AREA.replace(' ', '\n');
            welcomeText.setText(AREA);


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

            CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivity.this);
            categoryList = categoryTable.getAllData();
            constructCategoryList(categoryList);
            //rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
            //rlSubCatHolder.setVisibility(View.INVISIBLE);


            populateHolder();
            try {
                callMapFragment(lat, lon);
            } catch (Exception e) {

            }

            if (!firstRun || firstRunUpdate) runOverlay_ContinueMethod();



            toggleButton = (ToggleButton) findViewById(R.id.toggle);
            toggleButton.setVisibility(View.VISIBLE);
            toggleButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (toggleButton.isChecked()) {

                        sv.setVisibility(View.GONE);
                        svHolder.setVisibility(View.GONE);
                        llCatListHolder.setVisibility(View.GONE);

                    } else {

                        sv.setVisibility(View.VISIBLE);
                        svHolder.setVisibility(View.VISIBLE);
                        llCatListHolder.setVisibility(View.VISIBLE);

                    }

                }
            });
        }


    }








    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

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

        } else if (id == R.id.emergency_info) {

            Intent em = new Intent(this, EmergencyActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        } else if (id == R.id.new_place) {
            storedAreaArrayListall = storedAreaTable.getAllData();
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

        } else if (id == R.id.old_place) {

            //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
            Intent em = new Intent(this, AreaUpgrade.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        } else if (id == R.id.about_us) {

            Intent em = new Intent(this, AboutUs.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        } else if (id == R.id.disclaimer) {

            Intent em = new Intent(this, Disclaimer.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        } else if (id == R.id.tutorial) {

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


    public void showJobDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(PlaceDetailsActivity.this);
        View promptView = layoutInflater.inflate(R.layout.default_alert, null);


        final Dialog alertDialog = new Dialog(PlaceDetailsActivity.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final TextView header = (TextView) promptView.findViewById(R.id.headers);
        final TextView bodyText = (TextView) promptView.findViewById(R.id.body);
        final ImageView okay = (ImageView) promptView.findViewById(R.id.okay);

        header.setText("!!!");
        header.setTextColor(getResources().getColor(R.color.Black));
        bodyText.setText(R.string.job_portal_coming_soon);
        bodyText.setTextColor(getResources().getColor(R.color.Black));
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        int width = displayMetrics.widthPixels;

        //alertDialog.setCancelable(false);

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 0.0f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);

        alertDialog.getWindow().setLayout((width * 2) / 3, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    private void callMapFragment(String lat, String lon) {

        FragmentManager fragmentManager = getFragmentManager();

        mapFragment = new MapFragmentNew();
        mapFragment.setLocationName(areaKeyword);
        mapFragment.setLat(lat);
        mapFragment.setLon(lon);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment, "MAP");
        fragmentTransaction.addToBackStack("MAP");
        fragmentManager.executePendingTransactions();
        fragmentTransaction.commit();

    }


    private void runOverlay_ContinueMethod() {
        // the return handler is used to manipulate the cleanup of all the tutorial elements

        /*ChainTourGuide tourGuide1 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.category))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_select_category))
                        .setGravity(Gravity.RIGHT)
                )
                // note that there is no Overlay here, so the default one will be used
                .playLater(llCatListHolder);

        ChainTourGuide tourGuide2 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.job_info))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_job))
                        .setGravity(Gravity.TOP)
                )
                .playLater(jobButton);
        ChainTourGuide tourGuide3 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.search_for_service))

                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_select_service))
                        .setGravity(Gravity.TOP)
                )
                .playLater(searchButton);
        ChainTourGuide tourGuide4 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.compare_title))

                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_compare))
                        .setGravity(Gravity.TOP)
                )
                .playLater(compareButton);
        ChainTourGuide tourGuide5 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.menu))

                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_emergency))
                        .setGravity(Gravity.RIGHT)
                )
                .playLater(upText);
        ChainTourGuide tourGuide6 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.change_area))

                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.download_new_area))
                        .setGravity(Gravity.RIGHT)
                )
                .playLater(changeArea);

        Sequence sequence = new Sequence.SequenceBuilder()
                .add(tourGuide1, tourGuide2, tourGuide3, tourGuide4, tourGuide5, tourGuide6)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(mEnterAnimation)
                        .setExitAnimation(mExitAnimation)
                )
                .setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();


        ChainTourGuide.init(this).playInSequence(sequence);*/
    }

    private void runOverlay_ContinueMethodnavigation() {
        // the return handler is used to manipulate the cleanup of all the tutorial elements

        ChainTourGuide tourGuide1 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.new_area_info))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_download_new_area))
                        .setGravity(Gravity.RIGHT)
                )
                // note that there is no Overlay here, so the default one will be used
                .playLater(view);

        ChainTourGuide tourGuide2 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.change_downloaded_info))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_update_info))
                        .setGravity(Gravity.TOP)
                )
                .playLater(view2);


        Sequence sequence2 = new Sequence.SequenceBuilder()
                .add(tourGuide1, tourGuide2)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(mEnterAnimation)
                        .setExitAnimation(mExitAnimation)
                )
                .setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();


        ChainTourGuide.init(this).playInSequence(sequence2);
    }

    // need to change later
    public void onBackPressed_init() {
        if (doubleBackToExitPressedOnce) {


            super.onBackPressed();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
        }
        SharedPreferences settings = PlaceDetailsActivity.this.getSharedPreferences("prefs", 0);

   /*    if(settings.getBoolean("Reviewsent",false)==true && diffInDays>=30)
       {
           SharedPreferences.Editor editor = settings.edit();
           editor.putBoolean("Reviewsent", false);
           Reviewgiven=false;
           editor.apply();
       }*/
        if (!settings.getBoolean("Reviewsent", false)) help();
        ToastMessageDisplay.setText(PlaceDetailsActivity.this, getString(R.string.press_back_to_exit));
        ToastMessageDisplay.showText(this);
        Log.d("In on Back Pressed", "==========");
        this.doubleBackToExitPressedOnce = true;


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }



    public void help() {
        LayoutInflater layoutInflater = LayoutInflater.from(PlaceDetailsActivity.this);
        View promptView = layoutInflater.inflate(R.layout.app_feedback_dialog, null);
        //   final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlaceSelectionActivity.this);
        //  final AlertDialog alert = alertDialogBuilder.create();

        //   alertDialogBuilder.setView(promptView);

        final Dialog alertDialog = new Dialog(PlaceDetailsActivity.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        alertDialog.getWindow().setLayout((width * 4) / 5, WindowManager.LayoutParams.WRAP_CONTENT);


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


        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Float ratings;
                ratings = ratingBar.getRating();
                comment = submit_review.getText().toString();
                if (ratings == 0) ratings = (float) 0.0001;

                sendDataToserver(ratings, comment);
                SharedPreferences settings = PlaceDetailsActivity.this.getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("Reviewsent", true);
                editor.apply();
                reviewGiven = true;
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


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
        for (CategoryItem ci : categoryList2) {
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
        int modded_id = ci.getId() / 10000;
        ivIcon.setImageResource(AppConstants.ALL_CAT_ICONS_NEW[modded_id - 1]);
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        lpIv.width = (int) (primaryIconWidth * dwPercentage);
        ivIcon.setLayoutParams(lpIv);

        tvName.setText(ci.getNameBn());
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
                Arrays.fill(flag2, 1);
                clicked.clear();
                fHolder.setVisibility(View.GONE);
                headerHolder.clear();
                currentCategoryID = ci.getId();

                for (int i = 0; i < llCatListHolder.getChildCount(); i++) {
                    ImageView iv = (ImageView) ((ViewGroup) llCatListHolder.getChildAt(i)).getChildAt(0);

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
                * category id 7 means NGO
                * category id 8 means shelter */

                switch (currentCategoryID) {/*
                for clicking in category panel of left*/

                    case AppConstants.EDUCATION:


                        //   EDD.clear();
                        eduClicked = true;
                        healthClicked = false;
                        entClicked = false;
                        legalClicked = false;
                        finClicked = false;
                        govClicked = false;
                        ngoClicked = false;
                        shelterClicked = false;

                        catStatus = true;

                        setFilterCategoryId(currentCategoryID);
                        calladapter(catStatus);

                        mapcalledstatus = true;
                        callMapFragment((ArrayList<ModelType>) constructEducationListItem());

                       // ivIcon.setImageResource(R.drawable.ic_education);
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_education);

                        break;


                    case AppConstants.HEALTH:

                        healthClicked = true;
                        eduClicked = false;
                        entClicked = false;
                        legalClicked = false;
                        finClicked = false;
                        govClicked = false;
                        ngoClicked = false;
                        shelterClicked = false;
                        catStatus = true;

                        setFilterCategoryId(currentCategoryID);

                        calladapter(catStatus);


                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_health);

                        mapcalledstatus = true;

                        callMapFragment((ArrayList<ModelType>) constructHealthListItem());

                        break;



                    case AppConstants.ENTERTAINMENT:

                        entClicked = true;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        finClicked = false;
                        govClicked = false;
                        ngoClicked = false;
                        shelterClicked = false;

                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);


                        ivIcon.setImageResource(0);
                        mapcalledstatus = true;
                        callMapFragment((ArrayList<ModelType>) constructEntertainmentListItem());
                        ivIcon.setImageResource(R.drawable.ic_entertainment);

                        break;



                    case AppConstants.GOVERNMENT:

                        govClicked = true;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        finClicked = false;
                        ngoClicked = false;
                        shelterClicked = false;

                        setFilterCategoryId(currentCategoryID);

                        catStatus = true;
                        calladapter(catStatus);


                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_government);


                        mapcalledstatus = true;

                        callMapFragment((ArrayList<ModelType>) constructGovListItem());

                        break;


                    case AppConstants.LEGAL:

                        legalClicked = true;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        finClicked = false;
                        govClicked = false;
                        ngoClicked = false;
                        shelterClicked = false;

                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_legal);

                        mapcalledstatus = true;

                        callMapFragment((ArrayList<ModelType>) constructLegalaidListItem());
                        break;


                    case AppConstants.FINANCIAL:

                        finClicked = true;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        govClicked = false;
                        ngoClicked = false;
                        shelterClicked = false;

                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);


                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_finance);

                        callMapFragment((ArrayList<ModelType>) constructFinancialListItem());
                        mapcalledstatus = true;
                        break;



                    case AppConstants.NGO:

                        ngoClicked = true;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        govClicked = false;
                        finClicked = false;
                        shelterClicked = false;

                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);


                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_ngos);

                        callMapFragment((ArrayList<ModelType>) constructNgoListItem());
                        mapcalledstatus = true;

                        break;


                    case AppConstants.RELIGIOUS:

                        shelterClicked = true;
                        ngoClicked = false;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        govClicked = false;
                        finClicked = false;

                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);



                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.shelter);

                        callMapFragment((ArrayList<ModelType>) constructReligiousListItem());
                        mapcalledstatus = true;
                        break;


                    default:
                        break;
                }
            }
        });

        return v;
    }







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


    private ArrayList<EduNewModel> constructEducationListItem() {

        EduNewDBTableMain educationNewTable = new EduNewDBTableMain(PlaceDetailsActivity.this);
        return educationNewTable.getByAreaCategory(wardId, areaKeyword, AppConstants.EDUCATION);

    }


    private ArrayList<HealthNewDBModelMain> constructHealthListItem() {

        HealthNewDBTableMain healthServiceProviderTable = new HealthNewDBTableMain(PlaceDetailsActivity.this);
        return healthServiceProviderTable.getByAreaCategory(wardId, areaKeyword, AppConstants.HEALTH);
    }

    /**********************************************************Methods for entertainment*******************************************/

    private ArrayList<EntertainmentNewDBModel> constructEntertainmentListItem() {

        EntNewDBTable entNewDBTable = new EntNewDBTable(PlaceDetailsActivity.this);
        return entNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.ENTERTAINMENT);
    }


    /**********************************************************Methods for government**********************************************/

    private ArrayList<GovernmentNewDBModel> constructGovListItem() {

        GovNewDBTable govNewDBTable = new GovNewDBTable(PlaceDetailsActivity.this);
        return govNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.GOVERNMENT);
    }


    /**********************************************************Methods for legal***************************************************/

    private ArrayList<LegalAidNewDBModel> constructLegalaidListItem() {

        LegalAidNewDBTable legalAidNewDBTable = new LegalAidNewDBTable(PlaceDetailsActivity.this);
        return legalAidNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.LEGAL);
    }


    /**********************************************************Methods for financial**********************************************/
    private ArrayList<FinancialNewDBModel> constructFinancialListItem() {

        FinNewDBTable finNewDBTable = new FinNewDBTable(PlaceDetailsActivity.this);
        return finNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.FINANCIAL);
    }


    /**********************************************************Methods for NGO***************************************************/

    private ArrayList<NGONewDBModel> constructNgoListItem() {

        NGONewDBTable ngoNewDBTable = new NGONewDBTable(PlaceDetailsActivity.this);
        return ngoNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.NGO);
    }


    /**********************************************************Methods for Religious***************************************************/

    private ArrayList<ReligiousNewDBModel> constructReligiousListItem() {

        ReligiousNewDBTable religiousNewDBTable = new ReligiousNewDBTable(PlaceDetailsActivity.this);
        return religiousNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.RELIGIOUS);
    }


    private void callMapFragment(ArrayList<ModelType> dataList) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapViewController().setCenter(getLocation());
        fragment.getMapViewController().setZoom(15);
        fragment.setSubcategories(currentCategoryID);
        fragment.setCategoryId(currentCategoryID);
        fragment.populateIcons(dataList);

        called = true;

    }





/*
* load all the data in arraylist; then store all info in another adapter. This adapter is then set in search
* so that when user taps on education; it can just sort in arraylist rather than querying in database all the time*/


    public void populateHolder() {
        filterText = (EditText) findViewById(R.id.searchall);
        filterText.setTextColor(getResources().getColor(R.color.white));


        for (EduNewModel edu : constructEducationListItem()) {

            AllHolder all = new AllHolder(edu.getId(), edu.getRefNum(), edu.getNameEn(), edu.getNameBn(), AppConstants.EDUCATION);
            allHolders.add(all);
        }


        for (HealthNewDBModelMain health : constructHealthListItem()) {
            AllHolder all = new AllHolder(health.getId(), health.getRefNum(), health.getNameEn(), health.getNameBn(), AppConstants.HEALTH);
            allHolders.add(all);
        }


        for (LegalAidNewDBModel legal : constructLegalaidListItem()) {

            AllHolder all = new AllHolder(legal.getId(), legal.getRefNum(), legal.getNameEn(), legal.getNameBn(), AppConstants.LEGAL);
            allHolders.add(all);
        }


        for (EntertainmentNewDBModel ent : constructEntertainmentListItem()) {

            AllHolder all = new AllHolder(ent.getId(), ent.getRefNum(), ent.getNameEn(), ent.getNameBn(), AppConstants.ENTERTAINMENT);
            allHolders.add(all);
        }


        for (FinancialNewDBModel fin : constructFinancialListItem()) {

            AllHolder all = new AllHolder(fin.getId(), fin.getRefNum(), fin.getNameEn(), fin.getNameBn(), AppConstants.FINANCIAL);
            allHolders.add(all);

        }

        for (GovernmentNewDBModel gov : constructGovListItem()) {

            AllHolder all = new AllHolder(gov.getId(), gov.getRefNum(), gov.getNameEn(), gov.getNameBn(), AppConstants.GOVERNMENT);
            allHolders.add(all);

        }


        for (NGONewDBModel ngo : constructNgoListItem()) {

            AllHolder all = new AllHolder(ngo.getId(), ngo.getRefNum(), ngo.getNameEn(), ngo.getNameBn(), AppConstants.NGO);
            allHolders.add(all);
        }


        for (ReligiousNewDBModel religious : constructReligiousListItem()) {

            AllHolder all = new AllHolder(religious.getId(), religious.getRefNum(), religious.getNameEn(), religious.getNameBn(), AppConstants.RELIGIOUS);
            allHolders.add(all);
        }


        calladapter(false);

    }

    private String timeConverter(String time) {


        String timeInBengali = "";

        try {

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
        } catch (Exception e) {

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



    /*if user has already selected any category; then allHolders been filtered using the category id/sub cat id*/

    private void calladapter(boolean status) {

        boolean instatus = status;

        if (instatus) {
            int gotcatid = getFilterCategoryId();

            catHolders.clear();

            for (int i = 0; i < allHolders.size(); i++) {
                if (allHolders.get(i).getCatid() == gotcatid) {
                    catHolders.add(allHolders.get(i));
                }
            }

            /*if (filterClicked) {
                checkNum = String.valueOf(getsNumber());
            } else checkNum = String.valueOf(0);*/

            if (Integer.parseInt(checkNum) != 0) {
                subcatHolders.clear();
                for (int i = 0; i < catHolders.size(); i++) {
                    if (catHolders.get(i).getRefnum().contains(checkNum)) {
                        subcatHolders.add(catHolders.get(i));
                    }
                }
                adapter = new ListViewAdapterAllCategories(this, subcatHolders);
            } else if (Integer.parseInt(checkNum) == 0) {
                adapter = new ListViewAdapterAllCategories(this, catHolders);
            }
        } else {
            adapter = new ListViewAdapterAllCategories(this, allHolders);
        }

        allItemList.setAdapter(adapter);

        int[] colors = {0, 0xFFFF0000, 0}; // red for the example
        allItemList.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        allItemList.setDividerHeight(1);

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

        allItemList.setFastScrollEnabled(false);
        allItemList.setFastScrollEnabled(true);
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







    public void sendDataToserver(Float rating, String comment) {
        String username = SharedPreferencesHelper.getUser(PlaceDetailsActivity.this);
        SharedPreferencesHelper.setFeedback(PlaceDetailsActivity.this, username);
        String phone = SharedPreferencesHelper.getNumber(PlaceDetailsActivity.this);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);


        if (phone.equals("")) phone.replace("", "0");
        else {
            String comment2 = "";
            try {
                comment2 = URLEncoder.encode(comment.replace(" ", "%20"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url = "http://kolorob.net/kolorob-new-demo/api/app_rating?phone=" + phone + "&review=" + comment2 + "&rating=" + rating + "&username=" + this.username + "&password=" + this.password;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //   ToastMessageDisplay.ShowToast(PlaceSelectionActivity.this,"ধন্যবাদ");


                            try {
                                ToastMessageDisplay.setText(PlaceDetailsActivity.this, getString(R.string.thanks));
                                ToastMessageDisplay.showText(PlaceDetailsActivity.this);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ToastMessageDisplay.setText(PlaceDetailsActivity.this, error.toString());
                            ToastMessageDisplay.showText(PlaceDetailsActivity.this);
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<>();

                    return params;
                }

            };

// Adding request to request queue

            RequestQueue requestQueue = Volley.newRequestQueue(PlaceDetailsActivity.this);
            requestQueue.add(stringRequest);


        }


    }


    @Override
    public void onBackPressed() {


        if (getFragmentManager().findFragmentById(R.id.simpleFrameLayout) instanceof MapFragmentNew) {
            super.onBackPressed();
        } else {
            mapTab.select();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }


    }


}
