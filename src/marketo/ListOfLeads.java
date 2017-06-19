/**
 * Class: 		ListOfLeads
 * Function: 	Data Structure to form List of leads as per input.
 * 
 */
package marketo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arunp
 * Date: 2017/06/16
 */
public class ListOfLeads {

	private List<Lead> leads;
	ListOfLeads(){
		this.leads = new ArrayList<Lead>();
	}

	public List<Lead> getLeads() {
		return leads;
	}

	public void setLeads(List<Lead> leads) {
		this.leads = leads;
	}
}