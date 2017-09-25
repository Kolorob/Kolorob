package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


/**
 * Created by arafat on 28/05/2016.
 */

public class DetailsInfoActivityLegalNew extends BaseActivity <LegalAidNewDBModel> {

    LegalAidNewDBModel legal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_legal_new);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            legal = (LegalAidNewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_LEGAL);
        }

        viewBaseLayout(legal.getCommonModel());
        displayUniqueProperties();
        displayCommonProperties(legal.getCommonModel());
    }


    public void displayUniqueProperties(){
        CheckConcate("প্রতিষ্ঠানের ধরণ",  legal.getService());
        if(!legal.getService().equals(getReferences(legal.getCommonModel()))){
            CheckConcate("বিশেষত্ব", getReferences(legal.getCommonModel()));
        }
    }
}