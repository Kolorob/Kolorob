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

        checkBox = (CheckBox) findViewById(R.id.compare);

        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            education = (EduNewModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_EDU);
        }

        viewBaseLayout(education);
        displayUniqueProperties();
        displayCommonProperties(education);

    }



    public void displayUniqueProperties(){

        EduNewDBTableTraining trainingDB = new EduNewDBTableTraining(context);
        EduNewDBTableSchool schoolDB = new EduNewDBTableSchool(context);
        EducationResultDetailsTable resultDB = new EducationResultDetailsTable(context);

        CheckConcate(getString(R.string.institution_type), education.getEducationType());
        if(!education.getEducationType().equals(getReferences(education))){
            CheckConcate(getString(R.string.speciality), getReferences(education));
        }
        CheckConcate(getString(R.string.shift), education.getShift());

        if(!education.getStudentNo().equals("null")) CheckConcate(getString(R.string.num_of_students), English_to_bengali_number_conversion(education.getStudentNo()) + " " + getString(R.string.count_people));
        if(!education.getTeachersNo().equals("null")) CheckConcate(getString(R.string.num_of_teachers),  English_to_bengali_number_conversion(education.getTeachersNo()) + " " + getString(R.string.count_people));
        if(!education.getAverageStudentPerClass().equals("null")) CheckConcate(getString(R.string.avg_students),  English_to_bengali_number_conversion(education.getAverageStudentPerClass()) + " " + getString(R.string.count_people));
        CheckConcate(getString(R.string.facilities), education.getFacility());


        schools = schoolDB.getDataListFromForeignKey(education.getId());
        int schoolSize = schools.size();

        if (schoolSize != 0) {

            for (EduNewSchoolModel school : schools) {

                CheckConcate(getString(R.string.scholarship), school.getStipend());
                CheckConcate(getString(R.string.primary_fee), English_to_bengali_number_conversion(school.getPrimary_fees()));
                CheckConcate(getString(R.string.secondary_fee), English_to_bengali_number_conversion(school.getSecondary_fees()));
                CheckConcate(getString(R.string.college_fee), English_to_bengali_number_conversion(school.getCollege_fees()));
            }
        }

        results = resultDB.getDataListFromForeignKey(education.getId());

        if (results.size() != 0) {
            for (EducationResultItemNew result : results)  {
                //String label = String.format("%s %s", result.getExamname(), getString(R.string.num_examinee));

                CheckConcate(result.getExamname(), " ");
                CheckConcate(getString(R.string.num_examinee), English_to_bengali_number_conversion(result.getStudentno()));
                CheckConcate(getString(R.string.passed), English_to_bengali_number_conversion(result.getPassed()));
                CheckConcate(getString(R.string.gpa_5), English_to_bengali_number_conversion(result.getAplus()));
                CheckConcate(getString(R.string.golden_5), English_to_bengali_number_conversion(result.getGoldena()));
            }
        }

        trainings = trainingDB.getDataListFromForeignKey(education.getId());

        if (trainings.size() != 0) {
            for (EduTrainingModel training : trainings) {

                CheckConcate(getString(R.string.course_duration), English_to_bengali_number_conversion(training.getCourseduration()));
                CheckConcate(getString(R.string.training_name), training.getTrainingname());
                if(!training.getCost().equals("null")) CheckConcate(getString(R.string.cost), English_to_bengali_number_conversion(training.getCost()) + " " + getString(R.string.taka));
                CheckConcate(getString(R.string.course_name), training.getCoursename());

            }
        }

        compareValue = SharedPreferencesHelper.getComapreValueEdu(DetailsLayoutEducation.this); //check function
        previous_node = SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this); //check function
        String multipule[] = previous_node.split(",");

        if(compareValue == 1 && previous_node.equals(String.valueOf(education.getId()))) {
            checkBox.setChecked(true);
        }

        else if(compareValue == 2 && (multipule[0].equals(String.valueOf(education.getId())) || multipule[1].equals(String.valueOf(education.getId())))) {
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
                        compare_Data = multipule[1] + "," + String.valueOf(education.getId());
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
                        SharedPreferencesHelper.setComapareEdu(context, String.valueOf(education.getId()), 1);

                } else if (compareValue == 1) {

                    if (isChecked) {

                        String previous_node;
                        previous_node = SharedPreferencesHelper.getComapreData(context);
                        previous_node = previous_node + "," + String.valueOf(education.getId());
                        SharedPreferencesHelper.setComapareEdu(context, previous_node, 2);

                    } else {

                        SharedPreferencesHelper.setComapareEdu(context, "", 0);

                    }

                }
            }

        });

    }


}

