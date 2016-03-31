package demo.kolorob.kolorobdemoversion.adapters;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;

public class PopulatedfromDBEnt extends PopulatedfromDB {
	private String rank;
	private String nodeid;
	private int refident;
	private ArrayList<EntertainmentServiceProviderItem>fetchededu=null;



	public PopulatedfromDBEnt(String rank, String nodeid, int refidleg, ArrayList<EntertainmentServiceProviderItem> fetched) {
		super();
		this.rank = rank;
		this.nodeid=nodeid;
		this.refident=refidleg;
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


	public int getRefident() {
		return refident;
	}

	public void setRefident(int refident) {
		this.refident = refident;
	}

	public String getRank() {
		return this.rank;
	}

	
}
