package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveCategoryDBTask extends GenericSaveDBTask <CategoryTable, CategoryItem> {

    public SaveCategoryDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }

    @Override
    public Long saveItem(){
        return super.saveItem(new CategoryTable(context), new CategoryItem());
    }



}