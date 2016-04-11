package  demo.kolorob.kolorobdemoversion.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
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
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Pattern;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AlphabetListAdapter;
import demo.kolorob.kolorobdemoversion.adapters.Group;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapter;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterEdu;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterEnt;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterHel;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterLeg;
import demo.kolorob.kolorobdemoversion.adapters.MyExpandableListAdapter;
import demo.kolorob.kolorobdemoversion.adapters.PopulatedfromDB;
import demo.kolorob.kolorobdemoversion.adapters.PopulatedfromDBEdu;
import demo.kolorob.kolorobdemoversion.adapters.PopulatedfromDBEnt;
import demo.kolorob.kolorobdemoversion.adapters.PopulatedfromDBHel;
import demo.kolorob.kolorobdemoversion.adapters.PopulatedfromDBLeg;
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
public class PlaceDetailsActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = PlaceDetailsActivity.class.getSimpleName();
    private static final int ANIM_INTERVAL = 100;
    int caselan=0;
    private static double VIEW_WIDTH;
    private ScrollView svCatList;
    private LinearLayout llCatListHolder;
    private LinearLayout llSubCatListHolder;
    private FrameLayout placeDetailsLayout;
    private TextView categoryHeader;
    private ImageView categoryHeaderIcon;
    private TextView subCatItemListHeader;
    private ExpandableListView subCatItemList;
    private Button showSubCatListItem;
    private Button seeMap;
    CategoryItem ci;
    private HashMap<String, Integer> sections = new HashMap<String, Integer>();
    private HashMap<String, String> countriesv2 = new HashMap<>();
    private static TextView insSubCat;
    private static FrameLayout map;
    public LinearLayout showsearch2,llItemListHolderbody;
    private int height,dpi;
    private View nextChild;
    private boolean isCatExpandedOnce = false;
    private int primaryIconWidth;
    private int subCatShowFlag=0;
    private int locationNameId,subcategory;
    private String locationName;
    private LinearLayout catLayout;
    private RadioButton srad,brad;
    private GestureDetector mGestureDetector;
    private int sideIndexHeight;
    private List<Object[]> alphabet = new ArrayList<Object[]>();
    private static float sideIndexX;
    private static float sideIndexY;
    TextView textView,texthead,textmid;
    FinancialServiceProviderItem financialServiceProviderItem;
    ImageButton search;
    Button Back;
    public int layoutstatus;
    HorizontalScrollView svSubCategoryListHolder;

    public int getLayoutstatus() {
        return layoutstatus;
    }

    public void setLayoutstatus(int layoutstatus) {
        this.layoutstatus = layoutstatus;
    }

    TextView tmpTV;

    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.

    private Switch switchlan;
    public int status=0;
    AlphabetListAdapter adapterind = new AlphabetListAdapter(PlaceDetailsActivity.this);
    ArrayList<EntertainmentServiceProviderItem> printnamesent;
    ArrayList<JobServiceProviderItem> printnamesjob;
    ArrayList<LegalAidServiceProviderItem> printnamesleg;
    ArrayList<HealthServiceProviderItem> printnameshea;
    ArrayList<FinancialServiceProviderItem> printnamesfin;
    ArrayList<String> countries=new ArrayList<String>();
    ArrayList<SearchHolder> searchheads=new ArrayList<>();

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
    private EditText filterText;
    String fname,fnodeid,upname,names,ids;
    int refid;
    private RadioGroup fingroup;
    private ArrayList<FinancialServiceProviderItem>fetchedfin;
    private ArrayList<EducationServiceProviderItem>fetchededu;
    private ArrayList<LegalAidServiceProviderItem>fetchedleg;
    private ArrayList<EntertainmentServiceProviderItem>fetchedent;
    private ArrayList<HealthServiceProviderItem>fetchedhel;
    ListViewAdapter adapter;
    ListViewAdapterEdu adapterEdu;
    ListViewAdapterHel adapterHel;
    ListViewAdapterLeg adapterLeg;
    ListViewAdapterEnt adapterEnt;
    ArrayList<PopulatedfromDB> arraylist = new ArrayList<>();//fin
    ArrayList<PopulatedfromDBEdu>arraylist2=new ArrayList<>();
    ArrayList<PopulatedfromDBHel>arraylist3=new ArrayList<>();
    ArrayList<PopulatedfromDBLeg>arraylist4=new ArrayList<>();
    ArrayList<PopulatedfromDBEnt>arraylist5=new ArrayList<>();
    ArrayList<PopulatedfromDB>arraylistfin=new ArrayList<>();
    private String placeChoice;
    private int indexListSize;
    private ListActivity listView;

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

        Log.d(">>>>","test_dpi "+dpi);
       // svSubCategoryListHolder=(HorizontalScrollView)findViewById(R.id.svSubCategoryListHolder);

        HorizontalScrollView svSubCategoryListHolder = new HorizontalScrollView(this);
        if(dpi>300)
            setContentView(R.layout.placedetailsactivitysupermobile);

        else
        if(height>1000)
            setContentView(R.layout.place_details_activity);
        else {
            setContentView(R.layout.place_details_activity_mobiles);
          //  svSubCategoryListHolder.setMinimumHeight(70);

        }

        search=(ImageButton)findViewById(R.id.imageButton2);
        Back=(Button)findViewById(R.id.backbutton);
        textView=(TextView)findViewById(R.id.tvInstructionSubCat);
        showsearch2=(LinearLayout)findViewById(R.id.seearch);
        llItemListHolderbody=(LinearLayout)findViewById(R.id.llItemListHolder);
        sideIndex = (LinearLayout)findViewById(R.id.sideIndex);
     //   texthead = (TextView) findViewById(R.id.headtext);


        Intent intent;
        intent = getIntent();
        if (null != intent)
        {
            locationNameId = intent.getIntExtra(AppConstants.KEY_PLACE,0);
            if(locationNameId==AppConstants.PLACE_BAUNIABADH)
            {
                setPlaceChoice("Baunia Badh");
                locationName = AppConstants.BAUNIABADH;
            }
            else if(locationNameId==AppConstants.PLACE_PARIS_ROAD)
            {
                setPlaceChoice("Paris Road");
                locationName = AppConstants.PARIS_ROAD;
            }
        }


        //categoryHeader = (TextView) findViewById(R.id.tv_cat_name);
        categoryHeaderIcon = (ImageView) findViewById(R.id.ivHeadCatIconSubCatList);
        placeDetailsLayout = (FrameLayout) findViewById(R.id.place_details_layout);


        ///this code will change the background of the layout for two places.
        if(locationNameId==AppConstants.PLACE_BAUNIABADH)
        {
            placeDetailsLayout.setBackgroundResource(R.drawable.place_details_bg_baunia);
        }
        else if(locationNameId==AppConstants.PLACE_PARIS_ROAD)
        {
            placeDetailsLayout.setBackgroundResource(R.drawable.place_details_bg);
        }

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        itemList = (ListView)findViewById(R.id.listViewSearch);
        subCatItemListHeader = (TextView) findViewById(R.id.tv_sub_cat_item_list_head);

        subCatItemList = (ExpandableListView) findViewById(R.id.listView);
        map = (FrameLayout) findViewById(R.id.map_fragment);
        //showsearch=(RelativeLayout)findViewById(R.id.show);
        insSubCat = (TextView) findViewById(R.id.tvInstructionSubCat);
        seeMap = (Button) findViewById(R.id.btn_see_map);
        showSubCatListItem = (Button) findViewById(R.id.btn_show_sub_cat_list_item);
        VIEW_WIDTH = AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC;
        isCatExpandedOnce = false;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.80); // 80% of the view width

        svCatList = (ScrollView) findViewById(R.id.svCategoryListHolder);
        llCatListHolder = (LinearLayout) findViewById(R.id.llCategoryListHolder);
        llSubCatListHolder = (LinearLayout) findViewById(R.id.llSubCatListHolder);
        ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();
        lp.width = (int) (VIEW_WIDTH);

        /**
         * constructing category list
         **/
        CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivity.this);
        constructCategoryList(categoryTable.getAllCategories());
        rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        rlSubCatHolder.setVisibility(View.INVISIBLE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                llItemListHolderbody.setVisibility(View.GONE);

                showsearch2.setVisibility(View.VISIBLE);
                Back.setVisibility(View.VISIBLE);
                SearchResult(currentCategoryID);
                seeMap.setVisibility(View.GONE);
                Back.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        showsearch2.setVisibility(View.GONE);
                        map.setVisibility(View.VISIBLE);
                        llItemListHolderbody.setVisibility(View.VISIBLE);
                        getCategoryListItemView(getCi(), 1.0);
                        Back.setVisibility(View.GONE);

                    }
                });




        /*if (isChecked) caselan = 1;
        else caselan = 0;
        SearchResult(caselan, currentCategoryID);
        switchlan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position

            }
        });*/

                // subCatItemListHeader.setVisibility(View.GONE);
                //subCatItemList.setVisibility(View.GONE);
            }
        });
        mGestureDetector = new GestureDetector(this, new SideIndexGestureListener());
        showSubCatListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                subCatItemList.setVisibility(View.VISIBLE);
                subCatItemListHeader.setVisibility(View.VISIBLE);
                showSubCatListItem.setVisibility(View.GONE);
                seeMap.setVisibility(View.VISIBLE);
            }
        });
        seeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.VISIBLE);
                subCatItemList.setVisibility(View.GONE);
                subCatItemListHeader.setVisibility(View.GONE);
                showSubCatListItem.setVisibility(View.VISIBLE);
                seeMap.setVisibility(View.GONE);
            }
        });

        // callMapFragment();

    }
    protected void indexbar(ArrayList<SearchHolder> countries) {


        alphabet.clear();
        List<AlphabetListAdapter.Row> rows = new ArrayList<AlphabetListAdapter.Row>();
        int start = 0;
        int end = 0;
        String previousLetter = null;
        Object[] tmpIndexItem = null;
        Pattern numberPattern = Pattern.compile("[0-9]");

        for(int ii=0;ii<countries.size();ii++) {
            String country=countries.get(ii).getName();
            String idd=countries.get(ii).getId();
            int cattid=countries.get(ii).getCatid();
            String firstLetter = country.substring(0, 1);


            Log.d(">>>>>","country.size "+countries.size());
            Log.d(">>>>>","country "+countries.get(ii).getName());
            Log.d(">>>>>","idd "+countries.get(ii).getId());
            Log.d(">>>>>","cattid "+countries.get(ii).getCatid());
            Log.d(">>>>>","firstLetter "+country.substring(0, 1));


            // Group numbers together in the scroller
            if (numberPattern.matcher(firstLetter).matches()) {
                firstLetter = "#";
            }

            // If we've changed to a new letter, add the previous letter to the alphabet scroller
            if (previousLetter != null && !firstLetter.equals(previousLetter)) {
                end = rows.size() - 1;
                tmpIndexItem = new Object[3];
                tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
                tmpIndexItem[1] = start;
                tmpIndexItem[2] = end;
                alphabet.add(tmpIndexItem);

                start = end + 1;
            }

            // Check if we need to add a header row
            if (!firstLetter.equals(previousLetter)) {
                rows.add(new AlphabetListAdapter.Section(firstLetter));
                sections.put(firstLetter, start);
            }

            // Add the country to the list
            rows.add(new AlphabetListAdapter.Item(country,idd,cattid));
            previousLetter = firstLetter;
        }

        if (previousLetter != null) {
            // Save the last letter
            tmpIndexItem = new Object[3];
            tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
            tmpIndexItem[1] = start;
            tmpIndexItem[2] = rows.size() - 1;
            alphabet.add(tmpIndexItem);
        }


        adapterind.setRows(rows);
        adapterind.setSections(sections);
        itemList.setAdapter(adapterind);

        //  sideIndex.setLayoutParams(new RelativeLayout.LayoutParams(30, 0));


        updateList();
    }


    public void updateList() {



        sideIndex.removeAllViews();

        indexListSize = alphabet.size();
        if (indexListSize < 1) {
            return;
        }

        int indexMaxSize = (int) Math.floor(sideIndex.getHeight() / 20);
        int tmpIndexListSize = indexListSize;
        while (tmpIndexListSize > indexMaxSize) {
            tmpIndexListSize = tmpIndexListSize / 2;
        }
        double delta;
        if (tmpIndexListSize > 0) {
            delta = indexListSize / tmpIndexListSize;
        } else {
            delta = 1;
        }

        for (double i = 1; i <= indexListSize; i = i + delta) {
            Object[] tmpIndexItem = alphabet.get((int) i - 1);
            String tmpLetter = tmpIndexItem[0].toString();

            tmpTV = new TextView(this);
            tmpTV.setText(tmpLetter);
            tmpTV.setGravity(Gravity.CENTER);
            tmpTV.setTextSize(15);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            tmpTV.setLayoutParams(params);
            sideIndex.addView(tmpTV);
        }

        sideIndexHeight = sideIndex.getHeight();

        sideIndex.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // now you know coordinates of touch
                sideIndexX = event.getX();
                sideIndexY = event.getY();

                // and can display a proper item it country list
                displayListItem();

                return false;
            }
        });
    }

    class SideIndexGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            sideIndexX = sideIndexX - distanceX;
            sideIndexY = sideIndexY - distanceY;

            if (sideIndexX >= 0 && sideIndexY >= 0) {
                displayListItem();
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        } else {
            return false;
        }
    }
    public void displayListItem() {
        sideIndex = (LinearLayout) findViewById(R.id.sideIndex);
        sideIndexHeight = sideIndex.getHeight();
        // compute number of pixels for every side index item
        double pixelPerIndexItem = (double) sideIndexHeight / indexListSize;

        // compute the item index for given event position belongs to
        int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

        // get the item (we can do it since we know item index)
        if (itemPosition < alphabet.size()) {
            Object[] indexItem = alphabet.get(itemPosition);
            int subitemPosition = sections.get(indexItem[0]);


            itemList.setSelection(subitemPosition);
        }
    }
    private void SearchResult( int currentCategoryID)  {



        filterText = (EditText)findViewById(R.id.editText);


        if (currentCategoryID==1) {

            itemList.setAdapter(null);
           sideIndex.setVisibility(View.VISIBLE);
            EducationServiceProviderTable educationServiceProviderTable=new EducationServiceProviderTable(PlaceDetailsActivity.this);
            fetchededu=educationServiceProviderTable.getAllEducationSubCategoriesInfo(currentCategoryID);

            arraylist2.clear();

            for (int i=0;i<fetchededu.size();i++)
            {

                fname=fetchededu.get(i).getEduNameEng();
                fnodeid=fetchededu.get(i).getIdentifierId();
                refid=fetchededu.get(i).getEduSubCategoryId();
                upname=fetchededu.get(i).getEduNameBan();

                PopulatedfromDBEdu wp = new PopulatedfromDBEdu(fname,fnodeid,refid,fetchededu);
                arraylist2.add(wp);
            }



           searchheads.clear();
            for (int ind=0;ind<arraylist2.size();ind++)
            {
              names=arraylist2.get(ind).getRank();
                ids=arraylist2.get(ind).getNodeid();
                SearchHolder sh = new SearchHolder(names,ids,currentCategoryID);
                searchheads.add(sh);
            }
          Collections.sort(searchheads, SearchHolder.NameCompare);

                indexbar(searchheads);


            itemList.setFastScrollEnabled(false);
            itemList.setFastScrollEnabled(true);
            filterText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    adapterEdu = new ListViewAdapterEdu(PlaceDetailsActivity.this, arraylist2);
                    map.setVisibility(View.GONE);
                    sideIndex.setVisibility(View.GONE);
                    itemList.setAdapter(adapterEdu);

                    return false;
                }
            });

            filterText.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {

                    // TODO Auto-generated method stub
                    String text = filterText.getText().toString().toLowerCase(Locale.getDefault());

                    adapterEdu.filter(text);
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



        }
        else if (currentCategoryID==2){
            sideIndex.setVisibility(View.VISIBLE);
            HealthServiceProviderTable healthServiceProviderTable1=new HealthServiceProviderTable(PlaceDetailsActivity.this);
            fetchedhel=healthServiceProviderTable1.getAllHealthSubCategoriesInfo(currentCategoryID);
            arraylist3.clear();

            for (int i=0;i<fetchedhel.size();i++)
            {

                fname=fetchedhel.get(i).getNodeName();
                fnodeid=fetchedhel.get(i).getNodeId();
                refid=fetchedhel.get(i).getRefNum();
                upname=fetchedhel.get(i).getNameBn();

                PopulatedfromDBHel wp = new PopulatedfromDBHel(fname,fnodeid,refid,fetchedhel);
                arraylist3.add(wp);}




            searchheads.clear();
            for (int ind=0;ind<arraylist3.size();ind++)
            {
                names=arraylist3.get(ind).getRank();
                ids=arraylist3.get(ind).getNodeid();
                SearchHolder sh = new SearchHolder(names,ids,currentCategoryID);
                searchheads.add(sh);
            }
            Collections.sort(searchheads,SearchHolder.NameCompare);

            indexbar(searchheads);




            itemList.setFastScrollEnabled(false);
            itemList.setFastScrollEnabled(true);
            filterText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    sideIndex.setVisibility(View.GONE);
                    map.setVisibility(View.GONE);
                    adapterHel = new ListViewAdapterHel(PlaceDetailsActivity.this, arraylist3);


                    itemList.setAdapter(adapterHel);
                    return false;
                }
            });

            filterText.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {

                    // TODO Auto-generated method stub
                    String text = filterText.getText().toString().toLowerCase(Locale.getDefault());

                    adapterHel.filter(text);
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



        }
        else if (currentCategoryID==5){
            sideIndex.setVisibility(View.VISIBLE);
            itemList.setAdapter(null);
            LegalAidServiceProviderTable legalAidServiceProviderTable1=new LegalAidServiceProviderTable(PlaceDetailsActivity.this);
            fetchedleg=legalAidServiceProviderTable1.getAllLegalAidSubCategoriesInfo(currentCategoryID);
            arraylist4.clear();

            for (int i=0;i<fetchedleg.size();i++)
            {

                fname=fetchedleg.get(i).getLegalaidNameEng();
                fnodeid=fetchedleg.get(i).getIdentifierId();
                refid=fetchedleg.get(i).getLegalaidSubCategoryId();
                upname=fetchedleg.get(i).getLegalaidNameBan();

                PopulatedfromDBLeg wp = new PopulatedfromDBLeg(fname,fnodeid,refid,fetchedleg);
                arraylist4.add(wp);


            }
            searchheads.clear();
            for (int ind=0;ind<arraylist4.size();ind++)
            {
                names=arraylist4.get(ind).getRank();
                ids=arraylist4.get(ind).getNodeid();
                SearchHolder sh = new SearchHolder(names,ids,currentCategoryID);
                searchheads.add(sh);
            }
           Collections.sort(searchheads,SearchHolder.NameCompare);

            indexbar(searchheads);



            itemList.setFastScrollEnabled(false);
            itemList.setFastScrollEnabled(true);
            filterText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    map.setVisibility(View.GONE);
                    sideIndex.setVisibility(View.GONE);
                    adapterLeg = new ListViewAdapterLeg(PlaceDetailsActivity.this, arraylist4);

                    itemList.setAdapter(adapterLeg);

                    return false;
                }
            });

            filterText.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    String text = filterText.getText().toString().toLowerCase(Locale.getDefault());

                    adapterLeg.filter(text);
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



        }
        else if (currentCategoryID==3){
            sideIndex.setVisibility(View.VISIBLE);
            EntertainmentServiceProviderTable entertainmentServiceProviderTable1=new EntertainmentServiceProviderTable(PlaceDetailsActivity.this);

            fetchedent=entertainmentServiceProviderTable1.getAllEntertainmentSubCategoriesInfo(currentCategoryID);
            arraylist5.clear();

            for (int i=0;i<fetchedent.size();i++)
            {

                fname=fetchedent.get(i).getNodeName();
                fnodeid=fetchedent.get(i).getNodeId();
                refid=fetchedent.get(i).getEntSubCategoryId();
                upname=fetchedent.get(i).getNodeNameBn();

                PopulatedfromDBEnt wp = new PopulatedfromDBEnt(fname,fnodeid,refid,fetchedent);
                arraylist5.add(wp);


            }
            searchheads.clear();
            for (int ind=0;ind<arraylist5.size();ind++)
            {
                names=arraylist5.get(ind).getRank();
                ids=arraylist5.get(ind).getNodeid();
                SearchHolder sh = new SearchHolder(names,ids,currentCategoryID);
                searchheads.add(sh);
            }
            Collections.sort(searchheads,SearchHolder.NameCompare);

            indexbar(searchheads);





            itemList.setFastScrollEnabled(false);
            itemList.setFastScrollEnabled(true);
            filterText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    sideIndex.setVisibility(View.GONE);
                    map.setVisibility(View.GONE);
                    adapterEnt = new ListViewAdapterEnt(PlaceDetailsActivity.this, arraylist5);
                    itemList.setAdapter(adapterEnt);
                    return false;
                }
            });

            filterText.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    String text = filterText.getText().toString().toLowerCase(Locale.getDefault());

                    adapterEnt.filter(text);
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



        }
        else if (currentCategoryID==6){
            sideIndex.setVisibility(View.VISIBLE);
            FinancialServiceProviderTable financialServiceProviderTable=new FinancialServiceProviderTable(PlaceDetailsActivity.this);
            fetchedfin=financialServiceProviderTable.getAllFinancialSubCategoriesInfo(currentCategoryID);
            arraylistfin.clear();

            for (int i=0;i<fetchedfin.size();i++)
            {

                fname=fetchedfin.get(i).getNodeName();
                fnodeid=fetchedfin.get(i).getNodeId();
                refid=fetchedfin.get(i).getRefNum();
                upname=fetchedfin.get(i).getNamebn();

                PopulatedfromDB wp = new PopulatedfromDB(fname,fnodeid,refid,fetchedfin);
                arraylistfin.add(wp);


            }
            searchheads.clear();
            for (int ind=0;ind<arraylistfin.size();ind++)
            {
                names=arraylistfin.get(ind).getRank();
                ids=arraylistfin.get(ind).getNodeid();
                SearchHolder sh = new SearchHolder(names,ids,currentCategoryID);
                searchheads.add(sh);
            }
            Collections.sort(searchheads,SearchHolder.NameCompare);

            indexbar(searchheads);





            itemList.setFastScrollEnabled(false);
            itemList.setFastScrollEnabled(true);
            filterText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    sideIndex.setVisibility(View.GONE);
                    map.setVisibility(View.GONE);
                    adapter = new ListViewAdapter(PlaceDetailsActivity.this, arraylistfin);
                    itemList.setAdapter(adapter);
                    return false;
                }
            });

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



        }
    }

    public void createData(int cat_id, String head,String placeChoice) {
        switch (cat_id) {
            case AppConstants.EDUCATION:

                SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivity.this);
                currentCategoryID = cat_id;
                EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivity.this);
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

                SubCategoryTable subCategoryTable2 = new SubCategoryTable(PlaceDetailsActivity.this);
                currentCategoryID = cat_id;
                EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivity.this);
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

                SubCategoryTable subCategoryTable3 = new SubCategoryTable(PlaceDetailsActivity.this);
                currentCategoryID = cat_id;
                HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivity.this);
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

                SubCategoryTable subCategoryTable4 = new SubCategoryTable(PlaceDetailsActivity.this);
                currentCategoryID = cat_id;
                FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivity.this);
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

                SubCategoryTable subCategoryTable5 = new SubCategoryTable(PlaceDetailsActivity.this);
                currentCategoryID = cat_id;
                LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivity.this);
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

                SubCategoryTable subCategoryTable6= new SubCategoryTable(PlaceDetailsActivity.this);
                currentCategoryID = cat_id;
                JobServiceProviderTable jobServiceProviderTable = new JobServiceProviderTable(PlaceDetailsActivity.this);
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

    public static void subCatInsGone()
    {
        insSubCat.setVisibility(View.GONE);
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
        if(dpi>300)
            v = li.inflate(R.layout.cat_list_mobile, llCatListHolder, false);
        else
        if(dpi<300 && height>1000)
            v = li.inflate(R.layout.cat_list_mobile1, llCatListHolder, false);
        else

            v = li.inflate(R.layout.cat_side_list_item, llCatListHolder, false);
        ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconCatList);
        //TextView tvName = (TextView) v.findViewById(R.id.tvNameCatList);



        // BE CAREFUL :: Category ID is being mapped as to the icon serial no.
        // in the AppConstants.ALL_CAT_ICONS array
        ivIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId() - 1]);
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
                        map.setVisibility(View.VISIBLE);
                        search.setVisibility(View.VISIBLE);
                        if (showsearch2.getVisibility() == View.VISIBLE) showsearch2.setVisibility(View.GONE);
                        llItemListHolderbody.setVisibility(View.VISIBLE);
                        ArrayList<EducationServiceProviderItem> educationServiceProvider;
                        educationServiceProvider = constructEducationListItem(ci.getId());
                        callMapFragmentWithEducationInfo(ci.getCatName(), ci.getId(), educationServiceProvider);
                        textView.setText("যে ধরনের পড়াশুনা সম্বন্ধে জানতে চান,\nতার উপর চাপ দেন");

                        break;
                    case AppConstants.HEALTH:
                        map.setVisibility(View.VISIBLE);
                        search.setVisibility(View.VISIBLE);
                        if (showsearch2.getVisibility() == View.VISIBLE) showsearch2.setVisibility(View.GONE);
                        llItemListHolderbody.setVisibility(View.VISIBLE);
                        ArrayList<HealthServiceProviderItem> healthServiceProvider;
                        healthServiceProvider = constructHealthListItem(ci.getId());
                        callMapFragmentWithHealthInfo(ci.getCatName(), ci.getId(), healthServiceProvider);
                        textView.setText("যে ধরনের চিকিৎসা সুবিধা  সম্বন্ধে জানতে চান,\nতার উপর চাপ দেন");
                        break;

                    //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:
                        map.setVisibility(View.VISIBLE);
                        search.setVisibility(View.VISIBLE);
                        if (showsearch2.getVisibility() == View.VISIBLE) showsearch2.setVisibility(View.GONE);
                        llItemListHolderbody.setVisibility(View.VISIBLE);
                        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
                        entertainmentServiceProvider = constructEntertainmentListItem(ci.getId());
                        callMapFragmentWithEntertainmentInfo(ci.getCatName(), ci.getId(), entertainmentServiceProvider);
                        textView.setText("যে ধরনের আনন্দ ফুর্তি সম্বন্ধে জানতে চান,\nতার উপর চাপ দেন");
                        break;


                    //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:

                        if (showsearch2.getVisibility() == View.VISIBLE) showsearch2.setVisibility(View.GONE);
                        llItemListHolderbody.setVisibility(View.VISIBLE);
                        map.removeAllViews();
                        //TODO write necessary codes for government

                        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(PlaceDetailsActivity.this).create();

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
                        map.setVisibility(View.VISIBLE);
                        search.setVisibility(View.VISIBLE);
                        if (showsearch2.getVisibility() == View.VISIBLE) showsearch2.setVisibility(View.GONE);
                        llItemListHolderbody.setVisibility(View.VISIBLE);
                        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
                        legalaidServiceProvider = constructlegalaidListItem(ci.getId());
                        callMapFragmentWithLegalAidInfo(ci.getCatName(), ci.getId(), legalaidServiceProvider);
                        textView.setText("যে ধরনের আইন কানুন সম্বন্ধে জানতে চান,\nতার উপর চাপ দেন");
                        break;
                    case AppConstants.FINANCIAL:
                        map.setVisibility(View.VISIBLE);
                        search.setVisibility(View.VISIBLE);
                        if (showsearch2.getVisibility() == View.VISIBLE) showsearch2.setVisibility(View.GONE);
                        llItemListHolderbody.setVisibility(View.VISIBLE);
                        ArrayList<FinancialServiceProviderItem> financialServiceProvider;
                        financialServiceProvider = constructfinancialListItem(ci.getId());
                        callMapFragmentWithFinancialInfo(ci.getCatName(), ci.getId(), financialServiceProvider);
                        textView.setText("যে ধরনের টাকা পয়সা সম্বন্ধে জানতে চান,\nতার উপর চাপ দেন");
                        break;
                    case AppConstants.JOB:

                        if (showsearch2.getVisibility() == View.VISIBLE) showsearch2.setVisibility(View.GONE);
                        llItemListHolderbody.setVisibility(View.VISIBLE);
                        map.removeAllViews();
                        final android.app.AlertDialog alertDialog2 = new android.app.AlertDialog.Builder(PlaceDetailsActivity.this).create();

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
                showSubCatListItem.setEnabled(false);
                showSubCatListItem.setVisibility(View.VISIBLE);
                subCatItemList.setVisibility(View.GONE);
                subCatItemListHeader.setVisibility(View.GONE);
                insSubCat.setVisibility(View.VISIBLE);
                seeMap.setVisibility(View.GONE);
                ArrayList<SubCategoryItem> subCatList = getSubCategoryList(ci.getId());
                placeDetailsLayout.setBackgroundResource(R.drawable.cool_crash_ui_backdrop);
                // categoryHeader.setText(ci.getCatName());
                categoryHeaderIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId() - 1]);
                if (isCatExpandedOnce)
                    showAnimatedSubcategories(subCatList, 0.5, AppConstants.ALL_CAT_ICONS[ci.getId() - 1], ci.getId()); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                else
                    showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS[ci.getId() - 1], ci.getId()); //AppConstants.CAT_LIST_LG_WIDTH_PERC);
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



        subCatItemList = (ExpandableListView) findViewById(R.id.listView);

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, groups, cat_id);
        subCatItemList.setAdapter(adapter);


    }
    private ArrayList<SubCategoryItem> constructSubCategoryListItem(int cat_id,String header)
    {
        ArrayList<SubCategoryItem> subCategoryItems;
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivity.this);
        subCategoryItems=subCategoryTable.getAllSubCategoriesHeader(cat_id,header);

        return subCategoryItems;
    }


    private void constructSubCategoryList(ArrayList<SubCategoryItem> subCategoryList,double dwPercentage,int cat_id) {
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
                        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(PlaceDetailsActivity.this).create();

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
                        final android.app.AlertDialog alertDialog2 = new android.app.AlertDialog.Builder(PlaceDetailsActivity.this).create();

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

                showSubCatListItem.setEnabled(true);
                subCatItemListHeader.setText(si.getSubcatHeader());
                constructSubCategoryItemList(cat_id, si.getSubcatHeader());
            }
        });

        return v;
    }

    private ArrayList<SubCategoryItem> getSubCategoryList(int id) {
        // TODO Get sub-categories from the SUB_CATEGORY local table : NEXT PHASE
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivity.this);
        return subCategoryTable.getAllSubCategories(id);
    }


    private void showAnimatedSubcategories(final ArrayList<SubCategoryItem> subCatList, double dwPerc, int iconId, final int cat_id) {
        isCatExpandedOnce = true;
        decCatListWidth(dwPerc);

        // TODO Inflate the sub-category list from right
        final RelativeLayout rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        if(subCatShowFlag==1)
        {
            rlSubCatHolder.startAnimation(slideOutFromLeftAnim());
        }
        subCatShowFlag=1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rlSubCatHolder.setVisibility(View.VISIBLE);
                rlSubCatHolder.startAnimation(slideInFromRightAnim());
                constructSubCategoryList(subCatList, 1.0, cat_id);
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
                else if (dwPerc < 0.7 && dpi>300)return;
                else if (dwPerc < 0.6)
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
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivity.this);
        educationServiceProvider = educationServiceProviderTable.getAllEducationSubCategoriesInfo(cat_id);
        return educationServiceProvider;
    }

    private ArrayList<EducationServiceProviderItem> constructEducationListItemForHeader(int cat_id,String header)
    {
        ArrayList<EducationServiceProviderItem> educationServiceProvider;
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivity.this);
        educationServiceProvider = educationServiceProviderTable.getAllEducationSubCategoriesInfoWithHead(cat_id, header);
        return educationServiceProvider;
    }

    private void callMapFragmentWithEducationInfo(String item_name,int cat_id,ArrayList<EducationServiceProviderItem> educationServiceProviderItems)
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
        mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);
        mapFragment.setEducationServiceProvider(educationServiceProviderItems);
        mapFragment.setLocationNameId(locationNameId);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
    }

    /***********************************************************Methods for Health*************************************************/

    private ArrayList<HealthServiceProviderItem> constructHealthListItem(int cat_id)
    {
        ArrayList<HealthServiceProviderItem> healthServiceProvider;
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivity.this);
        healthServiceProvider = healthServiceProviderTable.getAllHealthSubCategoriesInfo(cat_id);
        return healthServiceProvider;
    }

    private void callMapFragmentWithHealthInfo(String item_name,int cat_id,ArrayList<HealthServiceProviderItem> healthServiceProviderItems)
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
        mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);
        mapFragment.setHealthServiceProvider(healthServiceProviderItems);
        mapFragment.setLocationNameId(locationNameId);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
    }
    private ArrayList<HealthServiceProviderItem> constructHealthListItemForHeader(int cat_id,String header)
    {
        ArrayList<HealthServiceProviderItem> healthServiceProvider;
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceDetailsActivity.this);
        healthServiceProvider = healthServiceProviderTable.getAllHealthSubCategoriesInfoWithHead(cat_id, header);
        return healthServiceProvider;
    }

    /**********************************************************Methods for entertainment*******************************************/

    private ArrayList<EntertainmentServiceProviderItem> constructEntertainmentListItem(int cat_id)
    {
        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivity.this);
        entertainmentServiceProvider = entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfo(cat_id);
        return entertainmentServiceProvider;
    }

    private void callMapFragmentWithEntertainmentInfo(String item_name,int cat_id,ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProviderItems)
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
        mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);
        mapFragment.setEntertainmentServiceProvider(entertainmentServiceProviderItems);
        mapFragment.setLocationNameId(locationNameId);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
    }

    private ArrayList<EntertainmentServiceProviderItem> constructEntertainmentListItemForHeader(int cat_id,String header)
    {
        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(PlaceDetailsActivity.this);
        entertainmentServiceProvider = entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfoWithHead(cat_id, header);
        return entertainmentServiceProvider;
    }



    /**********************************************************Methods for government**********************************************/




    /**********************************************************Methods for legal***************************************************/

    private ArrayList<LegalAidServiceProviderItem> constructlegalaidListItem(int cat_id)
    {
        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivity.this);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfo(cat_id);
        return legalaidServiceProvider;
    }

    private ArrayList<LegalAidServiceProviderItem> constructlegalaidListItemForHeader(int cat_id,String header)
    {
        ArrayList<LegalAidServiceProviderItem> legalaidServiceProvider;
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceDetailsActivity.this);
        legalaidServiceProvider = legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfoWithHead(cat_id, header);
        return legalaidServiceProvider;
    }

    private void callMapFragmentWithLegalAidInfo(String item_name,int cat_id,ArrayList<LegalAidServiceProviderItem> legalaidServiceProviderItems)
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
        mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);
        mapFragment.setLegalaidServiceProvider(legalaidServiceProviderItems);
        mapFragment.setLocationNameId(locationNameId);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
    }




    /**********************************************************Methods for financial**********************************************/
    private ArrayList<FinancialServiceProviderItem> constructfinancialListItem(int cat_id)
    {
        ArrayList<FinancialServiceProviderItem> financialServiceProvider;
        FinancialServiceProviderTable financialServiceProviderTable = new  FinancialServiceProviderTable (PlaceDetailsActivity.this);
        financialServiceProvider = financialServiceProviderTable.getAllFinancialSubCategoriesInfo(cat_id);
        return financialServiceProvider;
    }

    private ArrayList<FinancialServiceProviderItem> constructfinancialListItemForHeader(int cat_id,String header)
    {
        ArrayList<FinancialServiceProviderItem> financialServiceProvider;
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceDetailsActivity.this);
        financialServiceProvider = financialServiceProviderTable.getAllFinancialSubCategoriesInfoWithHead(cat_id, header);
        return financialServiceProvider;
    }

    private void callMapFragmentWithFinancialInfo(String item_name,int cat_id,ArrayList<FinancialServiceProviderItem> financialServiceProviderItems)
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
        mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);
        mapFragment.setFinancialServiceProvider(financialServiceProviderItems);
        mapFragment.setLocationNameId(locationNameId);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
    }





    /**********************************************************Methods for job*****************************************************/

    private ArrayList<JobServiceProviderItem> constructjobListItem(int cat_id)
    {
        ArrayList<JobServiceProviderItem> jobServiceProvider;
        JobServiceProviderTable jobServiceProviderTable = new  JobServiceProviderTable (PlaceDetailsActivity.this);
        jobServiceProvider = jobServiceProviderTable.getAllJobSubCategoriesInfo(cat_id);
        return jobServiceProvider;
    }

    private ArrayList<JobServiceProviderItem> constructjobListItemForHeader(int cat_id,String header)
    {
        ArrayList<JobServiceProviderItem> jobServiceProvider;
        JobServiceProviderTable jobServiceProviderTable = new JobServiceProviderTable(PlaceDetailsActivity.this);
        jobServiceProvider = jobServiceProviderTable.getAllJobSubCategoriesInfoWithHead(cat_id, header);
        return jobServiceProvider;
    }

    private void callMapFragmentWithJobInfo(String item_name,int cat_id,ArrayList<JobServiceProviderItem> jobServiceProviderItems)
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
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
            if(locationNameId==AppConstants.PLACE_BAUNIABADH)
            {
                locationName = AppConstants.BAUNIABADH;
            }
            else if(locationNameId==AppConstants.PLACE_PARIS_ROAD)
            {
                locationName = AppConstants.PARIS_ROAD;
            }
        }
        editor.putInt("LocationNameId", locationNameId);
        editor.commit();

        if (Latitude != null&&AppUtils.isNetConnected(getApplicationContext())) {
            Double Lon = Double.parseDouble(Longitude);
            Double Lat = Double.parseDouble(Latitude);

            implementRouteDrawingFragment();


        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
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