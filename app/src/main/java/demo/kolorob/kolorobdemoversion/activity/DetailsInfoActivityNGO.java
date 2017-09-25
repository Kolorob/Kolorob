package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by zahidul.islam
 */

public class DetailsInfoActivityNGO extends BaseActivity <NGONewDBModel> {

    NGONewDBModel ngo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_ngo);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            ngo = (NGONewDBModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_NGO);
        }

        viewBaseLayout(ngo.getCommonModel());
        displayUniqueProperties();
        displayCommonProperties(ngo.getCommonModel());
    }


    public void displayUniqueProperties(){
        
        String services = ngo.getNgoServices();

        if(services != null) {
            String[] subServices = new String[20];
            if(services.contains(",")){
                subServices = services.split(",");
            }
            if(subServices.length <= 3 || !services.contains(","))
                CheckConcate("প্রতিষ্ঠানের ধরণ", ngo.getNgoServices());
            else
                CheckConcate("প্রতিষ্ঠানের ধরণ", ngo.getNgoServiceType());

        }

        CheckConcate("সেবাসমূহ", ngo.getNgoServices());
        CheckConcate("যাদের জন্য প্রযোজ্য", ngo.getNgoServicesFor());
        CheckConcate("অন্যান্য সেবা", ngo.getNgoServicesOther());
        CheckConcate("অবস্থানের সময়", ngo.getDropTime());
        CheckConcate("ফি", ngo.getNgoFee());

    }
}




