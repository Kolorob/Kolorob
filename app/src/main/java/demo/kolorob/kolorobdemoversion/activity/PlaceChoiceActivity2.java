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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterAllCategories;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
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



//        else
//           toolbar = (Toolbar) findViewById(R.id.toolbars);

        // toolbar.setBackgroundResource(android.R.color.transparent);


        //toggle.syncState();


        //    gridView = (GridView) findViewById(R.id.grid);



        llSubCatListHolder = (LinearLayout) findViewById(R.id.llSubCatListHolder);
        allitemList=(ListView)findViewById(R.id.allitem);
        fleft=(LinearLayout)findViewById(R.id.linearLayout1);
        fright=(LinearLayout)findViewById(R.id.linearLayout2) ;

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

        help();

//        if (doubleBackToExitPressedOnce) {
//            new AlertDialog.Builder(this)
//                    .setTitle("Close")
//                    .setMessage("Are you sure you want to close Kolorob")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//        }
//searchmain.setVisibility(View.GONE);
//        placemain.setVisibility(View.VISIBLE);
//        this.doubleBackToExitPressedOnce = true;
//
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
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

        }
        else if (id == R.id.emergency_info) {

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
//        }
//         else if (id == R.id.local_representative) {
//
//           // Toast.makeText(con,"It will be added in next version.",Toast.LENGTH_LONG).show();
//            AlertMessage.showMessage(con, "Representative", "It will be added in next version.");
//
//        } else if (id == R.id.adv_info) {
//          //  Toast.makeText(con,"It will be added in next version.",Toast.LENGTH_LONG).show();
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

    public void back(){

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

    public void help(){
        LayoutInflater layoutInflater = LayoutInflater.from(PlaceChoiceActivity2.this);
        View promptView = layoutInflater.inflate(R.layout.help_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlaceChoiceActivity2.this);
        alertDialogBuilder.setView(promptView);

        final EditText userfeedback = (EditText) promptView.findViewById(R.id.edittext);
        final Button submit= (Button)promptView.findViewById(R.id.submit_btn);
        final Button button= (Button)promptView.findViewById(R.id.phone_call);
        final ImageView imageView7= (ImageView)promptView.findViewById(R.id.imageView7);



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

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = alertDialogBuilder.create();

                alert.cancel();
                finish();
            }
        });


        // setup a dialog window
        alertDialogBuilder.setCancelable(false);

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
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
                                AlertMessage.showMessage(PlaceChoiceActivity2.this, "মন্তব্যটি পাঠানো হয়ছে",
                                        "মন্তব্য করার জন্য আপনাকে ধন্যবাদ");
                            }
                            else
                                AlertMessage.showMessage(PlaceChoiceActivity2.this, "মন্তব্য পাঠানো সফল হয়নি",
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
