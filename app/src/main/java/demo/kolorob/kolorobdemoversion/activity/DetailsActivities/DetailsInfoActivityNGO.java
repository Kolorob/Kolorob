package demo.kolorob.kolorobdemoversion.activity.DetailsActivities;

import android.content.Intent;
import android.os.Bundle;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by zahidul.islam
 */

public class DetailsInfoActivityNGO extends BaseActivity {

    NGONewDBModel ngo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            ngo = (NGONewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_NGO);
        }

        viewBaseLayout(ngo);
        displayUniqueProperties();
        displayCommonProperties(ngo);
    }


    public void displayUniqueProperties(){

        String services = ngo.getNgoServices();

        if(services != null) {
            String[] subServices = new String[20];
            if(services.contains(",")){
                subServices = services.split(",");
            }
            if(subServices.length <= 3 || !services.contains(","))
                CheckConcate(getString(R.string.institution_type), ngo.getNgoServices());
            else
                CheckConcate(getString(R.string.institution_type), ngo.getNgoServiceType());

        }

        CheckConcate(getString(R.string.services), ngo.getNgoServices());
        CheckConcate(getString(R.string.services_for), ngo.getNgoServicesFor());
        CheckConcate(getString(R.string.other_services), ngo.getNgoServicesOther());
        CheckConcate(getString(R.string.drop_time), ngo.getDropTime());
        CheckConcate(getString(R.string.fee), ngo.getNgoFee());

    }
}




