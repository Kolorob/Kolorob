package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveGovernmentDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, GovNewDBTable, GovernmentNewDBModel> {


    public SaveGovernmentDBTask(Context ctx) {
        super(ctx);
    }

    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new GovNewDBTable(context), new GovernmentNewDBModel(), jsonArrays);
    }
}