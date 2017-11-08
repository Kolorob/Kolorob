package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveReferenceDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, SubCategoryTableNew, SubCategoryItemNew> {

    public SaveReferenceDBTask(Context ctx) {
        super(ctx);
    }


    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new SubCategoryTableNew(context), new SubCategoryItemNew(), jsonArrays);
    }

    @Override
    public void callNextProcess(){

    }
}