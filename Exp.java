// quick experiment with the ArrayList
import java.util.ArrayList;

import java.util.Calendar; //https://www.youtube.com/watch?v=J4lYwAy37d8
import java.util.GregorianCalendar;
public class Exp{

	public static void main(String args[]) {

	ArrayList<Integer>  josh = new ArrayList<Integer>() ;

	josh.add(0);
	josh.add(1);
	josh.add(3);
	josh.add(4);

	for (int x = 0; x<4;x++)
		System.out.println(josh.get(x));

	josh.add(2, 2);
	System.out.println("=============");

	for (int x = 0; x<5;x++)
		System.out.println(josh.get(x));	

	
	GregorianCalendar cal1 = new GregorianCalendar();
	GregorianCalendar cal2 = new GregorianCalendar();
	
	System.out.println("Hello World");
	System.out.println(cal1.getTime());
	
	System.out.println(cal1.compareTo(cal2));
	

	} // end main
} // end class
