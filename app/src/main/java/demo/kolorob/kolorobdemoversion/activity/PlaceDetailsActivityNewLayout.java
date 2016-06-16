package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
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
import android.widget.Spinner;
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
import demo.kolorob.kolorobdemoversion.adapters.MyExpandableListAdapter;
import demo.kolorob.kolorobdemoversion.adapters.SearchHolder;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentOSM;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
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

    public void setShowList(int showList) {
        this.showList = showList;
    }
    ToggleButton toggleButton;
    private static final String TAG = PlaceDetailsActivityNewLayout.class.getSimpleName();
    private static final int ANIM_INTERVAL = 200;
    private static double VIEW_WIDTH;
    private static boolean mapcalledstatus;
    private LinearLayout llCatListHolder,mapnother,listholder,explist;
    CategoryItem ci;
    private LinearLayout llSubCatListHolder;
    private HashMap<String, Integer> sections = new HashMap<String, Integer>();
    private static FrameLayout map;
    private Spinner spItems;
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

    private Button prebutton;

    private int sideIndexHeight;
    private List<Object[]> alphabet = new ArrayList<Object[]>();
    Activity act;
    public int layoutstatus;
    private Boolean list_expand=false;
    private TextView listOrMapDisplayText;
    boolean educlicked,helclicked,entclicked,finclicked,govclicked,legclicked,jobclicked=false;
    private Toolbar toolbar,toolbar2;


    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.


    ArrayList<EntertainmentServiceProviderItem> printnamesent;
    ArrayList<JobServiceProviderItem> printnamesjob;
    ArrayList<LegalAidServiceProviderItem> printnamesleg;
    ArrayList<HealthServiceProviderItem> printnameshea;
    ArrayList<FinancialServiceProviderItem> printnamesfin;
    ArrayList<String> allData= new ArrayList<>();
    private DrawerLayout drawer;
    ArrayList<SearchHolder> searchheads=new ArrayList<>();
    Context context;
    ArrayList <String>Headerholder=new ArrayList<>();
    ArrayList<EducationServiceProviderItem> printnames;
    //common for all categories
    public LinearLayout sideIndex,searchLayout;
    public CategoryItem getCi() {
        return ci;
    }

    public void setCi(CategoryItem ci) {
        this.ci = ci;
    }

    private ArrayList<SubCategoryItem> currentSubCategoryItem;
    public static int currentCategoryID;
    private  ViewGroup.LayoutParams kk;
    Vector<Group> groups = new Vector<Group>();
    TextView header;
    private String placeChoice;
    private int indexListSize;
    private ListActivity listView;
    private ImageButton expandableListShowing,more,MapButton,ListButton,SearchButton,CompareButton;
    private RelativeLayout mapholderr;
    ArrayList<CategoryItem> categoryList;
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
    private ArrayList<FinancialServiceProviderItem>fetchedfin;
    private ArrayList<EducationServiceProviderItem>fetchededu;
    private ArrayList<LegalAidServiceProviderItem>fetchedleg;
    private ArrayList<EntertainmentServiceProviderItem>fetchedent;
    private ArrayList<HealthServiceProviderItem>fetchedhel;
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
    ArrayList<EducationServiceProviderItem> eduItem=new ArrayList<>();
    ArrayList<HealthServiceProviderItem> healthItem=new ArrayList<>();
    ArrayList<EntertainmentServiceProviderItem> entItem=new ArrayList<>();
    ArrayList<LegalAidServiceProviderItem> legalItem=new ArrayList<>();
    ArrayList<FinancialServiceProviderItem> financialItem=new ArrayList<>();

    ArrayList<EducationServiceProviderItem> EDD=new ArrayList<>();
    ArrayList<HealthServiceProviderItem> HEL=new ArrayList<>();
    ArrayList<LegalAidServiceProviderItem>LEG=new ArrayList<>();
    ArrayList<EntertainmentServiceProviderItem>ENT =new ArrayList<>();
    ArrayList<FinancialServiceProviderItem>FIN=new ArrayList<>();
    ArrayList<JobServiceProviderItem>JJOB=new ArrayList<>();
    ArrayList <String>clicked=new ArrayList<>();
    EducationServiceProviderItem nulledu;
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
        /// Log.d(">>>>>>","You are in onResume");

        val = settings.getInt("KValue", 0);
        Log.e("ASinplaceDetails",String.valueOf(val));
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

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
        int buttonWidth = width/4;
        int buttonHeight = height/20;
        allitemList=(ListView)findViewById(R.id.allitem);
        explist=(LinearLayout)findViewById(R.id.explist);
        catholder=(RelativeLayout)findViewById(R.id.categoryfilterholder);
       // SearchButton.setLayoutParams(new RelativeLayout.LayoutParams(buttonWidth, buttonHeight));
      //  CompareButton.setLayoutParams(new RelativeLayout.LayoutParams(buttonWidth, buttonHeight));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) MapButton.getLayoutParams();
        params.weight = 1;
        params.width=buttonWidth;
        MapButton.setLayoutParams(params);
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) SearchButton.getLayoutParams();
        params2.weight = 1;
        params2.width=buttonWidth;
        SearchButton.setLayoutParams(params2);
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) ListButton.getLayoutParams();
        params3.weight = 1;
        params3.width=buttonWidth;
        ListButton.setLayoutParams(params2);
        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) CompareButton.getLayoutParams();
        params4.weight = 1;
        params4.width=buttonWidth;
        CompareButton.setLayoutParams(params4);
       // SearchButton.setMinimumWidth(buttonWidth);
        //ListButton.setLayoutParams(layoutParams);
       // SearchButton.setLayoutParams(layoutParams);
       // CompareButton.setLayoutParams(layoutParams);

mapcalledstatus=false;
        toolbar = (Toolbar) findViewById(R.id.categorytoolbar);


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

        //  Log.d(">>>>","test_dpi "+dpi);
        // svSubCategoryListHolder=(HorizontalScrollView)findViewById(R.id.svSubCategoryListHolder);

        HorizontalScrollView svSubCategoryListHolder = new HorizontalScrollView(this);
        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
//        wholeLayout=(RelativeLayout)findViewById(R.id.wholeLayout);


        final Intent intent;
        intent = getIntent();
        if (null != intent)
        {
            locationNameId = intent.getIntExtra(AppConstants.KEY_PLACE,0);
            if(locationNameId== AppConstants.PLACE_BAUNIABADH)
            {
                setPlaceChoice("Baunia Badh");
                locationName = AppConstants.BAUNIABADH;
                listData.add(AppConstants.BAUNIABADH);
                listData.add(AppConstants.PARIS_ROAD);
                setLocationNameEng("Baunia Badh");
            }
            else if(locationNameId== AppConstants.PLACE_PARIS_ROAD)
            {
                setPlaceChoice("Paris Road");
                locationName = AppConstants.PARIS_ROAD;
                listData.add(AppConstants.PARIS_ROAD);
                listData.add(AppConstants.BAUNIABADH);
                setLocationNameEng("Paris Road");
            }
        }








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
        VIEW_WIDTH = AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC_NEW;
        isCatExpandedOnce = false;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.92); // 80% of the view width

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


        ViewGroup.LayoutParams exlist= explist.getLayoutParams();
        RelativeLayout.LayoutParams expnlist = (RelativeLayout.LayoutParams) explist.getLayoutParams();

        expnlist.setMargins((s*9)/10,40,5,40);




        lp.height=100;

        if(height<1000)
            caTsList.setMargins(0, 60, 0, 0);
        else
            caTsList.setMargins(0, 10, 0, 0);




        //Log.d(">>>>>>>>","View_width       "+s);
        /**
         * constructing category list
         **/
        CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivityNewLayout.this);
        categoryList=categoryTable.getAllCategories();
        constructCategoryList(categoryList);
        //rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        //rlSubCatHolder.setVisibility(View.INVISIBLE);


        // callMapFragment();
        spItems = (Spinner) findViewById(R.id.areaitems);
        spItems.setVisibility(View.VISIBLE);
        arrayAdapter = new ArrayAdapter(PlaceDetailsActivityNewLayout.this,R.layout.area_row_spinner, listData);
        arrayAdapter.setDropDownViewResource(R.layout.area_row_spinners_dropdown);
        spItems.setAdapter(arrayAdapter);
        spItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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


        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                map.setVisibility(View.GONE);
                explist.setVisibility(View.GONE);
                searchviewholder.setVisibility(View.VISIBLE);
                populateSearch();
                if(educlicked==false||helclicked||false||entclicked==false||legclicked==false||finclicked==false)
                {
                    filterholder.setVisibility(View.GONE);
                }

            }
        });
        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchviewholder.setVisibility(View.GONE);
                map.setVisibility(View.VISIBLE);



            }
        });

        ListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_expand.equals(false))
                {
                llSubCatListHolder.setVisibility(View.GONE);
                subCatItemList.setVisibility(View.VISIBLE);
                explist.setVisibility(View.VISIBLE);
              //  wholeLayout.setBackgroundDrawable( getResources().getDrawable(R.drawable.splash) );
                map.setVisibility(View.GONE);
                setShowList(1);

                list_expand=true;
                //listOrMapDisplayText.setText("ম্যাপ দেখতে চাইলে এখানে চাপ দিন");
                Log.d("====","CategoryId"+currentCategoryID);
                categoryListBuildUp(currentCategoryID);
                }
                else
                {
                    llSubCatListHolder.setVisibility(View.VISIBLE);
                    setShowList(0);
                    map.setVisibility(View.VISIBLE);
                    list_expand=false;
                    subCatItemList.setVisibility(View.GONE);
                   // listOrMapDisplayText.setText("লিস্ট দেখতে চাইলে এখানে চাপ দিন");
                    //constructCategoryList(categoryList);

                }

            }
        });
        CompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SharedPreferencesHelper.getComapreValue(PlaceDetailsActivityNewLayout.this)==0)
                {
                    demo.kolorob.kolorobdemoversion.helpers.AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                            "আপনি কোন সেবা নির্বাচিত করেননি তুলনা করার জন্য");
                }
                else if(SharedPreferencesHelper.getComapreValue(PlaceDetailsActivityNewLayout.this)==1)
                {
                    demo.kolorob.kolorobdemoversion.helpers.AlertMessage.showMessage(con, "তুলনা করা সম্ভব হচ্ছে না",
                            "আপনি একটি সেবা নির্বাচিত করেছেন। তুলনা করার জন্য দুটি সেবা নির্বাচন করুন");
                }
                else {
                    Intent compare= new Intent(PlaceDetailsActivityNewLayout.this,CompareActivity.class);
                    startActivity(compare);

                }

            }
        });

        toggleButton=(ToggleButton)findViewById(R.id.toggle);
        toggleButton.setVisibility(View.VISIBLE);
        toggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(toggleButton.isChecked()){
                    llCatListHolder.setVisibility(View.VISIBLE);
                    if(educlicked==true||helclicked==true||entclicked==true||legclicked==true||finclicked==true)
                    {
                        llSubCatListHolder.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    llCatListHolder.setVisibility(View.GONE);
                    llSubCatListHolder.setVisibility(View.GONE);
                }

                //Button is OFF
                // Do Something
            }
        });

    }
    public void populateSearch()
    {

        ImageButton more=(ImageButton)findViewById(R.id.morebutton);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                catholder.setVisibility(View.VISIBLE);
                fholder.setVisibility(View.VISIBLE);
                populatefilterwords(getFilcatid());

            }
        });

    }





    public void createData(int cat_id, String head,String placeChoice) {
        switch (cat_id) {
            case AppConstants.EDUCATION:

                SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
                currentCategoryID = cat_id;
                EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> print = null;
                groups.removeAllElements();
                print = subCategoryTable.getSubnameedu(currentCategoryID, head);
                for (int j = 0; j < print.size(); j++) {
                    Group group = new Group(print.get(j));
                    printnames = null;
                    printnames = educationServiceProviderTable.Edunames(currentCategoryID, "", print.get(j), placeChoice);

                    // Log.d(">>>>", "printnames "+printnames);
                    /////  Log.d(">>>>", "currentCategoryID  "+currentCategoryID);
                    // Log.d(">>>>", "head "+head);
                    // Log.d(">>>>", "print.get(j) "+print.get(j));
                    // Log.d(">>>>", "placeChoice "+placeChoice);
                    for (int i = 0; i < printnames.size(); i++) {
                        group.children.add(i, printnames.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.ENTERTAINMENT:

                SubCategoryTable subCategoryTable2 = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
                currentCategoryID = cat_id;
                EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> printent = null;
                groups.removeAllElements();
                printent = subCategoryTable2.getSubnameedu(currentCategoryID, head);
                for (int j = 0; j < printent.size(); j++) {
                    Group group = new Group(printent.get(j));
                    printnamesent = null;
                    printnamesent = entertainmentServiceProviderTable.Entnames(currentCategoryID, head, printent.get(j), placeChoice);
                    for (int i = 0; i < printnamesent.size(); i++) {
                        group.childrenent.add(i, printnamesent.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.HEALTH:

                SubCategoryTable subCategoryTable3 = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
                currentCategoryID = cat_id;
                HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> printhea = null;
                groups.removeAllElements();
                printhea = subCategoryTable3.getSubnameedu(currentCategoryID, head);
                for (int j = 0; j < printhea.size(); j++) {
                    Group group = new Group(printhea.get(j));
                    printnameshea = null;
                    printnameshea = healthServiceProviderTable.Heanames(currentCategoryID, head, printhea.get(j), placeChoice);
                    for (int i = 0; i <  printnameshea .size(); i++) {
                        group.childrenhea.add(i,printnameshea .get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.FINANCIAL:

                SubCategoryTable subCategoryTable4 = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
                currentCategoryID = cat_id;
                FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> printfin = null;
                groups.removeAllElements();
                printfin= subCategoryTable4.getSubnameedu(currentCategoryID, head);
                for (int j = 0; j <  printfin.size(); j++) {
                    Group group = new Group(printfin.get(j));
                    printnamesfin = null;
                    printnamesfin= financialServiceProviderTable.Finnames(currentCategoryID, head, printfin.get(j), placeChoice);
                    for (int i = 0; i < printnamesfin.size(); i++) {
                        group.childrenfin.add(i, printnamesfin.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.LEGAL:

                SubCategoryTable subCategoryTable5 = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
                currentCategoryID = cat_id;
                LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivityNewLayout.this);
                ArrayList<String> printleg = null;
                groups.removeAllElements();
                printleg = subCategoryTable5.getSubnameedu(currentCategoryID, head);
                for (int j = 0; j < printleg.size(); j++) {
                    Group group = new Group(printleg.get(j));
                    printnamesleg = null;
                    printnamesleg = legalAidServiceProviderTable.Legnames(currentCategoryID, head, printleg.get(j), placeChoice);
                    for (int i = 0; i < printnamesleg.size(); i++) {
                        group.childrenleg.add(i, printnamesleg.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.JOB:


        }
    }


    public void populatefilterwords(int filcatid)
    {
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNewLayout.this);
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

        } else if (id == R.id.info_change) {

            Intent em = new Intent(this, Information_UpdateActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        } else if (id == R.id.emergency_info) {

            //  Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
            Intent em = new Intent(this, NewEmergency.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        } else if (id == R.id.local_representative) {

            // Toast.makeText(con,"It will be added in next version.",Toast.LENGTH_LONG).show();
            AlertMessage.showMessage(con, "Representative", "It will be added in next version.");

        } else if (id == R.id.adv_info) {
            //  Toast.makeText(con,"It will be added in next version.",Toast.LENGTH_LONG).show();

            AlertMessage.showMessage(con,"Advertisement","It will be added in next version.");
        } else if (id == R.id.adv) {
            AlertMessage.showMessage(con,"Ads Information","It will be added in next version.");
        }

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
                EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivityNewLayout.this.con);
                nulledu = educationServiceProviderTable.geteduNode2(node);
                Intent iient = new Intent(PlaceDetailsActivityNewLayout.this.con, DetailsInfoActivityEducation.class);
                iient.putExtra(AppConstants.KEY_DETAILS_VIEW, nulledu);
                this.startActivity(iient);

            }
            else if (NavigationCalled==false)
            {
                callMapFragment(locationNameId);
            }
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




    private void constructCategoryList(ArrayList<CategoryItem> categoryList) {
        constructCategoryList(categoryList, 1.0);
    }

    private void constructCategoryList(ArrayList<CategoryItem> categoryList, double dwPercentage) {
        llCatListHolder.removeAllViews();
        for ( CategoryItem ci : categoryList) {
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




        if( height>1000)
            v = li.inflate(R.layout.cat_side_list_item, llCatListHolder, false);
        else

            v = li.inflate(R.layout.cat_list_mobile, llCatListHolder, false);
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
                        EDD.clear();
                        educlicked=true;
                        setFilcatid(currentCategoryID);
                        catstatus=true;
                        calladapter(catstatus);

                        if(showList==1) {

                            explist.setVisibility(View.VISIBLE);
                            explist.setAnimation(slideOutFromLeftAnim());
                            llSubCatListHolder.setVisibility(View.GONE);
                            subCatItemList.setVisibility(View.VISIBLE);


                        }
                        else {

                            llSubCatListHolder.setVisibility(View.GONE);

                            ArrayList<EducationServiceProviderItem> educationServiceProvider;
                            educationServiceProvider = constructEducationListItem(ci.getId());
                            ivIcon.setImageResource(R.drawable.turned_on_porashona);
                            callMapFragmentWithEducationInfo(ci.getCatName(), ci.getId(), educationServiceProvider);


                        }


                        
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.turned_on_porashona);
                        mapcalledstatus=true;
                        llSubCatListHolder.setVisibility(View.GONE);

                        break;
                    case AppConstants.HEALTH:
                        HEL.clear();
                        helclicked=true;
                        setFilcatid(currentCategoryID);
                        catstatus=true;
                        calladapter(catstatus);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.turned_on_chikitsha);
                        ArrayList<HealthServiceProviderItem> healthServiceProvider;
                        healthServiceProvider = constructHealthListItem(ci.getId());
                        callMapFragmentWithHealthInfo(ci.getCatName(), ci.getId(), healthServiceProvider);
                        mapcalledstatus=true;
                            llSubCatListHolder.setVisibility(View.GONE);


                        break;

                    //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:
                        ENT.clear();
                        entclicked=true;
                        setFilcatid(currentCategoryID);
                        catstatus=true;
                        calladapter(catstatus);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
                        entertainmentServiceProvider = constructEntertainmentListItem(ci.getId());
                        ivIcon.setImageResource(R.drawable.turned_on_anondo_furti);
                        callMapFragmentWithEntertainmentInfo(ci.getCatName(), ci.getId(), entertainmentServiceProvider);
                        mapcalledstatus=true;




                            llSubCatListHolder.setVisibility(View.GONE);








                        break;


                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:
                        govclicked=true;
                        setFilcatid(currentCategoryID);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.turned_on_shorkari_shubidha);
                        mapcalledstatus=true;
                        llSubCatListHolder.setVisibility(View.GONE);

                        //TODO write necessary codes for government

                        toolbar.setVisibility(View.VISIBLE);
                       /* final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(PlaceDetailsActivityNew.this).create();

                        alertDialog.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
                        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                        alertDialog.dismiss();

                                        finish();
                                    }
                                });
                        alertDialog.getWindow().setLayout(200, 300);
                        alertDialog.show();*/
                        break;
                    case AppConstants.LEGAL:
                        LEG.clear();
                        legclicked=true;
                        setFilcatid(currentCategoryID);
                        catstatus=true;
                        calladapter(catstatus);
                        populatefilterwords(getFilcatid());
                        filterholder.setVisibility(View.VISIBLE);
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.turned_on_ain_kanun);
                        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
                        mapcalledstatus=true;
                        legalaidServiceProvider = constructlegalaidListItem(ci.getId());
                        callMapFragmentWithLegalAidInfo(ci.getCatName(), ci.getId(), legalaidServiceProvider);




                            llSubCatListHolder.setVisibility(View.GONE);








                        break;
                    case AppConstants.FINANCIAL:
                        FIN.clear();
                        finclicked=true;
                        setFilcatid(currentCategoryID);
                        catstatus=true;
                        calladapter(catstatus);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.turned_on_taka_poisha);
                        ArrayList<FinancialServiceProviderItem> financialServiceProvider;
                        financialServiceProvider = constructfinancialListItem(ci.getId());
                        callMapFragmentWithFinancialInfo(ci.getCatName(), ci.getId(), financialServiceProvider);
                        mapcalledstatus=true;



                            llSubCatListHolder.setVisibility(View.GONE);






                        break;
                    case AppConstants.JOB:
                        JJOB.clear();
                        jobclicked=true;
                        setFilcatid(currentCategoryID);
                        catstatus=true;
                        calladapter(catstatus);
                        filterholder.setVisibility(View.VISIBLE);
                        populatefilterwords(getFilcatid());
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.turned_on_chakri_bakri);
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


                        Intent intentJ = new Intent(PlaceDetailsActivityNewLayout.this,DisplayAllJobsActivity.class);
                        startActivity(intentJ);


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


                if(showList!=1)
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
        //     Log.d("cat_id",">>>" +cat_id);
        //   Log.d("header",">>>" +header);
          Log.d("======","catsss Id" +cat_id);



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
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, groups, currentCategoryID);
        subCatItemList.setAdapter(adapter);

    }
    private View getSubCategoryListItemView(final SubCategoryItem si, double dwPercentage, final int cat_id)
    {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        View v;
        LayoutInflater li = LayoutInflater.from(this);

        if(height>1000)
            v = li.inflate(R.layout.sub_cat_list_item, llSubCatListHolder, false);
        else
            v = li.inflate(R.layout.sub_cat_list_item1, llSubCatListHolder, false);
        final ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_sub_cat_icon);
        tvName = (TextView) v.findViewById(R.id.tv_sub_cat_name);
        if(height>1000)
            ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONS1[ subcategory++]);
        else{
            ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONS1[ subcategory++]);
        }
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        if(width>720)
            lpIv.width = (int) (primaryIconWidth * dwPercentage);
        else{
            lpIv.width = (int) (primaryIconWidth * dwPercentage*1.5);
        }

        ivIcon.setLayoutParams(lpIv);
        tvName.setTextColor(Color.MAGENTA);
        tvName.setText(si.getSubcatHeader());

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
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.blue_button);
                                                 continue;
                                             } else if (i == 1) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.red_button);
                                                 continue;

                                             } else if (i == 2) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.light_purple_button);
                                                 continue;

                                             } else if (i == 3) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.orange_button);
                                                 continue;

                                             } else if (i == 4) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.brown_button);
                                                 continue;

                                             } else if (i == 5) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.sky_blue_button);
                                                 continue;

                                             } else if (i == 6) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.light_orange_button);
                                                 continue;

                                             } else if (i == 7) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.deep_blue_button);
                                                 continue;

                                             } else if (i == 8) {
                                                 ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                                 ivIcon.setImageResource(0);
                                                 ivIcon.setImageResource(R.drawable.magenta_button);
                                                 continue;

                                             }

                                         } else {
                                             ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(i).findViewById(R.id.iv_sub_cat_icon));
                                             // TextView tv=(TextView) ((ViewGroup)llSubCatListHolder.getChildAt(i)).getChildAt(1);
                                             // new background because something has changed
                                             // check if it's not the imageView you just clicked because you don't want to change its background
                                             // tv.setText("as");

                                             ivIcon.setImageResource(0);
                                             ivIcon.setImageResource(AppConstants.OFF_BUTTON[0]);
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
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.blue_button);
                                                continue;
                                            } else if (iit == 1) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.red_button);
                                                continue;

                                            } else if (iit == 2) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.light_purple_button);
                                                continue;

                                            } else if (iit == 3) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.orange_button);
                                                continue;

                                            } else if (iit == 4) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.brown_button);
                                                continue;

                                            } else if (iit == 5) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.sky_blue_button);
                                                continue;

                                            } else if (iit == 6) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.light_orange_button);
                                                continue;

                                            } else if (iit== 7) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.deep_blue_button);
                                                continue;

                                            } else if (iit == 8) {
                                                ImageView ivIcon = (ImageView) (llSubCatListHolder.getChildAt(iit).findViewById(R.id.iv_sub_cat_icon));
                                                ivIcon.setImageResource(0);
                                                ivIcon.setImageResource(R.drawable.magenta_button);
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


                        Headerholder.add(si.getSubcatHeader());
                        for(int s=0;s<Headerholder.size();s++)
                        {
                            eduItem=constructEducationListItemForHeader(cat_id, Headerholder.get(s));

                        }
                        for (int ss=0;ss<eduItem.size();ss++)
                        {
                            EDD.add(eduItem.get(ss));
                        }


                        callMapFragmentWithEducationInfo(si.getSubcatHeader(), cat_id, EDD);
                        break;
                    case AppConstants.HEALTH:
                        //TODO write necessary codes for health
                        Headerholder.add(si.getSubcatHeader());
                        for(int s=0;s<Headerholder.size();s++)
                        {
                            healthItem = constructHealthListItemForHeader(cat_id,Headerholder.get(s));


                        }
                        for (int ss=0;ss<healthItem.size();ss++)
                        {
                            HEL.add(healthItem.get(ss));
                        }



                        callMapFragmentWithHealthInfo(si.getSubcatHeader(), cat_id, HEL);

                        break;
                    case AppConstants.ENTERTAINMENT:

                        Headerholder.add(si.getSubcatHeader());
                        for(int s=0;s<Headerholder.size();s++)
                        {

                            entItem = constructEntertainmentListItemForHeader(cat_id, Headerholder.get(s));

                        }
                        for (int ss=0;ss<entItem.size();ss++)
                        {
                            ENT.add(entItem.get(ss));
                        }


                        callMapFragmentWithEntertainmentInfo(si.getSubcatHeader(), cat_id, ENT);
                        break;
                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:
                        map.removeAllViews();
                        final AlertDialog alertDialog = new AlertDialog.Builder(PlaceDetailsActivityNewLayout.this).create();

                        alertDialog.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        alertDialog.dismiss();
                                    }
                                });
                        alertDialog.getWindow().setLayout(200, 300);
                        alertDialog.show();
                        break;
                    case AppConstants.LEGAL:
                        Headerholder.add(si.getSubcatHeader());
                        for(int s=0;s<Headerholder.size();s++)
                        {
                            legalItem = constructlegalaidListItemForHeader(cat_id, Headerholder.get(s));
                        }
                        for (int ss=0;ss<legalItem.size();ss++)
                        {
                            LEG.add(legalItem.get(ss));
                        }


                        callMapFragmentWithLegalAidInfo(si.getSubcatHeader(), cat_id, LEG);
                        break;
                    case AppConstants.FINANCIAL:
                        Headerholder.add(si.getSubcatHeader());
                        for(int s=0;s<Headerholder.size();s++)
                        {
                            financialItem = constructfinancialListItemForHeader(cat_id,  Headerholder.get(s));

                        }
                        for (int ss=0;ss<financialItem.size();ss++)
                        {
                            FIN.add(financialItem.get(ss));
                        }

                        callMapFragmentWithFinancialInfo(si.getSubcatHeader(), cat_id, FIN);
                        break;
                    case AppConstants.JOB:
                        map.removeAllViews();
                        final AlertDialog alertDialog2 = new AlertDialog.Builder(PlaceDetailsActivityNewLayout.this).create();

                        alertDialog2.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
                        alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        alertDialog2.dismiss();
                                    }
                                });
                        alertDialog2.getWindow().setLayout(200, 300);
                        alertDialog2.show();


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
        decCatListWidth(dwPerc);

        // TODO Inflate the sub-category list from right
        final RelativeLayout rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        if(subCatShowFlag==1&&showList!=1)
        {
            llSubCatListHolder.setVisibility(View.VISIBLE);
        }
        subCatShowFlag=1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(showList!=1)
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

    private ArrayList<EducationServiceProviderItem> constructEducationListItem(int cat_id)
    {
        ArrayList<EducationServiceProviderItem> educationServiceProvider;
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        educationServiceProvider = educationServiceProviderTable.getAllEducationSubCategoriesInfo(cat_id);
        return educationServiceProvider;
    }

    private ArrayList<EducationServiceProviderItem> constructEducationListItemForHeader(int cat_id, String header)
    {
        ArrayList<EducationServiceProviderItem> educationServiceProvider;
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        educationServiceProvider = educationServiceProviderTable.getAllEducationSubCategoriesInfoWithHead(cat_id, header);
        return educationServiceProvider;
    }

    private void callMapFragmentWithEducationInfo(String item_name,int cat_id,ArrayList<EducationServiceProviderItem> educationServiceProviderItems)
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
    }

    private void callMapFragment(int locationNameId) {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        //   mapFragment.setMapIndicatorText(item_name);
        mapFragment.setLocationNameId(locationNameId);
        if (mapcalledstatus == true) {
          if(educlicked){
              educlicked=false;
              mapFragment.setCategoryId(1);
              ArrayList<EducationServiceProviderItem> educationServiceProviderItems;
              educationServiceProviderItems = constructEducationListItem(1);
             mapFragment.setEducationServiceProvider(educationServiceProviderItems);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
              fragmentTransaction.replace(R.id.map_fragment,mapFragment);
            fragmentTransaction.commit();
          }
            if(helclicked){
                helclicked=false;
                mapFragment.setCategoryId(2);
                ArrayList<HealthServiceProviderItem> healthServiceProviderItems;
                healthServiceProviderItems = constructHealthListItem(2);
                mapFragment.setHealthServiceProvider(healthServiceProviderItems);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.map_fragment,mapFragment);
                fragmentTransaction.commit();
            }
            if(entclicked){
                entclicked=false;
                mapFragment.setCategoryId(3);
                ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProviderItems;
                entertainmentServiceProviderItems = constructEntertainmentListItem(3);
                mapFragment.setEntertainmentServiceProvider(entertainmentServiceProviderItems);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.map_fragment,mapFragment);
                fragmentTransaction.commit();
            }
            if(legclicked){
                legclicked=false;
                mapFragment.setCategoryId(5);
                ArrayList<LegalAidServiceProviderItem> legalAidServiceProviderItems;
                legalAidServiceProviderItems = constructlegalaidListItem(5);
                mapFragment.setLegalaidServiceProvider(legalAidServiceProviderItems);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.map_fragment,mapFragment);
                fragmentTransaction.commit();
            }
            if(finclicked){
                finclicked=false;
                mapFragment.setCategoryId(6);
                ArrayList<FinancialServiceProviderItem> financialServiceProviderItems;
                financialServiceProviderItems = constructfinancialListItem(6);
                mapFragment.setFinancialServiceProvider(financialServiceProviderItems);
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

    private ArrayList<HealthServiceProviderItem> constructHealthListItem(int cat_id)
    {
        ArrayList<HealthServiceProviderItem> healthServiceProvider;
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        healthServiceProvider = healthServiceProviderTable.getAllHealthSubCategoriesInfo(cat_id);
        return healthServiceProvider;
    }

    private void callMapFragmentWithHealthInfo(String item_name,int cat_id,ArrayList<HealthServiceProviderItem> healthServiceProviderItems)
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
    private ArrayList<HealthServiceProviderItem> constructHealthListItemForHeader(int cat_id, String header)
    {
        ArrayList<HealthServiceProviderItem> healthServiceProvider;
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        healthServiceProvider = healthServiceProviderTable.getAllHealthSubCategoriesInfoWithHead(cat_id, header);
        return healthServiceProvider;
    }

    /**********************************************************Methods for entertainment*******************************************/

    private ArrayList<EntertainmentServiceProviderItem> constructEntertainmentListItem(int cat_id)
    {
        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        entertainmentServiceProvider = entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfo(cat_id);
        return entertainmentServiceProvider;
    }

    private void callMapFragmentWithEntertainmentInfo(String item_name,int cat_id,ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProviderItems)
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

    private ArrayList<EntertainmentServiceProviderItem> constructEntertainmentListItemForHeader(int cat_id, String header)
    {
        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        entertainmentServiceProvider = entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfoWithHead(cat_id, header);
        return entertainmentServiceProvider;
    }



    /**********************************************************Methods for government**********************************************/




    /**********************************************************Methods for legal***************************************************/

    private ArrayList<LegalAidServiceProviderItem> constructlegalaidListItem(int cat_id)
    {
        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfo(cat_id);
        return legalaidServiceProvider;
    }

    private ArrayList<LegalAidServiceProviderItem> constructlegalaidListItemForHeader(int cat_id, String header)
    {
        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfoWithHead(cat_id, header);
        return legalaidServiceProvider;
    }

    private void callMapFragmentWithLegalAidInfo(String item_name,int cat_id,ArrayList<LegalAidServiceProviderItem> legalaidServiceProviderItems)
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
    private ArrayList<FinancialServiceProviderItem> constructfinancialListItem(int cat_id)
    {
        ArrayList<FinancialServiceProviderItem> financialServiceProvider;
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        financialServiceProvider = financialServiceProviderTable.getAllFinancialSubCategoriesInfo(cat_id);
        return financialServiceProvider;
    }

    private ArrayList<FinancialServiceProviderItem> constructfinancialListItemForHeader(int cat_id, String header)
    {
        ArrayList<FinancialServiceProviderItem> financialServiceProvider;
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        financialServiceProvider = financialServiceProviderTable.getAllFinancialSubCategoriesInfoWithHead(cat_id, header);
        return financialServiceProvider;
    }

    private void callMapFragmentWithFinancialInfo(String item_name,int cat_id,ArrayList<FinancialServiceProviderItem> financialServiceProviderItems)
    {
        MapFragmentOSM mapFragment = new MapFragmentOSM();
        mapFragment.setLocationName(getPlaceChoice());
        // mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);

        mapFragment.setLocationNameId(locationNameId);
        mapFragment.setFinancialServiceProvider(financialServiceProviderItems);
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
        EducationServiceProviderTable educationServiceProviderTable=new EducationServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        EntertainmentServiceProviderTable entertainmentServiceProviderTable=new EntertainmentServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivityNewLayout.this);
        fetchedent=entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfo(3);
        fetchedfin=financialServiceProviderTable.getAllFinancialSubCategoriesInfo(6);
        fetchedleg=legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfo(5);
        fetchedhel=healthServiceProviderTable.getAllHealthSubCategoriesInfo(2);
        fetchededu=educationServiceProviderTable.getAllEducationSubCategoriesInfo(1);
        String nameen,namebn,catid,node;
        int refname;
        for (int i=0;i<fetchededu.size();i++)
        {

            nameen=fetchededu.get(i).getEduNameEng();
            node=fetchededu.get(i).getIdentifierId();
            refname=fetchededu.get(i).getEduSubCategoryId();
            namebn=fetchededu.get(i).getEduNameBan();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,1);
            allHolders.add(all);
        }


        for (int i=0;i<fetchedhel.size();i++)
        {

            nameen=fetchedhel.get(i).getNodeName();
            node=fetchedhel.get(i).getNodeId();
            refname=fetchedhel.get(i).getRefNum();
            namebn=fetchedhel.get(i).getNameBn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,2);
            allHolders.add(all);
        }


        for (int i=0;i<fetchedleg.size();i++)
        {

            nameen=fetchedleg.get(i).getLegalaidNameEng();
            node=fetchedleg.get(i).getIdentifierId();
            refname=fetchedleg.get(i).getLegalaidSubCategoryId();
            namebn=fetchedleg.get(i).getLegalaidNameBan();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,5);
            allHolders.add(all);
        }
        for (int i=0;i<fetchedent.size();i++)
        {

            nameen=fetchedent.get(i).getNodeName();
            node=fetchedent.get(i).getNodeId();
            refname=fetchedent.get(i).getEntSubCategoryId();
            namebn=fetchedent.get(i).getNodeNameBn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,3);
            allHolders.add(all);
        }
        for (int i=0;i<fetchedfin.size();i++)
        {

            nameen=fetchedfin.get(i).getNodeName();
            node=fetchedfin.get(i).getNodeId();
            refname=fetchedfin.get(i).getRefNum();
            namebn=fetchedfin.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,6);
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
            int checknum=getSnumber();
            if(checknum!=0)
            {
                subcatHolders.clear();
                for(int iii=0;iii<catHolders.size();iii++)
                {
                    if(catHolders.get(iii).getRefnum()==checknum)
                    {
                        subcatHolders.add(catHolders.get(iii));
                    }
                }
                adapter = new ListViewAdapterAllCategories(this, subcatHolders);

                allitemList.setAdapter(adapter);
            }
            else if (checknum==0){
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
        toggleButton.setVisibility(View.VISIBLE);
        spItems.setVisibility(View.VISIBLE);

        //Log.d(">>>>>>>>","CategoryId "+currentCategoryID);
        if(showList==1)
        {

            MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, groups, currentCategoryID);
            subCatItemList.setAdapter(adapter);
        }
        SharedPreferences pref = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String Latitude = pref.getString("Latitude", null);
        String Longitude = pref.getString("Longitude", null);


        setNodefromback(pref.getString("nValue",null));
        Boolean valuecheck=pref.getBoolean("Value",false);
        if (valuecheck==false)
        {
          //  map.setVisibility(View.GONE);
        }



        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();


        /// Log.d(">>>>>>","You are in onResume");
        if (NavigationCalledOnce==true)
        {
            callMapFragment(locationNameId);
        }

        if (valuecheck!=false & NavigationCalledOnce==false)
        {
            spItems.setVisibility(View.GONE);
            explist.setVisibility(View.GONE);
            map.setVisibility(View.VISIBLE);
            llCatListHolder.setVisibility(View.GONE);
            llSubCatListHolder.setVisibility(View.GONE);
            toggleButton.setVisibility(View.GONE);
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
                map.setVisibility(View.VISIBLE);
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
        // Log.d(">>>>>>","You are in onPause");
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