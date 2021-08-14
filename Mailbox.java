/**
* The Mailbox class implements a mailbox that holds all the folders of Email objects.
* The Mailbox class also contains a main method that allows the user to interact
* with the Mailbox, Folder and Email objects
*
* @author MacGregor Winegard
*	email: macgregor.winegard@stonybrook.edu
* 	Stony Brook ID: 114152787
*
* @version 1 Build 1 August 8 2020
**/

import java.io.*;
import java.util.*;

public class Mailbox implements Serializable
{
	//invariants
	private Folder inbox; // inbox folder
	private Folder trash; // trash folder
	private ArrayList<Folder> folders; // all other folders
	
	/**
	* The Mailbox object stores all the informationa about the mailbox
	**/
	public Mailbox()
	{
		this.inbox = new Folder("Inbox");
		this.trash = new Folder("Trash");
		this.folders = new ArrayList<Folder>();
	}
	
	/**
	*adds a folder to the mailbox
	*
	*@param folder
	*	the folder being addded to the Mailbox
	*
	* Postcondition:
	*	the folder has been added to the mailbox
	**/
	public void addFolder(Folder inFolder)
	{
		this.folders.add(inFolder);
	}
	
	/**
	*deletes the folder with the given name
	*
	* @param name
	*	the name of the folder you want removed
	*
	* Postconition:
	*	the folder has been removed
	**/
	public void deleteFolder(String name)
	{
		name = name.toUpperCase();
		for (int x=0; x<this.folders.size(); x++)
		{
			if (this.folders.get(x).getName().toUpperCase().equals(name))
				this.folders.remove(x);
		}
	}
	
	/**
	*Gets user inputs on the content of the email and adds it to the inbox
	**/
	public void composeEmail()
	{
		System.out.println("\nComposing email.");
		Scanner compScan = new Scanner(System.in);
		String compTo = "";
		String compCc = "";
		String compBcc = "";
		String compSubject;
		String compBody;
		
		//while (!emailChecker(compTo))
		//{
			System.out.printf("\nPlease enter who the email is to in a valid format: ");
			compTo = compScan.nextLine();
		//}
		
		//while (!emailChecker(compCc))
		//{
			System.out.printf("\nPlease enter who is CC'd on the email in in a valid format: ");
			compCc = compScan.nextLine();
		//}
		
		//while (!emailChecker(compBcc))
		//{
			System.out.printf("\nPlease enter who is BCC'd on the email is to in a valid format: ");
			compBcc = compScan.nextLine();
		//}
		
		System.out.printf("\nPlease enter the subject of the email: ");
		compSubject = compScan.nextLine();
		
		System.out.printf("\nPlease enter the body of the email: ");
		compBody = compScan.nextLine();
		
		//initialize the email
		Email compEmail = new Email();
		compEmail.setTo(compTo);
		compEmail.setCc(compCc);
		compEmail.setBcc(compBcc);
		compEmail.setSubject(compSubject);
		compEmail.setBody(compBody);
		
		//add it to the inbox
		this.getFolder("Inbox").addEmail(compEmail);
	}
	
	/**
	* moves the email to the trash
	*
	* @param email
	*	the email to be deleted
	**/
	public void deleteEmail(Email email)
	{
		this.trash.addEmail(email);
		
	}
	
	/**
	* Removes all emails in the trash
	**/
	public void clearTrash()
	{
		this.trash.clearFolder();
	}
	
	/**
	* moves an email to a given inbox
	* if the folder is not found then it is moved to the inbox
	* doesnt remove a folder, remove email does that
	*
	* @param email
	*	the email we want moved
	*
	* @param target
	*	where we want the email to end up
	*
	* Postcondition:
	*	the given email has been moved to the target
	**/
	public void moveEmail(Email email, Folder target)
	{
		if (target == null)
			this.inbox.addEmail(email);
		else
			target.addEmail(email);
	}
	
	/**
	* gets the folder with the given name
	*
	* @param name
	*	the name of the desired folder
	**/
	public Folder getFolder(String name)
	{
		if (name.toUpperCase().equals("INBOX"))
		{
			return this.inbox;
		} else if (name.toUpperCase().equals("TRASH")) { 
			return this.trash;
		} else {
			int i = 0;
			while (i < this.folders.size() && !this.folders.get(i).getName().toUpperCase().equals(name.toUpperCase()))
				i++;
			
			if (i >= this.folders.size())
			{	
				return null;
			} else {
				return this.folders.get(i);
			}
		}
	}
	
	/**
	* Returns whether or not the email is in email format
	*
	* @returns
	*	a boolean as to whether or not the email is in the right format
	**/
	public boolean emailChecker(String inEmail)
	{	
		return inEmail.matches("[A-Fa-f0-9_]@[A-Fa-f0-9].[A-Fa-f0-9]");
	}
	
	public void printFolders()
	{
		System.out.println("\nMailbox folders: ");
		System.out.println("\tInbox");
		System.out.println("\tTrash");
		if (this.folders != null)
			for (int x = 0; x < this.folders.size(); x++)
				System.out.println("\t" + this.folders.get(x).getName());
		//System.out.println("");
	
	}
		
	
	
	//==================================================================================================
	// below is the main and all the methods used in it
	
	/**
	* The main method allows the user to interact with the Mailbox class
	**/
	public static void main (String args[])
	{
		System.out.println("Welcome to your email inbox.");
		System.out.println("Lets be honest: most of the messages in here will get trashed anyways...");
		System.out.println("You're just here to procrastinate.\n");
		
		String fileName = "saveMailbox.bin";
		
		Mailbox workMailbox = null;
		try { // https://www.youtube.com/watch?v=YzwiuRDgSSY
			FileInputStream    file = new FileInputStream(fileName);
			ObjectInputStream  fin  = new ObjectInputStream(file);
			 workMailbox = (Mailbox) fin.readObject();
			file.close();
			System.out.println("Succesfully loaded a saved Mailbox.\n");
		} catch (IOException | ClassNotFoundException a) {
			System.out.println("Created new mailbox!\n");
			workMailbox = new Mailbox();
		}
		
		//System.out.println(workMailbox.getFolder("Inbox").getSortingMethod()); // test to make sure we have some working mailbox
		
		// main variables
		boolean quit = false; // used to stay in the program
		String menuChoice; //string that is the choice selected
		Scanner inScanner = new Scanner(System.in); // general scanner that will be used throughout the entire program
		
		// submenu variables
		boolean subQuit = false; //used to stay in submenu
		Folder workingFolder; // folder we are currently working on
		String subChoice;
		String inWorkingFolder; // if they want to work with a specific folder this is it here
		int inIndex;
		
		
		//add folder String
		String inFolderName;
		
		//remove folder string
		String remFolder;
		
		//move variables
		int moveIndex;
		String moveFolderName;
		Folder moveFolder;
		Email moveEmail;
		
		
		
		while (!quit)
		{
			workMailbox.printFolders();
			printMenu();
			menuChoice = inScanner.nextLine().toUpperCase();
		
		
			if (menuChoice.equals("A")) 
			{ // add folder
		
				System.out.printf("\nPlease input the name of the folder: ");
				inFolderName = inScanner.nextLine();
				workMailbox.addFolder( new Folder(inFolderName));
				
				
			} else if (menuChoice.equals("R")) { // remove folder
				System.out.printf("\nEnter the name of the folder you want removed: ");
				remFolder = inScanner.nextLine();
				workMailbox.deleteFolder(remFolder);
				System.out.println("Folder removed.\n");
				
			} else if (menuChoice.equals("C")) { // compose email
				workMailbox.composeEmail();		
			
			
			} else if (menuChoice.equals("I") ||  menuChoice.equals("T") || menuChoice.equals("F")  ) { 
				// view inbox, trash or other folder calls submenu
				
				subQuit = false;
				workingFolder = null; // it kept saying I had not ititialized this variable which I had, 
					//so this was the wwas the workaround

				if (menuChoice.equals("I"))
				{
					workingFolder = workMailbox.getFolder("Inbox");
				}
				else if (menuChoice.equals("T"))
				{
					workingFolder = workMailbox.getFolder("Trash");
				} else if (menuChoice.equals("F")) { //  we need to fix this 
					System.out.printf("\nEnter the name of the folder you want to work on: ");
					inWorkingFolder = inScanner.nextLine();
				
					workingFolder = workMailbox.getFolder(inWorkingFolder);
					
					if (workingFolder == null)
					{
						System.out.println("Folder not found, returning to main menu.\n");
						subQuit = true;
					}
				}
				
				
				//System.out.println(workingFolder.getName());
				
				while(!subQuit)
				{
					workingFolder.printFolder();
					printSubMenu();
					subChoice = inScanner.nextLine();
					
					if (subChoice.toUpperCase().equals("M")) 
					{
						System.out.printf("\nEnter the index of the email you want to move: ");
						moveIndex = inScanner.nextInt();
						String crud = inScanner.nextLine();
						
						System.out.printf("\nEnter the name of the folder you want to move this email to: ");
						moveFolderName = inScanner.nextLine();
						
						moveFolder = workMailbox.getFolder(moveFolderName);					
						moveEmail = workingFolder.getEmail(moveIndex);
						
						if (moveEmail !=null && moveFolder != null)
						{
							workMailbox.moveEmail(moveEmail, moveFolder);
							workingFolder.removeEmail(moveIndex);
						} else {
							System.out.println("There was an error in moving the email.");
						}
						
						
					} else if (subChoice.toUpperCase().equals("D")) {
						moveFolderName = "";
						
						System.out.printf("\nEnter the index of the email you want to move to trash: ");
						moveIndex = inScanner.nextInt();
						String crud = inScanner.nextLine();
						
						moveFolder = workMailbox.getFolder("Trash");					
						moveEmail = workingFolder.getEmail(moveIndex);
						
						if (moveEmail !=null && moveFolder != null)
						{
							workMailbox.moveEmail(moveEmail, moveFolder);
							workingFolder.removeEmail(moveIndex);
						} else {
							System.out.println("There was an error in moving the email.");
						}
						
						
						
						
					
					} else if (subChoice.toUpperCase().equals("V")) {
						System.out.printf("\nPlease input the index of the email you want: ");
						inIndex = inScanner.nextInt();
						String vCrud = inScanner.nextLine();
						
						if (workingFolder.getEmail(inIndex) != null)
						{
							workingFolder.getEmail(inIndex).viewEmailContents();
						} else {
							System.out.println("There is no email at that index!");
						}
					
					} else if (subChoice.toUpperCase().equals("SA")) {
						workingFolder.sortBySubjectAscending();
						System.out.println("Folder sorted by Subject Ascending.\n");
						
					} else if (subChoice.toUpperCase().equals("SD")) {
						workingFolder.sortBySubjectDescending();
						System.out.println("Folder sorted by Subject Descending.\n");
						
					} else if (subChoice.toUpperCase().equals("DA")) {
						workingFolder.sortByDateAscending();
						System.out.println("Folder sorted by Date Ascending.\n");
					
					} else if (subChoice.toUpperCase().equals("DD")) {
						workingFolder.sortByDateDescending();
						System.out.println("Folder sorted by Date Descending.\n");
					
					} else if (subChoice.toUpperCase().equals("R")) {
						System.out.println("\nReturning to main menu.\n");
						subQuit = true; 
					} else {
						System.out.println("Thats not an option!"); 		
					}
				}
			
			} else if (menuChoice.equals("E")) { // empty trash
				System.out.println("\nEmptying trash...");
				workMailbox.clearTrash();
				System.out.println("Trash emptied.\n");
			} else if (menuChoice.equals("Q")) {// quit program
				quit = true;
				System.out.println("\nTerminating program.....");
			} else {
				System.out.println("Thats not an option!");
			}		
				
		} // end while !quit
		
		
		System.out.println("Saving mailbox...");
		try { // https://www.youtube.com/watch?v=YzwiuRDgSSY
			
				FileOutputStream file = new FileOutputStream(fileName);
				ObjectOutputStream fout = new ObjectOutputStream(file);
				fout.writeObject(workMailbox);
				fout.close();
				System.out.println("Mailbox succesfully saved!");
			} catch (IOException a ) {
				System.out.println("There was an error saving the file!");
			} 
		System.out.println("Have a great day!\n\n");
	} // end main
	
	
	/**
	* Prints the main menu in a format that satisfies my OCD
	**/
	public static void printMenu()
	{
		System.out.println("\nMenu:");
		System.out.println("\tA - Add Folder");
		System.out.println("\tR - Remove Folder");
		System.out.println("\tC - Compose Email");
		System.out.println("\tF - View Folder");
		System.out.println("\tI - View Inbox");
		System.out.println("\tT - View Trash");
		System.out.println("\tE - Empty Trash");
		System.out.println("\tQ - Quit Program");
		System.out.printf("Choice: ");		
	}
	
	/**
	* Prints the submenu
	**/
	public static void printSubMenu()
	{
		System.out.println("\nFolder Sub Menu: ");
		System.out.println("\tM  - Move email");
		System.out.println("\tD  - Delete Email");
		System.out.println("\tV  - View Email Contents");
		System.out.println("\tSA - Sort by Subjects Ascending");
		System.out.println("\tSD - Sort by Subjects Descending");
		System.out.println("\tDA - Sort by Date Ascending");
		System.out.println("\tDD - Sort by Date Ascending");
		System.out.println("\tR  - Return to main menu");
		System.out.printf("Choice: ");
	}	
	
} // end class