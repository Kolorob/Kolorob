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
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentOSM;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
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
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
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


/**
 *
 *
 * @author israt,arafat
 */


/*
* Debug to understand this activity. It has all the codes no fragment been used for search/bazar or compare or map.Since earlier structure
* got changed multiple times so it would be wise to check which part is doing what using debug*/


public class PlaceDetailsActivityNewLayout <ModelType extends CommonModel> extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

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



    TextView welcomeText, upText;
    TextView health_name1, health_name2, edu_name1, edu_name2;
    TextView tvName;

    CheckedTextView changeArea;

    private int primaryIconWidth;
    private int subcategory;
    int spinCount1, spinCount2;
    int filterCategoryId;

    String [] left_part, right_part, health_header;

    boolean doubleBackToExitPressedOnce, reviewGiven, selected, inCompare;
    boolean eduClicked, healthClicked, entClicked, finClicked, govClicked, legalClicked, ngoClicked, shelterClicked;
    Boolean searchClicked = false, jobClicked, compareClicked, mapClicked = true;
    boolean filterClicked, catStatus, called;
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
    private ImageButton mapButton, jobButton, searchButton, compareButton;


    EditText searchAll, filterText;
    ListViewAdapterAllCategories adapter;

    private int small;
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

    StoredAreaTable storedAreaTable;
    CategoryItem categoryItem;




    public Context getCon() {
        return con;
    }

    public void setCon(Context con) {
        this.con = con;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<StoredArea> getStoredAreaArrayList() {
        return storedAreaArrayList;
    }

    public void setStoredAreaArrayList(ArrayList<StoredArea> storedAreaArrayList) {
        this.storedAreaArrayList = storedAreaArrayList;
    }

    public ArrayList<StoredArea> getStoredAreaArrayListall() {
        return storedAreaArrayListall;
    }

    public void setStoredAreaArrayListall(ArrayList<StoredArea> storedAreaArrayListall) {
        this.storedAreaArrayListall = storedAreaArrayListall;
    }

    public ArrayList<StoredArea> getStoredAreas() {
        return storedAreas;
    }

    public void setStoredAreas(ArrayList<StoredArea> storedAreas) {
        this.storedAreas = storedAreas;
    }

    public ArrayList<EduNewModel> getFirstDataSetEdu() {
        return firstDataSetEdu;
    }

    public void setFirstDataSetEdu(ArrayList<EduNewModel> firstDataSetEdu) {
        this.firstDataSetEdu = firstDataSetEdu;
    }

    public ArrayList<EduNewModel> getSecondDataSetEdu() {
        return secondDataSetEdu;
    }

    public void setSecondDataSetEdu(ArrayList<EduNewModel> secondDataSetEdu) {
        this.secondDataSetEdu = secondDataSetEdu;
    }

    public ArrayList<HealthNewDBModelMain> getFirstDataSetHealth() {
        return firstDataSetHealth;
    }

    public void setFirstDataSetHealth(ArrayList<HealthNewDBModelMain> firstDataSetHealth) {
        this.firstDataSetHealth = firstDataSetHealth;
    }

    public ArrayList<HealthNewDBModelMain> getSecondDataSetHealth() {
        return secondDataSetHealth;
    }

    public void setSecondDataSetHealth(ArrayList<HealthNewDBModelMain> secondDataSetHealth) {
        this.secondDataSetHealth = secondDataSetHealth;
    }

    public ArrayList<CategoryItem> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<CategoryItem> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<String> getHeaderHolder() {
        return headerHolder;
    }

    public void setHeaderHolder(ArrayList<String> headerHolder) {
        this.headerHolder = headerHolder;
    }

    public ArrayList<String> getClicked() {
        return clicked;
    }

    public void setClicked(ArrayList<String> clicked) {
        this.clicked = clicked;
    }

    public ArrayList<String> getFilter() {
        return filter;
    }

    public void setFilter(ArrayList<String> filter) {
        this.filter = filter;
    }

    public ArrayList<String> getFilter2() {
        return filter2;
    }

    public void setFilter2(ArrayList<String> filter2) {
        this.filter2 = filter2;
    }

    public ArrayList<AllHolder> getAllHolders() {
        return allHolders;
    }

    public void setAllHolders(ArrayList<AllHolder> allHolders) {
        this.allHolders = allHolders;
    }

    public ArrayList<AllHolder> getCatHolders() {
        return catHolders;
    }

    public void setCatHolders(ArrayList<AllHolder> catHolders) {
        this.catHolders = catHolders;
    }

    public ArrayList<AllHolder> getSubcatHolders() {
        return subcatHolders;
    }

    public void setSubcatHolders(ArrayList<AllHolder> subcatHolders) {
        this.subcatHolders = subcatHolders;
    }

    public ArrayList<Subcatholder> getTagHolders() {
        return tagHolders;
    }

    public void setTagHolders(ArrayList<Subcatholder> tagHolders) {
        this.tagHolders = tagHolders;
    }


    public TextView getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(TextView welcomeText) {
        this.welcomeText = welcomeText;
    }

    public TextView getUpText() {
        return upText;
    }

    public void setUpText(TextView upText) {
        this.upText = upText;
    }

    public TextView getHealth_name1() {
        return health_name1;
    }

    public void setHealth_name1(TextView health_name1) {
        this.health_name1 = health_name1;
    }

    public TextView getHealth_name2() {
        return health_name2;
    }

    public void setHealth_name2(TextView health_name2) {
        this.health_name2 = health_name2;
    }

    public TextView getEdu_name1() {
        return edu_name1;
    }

    public void setEdu_name1(TextView edu_name1) {
        this.edu_name1 = edu_name1;
    }

    public TextView getEdu_name2() {
        return edu_name2;
    }

    public void setEdu_name2(TextView edu_name2) {
        this.edu_name2 = edu_name2;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public CheckedTextView getChangeArea() {
        return changeArea;
    }

    public void setChangeArea(CheckedTextView changeArea) {
        this.changeArea = changeArea;
    }

    public int getPrimaryIconWidth() {
        return primaryIconWidth;
    }

    public void setPrimaryIconWidth(int primaryIconWidth) {
        this.primaryIconWidth = primaryIconWidth;
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public String[] getLeft_part() {
        return left_part;
    }

    public void setLeft_part(String[] left_part) {
        this.left_part = left_part;
    }

    public String[] getRight_part() {
        return right_part;
    }

    public void setRight_part(String[] right_part) {
        this.right_part = right_part;
    }

    public String[] getHealth_header() {
        return health_header;
    }

    public void setHealth_header(String[] health_header) {
        this.health_header = health_header;
    }

    public boolean isDoubleBackToExitPressedOnce() {
        return doubleBackToExitPressedOnce;
    }

    public void setDoubleBackToExitPressedOnce(boolean doubleBackToExitPressedOnce) {
        this.doubleBackToExitPressedOnce = doubleBackToExitPressedOnce;
    }

    public boolean isReviewGiven() {
        return reviewGiven;
    }

    public void setReviewGiven(boolean reviewGiven) {
        this.reviewGiven = reviewGiven;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isInCompare() {
        return inCompare;
    }

    public void setInCompare(boolean inCompare) {
        this.inCompare = inCompare;
    }

    public boolean isEduClicked() {
        return eduClicked;
    }

    public void setEduClicked(boolean eduClicked) {
        this.eduClicked = eduClicked;
    }

    public boolean isHealthClicked() {
        return healthClicked;
    }

    public void setHealthClicked(boolean healthClicked) {
        this.healthClicked = healthClicked;
    }

    public boolean isEntClicked() {
        return entClicked;
    }

    public void setEntClicked(boolean entClicked) {
        this.entClicked = entClicked;
    }

    public boolean isFinClicked() {
        return finClicked;
    }

    public void setFinClicked(boolean finClicked) {
        this.finClicked = finClicked;
    }

    public boolean isGovClicked() {
        return govClicked;
    }

    public void setGovClicked(boolean govClicked) {
        this.govClicked = govClicked;
    }

    public boolean isLegalClicked() {
        return legalClicked;
    }

    public void setLegalClicked(boolean legalClicked) {
        this.legalClicked = legalClicked;
    }

    public boolean isNgoClicked() {
        return ngoClicked;
    }

    public void setNgoClicked(boolean ngoClicked) {
        this.ngoClicked = ngoClicked;
    }

    public boolean isShelterClicked() {
        return shelterClicked;
    }

    public void setShelterClicked(boolean shelterClicked) {
        this.shelterClicked = shelterClicked;
    }

    public Boolean getSearchClicked() {
        return searchClicked;
    }

    public void setSearchClicked(Boolean searchClicked) {
        this.searchClicked = searchClicked;
    }

    public Boolean getJobClicked() {
        return jobClicked;
    }

    public void setJobClicked(Boolean listClicked) {
        this.jobClicked = listClicked;
    }

    public Boolean getCompareClicked() {
        return compareClicked;
    }

    public void setCompareClicked(Boolean compareClicked) {
        this.compareClicked = compareClicked;
    }

    public Boolean getMapClicked() {
        return mapClicked;
    }

    public void setMapClicked(Boolean mapClicked) {
        this.mapClicked = mapClicked;
    }

    public boolean isFilterClicked() {
        return filterClicked;
    }

    public void setFilterClicked(boolean filterClicked) {
        this.filterClicked = filterClicked;
    }

    public boolean isCatStatus() {
        return catStatus;
    }

    public void setCatStatus(boolean catStatus) {
        this.catStatus = catStatus;
    }

    public Boolean getFirstRun() {
        return firstRun;
    }

    public void setFirstRun(Boolean firstRun) {
        this.firstRun = firstRun;
    }

    public Boolean getFirstRunUpdate() {
        return firstRunUpdate;
    }

    public void setFirstRunUpdate(Boolean firstRunUpdate) {
        this.firstRunUpdate = firstRunUpdate;
    }

    public Boolean getNavigationCalled() {
        return navigationCalled;
    }

    public void setNavigationCalled(Boolean navigationCalled) {
        this.navigationCalled = navigationCalled;
    }

    public Boolean getNavigationCalledOnce() {
        return navigationCalledOnce;
    }

    public void setNavigationCalledOnce(Boolean navigationCalledOnce) {
        this.navigationCalledOnce = navigationCalledOnce;
    }

    public LinearLayout getWholeLayout() {
        return wholeLayout;
    }

    public void setWholeLayout(LinearLayout wholeLayout) {
        this.wholeLayout = wholeLayout;
    }

    public LinearLayout getCompare_layout() {
        return compare_layout;
    }

    public void setCompare_layout(LinearLayout compare_layout) {
        this.compare_layout = compare_layout;
    }

    public LinearLayout getCompare_layout_edu() {
        return compare_layout_edu;
    }

    public void setCompare_layout_edu(LinearLayout compare_layout_edu) {
        this.compare_layout_edu = compare_layout_edu;
    }

    public LinearLayout getfHolder() {
        return fHolder;
    }

    public void setfHolder(LinearLayout fHolder) {
        this.fHolder = fHolder;
    }

    public LinearLayout getfLeft() {
        return fLeft;
    }

    public void setfLeft(LinearLayout fLeft) {
        this.fLeft = fLeft;
    }

    public LinearLayout getfRight() {
        return fRight;
    }

    public void setfRight(LinearLayout fRight) {
        this.fRight = fRight;
    }

    public LinearLayout getLlCatListHolder() {
        return llCatListHolder;
    }

    public void setLlCatListHolder(LinearLayout llCatListHolder) {
        this.llCatListHolder = llCatListHolder;
    }

    public LinearLayout getSvHolder() {
        return svHolder;
    }

    public void setSvHolder(LinearLayout svHolder) {
        this.svHolder = svHolder;
    }

    public RelativeLayout getCatHolder() {
        return catHolder;
    }

    public void setCatHolder(RelativeLayout catHolder) {
        this.catHolder = catHolder;
    }

    public RelativeLayout getSearchViewHolder() {
        return searchViewHolder;
    }

    public void setSearchViewHolder(RelativeLayout searchViewHolder) {
        this.searchViewHolder = searchViewHolder;
    }

    public RelativeLayout getFilterHolder() {
        return filterHolder;
    }

    public void setFilterHolder(RelativeLayout filterHolder) {
        this.filterHolder = filterHolder;
    }

    public ListView getHealth_compare_list() {
        return health_compare_list;
    }

    public void setHealth_compare_list(ListView health_compare_list) {
        this.health_compare_list = health_compare_list;
    }

    public ListView getEducation_compare_list() {
        return education_compare_list;
    }

    public void setEducation_compare_list(ListView education_compare_list) {
        this.education_compare_list = education_compare_list;
    }

    public ListView getAllItemList() {
        return allItemList;
    }

    public void setAllItemList(ListView allItemList) {
        this.allItemList = allItemList;
    }

    public ToggleButton getToggleButton() {
        return toggleButton;
    }

    public void setToggleButton(ToggleButton toggleButton) {
        this.toggleButton = toggleButton;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }

    public static int getAnimInterval() {
        return ANIM_INTERVAL;
    }

    public static double getViewWidth() {
        return VIEW_WIDTH;
    }

    public static void setViewWidth(double viewWidth) {
        VIEW_WIDTH = viewWidth;
    }

    public static boolean isMapcalledstatus() {
        return mapcalledstatus;
    }

    public static void setMapcalledstatus(boolean mapcalledstatus) {
        PlaceDetailsActivityNewLayout.mapcalledstatus = mapcalledstatus;
    }

    public static FrameLayout getMap() {
        return map;
    }

    public static void setMap(FrameLayout map) {
        PlaceDetailsActivityNewLayout.map = map;
    }

    public ScrollView getSv() {
        return sv;
    }

    public void setSv(ScrollView sv) {
        this.sv = sv;
    }

    public String getComapareData() {
        return comapareData;
    }

    public void setComapareData(String comapareData) {
        this.comapareData = comapareData;
    }

    public String getFirstData() {
        return firstData;
    }

    public void setFirstData(String firstData) {
        this.firstData = firstData;
    }

    public String getSecondData() {
        return secondData;
    }

    public void setSecondData(String secondData) {
        this.secondData = secondData;
    }

    public CheckBox getCheckBox1() {
        return checkBox1;
    }

    public void setCheckBox1(CheckBox checkBox1) {
        this.checkBox1 = checkBox1;
    }

    public CheckBox getCheckBox2() {
        return checkBox2;
    }

    public void setCheckBox2(CheckBox checkBox2) {
        this.checkBox2 = checkBox2;
    }

    public CheckBox getCheckLeft() {
        return checkLeft;
    }

    public void setCheckLeft(CheckBox checkLeft) {
        this.checkLeft = checkLeft;
    }

    public CheckBox getCheckRight() {
        return checkRight;
    }

    public void setCheckRight(CheckBox checkRight) {
        this.checkRight = checkRight;
    }

    public CheckBox getNegotiable() {
        return negotiable;
    }

    public void setNegotiable(CheckBox negotiable) {
        this.negotiable = negotiable;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public int[] getFlag2() {
        return flag2;
    }

    public void setFlag2(int[] flag2) {
        this.flag2 = flag2;
    }

    public static int getCurrentCategoryID() {
        return currentCategoryID;
    }

    public static void setCurrentCategoryID(int currentCategoryID) {
        PlaceDetailsActivityNewLayout.currentCategoryID = currentCategoryID;
    }

    public String getPlaceChoice() {
        return placeChoice;
    }

    public void setPlaceChoice(String placeChoice) {
        this.placeChoice = placeChoice;
    }

    public String getFilterWord() {
        return filterWord;
    }

    public void setFilterWord(String filterWord) {
        this.filterWord = filterWord;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public ImageButton getMapButton() {
        return mapButton;
    }

    public void setMapButton(ImageButton mapButton) {
        this.mapButton = mapButton;
    }

    public ImageButton getJobButton() {
        return jobButton;
    }

    public void setJobButton(ImageButton jobButton) {
        this.jobButton = jobButton;
    }

    public ImageButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(ImageButton searchButton) {
        this.searchButton = searchButton;
    }

    public ImageButton getCompareButton() {
        return compareButton;
    }

    public void setCompareButton(ImageButton compareButton) {
        this.compareButton = compareButton;
    }

    public EditText getSearchAll() {
        return searchAll;
    }

    public void setSearchAll(EditText searchAll) {
        this.searchAll = searchAll;
    }

    public EditText getFilterText() {
        return filterText;
    }

    public void setFilterText(EditText filterText) {
        this.filterText = filterText;
    }

    public ListViewAdapterAllCategories getAdapter() {
        return adapter;
    }

    public void setAdapter(ListViewAdapterAllCategories adapter) {
        this.adapter = adapter;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getsNumber() {
        return sNumber;
    }

    public void setsNumber(int sNumber) {
        this.sNumber = sNumber;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }

    public void setButtonHeight(int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }

    public int getButtonWidth() {
        return buttonWidth;
    }

    public void setButtonWidth(int buttonWidth) {
        this.buttonWidth = buttonWidth;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }

    public String getId4() {
        return id4;
    }

    public void setId4(String id4) {
        this.id4 = id4;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public boolean isMapFirst() {
        return mapFirst;
    }

    public void setMapFirst(boolean mapFirst) {
        this.mapFirst = mapFirst;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MapFragmentOSM getMapFragment() {
        return mapFragment;
    }

    public void setMapFragment(MapFragmentOSM mapFragment) {
        this.mapFragment = mapFragment;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getAreaKeyword() {
        return areaKeyword;
    }

    public void setAreaKeyword(String areaKeyword) {
        this.areaKeyword = areaKeyword;
    }

    public String getLat() {
        return lat;
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

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView2() {
        return view2;
    }

    public void setView2(View view2) {
        this.view2 = view2;
    }

    public ActionBarDrawerToggle getToggle() {
        return toggle;
    }

    public void setToggle(ActionBarDrawerToggle toggle) {
        this.toggle = toggle;
    }

    public RadioGroup getLeftGroup() {
        return leftGroup;
    }

    public void setLeftGroup(RadioGroup leftGroup) {
        this.leftGroup = leftGroup;
    }

    public RadioGroup getRightGroup() {
        return rightGroup;
    }

    public void setRightGroup(RadioGroup rightGroup) {
        this.rightGroup = rightGroup;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public void setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    public Animation getmEnterAnimation() {
        return mEnterAnimation;
    }

    public void setmEnterAnimation(Animation mEnterAnimation) {
        this.mEnterAnimation = mEnterAnimation;
    }

    public Animation getmExitAnimation() {
        return mExitAnimation;
    }

    public void setmExitAnimation(Animation mExitAnimation) {
        this.mExitAnimation = mExitAnimation;
    }

    public int getSpinCount1() {
        return spinCount1;
    }

    public void setSpinCount1(int spinCount1) {
        this.spinCount1 = spinCount1;
    }

    public int getSpinCount2() {
        return spinCount2;
    }

    public void setSpinCount2(int spinCount2) {
        this.spinCount2 = spinCount2;
    }

    public int getFilterCategoryId() {
        return filterCategoryId;
    }

    public void setFilterCategoryId(int filterCategoryId) {
        this.filterCategoryId = filterCategoryId;
    }

    public StoredAreaTable getStoredAreaTable() {
        return storedAreaTable;
    }

    public void setStoredAreaTable(StoredAreaTable storedAreaTable) {
        this.storedAreaTable = storedAreaTable;
    }

    public CategoryItem getCategoryItem() {
        return categoryItem;
    }

    public void setCategoryItem(CategoryItem categoryItem) {
        this.categoryItem = categoryItem;
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
        firstRun = settings.getBoolean("firstRunUp", false);
        firstRunUpdate = settings.getBoolean("update_first_run", true);

        wardId = settings.getString("_ward", null);
        areaKeyword = settings.getString("areakeyword", null);

        Log.e("","Ward ID: " + wardId + "Area: " + areaKeyword);

        storedAreaTable = new StoredAreaTable(PlaceDetailsActivityNewLayout.this);
        storedAreaArrayListall = storedAreaTable.getAllData();

        storedAreas = storedAreaTable.getStoredLocation(wardId, areaKeyword);

        if(storedAreaArrayListall.size() == 0 || storedAreas.size() == 0) {

            Intent em = new Intent(this, DataLoadingActivity.class);
            startActivity(em);
            finish();
        }
       /* else if (storedAreas.size()==0) {

            Intent em = new Intent(this, AreaUpgrade.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }*/
        else {

            setAreaName(storedAreas.get(0).getAreaBn());
            setLat(storedAreas.get(0).getLat());
            setLon(storedAreas.get(0).getLon());

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
            mapButton = (ImageButton) findViewById(R.id.mapbutton);
            jobButton = (ImageButton) findViewById(R.id.jobButton);
            searchButton = (ImageButton) findViewById(R.id.searchbutton);
            compareButton = (ImageButton) findViewById(R.id.compare);
            searchViewHolder = (RelativeLayout) findViewById(R.id.searchholder);


            buttonWidth = width / 4;
            buttonHeight = height / 20;
            allItemList = (ListView) findViewById(R.id.allitem);
            checkBox1 = (CheckBox) findViewById(R.id.compared);
            checkBox2 = (CheckBox) findViewById(R.id.compared2);

            health_compare_list = (ListView) findViewById(R.id.health_compare_list);
            education_compare_list = (ListView) findViewById(R.id.education_compare_list);


            checkLeft = (CheckBox) findViewById(R.id.checkLeft);
            checkRight = (CheckBox) findViewById(R.id.checkRight);

            catHolder = (RelativeLayout) findViewById(R.id.categoryfilterholder);

            final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mapButton.getLayoutParams();
            params.weight = 1;
            params.width = buttonWidth;
            double d = buttonWidth * 0.56;
            double large = buttonWidth * 0.69;
            final int larg = (int) Math.round(large);
            small = (int) Math.round(d);
            params.height = larg;
            compare_layout = (LinearLayout) findViewById(R.id.compare_layout);


            LinearLayout.LayoutParams com_layout = (LinearLayout.LayoutParams) compare_layout.getLayoutParams();
            com_layout.setMargins(0, 0, 0, small);

            compare_layout.setLayoutParams(com_layout);


            mapButton.setLayoutParams(params);

            final LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) searchButton.getLayoutParams();
            params2.weight = 1;
            params2.width = buttonWidth;
            params2.height = (int) Math.round(d);
            buttonHeight = (int) Math.round(d);
            searchButton.setLayoutParams(params2);
            Picasso.with(this)
                    .load(R.drawable.search)
                    .resize(buttonWidth, small)
                    .into(searchButton);
            final LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) jobButton.getLayoutParams();
            params3.weight = 1;
            params3.width = buttonWidth;
            params3.height = (int) Math.round(d);
            jobButton.setLayoutParams(params3);

            Picasso.with(this)
                    .load(R.drawable.job_unselectedtab)
                    .resize(buttonWidth, small)
                    .into(jobButton);
            jobButton.getHeight();
            final LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) compareButton.getLayoutParams();
            params4.weight = 1;
            params4.width = buttonWidth;
            params4.height = (int) Math.round(d);
            compareButton.setLayoutParams(params4);

            Picasso.with(this)
                    .load(R.drawable.compare)
                    .resize(buttonWidth, small)
                    .into(compareButton);


            mapcalledstatus = false;
            toolbar = (Toolbar) findViewById(R.id.categorytoolbar);

            welcomeText=(TextView)findViewById(R.id.toptext);
            SharedPreferencesHelper.setCompareData(PlaceDetailsActivityNewLayout.this, "", 0);
            searchAll = (EditText) findViewById(R.id.searchall);


            filterHolder = (RelativeLayout) findViewById(R.id.filterholder);
            upText = (TextView)findViewById(R.id.textView15);
            changeArea = (CheckedTextView)findViewById(R.id.changearea);
            upText.setText(R.string.menu);
            changeArea.setText(R.string.change_area);
            changeArea.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                /*Intent em = new Intent(PlaceDetailsActivityNewLayout.this, AreaUpgrade.class);
                startActivity(em);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);*/

                storedAreaArrayListall = storedAreaTable.getAllData();

                Intent em = new Intent(PlaceDetailsActivityNewLayout.this, DataLoadingActivity.class);
                startActivity(em);
                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


                }
            });


            setSupportActionBar(toolbar);
            ActionBar ab = getSupportActionBar();
            ab.setHomeAsUpIndicator(R.drawable.menu_icon);
            ab.setDisplayHomeAsUpEnabled(true);

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

                /** Called when a drawer has settled in a completely open state. */
                @SuppressLint("RestrictedApi")
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    view = navigationView.getTouchables().get(2);
                    view2 =    navigationView.getTouchables().get(4);

                    if(!firstRun || firstRunUpdate)runOverlay_ContinueMethodnavigation();
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
            screenSize = AppUtils.ScreenSize(PlaceDetailsActivityNewLayout.this);


//        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
            wholeLayout = (LinearLayout) findViewById(R.id.wholeLayout);
            mEnterAnimation = new AlphaAnimation(0f, 1f);
            mEnterAnimation.setDuration(600);
            mEnterAnimation.setFillAfter(true);

            mExitAnimation = new AlphaAnimation(1f, 0f);
            mExitAnimation.setDuration(600);
            mExitAnimation.setFillAfter(true);
            String AREA = getAreaName();
            AREA= AREA.replace(' ','\n');
            welcomeText.setText(AREA);
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
            compare_layout_edu = (LinearLayout) findViewById(compare_layoutedu);
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


            populateHolder();
            try {
                callMapFragment(lat, lon);
            } catch (Exception e) {

            }

            if(!firstRun || firstRunUpdate) runOverlay_ContinueMethod();

        /*Lower four buttons action are here. Since selected buttons size changes so others been marked not clicked one been marked clicked
        * and so on. Please DEBUG. Subcategory panels wont be visible in case of SearchButton Clicked.Category/subcategory/toggle wont be
        * shown if compare/bazar clicked(ListClicked)*/

            mapButton.setBackgroundResource(R.drawable.map_selected);

            Picasso.with(this)
                    .load(R.drawable.map_selected)
                    .resize(buttonWidth, larg)
                    .into(mapButton);


            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchClicked = true;
                    mapClicked = false;
                    jobClicked = false;
                    compareClicked = false;
                    inCompare = false;
                    wholeLayout.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this, R.color.white));
                    toolbar.setVisibility(View.VISIBLE);


                    populateSearch();

                    if (!compareClicked || !mapClicked || !jobClicked) {

                        Picasso.with(getApplicationContext())
                                .load(R.drawable.map)
                                .resize(buttonWidth, small)
                                .into(mapButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.compare)
                                .resize(buttonWidth, small)
                                .into(compareButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.job_unselectedtab)
                                .resize(buttonWidth, small)
                                .into(jobButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.search_selected)
                                .resize(buttonWidth, larg)
                                .into(searchButton);

                        params2.height = larg;
                        searchButton.setLayoutParams(params2);
                        params.height = small;
                        mapButton.setLayoutParams(params);
                        params3.height = small;
                        jobButton.setLayoutParams(params3);
                        params4.height = small;
                        compareButton.setLayoutParams(params4);

                        map.setVisibility(View.GONE);
                        svHolder.setVisibility(View.GONE);
                        sv.setVisibility(View.GONE);

                        toggleButton.setVisibility(View.VISIBLE);
                        compare_layout.setVisibility(View.GONE);
                        compare_layout_edu.setVisibility(View.GONE);
                        searchViewHolder.setVisibility(View.VISIBLE);
                    }
                    if (eduClicked || healthClicked || entClicked || legalClicked || finClicked || govClicked || ngoClicked || shelterClicked) {

                        filterHolder.setVisibility(View.VISIBLE);
                        toggleButton.setVisibility(View.VISIBLE);
                    } else {
                        filterHolder.setVisibility(View.GONE);
                    }

                    svHolder.setVisibility(View.VISIBLE);
                    sv.setVisibility(View.VISIBLE);

                    llCatListHolder.setVisibility(View.VISIBLE);
                    toggleButton.setVisibility(View.VISIBLE);

                }
            });

            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchClicked = false;
                    mapClicked = true;
                    jobClicked = false;
                    compareClicked = false;

                    if (!inCompare) {
                        //  callMapFragment(locationNameId);

                    }

                    if (!compareClicked || !searchClicked || !jobClicked) {
                        Picasso.with(getApplicationContext())
                                .load(R.drawable.map_selected)
                                .resize(buttonWidth, larg)
                                .into(mapButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.compare)
                                .resize(buttonWidth, small)
                                .into(compareButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.job_unselectedtab)
                                .resize(buttonWidth, small)
                                .into(jobButton);


                        Picasso.with(getApplicationContext())
                                .load(R.drawable.search)
                                .resize(buttonWidth, small)
                                .into(searchButton);
                        params.height = larg;
                        mapButton.setLayoutParams(params);

                        params2.height = small;
                        searchButton.setLayoutParams(params2);
                        params3.height = small;
                        jobButton.setLayoutParams(params3);
                        params4.height = small;
                        compareButton.setLayoutParams(params4);

//                    subCatItemList.setVisibility(View.GONE);

                        searchViewHolder.setVisibility(View.GONE);
                        compare_layout.setVisibility(View.GONE);
                        compare_layout_edu.setVisibility(View.GONE);
                    }
                    toggleButton.setVisibility(View.VISIBLE);
                    map.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.VISIBLE);


                }
            });

            jobButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //if ((AppUtils.isNetConnected(getApplicationContext())) && (ContextCompat.checkSelfPermission(PlaceDetailsActivityNewLayout.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {

                        searchClicked = false;
                        mapClicked = false;
                        inCompare = false;
                        jobClicked = true;
                        compareClicked = false;
                        searchViewHolder.setVisibility(View.GONE);
                        llCatListHolder.setVisibility(View.VISIBLE);

                        if (!mapClicked || !searchClicked || !compareClicked) {
                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.map)
                                    .resize(buttonWidth, small)
                                    .into(mapButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.compare)
                                    .resize(buttonWidth, small)
                                    .into(compareButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.job_selectedtab)
                                    .resize(buttonWidth, larg)
                                    .into(jobButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.search)
                                    .resize(buttonWidth, small)
                                    .into(searchButton);
                            params3.height = larg;
                            jobButton.setLayoutParams(params3);
                            params2.height = small;
                            searchButton.setLayoutParams(params2);
                            params.height = small;
                            mapButton.setLayoutParams(params);
                            params4.height = small;
                            compareButton.setLayoutParams(params4);

                            map.setVisibility(View.VISIBLE);
                        }

                        searchViewHolder.setVisibility(View.GONE);
                        compare_layout.setVisibility(View.GONE);
                        compare_layout_edu.setVisibility(View.GONE);

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
                        bodys.setText(R.string.job_portal_coming_soon);
                        bodys.setTextColor(getResources().getColor(R.color.Black));
                        okay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.cancel();
                            }
                        });

                        //alertDialog.setCancelable(false);

                        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
                        lp.dimAmount = 0.0f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
                        alertDialog.getWindow().setAttributes(lp);

                        alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);


                }

            });
            compareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (currentCategoryID == 10000 || currentCategoryID == 20000) {
                        searchClicked = false;
                        mapClicked = false;
                        jobClicked = false;
                        compareClicked = true;
                        inCompare = true;

                        if (currentCategoryID == 10000 && SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this) == 0) {
                            AlertMessage.showMessage(con, getString(R.string.cant_compare),
                                    getString(R.string.no_service_selected));
                        } else if (currentCategoryID == 20000 && SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this) == 0) {
                            AlertMessage.showMessage(con, getString(R.string.cant_compare),
                                    getString(R.string.no_service_selected));
                        } else if (currentCategoryID == 10000 && SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this) == 1) {
                            AlertMessage.showMessage(con, getString(R.string.cant_compare),
                                    getString(R.string.select_two_services));
                        } else if (currentCategoryID == 20000 && SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this) == 1) {
                            AlertMessage.showMessage(con, getString(R.string.cant_compare),
                                    getString(R.string.select_two_services));
                        } else {


                            if (!mapClicked || !searchClicked || !jobClicked) {

                                params4.height = larg;
                                compareButton.setLayoutParams(params4);

                                params2.height = small;
                                searchButton.setLayoutParams(params2);
                                params.height = small;
                                mapButton.setLayoutParams(params);
                                params.height = small;
                                jobButton.setLayoutParams(params);


                            }
                            toolbar.setVisibility(View.GONE);
                            // compare_layout.setVisibility(View.VISIBLE);

                            // @@@@arafat
                            // need to add condition for health and add color code for health,
                            // else educaton color code is okay
                            toggleButton.setVisibility(View.GONE);
                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.map)
                                    .resize(buttonWidth, small)
                                    .into(mapButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.compare_selected)
                                    .resize(buttonWidth, larg)
                                    .into(compareButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.job_unselectedtab)
                                    .resize(buttonWidth, small)
                                    .into(jobButton);


                            Picasso.with(getApplicationContext())
                                    .load(R.drawable.search)
                                    .resize(buttonWidth, small)
                                    .into(searchButton);
                            map.setVisibility(View.GONE);
                            llCatListHolder.setVisibility(View.GONE);
                            searchViewHolder.setVisibility(View.GONE);

                            sv.setVisibility(View.GONE);
                            svHolder.setVisibility(View.GONE);
                            spinCount1 = 0;
                            spinCount2 = 0;
                            compareTool();
                        }
                    } else
                        AlertMessage.showMessage(con, getString(R.string.cant_compare),
                                getString(R.string.incompatible_category_to_compare));


                }
            });

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

                    //Button is OFF
                    // Do Something
                }
            });
        }
    }

    public void compareTool()
    {
        if(currentCategoryID == 10000) {
            comapareData = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
            String delims = "[,]";
            String[] tokens = comapareData.split(delims);
            firstData = tokens[0];
            secondData = tokens[1];
            compare_layout.setVisibility(View.GONE);
            compare_layout_edu.setVisibility(View.VISIBLE);
            compare_layout_edu.setBackgroundColor(Color.parseColor("#FFFFFF"));
            compareEducation();
        }

        else {
            compare_layout.setVisibility(View.VISIBLE);
            comapareData = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
            String DataSet[] = comapareData.split(",");
            firstData = DataSet[0];
            secondData = DataSet[1];
            compareHealth();
        }
    }


    public void compareHealth() {

        //compare will be selected from detailspage and displayed here
        compare_layout.setVisibility(View.VISIBLE);
        compare_layout_edu.setVisibility(View.GONE);
        checkBox1.setChecked(true);
        checkBox2.setChecked(true);

        HealthNewDBTableMain healthDB = new HealthNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        firstDataSetHealth = healthDB.getDataListFromId(Integer.parseInt(firstData));
        secondDataSetHealth = healthDB.getDataListFromId(Integer.parseInt(secondData));
        health_header = new String[]{"খোলার সময়","সেবার ধরন","বন্ধের দিন","ডাক্তার আছে?","বিশেষত্ব","ভ্যাক্সিন সুবিধা"};
        HealthNewDBTablePharma healthSpecialistTable = new HealthNewDBTablePharma(PlaceDetailsActivityNewLayout.this);
        ArrayList <HealthNewDBModelPharmacy> healthSpecialistItemDetailses;
        ArrayList <HealthNewDBModelPharmacy> healthSpecialistItemDetailses2;
        healthSpecialistItemDetailses = healthSpecialistTable.getDataListFromId((Integer.parseInt(firstData)));
        healthSpecialistItemDetailses2 = healthSpecialistTable.getDataListFromId(Integer.parseInt(secondData));

        String firstSpecialistItemdoc = "", secondSpecialistItemdoc = "", firstSpecialistItemspe = "", secondSpecialistItemspe = "", firstSpecialistItemvac = "", secondSpecialistItemvac = "";

        if (!healthSpecialistItemDetailses.equals("")) {
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses) {
                if(healthSpecialistItemDetails.getDocAvailability().equals("false")) {
                    firstSpecialistItemdoc = firstSpecialistItemdoc + "ডাক্তার বসেন না";
                }
                else firstSpecialistItemdoc = firstSpecialistItemdoc +  "ডাক্তার বসেন" ;
            }
        }

        if (!healthSpecialistItemDetailses2.equals("")){
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses2) {
                if(healthSpecialistItemDetails.getDocAvailability().equals("false")) {
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
                if(healthSpecialistItemDetails.getVaccineAvailability().equals("false"))
                {
                    firstSpecialistItemvac=firstSpecialistItemvac + "ভ্যাকসিন সুবিধা নেই ";
                }
                else firstSpecialistItemvac = firstSpecialistItemvac +  "ভ্যাকসিন সুবিধা আছে "  ;
            }
        }

        if (!healthSpecialistItemDetailses2.equals("")){
            for (HealthNewDBModelPharmacy healthSpecialistItemDetails : healthSpecialistItemDetailses2) {
                if(healthSpecialistItemDetails.getVaccineAvailability().equals("false"))
                {
                    secondSpecialistItemvac=secondSpecialistItemvac + "ভ্যাকসিন সুবিধা নেই ";
                }
                else secondSpecialistItemvac = secondSpecialistItemvac +  "ভ্যাকসিন সুবিধা আছে " ;
            }
        }


        for (final HealthNewDBModelMain healthServiceProviderItemNew: firstDataSetHealth)
        {

            checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue = SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this);
                    if(compareValue==2) // if their is two value for compare
                    {
                        if(!isChecked)
                        {
                            String compare_Datas  = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            String multipule[] = compare_Datas.split(",");
                            compare_Datas = multipule[1];
                            id1 = multipule[0];
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datas, 1);
                        }
                    }

                    else if(compareValue==1) /// if their is one value for compare
                    {
                        if(!isChecked) {
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this,"",0);
                        }

                        else {
                            String compare_Datac = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            compare_Datac = compare_Datac + "," + id1;
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datac, 2);
                        }
                    }

                    else if (compareValue == 0) { /// if their no item for compare
                        if(isChecked)
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, String.valueOf(healthServiceProviderItemNew.getId()), 1);
                    }



                }
            });



            if(!healthServiceProviderItemNew.getNameBn().equalsIgnoreCase("null") && !healthServiceProviderItemNew.getNameBn().equals(""))
                health_name2.setText(healthServiceProviderItemNew.getNameBn());
            else
                health_name2.setText("তথ্য পাওয়া যায় নি ");

            String time2 = timeConverter(healthServiceProviderItemNew.getOpeningTime());
            left_part = new String [] {time2,healthServiceProviderItemNew.getInstituteType()
                    ,healthServiceProviderItemNew.getOffDay(),firstSpecialistItemdoc,firstSpecialistItemspe,firstSpecialistItemvac
                    };
        }


                               //this is for right side item

        for (final HealthNewDBModelMain healthServiceProviderItemNewx: secondDataSetHealth)
        {
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue = SharedPreferencesHelper.getComapreValueHealth(PlaceDetailsActivityNewLayout.this);
                    if(compareValue == 2) {
                        if(!isChecked) {
                            String compare_Datas = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            String multipule [] = compare_Datas.split(",");
                            compare_Datas = multipule[0];
                            id2 = multipule[1];
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Datas, 1);
                        }

                    }
                    else if(compareValue == 1) {
                        if(!isChecked) {
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this,"",0);
                        }
                        else {
                            String compare_Data = SharedPreferencesHelper.getComapreDataHealth(PlaceDetailsActivityNewLayout.this);
                            compare_Data = id2 + "," + compare_Data;
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, compare_Data, 2);
                        }
                    }

                    else if (compareValue == 0) {
                        if(isChecked)
                            SharedPreferencesHelper.setCompareDataHealth(PlaceDetailsActivityNewLayout.this, String.valueOf(healthServiceProviderItemNewx.getId()), 1);
                    }
                }
            });




            if(!healthServiceProviderItemNewx.getNameBn().equalsIgnoreCase("null") && !healthServiceProviderItemNewx.getNameBn().equals(""))
                health_name1.setText(healthServiceProviderItemNewx.getNameBn());
            else
                health_name1.setText("তথ্য পাওয়া যায় নি ");


            String time1 = timeConverter(healthServiceProviderItemNewx.getOpeningTime()); //convert the time
            right_part = new String [] {time1,healthServiceProviderItemNewx.getInstituteType()
                    ,healthServiceProviderItemNewx.getOffDay(),secondSpecialistItemdoc,
                    secondSpecialistItemspe,secondSpecialistItemvac
            };
        }
        CompareAdapter compareAdapter= new CompareAdapter(this,left_part,right_part,health_header);
        health_compare_list.setAdapter(compareAdapter);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) health_compare_list
                .getLayoutParams();
        layoutParams.setMargins(0, 0, 0, small);
    }



    public void compareEducation() {
        checkLeft.setChecked(true);
        checkRight.setChecked(true);

        EduNewDBTableMain educationNewTable = new EduNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        firstDataSetEdu = educationNewTable.getDataListFromId(Integer.parseInt(firstData));
        secondDataSetEdu = educationNewTable.getDataListFromId(Integer.parseInt(secondData));
        for (final EduNewModel educationNewItem: firstDataSetEdu)
        {
            checkLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue = 0;
                    compareValue = SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this);
                    if(compareValue == 2)
                    {
                        if(!isChecked) {
                            String compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
                            String multipule [] = compare_Data.split(",");
                            compare_Data = multipule[1];
                            id3 = multipule[0];
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 1);
                        }
                    }
                    else if(compareValue == 1) {
                        if(!isChecked) {
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this,"",0);
                        }
                        else {

                            String compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);

                            compare_Data = compare_Data + "," + id3;
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 2);
                        }
                    }

                    else if (compareValue == 0) {
                        if(isChecked)
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, String.valueOf(educationNewItem.getId()), 1);

                    }
                }
            });

            health_header = new String []{ "প্রতিষ্ঠানের ধরন", "গড় ছাত্র ছাত্রী সংখ্যা ","ছাত্র ছাত্রী সংখ্যা","শিক্ষক সংখ্যা ","শাখা","রেটিং"
            };
            if(educationNewItem.getNameBn() == null || educationNewItem.getNameBn().equalsIgnoreCase("null")|| educationNewItem.getNameBn().equals(""))
                edu_name2.setText("তথ্য পাওয়া যায় নি ");
            else
                edu_name2.setText(educationNewItem.getNameBn());

            left_part = new String []{educationNewItem.getEducationType(),English_to_bengali_number_conversion(educationNewItem.getAverageStudentPerClass()),
                    English_to_bengali_number_conversion(String.valueOf(educationNewItem.getStudentNo())),
                    English_to_bengali_number_conversion(educationNewItem.getTeachersNo()),educationNewItem.getShift(),
                    educationNewItem.getRatings()};
        }
        for (final EduNewModel educationNewItem: secondDataSetEdu)
        {
            checkRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int compareValue = SharedPreferencesHelper.getComapreValueEdu(PlaceDetailsActivityNewLayout.this);
                    if(compareValue == 2)
                    {
                        if(!isChecked) {

                            String compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
                            String multipule [] = compare_Data.split(",");
                            compare_Data = multipule[0];
                            id4 = multipule[1];
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 1);
                        }

                    }
                    else if(compareValue == 1) {
                        if(!isChecked) {
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, "", 0);
                        }
                        else {
                            String compare_Data = SharedPreferencesHelper.getComapreData(PlaceDetailsActivityNewLayout.this);
                            compare_Data = compare_Data + "," + id4;
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, compare_Data, 2);
                        }
                    }

                    else if (compareValue == 0) {
                        if(isChecked)
                            SharedPreferencesHelper.setComapareEdu(PlaceDetailsActivityNewLayout.this, String.valueOf(educationNewItem.getId()), 1);
                    }
                }
            });


            right_part = new String []{educationNewItem.getEducationType(),English_to_bengali_number_conversion(educationNewItem.getAverageStudentPerClass()),
                    English_to_bengali_number_conversion(String.valueOf(educationNewItem.getStudentNo())),
                    English_to_bengali_number_conversion(educationNewItem.getTeachersNo()),
                    educationNewItem.getShift(),educationNewItem.getRatings()};
            if(educationNewItem.getNameBn()==null || educationNewItem.getNameBn().equalsIgnoreCase("null")|| educationNewItem.getNameBn().equals(""))
                edu_name1.setText(getString(R.string.info_not_found));
            else
                edu_name1.setText(educationNewItem.getNameBn());
        }

        CompareAdapter compareAdapter= new CompareAdapter(this,left_part,right_part,health_header);
        education_compare_list.setAdapter(compareAdapter);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) education_compare_list
                .getLayoutParams();
        layoutParams.setMargins(0, 0, 0, small);//
        education_compare_list.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this,R.color.white));
    }

    public void populateSearch()
    {

        ImageButton more=(ImageButton)findViewById(R.id.morebutton);
        catHolder.setVisibility(View.VISIBLE);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterClicked=true;
                fHolder.setVisibility(View.VISIBLE);
                populateFilterWords(currentCategoryID);

            }
        });

    }



/*
* this populate filter keywords based on category id from db*/
    public void populateFilterWords(int filcatid)
    {
        SubCategoryTableNew subCategoryTable = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        tagHolders.clear();
        tagHolders = subCategoryTable.getcatSubCategories(filcatid);

        int upto = tagHolders.size()/2;
        filter.clear();
        filter2.clear();
        fLeft.removeAllViews();
        fRight.removeAllViews();

        for (int f = 0; f < tagHolders.size(); f++) {
            if (f >= upto)
                filter2.add(tagHolders.get(f).getName_bn());
            else
                filter.add(tagHolders.get(f).getName_bn());
        }

        final RadioButton[] rb = new RadioButton[30];
        leftGroup = new RadioGroup(this); //create the RadioGroup
        leftGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

        for(int i = 0; i < filter.size(); i++){
            rb[i] = new RadioButton(this);
            leftGroup.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filter.get(i).toString());
            rb[i].setTextColor(Color.WHITE);
        }

        rightGroup = new RadioGroup(this); //create the RadioGroup
        rightGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i = 0; i < filter2.size(); i++){
            rb[i] = new RadioButton(this);
            rightGroup.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filter2.get(i));
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

            Intent em = new Intent(this, EmergencyActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
        else if (id == R.id.new_place) {
            storedAreaArrayListall = storedAreaTable.getAllData();
                /*if(storedAreaArrayListall.size()>=5)
                {

                    AlertMessage.showMessagesize(PlaceDetailsActivityNewLayout.this,"দুঃখিত","আপনি ৫টি এলাকার বেশি তথ্য একবারে নামাতে পারবেন না। আগের এলাকা বাতিল করতে" +
                            "'তথ্য আপডেট/ডিলিট করুন' অপশন টি ব্যাবহার করুন",20,15);

                }*/
            //else {
                    Intent em = new Intent(this, DataLoadingActivity.class);
                    startActivity(em);
                    finish();
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
                        .setTitle(getString(R.string.new_area_info))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_download_new_area))
                        .setGravity(Gravity.RIGHT)
                )
                // note that there is no Overlay here, so the default one will be used
               .playLater( view);

     ChainTourGuide tourGuide2 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(getString(R.string.change_downloaded_info))
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription(getString(R.string.tutorial_update_info))
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
        ToastMessageDisplay.setText(PlaceDetailsActivityNewLayout.this,getString(R.string.press_back_to_exit));
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

                Float  ratings;
                ratings = ratingBar.getRating();
                comment=submit_review.getText().toString();
                if(ratings==0)ratings = (float) 0.0001;

                sendDataToserver(ratings, comment);
                SharedPreferences settings = PlaceDetailsActivityNewLayout.this.getSharedPreferences("prefs", 0);
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
    public void sendDataToserver(Float rating, String comment) {
        String username = SharedPreferencesHelper.getUser(PlaceDetailsActivityNewLayout.this);
        SharedPreferencesHelper.setFeedback(PlaceDetailsActivityNewLayout.this, username);
        String phone = SharedPreferencesHelper.getNumber(PlaceDetailsActivityNewLayout.this);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);


        if (phone.equals(""))phone.replace("","0");
        else {
            String comment2 = "";
            try {
                comment2 = URLEncoder.encode(comment.replace(" ", "%20"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url = "http://kolorob.net/kolorob-new-live/api/app_rating?phone=" + phone + "&review=" + comment2 + "&rating=" + rating + "&username=" + this.username + "&password=" + this.password;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //   ToastMessageDisplay.ShowToast(PlaceSelectionActivity.this,"ধন্যবাদ");


                            try {
                                ToastMessageDisplay.setText(PlaceDetailsActivityNewLayout.this,getString(R.string.thanks));
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




    private void constructCategoryList(ArrayList <CategoryItem> categoryList2) {
        constructCategoryList(categoryList2, 1.0);
    }

    private void constructCategoryList(ArrayList <CategoryItem> categoryList2, double dwPercentage) {
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
        int modded_id = ci.getId()/10000;
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
                Arrays.fill(flag2,1);
                clicked.clear();
                filterClicked = false;
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
                        jobClicked = false;
                        catStatus = true;
                        setFilterCategoryId(currentCategoryID);
                        calladapter(catStatus);

                        mapcalledstatus = true;
                        callMapFragment((ArrayList<ModelType>)constructEducationListItem());


                        ivIcon.setImageResource(R.drawable.ic_education);


                        if(searchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populateFilterWords(getFilterCategoryId());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_education);


                        break;
                    case AppConstants.HEALTH:

                        //HEL.clear();
                        healthClicked = true;
                        eduClicked = false;
                        entClicked = false;
                        legalClicked = false;
                        finClicked = false;
                        govClicked = false;
                        jobClicked = false;
                        catStatus = true;
                        setFilterCategoryId(currentCategoryID);
                        calladapter(catStatus);

                        if(searchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populateFilterWords(getFilterCategoryId());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_health);

                        mapcalledstatus = true;

                        callMapFragment((ArrayList <ModelType>)constructHealthListItem());

                        break;

                    //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:

                     //   ENT.clear();
                        entClicked = true;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        finClicked = false;
                        govClicked = false;
                        jobClicked = false;
                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);

                        if(searchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populateFilterWords(getFilterCategoryId());
                        }


                        ivIcon.setImageResource(0);

                        mapcalledstatus = true;
                        callMapFragment((ArrayList <ModelType>)constructEntertainmentListItem());
                        ivIcon.setImageResource(R.drawable.ic_entertainment);

                        break;


                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:

                        govClicked = true;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        finClicked = false;
                        jobClicked = false;
                        setFilterCategoryId(currentCategoryID);

                        catStatus = true;
                        calladapter(catStatus);

                        if(searchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populateFilterWords(getFilterCategoryId());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_government);


                        mapcalledstatus = true;

                        callMapFragment((ArrayList <ModelType>)constructGovListItem());




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

                        legalClicked = true;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        finClicked = false;
                        govClicked = false;
                        jobClicked = false;
                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);

                        if(searchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populateFilterWords(getFilterCategoryId());
                        }

                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_legal);


                        mapcalledstatus = true;

                        callMapFragment((ArrayList <ModelType>)constructLegalaidListItem());
                        break;


                    case AppConstants.FINANCIAL:

                        finClicked = true;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        govClicked = false;
                        jobClicked = false;
                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);

                        if(searchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populateFilterWords(getFilterCategoryId());
                        }
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_finance);

                        callMapFragment((ArrayList <ModelType>)constructFinancialListItem());
                        mapcalledstatus = true;
                        break;


                    /////// NGO ////
                    case AppConstants.NGO:

                        ngoClicked = true;
                        entClicked = false;
                        eduClicked = false;
                        healthClicked = false;
                        legalClicked = false;
                        govClicked = false;
                        jobClicked = false;
                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);

                        if (searchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populateFilterWords(getFilterCategoryId());
                        }
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.ic_ngos);

                        callMapFragment((ArrayList <ModelType>)constructNgoListItem());
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
                        jobClicked = false;
                        setFilterCategoryId(currentCategoryID);
                        catStatus = true;
                        calladapter(catStatus);

                        if (searchClicked) {
                            filterHolder.setVisibility(View.VISIBLE);
                            populateFilterWords(getFilterCategoryId());
                        }
                        ivIcon.setImageResource(0);
                        ivIcon.setImageResource(R.drawable.shelter);

                        callMapFragment((ArrayList <ModelType>)constructReligiousListItem());
                        mapcalledstatus = true;
                        break;


                    default:
                        break;
                }
            }
        });

        return v;
    }



    /*private ArrayList <SubCategoryItemNew> constructSubCategoryListItem(int cat_id, String header) {
        ArrayList <SubCategoryItemNew> subCategoryItems;
        SubCategoryTableNew subCategoryTable = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        subCategoryItems = subCategoryTable.getAllSubCategoriesHeader(cat_id,header);

        return subCategoryItems;
    }*/


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


    private View getSubCategoryListItemView(final SubCategoryItemNew si, double dwPercentage, final int cat_id) {

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

        tvName.setText(si.getSubCatLabelBn());
        tvName.setTextSize(12);
        flag = true;
        //tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));

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

    private ArrayList <SubCategoryItemNew> getSubCategoryList(int id) {
        // TODO Get sub-categories from the SUB_CATEGORY local table : NEXT PHASE
        SubCategoryTableNew subCategoryTable = new SubCategoryTableNew(PlaceDetailsActivityNewLayout.this);
        return subCategoryTable.getDataListFromForeignKey(id);
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



    private ArrayList <EduNewModel> constructEducationListItem() {

        EduNewDBTableMain educationNewTable = new EduNewDBTableMain(PlaceDetailsActivityNewLayout.this);
        return educationNewTable.getByAreaCategory(wardId, areaKeyword, AppConstants.EDUCATION);

    }



/*
* call mapfragment functions load fragments of map. based on location */
    /*private void callMapFragmentWithEducation(ArrayList <EduNewModel> educationServiceProviderItems) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);

        fragment.getMapViewController().setZoom(15);
          fragment.getMapViewController().setCenter(getLocation());

            fragment.setCategoryId(currentCategoryID);
            fragment.setSubcategories(currentCategoryID);
            fragment.populateIcons(educationServiceProviderItems);

            called = true;*/


      /*  else {
            if(mainedcalled)
            {

                mainedcalled=false;
            }
            fragment.Drawedu(edid,s);
        }*/
        // EDD.clear();
    //}
    /*
    * this is the default map view based on intent location name.If user change from spinner; this is also called*/
    private void callMapFragment(String lat, String lon) {

        FragmentManager fragmentManager = getFragmentManager();

        if(mapFirst && lat != null && lon != null) {

            mapFragment = new MapFragmentOSM();
            mapFragment.setLocationName(areaKeyword);
            mapFragment.setLat(lat);
            mapFragment.setLon(lon);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.map_fragment, mapFragment, "MAP");
            fragmentTransaction.addToBackStack("MAP");
            fragmentManager.executePendingTransactions();
            fragmentTransaction.commit();
            mapFirst=false;
        }


    }

    /***********************************************************Methods for Health*************************************************/


     private ArrayList <HealthNewDBModelMain> constructHealthListItem() {

         HealthNewDBTableMain healthServiceProviderTable = new HealthNewDBTableMain(PlaceDetailsActivityNewLayout.this);
         return healthServiceProviderTable.getByAreaCategory(wardId, areaKeyword, AppConstants.HEALTH);
    }

    /**********************************************************Methods for entertainment*******************************************/

    private ArrayList<EntertainmentNewDBModel> constructEntertainmentListItem() {

        EntNewDBTable entNewDBTable = new EntNewDBTable(PlaceDetailsActivityNewLayout.this);
        return entNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.ENTERTAINMENT);
    }


    /**********************************************************Methods for government**********************************************/

    private ArrayList <GovernmentNewDBModel> constructGovListItem() {

        GovNewDBTable govNewDBTable = new GovNewDBTable(PlaceDetailsActivityNewLayout.this);
        return govNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.GOVERNMENT);
    }



    /**********************************************************Methods for legal***************************************************/

    private ArrayList<LegalAidNewDBModel> constructLegalaidListItem() {

        LegalAidNewDBTable legalAidNewDBTable = new LegalAidNewDBTable(PlaceDetailsActivityNewLayout.this);
        return legalAidNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.LEGAL);
    }


    /**********************************************************Methods for financial**********************************************/
    private ArrayList <FinancialNewDBModel> constructFinancialListItem() {

        FinNewDBTable finNewDBTable = new FinNewDBTable(PlaceDetailsActivityNewLayout.this);
        return finNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.FINANCIAL);
    }



    /**********************************************************Methods for NGO***************************************************/

    private ArrayList <NGONewDBModel> constructNgoListItem() {

        NGONewDBTable ngoNewDBTable = new NGONewDBTable(PlaceDetailsActivityNewLayout.this);
        return ngoNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.NGO);
    }


    /**********************************************************Methods for Religious***************************************************/

    private ArrayList <ReligiousNewDBModel> constructReligiousListItem() {

        ReligiousNewDBTable religiousNewDBTable = new ReligiousNewDBTable(PlaceDetailsActivityNewLayout.this);
        return religiousNewDBTable.getByAreaCategory(wardId, areaKeyword, AppConstants.RELIGIOUS);
    }



    private void callMapFragment(ArrayList <ModelType> dataList) {

        MapFragmentOSM fragment = (MapFragmentOSM) getFragmentManager().findFragmentById(R.id.map_fragment);
        fragment.getMapViewController().setCenter(getLocation());

        if(wardId.startsWith("rcc")){
            fragment.getMapViewController().setZoom(14);
        }
        else{
            fragment.getMapViewController().setZoom(15);
        }
        fragment.setSubcategories(currentCategoryID);
        fragment.setCategoryId(currentCategoryID);
        fragment.populateIcons(dataList);

        called = true;

    }





/*
* load all the data in arraylist; then store all info in another adapter. This adapter is then set in search
* so that when user taps on education; it can just sort in arraylist rather than querying in database all the time*/


    public void populateHolder() {
        filterText = (EditText)findViewById(R.id.searchall);
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



    /*if user has already selected any category; then allHolders been filtered using the category id/sub cat id*/

    private void calladapter(boolean status) {
        boolean instatus = status;

        if(instatus) {
            int gotcatid = getFilterCategoryId();

            catHolders.clear();

            for(int i = 0; i < allHolders.size(); i++) {
                if(allHolders.get(i).getCatid() == gotcatid) {
                    catHolders.add(allHolders.get(i));
                }
            }

            if(filterClicked) {
                checkNum = String.valueOf(getsNumber());
            }
            else checkNum = String.valueOf(0);

            if(Integer.parseInt(checkNum)!=0) {
                subcatHolders.clear();
                for(int i = 0; i< catHolders.size(); i++) {
                    if(catHolders.get(i).getRefnum().contains(checkNum)) {
                        subcatHolders.add(catHolders.get(i));
                    }
                }
                adapter = new ListViewAdapterAllCategories(this, subcatHolders);
            }

            else if (Integer.parseInt(checkNum)==0){
                adapter = new ListViewAdapterAllCategories(this, catHolders);
            }
        }
        else {
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
                setFilterWord((String) radioButton.getText());
                int num=Findsubcatid(filterWord);
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
                setFilterWord((String) radioButton.getText());
                int num=Findsubcatid(filterWord);
                calladapter(true);

                Log.v("Inside fun2","fun1");

            }
        });
    }

    private int Findsubcatid(String filterword){

        for (int s = 0; s < tagHolders.size(); s++) {
            if (tagHolders.get(s).getName_bn().equals(filterword)) {
                setsNumber(tagHolders.get(s).getId());
                break;
            }
        }

        return sNumber;
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

}