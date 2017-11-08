package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;


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


    @Override
    public void callNextProcess(){
        getRequest(context, "http://kolorob.net/kolorob-new-demo/api/refs? ", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {
                        if (status == AppConstants.SUCCESS_CODE) {

                            try {
                                JSONArray jo = new JSONArray(apiContent);
                                new SaveReferenceDBTask(context).execute(jo);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }


}