package demo.kolorob.kolorobdemoversion.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.fragment.CommonFragment;
import demo.kolorob.kolorobdemoversion.fragment.CompareFragment;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentNew;
import demo.kolorob.kolorobdemoversion.fragment.SearchFragment;


public class PlaceDetailsActivity extends AppCompatActivity {

    static TabLayout.Tab mapTab;
    Context context;

    public Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.activity_place_details);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        tabLayout.setRotationX(180);

        mapTab = tabLayout.newTab();

        View mapIconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        mapIconView.findViewById(R.id.icon).setBackgroundResource(R.drawable.map);
        tabLayout.addTab(mapTab.setCustomView(mapIconView));

        View searchIconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        searchIconView.findViewById(R.id.icon).setBackgroundResource(R.drawable.search);
        tabLayout.addTab(tabLayout.newTab().setCustomView(searchIconView));

        View jobIconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        jobIconView.findViewById(R.id.icon).setBackgroundResource(R.drawable.job_unselectedtab);
        tabLayout.addTab(tabLayout.newTab().setCustomView(jobIconView));

        View compareIconView = getLayoutInflater().inflate(R.layout.custom_tab, null);
        compareIconView.findViewById(R.id.icon).setBackgroundResource(R.drawable.compare);
        tabLayout.addTab(tabLayout.newTab().setCustomView(compareIconView));


        LinearLayout tabs = ((LinearLayout) tabLayout.getChildAt(0));

        for (int i = 0; i < tabs.getChildCount(); i++) {
            LinearLayout item = ((LinearLayout) tabs.getChildAt(i));
            item.setRotationX(180);
        }

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.simpleFrameLayout, new SearchFragment());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        CommonFragment fragment = null;

                        switch (tab.getPosition()) {

                            case 0:
                                fragment = new MapFragmentNew();
                                break;

                            case 1:
                                fragment = new SearchFragment();
                                break;

                            case 2:
                                showJobDialog();
                                break;

                            case 3:
                                fragment = new CompareFragment();
                                break;
                        }

                        if (tab.getPosition() != 2) {
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.simpleFrameLayout, fragment).addToBackStack(null);

                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            fragmentTransaction.commit();

                        }


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


    public void showJobDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(PlaceDetailsActivity.this);
        View promptView = layoutInflater.inflate(R.layout.default_alert, null);


        final Dialog alertDialog = new Dialog(PlaceDetailsActivity.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(promptView);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();


        final TextView header = (TextView) promptView.findViewById(R.id.headers);
        final TextView bodys = (TextView) promptView.findViewById(R.id.body);
        final ImageView okay = (ImageView) promptView.findViewById(R.id.okay);

        header.setText("!!!");
        header.setTextColor(getResources().getColor(R.color.Black));
        bodys.setText(R.string.job_portal_coming_soon);
        bodys.setTextColor(getResources().getColor(R.color.Black));
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        int width = displayMetrics.widthPixels;

        //alertDialog.setCancelable(false);

        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 0.0f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
        alertDialog.getWindow().setAttributes(lp);

        alertDialog.getWindow().setLayout((width * 2) / 3, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onBackPressed() {


        if (getFragmentManager().findFragmentById(R.id.simpleFrameLayout) instanceof MapFragmentNew) {
            super.onBackPressed();
        } else {
            mapTab.select();
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }


    }


}
