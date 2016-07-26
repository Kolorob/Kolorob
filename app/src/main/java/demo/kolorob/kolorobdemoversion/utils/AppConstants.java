package demo.kolorob.kolorobdemoversion.utils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import org.osmdroid.util.GeoPoint;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by touhid on 10/29/15.
 *
 * @author touhid
 */
public class AppConstants {

    public static final String API_URL = "http://kolorob.net/KolorobApi/api/";

    public static final String[] ALL_CAT_BN = {"পড়াশুনা", "আনন্দ ফুর্তি", "সরকারী সুবিধা", "চিকিৎসা", "চাকরি বাকরি",
            "আইন কানুন", "টাকা পয়সা"};
    public static final String[] ALL_CAT_DETAIL_BN = {"some details on পড়াশুনা", "some details on আনন্দ ফুর্তি",
            "some details on সরকারী সুবিধা", "some details on চিকিৎসা", "some details on চাকরি বাকরি",
            "some details on আইন কানুন", "some details on টাকা পয়সা"};
    public static final int[] ALL_CAT_ICONS = {R.drawable.but_porashuna_small, R.drawable.but_chikitsha_small,R.drawable.but_ananda_small,
            R.drawable.but_shorkari_small,R.drawable.but_ain_small,  R.drawable.but_taka_small,R.drawable.but_chakri_small
            };
    public static final int[] OFF_BUTTON = {R.drawable.off_button};
    public static final int[] ALL_CAT_ICONS_NEW = {R.drawable.education, R.drawable.health,R.drawable.entertainment,
            R.drawable.government,R.drawable.legal,  R.drawable.finance,R.drawable.job
    };
//    public static final int[] ALL_CAT_ICONS_NEW_OPEN = {R.drawable.turned_on_porashona, R.drawable.turned_on_chikitsha,R.drawable.turned_on_anondo_furti,
//            R.drawable.turned_on_shorkari_shubidha,R.drawable.turned_on_ain_kanun,  R.drawable.turned_on_taka_poisha,R.drawable.turned_on_chakri_bakri
//    };
public static final int[] ALL_CAT_ICONS_NEW_OPEN = {R.drawable.ic_continue, R.drawable.ic_continue,R.drawable.ic_continue,
        R.drawable.ic_continue,R.drawable.ic_continue,  R.drawable.ic_continue,R.drawable.ic_continue
};
    public static final int[] ALL_CAT_MARKER_ICONSBUTTON = {R.drawable.pin_1,R.drawable.pin_2,R.drawable.pin_3,
            R.drawable.pin_4,R.drawable.pin_5,R.drawable.pin_6,R.drawable.pin_7,R.drawable.pin_8,
            R.drawable.pin_9,R.drawable.pin_10};
    public static final int[] ALL_CAT_MARKER_ICONS1 = {R.drawable.pin_map_1,R.drawable.pin_map_2,R.drawable.pin_map_3,
            R.drawable.pin_map_4,R.drawable.pin_map_5,R.drawable.pin_map_6,R.drawable.pin_map_7,R.drawable.pin_map_8,
            R.drawable.pin_map_9,R.drawable.pin_map_10};
    public static final float[] MARKER_HUE_COLOR = {BitmapDescriptorFactory.HUE_RED,BitmapDescriptorFactory.HUE_BLUE,
            BitmapDescriptorFactory.HUE_RED,(float)300,BitmapDescriptorFactory.HUE_GREEN,
            (float)324.8,(float)168.8,BitmapDescriptorFactory.HUE_YELLOW,BitmapDescriptorFactory.HUE_ORANGE};

    public static final String[] SUB_CAT_EDU_BN = {"স্কুল-কলেজ", "মাদ্রাসা", "কারিগরি", "মেডিকেল", "অন্যান্য"};
    public static final String[] SUB_CAT_FUN_BN = {"খেলার মাঠ", "সাংস্কৃতিক", "ভ্রমণ", "ইলেক্ট্রনিক্স", "অন্যান্য"};
    public static final String[] SUB_CAT_GOVT_BN = {"উপযোগ", "সরকারী অফিস", "জরুরী", "অন্যান্য"};
    public static final String[] SUB_CAT_HEALTH_BN = {"ক্লিনিক", "হসপিটাল", "ফার্মেসি", "অন্যান্য"};
    public static final String[] SUB_CAT_JOB_BN = {"sub-category-1", "sub-category-2", "sub-category-3"};
    public static final String[] SUB_CAT_LAW_BN = {"আইনজীবী", "আইন-কেন্দ্র", "সালিশ কেন্দ্র"};
    public static final String[] SUB_CAT_MONEY_BN = {"sub-category-1", "sub-category-2", "sub-category-3"};

    public static final GeoPoint BAUNIA1 = new GeoPoint(23.8197971,90.3803564);
    public static final GeoPoint PARIS1 = new GeoPoint(23.8135117,90.3707107);
    public static final LatLng BAUNIA = new LatLng(23.8197971,90.3803564);
    public static final LatLng PARIS = new LatLng(23.8135117,90.3707107);
    public static final String[][] SUB_CATEGORIES = {SUB_CAT_EDU_BN, SUB_CAT_FUN_BN, SUB_CAT_GOVT_BN,
            SUB_CAT_HEALTH_BN, SUB_CAT_JOB_BN, SUB_CAT_LAW_BN, SUB_CAT_MONEY_BN};

    public static final double CAT_LIST_LG_WIDTH_PERC = 0.15;
    public static final double CAT_LIST_LG_WIDTH_PERC_NEW = 0.20;
    public static final double CAT_LIST_SM_WIDTH_PERC = 0.11;


    //region category IDs
    public static final int CAT_EDU = 101;
    public static final int CAT_FUN = 102;
    public static final int CAT_GOVT = 103;
    public static final int CAT_HEALTH = 104;
    public static final int CAT_JOB = 105;
    public static final int CAT_LAW = 106;
    public static final int CAT_MONEY = 107;
    public static final int CAT_BASE = CAT_EDU;
    public static final int CAT_INVALID = -100;
    //endregion
    //region sub-category IDs
    public static final int SUB_CAT_EDU_SCHOOL_COLLEGE = 10101;
    public static final int SUB_CAT_EDU_MADRASA = 10102;
    public static final int SUB_CAT_EDU_VOCATIONAL = 10103;
    public static final int SUB_CAT_EDU_MEDICAL = 10104;
    public static final int SUB_CAT_EDU_OTHERS = 10105;

    public static final int SUB_CAT_FUN_FIELD = 10201;
    public static final int SUB_CAT_FUN_CULT = 10202;
    public static final int SUB_CAT_FUN_TOUR = 10203;
    public static final int SUB_CAT_FUN_ELECTRONICS = 10204;
    public static final int SUB_CAT_FUN_OTHERS = 10205;

    public static final int SUB_CAT_GOVT_UTIL = 10301;
    public static final int SUB_CAT_GOVT__OFC = 10302;
    public static final int SUB_CAT_GOVT_EMRGENCY = 10303;
    public static final int SUB_CAT_GOVT_OTHERS = 10304;
    // TODO Declare other sub-category IDs
    //endregion

    //region Keys of the activity data-passing extras
    public static final String KEY_CAT_OBJ = "category_object";
    public static final String KEY_PLACE = "place";
    public static final String BAUNIABADH = "মিরপুর ১১";
    public static final String WAITTAG = "একটু অপেক্ষা করুন";
    public static final String WAITDET = "তথ্য সংগ্রহ হচ্ছে.....";
    public static final String PARIS_ROAD = "মিরপুর ১০";
    public static final int PLACE_BAUNIABADH = 1;
    public static final int PLACE_PARIS_ROAD = 2;
    //endregion

    //region Server status codঁধ

    public static final int SUCCESS_CODE = 101;
    public static final int ERR_CODE = -101;
    public static final int ERR_VOLLEY_CODE = -110;

    public static final String KEY_STATUS = "status";
    public static final String KEY_DATA = "data";
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_ERROR = "error";
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
    public static final int EDUCATION= 1;
    public static final int HEALTH = 2;
    public static final int ENTERTAINMENT = 3;
    public static final int GOVERNMENT = 4;
    public static final int LEGAL = 5;
    public static final int FINANCIAL = 6;
    public static final int JOB = 7;

}
