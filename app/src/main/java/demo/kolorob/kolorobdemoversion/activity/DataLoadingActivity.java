package demo.kolorob.kolorobdemoversion.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AreaHolder;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_Adapter;
import demo.kolorob.kolorobdemoversion.adapters.RecyclerView_AdapterArea;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableHospital;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.database.StoredAreaTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.interfaces.ItemClickSupport;
import demo.kolorob.kolorobdemoversion.interfaces.RecyclerViewHolder;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.DataModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.StoredArea;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class DataLoadingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    Context context;
    private static int NUMBER_OF_TASKS = 6;
    View view = null;
    Button submit;
    Boolean  firstRun;
    //user and pass
    String user = "kolorobapp";
    String pass = "2Jm!4jFe3WgBZKEN";
    Boolean location = false, storage = false, smsstate = false, phonestate = false, accountstate = false, permission = false;
    int countofDb = 0;
    JSONObject allData;
    String AreaNameBn;

    public View getAreaview() {
        return areaview;
    }

    public void setAreaview(View areaview) {
        this.areaview = areaview;
    }

    public View getWardview() {
        return wardview;
    }

    public void setWardview(View wardview) {
        this.wardview = wardview;
    }

    View areaview, wardview;
    private static RecyclerView recyclerView, recyclerViewarea;

    private Animation mEnterAnimation, mExitAnimation;
    //String and Integer array for Recycler View Items
    public static final String[] TITLES = {"ওয়ার্ড ২", "ওয়ার্ড ৩", "ওয়ার্ড ৪", "ওয়ার্ড ৫", "ওয়ার্ড ৬", "ওয়ার্ড ৭", "ওয়ার্ড ৮", "ওয়ার্ড ৯", "ওয়ার্ড ১০", "ওয়ার্ড ১১", "ওয়ার্ড ১২", "ওয়ার্ড ১৩", "ওয়ার্ড ১৪", "ওয়ার্ড ১৫",
            "ওয়ার্ড ১৬"};
    public static final int[] wardid = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    public static final String[] AREANAMES = {"Mirpur 12:Mirpur DOHS",
            "Mirpur 10:Mirpur 11",
            "Mirpur 13:Mirpur 14:Baishteki",
            "Mirpur 11:Bauniabadh:Palashnagar",
            "Mirpur 6:Mirpur 7:Eastern Housing:Albodi:Pallabi:Albodi Rupnagar Tinshed:Duaripara",
            "Mirpur 2:Mirpur 6:Rupnagar:Government Housing Estate",
            "Mirpur 1:North Bishil:Baksnagar:Nobabaer Bag:BISF Staff Quarter:Botanical Garden Residential Area",
            "Bagbari:Harirampur:Jahurabad:Bazarpara:Bordhonbari:Golartek:Choto Diabari:Coat Bari:Anandanagar",
            "Gabtoli Jamidarbari:Gabtoli 1st Colony:Gabtoli 2nd Colony:Gabtoli 3rd Colony:Goidartek:Darus Salam",
            "Kallyanpur:Paikpara",
            "Ahammed Nagar:South Bishil:Shah Ali Bag:Kalwala Para:Paikpara Staff Quarter:Educatiob Board Staff Quarter:Tolarbag:BADC Staff Quarter",
            "Borobag:Pirer Bag:Monipur",
            "Kazi para:Shewrapara:Senpara Parbata"
            , "Vashantek:Albodortek:Damalkot:Lalasorai:Matikata:Manikdi:Balughat:Baigertek:Barontek",
            "Ibrahimpur:Kafrul"};

    public String getAreaNameBn() {
        return AreaNameBn;
    }

    public void setAreaNameBn(String areaNameBn) {
        AreaNameBn = areaNameBn;
    }
String Location;

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
ImageView rotateImage;
    public static final String[] AREANAMESBN = {"মিরপুর ১২: মিরপুর ডিওএইচএস",
            "মিরপুর ১০: মিরপুর ১১",
            "মিরপুর ১৩:  মিরপুর ১৪: বাইশটেকি",
            "মিরপুর ১১: বাউনিয়াবাঁধ: পলাশ নগর",
            "মিরপুর ৬: মিরপুর ৭:  পল্লবী:  আলবদী: দুয়ারী পাড়া: ইস্টার্ন হাউজিং:আলবদী রূপনগর টিনশেড",
            "মিরপুর ২: মিরপুর ৬: রূপনগর: সরকারী হাউজিং এষ্টেট",
            "মিরপুর ১: উত্তর বিশিল: বকস নগর:নবাবের বাগ : বোটনিক্যাল গার্ডেন আবাসিক এলাকা: বিআইএসএফ ষ্টাফ কোয়ার্টার ",
            "বাগবাড়ী: হরিরামপুর: জহরাবাদ: বাজার পাড়া: বর্ধনবাড়ী: গোলারটেক: ছোটদিয়াবাড়ী: কোটবাড়ী: আনন্দ নগর",
            "গাবতলী জমিদারবাড়ী : গাবতলী ১ম কলোনী: গাবতলী ২য় কলোনী: গাবতলী ৩য় কলোনী: গৈদারটেক: দারুস সালাম",
            "কল্যাণপুর: পাইক পাড়া",
            "আহম্মেদ নগর: দক্ষিণ বিশিল: শাহআলী বাগ: কালওয়ালা পাড়া: পাইকপাড়া ষ্টাফ কোয়ার্টার: শিক্ষা বোর্ড ষ্টাফ কোয়ার্টার: টোলারবাগ: বিএডিসি ষ্টাফ কোয়ার্টার",
            "বড় বাগ: পীরের বাগ: মনীপুর",
            "কাজী পাড়া: শেওড়া পাড়া: সেনপাড়া পর্বতা"
            , "ভাসান টেক: আলবদিরটেক: দামালকোট: লালাসরাই: মাটি কাটা: মানিকদি: বালুঘাট: বাইগারটেক: বারনটেক",
            "ইব্রাহীমপুর: কাফরুল"};
    public static final String[] AREAKEYWORDS = {"Mirpur_12:Mirpur_DOHS",
            "Mirpur_10:Mirpur_11",
            "Mirpur_13:Mirpur_14:Baishteki",
            "Mirpur_11:Bauniabadh:Palashnagar",
            "Mirpur_6:Mirpur_7:Pallabi:Albodi:Duaripara:Eastern_Housing:Albodi_Rupnagar_Tinshed",
            "Mirpur_2:Mirpur_6:_Rupnagar: সরকারী হাউজিং এষ্টেট",
            "Mirpur_1:North_Bishil:Baksnagar:নবাবের বাগ :Botanical_Garden_Residential_Area:BISF_Staff_Quarter",
            "Bagbari:Harirampur:Jahurabad:Bazarpara:Bordhonbari:Golartek:Choto_Diabari:Coat_Bari:Anandanagar",
            "Gabtoli_Jamidarbari :Gabtoli_1st_Colony:Gabtoli_2nd_Colony:Gabtoli_3rd_Colony:Goidartek :Darus_Salam",
            "Kallyanpur:Paikpara",
            "Admmed_Nagar:South_Bishil:Shah_Ali_Bag:Kalwala_Para:Paikpara_Staff_Quarter:Education_Board_Staff_Quarter:Tolarbag:BADC_Staff_Quarter",
            "Borobag:Pirer_Bag:Monipur",
            "Kazi_para:Shewrapara:Senpara_Parbata"
            , "Vashantek:Albodortek:Damalkot:Lalasorai:Matikata:Manikdi:Balughat:Baigertek:Barontek",
            "Ibrahimpur:Kafrul"};
    public static final String[] AREALATLONG = {"23.8251885:90.3706654+23.8366512:90.3700301",
            "23.8109961:90.3732765+23.8157519:90.3727312",
            "23.8072355:90.3785576+23.8026728:90.3826218+23.8105393:90.3805192",
            "23.8196417:90.3719654+23.8198672:90.3804651+23.8145179:90.3815702",
            "23.8103775:90.3645049+23.8181435:90.3633515+23.8250932:90.3611905+23.8354873:90.3527534+23.8250272:90.3560691+23.8261571:90.3536465+23.8215757:90.3582057",
            "23.8050564:90.3582204+23.8101316:90.3568877+23.8164594:90.3548548+23.8071084:90.3583686",
            "23.802316:90.3493793+23.7991725:90.3475983+23.8118352:90.3494097+23.810164:90.3409325+23.8101261:90.3470047+23.8097218:90.3510667",
            "23.7784712:90.3403535+23.7895259:90.3411475+23.7954947:90.3427568+23.7871501:90.3403964+23.7913519:90.3438082+23.7934331:90.3417483+23.7970457:90.3418556+23.7846565:90.3386369+23.7879748:90.3432718",
            "23.7827653:90.3465265+23.7865822:90.3474692+23.7927179:90.3489757+23.7889376:90.3455917+23.7787714:90.3473288+23.7793119:90.3547852",
            "23.7808527:90.3610078+23.7883853:90.3605831",
            "23.7935661:90.3596364+23.7943261:90.3519489+23.7974009:90.3574086+23.7983914:90.3546318+23.7868775:90.3546712+23.7979001:90.3528053+23.7890024:90.3513554+23.7871371:90.3514986",
            "23.801074:90.3630677+23.7887431:90.3671132+23.7966124:90.3668374",
            "23.7984577:90.3744075+23.7925622:90.3740769+23.80343:90.3697905"
            , "23.8115701:90.3906999+23.8422065:90.3846699+23.8061997:90.3894752+23.8026476:90.3900641+23.8181098:90.3926311+23.824347:90.3893504+23.8371166:90.3828885+23.8342118:90.3857033+23.83057505:90.3857026",
            "23.7955569:90.3835156+23.7894521:90.3849181"};


    int Pos, Posa = 0;
    ArrayList<StoredArea> storedAreas = new ArrayList<>();

    public int getPosa() {
        return Posa;
    }
    ArrayList<SubCategoryItemNew>si3=new ArrayList<>();
    RadioButton radioButton;

    public void setPosa(int posa) {
        Posa = posa;
    }

    String posWard = null;

    public String getPosWard() {
        return posWard;
    }

    public void setPosWard(String posWard) {
        this.posWard = posWard;
    }

    String posArea = null;
    TextView ward, area;
    ArrayList<DataModel> arrayList2 = new ArrayList<>();
    RecyclerViewHolder holder2;

    public int getPos() {
        return Pos;
    }

    public void setPos(int pos) {
        Pos = pos;
    }

    public String getPosArea() {
        return posArea;
    }

    public void setPosArea(String posArea) {
        this.posArea = posArea;
    }

    int PosAreaint = -1;
    private int height;
    private int width;

    public int getPosAreaint() {
        return PosAreaint;
    }

    String keyword;
    private AnimationDrawable frameAnimation;
    public void setPosAreaint(int posAreaint) {
        PosAreaint = posAreaint;
    }

    private GridLayoutManager lLayout, lLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //start download now

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_selection_activity);
        ward = (TextView) findViewById(R.id.chooseward);
        area = (TextView) findViewById(R.id.choosearea);
        submit = (Button) findViewById(R.id.submittoserverarea);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        firstRun = settings.getBoolean("firstRun", false);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPosAreaint() == -1) {
                    ToastMessageDisplay.setText(DataLoadingActivity.this, "please choose area first");
                    ToastMessageDisplay.showText(DataLoadingActivity.this);
                } else {
                    ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(AREAKEYWORDS[getPos()].split(":")));
                    ArrayList<String> listloc = new ArrayList<String>(Arrays.asList(AREALATLONG[getPos()].split("\\+")));
                    keyword = list2.get(getPosAreaint());
                    setLocation(listloc.get(getPosAreaint()));;
                    Servercall();
                }


            }
        });

        initViews();
        mEnterAnimation = new AlphaAnimation(0f, 1f);
        mEnterAnimation.setDuration(600);
        mEnterAnimation.setFillAfter(true);

        mExitAnimation = new AlphaAnimation(1f, 0f);
        mExitAnimation.setDuration(600);
        mExitAnimation.setFillAfter(true);
        populatRecyclerView();
        populatRecyclerView2();
        if(firstRun==false)  runOverlay_ContinueMethod();

        Pos = 0;

        ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        setPos(position);

                        populatRecyclerView2();
                        // ((CardView)v).setCardBackgroundColor(Color.WHITE);
                        // ((TextView)v).setBackgroundColor(Color.parseColor("#ff8800"));
                        if (getWardview() == null) {
                            setWardview(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getWardview() != v) {
                            ((CardView) getWardview()).setCardBackgroundColor(Color.parseColor("#7f000000"));

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setWardview(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        //Toast.makeText(DataLoadingActivity.this,"Existing areas are : "+AREANAMESBN[position].replace(':',','), Toast.LENGTH_SHORT).show();
                    }
                });

        ItemClickSupport.addTo(recyclerViewarea)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        ((CardView) v).setCardBackgroundColor(Color.WHITE);
                        setPosAreaint(position);
                        if (getAreaview() == null) {
                            setAreaview(v);
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        } else if (getAreaview() != v) {
                            ((CardView) getAreaview()).setCardBackgroundColor(Color.WHITE);

                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                            setAreaview(v);
                        } else
                            ((CardView) v).setCardBackgroundColor(Color.parseColor("#FF9800"));
                        setAreaNameBn(arrayList2.get(position).getTitle());

                        Toast.makeText(getApplicationContext(), arrayList2.get(position).getTitle() + " is selected!", Toast.LENGTH_SHORT).show();


                        //Toast.makeText(DataLoadingActivity.this,"Existing areas are : "+AREANAMESBN[position].replace(':',','), Toast.LENGTH_SHORT).show();
                    }
                });
        /*
         ItemClickSupport.addTo(recyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        setPos(position);

                        populatRecyclerView2();
                        //Toast.makeText(DataLoadingActivity.this,"Existing areas are : "+AREANAMESBN[position].replace(':',','), Toast.LENGTH_SHORT).show();
                    }
                });

        //now make the early request just in case
       if ((AppUtils.isNetConnected(getApplicationContext()) )&&(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED )) {
            getRequest(DataLoadingActivity.this, "http://kolorob.net/NewStructure.json" , new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {

                                   allData = new JSONObject(apiContent);


                                    //                                          frameAnimation.stop();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(allData.has("Finance"))
                                    new SaveDataArrayFinance(DataLoadingActivity.this).execute(allData);
                            }
                        }
                    }
            );
        }
*/


        context = this;


        //  setsongName();
    }

    private void initViews() {


        recyclerView = (RecyclerView)
                findViewById(R.id.recycler_view);
        recyclerViewarea = (RecyclerView)
                findViewById(R.id.recycler_view2);


        //Set RecyclerView type according to intent value
        lLayout = new GridLayoutManager(DataLoadingActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        else
//           toolbar = (Toolbar) findViewById(R.id.toolbars);


        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        ab.setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

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
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void runOverlay_ContinueMethod() {
        // the return handler is used to manipulate the cleanup of all the tutorial elements

        ChainTourGuide tourGuide1 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("১ম ধাপ")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("১ম লিস্টকে ডানে/বায়ে সরিয়ে আপনার ওয়ার্ড খুজে নিন")
                        .setGravity(Gravity.BOTTOM)
                )
                // note that there is no Overlay here, so the default one will be used
                .playLater(ward);

        ChainTourGuide tourGuide2 = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("২য় ধাপ")
                        .setBackgroundColor(Color.parseColor("#000000"))
                        .setDescription("ওয়ার্ড অনুযায়ী এলাকার লিস্ট থেকে এলাকা খুজে নিচের 'জমা দিন' বাটনটি ক্লিক করুন।")
                        .setGravity(Gravity.BOTTOM)
                )
                .playLater(area);


        Sequence sequence = new Sequence.SequenceBuilder()
                .add(tourGuide1, tourGuide2)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(mEnterAnimation)
                        .setExitAnimation(mExitAnimation)
                )
                .setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();


        ChainTourGuide.init(this).playInSequence(sequence);
    }


    // populate the list view by adding data to arraylist
    private void populatRecyclerView() {
        ArrayList<AreaHolder> arrayList = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            AreaHolder areaHolder1 = new AreaHolder(wardid[i], TITLES[i], AREANAMES[i], AREANAMESBN[i]);
            arrayList.add(areaHolder1);
        }
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(DataLoadingActivity.this, arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview


        adapter.notifyDataSetChanged();// Notify the adapter


    }

    private void populatRecyclerView2() {

        arrayList2.clear();

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(AREANAMESBN[getPos()].split(":")));
        for (int i = 0; i < list.size(); i++) {
            DataModel areaHolder1 = new DataModel(list.get(i));
            arrayList2.add(areaHolder1);
        }
        if (arrayList2.size() >= 4) {
            lLayout2 = new GridLayoutManager(DataLoadingActivity.this, 2, GridLayoutManager.HORIZONTAL, false);
        } else {
            lLayout2 = new GridLayoutManager(DataLoadingActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
        }
        recyclerViewarea.setHasFixedSize(false);
        recyclerViewarea.setLayoutManager(lLayout2);
        RecyclerView_AdapterArea adapter2 = new RecyclerView_AdapterArea(DataLoadingActivity.this, arrayList2);
        recyclerViewarea.setAdapter(adapter2);// set adapter on recyclerview


        adapter2.notifyDataSetChanged();// Notify the adapter

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }



    abstract class GenericSaveDBTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
        private Context ctx;

        public GenericSaveDBTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPostExecute(Result result) {
            if (((Long) result).longValue() == 0.0 && countofDb < NUMBER_OF_TASKS) { // Means the task is successful
                countofDb++;
                SharedPreferences settings = getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("KValue", countofDb);
                editor.apply();
                Log.d("tasks", "Tasks remaining: " + (NUMBER_OF_TASKS - countofDb));
                ToastMessageDisplay.setText(DataLoadingActivity.this.context, "তথ্য সংগ্রহ চলছে");
                ToastMessageDisplay.showText(DataLoadingActivity.this.context);
            }
        }

    }

    void Servercall() {

        if ( firstRun==false)
        {
            NUMBER_OF_TASKS=8;
        }
        LayoutInflater layoutInflater = LayoutInflater.from(DataLoadingActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.activity_waiting, null);
        final Dialog alertDialog = new Dialog(DataLoadingActivity.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final Handler handler = new Handler();
        Runnable runner = new Runnable() {
            int timeCounter = 0;
            @Override
            public void run() {




                if (DataLoadingActivity.this.countofDb >= NUMBER_OF_TASKS || timeCounter > 120000) {
                    overridePendingTransition(0, 0);
                    SharedPreferences settings = getSharedPreferences("prefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                  //  editor.putString("First", first);

                  //  editor.apply();
                    handler.removeCallbacks(this);
                        SharedPreferencesHelper.setifcommentedalready(DataLoadingActivity.this,null,SharedPreferencesHelper.getUname(DataLoadingActivity.this),"no");
                        Intent a = new Intent(DataLoadingActivity.this, AboutUs.class); // Default Activity

                        frameAnimation.stop();
                    alertDialog.cancel();
                        startActivity(a);
                        return;

                }
                //Create a loop
                handler.postDelayed(this, 1000);
                timeCounter += 1000;

            }

        };
        handler.postDelayed(runner, 1000);

        rotateImage = (ImageView) promptView.findViewById(R.id.rotate_image);
        rotateImage.setBackgroundResource(R.drawable.frame_animation_list);
        frameAnimation = (AnimationDrawable) rotateImage.getBackground();
        frameAnimation.setOneShot(false);
        frameAnimation.start();
        if ( firstRun==false)   {
            getRequest(DataLoadingActivity.this, "http://kolorob.net/kolorob-live/api/categories?", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {

                                try {

                                    JSONArray jo = new JSONArray(apiContent);

                                    new SaveCategoryListTask(DataLoadingActivity.this).execute(jo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
            getRequest(DataLoadingActivity.this, "http://kolorob.net/demo/api/refs?username=" + user + "&password=" + pass + " ", new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status, String apiContent) {
                            if (status == AppConstants.SUCCESS_CODE) {


                                try {
                                    JSONArray jo = new JSONArray(apiContent);

                                    new SaveSubCategoryNewListTask(DataLoadingActivity.this).execute(jo);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
            );
        }
        getRequest(DataLoadingActivity.this, "http://kolorob.net/kolorob-live/api/getspbyarea?ward=" + wardid[getPos()] + "&area=" + keyword, new VolleyApiCallback() {
            @Override
            public void onResponse(int status, String apiContent) {
                if (status == AppConstants.SUCCESS_CODE) {

                    try {

                        allData = new JSONObject(apiContent);
                        if (allData.length()==0)
                        {
                            ToastMessageDisplay.setText(DataLoadingActivity.this,"No data found. Please select another area");
                            ToastMessageDisplay.showText(DataLoadingActivity.this);
                        }
                    else {
                            if (allData.has("Education"))
                            {
                                new SavenewEduTask(DataLoadingActivity.this).execute(allData.getJSONArray("Education"));
                                //   SavenewEdu(allData.getJSONArray("Education"));
                            }

                            if (allData.has("Finance"))
                            {
                                new SavenewFinanceTask(DataLoadingActivity.this).execute(allData.getJSONArray("Finance"));
                                //SavenewFin(allData.getJSONArray("Finance"));

                            }

                            if (allData.has("Health"))
                            {
                                new  SaveHealthtDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Health"));
                                //SavenewHealth(allData.getJSONArray("Health"));

                            }

                            if (allData.has("Legal"))
                            {
                                new SaveLegalDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Legal"));
                                // SavenewLegal(allData.getJSONArray("Legal"));
                            }


                            if (allData.has("Government"))
                            {
                                new SavenewGovTask(DataLoadingActivity.this).execute(allData.getJSONArray("Government"));
                                // SavenewGov(allData.getJSONArray("Government"));
                            }


                            if (allData.has("Entertainment"))
                            {
                               // SavenewEntertainment(allData.getJSONArray("Entertainment"));
                                new SaveEntertainmentDataTask(DataLoadingActivity.this).execute(allData.getJSONArray("Entertainment"));
                            }

                        }
                            int p = allData.length();
                            Log.d("Doneall", String.valueOf(p));
                            StoredAreaTable storedAreaTable = new StoredAreaTable(DataLoadingActivity.this);
                            String A = getAreaNameBn();
                            String LOC = getLocation();
                            storedAreaTable.insertItem(String.valueOf(wardid[getPos()]), keyword, A, LOC);
                            Log.e("ward area ", String.valueOf(wardid[getPos()]));
                            SharedPreferences settings = getSharedPreferences("prefs", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putInt("ward", wardid[getPos()]);
                            editor.putString("areakeyword", keyword);
                            editor.apply();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        });


    }

    void SavenewEdu(JSONArray jo) {
        JSONArray Edu = jo;
        EduNewDBTableMain eduNewDBTableMain = new EduNewDBTableMain(DataLoadingActivity.this);
        EduNewDBTableTraining eduNewDBTableTraining = new EduNewDBTableTraining(DataLoadingActivity.this);

        int Govcount = Edu.length();

        for (int i = 0; i < Govcount; i++) {
            try {
                if (!Edu.isNull(i)) {
                    JSONObject jsonObject2 = Edu.getJSONObject(i);
                    EduNewModel eduNewModel = EduNewModel.parseEduNewModel(jsonObject2);
                    eduNewDBTableMain.insertItem(eduNewModel);
                    if (jsonObject2.has("training_details")) {
                        JSONArray edutrain = jsonObject2.getJSONArray("training_details");
                        int lenoftrain = edutrain.length();
                        for (int ii = 0; ii < lenoftrain; ii++) {
                            JSONObject train = edutrain.getJSONObject(ii);
                            EduTrainingModel eduTrainingModel = EduTrainingModel.parseEduTrainingModel(train);
                            eduNewDBTableTraining.insertItem(eduTrainingModel);
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("educount", String.valueOf(i));
        }
    }
    class SavenewEduTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewEduTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonObjects) {
            JSONArray Edu = jsonObjects[0];
            EduNewDBTableMain eduNewDBTableMain = new EduNewDBTableMain(DataLoadingActivity.this);
            EduNewDBTableTraining eduNewDBTableTraining = new EduNewDBTableTraining(DataLoadingActivity.this);

            int Govcount = Edu.length();;

            for (int i = 0; i < Govcount; i++) {
                try {
                    if (!Edu.isNull(i)) {
                        JSONObject jsonObject2 = Edu.getJSONObject(i);
                        EduNewModel eduNewModel = EduNewModel.parseEduNewModel(jsonObject2);
                        eduNewDBTableMain.insertItem(eduNewModel);
                        if (jsonObject2.has("training_details")) {
                            JSONArray edutrain = jsonObject2.getJSONArray("training_details");
                            int lenoftrain = edutrain.length();
                            for (int ii = 0; ii < lenoftrain; ii++) {
                                JSONObject train = edutrain.getJSONObject(ii);
                                EduTrainingModel eduTrainingModel = EduTrainingModel.parseEduTrainingModel(train);
                                eduNewDBTableTraining.insertItem(eduTrainingModel);
                            }
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }
    class SaveEntertainmentDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveEntertainmentDataTask(Context ctx) {
            super(ctx);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Ent = jsonArrays[0];
            EntNewDBTable entNewDBTable = new EntNewDBTable(DataLoadingActivity.this);


            int Entcount = Ent.length();

            for (int i = 0; i < Entcount; i++) {
                try {
                    if (!Ent.isNull(i)) {
                        JSONObject jsonObject2 = Ent.getJSONObject(i);
                        EntertainmentNewDBModel entertainmentNewDBModel = EntertainmentNewDBModel.parseEntertainmentNewDBModel(jsonObject2);
                        entNewDBTable.insertItem(entertainmentNewDBModel);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }


    }
    /*void SavenewEntertainment(JSONArray jo) {
        JSONArray Ent = jo;
        EntNewDBTable entNewDBTable = new EntNewDBTable(DataLoadingActivity.this);


        int Entcount = Ent.length();

        for (int i = 0; i < Entcount; i++) {
            try {
                if (!Ent.isNull(i)) {
                    JSONObject jsonObject2 = Ent.getJSONObject(i);
                    EntertainmentNewDBModel entertainmentNewDBModel = EntertainmentNewDBModel.parseEntertainmentNewDBModel(jsonObject2);
                    entNewDBTable.insertItem(entertainmentNewDBModel);


                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("entcount", String.valueOf(i));
        }
    }*/
    class SavenewGovTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewGovTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Gov = jsonArrays[0];
            GovNewDBTable govNewDBTable = new GovNewDBTable(DataLoadingActivity.this);


            int Govcount = Gov.length();

            for (int i = 0; i < Govcount; i++) {
                try {
                    if (!Gov.isNull(i)) {
                        JSONObject jsonObject2 = Gov.getJSONObject(i);
                        GovernmentNewDBModel governmentNewDBModel = GovernmentNewDBModel.parseGovernmentNewDBModel(jsonObject2);
                        govNewDBTable.insertItem(governmentNewDBModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }
    /*
    void SavenewGov(JSONArray jo) {
        JSONArray Gov = jo;
        GovNewDBTable govNewDBTable = new GovNewDBTable(DataLoadingActivity.this);


        int Govcount = Gov.length();

        for (int i = 0; i < Govcount; i++) {
            try {
                if (!Gov.isNull(i)) {
                    JSONObject jsonObject2 = Gov.getJSONObject(i);
                    GovernmentNewDBModel governmentNewDBModel = GovernmentNewDBModel.parseGovernmentNewDBModel(jsonObject2);
                    govNewDBTable.insertItem(governmentNewDBModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("govcount", String.valueOf(i));
        }
    }

    void SavenewLegal(JSONArray jo) {
        JSONArray Legal = jo;
        LegalAidNewDBTable legalAidNewDBTable = new LegalAidNewDBTable(DataLoadingActivity.this);


        int Legalcount = Legal.length();

        for (int i = 0; i < Legalcount; i++) {
            try {
                if (!Legal.isNull(i)) {
                    JSONObject jsonObject2 = Legal.getJSONObject(i);
                    LegalAidNewDBModel legalAidNewDBModel = LegalAidNewDBModel.parseLegalAidNewDBModel(jsonObject2);
                    legalAidNewDBTable.insertItem(legalAidNewDBModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("legalcount", String.valueOf(i));
        }
    }
*/
    class SaveLegalDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveLegalDataTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Legal = jsonArrays[0];
            LegalAidNewDBTable legalAidNewDBTable = new LegalAidNewDBTable(DataLoadingActivity.this);


            int Legalcount = Legal.length();

            for (int i = 0; i < Legalcount; i++) {
                try {
                    if (!Legal.isNull(i)) {
                        JSONObject jsonObject2 = Legal.getJSONObject(i);
                        LegalAidNewDBModel legalAidNewDBModel = LegalAidNewDBModel.parseLegalAidNewDBModel(jsonObject2);
                        legalAidNewDBTable.insertItem(legalAidNewDBModel);
                    }


                } catch (JSONException e)
                {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }


    }

    class SavenewFinanceTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SavenewFinanceTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Fin = jsonArrays[0];
            FinNewDBTable finNewDBTable = new FinNewDBTable(DataLoadingActivity.this);


            int Fincount = Fin.length();



            for (int i = 0; i < Fincount; i++) {
                try {
                    if (!Fin.isNull(i)) {
                        JSONObject jsonObject2 = Fin.getJSONObject(i);
                        FinancialNewDBModel financialNewDBModel = FinancialNewDBModel.parseFinancialNewDBModel(jsonObject2);
                        finNewDBTable.insertItem(financialNewDBModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }
    /*
    void SavenewFin(JSONArray jo) {
        JSONArray Fin = jo;
        FinNewDBTable finNewDBTable = new FinNewDBTable(DataLoadingActivity.this);


        int Fincount = Fin.length();

        for (int i = 0; i < Fincount; i++) {
            try {
                if (!Fin.isNull(i)) {
                    JSONObject jsonObject2 = Fin.getJSONObject(i);
                    FinancialNewDBModel financialNewDBModel = FinancialNewDBModel.parseFinancialNewDBModel(jsonObject2);
                    finNewDBTable.insertItem(financialNewDBModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("fcount", String.valueOf(i));
        }
    }

    /*void SavenewHealth(JSONArray jo) {
        JSONArray Hel = jo;
        HealthNewDBTableMain govNewDBTable = new HealthNewDBTableMain(DataLoadingActivity.this);
        HealthNewDBTablePharma healthNewDBTablePharma = new HealthNewDBTablePharma(DataLoadingActivity.this);
        HealthNewDBTableHospital healthNewDBTableHospital = new HealthNewDBTableHospital(DataLoadingActivity.this);
        int Helcount = Hel.length();

        for (int i = 0; i < Helcount; i++) {
            try {
                if (!Hel.isNull(i)) {
                    JSONObject jsonObject2 = Hel.getJSONObject(i);
                    HealthNewDBModelMain healthNewDBModelMain = HealthNewDBModelMain.parseHealthNewDBModelMain(jsonObject2);
                    govNewDBTable.insertItem(healthNewDBModelMain);
                    if (jsonObject2.has("health_pharmacy")) {
                        JSONObject pharmacy = jsonObject2.getJSONObject("health_pharmacy");


                        HealthNewDBModelPharmacy healthNewDBModelPharmacy = HealthNewDBModelPharmacy.parseHealthNewDBModelPharmacy(pharmacy, jsonObject2.getInt("id"));
                        healthNewDBTablePharma.insertItem(healthNewDBModelPharmacy);


                    } else if (jsonObject2.has("health_hospital")) {
                        JSONObject hospital = jsonObject2.getJSONObject("health_hospital");


                        HealthNewDBModelHospital healthNewDBModelHospital = HealthNewDBModelHospital.parseHealthNewDBModelHospital(hospital, jsonObject2.getInt("id"));
                        healthNewDBTableHospital.insertItem(healthNewDBModelHospital);


                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("hcount", String.valueOf(i));
        }
    }*/
    class SaveHealthtDataTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveHealthtDataTask(Context ctx) {
            super(ctx);
        }


        protected Long doInBackground(JSONArray... jsonArrays) {
            JSONArray Hel = jsonArrays[0];
            HealthNewDBTableMain govNewDBTable = new HealthNewDBTableMain(DataLoadingActivity.this);
            HealthNewDBTablePharma healthNewDBTablePharma = new HealthNewDBTablePharma(DataLoadingActivity.this);
            HealthNewDBTableHospital healthNewDBTableHospital = new HealthNewDBTableHospital(DataLoadingActivity.this);
            int Helcount = Hel.length();
            Log.d("HealthData","********"+Helcount);

            for (int i = 0; i < Helcount; i++) {
                try {
                    if (!Hel.isNull(i)) {
                        JSONObject jsonObject2 = Hel.getJSONObject(i);
                        HealthNewDBModelMain healthNewDBModelMain = HealthNewDBModelMain.parseHealthNewDBModelMain(jsonObject2);
                        govNewDBTable.insertItem(healthNewDBModelMain);
                        if (jsonObject2.has("health_pharmacy")) {
                            JSONObject pharmacy = jsonObject2.getJSONObject("health_pharmacy");
                            HealthNewDBModelPharmacy healthNewDBModelPharmacy = HealthNewDBModelPharmacy.parseHealthNewDBModelPharmacy(pharmacy, jsonObject2.getInt("id"));
                            healthNewDBTablePharma.insertItem(healthNewDBModelPharmacy);
                        } else if (jsonObject2.has("health_hospital")) {
                            JSONObject hospital = jsonObject2.getJSONObject("health_hospital");
                            HealthNewDBModelHospital healthNewDBModelHospital = HealthNewDBModelHospital.parseHealthNewDBModelHospital(hospital, jsonObject2.getInt("id"));
                            healthNewDBTableHospital.insertItem(healthNewDBModelHospital);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }

    class SaveCategoryListTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveCategoryListTask(Context ctx) {
            super(ctx);
        }

        @Override
        protected Long doInBackground(JSONArray... categoryArrays) {
            JSONArray categoryArray = categoryArrays[0];
            CategoryTable catTable = new CategoryTable(DataLoadingActivity.this);
            catTable.dropTable();
            int catCount = categoryArray.length();
            for (int i = 0; i < catCount; i++) {
                try {
                    JSONObject jo = categoryArray.getJSONObject(i);
                    CategoryItem ci = CategoryItem.parseCategoryItem(jo);
                    catTable.insertItem(ci);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }
            return new Long(0);
        }
    }
    class SaveSubCategoryNewListTask extends GenericSaveDBTask<JSONArray, Integer, Long> {
        public SaveSubCategoryNewListTask(Context ctx) {
            super(ctx);
        }

        protected Long doInBackground(JSONArray... categoryArrays) {
            JSONArray subcat = categoryArrays[0];
            SubCategoryTableNew subCategoryTableNew = new SubCategoryTableNew(DataLoadingActivity.this);
            subCategoryTableNew.dropTable();

            int legalaidServiceProviderCount = subcat.length();

            for (int i = 0; i < legalaidServiceProviderCount; i++) {
                try {
                    JSONObject jo = subcat.getJSONObject(i);
                    SubCategoryItemNew et = SubCategoryItemNew.parseSubCategoryItem(jo);
                    subCategoryTableNew.insertItem(et);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new Long(-1);
                }
            }

            si3 = subCategoryTableNew.getAllSubCat();
            si3.size();

            return new Long(0);
        }
    }
}




