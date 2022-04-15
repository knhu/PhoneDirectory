/** This program maintains a list of names and phone numbers for a phone
 * directory of your friends.
 *
 * Authors: Matthew Gloriani, Kevin Nhu
 *
 * DLM: April 12, 2021 
 */

public class DirectoryEntry {
	String name; // data field
	String number; // data field

	/**
	 * Non-default constructor for DirectoryEntry object
	 *
	 * @param name Name of DirectoryEntry
	 * @param number Phone # of DirectoryEntry
	 */
	public DirectoryEntry(String name, String number) {
		this.name = name;
		this.number = number; 
	}

	/**
	* Displays a DirectoryEntry object as a string.
	*
	* @return String representation of a specific DirectoryEntry object
	*/
	public String toString() {
		return name + ", " + number;
	}

	/**
	 * Determines if two DirectoryEntry objects are identical. 
	 * Names are used to determine equivalence between objects.
	 *
	 * @param o Another DirectoryEntry object
	 * 
	 * @return True if equal, False otherwise
	 */
	public boolean equals(Object o) {
		String anotherName = ((DirectoryEntry) o).name;
		return anotherName.equalsIgnoreCase(this.name);
	}
}
