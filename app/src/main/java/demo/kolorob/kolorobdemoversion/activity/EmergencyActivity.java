package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import demo.kolorob.kolorobdemoversion.R;


public class EmergencyActivity extends Activity {

    Context context;
    Spinner spinner2,spinner3,spinner4;
    ImageView rotateImage;
    private Handler handler;
    int in = 0;
    View view=null;

    ListView emergencyList;
    String[] values = new String[] { "Bangladesh Fire Service",
            "Police Control Room",
            "Pllabi Police Station",
            "RAB – 4",
            "DESCO - Electricity",
            "Ministry of Disaster Management"
    };

    String[] nubmer = new String[] { "029555555",
            "029555555",
            "027124000",
            "02-9015922",
            "029015922",
            "01777910499"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.emergency);
        context = this;
        emergencyList = (ListView) findViewById(R.id.list);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        emergencyList.setAdapter(adapter);

        // ListView Item Click Listener
        emergencyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) emergencyList.getItemAtPosition(position);
             //   int hasSMSPermission = checkSelfPermission( Manifest.permission.SEND_SMS );
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  Calling : " + itemValue, Toast.LENGTH_LONG)
                        .show();
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse(nubmer[itemPosition]));
//                if(checkPermission())
//                    context.startActivity(callIntent);

            }

        });
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }

}
