package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveEducationDBTask extends GenericSaveDBTask<JSONArray, Integer, Long, EduNewDBTableMain, EduNewModel> {

    public SaveEducationDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }



    @Override
    public void callNextProcess() {
        if (json.has("Health")) {
            try {
                new SaveHealthDBTask(context, json).execute(json.getJSONArray("Health"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Long doInBackground(JSONArray... jsonArrays) {

        Log.e(" Data collection : ",  "ongoing " + getClass());

        JSONArray data = jsonArrays[0];

        for (int i = 0; i < data.length(); i++) {

            try {
                if (!data.isNull(i)) {
                    JSONObject jsonObject = data.getJSONObject(i);

                    EduNewModel edu = new EduNewModel().parse(jsonObject);
                    new EduNewDBTableMain(context).insertItem(edu);

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

            } catch (JSONException e) {
                e.printStackTrace();
                return new Long(-1);
            }
        }


        return new Long(0);

    }
}
