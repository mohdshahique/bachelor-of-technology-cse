import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectionSimple {
	static ResultSet rs;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt=null;
		try {
			//1. Register the Driver class
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. Get the Connection Object
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestappdb", "shahimranalam",
					"shahimranalam@1980");
			
			//3. Create the Statement 
			 stmt = con.createStatement();
			
			//4. Execute the query
			ResultSet rs = stmt.executeQuery("select * from user_credentials");
			
			//Process the result
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			
			//5. Close the connection object
			con.close();
			

		} catch (SQLException e) {
			System.out.println(" Inside catch SQLException block");
			System.out.println(e.getCause());

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
