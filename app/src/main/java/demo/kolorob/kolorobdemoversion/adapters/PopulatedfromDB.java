package demo.kolorob.kolorobdemoversion.adapters;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;

public class PopulatedfromDB {
	private String rank;
	private String nodeid;
	private String refid;
	private int ref;
	private ArrayList<FinancialServiceProviderItem>fetched=null;

	public PopulatedfromDB() {

	}

	public PopulatedfromDB(String upname, String fnodeid, int refid, ArrayList<FinancialServiceProviderItem> fetchedfin) {

		this.rank = upname;
		this.nodeid=fnodeid;
		this.ref=refid;
		this.fetched=fetchedfin;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
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
