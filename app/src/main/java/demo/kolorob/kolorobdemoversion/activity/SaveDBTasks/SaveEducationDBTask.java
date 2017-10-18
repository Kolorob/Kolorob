package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */



public class SaveEducationDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, EduNewDBTableMain, EduNewModel> {
    public SaveEducationDBTask(Context ctx) {
        super(ctx);
    }

    protected Long doInBackground(JSONArray... jsonObjects) {
        return super.doInBackground(new EduNewDBTableMain(context), new EduNewModel(), AppConstants.EDUCATION, jsonObjects);
    }
}
