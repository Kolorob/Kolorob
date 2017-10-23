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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveEducationDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveEntertainmentDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveFinancialDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveGovernmentDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveHealthDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveLegalDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveNgoDBTask;
import demo.kolorob.kolorobdemoversion.activity.SaveDBTasks.SaveShelterDBTask;
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
              selectedId = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));


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
                        serverCall(storedAreas.get(selectedId).getWard(),storedAreas.get(selectedId).getArea());
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


    void serverCall(String ward, String area) {

        getRequest(AreaUpgrade.this, "http://kolorob.net/kolorob-new-demo/api/getspbyarea?ward=" + ward + "&area=" + area, new VolleyApiCallback() {
            @Override
            public void onResponse(int status, String apiContent) {
                if (status == AppConstants.SUCCESS_CODE) {

                    try {

                        allData = new JSONObject(apiContent);

                        if (allData.has("Education")) {
                            new SaveEducationDBTask(AreaUpgrade.this).execute(allData.getJSONArray("Education"));
                        }

                        if (allData.has("Finance")) {
                            new SaveFinancialDBTask(AreaUpgrade.this).execute(allData.getJSONArray("Finance"));

                        }

                        if (allData.has("Health")) {
                            new SaveHealthDBTask(AreaUpgrade.this).execute(allData.getJSONArray("Health"));

                        }

                        if (allData.has("Legal")) {
                            new SaveLegalDBTask(AreaUpgrade.this).execute(allData.getJSONArray("Legal"));

                        }

                        if (allData.has("Government")) {
                            new SaveGovernmentDBTask(AreaUpgrade.this).execute(allData.getJSONArray("Government"));
                        }

                        if (allData.has("NGO")) {
                            new SaveNgoDBTask(AreaUpgrade.this).execute(allData.getJSONArray("NGO"));
                        }

                        if (allData.has("Entertainment")) {
                            new SaveEntertainmentDBTask(AreaUpgrade.this).execute(allData.getJSONArray("Entertainment"));
                        }

                        if (allData.has("Religious Shelter")) {

                            new SaveShelterDBTask(AreaUpgrade.this).execute(allData.getJSONArray("Religious Shelter"));
                        }



                        Log.d("Doneall",String.valueOf(allData.length()));
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



    public <TableType extends CommonDBTable> void deleteAll (String ward, String area) {

        ArrayList <TableType> tables = new ArrayList<>();

        tables.add((TableType)new EduNewDBTableMain(AreaUpgrade.this));
        tables.add((TableType)new HealthNewDBTableMain(AreaUpgrade.this));
        tables.add((TableType)new FinNewDBTable(AreaUpgrade.this));
        tables.add((TableType)new GovNewDBTable(AreaUpgrade.this));
        tables.add((TableType)new LegalAidNewDBTable(AreaUpgrade.this));
        tables.add((TableType)new EntNewDBTable(AreaUpgrade.this));
        tables.add((TableType)new NGONewDBTable(AreaUpgrade.this));
        tables.add((TableType)new ReligiousNewDBTable(AreaUpgrade.this));

        new EduNewDBTableSchool(AreaUpgrade.this).delete(ward, area);
        new EducationResultDetailsTable(AreaUpgrade.this).delete(ward, area);
        new EduNewDBTableTraining(AreaUpgrade.this).delete(ward, area);
        new HealthNewDBTableHospital(AreaUpgrade.this).delete(ward, area);
        new HealthNewDBTablePharma(AreaUpgrade.this).delete(ward, area);


        for(TableType table : tables){
            delete(table, ward, area);
        }

        StoredAreaTable storedAreaDB = new StoredAreaTable(AreaUpgrade.this);
        storedAreaDB.delete(storedAreaDB.getNodeInfo(ward, area).getId());


        radioGroup.clearCheck();
        dialog2.dismiss();
        radioGroup.removeAllViews();

        ToastMessageDisplay.setText(AreaUpgrade.this,"তথ্য ডিলিট করা হয়েছে");
        ToastMessageDisplay.showText(AreaUpgrade.this);
        deleted = true;
        radiobuttonsetup();

    }

    static <TableType extends CommonDBTable> void delete(TableType table, String ward, String area){
        table.delete(ward, area);
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
        /*if(deleted) {
            AlertMessage.showMessage(AreaUpgrade.this,"দুঃখিত","দয়া করে যে এলাকার তথ্য দেখতে চান সেটি নির্বাচন করে 'এলাকার তথ্য দেখুন' বাটন টি চাপুন");
        }
        else*/
        if(storedAreas.isEmpty()){
            Intent em = new Intent(this, DataLoadingActivity.class);
            startActivity(em);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
        else super.onBackPressed();
    }
}
