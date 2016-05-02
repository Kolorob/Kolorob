package demo.kolorob.kolorobdemoversion.activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import demo.kolorob.kolorobdemoversion.R;

public class PlaceChoiceActivity2 extends AppCompatActivity {

    LinearLayout first,second,third,menubar,SearchBar,SearchIcon,imgbau,imgpar;
    int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice2);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels-32;
        width=displayMetrics.widthPixels-32;

        Log.d("...>>>","Layout width"+width);

        first=(LinearLayout)findViewById(R.id.top_section);
        second= (LinearLayout)findViewById(R.id.bauniabad_section);
        third = (LinearLayout)findViewById(R.id.parisRoad_section);
        menubar=(LinearLayout)findViewById(R.id.menuBar);
        SearchBar=(LinearLayout)findViewById(R.id.SearchBar);
        SearchIcon=(LinearLayout)findViewById(R.id.SearchIcon);
        imgbau=(LinearLayout)findViewById(R.id.imageBau);
        imgpar=(LinearLayout)findViewById(R.id.imagePar);

       //LayoutParams = first.getLayoutParams();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) first.getLayoutParams();
        params.height = height/6;
        params.width = width;
        first.setLayoutParams(params);


        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) second.getLayoutParams();
        params2.height = height/5;
        params2.width = width;
        second.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) third.getLayoutParams();
        params3.height = height/5;
        params3.width = width;
        third.setLayoutParams(params3);

        LinearLayout.LayoutParams paramsMenue = (LinearLayout.LayoutParams) menubar.getLayoutParams();
        paramsMenue.height = height/16;
        paramsMenue.width = (width*2)/14;

        Log.d("...>>>", "Munue Width" + (width * 2) / 14);
        menubar.setLayoutParams(paramsMenue);

        LinearLayout.LayoutParams paramsSearch = (LinearLayout.LayoutParams) SearchBar.getLayoutParams();
        paramsSearch.height = height/16;
        paramsSearch.width = (width*10)/14;
        Log.d("...>>>", "SearchBar Width" + (width * 10) / 14);
        SearchBar.setLayoutParams(paramsSearch);

        LinearLayout.LayoutParams paramsSearchIcon = (LinearLayout.LayoutParams) SearchIcon.getLayoutParams();
        paramsSearchIcon.height = height/16;
        paramsSearchIcon.width = (width*2)/14;
        Log.d("...>>>", "Search Icon" + (width * 2) / 14);
        SearchIcon.setLayoutParams(paramsSearchIcon);


        LinearLayout.LayoutParams paramsBau = (LinearLayout.LayoutParams) imgbau.getLayoutParams();

        paramsBau.width = (width*2)/3;
        imgbau.setLayoutParams(paramsBau);


        LinearLayout.LayoutParams paramsPar = (LinearLayout.LayoutParams) imgpar.getLayoutParams();

        paramsPar.width = (width*2)/3;
        imgpar.setLayoutParams(paramsPar);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_choice_activity2, menu);
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
