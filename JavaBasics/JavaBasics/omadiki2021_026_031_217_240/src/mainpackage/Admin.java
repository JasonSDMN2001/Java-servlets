package mainpackage;
public class Admin extends User {

    //δημιουργια αντικειμενων τυπου doctor
    public boolean doctorregistration() {
        return true;
    }

    //μεθοδος διαγραφης
    public boolean deletedoctor(){
        return true;
    }

    public Admin(String name,String pass) {
        super(name,pass);
    }
}