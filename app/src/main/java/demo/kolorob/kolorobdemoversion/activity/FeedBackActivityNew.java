package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;

public class FeedBackActivityNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_activity_new);

        Intent intent= new Intent();



        ArrayList<String> age = new ArrayList<String>();
        age.add("ভাল");
        age.add("মোটামোটি ");
        age.add("ভাল না");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.dropdown_text_survey, age);
        Spinner ratingSpinner = (Spinner)findViewById(R.id.RatingSpinner);
        ratingSpinner.setAdapter(adapter);

    }

}
