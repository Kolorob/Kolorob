package demo.kolorob.kolorobdemoversion.activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;

import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.Lg;
import demo.kolorob.kolorobdemoversion.fragment.MapFragment;
/**
 * Created by touhid on 12/3/15.
 *
 * @author touhid,arafat
 */
public class PlaceDetailsActivity extends BaseActivity  implements View.OnClickListener {

    private static final String TAG = PlaceDetailsActivity.class.getSimpleName();
    private static final int ANIM_INTERVAL = 100;

    private static double VIEW_WIDTH;
    private ScrollView svCatList;
    private LinearLayout llCatListHolder;
    private LinearLayout llSubCatListHolder;
    private FrameLayout placeDetailsLayout;
    private TextView categoryHeader;
    private ImageView categoryHeaderIcon;
    private TextView subCatItemListHeader;
    private ListView subCatItemList;
    private Button showSubCatListItem;
    private Button seeMap;
    private static TextView insSubCat;
    private static FrameLayout map;

    private boolean isCatExpandedOnce = false;
    private int primaryIconWidth;
    private int subCatShowFlag=0;
    private int locationNameId;
    private String locationName;


    //TODO Declare object array for each subcategory item. Different for each category. Depends on the database table.
    private ArrayList<EducationServiceProviderItem> currentEducationServiceProvider;
    private ArrayList<EntertainmentServiceProviderItem> currentEntertainmentServiceProvider;
    private ArrayList<HealthServiceProviderItem> currentHealthServiceProvider;


    //common for all categories
    private ArrayList<SubCategoryItem> currentSubCategoryItem;
    private int currentCategoryID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_activity);

        Intent intent = getIntent();
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


        categoryHeader = (TextView) findViewById(R.id.tv_cat_name);
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


        subCatItemListHeader = (TextView) findViewById(R.id.tv_sub_cat_item_list_head);
        subCatItemList = (ListView) findViewById(R.id.sub_cat_item_list);
        map = (FrameLayout) findViewById(R.id.map_fragment);
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
        final RelativeLayout rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        rlSubCatHolder.setVisibility(View.INVISIBLE);


        showSubCatListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subCatItemList.setVisibility(View.VISIBLE);
                subCatItemListHeader.setVisibility(View.VISIBLE);
                showSubCatListItem.setVisibility(View.GONE);
                seeMap.setVisibility(View.VISIBLE);
            }
        });
        seeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subCatItemList.setVisibility(View.GONE);
                subCatItemListHeader.setVisibility(View.GONE);
                showSubCatListItem.setVisibility(View.VISIBLE);
                seeMap.setVisibility(View.GONE);
            }
        });

        callMapFragment();
        subCatItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int subcategoryId=0;
                int i=0;
                for(SubCategoryItem ct:currentSubCategoryItem)
                {
                    if(i==position)
                    {
                        subcategoryId=ct.getId();
                    }
                    i++;
                }
                /*This will generate alert list with subcategory items*/
                /*following code will be different for each category*/
                /*category id 1 means education.
                * category id 2 means health
                * category id 3 means entertainment
                * category id 4 means government
                * category id 5 means legal
                * category id 6 means financial
                * category id 7 means job*/
                switch (currentCategoryID)
                {
                    case AppConstants.EDUCATION:
                        EducationServiceProviderTable educationServiceProviderTables = new EducationServiceProviderTable(PlaceDetailsActivity.this);
                        ArrayList<EducationServiceProviderItem> educationServiceProviderItems;
                        educationServiceProviderItems = educationServiceProviderTables.getAllEducationSubCategoriesInfo(currentCategoryID, subcategoryId);
                        ArrayList<String> itemName = new ArrayList<String>();
                        currentEducationServiceProvider = educationServiceProviderItems;
                        for (EducationServiceProviderItem si : educationServiceProviderItems) {
                            itemName.add(si.getEduNameEng());
                        }
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlaceDetailsActivity.this);
                        LayoutInflater inflater = getLayoutInflater();
                        View convertView = (View) inflater.inflate(R.layout.subcat_item_list, null);
                        TextView head = (TextView) convertView.findViewById(R.id.tv_item_hd);
                        String header = subCatItemList.getItemAtPosition(position).toString();
                        head.setText(header);
                        alertDialog.setView(convertView);
                        ListView lv = (ListView) convertView.findViewById(R.id.subcat_list);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlaceDetailsActivity.this, R.layout.sub_cat_item_list_item, R.id.textView5, itemName);
                        lv.setAdapter(adapter);
                        alertDialog.show();
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                EducationServiceProviderItem currEduItem = null;
                                int i = 0;
                                for (EducationServiceProviderItem et : currentEducationServiceProvider) {
                                    if (i == position) {
                                        currEduItem = et;
                                    }
                                }
                                Intent ii = new Intent(PlaceDetailsActivity.this, DetailsInfoActivity.class);
                                ii.putExtra(AppConstants.KEY_DETAILS_VIEW, currEduItem);
                                startActivity(ii);
                            }
                        });
                        break;
                    case AppConstants.HEALTH:

                        HealthServiceProviderTable healthServiceProviderTables = new HealthServiceProviderTable(PlaceDetailsActivity.this);
                        ArrayList<HealthServiceProviderItem> healthServiceProviderItems;
                        healthServiceProviderItems = healthServiceProviderTables.getAllHealthSubCategoriesInfo(currentCategoryID, subcategoryId);
                        ArrayList<String> itemNameHealth = new ArrayList<String>();
                        currentHealthServiceProvider = healthServiceProviderItems;
                        for (HealthServiceProviderItem si : healthServiceProviderItems) {
                            itemNameHealth.add(si.getNodeName());
                        }
                        AlertDialog.Builder alertDialogHealth = new AlertDialog.Builder(PlaceDetailsActivity.this);
                        LayoutInflater inflaterHealth = getLayoutInflater();
                        View convertViewHealth = (View) inflaterHealth.inflate(R.layout.subcat_item_list, null);
                        TextView headHealth = (TextView) convertViewHealth.findViewById(R.id.tv_item_hd);
                        String headerHealth = subCatItemList.getItemAtPosition(position).toString();
                        headHealth.setText(headerHealth);
                        alertDialogHealth.setView(convertViewHealth);
                        ListView lvHealth = (ListView) convertViewHealth.findViewById(R.id.subcat_list);
                        ArrayAdapter<String> adapterHealth = new ArrayAdapter<String>(PlaceDetailsActivity.this, R.layout.sub_cat_item_list_item, R.id.textView5, itemNameHealth);
                        lvHealth.setAdapter(adapterHealth);
                        alertDialogHealth.show();
                        lvHealth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                HealthServiceProviderItem currHealthItem = null;
                                int i = 0;
                                for (HealthServiceProviderItem et : currentHealthServiceProvider) {
                                    if (i == position) {
                                        currHealthItem = et;
                                    }
                                }
                                Intent hel = new Intent(PlaceDetailsActivity.this, DetailsInfoActivityHealth.class);
                                hel.putExtra(AppConstants.KEY_DETAILS_VIEW, currHealthItem);
                                startActivity(hel);
                            }
                        });
                        break;


                        //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:
                        EntertainmentServiceProviderTable entertainmentServiceProviderTables = new EntertainmentServiceProviderTable(PlaceDetailsActivity.this);
                        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProviderItems;
                        entertainmentServiceProviderItems = entertainmentServiceProviderTables.getAllEntertainmentSubCategoriesInfo( subcategoryId);
                        ArrayList<String> itemNameEnt = new ArrayList<String>();
                        currentEntertainmentServiceProvider = entertainmentServiceProviderItems;
                        for (EntertainmentServiceProviderItem si : entertainmentServiceProviderItems) {
                            itemNameEnt.add(si.getNodeName());
                        }
                        AlertDialog.Builder alertDialogEnt = new AlertDialog.Builder(PlaceDetailsActivity.this);
                        LayoutInflater inflaterEnt = getLayoutInflater();
                        View convertViewEnt = (View) inflaterEnt.inflate(R.layout.subcat_item_list, null);
                        TextView headEnt = (TextView) convertViewEnt.findViewById(R.id.tv_item_hd);
                        String headerEnt = subCatItemList.getItemAtPosition(position).toString();
                        headEnt.setText(headerEnt);
                        alertDialogEnt.setView(convertViewEnt);
                        ListView lvEnt = (ListView) convertViewEnt.findViewById(R.id.subcat_list);
                        ArrayAdapter<String> adapterEnt = new ArrayAdapter<String>(PlaceDetailsActivity.this, R.layout.sub_cat_item_list_item, R.id.textView5, itemNameEnt);
                        lvEnt.setAdapter(adapterEnt);
                        alertDialogEnt.show();
                        lvEnt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                EntertainmentServiceProviderItem currEntItem = null;
                                int i = 0;
                                for (EntertainmentServiceProviderItem et : currentEntertainmentServiceProvider) {
                                    if (i == position) {
                                        currEntItem = et;
                                    }
                                }
                                Intent iiEnt = new Intent(PlaceDetailsActivity.this, DetailsInfoActivity.class);
                                iiEnt.putExtra(AppConstants.KEY_DETAILS_VIEW, currEntItem);
                                startActivity(iiEnt);
                            }
                        });
                        //TODO write necessary codes for entertainment
                        break;
                    case AppConstants.GOVERNMENT:


                        //TODO write necessary codes for government
                        break;
                    case AppConstants.LEGAL:
                        //TODO write necessary codes for legal
                        break;
                    case AppConstants.FINANCIAL:
                        //TODO write necessary codes for financial
                        break;
                    case AppConstants.JOB:
                        //TODO write necessary codes for job
                        break;
                    default:
                        break;
                }

            }
        });
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
        for (CategoryItem ci : categoryList) {
            llCatListHolder.addView(getCategoryListItemView(ci, dwPercentage));

        }
    }

    private View getCategoryListItemView(final CategoryItem ci, double dwPercentage) {
        LayoutInflater li = LayoutInflater.from(this);
        View v = li.inflate(R.layout.cat_side_list_item, llCatListHolder, false);
        ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconCatList);
        TextView tvName = (TextView) v.findViewById(R.id.tvNameCatList);

        // BE CAREFUL :: Category ID is being mapped as to the icon serial no.
        // in the AppConstants.ALL_CAT_ICONS array
        ivIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId() - 1]);
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        lpIv.width = (int) (primaryIconWidth * dwPercentage);
        ivIcon.setLayoutParams(lpIv);

        tvName.setText(ci.getCatName());
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

                currentCategoryID=ci.getId();

                /*code for category*/
                /*following code will be different for each category*/
                /*category id 1 means education.
                * category id 2 means health
                * category id 3 means entertainment
                * category id 4 means government
                * category id 5 means legal
                * category id 6 means financial
                * category id 7 means job*/
                switch (currentCategoryID)
                {
                    case AppConstants.EDUCATION:
                        ArrayList<EducationServiceProviderItem> educationServiceProvider;
                        educationServiceProvider = constructEducationListItem(ci.getId());
                        callMapFragmentWithEducationInfo(ci.getCatName(),ci.getId(),educationServiceProvider);
                        break;
                    case AppConstants.HEALTH:

                        ArrayList<HealthServiceProviderItem> healthServiceProvider;
                        healthServiceProvider = constructHealthListItem(ci.getId());
                        callMapFragmentWithHealthInfo(ci.getCatName(), ci.getId(), healthServiceProvider);
                        break;


                        //TODO write necessary codes for health

                    case AppConstants.ENTERTAINMENT:
                        ArrayList<EntertainmentServiceProviderItem> entertainmentServiceProvider;
                        entertainmentServiceProvider = constructEntertainmentListItem(ci.getId());
                        callMapFragmentWithEntertainmentInfo(ci.getCatName(), ci.getId(), entertainmentServiceProvider);
                        break;

                        //TODO write necessary codes for entertainment

                    case AppConstants.GOVERNMENT:
                        //TODO write necessary codes for government
                        break;
                    case AppConstants.LEGAL:
                        //TODO write necessary codes for legal
                        break;
                    case AppConstants.FINANCIAL:
                        //TODO write necessary codes for financial
                        break;
                    case AppConstants.JOB:
                        //TODO write necessary codes for job
                        break;
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
                categoryHeader.setText(ci.getCatName());
                categoryHeaderIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId() - 1]);
                if (isCatExpandedOnce)
                    showAnimatedSubcategories(subCatList, .3,AppConstants.ALL_CAT_ICONS[ci.getId() - 1],ci.getId()); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                else
                    showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS[ci.getId() - 1],ci.getId()); //AppConstants.CAT_LIST_LG_WIDTH_PERC);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlaceDetailsActivity.this, R.layout.sub_cat_item_list_item,R.id.textView5, itemName);
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
        LayoutInflater li = LayoutInflater.from(this);
        View v = li.inflate(R.layout.sub_cat_list_item, llCatListHolder, false);
        ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_sub_cat_icon);
        TextView tvName = (TextView) v.findViewById(R.id.tv_sub_cat_name);
        ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONS[cat_id-1]);
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        lpIv.width = (int) (primaryIconWidth * dwPercentage);
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
                        //TODO write necessary codes for government
                        break;
                    case AppConstants.LEGAL:
                        //TODO write necessary codes for legal
                        break;
                    case AppConstants.FINANCIAL:
                        //TODO write necessary codes for financial
                        break;
                    case AppConstants.JOB:
                        //TODO write necessary codes for job
                        break;
                    default:
                        break;
                }
                /*code for all*/
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
                constructSubCategoryList(subCatList,1.0,cat_id);
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
                if (dwPerc < .6)
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





    /**********************************************************Methods for financial**********************************************/





    /**********************************************************Methods for job*****************************************************/






}
