package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveShelterDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, ReligiousNewDBTable, ReligiousNewDBModel> {

    public SaveShelterDBTask(Context ctx) {
        super(ctx);
    }

    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new ReligiousNewDBTable(context), new ReligiousNewDBModel(), jsonArrays);
    }
}
