package demo.kolorob.kolorobdemoversion.utils;
import org.osmdroid.util.GeoPoint;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by touhid on 10/29/15.
 *
 * @author touhid
 */
public class AppConstants {

    public static final String API_URL = "http://kolorob.net/KolorobApi/api/";

    public static final int[] ALL_CAT_ICONS_NEW = {R.drawable.ic_education_selected, R.drawable.ic_health_selected,R.drawable.ic_entertainment_selected,
            R.drawable.ic_government_selected,R.drawable.ic_legal_selected,  R.drawable.ic_finance_selected ,
            R.drawable.ic_ngos_selected,R.drawable.shelter_selected
    };

    /*public static final int[] ALL_CAT_ICONS_NEW = {R.drawable.education, R.drawable.health,R.drawable.entertainment,
            R.drawable.government,R.drawable.legal,  R.drawable.finance,R.drawable.job
    };*/


    public static final int[] ALL_CAT_MARKER_ICONSBUTTON = {R.drawable.pin_1,R.drawable.pin_2,R.drawable.pin_3,
            R.drawable.pin_4,R.drawable.pin_5,R.drawable.pin_6,R.drawable.pin_7,R.drawable.pin_8,
            R.drawable.pin_9,R.drawable.pin_10};

    public static final int[] ALL_CAT_MARKER_ICONSBUTTON2 = {R.drawable.pin_1_selected,R.drawable.pin_2_selected,R.drawable.pin_3_selected,
            R.drawable.pin_4_selected,R.drawable.pin_5_selected,R.drawable.pin_6_selected,R.drawable.pin_7_selected,R.drawable.pin_8_selected,
            R.drawable.pin_9_selected,R.drawable.pin_10_selected};



    public static final GeoPoint BAUNIA1 = new GeoPoint(23.8185747,90.3742368);
    public static final GeoPoint PARIS1 = new GeoPoint(23.8105392,90.371817);
    public static final GeoPoint TWELVE = new GeoPoint(23.8254095,90.3710484);



    public static final double CAT_LIST_LG_WIDTH_PERC = 0.15;
    public static final double CAT_LIST_SM_WIDTH_PERC = 0.11;


    //region category IDs
    public static final int CAT_EDU = 101;

    //endregion
    //region sub-category IDs

    // TODO Declare other sub-category IDs
    //endregion

    //region Keys of the activity data-passing extras

    public static final String KEY_PLACE = "place";
    public static final String BAUNIABADH = "মিরপুর ১১";

    public static final String PARIS_ROAD = "মিরপুর ১০";
    public static final String MIRPUR_TWELVE = "মিরপুর ১২";

    public static final int PLACE_BAUNIABADH = 1;
    public static final int PLACE_PARIS_ROAD = 2;
    public static final int PLACE_MIRPUR_12 = 3;
    //endregion

    //region Server status codঁধ

    public static final int SUCCESS_CODE = 101;

    public static final int ERR_VOLLEY_CODE = -110;

    public static final String KEY_STATUS = "status";
    public static final String KEY_DATA = "data";
    public static final String KEY_SUCCESS = "success";

    //endregion

    public static final String KEY_DETAILS_JOB = "jobprovider";
    public static final String KEY_DETAILS_ENT = "entProvider";
    public static final String KEY_DETAILS_VIEW = "eduProvider";
    public static final String KEY_DETAILS_GOV = "govProvider";
    public static final String KEY_DETAILS_EDU= "eduProviderNew";
    public static final String KEY_DETAILS_HEALTH = "heaProvider";
    public static final String KEY_DETAILS_HEALTH_NEW = "heaProvider_new";
    public static final String KEY_DETAILS_LEGAL = "legalaidprovider";
    public static final String KEY_DETAILS_FINANCIAL = "financialprovider";
    public static final String KEY_DETAILS_FINANCIALNEW = "financialprovider";
    public static final String KEY_DETAILS_NGO = "ngoprovider";
    public static final String KEY_DETAILS_RELIGIOUS = "religiousprovider";

    public static final int EDUCATION = 10000;
    public static final int HEALTH = 20000;
    public static final int ENTERTAINMENT = 30000;
    public static final int GOVERNMENT = 40000;
    public static final int LEGAL = 50000;
    public static final int FINANCIAL = 60000;
    public static final int NGO = 70000;
    public static final int RELIGIOUS = 80000;

    public static final int MAP_BUTTON = 1;
    public static final int JOB_BUTTON = 2;
    public static final int SEARCH_BUTTON = 3;
    public static final int COMPARE_BUTTON = 4;

    public static final String EDU_API = "Education";
    public static final String HEALTH_API = "Health";
    public static final String ENTERTAINMENT_API = "Entertainment";
    public static final String GOVERNMENT_API = "Government";
    public static final String LEGAL_API = "Legal";
    public static final String FINANCE_API = "Finance";
    public static final String NGO_API = "NGO";
    public static final String SHELTER_API = "Religious Shelter";

    public static final String SCHOOL_API = "education_school";
    public static final String RESULT_API = "result_details";
    public static final String TRAINING_API = "training_details";

    public static final String HOSPITAL_API = "health_hospital";
    public static final String PHARMACY_API = "health_pharmacy";

    public static final String CC_API = "city_corporation";
    public static final String WARD_API = "ward";
    public static final String AREA_API = "areas";

}
