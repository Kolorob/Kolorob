package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.BazarItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

import static demo.kolorob.kolorobdemoversion.parser.VolleyApiParser.getRequest;

public class EditBazarActivity extends AppCompatActivity {
    EditText product_name;
    EditText phone;
    int buttonh;
    EditText address;
    EditText price;
    EditText description;
    EditText contact_person;
    EditText contact;
    Double screenSize;
    int height,width;
    int tution_detector=0;
    private int spinCounter=0,spinCounter1=0;
    CheckBox negotiable;
    int negotiable_check=0;
    TextView submit_bazar;
    String pname,paddress,powner,pdescription;
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bazar);

        Intent intent=getIntent();



        final Spinner spinner = (Spinner) findViewById(R.id.bazar_spinner);
        final Spinner type_spinner = (Spinner) findViewById(R.id.type_spinner);

        List<String> categories = new ArrayList<String>();
        categories.add("কন্ডিশন");
        categories.add("নতুন");

        categories.add("ব্যবহৃত");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.bazar_spinner, categories);
        spinner.setAdapter(dataAdapter);



        product_name= (EditText)findViewById(R.id.product_name);
        phone= (EditText)findViewById(R.id.phone_no);
        address= (EditText)findViewById(R.id.address);
        price= (EditText)findViewById(R.id.costs);
        description= (EditText)findViewById(R.id.descriptions);
        contact_person= (EditText)findViewById(R.id.contact_person);
        contact= (EditText)findViewById(R.id.contact);
        int heightconsiderforcost=contact_person.getHeight();



        screenSize= AppUtils.ScreenSize(this);

        int text_field_height;
        if(screenSize>6.5)
            text_field_height = height/30;
        else
            text_field_height = height/24;
        LinearLayout.LayoutParams spinnners = (LinearLayout.LayoutParams) spinner.getLayoutParams();
        spinnners.height= text_field_height;
        spinner.setLayoutParams(spinnners);



        LinearLayout.LayoutParams type_spinners = (LinearLayout.LayoutParams) type_spinner.getLayoutParams();
        type_spinners.height= text_field_height;
        spinner.setLayoutParams(spinnners);


        product_name.setHeight(text_field_height);
        price.setHeight(text_field_height);
        description.setHeight(text_field_height);
        contact_person.setHeight(text_field_height);
        contact.setHeight(text_field_height);
        phone.setHeight(text_field_height);
        address.setHeight(text_field_height);








        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if((position>=1&&position<=3)&&spinCounter>0)
                {
                    TextView text1 = (TextView)parent.getChildAt(0);

                    text1.setTextColor(ContextCompat.getColor(EditBazarActivity.this,R.color.black));
                    text1.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
//                    spinner.setBackgroundResource(R.drawable.border_spinner_adapter);
//                    spinner.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this,R.color.white));

                }
                else
                {
                    TextView text1 = (TextView)parent.getChildAt(0);
                    text1.setTextColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                    text1.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }


                spinCounter++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TextView text1 = (TextView)parent.getChildAt(0);
                text1.setTextColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
            }
        });




        List<String> types = new ArrayList<String>();
        types.add("বিজ্ঞাপনের ধরন");
        types.add("ক্রয়");
        types.add("বিক্রয়");
        types.add("বিনিময়");
        types.add("টু লেট");
        types.add("টিউশন");
        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(this, R.layout.bazar_spinner, types);
        type_spinner.setAdapter(type_adapter);


        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if((position>=1&&position<=5)&&spinCounter1>0)
                {
                    TextView text2 = (TextView)parent.getChildAt(0);

                    text2.setTextColor(ContextCompat.getColor(EditBazarActivity.this,R.color.black));
                    text2.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
//                    spinner.setBackgroundResource(R.drawable.border_spinner_adapter);
//                    spinner.setBackgroundColor(ContextCompat.getColor(PlaceDetailsActivityNewLayout.this,R.color.white));

                }
                else
                {
                    TextView text2 = (TextView)parent.getChildAt(0);
                    text2.setTextColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                    text2.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }

                final LinearLayout pricing= (LinearLayout)findViewById(R.id.pricing);

                if(position==5)
                {
                    spinner.setVisibility(View.GONE);
                    pricing.setVisibility(View.GONE);
                    product_name.setVisibility(View.VISIBLE);
                    tution_detector =1;
                    product_name.setHint("টিউশনির ধরন");
                    description.setHint("টিউশনির বিবরন");
                    tution_detector = position;
                }
                else if (position==4)
                {
                    spinner.setVisibility(View.GONE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    product_name.setHint("কি ভাড়া দিতে চান?");
                    price.setHint("বাসাভাড়া");
                    description.setHint("বাসার বিবরন");
                    tution_detector = position;
                }
                else if(position==3)
                {
                    spinner.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    product_name.setHint("কি বিনিময় করবেন?");
                    price.setHint("মূল্য");
                    description.setHint("বিবরন");
                    tution_detector = 0;
                }

                else if(position==2)
                {
                    spinner.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    product_name.setHint("কি বিক্রয় করবেন?");
                    price.setHint("মূল্য");
                    tution_detector = 0;
                    description.setHint("বিবরন");
                }
                else if(position==1)
                {
                    spinner.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    product_name.setHint("কি ক্রয় করবেন?");
                    price.setHint("মূল্য");
                    description.setHint("বিবরন");
                    tution_detector = 0;
                }

                else
                {
                    spinner.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    product_name.setVisibility(View.VISIBLE);
                    tution_detector = 0;
                    product_name.setHint("কি পন্য বিক্রয় করবেন?");
                    description.setHint("পণ্যের বিবরন");
                }



                spinCounter1++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TextView text2 = (TextView)parent.getChildAt(0);
                text2.setTextColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
            }
        });

        negotiable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    negotiable_check=1;
                else
                    negotiable_check=0;
            }
        });

        screenSize= AppUtils.ScreenSize(this);

        if(screenSize>6.5)
            negotiable.setTextSize(20);
        else
            negotiable.setTextSize(14);




        String number = SharedPreferencesHelper.getNumber(EditBazarActivity.this);
        String name = SharedPreferencesHelper.getUname(EditBazarActivity.this);
        phone.setText(number);
        phone.setEnabled(false);
        contact_person.setText(name);
        contact_person.setEnabled(true);


        product_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0)
                {
                    product_name.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                }
                else
                {
                    product_name.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0)
                {
                    phone.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                }
                else
                {
                    phone.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0)
                {
                    address.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                }
                else
                {
                    address.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0)
                {
                    price.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                }
                else
                {
                    price.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0)
                {
                    description.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                }
                else
                {
                    description.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        contact_person.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0)
                {
                    contact_person.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                }
                else
                {
                    contact_person.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0)
                {
                    contact.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.white));
                }
                else
                {
                    contact.setBackgroundColor(ContextCompat.getColor(EditBazarActivity.this,R.color.drak_yellow));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        submit_bazar= (TextView)findViewById(R.id.submit_bazar);

        submit_bazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {


//           final BazarItem b = new BazarItem();
//           b.description=description.getText().toString();
//
//           b.type = type_spinner.getSelectedItem().toString();
//           b.phone = phone.getText().toString(); //MUST BE REGISTERED
//           b.contact = contact.getText().toString();
//           b.condition = spinner.getSelectedItem().toString();
//           b.contact_person = contact_person.getText().toString();
//           b.address= "address";
//           Log.d("type Spinner","$$$$$$"+address.getText().toString());
//           if(negotiable_check)
//           {
//               b.price = price.getText().toString()+ " (Negotiable)";
//           }
//           else {
//               b.price = price.getText().toString();
//           }
//
//           b.product_name= "product";



                    if(tution_detector==5)
                    {
                        spinner.setSelection(2);
                        price.setText("1111");
                    }
                    else if(tution_detector==4)
                    {
                        spinner.setSelection(2);

                    }




                    final BazarItem b = new BazarItem();

                    String number =SharedPreferencesHelper.getNumber(EditBazarActivity.this);
                    if(number.equals(""))
                    {

                    }
                    else
                    {
                        if(spinner.getSelectedItem().toString().equals("কন্ডিশন"))
                        {


                            AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক কন্ডিশন ইনপুট দিন","");
//               ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক কন্ডিশন ইনপুট দিন");

                        }
                        else
                        {

                            String conditions = spinner.getSelectedItem().toString();

                            if(conditions.equals("নতুন"))
                                b.condition = "New";
                            else if(conditions.equals("ব্যবহৃত"))
                                b.condition = "Refurbished";




                            if(type_spinner.getSelectedItem().toString().equals("বিজ্ঞাপনের ধরন"))
                            {
                                AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক বিজ্ঞাপনের ধরন ইনপুট দিন","");
//                   ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক বিজ্ঞাপনের ধরন ইনপুট দিন");
                            }
                            else {
                                String condition_selector = type_spinner.getSelectedItem().toString();

                                if(condition_selector.equals("বিনিময়"))
                                    b.type = "Exchange";
                                else if(condition_selector.equals("বিক্রয়"))
                                    b.type = "Sell";
                                else if(condition_selector.equals("টিউশন"))
                                    b.type = "Tution";
                                else if(condition_selector.equals("ক্রয়"))
                                    b.type = "Buy";
                                else if(condition_selector.equals("টু লেট"))
                                    b.type = "To_Let";
//                       b.type = type_spinner.getSelectedItem().toString().replace(' ','+');
                                if(product_name.getText().toString().equals(""))
                                {
//                       AlertMessage.showMessage(context,"","");
                                    AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক পন্যের নাম ইনপুট দিন","");
//                       ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক পন্যের নাম ইনপুট দিন");
                                }
                                else {

                                    b.product_name= product_name.getText().toString();
                                    try {
                                        pname=   URLEncoder.encode(b.product_name.replace(" ", "%20"), "utf-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    if(phone.getText().toString().equals(""))
                                    {
                                        AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক ফোন নম্বর ইনপুট দিন","");
//                           ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক ফোন নম্বর ইনপুট দিন");
                                    }
                                    else
                                    {
                                        b.phone = phone.getText().toString(); //MUST BE REGISTERED
                                        if(AppUtils.mobile_number_verification(contact.getText().toString())) {
                                            AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক সঠিক ফোন নম্বর ইনপুট দিন","");
//                               ToastMessageDisplay.setText(context, "অনুগ্রহ পূর্বক অন্য ফোন নম্বরটি ইনপুট দিন");
                                        }
                                        else {
                                            b.contact = contact.getText().toString();
                                            if(address.getText().toString().equals(""))
                                            {
                                                AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক আপনার ঠিকানা ইনপুট দিন","");
//
                                            }
                                            else {
                                                b.address= address.getText().toString();
                                                try {
                                                    paddress=   URLEncoder.encode( b.address.replace(" ", "%20"), "utf-8");
                                                } catch (UnsupportedEncodingException e) {
                                                    e.printStackTrace();
                                                }
                                                if(contact_person.getText().toString().equals(""))
                                                {
//
                                                    AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক আপনার নাম ইনপুট দিন","");
                                                }
                                                else
                                                {
                                                    b.contact_person = contact_person.getText().toString();
                                                    try {
                                                        powner=   URLEncoder.encode( b.contact_person.replace(" ", "%20"), "utf-8");
                                                    } catch (UnsupportedEncodingException e) {
                                                        e.printStackTrace();
                                                    }
                                                    if(price.getText().toString().equals("")&&negotiable_check==0)
                                                    {
//                                           ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক পণ্যের মূল্য ইনপুট দিন");
                                                        AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক পণ্যের মূল্য ইনপুট দিন","");
                                                    }
                                                    else
                                                    {
                                                        Log.d("negotiable_check","=============="+negotiable_check);

                                                        b.price = price.getText().toString() + negotiable_check;
                                                        if(description.getText().toString().equals(""))
                                                        {
//                                               ToastMessageDisplay.setText(context,"অনুগ্রহ পূর্বক বিস্তারিত তহত্য ইনপুট দিন");
                                                            AlertMessage.showMessage(EditBazarActivity.this,"অনুগ্রহ পূর্বক বিস্তারিত তথ্য ইনপুট দিন","");
                                                        }
                                                        else {
                                                            b.description=description.getText().toString();
                                                            try {
                                                                pdescription=   URLEncoder.encode( b.description.replace(" ", "%20"), "utf-8");
                                                            } catch (UnsupportedEncodingException e) {
                                                                e.printStackTrace();
                                                            }
                                                            saveBazar(b,EditBazarActivity.this);
                                                        }
                                                    }
                                                }
                                            }

                                        }

                                    }
                                }

                            }
                        }
                    }






                    if(b.equals(""))
                    {
                        ToastMessageDisplay.setText(EditBazarActivity.this,"অনুগ্রহ পূর্বক তথ্য ইনপুট দিন");
                    }

//           if()






                }
                catch (Exception e)
                {

                }

            }
        });
    }



    private void saveBazar(BazarItem b,final Context contexts){
        if ((AppUtils.isNetConnected(getApplicationContext()) )&&(ContextCompat.checkSelfPermission(EditBazarActivity.this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED ))
        {
            getRequest(contexts, "http://kolorob.net/demo/api/post_advertise?username=" + user +"&password="+ pass
                            +"&description=" + pdescription +
                            "&type=" + b.type +
                            "&phone=" + b.phone +
                            "&contact=" + b.contact +
                            "&condition=" + b.condition +
                            "&contact_person=" + powner +
                            "&price=" + b.price+
                            "&name=" + pname+
                            "&adress=" + paddress,


                    new VolleyApiCallback() {
                        @Override
                        public void onResponse(int status,final String apiContent) {

                            if (status == AppConstants.SUCCESS_CODE) {
                                //tester. You may delete this portion

                                if(apiContent.equals("true"))
                                {
                                    DisplayMetrics displayMetrics = contexts.getResources().getDisplayMetrics();
                                    height = displayMetrics.heightPixels;
                                    width = displayMetrics.widthPixels;

                                    LayoutInflater layoutInflater = LayoutInflater.from(contexts);
                                    View promptView = layoutInflater.inflate(R.layout.default_alert, null);


                                    final Dialog alertDialog = new Dialog(contexts);
                                    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    alertDialog.setContentView(promptView);
                                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    alertDialog.show();


                                    final TextView header = (TextView) promptView.findViewById(R.id.headers);
                                    final TextView bodys = (TextView) promptView.findViewById(R.id.body);
                                    final ImageView okay=(ImageView)promptView.findViewById(R.id.okay);

                                    header.setText("আপনার বিজ্ঞাপন টি পাঠানো হয়েছে");
                                    bodys.setText("কলরব থেকে বিজ্ঞাপন দেয়ার জন্য আপনাকে ধন্যবাদ");

                                    product_name.setText("");
                                    phone.setText("");
                                    address.setText("");
                                    price.setText("");
                                    description.setText("");
                                    contact_person.setText("");
                                    contact.setText("");
                                    negotiable.setChecked(false);
                                    negotiable_check=0;

                                    okay.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.cancel();

                                        }
                                    });

                                    alertDialog.setCancelable(true);
//		if(SharedPreferencesHelper.isTabletDevice(c))
//			textAsk.setTextSize(23);
//		else
//			textAsk.setTextSize(17);
                                    alertDialog.getWindow().setLayout((width*5)/6, WindowManager.LayoutParams.WRAP_CONTENT);
                                }
                                //tester ends======
                            }
                        }
                    }
            );
        }
        else {
            AlertMessage.showMessage(EditBazarActivity.this,"আপনার ফোনে ইন্টারনেট সংযোগ নেই।","অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি চালু করুন। ...");

        }
        Log.d("Advertizement Type","=========="+b.type);




    }
}
