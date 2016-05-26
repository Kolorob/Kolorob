package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.TextViewAdapter;

/**
 * Created by HP on 5/25/2016.
 */
public class SearchActivity extends Activity {

    GridView gridView;

    static final String[] MOBILE_OS = new String[] {
            "Android", "iOS","Windows", "Blackberry", "Blackberry","Android", "iOS","Windows", "Blackberry", "Blackberry","Android", "iOS","Windows", "Blackberry", "Blackberry","Android", "iOS","Windows", "Blackberry", "Blackberry", "Blacakberry", "Blackberry", "Blackberry", "Blackberry" ,"Blackberry" };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfilter);

        gridView = (GridView) findViewById(R.id.grid);

        gridView.setAdapter(new TextViewAdapter(getApplicationContext(), MOBILE_OS));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override


            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        ((RadioButton) v.findViewById(R.id.grid_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
