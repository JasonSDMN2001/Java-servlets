package previousResources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient extends User {
    /*final*/protected String x;  //η μεταβλητη στην οποια εκχωρειται το ΑΜΚΑ για να την χρησιμοποιησουμε στον constructor.
    final String amka;


   /* public String getAmka() {
        return amka;
    }

    // public void setAmka(int amka) { Αφού δεν επιτρέπεται να τροποποιηθεί,για ποιο λόγο το set?
    //     this.amka = amka;
    // }

    public boolean registration() {        //εγγραφή χρήστη
        return true;
    } //εγραφη

    public boolean searchappointmentbydoctor() {  //αναζήτηση διαθέσιμου ραντεβού για συγκεκριμένο ιατρό
        return true;
    } //ψαχνει ραντεβου για οποιονδηποτε γιατρο

    public boolean searchappointmentbyspecialty() {  //αναζήτηση ραντεβού για οποιονδήποτε διαθέσιμο ιατρό μίας ειδικότητας
        return true;
    } //αναλογα την ειδικοτητα του

    public boolean scheduledapointments(){ //προγραμματισμενα ραντεβου
        return true;
    } //προγγραματισμενα ραντεβου
    public boolean allapointments(){ //ιστορικο των ραντεβου
        return true;
    } //προβολη ιστορικου ραντεβου*/

    //δεχομαστε καταλληλα αντικειμενα στα ορισματα της μεθοδου makeappoitment και τα περναμε στην class Appointment
    public boolean makeappointment(Doctor doc,String datetime,String datetime2) throws SQLException {     //κλείσιμο ραντεβού χρήστη
        Appointment app = new Appointment(this,doc,datetime,datetime2);
        return true;
    }

    //διαγραφή ραντεβού ασθενή από την βάση
    public boolean cancelappointment(String date, String time, String doctorID) throws SQLException {    //ακύρωση ραντεβού χρήστη
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		int cancel = stmt.executeUpdate("DELETE FROM mydb.appointment WHERE \""+date+"\">current_date() + INTERVAL 3 DAY AND Availability_date = \""+date+"\"  AND Availability_time =\""+time+"\"  AND Availability_doctorID=\""+doctorID+"\"");
		stmt.close();
		if (cancel==0) {
			stmt2.close();
			con.close();
			return false;
		}
		else {
			//θέσιμο διαθεσιμότητας ιατρού σε θετική, στην ημερομηνία και ώρα του ακυρωμένου ραντεβού
			int releaseavailability = stmt2.executeUpdate("UPDATE mydb.availability SET available = true WHERE available = false AND date = \""+date+"\"  AND time =\""+time+"\"  AND doctorID=\""+doctorID+"\"");
			stmt2.close();
			con.close();
			return true;
		}
    }


    public Patient(String x,String name,String pass) {
        super(name,pass);
        amka=x;
    }

}