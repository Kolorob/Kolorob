package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidtypeServiceProviderLegalAdviceTable;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidLegalAdviceItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidSalishiItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivityLegalNew extends Activity {

    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar,linearLayout;
    ImageView left_image,middle_image,right_image;
    TextView address_text,phone_text,email_text;
    int width,height;
    String basic_part;
    TextView ups_text,itemopeningTime;
    private ImageView close_button;
    private LinearLayout ll3,scrollingPart;
    LinearLayout ll1,ll2;
    ImageView close,legal;
    TextView close_tv;
    ImageButton Feedback;
    ListView lv11,lv2;
    String result_concate="";
    ArrayList<LegalAidtypeServiceProviderLegalAdviceTable> legalAidtypeServiceProviderLegalAdviceTables;
    ArrayList<LegalAidLegalAdviceItem> legalAidLegalAdviceItems;
    ArrayList<LegalAidSalishiItem> legalAidSalishiItems;
    LegalAidServiceProviderItem legalAidServiceProviderItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_legal_new);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;

        Intent intent = getIntent();

        if (null != intent)
        {
            legalAidServiceProviderItem = (LegalAidServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_LEGAL);

        }






        linearLayout=(LinearLayout)findViewById(R.id.lll);
        upperHand=(LinearLayout)findViewById(R.id.upper_part);
        upperText=(LinearLayout)findViewById(R.id.upperText);
        left_way=(LinearLayout)findViewById(R.id.left_go_process);
        middle_phone=(LinearLayout)findViewById(R.id.middle_phone);
        right_email=(LinearLayout)findViewById(R.id.right_email);
        left_image=(ImageView)findViewById(R.id.distance_left);
        bottom_bar=(LinearLayout)findViewById(R.id.bottom_bar);
        middle_image=(ImageView)findViewById(R.id.phone_middl);
        right_image=(ImageView)findViewById(R.id.right_side_email);
        address_text=(TextView)findViewById(R.id.address_text);
        phone_text=(TextView)findViewById(R.id.phone_text);
        email_text=(TextView)findViewById(R.id.email_text);
        close_button=(ImageView)findViewById(R.id.close_button);
        scrollingPart=(LinearLayout)findViewById(R.id.scrollingPart);
        lv11 = (ListView) findViewById(R.id.listView7);
        lv2 = (ListView) findViewById(R.id.listView8);
        ll1=(LinearLayout)findViewById(R.id.first_list);
        ll2=(LinearLayout)findViewById(R.id.second_list);
        itemopeningTime=(TextView)findViewById(R.id.opening_time);

        if(!legalAidServiceProviderItem.getOpeningtime().equals(""))
            concateBasic(" খোলার সময়: ",legalAidServiceProviderItem.getOpeningtime());
        if(!legalAidServiceProviderItem.getAdditionaltime().equals(""))
            concateBasic(" অতিরিক্ত সময়: ",legalAidServiceProviderItem.getAdditionaltime());

        if(!legalAidServiceProviderItem.getClosingtime().equals(""))
            concateBasic(" বন্ধ করার সময়: ",legalAidServiceProviderItem.getClosingtime());


        itemopeningTime.setText(result_concate);
        result_concate="";





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
        ups_text.setText(legalAidServiceProviderItem.getLegalaidNameBan());
        phone_text.setText(legalAidServiceProviderItem.getContactNo());
        email_text.setText(legalAidServiceProviderItem.getEmailAddress());


        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
        int  vcc=params_bottom_bar.height = height/13;
        params_bottom_bar.width = width;
        bottom_bar.setLayoutParams(params_bottom_bar);

        LinearLayout.LayoutParams expnlist = (LinearLayout.LayoutParams) scrollingPart.getLayoutParams();
        expnlist.setMargins(0,0,0,vcc);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        result_concate= result_concate+value + "\n";




        return result_concate;
    }
}
