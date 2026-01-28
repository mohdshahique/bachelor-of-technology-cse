import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StatementDemo {
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
			
			
			
			
			/*int count=stmt.executeUpdate("insert into user_credentials values(11,'aaa1','aaa1pwd')");
			count=stmt.executeUpdate("update user_credentials set user_password='helloabcde2' where user_name='abcde2'");
			count=stmt.executeUpdate("delete from user_credentials where user_name='a1'");
			*/
			
			//5. Close the connection object
			Scanner scan = new Scanner(System.in);
			String sql=scan.nextLine();
			boolean result=stmt.execute(sql);
			if(result==true) {
				ResultSet rs=stmt.getResultSet();	
				while (rs.next())
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
				
			}else {
				System.out.println("the number of records effected are = "+stmt.getUpdateCount());
			}
			
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
