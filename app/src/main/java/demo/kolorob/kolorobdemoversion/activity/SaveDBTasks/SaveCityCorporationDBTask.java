package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.database.CityCorporationTable;
import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public class SaveCityCorporationDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, CityCorporationTable, CityCorporation> {

    public SaveCityCorporationDBTask(Context ctx) {
        super(ctx);
    }

    @Override
    protected void onPostExecute(Long result) {
        Log.e(" Data collection : ",  "done " + getClass());
    }

    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new CityCorporationTable(context), new CityCorporation(), jsonArrays);
    }

    @Override

    protected void onPreExecute(){
        Log.e(" Data collection : ",  "starting " + getClass());
    }

}