package demo.kolorob.kolorobdemoversion.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableChamber;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableHospital;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.database.StoredAreaTable;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthModelChamber;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.model.StoredArea;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;


/**
 * Created by HP on 2/13/2017.
 */
/*
this activity is for area upgrade/delete/browse. This is almost similar to data loading activity.
 */



public class AreaUpgrade extends AppCompatActivity {

    private final int NUMBER_OF_TASKS = 8;
    ArrayList<StoredArea> storedAreas = new ArrayList<>();
    RadioGroup radioGroup;
    Button update, delete, browse;
    int selectedId = -1;
    Context context;
    Boolean deleted = false;
    LinearLayout linearLayout;
    ProgressDialog dialog, dialog2;
    JSONObject allData;
    StoredAreaTable storedAreaTable;

    static int counter = 0;

    ArrayList<StoredArea> storedAreaArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areas);
        context = this;

        update = (Button) findViewById(R.id.updatearea);
        delete = (Button) findViewById(R.id.deletearea);
        browse = (Button) findViewById(R.id.browsearea);
        linearLayout = (LinearLayout) findViewById(R.id.linearradio);
        storedAreaTable = new StoredAreaTable(AreaUpgrade.this);

        radioGroup = (RadioGroup) findViewById(R.id.areagroup);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radiobuttonsetup();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedId = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (selectedId == -1) {
                    ToastMessageDisplay.setText(AreaUpgrade.this, getString(R.string.select_area_first));
                    ToastMessageDisplay.showText(AreaUpgrade.this);
                } else {
                    dialog2 = new ProgressDialog(AreaUpgrade.this);
                    dialog2.setMessage(getString(R.string.please_wait));
                    dialog2.setCancelable(true);
                    dialog2.show();
                    deleteAll(storedAreas.get(selectedId).getWard(), storedAreas.get(selectedId).getArea()); // to delete area stored in device

                }
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (selectedId == -1) {
                    ToastMessageDisplay.setText(AreaUpgrade.this, getString(R.string.select_area_first));
                    ToastMessageDisplay.showText(AreaUpgrade.this);
                } else {

                    storedAreaArrayList = RetriveLocation(storedAreas.get(selectedId).getWard(), storedAreas.get(selectedId).getArea());
                    SharedPreferences settings = getSharedPreferences("prefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("_ward", storedAreaArrayList.get(0).getWard()); // store ward and area from stored area in pref
                    //to use in next activity
                    editor.putString("areakeyword", storedAreaArrayList.get(0).getArea());
                    editor.apply();
                    Intent a = new Intent(AreaUpgrade.this, PlaceDetailsActivityNewLayout.class); // Default Activity
                    startActivity(a);
                    finish();
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {

                  if (selectedId == -1) {
                      ToastMessageDisplay.setText(AreaUpgrade.this, getString(R.string.select_area_first));
                      ToastMessageDisplay.showText(AreaUpgrade.this);
                  } else {
                      dialog = new ProgressDialog(AreaUpgrade.this);
                      dialog.setMessage(getString(R.string.please_wait));
                      dialog.setCancelable(true);
                      dialog.show();

                      if (AppUtils.isNetConnected(getApplicationContext())) {
                          serverCall(storedAreas.get(selectedId).getWard(), storedAreas.get(selectedId).getArea());
                      } else {
                          AlertMessage.showMessage(AreaUpgrade.this, getString(R.string.sorry), getString(R.string.connect_to_internet));
                          dialog.cancel();
                      }

                  }
              }
        });

    }

    public ArrayList<StoredArea> RetriveLocation(String ward, String area) //last existing location er jonno
    {
        return storedAreaTable.getStoredLocation(ward, area);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    void radiobuttonsetup() // database operation for getting information which areas are stored in devices and store those in an arraylist

    {

        storedAreas = storedAreaTable.getAllData();

        radioGroup.clearCheck();
        if (storedAreas.isEmpty()) {
            ToastMessageDisplay.setText(AreaUpgrade.this, getString(R.string.no_downloaded_area));
            ToastMessageDisplay.showText(AreaUpgrade.this);
            Intent em = new Intent(this, DataLoadingActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        } else {
            for (int i = 0; i < storedAreas.size(); i++) {


                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(storedAreas.get(i).getAreaBn());

                radioGroup.addView(radioButton);


            }

        }
    }


    void serverCall(String ward, String area) {

        getRequest(context, "http://kolorob.net/kolorob-new-live/api/getspbyarea?ward=" + ward + "&area=" + area, new VolleyApiCallback() {
            @Override
            public void onResponse(int status, String apiContent) {
                if (status == AppConstants.SUCCESS_CODE) {

                    try {

                        allData = new JSONObject(apiContent);

                        /*if(allData.has(AppConstants.EDU_API)){
                            counter += new SaveEducationDBTask(context, allData.getJSONArray("Education")).saveItem();
                        }
                        if(allData.has(AppConstants.HEALTH_API)){
                            counter += new SaveHealthDBTask(context, allData.getJSONArray("Health")).saveItem();
                        }
                        if(allData.has(AppConstants.ENTERTAINMENT_API)){
                            counter += new SaveEntertainmentDBTask(context, allData.getJSONArray("Entertainment")).saveItem();
                        }
                        if(allData.has(AppConstants.GOVERNMENT_API)){
                            counter += new SaveGovernmentDBTask(context, allData.getJSONArray("Government")).saveItem();
                        }
                        if(allData.has(AppConstants.LEGAL_API)){
                            counter += new SaveLegalDBTask(context, allData.getJSONArray("Legal")).saveItem();
                        }
                        if(allData.has(AppConstants.FINANCE_API)){
                            counter += new SaveFinancialDBTask(context, allData.getJSONArray("Finance")).saveItem();
                        }
                        if(allData.has(AppConstants.NGO_API)){
                            counter += new SaveNgoDBTask(context, allData.getJSONArray("NGO")).saveItem();
                        }
                        if(allData.has(AppConstants.SHELTER_API)){
                            counter += new SaveShelterDBTask(context, allData.getJSONArray("Religious Shelter")).saveItem();
                        }
                        */

                        if (allData.has(AppConstants.EDU_API))
                            upgradeEducation(allData.getJSONArray(AppConstants.EDU_API));
                        if (allData.has(AppConstants.HEALTH_API))
                            upgradeHealth(allData.getJSONArray(AppConstants.HEALTH_API));
                        if (allData.has(AppConstants.ENTERTAINMENT_API))
                            upgradeEntertainment(allData.getJSONArray(AppConstants.ENTERTAINMENT_API));
                        if (allData.has(AppConstants.GOVERNMENT_API))
                            upgradeGovernment(allData.getJSONArray(AppConstants.GOVERNMENT_API));
                        if (allData.has(AppConstants.LEGAL_API))
                            upgradeLegal(allData.getJSONArray(AppConstants.LEGAL_API));
                        if (allData.has(AppConstants.FINANCE_API))
                            upgradeFinancial(allData.getJSONArray(AppConstants.FINANCE_API));
                        if (allData.has(AppConstants.NGO_API))
                            upgradeNGO(allData.getJSONArray(AppConstants.SHELTER_API));
                        if (allData.has(AppConstants.SHELTER_API))
                            upgradeShelter(allData.getJSONArray(AppConstants.SHELTER_API));


                        Log.d("Done", String.valueOf(allData.length()));
                        dialog.dismiss();
                        if (counter == NUMBER_OF_TASKS) {
                            ToastMessageDisplay.setText(AreaUpgrade.this, getString(R.string.info_updated));
                            ToastMessageDisplay.showText(AreaUpgrade.this);
                            SharedPreferences settings = getSharedPreferences("prefs", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            settings.edit().putLong("time", System.currentTimeMillis()).apply();
                            editor.putString("_ward", storedAreas.get(selectedId).getWard());
                            editor.putString("areakeyword", storedAreas.get(selectedId).getArea());
                            editor.apply();
                        } else {
                            ToastMessageDisplay.setText(context, getString(R.string.try_later));
                            ToastMessageDisplay.showText(context);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        });
    }

    void upgradeEducation(JSONArray jsonArray) {


        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                if (!jsonArray.isNull(i)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    EduNewModel edu = new EduNewModel().parse(jsonObject);
                    new EduNewDBTableMain(context).insertItem(edu);

                    if (jsonObject.has(AppConstants.TRAINING_API)) {

                        Log.e(" Edu : ", "training details");

                        JSONArray trainings = jsonObject.getJSONArray(AppConstants.TRAINING_API);

                        for (int j = 0; j < trainings.length(); j++) {
                            JSONObject training = trainings.getJSONObject(j);
                            new EduNewDBTableTraining(context).insertItem(new EduTrainingModel().parse(training, edu.getId()));
                        }
                    }

                    if (jsonObject.has(AppConstants.RESULT_API)) {

                        Log.e(" Edu : ", "result details");

                        JSONArray results = jsonObject.getJSONArray(AppConstants.RESULT_API);

                        for (int j = 0; j < results.length(); j++) {
                            JSONObject result = results.getJSONObject(j);
                            new EducationResultDetailsTable(context).insertItem(new EducationResultItemNew().parse(result, edu.getId()));
                        }
                    }

                    if (jsonObject.has(AppConstants.SCHOOL_API)) {

                        Log.e(" Edu : ", "school details");

                        JSONObject school = jsonObject.getJSONObject(AppConstants.SCHOOL_API);
                        new EduNewDBTableSchool(context).insertItem(new EduNewSchoolModel().parse(school, edu.getId()));
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
        counter++;
    }


    void upgradeHealth(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                if (!jsonArray.isNull(i)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    HealthNewDBModelMain health = new HealthNewDBModelMain().parse(jsonObject);
                    new HealthNewDBTableMain(context).insertItem(health);

                    if (jsonObject.has(AppConstants.PHARMACY_API)) {
                        Log.e(" Health : ", "pharmacy");
                        JSONObject pharmacy = jsonObject.getJSONObject(AppConstants.PHARMACY_API);
                        new HealthNewDBTablePharma(context).insertItem(new HealthNewDBModelPharmacy().parse(pharmacy, health.getId()));
                    }
                    if (jsonObject.has(AppConstants.HOSPITAL_API)) {
                        Log.e(" Health : ", "hospital");
                        JSONObject hospital = jsonObject.getJSONObject(AppConstants.HOSPITAL_API);
                        new HealthNewDBTableHospital(context).insertItem(new HealthNewDBModelHospital().parse(hospital, health.getId()));
                    }
                    if(jsonObject.has(AppConstants.CHAMBER_API)){
                        Log.e("Health : ", "chamber");
                        JSONObject chamber = jsonObject.getJSONObject(AppConstants.CHAMBER_API);
                        new HealthNewDBTableChamber(context).insertItem(new HealthModelChamber().parse(chamber, health.getId()));
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        counter++;
    }

    void upgradeEntertainment(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (!jsonArray.isNull(i)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    new EntNewDBTable(context).insertItem(new EntertainmentNewDBModel().parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        counter++;
    }

    void upgradeGovernment(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (!jsonArray.isNull(i)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    new GovNewDBTable(context).insertItem(new GovernmentNewDBModel().parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        counter++;
    }

    void upgradeLegal(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (!jsonArray.isNull(i)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    new LegalAidNewDBTable(context).insertItem(new LegalAidNewDBModel().parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        counter++;
    }

    void upgradeFinancial(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (!jsonArray.isNull(i)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    new FinNewDBTable(context).insertItem(new FinancialNewDBModel().parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        counter++;
    }

    void upgradeNGO(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (!jsonArray.isNull(i)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    new NGONewDBTable(context).insertItem(new NGONewDBModel().parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        counter++;
    }

    void upgradeShelter(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (!jsonArray.isNull(i)) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    new ReligiousNewDBTable(context).insertItem(new ReligiousNewDBModel().parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        counter++;
    }


    public <TableType extends CommonDBTable> void deleteAll(String ward, String area) {

        ArrayList<TableType> tables = new ArrayList<>();

        tables.add((TableType) new EduNewDBTableMain(AreaUpgrade.this));
        tables.add((TableType) new HealthNewDBTableMain(AreaUpgrade.this));
        tables.add((TableType) new FinNewDBTable(AreaUpgrade.this));
        tables.add((TableType) new GovNewDBTable(AreaUpgrade.this));
        tables.add((TableType) new LegalAidNewDBTable(AreaUpgrade.this));
        tables.add((TableType) new EntNewDBTable(AreaUpgrade.this));
        tables.add((TableType) new NGONewDBTable(AreaUpgrade.this));
        tables.add((TableType) new ReligiousNewDBTable(AreaUpgrade.this));

        new EduNewDBTableSchool(AreaUpgrade.this).delete(ward, area);
        new EducationResultDetailsTable(AreaUpgrade.this).delete(ward, area);
        new EduNewDBTableTraining(AreaUpgrade.this).delete(ward, area);
        new HealthNewDBTableHospital(AreaUpgrade.this).delete(ward, area);
        new HealthNewDBTablePharma(AreaUpgrade.this).delete(ward, area);


        for (TableType table : tables) {
            delete(table, ward, area);
        }

        StoredAreaTable storedAreaDB = new StoredAreaTable(AreaUpgrade.this);
        storedAreaDB.delete(storedAreaDB.getNodeInfo(ward, area).getId());


        radioGroup.clearCheck();
        dialog2.dismiss();
        radioGroup.removeAllViews();

        ToastMessageDisplay.setText(AreaUpgrade.this, getString(R.string.info_deleted));
        ToastMessageDisplay.showText(AreaUpgrade.this);
        deleted = true;
        radiobuttonsetup();

    }

    static <TableType extends CommonDBTable> void delete(TableType table, String ward, String area) {
        table.delete(ward, area);
    }

    public static <TableType extends CommonDBTable, ModelType extends CommonModel> void deleteData(TableType table, ArrayList<ModelType> list) {
        for (ModelType model : list) {
            if (table.isFieldExist(model.getId())) {
                table.delete(model.getId());
            }
        }
    }

    @Override
    public void onBackPressed() {
        /*if(deleted) {
            AlertMessage.showMessage(AreaUpgrade.this,"দুঃখিত","দয়া করে যে এলাকার তথ্য দেখতে চান সেটি নির্বাচন করে 'এলাকার তথ্য দেখুন' বাটন টি চাপুন");
        }
        else*/
        if (storedAreas.isEmpty()) {
            Intent em = new Intent(this, DataLoadingActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        } else super.onBackPressed();
    }
}
