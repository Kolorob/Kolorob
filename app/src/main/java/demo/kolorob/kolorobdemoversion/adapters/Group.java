package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by israt.jahan on 1/17/16.
 */
import java.util.ArrayList;
import java.util.List;

import demo.kolorob.kolorobdemoversion.model.Education.EducationNewItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialNewItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Government.GovernmentNewItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItemNew;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItemNew;


public class Group {

    public String string;
    public  List<EducationNewItem> children = new ArrayList<EducationNewItem>();
    public  List<EntertainmentServiceProviderItemNew> childrenent = new ArrayList<EntertainmentServiceProviderItemNew>();
    public  List<HealthServiceProviderItemNew> childrenhea = new ArrayList<HealthServiceProviderItemNew>();
    public  List<FinancialNewItem> childrenfin = new ArrayList<FinancialNewItem>();
    public  List<LegalAidServiceProviderItemNew> childrenleg = new ArrayList<LegalAidServiceProviderItemNew>();
    public  List<JobServiceProviderItem> childrenjob = new ArrayList<JobServiceProviderItem>();
    public  List<GovernmentNewItem> childrengov = new ArrayList<GovernmentNewItem>();
    public List<HealthServiceProviderItemNew> getChildrenhea() {
        return childrenhea;
    }

    public void setChildrenhea(List<HealthServiceProviderItemNew> childrenhea) {
        this.childrenhea = childrenhea;
    }

    public List<FinancialNewItem> getChildrenfin() {
        return childrenfin;
    }

    public void setChildrenfin(List<FinancialNewItem> childrenfin) {
        this.childrenfin = childrenfin;
    }

    public List<JobServiceProviderItem> getChildrenjob() {
        return childrenjob;
    }

    public void setChildrenjob(List<JobServiceProviderItem> childrenjob) {
        this.childrenjob = childrenjob;
    }

    public List<LegalAidServiceProviderItemNew> getChildrenleg() {
        return childrenleg;
    }

    public void setChildrenleg(List<LegalAidServiceProviderItemNew> childrenleg) {
        this.childrenleg = childrenleg;
    }

    public List<EntertainmentServiceProviderItemNew> getChildrenent() {
        return childrenent;
    }

    public void setChildrenent(List<EntertainmentServiceProviderItemNew> childrenent) {
        this.childrenent = childrenent;
    }

    public List<EducationNewItem> getChildren() {
        return children;
    }

    public void setChildren(List<EducationNewItem> children) {
        this.children = children;
    }

    public List<GovernmentNewItem> getChildrengov() {
        return childrengov;
    }

    public void setChildrengov(List<GovernmentNewItem> childrengov) {
        this.childrengov = childrengov;
    }

    public List<EducationNewItem> getchildren() {
        return children;
    }

    public Group(String string) {
        this.string = string;
    }
    public void setchildren(List<EducationServiceProviderItem> itemList) {
        this.children = children;
    }
}