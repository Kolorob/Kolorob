package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import demo.kolorob.kolorobdemoversion.R;


public class LocationAskActivity extends Activity implements View.OnClickListener{

    private ImageButton yes;
    private ImageButton no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_ask);

        yes = (ImageButton) findViewById(R.id.img_btn_yes);
        no = (ImageButton) findViewById(R.id.img_btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_btn_yes:
                Intent i = new Intent(LocationAskActivity.this, LocationInstructionActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.img_btn_no:
                Intent ii = new Intent(LocationAskActivity.this, LocationInstructionActivity.class);
                startActivity(ii);
                finish();
                break;
            default:
                break;

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
