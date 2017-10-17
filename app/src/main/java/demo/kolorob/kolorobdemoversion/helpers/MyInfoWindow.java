package demo.kolorob.kolorobdemoversion.helpers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainmentNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealthNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityLegalNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityNGO;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityReligious;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutEducation;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutFinance;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutGovernment;
import demo.kolorob.kolorobdemoversion.database.Education.EduNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Government.GovNewDBTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthNewDBTableMain;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidNewDBTable;
import demo.kolorob.kolorobdemoversion.database.NGO.NGONewDBTable;
import demo.kolorob.kolorobdemoversion.database.Religious.ReligiousNewDBTable;
import demo.kolorob.kolorobdemoversion.model.EduNewDB.EduNewModel;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Financial.FinancialNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewDBModel;
import demo.kolorob.kolorobdemoversion.model.Health.HealthNewDBModelMain;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidNewDBModel;
import demo.kolorob.kolorobdemoversion.model.NGO.NGONewDBModel;
import demo.kolorob.kolorobdemoversion.model.Religious.ReligiousNewDBModel;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by HP on 5/14/2016.
 */
public class MyInfoWindow extends InfoWindow {
    String titleMarker, contact2, address;
    int n;

    LinearLayout layout;

    EduNewModel nulledu;
    GovernmentNewDBModel nullgov;
    HealthNewDBModelMain nullhel;
    EntertainmentNewDBModel nullent;
    FinancialNewDBModel nullfin;
    LegalAidNewDBModel nullleg;
    NGONewDBModel nullngo;
    ReligiousNewDBModel nullreligious;
    Activity con;
    GeoPoint pp;


    int catid;
    String referenceid;

    public MyInfoWindow(int layoutResId, MapView mapView, Activity con, GeoPoint point, String title, String contact, int Node, int categoryid,String add,String rid) {
        super(layoutResId, mapView);
        this.con=con;
        this.pp=point;
        this.titleMarker =title;
        this.contact2=contact;
        this.n=Node;
        this.catid=categoryid;
        this.address=add;
        this.referenceid=rid;
    }


    public void onClose() {
    }

    public void onOpen(Object arg0) {
        MyInfoWindow.closeAllInfoWindowsOn(mMapView);

        layout = (LinearLayout) mView.findViewById(R.id.bubble_layout);
        Button btnMoreInfo = (Button) mView.findViewById(R.id.bubble_moreinfo);
        final TextView txtTitle = (TextView) mView.findViewById(R.id.bubble_title);
        txtTitle.setTextSize(20);

        // TextView contact=(TextView) mView.findViewById(R.id.contact_no);
        final TextView adddescription = (TextView) mView.findViewById(R.id.bubble_description);
        final TextView txtSubdescription = (TextView) mView.findViewById(R.id.bubble_subdescription);
        contact2 = English_to_bengali_number_conversion(contact2);
        if(contact2==null||contact2.equals(" ")||contact2.equals("null"))contact2="পাওয়া যায় নি";
        txtTitle.setText(titleMarker);

        txtSubdescription.setText("রেটিং : " + address);
        adddescription.setText("যোগাযোগ: " + contact2);
        // contact.setText(contact2);



        layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (catid) {
                    case AppConstants.EDUCATION:

                        layout.setVisibility(View.VISIBLE);
                        EduNewDBTableMain educationNewTable = new EduNewDBTableMain(MyInfoWindow.this.con);

                        nulledu = educationNewTable.getNodeInfo(n);
                        Intent iiedu = new Intent(MyInfoWindow.this.con, DetailsLayoutEducation.class);
                        iiedu.putExtra(AppConstants.KEY_DETAILS_EDU, nulledu);
                        MyInfoWindow.this.con.startActivity(iiedu);

                        break;
                    case AppConstants.GOVERNMENT:

                        layout.setVisibility(View.VISIBLE);
                        GovNewDBTable governmentNewTable = new GovNewDBTable(MyInfoWindow.this.con);

                        nullgov = governmentNewTable.getNodeInfo(n);
                        Intent iigov = new Intent(MyInfoWindow.this.con, DetailsLayoutGovernment.class);
                        iigov.putExtra(AppConstants.KEY_DETAILS_GOV, nullgov);
                        MyInfoWindow.this.con.startActivity(iigov);

                        break;
                    case AppConstants.HEALTH:

                        layout.setVisibility(View.VISIBLE);

                        HealthNewDBTableMain healthServiceProviderTable = new HealthNewDBTableMain(MyInfoWindow.this.con);
                        nullhel = healthServiceProviderTable.getNodeInfo(n);
                        Intent iihel = new Intent(MyInfoWindow.this.con, DetailsInfoActivityHealthNew.class);
                        iihel.putExtra(AppConstants.KEY_DETAILS_HEALTH_NEW, nullhel);
                        MyInfoWindow.this.con.startActivity(iihel);

                        break;
                    case AppConstants.ENTERTAINMENT:

                        layout.setVisibility(View.VISIBLE);
                        EntNewDBTable entertainmentServiceProviderTableNew = new EntNewDBTable(MyInfoWindow.this.con);
                        nullent = entertainmentServiceProviderTableNew.getNodeInfo(n);
                        Intent iientt = new Intent(MyInfoWindow.this.con, DetailsInfoActivityEntertainmentNew.class);
                        iientt.putExtra(AppConstants.KEY_DETAILS_ENT, nullent);
                        MyInfoWindow.this.con.startActivity(iientt);

                        break;
                    case AppConstants.LEGAL:

                        layout.setVisibility(View.VISIBLE);
                        LegalAidNewDBTable legalAidServiceProviderTableNew = new LegalAidNewDBTable(MyInfoWindow.this.con);
                        nullleg = legalAidServiceProviderTableNew.getNodeInfo(n);
                        Intent iileg = new Intent(MyInfoWindow.this.con, DetailsInfoActivityLegalNew.class);
                        iileg.putExtra(AppConstants.KEY_DETAILS_LEGAL, nullleg);
                        MyInfoWindow.this.con.startActivity(iileg);

                        break;
                    case AppConstants.FINANCIAL:

                        layout.setVisibility(View.VISIBLE);
                        FinNewDBTable financialServiceNewTable = new FinNewDBTable(MyInfoWindow.this.con);
                        nullfin = financialServiceNewTable.getNodeInfo(n);
                        Intent iifin = new Intent(MyInfoWindow.this.con, DetailsLayoutFinance.class);
                        iifin.putExtra(AppConstants.KEY_DETAILS_FINANCIALNEW, nullfin);
                        MyInfoWindow.this.con.startActivity(iifin);

                        break;


                    case AppConstants.NGO:

                        layout.setVisibility(View.VISIBLE);
                        NGONewDBTable ngoServiceProviderTableNew = new NGONewDBTable(MyInfoWindow.this.con);
                        nullngo = ngoServiceProviderTableNew.getNodeInfo(n);
                        Intent iingo = new Intent(MyInfoWindow.this.con, DetailsInfoActivityNGO.class);
                        iingo.putExtra(AppConstants.KEY_DETAILS_NGO, nullngo);
                        MyInfoWindow.this.con.startActivity(iingo);

                        break;



                    ////// Religious ///

                    case AppConstants.RELIGIOUS:

                        layout.setVisibility(View.VISIBLE);
                        ReligiousNewDBTable religiousServiceProviderTableNew = new ReligiousNewDBTable(MyInfoWindow.this.con);
                        nullreligious = religiousServiceProviderTableNew.getNodeInfo(n);
                        Intent iireligious = new Intent(MyInfoWindow.this.con, DetailsInfoActivityReligious.class);
                        iireligious.putExtra(AppConstants.KEY_DETAILS_RELIGIOUS, nullreligious);
                        MyInfoWindow.this.con.startActivity(iireligious);

                        break;

                }

            }
        });
    }

    private String English_to_bengali_number_conversion(String english_number) {
        //Toast.makeText(DetailsInfoActivityEntertainmentNew.this, "Eng number: " + english_number, To)
        if(english_number.equals("null")||english_number.equals(""))
            return english_number;
        int v = english_number.length();
        String concatResult = "";
        for (int i = 0; i < v; i++) {
            if (english_number.charAt(i) == '1')
                concatResult = concatResult + "১";
            else if (english_number.charAt(i) == '2')
                concatResult = concatResult + "২";
            else if (english_number.charAt(i) == '3')
                concatResult = concatResult + "৩";
            else if (english_number.charAt(i) == '4')
                concatResult = concatResult + "৪";
            else if (english_number.charAt(i) == '5')
                concatResult = concatResult + "৫";
            else if (english_number.charAt(i) == '6')
                concatResult = concatResult + "৬";
            else if (english_number.charAt(i) == '7')
                concatResult = concatResult + "৭";
            else if (english_number.charAt(i) == '8')
                concatResult = concatResult + "৮";
            else if (english_number.charAt(i) == '9')
                concatResult = concatResult + "৯";
            else if (english_number.charAt(i) == '0')
                concatResult = concatResult + "০";
            else if (english_number.charAt(i) == '.')
                concatResult = concatResult + ".";
            else if(english_number.charAt(i) == '/')
                concatResult = concatResult + "/";
            else if(english_number.charAt(i) == '-')
                concatResult = concatResult + "-";
            else if(english_number.charAt(i) == '+'){
                concatResult = concatResult + "+";
            }
            else {
                return english_number;
            }

        }
        return concatResult;
    }

}
