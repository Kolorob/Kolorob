package demo.kolorob.kolorobdemoversion.activity;

import android.os.Bundle;
import demo.kolorob.kolorobdemoversion.R;
import android.content.Intent;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class DetailsInfoActivityReligious extends BaseActivity {

    ReligiousNewDBModel shelter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            shelter = (ReligiousNewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_RELIGIOUS);
        }

        viewBaseLayout(shelter);
        displayUniqueProperties();
        displayCommonProperties(shelter);

    }

    public void displayUniqueProperties(){
        CheckConcate(getString(R.string.religion), shelter.getRsReligion());
        CheckConcate(getString(R.string.services_for), shelter.getRsServicesFor());
        CheckConcate(getString(R.string.services_for_religion), shelter.getRsServicesForReligion());
        CheckConcate(getString(R.string.other_religions), shelter.getOtherReligion());
        CheckConcate(getString(R.string.drop_time_limit), shelter.getRsTime());
        CheckConcate(getString(R.string.fee), shelter.getRsFee());
    }
}