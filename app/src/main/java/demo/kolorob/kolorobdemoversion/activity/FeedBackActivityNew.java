package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;

public class FeedBackActivityNew extends AppCompatActivity {

    private String nodeId;
    private String CategoryId;
    private EditText name,email,phone_no;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_activity_new);

        Intent intent= new Intent();

        nodeId=intent.getStringExtra("id");
        CategoryId=intent.getStringExtra("categoryId");
        name=(EditText)findViewById(R.id.name_userx);
        email=(EditText)findViewById(R.id.email_userx);
        phone_no=(EditText)findViewById(R.id.phone_userx);
        send =(Button)findViewById(R.id.send);






        ArrayList<String> age = new ArrayList<String>();
        age.add("ভাল");
        age.add("মোটামোটি ");
        age.add("ভাল না");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.dropdown_text_survey, age);
        Spinner ratingSpinner = (Spinner)findViewById(R.id.RatingSpinner);
        ratingSpinner.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

}
