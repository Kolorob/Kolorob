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


public class SaveEntertainmentDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, EntNewDBTable, EntertainmentNewDBModel> {

    public SaveEntertainmentDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }

    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new EntNewDBTable(context), new EntertainmentNewDBModel(), jsonArrays);
    }

    @Override
    public void onPostExecute(Long result){
        callNextProcess();
    }

    @Override
    void callNextProcess(){
        if (json.has("Government")) {
            try {
                new SaveGovernmentDBTask(context, json).execute(json.getJSONArray("Government"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
