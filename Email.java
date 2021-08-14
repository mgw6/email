/**
* The Email class implements an Email object that contains all the necessary information in the email
*
* @author MacGregor Winegard
*	email: macgregor.winegard@stonybrook.edu
* 	Stony Brook ID: 114152787
*
* @version 1 Build 1 August 5 2020
**/

import java.util.Calendar; //https://www.youtube.com/watch?v=J4lYwAy37d8
import java.io.*;
import java.util.*; //https://stackoverflow.com/questions/447898/what-is-object-serialization
public class Email implements Serializable {
	
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String body;
	private GregorianCalendar timeStamp;
	
	/* invariants
	to is who the message is to
	cc is who is cc'd on the email
	bcc is who is bcc'd on the email
	Subject is the subject line on the email
	body is the body of the email
	time stamp is the time the email was creates */
	
	/**
	* Returns an instance of an Email object
	**/
	public Email()
	{
		this.to = "";
		this.cc = "";
		this.bcc = "";
		this.subject = "";
		this.body = "";
		this.timeStamp = new GregorianCalendar();
	}
	
	/**
	* Sets the to line of the email
	*
	* @param inTo
	*	who the email is to
	**/
	public void setTo(String inTo)
	{
		this.to = inTo;
	}
	
	/**
	* Gets the to line of the email
	*
	* @returns
	*	the to line of email
	**/
	public String getTo()
	{
		return this.to;
	}
	
	/**
	*sets this cc
	*
	* @param inCc
	* sets this cc
	**/
	public void setCc(String inCc)
	{
		this.cc = inCc;
	}
	
	/*
	*gets this cc
	*
	* @returns
	* 	this cc
	**/
	public String getCc()
	{
		return this.cc;
	}
	
	/**
	* sets the bcc
	*
	* @param inBcc
	*	the desired bcc of this email
	**/
	public void setBcc(String inBcc)
	{
		this.bcc = inBcc;
	}
	
	/**
	* sets the bcc
	*
	* @param inBcc
	*	the desired bcc of the email
	**/
	public String getBcc()
	{
		return this.bcc;
	}
	
	/**
	* Sets the subject of the email
	*
	* @param inSubject
	* 	the desired subject of the email
	**/
	public void setSubject(String inSubject)
	{
		this.subject = inSubject;
	}
	
	/*
	* gets the subject of this email
	*
	* @returns 
	*	this subject
	**/
	public String getSubject()
	{
		return this.subject;
	}
	
	/**
	* sets the body of this email
	*
	* @param inBody
	* the desired body of this email
	**/
	public void setBody(String inBody)
	{
		this.body = inBody;
	}
	
	/**
	* gets the body of this email
	*
	* @returns
	*	this body
	**/
	public String getBody()
	{
		return this.body;
	}
	
	/**
	* sets the time stamp of this email
	*
	* @param inTimeStamp
	* 	the desired timestamp of this email
	**/
	public void setTimeStamp(GregorianCalendar inTimeStamp)
	{
		this.timeStamp = inTimeStamp;
	}
	
	/**
	* sets the timestamp of this email
	*
	* @returns
	* 	this timeStamp
	**/
	public GregorianCalendar getTimeStamp()
	{
		return this.timeStamp;
	}
	
	
	/**
	* Prints the contents of the  email in a format that is friendly with my OCD
	**/
	public void viewEmailContents()
	{
		System.out.println("Subject: " + this.subject);
		System.out.println("\tTime: " + this.timeStamp.getTime()); //time
		System.out.println("\tTo: " + this.to);
		System.out.println("\tCC: " + this.cc); //cc
		System.out.println("\tBCC: " + this.bcc); //bcc
		System.out.println("\tBody: " + this.body); //subject
	}
} // end class