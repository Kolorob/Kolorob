package demo.kolorob.kolorobdemoversion.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
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
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
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
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

/**
 * Created by HP on 2/13/2017.
 */

public class AreaUpgrade extends AppCompatActivity {
ArrayList<StoredArea>storedAreas=new ArrayList<>();
    RadioGroup rg;
    Button update,delete;
    int selectedId=-1;
    Context context;
    ProgressDialog dialog,dialog2;
    ArrayList<HealthNewDBModelMain>healthNewDBModelMains=new ArrayList<>();
    JSONObject allData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areas);
 rg= (RadioGroup)findViewById(R.id.areagroup);
        update= (Button) findViewById(R.id.updatearea);
        delete= (Button) findViewById(R.id.deletearea);
        StoredAreaTable storedAreaTable = new StoredAreaTable(AreaUpgrade.this);
        storedAreas=storedAreaTable.getAllstored();
context=this;
        rg.setOrientation(RadioGroup.VERTICAL);
        for(int i = 0; i < storedAreas.size(); i++) {
            RadioButton ch = new RadioButton(this);
            ch.setTextSize(30);
            ch.setText(storedAreas.get(i).getAreaid().concat(",ward ").concat(storedAreas.get(i).getWardid()));
            rg.addView(ch);


        }
delete.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        selectedId = rg.getCheckedRadioButtonId() - 1;
        if(selectedId ==-2)
        {
            ToastMessageDisplay.setText(AreaUpgrade.this,"please choose first");
            ToastMessageDisplay.showText(AreaUpgrade.this);
        }
        else {
            dialog2 = new ProgressDialog(AreaUpgrade.this);
            dialog2.setMessage("দয়া করে অপেক্ষা করুন");
            dialog2.setCancelable(true);
            dialog2.show();
           deleteall(storedAreas.get(selectedId).getWardid(),storedAreas.get(selectedId).getAreaid());
        }
    }}
);

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectedId = rg.getCheckedRadioButtonId() - 1;
                if(selectedId ==-2)
                {
                    ToastMessageDisplay.setText(AreaUpgrade.this,"please choose first");
                    ToastMessageDisplay.showText(AreaUpgrade.this);
                }
                else {
                    dialog = new ProgressDialog(AreaUpgrade.this);
                    dialog.setMessage("দয়া করে অপেক্ষা করুন");
                    dialog.setCancelable(true);
                    dialog.show();

                    Servercall(storedAreas.get(selectedId).getWardid(),storedAreas.get(selectedId).getAreaid());
                }
            }}
        );

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        selectedId=-1;
    }
    @Override
    protected void onResume() {
        super.onRestart();
        selectedId=-1;
    }

    void Servercall(String ward, String area) {



        getRequest(AreaUpgrade.this, "http://kolorob.net/kolorob-live/api/getspbyarea?ward="+ward+"&area="+area, new VolleyApiCallback() {
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
                        ToastMessageDisplay.setText(AreaUpgrade.this,"Data Updated");
                        ToastMessageDisplay.showText(AreaUpgrade.this);
                        selectedId=-1;
                        rg.clearCheck();
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

        int Govcount = Edu.length();

        for (int i = 0; i < Govcount; i++) {
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
    }
    public void deleteall(String ward,String area)
    {
        HealthNewDBTableMain healthNewDBTableMain = new HealthNewDBTableMain(AreaUpgrade.this);
        FinNewDBTable finNewDBTable = new FinNewDBTable(AreaUpgrade.this);
        LegalAidNewDBTable legalAidNewDBTable = new LegalAidNewDBTable(AreaUpgrade.this);
        GovNewDBTable govNewDBTable = new GovNewDBTable(AreaUpgrade.this);
        EntNewDBTable entNewDBTable = new EntNewDBTable(AreaUpgrade.this);
        EduNewDBTableMain eduNewDBTableMain = new EduNewDBTableMain(AreaUpgrade.this);
        StoredAreaTable storedAreaTable=new StoredAreaTable(AreaUpgrade.this);

        healthNewDBTableMain.delete(ward,area);
        eduNewDBTableMain.delete(ward,area);
        entNewDBTable.delete(ward,area);
        legalAidNewDBTable.delete(ward,area);
        finNewDBTable.delete(ward,area);
        govNewDBTable.delete(ward,area);
        storedAreaTable.delete(ward,area);
        rg.clearCheck();
        selectedId=-1;
        dialog2.dismiss();
        ToastMessageDisplay.setText(AreaUpgrade.this,"Data Deleted");
        ToastMessageDisplay.showText(AreaUpgrade.this);

    }
}
