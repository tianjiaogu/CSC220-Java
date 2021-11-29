package lab06;

import java.util.Comparator;

public class OrderStrings implements Comparator<String> 
{

 	public int compare( String m, String n) 
 	{
 		return m.compareTo(n);
 	}
  }