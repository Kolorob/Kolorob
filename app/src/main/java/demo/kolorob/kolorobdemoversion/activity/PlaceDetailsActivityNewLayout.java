package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
import demo.kolorob.kolorobdemoversion.adapters.BazarToolAdapter;
import demo.kolorob.kolorobdemoversion.adapters.Group;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterAllCategories;
import demo.kolorob.kolorobdemoversion.adapters.SearchHolder;
import demo.kolorob.kolorobdemoversion.adapters.ServiceListDisplayAdapter;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.DatabaseManager;
import demo.kolorob.kolorobdemoversion.database.Education.EducationNewTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceNewTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovernmentNewTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTableDetails;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentOSM;
import demo.kolorob.kolorobdemoversion.interfaces.KolorobSpinner;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.BazarItem;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Health.HealthSpecialistItemDetails;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

/**
 * Created by touhid on 12/3/15.
 *
 * @author israt, arafat
 */
public class PlaceDetailsActivityNewLayout extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public int getShowList() {
        return showList;
    }

    EducationServiceProviderTable educationServiceProviderTable;
    EducationNewTable educationNewTable;
    ArrayList<EducationNewItem> firstDataSet;
    boolean mainedcalled = false;
    TextView footer;
    ArrayList<ArrayList<String>> myList;
    int count;
    private int spinCounter = 0, spinCounter1 = 0;
    String pname, paddress, powner, pdescription;

    private SlidingUpPanelLayout mLayout;
    ArrayList<EducationNewItem> secondDataSet;
    ArrayList<HealthServiceProviderItemNew> firstDataSetHealth;
    ArrayList<HealthServiceProviderItemNew> secondDataSetHealth;

    public void setShowList(int showList) {
        this.showList = showList;
    }

    ToggleButton toggleButton;
    int tution_detector = 0;
    Double screenSize;
    private static final String TAG = PlaceDetailsActivityNewLayout.class.getSimpleName();
    private static final int ANIM_INTERVAL = 150;
    private static double VIEW_WIDTH;
    private static boolean mapcalledstatus;
    private LinearLayout llCatListHolder, mapnother, listholder, explist, svholder, svsholder;
    CategoryItem ci;
    String user = "kolorobapp";
    String pass = "2Jm!4jFe3WgBZKEN";
    ArrayList<BazarItem> allBazar = new ArrayList<BazarItem>();
    LinearLayout bazar_post_layout;
    private LinearLayout llSubCatListHolder;
    private HashMap<String, Integer> sections = new HashMap<String, Integer>();
    private static FrameLayout map;
    ProgressDialog dialog;
    private KolorobSpinner spItems;
    ArrayAdapter arrayAdapter;
    ArrayList<HealthServiceProviderItemNew> healthServiceProvider;
    List<String> listData = new ArrayList<String>();
    private int height, dpi;
    Boolean panelStates = true;
    private View nextChild;
    int buttonHeights;
    private ExpandableListView subCatItemList;
    private boolean isCatExpandedOnce = false;
    private int primaryIconWidth;
    private int subCatShowFlag = 0;
    private int locationNameId, subcategory;
    private String locationName;
    private ListView expandableListview;
    private LinearLayout  wholeLayout;

    private String locationNameEng;
    private String comapreData;
    ScrollView sv, svs, scrolling_part;
    int pannl_height;
    String firstData = "", SecondData = "";
    int checker = 0;
    LinearLayout slider_part;
    Boolean InCompare = false;
    private Button prebutton;
    private HealthServiceProviderTableNew healthServiceProviderTableNew;
    private int sideIndexHeight;
    private LinearLayout compare_layout, shift1_1, shift1_11, canteen_facility_1, canteen_facility_11;
    private List<Object[]> alphabet = new ArrayList<Object[]>();
    Activity act;
    int width;
    CheckBox checkBox, checkBox2, checkLeft, checkRight;
    RelativeLayout compare_layoutedu;
    public int layoutstatus;
    private Boolean list_expand = false;
    private TextView listOrMapDisplayText;
    boolean educlicked, helclicked, entclicked, finclicked, govclicked, legclicked, jobclicked = false;
    private Toolbar toolbar;
    TextView health_name2, opening_time2, language_spoken2, service_type2, specialist_available2, clean_facilities2, privacy2, quality_equipment2;
    TextView health_name1, opening_time1, language_spoken1, service_type1, specialist_available1, clean_facilities1, privacy1, quality_equipment1, cost1, cost2, cost3;
    TextView health_name3, opening_time3, language_spoken3, service_type3, specialist_available3, clean_facilities3, privacy3, quality_equipment3;
    EditText product_name;
    EditText phone;
    int buttonh;
    EditText address;
    EditText price;
    List<String> listDataHeader;
    ArrayList<String> bazar_data;
    private ImageView refresh_button;
    HashMap<String, ArrayList<String>> listDataChild;
    ExpandableListView expListView;
    private int lastExpandedPosition = -1;
    BazarToolAdapter bazarToolAdapter;
    private int bazar_counter = 0;
    EditText description;
    EditText contact_person;
    EditText contact;
    private int showList;
    TextView edu_name_ban, edtype, hostel_facility, transport_facility, playground, total_students, total_classes, total_teachers, course_provided, shift, canteen_facility;
    TextView edu_name_ban1, edtype1, hostel_facility1, transport_facility1, playground1, total_students1, total_classes1, total_teachers1, course_provided1, shift1, canteen_facility1;
    TextView edu_name_ban22, edtype2, hostel_facility2, transport_facility2, playground2, total_students2, total_classes2, total_teachers2, course_provided2, shift2, canteen_facility2;

    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.


    ArrayList<EntertainmentServiceProviderItemNew> printnamesent;

    ArrayList<LegalAidServiceProviderItemNew> printnamesleg;
    ArrayList<HealthServiceProviderItemNew> printnameshea;
    ArrayList<FinancialNewItem> printnamesfin;
    ArrayList<String> allData = new ArrayList<>();
    private DrawerLayout drawer;
    ArrayList<SearchHolder> searchheads = new ArrayList<>();
    Context context;
    ArrayList<String> Headerholder = new ArrayList<>();
    ArrayList<EducationNewItem> printnames;
    ArrayList<GovernmentNewItem> printgovs;
    //common for all categories
    public LinearLayout sideIndex, searchLayout;

    public CategoryItem getCi() {
        return ci;
    }

    int[] flag2 = new int[15];

    public void setCi(CategoryItem ci) {
        this.ci = ci;
    }

    int negotiable_check = 0;
    private ArrayList<SubCategoryItem> currentSubCategoryItem;
    public static int currentCategoryID, currentCategoryIDconverted;
    private ViewGroup.LayoutParams kk;

    Vector<Group> groups = new Vector<Group>();
    TextView header;
    private String placeChoice;
    private int indexListSize;
    private ListActivity listView;
    private ImageButton expandableListShowing, more, MapButton, ListButton, SearchButton, CompareButton;
    private RelativeLayout mapholderr;
    ArrayList<CategoryItem> categoryList;
    ArrayList<CategoryItem> categoryList2 = new ArrayList<>();
    Boolean SearchClicked = false, MapClicked = true, ListClicked = false, CompareClicked = false;
    private Context con;
    private RelativeLayout bazar_tool;
    public RelativeLayout getRlSubCatHolder() {
        return rlSubCatHolder;
    }

    public void setRlSubCatHolder(RelativeLayout rlSubCatHolder) {
        this.rlSubCatHolder = rlSubCatHolder;
    }

    private ListView itemList;
    public RelativeLayout rlSubCatHolder;

    public String getPlaceChoice() {
        return placeChoice;
    }

    public void setPlaceChoice(String placeChoice) {
        this.placeChoice = placeChoice;
    }

    EditText Searchall, catsearch;
    boolean catsearchclicked = false;
    ListViewAdapterAllCategories adapter;
    EditText filterText;
    ListView allitemList;
    String filterword;
    TextView searchtext;
    private int smal;

    int snumber = 0;
    TextView submit_bazar;

    public String getLocationNameEng() {
        return locationNameEng;
    }

    public void setLocationNameEng(String locationNameEng) {
        this.locationNameEng = locationNameEng;
    }

    public int getSnumber() {
        return snumber;
    }

    public void setSnumber(int snumber) {
        this.snumber = snumber;
    }

    public String getFilterword() {
        return filterword;
    }

    TextView tvName;

    public void setFilterword(String filterword) {
        this.filterword = filterword;
    }

    boolean catstatus = false;
    int filcatid;
    CheckBox negotiable;
    RelativeLayout catholder;
    CheckBox check;
    Boolean NavigationCalled, NavigationCalledOnce;
    LinearLayout fholder, fleft, fright, mbholder, lbholder, sbholder, cbholder;
    RelativeLayout searchviewholder, filterholder;
    ArrayList<AllHolder> allHolders = new ArrayList<>();
    ArrayList<AllHolder> catHolders = new ArrayList<>();
    ArrayList<AllHolder> subcatHolders = new ArrayList<>();
    private ArrayList<FinancialNewItem> fetchedfin;
    private ArrayList<EducationNewItem> fetchededu;
    private ArrayList<LegalAidServiceProviderItemNew> fetchedleg;
    private ArrayList<EntertainmentServiceProviderItemNew> fetchedent;
    private ArrayList<HealthServiceProviderItemNew> fetchedhel;
    public ArrayList<GovernmentNewItem> fetchedgov;
    private ArrayList<Subcatholder> subholders = new ArrayList<>();
    RadioGroup catgroup, fgrp1, fgrp2;
    int va;
    ArrayList<String> filter = new ArrayList<>();
    ArrayList<String> filter2 = new ArrayList<>();

    public int getFilcatid() {
        return filcatid;
    }

    ArrayList<SubCategoryItem> subCategoryList;

    public void setFilcatid(int filcatid) {
        this.filcatid = filcatid;
    }

    boolean doubleBackToExitPressedOnce = false;
    int val;
    String checknum;
    Boolean flag;
    boolean filterclicked = false;
    ArrayList<EducationNewItem> eduItem = new ArrayList<>();
    ArrayList<GovernmentNewItem> govItem = new ArrayList<>();
    ArrayList<HealthServiceProviderItemNew> healthItem = new ArrayList<>();
    ArrayList<EntertainmentServiceProviderItemNew> entItem = new ArrayList<>();
    ArrayList<LegalAidServiceProviderItemNew> legalItem = new ArrayList<>();
    ArrayList<FinancialNewItem> financialItem = new ArrayList<>();
    String idx, idxx, idxxx, idxxxx;
    ArrayList<EducationNewItem> EDD = new ArrayList<>();
    ArrayList<HealthServiceProviderItemNew> HEL = new ArrayList<>();
    ArrayList<LegalAidServiceProviderItemNew> LEG = new ArrayList<>();
    ArrayList<EntertainmentServiceProviderItemNew> ENT = new ArrayList<>();
    ArrayList<FinancialNewItem> FIN = new ArrayList<>();
    ArrayList<GovernmentNewItem> GOV = new ArrayList<>();
    TextView uptext;
    boolean mapfirst = true;
    ArrayList<String> clicked = new ArrayList<>();
    EducationServiceProviderItem nulledu;
    EducationNewItem nulledu2;
    String nodefromback;
    int index;
    MapFragmentOSM mapFragment;

    public String getNodefromback() {
        return nodefromback;
    }

    public void setNodefromback(String nodefromback) {
        this.nodefromback = nodefromback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("prefs", 0);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("Value", false);
        editor.putInt("ValueD", 23);

        editor.apply();

        NavigationCalled = false;
        NavigationCalledOnce = false;
      //  bazar_tool = (RelativeLayout) findViewById(R.id.bazz);
        val = settings.getInt("KValue", 0);
        Log.e("ASinplaceDetails", String.valueOf(val));
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int checkheight = getApplicationContext().getResources().getConfiguration().screenHeightDp;
        int checkwidth = getApplicationContext().getResources().getConfiguration().screenWidthDp;
        dpi = displayMetrics.densityDpi;
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        setContentView(R.layout.activity_place_detailnew);
        fholder = (LinearLayout) findViewById(R.id.LinearLayoutfilter);
        con = this;
        MapButton = (ImageButton) findViewById(R.id.mapbutton);
        ListButton = (ImageButton) findViewById(R.id.listbutton);
        SearchButton = (ImageButton) findViewById(R.id.searchbutton);
        CompareButton = (ImageButton) findViewById(R.id.compare);
        searchviewholder = (RelativeLayout) findViewById(R.id.searchholder);
        footer = (TextView) findViewById(R.id.footer);
        shift1_1 = (LinearLayout) findViewById(R.id.shift1_1);
        shift1_11 = (LinearLayout) findViewById(R.id.shift1_11);
        canteen_facility_1 = (LinearLayout) findViewById(R.id.canteen_facility_1);
        canteen_facility_11 = (LinearLayout) findViewById(R.id.canteen_facility_11);
        int buttonWidth = width / 4;
        int buttonHeight = height / 20;
        allitemList = (ListView) findViewById(R.id.allitem);
        checkBox = (CheckBox) findViewById(R.id.compared);
        checkBox2 = (CheckBox) findViewById(R.id.compared2);
        negotiable = (CheckBox) findViewById(R.id.negotiable);
        checkLeft = (CheckBox) findViewById(R.id.checkLeft);
        checkRight = (CheckBox) findViewById(R.id.checkRight);
        bazar_tool = (RelativeLayout) findViewById(R.id.bazz);
        bazar_tool = (RelativeLayout) findViewById(R.id.bazar_too);
        wholeLayout= (LinearLayout)findViewById(R.id.wholeLayout);
        Log.d("Touch Bazar","=====================");
        // explist=(LinearLayout)findViewById(R.id.explist);
        catholder = (RelativeLayout) findViewById(R.id.categoryfilterholder);
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
        pannl_height = height / 17;
        if (pannl_height < 70)
            pannl_height = 70;
        submit_bazar = (TextView) findViewById(R.id.submit_bazar);
        compare_layout = (LinearLayout) findViewById(R.id.compare_layout);

        scrolling_part = (ScrollView) findViewById(R.id.scrolling_part);

        LinearLayout.LayoutParams scrolling_partc = (LinearLayout.LayoutParams) scrolling_part.getLayoutParams();
        scrolling_partc.setMargins(0, 0, 0, smal);
        refresh_button = (ImageView) findViewById(R.id.refresh_button);
        width = AppUtils.getScreenWidth(PlaceDetailsActivityNewLayout.this);
        refresh_button.getLayoutParams().height = width / 14;
        refresh_button.getLayoutParams().width = width / 14;
        screenSize = AppUtils.ScreenSize(PlaceDetailsActivityNewLayout.this);

        RelativeLayout.LayoutParams com_layout = (RelativeLayout.LayoutParams) compare_layout.getLayoutParams();
        com_layout.setMargins(0, 0, 0, smal);

        compare_layout.setLayoutParams(com_layout);

        MapButton.setLayoutParams(params);
        final LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) SearchButton.getLayoutParams();
        params2.weight = 1;
        params2.width = buttonWidth;
        params2.height = (int) Math.round(d);
        SearchButton.setLayoutParams(params2);
        final LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) ListButton.getLayoutParams();
        params3.weight = 1;
        params3.width = buttonWidth;
        params3.height = (int) Math.round(d);
        ListButton.setLayoutParams(params3);
        ListButton.getHeight();
        final LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) CompareButton.getLayoutParams();
        params4.weight = 1;
        params4.width = buttonWidth;
        params4.height = (int) Math.round(d);
        CompareButton.setLayoutParams(params4);
        // SearchButton.setMinimumWidth(buttonWidth);
        //ListButton.setLayoutParams(layoutParams);
        // SearchButton.setLayoutParams(layoutParams);
        // CompareButton.setLayoutParams(layoutParams);
        buttonHeights = (int) Math.round(d);
        mapcalledstatus = false;
        toolbar = (Toolbar) findViewById(R.id.categorytoolbar);
        uptext = (TextView) findViewById(R.id.textView15);
        healthServiceProvider = constructHealthListItem(1);

        SharedPreferencesHelper.setCompareData(PlaceDetailsActivityNewLayout.this, "", 0);
        Searchall = (EditText) findViewById(R.id.searchall);

        prebutton = (Button) findViewById(R.id.prebutton);
        filterholder = (RelativeLayout) findViewById(R.id.filterholder);

        header = (TextView) findViewById(R.id.textView15);
        // toolbar.setBackgroundResource(android.R.color.transparent);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        ab.setDisplayHomeAsUpEnabled(true);
        mapnother = (LinearLayout) findViewById(R.id.mapnothers);
        mapholderr = (RelativeLayout) findViewById(R.id.mapholder);
        listholder = (LinearLayout) findViewById(R.id.listholder);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //  getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /**
             * Called when a drawer has settled in a completely closed state.
             */
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // svSubCategoryListHolder=(HorizontalScrollView)findViewById(R.id.svSubCategoryListHolder);

        HorizontalScrollView svSubCategoryListHolder = new HorizontalScrollView(this);
        svholder = (LinearLayout) findViewById(R.id.llCategoryListHolderback);
        svsholder = (LinearLayout) findViewById(R.id.llSubCategoryListHolderback);
        svholder.setVisibility(View.VISIBLE);
        svsholder.setVisibility(View.GONE);
        sv = (ScrollView) findViewById(R.id.svCategoryListHolder);
        svs = (ScrollView) findViewById(R.id.svSubCategoryListHolder);
        sv.setVisibility(View.VISIBLE);

        svs.setVisibility(View.GONE);
        //subCatItemList = (ExpandableListView) findViewById(R.id.listView);
//        wholeLayout=(RelativeLayout)findViewById(R.id.wholeLayout);


        final Intent intent;
        intent = getIntent();
        if (null != intent) {
            locationNameId = intent.getIntExtra(AppConstants.KEY_PLACE, 0);
            if (locationNameId == AppConstants.PLACE_BAUNIABADH) {
                setPlaceChoice("Mirpur-11");
                locationName = AppConstants.BAUNIABADH;
                listData.add(AppConstants.BAUNIABADH);
                listData.add(AppConstants.PARIS_ROAD);
                setLocationNameEng("Mirpur-11");
            } else if (locationNameId == AppConstants.PLACE_PARIS_ROAD) {
                setPlaceChoice("Mirpur-10");
                locationName = AppConstants.PARIS_ROAD;
                listData.add(AppConstants.PARIS_ROAD);
                listData.add(AppConstants.BAUNIABADH);
                setLocationNameEng("Mirpur-10");
            }
        }


        health_name2 = (TextView) findViewById(R.id.health_name2);
        opening_time2 = (TextView) findViewById(R.id.opening_time2);
        language_spoken2 = (TextView) findViewById(R.id.language_spoken2);
        service_type2 = (TextView) findViewById(R.id.service_type2);
        specialist_available2 = (TextView) findViewById(R.id.specialist_available2);
        clean_facilities2 = (TextView) findViewById(R.id.clean_facilities2);
        privacy2 = (TextView) findViewById(R.id.privacy2);
        quality_equipment2 = (TextView) findViewById(R.id.quality_equipment2);
        cost2 = (TextView) findViewById(R.id.cost2);
        compare_layout = (LinearLayout) findViewById(R.id.compare_layout);
        compare_layoutedu = (RelativeLayout) findViewById(R.id.compare_layoutedu);
        health_name3 = (TextView) findViewById(R.id.health_name3);
        opening_time3 = (TextView) findViewById(R.id.opening_time3);
        language_spoken3 = (TextView) findViewById(R.id.language_spoken3);
        service_type3 = (TextView) findViewById(R.id.service_type3);
        specialist_available3 = (TextView) findViewById(R.id.specialist_available3);
        clean_facilities3 = (TextView) findViewById(R.id.clean_facilities3);
        privacy3 = (TextView) findViewById(R.id.privacy3);
        quality_equipment3 = (TextView) findViewById(R.id.quality_equipment3);
        cost3 = (TextView) findViewById(R.id.cost3);
        opening_time1 = (TextView) findViewById(R.id.opening_time1);
        language_spoken1 = (TextView) findViewById(R.id.language_spoken1);
        service_type1 = (TextView) findViewById(R.id.service_type1);
        specialist_available1 = (TextView) findViewById(R.id.specialist_available1);
        clean_facilities1 = (TextView) findViewById(R.id.clean_facilities1);
        privacy1 = (TextView) findViewById(R.id.privacy1);
        quality_equipment1 = (TextView) findViewById(R.id.quality_equipment1);
        cost1 = (TextView) findViewById(R.id.cost1);


        edu_name_ban = (TextView) findViewById(R.id.edu_name_ban3);
        edu_name_ban22 = (TextView) findViewById(R.id.edu_name_ban22);
        edtype = (TextView) findViewById(R.id.eduType2);
        hostel_facility = (TextView) findViewById(R.id.hostel_facility2);
        transport_facility = (TextView) findViewById(R.id.transport_facility2);
        playground = (TextView) findViewById(R.id.playground2);
        total_students = (TextView) findViewById(R.id.ttl_students);
        total_classes = (TextView) findViewById(R.id.total_classes2);
        total_teachers = (TextView) findViewById(R.id.total_teachers2);
        course_provided = (TextView) findViewById(R.id.course_provided2);
        shift = (TextView) findViewById(R.id.shift2);
        canteen_facility = (TextView) findViewById(R.id.canteen_facility2);
        compare_layout = (LinearLayout) findViewById(R.id.compare_layout);
        compare_layoutedu = (RelativeLayout) findViewById(R.id.compare_layoutedu);
        edu_name_ban1 = (TextView) findViewById(R.id.edu_name_ban3);
        edtype1 = (TextView) findViewById(R.id.eduType3);
        hostel_facility1 = (TextView) findViewById(R.id.hostel_facility3);
        transport_facility1 = (TextView) findViewById(R.id.transport_facility3);
        playground1 = (TextView) findViewById(R.id.playground3);
        total_students1 = (TextView) findViewById(R.id.total_students3);
        total_classes1 = (TextView) findViewById(R.id.total_classes3);
        total_teachers1 = (TextView) findViewById(R.id.total_teachers3);
        course_provided1 = (TextView) findViewById(R.id.course_provided3);
        shift1 = (TextView) findViewById(R.id.shift3);
        canteen_facility1 = (TextView) findViewById(R.id.canteen_facility3);

        //    edu_name_ban2=(TextView)findViewById(R.id.edu_name_ban1);
        edtype2 = (TextView) findViewById(R.id.eduType1);
        hostel_facility2 = (TextView) findViewById(R.id.hostel_facility1);
        transport_facility2 = (TextView) findViewById(R.id.transport_facility1);
        playground2 = (TextView) findViewById(R.id.playground1);
        total_students2 = (TextView) findViewById(R.id.total_students1);
        total_classes2 = (TextView) findViewById(R.id.total_classes1);
        total_teachers2 = (TextView) findViewById(R.id.total_teachers1);
        course_provided2 = (TextView) findViewById(R.id.course_provided1);
        shift2 = (TextView) findViewById(R.id.shift1);
        canteen_facility2 = (TextView) findViewById(R.id.canteen_facility1);
        //categoryHeader = (TextView) findViewById(R.id.tv_cat_name);
        //categoryHeaderIcon = (ImageView) findViewById(R.id.ivHeadCatIconSubCatList);
        //placeDetailsLayout = (FrameLayout) findViewById(R.id.place_details_layout);
        ///this code will change the background of the layout for two places.


        // itemList = (ListView)findViewById(R.id.listViewSearch);
        //subCatItemListHeader = (TextView) findViewById(R.id.tv_sub_cat_item_list_head);

        //subCatItemList = (ExpandableListView) findViewById(R.id.listView);
        map = (FrameLayout) findViewById(R.id.map_fragment);
        map.setVisibility(View.VISIBLE);

        //showsearch=(RelativeLayout)findViewById(R.id.show);
        // insSubCat = (TextView) findViewById(R.id.tvInstructionSubCat);
        //seeMap = (Button) findViewById(R.id.btn_see_map);
        // showSubCatListItem = (Button) findViewById(R.id.btn_show_sub_cat_list_item);
        VIEW_WIDTH = AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC;
        isCatExpandedOnce = false;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.97); // 80% of the view width
        AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "This information is for display purposes only ", "");
        fleft = (LinearLayout) findViewById(R.id.linearLayout1);
        fright = (LinearLayout) findViewById(R.id.linearLayout2);

        //  svCatList = (ScrollView) findViewById(R.id.svCategoryListHolder);
        llCatListHolder = (LinearLayout) findViewById(R.id.llCategoryListHolder);
        llSubCatListHolder = (LinearLayout) findViewById(R.id.llSubCatListHolder);
        llCatListHolder.setVisibility(View.VISIBLE);
        //rlSubCatHolder.setVisibility(View.VISIBLE);
        llSubCatListHolder.setVisibility(View.GONE);
        ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();
        ViewGroup.LayoutParams lp_sub = llSubCatListHolder.getLayoutParams();
        final int s = lp.width = (int) (VIEW_WIDTH);
        lp_sub.width = s;
        FrameLayout.LayoutParams caTsList = (FrameLayout.LayoutParams) llCatListHolder.getLayoutParams();


        //final ViewGroup.LayoutParams exlist= explist.getLayoutParams();
        // final RelativeLayout.LayoutParams expnlist = (RelativeLayout.LayoutParams) explist.getLayoutParams();

        // expnlist.setMargins((s*9)/10,40,5,40);

        lp.height = 100;

        if (height < 1000)
            caTsList.setMargins(0, 60, 0, 0);
        else
            caTsList.setMargins(0, 10, 0, 0);


        /**
         * constructing category list
         **/
        CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivityNewLayout.this);
        categoryList = categoryTable.getAllCategories();
        categoryList2 = categoryList;
        for (int i = 0; i < categoryList2.size(); i++) {

            int cid = categoryList2.get(i).getId();

            if (cid == 1) {
                categoryList2.get(i).setId(2);
            } else if (cid == 53) {
                categoryList2.get(i).setId(7);
            } else if (cid == 5) {
                categoryList2.get(i).setId(1);
            } else if (cid == 14) {
                categoryList2.get(i).setId(3);
            } else if (cid == 29) {
                categoryList2.get(i).setId(5);
            } else if (cid == 11) {
                categoryList2.get(i).setId(6);
            } else if (cid == 33) {
                categoryList2.get(i).setId(4);
            }


        }
        categoryList2.size();
        constructCategoryList(categoryList2);
        //rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        //rlSubCatHolder.setVisibility(View.INVISIBLE);


        spItems = (KolorobSpinner) findViewById(R.id.areaitems);
        spItems.setVisibility(View.VISIBLE);
        arrayAdapter = new ArrayAdapter(PlaceDetailsActivityNewLayout.this, R.layout.area_row_spinner, listData);
        arrayAdapter.setDropDownViewResource(R.layout.area_row_spinners_dropdown);
        spItems.setAdapter(arrayAdapter);


        spItems.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String imc_met = spItems.getSelectedItem().toString();
                setPlaceChoice(imc_met);


                if (imc_met == AppConstants.BAUNIABADH) {
                    locationNameId = AppConstants.PLACE_BAUNIABADH;
                    setPlaceChoice("Mirpur-11");
                    setLocationNameEng("Mirpur-11");

                    allHolders.clear();
                    if (educlicked == true || helclicked == true || entclicked == true || legclicked == true || finclicked == true || govclicked == true) {
                        Populateholder("Mirpur-11");
                        calladapter(true);
                    } else Populateholder("Mirpur-11");
                    callMapFragment(locationNameId);
                    //   createData(currentCategoryID, "", "Mirpur-11");
                    //    ServiceListDisplayAdapter adapter = new ServiceListDisplayAdapter(PlaceDetailsActivityNewLayout.this, groups, currentCategoryID);
                    // subCatItemList.setAdapter(adapter);
                } else {
                    locationNameId = AppConstants.PLACE_PARIS_ROAD;
                    setPlaceChoice("Mirpur-10");
                    setLocationNameEng("Mirpur-10");

                    allHolders.clear();
                    if (educlicked == true || helclicked == true || entclicked == true || legclicked == true || finclicked == true || govclicked == true) {
                        Populateholder("Mirpur-10");
                        calladapter(true);
                    } else Populateholder("Mirpur-10");
                    callMapFragment(locationNameId);
                    //   createData(currentCategoryID, "", "Mirpur-10");
                    //  ServiceListDisplayAdapter adapter = new ServiceListDisplayAdapter(PlaceDetailsActivityNewLayout.this, groups, currentCategoryID);
                    // subCatItemList.setAdapter(adapter);
                }

                if (mapcalledstatus) {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        Populateholder(getPlaceChoice());
        callMapFragment(locationNameId);
        MapButton.setBackgroundResource(R.drawable.map_selected);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchClicked = true;
                MapClicked = false;
                ListClicked = false;
                CompareClicked = false;
                InCompare = false;


                populateSearch();
                if (CompareClicked == false || MapClicked == false || ListClicked == false) {
                    SearchButton.setImageResource(0);
                    MapButton.setImageResource(0);
                    CompareButton.setImageResource(0);
                    ListButton.setImageResource(0);
                    params2.height = larg;
                    SearchButton.setLayoutParams(params2);
                    params.height = smal;
                    MapButton.setLayoutParams(params);
                    params3.height = smal;
                    ListButton.setLayoutParams(params3);
                    params4.height = smal;
                    CompareButton.setLayoutParams(params4);
                    SearchButton.setBackgroundResource(R.drawable.search_selected);
                    ListButton.setBackgroundResource(R.drawable.bazaar);
                    MapButton.setBackgroundResource(R.drawable.map);
                    CompareButton.setBackgroundResource(R.drawable.compare);
                    map.setVisibility(View.GONE);
                    svs.setVisibility(View.GONE);
                    svholder.setVisibility(View.GONE);
                    svsholder.setVisibility(View.GONE);
                    sv.setVisibility(View.GONE);
                    bazar_tool.setVisibility(View.GONE);
                    toggleButton.setVisibility(View.VISIBLE);
                    compare_layout.setVisibility(View.GONE);
                    compare_layoutedu.setVisibility(View.GONE);
                    searchviewholder.setVisibility(View.VISIBLE);
                }
                if (educlicked == true || helclicked == true || entclicked == true || legclicked == true || finclicked == true || govclicked == true) {
                    map.setVisibility(View.GONE);
                    filterholder.setVisibility(View.VISIBLE);
                    toggleButton.setVisibility(View.VISIBLE);
                } else {
                    filterholder.setVisibility(View.GONE);
                }
                svholder.setVisibility(View.VISIBLE);
                sv.setVisibility(View.VISIBLE);
                llCatListHolder.setVisibility(View.VISIBLE);
                init(PlaceDetailsActivityNewLayout.this);


                loadBazar(PlaceDetailsActivityNewLayout.this);
                panelListener(PlaceDetailsActivityNewLayout.this);
                wholeLayout.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.kolorob_color));


                toolbar.setVisibility(View.GONE);
                toggleButton.setVisibility(View.GONE);
            }
        });


        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((AppUtils.isNetConnected(getApplicationContext())) && (ContextCompat.checkSelfPermission(PlaceDetailsActivityNewLayout.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
                    dialog = new ProgressDialog(PlaceDetailsActivityNewLayout.this);
                    dialog.setMessage("দয়া করে অপেক্ষা করুন");
                    dialog.setCancelable(false);
                    dialog.show();
                    loadBazar(PlaceDetailsActivityNewLayout.this);
                } else {
                    AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই।", "অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");

                }


            }
        });
        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchClicked = false;
                MapClicked = true;
                ListClicked = false;
                CompareClicked = false;
                if (toggleButton.isChecked() && educlicked == true || helclicked == true || entclicked == true || legclicked == true || finclicked == true || govclicked == true) {
                    svsholder.setVisibility(View.VISIBLE);
                    svs.setVisibility(View.VISIBLE);
                    llSubCatListHolder.setVisibility(View.VISIBLE);
                }
                spItems.setVisibility(View.VISIBLE);
                uptext.setVisibility(View.VISIBLE);


                if (educlicked == true || helclicked == true || entclicked == true || legclicked == true || finclicked == true || govclicked == true) {

                    svsholder.setVisibility(View.VISIBLE);

                    svs.setVisibility(View.VISIBLE);
                    llSubCatListHolder.setVisibility(View.VISIBLE);
                }
                if (CompareClicked == false || SearchClicked == false || ListClicked == false) {
                    SearchButton.setImageResource(0);
                    MapButton.setImageResource(0);
                    CompareButton.setImageResource(0);
                    ListButton.setImageResource(0);
                    params.height = larg;
                    MapButton.setLayoutParams(params);

                    params2.height = smal;
                    SearchButton.setLayoutParams(params2);
                    params3.height = smal;
                    ListButton.setLayoutParams(params3);
                    params4.height = smal;
                    CompareButton.setLayoutParams(params4);
                    SearchButton.setBackgroundResource(R.drawable.search);
                    ListButton.setBackgroundResource(R.drawable.bazaar);
                    MapButton.setBackgroundResource(R.drawable.map_selected);
                    CompareButton.setBackgroundResource(R.drawable.compare);
                    //subCatItemList.setVisibility(View.GONE);

                    bazar_tool.setVisibility(View.GONE);
                    searchviewholder.setVisibility(View.GONE);
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

                if ((AppUtils.isNetConnected(getApplicationContext())) && (ContextCompat.checkSelfPermission(PlaceDetailsActivityNewLayout.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {
                    dialog = new ProgressDialog(PlaceDetailsActivityNewLayout.this);
                    dialog.setMessage("দয়া করে অপেক্ষা করুন");
                    dialog.setCancelable(true);
                    dialog.show();

                    spItems.setVisibility(View.VISIBLE);
                    uptext.setVisibility(View.VISIBLE);
                    SearchClicked = false;
                    MapClicked = false;
                    InCompare = false;
                    ListClicked = true;
                    CompareClicked = false;
                    searchviewholder.setVisibility(View.GONE);
                    llCatListHolder.setVisibility(View.VISIBLE);
                    if (MapClicked == false || SearchClicked == false || CompareClicked == false) {
                        SearchButton.setImageResource(0);
                        MapButton.setImageResource(0);
                        CompareButton.setImageResource(0);
                        ListButton.setImageResource(0);
                        params3.height = larg;
                        ListButton.setLayoutParams(params3);

                        params2.height = smal;
                        SearchButton.setLayoutParams(params2);
                        params.height = smal;
                        MapButton.setLayoutParams(params);
                        params4.height = smal;
                        CompareButton.setLayoutParams(params4);
                        SearchButton.setBackgroundResource(R.drawable.search);
                        ListButton.setBackgroundResource(R.drawable.bazaar_selected);
                        MapButton.setBackgroundResource(R.drawable.map);
                        CompareButton.setBackgroundResource(R.drawable.compare);
                        map.setVisibility(View.GONE);
                    }
                    // subCatItemList.setVisibility(View.VISIBLE);
                    bazar_tool.setVisibility(View.VISIBLE);
                    searchviewholder.setVisibility(View.GONE);
                    compare_layout.setVisibility(View.GONE);
                    compare_layoutedu.setVisibility(View.GONE);


                    svs.setVisibility(View.VISIBLE);
                    svholder.setVisibility(View.VISIBLE);
                    svsholder.setVisibility(View.GONE);
                    sv.setVisibility(View.VISIBLE);
                    llSubCatListHolder.setVisibility(View.GONE);
                    //   subCatItemList.setVisibility(View.VISIBLE);
//                    bazar_tool.setVisibility(View.VISIBLE);
                    //  wholeLayout.setBackgroundDrawable( getResources().getDrawable(R.drawable.splash) );

                    setShowList(1);
                    toolbar.setVisibility(View.VISIBLE);
                    toggleButton.setVisibility(View.VISIBLE);
                    list_expand = true;
                    //listOrMapDisplayText.setText("ম্যাপ দেখতে চাইলে এখানে চাপ দিন");

                    if (currentCategoryID < 1)
                        categoryListBuildUp(1);
                    else
                        categoryListBuildUp(currentCategoryID);

//                else
//                {
//                    llSubCatListHolder.setVisibility(View.VISIBLE);
//                    setShowList(0);
//                    map.setVisibility(View.VISIBLE);
//                    list_expand=false;
//                    subCatItemList.setVisibility(View.GONE);
//                   // listOrMapDisplayText.setText("লিস্ট দেখতে চাইলে এখানে চাপ দিন");
//                    //constructCategoryList(categoryList);
//
//                }
                } else {
                    AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "আপনার ফোনে ইন্টারনেট সংযোগ নেই।", "অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");

                }

            }

        });
        CompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentCategoryID == 1 || currentCategoryID == 2) {
                    SearchClicked = false;
                    MapClicked = false;
                    ListClicked = false;
                    CompareClicked = true;
                    InCompare = true;

                    if (currentCategoryID == 1 && SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this) == 0) {
                        AlertMessage.showMessage(con, "Comparison is not possible",
                                "You have not yet selected any services to compare");
                    } else if (currentCategoryID == 2 && SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this) == 0) {
                        AlertMessage.showMessage(con, "Comparison is not possible",
                                "You have not yet selected any services to compare");
                    } else if (currentCategoryID == 1 && SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this) == 1) {
                        AlertMessage.showMessage(con, "Comparison is not possible",
                                "You have chosen one service only. Please choose another one to proceed");
                    } else if (currentCategoryID == 2 && SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this) == 1) {
                        AlertMessage.showMessage(con, "Comparison is not possible",
                                "You have chosen one service only. Please choose another one to proceed");
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
                            SearchButton.setImageResource(0);
                            MapButton.setImageResource(0);
                            CompareButton.setImageResource(0);
                            ListButton.setImageResource(0);


                        }
                        toolbar.setVisibility(View.GONE);
                        // compare_layout.setVisibility(View.VISIBLE);

                        // @@@@arafat
                        // need to add condition for health and add color code for health,
                        // else educaton color code is okay
                        toggleButton.setVisibility(View.GONE);
                        SearchButton.setBackgroundResource(R.drawable.search);
                        ListButton.setBackgroundResource(R.drawable.bazaar);
                        MapButton.setBackgroundResource(R.drawable.map);
                        CompareButton.setBackgroundResource(R.drawable.compare_selected);
                        map.setVisibility(View.GONE);
                        llCatListHolder.setVisibility(View.GONE);
                        //   subCatItemList.setVisibility(View.GONE);
                        bazar_tool.setVisibility(View.GONE);
                        searchviewholder.setVisibility(View.GONE);
                        svs.setVisibility(View.GONE);
                        sv.setVisibility(View.GONE);
                        svholder.setVisibility(View.GONE);
                        svsholder.setVisibility(View.GONE);
                        llSubCatListHolder.setVisibility(View.GONE);
                        compareTool();
                    }
                } else
                    AlertMessage.showMessage(con, "Comparison is not possible",
                            "You have not yet selected any services to compare");


            }
        });

        toggleButton = (ToggleButton) findViewById(R.id.toggle);
        toggleButton.setVisibility(View.VISIBLE);
        toggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (toggleButton.isChecked()) {

                    sv.setVisibility(View.GONE);
                    svs.setVisibility(View.GONE);
                    svholder.setVisibility(View.GONE);
                    svsholder.setVisibility(View.GONE);
                    llCatListHolder.setVisibility(View.GONE);
                    llSubCatListHolder.setVisibility(View.GONE);
                } else {
                    if (ListClicked.equals(true) || SearchClicked.equals(true)) {

                        svsholder.setVisibility(View.GONE);
                        svs.setVisibility(View.GONE);
                    }
                    sv.setVisibility(View.VISIBLE);
                    svholder.setVisibility(View.VISIBLE);
                    llCatListHolder.setVisibility(View.VISIBLE);
                    if (educlicked == true || helclicked == true || entclicked == true || legclicked == true || finclicked == true) {
                        if (!ListClicked.equals(true) && !SearchClicked.equals(true)) {
                            svsholder.setVisibility(View.VISIBLE);
                            svs.setVisibility(View.VISIBLE);
                            llSubCatListHolder.setVisibility(View.VISIBLE);
                        }
                    }
                }

                //Button is OFF
                // Do Something
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void compareTool() {
        // compare_layout.setBackgroundColor(Color.parseColor("#F7931E"));

        if (currentCategoryID == 1) { //compare_layout.setBackgroundColor(Color.parseColor("#2F7281"));
            comapreData = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);

            String delims = "[,]";
            String[] tokens = comapreData.split(delims);

            firstData = tokens[0];
            SecondData = tokens[1];
            compare_layout.setVisibility(View.GONE);
            compare_layoutedu.setVisibility(View.VISIBLE);
            compare_layoutedu.setBackgroundColor(Color.parseColor("#2F7281"));
            compareEducation();
        } else {
            compare_layout.setVisibility(View.VISIBLE);

            comapreData = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
            int size = comapreData.length();
            String DataSet[] = comapreData.split(",");
            firstData = DataSet[0];
            SecondData = DataSet[1];

            compareHealth();
        }
    }


    public void compareHealth() {
        compare_layout.setVisibility(View.VISIBLE);
        compare_layoutedu.setVisibility(View.GONE);
        checkBox.setChecked(true);
        checkBox2.setChecked(true);

        healthServiceProviderTableNew = new HealthServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        firstDataSetHealth = healthServiceProviderTableNew.getHealthData(firstData);
        secondDataSetHealth = healthServiceProviderTableNew.getHealthData(SecondData);


        opening_time1.setText("Opening Time");
        language_spoken1.setText("Language");
        service_type1.setText("Service Type");
        specialist_available1.setText("Specialist Type");
        clean_facilities1.setText("Pharmacy Service");
        privacy1.setText("Privacy");
        quality_equipment1.setText("Service Details and Equipments");
        cost1.setText("Service Cost");
        shift1_11.setVisibility(View.GONE);
        shift1_1.setVisibility(View.GONE);
        canteen_facility_1.setVisibility(View.GONE);
        canteen_facility_11.setVisibility(View.GONE);
        HealthSpecialistTableDetails healthSpecialistTable = new HealthSpecialistTableDetails(PlaceDetailsActivityNewLayout.this);
        ArrayList<HealthSpecialistItemDetails> healthSpecialistItemDetailses;
        ArrayList<HealthSpecialistItemDetails> healthSpecialistItemDetailses2;
        healthSpecialistItemDetailses = healthSpecialistTable.getHealthSpecialistData(firstData);
        healthSpecialistItemDetailses2 = healthSpecialistTable.getHealthSpecialistData(SecondData);

        String firstSpecialistItem = "";
        String secondSpecialistItem = "";

        if (!healthSpecialistItemDetailses.equals("")) {
            for (HealthSpecialistItemDetails healthSpecialistItemDetails : healthSpecialistItemDetailses) {
                firstSpecialistItem = firstSpecialistItem + healthSpecialistItemDetails.getSpecialisttype() + ", ";
            }
        }

        if (!healthSpecialistItemDetailses2.equals("")) {
            for (HealthSpecialistItemDetails healthSpecialistItemDetails : healthSpecialistItemDetailses2) {
                secondSpecialistItem = secondSpecialistItem + healthSpecialistItemDetails.getSpecialisttype() + ", ";
            }
        }

        String healthService1 = "";
        String health_service_data1 = "";

        for (final HealthServiceProviderItemNew healthServiceProviderItemNew : firstDataSetHealth) {

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue = SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this);


                    if (compareValue == 2) {
                        if (!isChecked) {

                            String compare_Datas = "";

                            compare_Datas = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            String multipule[] = compare_Datas.split(",");
                            compare_Datas = multipule[0];

                            idx = multipule[1];
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datas, 1);
                        }


                    } else if (compareValue == 1) {
                        if (!isChecked) {

                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, "", 0);
                        } else {

                            String compare_Datac = "";
                            compare_Datac = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);

                            compare_Datac = compare_Datac + "," + idx;

                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datac, 2);

                        }
                    } else if (compareValue == 0) {
                        if (isChecked)
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, healthServiceProviderItemNew.getId(), 1);

                    }


                }
            });


            healthService1 = healthServiceProviderItemNew.getFamily_privacy();
            if (!healthService1.equals("")) {
                for (int i = 0; i < healthService1.length(); i++) {
                    if (healthService1.charAt(i) == '1') {
                        health_service_data1 = health_service_data1 + "Emergency Service,";
                    } else if (healthService1.charAt(i) == '2') {
                        health_service_data1 = health_service_data1 + " Ambulance Service,";
                    } else
                        health_service_data1 = health_service_data1 + " Maternity Service";

                }
            }
            if (!healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("null") && !healthServiceProviderItemNew.getNode_bn().equals(""))
                health_name3.setText(healthServiceProviderItemNew.getNode_bn());
            else
                health_name3.setText("Data is being collected");

            String time2 = "";
            time2 = healthServiceProviderItemNew.getOpening_time();
            if (!time2.equals("") && !time2.equals("null"))
                opening_time3.setText(time2);
            else
                opening_time3.setText("Data is being collected");

            if (!healthServiceProviderItemNew.getSpoken_lang().equals("") && !healthServiceProviderItemNew.getSpoken_lang().equalsIgnoreCase("null"))
                language_spoken3.setText(healthServiceProviderItemNew.getSpoken_lang());
            else
                language_spoken3.setText("Data is being collected");

            if (!health_service_data1.equals("") && !health_service_data1.equals("null"))
                service_type3.setText(health_service_data1);
            else
                service_type3.setText("Data is being collected");
            if (!firstSpecialistItem.equals("") && !firstSpecialistItem.equals("null"))
                specialist_available3.setText(firstSpecialistItem);
            else
                specialist_available3.setText("No Specialist is available");

            if (!healthServiceProviderItemNew.getPharmacy_speciality().equals("") && !healthServiceProviderItemNew.getPharmacy_speciality().equalsIgnoreCase("null"))
                clean_facilities3.setText(healthServiceProviderItemNew.getPharmacy_speciality());
            else
                clean_facilities3.setText("Data is being collected");

            if (!healthServiceProviderItemNew.getPharmacy_privacy().equals("") && !healthServiceProviderItemNew.getPharmacy_privacy().equals("null"))
                privacy3.setText(String.valueOf(healthServiceProviderItemNew.getPharmacy_privacy()));
            else
                privacy3.setText("Data is being collected");

            if (!healthServiceProviderItemNew.getQuality_equipments().equals("") && !healthServiceProviderItemNew.getQuality_equipments().equalsIgnoreCase("null"))
                quality_equipment3.setText(healthServiceProviderItemNew.getQuality_equipments());
            else
                quality_equipment3.setText("Data is being collected");

            if (!healthServiceProviderItemNew.getGeneral_cost().equals("") && !healthServiceProviderItemNew.getGeneral_cost().equalsIgnoreCase("null"))
                cost3.setText(healthServiceProviderItemNew.getGeneral_cost() + " BDT");
            else
                cost3.setText("Data is being collected");
        }


        String healthService2 = "";
        String health_service_data2 = "";
        for (final HealthServiceProviderItemNew healthServiceProviderItemNewx : secondDataSetHealth) {
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    int compareValue = SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this);


                    if (compareValue == 2) {
                        if (!isChecked) {

                            String compare_Datas = "";

                            compare_Datas = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            String multipule[] = compare_Datas.split(",");

                            compare_Datas = multipule[1];
                            idxx = multipule[0];

                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datas, 1);
                        }

                    } else if (compareValue == 1) {
                        if (!isChecked) {

                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, "", 0);
                        } else {

                            String compare_Data = "";
                            compare_Data = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);

                            // String multipule[]= compare_Data.split(",");
                            compare_Data = compare_Data + "," + idxx;
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Data, 2);

                        }
                    } else if (compareValue == 0) {
                        if (isChecked)
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, healthServiceProviderItemNewx.getId(), 1);

                    }


                }
            });


            healthService2 = healthServiceProviderItemNewx.getFamily_privacy();
            if (!healthService2.equals("")) {
                for (int i = 0; i < healthService2.length(); i++) {
                    if (healthService2.charAt(i) == '1') {
                        health_service_data2 = health_service_data1 + "Emergency Service, ";
                    } else if (healthService2.charAt(i) == '2') {
                        health_service_data2 = health_service_data2 + " Ambulance Service, ";
                    } else
                        health_service_data2 = health_service_data2 + " Maternity Service";

                }
            }
            if (!healthServiceProviderItemNewx.getNode_bn().equalsIgnoreCase("null") && !healthServiceProviderItemNewx.getNode_bn().equals(""))
                health_name2.setText(healthServiceProviderItemNewx.getNode_bn());
            else
                health_name2.setText("Data is being collected");

            String time1 = "";
            time1 = healthServiceProviderItemNewx.getOpening_time();
            if (!time1.equals("") && !time1.equals("null"))
                opening_time2.setText(time1);
            else
                opening_time2.setText("Data is being collected");

            if (!healthServiceProviderItemNewx.getSpoken_lang().equals("") && !healthServiceProviderItemNewx.getSpoken_lang().equalsIgnoreCase("null"))
                language_spoken2.setText(healthServiceProviderItemNewx.getSpoken_lang());
            else
                language_spoken2.setText("Data is being collected");

            if (!health_service_data2.equals("") && !health_service_data2.equals("null"))
                service_type2.setText(health_service_data1);
            else
                service_type2.setText("Data is being collected");
            if (!secondSpecialistItem.equals("") && !secondSpecialistItem.equals("null"))
                specialist_available2.setText(secondSpecialistItem);
            else
                specialist_available2.setText("No Specialist is available");

            if (!healthServiceProviderItemNewx.getPharmacy_speciality().equals("") && !healthServiceProviderItemNewx.getPharmacy_speciality().equals("null"))
                clean_facilities2.setText(healthServiceProviderItemNewx.getPharmacy_speciality());
            else
                clean_facilities2.setText("Data is being collected");

            if (!healthServiceProviderItemNewx.getPharmacy_privacy().equals("") && !healthServiceProviderItemNewx.getPharmacy_privacy().equals("null"))
                privacy2.setText(String.valueOf(healthServiceProviderItemNewx.getPharmacy_privacy()));
            else
                privacy2.setText("Data is being collected");

            if (!healthServiceProviderItemNewx.getQuality_equipments().equals("") && !healthServiceProviderItemNewx.getQuality_equipments().equalsIgnoreCase("null"))
                quality_equipment2.setText(healthServiceProviderItemNewx.getQuality_equipments());
            else
                quality_equipment2.setText("Data is being collected");

            if (!healthServiceProviderItemNewx.getGeneral_cost().equals("") && !healthServiceProviderItemNewx.getGeneral_cost().equalsIgnoreCase("null"))
                cost2.setText(healthServiceProviderItemNewx.getGeneral_cost() + " BDT");
            else
                cost2.setText("Data is being collected");


        }
        //  SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this,"",0);
    }


    public void compareEducation() {

        checkLeft.setChecked(true);
        checkRight.setChecked(true);


        educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        educationNewTable = new EducationNewTable(PlaceDetailsActivityNewLayout.this);

        firstDataSet = educationNewTable.getEducationData(String.valueOf(firstData));
        secondDataSet = educationNewTable.getEducationData(String.valueOf(SecondData));


        for (final EducationNewItem educationNewItem : firstDataSet) {

            checkLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    int compareValue = 0;
                    compareValue = SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this);
                    if (compareValue == 2) {
                        if (!isChecked) {
                            String compare_Data = "";
                            compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
                            String multipule[] = compare_Data.split(",");
                            compare_Data = multipule[1];
                            idxxx = multipule[0];
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 1);


                        }

                    } else if (compareValue == 1) {
                        if (!isChecked) {
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, "", 0);
                        } else {

                            String compare_Data = "";
                            compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);

                            compare_Data = compare_Data + "," + idxxx;
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 2);


                        }
                    } else if (compareValue == 0) {
                        if (isChecked)
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, String.valueOf(educationNewItem.getEduId()), 1);

                    }


                }
            });


            if (educationNewItem.getNamebn() == null || educationNewItem.getNamebn().equalsIgnoreCase("null") || educationNewItem.getNamebn().equals(""))
                edu_name_ban22.setText("Data is being collected ");
            else
                edu_name_ban22.setText(educationNewItem.getNamebn());

            if (educationNewItem.getEdtype() == null || educationNewItem.getEdtype().equalsIgnoreCase("null") || educationNewItem.getEdtype().equals(""))
                edtype.setText("Data is being collected ");
            else
                edtype.setText(educationNewItem.getEdtype());

            if (educationNewItem.getRating() == null || educationNewItem.getRating().equalsIgnoreCase("null") || educationNewItem.getRating().equals(""))
                hostel_facility.setText("Data is being collected "); //center type
            else
                hostel_facility.setText(educationNewItem.getRating());

            if (educationNewItem.getLandmark() == null || educationNewItem.getLandmark().equalsIgnoreCase("null") || educationNewItem.getLandmark().equals(""))
                transport_facility.setText("Data is being collected");
            else
                transport_facility.setText(educationNewItem.getLandmark());//done

            if (educationNewItem.getAveragestudent() == null || educationNewItem.getAveragestudent().equalsIgnoreCase("null") || educationNewItem.getAveragestudent().equals(""))
                playground.setText("Data is being collected");
            else
                playground.setText(educationNewItem.getAveragestudent() + " Person"); //done

            if (educationNewItem.getStudentno() == null || educationNewItem.getStudentno().equalsIgnoreCase("null") || educationNewItem.getStudentno().equals(""))
                total_students.setText("Data is being collected");
            else
                total_students.setText(String.valueOf(educationNewItem.getStudentno()) + " Person");

            if (educationNewItem.getClassno() == null || educationNewItem.getClassno().equalsIgnoreCase("null") || educationNewItem.getClassno().equals(""))
                total_classes.setText("Data is being collected");
            else
                total_classes.setText(String.valueOf(educationNewItem.getClassno()));

            if (educationNewItem.getTeachersno() == null || educationNewItem.getTeachersno().equalsIgnoreCase("null") || educationNewItem.getTeachersno().equals(""))
                total_teachers.setText("Data is being collected");
            else
                total_teachers.setText(String.valueOf(educationNewItem.getTeachersno()) + " Person");

            if (educationNewItem.getWatercondition() == null || educationNewItem.getWatercondition().equalsIgnoreCase("null") || educationNewItem.getWatercondition().equals(""))
                course_provided.setText("Data is being collected");
            else
                course_provided.setText(educationNewItem.getWatercondition());

            if (educationNewItem.getShift() == null || educationNewItem.getShift().equalsIgnoreCase("null") || educationNewItem.getShift().equals(""))
                shift.setText("Data is being collected");
            else
                shift.setText(educationNewItem.getShift());

            if (educationNewItem.getWatersource() == null || educationNewItem.getWatersource().equalsIgnoreCase("null") || educationNewItem.getWatersource().equals(""))
                canteen_facility.setText("Data is being collected");
            else {
                String w = AppUtils.Check_Capitalization(educationNewItem.getWatersource());
                canteen_facility.setText(w);
            }
        }
        for (final EducationNewItem educationNewItem : secondDataSet) {


            checkRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    int compareValue = 0;
                    compareValue = SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this);
                    if (compareValue == 2) {
                        if (!isChecked) {
                            String compare_Data = "";
                            compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
                            String multipule[] = compare_Data.split(",");
                            compare_Data = multipule[0];
                            idxxxx = multipule[1];
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 1);


                        }

                    } else if (compareValue == 1) {
                        if (!isChecked) {
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, "", 0);
                        } else {

                            String compare_Data = "";
                            compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);

                            compare_Data = compare_Data + "," + idxxxx;
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 2);


                        }
                    } else if (compareValue == 0) {
                        if (isChecked)
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, String.valueOf(educationNewItem.getEduId()), 1);

                    }


                }
            });


            if (educationNewItem.getNamebn() == null || educationNewItem.getNamebn().equalsIgnoreCase("null") || educationNewItem.getNamebn().equals(""))
                edu_name_ban.setText("Data is being collected");
            else
                edu_name_ban.setText(educationNewItem.getNamebn());

            if (educationNewItem.getEdtype() == null || educationNewItem.getEdtype().equalsIgnoreCase("null") || educationNewItem.getEdtype().equals(""))
                edtype1.setText("Data is being collected");
            else
                edtype1.setText(educationNewItem.getEdtype());

            if (educationNewItem.getRating() == null || educationNewItem.getRating().equalsIgnoreCase("null") || educationNewItem.getRating().equals(""))
                hostel_facility1.setText("Data is being collected");
            else
                hostel_facility1.setText(educationNewItem.getRating());

            if (educationNewItem.getLandmark() == null || educationNewItem.getLandmark().equalsIgnoreCase("null") || educationNewItem.getLandmark().equals(""))
                transport_facility1.setText("Data is being collected");
            else
                transport_facility1.setText(educationNewItem.getLandmark());

            if (educationNewItem.getAveragestudent() == null || educationNewItem.getAveragestudent().equalsIgnoreCase("null") || educationNewItem.getAveragestudent().equals(""))
                playground1.setText("Data is being collected");
            else
                playground1.setText(educationNewItem.getAveragestudent() + " Person");

            if (educationNewItem.getStudentno() == null || educationNewItem.getStudentno().equalsIgnoreCase("null") || educationNewItem.getStudentno().equals(""))
                total_students1.setText("Data is being collected");
            else
                total_students1.setText(String.valueOf(educationNewItem.getStudentno()) + " Person");

            if (educationNewItem.getClassno() == null || educationNewItem.getClassno().equalsIgnoreCase("null") || educationNewItem.getClassno().equals(""))
                total_classes1.setText("Data is being collected");
            else
                total_classes1.setText(String.valueOf(educationNewItem.getClassno()));

            if (educationNewItem.getTeachersno() == null || educationNewItem.getTeachersno().equalsIgnoreCase("null") || educationNewItem.getTeachersno().equals(""))
                total_teachers1.setText("Data is being collected");
            else
                total_teachers1.setText(String.valueOf(educationNewItem.getTeachersno()) + " Person");

            if (educationNewItem.getWatercondition() == null || educationNewItem.getWatercondition().equalsIgnoreCase("null") || educationNewItem.getWatercondition().equals(""))
                course_provided1.setText("Data is being collected");
            else
                course_provided1.setText(educationNewItem.getWatercondition());

            if (educationNewItem.getShift() == null || educationNewItem.getShift().equalsIgnoreCase("null") || educationNewItem.getShift().equals(""))
                shift1.setText("Data is being collected");
            else
                shift1.setText(educationNewItem.getShift());

            if (educationNewItem.getWatersource() == null || educationNewItem.getWatersource().equalsIgnoreCase("null") || educationNewItem.getWatersource().equals(""))
                canteen_facility1.setText("Data is being collected");
            else {
                String s = AppUtils.Check_Capitalization(educationNewItem.getWatersource());
                canteen_facility1.setText(s);
            }
        }

//        SharedPreferencesHelper.setCompareData(PlaceDetailsActivityNewLayout.this,"",0);
    }

    public void populateSearch() {

        ImageButton more = (ImageButton) findViewById(R.id.morebutton);
        catholder.setVisibility(View.VISIBLE);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterclicked = true;

                fholder.setVisibility(View.VISIBLE);
                populatefilterwords(getFilcatid());

            }
        });

    }


    public void populatefilterwords(int filcatid) {
        SubCategoryTableNew subCategoryTable = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        subholders.clear();
        subholders = subCategoryTable.getcatSubCategories(filcatid);

        int upto = subholders.size() / 2;
        filter.clear();
        filter2.clear();
        fleft.removeAllViews();
        fright.removeAllViews();
        for (int f = 0; f < subholders.size(); f++) {
            if (f >= upto)
                filter2.add(subholders.get(f).getSubcatname());
            else {
                filter.add(subholders.get(f).getSubcatname());
            }
        }
        final RadioButton[] rb = new RadioButton[30];
        fgrp1 = new RadioGroup(this); //create the RadioGroup
        fgrp1.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for (int i = 0; i < filter.size(); i++) {
            rb[i] = new RadioButton(this);
            fgrp1.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filter.get(i).toString());
            rb[i].setTextColor(Color.WHITE);
        }
        fgrp2 = new RadioGroup(this); //create the RadioGroup
        fgrp2.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for (int i = 0; i < filter2.size(); i++) {
            rb[i] = new RadioButton(this);
            fgrp2.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filter2.get(i).toString());
            rb[i].setTextColor(Color.WHITE);

        }
        fleft.addView(fgrp1);
        fright.addView(fgrp2);//you add the w

        fgrp1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        fgrp2.clearCheck();
        fgrp1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId != -1) {
                    fun2();
                }
            }
        });

        fgrp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

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

        } else if (id == R.id.emergency_info) {

            //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
            Intent em = new Intent(this, NewEmergency.class);
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

            int mapdetail = 1;

            Intent em = new Intent(this, ViewPagerDemo.class);
            em.putExtra("YourValueKey", mapdetail);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }
//        else if (id == R.id.info_change) {
//
//            Intent em = new Intent(this, Information_UpdateActivity.class);
//            startActivity(em);
//            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//
//        }else if (id == R.id.local_representative) {
//
//            // Toast.makeText(con,"It will be added in next version.",Toast.LENGTH_LONG).show();
//            AlertMessage.showMessage(con, "Representative", "It will be added in next version.");
//
//        } else if (id == R.id.adv_info) {
//            //  Toast.makeText(con,"It will be added in next version.",Toast.LENGTH_LONG).show();
//
//            AlertMessage.showMessage(con,"Advertisement","It will be added in next version.");
//        } else if (id == R.id.adv) {
//            AlertMessage.showMessage(con,"Ads Information","It will be added in next version.");
//        }

//        else if (id == R.id.nav_share) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


//    @Override
//    public boolean onNavigationItemSelected(MenuItem menuItem) {
//        return false;
//    }
//
//


    @Override
    public void onBackPressed() {
        if (CompareClicked) {

        } else {
            if (doubleBackToExitPressedOnce) {
//
                super.onBackPressed();

                //    finish();
                return;
            }

            ToastMessageDisplay.ShowToast(this, "Press once again to leave the screen ");


//        toolbar.setVisibility(View.VISIBLE);
//        toggleButton.setVisibility(View.VISIBLE);
            this.doubleBackToExitPressedOnce = true;


        }
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
            setCi(ci);
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


        //TextView tvName = (TextView) v.findViewById(R.id.tvNameCatList);


        // BE CAREFUL :: Category ID is being mapped as to the icon serial no.
        // in the AppConstants.ALL_CAT_ICONS array
        ivIcon.setImageResource(AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1]);
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        lpIv.width = (int) (primaryIconWidth * dwPercentage);
        ivIcon.setLayoutParams(lpIv);

        //   tvName.setText(ci.getCatName());
        //   tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));

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
                filterclicked = false;
                fholder.setVisibility(View.GONE);
                Headerholder.clear();
                currentCategoryID = ci.getId();

                for (int i = 0; i < llCatListHolder.getChildCount(); i++) {
                    ImageView iv = (ImageView) ((ViewGroup) llCatListHolder.getChildAt(i)).getChildAt(0);

                    // new background because something has changed
                    // check if it's not the imageView you just clicked because you don't want to change its background
                    iv.setImageResource(0);

                    iv.setImageResource(AppConstants.ALL_CAT_ICONS_NEW[i]);
                }
               /* llCatListHolder.getChildAt(0).setBackgroundResource(R.drawable.turned_off_porashona);
                llCatListHolder.getChildAt(1).setBackgroundDrawable(getResources().getDrawable(R.drawable.turned_off_chikitsha));
                llCatListHolder.getChildAt(2).setBackgroundDrawable(getResources().getDrawable(R.drawable.turned_off_anondo_furti));
                llCatListHolder.getChildAt(3).setBackgroundDrawable(getResources().getDrawable(R.drawable.turned_off_shorkari_shubidha));
                llCatListHolder.getChildAt(4).setBackgroundDrawable(getResources().getDrawable(R.drawable.turned_off_ain_kanun));
                llCatListHolder.getChildAt(5).setBackgroundDrawable(getResources().getDrawable(R.drawable.turned_off_taka_poisha));
                llCatListHolder.getChildAt(6).setBackgroundDrawable(getResources().getDrawable(R.drawable.turned_off_chakri_bakri));
*/


                //
                /*code for category*/
                /*following code will be different for each category*/
                /*category id 1 means education.
                * category id 2 means health
                * category id 3 means entertainment
                * category id 4 means government
                * category id 5 means legal
                * category id 6 means financial
                * category id 7 means job*/
                switch (currentCategoryID) {

                    case AppConstants.EDUCATION:
                        MediaPlayer mp_e = MediaPlayer.create(getApplicationContext(), R.raw.education);
                        mp_e.start();

                        EDD.clear();
                        educlicked = true;
                        setFilcatid(5);
                        catstatus = true;
                        calladapter(catstatus);
//                        if(ListClicked.equals(true))
//                            bazar_tool.setVisibility(View.VISIBLE);

                        llSubCatListHolder.setVisibility(View.GONE);


                        ArrayList<EducationNewItem> educationServiceProvider;
                        educationServiceProvider = constructEducationListItem();
                        mapcalledstatus = true;
                        callMapFragmentWithEducation(-1, educationServiceProvider, true);


                        ivIcon.setImageResource(R.drawable.education_selected);


                        if (SearchClicked) {
                            filterholder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.education_selected);
                        llSubCatListHolder.setVisibility(View.GONE);
                        if (educationServiceProvider.size() == 0) {

                            AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                        }
                        break;
                    case AppConstants.HEALTH:
                        MediaPlayer mp_h = MediaPlayer.create(getApplicationContext(), R.raw.health);
                        mp_h.start();
                        HEL.clear();
                        helclicked = true;
                        setFilcatid(1);
                        catstatus = true;
                        calladapter(catstatus);
                        if (SearchClicked) {
                            filterholder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.health_selected);


                        ArrayList<HealthServiceProviderItemNew> healthServiceProvider;
                        healthServiceProvider = constructHealthListItem(1);
                        mapcalledstatus = true;

                        callMapFragmentWithHealth(-1, healthServiceProvider, true);

                        if (healthServiceProvider.size() == 0) {

                            AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                        }


                        llSubCatListHolder.setVisibility(View.GONE);
//                            if(ListClicked.equals(true))
//                                bazar_tool.setVisibility(View.VISIBLE);


                        break;

                    //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:

                        ENT.clear();
                        entclicked = true;
                        setFilcatid(14);
                        catstatus = true;
                        calladapter(catstatus);
                        if (SearchClicked) {
                            filterholder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }


                        ivIcon.setImageResource(0);

                        ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProvider;
                        entertainmentServiceProvider = constructEntertainmentListItem(ci.getId());
                        mapcalledstatus = true;
                        callMapFragmentWithEntertainment(-1, entertainmentServiceProvider, true);

                        ivIcon.setImageResource(R.drawable.entertainment_selected);

//                        if(ListClicked.equals(true))
//                            explist.setVisibility(View.VISIBLE);


                        llSubCatListHolder.setVisibility(View.GONE);


                        MediaPlayer mp_en = MediaPlayer.create(getApplicationContext(), R.raw.entertainment);
                        mp_en.start();
                        if (entertainmentServiceProvider.size() == 0) {

                            AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                        }


                        break;


                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:
                        MediaPlayer mp_g = MediaPlayer.create(getApplicationContext(), R.raw.government);
                        mp_g.start();
                        govclicked = true;
                        GOV.clear();
                        setFilcatid(33);
                        catstatus = true;
                        calladapter(catstatus);
                        if (SearchClicked) {
                            filterholder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.government_selected);


                        mapcalledstatus = true;
                        ArrayList<GovernmentNewItem> governmentNewItems;
                        governmentNewItems = constructgovListItem();
                        callMapFragmentWithGovernment(-1, governmentNewItems, true);

                        llSubCatListHolder.setVisibility(View.GONE);
//                        if(ListClicked.equals(true))
//                            explist.setVisibility(View.VISIBLE);


                        toolbar.setVisibility(View.VISIBLE);
                        if (governmentNewItems.size() == 0) {

                            AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                        }
                        break;
                    case AppConstants.LEGAL:
                        MediaPlayer mp_l = MediaPlayer.create(getApplicationContext(), R.raw.legal);
                        mp_l.start();
                        LEG.clear();
                        legclicked = true;
                        setFilcatid(29);
                        catstatus = true;
                        calladapter(catstatus);
                        if (SearchClicked) {
                            filterholder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }


                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.legal_selected);


                        ArrayList<LegalAidServiceProviderItemNew> legalaidServiceProvider;
                        mapcalledstatus = true;
                        legalaidServiceProvider = constructlegalaidListItem(ci.getId());
                        callMapFragmentWithLegal(-1, legalaidServiceProvider, true);


//                        if(ListClicked.equals(true))
//                            explist.setVisibility(View.VISIBLE);


                        llSubCatListHolder.setVisibility(View.GONE);


                        if (legalaidServiceProvider.size() == 0) {

                            AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                        }


                        break;
                    case AppConstants.FINANCIAL:
                        MediaPlayer mp_f = MediaPlayer.create(getApplicationContext(), R.raw.finance);
                        mp_f.start();
                        FIN.clear();
                        finclicked = true;
                        setFilcatid(11);
                        catstatus = true;
                        calladapter(catstatus);
                        if (SearchClicked) {
                            filterholder.setVisibility(View.VISIBLE);
                            populatefilterwords(getFilcatid());
                        }
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.finance_selected);


                        ArrayList<FinancialNewItem> financialNewItems;
                        financialNewItems = constructfinancialListItem();
                        callMapFragmentWithFinancial(-1, financialNewItems, true);
                        mapcalledstatus = true;


//                        if(ListClicked.equals(true))
//                            explist.setVisibility(View.VISIBLE);


                        llSubCatListHolder.setVisibility(View.GONE);
                        if (financialNewItems.size() == 0) {

                            AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                        }


                        break;
                    case AppConstants.JOB:
                        MediaPlayer mp_j = MediaPlayer.create(getApplicationContext(), R.raw.job);
                        mp_j.start();
//                        explist.setVisibility(View.GONE);

                        Intent intentJ = new Intent(PlaceDetailsActivityNewLayout.this, DisplayAllJobsActivity.class);
                        startActivity(intentJ);

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.job_selected);
                        callMapFragmentWithFinancial(-1, null, true);//this is for removing other category marker from job
                        llSubCatListHolder.setVisibility(View.GONE);

                        break;


                    default:
                        break;
                }


                ArrayList<SubCategoryItem> subCatList = getSubCategoryList(ci.getId());


                if (SearchClicked == true) {
                    svsholder.setVisibility(View.GONE);
                    svs.setVisibility(View.GONE);
                } else if (showList != 1 && SearchClicked == false) {

                    if (isCatExpandedOnce)
                        showAnimatedSubcategories(subCatList, 0.5, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId()); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                    else
                        showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId());
                } else {
                    if (isCatExpandedOnce)
                        showAnimatedSubcategories(subCatList, 0.5, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId()); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                    else
                        showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId());

                    if (ListClicked)
                        categoryListBuildUp(currentCategoryID);
                }


                //AppConstants.CAT_LIST_LG_WIDTH_PERC);
            }
        });

        return v;
    }

    public void constructSubCategoryItemList(int cat_id, String header) {

        ArrayList<SubCategoryItem> subCategoryItems;
        subCategoryItems = constructSubCategoryListItem(cat_id, header);


        ArrayList<String> itemName = new ArrayList<String>();
        currentSubCategoryItem = subCategoryItems;
        for (SubCategoryItem si : subCategoryItems) {
            itemName.add(si.getSubCatName());
        }
        int i = 0;


    }

    private ArrayList<SubCategoryItem> constructSubCategoryListItem(int cat_id, String header) {
        ArrayList<SubCategoryItem> subCategoryItems;
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
        subCategoryItems = subCategoryTable.getAllSubCategoriesHeader(cat_id, header);


        return subCategoryItems;
    }


    private void constructSubCategoryList(ArrayList<SubCategoryItem> subCategoryList, double dwPercentage, int cat_id) {
        llSubCatListHolder.removeAllViews();
        ArrayList<String> header = new ArrayList<>();
        subcategory = 0;
        for (SubCategoryItem si : subCategoryList) {

            if (!header.contains(si.getSubcatHeader())) {
                header.add(si.getSubcatHeader());
                llSubCatListHolder.addView(getSubCategoryListItemView(si, dwPercentage, cat_id));
            }
        }
    }

    private void categoryListBuildUp(int currentCategoryID) {


//        createData(currentCategoryID,"",getLocationNameEng());
//        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
//        ServiceListDisplayAdapter adapter = new ServiceListDisplayAdapter(this, groups, currentCategoryID);
//        subCatItemList.setAdapter(adapter);

    }

    private View getSubCategoryListItemView(final SubCategoryItem si, double dwPercentage, final int cat_id) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        View v;
        LayoutInflater li = LayoutInflater.from(this);


        v = li.inflate(R.layout.subcatholderlist, llSubCatListHolder, false);

        final ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconSCatList);
        tvName = (TextView) v.findViewById(R.id.tv_sub_cat_name);
        ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONSBUTTON2[subcategory++]);


        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();

        lpIv.width = (int) (primaryIconWidth * dwPercentage);


        ivIcon.setLayoutParams(lpIv);
        tvName.setTextColor(Color.WHITE);

        tvName.setText(si.getSubcatHeader());
        tvName.setTextSize(12);
        flag = true;

        va = 0;
/**************************
 *
 *
 *
 *
 *
 *This OnClickListener will be called for clicking subcategory items from the right list
 *
 *
 *
 *
 * ************************/
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                index = llSubCatListHolder.indexOfChild(v);
                if (flag2[index] == 1) {
                    flag2[index] = 0; // 1 => Button ON

                    ivIcon.setImageResource(0);
                    ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONSBUTTON[index]);
                    switch (currentCategoryID) {


                        case AppConstants.EDUCATION:


                            callMapFragmentWithEducation(index, null, false);
                            break;
                        case AppConstants.HEALTH:

                            callMapFragmentWithHealth(index, null, false);
                            break;
                        case AppConstants.ENTERTAINMENT:

                            callMapFragmentWithEntertainment(index, null, false);
                            break;
                        case AppConstants.GOVERNMENT:

                            callMapFragmentWithGovernment(index, null, false);
                            break;
                        case AppConstants.LEGAL:

                            callMapFragmentWithLegal(index, null, false);
                            break;
                        case AppConstants.FINANCIAL:

                            callMapFragmentWithFinancial(index, null, false);
                            break;
                        default:
                            break;
                    }

                } else {

                    ivIcon.setImageResource(0);
                    ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONSBUTTON2[index]);
                    flag2[index] = 1; // 0 => Button OFF
                    switch (currentCategoryID) {


                        case AppConstants.EDUCATION:

                            callMapFragmentWithEducation(index, null, true);
                            break;

                        case AppConstants.HEALTH:

                            callMapFragmentWithHealth(index, null, true);
                            break;
                        case AppConstants.ENTERTAINMENT:

                            callMapFragmentWithEntertainment(index, null, true);
                            break;
                        case AppConstants.GOVERNMENT:

                            callMapFragmentWithGovernment(index, null, true);
                            break;
                        case AppConstants.LEGAL:

                            callMapFragmentWithLegal(index, null, true);
                            break;
                        case AppConstants.FINANCIAL:

                            callMapFragmentWithFinancial(index, null, true);
                            break;
                        default:
                            break;
                    }
                }


                // showSubCatListItem.setEnabled(true);
                // subCatItemListHeader.setText(si.getSubcatHeader());
                constructSubCategoryItemList(cat_id, si.getSubcatHeader());
            }
        });

        return v;
    }

    private ArrayList<SubCategoryItem> getSubCategoryList(int id) {
        // TODO Get sub-categories from the SUB_CATEGORY local table : NEXT PHASE
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
        return subCategoryTable.getAllSubCategories(id);
    }


    private void showAnimatedSubcategories(final ArrayList<SubCategoryItem> subCatList, double dwPerc, int iconId, final int cat_id) {
        isCatExpandedOnce = true;


        // TODO Inflate the sub-category list from right

        if (subCatShowFlag == 1 && showList != 1) {
            svsholder.setVisibility(View.VISIBLE);
            svs.setVisibility(View.VISIBLE);
            llSubCatListHolder.setVisibility(View.VISIBLE);
        }
        subCatShowFlag = 1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (showList != 1)
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


    /*********************************************************
     * methods for education
     **********************************************/

    private ArrayList<EducationNewItem> constructEducationListItem() {
        ArrayList<EducationNewItem> educationServiceProvider;
        EducationNewTable educationNewTable = new EducationNewTable(PlaceDetailsActivityNewLayout.this);
        educationServiceProvider = educationNewTable.getAllEducationSubCategoriesInfo(getLocationNameEng());
        return educationServiceProvider;
    }


    private void callMapFragmentWithEducation(int edid, ArrayList<EducationNewItem> educationServiceProviderItems, boolean s) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        if (locationNameId == 1) fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);
        else fragment.getMapViewController().setCenter(AppConstants.PARIS1);
        fragment.getMapViewController().setZoom(16);

        if (edid == -1) {
            fragment.setCategoryId(1);
            fragment.setEducationServiceProvider(educationServiceProviderItems);
            fragment.eduicons();
            fragment.Drawedu(edid, s);
            mainedcalled = true;
        } else {
            if (mainedcalled) {

                mainedcalled = false;
            }
            fragment.Drawedu(edid, s);
        }
        // EDD.clear();
    }

    private void callMapFragment(int locationNameId) {

        FragmentManager fragmentManager = getFragmentManager();
        if (mapfirst) {


            mapFragment = new MapFragmentOSM();
            mapFragment.setLocationName(getPlaceChoice());
            mapFragment.setLocationNameId(locationNameId);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.map_fragment, mapFragment, "MAP");
            fragmentTransaction.addToBackStack("MAP");
            fragmentManager.executePendingTransactions();
            fragmentTransaction.commit();
            mapfirst = false;
        }


        MapFragmentOSM prev_fragment = (MapFragmentOSM) getFragmentManager().findFragmentByTag("MAP");
        if (prev_fragment != null && prev_fragment.getMapViewController() != null) {
            if (locationNameId == 1)
                prev_fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);
            else prev_fragment.getMapViewController().setCenter(AppConstants.PARIS1);
            prev_fragment.getMapViewController().setZoom(16);
            if (mapcalledstatus) {
                Arrays.fill(flag2, 1);
                if (currentCategoryID == 1) {
                    educlicked = false;
                    prev_fragment.setCategoryId(1);
                    ArrayList<EducationNewItem> educationServiceProviderItems;
                    educationServiceProviderItems = constructEducationListItem();
                    prev_fragment.setEducationServiceProvider(educationServiceProviderItems);
                    prev_fragment.eduicons();
                    prev_fragment.Drawedu(-1, true);
                    if (educationServiceProviderItems.size() == 0) {

                        AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                    }

                } else if (currentCategoryID == 2) {
                    helclicked = false;
                    mapFragment.setCategoryId(2);
                    ArrayList<HealthServiceProviderItemNew> healthServiceProviderItems;
                    healthServiceProviderItems = constructHealthListItem(2);
                    mapFragment.setHealthServiceProvider(healthServiceProviderItems);
                    prev_fragment.healthicons();
                    if (healthServiceProviderItems.size() == 0) {

                        AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                    }
                    prev_fragment.Drawhel(-1, true);
                } else if (currentCategoryID == 3) {
                    entclicked = false;
                    mapFragment.setCategoryId(3);
                    ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProviderItems;
                    entertainmentServiceProviderItems = constructEntertainmentListItem(3);
                    mapFragment.setEntertainmentServiceProvider(entertainmentServiceProviderItems);
                    prev_fragment.enticons();
                    if (entertainmentServiceProviderItems.size() == 0) {

                        AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                    }
                    prev_fragment.Drawent(-1, true);
                } else if (currentCategoryID == 4) {
                    govclicked = false;
                    mapFragment.setCategoryId(4);
                    ArrayList<GovernmentNewItem> governmentNewItems;
                    governmentNewItems = constructgovListItem();
                    if (governmentNewItems.size() == 0) {

                        AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                    }
                    mapFragment.setGovernmentNewItems(governmentNewItems);
                    prev_fragment.govicons();
                    prev_fragment.Drawgov(-1, true);

                } else if (currentCategoryID == 5) {
                    legclicked = false;
                    mapFragment.setCategoryId(5);
                    ArrayList<LegalAidServiceProviderItemNew> legalAidServiceProviderItems;
                    legalAidServiceProviderItems = constructlegalaidListItem(5);
                    mapFragment.setLegalaidServiceProvider(legalAidServiceProviderItems);
                    prev_fragment.legicons();
                    prev_fragment.Drawleg(-1, true);
                    if (legalAidServiceProviderItems.size() == 0) {

                        AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                    }
                } else if (currentCategoryID == 6) {
                    finclicked = false;
                    mapFragment.setCategoryId(6);
                    ArrayList<FinancialNewItem> financialNewItems;
                    financialNewItems = constructfinancialListItem();
                    mapFragment.setFinancialServiceProvider(financialNewItems);
                    prev_fragment.finicons();
                    if (financialNewItems.size() == 0) {

                        AlertMessage.showMessage(PlaceDetailsActivityNewLayout.this, "Sorry! No information is available for this category item.", "");
//
                    }
                    prev_fragment.Drawfin(-1, true);
                }
                ArrayList<SubCategoryItem> subCatList = getSubCategoryList(currentCategoryID);
                if (SearchClicked != true) {
                    showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId());
                }
            }

        }
    }

    /***********************************************************
     * Methods for Health
     *************************************************/

    private ArrayList<HealthServiceProviderItemNew> constructHealthListItem(int cat_id) {
        ArrayList<HealthServiceProviderItemNew> healthServiceProvider;
        HealthServiceProviderTableNew healthServiceProviderTable = new HealthServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        healthServiceProvider = healthServiceProviderTable.getAllHealthSubCategoriesInfosearch(getLocationNameEng());
        return healthServiceProvider;
    }

    private void callMapFragmentWithHealth(int helid, ArrayList<HealthServiceProviderItemNew> healthServiceProviderItemNews, boolean s) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        if (locationNameId == 1) fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);
        else fragment.getMapViewController().setCenter(AppConstants.PARIS1);
        fragment.getMapViewController().setZoom(16);
        if (helid == -1) {
            fragment.setCategoryId(2);
            fragment.setHealthServiceProvider(healthServiceProviderItemNews);
            fragment.healthicons();
            fragment.Drawhel(helid, s);
            mainedcalled = true;
        } else {
            if (mainedcalled) {

                mainedcalled = false;
            }
            fragment.Drawhel(helid, s);
        }
        // EDD.clear();
    }


    /**********************************************************
     * Methods for entertainment
     *******************************************/

    private ArrayList<EntertainmentServiceProviderItemNew> constructEntertainmentListItem(int cat_id) {
        ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProviderItemNews;
        EntertainmentServiceProviderTableNew entertainmentServiceProviderTableNew = new EntertainmentServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        entertainmentServiceProviderItemNews = entertainmentServiceProviderTableNew.getAllEntertainmentSubCategoriesInfo(getLocationNameEng());
        return entertainmentServiceProviderItemNews;
    }

    private void callMapFragmentWithEntertainment(int edid, ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProviderItemNews, boolean s) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        if (locationNameId == 1) fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);
        else fragment.getMapViewController().setCenter(AppConstants.PARIS1);
        fragment.getMapViewController().setZoom(16);

        if (edid == -1) {
            fragment.setCategoryId(3);
            fragment.setEntertainmentServiceProvider(entertainmentServiceProviderItemNews);
            fragment.enticons();
            fragment.Drawent(edid, s);
            mainedcalled = true;
        } else {
            if (mainedcalled) {

                mainedcalled = false;
            }
            fragment.Drawent(edid, s);
        }

    }


    /**********************************************************
     * Methods for government
     **********************************************/

    private ArrayList<GovernmentNewItem> constructgovListItem() {
        ArrayList<GovernmentNewItem> governmentNewItems;
        GovernmentNewTable governmentNewTable = new GovernmentNewTable(PlaceDetailsActivityNewLayout.this);
        governmentNewItems = governmentNewTable.getAllGovSubCategoriesInfo(getLocationNameEng());
        return governmentNewItems;
    }

    private void callMapFragmentWithGovernment(int edid, ArrayList<GovernmentNewItem> governmentNewItems, boolean s) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        if (locationNameId == 1) fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);
        else fragment.getMapViewController().setCenter(AppConstants.PARIS1);
        fragment.getMapViewController().setZoom(16);

        if (edid == -1) {
            fragment.setCategoryId(4);
            fragment.setGovernmentNewItems(governmentNewItems);
            fragment.govicons();
            fragment.Drawgov(edid, s);
            mainedcalled = true;
        } else {
            if (mainedcalled) {

                mainedcalled = false;
            }
            fragment.Drawgov(edid, s);
        }

    }


    /**********************************************************
     * Methods for legal
     ***************************************************/

    private ArrayList<LegalAidServiceProviderItemNew> constructlegalaidListItem(int cat_id) {
        ArrayList<LegalAidServiceProviderItemNew> legalaidServiceProvider;
        LegalAidServiceProviderTableNew legalAidServiceProviderTable = new LegalAidServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfosearch(getLocationNameEng());


        return legalaidServiceProvider;
    }

    private void callMapFragmentWithLegal(int edid, ArrayList<LegalAidServiceProviderItemNew> legalAidServiceProviderItemNews, boolean s) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        if (locationNameId == 1) fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);
        else fragment.getMapViewController().setCenter(AppConstants.PARIS1);
        fragment.getMapViewController().setZoom(16);

        if (edid == -1) {
            fragment.setCategoryId(5);
            fragment.setLegalaidServiceProvider(legalAidServiceProviderItemNews);
            fragment.legicons();
            fragment.Drawleg(edid, s);
            mainedcalled = true;
        } else {
            if (mainedcalled) {
                mainedcalled = false;
            }
            fragment.Drawleg(edid, s);
        }

    }


    /**********************************************************
     * Methods for financial
     **********************************************/
    private ArrayList<FinancialNewItem> constructfinancialListItem() {
        ArrayList<FinancialNewItem> financialNewItems;
        FinancialServiceNewTable financialServiceNewTable = new FinancialServiceNewTable(PlaceDetailsActivityNewLayout.this);
        financialNewItems = financialServiceNewTable.getAllFinancialSubCategoriesInfo(getLocationNameEng());
        return financialNewItems;
    }

    private void callMapFragmentWithFinancial(int edid, ArrayList<FinancialNewItem> financialNewItems, boolean s) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        if (locationNameId == 1) fragment.getMapViewController().setCenter(AppConstants.BAUNIA1);
        else fragment.getMapViewController().setCenter(AppConstants.PARIS1);
        fragment.getMapViewController().setZoom(16);

        if (edid == -1) {
            fragment.setCategoryId(6);
            fragment.setFinancialServiceProvider(financialNewItems);
            fragment.finicons();
            fragment.Drawfin(edid, s);
            mainedcalled = true;
        } else {
            if (mainedcalled) {

                mainedcalled = false;
            }
            fragment.Drawfin(edid, s);
        }

    }


    /**********************************************************
     * Methods for job
     *****************************************************/

    public void init(final Activity activity) {

        bazar_post_layout = (LinearLayout) findViewById(R.id.bazar_post_layout);
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        mLayout.setPanelHeight(pannl_height);
        FrameLayout bazarPosting = (FrameLayout) findViewById(R.id.bazar_posting);
        slider_part = (LinearLayout) findViewById(R.id.slider_part);
//
//        LinearLayout.LayoutParams sliding_parts = (LinearLayout.LayoutParams) slider_part.getLayoutParams();
//        sliding_parts.height=120;
//        slider_part.setLayoutParams(sliding_parts);

        mLayout.setTouchEnabled(true);

        bazarPosting.setEnabled(true);
        bazar_post_layout.setFocusableInTouchMode(true);
        bazar_post_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        Log.d("Panel States", "******" + panelStates);

        slider_part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.hideKeyboard(activity);
                Log.d("Panel works or not", "*********");
                Log.d("Panel States", "******" + panelStates);
                if (panelStates) {
                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                    panelStates = false;
                } else {
                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

                    panelStates = true;
                }
            }
        });
//
//        slider_part.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
////                if(mLayout.getPanelState().equals("EXPANDED"))
////                {
////
////                }
////                else
////                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
//                return true;
//            }
//        });


        // mLayout.setTouchEnabled(false);
        //   bazarPosting.setEnabled(true);
        // bazarPosting.setClickable(true);


        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bazar_tool.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, (smal * 5) / 4);
        bazar_tool.setLayoutParams(layoutParams);


    }


    private void loadBazar(final Context context) {
        getRequest(PlaceDetailsActivityNewLayout.this, "http://kolorob.net/demo/api/getadvsql?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {

                        if (status == AppConstants.SUCCESS_CODE) {
                            //ge the db instance
                            SQLiteDatabase db = DatabaseManager.getInstance(PlaceDetailsActivityNewLayout.this).openDatabase();
                            //split into single sql queries
                            String[] sql = apiContent.split("~");
                            //run the sqls one by one
                            for (int i = 0; i < sql.length; i++) {
                                Log.d("SQL[i]", "%%%%%%" + sql[i]);
                                db.execSQL(sql[i]);
                            }
                            //now reload the data taht has beed saved
                            //get all data from db
                            Cursor cursor = db.rawQuery("select * from custom_advertisement", null);
                            allBazar = new ArrayList<BazarItem>();
                            int vf = 0;
                            while (cursor.moveToNext()) {
                                allBazar.add(new BazarItem(cursor));
                                vf++;
                            }
                            Log.d("vvff", "%%%%%%" + vf);
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
                        bazar_data = new ArrayList<String>();
                        bazar_data.clear();
                        bazar_counter = 0;
                        myList = new ArrayList<ArrayList<String>>(allBazar.size());
                        int size = allBazar.size();
                        for (BazarItem bazarItem : allBazar) {

                            //

                            String bazarData = "Description: " + bazarItem.description + "@" +
                                    bazarItem.price + "@" +
                                    "Date: " + bazarItem.date + "@" +

                                    bazarItem.condition + "@" +
                                    "Area: " + bazarItem.address + "@" +
                                    bazarItem.phone + "@" +
                                    "Phone Number: " + bazarItem.contact + "@" +
                                    "Posted by: " + bazarItem.contact_person + "@" + "v";


                            Log.d("Bazar Data", "=============" + bazarData);

                            String group_data = bazarItem.product_name + "@" +
                                    bazarItem.type + "@" + "v";

                            bazar_data.add(bazar_counter, bazarData);


                            listDataHeader.add(group_data);

                            // myList.add(bazar_counter,bazar_data);
                            // myList.get(bazar_counter).add(bazarData);
                            //             myList.add(bazar_data);

                            Log.d("myList", "######" + myList);


                            bazar_counter++;


                        }
                        ArrayList<String> temp = new ArrayList<String>();


                        for (int k = 0; k < bazar_counter; k++) {

                            myList.add(k, bazar_data);
                            // myList.get(0).set(k,bazar_data.get(k));
                            //                      myList.add(k,temp);

                            temp.clear();
                        }


                        for (int i = 0; i < bazar_counter; i++) {
                            listDataChild.put(listDataHeader.get(i), myList.get(i));

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


                        Log.d("Button Heights", "%%%%%%" + buttonHeights);
                        SlidingUpPanelLayout slidingUpPanelLayout;
                        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
                        RelativeLayout.LayoutParams slidingp = (RelativeLayout.LayoutParams) slidingUpPanelLayout.getLayoutParams();


                    }
                }
        );
    }


    public void panelListener(final Context context) {

        mLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {

            // During the transition of expand and collapse onPanelSlide function will be called.
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

                Log.d(">>>>", "Slide Up");

            }


            // This method will be call after slide up layout
            @Override
            public void onPanelExpanded(View panel) {

                slider_part.setVisibility(View.VISIBLE);

                LinearLayout.LayoutParams sliding_parts = (LinearLayout.LayoutParams) slider_part.getLayoutParams();
                sliding_parts.setMargins(0, height / 25, 0, height / 140);
                slider_part.setLayoutParams(sliding_parts);
                pannl_height = height / 17;

                int heights = footer.getHeight();
                Log.d("footer height", "================" + heights);
                submit_bazar.setHeight(heights);
                if (pannl_height < 70)
                    pannl_height = 70;
                //submit_bazar.getLayoutParams.(pannl_height);
                //   submit_bazar.getLayoutParams().height=pannl_height;


                footer.setText("বিজ্ঞাপন দেখুন");

                String number = SharedPreferencesHelper.getNumber(context);
//                if(number.equals(""))
//                {
//                    AlertMessage.showAskToRegister(context,"অনুগ্রহ পূর্বক রেজিস্ট্রাতিওন করুন","");
//                }
//                else {
                bazar_post_layout.setVisibility(View.VISIBLE);
//                }


                postbazar(context);

            }

            // This method will be call after slide down layout.
            @Override
            public void onPanelCollapsed(View panel) {
                slider_part.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams sliding_parts = (LinearLayout.LayoutParams) slider_part.getLayoutParams();
                sliding_parts.setMargins(0, 0, 0, height / 140);
                slider_part.setLayoutParams(sliding_parts);
                buttonh = footer.getHeight();
                pannl_height = height / 17;
                if (pannl_height < 70)
                    pannl_height = 70;
                // submit_bazar.setHeight(pannl_height);
                Log.d(">>>>", "onPanelCollapsed");

                footer.setText("বিজ্ঞাপন দিন");

            }


            @Override
            public void onPanelAnchored(View panel) {
                Log.e(TAG, "onPanelAnchored");
            }

            @Override
            public void onPanelHidden(View panel) {

                Log.d("OnPanelHidden", "============");
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);


                Log.e(TAG, "onPanelHidden");
            }
        });
    }


    public void Populateholder(String place) {
        filterText = (EditText) findViewById(R.id.searchall);
        filterText.setTextColor(getResources().getColor(R.color.white));
        EducationNewTable educationServiceProviderTable = new EducationNewTable(PlaceDetailsActivityNewLayout.this);
        EntertainmentServiceProviderTableNew entertainmentServiceProviderTable = new EntertainmentServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        HealthServiceProviderTableNew healthServiceProviderTable = new HealthServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        FinancialServiceNewTable financialServiceProviderTable = new FinancialServiceNewTable(PlaceDetailsActivityNewLayout.this);
        GovernmentNewTable governmentNewTable = new GovernmentNewTable(PlaceDetailsActivityNewLayout.this);
        LegalAidServiceProviderTableNew legalAidServiceProviderTable = new LegalAidServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        fetchedent = entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfo(place);
        fetchedfin = financialServiceProviderTable.getAllFinancialSubCategoriesInfo(place);
        fetchedleg = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfosearch(place);
        fetchedhel = healthServiceProviderTable.getAllHealthSubCategoriesInfosearch(place);
        fetchededu = educationServiceProviderTable.getAllEducationSubCategoriesInfo(place);
        fetchedgov = governmentNewTable.getAllGovSubCategoriesInfo(place);
        String nameen;
        String namebn;

        int node;
        String refname;
        for (int i = 0; i < fetchededu.size(); i++) {

            nameen = fetchededu.get(i).getNameen();
            node = fetchededu.get(i).getEduId();
            refname = fetchededu.get(i).getRefnumm();
            namebn = fetchededu.get(i).getNamebn();

            AllHolder all = new AllHolder(node, refname, nameen, namebn, 5);
            allHolders.add(all);
        }


        for (int i = 0; i < fetchedhel.size(); i++) {

            nameen = fetchedhel.get(i).getNode_name();
            node = Integer.parseInt(fetchedhel.get(i).getId());
            refname = fetchedhel.get(i).getReferences();
            namebn = fetchedhel.get(i).getNode_bn();

            AllHolder all = new AllHolder(node, refname, nameen, namebn, 1);
            allHolders.add(all);
        }


        for (int i = 0; i < fetchedleg.size(); i++) {

            nameen = fetchedleg.get(i).getLegalaidNameEng();
            node = Integer.parseInt(fetchedleg.get(i).getIdentifierId());
            refname = fetchedleg.get(i).getBreaktime2();
            namebn = fetchedleg.get(i).getLegalaidNameBan();

            AllHolder all = new AllHolder(node, refname, nameen, namebn, 29);
            allHolders.add(all);
        }
        for (int i = 0; i < fetchedent.size(); i++) {

            nameen = fetchedent.get(i).getNodeName();
            node = Integer.parseInt(fetchedent.get(i).getNodeId());
            refname = fetchedent.get(i).getNodeAdditional();
            namebn = fetchedent.get(i).getNodeNameBn();

            AllHolder all = new AllHolder(node, refname, nameen, namebn, 14);
            allHolders.add(all);
        }
        for (int i = 0; i < fetchedfin.size(); i++) {

            nameen = fetchedfin.get(i).getNameen();
            node = fetchedfin.get(i).getFinId();
            refname = fetchedfin.get(i).getRefnumm();
            namebn = fetchedfin.get(i).getNamebn();

            AllHolder all = new AllHolder(node, refname, nameen, namebn, 11);
            allHolders.add(all);


        }

        for (int i = 0; i < fetchedgov.size(); i++) {

            nameen = fetchedgov.get(i).getNameen();
            node = fetchedgov.get(i).getFinId();
            refname = fetchedgov.get(i).getRefnumm();
            namebn = fetchedgov.get(i).getNamebn();

            AllHolder all = new AllHolder(node, refname, nameen, namebn, 33);
            allHolders.add(all);


        }
        calladapter(false);

    }


    public void postbazar(final Context context) {
        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.bazar_spinner);
        final Spinner type_spinner = (Spinner) findViewById(R.id.type_spinner);

        List<String> categories = new ArrayList<String>();
        categories.add("Condition");
        categories.add("New");
        categories.add("Used");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.bazar_spinner, categories);
        spinner.setAdapter(dataAdapter);


        product_name = (EditText) findViewById(R.id.product_name);
        phone = (EditText) findViewById(R.id.phone_no);
        address = (EditText) findViewById(R.id.address);
        price = (EditText) findViewById(R.id.costs);
        description = (EditText) findViewById(R.id.descriptions);
        contact_person = (EditText) findViewById(R.id.contact_person);
        contact = (EditText) findViewById(R.id.contact);
        int heightconsiderforcost = contact_person.getHeight();


        screenSize = AppUtils.ScreenSize(this);

        int text_field_height;
        if (screenSize > 6.5)
            text_field_height = height / 30;
        else
            text_field_height = height / 24;
        LinearLayout.LayoutParams spinnners = (LinearLayout.LayoutParams) spinner.getLayoutParams();
        spinnners.height = text_field_height;
        spinner.setLayoutParams(spinnners);


        LinearLayout.LayoutParams type_spinners = (LinearLayout.LayoutParams) type_spinner.getLayoutParams();
        type_spinners.height = text_field_height;
        spinner.setLayoutParams(spinnners);


        product_name.setHeight(text_field_height);
        price.setHeight(text_field_height);
        description.setHeight(text_field_height);
        contact_person.setHeight(text_field_height);
        contact.setHeight(text_field_height);
        phone.setHeight(text_field_height);
        address.setHeight(text_field_height);


        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if ((position >= 1 && position <= 3) && spinCounter > 0) {
                    TextView text1 = (TextView) parent.getChildAt(0);

                    text1.setTextColor(ContextCompat.getColor(context, R.color.black));
                    text1.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
//                    spinner.setBackgroundResource(R.drawable.border_spinner_adapter);
//                    spinner.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this,R.color.white));

                } else {
                    TextView text1 = (TextView) parent.getChildAt(0);
                    text1.setTextColor(ContextCompat.getColor(context, R.color.white));
                    text1.setBackgroundColor(ContextCompat.getColor(context, R.color.drak_yellow));
                }


                spinCounter++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TextView text1 = (TextView) parent.getChildAt(0);
                text1.setTextColor(ContextCompat.getColor(context, R.color.white));
            }
        });


        List<String> types = new ArrayList<String>();
        types.add("Advertizement Type");
        types.add("Buy");
        types.add("Sell");
        types.add("Exchange");
        types.add("To Let");
        types.add("Tution");
        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(this, R.layout.bazar_spinner, types);
        type_spinner.setAdapter(type_adapter);


        type_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if ((position >= 1 && position <= 5) && spinCounter1 > 0) {
                    TextView text2 = (TextView) parent.getChildAt(0);

                    text2.setTextColor(ContextCompat.getColor(context, R.color.black));
                    text2.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
//                    spinner.setBackgroundResource(R.drawable.border_spinner_adapter);
//                    spinner.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this,R.color.white));

                } else {
                    TextView text2 = (TextView) parent.getChildAt(0);
                    text2.setTextColor(ContextCompat.getColor(context, R.color.white));
                    text2.setBackgroundColor(ContextCompat.getColor(context, R.color.drak_yellow));
                }

                final LinearLayout pricing = (LinearLayout) findViewById(R.id.pricing);

                if (position == 5) {
                    spinner.setVisibility(View.GONE);
                    pricing.setVisibility(View.GONE);
                    product_name.setVisibility(View.VISIBLE);
                    tution_detector = 1;
                    product_name.setHint("Tution Type");
                    description.setHint("Tution Details");
                    tution_detector = position;
                } else if (position == 4) {
                    spinner.setVisibility(View.GONE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    product_name.setHint("What do you want to rent?");
                    price.setHint("House Rent");
                    description.setHint("House Description");
                    tution_detector = position;
                } else if (position == 3) {
                    spinner.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    product_name.setHint("Whant do you want to Exchange");
                    price.setHint("Price");
                    description.setHint("Description");
                    tution_detector = 0;
                } else if (position == 2) {
                    spinner.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    product_name.setHint("What do you want to Exchange");
                    price.setHint("Price");
                    tution_detector = 0;
                    description.setHint("Description");
                } else if (position == 1) {
                    spinner.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    product_name.setHint("What do you want to buy");
                    price.setHint("Price");
                    description.setHint("Description");
                    tution_detector = 0;
                } else {
                    spinner.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    tution_detector = 0;
                    product_name.setHint("What ");
                    description.setHint("Description");
                }


                spinCounter1++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TextView text2 = (TextView) parent.getChildAt(0);
                text2.setTextColor(ContextCompat.getColor(context, R.color.white));
            }
        });

        negotiable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    negotiable_check = 1;
                else
                    negotiable_check = 0;
            }
        });

        screenSize = AppUtils.ScreenSize(this);

        if (screenSize > 6.5)
            negotiable.setTextSize(20);
        else
            negotiable.setTextSize(14);


        String number = "01790615263";
        String name = "Kolorob";
        phone.setText(number);
        phone.setEnabled(false);
        contact_person.setText(name);
        contact_person.setEnabled(true);


        product_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    product_name.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                } else {
                    product_name.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    phone.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                } else {
                    phone.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    address.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                } else {
                    address.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    price.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                } else {
                    price.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    description.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                } else {
                    description.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        contact_person.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    contact_person.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                } else {
                    contact_person.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    contact.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                } else {
                    contact.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        submit_bazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


//           final BazarItem b = new BazarItem();
//           b.description=description.getText().toString();
//
//           b.type = type_spinner.getSelectedItem().toString();
//           b.phone = phone.getText().toString(); //MUST BE REGISTERED
//           b.contact = contact.getText().toString();
//           b.condition = spinner.getSelectedItem().toString();
//           b.contact_person = contact_person.getText().toString();
//           b.address= "address";
//           Log.d("type Spinner","$$$$$$"+address.getText().toString());
//           if(negotiable_check)
//           {
//               b.price = price.getText().toString()+ " (Negotiable)";
//           }
//           else {
//               b.price = price.getText().toString();
//           }
//
//           b.product_name= "product";


                    if (tution_detector == 5) {
                        spinner.setSelection(2);
                        price.setText("1111");
                    } else if (tution_detector == 4) {
                        spinner.setSelection(2);

                    }


                    final BazarItem b = new BazarItem();

                    String number = SharedPreferencesHelper.getNumber(context);
                    if (number.equals("")) {

                    } else {
                        if (spinner.getSelectedItem().toString().equals("Condition")) {


                            AlertMessage.showMessage(context, "Please Insert Condition", "");
//               ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক কন্ডিশন ইনপুট দিন");

                        } else {

                            String conditions = spinner.getSelectedItem().toString();

                            if (conditions.equals("New"))
                                b.condition = "New";
                            else if (conditions.equals("Refurbished"))
                                b.condition = "Refurbished";


                            if (type_spinner.getSelectedItem().toString().equals("Specialist Type")) {
                                AlertMessage.showMessage(context, "Please Insert Advertizement Type", "");
//                   ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক বিজ্ঞাপনের ধরন ইনপুট দিন");
                            } else {
                                String condition_selector = type_spinner.getSelectedItem().toString();

                                if (condition_selector.equals("Exchange"))
                                    b.type = "Exchange";
                                else if (condition_selector.equals("Sell"))
                                    b.type = "Sell";
                                else if (condition_selector.equals("Tution"))
                                    b.type = "Tution";
                                else if (condition_selector.equals("Buy"))
                                    b.type = "Buy";
                                else if (condition_selector.equals("To_Let"))
                                    b.type = "To_Let";
//                       b.type = type_spinner.getSelectedItem().toString().replace(' ','+');
                                if (product_name.getText().toString().equals("")) {
//                       AlertMessage.showMessage(context,"","");
                                    AlertMessage.showMessage(context, "Please Insert Product Name", "");
//                       ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক পন্যের নাম ইনপুট দিন");
                                } else {

                                    b.product_name = product_name.getText().toString();
                                    try {
                                        pname = URLEncoder.encode(b.product_name.replace(" ", "%20"), "utf-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    if (phone.getText().toString().equals("")) {
                                        AlertMessage.showMessage(context, "Please Insert Phone Number", "");
//                           ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক ফোন নম্বর ইনপুট দিন");
                                    } else {
                                        b.phone = phone.getText().toString(); //MUST BE REGISTERED
                                        if (AppUtils.mobile_number_verification(contact.getText().toString())) {
                                            AlertMessage.showMessage(context, "Please Insert Correct Phone Number", "");
//                               ToastMessageDisplay.setText(context, "অনুগ্রহ পূর্বক অন্য ফোন নম্বরটি ইনপুট দিন");
                                        } else {
                                            b.contact = contact.getText().toString();
                                            if (address.getText().toString().equals("")) {
                                                AlertMessage.showMessage(context, "Please Insert your address", "");
//
                                            } else {
                                                b.address = address.getText().toString();
                                                try {
                                                    paddress = URLEncoder.encode(b.address.replace(" ", "%20"), "utf-8");
                                                } catch (UnsupportedEncodingException e) {
                                                    e.printStackTrace();
                                                }
                                                if (contact_person.getText().toString().equals("")) {
//
                                                    AlertMessage.showMessage(context, "Please Insert your Name", "");
                                                } else {
                                                    b.contact_person = contact_person.getText().toString();
                                                    try {
                                                        powner = URLEncoder.encode(b.contact_person.replace(" ", "%20"), "utf-8");
                                                    } catch (UnsupportedEncodingException e) {
                                                        e.printStackTrace();
                                                    }
                                                    if (price.getText().toString().equals("") && negotiable_check == 0) {
//                                           ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক পণ্যের মূল্য ইনপুট দিন");
                                                        AlertMessage.showMessage(context, "Please Insert price", "");
                                                    } else {
                                                        Log.d("negotiable_check", "==============" + negotiable_check);

                                                        b.price = price.getText().toString() + negotiable_check;
                                                        if (description.getText().toString().equals("")) {
//                                               ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক বিস্তারিত তহত্য ইনপুট দিন");
                                                            AlertMessage.showMessage(context, "Please insert details information", "");
                                                        } else {
                                                            b.description = description.getText().toString();
                                                            try {
                                                                pdescription = URLEncoder.encode(b.description.replace(" ", "%20"), "utf-8");
                                                            } catch (UnsupportedEncodingException e) {
                                                                e.printStackTrace();
                                                            }
                                                            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                                                            height = displayMetrics.heightPixels;
                                                            final int width = displayMetrics.widthPixels;

                                                            LayoutInflater layoutInflater = LayoutInflater.from(context);
                                                            View promptView = layoutInflater.inflate(R.layout.default_alert, null);


                                                            final Dialog alertDialog = new Dialog(context);
                                                            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                            alertDialog.setContentView(promptView);
                                                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                            alertDialog.show();


                                                            final TextView header = (TextView) promptView.findViewById(R.id.headers);
                                                            final TextView bodys = (TextView) promptView.findViewById(R.id.body);
                                                            final ImageView okay = (ImageView) promptView.findViewById(R.id.okay);

                                                            header.setText("Your Advertizement has been sent");
                                                            bodys.setText("Thank you for advertizement through kolorob");

                                                            product_name.setText("");
                                                            phone.setText("");
                                                            address.setText("");
                                                            price.setText("");
                                                            description.setText("");
                                                            contact_person.setText("");
                                                            contact.setText("");
                                                            negotiable.setChecked(false);
                                                            negotiable_check = 0;

                                                            okay.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    alertDialog.cancel();
                                                                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                                                    loadBazar(PlaceDetailsActivityNewLayout.this);
                                                                }
                                                            });

                                                            alertDialog.setCancelable(true);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
                                                            alertDialog.getWindow().setLayout((width * 5) / 6, WindowManager.LayoutParams.WRAP_CONTENT);
                                                        }
                                                    }
                                                }
                                            }

                                        }

                                    }
                                }

                            }
                        }
                    }


//                    if(b.equals(""))
//                    {
//                        ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক তথ্য ইনপুট দিন");
//                    }

//           if()


                } catch (Exception e) {

                }

            }
        });


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

    public static boolean moreThanOnce(ArrayList<Integer> list, int searched) {

        int numCount = 0;
        boolean more = false;

        for (int thisNum : list) {
            if (thisNum == searched) {
                numCount++;
            }
        }

        if (numCount > 1) {
            more = true;
        }

        return more;
    }

    private void calladapter(boolean status) {
        boolean instatus = status;
        if (instatus == true) {
            int gotcatid = getFilcatid();
            catHolders.clear();
            for (int ii = 0; ii < allHolders.size(); ii++) {
                if (allHolders.get(ii).getCatid() == gotcatid) {
                    catHolders.add(allHolders.get(ii));
                }
            }
            if (filterclicked) {
                checknum = String.valueOf(getSnumber());
            } else checknum = String.valueOf(0);
            if (Integer.parseInt(checknum) != 0) {
                subcatHolders.clear();
                for (int iii = 0; iii < catHolders.size(); iii++) {
                    if (catHolders.get(iii).getRefnum().contains(checknum)) {
                        subcatHolders.add(catHolders.get(iii));
                    }
                }
                adapter = new ListViewAdapterAllCategories(this, subcatHolders);

                allitemList.setAdapter(adapter);
            } else if (Integer.parseInt(checknum) == 0) {
                adapter = new ListViewAdapterAllCategories(this, catHolders);

                allitemList.setAdapter(adapter);
            }
        } else {
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


    public void fun1() {
        fgrp2.setOnCheckedChangeListener(null);
        fgrp2.clearCheck();
        fgrp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fun2();
                int buttonId = fgrp2.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(buttonId);
                setFilterword((String) radioButton.getText());
                int num = Findsubcatid(filterword);
                calladapter(true);

                Log.v("Inside fun1", String.valueOf(num));
            }
        });
    }

    public void fun2() {
        fgrp1.setOnCheckedChangeListener(null);
        fgrp1.clearCheck();
        fgrp1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                fun1();
                int buttonId = fgrp1.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(buttonId);
                setFilterword((String) radioButton.getText());
                int num = Findsubcatid(filterword);
                calladapter(true);

                Log.v("Inside fun2", "fun1");

            }
        });
    }

    private int Findsubcatid(String filterword) {

        for (int s = 0; s <= subholders.size(); s++) {
            if (subholders.get(s).getSubcatname().equals(filterword)) {
                setSnumber(subholders.get(s).getSubcatid());
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

        toggleButton.setVisibility(View.VISIBLE);
        spItems.setVisibility(View.VISIBLE);

//
//            map.setVisibility(View.GONE);
        if (showList == 1) {
//
//            ServiceListDisplayAdapter adapter = new ServiceListDisplayAdapter(this, groups, currentCategoryID);
//            subCatItemList.setAdapter(adapter);
        }


        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();


        if (NavigationCalledOnce == true) {
            //callMapFragment(locationNameId);
        } else {
            Intent intent;
            intent = getIntent();
            if (null != intent) {
                locationNameId = intent.getIntExtra(AppConstants.KEY_PLACE, 0);
                if (locationNameId == AppConstants.PLACE_BAUNIABADH) {
                    locationName = AppConstants.BAUNIABADH;
                    setPlaceChoice(locationName);
                } else if (locationNameId == AppConstants.PLACE_PARIS_ROAD) {
                    locationName = AppConstants.PARIS_ROAD;
                    setPlaceChoice(locationName);
                }
                if (!ListClicked.equals(true))
                    map.setVisibility(View.VISIBLE);
                if (SearchClicked) {
                    map.setVisibility(View.GONE);
                }
            }


        }
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
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
