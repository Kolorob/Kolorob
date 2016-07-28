package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Arafat on 21 July.

 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityEntertainmentNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityHealthNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsInfoActivityLegalNew;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutEducation;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutFinance;
import demo.kolorob.kolorobdemoversion.activity.DetailsLayoutGovernment;
import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class ServiceListDisplayAdapter extends BaseExpandableListAdapter {

    private final Vector<Group> groups;
    private Object object;
    private long longs;
    private int size;


    public LayoutInflater inflater;
    public Activity activity;
    private int catid;
    private Context ctx;
    private LinearLayout linearLayout;

    public ServiceListDisplayAdapter(Activity act, Vector<Group> groups,int categoryid) {
        activity = act;
        this.groups = groups;

        inflater = act.getLayoutInflater();
        this.catid=categoryid;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        if (catid==AppConstants.EDUCATION)
            object= groups.get(groupPosition).getchildren().get(childPosition);
        else if(catid==AppConstants.ENTERTAINMENT)
            object= groups.get(groupPosition).getChildrenent().get(childPosition);
        else if (catid==AppConstants.HEALTH)
            object= groups.get(groupPosition).getChildrenhea().get(childPosition);
        else if(catid==AppConstants.FINANCIAL)
            object= groups.get(groupPosition).getChildrenfin().get(childPosition);
        else if (catid==AppConstants.LEGAL)

            object= groups.get(groupPosition).getChildrenleg().get(childPosition);

        else if(catid==AppConstants.GOVERNMENT)
            object= groups.get(groupPosition).getChildrengov().get(childPosition);
        return object;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if (catid==AppConstants.EDUCATION)
            longs= groups.get(groupPosition).getchildren().get(childPosition).hashCode();
        else if(catid==AppConstants.ENTERTAINMENT)
            longs= groups.get(groupPosition).getChildrenent().get(childPosition).hashCode();
        if (catid==AppConstants.HEALTH)
            longs= groups.get(groupPosition).getChildrenhea().get(childPosition).hashCode();
        else if(catid==AppConstants.FINANCIAL)
            longs= groups.get(groupPosition).getChildrenfin().get(childPosition).hashCode();
        if (catid==AppConstants.LEGAL)
            longs= groups.get(groupPosition).getChildrenleg().get(childPosition).hashCode();

        else if(catid==AppConstants.GOVERNMENT)
            longs= groups.get(groupPosition).getChildrengov().get(childPosition).hashCode();
        return longs;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
//        final String children = (String) getChild(groupPosition, childPosition);
        final String name;
        TextView text = null;
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.listrow_details, null);
        }
        linearLayout=(LinearLayout)v.findViewById(R.id.row_view);
        switch (catid) {
            case AppConstants.EDUCATION:
                final EducationNewItem det = groups.get(groupPosition).getchildren().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(det.getNamebn());
                linearLayout.setBackgroundResource(R.color.education_color);


                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent ii = new Intent(getActivity(), DetailsLayoutEducation.class);
                        ii.putExtra(AppConstants.KEY_DETAILS_EDU, det);
                        activity.startActivity(ii);


                    }


                });
                break;
            case AppConstants.ENTERTAINMENT:
                final EntertainmentServiceProviderItemNew detent = groups.get(groupPosition).getChildrenent().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detent.getNodeNameBn());
                linearLayout.setBackgroundResource(R.color.entertainment_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iient = new Intent(getActivity(), DetailsInfoActivityEntertainmentNew.class);
                        iient.putExtra(AppConstants.KEY_DETAILS_ENT, detent);
                        activity.startActivity(iient);


                    }
                });
                break;
            case AppConstants.HEALTH:
                final HealthServiceProviderItemNew dethea = groups.get(groupPosition).getChildrenhea().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(dethea.getNode_name());
                linearLayout.setBackgroundResource(R.color.health_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iihea = new Intent(getActivity(), DetailsInfoActivityHealthNew.class);
                        iihea.putExtra(AppConstants.KEY_DETAILS_HEALTH_NEW, dethea);
                        activity.startActivity(iihea);


                    }


                });
                break;
            case AppConstants.FINANCIAL:
                final FinancialNewItem detfin = groups.get(groupPosition).getChildrenfin().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detfin.getNamebn());
                linearLayout.setBackgroundResource(R.color.financial_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iifin = new Intent(getActivity(), DetailsLayoutFinance.class);
                        iifin.putExtra(AppConstants.KEY_DETAILS_FINANCIALNEW, detfin);
                        activity.startActivity(iifin);

                    }
                });
                break;
            case AppConstants.LEGAL:
                final LegalAidServiceProviderItemNew detleg = groups.get(groupPosition).getChildrenleg().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detleg.getLegalaidNameBan());
                linearLayout.setBackgroundResource(R.color.legal_aid_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iileg = new Intent(getActivity(), DetailsInfoActivityLegalNew.class);
                        iileg.putExtra(AppConstants.KEY_DETAILS_LEGAL, detleg);
                        activity.startActivity(iileg);


                    }


                });
                break;


            case AppConstants.GOVERNMENT:
                final GovernmentNewItem detgov = groups.get(groupPosition).getChildrengov().get(childPosition);
                text = (TextView) v.findViewById(R.id.textView1);
                text.setText(detgov.getAddress());
                linearLayout.setBackgroundResource(R.color.government_color);
                v.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent iijob = new Intent(getActivity(), DetailsLayoutGovernment.class);
                        iijob.putExtra(AppConstants.KEY_DETAILS_GOV, detgov);
                        activity.startActivity(iijob);


                    }


                });
                break;
            default:break;
        }

        return v;
    }




    @Override
    public int getChildrenCount(int groupPosition) {
        if (catid==AppConstants.EDUCATION)
            size= groups.get(groupPosition).children.size();
        else if(catid==AppConstants.ENTERTAINMENT)
            size= groups.get(groupPosition).childrenent.size();
        else if (catid==AppConstants.HEALTH)
            size= groups.get(groupPosition).childrenhea.size();
        else if(catid==AppConstants.FINANCIAL)
            size= groups.get(groupPosition).childrenfin.size();
        else  if (catid==AppConstants.LEGAL)
            size= groups.get(groupPosition).childrenleg.size();

        else if(catid==AppConstants.GOVERNMENT)
            size= groups.get(groupPosition).childrengov.size();
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_groups, null);
        }
        Group group = (Group) getGroup(groupPosition);
        Log.d(">>>>>>","Group Value "+catid);

        if(catid==1)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.education_color);
        else if(catid==2)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.health_color);
        else if(catid==3)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.entertainment_color);
        else if(catid==5)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.legal_aid_color);
        else if(catid==6)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.financial_color);
        else if(catid==4)
            ((CheckedTextView) convertView).setBackgroundResource(R.color.government_color);


        //
        if(group.string.equals("Govt. Primary School "))
            ((CheckedTextView) convertView).setText("সরকারী প্রাইমারী স্কুল (Govt. Primary School)");

        else if(group.string.equals("Day Care Centres"))
            ((CheckedTextView) convertView).setText("ডে কেয়ার সেন্টার(Day Care Centres)");
        else if(group.string.equals("Kindergarten"))
            ((CheckedTextView) convertView).setText("কিন্ডারগার্ডেন (Kindergarten)");
        else if(group.string.equals("NGO Schools"))
            ((CheckedTextView) convertView).setText("এনজিও স্কুল (NGO Schools)");
        else if(group.string.equals("Govt. High School"))
            ((CheckedTextView) convertView).setText("সরকারী উচ্চ বিদ্যালয় (Govt. High School)");
        else if(group.string.equals("Teachers Training College"))
            ((CheckedTextView) convertView).setText("শিক্ষকদের প্রশিক্ষন কলেজ (Teachers Training College)");
        else if(group.string.equals("Private School"))
            ((CheckedTextView) convertView).setText("প্রাইভেট স্কুল (Private School)");
        else if(group.string.equals("English Medium School"))
            ((CheckedTextView) convertView).setText("ইংলিশ মিডিয়াম  স্কুল (English Medium School)");
        else if(group.string.equals("Govt. College"))
            ((CheckedTextView) convertView).setText("সরকারি কলেজ (Govt. College)");
        else if(group.string.equals("Private College"))
            ((CheckedTextView) convertView).setText("প্রাইভেট কলেজ (Private College)");

        else if(group.string.equals("Women College"))
            ((CheckedTextView) convertView).setText("মহিলা কলেজ (Women College)");
        else if(group.string.equals("Welfare/Trust School"))
            ((CheckedTextView) convertView).setText("বৃত্তি/ কল্যাণমূলক স্কুল (Welfare/Trust School)");
        else if(group.string.equals("MPO Registered School"))
            ((CheckedTextView) convertView).setText("এমপিও ভুক্ত স্কুল (MPO Registered School)");
        else if(group.string.equals("Alia Madrasah"))
            ((CheckedTextView) convertView).setText("আলিয়া মাদ্রাসা (Alia Madrasah)");
        else if(group.string.equals("Kowmi Madrasah"))
            ((CheckedTextView) convertView).setText("কওমী মাদ্রাসা  (Kowmi Madrasah)");
        else if(group.string.equals("Hafezia Madrasah"))
            ((CheckedTextView) convertView).setText("হাফিজিয়া মাদ্রাসা  (Hafezia Madrasah)");
        else if(group.string.equals("Orphanage"))
            ((CheckedTextView) convertView).setText("এতিম খানা (Orphanage)");
        else if(group.string.equals("Moktob"))
            ((CheckedTextView) convertView).setText("মক্তব (Moktob)" );
        else if(group.string.equals("Polytechnique Institutes"))
            ((CheckedTextView) convertView).setText("পলিটেকনিক ইন্সটিটিউট (Polytechnique Institutes)");
        else if(group.string.equals("Vocational Training Centres"))
            ((CheckedTextView) convertView).setText("কারিগরি প্রশিক্ষন কেন্দ্র  (Vocational Training Centres)");
        else if(group.string.equals("Medical College"))
            ((CheckedTextView) convertView).setText("মেডিকেল কলেজ (Medical College)");
        else if(group.string.equals("Nursing Institutes"))
            ((CheckedTextView) convertView).setText("নার্সিং ইন্সটিটিউট (Nursing Institutes)");
        else if(group.string.equals("Language Institutes"))
            ((CheckedTextView) convertView).setText("ভাষা শিক্ষা প্রতিষ্ঠান (Language Institutes)");
        else if(group.string.equals("Coaching Centres"))
            ((CheckedTextView) convertView).setText("কোচিং সেন্টার  (Coaching Centres)");
        else if(group.string.equals("Mosques"))
            ((CheckedTextView) convertView).setText("মসজিদ (Mosques)");
        else if(group.string.equals("NGO Office"))
            ((CheckedTextView) convertView).setText("এনজিও অফিস (NGO Office)");

        else if(group.string.equals("NGO Clinic"))
            ((CheckedTextView) convertView).setText("এনজিও ক্লিনিক  (NGO Clinic)");
        else if(group.string.equals("Vaccine Centre"))
            ((CheckedTextView) convertView).setText("প্রতিষেধক/ টিক দেয়ার কেন্দ্র (Vaccine Centre)");
        else if(group.string.equals("Delivery Centre"))
            ((CheckedTextView) convertView).setText("সন্তান জন্মদান কেন্দ্র (Delivery Centre)");
        else if(group.string.equals("Dentist Chamber"))
            ((CheckedTextView) convertView).setText("দন্ত চিকিৎসকের চেম্বার (Dentist Chamber)");
        else if(group.string.equals("Satelite Clinic"))
            ((CheckedTextView) convertView).setText("স্যাটেলাইট ক্লিনিক  (Satelite Clinic)");
        else if(group.string.equals("Drug Rehabilitation Centre"))
            ((CheckedTextView) convertView).setText("মাদকাশক্তি পুনর্বাসন কেন্দ্র (Drug Rehabilitation Centre)");
        else if(group.string.equals("Diagonostic Centre"))
            ((CheckedTextView) convertView).setText("রোগনির্ণয় কেন্দ্র (Diagonostic Centre)");


        else if(group.string.equals("Private Hospital"))
            ((CheckedTextView) convertView).setText("প্রাইভেট হাসপাতাল (Private Hospital)");
        else if(group.string.equals("Maternity Hospital"))
            ((CheckedTextView) convertView).setText("মাতৃ হাসপাতাল (Maternity Hospital)");
        else if(group.string.equals("Govt. Hospital"))
            ((CheckedTextView) convertView).setText("সরকারি হাসপাতাল  (Govt. Hospital)");
        else if(group.string.equals("Eye Hospital"))
            ((CheckedTextView) convertView).setText("চক্ষু হাসপাতাল (Eye Hospital)");
        else if(group.string.equals("Mental Hospital"))
            ((CheckedTextView) convertView).setText("মানসিক হাসপাতাল (Mental Hospital)");



        else if(group.string.equals("Pharmacy(MBBS)"))
            ((CheckedTextView) convertView).setText("ফার্মেসি (ডাক্তার এম বি বি এস)  (Pharmacy(MBBS))");
        else if(group.string.equals("Pharmacy (LMAF)"))
            ((CheckedTextView) convertView).setText("ফার্মেসি(ডাক্তার এল এম এ এফ) (Pharmacy (LMAF))");
        else if(group.string.equals("Pharmacy(Medicine Only)"))
            ((CheckedTextView) convertView).setText("ফার্মেসী ( শুধুমাত্র ঔষধ)  (Pharmacy(Medicine Only))");



        else if(group.string.equals("Harbal"))
            ((CheckedTextView) convertView).setText("ভেষজ (Harbal)");
        else if(group.string.equals("Dental Chamber"))
            ((CheckedTextView) convertView).setText("দাতের চিকিৎসক চেম্বার (Dental Chamber)");
        else if(group.string.equals("Diabetic Society"))
            ((CheckedTextView) convertView).setText("ডায়বেটিকস সোসাইটি  (Diabetic Society)");
        else if(group.string.equals("Homeopathy"))
            ((CheckedTextView) convertView).setText("হোমিওপ্যাথি (Homeopathy)");
        else if(group.string.equals("Ayurbedhik"))
            ((CheckedTextView) convertView).setText("আয়ুর্বেদিক  (Ayurbedhik)");


        else if(group.string.equals("Para Medical"))
            ((CheckedTextView) convertView).setText("প্যারামেডিকেল (Para Medical)");


        else if(group.string.equals("Vetenary"))
            ((CheckedTextView) convertView).setText("পশু  চিকিৎসা কেন্দ্র (Vetenary)");


        else if(group.string.equals("ATM Booth"))
            ((CheckedTextView) convertView).setText("এ টি এম (ATM Booth)");
        else if(group.string.equals("Bank"))
            ((CheckedTextView) convertView).setText("ব্যাংক (Bank)");
        else if(group.string.equals("Courier Service"))
            ((CheckedTextView) convertView).setText("কুরিয়ার সার্ভিস (Courier Service)");
        else if(group.string.equals("Post Office"))
            ((CheckedTextView) convertView).setText("পোস্ট অফিস  (Post Office)");



        else if(group.string.equals("bKash"))
            ((CheckedTextView) convertView).setText("বিকাশ (bKash)");
        else if(group.string.equals("DBBL Banking"))
            ((CheckedTextView) convertView).setText("ডাচ বাংলা ব্যাঙ্কিং (DBBL Banking)");
        else if(group.string.equals("Sure Cash"))
            ((CheckedTextView) convertView).setText("সিউর ক্যাশ (Sure Cash)");
        else if(group.string.equals("Mobi Cash"))
            ((CheckedTextView) convertView).setText("মোবি ক্যাশ (Mobi Cash)");
        else if(group.string.equals("Ucash"))
            ((CheckedTextView) convertView).setText("ইউক্যাশ  (Ucash)");
        else if(group.string.equals("MyCash"))
            ((CheckedTextView) convertView).setText("মাইক্যাশ (MyCash)");

        else if(group.string.equals("DESCO Bill Pay Cenre"))
            ((CheckedTextView) convertView).setText("ডেসকো বিদ্যুৎ বিল পরিশোধ সেন্টার  (DESCO Bill Pay Cenre)");
        else if(group.string.equals("Grameenphone Bill Pay"))
            ((CheckedTextView) convertView).setText("গ্রামীণফোন বিল পরিশোধ সেন্টার (Grameenphone Bill Pay)");
        else if(group.string.equals("Robi Bill Pay"))
            ((CheckedTextView) convertView).setText("রবি বিল পরিশোধ সেন্টার  (Robi Bill Pay)");
        else if(group.string.equals("Banglalink Point"))
            ((CheckedTextView) convertView).setText("বাংলালিংক পয়েন্ট (Banglalink Point)");

        else if(group.string.equals("Life Insurance"))
            ((CheckedTextView) convertView).setText("জীবন বীমা  (Life Insurance)");
        else if(group.string.equals("Vehicle Insurance"))
            ((CheckedTextView) convertView).setText("যানবাহন বীমা (Vehicle Insurance)");
        else if(group.string.equals("Property Insurance"))
            ((CheckedTextView) convertView).setText("সম্পত্তি বীমা  (Property Insurance)");



        else if(group.string.equals("Cooperative Society (Shomity)"))
            ((CheckedTextView) convertView).setText("সমবায় সমিতি  (Cooperative Society (Shomity))");
//            else if(group.string.equals("NGO"))
//                ((CheckedTextView) convertView).setText("এন জি ও (NGO)");
        else if(group.string.equals("Personal Loan Giver"))
            ((CheckedTextView) convertView).setText("ব্যক্তিগতভাবে লোন দাতা  (Personal Loan Giver)");

        else if(group.string.equals("Hundi"))
            ((CheckedTextView) convertView).setText("হুন্ডি (Hundi)");



        else if(group.string.equals("Playground"))
            ((CheckedTextView) convertView).setText("খেলার মাঠ  (Playground)");
        else if(group.string.equals("Dance Tuition"))
            ((CheckedTextView) convertView).setText("নাচের পাঠশালা  (Dance Tuition)");


        else if(group.string.equals("Singing Tuition"))
            ((CheckedTextView) convertView).setText("গানের পাঠশালা   (Singing Tuition)");
        else if(group.string.equals("Art Cenre"))
            ((CheckedTextView) convertView).setText("আর্ট সেন্টার (Art Cenre)");


        else if(group.string.equals("Events"))
            ((CheckedTextView) convertView).setText("অনুষ্ঠান  (Events)");
        else if(group.string.equals("Clubs"))
            ((CheckedTextView) convertView).setText("ক্লাব (Clubs)");
        else if(group.string.equals("Musical Bands"))
            ((CheckedTextView) convertView).setText("গানের ব্যান্ড  (Musical Bands)");
        else if(group.string.equals("NGO"))
            ((CheckedTextView) convertView).setText("এন জি ও (NGO)");
        else if(group.string.equals("Cultural Groups"))
            ((CheckedTextView) convertView).setText("সংস্কৃতিক গ্রুপ (Cultural Groups)");
        else if(group.string.equals("Theatre Groups"))
            ((CheckedTextView) convertView).setText("নাট্যদল  (Theatre Groups)");

        else if(group.string.equals("Park"))
            ((CheckedTextView) convertView).setText("পার্ক (Park)");
        else if(group.string.equals("Zoo"))
            ((CheckedTextView) convertView).setText("চিড়িয়াখানা (Zoo)");
        else if(group.string.equals("Lake"))
            ((CheckedTextView) convertView).setText("হ্রদ(Lake)");
        else if(group.string.equals("Shishu Park"))
            ((CheckedTextView) convertView).setText("শিশু পার্ক (Shishu Park)");
        else if(group.string.equals("Cable Service Provider"))
            ((CheckedTextView) convertView).setText("ক্যাবল সুবিধা প্রদানকারী  (Cable Service Provider)");
        else if(group.string.equals("Internet Service Provider"))
            ((CheckedTextView) convertView).setText("ইন্টারনেট সুবিধা প্রদানকারি (Internet Service Provider)");

        else if(group.string.equals("Book Shop"))
            ((CheckedTextView) convertView).setText("বইয়ের দোকান (Book Shop)");
        else if(group.string.equals("Video Games"))
            ((CheckedTextView) convertView).setText("ভিডিও গেম  (Video Games)");
        else if(group.string.equals("Cyber Cafe"))
            ((CheckedTextView) convertView).setText("সাইবার ক্যাফে (Cyber Cafe)");
        else if(group.string.equals("Beauty Parlour(Gents)"))
            ((CheckedTextView) convertView).setText("বিউটি পারলার(পুরুষদের) (Beauty Parlour(Gents))");
        else if(group.string.equals("Beauty Parlour(Ladies)"))
            ((CheckedTextView) convertView).setText("বিউটি পারলার(মহিলাদের))   (Beauty Parlour(Ladies))");
        else if(group.string.equals("Gym"))
            ((CheckedTextView) convertView).setText("ব্যায়ামাগার   (Gym)");



        else if(group.string.equals("Day Labour Place"))
            ((CheckedTextView) convertView).setText("দিন মজুরদের স্থান  (Day Labour Place)");
        else if(group.string.equals("Word of Mouth"))
            ((CheckedTextView) convertView).setText("কারো মুখে শোনা (Word of Mouth)");
        else if(group.string.equals("Handicraft-Agent/Broker"))
            ((CheckedTextView) convertView).setText("হস্তশিল্পের এজেন্ট  (Handicraft-Agent\\/Broker)");
        else if(group.string.equals("Word of Mouth"))
            ((CheckedTextView) convertView).setText("কারো মুখে শোনা (Word of Mouth)");
        else if(group.string.equals("Banner"))
            ((CheckedTextView) convertView).setText("ব্যানারের মাধ্যমে   (Banner)");
        else if(group.string.equals("Leaflet"))
            ((CheckedTextView) convertView).setText("লিফলেটের মাধ্যমে (Leaflet)");
        else if(group.string.equals("Mosque Announcement"))
            ((CheckedTextView) convertView).setText("মসজিদের ঘোষনা  (Mosque Announcement)");
        else if(group.string.equals("Factory Gate"))
            ((CheckedTextView) convertView).setText("শিল্পকারখানার গেটে  (Factory Gate)");
        else if(group.string.equals("Newspaper"))
            ((CheckedTextView) convertView).setText("সংবাদপত্রের মাধ্যমে  (Newspaper)");
        else if(group.string.equals("Newspaper"))
            ((CheckedTextView) convertView).setText("সংবাদপত্রের মাধ্যমে  (Newspaper)");
        else if(group.string.equals("Weekly Chakrir Khobor"))
            ((CheckedTextView) convertView).setText("সাপ্তাহিক চাকুরীর খবর  (Weekly Chakrir Khobor)");
        else if(group.string.equals("BDjobs/Online/Prothom Alo jobs"))
            ((CheckedTextView) convertView).setText("বিডি জবস/ অনলাইন বা প্রথম জবস (BDjobs/Online/Prothom Alo jobs)");
        else if(group.string.equals("Word of Mouth"))
            ((CheckedTextView) convertView).setText("কারো মুখে শোনা(Word of Mouth)");
        else if(group.string.equals("Relative"))
            ((CheckedTextView) convertView).setText("আত্মীয় সজন  (Relative)");
        else if(group.string.equals("Website"))
            ((CheckedTextView) convertView).setText("ওয়েবসাইট  (Website)");
        else if(group.string.equals("Agent/Adam Byabshayee"))
            ((CheckedTextView) convertView).setText("আদম ব্যবসায়ী  (Agent/Adam Byabshayee)");
        else if(group.string.equals("Recruiting Agency"))
            ((CheckedTextView) convertView).setText("চাকুরিদাতা সংস্থা  (Recruiting Agency)");
        else if(group.string.equals("Man Power Bureau"))
            ((CheckedTextView) convertView).setText("জনশক্তি ব্যুরো  (Man Power Bureau)");
        else if(group.string.equals("Probashi Kolyan Bank"))
            ((CheckedTextView) convertView).setText("প্রবাসী কল্যাণ ব্যাংক   (Probashi Kolyan Bank)");
        else if(group.string.equals("Lawers"))
            ((CheckedTextView) convertView).setText("আইনজীবী (Lawers)");
        else if(group.string.equals("Salishi Personnel"))
            ((CheckedTextView) convertView).setText("ব্যক্তিগত সালিশী  (Salishi Personnel)");
        else if(group.string.equals("Document Writer"))
            ((CheckedTextView) convertView).setText("পত্র লেখক (Document Writer)");
        else if(group.string.equals("Legal Aid Centre"))
            ((CheckedTextView) convertView).setText("আইন সহায়তাকারী সেন্টার  (Legal Aid Centre)");
        else if(group.string.equals("Other Legal Help"))
            ((CheckedTextView) convertView).setText("অন্যন্য আইনগত সাহায্য  (Other Legal Help)");









        else
            ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public Activity getActivity() {
        return activity;
    }
}