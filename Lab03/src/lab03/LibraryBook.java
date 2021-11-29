package lab03;

import java.util.GregorianCalendar;
// What imports do you need to include? Put them here.

public class LibraryBook extends Book { 
String holder;
GregorianCalendar duedate;
	// A LibraryBook contains a holder (a String) and due date represented by
	// a GregorianCalendar
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		
	}

	public String getHolder() {
		return this.holder;
		 // placeholder
	}
	
	public GregorianCalendar getDueDate() {
		return this.duedate; // placeholder
	}
	
	public void checkin() {
		
		holder = null;
		duedate = null;
	
	}
	
	public void checkout(String holder, GregorianCalendar dueDate){
		
		this.holder = holder;
		this.duedate = dueDate;
		
		
	}	

	// Do not override the equals method in Book

}