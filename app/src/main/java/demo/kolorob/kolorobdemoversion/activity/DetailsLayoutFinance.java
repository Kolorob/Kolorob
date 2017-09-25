package demo.kolorob.kolorobdemoversion.activity;

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
        setContentView(R.layout.activity_details_layout_finance);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            finance = (FinancialNewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_FINANCIALNEW);
        }

        viewBaseLayout(finance.getCommonModel());
        displayUniqueProperties();
        displayCommonProperties(finance.getCommonModel());
    }

    public void displayUniqueProperties(){

        CheckConcate("প্রতিষ্ঠানের  ধরণ",  finance.getFinType());
        if(!finance.getFinType().equals(getReferences(finance.getCommonModel()))){
            CheckConcate("বিশেষত্ব", getReferences(finance.getCommonModel()));
        }
        CheckConcate("সুবিধার ধরণ",  finance.getServiceType());
    }

}