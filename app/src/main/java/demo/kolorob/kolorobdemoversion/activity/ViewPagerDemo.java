package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.TestFragmentAdapter;

public class ViewPagerDemo extends FragmentActivity {

    TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    int data=0;
    int YourtransferredData;
    Button close;
    int Psition,Ps2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtutorial);
        Intent i1 = getIntent();
        if (i1 != null) {
           data = i1.getIntExtra("YourValueKey",0);
        }

        close=(Button)findViewById(R.id.button_next);
        close.setVisibility(View.GONE);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data==2) {
                    Intent a = new Intent(ViewPagerDemo.this, PlaceSelectionActivity.class); // Default Activity
                    startActivity(a);
                    finish();
                }
                else   if(data==0) {
                    Intent a = new Intent(ViewPagerDemo.this, PlaceSelectionActivity.class); // Default Activity
                    startActivity(a);
                    finish();
                }
                else if(data==1){
                    Intent a = new Intent(ViewPagerDemo.this, PlaceDetailsActivityNewLayout.class); // Default Activity
                    startActivity(a);
                    finish();
                }
            }
        });
        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator = indicator;
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0xFF303434);
        indicator.setRadius(10 * density);
        indicator.setPageColor(0xFFFFFFFF);
        indicator.setFillColor(0xFFFF8040);
        indicator.setStrokeColor(0xFF000000);
        indicator.setStrokeWidth(2 * density);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("p",String.valueOf(position));
                Ps2=position;
                int k= mPager.getAdapter().getCount();


            }

            @Override
            public void onPageSelected(int position)
            {
                Log.d("p2",String.valueOf(position));
                int k= mPager.getAdapter().getCount();
                Psition=position;
                if(position==mPager.getAdapter().getCount()-1 &&data==0){
                  close.setVisibility(View.VISIBLE);
                }
                else  close.setVisibility(View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("p3",String.valueOf(state));
               /* if(Psition==mPager.getAdapter().getCount()-1&&state==0&&Ps2==Psition){
                    Toast.makeText(ViewPagerDemo.this, "hello", Toast.LENGTH_SHORT).show();
                }*/
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (data == 0) {

        }
        else
        super.onBackPressed();
// dont call **super**, if u want disable back button in current screen.


    }
}