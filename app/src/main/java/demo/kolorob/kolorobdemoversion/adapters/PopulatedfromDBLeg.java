package demo.kolorob.kolorobdemoversion.adapters;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;

public class PopulatedfromDBLeg extends PopulatedfromDB {
	private String rank;
	private String nodeid;
	private int refidleg;
	private ArrayList<LegalAidServiceProviderItem>fetchededu=null;



	public PopulatedfromDBLeg(String rank, String nodeid, int refidleg, ArrayList<LegalAidServiceProviderItem> fetched) {
		super();
		this.rank = rank;
		this.nodeid=nodeid;
		this.refidleg=refidleg;
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
