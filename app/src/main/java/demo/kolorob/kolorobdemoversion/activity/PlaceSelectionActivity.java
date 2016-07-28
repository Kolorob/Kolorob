package demo.kolorob.kolorobdemoversion.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;


public class PlaceSelectionActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton img;
    Toast t = null;
    boolean doubleBackToExitPressedOnce = false;
    float [][]mirpur10Coords = {
            { 42,267 },
            { 80,420 },
            { 109,412 },
            { 121,431 },
            { 440,363 },
            { 439,282 },
            { 439,278 },
            { 427,237 },
            { 419,224 },
            { 410,214 },
            { 401,212 },
            { 391,208 },
            { 377,197 },
            { 370,195 },
            { 343,201 },
            { 314,204 },
            { 313,220 },
            { 312,222 },
            { 297,223 },
            { 42,267 }
    };
    float [][]mirpur11Coords = {
            { 110,412 },
            { 120,433 },
            { 269,405 },
            { 275,416 },
            { 274,424 },
            { 283,444 },
            { 292,443 },
            { 294,465 },
            { 287,486 },
            { 260,483 },
            { 267,502 },
            { 296,519 },
            { 235,625 },
            { 222,619 },
            { 201,630 },
            { 168,636 },
            { 144,637 },
            { 129,641 },
            { 81,639 },
            { 66,624 },
            { 60,596 },
            { 60,593 },
            { 119,575 },
            { 80,421 }
    };
    @Override
    public void onBackPressed() {

        help();

//        if (doubleBackToExitPressedOnce) {
//            new AlertDialog.Builder(this)
//                    .setTitle("Close")
//                    .setMessage("Are you sure you want to close Kolorob")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//        }
//searchmain.setVisibility(View.GONE);
//        placemain.setVisibility(View.VISIBLE);
//        this.doubleBackToExitPressedOnce = true;
//
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
    }



    public void help(){
        LayoutInflater layoutInflater = LayoutInflater.from(PlaceSelectionActivity.this);
        View promptView = layoutInflater.inflate(R.layout.help_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlaceSelectionActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText userfeedback = (EditText) promptView.findViewById(R.id.edittext);
        final Button submit= (Button)promptView.findViewById(R.id.submit_btn);
        final Button button= (Button)promptView.findViewById(R.id.phone_call);
        final ImageView imageView7= (ImageView)promptView.findViewById(R.id.imageView7);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= SharedPreferencesHelper.getUser(PlaceSelectionActivity.this);
                String testUser=SharedPreferencesHelper.getFeedback(PlaceSelectionActivity.this);
                if(user.equals(testUser))
                {

                    AlertMessage.showMessage(PlaceSelectionActivity.this, "দুঃখিত মতামত গ্রহন করা সম্ভব হচ্ছে না", "আপনি ইতিপূর্বে মতামত দিয়ে ফেলেছেন");
                    back();
                }

                else
                    sendDataToserver(userfeedback.getText().toString());

            }
        });
//        prebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                PlaceDetailsActivityNewLayout.this.onBackPressed();
//
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PlaceSelectionActivity.this, "...ok....",Toast.LENGTH_LONG).show();
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = alertDialogBuilder.create();

                alert.cancel();
                finish();
            }
        });


        // setup a dialog window
        alertDialogBuilder.setCancelable(false);

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void back(){

        new AlertDialog.Builder(this)
                .setTitle("Close")
                .setMessage("আপনি কি কলরব বন্ধ করতে চান?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();


        this.doubleBackToExitPressedOnce = true;


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    public void sendDataToserver(final String text)
    {
        String username=SharedPreferencesHelper.getUser(PlaceSelectionActivity.this);
        SharedPreferencesHelper.setFeedback(PlaceSelectionActivity.this,username);

        String url = "http://www.kolorob.net/KolorobApi/api/help/save_query?query="+text;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PlaceSelectionActivity.this,response,Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jo = new JSONObject(response);
                            JSONArray forms = jo.getJSONArray("true");

                            if(forms.toString().equals("true"))
                            {
                                AlertMessage.showMessage(PlaceSelectionActivity.this, "মন্তব্যটি পাঠানো হয়ছে",
                                        "মন্তব্য করার জন্য আপনাকে ধন্যবাদ");
                                back();
                            }
                            else {
                                AlertMessage.showMessage(PlaceSelectionActivity.this, "মন্তব্য পাঠানো সফল হয়নি",
                                        "মন্তব্য করার জন্য আপনাকে ধন্যবাদ");
                                back();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlaceSelectionActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(PlaceSelectionActivity.this);
        requestQueue.add(stringRequest);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_selection_activity);

        // Get Display Metrics
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int height = metrics.heightPixels;
        final int width = metrics.widthPixels;
     //   int relativeWidthOfImage = (int)(width * 0.1);
        final int coordsHeight = 800;
        final int coordsWidth = 480;

//        Log.e("heightPixels", String.valueOf(height));
//        Log.e("widthPixels", String.valueOf(width));
//        Log.e("density", String.valueOf(metrics.density));
//        Log.e("densityDpi", String.valueOf(metrics.densityDpi));
//        Log.e("scaledDensity", String.valueOf(metrics.scaledDensity));
//        Log.e("xdpi", String.valueOf(metrics.xdpi));
//        Log.e("ydpi", String.valueOf(metrics.ydpi));
//        Log.e("rel img width", String.valueOf(relativeWidthOfImage));


        FrameLayout holder = (FrameLayout)findViewById(R.id.holder);
        holder.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                // Hack to deal with issue in original image source
                y = y - 37;
                x = x * ( (float)coordsWidth / (float)width );
                y = y * ( (float)coordsHeight / (float)height );
                boolean mirpur10Hit = isPointInPolygon(x, y, mirpur10Coords);
                boolean mirpur11Hit = isPointInPolygon(x, y, mirpur11Coords);
                boolean anyHit = false;
                if (t != null)
                    t.cancel();
//                if (y < ((float)height) / 2.0) {
                if (mirpur10Hit) {
                    Intent intent =new Intent(PlaceSelectionActivity.this,PlaceDetailsActivityNewLayout.class);
                    intent.putExtra(AppConstants.KEY_PLACE, 1);
                    startActivity(intent);
                    t = Toast.makeText(getApplicationContext(), "BAUNIABHAD!!!", Toast.LENGTH_SHORT);
                    anyHit = true;
                }
                else if (mirpur11Hit) {
                    Intent intent =new Intent(PlaceSelectionActivity.this,PlaceDetailsActivityNewLayout.class);
                    intent.putExtra(AppConstants.KEY_PLACE, 2);
                    startActivity(intent);
                    t = Toast.makeText(getApplicationContext(), "PARIS ROAD!!!", Toast.LENGTH_SHORT);
                    anyHit = true;


                }
                if (anyHit)
                    t.show();
                return true;
            }
        });

//        ImageButton img = new ImageButton(this);
//        ImageButton img2 = new ImageButton(this);
//        img.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                if (t != null)
//                    t.cancel();
//                t = Toast.makeText(getApplicationContext(), "marker 1 clicked", Toast.LENGTH_SHORT);
//                t.show();
//            }
//        });
//
//        img2.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                if (t != null)
//                    t.cancel();
//                t = Toast.makeText(getApplicationContext(), "marker 2 clicked", Toast.LENGTH_SHORT);
//                t.show();
//            }
//        });
//
//        img.setImageResource(R.drawable.place_marker);
//        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        img2.setImageResource(R.drawable.place_marker);
//        img2.setScaleType(ImageView.ScaleType.CENTER_CROP);

//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.width = relativeWidthOfImage;
//        params.height = relativeWidthOfImage;
//        params.leftMargin = (int) ((int) width * 0.7);
//        TypedValue typedValue = new TypedValue();
//        getResources().getValue(R.dimen.my_value, typedValue, true);
//        float myFloatValue = typedValue.getFloat();
//        params.topMargin  = (int) (height/myFloatValue);
//        img.setLayoutParams(params);
//
//        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params2.width = relativeWidthOfImage;
//        params2.height = relativeWidthOfImage;
//        float myFloatValue2= (float) (myFloatValue - 0.2);
//        params2.leftMargin = (int) ((int) width * 0.45);
//        params2.topMargin  = (int) (height / myFloatValue2);
//        img2.setLayoutParams(params2);
//
//
//        holder.addView(img, params);
//        holder.addView(img2, params2);

    }



    public boolean isPointInPolygon(float x, float y, float[][] coords) {


        int j = coords.length - 1;
        boolean oddNodes = false;

        for (int i = 0; i < coords.length; i++) {
            if ( ( coords[i][1] < y && coords[j][1] >= y ) || ( coords[j][1] < y && coords[i][1] >= y ) )
            {
                if (coords[i][0] +
                        (y - coords[i][1]) / (coords[j][1] - coords[i][1])*(coords[j][0] - coords[i][0]) < x) {
                    oddNodes = !oddNodes;
                }
            }
            j = i;
        }

        return oddNodes;
    }

    @Override
    public void onClick(View view){


    }


}