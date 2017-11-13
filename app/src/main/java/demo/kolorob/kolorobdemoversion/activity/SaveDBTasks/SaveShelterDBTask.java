package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

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
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveShelterDBTask extends GenericSaveDBTask <ReligiousNewDBTable, ReligiousNewDBModel> {

    public SaveShelterDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public Long saveItem(){
        return super.saveItem(new ReligiousNewDBTable(context), new ReligiousNewDBModel());
    }

}
