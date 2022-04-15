/**The Phone Directory has the array list as its private data field and
 * additional methods that are explained in the comments.
 *
 * Authors: Matthew Gloriani, Kevin Nhu
 *
 * DLM: April 12, 2021
 */

import java.util.*;

public class PhoneDirectory extends DirectoryEntry {
	ArrayList<DirectoryEntry> theDirectory = new ArrayList<>(); 

	/**
	 * Non-default constructor for PhoneDirectory object
	 *
	 * @param name First entry's name
	 * @param number First entry's phone #
	 *
	 */
	public PhoneDirectory(String name, String number) {
		super(name, number);
		addOrChangeEntry(this.name, this.number);
	}

	/** Add an entry to directory or change an existing entry
	 *
	 * @param name Name of desired entry
	 * @param number Phone # of desired entry
	 *
	 * @return If the entry already exists, return original phone #, otherwise
	 * return null 
	 */
	public DirectoryEntry addOrChangeEntry(String name, String number) {
		// Get index of user's desired entry
		int desiredEntryIndex = theDirectory.indexOf(new DirectoryEntry(name, ""));
		DirectoryEntry sampleEntry;
		if (desiredEntryIndex != -1) { // If the user-specified entry exists in directory
			sampleEntry = theDirectory.get(desiredEntryIndex);
			theDirectory.set(desiredEntryIndex, new DirectoryEntry(name, number));
			return sampleEntry;
		}
		else { // If the user-specified entry doesn't exist in directory
			DirectoryEntry newEntry = new DirectoryEntry(name, number);
			theDirectory.add(newEntry);
			return null;
		}
	}

	/**  
	 * Searches the directory for an entry based on the user-specified name
	 *
	 * @param name The name inputted by user
	 * @return If it exists, the corresponding entry is returned, otherwise
	 * returns null 
	 */
	public DirectoryEntry searchEntry(String name) {
		// Get index of user's desired entry
		int desiredEntryIndex = theDirectory.indexOf(new DirectoryEntry(name, ""));
		System.out.println(desiredEntryIndex);
		if (desiredEntryIndex != -1) { // if name found 
			return theDirectory.get(desiredEntryIndex);
		}
		else { // if name not found
			return null; 
		}
	}

	/** 
	 * Removes an entry from the directory based on the user-specified name
	 * 
	 * @param name The name inputted by user
	 * @return If it exists, the removed directory entry is returned,
	 * otherwise null is returned
	 */ 
	public DirectoryEntry removeEntry(String name) {
		// Get index of user's desired entry
		int desiredEntryIndex = theDirectory.indexOf(new DirectoryEntry(name, ""));
		if (desiredEntryIndex != -1) { // if name found
			DirectoryEntry desiredEntry = theDirectory.get(desiredEntryIndex);
			theDirectory.remove(desiredEntryIndex); 
			return desiredEntry;
		}
		else { // if name  not found 
			return null; 
		}
	}

	/** 
	 * Displays all of the directory entries in a nice and readable format
	 *
	 * @param none No parameters
	 * @return none No return value
	 */
	public void displayAllEntries() {
		System.out.println("Size: " + theDirectory.size());
		System.out.println(this.toString());			
	}

	/**
	 * Displays a PhoneDirectory object as a string.
	 *
	 * @return String representation of the PhoneDirectory object
	 */
	public String toString() {
		int directoryLength = theDirectory.size();
		String phoneBookEntry = "";
		for (int x = 0; x < directoryLength; x++) {
			phoneBookEntry += theDirectory.get(x).toString() + "\n";
		}
		return phoneBookEntry;
	}
}
