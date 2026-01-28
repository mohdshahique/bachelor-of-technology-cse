import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class UpdatePrepared {
	public static void main(String args[]){
		 try{  
		    	Class.forName("com.mysql.jdbc.Driver");  
			    
			Statement stmt1; 
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestappdb", "shahimranalam",
					"shahimranalam@1980");
			    
		    PreparedStatement stmt=con.prepareStatement("update user_credentials set user_password=? where user_name=?");  
		    
		     
		    stmt.setString(1,"kumar"); 
		    stmt.setString(2,"amit");
		   
		    int i=stmt.executeUpdate();
		   	    System.out.println(i+" records updated");  
		      
		    con.close();  
		      
		    }catch(Exception e){ System.out.println(e);}  
		     	
			
	}

}
