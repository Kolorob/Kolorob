package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;


import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.model.BaseModel;


/**
 * Created by shamima.yasmin on 10/17/2017.
 * Methods are originally written by Israt.Mity
 */


public abstract class GenericSaveDBTask <TableType extends BaseDBTable, ModelType extends BaseModel>  {

    protected Context context;
    protected JSONArray json;

    abstract int saveItem();


    public GenericSaveDBTask(Context ctx, JSONArray json){
        this.context = ctx;
        this.json = json;
    }

    public GenericSaveDBTask(Context ctx){
        this.context = ctx;
    }


     protected int saveItem(TableType table, ModelType model) {


        Log.e(" Data collection : ",  "ongoing " + getClass());
        for (int i = 0; i < json.length(); i++) {
            try {
                if (!json.isNull(i)) {
                    JSONObject jsonObject = json.getJSONObject(i);
                    table.insertItem(model.parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 1;
    }


}