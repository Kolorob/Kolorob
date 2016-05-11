package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.DisplayAllJobList;

public class DisplayAllJobsActivity extends Activity {

        Context context;
        ListView joblist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_jobs);



        context=this;

        String[] tittle = new String[] { "Plumber Needed",
                "Electrician Needed",
                "Babysitter Needed",

        };

        String[] salary_range = new String[] { "10,000 Taka",
                "30,000 Taka",
                "20,000 Taka",

        };

        String[] remaining_date = new String[] { "3 days",
                "4 days",
                "1 day",

        };

        String[] address = new String[] { "Bauniabad",
                "Paris Road",
                "Bauniabad",

        };

        String[] contact_number = new String[] { "01988009755",
                "01790615263",
                "01558409186",

        };


        joblist=(ListView)findViewById(R.id.jobList);

        DisplayAllJobList displayAllJobList= new DisplayAllJobList(this,tittle, salary_range, remaining_date, address, contact_number);
        joblist.setAdapter(displayAllJobList);


        joblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             Intent intent = new Intent(DisplayAllJobsActivity.this,DetailsJobActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_all_jobs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
