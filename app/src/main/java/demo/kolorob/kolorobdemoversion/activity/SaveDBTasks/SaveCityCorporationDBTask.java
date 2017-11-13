package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CityCorporationTable;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

import static demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity.countofDb;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public class SaveCityCorporationDBTask extends GenericSaveDBTask <CityCorporationTable, CityCorporation> {

    public SaveCityCorporationDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public Long saveItem(){
        return super.saveItem(new CityCorporationTable(context), new CityCorporation());
    }

}