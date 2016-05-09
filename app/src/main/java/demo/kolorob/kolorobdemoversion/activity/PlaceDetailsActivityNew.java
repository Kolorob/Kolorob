package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.Group;
import demo.kolorob.kolorobdemoversion.adapters.MyExpandableListAdapter;
import demo.kolorob.kolorobdemoversion.adapters.SearchHolder;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Job.JobServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.fragment.MapFragment;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentOSM;
import demo.kolorob.kolorobdemoversion.fragment.MapRouteDrawingFragment;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by touhid on 12/3/15.
 *
 * @author touhid,israt,arafat
 */
public class PlaceDetailsActivityNew extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = PlaceDetailsActivityNew.class.getSimpleName();
    private static final int ANIM_INTERVAL = 200;
    private static double VIEW_WIDTH;
    private static boolean mapcalledstatus;
    private LinearLayout llCatListHolder,mapnother,listholder;
    CategoryItem ci;
    private HashMap<String, Integer> sections = new HashMap<String, Integer>();
    private static FrameLayout map;
    private Spinner spItems;
    ArrayAdapter arrayAdapter;
    List<String>listData=new ArrayList<String>();
    private int height,dpi;
    private View nextChild;
    private boolean isCatExpandedOnce = false;
    private int primaryIconWidth;
    private int subCatShowFlag=0;
    private int locationNameId,subcategory;
    private String locationName;

    private int sideIndexHeight;
    private List<Object[]> alphabet = new ArrayList<Object[]>();
Activity act;
    public int layoutstatus;

private Toolbar toolbar,toolbar2;


    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.


    ArrayList<EntertainmentServiceProviderItem> printnamesent;
    ArrayList<JobServiceProviderItem> printnamesjob;
    ArrayList<LegalAidServiceProviderItem> printnamesleg;
    ArrayList<HealthServiceProviderItem> printnameshea;
    ArrayList<FinancialServiceProviderItem> printnamesfin;
private DrawerLayout drawer;
    ArrayList<SearchHolder> searchheads=new ArrayList<>();
Context context;
    ArrayList<EducationServiceProviderItem> printnames;
    //common for all categories
    public LinearLayout sideIndex;
    public CategoryItem getCi() {
        return ci;
    }

    public void setCi(CategoryItem ci) {
        this.ci = ci;
    }

    private ArrayList<SubCategoryItem> currentSubCategoryItem;
    private int currentCategoryID;
    private  ViewGroup.LayoutParams kk;
    Vector<Group> groups = new Vector<Group>();

    private String placeChoice;
    private int indexListSize;
    private ListActivity listView;
    private RelativeLayout mapholderr;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        dpi=displayMetrics.densityDpi;
        int width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        setContentView(R.layout.activity_place_detailnew);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar2 = (Toolbar) findViewById(R.id.categorytoolbar);
       // toolbar.setBackgroundResource(android.R.color.transparent);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        ab.setDisplayHomeAsUpEnabled(true);
        mapnother=(LinearLayout)findViewById(R.id.mapnothers);
        mapholderr=(RelativeLayout)findViewById(R.id.mapholder);
        listholder=(LinearLayout)findViewById(R.id.listholder);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
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

        Log.d(">>>>","test_dpi "+dpi);
       // svSubCategoryListHolder=(HorizontalScrollView)findViewById(R.id.svSubCategoryListHolder);

        HorizontalScrollView svSubCategoryListHolder = new HorizontalScrollView(this);


        Intent intent;
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
            }
            else if(locationNameId== AppConstants.PLACE_PARIS_ROAD)
            {
                setPlaceChoice("Paris Road");
                locationName = AppConstants.PARIS_ROAD;
                listData.add(AppConstants.PARIS_ROAD);
                listData.add(AppConstants.BAUNIABADH);
            }
        }


        //categoryHeader = (TextView) findViewById(R.id.tv_cat_name);
        //categoryHeaderIcon = (ImageView) findViewById(R.id.ivHeadCatIconSubCatList);
        //placeDetailsLayout = (FrameLayout) findViewById(R.id.place_details_layout);


        ///this code will change the background of the layout for two places.


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
       // itemList = (ListView)findViewById(R.id.listViewSearch);
        //subCatItemListHeader = (TextView) findViewById(R.id.tv_sub_cat_item_list_head);

        //subCatItemList = (ExpandableListView) findViewById(R.id.listView);
        map = (FrameLayout) findViewById(R.id.map_fragment);
        //showsearch=(RelativeLayout)findViewById(R.id.show);
       // insSubCat = (TextView) findViewById(R.id.tvInstructionSubCat);
        //seeMap = (Button) findViewById(R.id.btn_see_map);
       // showSubCatListItem = (Button) findViewById(R.id.btn_show_sub_cat_list_item);
        VIEW_WIDTH = AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC_NEW;
        isCatExpandedOnce = false;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.92); // 80% of the view width

      //  svCatList = (ScrollView) findViewById(R.id.svCategoryListHolder);
        llCatListHolder = (LinearLayout) findViewById(R.id.llCategoryListHolder);
       // llSubCatListHolder = (LinearLayout) findViewById(R.id.llSubCatListHolder);
        ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();
        int s=lp.width = (int) (VIEW_WIDTH);
        FrameLayout.LayoutParams caTsList = (FrameLayout.LayoutParams) llCatListHolder.getLayoutParams();

        lp.height=100;

        if(height<1000)
        caTsList.setMargins(0, 60, 0, 0);
        else
            caTsList.setMargins(0, 10, 0, 0);


        Log.d(">>>>>>>>","View_width       "+s);
        /**
         * constructing category list
         **/
        CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivityNew.this);
        constructCategoryList(categoryTable.getAllCategories());
        //rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        //rlSubCatHolder.setVisibility(View.INVISIBLE);




        // callMapFragment();
        spItems = (Spinner) findViewById(R.id.areaitems);

        arrayAdapter = new ArrayAdapter(PlaceDetailsActivityNew.this,R.layout.area_row_spinner, listData);
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
                }
                else {locationNameId=AppConstants.PLACE_PARIS_ROAD;}
            if(mapcalledstatus){

            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }







    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }







    public void createData(int cat_id, String head,String placeChoice) {
        switch (cat_id) {
            case AppConstants.EDUCATION:

                SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNew.this);
                currentCategoryID = cat_id;
                EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivityNew.this);
                ArrayList<String> print = null;
                groups.removeAllElements();
                print = subCategoryTable.getSubnameedu(currentCategoryID, head);
                for (int j = 0; j < print.size(); j++) {
                    Group group = new Group(print.get(j));
                    printnames = null;
                    printnames = educationServiceProviderTable.Edunames(currentCategoryID, head, print.get(j), placeChoice);
                    for (int i = 0; i < printnames.size(); i++) {
                        group.children.add(i, printnames.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            case AppConstants.ENTERTAINMENT:

                SubCategoryTable subCategoryTable2 = new SubCategoryTable(PlaceDetailsActivityNew.this);
                currentCategoryID = cat_id;
                EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivityNew.this);
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

                SubCategoryTable subCategoryTable3 = new SubCategoryTable(PlaceDetailsActivityNew.this);
                currentCategoryID = cat_id;
                HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivityNew.this);
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

                SubCategoryTable subCategoryTable4 = new SubCategoryTable(PlaceDetailsActivityNew.this);
                currentCategoryID = cat_id;
                FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivityNew.this);
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

                SubCategoryTable subCategoryTable5 = new SubCategoryTable(PlaceDetailsActivityNew.this);
                currentCategoryID = cat_id;
                LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivityNew.this);
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

                SubCategoryTable subCategoryTable6= new SubCategoryTable(PlaceDetailsActivityNew.this);
                currentCategoryID = cat_id;
                JobServiceProviderTable jobServiceProviderTable = new JobServiceProviderTable(PlaceDetailsActivityNew.this);
                ArrayList<String> printjob = null;
                groups.removeAllElements();
                printjob  = subCategoryTable6.getSubnameedu(currentCategoryID, head);
                for (int j = 0; j < printjob.size(); j++) {
                    Group group = new Group(printjob.get(j));
                    printnamesjob = null;
                    printnamesjob = jobServiceProviderTable.Jobnames(currentCategoryID, head, printjob.get(j), placeChoice);
                    for (int i = 0; i < printnamesjob.size(); i++) {
                        group.childrenjob.add(i, printnamesjob.get(i));
                    }
                    groups.add(j, group);
                }
                break;
            default:break;
        }
    }







    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.sub_cat_item_list :

                break;

            default:
                break;

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Ignoring the device orientation change (always on portrait ensured) :: #HARD_CODED_(:()
        // super.onConfigurationChanged(newConfig);
    }


    private void callMapFragment()
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
        mapFragment.setMapIndicatorText("");
        mapFragment.setCategoryId(0);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
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
        ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconCatList);
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

                currentCategoryID = ci.getId();
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
                        mapcalledstatus=true;
                        map.setVisibility(View.VISIBLE);
                        toolbar.setVisibility(View.GONE);
                        toolbar2.setVisibility(View.VISIBLE);
                        listholder.setVisibility(View.VISIBLE);
                        listholder.setBackgroundColor(Color.parseColor("#58BED6"));
                        toolbar2.setBackgroundColor(Color.parseColor("#58BED6"));
                        toolbar2.startAnimation(slideInFromRightAnim());
                        listholder.startAnimation(slideInFromRightAnim());
                        setSupportActionBar(toolbar2);
                        ActionBar ab2 = getSupportActionBar();
                        ab2.setHomeAsUpIndicator(R.drawable.menu_icon);
                        ab2.setDisplayHomeAsUpEnabled(true);
                        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                                act, drawer, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

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
                        mapholderr.startAnimation(slideInFromRightAnim());

                        ab2.openOptionsMenu();
                        ArrayList<EducationServiceProviderItem> educationServiceProvider;
                        educationServiceProvider = constructEducationListItem(ci.getId());
                        callMapFragmentWithEducationInfo(ci.getCatName(), ci.getId(), educationServiceProvider);


                        break;
                    case AppConstants.HEALTH:
                        mapcalledstatus=true;
                        map.setVisibility(View.VISIBLE);
                        toolbar.setVisibility(View.GONE);
                        toolbar2.setVisibility(View.VISIBLE);
                        toolbar2.setBackgroundColor(Color.parseColor("#DF554E"));
                        toolbar2.startAnimation(slideInFromRightAnim());
                        listholder.setVisibility(View.VISIBLE);
                        listholder.setBackgroundColor(Color.parseColor("#DF554E"));
                        listholder.startAnimation(slideInFromRightAnim());
                        setSupportActionBar(toolbar2);
                        ActionBar ab3 = getSupportActionBar();
                        ab3.setHomeAsUpIndicator(R.drawable.menu_icon);
                        ab3.setDisplayHomeAsUpEnabled(true);
                        ActionBarDrawerToggle toggle3 = new ActionBarDrawerToggle(
                                act, drawer, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

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
                        toggle3.setDrawerIndicatorEnabled(true);
                        drawer.setDrawerListener(toggle3);
                        mapholderr.startAnimation(slideInFromRightAnim());
                        ArrayList<HealthServiceProviderItem> healthServiceProvider;
                        healthServiceProvider = constructHealthListItem(ci.getId());
                        callMapFragmentWithHealthInfo(ci.getCatName(), ci.getId(), healthServiceProvider);

                        break;

                    //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:
                        mapcalledstatus=true;
                        map.setVisibility(View.VISIBLE);
                        toolbar.setVisibility(View.GONE);
                        toolbar2.setVisibility(View.VISIBLE);
                        toolbar2.setBackgroundColor(Color.parseColor("#7377B7"));
                        toolbar2.startAnimation(slideInFromRightAnim());
                        listholder.setVisibility(View.VISIBLE);
                        listholder.setBackgroundColor(Color.parseColor("#7377B7"));
                        listholder.startAnimation(slideInFromRightAnim());
                        setSupportActionBar(toolbar2);
                        ActionBar ab4 = getSupportActionBar();
                        ab4.setHomeAsUpIndicator(R.drawable.menu_icon);
                        ab4.setDisplayHomeAsUpEnabled(true);
                        ActionBarDrawerToggle toggle4 = new ActionBarDrawerToggle(
                                act, drawer, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

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
                        toggle4.setDrawerIndicatorEnabled(true);
                        drawer.setDrawerListener(toggle4);
                        mapholderr.startAnimation(slideInFromRightAnim());
                        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
                        entertainmentServiceProvider = constructEntertainmentListItem(ci.getId());
                        callMapFragmentWithEntertainmentInfo(ci.getCatName(), ci.getId(), entertainmentServiceProvider);

                        break;


                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:

                        mapcalledstatus=false;
                        map.removeAllViews();
                        //TODO write necessary codes for government
                        toolbar2.setVisibility(View.GONE);
                        listholder.setVisibility(View.GONE);
                        toolbar.setVisibility(View.VISIBLE);
                        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(PlaceDetailsActivityNew.this).create();

                        alertDialog.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
                        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        alertDialog.dismiss();
                                    }
                                });
                        alertDialog.getWindow().setLayout(200, 300);
                        alertDialog.show();
                        break;
                    case AppConstants.LEGAL:
                        mapcalledstatus=true;
                        map.setVisibility(View.VISIBLE);
                        toolbar.setVisibility(View.GONE);
                        listholder.setVisibility(View.VISIBLE);
                        listholder.setBackgroundColor(Color.parseColor("#67C3A2"));
                        listholder.startAnimation(slideInFromRightAnim());
                        toolbar2.setVisibility(View.VISIBLE);
                        toolbar2.setBackgroundColor(Color.parseColor("#67C3A2"));
                        toolbar2.startAnimation(slideInFromRightAnim());
                        setSupportActionBar(toolbar2);
                        ActionBar ab5 = getSupportActionBar();
                        ab5.setHomeAsUpIndicator(R.drawable.menu_icon);
                        ab5.setDisplayHomeAsUpEnabled(true);
                        ActionBarDrawerToggle toggle5 = new ActionBarDrawerToggle(
                                act, drawer, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

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
                        toggle5.setDrawerIndicatorEnabled(true);
                        drawer.setDrawerListener(toggle5);
                        mapholderr.startAnimation(slideInFromRightAnim());
                        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
                        legalaidServiceProvider = constructlegalaidListItem(ci.getId());
                        callMapFragmentWithLegalAidInfo(ci.getCatName(), ci.getId(), legalaidServiceProvider);

                        break;
                    case AppConstants.FINANCIAL:
                        mapcalledstatus=true;
                        map.setVisibility(View.VISIBLE);
                        toolbar.setVisibility(View.GONE);
                        listholder.setVisibility(View.VISIBLE);
                        listholder.setBackgroundColor(Color.parseColor("#7a378b"));
                        listholder.startAnimation(slideInFromRightAnim());
                        toolbar2.setVisibility(View.VISIBLE);
                        toolbar2.setBackgroundColor(Color.parseColor("#7a378b"));
                        toolbar2.startAnimation(slideInFromRightAnim());
                        setSupportActionBar(toolbar2);
                        ActionBar ab6 = getSupportActionBar();
                        ab6.setHomeAsUpIndicator(R.drawable.menu_icon);
                        ab6.setDisplayHomeAsUpEnabled(true);
                        ActionBarDrawerToggle toggle6 = new ActionBarDrawerToggle(
                                act, drawer, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

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
                        toggle6.setDrawerIndicatorEnabled(true);
                        drawer.setDrawerListener(toggle6);
                        mapholderr.startAnimation(slideInFromRightAnim());
                        ArrayList<FinancialServiceProviderItem> financialServiceProvider;
                        financialServiceProvider = constructfinancialListItem(ci.getId());
                        callMapFragmentWithFinancialInfo(ci.getCatName(), ci.getId(), financialServiceProvider);

                        break;
                    case AppConstants.JOB:
                        mapcalledstatus=false;

                        map.removeAllViews();
                        toolbar2.setVisibility(View.GONE);
                        listholder.setVisibility(View.GONE);
                        toolbar.setVisibility(View.VISIBLE);
                        final android.app.AlertDialog alertDialog2 = new android.app.AlertDialog.Builder(PlaceDetailsActivityNew.this).create();

                        alertDialog2.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
                        alertDialog2.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
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

                if (isCatExpandedOnce)
                    showAnimatedSubcategories(subCatList, 0.5, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId()); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                else
                    showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS_NEW[ci.getId() - 1], ci.getId()); //AppConstants.CAT_LIST_LG_WIDTH_PERC);
            }
        });

        return v;
    }
    public void constructSubCategoryItemList(int cat_id,String header)
    {
        ArrayList<SubCategoryItem> subCategoryItems;
        subCategoryItems = constructSubCategoryListItem(cat_id,header);
        createData(cat_id,header,placeChoice);
        ArrayList<String> itemName = new ArrayList<String>();
        currentSubCategoryItem = subCategoryItems;
        for(SubCategoryItem si : subCategoryItems)
        {
            itemName.add(si.getSubCatName());
        }
        int i=0;



    //    subCatItemList = (ExpandableListView) findViewById(R.id.listView);

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, groups, cat_id);
     //   subCatItemList.setAdapter(adapter);


    }
    private ArrayList<SubCategoryItem> constructSubCategoryListItem(int cat_id, String header)
    {
        ArrayList<SubCategoryItem> subCategoryItems;
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNew.this);
        subCategoryItems=subCategoryTable.getAllSubCategoriesHeader(cat_id,header);

        return subCategoryItems;
    }


    private void constructSubCategoryList(ArrayList<SubCategoryItem> subCategoryList, double dwPercentage, int cat_id) {
       // llSubCatListHolder.removeAllViews();
        ArrayList<String> header = new ArrayList<>();
        subcategory=0;
        for (SubCategoryItem si : subCategoryList) {

            if(!header.contains(si.getSubcatHeader()))
            {
                header.add(si.getSubcatHeader());

               // llSubCatListHolder.addView(getSubCategoryListItemView(si,dwPercentage,cat_id));
            }
        }
    }
    private View getSubCategoryListItemView(final SubCategoryItem si, double dwPercentage, final int cat_id)
    {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        View v;
        LayoutInflater li = LayoutInflater.from(this);
        if(dpi>300)
            v = li.inflate(R.layout.sub_cat_list_item, llCatListHolder, false);
        else
        if(height>1000)
            v = li.inflate(R.layout.sub_cat_list_item, llCatListHolder, false);
        else
            v = li.inflate(R.layout.sub_cat_list_item1, llCatListHolder, false);
        ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_sub_cat_icon);
        TextView tvName = (TextView) v.findViewById(R.id.tv_sub_cat_name);
        if(height>1000)
            ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONS[ subcategory++]);
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
        tvName.setText(si.getSubcatHeader());

        tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));

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
                        ArrayList<EducationServiceProviderItem> eduItem;
                        eduItem = constructEducationListItemForHeader(cat_id, si.getSubcatHeader());
                        callMapFragmentWithEducationInfo(si.getSubcatHeader(), cat_id, eduItem);
                        break;
                    case AppConstants.HEALTH:
                        //TODO write necessary codes for health
                        ArrayList<HealthServiceProviderItem> healthItem;
                        healthItem = constructHealthListItemForHeader(cat_id, si.getSubcatHeader());
                        callMapFragmentWithHealthInfo(si.getSubcatHeader(), cat_id, healthItem);

                        break;
                    case AppConstants.ENTERTAINMENT:


                        ArrayList<EntertainmentServiceProviderItem> entItem;
                        entItem = constructEntertainmentListItemForHeader(cat_id, si.getSubcatHeader());
                        callMapFragmentWithEntertainmentInfo(si.getSubcatHeader(), cat_id, entItem);
                        break;
                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:
                        map.removeAllViews();
                        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(PlaceDetailsActivityNew.this).create();

                        alertDialog.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
                        alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        alertDialog.dismiss();
                                    }
                                });
                        alertDialog.getWindow().setLayout(200, 300);
                        alertDialog.show();
                        break;
                    case AppConstants.LEGAL:
                        ArrayList<LegalAidServiceProviderItem> legalItem;
                        legalItem = constructlegalaidListItemForHeader(cat_id, si.getSubcatHeader());
                        callMapFragmentWithLegalAidInfo(si.getSubcatHeader(), cat_id, legalItem);
                        break;
                    case AppConstants.FINANCIAL:
                        ArrayList<FinancialServiceProviderItem> financialItem;
                        financialItem = constructfinancialListItemForHeader(cat_id, si.getSubcatHeader());
                        callMapFragmentWithFinancialInfo(si.getSubcatHeader(), cat_id, financialItem);
                        break;
                    case AppConstants.JOB:
                        map.removeAllViews();
                        final android.app.AlertDialog alertDialog2 = new android.app.AlertDialog.Builder(PlaceDetailsActivityNew.this).create();

                        alertDialog2.setMessage("দুঃখিত! তথ্য পাওয়া যায় নি");
                        alertDialog2.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
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
                for(int i=0; i<((ViewGroup)v).getChildCount(); ++i) {
                    nextChild = ((ViewGroup)v).getChildAt(i);
                    nextChild.setBackgroundColor(p);
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
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivityNew.this);
        return subCategoryTable.getAllSubCategories(id);
    }


    private void showAnimatedSubcategories(final ArrayList<SubCategoryItem> subCatList, double dwPerc, int iconId, final int cat_id) {
        isCatExpandedOnce = true;
        decCatListWidth(dwPerc);

        // TODO Inflate the sub-category list from right
        final RelativeLayout rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        if(subCatShowFlag==1)
        {
           // rlSubCatHolder.startAnimation(slideOutFromLeftAnim());
        }
        subCatShowFlag=1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // rlSubCatHolder.setVisibility(View.VISIBLE);
             //   rlSubCatHolder.startAnimation(slideInFromRightAnim());
               // constructSubCategoryList(subCatList, 1.0, cat_id);
            }
        }, ANIM_INTERVAL *
                (int) (200 *
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
                Animation.RELATIVE_TO_PARENT, +0.95f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
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
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivityNew.this);
        educationServiceProvider = educationServiceProviderTable.getAllEducationSubCategoriesInfo(cat_id);
        return educationServiceProvider;
    }

    private ArrayList<EducationServiceProviderItem> constructEducationListItemForHeader(int cat_id, String header)
    {
        ArrayList<EducationServiceProviderItem> educationServiceProvider;
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivityNew.this);
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

    /***********************************************************Methods for Health*************************************************/

    private ArrayList<HealthServiceProviderItem> constructHealthListItem(int cat_id)
    {
        ArrayList<HealthServiceProviderItem> healthServiceProvider;
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivityNew.this);
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
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivityNew.this);
        healthServiceProvider = healthServiceProviderTable.getAllHealthSubCategoriesInfoWithHead(cat_id, header);
        return healthServiceProvider;
    }

    /**********************************************************Methods for entertainment*******************************************/

    private ArrayList<EntertainmentServiceProviderItem> constructEntertainmentListItem(int cat_id)
    {
        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivityNew.this);
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
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivityNew.this);
        entertainmentServiceProvider = entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfoWithHead(cat_id, header);
        return entertainmentServiceProvider;
    }



    /**********************************************************Methods for government**********************************************/




    /**********************************************************Methods for legal***************************************************/

    private ArrayList<LegalAidServiceProviderItem> constructlegalaidListItem(int cat_id)
    {
        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivityNew.this);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfo(cat_id);
        return legalaidServiceProvider;
    }

    private ArrayList<LegalAidServiceProviderItem> constructlegalaidListItemForHeader(int cat_id, String header)
    {
        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivityNew.this);
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
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivityNew.this);
        financialServiceProvider = financialServiceProviderTable.getAllFinancialSubCategoriesInfo(cat_id);
        return financialServiceProvider;
    }

    private ArrayList<FinancialServiceProviderItem> constructfinancialListItemForHeader(int cat_id, String header)
    {
        ArrayList<FinancialServiceProviderItem> financialServiceProvider;
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivityNew.this);
        financialServiceProvider = financialServiceProviderTable.getAllFinancialSubCategoriesInfoWithHead(cat_id, header);
        return financialServiceProvider;
    }

    private void callMapFragmentWithFinancialInfo(String item_name,int cat_id,ArrayList<FinancialServiceProviderItem> financialServiceProviderItems)
    {    MapFragmentOSM mapFragment = new MapFragmentOSM();
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

    private ArrayList<JobServiceProviderItem> constructjobListItem(int cat_id)
    {
        ArrayList<JobServiceProviderItem> jobServiceProvider;
        JobServiceProviderTable jobServiceProviderTable = new JobServiceProviderTable(PlaceDetailsActivityNew.this);
        jobServiceProvider = jobServiceProviderTable.getAllJobSubCategoriesInfo(cat_id);
        return jobServiceProvider;
    }

    private ArrayList<JobServiceProviderItem> constructjobListItemForHeader(int cat_id, String header)
    {
        ArrayList<JobServiceProviderItem> jobServiceProvider;
        JobServiceProviderTable jobServiceProviderTable = new JobServiceProviderTable(PlaceDetailsActivityNew.this);
        jobServiceProvider = jobServiceProviderTable.getAllJobSubCategoriesInfoWithHead(cat_id, header);
        return jobServiceProvider;
    }

    private void callMapFragmentWithJobInfo(String item_name,int cat_id,ArrayList<JobServiceProviderItem> jobServiceProviderItems)
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(getPlaceChoice());
        mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);
        mapFragment.setJobServiceProvider(jobServiceProviderItems);
        mapFragment.setLocationNameId(locationNameId);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
    }


    public void implementRouteDrawingFragment()
    {
        MapRouteDrawingFragment mapRouteDrawingFragment = new MapRouteDrawingFragment();
        map.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapRouteDrawingFragment);
        fragmentTransaction.commit();
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
        SharedPreferences pref = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();

        String Longitude = pref.getString("Latitude", null);
        String Latitude = pref.getString("Longitude", null);


        Intent intent;
        intent = getIntent();
        if (null != intent)
        {
            locationNameId = intent.getIntExtra(AppConstants.KEY_PLACE,0);
            if(locationNameId== AppConstants.PLACE_BAUNIABADH)
            {
                locationName = AppConstants.BAUNIABADH;
                setPlaceChoice(locationName);
            }
            else if(locationNameId== AppConstants.PLACE_PARIS_ROAD)
            {
                locationName = AppConstants.PARIS_ROAD;
                setPlaceChoice(locationName);
            }
        }
        editor.putInt("LocationNameId", locationNameId);
        editor.commit();

        if (Latitude != null&& AppUtils.isNetConnected(getApplicationContext())) {
            Double Lon = Double.parseDouble(Longitude);
            Double Lat = Double.parseDouble(Latitude);

            implementRouteDrawingFragment();


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