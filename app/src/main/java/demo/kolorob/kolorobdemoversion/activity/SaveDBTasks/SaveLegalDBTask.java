package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;

import org.json.JSONArray;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;

/**
 * Created by shamima.yasmin on 10/17/2017.
 */

public class SaveLegalDBTask extends GenericSaveDBTask <LegalAidNewDBTable, LegalAidNewDBModel> {

    public SaveLegalDBTask(Context ctx, JSONArray json) {
        super(ctx, json);
    }


    @Override
    public int saveItem(){
        return super.saveItem(new LegalAidNewDBTable(context), new LegalAidNewDBModel());
    }

}


