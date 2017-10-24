package demo.kolorob.kolorobdemoversion.activity.DetailsActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableHospital;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTablePharma;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelHospital;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelPharmacy;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;


/**
 * Created by arafat on 28/05/2016.
 */


public class DetailsInfoActivityHealthNew extends BaseActivity {

    HealthNewDBModelMain health;
    ArrayList <HealthNewDBModelPharmacy> pharmacies;
    ArrayList <HealthNewDBModelHospital> hospitals;
    private CheckBox checkBox = null;
    private String compare_Data = "";
    int compareValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout_comparison_tool);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            health = (HealthNewDBModelMain) intent.getSerializableExtra(AppConstants.KEY_DETAILS_HEALTH_NEW);
        }

        viewBaseLayout(health);
        displayUniqueProperties();
        displayCommonProperties(health);
        //compare();
    }


    public void displayUniqueProperties(){


        final HealthNewDBTableHospital hospitalDB = new HealthNewDBTableHospital(context);

        CheckConcate(getString(R.string.institution_type),  health.getInstituteType());

        if(!health.getInstituteType().equals(getReferences(health))){
            CheckConcate(getString(R.string.speciality), getReferences(health));
        }

        hospitals = hospitalDB.getDataListFromForeignKey(health.getId());
        int hospitalSize = hospitals.size();

        if (hospitalSize != 0) {

            for (HealthNewDBModelHospital hospital : hospitals) {

                if(hospital.getEmergencyavailable().equals(false)) {
                    CheckConcate(getString(R.string.emergency_service), getString(R.string.not_available));
                }

                else CheckConcate(getString(R.string.emergency_service), getString(R.string.available));

                CheckConcate(getString(R.string.emergency_number), English_to_bengali_number_conversion(hospital.getEmergencynumber()));

                if(hospital.getAmbulanceavailable().equals(false)) {
                    CheckConcate(getString(R.string.ambulance_service), getString(R.string.not_available));
                }

                else CheckConcate(getString(R.string.ambulance_service), getString(R.string.available));


                CheckConcate(getString(R.string.ambulance_number), English_to_bengali_number_conversion(hospital.getAmbulancenumber()));

                if(hospital.getMaternityavailable().equals(false)) {
                    CheckConcate(getString(R.string.maternity_service), getString(R.string.not_available));
                }

                else CheckConcate(getString(R.string.maternity_service), getString(R.string.available));

                CheckConcate(getString(R.string.maternity_services), hospital.getMaternitynumber());
                CheckConcate(getString(R.string.maternity_privacy), hospital.getMaternityprivacy());

            }
        }


        HealthNewDBTablePharma pharmacyDB = new HealthNewDBTablePharma(context);
        pharmacies = pharmacyDB.getDataListFromForeignKey(health.getId());

        int pharmacySize = pharmacies.size();

        if (pharmacySize != 0) {
            for (HealthNewDBModelPharmacy pharmacy : pharmacies) {

                if(pharmacy.getDocavailability().equals("false")) {
                    CheckConcate(getString(R.string.doctor_availability), getString(R.string.not_available));
                }
                else CheckConcate(getString(R.string.doctor_availability), getString(R.string.available));

                CheckConcate(getString(R.string.speciality), pharmacy.getSpeciality());

                if(pharmacy.getVaccineavailability().equals("false")) {
                    CheckConcate(getString(R.string.vaccine_facility), getString(R.string.not_available));
                }
                else CheckConcate(getString(R.string.vaccine_facility), getString(R.string.available));

            }
        }
    }


    public void compare(){

        compare_Data = SharedPreferencesHelper.getComapreDataHealth(context);
        compareValue = SharedPreferencesHelper.getComapreValueHealth(context);

        String multipule[] = compare_Data.split(",");

        if(compareValue == 1 && compare_Data.equals(health.getId())) {
            checkBox.setChecked(true);
        }

        else if(compareValue == 2 && (multipule[0].equals(health.getId()) || multipule[1].equals(health.getId()))) {
            checkBox.setChecked(true);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            compareValue = SharedPreferencesHelper.getComapreValueHealth(DetailsInfoActivityHealthNew.this);

            if (compareValue >= 2) {

                if(isChecked) {

                    String new_compare_Data = "";
                    compare_Data = SharedPreferencesHelper.getComapreDataHealth(context);
                    String multipule[] = compare_Data.split(",");
                    new_compare_Data = multipule[1] + "," + health.getId();
                    SharedPreferencesHelper.setCompareDataHealth(context, new_compare_Data, 2);
                }
                else {
                    String compare_Data = "";
                    String new_compare_Data="";
                    compare_Data = SharedPreferencesHelper.getComapreDataHealth(context);
                    String multipule[] = compare_Data.split(",");
                    new_compare_Data = multipule[0];
                    SharedPreferencesHelper.setCompareDataHealth(context, new_compare_Data, 1);
                }

            }

            else if (compareValue == 0) {
                if(isChecked)
                    SharedPreferencesHelper.setCompareDataHealth(DetailsInfoActivityHealthNew.this, String.valueOf(health.getId()), 1);

            }
            else if (compareValue == 1) {

                if(isChecked) {

                    String previous_node;
                    previous_node = SharedPreferencesHelper.getComapreDataHealth(context);
                    previous_node = previous_node + "," + health.getId();
                    SharedPreferencesHelper.setCompareDataHealth(context, previous_node, 2);

                }
                else {
                    SharedPreferencesHelper.setCompareDataHealth(context, "", 0);
                }

            }


            }
        });

    }

}