package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveEntertainmentDBTask extends GenericSaveDBTask <EntNewDBTable, EntertainmentNewDBModel> {

    public SaveEntertainmentDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }

    @Override
    public int saveItem(){
        return super.saveItem(new EntNewDBTable(context), new EntertainmentNewDBModel());
    }

}
