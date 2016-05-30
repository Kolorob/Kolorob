package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.helpers.AlertMessage;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;

/**
 * Created by arafat on 1/11/2016.
 */
public class PhoneRegActivity extends Activity {
    ImageView close,kivabejabejob;
    TextView close_tv;
    ImageButton Feedback;
    /**
     * Following components are only for LegalAid
     * For other categories this components may vary
     * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
     * */
    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;
    private String phoneNumber;

    private EditText phone;
    private EditText name;
    private TextView fb,openTime,close_Time,breakTIme,jobName,road,block,landmark;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    JobServiceProviderItem jobServiceProviderItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_reg);


        phone  = (EditText)findViewById(R.id.phone_id);
        phoneNumber=phone.getText().toString();




    }

    public void submit(View v){

        phoneNumber = phone.getText().toString();
        int size=phoneNumber.length();

        if(size!=11)
        {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        }



    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
