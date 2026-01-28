    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
    class InsertPreparedWithBugs{  
    public static void main(String args[]){  
    try{  
    	Class.forName("com.mysql.jdbc.Driver");  
	    
	      
	    Connection con=DriverManager.getConnection(  
	    "jdbc:mysql://localhost:3306/mytestappdb","shahimranalam","shahimranalam@1980");
	    
    PreparedStatement stmt=con.prepareStatement("insert into user_credentials values(?,?,?)");  
    //stmt.setInt(1,2);//1 specifies the first parameter in the query  
    //stmt.setString(2,"shah"); 
    //stmt.setString(3,"alam");
    boolean exit=false;
    int i=0;
    int records=0;
    ResultSet rs = stmt.executeQuery("select * from user_credentials");
    rs.last();
    i=rs.getRow();
;
    Scanner scan=new Scanner(System.in);
    while(!exit){
    	
    	System.out.println("Enter record "+(i+1)+": username,password");
    	stmt.setInt(1,i+1);
    	stmt.setString(2, scan.next());
    	stmt.setString(3, scan.next());
    	records=records+stmt.executeUpdate();
    	System.out.println("Enter c to continue or e to exit");
    	i++;
       	if(scan.next().equalsIgnoreCase("e")){
    		exit=true;
    			
    	}
       	
    }
      
   // int i=stmt.executeUpdate();  
    System.out.println(i+" records inserted");  
    
     rs = stmt.executeQuery("select * from user_credentials");
	System.out.println("Present Table state  ------------------------------------");
	//Process the result
	while (rs.next())
		System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
	
      
    con.close();  
      
    }catch(Exception e){ System.out.println(e);}  
      
    }  
    }  