package demo.kolorob.kolorobdemoversion.adapters;

import java.io.Serializable;

/**
 * Created by israt.jahan on 1/21/2016.
 */
public class ChildrenEducation implements Serializable {
    private String identifierId;
    private String serviceProviderId;
    private int eduSubCategoryId;
    private int categoryId;
    private String eduNameEng;
    private String eduNameBan;
    private String eduType;
    private String hostelFacility;
    private String transportFacility;
    private String playground;
    private String contactPersonDesignation;
    private String contactNo;
    private String emailAddress;
    private String websiteLink;
    private String fbLink;
    private String registeredWith;
    private String registrationNo;
    private String totalStudents;
    private String totalClasses;
    private String totalTeachers;
    private String courseProvided;
    private String shift;
    private String canteenFacility;
    private String additionalInfo;
    private String latitude;
    private String longitude;

    public ChildrenEducation(String identifierId, String serviceProviderId, int eduSubCategoryId, int categoryId, String eduNameEng, String eduNameBan, String eduType, String hostelFacility, String transportFacility, String playground, String contactPersonDesignation, String contactNo, String emailAddress, String websiteLink, String fbLink, String registeredWith, String registrationNo, String totalStudents, String totalClasses, String totalTeachers, String courseProvided, String shift, String canteenFacility, String additionalInfo, String latitude, String longitude) {
        this.identifierId = identifierId;
        this.serviceProviderId = serviceProviderId;
        this.eduSubCategoryId = eduSubCategoryId;
        this.categoryId = categoryId;
        this.eduNameEng = eduNameEng;
        this.eduNameBan = eduNameBan;
        this.eduType = eduType;
        this.hostelFacility = hostelFacility;
        this.transportFacility = transportFacility;
        this.playground = playground;
        this.contactPersonDesignation = contactPersonDesignation;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.websiteLink = websiteLink;
        this.fbLink = fbLink;
        this.registeredWith = registeredWith;
        this.registrationNo = registrationNo;
        this.totalStudents = totalStudents;
        this.totalClasses = totalClasses;
        this.totalTeachers = totalTeachers;
        this.courseProvided = courseProvided;
        this.shift = shift;
        this.canteenFacility = canteenFacility;
        this.additionalInfo = additionalInfo;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

