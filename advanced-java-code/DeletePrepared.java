

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class DeletePrepared {
	public static void main(String args[]){
		 try{  
		    	Class.forName("com.mysql.jdbc.Driver");  
			    
			      
		    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestappdb", "shahimranalam",
						"shahimranalam@1980");
			    
		    PreparedStatement stmt=con.prepareStatement("delete from user_credentials where user_name=?");  
		     
		    stmt.setString(1,"testuser"); 
		    int i=stmt.executeUpdate();
		   	    System.out.println(i+" records deleted");  
		      
		    con.close();  
		      
		    }catch(Exception e){ System.out.println(e);}  
		     	
			
	}

}
