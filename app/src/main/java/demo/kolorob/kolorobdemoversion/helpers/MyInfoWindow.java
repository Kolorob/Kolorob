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
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutEducation;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutFinance;
import demo.kolorob.kolorobdemoversion.database.Education.EducationNewTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceNewTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTableNew;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by HP on 5/14/2016.
 */
public class MyInfoWindow extends InfoWindow {
    String titlemarker,contact2,node,address;
    int n;
    EducationServiceProviderItem nulledu;
    EducationNewItem nulledu2;
    HealthServiceProviderItem nullhel;
    EntertainmentServiceProviderItem nullent;
    FinancialNewItem nullfin;
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
    public MyInfoWindow(int layoutResId, MapView mapView, Activity con, GeoPoint point, String title, String contact, int Node, int categoryid,String add) {
        super(layoutResId, mapView);
        this.con=con;
        this.pp=point;
        this.titlemarker=title;
        this.contact2=contact;
        this.n=Node;
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
                         EducationNewTable educationNewTable = new EducationNewTable(MyInfoWindow.this.con);
                        nulledu2 = educationNewTable.geteduNode2(n);
                        Intent iiedu = new Intent(MyInfoWindow.this.con, DetailsLayoutEducation.class);
                        iiedu.putExtra(AppConstants.KEY_DETAILS_EDU, nulledu2);
                        MyInfoWindow.this.con.startActivity(iiedu);

                        break;
                    case AppConstants.HEALTH:
                        //Toast.makeText(MyInfoWindow.this.con, "Tap on (" + pp.getLatitude() + "," + pp.getLongitude() + ")", Toast.LENGTH_SHORT).show();
                        layout.setVisibility(View.VISIBLE);
                        HealthServiceProviderTableNew healthServiceProviderTable = new HealthServiceProviderTableNew(MyInfoWindow.this.con);
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
                        FinancialServiceNewTable financialServiceNewTable = new FinancialServiceNewTable(MyInfoWindow.this.con);
                        nullfin = financialServiceNewTable.getfinNode2(n);
                        Intent iifin = new Intent(MyInfoWindow.this.con, DetailsLayoutFinance.class);
                        iifin.putExtra(AppConstants.KEY_DETAILS_FINANCIALNEW, nullfin);
                        MyInfoWindow.this.con.startActivity(iifin);

                        break;
                }

            }
        });
    }
}
