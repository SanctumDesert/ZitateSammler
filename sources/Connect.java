import java.sql.Connection;
import java.sql.DriverManager;


public class Connect {

	Connection connection;
	Connect(){
		try {
			
			//Connection to Localhost
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zitatedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", "root", "");
			//Connection to Fabian
			//this.connection = DriverManager.getConnection("jdbc:mysql://169.254.207.248:3306/zitatedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", "Lukas", "root");
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
	
	Connection getConnection() {
		return connection;
	}
	
}
