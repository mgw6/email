/**
* The Folder class stores an array of emails
*
* @author MacGregor Winegard
*	email: macgregor.winegard@stonybrook.edu
* 	Stony Brook ID: 114152787
*
* @version 1 Build 1 August 5 2020
**/

import java.util.Calendar; //https://www.youtube.com/watch?v=J4lYwAy37d8
import java.io.*;
import java.util.*; 
public class Folder implements Serializable
{
	
	private ArrayList<Email> emails;
	private String name;
	private String currentSortingMethod;
	
	/* invariants
	emails is the list of emails
	name is the name of the folder
	currentSortingMethod is the sorting method currently being used
		to sort the folder
	*/
	
	/*
	Types of currentSortingMethod :
	"Date Ascending"
	"Date Descending"
	"Subject Ascending"
	"Subject Descending"
	*/
	
	/**
	* returns an instance of a folder 
	**/
	public Folder(String inName)
	{
		this.emails =  new ArrayList<Email>();
		this.name = inName;
		this.currentSortingMethod = "Date Ascending";
	}
	
	/**
	*sets the list of emails
	*
	* @param inEmails
	*	ArrayList that contains all the Email objects
	**/
	public void setEmails(ArrayList<Email> inEmails)
	{
		this.emails = inEmails;
	}
	
	/**
	* returns the ArrayList of emails
	*
	* @returns 
	*	this list of emails
	**/
	public ArrayList<Email> getEmails()
	{
		return this.emails;
	}
	
	/**
	* sets the name of this folder
	*
	* @param inName
	* 	the desired name of the folder
	**/
	public void setName(String inName)
	{
		this.name = inName;
	}
	
	/**
	* gets the name of the folder
	*
	* @returns
	*	the name of this folder
	**/
	public String getName()
	{
		return this.name;
	}
	
	/**
	* sets the sorting method of the folder
	*
	* @param inSortingMethod
	*	the desired sorting method of the folder
	**/
	public void setSortingMethod(String inSortingMethod)
	{
		this.currentSortingMethod = inSortingMethod;
	}
	
	/**
	* gets the sorting method
	*
	* @returns 
	*	the currentSortingMethod of the folder 
	**/
	public String getSortingMethod()
	{
		return this.currentSortingMethod;
	}
	
	/**
	* Adds an email to the list of emails
	*
	* @param inEmail
	*	the email object being added to the list
	**/
	public void addEmail(Email inEmail)
	{ //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
		int location = 0;
		//System.out.println("Size: " + this.emails.size());
		
		
		// once we get the above sorted out we probably want to make this an if else 
		if (this.currentSortingMethod.equals("Date Ascending")) {
			
			
			while(location < this.emails.size() && inEmail.getTimeStamp().compareTo(this.emails.get(location).getTimeStamp()) <=0 )
			{	
				//System.out.println("Entered while, count : " + location);
				location++;
			}	
			
		
		} else if (this.currentSortingMethod.equals("Date Descending")) {
			
			while(location < this.emails.size() && inEmail.getTimeStamp().compareTo(this.emails.get(location).getTimeStamp()) >=0 )
			{
				//System.out.println("Entered while, count : " + location);
				location++;				
			}
		
		} else if (this.currentSortingMethod.equals("Subject Ascending")) {
			
			while(location < this.emails.size() && inEmail.getSubject().compareTo(this.emails.get(location).getSubject()) <=0)
			{	
				//System.out.println("Entered while, count : " + location);
				location++;
				
			}
		
		} else if (this.currentSortingMethod.equals("Subject Descending")) {
			
			while(location < this.emails.size() && inEmail.getSubject().compareTo(this.emails.get(location).getSubject()) >=0)
			{
				//System.out.println("Entered while, count : " + location);
				location++;				
			}	
		
		} else {
			System.out.println("There is a problem with the currentSortingMethod!!");
		}
		
		this.emails.add(location, inEmail);
		//System.out.println("Location: " + location);
	}
	
	/**
	* removes an email from the emails list
	*
	* @param index
	*	the location of the email you want to remove
	*
	* @returns
	*	the email that was removed
	**/
	public Email removeEmail(int index)
	{
		Email temp = this.emails.get(index);
		this.emails.remove(index);
		return temp;
	}
	
	/*
	* Sorts the emails by subject ascending
	*
	* Postcondition
	*	the emails have been sorted in the emails list by subject ascending
	**/
	public void sortBySubjectAscending()
	{
		int listSize = this.emails.size();
		int i, j, minLocation;
		
		for (i = 0; i<=listSize - 2; i++)
		{
			minLocation = i;
			for(j=i+1; j<=listSize - 1 ; j++)
			{
				if 	(this.emails.get(j).getSubject().compareTo(this.emails.get(minLocation).getSubject()) < 0 )
				{	
					minLocation = j;		
				}
				Email temp = this.emails.get(i);
				this.emails.set(i, this.emails.get(minLocation));
				this.emails.set(minLocation, temp);
			}
		}
		this.currentSortingMethod = "Subject Ascending";
	} 
	
	/*
	* Sorts the emails by subject descending
	*
	* Postcondition:
	*	the emails have been sorted in the emails list by subject descending
	**/
	public void sortBySubjectDescending()
	{
		int listSize = this.emails.size();
		int i, j, minLocation;
		
		for (i = 0; i<= listSize - 2; i++)
		{
			minLocation = i;
			for(j=i+1; j<= listSize - 1; j++)
			{
				if 	(this.emails.get(j).getSubject().compareTo(this.emails.get(minLocation).getSubject()) > 0 )
				{	
					minLocation = j;		
				}
				
				Email temp = this.emails.get(i);
				this.emails.set(i, this.emails.get(minLocation));
				this.emails.set(minLocation, temp);
			}	
		}
		this.currentSortingMethod = "Subject Descending";
	}
	
	/*
	* Sorts the emails by date ascending
	*
	* Postcondition
	*	the emails have been sorted in the emails list by date ascending
	**/
	public void sortByDateAscending()
	{
		int listSize = this.emails.size();
		int i, j, minLocation;
		
		for (i = 0; i<= listSize - 2; i++)
		{
			minLocation = i;
			for(j=i+1; j<= listSize -1; j++)
			{
				if 	(this.emails.get(j).getTimeStamp().compareTo(this.emails.get(minLocation).getTimeStamp()) < 0 )
				{	
					minLocation = j;		
				}
				Email temp = this.emails.get(i);
				this.emails.set(i, this.emails.get(minLocation));
				this.emails.set(minLocation, temp);
			}
		}	
		this.currentSortingMethod = "Date Ascending";
	}

	/*
	* Sorts the emails by date descending
	*
	* Postcondition
	*	the emails have been sorted in the emails list by date descending
	**/
	public void sortByDateDescending()
	{
		int listSize = this.emails.size();
		int i, j, minLocation;
		
		for (i = 0; i<= listSize - 2; i++)
		{
			minLocation = i;
			for(j=i+1; j<= listSize-1 ; j++)
			{
				if 	(this.emails.get(j).getTimeStamp().compareTo(this.emails.get(minLocation).getTimeStamp()) > 0 )
				{
					minLocation = j;	
				}
				Email temp = this.emails.get(i);
				this.emails.set(i, this.emails.get(minLocation));
				this.emails.set(minLocation, temp);
			}	
			
		}
		this.currentSortingMethod = "Date Descending";		
	}
	
	/**
	* Sets the contents of the folder to empty
	* predominanlty used in the clear trash method
	*
	* Postcondition:
	*	the folder has been emptied
	**/
	public void clearFolder()
	{
		this.emails.clear();
	}
	
	/**
	*Prints the folder in the suggested format for the subfolder
	**/
	public void printFolder()
	{
		System.out.println("\nFolder: " + this.name);
		
		if (this.emails.size() == 0)
		{
			System.out.println("Folder is empty!");
		} else {
			System.out.printf("Index  ||              Time            || Subject");
		
			for (int x =0; x<this.emails.size(); x++)
			{
				System.out.printf("\n %d     || %s || %s", x, 
						this.emails.get(x).getTimeStamp().getTime(), 
						this.emails.get(x).getSubject());
			}
		}
	}
	
	/**
	* returns the email at the index
	*
	* @param index
	*	the index that the user wants
	**/
	public Email getEmail(int index)
	{
		return this.emails.get(index);
	}
	
} // end class