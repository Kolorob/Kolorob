package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
import demo.kolorob.kolorobdemoversion.adapters.Group;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterAllCategories;
import demo.kolorob.kolorobdemoversion.adapters.SearchHolder;
import demo.kolorob.kolorobdemoversion.adapters.ServiceListDisplayAdapter;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationNewTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceNewTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovernmentNewTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.Health.HealthSpecialistTableDetails;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentOSM;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.interfaces.KolorobSpinner;
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
import demo.kolorob.kolorobdemoversion.utils.Lg;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
/**
 * Created by touhid on 12/3/15.
 *
 * @author touhid,israt,arafat
 */
public class PlaceDetailsActivityNewLayout extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    public int getShowList() {
        return showList;
    }
    EducationServiceProviderTable educationServiceProviderTable;
    EducationNewTable educationNewTable;
    ArrayList<EducationNewItem> firstDataSet;
    ArrayList<EducationNewItem> secondDataSet;
    ArrayList<HealthServiceProviderItemNew> firstDataSetHealth;
    ArrayList<HealthServiceProviderItemNew> secondDataSetHealth;
    public void setShowList(int showList) {
        this.showList = showList;
    }
    ToggleButton toggleButton;
    private static final String TAG = PlaceDetailsActivityNewLayout.class.getSimpleName();
    private static final int ANIM_INTERVAL = 200;
    private static double VIEW_WIDTH;
    private static boolean mapcalledstatus;
    private LinearLayout llCatListHolder,mapnother,listholder,explist,svholder,svsholder;
    CategoryItem ci;
    private LinearLayout llSubCatListHolder;
    private HashMap<String, Integer> sections = new HashMap<String, Integer>();
    private static FrameLayout map;
    private KolorobSpinner spItems;
    ArrayAdapter arrayAdapter;
    List<String>listData=new ArrayList<String>();
    private int height,dpi;
    private View nextChild;
    private ExpandableListView subCatItemList;
    private boolean isCatExpandedOnce = false;
    private int primaryIconWidth;
    private int subCatShowFlag=0;
    private int locationNameId,subcategory;
    private String locationName;
    private ListView expandableListview;
    private RelativeLayout wholeLayout;
    private int showList;
    private String locationNameEng;
    private String comapreData;
    ScrollView sv,svs;
    String firstData="",SecondData="";
    int checker=0;
    private Button prebutton;
    private HealthServiceProviderTableNew healthServiceProviderTableNew;
    private int sideIndexHeight;
    private LinearLayout compare_layout,shift1_1,shift1_11,canteen_facility_1,canteen_facility_11;
    private List<Object[]> alphabet = new ArrayList<Object[]>();
    Activity act;
    RelativeLayout compare_layoutedu;
    public int layoutstatus;
    private Boolean list_expand=false;
    private TextView listOrMapDisplayText;
    boolean educlicked,helclicked,entclicked,finclicked,govclicked,legclicked,jobclicked=false;
    private Toolbar toolbar;
    TextView health_name2,opening_time2,language_spoken2,service_type2,specialist_available2,clean_facilities2,privacy2,quality_equipment2;
    TextView health_name1,opening_time1,language_spoken1,service_type1,specialist_available1,clean_facilities1,privacy1,quality_equipment1,cost1,cost2,cost3;
    TextView health_name3,opening_time3,language_spoken3,service_type3,specialist_available3,clean_facilities3,privacy3,quality_equipment3;

    TextView edu_name_ban,edtype,hostel_facility,transport_facility,playground,total_students,total_classes,total_teachers,course_provided,shift,canteen_facility;
    TextView edu_name_ban1,edtype1,hostel_facility1,transport_facility1,playground1,total_students1,total_classes1,total_teachers1,course_provided1,shift1,canteen_facility1;
    TextView edu_name_ban22,edtype2,hostel_facility2,transport_facility2,playground2,total_students2,total_classes2,total_teachers2,course_provided2,shift2,canteen_facility2;

    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.


    ArrayList<EntertainmentServiceProviderItemNew> printnamesent;

    ArrayList<LegalAidServiceProviderItemNew> printnamesleg;
    ArrayList<HealthServiceProviderItemNew> printnameshea;
    ArrayList<FinancialNewItem> printnamesfin;
    ArrayList<String> allData= new ArrayList<>();
    private DrawerLayout drawer;
    ArrayList<SearchHolder> searchheads=new ArrayList<>();
    Context context;
    ArrayList <String>Headerholder=new ArrayList<>();
    ArrayList<EducationNewItem> printnames;
    ArrayList<GovernmentNewItem> printgovs;
    //common for all categories
    public LinearLayout sideIndex,searchLayout;
    public CategoryItem getCi() {
        return ci;
    }

    public void setCi(CategoryItem ci) {
        this.ci = ci;
    }

    private ArrayList<SubCategoryItem> currentSubCategoryItem;
    public static int currentCategoryID,currentCategoryIDconverted;
    private  ViewGroup.LayoutParams kk;
    Vector<Group> groups = new Vector<Group>();
    TextView header;
    private String placeChoice;
    private int indexListSize;
    private ListActivity listView;
    private ImageButton expandableListShowing,more,MapButton,ListButton,SearchButton,CompareButton;
    private RelativeLayout mapholderr;
    ArrayList<CategoryItem> categoryList;
    ArrayList<CategoryItem> categoryList2=new ArrayList<>();
    Boolean SearchClicked=false,MapClicked=false,ListClicked=false,CompareClicked=false;
    private Context con;
    public RelativeLayout getRlSubCatHolder() {
        return rlSubCatHolder;
    }

    public void setRlSubCatHolder(RelativeLayout rlSubCatHolder) {
        this.rlSubCatHolder = rlSubCatHolder;
    }

    private ListView itemList;
    public    RelativeLayout rlSubCatHolder;
    public String getPlaceChoice() {
        return placeChoice;
    }

    public void setPlaceChoice(String placeChoice) {
        this.placeChoice = placeChoice;
    }
    EditText Searchall,catsearch;
    boolean catsearchclicked=false;
    ListViewAdapterAllCategories adapter;
    EditText filterText;
    ListView allitemList;
    String filterword;
    TextView searchtext;

    int snumber=0;

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

    boolean catstatus=false;
    int filcatid;
    RelativeLayout catholder;
    CheckBox check;
    Boolean NavigationCalled,NavigationCalledOnce;
    LinearLayout fholder,fleft,fright,mbholder,lbholder,sbholder,cbholder;
    RelativeLayout searchviewholder,filterholder;
    ArrayList<AllHolder>allHolders=new ArrayList<>();
    ArrayList<AllHolder>catHolders=new ArrayList<>();
    ArrayList<AllHolder>subcatHolders=new ArrayList<>();
    private ArrayList<FinancialNewItem>fetchedfin;
    private ArrayList<EducationNewItem>fetchededu;
    private ArrayList<LegalAidServiceProviderItemNew>fetchedleg;
    private ArrayList<EntertainmentServiceProviderItemNew>fetchedent;
    private ArrayList<HealthServiceProviderItemNew>fetchedhel;
    public ArrayList<GovernmentNewItem>fetchedgov;
    private ArrayList<Subcatholder>subholders=new ArrayList<>();
    RadioGroup catgroup,fgrp1,fgrp2;
    int va;
    ArrayList<String>filter=new ArrayList<>();
    ArrayList<String>filter2=new ArrayList<>();
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
    boolean filterclicked=false;
    ArrayList<EducationNewItem> eduItem=new ArrayList<>();
    ArrayList<GovernmentNewItem> govItem=new ArrayList<>();
    ArrayList<HealthServiceProviderItemNew> healthItem=new ArrayList<>();
    ArrayList<EntertainmentServiceProviderItemNew> entItem=new ArrayList<>();
    ArrayList<LegalAidServiceProviderItemNew> legalItem=new ArrayList<>();
    ArrayList<FinancialNewItem> financialItem=new ArrayList<>();

    ArrayList<EducationNewItem> EDD=new ArrayList<>();
    ArrayList<HealthServiceProviderItemNew> HEL=new ArrayList<>();
    ArrayList<LegalAidServiceProviderItemNew>LEG=new ArrayList<>();
    ArrayList<EntertainmentServiceProviderItemNew>ENT =new ArrayList<>();
    ArrayList<FinancialNewItem>FIN=new ArrayList<>();
    ArrayList<GovernmentNewItem>GOV=new ArrayList<>();

    ArrayList <String>clicked=new ArrayList<>();
    EducationServiceProviderItem nulledu;
    EducationNewItem nulledu2;
    String nodefromback;

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
        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("Value", false);
        editor.putInt("ValueD", 23);

        editor.commit();

        NavigationCalled=false;
        NavigationCalledOnce=false;

        val = settings.getInt("KValue", 0);
        Log.e("ASinplaceDetails",String.valueOf(val));
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
      int checkheight=  getApplicationContext().getResources().getConfiguration().screenHeightDp;
        int checkwidth=  getApplicationContext().getResources().getConfiguration().screenWidthDp;
        dpi=displayMetrics.densityDpi;
        int width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        setContentView(R.layout.activity_place_detailnew);
fholder=(LinearLayout)findViewById(R.id.LinearLayoutfilter);
        con = this;
        MapButton=(ImageButton)findViewById(R.id.mapbutton);
        ListButton=(ImageButton)findViewById(R.id.listbutton);
        SearchButton=(ImageButton)findViewById(R.id.searchbutton);
        CompareButton=(ImageButton)findViewById(R.id.compare);
        searchviewholder=(RelativeLayout)findViewById(R.id.searchholder);

        shift1_1=(LinearLayout)findViewById(R.id.shift1_1);
        shift1_11=(LinearLayout)findViewById(R.id.shift1_11);
        canteen_facility_1=(LinearLayout)findViewById(R.id.canteen_facility_1);
        canteen_facility_11=(LinearLayout)findViewById(R.id.canteen_facility_11);
        int buttonWidth = width/4;
        int buttonHeight = height/20;
        allitemList=(ListView)findViewById(R.id.allitem);

        explist=(LinearLayout)findViewById(R.id.explist);
        catholder=(RelativeLayout)findViewById(R.id.categoryfilterholder);
       // SearchButton.setLayoutParams(new RelativeLayout.LayoutParams(buttonWidth, buttonHeight));
      //  CompareButton.setLayoutParams(new RelativeLayout.LayoutParams(buttonWidth, buttonHeight));
        final  LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) MapButton.getLayoutParams();
        params.weight = 1;
        params.width=buttonWidth;
        double d=buttonWidth*0.56;
        double large=buttonWidth*0.69;
        final int larg=(int)Math.round(large);
        final int smal=(int)Math.round(d);
        params.height=larg;

        MapButton.setLayoutParams(params);
        final LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) SearchButton.getLayoutParams();
        params2.weight = 1;
        params2.width=buttonWidth;
        params2.height=(int)Math.round(d);
        SearchButton.setLayoutParams(params2);
        final   LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) ListButton.getLayoutParams();
        params3.weight = 1;
        params3.width=buttonWidth;
        params3.height=(int)Math.round(d);
        ListButton.setLayoutParams(params3);
        ListButton.getHeight();
        final LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) CompareButton.getLayoutParams();
        params4.weight = 1;
        params4.width=buttonWidth;
        params4.height=(int)Math.round(d);
        CompareButton.setLayoutParams(params4);
       // SearchButton.setMinimumWidth(buttonWidth);
        //ListButton.setLayoutParams(layoutParams);
       // SearchButton.setLayoutParams(layoutParams);
       // CompareButton.setLayoutParams(layoutParams);

        mapcalledstatus=false;
        toolbar = (Toolbar) findViewById(R.id.categorytoolbar);

        SharedPreferencesHelper.setCompareData(PlaceDetailsActivityNewLayout.this,"",0);
        Searchall=(EditText)findViewById(R.id.searchall);

        prebutton=(Button) findViewById(R.id.prebutton);
        filterholder=(RelativeLayout)findViewById(R.id.filterholder);

        header=(TextView)findViewById(R.id.textView15);
        // toolbar.setBackgroundResource(android.R.color.transparent);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        ab.setDisplayHomeAsUpEnabled(true);
        mapnother=(LinearLayout)findViewById(R.id.mapnothers);
        mapholderr=(RelativeLayout)findViewById(R.id.mapholder);
        listholder=(LinearLayout)findViewById(R.id.listholder);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

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
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // svSubCategoryListHolder=(HorizontalScrollView)findViewById(R.id.svSubCategoryListHolder);

        HorizontalScrollView svSubCategoryListHolder = new HorizontalScrollView(this);
        svholder=(LinearLayout)findViewById(R.id.llCategoryListHolderback);
        svsholder=(LinearLayout)findViewById(R.id.llSubCategoryListHolderback);
        svholder.setVisibility(View.GONE);
        svsholder.setVisibility(View.GONE);
    sv= (ScrollView)findViewById(R.id.svCategoryListHolder);
        svs= (ScrollView)findViewById(R.id.svSubCategoryListHolder);
        sv.setVisibility(View.GONE);
        svs.setVisibility(View.GONE);
        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
//        wholeLayout=(RelativeLayout)findViewById(R.id.wholeLayout);


        final Intent intent;
        intent = getIntent();
        if (null != intent)
        {
            locationNameId = intent.getIntExtra(AppConstants.KEY_PLACE,0);
            if(locationNameId== AppConstants.PLACE_BAUNIABADH)
            {
                setPlaceChoice("Mirpur-11");
                locationName = AppConstants.BAUNIABADH;
                listData.add(AppConstants.BAUNIABADH);
                listData.add(AppConstants.PARIS_ROAD);
                setLocationNameEng("Mirpur-11");
            }
            else if(locationNameId== AppConstants.PLACE_PARIS_ROAD)
            {
                setPlaceChoice("Mirpur-10");
                locationName = AppConstants.PARIS_ROAD;
                listData.add(AppConstants.PARIS_ROAD);
                listData.add(AppConstants.BAUNIABADH);
                setLocationNameEng("Mirpur-10");
            }
        }



        health_name2=(TextView)findViewById(R.id.health_name2);
        opening_time2=(TextView)findViewById(R.id.opening_time2);
        language_spoken2=(TextView)findViewById(R.id.language_spoken2);
        service_type2=(TextView)findViewById(R.id.service_type2);
        specialist_available2=(TextView)findViewById(R.id.specialist_available2);
        clean_facilities2=(TextView)findViewById(R.id.clean_facilities2);
        privacy2=(TextView)findViewById(R.id.privacy2);
        quality_equipment2=(TextView)findViewById(R.id.quality_equipment2);
        cost2=(TextView)findViewById(R.id.cost2);
        compare_layout=(LinearLayout)findViewById(R.id.compare_layout);
        compare_layoutedu=(RelativeLayout)findViewById(R.id.compare_layoutedu);
        health_name3=(TextView)findViewById(R.id.health_name3);
        opening_time3=(TextView)findViewById(R.id.opening_time3);
        language_spoken3=(TextView)findViewById(R.id.language_spoken3);
        service_type3=(TextView)findViewById(R.id.service_type3);
        specialist_available3=(TextView)findViewById(R.id.specialist_available3);
        clean_facilities3=(TextView)findViewById(R.id.clean_facilities3);
        privacy3=(TextView)findViewById(R.id.privacy3);
        quality_equipment3=(TextView)findViewById(R.id.quality_equipment3);
        cost3=(TextView)findViewById(R.id.cost3);
        opening_time1=(TextView)findViewById(R.id.opening_time1);
        language_spoken1=(TextView)findViewById(R.id.language_spoken1);
        service_type1=(TextView)findViewById(R.id.service_type1);
        specialist_available1=(TextView)findViewById(R.id.specialist_available1);
        clean_facilities1=(TextView)findViewById(R.id.clean_facilities1);
        privacy1=(TextView)findViewById(R.id.privacy1);
        quality_equipment1=(TextView)findViewById(R.id.quality_equipment1);
        cost1=(TextView)findViewById(R.id.cost1);



        edu_name_ban=(TextView)findViewById(R.id.edu_name_ban3);
        edu_name_ban22=(TextView)findViewById(R.id.edu_name_ban22);
        edtype=(TextView)findViewById(R.id.eduType2);
        hostel_facility=(TextView)findViewById(R.id.hostel_facility2);
        transport_facility=(TextView)findViewById(R.id.transport_facility2);
        playground=(TextView)findViewById(R.id.playground2);
        total_students=(TextView)findViewById(R.id.ttl_students);
        total_classes=(TextView)findViewById(R.id.total_classes2);
        total_teachers=(TextView)findViewById(R.id.total_teachers2);
        course_provided=(TextView)findViewById(R.id.course_provided2);
        shift=(TextView)findViewById(R.id.shift2);
        canteen_facility=(TextView)findViewById(R.id.canteen_facility2);
        compare_layout=(LinearLayout)findViewById(R.id.compare_layout);
        compare_layoutedu=(RelativeLayout)findViewById(R.id.compare_layoutedu);
        edu_name_ban1=(TextView)findViewById(R.id.edu_name_ban3);
        edtype1=(TextView)findViewById(R.id.eduType3);
        hostel_facility1=(TextView)findViewById(R.id.hostel_facility3);
        transport_facility1=(TextView)findViewById(R.id.transport_facility3);
        playground1=(TextView)findViewById(R.id.playground3);
        total_students1=(TextView)findViewById(R.id.total_students3);
        total_classes1=(TextView)findViewById(R.id.total_classes3);
        total_teachers1=(TextView)findViewById(R.id.total_teachers3);
        course_provided1=(TextView)findViewById(R.id.course_provided3);
        shift1=(TextView)findViewById(R.id.shift3);
        canteen_facility1=(TextView)findViewById(R.id.canteen_facility3);

        //    edu_name_ban2=(TextView)findViewById(R.id.edu_name_ban1);
        edtype2=(TextView)findViewById(R.id.eduType1);
        hostel_facility2=(TextView)findViewById(R.id.hostel_facility1);
        transport_facility2=(TextView)findViewById(R.id.transport_facility1);
        playground2=(TextView)findViewById(R.id.playground1);
        total_students2=(TextView)findViewById(R.id.total_students1);
        total_classes2=(TextView)findViewById(R.id.total_classes1);
        total_teachers2=(TextView)findViewById(R.id.total_teachers1);
        course_provided2=(TextView)findViewById(R.id.course_provided1);
        shift2=(TextView)findViewById(R.id.shift1);
        canteen_facility2=(TextView)findViewById(R.id.canteen_facility1);
        //categoryHeader = (TextView) findViewById(R.id.tv_cat_name);
        //categoryHeaderIcon = (ImageView) findViewById(R.id.ivHeadCatIconSubCatList);
        //placeDetailsLayout = (FrameLayout) findViewById(R.id.place_details_layout);
        ///this code will change the background of the layout for two places.


        // itemList = (ListView)findViewById(R.id.listViewSearch);
        //subCatItemListHeader = (TextView) findViewById(R.id.tv_sub_cat_item_list_head);

        //subCatItemList = (ExpandableListView) findViewById(R.id.listView);
        map = (FrameLayout) findViewById(R.id.map_fragment);
        map.setVisibility(View.VISIBLE);
        callMapFragment(locationNameId);
        //showsearch=(RelativeLayout)findViewById(R.id.show);
        // insSubCat = (TextView) findViewById(R.id.tvInstructionSubCat);
        //seeMap = (Button) findViewById(R.id.btn_see_map);
        // showSubCatListItem = (Button) findViewById(R.id.btn_show_sub_cat_list_item);
        VIEW_WIDTH = AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC;
        isCatExpandedOnce = false;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.97); // 80% of the view width

        fleft=(LinearLayout)findViewById(R.id.linearLayout1);
        fright=(LinearLayout)findViewById(R.id.linearLayout2) ;

        //  svCatList = (ScrollView) findViewById(R.id.svCategoryListHolder);
        llCatListHolder = (LinearLayout) findViewById(R.id.llCategoryListHolder);
        llSubCatListHolder = (LinearLayout) findViewById(R.id.llSubCatListHolder);
        llCatListHolder.setVisibility(View.GONE);
        //rlSubCatHolder.setVisibility(View.VISIBLE);
        llSubCatListHolder.setVisibility(View.GONE);
        ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();
        ViewGroup.LayoutParams lp_sub= llSubCatListHolder.getLayoutParams();
        final int s=lp.width = (int) (VIEW_WIDTH);
        lp_sub.width=s;
        FrameLayout.LayoutParams caTsList = (FrameLayout.LayoutParams) llCatListHolder.getLayoutParams();


        final ViewGroup.LayoutParams exlist= explist.getLayoutParams();
        final RelativeLayout.LayoutParams expnlist = (RelativeLayout.LayoutParams) explist.getLayoutParams();

        expnlist.setMargins((s*9)/10,40,5,40);

        lp.height=100;

        if(height<1000)
            caTsList.setMargins(0, 60, 0, 0);
        else
            caTsList.setMargins(0, 10, 0, 0);




        /**
         * constructing category list
         **/
        CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivityNewLayout.this);
        categoryList=categoryTable.getAllCategories();
        categoryList2=categoryList;
        for(int i=0;i<categoryList2.size();i++)
        {

            int cid=categoryList2.get(i).getId();

            if (cid==1)
            {
                categoryList2.get(i).setId(2);
            }
            else   if (cid==53)
            {
               categoryList2.get(i).setId(7);
            }
          else   if (cid==5)
            {
                categoryList2.get(i).setId(1);
            }
            else   if (cid==14)
            {
                categoryList2.get(i).setId(3);
            }
            else  if (cid==29)
            {
                categoryList2.get(i).setId(5);
            }
            else  if (cid==11)
            {
                categoryList2.get(i).setId(6);
            }
            else  if (cid==33)
            {
                categoryList2.get(i).setId(4);
            }



        }
        categoryList2.size();
        constructCategoryList(categoryList2);
        //rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        //rlSubCatHolder.setVisibility(View.INVISIBLE);


        // callMapFragment();
        spItems = (KolorobSpinner) findViewById(R.id.areaitems);
        spItems.setVisibility(View.VISIBLE);
        arrayAdapter = new ArrayAdapter(PlaceDetailsActivityNewLayout.this,R.layout.area_row_spinner, listData);
        arrayAdapter.setDropDownViewResource(R.layout.area_row_spinners_dropdown);
        spItems.setAdapter(arrayAdapter);


        spItems.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String imc_met=spItems.getSelectedItem().toString();
                setPlaceChoice(imc_met);
                if(imc_met==AppConstants.BAUNIABADH) {
                    locationNameId = AppConstants.PLACE_BAUNIABADH;
                    callMapFragment(locationNameId);
                }
                else {locationNameId=AppConstants.PLACE_PARIS_ROAD;
                    callMapFragment(locationNameId);}
                if(mapcalledstatus){

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        Populateholder();
        callMapFragment(locationNameId);
        MapButton.setBackgroundResource(R.drawable.map_selected);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchClicked=true;
                MapClicked=false;
                ListClicked=false;
                CompareClicked=false;

                populateSearch();
                if (CompareClicked==false||MapClicked==false||ListClicked==false)
                {
                    SearchButton.setImageResource(0);
                    MapButton.setImageResource(0);
                    CompareButton.setImageResource(0);
                    ListButton.setImageResource(0);
                    params2.height=larg;
                    SearchButton.setLayoutParams(params2);
                    params.height=smal;
                    MapButton.setLayoutParams(params);
                    params3.height=smal;
                    ListButton.setLayoutParams(params3);
                    params4.height=smal;
                    CompareButton.setLayoutParams(params4);
                    SearchButton.setBackgroundResource(R.drawable.search_selected);
                    ListButton.setBackgroundResource(R.drawable.list);
                    MapButton.setBackgroundResource(R.drawable.map);
                    CompareButton.setBackgroundResource(R.drawable.compare);
                    map.setVisibility(View.GONE);
                    svs.setVisibility(View.GONE);
                    svholder.setVisibility(View.GONE);
                    svsholder.setVisibility(View.GONE);
                    sv.setVisibility(View.GONE);
                    explist.setVisibility(View.GONE);
                    toggleButton.setVisibility(View.VISIBLE);
                    compare_layout.setVisibility(View.GONE);
                    compare_layoutedu.setVisibility(View.GONE);
                    searchviewholder.setVisibility(View.VISIBLE);
                }
                if(educlicked==false||helclicked==false||entclicked==false||legclicked==false||finclicked==false)
                {

                    filterholder.setVisibility(View.GONE);
                }
                else filterholder.setVisibility(View.VISIBLE);
                toggleButton.setVisibility(View.VISIBLE);

            }
        });
        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchClicked=false;
                MapClicked=true;
                ListClicked=false;
                CompareClicked=false;
                callMapFragment(locationNameId);;
                if(educlicked==true||helclicked==true||entclicked==true||legclicked==true||finclicked==true)
                {

                    svs.setVisibility(View.VISIBLE);
                    svsholder.setVisibility(View.VISIBLE);
                    llSubCatListHolder.setVisibility(View.VISIBLE);
                }
                if (CompareClicked==false||SearchClicked==false||ListClicked==false)
                {
                    SearchButton.setImageResource(0);
                    MapButton.setImageResource(0);
                    CompareButton.setImageResource(0);
                    ListButton.setImageResource(0);
                    params.height=larg;
                    MapButton.setLayoutParams(params);

                    params2.height=smal;
                    SearchButton.setLayoutParams(params2);
                    params3.height=smal;
                    ListButton.setLayoutParams(params3);
                    params4.height=smal;
                    CompareButton.setLayoutParams(params4);
                    SearchButton.setBackgroundResource(R.drawable.search);
                    ListButton.setBackgroundResource(R.drawable.list);
                    MapButton.setBackgroundResource(R.drawable.map_selected);
                    CompareButton.setBackgroundResource(R.drawable.compare);
                    subCatItemList.setVisibility(View.GONE);

                    explist.setVisibility(View.GONE);
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


                SearchClicked=false;
                MapClicked=false;
                ListClicked=true;
                CompareClicked=false;
                searchviewholder.setVisibility(View.GONE);
                llCatListHolder.setVisibility(View.VISIBLE);
                if (MapClicked == false || SearchClicked == false || CompareClicked == false) {
                    SearchButton.setImageResource(0);
                    MapButton.setImageResource(0);
                    CompareButton.setImageResource(0);
                    ListButton.setImageResource(0);
                    params3.height=larg;
                    ListButton.setLayoutParams(params3);

                    params2.height=smal;
                    SearchButton.setLayoutParams(params2);
                    params.height=smal;
                    MapButton.setLayoutParams(params);
                    params4.height=smal;
                    CompareButton.setLayoutParams(params4);
                    SearchButton.setBackgroundResource(R.drawable.search);
                    ListButton.setBackgroundResource(R.drawable.list_selected);
                    MapButton.setBackgroundResource(R.drawable.map);
                    CompareButton.setBackgroundResource(R.drawable.compare);
                    map.setVisibility(View.GONE);
                }
                    subCatItemList.setVisibility(View.VISIBLE);
                    explist.setVisibility(View.VISIBLE);
                    searchviewholder.setVisibility(View.GONE);
                    compare_layout.setVisibility(View.GONE);
                compare_layoutedu.setVisibility(View.GONE);


                svs.setVisibility(View.VISIBLE);
                svholder.setVisibility(View.VISIBLE);
                svsholder.setVisibility(View.GONE);
                sv.setVisibility(View.VISIBLE);
                llSubCatListHolder.setVisibility(View.GONE);
                subCatItemList.setVisibility(View.VISIBLE);
                explist.setVisibility(View.VISIBLE);
                //  wholeLayout.setBackgroundDrawable( getResources().getDrawable(R.drawable.splash) );

                setShowList(1);
                toolbar.setVisibility(View.VISIBLE);
                toggleButton.setVisibility(View.VISIBLE);
                list_expand = true;
                //listOrMapDisplayText.setText("ম্যাপ দেখতে চাইলে এখানে চাপ দিন");

                if(currentCategoryID<1)
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

            }

        });
        CompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentCategoryID==1||currentCategoryID==2)
                {
                    SearchClicked=false;
                    MapClicked=false;
                    ListClicked=false;
                    CompareClicked=true;


                    if(currentCategoryID==1&&SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this)==0)
                    {
                        AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                                "আপনি কোন সেবা নির্বাচিত করেননি তুলনা করার জন্য");
                    }
                   else if(currentCategoryID==2&&SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this)==0)
                {
                    AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                            "আপনি কোন সেবা নির্বাচিত করেননি তুলনা করার জন্য");
                }
                    else if(currentCategoryID==1&&SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this)==1)
                    {
                        AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                                "আপনি একটি সেবা নির্বাচিত করেছেন। তুলনা করার জন্য দুটি সেবা নির্বাচন করুন");
                    }
                    else if(currentCategoryID==2&&SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this)==1)
                    {
                        AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                                "আপনি একটি সেবা নির্বাচিত করেছেন। তুলনা করার জন্য দুটি সেবা নির্বাচন করুন");
                    }
                    else {




                        if(MapClicked==false||SearchClicked==false||ListClicked==false)
                        {

                            params4.height=larg;
                            CompareButton.setLayoutParams(params4);

                            params2.height=smal;
                            SearchButton.setLayoutParams(params2);
                            params.height=smal;
                            MapButton.setLayoutParams(params);
                            params.height=smal;
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
                        ListButton.setBackgroundResource(R.drawable.list);
                        MapButton.setBackgroundResource(R.drawable.map);
                        CompareButton.setBackgroundResource(R.drawable.compare_selected);
                        map.setVisibility(View.GONE);
                        llCatListHolder.setVisibility(View.GONE);
                        subCatItemList.setVisibility(View.GONE);
                        explist.setVisibility(View.GONE);
                        searchviewholder.setVisibility(View.GONE);
                        svs.setVisibility(View.GONE);
                        sv.setVisibility(View.GONE);
                        svholder.setVisibility(View.GONE);
                        svsholder.setVisibility(View.GONE);
                        llSubCatListHolder.setVisibility(View.GONE);
                        compareTool();
                    }
                }

                else
                    AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                            "বর্তমান ক্যাটাগরির জন্য তুলনা সম্ভব নয়");



            }
        });

        toggleButton=(ToggleButton)findViewById(R.id.toggle);
        toggleButton.setVisibility(View.VISIBLE);
        toggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(toggleButton.isChecked()){

                   if(ListClicked.equals(true))
                   {

                       svsholder.setVisibility(View.VISIBLE);
                   }
                    sv.setVisibility(View.VISIBLE);
                    svsholder.setVisibility(View.GONE);
                    svholder.setVisibility(View.VISIBLE);
                    llCatListHolder.setVisibility(View.VISIBLE);
                    if(educlicked==true||helclicked==true||entclicked==true||legclicked==true||finclicked==true)
                    {
                        svsholder.setVisibility(View.VISIBLE);
                        svs.setVisibility(View.VISIBLE);
                        llSubCatListHolder.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    sv.setVisibility(View.GONE);
                    svs.setVisibility(View.GONE);
                    svholder.setVisibility(View.GONE);
                    svsholder.setVisibility(View.GONE);
                    llCatListHolder.setVisibility(View.GONE);
                    llSubCatListHolder.setVisibility(View.GONE);
                }

                //Button is OFF
                // Do Something
            }
        });
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(PlaceDetailsActivityNewLayout.this, R.anim.twin);
        toggleButton.startAnimation(myFadeInAnimation);
        toggleButton.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {


           toggleButton.clearAnimation();

            return false;
        }
    });
    }

    public void compareTool()
    {
       // compare_layout.setBackgroundColor(Color.parseColor("#F7931E"));

        if(currentCategoryID==1)
        { //compare_layout.setBackgroundColor(Color.parseColor("#2F7281"));
            comapreData = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);

            String delims = "[,]";
            String[] tokens = comapreData.split(delims);

            firstData=tokens[0];
            SecondData=tokens[1];
            compare_layout.setVisibility(View.GONE);
            compare_layoutedu.setVisibility(View.VISIBLE);
            compare_layoutedu.setBackgroundColor(Color.parseColor("#2F7281"));
                compareEducation();}

        else {
            compare_layout.setVisibility(View.VISIBLE);

            comapreData = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
            int size=comapreData.length();
            for(int i=0;i<size;i++)
            {

                if(checker==1)
                {
                    SecondData=SecondData+comapreData.charAt(i);
                }
                else  if(comapreData.charAt(i)==' ')
                {
                    checker=1;
                }
                else
                    firstData=firstData+comapreData.charAt(i);
            }
            compareHealth();
        }
    }


    public void compareHealth() {
        compare_layout.setVisibility(View.VISIBLE);
        compare_layoutedu.setVisibility(View.GONE);

        healthServiceProviderTableNew = new HealthServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        firstDataSetHealth = healthServiceProviderTableNew.getHealthData(firstData);
        secondDataSetHealth = healthServiceProviderTableNew.getHealthData(SecondData);


        opening_time1.setText("খোলার সময়");
        language_spoken1.setText("প্রচলিত ভাষা");
        service_type1.setText("সেবার ধরন");
        specialist_available1.setText("বিশেষজ্ঞের ধরন");
        clean_facilities1.setText("ফার্মেসি সুবিধা");
        privacy1.setText("গোপনীয়তা");
        quality_equipment1.setText("ভাল সুবিধা এবং যন্ত্রপাতি");
        cost1.setText("সেবার খরচ");
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

        if (!healthSpecialistItemDetailses2.equals("")){
            for (HealthSpecialistItemDetails healthSpecialistItemDetails : healthSpecialistItemDetailses2) {
                secondSpecialistItem = secondSpecialistItem + healthSpecialistItemDetails.getSpecialisttype() + ", ";
            }
        }

        String healthService="";
        String health_service_data1="";

        for (HealthServiceProviderItemNew healthServiceProviderItemNew: firstDataSetHealth)
        {
            healthService=healthServiceProviderItemNew.getFamily_privacy();
            if(!healthService.equals(""))
            {
                for (int i=0;i<healthService.length();i++)
                {
                    if(healthService.charAt(i)=='1')
                    {
                        health_service_data1=health_service_data1+"Emergency Service,";
                    }
                    else if(healthService.charAt(i)=='2')
                    {
                        health_service_data1=health_service_data1+" Ambulance Service,";
                    }
                    else
                        health_service_data1=health_service_data1+" Maternity Service";

                }
            }

            if(healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("")||healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("null")||healthServiceProviderItemNew.getNode_bn()==null)
                health_name3.setText("X");
            else
                health_name3.setText(healthServiceProviderItemNew.getNode_bn());

            if(healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("")||healthServiceProviderItemNew.getOpening_time().equalsIgnoreCase("null")||healthServiceProviderItemNew.getOpening_time()==null)
                opening_time3.setText("X");
            else
                opening_time3.setText(healthServiceProviderItemNew.getOpening_time());

            if(healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("")||healthServiceProviderItemNew.getSpoken_lang().equalsIgnoreCase("null")||healthServiceProviderItemNew.getSpoken_lang()==null)
                language_spoken3.setText("X");
            else
                language_spoken3.setText(healthServiceProviderItemNew.getSpoken_lang());

            if(!health_service_data1.equals(""))
                service_type3.setText(health_service_data1);
            else
                service_type3.setText("X");
            if(firstSpecialistItem==null)
                specialist_available3.setText("X");
            else
                specialist_available3.setText(firstSpecialistItem);

            if(healthServiceProviderItemNew.getPharmacy_speciality()==""||healthServiceProviderItemNew.getPharmacy_speciality()=="null")
                clean_facilities3.setText("X");
            else
                clean_facilities3.setText(firstSpecialistItem);

            if(healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("")||String.valueOf(healthServiceProviderItemNew.getNode_facebook()).equalsIgnoreCase("null")||String.valueOf(healthServiceProviderItemNew.getNode_facebook())==null)
                privacy3.setText("X");
            else
                privacy3.setText(String.valueOf(healthServiceProviderItemNew.getNode_facebook()));

            if(healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("")||String.valueOf(healthServiceProviderItemNew.getPharmacy_privacy()).equalsIgnoreCase("null")||String.valueOf(healthServiceProviderItemNew.getPharmacy_privacy())==null)
                quality_equipment3.setText("X");
            else
                quality_equipment3.setText(String.valueOf(healthServiceProviderItemNew.getPharmacy_privacy()));

            if(healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("")||healthServiceProviderItemNew.getQuality_equipments().equalsIgnoreCase("null") || healthServiceProviderItemNew.getQuality_equipments()==null )
                cost3.setText("X");
            else
                cost3.setText(healthServiceProviderItemNew.getQuality_equipments());

        }


        String healthService1="";
        String health_service_data2="";
        for (HealthServiceProviderItemNew healthServiceProviderItemNew: secondDataSetHealth)
        {
            healthService1=healthServiceProviderItemNew.getFamily_privacy();
            if(!healthService.equals(""))
            {
                for (int i=0;i<healthService1.length();i++)
                {
                    if(healthService1.charAt(i)=='1')
                    {
                        health_service_data2=health_service_data1+"Emergency Service, ";
                    }
                    else if(healthService1.charAt(i)=='2')
                    {
                        health_service_data2=health_service_data1+" Ambulance Service, ";
                    }
                    else
                        health_service_data2=health_service_data1+" Maternity Service";

                }
            }
            if(healthServiceProviderItemNew.getNode_bn().equalsIgnoreCase("null")||healthServiceProviderItemNew.getNode_bn()==null)
                health_name2.setText("X");
            else
                health_name2.setText(healthServiceProviderItemNew.getNode_bn());
            if(healthServiceProviderItemNew.getOpening_time().equalsIgnoreCase("null")|| healthServiceProviderItemNew.getOpening_time()==null)
                opening_time2.setText("X");
            else
                opening_time2.setText(healthServiceProviderItemNew.getOpening_time());

            if(healthServiceProviderItemNew.getSpoken_lang().equalsIgnoreCase("null")||healthServiceProviderItemNew.getSpoken_lang()==null)
                language_spoken2.setText("X");
            else
                language_spoken2.setText(healthServiceProviderItemNew.getSpoken_lang());

            if(!health_service_data2.equals(""))
                service_type2.setText(health_service_data2);
            else
                service_type2.setText("X");

            if(healthServiceProviderItemNew.getPharmacy_speciality().equalsIgnoreCase("null")||healthServiceProviderItemNew.getPharmacy_speciality()==null)
                clean_facilities2.setText("X");
            else
                clean_facilities2.setText(healthServiceProviderItemNew.getPharmacy_speciality());

            if(firstSpecialistItem==null)
                specialist_available2.setText("X");
            else
                specialist_available2.setText(firstSpecialistItem);

            if(String.valueOf(healthServiceProviderItemNew.getNode_facebook()).equalsIgnoreCase("null")|| String.valueOf(healthServiceProviderItemNew.getNode_facebook())==null)
                privacy2.setText("X");
            else
                privacy2.setText(String.valueOf(healthServiceProviderItemNew.getNode_facebook()));

            if(String.valueOf(healthServiceProviderItemNew.getPharmacy_privacy()).equalsIgnoreCase("null") || String.valueOf(healthServiceProviderItemNew.getPharmacy_privacy())==null)
                quality_equipment2.setText("X");
            else
                quality_equipment2.setText(String.valueOf(healthServiceProviderItemNew.getPharmacy_privacy()));

            if(healthServiceProviderItemNew.getQuality_equipments()==null || healthServiceProviderItemNew.getQuality_equipments().equalsIgnoreCase("null"))
                cost2.setText("X");
            else
                cost2.setText(healthServiceProviderItemNew.getQuality_equipments());




        }
        SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this,"",0);
    }


    public void compareEducation()
    {
        educationServiceProviderTable=new EducationServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        educationNewTable = new EducationNewTable(PlaceDetailsActivityNewLayout.this);

        firstDataSet=educationNewTable.getEducationData(String.valueOf(firstData));
        secondDataSet=educationNewTable.getEducationData(String.valueOf(SecondData));


        for (EducationNewItem educationNewItem: firstDataSet)
        {
            if(educationNewItem.getNamebn()==null || educationNewItem.getNamebn().equalsIgnoreCase("null"))
                edu_name_ban22.setText("পাওয়া যায় নি");
            else
            edu_name_ban22.setText(educationNewItem.getNamebn());

            if(educationNewItem.getEdtype()==null || educationNewItem.getEdtype().equalsIgnoreCase("null"))
                edtype.setText("পাওয়া যায় নি");
            else
            edtype.setText(educationNewItem.getEdtype());

            if(educationNewItem.getFloor()==null || educationNewItem.getFloor().equalsIgnoreCase("null"))
                hostel_facility.setText("পাওয়া যায় নি"); //center type
            else
            hostel_facility.setText(educationNewItem.getFloor());

            if(educationNewItem.getLandmark()==null || educationNewItem.getLandmark().equalsIgnoreCase("null"))
                transport_facility.setText("পাওয়া যায় নি");
            else
            transport_facility.setText(educationNewItem.getLandmark());//done

            if(educationNewItem.getAveragestudent()==null || educationNewItem.getAveragestudent().equalsIgnoreCase("null"))
                playground.setText("পাওয়া যায় নি");
            else
            playground.setText(educationNewItem.getAveragestudent()); //done

            if(educationNewItem.getStudentno()==null || educationNewItem.getStudentno().equalsIgnoreCase("null"))
                total_students.setText("পাওয়া যায় নি");
            else
            total_students.setText(String.valueOf(educationNewItem.getStudentno()));

            if(educationNewItem.getClassno()==null || educationNewItem.getClassno().equalsIgnoreCase("null"))
                total_classes.setText("পাওয়া যায় নি");
            else
            total_classes.setText(String.valueOf(educationNewItem.getClassno()));

            if(educationNewItem.getTeachersno()==null || educationNewItem.getTeachersno().equalsIgnoreCase("null"))
                total_teachers.setText("পাওয়া যায় নি");
            else
            total_teachers.setText(String.valueOf(educationNewItem.getTeachersno()));

            if(educationNewItem.getWatercondition()==null || educationNewItem.getWatercondition().equalsIgnoreCase("null"))
                course_provided.setText("পাওয়া যায় নি");
            else
            course_provided.setText(educationNewItem.getWatercondition());

            if(educationNewItem.getShift()==null || educationNewItem.getShift().equalsIgnoreCase("null"))
                shift.setText("পাওয়া যায় নি");
            else
            shift.setText(educationNewItem.getShift());

            if(educationNewItem.getWatersource()==null || educationNewItem.getWatersource().equalsIgnoreCase("null"))
                canteen_facility.setText("পাওয়া যায় নি");
            else
            canteen_facility.setText(educationNewItem.getWatersource());
        }
        for (EducationNewItem educationNewItem: secondDataSet)
        {

            if(educationNewItem.getNamebn()==null || educationNewItem.getNamebn().equalsIgnoreCase("null"))
                edu_name_ban.setText("পাওয়া যায় নি");
            else
            edu_name_ban.setText(educationNewItem.getNamebn());

            if(educationNewItem.getEdtype()==null || educationNewItem.getEdtype().equalsIgnoreCase("null"))
                edtype1.setText("পাওয়া যায় নি");
            else
            edtype1.setText(educationNewItem.getEdtype());

            if(educationNewItem.getFloor()==null || educationNewItem.getFloor().equalsIgnoreCase("null"))
                hostel_facility1.setText("পাওয়া যায় নি");
            else
            hostel_facility1.setText(educationNewItem.getFloor());

            if(educationNewItem.getLandmark()==null || educationNewItem.getLandmark().equalsIgnoreCase("null"))
                transport_facility1.setText("পাওয়া যায় নি");
            else
            transport_facility1.setText(educationNewItem.getLandmark());

            if(educationNewItem.getAveragestudent()==null || educationNewItem.getAveragestudent().equalsIgnoreCase("null"))
                playground1.setText("পাওয়া যায় নি");
            else
            playground1.setText(educationNewItem.getAveragestudent());

            if(educationNewItem.getStudentno()==null || educationNewItem.getStudentno().equalsIgnoreCase("null"))
                total_students1.setText("পাওয়া যায় নি");
            else
            total_students1.setText(String.valueOf(educationNewItem.getStudentno()));

            if(educationNewItem.getClassno()==null || educationNewItem.getClassno().equalsIgnoreCase("null"))
                total_classes1.setText("পাওয়া যায় নি");
            else
            total_classes1.setText(String.valueOf(educationNewItem.getClassno()));

            if(educationNewItem.getTeachersno()==null || educationNewItem.getTeachersno().equalsIgnoreCase("null"))
                total_teachers1.setText("পাওয়া যায় নি");
            else
            total_teachers1.setText(String.valueOf(educationNewItem.getTeachersno()));

            if(educationNewItem.getWatercondition()==null || educationNewItem.getWatercondition().equalsIgnoreCase("null"))
                course_provided1.setText("পাওয়া যায় নি");
            else
            course_provided1.setText(educationNewItem.getWatercondition());

            if(educationNewItem.getShift()==null || educationNewItem.getShift().equalsIgnoreCase("null"))
                shift1.setText("পাওয়া যায় নি");
            else
            shift1.setText(educationNewItem.getShift());

            if(educationNewItem.getWatersource()==null || educationNewItem.getWatersource().equalsIgnoreCase("null"))
                canteen_facility1.setText("পাওয়া যায় নি");
            else
            canteen_facility1.setText(educationNewItem.getWatersource());
        }

        SharedPreferencesHelper.setCompareData(PlaceDetailsActivityNewLayout.this,"",0);
    }

    public void populateSearch()
    {

        ImageButton more=(ImageButton)findViewById(R.id.morebutton);
        catholder.setVisibility(View.VISIBLE);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterclicked=true;

                fholder.setVisibility(View.VISIBLE);
                populatefilterwords(getFilcatid());

            }
        });

    }





    public void createData(int cat_id, String head,String placeChoice) {
        switch (cat_id) {
            case AppConstants.EDUCATION:
                SubCategoryTableNew subCategoryTable = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
                //currentCategoryID = 5;
                EducationNewTable educationServiceProviderTable = new EducationNewTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> print = null;
                groups.removeAllElements();

                subCatItemList.setChildDivider(getResources().getDrawable(R.color.education_color));
               // subCatItemList.setChildDivider(R.color.black);

                print = subCategoryTable.getSubnameedu(5);
                for (int j = 0; j < print.size(); j++) {
                    Group group = new Group(print.get(j));
                    printnames = null;
                  printnames = educationServiceProviderTable.Edunames(print.get(j),placeChoice);
                 //   printnames = educationServiceProviderTable.getAllSubCat();
                    for (int i = 0; i < printnames.size(); i++) {
                        group.children.add(i, printnames.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.ENTERTAINMENT:

                //SubCategoryTable subCategoryTable2 = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
                SubCategoryTableNew subCategoryTableNewEnt=new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
                subCatItemList.setChildDivider(getResources().getDrawable(R.color.entertainment_color));
                currentCategoryID = cat_id;
                EntertainmentServiceProviderTableNew entertainmentServiceProviderTableNew = new EntertainmentServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> RefEnt = null;
                groups.removeAllElements();
                RefEnt=subCategoryTableNewEnt.getSubnameedu(14);
                printnamesent=entertainmentServiceProviderTableNew.entertainmentServiceProviderItemNews();


                for (int j = 0; j < RefEnt.size(); j++) {
                    Group group = new Group(RefEnt.get(j));
                    printnamesent = null;
                    int refId=subCategoryTableNewEnt.getRefId(RefEnt.get(j));
                    printnamesent = entertainmentServiceProviderTableNew.EntNames(currentCategoryID, refId,RefEnt.get(j), placeChoice);

                    for (int i = 0; i < printnamesent.size(); i++) {
                        group.childrenent.add(i, printnamesent.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.GOVERNMENT:
                SubCategoryTableNew subCategoryTableg = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
               // currentCategoryID = 33;
                GovernmentNewTable governmentNewTable = new GovernmentNewTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> printgov = null;
                groups.removeAllElements();

                subCatItemList.setChildDivider(getResources().getDrawable(R.color.education_color));
                // subCatItemList.setChildDivider(R.color.black);

                printgov = subCategoryTableg.getSubnameedu(33);
                for (int j = 0; j < printgov.size(); j++) {
                    Group group = new Group(printgov.get(j));
                    printgovs = null;
                    printgovs = governmentNewTable.Govnames(printgov.get(j),placeChoice);
                    for (int i = 0; i < printgovs.size(); i++) {
                        group.childrengov.add(i, printgovs.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.HEALTH:

                //SubCategoryTable subCategoryTable3 = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
                SubCategoryTableNew subCategoryTableNew=new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
                String p="Diagonostic Centre";

                currentCategoryID = cat_id;
                subCatItemList.setChildDivider(getResources().getDrawable(R.color.health_color));
                HealthServiceProviderTableNew healthServiceProviderTableNew=new HealthServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);

                HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> printhea = null;
                ArrayList<String> RefHealth=null;
                ArrayList<SubCategoryItemNew> RefHealthx=null;
                groups.removeAllElements();
                RefHealth=subCategoryTableNew.getSubnameedu(1);
                ArrayList<HealthServiceProviderItemNew> healthServiceProviderItemNews2;
               // printhea = subCategoryTable3.getSubnameedu(currentCategoryID, head);

                for (int j = 0; j < RefHealth.size(); j++) {
                    Group group = new Group(RefHealth.get(j));
                    printnameshea = null;
                    int refId=subCategoryTableNew.getRefId(RefHealth.get(j));
                    ArrayList<SubCategoryItemNew>subCategoryItemNews;
                   // subCategoryItemNews=subCategoryTableNew.getAllSubCat();
                    //ealthServiceProviderItemNews2=healthServiceProviderTableNew.getAllHealthSubCategoriesInfo();

                    printnameshea = healthServiceProviderTableNew.getAllHealthSubCategoriesInfo();
                    printnameshea = healthServiceProviderTableNew.Heanames(currentCategoryID, refId, RefHealth.get(j), placeChoice);
                    for (int i = 0; i <  printnameshea .size(); i++) {
                        group.childrenhea.add(i,printnameshea .get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.FINANCIAL:

                SubCategoryTableNew subCategoryTable4 = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
               // currentCategoryID = 11;
                FinancialServiceNewTable financialServiceProviderTable = new FinancialServiceNewTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> printfin = null;

                subCatItemList.setChildDivider(getResources().getDrawable(R.color.financial_color));
                groups.removeAllElements();
                printfin= subCategoryTable4.getSubnameedu(11);
                for (int j = 0; j <  printfin.size(); j++) {
                    Group group = new Group(printfin.get(j));
                    printnamesfin = null;
                    printnamesfin= financialServiceProviderTable.Finnames(printfin.get(j),placeChoice);;
                    for (int i = 0; i < printnamesfin.size(); i++) {
                        group.childrenfin.add(i, printnamesfin.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.LEGAL:
                SubCategoryTableNew subCategoryTableNews=new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);

             //   SubCategoryTable subCategoryTable5 = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
                currentCategoryID = cat_id;
                subCatItemList.setChildDivider(getResources().getDrawable(R.color.legal_aid_color));
                LegalAidServiceProviderTableNew legalAidServiceProviderTableNew = new LegalAidServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
                //ArrayList<String> printleg = null;
                ArrayList<String> RefLegal = null;
                RefLegal=subCategoryTableNews.getSubnameedu(29);
                //("RefLegal","======"+RefLegal);

                groups.removeAllElements();
               // printleg = subCategoryTableNew.getSubnameedu(currentCategoryID, head);
                for (int j = 0; j < RefLegal.size(); j++) {
                    Group group = new Group(RefLegal.get(j));
                    int refId=subCategoryTableNews.getRefId(RefLegal.get(j));

                    printnamesleg = null;
                    printnamesleg = legalAidServiceProviderTableNew.LegalInfo(currentCategoryID, refId, RefLegal.get(j), placeChoice);
                  //  printnamesleg = legalAidServiceProviderTableNew.getAllLegalAidSubCategoriesInfosearch();

                    for (int i = 0; i < printnamesleg.size(); i++) {
                        group.childrenleg.add(i, printnamesleg.get(i));
                    }
                    groups.add(j, group);
                }
                break;


        }
    }


    public void populatefilterwords(int filcatid)
    {
        SubCategoryTableNew subCategoryTable = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        subholders.clear();
        subholders=subCategoryTable.getcatSubCategories(filcatid);

        int upto=subholders.size()/2;
        filter.clear();
        filter2.clear();
        fleft.removeAllViews();
        fright.removeAllViews();
        for (int f=0;f<subholders.size();f++)
        {
            if (f>=upto)
                filter2.add(subholders.get(f).getSubcatname());
            else
            {
                filter.add(subholders.get(f).getSubcatname());
            }
        }
        final RadioButton[] rb = new RadioButton[30];
        fgrp1 = new RadioGroup(this); //create the RadioGroup
        fgrp1.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<filter.size(); i++){
            rb[i]  = new RadioButton(this);
            fgrp1.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filter.get(i).toString());
            rb[i].setTextColor(Color.WHITE);
        }
        fgrp2 = new RadioGroup(this); //create the RadioGroup
        fgrp2.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<filter2.size(); i++){
            rb[i]  = new RadioButton(this);
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

        }  else if (id == R.id.emergency_info) {

            //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
            Intent em = new Intent(this, NewEmergency.class);
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
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        if(NavigationCalled)
        {
            NavigationCalled=false;
            toggleButton.setVisibility(View.VISIBLE);
            toggleButton.setChecked(false);

            if (currentCategoryID==1)
            {
                NavigationCalledOnce=true;
                String node=getNodefromback();
                EducationNewTable educationNewTable = new EducationNewTable(PlaceDetailsActivityNewLayout.this.con);
                nulledu2 = educationNewTable.geteduNode2(Integer.parseInt(node));
                Intent iient = new Intent(PlaceDetailsActivityNewLayout.this.con, DetailsLayoutEducation.class);
                iient.putExtra(AppConstants.KEY_DETAILS_EDU, nulledu2);
                this.startActivity(iient);

            }

        }
        else if (NavigationCalled==false)
        {
            callMapFragment(locationNameId);
        }



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
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

                clicked.clear();
                filterclicked=false;
                Headerholder.clear();
                currentCategoryID = ci.getId();

                for(int i= 0; i < llCatListHolder.getChildCount(); i++){
                    ImageView iv = (ImageView) ((ViewGroup)llCatListHolder.getChildAt(i)).getChildAt(0);

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
                        educlicked=true;
                        setFilcatid(5);
                        catstatus=true;
                        calladapter(catstatus);
                        if(ListClicked.equals(true))
                        explist.setVisibility(View.VISIBLE);

                        if(showList==1) {
/*
                            explist.setVisibility(View.VISIBLE);
                            explist.setAnimation(slideOutFromLeftAnim());
                            llSubCatListHolder.setVisibility(View.GONE);
                            subCatItemList.setVisibility(View.VISIBLE);
*/

                        }
                        else {

                            llSubCatListHolder.setVisibility(View.GONE);

                            ArrayList<EducationNewItem> educationServiceProvider;
                            educationServiceProvider = constructEducationListItem();
                            ivIcon.setImageResource(R.drawable.education_selected);
                            callMapFragmentWithEducationInfo(ci.getCatName(), 1, educationServiceProvider);


                        }


                        
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.education_selected);
                        mapcalledstatus=true;
                        llSubCatListHolder.setVisibility(View.GONE);

                        break;
                    case AppConstants.HEALTH:
                        MediaPlayer mp_h = MediaPlayer.create(getApplicationContext(), R.raw.health);
                        mp_h.start();
                        HEL.clear();
                        helclicked=true;
                        setFilcatid(1);
                        catstatus=true;
                        calladapter(catstatus);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.health_selected);
                        ArrayList<HealthServiceProviderItemNew> healthServiceProvider;
                        healthServiceProvider = constructHealthListItem(1);
                        callMapFragmentWithHealthInfo(ci.getCatName(), 2, healthServiceProvider);
                        mapcalledstatus=true;
                            llSubCatListHolder.setVisibility(View.GONE);
                        if(ListClicked.equals(true))
                            explist.setVisibility(View.VISIBLE);


                        break;

                    //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:

                        ENT.clear();
                        entclicked=true;
                        setFilcatid(14);
                        catstatus=true;
                        calladapter(catstatus);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProvider;
                        entertainmentServiceProvider = constructEntertainmentListItem(ci.getId());
                        ivIcon.setImageResource(R.drawable.entertainment_selected);
                        callMapFragmentWithEntertainmentInfo(ci.getCatName(), 3, entertainmentServiceProvider);
                        mapcalledstatus=true;
                        if(ListClicked.equals(true))
                            explist.setVisibility(View.VISIBLE);




                            llSubCatListHolder.setVisibility(View.GONE);


                        MediaPlayer mp_en = MediaPlayer.create(getApplicationContext(), R.raw.entertainment);
                        mp_en.start();





                        break;


                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:
                        MediaPlayer mp_g = MediaPlayer.create(getApplicationContext(), R.raw.government);
                        mp_g.start();
                        govclicked=true;
                        GOV.clear();
                        setFilcatid(33);
                        catstatus=true;
                        calladapter(catstatus);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.government_selected);
                        mapcalledstatus=true;
                        llSubCatListHolder.setVisibility(View.GONE);
                        if(ListClicked.equals(true))
                            explist.setVisibility(View.VISIBLE);





                        ArrayList<GovernmentNewItem> governmentNewItems;
                        governmentNewItems = constructgovListItem();
                        callMapFragmentWithGovInfo(ci.getCatName(), 4, governmentNewItems);





                        toolbar.setVisibility(View.VISIBLE);

                        break;
                    case AppConstants.LEGAL:
                        MediaPlayer mp_l = MediaPlayer.create(getApplicationContext(), R.raw.legal);
                        mp_l.start();
                        LEG.clear();
                        legclicked=true;
                        setFilcatid(29);
                        catstatus=true;
                        calladapter(catstatus);
                        populatefilterwords(getFilcatid());
                        filterholder.setVisibility(View.VISIBLE);
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.legal_selected);
                        ArrayList<LegalAidServiceProviderItemNew> legalaidServiceProvider;
                        mapcalledstatus=true;
                        legalaidServiceProvider = constructlegalaidListItem(ci.getId());
                        callMapFragmentWithLegalAidInfo(ci.getCatName(), 5, legalaidServiceProvider);
                        if(ListClicked.equals(true))
                            explist.setVisibility(View.VISIBLE);



                            llSubCatListHolder.setVisibility(View.GONE);








                        break;
                    case AppConstants.FINANCIAL:
                        MediaPlayer mp_f = MediaPlayer.create(getApplicationContext(), R.raw.finance);
                        mp_f.start();
                        FIN.clear();
                        finclicked=true;
                        setFilcatid(11);
                        catstatus=true;
                        calladapter(catstatus);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.finance_selected);
                        ArrayList<FinancialNewItem> financialNewItems;
                        financialNewItems = constructfinancialListItem();
                        callMapFragmentWithFinancialInfo(ci.getCatName(), 6, financialNewItems);
                        mapcalledstatus=true;
                        if(ListClicked.equals(true))
                            explist.setVisibility(View.VISIBLE);


                            llSubCatListHolder.setVisibility(View.GONE);






                        break;
                    case AppConstants.JOB:
                        MediaPlayer mp_j = MediaPlayer.create(getApplicationContext(), R.raw.job);
                        mp_j.start();
                        explist.setVisibility(View.GONE);

                        Intent intentJ = new Intent(PlaceDetailsActivityNewLayout.this,DisplayAllJobsActivity.class);
                        startActivity(intentJ);
                       // JJOB.clear();
                      //  jobclicked=true;
                       // setFilcatid(currentCategoryID);
                        //catstatus=true;
                        //calladapter(catstatus);
                        //filterholder.setVisibility(View.VISIBLE);
                        //populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.job_selected);
                        // mapcalledstatus=false;
                        llSubCatListHolder.setVisibility(View.GONE);
                        //   map.removeAllViews();
//                        toolbar2.setVisibility(View.GONE);
//                        listholder.setVisibility(View.GONE);
//                        toolbar.setVisibility(View.VISIBLE);
//                        final android.app.AlertDialog alertDialog2 = new android.app.AlertDialog.Builder(PlaceDetailsActivityNew.this).create();
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
                        break;



                    default:
                        break;
                }



                /**
                 * code for all categories
                 **/
                //  showSubCatListItem.setEnabled(false);
                //   showSubCatListItem.setVisibility(View.VISIBLE);
                //  subCatItemList.setVisibility(View.GONE);
                //   subCatItemListHeader.setVisibility(View.GONE);
                //   insSubCat.setVisibility(View.VISIBLE);
                // seeMap.setVisibility(View.VISIBLE);
                ArrayList<SubCategoryItem> subCatList = getSubCategoryList(ci.getId());

                // categoryHeader.setText(ci.getCatName());

                if(SearchClicked==true)
                {
                    svsholder.setVisibility(View.GONE);
                    svs.setVisibility(View.GONE);
                }
                else if(showList!=1 && SearchClicked==false)
                {

                    if (isCatExpandedOnce)
                        showAnimatedSubcategories(subCatList, 0.5, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId()); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                    else
                        showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId());
                }


                else
                {
                    if (isCatExpandedOnce)
                        showAnimatedSubcategories(subCatList, 0.5, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId()); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                    else
                        showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId());
                        categoryListBuildUp(currentCategoryID);
                }



                //AppConstants.CAT_LIST_LG_WIDTH_PERC);
            }
        });

        return v;
    }
    public void constructSubCategoryItemList(int cat_id,String header)
    {

        ArrayList<SubCategoryItem> subCategoryItems;
        subCategoryItems = constructSubCategoryListItem(cat_id,header);




        ArrayList<String> itemName = new ArrayList<String>();
        currentSubCategoryItem = subCategoryItems;
        for(SubCategoryItem si : subCategoryItems)
        {
            itemName.add(si.getSubCatName());
        }
        int i=0;


        //    subCatItemList = (ExpandableListView) findViewById(R.id.listView);

//        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
//
//        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, groups, cat_id);
//        subCatItemList.setAdapter(adapter);

        //  MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, groups, cat_id);
        //   subCatItemList.setAdapter(adapter);


    }
    private ArrayList<SubCategoryItem> constructSubCategoryListItem(int cat_id, String header)
    {
        ArrayList<SubCategoryItem> subCategoryItems;
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
        subCategoryItems=subCategoryTable.getAllSubCategoriesHeader(cat_id,header);


        return subCategoryItems;
    }


    private void constructSubCategoryList(ArrayList<SubCategoryItem> subCategoryList, double dwPercentage, int cat_id) {
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

    private void categoryListBuildUp(int currentCategoryID)
    {
        createData(currentCategoryID,"",getLocationNameEng());
        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
        ServiceListDisplayAdapter adapter = new ServiceListDisplayAdapter(this, groups, currentCategoryID);
        subCatItemList.setAdapter(adapter);

    }
    private View getSubCategoryListItemView(final SubCategoryItem si, double dwPercentage, final int cat_id)
    {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        View v;
        LayoutInflater li = LayoutInflater.from(this);


            v = li.inflate(R.layout.subcatholderlist, llSubCatListHolder, false);

        final ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconSCatList);
        tvName = (TextView) v.findViewById(R.id.tv_sub_cat_name);

            ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONSBUTTON[ subcategory++]);

            ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();

            lpIv.width = (int) (primaryIconWidth * dwPercentage);


        ivIcon.setLayoutParams(lpIv);
        tvName.setTextColor(Color.WHITE);

        tvName.setText(si.getSubCatHeaderBn());

        tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));
        va=0;
/**************************
 *
 *
 *
 *
 *
 *This OnClickListener will be called for clicking subcategory items from the top list
 *
 *
 *
 *
 * ************************/
        v.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {


                                     int index = llSubCatListHolder.indexOfChild(v);
                                     clicked.add(String.valueOf(index));
                                     for (int i = 0; i < llSubCatListHolder.getChildCount(); i++) {
                                         if (i == index ) {
                                             if (i == 0) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin1_selected);
                                                 continue;
                                             } else if (i == 1) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin2_selected);
                                                 continue;

                                             } else if (i == 2) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin3_selected);
                                                 continue;

                                             } else if (i == 3) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin4_selected);
                                                 continue;

                                             } else if (i == 4) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin5_selected);
                                                 continue;

                                             } else if (i == 5) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin6_selected);
                                                 continue;

                                             } else if (i == 6) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin7_selected);
                                                 continue;

                                             } else if (i == 7) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin8_selected);
                                                 continue;

                                             } else if (i == 8) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.pin9_selected);
                                                 continue;

                                             }

                                         } else {
                                             ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.ivIconSCatList));
                                             // TextView tv=(TextView) ((ViewGroup)llSubCatListHolder.getChildAt(i)).getChildAt(1);
                                             // new background because something has changed
                                             // check if it's not the imageView you just clicked because you don't want to change its background
                                             // tv.setText("as");


                                         }
                                     }
                                    Collections.sort(clicked);
                                     String cl = Collections.max(clicked);
                                        int cll=Integer.parseInt(cl);
                                     for (int ii = 0; ii < clicked.size(); ii++) {
                                        if(clicked.get(ii)==null)
                                            continue;
                                         else {
                                            int iit = Integer.parseInt(clicked.get(ii));

                                            if (iit == 0) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin1_selected);
                                                continue;
                                            } else if (iit == 1) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin2_selected);
                                                continue;

                                            } else if (iit == 2) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin3_selected);
                                                continue;

                                            } else if (iit == 3) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin4_selected);
                                                continue;

                                            } else if (iit == 4) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin5_selected);
                                                continue;

                                            } else if (iit == 5) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin6_selected);
                                                continue;

                                            } else if (iit == 6) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin7_selected);
                                                continue;

                                            } else if (iit== 7) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin8_selected);
                                                continue;

                                            } else if (iit == 8) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.ivIconSCatList));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.pin9_selected);
                                                continue;

                                            }

                                        }
                                     }



//llSubCatListHolder.findViewById(R.id.f).findViewById(R.id.f2).findViewById(R.id.iv_sub_cat_icon).setBackground(R.drawable.off_button);
                /*code for category*/
                /*following code will be different for each category*/
                /*category id 1 means education.
                * category id 2 means health
                * category id 3 means entertainment
                * category id 4 means government
                * category id 5 means legal
                * category id 6 means financial
                * category id 7 means job*/

                if(nextChild!=null)
                    nextChild.setBackgroundColor(Color.TRANSPARENT);

                switch (currentCategoryID) {





                    case AppConstants.EDUCATION:

                         Headerholder.add(si.getSubCatHeaderBn());

                        for(int s=0;s<Headerholder.size();s++)
                        {
                            eduItem=constructEducationListItemForHeader(cat_id, Headerholder.get(s));

                        }

                        for (int ss=0;ss<eduItem.size();ss++)
                        {
                            EDD.add(eduItem.get(ss));
                        }


                      callMapFragmentWithEducationInfo(si.getSubCatHeaderBn(), cat_id, EDD);

                        break;
                    case AppConstants.HEALTH:
                        //TODO write necessary codes for health
                        Headerholder.add(si.getSubCatHeaderBn());
                        for(int s=0;s<Headerholder.size();s++)
                        {

                            healthItem = constructHealthListItemForHeader(cat_id,Headerholder.get(s));
                        }
                        callMapFragmentWithHealthInfo(si.getSubcatHeader(), cat_id, healthItem);

                        break;
                    case AppConstants.ENTERTAINMENT:

                        Headerholder.add(si.getSubCatHeaderBn());
                        for(int s=0;s<Headerholder.size();s++)
                        {

                            entItem = constructEntertainmentListItemForHeader(cat_id, Headerholder.get(s));

                        }


                        callMapFragmentWithEntertainmentInfo(si.getSubCatHeaderBn(), cat_id, entItem);
                        break;
                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:
                        Headerholder.add(si.getSubCatHeaderBn());

                        for(int s=0;s<Headerholder.size();s++)
                        {
                            govItem=constructGovernmentListItemForHeader(cat_id, Headerholder.get(s));

                        }

                        for (int ss=0;ss<govItem.size();ss++)
                        {
                            GOV.add(govItem.get(ss));
                        }


                        callMapFragmentWithGovInfo(si.getSubCatHeaderBn(), cat_id, GOV);
                        break;
                    case AppConstants.LEGAL:
                        Headerholder.add(si.getSubCatHeaderBn());
                        for(int s=0;s<Headerholder.size();s++)
                        {
                            legalItem = constructlegalaidListItemForHeader(cat_id, Headerholder.get(s));
                        }
                        callMapFragmentWithLegalAidInfo(si.getSubcatHeader(), cat_id, legalItem);
                        break;
                    case AppConstants.FINANCIAL:
                        Headerholder.add(si.getSubCatHeaderBn());

                        for(int s=0;s<Headerholder.size();s++)
                        {
                            financialItem=constructFinancialListItemForHeader(cat_id, Headerholder.get(s));

                        }

                        for (int ss=0;ss<financialItem.size();ss++)
                        {
                            FIN.add(financialItem.get(ss));
                        }


                        callMapFragmentWithFinancialInfo(si.getSubCatHeaderBn(), cat_id, FIN);
                        break;
                    case AppConstants.JOB:
//                        map.removeAllViews();
//                        final AlertDialog alertDialog2 = new AlertDialog.Builder(PlaceDetailsActivityNewLayout.this).create();
//
//                        alertDialog2.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
//                        alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        alertDialog2.dismiss();
//                                    }
//                                });
//                        alertDialog2.getWindow().setLayout(200, 300);
//                        alertDialog2.show();
                        break;


                    default:
                        break;
                }
                /*code for all*/

                int p= getResources().getColor(R.color.subcategory_color);


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
        final RelativeLayout rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
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
                (int) (150 *
                        (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                ));

    }



    private void decCatListWidth(final double dwPerc) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Lg.i(TAG, "decCatListWidth : dwPerc = " + dwPerc);
                if (height < 1000d && dwPerc == 1)
                    return;
                else if (dwPerc < 0.99 && dpi>300)return;
                else if (dwPerc < 0.99)
                    return;
                // Decrease category-list width
                ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();
                lp.width = (int) (VIEW_WIDTH * dwPerc);
                llCatListHolder.setLayoutParams(lp);

                int csz = llCatListHolder.getChildCount();
                for (int i = 0; i < csz; i++) {
                    View v = llCatListHolder.getChildAt(i);
                    ImageView iv = (ImageView) v.findViewById(R.id.ivIconCatList);
                    ViewGroup.LayoutParams lpIv = iv.getLayoutParams();
                    lpIv.width = (int) (primaryIconWidth * dwPerc);
                    iv.setLayoutParams(lpIv);
                    /*TextView tv = (TextView) v.findViewById(R.id.tvNameCatList);
                    tv.setTextSize();*/
                }
                decCatListWidth(dwPerc - 0.05);
            }
        }, ANIM_INTERVAL);
    }

    private Animation slideInFromRightAnim() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromRight.setDuration(ANIM_INTERVAL *
                (int) (200 *
                        (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                )
        );
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    private Animation slideOutFromLeftAnim() {
        Animation outToLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.95f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outToLeft.setDuration(ANIM_INTERVAL *
                (int) (200 *
                        (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                )
        );
        outToLeft.setInterpolator(new AccelerateInterpolator());
        return outToLeft;
    }


    /*********************************************************methods for education**********************************************/

    private ArrayList<EducationNewItem> constructEducationListItem()
    {
        ArrayList<EducationNewItem> educationServiceProvider;
        EducationNewTable educationNewTable = new EducationNewTable(PlaceDetailsActivityNewLayout.this);
        educationServiceProvider = educationNewTable.getAllEducationSubCategoriesInfo();
        return educationServiceProvider;
    }

    private ArrayList<EducationNewItem> constructEducationListItemForHeader(int cat_id, String header)

    {

        Log.d("cat_id","####"+cat_id);
        Log.d("header","####"+header);
        ArrayList<EducationNewItem> educationNewItems;
        EducationNewTable educationNewTable = new EducationNewTable(PlaceDetailsActivityNewLayout.this);
        educationNewItems = educationNewTable.getAllEducationSubCategoriesInfoWithHead(header);
        return educationNewItems;
    }

    private void callMapFragmentWithEducationInfo(String item_name,int cat_id,ArrayList<EducationNewItem> educationServiceProviderItems)
    {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        //   mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);

        mapFragment.setLocationNameId(locationNameId);
        mapFragment.setEducationServiceProvider(educationServiceProviderItems);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment,mapFragment);
        fragmentTransaction.commit();
       // EDD.clear();
    }

    private void callMapFragment(int locationNameId) {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        //   mapFragment.setMapIndicatorText(item_name);
        mapFragment.setLocationNameId(locationNameId);
        if (mapcalledstatus == true) {
          if(educlicked){
              //educlicked=false;
              mapFragment.setCategoryId(1);
              ArrayList<EducationNewItem> educationServiceProviderItems;
              educationServiceProviderItems = constructEducationListItem();
             mapFragment.setEducationServiceProvider(educationServiceProviderItems);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
              fragmentTransaction.replace(R.id.map_fragment,mapFragment);
            fragmentTransaction.commit();
          }
            if(helclicked){
                //helclicked=false;
                mapFragment.setCategoryId(2);
                ArrayList<HealthServiceProviderItemNew> healthServiceProviderItems;
                healthServiceProviderItems = constructHealthListItem(2);
                mapFragment.setHealthServiceProvider(healthServiceProviderItems);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.map_fragment,mapFragment);
                fragmentTransaction.commit();
            }
            if(entclicked){
              //  entclicked=false;
                mapFragment.setCategoryId(3);
                ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProviderItems;
                entertainmentServiceProviderItems = constructEntertainmentListItem(3);
                mapFragment.setEntertainmentServiceProvider(entertainmentServiceProviderItems);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.map_fragment,mapFragment);
                fragmentTransaction.commit();
            }
            if(legclicked){
               // legclicked=false;
                mapFragment.setCategoryId(5);
                ArrayList<LegalAidServiceProviderItemNew> legalAidServiceProviderItems;
                legalAidServiceProviderItems = constructlegalaidListItem(5);
                mapFragment.setLegalaidServiceProvider(legalAidServiceProviderItems);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.map_fragment,mapFragment);
                fragmentTransaction.commit();
            }
            if(finclicked){
               // finclicked=false;
                mapFragment.setCategoryId(6);
                ArrayList<FinancialNewItem> financialNewItems;
                financialNewItems = constructfinancialListItem();
                mapFragment.setFinancialServiceProvider(financialNewItems);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.map_fragment,mapFragment);
                fragmentTransaction.commit();
            }
        } else {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.map_fragment, mapFragment);
            fragmentTransaction.commit();
        }
    }

    /***********************************************************Methods for Health*************************************************/

    private ArrayList<HealthServiceProviderItemNew> constructHealthListItem(int cat_id)
    {
        ArrayList<HealthServiceProviderItemNew> healthServiceProvider;
        HealthServiceProviderTableNew healthServiceProviderTable = new HealthServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        healthServiceProvider = healthServiceProviderTable.getAllHealthSubCategoriesInfo();
        return healthServiceProvider;
    }

    private void callMapFragmentWithHealthInfo(String item_name,int cat_id,ArrayList<HealthServiceProviderItemNew> healthServiceProviderItems)
    {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        // mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);

        mapFragment.setLocationNameId(locationNameId);
        mapFragment.setHealthServiceProvider(healthServiceProviderItems);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment,mapFragment);
        fragmentTransaction.commit();
    }
    private ArrayList<HealthServiceProviderItemNew> constructHealthListItemForHeader(int cat_id, String header)
    {

       // Log.d("cat_id","####"+cat_id);
       // Log.d("header","####"+header);
        ArrayList<HealthServiceProviderItemNew> healthServiceProvider;
        ArrayList<HealthServiceProviderItemNew> healthServiceProvider1;
        HealthServiceProviderTableNew healthServiceProviderTable = new HealthServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        SubCategoryTableNew subCategoryTableNew=new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        int refId=subCategoryTableNew.getSubcategoryId(header);
       // Log.d("refId_newer","####"+refId);
        String refIds=String.valueOf(refId);

        healthServiceProvider1 = healthServiceProviderTable.getAllHealthSubCategoriesInfoWithHead(cat_id, refIds);
        healthServiceProvider = healthServiceProviderTable.getAllHealthSubCategoriesInfoWithHead(cat_id, refIds);
        return healthServiceProvider;
    }

    /**********************************************************Methods for entertainment*******************************************/

    private ArrayList<EntertainmentServiceProviderItemNew> constructEntertainmentListItem(int cat_id)
    {
        ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProviderItemNews;
        EntertainmentServiceProviderTableNew entertainmentServiceProviderTableNew = new EntertainmentServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        entertainmentServiceProviderItemNews = entertainmentServiceProviderTableNew.entertainmentServiceProviderItemNews();
        return entertainmentServiceProviderItemNews;
    }

    private void callMapFragmentWithEntertainmentInfo(String item_name,int cat_id,ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProviderItems)
    {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        // mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);

        mapFragment.setLocationNameId(locationNameId);
        mapFragment.setEntertainmentServiceProvider(entertainmentServiceProviderItems);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment,mapFragment);
        fragmentTransaction.commit();

    }

    private ArrayList<EntertainmentServiceProviderItemNew> constructEntertainmentListItemForHeader(int cat_id, String header)
    {
        ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProvider;
        ArrayList<EntertainmentServiceProviderItemNew> entertainmentServiceProvider1;
        EntertainmentServiceProviderTableNew entertainmentServiceProviderTable = new EntertainmentServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        SubCategoryTableNew subCategoryTableNew=new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        int refId=subCategoryTableNew.getSubcategoryId(header);
        String refIds=String.valueOf(refId);
        Log.d("SubcatHeader","@@@@@@"+refId);
        entertainmentServiceProvider1 = entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfoWithHead(cat_id, refIds);

        entertainmentServiceProvider = entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfoWithHead(cat_id, refIds);
        return entertainmentServiceProvider;
    }



    /**********************************************************Methods for government**********************************************/

    private ArrayList<GovernmentNewItem> constructgovListItem()
    {
        ArrayList<GovernmentNewItem> governmentNewItems;
        GovernmentNewTable governmentNewTable = new GovernmentNewTable(PlaceDetailsActivityNewLayout.this);
        governmentNewItems = governmentNewTable.getAllGovSubCategoriesInfo();
        return governmentNewItems;
    }

    private ArrayList<GovernmentNewItem> constructGovernmentListItemForHeader(int cat_id, String header)
    {
        ArrayList<GovernmentNewItem> governmentNewItems;
        GovernmentNewTable governmentNewTable = new GovernmentNewTable(PlaceDetailsActivityNewLayout.this);
        governmentNewItems = governmentNewTable.getAllGovSubCategoriesInfoWithHead(header);
        return governmentNewItems;
    }
    private void callMapFragmentWithGovInfo(String item_name,int cat_id,ArrayList<GovernmentNewItem> governmentNewItems)
    {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        //   mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);

        mapFragment.setLocationNameId(locationNameId);
        mapFragment.setGovernmentNewItems(governmentNewItems);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment,mapFragment);
        fragmentTransaction.commit();
    }

   /* private void callMapFragmentWithGovInfo(String item_name,int cat_id,ArrayList<GovernmentNewItem> governmentNewItems)
    {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        // mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);

        mapFragment.setLocationNameId(locationNameId);
        mapFragment.setGovernmentNewItems(governmentNewItems);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment,mapFragment);
        fragmentTransaction.commit();



    }*/


    /**********************************************************Methods for legal***************************************************/

    private ArrayList<LegalAidServiceProviderItemNew> constructlegalaidListItem(int cat_id)
    {
        ArrayList<LegalAidServiceProviderItemNew> legalaidServiceProvider;
        LegalAidServiceProviderTableNew legalAidServiceProviderTable = new LegalAidServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfo(cat_id);


        return legalaidServiceProvider;
    }

    private ArrayList<LegalAidServiceProviderItemNew> constructlegalaidListItemForHeader(int cat_id, String header)
    {
        ArrayList<LegalAidServiceProviderItemNew> legalaidServiceProvider;
        SubCategoryTableNew subCategoryTableNew=new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        LegalAidServiceProviderTableNew legalAidServiceProviderTable = new LegalAidServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        int refId=subCategoryTableNew.getSubcategoryId(header);
        String refIds=String.valueOf(refId);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfoWithHead(cat_id, refIds);
        return legalaidServiceProvider;
    }

    private void callMapFragmentWithLegalAidInfo(String item_name,int cat_id,ArrayList<LegalAidServiceProviderItemNew> legalaidServiceProviderItems)
    {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        // mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);

        mapFragment.setLocationNameId(locationNameId);
        mapFragment.setLegalaidServiceProvider(legalaidServiceProviderItems);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment,mapFragment);
        fragmentTransaction.commit();

    }




    /**********************************************************Methods for financial**********************************************/
    private ArrayList<FinancialNewItem> constructfinancialListItem()
    {
        ArrayList<FinancialNewItem> financialNewItems;
        FinancialServiceNewTable financialServiceNewTable = new FinancialServiceNewTable(PlaceDetailsActivityNewLayout.this);
        financialNewItems = financialServiceNewTable.getAllFinancialSubCategoriesInfo();
        return financialNewItems;
    }
    private ArrayList<FinancialNewItem> constructFinancialListItemForHeader(int cat_id, String header)
    {
        ArrayList<FinancialNewItem> financialNewItems;
        FinancialServiceNewTable financialServiceNewTable = new FinancialServiceNewTable(PlaceDetailsActivityNewLayout.this);
        financialNewItems = financialServiceNewTable.getAllFinancialSubCategoriesInfoWithHead(header);
        return financialNewItems;
    }
    /*private ArrayList<FinancialNewItem> constructfinancialListItemForHeader(int cat_id, String header)
    {
        ArrayList<FinancialNewItem> financialServiceProvider;
        FinancialServiceNewTable financialServiceNewTable = new FinancialServiceNewTable(PlaceDetailsActivityNewLayout.this);
      //  financialServiceProvider = financialServiceNewTable.getAllFinancialSubCategoriesInfoWithHead(cat_id, header);
        return financialServiceProvider;
    }*/

    private void callMapFragmentWithFinancialInfo(String item_name,int cat_id,ArrayList<FinancialNewItem> financialNewItems)
    {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        //   mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);

        mapFragment.setLocationNameId(locationNameId);
        mapFragment.setFinancialServiceProvider(financialNewItems);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment,mapFragment);
        fragmentTransaction.commit();
    }






    /**********************************************************Methods for job*****************************************************/




    public void implementRouteDrawingFragmentOSM()
    {

        //listholder.setVisibility(View.GONE);
        NavigationCalled=true;
        MapFragmentRouteOSM mapFragmentOSM =new MapFragmentRouteOSM();

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragmentOSM);
        fragmentTransaction.commit();
    }

    public void Populateholder()
    {
        filterText = (EditText)findViewById(R.id.searchall);
        EducationNewTable educationServiceProviderTable=new EducationNewTable(PlaceDetailsActivityNewLayout.this);
        EntertainmentServiceProviderTableNew entertainmentServiceProviderTable=new EntertainmentServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        HealthServiceProviderTableNew healthServiceProviderTable = new HealthServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        FinancialServiceNewTable financialServiceProviderTable = new FinancialServiceNewTable(PlaceDetailsActivityNewLayout.this);
        GovernmentNewTable governmentNewTable=new GovernmentNewTable(PlaceDetailsActivityNewLayout.this);
        LegalAidServiceProviderTableNew legalAidServiceProviderTable = new LegalAidServiceProviderTableNew(PlaceDetailsActivityNewLayout.this);
        fetchedent=entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfo();
        fetchedfin=financialServiceProviderTable.getAllFinancialSubCategoriesInfo();
        fetchedleg=legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfosearch();
        fetchedhel=healthServiceProviderTable.getAllHealthSubCategoriesInfosearch();
        fetchededu=educationServiceProviderTable.getAllEducationSubCategoriesInfo();
        fetchedgov=governmentNewTable.getAllGovSubCategoriesInfo();
        String nameen;
        String namebn;

        int node;
        String refname;
        for (int i=0;i<fetchededu.size();i++)
        {

            nameen=fetchededu.get(i).getNameen();
            node=fetchededu.get(i).getEduId();
            refname=fetchededu.get(i).getRefnumm();
            namebn=fetchededu.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,5);
            allHolders.add(all);
        }


        for (int i=0;i<fetchedhel.size();i++)
        {

            nameen=fetchedhel.get(i).getNode_name();
            node= Integer.parseInt(fetchedhel.get(i).getId());
            refname=fetchedhel.get(i).getReferences();
            namebn=fetchedhel.get(i).getNode_bn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,1);
            allHolders.add(all);
        }


        for (int i=0;i<fetchedleg.size();i++)
        {

            nameen=fetchedleg.get(i).getLegalaidNameEng();
            node= Integer.parseInt(fetchedleg.get(i).getIdentifierId());
            refname=fetchedleg.get(i).getBreaktime2();
            namebn=fetchedleg.get(i).getLegalaidNameBan();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,29);
            allHolders.add(all);
        }
        for (int i=0;i<fetchedent.size();i++)
        {

            nameen=fetchedent.get(i).getNodeName();
            node= Integer.parseInt(fetchedent.get(i).getNodeId());
            refname=fetchedent.get(i).getNodeAdditional();
            namebn=fetchedent.get(i).getNodeNameBn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,14);
            allHolders.add(all);
        }
        for (int i=0;i<fetchedfin.size();i++)
        {

            nameen=fetchedfin.get(i).getNameen();
            node=fetchedfin.get(i).getFinId();
            refname=fetchedfin.get(i).getRefnumm();
            namebn=fetchedfin.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,11);
            allHolders.add(all);


        }

        for (int i=0;i<fetchedgov.size();i++)
        {

            nameen=fetchedgov.get(i).getNameen();
            node=fetchedgov.get(i).getFinId();
            refname=fetchedgov.get(i).getRefnumm();
            namebn=fetchedgov.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,33);
            allHolders.add(all);


        }
        calladapter(false);

    }
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
    public void fun1() {
        fgrp2.setOnCheckedChangeListener(null);
        fgrp2.clearCheck();
        fgrp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fun2();
                int buttonId=fgrp2.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)findViewById(buttonId);
                setFilterword((String) radioButton.getText());
                int num=Findsubcatid(filterword);
                calladapter(true);

                Log.v("Inside fun1",String.valueOf(num));
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
                int buttonId=fgrp1.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)findViewById(buttonId);
                setFilterword((String) radioButton.getText());
                int num=Findsubcatid(filterword);
                calladapter(true);

                Log.v("Inside fun2","fun1");

            }
        });
    }
    private int Findsubcatid(String filterword){

        for (int s=0;s<=subholders.size();s++)
        {
            if (subholders.get(s).getSubcatname().equals(filterword))
            {
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
        if(SearchClicked)
        {
            searchviewholder.setVisibility(View.GONE);
            SearchClicked=false;

        }
        toggleButton.setVisibility(View.VISIBLE);
        spItems.setVisibility(View.VISIBLE);

//
//            map.setVisibility(View.GONE);
        if(showList==1)
        {

            ServiceListDisplayAdapter adapter = new ServiceListDisplayAdapter(this, groups, currentCategoryID);
            subCatItemList.setAdapter(adapter);
        }
        SharedPreferences pref = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String Latitude = pref.getString("Latitude", null);
        String Longitude = pref.getString("Longitude", null);
//

        setNodefromback(pref.getString("nValue",null));
        Boolean valuecheck=pref.getBoolean("Value",false);
        if (valuecheck==false)
        {
          //  map.setVisibility(View.GONE);
        }



        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();


        if (NavigationCalledOnce==true)
        {
            callMapFragment(locationNameId);
        }

        if (valuecheck!=false & NavigationCalledOnce==false)
        {
            spItems.setVisibility(View.GONE);
            explist.setVisibility(View.GONE);
            if(!ListClicked.equals(true))
            map.setVisibility(View.VISIBLE);
            llCatListHolder.setVisibility(View.GONE);
            llSubCatListHolder.setVisibility(View.GONE);
            toggleButton.setVisibility(View.GONE);
            svsholder.setVisibility(View.GONE);
            svholder.setVisibility(View.GONE);
            implementRouteDrawingFragmentOSM();
        }


        else {
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
                if(!ListClicked.equals(true))
                    map.setVisibility(View.VISIBLE);
                if(SearchClicked){
                    map.setVisibility(View.GONE);
                }
            }
            editor.putInt("LocationNameId", locationNameId);
            editor.commit();

            if (Latitude != null && AppUtils.isNetConnected(getApplicationContext())) {
                Double Lon = Double.parseDouble(Longitude);
                Double Lat = Double.parseDouble(Latitude);



            }
        }
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



}