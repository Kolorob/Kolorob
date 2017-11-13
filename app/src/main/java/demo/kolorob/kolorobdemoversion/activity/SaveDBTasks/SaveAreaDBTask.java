package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import demo.kolorob.kolorobdemoversion.database.AreaTable;
import demo.kolorob.kolorobdemoversion.model.Area;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public class SaveAreaDBTask extends GenericSaveDBTask <AreaTable, Area> {

    public SaveAreaDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }

    @Override
    public Long saveItem(){
        return super.saveItem(new AreaTable(context), new Area());
    }

}