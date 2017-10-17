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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CommonDBTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
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
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
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



public class AreaUpgrade <ModelType extends CommonModel> extends AppCompatActivity {

    ArrayList <StoredArea> storedAreas = new ArrayList<>();
    RadioGroup radioGroup;
    Button update,delete,browse;
    int selectedId = -1;
    Context context;
    Boolean deleted = false;
    LinearLayout linearLayout;
    ProgressDialog dialog, dialog2;
    JSONObject allData;
    StoredAreaTable storedAreaTable;

    ArrayList<StoredArea>storedAreaArrayList = new ArrayList<>();
    ArrayList<StoredArea>storedAreaArrayList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areas);

        update= (Button) findViewById(R.id.updatearea);
        delete= (Button) findViewById(R.id.deletearea);
        browse= (Button) findViewById(R.id.browsearea);
        linearLayout=(LinearLayout)findViewById(R.id.linearradio);
        storedAreaTable = new StoredAreaTable(AreaUpgrade.this);

        context=this;
        radioGroup = (RadioGroup)findViewById(R.id.areagroup);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radiobuttonsetup();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
              selectedId= radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(selectedId ==-1)
                {
                    ToastMessageDisplay.setText(AreaUpgrade.this,"প্রথমে এলাকা নির্বাচন করুন");
                    ToastMessageDisplay.showText(AreaUpgrade.this);
                }
                else {
                    dialog2 = new ProgressDialog(AreaUpgrade.this);
                    dialog2.setMessage("দয়া করে অপেক্ষা করুন");
                    dialog2.setCancelable(true);
                    dialog2.show();
                    deleteAll(storedAreas.get(selectedId).getWard(),storedAreas.get(selectedId).getArea()); // to delete area stored in device

                }
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(selectedId ==-1)
                {
                    ToastMessageDisplay.setText(AreaUpgrade.this,"প্রথমে এলাকা নির্বাচন করুন");
                    ToastMessageDisplay.showText(AreaUpgrade.this);
                }
                else {

                    storedAreaArrayList2=RetriveLocation(storedAreas.get(selectedId).getWard(),storedAreas.get(selectedId).getArea());
                    SharedPreferences settings = getSharedPreferences("prefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("_ward", storedAreaArrayList2.get(0).getWard()); // store ward and area from stored area in pref
                    //to use in next activity
                    editor.putString("areakeyword", storedAreaArrayList2.get(0).getArea());
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

                if(selectedId ==-1)
                {
                    ToastMessageDisplay.setText(AreaUpgrade.this,"প্রথমে এলাকা নির্বাচন করুন");
                    ToastMessageDisplay.showText(AreaUpgrade.this);
                }
                else {
                    dialog = new ProgressDialog(AreaUpgrade.this);
                    dialog.setMessage("দয়া করে অপেক্ষা করুন");
                    dialog.setCancelable(true);
                    dialog.show();
                    if(AppUtils.isNetConnected(getApplicationContext()))
                    {
                        Servercall(storedAreas.get(selectedId).getWard(),storedAreas.get(selectedId).getArea());
                    }
                    else
                    {
                        AlertMessage.showMessage(AreaUpgrade.this, " দুঃখিত","আপনার ডিভাইসের ইন্টারনেট চালু করুন");
                        dialog.cancel();
                    }

                }
            }}
        );

    }

    public ArrayList<StoredArea> RetriveLocation(String ward,String area) //last existing location er jonno
    {
        storedAreaArrayList = storedAreaTable.getStoredLocation(ward, area);
        return storedAreaArrayList;
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
        if(storedAreas.isEmpty()){
            ToastMessageDisplay.setText(AreaUpgrade.this,"কোন এলাকার তথ্য নামানো নেই");
            ToastMessageDisplay.showText(AreaUpgrade.this);
            Intent em = new Intent(this, DataLoadingActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        } else {
            for (int i = 0; i < storedAreas.size(); i++) {


                RadioButton ch = new RadioButton(this);
                ch.setText(storedAreas.get(i).getAreaBn());

                radioGroup.addView(ch);


            }

        }
    }


    void Servercall(String ward, String area) {



        getRequest(AreaUpgrade.this, "http://kolorob.net/kolorob-new-demo/api/getspbyarea?ward="+ward+"&area="+area, new VolleyApiCallback() {
            @Override
            public void onResponse(int status, String apiContent) {
                if (status == AppConstants.SUCCESS_CODE) {

                    try {

                        allData = new JSONObject(apiContent);


                        if(allData.has("Education"))
                            SavenewEdu(allData.getJSONArray("Education"));
                        if(allData.has("Finance"))
                            SavenewFin(allData.getJSONArray("Finance"));
                        if(allData.has("Health"))
                            SavenewHealth(allData.getJSONArray("Health"));

                        if(allData.has("Legal"))
                            SavenewLegal(allData.getJSONArray("Legal"));

                        if(allData.has("Government"))
                            SavenewGov(allData.getJSONArray("Government"));

                        if(allData.has("Entertainment"))
                            SavenewEntertainment(allData.getJSONArray("Entertainment"));

                        int p= allData.length();
                        Log.d("Doneall",String.valueOf(p));
                        dialog.dismiss();
                        ToastMessageDisplay.setText(AreaUpgrade.this,"তথ্য আপডেট হয়েছে");
                        ToastMessageDisplay.showText(AreaUpgrade.this);
                        SharedPreferences settings = getSharedPreferences("prefs", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        settings.edit().putLong("time", System.currentTimeMillis()).apply();
                        editor.putString("_ward", storedAreas.get(selectedId).getWard());
                        editor.putString("areakeyword", storedAreas.get(selectedId).getArea());
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
        EduNewDBTableMain eduNewDBTableMain = new EduNewDBTableMain(AreaUpgrade.this);
        EduNewDBTableTraining eduNewDBTableTraining = new EduNewDBTableTraining(AreaUpgrade.this);
        EduNewDBTableSchool eduNewDBTableSchool = new EduNewDBTableSchool(AreaUpgrade.this);
        EducationResultDetailsTable educationResultDetailsTable = new EducationResultDetailsTable(AreaUpgrade.this);

        for (int i = 0; i < Edu.length(); i++) {
            try {
                if(!Edu.isNull(i)) {
                    JSONObject jsonObject2 = Edu.getJSONObject(i);
                    EduNewModel eduNewModel = EduNewModel.parseEduNewModel(jsonObject2);
                    eduNewDBTableMain.insertItem(eduNewModel);
                    if (jsonObject2.has("training_details")) {
                        JSONArray edutrain = jsonObject2.getJSONArray("training_details");
                        int lenoftrain = edutrain.length();
                        for (int ii = 0; ii < lenoftrain; ii++) {
                            JSONObject train = edutrain.getJSONObject(ii);

                            EduTrainingModel eduTrainingModel = EduTrainingModel.parseEduTrainingModel(train, eduNewModel.getId());
                            eduNewDBTableTraining.insertItem(eduTrainingModel);
                        }

                    }
                     if (jsonObject2.has("education_school")) {
                        JSONObject school = jsonObject2.getJSONObject("education_school");
                        EduNewSchoolModel eduNewSchoolModel = EduNewSchoolModel.parseEduNewSchoolModel(school, eduNewModel.getId());
                        eduNewDBTableSchool.insertItem(eduNewSchoolModel);
                    }

                    if (jsonObject2.has("result_details")) {
                        JSONArray eduResult = jsonObject2.getJSONArray("result_details");

                        for (int ii = 0; ii < eduResult.length(); ii++) {
                            JSONObject result = eduResult.getJSONObject(ii);

                            EducationResultItemNew resultItemNew = EducationResultItemNew.parseEducationResultItemNew(result, eduNewModel.getId());
                            educationResultDetailsTable.insertItem(resultItemNew);
                        }

                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("educount", String.valueOf(i));
        }
    }
    void SavenewEntertainment(JSONArray jo) {
        JSONArray Ent = jo;
        EntNewDBTable entNewDBTable = new EntNewDBTable(AreaUpgrade.this);


        int Entcount = Ent.length();

        for (int i = 0; i < Entcount; i++) {
            try {
                if(!Ent.isNull(i)) {
                    JSONObject jsonObject2 = Ent.getJSONObject(i);
                    EntertainmentNewDBModel entertainmentNewDBModel = EntertainmentNewDBModel.parseEntertainmentNewDBModel(jsonObject2);
                    entNewDBTable.insertItem(entertainmentNewDBModel);


                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("entcount", String.valueOf(i));
        }
    }
    void SavenewGov(JSONArray jo) {
        JSONArray Gov = jo;
        GovNewDBTable govNewDBTable = new GovNewDBTable(AreaUpgrade.this);


        int Govcount = Gov.length();

        for (int i = 0; i < Govcount; i++) {
            try {
                if(!Gov.isNull(i)) {
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
        LegalAidNewDBTable legalAidNewDBTable = new LegalAidNewDBTable(AreaUpgrade.this);


        int Legalcount = Legal.length();

        for (int i = 0; i < Legalcount; i++) {
            try {
                if(!Legal.isNull(i)) {
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
    void SavenewFin(JSONArray jo) {
        JSONArray Fin = jo;
        FinNewDBTable finNewDBTable = new FinNewDBTable(AreaUpgrade.this);


        int Fincount = Fin.length();

        for (int i = 0; i < Fincount; i++) {
            try {
                if(!Fin.isNull(i)) {
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
    void SavenewHealth(JSONArray jo) {
        JSONArray Hel = jo;
        HealthNewDBTableMain govNewDBTable = new HealthNewDBTableMain(AreaUpgrade.this);
        HealthNewDBTablePharma healthNewDBTablePharma = new HealthNewDBTablePharma(AreaUpgrade.this);
        HealthNewDBTableHospital healthNewDBTableHospital=new HealthNewDBTableHospital(AreaUpgrade.this);
        int Helcount = Hel.length();

        for (int i = 0; i < Helcount; i++) {
            try {
                if(!Hel.isNull(i)) {
                    JSONObject jsonObject2 = Hel.getJSONObject(i);
                    HealthNewDBModelMain healthNewDBModelMain = HealthNewDBModelMain.parseHealthNewDBModelMain(jsonObject2);
                    govNewDBTable.insertItem(healthNewDBModelMain);
                    if (jsonObject2.has("health_pharmacy")) {
                        JSONObject pharmacy = jsonObject2.getJSONObject("health_pharmacy");


                        HealthNewDBModelPharmacy healthNewDBModelPharmacy = HealthNewDBModelPharmacy.parseHealthNewDBModelPharmacy(pharmacy, jsonObject2.getInt("id"));
                        healthNewDBTablePharma.insertItem(healthNewDBModelPharmacy);


                    } if (jsonObject2.has("health_hospital")) {
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
    }


    public <TableType extends CommonDBTable> void deleteAll (String ward, String area) {

        EduNewDBTableMain educationDB = new EduNewDBTableMain(AreaUpgrade.this);
        EduNewDBTableSchool schoolDB = new EduNewDBTableSchool(AreaUpgrade.this);
        EducationResultDetailsTable resultDB = new EducationResultDetailsTable(AreaUpgrade.this);
        EduNewDBTableTraining trainingDB = new EduNewDBTableTraining(AreaUpgrade.this);

        HealthNewDBTableMain healthDB = new HealthNewDBTableMain(AreaUpgrade.this);
        HealthNewDBTableHospital hospitalDB = new HealthNewDBTableHospital(AreaUpgrade.this);
        HealthNewDBTablePharma pharmacyDB = new HealthNewDBTablePharma(AreaUpgrade.this);

        EntNewDBTable entertainmentDB = new EntNewDBTable(AreaUpgrade.this);
        FinNewDBTable financeDB = new FinNewDBTable(AreaUpgrade.this);
        GovNewDBTable governmentDB = new GovNewDBTable(AreaUpgrade.this);
        LegalAidNewDBTable legalDB = new LegalAidNewDBTable(AreaUpgrade.this);
        NGONewDBTable ngoDB = new NGONewDBTable(AreaUpgrade.this);
        ReligiousNewDBTable shelterDB = new ReligiousNewDBTable(AreaUpgrade.this);

        StoredAreaTable storedAreaDB = new StoredAreaTable(AreaUpgrade.this);

        ArrayList <EduNewModel> eduList = educationDB.getByAreaCategory(ward, area, AppConstants.EDUCATION);
        ArrayList <HealthNewDBModelMain> healthList = healthDB.getByAreaCategory(ward, area, AppConstants.HEALTH);


        HashMap <TableType, ArrayList <ModelType> > hashMap = new HashMap<>();

        hashMap.put((TableType)educationDB, (ArrayList <ModelType>) eduList);
        hashMap.put((TableType)healthDB, (ArrayList <ModelType>) healthList);
        hashMap.put((TableType)entertainmentDB, (ArrayList <ModelType>) entertainmentDB.getByAreaCategory(ward, area, AppConstants.ENTERTAINMENT));
        hashMap.put((TableType)financeDB, (ArrayList <ModelType>) financeDB.getByAreaCategory(ward, area, AppConstants.FINANCIAL));
        hashMap.put((TableType)governmentDB, (ArrayList <ModelType>) governmentDB.getByAreaCategory(ward, area, AppConstants.GOVERNMENT));
        hashMap.put((TableType)legalDB, (ArrayList <ModelType>) legalDB.getByAreaCategory(ward, area, AppConstants.LEGAL));
        hashMap.put((TableType)ngoDB, (ArrayList <ModelType>) ngoDB.getByAreaCategory(ward, area, AppConstants.NGO));
        hashMap.put((TableType)shelterDB, (ArrayList <ModelType>) shelterDB.getByAreaCategory(ward, area, AppConstants.RELIGIOUS));


        for(EduNewModel edu : eduList){

            for(EduNewSchoolModel school : schoolDB.getDataListFromForeignKey(edu.getId())){
                schoolDB.delete(school.getId());
            }
            for(EducationResultItemNew result : resultDB.getDataListFromForeignKey(edu.getId())){
                resultDB.delete(result.getId());
            }
            for(EduTrainingModel training : trainingDB.getDataListFromForeignKey(edu.getId())){
                trainingDB.delete(training.getId());
            }
        }

        for(HealthNewDBModelMain health : healthList){
            for(HealthNewDBModelHospital hospital : hospitalDB.getDataListFromForeignKey(health.getId())){
                hospitalDB.delete(hospital.getId());
            }
            for(HealthNewDBModelPharmacy pharmacy : pharmacyDB.getDataListFromForeignKey(health.getId())){
                pharmacyDB.delete(pharmacy.getId());
            }
        }


        Set <TableType> keySet = hashMap.keySet();
        Iterator <TableType> keySetIterator = keySet.iterator();

        while (keySetIterator.hasNext()) {
            TableType key = keySetIterator.next();
            deleteData(key, hashMap.get(key));
        }


        storedAreaDB.delete(storedAreaDB.getNodeInfo(ward, area).getId());


        radioGroup.clearCheck();
        dialog2.dismiss();
        radioGroup.removeAllViews();

        ToastMessageDisplay.setText(AreaUpgrade.this,"তথ্য ডিলিট করা হয়েছে");
        ToastMessageDisplay.showText(AreaUpgrade.this);
        deleted=true;
        radiobuttonsetup();

    }

    public static <TableType extends CommonDBTable, ModelType extends CommonModel> void deleteData(TableType table, ArrayList <ModelType> list ){
        for(ModelType model : list){
            if(table.isFieldExist(model.getId())){
                table.delete(model.getId());
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(deleted) {
            AlertMessage.showMessage(AreaUpgrade.this,"দুঃখিত","দয়া করে যে এলাকার তথ্য দেখতে চান সেটি নির্বাচন করে 'এলাকার তথ্য দেখুন' বাটন টি চাপুন");
        }
        else
        super.onBackPressed();
    }
}
