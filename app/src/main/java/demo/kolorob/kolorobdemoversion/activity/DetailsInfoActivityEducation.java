package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;

import demo.kolorob.kolorobdemoversion.R;

public class DetailsInfoActivityEducation extends Activity {
    Dialog dialog;
    LinearLayout upperHand;
    int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_education);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels-32;
        width=displayMetrics.widthPixels-32;


        upperHand=(LinearLayout)findViewById(R.id.upper_part);

        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) upperHand.getLayoutParams();
        params2.height = height/5;
        params2.width = width;
        upperHand.setLayoutParams(params2);









    }
}
