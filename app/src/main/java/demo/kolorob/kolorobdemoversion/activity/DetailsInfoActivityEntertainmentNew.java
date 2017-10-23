package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by arafat on 28/05/2016.
 */

public class DetailsInfoActivityEntertainmentNew extends BaseActivity{

    EntertainmentNewDBModel entertainment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            entertainment = (EntertainmentNewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_ENT);
        }

        viewBaseLayout(entertainment);
        displayUniqueProperties();
        displayCommonProperties(entertainment);
    }

    public void displayUniqueProperties(){

        CheckConcate(getString(R.string.institution_type), entertainment.getEntType());
        if(!entertainment.getEntType().equals(getReferences(entertainment))){
            CheckConcate(getString(R.string.speciality), getReferences(entertainment));
        }
        if(entertainment.getEntryFee().equals(true)) CheckConcate(getString(R.string.entry_fee),  getString(R.string.applicable));

    }
}