package mainpackage;

import java.io.File;
import java.io.FileNotFoundException;       //καποια exceptions ου μπορει να προκυψουν αν δεν ανοιγει το αρχειο
import java.util.ArrayList;
import java.util.InputMismatchException; //για οταν τα στοιχεια που του δωσαμε απο τον scanner δεν ειναι ορθα
import java.util.List;
import java.util.Scanner;

import static java.lang.Long.parseLong; //μια μετατροπη απο string σε long που χρειαζομαστε οταν φτιαξουμε ασθενη απο αρχειο
import static mainpackage.User.usersCounter;  //χρειαζομαστε τον μετρητη των χρηστων απο την κλαση user γιατι δεν την κληρονομει η createusers

public class CreateUsers {

    //δημιουργια αντικειμενων
    public static void main(String[] args) {

        User u1 = new User("3242","kle");//Κάθε φορά που δημιουργείται ένα αντικείμενο,καλείται ο constructor της User και το usersCounter αυξάνεται κατά ένα.
        System.out.println("Δημιουργία χρήστη u1");
        u1.setName("name1");
        System.out.println("Όνομα χρήστη u1:"+u1.getName());
        u1.setSurname("surname1");
        System.out.println("Eπώνυμο χρήστη u1:"+u1.getSurname());
        u1.setUsername("user1");
        System.out.println("Username χρήστη u1:"+u1.getUsername());
        u1.setPassword("ags22");
        System.out.println("Κωδικός χρήστη u1:"+u1.getPassword());
        System.out.println("Αριθμός χρηστών:"+usersCounter);
        System.out.println("Σύνδεση στο σύστημα:"+u1.Login());
        System.out.println("Aποσύνδεση από το σύστημα:"+u1.Logout());


        Patient p1 = new Patient("35252525223","giannis","124r"); //Κάθε φορά που δημιουργείται ένα αντικείμενο,καλείται ο constructor της patient και το usersCounter αυξάνεται κατά ένα.
        System.out.println("Δημιουργία ασθενή p1");
        System.out.println("ΑΜΚΑ ασθενή:"+p1.getAmka());
        System.out.println("Επιτυχία εγγραφής χρήστη:"+p1.registration());
        System.out.println("αναζήτηση διαθέσιμου ραντεβού για συγκεκριμένο ιατρό:"+p1.searchappointmentbydoctor());
        System.out.println("αναζήτηση ραντεβού για οποιονδήποτε διαθέσιμο ιατρό μίας ειδικότητας:"+p1.searchappointmentbyspecialty());
        System.out.println("Aκύρωση ραντεβού:"+p1.cancelappointment());
        System.out.println("Προγραμματισμένα ραντεβού:"+p1.scheduledapointments());
        System.out.println("Ιστορικό των ραντεβού:"+p1.allapointments());
        Doctor d0 = new Doctor("sfsfs","3242");
        String date="22Feb";
        String time="20:00";
        System.out.println("Κλείσιμο ραντεβού:"+p1.makeappointment(d0,date,time));


        Doctor d1 = new Doctor("gdgd","35353"); //Κάθε φορά που δημιουργείται ένα αντικείμενο,καλείται ο constructor της doctor και το usersCounter αυξάνεται κατά ένα.
        System.out.println("Δημιουργία γιατρού d1");
        d1.setSpecialty("cardiologist");
        System.out.println("Ειδικότητα γιατρού:"+d1.getSpecialty());
        System.out.println("Καταχώρηση διαθέσιμων ημερομηνιών από τη μεριά του ιατρού:"+d1.availability());
        System.out.println("Προβολή προγράμματος ραντεβού του γιατρού:"+d1.programview());
        System.out.println("ακύρωση διαθεσιμότητας από μεριάς γιατρού:"+d1.cancelavailability());


        Admin a1 = new Admin("fsfd","45353"); //Κάθε φορά που δημιουργείται ένα αντικείμενο,καλείται ο constructor της admin και το usersCounter αυξάνεται κατά ένα.
        System.out.println("Εγγραφή γιατρού στο σύστημα:"+a1.doctorregistration());
        System.out.println("Διαγραφή γιατρού από το σύστημα:"+a1.deletedoctor());


        Appointment c1 = new Appointment(p1,d0,date,time);  //θελουμε να δημιουργησουμε ρανεβου μεσα απο την κλασση appoinment,για την οποια εχουμε δημιουργησει μια νεα κλασση την availability
        System.out.println("Πραγματοποιήθηκε η δημιουργία του ραντεβού c1");
        List<String> Date = new ArrayList<String>(); //δημιουργια λιστας String
        Date.add("13/3/2021 12:20-12:50");  //την οποια γεμιζουμε μελλοντικα απο αρχειο
        Availability av = new Availability(d1, Date);//ελεγχος διαθεσιμοτητας,απο εναν γιατρο,με βαση την προηγουμενη ημερομηνια
        System.out.println("Δημιουργήθηκε αντικείμενο Διαθεσιμότητας ");

        Scanner input1 = new Scanner(System.in);
        String docname,patname,docpass,patpass,patamka;   //αντικειμμενα μεταβλητες που θα μας χρησιμευσουν στο input

            while (true) { //με πολλα try catch ελεγχουμε την ορθοτητα των χαρακτηριστικων που θα περασουμε στα αντικειμενα doctor και patient
                try {
                    System.out.println("Εισάγετε το username του γιατρού");
                    docname = input1.next();
                    if (docname.length() < 4 || docname.length() > 16) {  //ελεγχουμε αν στην ακολουθια του string docname ειναι εντος καποιων οριων τιμων
                        throw new InputMismatchException();
                    } else {
                        break; //αν οχι,και ειναι μηκους μεγαλυτερο 16,τοτε σταματαει το exception
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Παρακαλούμε εισάγετε username με 4-16 χαρακτήρες");
                }
            }
            while (true) {
                try {
                    System.out.println("Εισάγετε τον κωδικό πρόσβασης του γιατρού");
                    docpass = input1.next();
                    if (docpass.length() < 6 || docpass.length() > 16) {  //ελεγχουμε αν στην ακολουθια του string docpass ειναι εντος καποιων οριων τιμων
                        throw new InputMismatchException();
                    } else {
                        break; //αν οχι,και ειναι μηκους μεγαλυτερο 16,τοτε σταματαει το exception
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Παρακαλούμε εισάγετε τον κωδικό πρόσβασης του γιατρού με 6-16 χαρακτήρες");
                }
            }
            while (true) {
                try {
                    System.out.println("Εισάγετε το username του ασθενή");
                    patname = input1.next();
                    if (patname.length() < 4 || patname.length() > 16) {  //ελεγχουμε αν στην ακολουθια του string patname ειναι εντος καποιων οριων τιμων
                        throw new InputMismatchException();
                    } else {
                        break; //αν οχι,και ειναι μηκους μεγαλυτερο 16,τοτε σταματαει το exception
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Παρακαλούμε εισάγετε το username του ασθενή με 4-16 χαρακτήρες");
                }
            }
            while (true) {
                try {
                    System.out.println("Εισάγετε τον κωδικό πρόσβασης του ασθενή");
                    patpass = input1.next();
                    if (patpass.length() < 6 || patpass.length() > 16) {  //ελεγχουμε αν στην ακολουθια του string patpass ειναι εντος καποιων οριων τιμων
                        throw new InputMismatchException();
                    } else {
                        break; //αν οχι,και ειναι μηκους μεγαλυτερο 16,τοτε σταματαει το exception
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Παρακαλούμε εισάγετε τον κωδικό πρόσβασης του ασθενή με 6-16 χαρακτήρες");
                }
            }
            while (true) {
                try {
                    System.out.println("Εισάγετε το ΑΜΚΑ του ασθενή");
                    patamka = input1.next();
                    if (patamka.matches("^[0-9]+$") && patamka.length() == 11) {  //ελεγχουμε αν στην ακολουθια του string patamka εχει γραμματα μεσα
                        break; //αν οχι,και ειναι μηκους 11,τοτε σταματαει το exception
                    } else {
                        throw new InputMismatchException();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Παρακαλούμε εισάγετε σωστά το ΑΜΚΑ");
                }
            }
            Doctor mpampis = new Doctor(docname, docpass);      //και δημιουργουμε τα αντικειμενα που ζητηθηκαν
            Patient antonis = new Patient(patamka, patname, patpass);
        try {
            File myObj = new File("omadiki2021_026_031_217_240/radevou.txt");   //βαζουμε το path του αρχειου
            Scanner myReader = new Scanner(myObj);  //οριζουμε scanner για να προσπελασουμε τα περιεχομενα του αρχειου
            String[] tokens = new String[0];    //θελουμε να βαλουμε καπου τα στοιχεια που θα διαβσουμε
            int patientCounter = 0; //βαζουμε εναν μετρητη για του ασθενεις που θα δημιουργηθουν
            List<Patient> patientList = new ArrayList<Patient>();   //το πληθος των ασθενων,απο βιβλιογραφεια
            while (myReader.hasNext()) {    //οσο εχει στοιχεια να διαβασει απο το αρχειο
                String kati = myReader.nextLine();  //μια γραμμη απο το αρχειο
                tokens = kati.split(" ");   // χωρηζουμε τα στοιχεια με κενα,και τα βαζουμε σε εναν πινακα
                if(tokens.length!=7) {
                    throw new InputMismatchException("Λανθασμένος αριθμος στοιχειων,στη γραμμή "+(patientCounter+1));   //αν εχουν λιγοτερα ή περισσοτερα στοιχεια απο οσα χρειαζομαστε

                }
                    Patient filePatient = new Patient(tokens[6], tokens[0], tokens[3]); //φτιαχνουμε τωρα τον patient με τα δεδομενα που διαβασαμε απο το αρχειο
                    patientList.add(filePatient); //του προσθετουμε χαρακτηριστικα
                    filePatient.setName(tokens[1]);
                    filePatient.setSurname(tokens[2]);
                    filePatient.setAddress(tokens[4]);
                    filePatient.setTelephone(parseLong(tokens[5]));
                    System.out.println("Δημιουργία ασθενή filePatient" + patientCounter);
                    System.out.println("ΑΜΚΑ ασθενή:" + patientList.get(patientCounter).getAmka());
                    System.out.println("Όνομα ασθενή:" + patientList.get(patientCounter).getName());
                    System.out.println("Επώνυμο ασθενή:" + patientList.get(patientCounter).getSurname());
                    System.out.println("Διεύθυνση:" + patientList.get(patientCounter).getAddress());
                    System.out.println("Τηλέφωνο:" + patientList.get(patientCounter).getTelephone());
                    patientCounter++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}