package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveEntertainmentDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, EntNewDBTable, EntertainmentNewDBModel> {

    public SaveEntertainmentDBTask(Context ctx) {
        super(ctx);
    }

    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new EntNewDBTable(context), new EntertainmentNewDBModel(), jsonArrays);
    }
}
