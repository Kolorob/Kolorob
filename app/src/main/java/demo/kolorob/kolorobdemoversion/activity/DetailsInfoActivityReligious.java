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

        viewBaseLayout(shelter.getCommonModel());
        displayUniqueProperties();
        displayCommonProperties(shelter.getCommonModel());

    }

    public void displayUniqueProperties(){
        CheckConcate("ধর্ম", shelter.getRsReligion());
        CheckConcate("যাদের জন্য প্রযোজ্য", shelter.getRsServicesFor());
        CheckConcate("যেসব ধর্মের জন্য প্রযোজ্য", shelter.getRsServicesForReligion());
        CheckConcate("অন্যান্য ধর্ম", shelter.getOtherReligion());
        CheckConcate("অবস্থানের সময়সীমা", shelter.getRsTime());
        CheckConcate("ফি", shelter.getRsFee());
    }
}