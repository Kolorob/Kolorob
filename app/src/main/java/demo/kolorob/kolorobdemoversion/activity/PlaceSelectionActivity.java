package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class PlaceSelectionActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton img;

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;

    Toast t = null;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_selection_activity);


        addListenerOnRatingBar();
        addListenerOnButton();

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


    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
      //  txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    public void addListenerOnButton() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
      //  btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                Toast.makeText(PlaceSelectionActivity.this,
//                        String.valueOf(ratingBar.getRating()),
//                        Toast.LENGTH_SHORT).show();

            }

        });

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