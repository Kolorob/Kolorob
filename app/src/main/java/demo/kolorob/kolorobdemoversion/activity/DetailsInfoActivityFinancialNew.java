package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.FInancialBilsAdapter;
import demo.kolorob.kolorobdemoversion.adapters.FInancialInsuranceAdapter;
import demo.kolorob.kolorobdemoversion.adapters.FinancialPaymentAdapter;
import demo.kolorob.kolorobdemoversion.adapters.FinancialSocialAdapter;
import demo.kolorob.kolorobdemoversion.adapters.FinancilaLoadAdapter;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialBillsTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialInsuranceTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialLoanTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialPaymentTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialSocialTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTaxTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTransactionTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialTuitionTable;
import demo.kolorob.kolorobdemoversion.helpers.Helpes;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialBillsItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialInsuranceItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialLoanItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialPaymentItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialSocialItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTaxItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTransactionItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialTuitionItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class DetailsInfoActivityFinancialNew extends Activity {

    Dialog dialog;
    LinearLayout upperHand,upperText,left_way,middle_phone,right_email,bottom_bar,linearLayout;
    ImageView left_image,middle_image,right_image;
    TextView address_text,phone_text,email_text;
    int width,height;
    String basic_part;
    TextView ups_text;
    private ImageView close_button;
    private LinearLayout ll3,scrollingPart;
    FinancialServiceProviderItem financialServiceProviderItem;
    ArrayList<FinancialServiceProviderItem> finfromsearch;
    ArrayList<FinancialBillsItem> financialBillsItems;
    ArrayList<FinancialInsuranceItem> financialInsuranceItems;
    ArrayList<FinancialLoanItem> financialLoanItems;
    ArrayList<FinancialPaymentItem> financialPaymentItems;
    ArrayList<FinancialSocialItem> financialSocialItems;
    ArrayList<FinancialTaxItem> financialTaxItems;
    ArrayList<FinancialTransactionItem> financialTransactionItems;
    ArrayList<FinancialTuitionItem> financialTuitionItems;
    ListView navlist,navlist1,navlist2,navlist3,navlist4,navlist5,navlist6,navlist7;
    LinearLayout l1,l2,l3,l4,l5,l6,l7,l8;
    private TextView open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info_activity_financial_new);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;

        Intent intent = getIntent();

        if (null != intent)
        {

            financialServiceProviderItem = (FinancialServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_FINANCIAL);

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
        l1=(LinearLayout)findViewById(R.id.first_list);
        l2=(LinearLayout)findViewById(R.id.second_list);
        l3=(LinearLayout)findViewById(R.id.third_list);
        l4=(LinearLayout)findViewById(R.id.fourth_list);
        l5=(LinearLayout)findViewById(R.id.fifth_list);
        l6=(LinearLayout)findViewById(R.id.sixth_list);
        l7=(LinearLayout)findViewById(R.id.seventh_list);
        l8=(LinearLayout)findViewById(R.id.eighth_list);
        navlist = (ListView) findViewById(R.id.listView7s);
        navlist1 = (ListView) findViewById(R.id.listView8s);
        navlist2 = (ListView) findViewById(R.id.listView9s);
        navlist3 = (ListView) findViewById(R.id.listView10s);
        navlist4 = (ListView) findViewById(R.id.listView7ss);
        navlist5 = (ListView) findViewById(R.id.listView8ss);
        navlist6 = (ListView) findViewById(R.id.listView9ss);
        navlist7 = (ListView) findViewById(R.id.listView10ss);
        open=(TextView)findViewById(R.id.opening_time);


        if(!financialServiceProviderItem.getOpeningtime().equals(""))
        {
            concateBasic(" খোলার সময়: ",financialServiceProviderItem.getOpeningtime());
        }

        if(!financialServiceProviderItem.getBreaktime2().equals(""))
            concateBasic(" বিরতির সময়: ",financialServiceProviderItem.getBreaktime2());

        if(!financialServiceProviderItem.getClosingtime().equals(""))
            concateBasic(" বন্ধের সময়: ",financialServiceProviderItem.getClosingtime());

        if(!financialServiceProviderItem.getAdditionaltime().equals(""))
            concateBasic(" অতিরিক্ত সময়: ",financialServiceProviderItem.getAdditionaltime());





        open.setText(basic_part);




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
        ups_text.setText(financialServiceProviderItem.getNamebn());
        phone_text.setText(financialServiceProviderItem.getNodeContact());
        email_text.setText(financialServiceProviderItem.getNodeEmail());


        RelativeLayout.LayoutParams params_bottom_bar = (RelativeLayout.LayoutParams) bottom_bar.getLayoutParams();
        int  vcc=params_bottom_bar.height = height/13;
        params_bottom_bar.width = width;
        bottom_bar.setLayoutParams(params_bottom_bar);

        LinearLayout.LayoutParams expnlist = (LinearLayout.LayoutParams) scrollingPart.getLayoutParams();
        expnlist.setMargins(0,0,0,vcc);


        FinancialBillsTable financialBillsTable = new FinancialBillsTable(DetailsInfoActivityFinancialNew.this);
        FinancialInsuranceTable financialInsuranceTable= new FinancialInsuranceTable(this);
        FinancialLoanTable financialLoanTable = new FinancialLoanTable(this);
        FinancialPaymentTable financialPaymentTable= new FinancialPaymentTable(this);
        FinancialSocialTable financialSocialTable = new FinancialSocialTable(this);
        FinancialTaxTable financialTaxTable =new FinancialTaxTable(this);
        FinancialTransactionTable financialTransactionTable = new FinancialTransactionTable(this);
        FinancialTuitionTable financialTuitionTable = new FinancialTuitionTable(this);

        String fs=financialServiceProviderItem.getNodeId();
        financialBillsItems= financialBillsTable.getFinancialBills(financialServiceProviderItem.getNodeId());
        financialInsuranceItems = financialInsuranceTable.getInsurance(financialServiceProviderItem.getNodeId());
        financialLoanItems = financialLoanTable.getFinancialLoan(financialServiceProviderItem.getNodeId());
        financialPaymentItems = financialPaymentTable.getFinancialPayment(financialServiceProviderItem.getNodeId());
        financialSocialItems = financialSocialTable.getFinancialSocial(fs);
        financialTaxItems = financialTaxTable.getFinancialTax(fs);
        financialTransactionItems= financialTransactionTable.getFinancialTransaction(fs);
        financialTuitionItems =financialTuitionTable.getFinancialTuition(fs);

        if(financialBillsItems!=null) {

            int g= financialBillsItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];
            String[] ref_num=new String[g];

            int  k=0;
            for (FinancialBillsItem et : financialBillsItems) {



                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                ref_num[k]=et.getRefNum();
                k++;
                l1.setVisibility(View.VISIBLE);
            }
            FInancialBilsAdapter adapter=new FInancialBilsAdapter(this,service_name,yes_no,
                    costs,remark,ref_num);
            navlist.setAdapter(adapter);
            Helpes.getListViewSize(navlist);
        }



        if(financialInsuranceItems!=null) {

            int g= financialInsuranceItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialInsuranceItem et : financialInsuranceItems) {

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l2.setVisibility(View.VISIBLE);

                k++;
            }
            FInancialInsuranceAdapter adapter=new FInancialInsuranceAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist1.setAdapter(adapter);
            Helpes.getListViewSize(navlist1);
        }



        if(financialLoanItems!=null) {

            int g= financialLoanItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];
            l1.setVisibility(View.VISIBLE);



            int  k=0;
            for (FinancialLoanItem et : financialLoanItems) {

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l3.setVisibility(View.VISIBLE);


                k++;
            }
            FinancialPaymentAdapter adapter=new FinancialPaymentAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist2.setAdapter(adapter);
            Helpes.getListViewSize(navlist2);
        }
        if(financialPaymentItems!=null) {

            int g= financialPaymentItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialPaymentItem et : financialPaymentItems) {

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l4.setVisibility(View.VISIBLE);

                k++;
            }
            FinancilaLoadAdapter adapter=new FinancilaLoadAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist3.setAdapter(adapter);
            Helpes.getListViewSize(navlist3);
        }




        if(financialSocialItems!=null) {

            int g= financialSocialItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialSocialItem et : financialSocialItems) {


                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l5.setVisibility(View.VISIBLE);
                k++;
            }
            FinancialSocialAdapter adapter=new FinancialSocialAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist4.setAdapter(adapter);
            Helpes.getListViewSize(navlist4);
        }



        if(financialTaxItems!=null) {

            int g= financialTaxItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialTaxItem et : financialTaxItems) {

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l6.setVisibility(View.VISIBLE);

                k++;
            }
            FinancialSocialAdapter adapter=new FinancialSocialAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist5.setAdapter(adapter);
            Helpes.getListViewSize(navlist5);
        }



        if(financialTransactionItems!=null) {

            int g= financialTransactionItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialTransactionItem et : financialTransactionItems) {


                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();
                l7.setVisibility(View.VISIBLE);
                k++;
            }
            FinancialSocialAdapter adapter=new FinancialSocialAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist6.setAdapter(adapter);
            Helpes.getListViewSize(navlist6);
        }



        if(financialTuitionItems!=null) {

            int g= financialTuitionItems.size();
            String[] service_name=new String[g];
            String[] yes_no=new String[g];
            String[] costs=new String[g];
            String[] remark=new String[g];


            int  k=0;
            for (FinancialTuitionItem et : financialTuitionItems) {
                l8.setVisibility(View.VISIBLE);

                service_name[k]=et.getServicename();
                yes_no[k]=et.getYn();
                costs[k]=et.getServicecost();
                remark[k]=et.getServiceremark();

                k++;
            }
            FinancialSocialAdapter adapter=new FinancialSocialAdapter(this,service_name,yes_no,
                    costs,remark);
            navlist7.setAdapter(adapter);
            Helpes.getListViewSize(navlist7);
        }










    }

    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        basic_part= basic_part+value + "\n";

        Log.d("....>>>", "Values   " + basic_part);


        return basic_part;
    }
}
