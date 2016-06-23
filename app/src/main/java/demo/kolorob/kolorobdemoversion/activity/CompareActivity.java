/**
 * Created by arafat on 16/06/2016.
 */
package demo.kolorob.kolorobdemoversion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.ArrayList;
import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.helpers.AlertMessage;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

public class CompareActivity extends AppCompatActivity {
    private String comapreData;
    String firstData="",SecondData="";
    int checker=0;
    EducationServiceProviderTable educationServiceProviderTable;
    ArrayList<EducationServiceProviderItem> firstDataSet;
    ArrayList<EducationServiceProviderItem> secondDataSet;
    TextView edu_name_ban,edtype,hostel_facility,transport_facility,playground,total_students,total_classes,total_teachers,course_provided,shift,canteen_facility;
    TextView edu_name_ban1,edtype1,hostel_facility1,transport_facility1,playground1,total_students1,total_classes1,total_teachers1,course_provided1,shift1,canteen_facility1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        edu_name_ban=(TextView)findViewById(R.id.edu_name_ban2);
        edtype=(TextView)findViewById(R.id.eduType2);
        hostel_facility=(TextView)findViewById(R.id.hostel_facility2);
        transport_facility=(TextView)findViewById(R.id.transport_facility2);
        playground=(TextView)findViewById(R.id.playground2);
        total_students=(TextView)findViewById(R.id.ttl_students);
        total_classes=(TextView)findViewById(R.id.total_classes2);
        total_teachers=(TextView)findViewById(R.id.total_teachers2);
        course_provided=(TextView)findViewById(R.id.course_provided2);
        shift=(TextView)findViewById(R.id.shift2);
        canteen_facility=(TextView)findViewById(R.id.canteen_facility2);

        edu_name_ban1=(TextView)findViewById(R.id.edu_name_ban3);
        edtype1=(TextView)findViewById(R.id.eduType3);
        hostel_facility1=(TextView)findViewById(R.id.hostel_facility3);
        transport_facility1=(TextView)findViewById(R.id.transport_facility3);
        playground1=(TextView)findViewById(R.id.playground3);
        total_students1=(TextView)findViewById(R.id.total_students3);
        total_classes1=(TextView)findViewById(R.id.total_classes3);
        total_teachers1=(TextView)findViewById(R.id.total_teachers3);
        course_provided1=(TextView)findViewById(R.id.course_provided3);
        shift1=(TextView)findViewById(R.id.shift3);
        canteen_facility1=(TextView)findViewById(R.id.canteen_facility3);


        comapreData = SharedPreferencesHelper.getComapreData(CompareActivity.this);

        int size=comapreData.length();
        for(int i=0;i<size;i++)
        {

               if(checker==1)
               {
                   SecondData=SecondData+comapreData.charAt(i);
                   Log.d("===","second_data" +SecondData);
               }
               else  if(comapreData.charAt(i)==' ')
                {
                    checker=1;
                }
               else
                firstData=firstData+comapreData.charAt(i);
                Log.d("===","firstData" +firstData);
        }


        educationServiceProviderTable=new EducationServiceProviderTable(CompareActivity.this);
        firstDataSet=educationServiceProviderTable.getEducationData(firstData);
        secondDataSet=educationServiceProviderTable.getEducationData(SecondData);


        for (EducationServiceProviderItem educationServiceProviderItem: firstDataSet)
        {
            edu_name_ban.setText(educationServiceProviderItem.getEduNameEng());
            edtype.setText(educationServiceProviderItem.getEduType());
            hostel_facility.setText(educationServiceProviderItem.getHostelFacility());
            transport_facility.setText(educationServiceProviderItem.getTransportFacility());
            playground.setText(educationServiceProviderItem.getPlayground());
            total_students.setText(String.valueOf(educationServiceProviderItem.getTotalStudents()));
            total_classes.setText(String.valueOf(educationServiceProviderItem.getTotalClasses()));
            total_teachers.setText(String.valueOf(educationServiceProviderItem.getTotalTeachers()));
            course_provided.setText(educationServiceProviderItem.getCourseProvided());
            shift.setText(educationServiceProviderItem.getShift());
            canteen_facility.setText(educationServiceProviderItem.getCanteenFacility());
        }
        for (EducationServiceProviderItem educationServiceProviderItem: secondDataSet)
        {
            edu_name_ban1.setText(educationServiceProviderItem.getEduNameEng());
            edtype1.setText(educationServiceProviderItem.getEduType());
            hostel_facility1.setText(educationServiceProviderItem.getHostelFacility());
            transport_facility1.setText(educationServiceProviderItem.getTransportFacility());
            playground1.setText(educationServiceProviderItem.getPlayground());
            total_students1.setText(String.valueOf(educationServiceProviderItem.getTotalStudents()));
            total_classes1.setText(String.valueOf(educationServiceProviderItem.getTotalClasses()));
            total_teachers1.setText(String.valueOf(educationServiceProviderItem.getTotalTeachers()));
            course_provided1.setText(educationServiceProviderItem.getCourseProvided());
            shift1.setText(educationServiceProviderItem.getShift());
            canteen_facility1.setText(educationServiceProviderItem.getCanteenFacility());
        }

        SharedPreferencesHelper.setCompareData(CompareActivity.this,"",0);




    }


}
