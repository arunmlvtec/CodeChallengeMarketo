/**
 * Class: 		RemoveDuplicates
 * Function: 	Reads a JSON file and removes the duplicate entries on id, email by updating the JSON with newest object.
 * 
 */

package marketo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

/**
 * @author arunp
 * Date: 2017/06/16
 */
public class RemoveDuplicates {

	public static void main(String[] args) {

		RemoveDuplicates rd = new RemoveDuplicates();
		Gson gson = new Gson();
		Scanner sc = new Scanner(System.in);
		BufferedReader br = null;
		BufferedWriter bw = null;
		String uniqLeads = null;
		ListOfLeads inputLeads = new ListOfLeads();

		try {
			System.out.println("Enter the input file path: ");
			br = new BufferedReader(new FileReader(sc.next()));
			
			// Converting String into Object using Gson.
			inputLeads = gson.fromJson(br, ListOfLeads.class);

			// Calling reconcileLeads method to remove duplicate leads and
			// generate a unique list of leads.
			ListOfLeads result = rd.reconcileLeads(inputLeads);

			try {
				// Converting Object to String using Gson.
				uniqLeads = gson.toJson(result, ListOfLeads.class);
			} catch (JsonIOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("Enter the output file path: ");
			bw = new BufferedWriter(new FileWriter(sc.next()));
			bw.write(uniqLeads);

			System.out.println("Done!");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			try {
				bw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private ListOfLeads reconcileLeads(ListOfLeads inputLeads) {
		/**
		 * @method reconcileLeads(ListOfLeads inputLeads) generate result Leads
		 *         from given input leads.
		 * 
		 * @param inputLeads
		 *            is the ListOfLeads class object containing input leads.
		 * @return the ListOfLeads class object containing unique and latest
		 *         leads.
		 */
		ListOfLeads result = new ListOfLeads();
		for (Lead lead : inputLeads.getLeads()) {

			if (result.getLeads().isEmpty()) {
				result.getLeads().add(lead);
			} else {
				addIfUniq(lead, result);
			}
		}
		return result;
	}

	private void addIfUniq(Lead lead, ListOfLeads result) {
		/**
		 * @method addIfUniq(Lead lead, ListOfLeads result) checks the
		 *         uniqueness and freshness of the @param lead with @param
		 *         result and add it in result.
		 * @param lead
		 *            is object of Lead class containing single Json Element
		 *            from the input leads.
		 * @param result
		 *            is object of ListOfLeads class containing unique and
		 *            latest leads.
		 */
		for (Lead ld : result.getLeads()) {
			/**
			 * If old id matches to new id OR old email matches to new email and
			 * if the old date is before new date, then ld is removed and lead
			 * is inserted.
			 */
			if ((ld.getId().equalsIgnoreCase(lead.getId()) || ld.getEmail()
					.equalsIgnoreCase(lead.getEmail()))
					&& (!ld.getEntryDate().after(lead.getEntryDate()))) {

				result.getLeads().remove(ld);
				result.getLeads().add(lead);
				return;
			}
		}
		result.getLeads().add(lead);
	}
	// new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(entryDate)
}