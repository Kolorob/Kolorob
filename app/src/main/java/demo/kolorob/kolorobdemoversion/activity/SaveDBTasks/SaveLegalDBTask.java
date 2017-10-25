package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveLegalDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, LegalAidNewDBTable, LegalAidNewDBModel> {

    public SaveLegalDBTask(Context ctx) {
        super(ctx);
    }


    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new LegalAidNewDBTable(context), new LegalAidNewDBModel(), jsonArrays);
    }
}


