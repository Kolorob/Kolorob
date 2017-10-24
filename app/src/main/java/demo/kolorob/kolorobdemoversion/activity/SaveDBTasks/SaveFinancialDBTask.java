package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;

import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveFinancialDBTask extends GenericSaveDBTask <JSONArray, Integer, Long, FinNewDBTable, FinancialNewDBModel> {


    public SaveFinancialDBTask(Context ctx) {
        super(ctx);
    }


    public Long doInBackground(JSONArray... jsonArrays){
        return super.doInBackground(new FinNewDBTable(context), new FinancialNewDBModel(), jsonArrays);
    }
}
