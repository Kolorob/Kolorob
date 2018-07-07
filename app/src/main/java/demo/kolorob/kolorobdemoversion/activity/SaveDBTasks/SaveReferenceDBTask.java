package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import org.json.JSONArray;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveReferenceDBTask extends GenericSaveDBTask<SubCategoryTableNew, SubCategoryItemNew> {

    public SaveReferenceDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public int saveItem() {
        return super.saveItem(new SubCategoryTableNew(context), new SubCategoryItemNew());
    }

}
