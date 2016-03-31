package demo.kolorob.kolorobdemoversion.adapters;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;

public class PopulatedfromDB {
	private String rank;
	private String nodeid;
	private String refid;
	private ArrayList<FinancialServiceProviderItem>fetched=null;

	public PopulatedfromDB() {

	}


	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public PopulatedfromDB(String rank,String nodeid,String refid, ArrayList<FinancialServiceProviderItem>fetched) {
		this.rank = rank;
		this.nodeid=nodeid;
		this.refid=refid;
		this.fetched=fetched;
		
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


	public ArrayList<FinancialServiceProviderItem> getFetched() {
		return fetched;
	}

	public void setFetched(ArrayList<FinancialServiceProviderItem> fetched) {
		this.fetched = fetched;
	}

	public String getRank() {
		return this.rank;
	}

	
}
