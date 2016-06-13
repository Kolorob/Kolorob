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
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEducation;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainmentNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityFinancialNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealthNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityLegalNew;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by HP on 5/14/2016.
 */
public class MyInfoWindow extends InfoWindow {
    String titlemarker,contact2,node,address;
    EducationServiceProviderItem nulledu;
    HealthServiceProviderItem nullhel;
    EntertainmentServiceProviderItem nullent;
    FinancialServiceProviderItem nullfin;
    LegalAidServiceProviderItem nullleg;
    Activity con;
    GeoPoint pp;
    int catid;
    public MyInfoWindow(int layoutResId, MapView mapView, Activity con, GeoPoint point, String title, String contact, String Node, int categoryid,String add) {
        super(layoutResId, mapView);
        this.con=con;
        this.pp=point;
        this.titlemarker=title;
        this.contact2=contact;
        this.node=Node;
        this.catid=categoryid;
        this.address=add;
    }
    public void onClose() {
    }

    public void onOpen(Object arg0) {
        final LinearLayout layout = (LinearLayout) mView.findViewById(R.id.bubble_layout);
        Button btnMoreInfo = (Button) mView.findViewById(R.id.bubble_moreinfo);
        TextView txtTitle = (TextView) mView.findViewById(R.id.bubble_title);
        txtTitle.setTextSize(20);
        // TextView contact=(TextView) mView.findViewById(R.id.contact_no);
        TextView adddescription = (TextView) mView.findViewById(R.id.bubble_description);
        TextView txtSubdescription = (TextView) mView.findViewById(R.id.bubble_subdescription);
        txtTitle.setText(titlemarker);
        // contact.setText(contact2);
        txtSubdescription.setText("যোগাযোগঃ "+contact2);
        adddescription.setText("ঠিকানাঃ " + address);
        layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (catid) {
                    case AppConstants.EDUCATION:
                        // Override Marker's onClick behaviour here
                        //Toast.makeText(MyInfoWindow.this.con, "Tap on (" + pp.getLatitude() + "," + pp.getLongitude() + ")", Toast.LENGTH_SHORT).show();
                        layout.setVisibility(View.VISIBLE);
                        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(MyInfoWindow.this.con);
                        nulledu = educationServiceProviderTable.geteduNode2(node);
                        Intent iient = new Intent(MyInfoWindow.this.con, DetailsInfoActivityEducation.class);
                        iient.putExtra(AppConstants.KEY_DETAILS_VIEW, nulledu);
                        MyInfoWindow.this.con.startActivity(iient);

                        break;
                    case AppConstants.HEALTH:
                        //Toast.makeText(MyInfoWindow.this.con, "Tap on (" + pp.getLatitude() + "," + pp.getLongitude() + ")", Toast.LENGTH_SHORT).show();
                        layout.setVisibility(View.VISIBLE);
                        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(MyInfoWindow.this.con);
                        nullhel = healthServiceProviderTable.gethelNode2(node);
                        Intent iihel = new Intent(MyInfoWindow.this.con, DetailsInfoActivityHealthNew.class);
                        iihel.putExtra(AppConstants.KEY_DETAILS_HEALTH, nullhel);
                        MyInfoWindow.this.con.startActivity(iihel);

                        break;
                    case AppConstants.ENTERTAINMENT:
                        //Toast.makeText(MyInfoWindow.this.con, "Tap on (" + pp.getLatitude() + "," + pp.getLongitude() + ")", Toast.LENGTH_SHORT).show();
                        layout.setVisibility(View.VISIBLE);
                        EntertainmentServiceProviderTable entertainmentServiceProviderTable = new EntertainmentServiceProviderTable(MyInfoWindow.this.con);
                        nullent = entertainmentServiceProviderTable.getentNode2(node);
                        Intent iientt = new Intent(MyInfoWindow.this.con, DetailsInfoActivityEntertainmentNew.class);
                        iientt.putExtra(AppConstants.KEY_DETAILS_ENT, nullent);
                        MyInfoWindow.this.con.startActivity(iientt);

                        break;
                    case AppConstants.LEGAL:
                      //  Toast.makeText(MyInfoWindow.this.con, "Tap on (" + pp.getLatitude() + "," + pp.getLongitude() + ")", Toast.LENGTH_SHORT).show();
                        layout.setVisibility(View.VISIBLE);
                        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(MyInfoWindow.this.con);
                        nullleg = legalAidServiceProviderTable.getlegNode2(node);
                        Intent iileg = new Intent(MyInfoWindow.this.con, DetailsInfoActivityLegalNew.class);
                        iileg.putExtra(AppConstants.KEY_DETAILS_LEGAL, nullleg);
                        MyInfoWindow.this.con.startActivity(iileg);

                        break;
                    case AppConstants.FINANCIAL:
                       // Toast.makeText(MyInfoWindow.this.con, "Tap on (" + pp.getLatitude() + "," + pp.getLongitude() + ")", Toast.LENGTH_SHORT).show();
                        layout.setVisibility(View.VISIBLE);
                        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(MyInfoWindow.this.con);
                        nullfin = financialServiceProviderTable.getfinNode2(node);
                        Intent iifin = new Intent(MyInfoWindow.this.con, DetailsInfoActivityFinancialNew.class);
                        iifin.putExtra(AppConstants.KEY_DETAILS_FINANCIAL, nullfin);
                        MyInfoWindow.this.con.startActivity(iifin);

                        break;
                }

            }
        });
    }
}
