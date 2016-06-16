package demo.kolorob.kolorobdemoversion.activity;

import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;

public class CompareActivity extends AppCompatActivity {

    private String comapreData;
    String firstData,SecondData;
    int checker=0;
    EducationServiceProviderTable educationServiceProviderTable;
    ArrayList<EducationServiceProviderItem> firstDataSet;
    ArrayList<EducationServiceProviderItem> secondDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);


        comapreData = SharedPreferencesHelper.getComapreData(CompareActivity.this);
        int size=comapreData.length();
        for(int i=0;i<size;i++)
        {

               if(checker==1)
               {
                   SecondData=SecondData+comapreData.charAt(i);
               }

               else  if(comapreData.charAt(i)==' ')
                {

                    checker=1;
                }
               else
                firstData=firstData+comapreData.charAt(i);
        }


        educationServiceProviderTable=new EducationServiceProviderTable(CompareActivity.this);
        firstDataSet=educationServiceProviderTable.getEducationData(Integer.parseInt(firstData));
        secondDataSet=educationServiceProviderTable.getEducationData(Integer.parseInt(SecondData));
























    }


}
