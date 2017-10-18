package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableHospital;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.model.BaseModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public abstract class GenericSaveDBTask <Params, Progress, Result, TableType extends BaseDBTable, ModelType extends BaseModel> extends AsyncTask <Params, Progress, Result> {

    protected Context context;

    public GenericSaveDBTask(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected void onPostExecute(Result result) {
        if (((Long) result).longValue() == 0.0 && DataLoadingActivity.getCountofDb() < DataLoadingActivity.getNumberOfTasks()) { // Means the task is successful
            DataLoadingActivity.setCountofDb(DataLoadingActivity.getCountofDb() + 1);

            SharedPreferences settings = context.getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("KValue", DataLoadingActivity.getCountofDb());
            editor.apply();
            Log.d("tasks", "Tasks remaining: " + (DataLoadingActivity.getNumberOfTasks() - DataLoadingActivity.getCountofDb()));//number of tasks equivalent to how many api data is being stored
            ToastMessageDisplay.setText(context, "তথ্য সংগ্রহ চলছে");
            ToastMessageDisplay.showText(context);
        }
    }

    protected Long doInBackground(TableType table, ModelType model, JSONArray... jsonArrays) {


        JSONArray data = jsonArrays[0];

        for (int i = 0; i < data.length(); i++) {
            try {
                if (!data.isNull(i)) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    table.insertItem(model.parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return new Long(-1);
            }
        }
        return new Long(0);
    }

    protected Long doInBackground(TableType table, ModelType model, int categoryID, JSONArray... jsonArrays) {

        JSONArray data = jsonArrays[0];

        for (int i = 0; i < data.length(); i++) {
            try {
                if (!data.isNull(i)) {
                    JSONObject jsonObject = data.getJSONObject(i);

                    if(categoryID == AppConstants.HEALTH){

                        HealthNewDBModelMain health = (HealthNewDBModelMain)model.parse(jsonObject);
                        table.insertItem(health);

                        if (jsonObject.has("health_pharmacy")) {
                            JSONObject pharmacy = jsonObject.getJSONObject("health_pharmacy");
                            new HealthNewDBTablePharma(context).insertItem(new HealthNewDBModelPharmacy().parse(pharmacy, health.getId()));
                        }
                        if (jsonObject.has("health_hospital")) {
                            JSONObject hospital = jsonObject.getJSONObject("health_hospital");
                            new HealthNewDBTableHospital(context).insertItem(new HealthNewDBModelHospital().parse(hospital, health.getId()));
                        }
                    }

                    else if (categoryID == AppConstants.EDUCATION){

                        EduNewModel edu = (EduNewModel)model.parse(jsonObject);
                        table.insertItem(edu);

                        if (jsonObject.has("training_details")) {

                            JSONArray trainings = jsonObject.getJSONArray("training_details");

                            for (int j = 0; j < trainings.length(); j++) {
                                JSONObject training = trainings.getJSONObject(j);
                                new EduNewDBTableTraining(context).insertItem(new EduTrainingModel().parse(training, edu.getId()));
                            }
                        }
                        if (jsonObject.has("result_details")) {

                            JSONArray results = jsonObject.getJSONArray("result_details");

                            for (int j = 0; j < results.length(); j++) {
                                JSONObject result = results.getJSONObject(j);
                                new EducationResultDetailsTable(context).insertItem(new EducationResultItemNew().parse(result, edu.getId()));
                            }
                        }
                        if (jsonObject.has("education_school")) {
                            JSONObject school = jsonObject.getJSONObject("education_school");
                            new EduNewDBTableSchool(context).insertItem(new EduNewSchoolModel().parse(school, edu.getId()));
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