package demo.kolorob.kolorobdemoversion.adapters;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;

public class PopulatedfromDBEdu extends PopulatedfromDB {
	private String rank;
	private String nodeid;
	private int refidedu;
	private ArrayList<EducationServiceProviderItem>fetchededu=null;

	public ArrayList<EducationServiceProviderItem> getFetchededu() {
		return fetchededu;
	}

	public void setFetchededu(ArrayList<EducationServiceProviderItem> fetchededu) {
		this.fetchededu = fetchededu;
	}

	public int getRefidedu() {
		return refidedu;
	}

	public void setRefidedu(int refidedu) {
		this.refidedu = refidedu;
	}

	public PopulatedfromDBEdu(String rank, String nodeid, int refidedu, ArrayList<EducationServiceProviderItem> fetched) {
		super();
		this.rank = rank;
		this.nodeid=nodeid;
		this.refidedu=refidedu;
		this.fetchededu=fetched;
		
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}




	public String getRank() {
		return this.rank;
	}

	
}
