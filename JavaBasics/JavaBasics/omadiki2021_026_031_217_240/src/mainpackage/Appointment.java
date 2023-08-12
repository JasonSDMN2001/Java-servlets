package mainpackage;

public class Appointment {

    protected String appoint,time;
    Patient mypatient = new Patient("00022424242","gdgd","fgdgd");
    Doctor mydoctor = new Doctor("34535235","sege");

    //αρχικα απο την CreateUsers θα δεχομαστε στα ορισματα καταλληλα αντικειμενα
    //και θα ελεγχουμε αν μπορει να γινει το ραντεβου
    public Appointment(Patient patient, Doctor doctor,String date,String time1) {
        mypatient = patient;
        mydoctor = doctor;
        appoint = date;
        time=time1;
        User.usersCounter=User.usersCounter-2;
    }
}
