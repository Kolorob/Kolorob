package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterAllCategories;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

/**
 * Created by arafat on 10/5/15.
 *
 * @author arafat
 */

public class PlaceChoiceActivity2 extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    LinearLayout first,second,third,menubar,SearchBar,SearchIcon,imgbau,imgpar;
    int width,height;
    EditText Searchall;
    private static final int DELAY_PLACE_DETAILS_LAUNCH_ANIM = 500;
    private LinearLayout llSubCatListHolder,exlist;
    ArrayList<String> subcategorynames;
    ListViewAdapterAllCategories adapter;
    EditText filterText;
    ListView allitemList;
    String filterword;
    TextView searchtext;
    ImageButton more,help;
    int snumber=0;

    String app_ver;
    NotificationManager manager;
    Notification myNotication;
    public int getSnumber() {
        return snumber;
    }

    public void setSnumber(int snumber) {
        this.snumber = snumber;
    }

    public String getFilterword() {
        return filterword;
    }

    public void setFilterword(String filterword) {
        this.filterword = filterword;
    }
    boolean doubleBackToExitPressedOnce = false;
    boolean catstatus=false;
    int filcatid=0;
    RelativeLayout catholder;
    CheckBox check;
    LinearLayout fholder,fleft,fright;
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
    ArrayList<String>filter=new ArrayList<>();
    ArrayList<String>filter2=new ArrayList<>();
    FrameLayout mapp;
    public int getFilcatid() {
        return filcatid;
    }
int val;
    public void setFilcatid(int filcatid) {
        this.filcatid = filcatid;
    }

    private Context con;
    LinearLayout placemain,searchmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice2);
mapp=(FrameLayout)findViewById(R.id.map_fragment);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("Value", false);
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();

        editor.putInt("ValueD", 10);

        editor.commit();

        editor.commit();
        con = this;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels-32;
        width=displayMetrics.widthPixels-32;
placemain=(LinearLayout)findViewById(R.id.placemainpageholder);
        searchmain=(LinearLayout)findViewById(R.id.searchviewholder);
        searchmain.setVisibility(View.GONE);
        placemain.setVisibility(View.VISIBLE);

        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();


        /// Log.d(">>>>>>","You are in onResume");

        val = settings.getInt("KValue", 0);
        if (val==6)val=val+1;
        Log.e("ASinplaceDetails",String.valueOf(val));
        if (val!=7)
        {
            final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(PlaceChoiceActivity2.this).create();

            alertDialog.setMessage("Data have not uploaded probably");
            alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Intent i = new Intent(PlaceChoiceActivity2.this, OpeningActivity.class);

                            alertDialog.dismiss();
                            startActivity(i);
                            finish();
                        }
                    });
            alertDialog.getWindow().setLayout(200, 300);
            alertDialog.show();
        }
        Log.d("...>>>","Layout width"+width);

        try
        {
            app_ver = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        }


        catch (PackageManager.NameNotFoundException e) {
           // Log.e(tag, e.getMessage());

        }

        Log.d(">>>","Application Version: "+app_ver);


        checkVersion(Double.parseDouble(app_ver));

            first=(LinearLayout)findViewById(R.id.top_section);
        second= (LinearLayout)findViewById(R.id.bauniabad_section);
        third = (LinearLayout)findViewById(R.id.parisRoad_section);
       // menubar=(LinearLayout)findViewById(R.id.menuBar);
       // SearchBar=(LinearLayout)findViewById(R.id.SearchBar);
       // SearchIcon=(LinearLayout)findViewById(R.id.SearchIcon);
        imgbau=(LinearLayout)findViewById(R.id.imageBau);
        imgpar=(LinearLayout)findViewById(R.id.imagePar);

       //LayoutParams = first.getLayoutParams();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) first.getLayoutParams();
        params.height = height/6;
        params.width = width;
        first.setLayoutParams(params);


        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) second.getLayoutParams();
        params2.height = height/5;
        params2.width = width;
        second.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) third.getLayoutParams();
        params3.height = height/5;
        params3.width = width;
        third.setLayoutParams(params3);





//        LinearLayout.LayoutParams paramsMenue = (LinearLayout.LayoutParams) menubar.getLayoutParams();
//        paramsMenue.height = height/16;
//        paramsMenue.width = (width*2)/14;
//
//        Log.d("...>>>", "Munue Width" + (width * 2) / 14);
//        menubar.setLayoutParams(paramsMenue);
//
//        LinearLayout.LayoutParams paramsSearch = (LinearLayout.LayoutParams) SearchBar.getLayoutParams();
//        paramsSearch.height = height/16;
//        paramsSearch.width = (width*10)/14;
//        Log.d("...>>>", "SearchBar Width" + (width * 10) / 14);
//        SearchBar.setLayoutParams(paramsSearch);
//
//        LinearLayout.LayoutParams paramsSearchIcon = (LinearLayout.LayoutParams) SearchIcon.getLayoutParams();
//        paramsSearchIcon.height = height/16;
//        paramsSearchIcon.width = (width*2)/14;
//        Log.d("...>>>", "Search Icon" + (width * 2) / 14);
//        SearchIcon.setLayoutParams(paramsSearchIcon);


        LinearLayout.LayoutParams paramsBau = (LinearLayout.LayoutParams) imgbau.getLayoutParams();

        paramsBau.width = (width*2)/3;
        imgbau.setLayoutParams(paramsBau);

Searchall=(EditText)findViewById(R.id.searchall);
        Searchall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                calladapter(false);
                placemain.setVisibility(View.GONE);
                searchmain.setVisibility(View.VISIBLE);
                mapp.setVisibility(View.GONE);
                allitemList.setVisibility(View.VISIBLE);
                catholder.setVisibility(View.GONE);
                fholder.setVisibility(View.GONE);
                catgroup.setVisibility(View.GONE);
               if(catgroup.getCheckedRadioButtonId()!=-1)catgroup.clearCheck();
                check.setChecked(false);
                check.setVisibility(View.GONE);

                return false;
            }
        });
        LinearLayout.LayoutParams paramsPar = (LinearLayout.LayoutParams) imgpar.getLayoutParams();

        paramsPar.width = (width*2)/3;
        imgpar.setLayoutParams(paramsPar);

        Searchall.setOnClickListener((View.OnClickListener) this);
        imgbau.setOnClickListener((View.OnClickListener) this);
        imgpar.setOnClickListener((View.OnClickListener) this);

//       if(height>1000)
       toolbar = (Toolbar) findViewById(R.id.toolbar);
//        else
//           toolbar = (Toolbar) findViewById(R.id.toolbars);

        // toolbar.setBackgroundResource(android.R.color.transparent);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        ab.setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        more=(ImageButton)findViewById(R.id.morebutton);
        searchtext=(TextView)findViewById(R.id.textView17) ;
        check=(CheckBox)findViewById(R.id.searchmbox);
        more.setOnClickListener((View.OnClickListener) this);

        fholder=(LinearLayout)findViewById(R.id.LinearLayoutfilter);
        catholder=(RelativeLayout)findViewById(R.id.categoryfilterholder);
        catholder.setVisibility(View.GONE);
        catgroup=(RadioGroup)findViewById(R.id.catradioGroup);
        catgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.edradioButton) {

                    setFilcatid(1);
                    catstatus=true;
                    getFilcatid();
                    calladapter(catstatus);

                } else  if (checkedId == R.id.helradioButton2) {
                    //do work when radioButton2 is active
                    setFilcatid(2);
                    catstatus=true;
                    calladapter(catstatus);
                }
                else  if (checkedId == R.id.entradioButton5) {
                    //do work when radioButton2 is active
                    setFilcatid(3);
                    catstatus=true;
                    calladapter(catstatus);
                }
                else  if (checkedId == R.id.finradioButton4) {
                    //do work when radioButton2 is active
                    setFilcatid(6);
                    catstatus=true;
                    calladapter(catstatus);
                }
                else  if (checkedId == R.id.legradioButton3) {
                    //do work when radioButton2 is active
                    setFilcatid(5);
                    catstatus=true;
                    calladapter(catstatus);
                }

                check.setVisibility(View.VISIBLE);
            }
        });
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    // perform logic
                    catgroup.setVisibility(View.GONE);
                    fholder.setVisibility(View.VISIBLE);
                    populatefilterwords(getFilcatid());
                    check.setVisibility(View.GONE);
                }

            }
        });
//        else
//           toolbar = (Toolbar) findViewById(R.id.toolbars);

        // toolbar.setBackgroundResource(android.R.color.transparent);


        //toggle.syncState();


        //    gridView = (GridView) findViewById(R.id.grid);



        llSubCatListHolder = (LinearLayout) findViewById(R.id.llSubCatListHolder);
        allitemList=(ListView)findViewById(R.id.allitem);
        fleft=(LinearLayout)findViewById(R.id.linearLayout1);
        fright=(LinearLayout)findViewById(R.id.linearLayout2) ;
        Populateholder();
help=(ImageButton)findViewById(R.id.helpicon);

    }


   public void checkVersion(final double current_version)
    {
        getRequest(PlaceChoiceActivity2.this, "http://kolorob.net/app_version.json", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {
                        Log.d(">>>","Start Json Parsing "+apiContent);
                            try {
                                JSONObject jo = new JSONObject(apiContent);
                                Log.d(">>>","JsonObject: "+jo);
                                Double remote_version = jo.getDouble("version");

                                if(remote_version>current_version)
                                {
                                    Toast.makeText(PlaceChoiceActivity2.this, "You must update the App =)",
                                            Toast.LENGTH_LONG).show();
                                    generateNotification();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }
                }
        );
    }



    public void generateNotification()
    {
        String url = "https://play.google.com/store/apps/details?id=demo.kolorob.kolorobdemoversion&hl=en";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        // i.setData(Uri.parse("package:demo.kolorob.kolorobdemoversion"));

        //Intent intent = new Intent(this,NotificationView.class);

        manager.cancel(11);
        PendingIntent pendingIntent = PendingIntent.getActivity(PlaceChoiceActivity2.this, 0, i, 0);

        Notification.Builder builder = new Notification.Builder(PlaceChoiceActivity2.this);

        builder.setAutoCancel(false);
        builder.setTicker("New Version of Kolorob is Available");
        builder.setContentTitle("Update kolorob");
      //  builder.setContentText("To update click here.");
        builder.setSmallIcon(R.drawable.kolorob_logo_first_page);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
      //  builder.setSubText("Click here to update");   //API level 16
        builder.setNumber(100);
     //   builder.build();

         builder.setContentTitle("Update kolorob").setContentText("New Version of Kolorob is Available")
                .setSmallIcon(R.drawable.kolorob_logo_first_page).getNotification();

        myNotication = builder.getNotification();
        manager.notify(11, myNotication);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageBau:

                gotoPlaceDetailsView(AppConstants.PLACE_BAUNIABADH);
                break;

            case R.id.imagePar:

                gotoPlaceDetailsView(AppConstants.PLACE_PARIS_ROAD);
                break;

            case R.id.morebutton:
                searchtext.setText(R.string.searchtext);
                catholder.setVisibility(View.VISIBLE);
                catgroup.setVisibility(View.VISIBLE);
                break;
            case R.id.helpicon:
                helpDialog(view);
                break;

            default:
                break;

        }

    }



    private void gotoPlaceDetailsView(final int placeId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PlaceChoiceActivity2.this, PlaceDetailsActivityNewLayout.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(AppConstants.KEY_PLACE, placeId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
               // startActivity(intent);

            }
        }, DELAY_PLACE_DETAILS_LAUNCH_ANIM);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            new AlertDialog.Builder(this)
                    .setTitle("Close")
                    .setMessage("Are you sure you want to close Kolorob")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
searchmain.setVisibility(View.GONE);
        placemain.setVisibility(View.VISIBLE);
        this.doubleBackToExitPressedOnce = true;


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_choice_activity2, menu);
        return true;
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub

        super.onStart();

        System.out.println("----main activity---onStart---");
        this.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
    public void populatefilterwords(int filcatid)
    {
        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceChoiceActivity2.this);
        subcatHolders.clear();
        filter.clear();
        filter2.clear();
        fleft.removeAllViews();
        fright.removeAllViews();
        subholders=subCategoryTable.getcatSubCategories(filcatid);

        int upto=subholders.size()/2;
        for (int f=0;f<subholders.size();f++)
        {
            if (f>=upto)
                filter2.add(subholders.get(f).getSubcatname());
            else
            {
                filter.add(subholders.get(f).getSubcatname());}
        }

        final RadioButton[] rb = new RadioButton[30];
        fgrp1 = new RadioGroup(this); //create the RadioGroup
        fgrp1.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<filter.size(); i++){
            rb[i]  = new RadioButton(this);
            fgrp1.addView(rb[i]);
          //the RadioButtons are added to the radioGroup instead of the layout
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
        searchtext.setText(R.string.searchtext);
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

    public void Populateholder()
    {
        filterText = (EditText)findViewById(R.id.searchall);
        EducationServiceProviderTable educationServiceProviderTable=new EducationServiceProviderTable(PlaceChoiceActivity2.this);
        EntertainmentServiceProviderTable entertainmentServiceProviderTable=new EntertainmentServiceProviderTable(PlaceChoiceActivity2.this);
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceChoiceActivity2.this);
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceChoiceActivity2.this);
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceChoiceActivity2.this);
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
    protected void onResume() {
        super.onResume();
        //Log.d(">>>>>>>>","CategoryId "+currentCategoryID);

        SharedPreferences pref = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String Latitude = pref.getString("Latitude", null);
        String Longitude = pref.getString("Longitude", null);
        Boolean valuecheck=pref.getBoolean("Value",false);
        int getv=pref.getInt("ValueD",0);
        searchmain.setVisibility(View.GONE);
        placemain.setVisibility(View.VISIBLE);

        // Toast.makeText(getApplicationContext(), "Now I am in onResume ", Toast.LENGTH_SHORT).show();


        /// Log.d(">>>>>>","You are in onResume");

if(valuecheck==false)
{

       allitemList.setVisibility(View.VISIBLE);

}
        if (valuecheck!=false && getv==10)
        {
            placemain.setVisibility(View.GONE);
            allitemList.setVisibility(View.GONE);
           mapp.setVisibility(View.VISIBLE);
            searchmain.setVisibility(View.VISIBLE);

           // map.setVisibility(View.VISIBLE);
            implementRouteDrawingFragmentOSM();
        }

        else {
            Intent intent;
            intent = getIntent();
            if (null != intent) {

            }


            if (Latitude != null && AppUtils.isNetConnected(getApplicationContext())) {
                Double Lon = Double.parseDouble(Longitude);
                Double Lat = Double.parseDouble(Latitude);



            }
        }
    }
    public void implementRouteDrawingFragmentOSM()
    {


        MapFragmentRouteOSM mapFragmentOSM =new MapFragmentRouteOSM();

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragmentOSM);
        fragmentTransaction.commit();
    }
    public void helpDialog(View v){

        LayoutInflater layoutInflater = LayoutInflater.from(PlaceChoiceActivity2.this);
        View promptView = layoutInflater.inflate(R.layout.help_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlaceChoiceActivity2.this);
        alertDialogBuilder.setView(promptView);

        final EditText userfeedback = (EditText) promptView.findViewById(R.id.edittext);
        final Button submit= (Button)promptView.findViewById(R.id.submit_btn);
        final Button button= (Button)promptView.findViewById(R.id.phone_call);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= SharedPreferencesHelper.getUser(PlaceChoiceActivity2.this);
                String testUser=SharedPreferencesHelper.getFeedback(PlaceChoiceActivity2.this);
                if(user.equals(testUser))
                {
                    AlertMessage.showMessage(con, "দুঃখিত মতামত গ্রহন করা সম্ভব হচ্ছে না", "আপনি ইতিপূর্বে মতামত দিয়ে ফেলেছেন");
                }

                else
                    sendDataToserver(userfeedback.getText().toString());

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall();
                Toast.makeText(PlaceChoiceActivity2.this, "...ok....",Toast.LENGTH_LONG).show();
            }
        });
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("ঠিক আছে", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText.setText("Hello, " + userfeedback.getText());
                    }
                })
                .setNegativeButton("বাতিল করুন",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }
    public void phoneCall()
    {

        Intent callIntent1 = new Intent(Intent.ACTION_CALL);
        callIntent1.setData(Uri.parse("tel:" + "01796559112"));
        if(checkPermission())
            startActivity(callIntent1);

    }
    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }
    public void sendDataToserver(final String text)
    {
        String username=SharedPreferencesHelper.getUser(PlaceChoiceActivity2.this);
        SharedPreferencesHelper.setFeedback(PlaceChoiceActivity2.this,username);

        String url = "http://www.kolorob.net/KolorobApi/api/help/save_query?query="+text;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PlaceChoiceActivity2.this,response,Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jo = new JSONObject(response);
                            JSONArray forms = jo.getJSONArray("true");

                            if(forms.toString().equals("true"))
                            {
                                demo.kolorob.kolorobdemoversion.helpers.AlertMessage.showMessage(PlaceChoiceActivity2.this, "মন্তব্যটি পাঠানো হয়ছে",
                                        "মন্তব্য করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                demo.kolorob.kolorobdemoversion.helpers.AlertMessage.showMessage(PlaceChoiceActivity2.this, "মন্তব্য পাঠানো সফল হয়নি",
                                        "মন্তব্য করার জন্য আপনাকে ধন্যবাদ");







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlaceChoiceActivity2.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(PlaceChoiceActivity2.this);
        requestQueue.add(stringRequest);



    }
}
