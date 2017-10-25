package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.WardTable;
import demo.kolorob.kolorobdemoversion.model.Ward;


/**
 * Created by shamima.yasmin on 10/18/2017.
 */


public class SaveWardDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, WardTable, Ward> {

    public SaveWardDBTask(Context ctx) {
        super(ctx);
    }

    @Override
    protected void onPostExecute(Long result) {

    }

    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new WardTable(context), new Ward(), jsonArrays);
    }
}