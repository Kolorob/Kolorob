package demo.kolorob.kolorobdemoversion.activity.DetailsActivities;

import android.content.Intent;
import android.os.Bundle;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by israt.jahan on 7/17/2016.
 */

public class DetailsLayoutFinance extends BaseActivity {

    FinancialNewDBModel finance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            finance = (FinancialNewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_FINANCIALNEW);
        }

        viewBaseLayout(finance);
        displayUniqueProperties();
        displayCommonProperties(finance);
    }

    public void displayUniqueProperties(){

        CheckConcate(getString(R.string.institution_type),  finance.getFinType());
        if(!finance.getFinType().equals(getReferences(finance))){
            CheckConcate(getString(R.string.speciality), getReferences(finance));
        }
        CheckConcate(getString(R.string.service_type), finance.getServiceType());
    }

}