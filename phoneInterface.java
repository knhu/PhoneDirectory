/** This program provides a user-friendly and menu driven program that allows
 * the user to select and execute miscellaneous operations/services. .
 *
 * Authors: Matthew Gloriani, Kevin Nhu
 *
 * DLM: April 12, 2021
 *
 *
 */

import java.io.*;
import java.util.*;

public class phoneInterface {
	public static void main(String[] args) {
		System.out.println("\nWelcome to Matthew and Kevin's PhoneDirectory.\n");
		Scanner userChoice = new Scanner(System.in);

		// Initialize the phone directory
		PhoneDirectory phoneDirectory = new PhoneDirectory("name", "number");

		// Allows the user to continue running until user chooses option 7
		// (exit program)
		while (true) {
			int choice = getMenuChoice(userChoice);
			executeChoice(phoneDirectory, userChoice, choice);
		}
	}
	
	/**
	 * Executes an action based on what the user chooses
	 *
	 * @param phoneDirectory The phonedirectory
	 * @param userChoice A scanner object that reads in the user's menu choice
	 * @param choice User chooses an integer between 1 and 7.
	 */
	public static void executeChoice(PhoneDirectory phoneDirectory, Scanner userChoice, int choice) {
		// initializing our local variables
		String name = "", number = "", file = "";
		DirectoryEntry desiredEntry = null;
		PhoneDirectory pb = null;

		// Here we determine what will be executed based on what the user
		// chooses for choice
		if (choice == 1) { // Load a previously saved phone directory from file 
			System.out.println("Please enter the name of a file: ");
			file = userChoice.nextLine();
			pb = readFile(file);
			if (pb == null) { // if file not found
				System.out.println("Your phone directory has no changes");
			}
			else { // if file is found
				System.out.println("Congrats! Your new phone directory has been read from your file!");
			}
			return;
		}
		else if (choice == 2) { // Add or change a specific entry from directory
			System.out.print("Enter a name: ");
			name = userChoice.nextLine();
			System.out.print("Enter a number: ");
			number = userChoice.nextLine();
			desiredEntry = phoneDirectory.addOrChangeEntry(name, number);
			if (desiredEntry == null) { // If specified name and number don't exist already
				System.out.println("A new entry has been added to your phone directory.");
			}
			else { // If name and number specified above already exist
				System.out.printf("You have overwritten existing entry [%s].\n", desiredEntry.toString());
			}
			return;
		}
		else if (choice == 3) { // Remove an entry correlating with a specified name 
			System.out.print("Enter a name: ");
			name = userChoice.nextLine();
			desiredEntry = phoneDirectory.removeEntry(name);
			if (desiredEntry == null) { // if specified name is not found
				System.out.println("No phone directory entry with the specified name " + name + " was found.");	
			}
			else { // if specified name is found
				System.out.printf("You removed the entry [%s] from the phone directory.\n", desiredEntry.toString());	
			}
			return; 
		}
		else if (choice == 4) { // Search for an entry correlating with a specified name
			System.out.print("Enter a name: ");
			name = userChoice.nextLine();
			desiredEntry = phoneDirectory.searchEntry(name);
			if (desiredEntry != null) { // if specified name exists in directory
				System.out.printf("The entry <%s> corresponds with the name (%s).\n", desiredEntry.toString(), name);
			}
			else { // if specified name doesn't exist in directory
				System.out.printf("Your specified name (%s) does not match with any entry.\n", name);
			}
			return;
		}
		else if (choice == 5) { // Display all entries in the directory
			phoneDirectory.displayAllEntries();
			return;
		}
		else if (choice == 6) { // Save the phone directory into a specified file
			try { // If the file name specified is of a valid format
				System.out.print("Enter the name of a file: ");
				file = userChoice.nextLine();
				PrintWriter x = new PrintWriter(new FileWriter(file));
				x.print(phoneDirectory.toString());
				x.close();
			}
			catch (IOException e) { // If the file name specified is not valid
				System.out.println("This is not a valid file name.");
			}
			return;
		}
		else if (choice == 7) { // Quit the program 
			System.out.println("Thanks for using Matthew and Kevin's PhoneDirectory");
			System.exit(0);
		}
		else { // If user's specified choice is not within the valid range 1-7
			System.out.println("Your choice is invalid. Please choose a number (1-7).");
			return;
		}
	}
	
	/**
	 * Retrieves the user's main menu choice
	 *
	 * @param userChoice Scanner object that scans in the user's choice
	 * @return Integer that corresponds with the user's inputted choice 
	 */
	public static int getMenuChoice(Scanner userChoice) {
		int minValidOption = 1, maxValidOption = 7;
		System.out.println("\n					Main Menu				\n");
		System.out.println(" 1. Load a previously saved phone directory from file ");
		System.out.println(" 2. Add or change an entry ");
		System.out.println(" 3. Remove an entry ");
		System.out.println(" 4. Search for an entry ");
		System.out.println(" 5. Display all entries ");
		System.out.println(" 6. Save the current phone directory to a file "); 
		System.out.println(" 7. Quit the program ");
		
		return getUserChoice(userChoice, minValidOption, maxValidOption);
	}

	/**
	* Receives an integer input from the user, reprompts user to try again if
	* outside range
	*
	* @param userChoice Scanner object that reads in user's inputted choice
	* @param low The lowest possible valid input value
	* @param high The highest possible valid input value
	* @return The input value
	*/
	public static int getUserChoice(Scanner userChoice, int low, int high) {
		int userInput = low - 1;
		while (userInput < low || userInput > high) { // while user input is out of valid range
			System.out.print("\nPlease choose an option (1-7): ");
			try { // if valid, returns userChoice as an int
				userInput = Integer.valueOf(userChoice.nextLine());
				if (userInput < low || userInput > high) {
					throw new NumberFormatException();
				}
				System.out.println();
			}
			catch (NumberFormatException e) { // if still invalid
				System.out.printf("The number you've entered is invalid. The choice must be between %d and %d\n", low, high);
				userInput = low - 1;
			}
		}
		return userInput; 
	}

	/**
	 * Reads in a list of directory entries from a user-specified file name
	 *
	 * @param file - The name of the file
	 * @return The ArrayList of numbers if file read was successful, null
	 * otherwise
	 *
	 */
	public static PhoneDirectory readFile(String file) {
		try { // If file is of valid format and exists
			PhoneDirectory phoneDirectory = new PhoneDirectory("name", "number");
			// Here we first open user specified input file and split it into
			// tokens
			Scanner read = new Scanner(new File(file));
			while (read.hasNextLine()) {
				StringTokenizer tokens = new StringTokenizer(read.nextLine(), ", ");
				if (tokens.countTokens() == 2) {
					phoneDirectory.addOrChangeEntry(tokens.nextToken(), tokens.nextToken());
				}
				else {
					throw new InputMismatchException("This is an invalid format.");
				}
			}
			return phoneDirectory;
		}
		catch (FileNotFoundException e) { // If the file doesn't exist
			System.out.printf("The specified file (%s) was not found.\n", file);
		}
		catch (InputMismatchException o) { // If the file is not of valid format
			System.out.println("Your specified input file does not have the format 'name, number'.");
		}
		return null; 
	}
