/**
 * Class: 		Lead
 * Function: 	Data structure of leads.
 * 
 */
package marketo;

import java.util.Date;

/**
 * @author arunp
 * Date: 2017/06/16
 */
public class Lead {
	
	private String _id;
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private Date entryDate;

	public String getId() {
		return _id;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
}