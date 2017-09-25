package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.Comment_layout_adapter;
import demo.kolorob.kolorobdemoversion.adapters.DefaultAdapter;
import demo.kolorob.kolorobdemoversion.database.CommentTable;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableSchool;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableTraining;
import demo.kolorob.kolorobdemoversion.database.Education.EducationResultDetailsTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTableNew;
import demo.kolorob.kolorobdemoversion.fragment.MapFragmentRouteOSM;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.CommentItem;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewSchoolModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduTrainingModel;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EducationResultItemNew;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItemNew;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

import static demo.kolorob.kolorobdemoversion.R.raw.health;

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
        setContentView(R.layout.activity_details_layout_education);
        context = this;
        Intent intent = getIntent();
        if (null != intent) {
            education = (EduNewModel) intent.getSerializableExtra(AppConstants.KEY_DETAILS_EDU);
        }

        viewBaseLayout(education.getCommonModel());
        displayUniqueProperties();
        displayCommonProperties(education.getCommonModel());
        compare();
    }



        EduNewDBTableTraining trainingDB = new EduNewDBTableTraining(DetailsLayoutEducation.this);
        EduNewDBTableSchool schoolDB = new EduNewDBTableSchool(DetailsLayoutEducation.this);
        EducationResultDetailsTable resultDB = new EducationResultDetailsTable(DetailsLayoutEducation.this);






        CheckConcate("প্রতিষ্ঠানের ধরণ ", education.getEdtype());
        if(!educationNewItem.get(0).getEdtype().equals(getReferences(educationNewItem.get(0)))){
            CheckConcate("বিশেষত্ব", getReferences(educationNewItem.get(0)));
        }
        CheckConcate("শাখা", educationNewItem.get(0).getShift());

        if(!educationNewItem.get(0).getStudentno().equals("null")) CheckConcate("ছাত্রছাত্রী সংখ্যা", EtoB(educationNewItem.get(0).getStudentno())+" জন");
        if(!educationNewItem.get(0).getTeachersno().equals("null")) CheckConcate("শিক্ষক সংখ্যা",  EtoB(educationNewItem.get(0).getTeachersno())+" জন");
        if(!educationNewItem.get(0).getAveragestdperclass().equals("null")) CheckConcate("ছাত্রছাত্রী সংখ্যা (গড়)",  EtoB(educationNewItem.get(0).getAveragestdperclass())+" জন");
        CheckConcate("সুযোগ সুবিধা",  educationNewItem.get(0).getFacility());





        eduNewSchoolModels = eduNewDBTableSchool.getschoolInfo(educationNewItem.get(0).getEduId());
        int schoolsize = eduNewSchoolModels.size();
        if (schoolsize != 0) {
            for (EduNewSchoolModel eduNewSchoolModel : eduNewSchoolModels) {


                CheckConcate("বৃত্তি সুবিধা", eduNewSchoolModel.getStipend());
                CheckConcate("প্রাইমারী লেভেলের বেতন (বার্ষিক) ", formatPayment(eduNewSchoolModel.getPrimary_fees()));
                CheckConcate("সেকেন্ডারি লেভেলের বেতন (বার্ষিক) ", formatPayment(eduNewSchoolModel.getSecondary_fees()));
                CheckConcate("কলেজের বেতন (বার্ষিক) ", formatPayment(eduNewSchoolModel.getCollage_fees()));
            }
        }

        educationResultItemNew = educationResultDetailsTable.getResultInfo(educationNewItem.get(0).getEduId());
        int resultSize = eduNewSchoolModels.size();
        if (resultSize != 0) {
            for (EducationResultItemNew eduResult : educationResultItemNew)  {
                CheckConcate(eduResult.getExamname() + " পরীক্ষায় অংশগ্রহণকারী শিক্ষার্থীর সংখ্যা", EtoB(eduResult.getStudentno()));
                CheckConcate("উত্তীর্ণ শিক্ষার্থীর সংখ্যা", EtoB(eduResult.getPassed()));
                CheckConcate("জিপিএ ৫ এর সংখ্যা", EtoB(eduResult.getAplus()));
                CheckConcate("গোল্ডেন জিপিএ ৫ এর সংখ্যা", EtoB(eduResult.getGoldena()));
            }
        }
        educationTrainingDetailsItems = eduNewDBTableTraining.gettrainingInfo(educationNewItem.get(0).getEduId());
        int training_size = educationTrainingDetailsItems.size();
        if (training_size != 0) {
            for (EduTrainingModel eduTrainingModel : educationTrainingDetailsItems) {


                CheckConcate("কত মাসের কোর্স", EtoB(eduTrainingModel.getCourseduration()));
                CheckConcate("ট্রেইনিং এর নাম ", eduTrainingModel.getTrainingname());
                CheckConcate("খরচ", eduTrainingModel.getCost()+" টাকা");
                CheckConcate("কোর্সের নাম", eduTrainingModel.getCoursename());



            }
        }


        compareValue = SharedPreferencesHelper.getComapreValueEdu(DetailsLayoutEducation.this); //check function
        previous_node = SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this); //check function
        String multipule[]= previous_node.split(",");

        if(compareValue==1&&previous_node.equals(String.valueOf(educationNewItem.get(0).getEduId())))
        {

            checkBox.setChecked(true);
        }
        else if(compareValue==2&&(multipule[0].equals(String.valueOf(educationNewItem.get(0).getEduId()))||multipule[1].equals(String.valueOf(educationNewItem.get(0).getEduId()))))
        {

            checkBox.setChecked(true);
        }




        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String node = String.valueOf(educationNewItem.get(0).getEduId());
                compareValue = SharedPreferencesHelper.getComapreValueEdu(DetailsLayoutEducation.this);

                if (compareValue >= 2)
                {
                    if(isChecked)
                    {

                        String compare_Data="";
                        compare_Data=SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
                        String multipule[]= compare_Data.split(",");
                        compare_Data = multipule[1]+","+String.valueOf(educationNewItem.get(0).getEduId());
                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, compare_Data, 2);
                    }
                    else
                    {
                        String compare_Data="";
                        String new_compare_Data="";
                        compare_Data=SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
                        String multipule[]= compare_Data.split(",");
                        new_compare_Data = multipule[0];
                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, new_compare_Data, 1);
                    }

                }
                else if (compareValue == 0) {
                    if(isChecked)
                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, String.valueOf(educationNewItem.get(0).getEduId()), 1);

                }
                else if (compareValue == 1) {

                    if(isChecked)
                    {

                        String previous_node;
                        previous_node = SharedPreferencesHelper.getComapreData(DetailsLayoutEducation.this);
                        previous_node = previous_node + "," + String.valueOf(educationNewItem.get(0).getEduId());
                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this, previous_node, 2);

                    }
                    else
                    {

                        SharedPreferencesHelper.setComapareEdu(DetailsLayoutEducation.this,"",0);

                    }

                }






            }
        });




    private String formatPayment(String payment) {

        String payment_bn = "";

        if (payment != null) {
            for (int i = 0; i < payment.length(); i++) {
                switch (payment.charAt(i)) {
                    case '0':
                        payment_bn += "০";
                        break;
                    case '1':
                        payment_bn += "১";
                        break;
                    case '2':
                        payment_bn += "২";
                        break;
                    case '3':
                        payment_bn += "৩";
                        break;
                    case '4':
                        payment_bn += "৪";
                        break;
                    case '5':
                        payment_bn += "৫";
                        break;
                    case '6':
                        payment_bn += "৬";
                        break;
                    case '7':
                        payment_bn += "৭";
                        break;
                    case '8':
                        payment_bn += "৮";
                        break;
                    case '9':
                        payment_bn += "৯";
                        break;
                    case '.':
                        payment_bn += "।";
                        break;
                    case ',':
                        payment_bn += ",";
                        break;
                    case '-':
                        payment_bn += "-";
                        break;

                }
            }
        }
        return payment_bn;
    }
}
