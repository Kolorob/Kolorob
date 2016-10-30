package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import info.hoang8f.widget.FButton;

/**
 * Created by israt.jahan on 10/30/2016.
 */

public class OfferActivity extends Activity {
    TextView time;
    FButton claim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_offer);
        SharedPreferences settings = OfferActivity.this.getSharedPreferences("prefs", 0);
        time=(TextView)findViewById(R.id.counter);
        claim=(FButton) findViewById(R.id.claim);
        claim.setShadowEnabled(false);
        claim.setButtonColor(getResources().getColor(R.color.gray));
        Date date2 = new Date(settings.getLong("time", 0));
        Date today=new Date();
        long diffInMillisec = today.getTime() - date2.getTime();

        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillisec);
        if (diffInDays==0) time.setText("0");
        else
        {

           time.setText(Long.toString(diffInDays));
        }
if(diffInDays>1)
{

    claim.setButtonColor(getResources().getColor(R.color.orange));
    claim.setShadowEnabled(true);
    claim.setShadowHeight(5);
    claim.setShadowColor(getResources().getColor(R.color.colorPrimaryDark));
}
    }
}
