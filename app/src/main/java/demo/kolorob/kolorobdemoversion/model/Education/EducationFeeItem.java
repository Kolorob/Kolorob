package demo.kolorob.kolorobdemoversion.model.Education;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


/**
 * Created by mity on 2/19/16.
 */
public class EducationFeeItem implements Serializable {
    private String   fee_id;
    private String   identifier_id;
    private String   pre_school_free;
    private String   pre_school_stipend_speciality;
    private String   pre_school_stipend_type;
    private String   pre_school_stipend_details;
    private String   pre_school_max_fee;
    private String   pre_school_min_fee;
    private String   pre_school_coaching_fee;
    private String   pre_school_additional_fee;
    private String    i_v_free;
    private String    i_v_stipend_speciality;
    private String    i_v_stipend_type;
    private String    i_v_stipend_details;
    private String   i_v_max_fee;
    private String   i_v_min_fee;
    private String    i_v_coaching_fee;
    private String    i_v_additional_fee;
    private String    vi_x_free;
    private String    vi_x_stipend_speciality;
    private String    vi_x_stipend_type;
    private String     vi_x_stipend_details;
    private String    vi_x_max_fee;
    private String    vi_x_min_fee;
    private String    vi_x_coaching_fee;
    private String    vi_x_additional_fee;
    private String    xi_xii_free;
    private String    xi_xii_stipend_speciality;
    private String    xi_xii_stipend_type;
    private String    xi_xii_stipend_details;
    private String     xi_xii_max_fee;
    private String    xi_xii_min_fee;
    private String     xi_xii_coaching_fee;
    private String     xi_xii_additional_fee;
    private String       uni_free;
    private String     uni_stipend_speciality;
    private String      uni_stipend_type;
    private String      uni_stipend_details;
    private String       uni_max_fee;
    private String       uni_min_fee;
    private String      uni_coaching_fee;
    private String      uni_additional_fee;

    public EducationFeeItem(String fee_id, String identifier_id, String pre_school_free, String pre_school_stipend_speciality,
                            String pre_school_stipend_type, String pre_school_stipend_details, String pre_school_max_fee,
                            String pre_school_min_fee, String pre_school_coaching_fee, String pre_school_additional_fee, String i_v_free,
                            String i_v_stipend_speciality, String i_v_stipend_type, String i_v_stipend_details, String i_v_max_fee,
                            String i_v_min_fee, String i_v_coaching_fee, String i_v_additional_fee, String vi_x_free,
                            String vi_x_stipend_speciality, String vi_x_stipend_type, String vi_x_stipend_details, String vi_x_max_fee,
                            String vi_x_min_fee, String vi_x_coaching_fee, String vi_x_additional_fee, String xi_xii_free,
                            String xi_xii_stipend_speciality, String xi_xii_stipend_type, String xi_xii_stipend_details, String xi_xii_max_fee,
                            String xi_xii_min_fee, String xi_xii_coaching_fee, String xi_xii_additional_fee, String uni_free,
                            String uni_stipend_speciality, String uni_stipend_type, String uni_stipend_details, String uni_max_fee,
                            String uni_min_fee, String uni_coaching_fee, String uni_additional_fee) {
        this.fee_id = fee_id;
        this.identifier_id = identifier_id;
        this.pre_school_free = pre_school_free;
        this.pre_school_stipend_speciality = pre_school_stipend_speciality;
        this.pre_school_stipend_type = pre_school_stipend_type;
        this.pre_school_stipend_details = pre_school_stipend_details;
        this.pre_school_max_fee = pre_school_max_fee;
        this.pre_school_min_fee = pre_school_min_fee;
        this.pre_school_coaching_fee = pre_school_coaching_fee;
        this.pre_school_additional_fee = pre_school_additional_fee;
        this.i_v_free = i_v_free;
        this.i_v_stipend_speciality = i_v_stipend_speciality;
        this.i_v_stipend_type = i_v_stipend_type;
        this.i_v_stipend_details = i_v_stipend_details;
        this.i_v_max_fee = i_v_max_fee;
        this.i_v_min_fee = i_v_min_fee;
        this.i_v_coaching_fee = i_v_coaching_fee;
        this.i_v_additional_fee = i_v_additional_fee;
        this.vi_x_free = vi_x_free;
        this.vi_x_stipend_speciality = vi_x_stipend_speciality;
        this.vi_x_stipend_type = vi_x_stipend_type;
        this.vi_x_stipend_details = vi_x_stipend_details;
        this.vi_x_max_fee = vi_x_max_fee;
        this.vi_x_min_fee = vi_x_min_fee;
        this.vi_x_coaching_fee = vi_x_coaching_fee;
        this.vi_x_additional_fee = vi_x_additional_fee;
        this.xi_xii_free = xi_xii_free;
        this.xi_xii_stipend_speciality = xi_xii_stipend_speciality;
        this.xi_xii_stipend_type = xi_xii_stipend_type;
        this.xi_xii_stipend_details = xi_xii_stipend_details;
        this.xi_xii_max_fee = xi_xii_max_fee;
        this.xi_xii_min_fee = xi_xii_min_fee;
        this.xi_xii_coaching_fee = xi_xii_coaching_fee;
        this.xi_xii_additional_fee = xi_xii_additional_fee;
        this.uni_free = uni_free;
        this.uni_stipend_speciality = uni_stipend_speciality;
        this.uni_stipend_type = uni_stipend_type;
        this.uni_stipend_details = uni_stipend_details;
        this.uni_max_fee = uni_max_fee;
        this.uni_min_fee = uni_min_fee;
        this.uni_coaching_fee = uni_coaching_fee;
        this.uni_additional_fee = uni_additional_fee;
    }

    public String getFee_id() {
        return fee_id;
    }

    public void setFee_id(String fee_id) {
        this.fee_id = fee_id;
    }

    public String getIdentifier_id() {
        return identifier_id;
    }

    public void setIdentifier_id(String identifier_id) {
        this.identifier_id = identifier_id;
    }

    public String getPre_school_free() {
        return pre_school_free;
    }

    public void setPre_school_free(String pre_school_free) {
        this.pre_school_free = pre_school_free;
    }

    public String getPre_school_stipend_speciality() {
        return pre_school_stipend_speciality;
    }

    public void setPre_school_stipend_speciality(String pre_school_stipend_speciality) {
        this.pre_school_stipend_speciality = pre_school_stipend_speciality;
    }

    public String getPre_school_stipend_type() {
        return pre_school_stipend_type;
    }

    public void setPre_school_stipend_type(String pre_school_stipend_type) {
        this.pre_school_stipend_type = pre_school_stipend_type;
    }

    public String getPre_school_stipend_details() {
        return pre_school_stipend_details;
    }

    public void setPre_school_stipend_details(String pre_school_stipend_details) {
        this.pre_school_stipend_details = pre_school_stipend_details;
    }

    public String getPre_school_max_fee() {
        return pre_school_max_fee;
    }

    public void setPre_school_max_fee(String pre_school_max_fee) {
        this.pre_school_max_fee = pre_school_max_fee;
    }

    public String getPre_school_min_fee() {
        return pre_school_min_fee;
    }

    public void setPre_school_min_fee(String pre_school_min_fee) {
        this.pre_school_min_fee = pre_school_min_fee;
    }

    public String getPre_school_coaching_fee() {
        return pre_school_coaching_fee;
    }

    public void setPre_school_coaching_fee(String pre_school_coaching_fee) {
        this.pre_school_coaching_fee = pre_school_coaching_fee;
    }

    public String getPre_school_additional_fee() {
        return pre_school_additional_fee;
    }

    public void setPre_school_additional_fee(String pre_school_additional_fee) {
        this.pre_school_additional_fee = pre_school_additional_fee;
    }

    public String getI_v_free() {
        return i_v_free;
    }

    public void setI_v_free(String i_v_free) {
        this.i_v_free = i_v_free;
    }

    public String getI_v_stipend_speciality() {
        return i_v_stipend_speciality;
    }

    public void setI_v_stipend_speciality(String i_v_stipend_speciality) {
        this.i_v_stipend_speciality = i_v_stipend_speciality;
    }

    public String getI_v_stipend_type() {
        return i_v_stipend_type;
    }

    public void setI_v_stipend_type(String i_v_stipend_type) {
        this.i_v_stipend_type = i_v_stipend_type;
    }

    public String getI_v_stipend_details() {
        return i_v_stipend_details;
    }

    public void setI_v_stipend_details(String i_v_stipend_details) {
        this.i_v_stipend_details = i_v_stipend_details;
    }

    public String getI_v_max_fee() {
        return i_v_max_fee;
    }

    public void setI_v_max_fee(String i_v_max_fee) {
        this.i_v_max_fee = i_v_max_fee;
    }

    public String getI_v_min_fee() {
        return i_v_min_fee;
    }

    public void setI_v_min_fee(String i_v_min_fee) {
        this.i_v_min_fee = i_v_min_fee;
    }

    public String getI_v_coaching_fee() {
        return i_v_coaching_fee;
    }

    public void setI_v_coaching_fee(String i_v_coaching_fee) {
        this.i_v_coaching_fee = i_v_coaching_fee;
    }

    public String getI_v_additional_fee() {
        return i_v_additional_fee;
    }

    public void setI_v_additional_fee(String i_v_additional_fee) {
        this.i_v_additional_fee = i_v_additional_fee;
    }

    public String getVi_x_free() {
        return vi_x_free;
    }

    public void setVi_x_free(String vi_x_free) {
        this.vi_x_free = vi_x_free;
    }

    public String getVi_x_stipend_speciality() {
        return vi_x_stipend_speciality;
    }

    public void setVi_x_stipend_speciality(String vi_x_stipend_speciality) {
        this.vi_x_stipend_speciality = vi_x_stipend_speciality;
    }

    public String getVi_x_stipend_type() {
        return vi_x_stipend_type;
    }

    public void setVi_x_stipend_type(String vi_x_stipend_type) {
        this.vi_x_stipend_type = vi_x_stipend_type;
    }

    public String getVi_x_stipend_details() {
        return vi_x_stipend_details;
    }

    public void setVi_x_stipend_details(String vi_x_stipend_details) {
        this.vi_x_stipend_details = vi_x_stipend_details;
    }

    public String getVi_x_max_fee() {
        return vi_x_max_fee;
    }

    public void setVi_x_max_fee(String vi_x_max_fee) {
        this.vi_x_max_fee = vi_x_max_fee;
    }

    public String getVi_x_min_fee() {
        return vi_x_min_fee;
    }

    public void setVi_x_min_fee(String vi_x_min_fee) {
        this.vi_x_min_fee = vi_x_min_fee;
    }

    public String getVi_x_coaching_fee() {
        return vi_x_coaching_fee;
    }

    public void setVi_x_coaching_fee(String vi_x_coaching_fee) {
        this.vi_x_coaching_fee = vi_x_coaching_fee;
    }

    public String getVi_x_additional_fee() {
        return vi_x_additional_fee;
    }

    public void setVi_x_additional_fee(String vi_x_additional_fee) {
        this.vi_x_additional_fee = vi_x_additional_fee;
    }

    public String getXi_xii_free() {
        return xi_xii_free;
    }

    public void setXi_xii_free(String xi_xii_free) {
        this.xi_xii_free = xi_xii_free;
    }

    public String getXi_xii_stipend_speciality() {
        return xi_xii_stipend_speciality;
    }

    public void setXi_xii_stipend_speciality(String xi_xii_stipend_speciality) {
        this.xi_xii_stipend_speciality = xi_xii_stipend_speciality;
    }

    public String getXi_xii_stipend_type() {
        return xi_xii_stipend_type;
    }

    public void setXi_xii_stipend_type(String xi_xii_stipend_type) {
        this.xi_xii_stipend_type = xi_xii_stipend_type;
    }

    public String getXi_xii_stipend_details() {
        return xi_xii_stipend_details;
    }

    public void setXi_xii_stipend_details(String xi_xii_stipend_details) {
        this.xi_xii_stipend_details = xi_xii_stipend_details;
    }

    public String getXi_xii_max_fee() {
        return xi_xii_max_fee;
    }

    public void setXi_xii_max_fee(String xi_xii_max_fee) {
        this.xi_xii_max_fee = xi_xii_max_fee;
    }

    public String getXi_xii_min_fee() {
        return xi_xii_min_fee;
    }

    public void setXi_xii_min_fee(String xi_xii_min_fee) {
        this.xi_xii_min_fee = xi_xii_min_fee;
    }

    public String getXi_xii_coaching_fee() {
        return xi_xii_coaching_fee;
    }

    public void setXi_xii_coaching_fee(String xi_xii_coaching_fee) {
        this.xi_xii_coaching_fee = xi_xii_coaching_fee;
    }

    public String getXi_xii_additional_fee() {
        return xi_xii_additional_fee;
    }

    public void setXi_xii_additional_fee(String xi_xii_additional_fee) {
        this.xi_xii_additional_fee = xi_xii_additional_fee;
    }

    public String getUni_free() {
        return uni_free;
    }

    public void setUni_free(String uni_free) {
        this.uni_free = uni_free;
    }

    public String getUni_stipend_speciality() {
        return uni_stipend_speciality;
    }

    public void setUni_stipend_speciality(String uni_stipend_speciality) {
        this.uni_stipend_speciality = uni_stipend_speciality;
    }

    public String getUni_stipend_type() {
        return uni_stipend_type;
    }

    public void setUni_stipend_type(String uni_stipend_type) {
        this.uni_stipend_type = uni_stipend_type;
    }

    public String getUni_stipend_details() {
        return uni_stipend_details;
    }

    public void setUni_stipend_details(String uni_stipend_details) {
        this.uni_stipend_details = uni_stipend_details;
    }

    public String getUni_max_fee() {
        return uni_max_fee;
    }

    public void setUni_max_fee(String uni_max_fee) {
        this.uni_max_fee = uni_max_fee;
    }

    public String getUni_min_fee() {
        return uni_min_fee;
    }

    public void setUni_min_fee(String uni_min_fee) {
        this.uni_min_fee = uni_min_fee;
    }

    public String getUni_coaching_fee() {
        return uni_coaching_fee;
    }

    public void setUni_coaching_fee(String uni_coaching_fee) {
        this.uni_coaching_fee = uni_coaching_fee;
    }

    public String getUni_additional_fee() {
        return uni_additional_fee;
    }

    public void setUni_additional_fee(String uni_additional_fee) {
        this.uni_additional_fee = uni_additional_fee;
    }



    public static EducationFeeItem parseEducationFeeItem(JSONObject jo) throws JSONException {
        String _fee_id=jo.getString("fee_id");
        String _identifier_id=jo.getString("identifier_id");
        String _pre_school_free=jo.getString("pre_school_free");
        String _pre_school_stipend_speciality=jo.getString("pre_school_stipend_speciality");
        String _pre_school_stipend_type=jo.getString("pre_school_stipend_type");
        String _pre_school_stipend_details=jo.getString("pre_school_stipend_details");
        String _pre_school_max_fee=jo.getString("pre_school_max_fee");
        String _pre_school_min_fee=jo.getString("pre_school_min_fee");
        String _pre_school_coaching_fee=jo.getString("pre_school_coaching_fee");
        String _pre_school_additional_fee=jo.getString("pre_school_additional_fee");

        String _i_v_free=jo.getString("i_v_free");
        String _i_v_stipend_speciality=jo.getString("i_v_stipend_speciality");
        String _i_v_stipend_type=jo.getString("i_v_stipend_type");
        String _i_v_stipend_details=jo.getString("i_v_stipend_details");
        String _i_v_max_fee=jo.getString("i_v_max_fee");
        String _i_v_min_fee=jo.getString("i_v_min_fee");
        String _i_v_coaching_fee=jo.getString("i_v_coaching_fee");
        String _i_v_additional_fee=jo.getString("i_v_additional_fee");

        String _vi_x_free=jo.getString("vi_x_free");
        String _vi_x_stipend_speciality=jo.getString("vi_x_stipend_speciality");
        String _vi_x_stipend_type=jo.getString("vi_x_stipend_type");
        String _vi_x_stipend_details=jo.getString("vi_x_stipend_details");
        String _vi_x_max_fee=jo.getString("vi_x_max_fee");
        String _vi_x_min_fee=jo.getString("vi_x_min_fee");
        String _vi_x_coaching_fee=jo.getString("vi_x_coaching_fee");
        String _vi_x_additional_fee=jo.getString("vi_x_additional_fee");

        String _xi_xii_free = jo.getString("xi_xii_free");
        String _xi_xii_stipend_speciality = jo.getString("xi_xii_stipend_speciality");
        String _xi_xii_stipend_type=jo.getString("xi_xii_stipend_type");
        String _xi_xii_stipend_details=jo.getString("xi_xii_stipend_details");
        String _xi_xii_max_fee=jo.getString("xi_xii_max_fee");
        String _xi_xii_min_fee=jo.getString("xi_xii_min_fee");
        String _xi_xii_coaching_fee=jo.getString("xi_xii_coaching_fee");
        String _xi_xii_additional_fee = jo.getString("xi_xii_additional_fee");

        String _uni_free = jo.getString("uni_free");
        String _uni_stipend_speciality= jo.getString("uni_stipend_speciality");
        String _uni_stipend_type=jo.getString("uni_stipend_type");
        String _uni_stipend_details=jo.getString("uni_stipend_details");
        String _uni_max_fee=jo.getString("uni_max_fee");
        String _uni_min_fee = jo.getString("uni_min_fee");
        String _uni_coaching_fee = jo.getString("uni_coaching_fee");
        String _uni_additional_fee= jo.getString("uni_additional_fee");

        return new EducationFeeItem( _fee_id,
                _identifier_id,
                _pre_school_free,
                _pre_school_stipend_speciality,
                _pre_school_stipend_type,
                _pre_school_stipend_details,
                _pre_school_max_fee,
                _pre_school_min_fee,
                _pre_school_coaching_fee,
                _pre_school_additional_fee,
                _i_v_free,
                _i_v_stipend_speciality,
                _i_v_stipend_type,
                _i_v_stipend_details,
                _i_v_max_fee,
                _i_v_min_fee,
                _i_v_coaching_fee,
                _i_v_additional_fee,
                _vi_x_free,
                _vi_x_stipend_speciality,
                _vi_x_stipend_type,
                _vi_x_stipend_details,
                _vi_x_max_fee,
                _vi_x_min_fee,_vi_x_coaching_fee,_vi_x_additional_fee,
                _xi_xii_free,
                _xi_xii_stipend_speciality,_xi_xii_stipend_type,
                _xi_xii_stipend_details,_xi_xii_max_fee,_xi_xii_min_fee,
                _xi_xii_coaching_fee,_xi_xii_additional_fee,
                _uni_free,_uni_stipend_speciality,
                _uni_stipend_type,_uni_stipend_details,_uni_max_fee,_uni_min_fee,
                _uni_coaching_fee,_uni_additional_fee);
    }
}
