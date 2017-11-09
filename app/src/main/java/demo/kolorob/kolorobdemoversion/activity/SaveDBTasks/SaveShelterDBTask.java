package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.activity.PlaceDetailsActivityNewLayout;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.database.StoredAreaTable;
import demo.kolorob.kolorobdemoversion.database.WardTable;
import demo.kolorob.kolorobdemoversion.model.Area;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.model.StoredArea;

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

        StoredAreaTable storedAreaTable = new StoredAreaTable(context);
        WardTable wardTable = new WardTable(context);

        SharedPreferences settings = context.getSharedPreferences("prefs", 0);
        Area area = new AreaTable(context).getNodeInfo(settings.getInt("areaID", 0));
        storedAreaTable.insertItem(new StoredArea(area.getId(), wardTable.getNodeInfo(area.getWard_id()).getWard_keyword(), area.getArea_keyword(), area.getArea_bn(), area.getParentArea(), area.getLat(), area.getLon()));

        Intent a = new Intent(context, PlaceDetailsActivityNewLayout.class); // Default Activity
        context.startActivity(a);
        context.stopService(a);

        ((Activity)context).overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        ((Activity) context).finish();
    }
}
