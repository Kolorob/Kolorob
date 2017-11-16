package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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


public class SaveEducationDBTask extends GenericSaveDBTask <EduNewDBTableMain, EduNewModel> {

    public SaveEducationDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }



    @Override
    public int saveItem() {

        Log.e(" Data collection : ",  "ongoing " + getClass());

        for (int i = 0; i < json.length(); i++) {

            try {
                if (!json.isNull(i)) {
                    JSONObject jsonObject = json.getJSONObject(i);

                    EduNewModel edu = new EduNewModel().parse(jsonObject);
                    new EduNewDBTableMain(context).insertItem(edu);

                    if (jsonObject.has("training_details")) {

                        Log.e(" Edu : ",  "training details");

                        JSONArray trainings = jsonObject.getJSONArray("training_details");

                        for (int j = 0; j < trainings.length(); j++) {
                            JSONObject training = trainings.getJSONObject(j);
                            new EduNewDBTableTraining(context).insertItem(new EduTrainingModel().parse(training, edu.getId()));
                        }
                    }
                    if (jsonObject.has("result_details")) {

                        Log.e(" Edu : ",  "result details");

                        JSONArray results = jsonObject.getJSONArray("result_details");

                        for (int j = 0; j < results.length(); j++) {
                            JSONObject result = results.getJSONObject(j);
                            new EducationResultDetailsTable(context).insertItem(new EducationResultItemNew().parse(result, edu.getId()));
                        }
                    }
                    if (jsonObject.has("education_school")) {

                        Log.e(" Edu : ",  "school details");

                        JSONObject school = jsonObject.getJSONObject("education_school");
                        new EduNewDBTableSchool(context).insertItem(new EduNewSchoolModel().parse(school, edu.getId()));
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
                return -1;
            }
        }


        return 1;

    }
}
