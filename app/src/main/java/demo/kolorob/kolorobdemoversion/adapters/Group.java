package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by israt.jahan on 1/17/16.
 */
import java.util.ArrayList;
import java.util.List;

import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;


public class Group {

    public String string;
    public  List<EducationServiceProviderItem> children = new ArrayList<EducationServiceProviderItem>();
    public  List<EntertainmentServiceProviderItem> childrenent = new ArrayList<EntertainmentServiceProviderItem>();
    public  List<HealthServiceProviderItemNew> childrenhea = new ArrayList<HealthServiceProviderItemNew>();
    public  List<FinancialServiceProviderItem> childrenfin = new ArrayList<FinancialServiceProviderItem>();
    public  List<LegalAidServiceProviderItem> childrenleg = new ArrayList<LegalAidServiceProviderItem>();
    public  List<JobServiceProviderItem> childrenjob = new ArrayList<JobServiceProviderItem>();

    public List<HealthServiceProviderItemNew> getChildrenhea() {
        return childrenhea;
    }

    public void setChildrenhea(List<HealthServiceProviderItemNew> childrenhea) {
        this.childrenhea = childrenhea;
    }

    public List<FinancialServiceProviderItem> getChildrenfin() {
        return childrenfin;
    }

    public void setChildrenfin(List<FinancialServiceProviderItem> childrenfin) {
        this.childrenfin = childrenfin;
    }

    public List<JobServiceProviderItem> getChildrenjob() {
        return childrenjob;
    }

    public void setChildrenjob(List<JobServiceProviderItem> childrenjob) {
        this.childrenjob = childrenjob;
    }

    public List<LegalAidServiceProviderItem> getChildrenleg() {
        return childrenleg;
    }

    public void setChildrenleg(List<LegalAidServiceProviderItem> childrenleg) {
        this.childrenleg = childrenleg;
    }

    public List<EntertainmentServiceProviderItem> getChildrenent() {
        return childrenent;
    }

    public void setChildrenent(List<EntertainmentServiceProviderItem> childrenent) {
        this.childrenent = childrenent;
    }

    public List<EducationServiceProviderItem> getchildren() {
        return children;
    }

    public Group(String string) {
        this.string = string;
    }
    public void setchildren(List<EducationServiceProviderItem> itemList) {
        this.children = children;
    }
}