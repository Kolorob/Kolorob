package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by israt.jahan on 7/17/2016.
 */

public class DetailsLayoutGovernment extends BaseActivity {

    GovernmentNewDBModel government;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            government = (GovernmentNewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_GOV);
        }

        viewBaseLayout(government.getCommonModel());
        displayUniqueProperties();
        displayCommonProperties(government.getCommonModel());

    }

    public void displayUniqueProperties(){
        CheckConcate("সুবিধার ধরণ", government.getServiceName());
        if(!government.getServiceName().equals(getReferences(government.getCommonModel()))){
            CheckConcate("বিশেষত্ব", getReferences(government.getCommonModel()));
        }
    }
}