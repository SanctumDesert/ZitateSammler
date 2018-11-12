import java.sql.Connection;
import java.sql.DriverManager;


public class Connect {

	Connection connection;
	Connect(){
		try {
			
			//Connection to Localhost
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zitatedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", "root", "");
			System.out.println("Connection successfull!");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	Connection getConnection() {
		return connection;
	}
	
}
