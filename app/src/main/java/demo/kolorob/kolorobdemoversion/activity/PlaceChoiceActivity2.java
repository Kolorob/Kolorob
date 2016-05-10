package demo.kolorob.kolorobdemoversion.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Vector;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class PlaceChoiceActivity2 extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    AutoCompleteTextView autocompletetextview2;
    LinearLayout first,second,third,menubar,SearchBar,SearchIcon,imgbau,imgpar;
    int width,height;
    private static final int DELAY_PLACE_DETAILS_LAUNCH_ANIM = 500;
    Vector vector= new Vector();
    Vector compare= new Vector();
    Vector vectorHel= new Vector();
    Vector vectorEnt= new Vector();
    Vector vectorEdu= new Vector();
    Vector vectorFin= new Vector();
    Vector vectorLeg= new Vector();

    private Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice2);


        con = this;

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels-32;
        width=displayMetrics.widthPixels-32;

        Log.d("...>>>","Layout width"+width);

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


        LinearLayout.LayoutParams paramsPar = (LinearLayout.LayoutParams) imgpar.getLayoutParams();

        paramsPar.width = (width*2)/3;
        imgpar.setLayoutParams(paramsPar);


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


    }



    public void search()
    {
        final EducationServiceProviderTable educationServiceProviderTable=new EducationServiceProviderTable(PlaceChoiceActivity2.this);
        final EntertainmentServiceProviderTable entertainmentServiceProviderTable=new EntertainmentServiceProviderTable(PlaceChoiceActivity2.this);
        final HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(PlaceChoiceActivity2.this);
        final FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(PlaceChoiceActivity2.this);
        final LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(PlaceChoiceActivity2.this);
        vector=educationServiceProviderTable.getAllEducationSubCategoriesInfo();
        vectorEnt=entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfo();
        vectorHel=healthServiceProviderTable.getAllEntertainmentSubCategoriesInfo();
        vectorFin=financialServiceProviderTable.getAllEntertainmentSubCategoriesInfo();
        vectorLeg=legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfo();

        compare.removeAllElements();

        vectorEdu.addAll(vectorEnt);
        vectorEdu.addAll(vector);
        vectorEdu.addAll(vectorHel);
        vectorEdu.addAll(vectorFin);
        vectorEdu.addAll(vectorLeg);










        autocompletetextview2 = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1x);



        ArrayAdapter<String> adapter2;





        adapter2 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, vectorEdu);


        autocompletetextview2.setThreshold(2);


        autocompletetextview2.setAdapter(adapter2);







//
//                Intent ii = new Intent(PlaceDetailsActivity.this, DetailsInfoActivity.class);
//                ii.putExtra(AppConstants.KEY_DETAILS_VIEW, SearchedEducation);
//                startActivity(ii);

                //TODO Do something with the selected text
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


            default:
                break;

        }

    }



    private void gotoPlaceDetailsView(final int placeId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PlaceChoiceActivity2.this, PlaceDetailsActivityNew.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(AppConstants.KEY_PLACE, placeId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                startActivity(intent);

            }
        }, DELAY_PLACE_DETAILS_LAUNCH_ANIM);
    }

    @Override
    public void onBackPressed() {
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_emergency) {

            Toast.makeText(con,"emergency",Toast.LENGTH_LONG).show();
            Intent em = new Intent(this, EmergencyActivity.class);
            startActivity(em);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
