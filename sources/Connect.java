import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	Connect(){
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://169.254.207.248:3306/zitatedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", "Lukas", "root");
			System.out.println("Connection successfull!");
			/*Statement myStmt = connection.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM tbluser");
			while(myRs.next()) {
				System.out.println(myRs.getString("vorname") + "," + myRs.getString("nachname"));
			}*/
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}
