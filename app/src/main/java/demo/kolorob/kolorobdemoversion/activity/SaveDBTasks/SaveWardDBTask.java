package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import demo.kolorob.kolorobdemoversion.database.WardTable;
import demo.kolorob.kolorobdemoversion.model.Ward;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */


public class SaveWardDBTask extends GenericSaveDBTask <WardTable, Ward> {

    public SaveWardDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public int saveItem(){
        return super.saveItem(new WardTable(context), new Ward());
    }

}