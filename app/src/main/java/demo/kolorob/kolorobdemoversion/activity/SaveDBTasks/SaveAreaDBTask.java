package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.model.Area;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public class SaveAreaDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, AreaTable, Area> {

    public SaveAreaDBTask(Context ctx) {
        super(ctx);
    }

    @Override
    protected void onPostExecute(Long result) {

    }

    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new AreaTable(context), new Area(), jsonArrays);
    }
}