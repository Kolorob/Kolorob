package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveCategoryDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, CategoryTable, CategoryItem> {

    public SaveCategoryDBTask(Context ctx) {
        super(ctx);
    }

    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new CategoryTable(context), new CategoryItem(), jsonArrays);
    }
}