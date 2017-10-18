package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public abstract class SubModel <ModelType>{
    public abstract ModelType parse(JSONObject jo, int id) throws JSONException;
}
