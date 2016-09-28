package demo.kolorob.kolorobdemoversion.parser;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.http.VolleySingleton;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.Lg;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/**
 * Created by touhid on 12/1/15.
 *
 * @author touhid
 */
public class VolleyApiParser {
    private static final String TAG = VolleyApiParser.class.getSimpleName();
    private static final String VOLLEY_TAG = "Background_API_Request";
    public Activity activity;
    public static String FinalUrl;


    public static void postRequest(final Context ctx, final int reqCode,
                                   final String jsonContent,
                                   final VolleyApiCallback vApiCb) {
        Map<String, String> contentMap = new HashMap<>();
        try {
            JSONObject jo = new JSONObject(jsonContent);
            Iterator<String> iter = jo.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    contentMap.put(key, jo.get(key).toString());
                } catch (JSONException e) {
                    Lg.e(TAG, "API JSON key conversion failed: key=" + key, e);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        postRequest(ctx, reqCode, contentMap, vApiCb);
    }

    public static void postRequest(final Context ctx, final int reqCode,
                                   final Map<String, String> contentMap,
                                   final VolleyApiCallback vApiCb) {
        postRequest(ctx, reqCode, contentMap, vApiCb, Request.Priority.NORMAL);
    }

    private static Map<String, String> getDefaultPostRequestMap(int reqCode) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", "-1");
        return map;
    }

    public Activity getActivity() {
        return activity;
    }

    public static void postRequest(final Context ctx, final int reqCode,
                                   final Map<String, String> contentMap,
                                   final VolleyApiCallback vApiCb,
                                   final Request.Priority priority) {

        if (!AppUtils.isNetConnected(ctx)) {


            ToastMessageDisplay.setText(ctx,"আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");
            ToastMessageDisplay.showText(ctx);
            return;
        }

        StringRequest strReq = new StringRequest(
                Request.Method.POST,
                AppConstants.API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Lg.i(TAG, VOLLEY_TAG + " : Response got: " + response);
                        vApiCb.onResponse(AppConstants.SUCCESS_CODE, response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (error != null)
                                Lg.e(TAG, VOLLEY_TAG + " : Error : toString=" + error.toString()
                                        + ", net response code=" + error.networkResponse.statusCode
                                        + ", NetResponse data length = " + error.networkResponse.data.length);
                            else
                                Lg.e(TAG,
                                        "onErrorResponse : Even halar-bai VolleyError is also null (>_<)");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        vApiCb.onResponse(AppConstants.ERR_VOLLEY_CODE,
                                "API Response error! Check connection & tried URL ...");
                    }
                }) {
            @Override
            public Object getTag() {
                return VOLLEY_TAG;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = getDefaultPostRequestMap(reqCode);
                if (contentMap != null)
                    for (Map.Entry<String, String> ce : contentMap.entrySet())
                        map.put(ce.getKey(), ce.getValue());
                return map;
            }

            @Override
            public Priority getPriority() {
                return priority;
            }


        };
        VolleySingleton.getInstance(ctx).addToRequestQueue(strReq);
    }

    public static void getRequest(final Context ctx, final String apiUrl,
                                  final VolleyApiCallback vApiCb) {
        getRequest(ctx, apiUrl, vApiCb, Request.Priority.NORMAL);
    }

    public static void getRequest(final Context ctx, final String apiUrl,
                                  final VolleyApiCallback vApiCb,
                                  final Request.Priority priority) {
        if (!AppUtils.isNetConnected(ctx)) {

            ToastMessageDisplay.setText(ctx,"আপনার ফোনে ইন্টারনেট সংযোগ নেই। অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");
            ToastMessageDisplay.showText(ctx);

        return;
    }


        Character c=apiUrl.charAt(0);
        Character c1= apiUrl.charAt(1);

         if(c.equals('h')&&(c1.equals('t')))
         {
             FinalUrl=apiUrl;
         }
        else
             FinalUrl= AppConstants.API_URL + apiUrl ;

        Log.d(">>>","FinalUrl"+FinalUrl);
        Log.d(">>>","apiUrl "+apiUrl);




        StringRequest strReq = new StringRequest(
                Request.Method.GET,
                FinalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Lg.i(TAG, VOLLEY_TAG + " : Response got: " + response);
                        vApiCb.onResponse(AppConstants.SUCCESS_CODE, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (error != null)
                                Lg.e(TAG, VOLLEY_TAG + " : Error : toString=" + error.toString()
                                        + ", net response code=" + error.networkResponse.statusCode
                                        + ", NetResponse data length = " + error.networkResponse.data.length);
                            else
                                Lg.e(TAG,
                                        "onErrorResponse :  VolleyError is also null (>_<)");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        vApiCb.onResponse(AppConstants.ERR_VOLLEY_CODE,
                                "API Response error! Check connection & tried URL ...");
                    }
                }) {

            @Override
            public Priority getPriority() {
                return priority;
            }

            @Override
            public Object getTag() {
                return VOLLEY_TAG;
            }
        };
        VolleySingleton.getInstance(ctx).addToRequestQueue(strReq);
    }

    /*public static void putRequest(final Context ctx, final int reqCode,
                                  final Map<String, String> contentMap,
                                  final VolleyApiCallback vApiCb) {
    }

    public static void putRequest(final Context ctx, final int reqCode,
                                  final Map<String, String> contentMap,
                                  final VolleyApiCallback vApiCb,
                                  final Request.Priority priority) {
    }

    public static void deleteRequest(final Context ctx, final int reqCode,
                                     final Map<String, String> contentMap,
                                     final VolleyApiCallback vApiCb) {
    }

    public static void deleteRequest(final Context ctx, final int reqCode,
                                     final Map<String, String> contentMap,
                                     final VolleyApiCallback vApiCb,
                                     final Request.Priority priority) {
    }*/
}
