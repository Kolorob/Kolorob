package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveFinancialDBTask extends GenericSaveDBTask <FinNewDBTable, FinancialNewDBModel> {


    public SaveFinancialDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public Long saveItem(){
        return super.saveItem(new FinNewDBTable(context), new FinancialNewDBModel());
    }


}
