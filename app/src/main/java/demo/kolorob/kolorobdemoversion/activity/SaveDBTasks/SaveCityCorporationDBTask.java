package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import demo.kolorob.kolorobdemoversion.database.CityCorporationTable;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;


/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public class SaveCityCorporationDBTask extends GenericSaveDBTask <CityCorporationTable, CityCorporation> {

    public SaveCityCorporationDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public int saveItem(){
        return super.saveItem(new CityCorporationTable(context), new CityCorporation());
    }

}