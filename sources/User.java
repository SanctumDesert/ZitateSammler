
public class User {
	
	int ID;
	String vorname;
	String nachname;
	String mail;
	String nutzername;
	String passwort;
	int klassenID;
	boolean admin;
	
	User(int ID, String vorname, String nachname, String mail, String nutzername, String passwort, int klassenID, boolean admin){
		this.ID = ID;
		this.vorname = vorname;
		this.nachname = nachname;
		this.mail = mail;
		this.nutzername = nutzername;
		this.passwort = passwort;
		this.klassenID = klassenID;
		this.admin = admin;
	}
}
