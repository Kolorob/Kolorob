package demo.kolorob.kolorobdemoversion.activity.SaveDBTasks;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;


/**
 * Created by shamima.yasmin on 10/17/2017.
 */


public class SaveReferenceDBTask extends GenericSaveDBTask<JSONArray, Integer, Long, SubCategoryTableNew, SubCategoryItemNew> {

    public SaveReferenceDBTask(Context ctx) {
        super(ctx);
    }


    @Override
    public Long doInBackground(JSONArray... jsonArrays) {
        return super.doInBackground(new SubCategoryTableNew(context), new SubCategoryItemNew(), jsonArrays);
    }

    @Override
    public void callNextProcess() {

        SharedPreferences settings = context.getSharedPreferences("prefs", 0);
        String ward = settings.getString("_ward", null);
        String area = settings.getString("areakeyword", null);


        getRequest(context, "http://kolorob.net/kolorob-new-demo/api/getspbyarea?ward=" + ward + "&area=" + area, new VolleyApiCallback() {
            @Override
            public void onResponse(int status, String apiContent) {
                if (status == AppConstants.SUCCESS_CODE) {

                    try {

                        JSONObject allData = new JSONObject(apiContent);

                        if (allData.has("Education")) {
                            new SaveEducationDBTask(context, allData).execute(allData.getJSONArray("Education"));
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }


                }


            }


        });


    }

}
