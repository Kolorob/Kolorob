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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
            "Ministry of Disaster Management","Titas Gas","Dhaka WASA","Blood Bank","Dhaka North City Corporation","Violence against Women & Children",""
    };

    String[] address = new String[] { "Mirpur 10, Dhaka",
            "",
            "পল্লবী থানা",
            "",
            "DESCO, Pallabi, Dhaka",
            "দুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয","Pllabi, Dhaka","","Red Crescent","","","",
    };
    String[] name_bangla = new String[] { "029555555",
            "পুলিশ কন্ট্রোল রুম",
            "পল্লবী থানা",
            "র্যাব - ৪",
            "ডেসকো – ইলেক্ট্রিসিটি ",
            "দুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয়","তিতাস গ্যাস ","ঢাকা ওয়াসা","ব্লাড ব্যাংক ","ঢাকা উত্তর সিটি কর্পোরেশন","নারী ও শিশু নির্যাতন প্রতিরোধ",""
    };


    String[] phone_no = new String[] { "বাংলাদেশ ফায়ার সার্ভিস অ্যান্ড সিভিল ডিফেন্স ",
            "029555555",
            "027124000",
            "02-9015922",
            "029015922",
            "01777910499","02-9014291","16162","029139940","16364","10921",""
    };


    String[] address_bangla = new String[] { "",
            "মিরপুর ১০, ঢাকা",
            "Not Found",
            "",
            "পল্লবী, ঢাকা",
            "পল্লবী, ঢাকা","","","","","","",
    };


    String[] phone_no_bangla = new String[] { "০২৯৫৫৫৫৫৫",
            "০২৭১২৪০০০",
            "০২-৯০১৫৯২২",
            "০১৭৭৭৯১০৪৯৯",
            "০২৯০০১০৫১",
            "১০৯৪১","০২৯০১৪২৯১","১৬১৬২","০২৯১৩৯৯৪০","১৬৩৬৪","১০৯২১","১০৯৪১"
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


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        //emergencyList.setAdapter(adapter);

        final EmergencyAdapter adapter = new EmergencyAdapter(this);
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


                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                callIntent1.setData(Uri.parse("tel:" + phone_no[itemPosition]));
                if(checkPermission())
                    startActivity(callIntent1);
                else{
                    Toast.makeText(getApplicationContext(),
                            "Sorry, Phone call is not possible now. ", Toast.LENGTH_LONG)
                            .show();
                }

            }

        });
    }

    /*
	 * first adapter for state
	 */

    private class EmergencyAdapter extends ArrayAdapter<String> {
        // StateListActivty context;
        private final Context con;

        public EmergencyAdapter(final Context c) {
            super(c, R.layout.emergency_row, values);
            con = c;
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getView(final int position, final View convertView,
                            final ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) con
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.emergency_row, null);
            }
            final TextView textView = (TextView) v
                    .findViewById(R.id.title_id);
            textView.setText(values[position]);
            final TextView addr = (TextView) v
                    .findViewById(R.id.add_id);
            addr.setText(address[position]);
            final TextView no = (TextView) v
                    .findViewById(R.id.phone_id);
            no.setText(phone_no[position]);
            final TextView bangla_name=(TextView)v.findViewById(R.id.bangla_name);
            bangla_name.setText(name_bangla[position]);

            final TextView bangla_address =(TextView)v.findViewById(R.id.bangla_address);
            bangla_address.setText(address_bangla[position]);

            final TextView bangla_phone =(TextView)v.findViewById(R.id.bangla_phone);
            bangla_phone.setText(phone_no_bangla[position]);

            return v;
        }
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
