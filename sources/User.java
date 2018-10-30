
public class User {
	
	int ID;
	String vorname;
	String nachname;
	String mail;
	String nutzername;
	String passwort;
	boolean admin;
	
	User(int ID, String vorname, String nachname, String passwort, String mail, String nutzername, boolean admin){
		this.ID = ID;
		this.vorname = vorname;
		this.nachname = nachname;
		this.mail = mail;
		this.nutzername = nutzername;
		this.passwort = passwort;
		this.admin = admin;
	}
}
