package previousResources;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import userServlets.PatientServlet;

import java.util.Random;

public class Admin extends User {
	private DataSource datasource = null;
	String aname, adminID;

    //δημιουργια doctor
    public boolean doctorregistration(String username, String password, String firstName, String surname, String address, String phoneNumber, String specialty) throws SQLException {
    	//επιστρoφή doctorID
    	String doctorID = idgenerator(username);
    	//διαδικασία hashing + salting 
    	SecureRandom random = new SecureRandom();
		byte bytes[]= new byte[20];
		 random.nextBytes(bytes);
		password = PatientServlet.getHashMD5(password,random.toString());
		//σύνδεση στην βάση 
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		//εύρεση πεδίου adminID με query 
		ResultSet rsID = stmt2.executeQuery("SELECT adminID FROM mydb.admin WHERE username = '"+aname+" ';");
		while (rsID.next()) {
			adminID = rsID.getString("adminID");
		}
		//εισαγωγή νέου ιατρού στην βάση με τα στοιχεία που δόθηκαν απο τον διαχειριστή
		int rscreate = stmt.executeUpdate("INSERT INTO mydb.doctor(doctorID,username,password,firstName,surname,address,phoneNumber,specialty,Admin_adminID) VALUES("+doctorID+",\""+username+"\",\""+password+"\",\""+firstName+"\",\""+surname+"\",\""+address+"\",\""+phoneNumber+"\",\""+specialty+"\",\""+adminID+"\")");
		rsID.close();
		stmt.close();
		stmt2.close();
		con.close();
		return true;
    }

    //μεθοδος διαγραφης
    public boolean deletedoctor(String doctorID) throws SQLException{
    	Connection conDel = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
		Statement del = conDel.createStatement();
		int rsdelete = del.executeUpdate("DELETE FROM mydb.doctor WHERE doctorID="+doctorID);
		conDel.close();
		if(rsdelete>0) {
        return true;
        	}
		else {
        	return false;
        	}
		
    }
    //μέθοδος εύρεσης του doctorID από το username του ιατρού
    public String idgenerator(String username){
    	Random rd = new Random();
    	int num = rd.nextInt(99999);
    	String formatted = String.format("%05d", num);
    	try {
    	Connection conDoc = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
		Statement stmtDoc = conDoc.createStatement();
		ResultSet rsIDcheck = stmtDoc.executeQuery("SELECT username FROM mydb.doctor WHERE doctorID = '"+formatted+" ';");
			if (rsIDcheck.next()) {
				rsIDcheck.close();
				conDoc.close();
				idgenerator(username);
			}
			else {
				rsIDcheck.close();
				conDoc.close();
			}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	return formatted;
    }

    public Admin(String name,String pass) {
        super(name,pass);
        aname = name;
        try {
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} 
		catch(Exception e) {
			e.printStackTrace();
			}
    }
}