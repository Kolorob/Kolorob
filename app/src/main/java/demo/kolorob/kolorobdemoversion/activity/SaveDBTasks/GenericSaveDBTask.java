package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity;
import demo.kolorob.kolorobdemoversion.database.BaseDBTable;
import demo.kolorob.kolorobdemoversion.model.BaseModel;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

import static demo.kolorob.kolorobdemoversion.activity.DataLoadingActivity.countofDb;


/**
 * Created by shamima.yasmin on 10/17/2017.
 * Methods are originally written by Israt.Mity
 */


public abstract class GenericSaveDBTask <Params, Progress, Result, TableType extends BaseDBTable, ModelType extends BaseModel> extends AsyncTask<Params, Progress, Result> {

    protected Context context;
    protected JSONObject json;

    abstract void callNextProcess();


    public GenericSaveDBTask(Context ctx, JSONObject json){
        super();
        this.context = ctx;
        this.json = json;
    }

    public GenericSaveDBTask(Context ctx){
        super();
        this.context = ctx;
    }


    /*@Override
    protected void onPostExecute(Result result) {

        Log.e(" Data collection : ",  "done " + getClass());
        if (((Long) result).longValue() == 0.0 && countofDb < NUMBER_OF_TASKS) { // Means the task is successful
            countofDb++;

            SharedPreferences settings = context.getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("KValue", countofDb);
            editor.apply();
            Log.d("tasks", "Tasks remaining: " + (NUMBER_OF_TASKS - countofDb));  //number of tasks equivalent to how many api data is being stored
            /*ToastMessageDisplay.setText(context, context.getString(R.string.downloading_data));
            ToastMessageDisplay.showText(context);*/
      /*  }
    }*/

    @Override
    protected void onPostExecute(Result result) {

        Log.e(" Data collection : ", "done " + getClass());
        //ToastMessageDisplay.setText(context, context.getString(R.string.collecting_data));
        //ToastMessageDisplay.showText(context);
        if (((Long)result).longValue() == 0.0) {
            countofDb++;
            callNextProcess();
        }
    }


     protected Long doInBackground(TableType table, ModelType model, JSONArray... jsonArrays) {


        Log.e(" Data collection : ",  "ongoing " + getClass());

        JSONArray data = jsonArrays[0];

        for (int i = 0; i < data.length(); i++) {
            try {
                if (!data.isNull(i)) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    table.insertItem(model.parse(jsonObject));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return new Long(-1);
            }
        }
        return new Long(0);
    }

    @Override

    protected void onPreExecute(){
        super.onPreExecute();
        Log.e(" Data collection : ",  "starting " + getClass());
    }


}