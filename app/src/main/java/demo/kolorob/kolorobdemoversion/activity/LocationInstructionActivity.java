package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import demo.kolorob.kolorobdemoversion.R;


public class LocationInstructionActivity extends Activity {

    private ImageButton yes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_instruction);
        yes = (ImageButton) findViewById(R.id.img_btn_ins_yes);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationInstructionActivity.this,PlaceChoiceActivity2.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
