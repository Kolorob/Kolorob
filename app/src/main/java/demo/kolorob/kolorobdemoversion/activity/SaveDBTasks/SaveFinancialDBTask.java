package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveFinancialDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, FinNewDBTable, FinancialNewDBModel> {


    public SaveFinancialDBTask(Context ctx, JSONObject json) {
        super(ctx, json);
    }


    @Override
    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new FinNewDBTable(context), new FinancialNewDBModel(), jsonArrays);
    }


    @Override
    void callNextProcess(){
        if (json.has("NGO")) {
            try {
                new SaveNgoDBTask(context, json).execute(json.getJSONArray("NGO"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
