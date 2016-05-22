package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.LegalAidAdviceAdapter;
import demo.kolorob.kolorobdemoversion.database.Education.EducationCourseTable;
import demo.kolorob.kolorobdemoversion.database.Education.EducationFeeTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationCourseItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationFeeItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivityEducation extends Activity {
    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar;
    ImageView left_image,middle_image,right_image;
    int width,height;
    TextView ups_text;
    EducationServiceProviderItem educationServiceProviderItem;
    ArrayList<EducationCourseItem> educationCourseItems;
    ArrayList<EducationFeeItem>educationFeeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_education);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;


        Intent intent = getIntent();


        if (null != intent)
        {
            educationServiceProviderItem = (EducationServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_VIEW);

        }


        EducationCourseTable educationCourseTable = new EducationCourseTable(DetailsInfoActivityEducation.this);
        EducationFeeTable educationFeeTable = new EducationFeeTable(DetailsInfoActivityEducation.this);
        educationCourseItems=educationCourseTable.getEduCourse(educationServiceProviderItem.getIdentifierId());
        educationFeeItems = educationFeeTable.getEduFee(educationServiceProviderItem.getIdentifierId());


        upperHand=(LinearLayout)findViewById(R.id.upper_part);
        upperText=(LinearLayout)findViewById(R.id.upperText);
        left_way=(LinearLayout)findViewById(R.id.left_go_process);
        middle_phone=(LinearLayout)findViewById(R.id.middle_phone);
        right_email=(LinearLayout)findViewById(R.id.right_email);
        left_image=(ImageView)findViewById(R.id.distance_left);
        bottom_bar=(LinearLayout)findViewById(R.id.bottom_bar);
        middle_image=(ImageView)findViewById(R.id.phone_middl);
        right_image=(ImageView)findViewById(R.id.right_side_email);


        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) upperHand.getLayoutParams();
        int upperhad_height=params2.height = height/6;

        upperHand.setLayoutParams(params2);




        LinearLayout.LayoutParams params_upperText = (LinearLayout.LayoutParams) upperText.getLayoutParams();
        int  vd=params_upperText.height = height/24;
        params_upperText.width = width;
        upperText.setLayoutParams(params_upperText);

        LinearLayout.LayoutParams params_left_way = (LinearLayout.LayoutParams) left_way.getLayoutParams();
        int lett_img=params_left_way.height = (height*3)/24;
        int right_img=params_left_way.width = width/3;
        left_way.setLayoutParams(params_left_way);


        left_image.getLayoutParams().height= (lett_img*2)/3;
        left_image.getLayoutParams().width=right_img/2;


        LinearLayout.LayoutParams params_middle_phone = (LinearLayout.LayoutParams) middle_phone.getLayoutParams();
        int  vx=params_middle_phone.height = (height*3)/24;
        params_middle_phone.width = width/3;
        middle_phone.setLayoutParams(params_middle_phone);

        middle_image.getLayoutParams().height= (lett_img*2)/3;
        middle_image.getLayoutParams().width=right_img/2;

        right_image.getLayoutParams().height= (lett_img*2)/3;
        right_image.getLayoutParams().width=right_img/2;

        LinearLayout.LayoutParams params_right_email = (LinearLayout.LayoutParams) right_email.getLayoutParams();
        int  vc=params_right_email.height = (height*3)/24;
        params_right_email.width = width/3;
        right_email.setLayoutParams(params_right_email);

        ups_text=(TextView)findViewById(R.id.ups_text);
        ups_text.setTextSize(width/25);




        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
        int  vcc=params_bottom_bar.height = height/13;
        params_bottom_bar.width = width;
        bottom_bar.setLayoutParams(params_bottom_bar);



        Log.d(">>>>>","Uppertext Height "+lett_img);









    }
}
