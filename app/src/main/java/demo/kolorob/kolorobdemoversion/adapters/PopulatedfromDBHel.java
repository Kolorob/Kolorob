package demo.kolorob.kolorobdemoversion.adapters;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;

public class PopulatedfromDBHel extends PopulatedfromDB {
	private String rank;
	private String nodeid;
	private int refidedu;
	private ArrayList<HealthServiceProviderItem>fetchededu=null;



	public PopulatedfromDBHel(String rank, String nodeid, int refidedu, ArrayList<HealthServiceProviderItem> fetched) {
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
