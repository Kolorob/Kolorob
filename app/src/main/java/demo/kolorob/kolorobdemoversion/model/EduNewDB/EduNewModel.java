package demo.kolorob.kolorobdemoversion.model.EduNewDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by israt.jahan on 1/26/2017.
 */

public class EduNewModel extends CommonModel implements Serializable {

    private String educationType, shift, studentNo, teachersNo, averageStudentPerClass, facility;

    public EduNewModel() {

    }


    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getTeachersNo() {
        return teachersNo;
    }

    public void setTeachersNo(String teachersNo) {
        this.teachersNo = teachersNo;
    }

    public String getAverageStudentPerClass() {
        return averageStudentPerClass;
    }

    public void setAverageStudentPerClass(String averageStudentPerClass) {
        this.averageStudentPerClass = averageStudentPerClass;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public EduNewModel(int id, String nameEn, String nameBn, String houseNo, String block, String road, String area, String areaBn, String parentArea, String ward, String policeStation, String nodeContact, String nodeEmail, String otherInfo, String openingTime, String closingTime, String offDay, String lat, String lon, int categoryId, String subcat, String refNum, String ratings, String comment, String educationType, String shift, String studentNo, String teachersNo, String averageStudentPerClass, String facility) {
        super(id, nameEn, nameBn, houseNo, block, road, area, areaBn, parentArea, ward, policeStation, nodeContact, nodeEmail, otherInfo, openingTime, closingTime, offDay, lat, lon, categoryId, subcat, refNum, ratings, comment);
        this.educationType = educationType;
        this.shift = shift;
        this.studentNo = studentNo;
        this.teachersNo = teachersNo;
        this.averageStudentPerClass = averageStudentPerClass;
        this.facility = facility;
    }


    public EduNewModel(CommonModel cm, String educationType, String shift, String studentNo, String teachersNo, String averageStudentPerClass, String facility) {
        super(cm.getId(), cm.getNameEn(), cm.getNameBn(),
                cm.getHouseNo(), cm.getBlock(), cm.getRoad(), cm.getArea(), cm.getAreaBn(), cm.getParentArea(), cm.getWard(), cm.getPoliceStation(),
                cm.getNodeContact(), cm.getNodeEmail(), cm.getOtherInfo(),
                cm.getOpeningTime(), cm.getClosingTime(), cm.getOffDay(),
                cm.getLat(), cm.getLon(),
                cm.getCategoryId(), cm.getSubcat(), cm.getRefNum(), cm.getRatings(), cm.getComment());
        this.educationType = educationType;
        this.shift = shift;
        this.studentNo = studentNo;
        this.teachersNo = teachersNo;
        this.averageStudentPerClass = averageStudentPerClass;
        this.facility = facility;
    }



    @Override
    public EduNewModel parse (JSONObject jo) throws JSONException {

        CommonModel _commonModel = new CommonModel().parse(jo);
        String _educationType = jo.getString("education_type");
        String _shift = jo.getString("shift");
        String _studentNo = jo.getString("no_of_students");
        String _teachersNo = jo.getString("no_of_teachers");
        String _avgStudentPerClass = jo.getString("class_size");
        String _facility = jo.getString("facility");

        return new EduNewModel(_commonModel, _educationType, _shift, _studentNo, _teachersNo, _avgStudentPerClass, _facility);
    }
}
