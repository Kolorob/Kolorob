package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveGovernmentDBTask extends GenericSaveDBTask <GovNewDBTable, GovernmentNewDBModel> {


    public SaveGovernmentDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public Long saveItem(){
        return super.saveItem(new GovNewDBTable(context), new GovernmentNewDBModel());
    }

}