package mainpackage;


public class Doctor extends User{
    protected String specialty; //ειδικοτητα του γιατρου

    public String getSpecialty() { //τι ειδικοτητα ειναι ο γιατρος
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty=specialty;
    }

    public boolean availability() {  //Καταχώρηση διαθέσιμων ημερομηνιών από τη μεριά του ιατρού.

        return true;
    }

    public boolean programview() {   //Προβολή προγράμματος ραντεβού του γιατρού
        return true;
    } //προσεχες μεθοδοι που θα βλεπουμε το προγραμμα του γιατρου

    public boolean cancelavailability(){  //ακύρωση διαθεσιμότητας από μεριάς γιατρού.
        return true;
    } //ακυρωση ραντεβου

    public Doctor(String name,String pass) {

        super(name,pass);

    }
}