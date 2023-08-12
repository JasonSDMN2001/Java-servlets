package mainpackage;

public class Patient extends User {
    /*final*/protected String x;  //η μεταβλητη στην οποια εκχωρειται το ΑΜΚΑ για να την χρησιμοποιησουμε στον constructor.
    final String amka;


    public String getAmka() {
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
    } //προβολη ιστορικου ραντεβου

    //δεχομαστε καταλληλα αντικειμενα στα ορισματα της μεθοδου makeappoitment και τα περναμε στην class Appointment
    public boolean makeappointment(Doctor doc,String datetime,String datetime2) {     //κλείσιμο ραντεβού χρήστη
        Appointment app = new Appointment(this,doc,datetime,datetime2);
        return true;
    }

    public boolean cancelappointment() {    //ακύρωση ραντεβού χρήστη
        return true;
    } //ακυρωση ραντεβου


    public Patient(String x,String name,String pass) {
        super(name,pass);
        amka=x;
    }

}
