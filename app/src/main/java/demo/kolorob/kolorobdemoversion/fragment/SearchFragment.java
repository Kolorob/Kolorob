package demo.kolorob.kolorobdemoversion.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Locale;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.PlaceDetailsActivity;
import demo.kolorob.kolorobdemoversion.adapters.Subcatholder;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;

public class SearchFragment extends CommonFragment {


    View rootView;
    LinearLayout filterLayout;
    RelativeLayout searchViewHolder, categoryFilterHolder, filterHolder;
    ListView allItemList;
    private ArrayList <Subcatholder> tagHolders = new ArrayList<>();
    ArrayList<String> filterLeftList = new ArrayList<>();
    ArrayList<String> filterRightList = new ArrayList<>();
    private EditText searchAll;
    private ImageButton filterButton;
    private LinearLayout filterLeft, filterRight;
    private RadioGroup leftGroup;
    private RadioGroup rightGroup;
    private String filterWord;
    private int sNumber;
    private EditText filterText;


    public SearchFragment() {
       // setLayoutId(R.layout.fragment_search);
    }

    public void setFilterWord(String filterWord) {
        this.filterWord = filterWord;
    }
    public void setsNumber(int sNumber) {
        this.sNumber = sNumber;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // search

        super.onCreate(savedInstanceState);
        SharedPreferences settings = SearchFragment.this.getActivity().getSharedPreferences("prefs", 0);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);



        filterLayout = (LinearLayout) rootView.findViewById(R.id.LinearLayoutfilter);
        searchViewHolder = (RelativeLayout) rootView.findViewById(R.id.searchholder);
        searchViewHolder.setVisibility(View.VISIBLE);
        allItemList = (ListView) rootView.findViewById(R.id.allitem);
        categoryFilterHolder = (RelativeLayout) rootView.findViewById(R.id.categoryfilterholder);
        searchAll = (EditText) rootView.findViewById(R.id.searchall);

        //String lat = ((PlaceDetailsActivity)getActivity()).getLat();  //  how to access Activity variable from fragment


        filterHolder = (RelativeLayout) rootView.findViewById(R.id.filterholder);
        filterButton = (ImageButton) rootView.findViewById(R.id.filterButton);

        filterLeft = (LinearLayout) rootView.findViewById(R.id.filterLayoutLeft);
        filterRight = (LinearLayout) rootView.findViewById(R.id.filterLayoutRight);

        filterText = (EditText) rootView.findViewById(R.id.searchall);



        ((PlaceDetailsActivity)getActivity()).populateHolder();

        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = filterText.getText().toString().toLowerCase(Locale.getDefault());

                ((PlaceDetailsActivity)getActivity()).getAdapter().filter(text);
                //calladapter(true);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }

        });



        return rootView;


    }

    public void populateSearch() {

        //filterText.setTextColor(getResources().getColor(R.color.white));
        categoryFilterHolder.setVisibility(View.VISIBLE);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterLayout.setVisibility(View.VISIBLE);
                populateFilterWords(((PlaceDetailsActivity)getActivity()).currentCategoryID);

            }
        });

    }


    /*
    * this populate filter keywords based on category id from db
    */
    public void populateFilterWords(int filcatid) {
        SubCategoryTableNew subCategoryTable = new SubCategoryTableNew(getActivity());
        tagHolders.clear();
        tagHolders = subCategoryTable.getcatSubCategories(filcatid);

        int upto = tagHolders.size() / 2;
        filterLeftList.clear();
        filterRightList.clear();
        filterLeft.removeAllViews();
        filterRight.removeAllViews();

        for (int f = 0; f < tagHolders.size(); f++) {
            if (f >= upto)
                filterRightList.add(tagHolders.get(f).getName_bn());
            else
                filterLeftList.add(tagHolders.get(f).getName_bn());
        }

        final RadioButton[] rb = new RadioButton[30];
        leftGroup = new RadioGroup(getActivity()); //create the RadioGroup
        leftGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

        for (int i = 0; i < filterLeftList.size(); i++) {
            rb[i] = new RadioButton(getActivity());
            leftGroup.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filterLeftList.get(i).toString());
            rb[i].setTextColor(Color.WHITE);
        }

        rightGroup = new RadioGroup(getActivity()); //create the RadioGroup  // previously, it was "this"
        rightGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for (int i = 0; i < filterRightList.size(); i++) {
            rb[i] = new RadioButton(getActivity());
            rightGroup.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(filterRightList.get(i));
            rb[i].setTextColor(Color.WHITE);

        }

        filterLeft.addView(leftGroup);
        filterRight.addView(rightGroup);//you add the w

        leftGroup.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        rightGroup.clearCheck();
        leftGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId != -1) {
                    fun2();
                }
            }
        });

        rightGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId != -1) {
                    fun1();
                }
            }
        });
    }

    /*fun1 and fun2 is for selecting only one item from two radiogroup to get the filter id.*/

    public void fun1() {
        rightGroup.setOnCheckedChangeListener(null);
        rightGroup.clearCheck();
        rightGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fun2();
                RadioButton radioButton = (RadioButton) rootView.findViewById(checkedId);
                setFilterWord((String) radioButton.getText());
                int num = Findsubcatid(filterWord);
                ((PlaceDetailsActivity)getActivity()).callAdapter(true);

                Log.v("Inside fun1", String.valueOf(num));
            }
        });
    }


    public void fun2() {
        leftGroup.setOnCheckedChangeListener(null);
        leftGroup.clearCheck();
        leftGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                fun1();
                RadioButton radioButton = (RadioButton) rootView.findViewById(checkedId);
                setFilterWord((String) radioButton.getText());
                int num = Findsubcatid(filterWord);
                ((PlaceDetailsActivity)getActivity()).callAdapter(true);

                Log.v("Inside fun2", String.valueOf(num));

            }
        });
    }

    private int Findsubcatid(String filterword) {

        for (int s = 0; s < tagHolders.size(); s++) {
            if (tagHolders.get(s).getName_bn().equals(filterword)) {
                setsNumber(tagHolders.get(s).getId());
                break;
            }
        }

        return sNumber;
    }


}
