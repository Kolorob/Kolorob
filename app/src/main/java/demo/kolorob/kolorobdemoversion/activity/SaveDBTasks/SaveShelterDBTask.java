package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.activity.PlaceDetailsActivityNewLayout;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;

import static demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity.NUMBER_OF_TASKS;
import static demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity.countofDb;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveShelterDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, ReligiousNewDBTable, ReligiousNewDBModel> {

    public SaveShelterDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }


    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new ReligiousNewDBTable(context), new ReligiousNewDBModel(), jsonArrays);
    }


    @Override
    public void callNextProcess(){
        Intent a = new Intent(context, PlaceDetailsActivityNewLayout.class); // Default Activity
        context.startActivity(a);
    }
}
