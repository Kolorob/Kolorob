package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveNgoDBTask extends GenericSaveDBTask <NGONewDBTable, NGONewDBModel> {


    public SaveNgoDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public int saveItem(){
        return super.saveItem(new NGONewDBTable(context), new NGONewDBModel());
    }


}
