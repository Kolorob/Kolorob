package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by israt.jahan on 1/10/2016.
 */



    public class DetailsFinancialActivity_new extends Activity {

        ImageView close;
        TextView close_tv;
        ImageView btnroute;
    ImageButton Feedback;
    String basic_part="";
    String user="kolorobapp";
    String pass="2Jm!4jFe3WgBZKEN";
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

    private TextView open;
    private TextView closen;
    private TextView breaking;
    private TextView road;
    private TextView block;
    private TextView landmark;
    private TextView additional;

    LinearLayout first,second,third,fourth;

        //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
        FinancialServiceProviderItem financialServiceProviderItem;
ArrayList<FinancialServiceProviderItem>finfromsearch;
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



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details_financial_new);
            Intent intent = getIntent();
            Feedback = (ImageButton) findViewById(R.id.button2);

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


            open=(TextView)findViewById(R.id.opening_time);
            closen=(TextView)findViewById(R.id.close_time);
            breaking =(TextView)findViewById(R.id.breaktime);
            road= (TextView)findViewById(R.id.road);
            block =(TextView)findViewById(R.id.block);
            landmark=(TextView)findViewById(R.id.landmark);
            additional=( TextView )findViewById(R.id.additionalTime);


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
            itemName.setText(" "+ financialServiceProviderItem.getNamebn());
            itemType.setText(""+ financialServiceProviderItem.getNodeRegisteredwith());

            if(!financialServiceProviderItem.getArea().equals(""))
                concateBasic(" ঠিকানা: ",financialServiceProviderItem.getArea());
            if(!financialServiceProviderItem.getNodeRegisteredwith().equals(""))
                concateBasic(" রেজিস্ট্রেশন: ", financialServiceProviderItem.getNodeRegisteredwith());

            itemAddress.setText(basic_part);

            basic_part="";


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
            basic_part="";
//            closen.setText(" বন্ধের সময়: " +financialServiceProviderItem.getClosingtime());
//            breaking.setText(" বিরতির সময়: "+ financialServiceProviderItem.getBreaktime2());
//            additional.setText(" অতিরিক্ত সময়: "+financialServiceProviderItem.getAdditionaltime());

            if(!financialServiceProviderItem.getNodeContact().equals(""))
                concateBasic("  মোবাইল/মুঠোফোন নম্বর: ",financialServiceProviderItem.getNodeContact());

            if(! financialServiceProviderItem.getNodeDesignation().equals(""))
                concateBasic(" ইমেইল: ", financialServiceProviderItem.getNodeDesignation());

            if(!financialServiceProviderItem.getNodeWebsite().equals(""))
                concateBasic(" ওয়েবসাইট :",financialServiceProviderItem.getNodeWebsite());

            if(!financialServiceProviderItem.getNodeFacebook().equals(""))
                concateBasic(" ফেসবুক : ",financialServiceProviderItem.getNodeFacebook());
            email.setText("  মোবাইল/মুঠোফোন নম্বর: "+ financialServiceProviderItem.getNodeContact());
//            road.setText(" রাস্তা: "+financialServiceProviderItem.getRoad());
//            block.setText(" ব্লক: "+ financialServiceProviderItem.getBlock());
//            landmark.setText(" কাছাকাছি পরিচিত স্থান:" +financialServiceProviderItem.getLandmark());

//
//
//
//            email.setText(" ইমেইল: "+);
//            website.setText(" ওয়েবসাইট :"+ );
//            fb.setText(" ফেসবুক : " + financialServiceProviderItem.getNodeFacebook());
            itemContact.setText(basic_part);



           /* Feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent a = new Intent(DetailsFinancialActivity_new.this, FeedbackActivity.class);
                    a.putExtra("NodeId",financialServiceProviderItem.getNodeId());
                    a.putExtra("CatId", financialServiceProviderItem.getCategoryId());
                    startActivity(a);
                    finish();
                }
            });
*/



            FinancialBillsTable financialBillsTable = new FinancialBillsTable(DetailsFinancialActivity_new.this);
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








            btnroute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if(AppUtils.isNetConnected(getApplicationContext())  && AppUtils.displayGpsStatus(getApplicationContext())) {

                        String lat = financialServiceProviderItem.getLatitude().toString();
                        // double latitude = Double.parseDouble(lat);
                        String lon = financialServiceProviderItem.getLongitude().toString();
                        String name= financialServiceProviderItem.getNamebn().toString();
                        // double longitude = Double.parseDouble(lon);
                        boolean fromornot=true;
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("Latitude", lat);
                        editor.putString("Longitude", lon);
                        editor.putString("Name", name);
                        editor.putBoolean("Value", fromornot);
                        editor.commit();


                        String Longitude = pref.getString("Latitude", null);
                        String Latitude = pref.getString("Longitude", null);

                        if (Latitude != null && Longitude != null) {
                            Double Lon = Double.parseDouble(Longitude);
                            Double Lat = Double.parseDouble(Latitude);
                          //  Toast.makeText(getApplicationContext(), "Your Longitude is " + Lon, Toast.LENGTH_SHORT).show();
                          //  Toast.makeText(getApplicationContext(), "Your Latitude is " + Lat, Toast.LENGTH_SHORT).show();
                            // implementFragment();

                            //username and password are present, do your stuff
                        }


                        finish();

                    }
                    else if(!AppUtils.displayGpsStatus(getApplicationContext())){

                        AppUtils.showSettingsAlert(DetailsFinancialActivity_new.this);

                    }
                    else
                    {

                        AlertDialog alertDialog = new AlertDialog.Builder(DetailsFinancialActivity_new.this, AlertDialog.THEME_HOLO_LIGHT).create();
                        alertDialog.setTitle("ইন্টারনেট সংযোগ বিচ্চিন্ন ");
                        alertDialog.setMessage(" দুঃখিত আপনার ইন্টারনেট সংযোগটি সচল নয়। \n পথ দেখতে চাইলে অনুগ্রহপূর্বক ইন্টারনেট সংযোগটি সচল করুন।  ");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();



                    }



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




    private String concateBasic(String value1,String value2){

        String value= value1+value2;
        basic_part= basic_part+value + "\n";

        Log.d("....>>>", "Values   " + basic_part);


        return basic_part;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
