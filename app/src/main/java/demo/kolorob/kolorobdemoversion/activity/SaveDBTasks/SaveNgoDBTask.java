package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveNgoDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, NGONewDBTable, NGONewDBModel> {


    public SaveNgoDBTask(Context ctx) {
        super(ctx);
    }


    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new NGONewDBTable(context), new NGONewDBModel(), jsonArrays);
    }
}
