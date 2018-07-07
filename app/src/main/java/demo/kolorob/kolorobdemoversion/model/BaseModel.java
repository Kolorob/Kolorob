package demo.kolorob.kolorobdemoversion.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shamima.yasmin on 10/18/2017.
 */

public abstract class BaseModel <ModelType>{
    public abstract ModelType parse (JSONObject jo) throws JSONException;
}
