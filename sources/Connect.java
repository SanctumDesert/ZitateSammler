import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;

public class Connect {

	public static void main(String[] args) {
		//Connect(){
		try {
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://169.254.207.248:3306/zitatedb", "Lukas", "");
			System.out.println("Connection successfull!");
		}
		catch(Exception e){
			System.out.println(e);
		}
	//}
	}
	
}
