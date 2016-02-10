package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * Created by israt.jahan on 1/10/2016.
 */



    public class DetailsFinancialActivity extends Activity {

        ImageView close;
        TextView close_tv;
        ImageView btnroute;
    ImageButton Feedback;
        /**
         * Following components are only for Financial
         * For other categories this components may vary
         * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
         * */
        private TextView itemName;
        private TextView itemAddress;
        private TextView itemType;
        private TextView itemContact;

        private TextView email;
        private TextView website;
        private TextView fb;

        //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
        FinancialServiceProviderItem financialServiceProviderItem;

    ArrayList<FinancialBillsItem> financialBillsItems;
    ArrayList<FinancialInsuranceItem> financialInsuranceItems;
    ArrayList<FinancialLoanItem> financialLoanItems;
    ArrayList<FinancialPaymentItem> financialPaymentItems;
    ArrayList<FinancialSocialItem> financialSocialItems;
    ArrayList<FinancialTaxItem> financialTaxItems;
    ArrayList<FinancialTransactionItem> financialTransactionItems;
    ArrayList<FinancialTuitionItem> financialTuitionItems;
    ListView navlist,navlist1,navlist2,navlist3,navlist4,navlist5,navlist6,navlist7;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details_financial);
            Intent intent = getIntent();
            Feedback = (ImageButton) findViewById(R.id.button2);
            Feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a = new Intent(DetailsFinancialActivity.this, FeedbackActivity.class);
                    startActivity(a);
                    finish();
                }
            });

            if (null != intent)
            {
                financialServiceProviderItem = (FinancialServiceProviderItem)intent.getSerializableExtra(AppConstants.KEY_DETAILS_FINANCIAL);

            }
            /**
             *following codes only for legal. This may vary for different category.
             * */
            itemName = (TextView) findViewById(R.id.tv_header);
            itemAddress = (TextView) findViewById(R.id.tv_item_location);
            itemType = (TextView) findViewById(R.id.tv_item_type);
            itemContact = (TextView) findViewById(R.id.tv_item_contact);
            email = (TextView) findViewById(R.id.tv_email);
            website = (TextView) findViewById(R.id.tv_website);
            fb = (TextView) findViewById(R.id.tv_fb);
            btnroute=(ImageView)findViewById(R.id.kivabejabenFinancial);



            navlist = (ListView) findViewById(R.id.listView7s);
            navlist1 = (ListView) findViewById(R.id.listView8s);
            navlist2 = (ListView) findViewById(R.id.listView9s);
            navlist3 = (ListView) findViewById(R.id.listView10s);
            navlist4 = (ListView) findViewById(R.id.listView7ss);
            navlist5 = (ListView) findViewById(R.id.listView8ss);
            navlist6 = (ListView) findViewById(R.id.listView9ss);
            navlist7 = (ListView) findViewById(R.id.listView10ss);

            itemName.setText( financialServiceProviderItem.getNodeName());
            itemAddress.setText("ঠিকানাঃ "+ financialServiceProviderItem.getArea());
            itemType.setText("যোগাযোগঃ  "+ financialServiceProviderItem.getNodeContact());

            itemContact.setText("রেজিস্ট্রেশঃন "+ financialServiceProviderItem.getNodeRegisteredwith());

            email.setText("সংবাদ দাতাঃ "+ financialServiceProviderItem.getNodeDesignation());
            website.setText("ওয়েবসাইটঃ "+ financialServiceProviderItem.getNodeWebsite());
            fb.setText("ফেসবুকঃ " + financialServiceProviderItem.getNodeFacebook());


            FinancialBillsTable financialBillsTable = new FinancialBillsTable(DetailsFinancialActivity.this);
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


                int  k=0;
                for (FinancialLoanItem et : financialLoanItems) {

                    service_name[k]=et.getServicename();
                    yes_no[k]=et.getYn();
                    costs[k]=et.getServicecost();
                    remark[k]=et.getServiceremark();

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







            btnroute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String lat= financialServiceProviderItem.getLatitude().toString();
                    // double latitude = Double.parseDouble(lat);
                    String lon = financialServiceProviderItem.getLongitude().toString();
                    // double longitude = Double.parseDouble(lon);
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Latitude",lat);
                    editor.putString("Longitude",lon);
                    editor.commit();


                    String Longitude=pref.getString("Latitude", null);
                    String Latitude=pref.getString("Longitude", null);

                    if (Latitude != null && Longitude != null )
                    {
                        Double Lon= Double.parseDouble(Longitude);
                        Double Lat= Double.parseDouble(Latitude);
                        Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat,Toast.LENGTH_SHORT).show();
                        // implementFragment();

                        //username and password are present, do your stuff
                    }





                    finish();
                }
            });

            //common for all category
            close = (ImageView) findViewById(R.id.iv_close);
            close_tv = (TextView) findViewById(R.id.tv_close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            close_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }

        @Override
        public void onBackPressed() {
            finish();
            super.onBackPressed();
        }
}
