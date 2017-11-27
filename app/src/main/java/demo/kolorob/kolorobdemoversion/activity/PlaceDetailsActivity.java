package demo.kolorob.kolorobdemoversion.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.google.android.gms.maps.MapFragment;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.fragment.CommonFragment;
import demo.kolorob.kolorobdemoversion.fragment.CompareFragment;
import demo.kolorob.kolorobdemoversion.fragment.JobFragment;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentNew;
import demo.kolorob.kolorobdemoversion.fragment.SearchFragment;

public class PlaceDetailsActivity extends AppCompatActivity {

    static TabLayout.Tab mapTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.simpleTabLayout);
        tabLayout.setRotationX(180);

        mapTab = tabLayout.newTab();
        mapTab.setIcon(R.drawable.map);
        tabLayout.addTab(mapTab, true);

        TabLayout.Tab searchTab = tabLayout.newTab();
        searchTab.setIcon(R.drawable.search);
        searchTab.setText("SEARCH");
        tabLayout.addTab(searchTab);

        TabLayout.Tab jobTab = tabLayout.newTab();
        jobTab.setIcon(R.drawable.job_unselectedtab);
        tabLayout.addTab(jobTab);

        TabLayout.Tab compareTab = tabLayout.newTab();
        compareTab.setIcon(R.drawable.compare);

        tabLayout.addTab(compareTab);


        LinearLayout tabs = ((LinearLayout) tabLayout.getChildAt(0));

        for (int i = 0; i < tabs.getChildCount(); i++) {
            LinearLayout item = ((LinearLayout) tabs.getChildAt(i));
            item.setRotationX(180);
        }

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.simpleFrameLayout, new MapFragment());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        CommonFragment fragment = null;

                        switch(tab.getPosition()){
                            case 0: fragment = new MapFragmentNew();
                                break;
                            case 1: fragment = new SearchFragment();
                                break;
                            case 2: fragment = new JobFragment();
                                break;
                            case 3: fragment = new CompareFragment();
                                break;
                        }

                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);

                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();


                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );




    }

    @Override
    public void onBackPressed() {


        if (getFragmentManager().findFragmentById(R.id.simpleFrameLayout) instanceof MapFragmentNew){
            super.onBackPressed();
        }

        else {
            mapTab.select();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}
