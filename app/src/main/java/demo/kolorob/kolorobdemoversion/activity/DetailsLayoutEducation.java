package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;


/**
 * Created by israt.jahan on 7/17/2016.
 */


public class DetailsLayoutEducation extends BaseActivity {


    EduNewModel education;

    int compareValue;
    String previous_node;
    ArrayList <EduTrainingModel> trainings;
    ArrayList <EduNewSchoolModel> schools;
    ArrayList <EducationResultItemNew> results;

    private CheckBox checkBox = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_layout_comparison_tool);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            education = (EduNewModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_EDU);
        }

        viewBaseLayout(education.getCommonModel());
        displayUniqueProperties();
        displayCommonProperties(education.getCommonModel());
      //  compare();
    }



    public void displayUniqueProperties(){

        EduNewDBTableTraining trainingDB = new EduNewDBTableTraining(context);
        EduNewDBTableSchool schoolDB = new EduNewDBTableSchool(context);
        EducationResultDetailsTable resultDB = new EducationResultDetailsTable(context);

        CheckConcate("প্রতিষ্ঠানের ধরণ ", education.getEducationType());
        if(!education.getEducationType().equals(getReferences(education.getCommonModel()))){
            CheckConcate("বিশেষত্ব", getReferences(education.getCommonModel()));
        }
        CheckConcate("শাখা", education.getShift());

        if(!education.getStudentNo().equals("null")) CheckConcate("ছাত্রছাত্রী সংখ্যা", English_to_bengali_number_conversion(education.getStudentNo()) + " জন");
        if(!education.getTeachersNo().equals("null")) CheckConcate("শিক্ষক সংখ্যা",  English_to_bengali_number_conversion(education.getTeachersNo()) + " জন");
        if(!education.getAverageStudentPerClass().equals("null")) CheckConcate("ছাত্রছাত্রী সংখ্যা (গড়)",  English_to_bengali_number_conversion(education.getAverageStudentPerClass()) + " জন");
        CheckConcate("সুযোগ সুবিধা", education.getFacility());


        schools = schoolDB.getDataFromForeignKey(education.getCommonModel().getId());
        int schoolSize = schools.size();

        if (schoolSize != 0) {

            for (EduNewSchoolModel school : schools) {

                CheckConcate("বৃত্তি সুবিধা", school.getStipend());
                CheckConcate("প্রাইমারী লেভেলের বেতন (বার্ষিক) ", English_to_bengali_number_conversion(school.getPrimary_fees()));
                CheckConcate("সেকেন্ডারি লেভেলের বেতন (বার্ষিক) ", English_to_bengali_number_conversion(school.getSecondary_fees()));
                CheckConcate("কলেজের বেতন (বার্ষিক) ", English_to_bengali_number_conversion(school.getCollege_fees()));
            }
        }

        results = resultDB.getDataFromForeignKey(education.getCommonModel().getId());
        int resultSize = results.size();
        if (resultSize != 0) {
            for (EducationResultItemNew result : results)  {
                CheckConcate(result.getExamname() + " পরীক্ষায় অংশগ্রহণকারী শিক্ষার্থীর সংখ্যা", English_to_bengali_number_conversion(result.getStudentno()));
                CheckConcate("উত্তীর্ণ শিক্ষার্থীর সংখ্যা", English_to_bengali_number_conversion(result.getPassed()));
                CheckConcate("জিপিএ ৫ এর সংখ্যা", English_to_bengali_number_conversion(result.getAplus()));
                CheckConcate("গোল্ডেন জিপিএ ৫ এর সংখ্যা", English_to_bengali_number_conversion(result.getGoldena()));
            }
        }

        trainings = trainingDB.getDataFromForeignKey(education.getCommonModel().getId());
        int trainingSize = trainings.size();
        if (trainingSize != 0) {
            for (EduTrainingModel training : trainings) {

                CheckConcate("কোর্সের ব্যাপ্তিকাল (মাস) ", English_to_bengali_number_conversion(training.getCourseduration()));
                CheckConcate("ট্রেইনিং এর নাম ", training.getTrainingname());
                if(!training.getCost().equals("null")) CheckConcate("খরচ", training.getCost()+" টাকা");
                CheckConcate("কোর্সের নাম", training.getCoursename());

            }
        }

    }


    public void compare(){
        compareValue = SharedPreferencesHelper.getComapreValueEdu(DetailsLayoutEducation.this); //check function
        previous_node = SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this); //check function
        String multipule[] = previous_node.split(",");

        if(compareValue == 1 && previous_node.equals(String.valueOf(education.getCommonModel().getId()))) {
            checkBox.setChecked(true);
        }

        else if(compareValue == 2 && (multipule[0].equals(String.valueOf(education.getCommonModel().getId())) || multipule[1].equals(String.valueOf(education.getCommonModel().getId())))) {
            checkBox.setChecked(true);
        }


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                compareValue = SharedPreferencesHelper.getComapreValueEdu(DetailsLayoutEducation.this);

                if (compareValue >= 2) {
                    if (isChecked) {

                        String compare_Data = "";
                        compare_Data = SharedPreferencesHelper.getComapreData(context);
                        String multipule[] = compare_Data.split(",");
                        compare_Data = multipule[1] + "," + String.valueOf(education.getCommonModel().getId());
                        SharedPreferencesHelper.setComapareEdu(context, compare_Data, 2);
                    } else {
                        String compare_Data = "";
                        String new_compare_Data = "";
                        compare_Data = SharedPreferencesHelper.getComapreData(context);
                        String multipule[] = compare_Data.split(",");
                        new_compare_Data = multipule[0];
                        SharedPreferencesHelper.setComapareEdu(context, new_compare_Data, 1);
                    }

                } else if (compareValue == 0) {
                    if (isChecked)
                        SharedPreferencesHelper.setComapareEdu(context, String.valueOf(education.getCommonModel().getId()), 1);

                } else if (compareValue == 1) {

                    if (isChecked) {

                        String previous_node;
                        previous_node = SharedPreferencesHelper.getComapreData(context);
                        previous_node = previous_node + "," + String.valueOf(education.getCommonModel().getId());
                        SharedPreferencesHelper.setComapareEdu(context, previous_node, 2);

                    } else {

                        SharedPreferencesHelper.setComapareEdu(context, "", 0);

                    }

                }
            }

        });

    }


}
