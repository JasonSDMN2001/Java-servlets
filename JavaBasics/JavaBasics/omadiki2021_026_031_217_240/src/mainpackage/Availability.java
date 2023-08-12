package mainpackage;

import java.util.List;

//με βαση το αντικειμενο γιατρου ελεγχουμε αν υπαρχει κενη ωρα την οποια θα διαβαζουμε απο καποιο αρχειο
//ή θα ειναι hardcoded ή θα το βρισκουμε δυναμικα
public class Availability {
    Doctor doc = new Doctor("sfdsfs","dsfsfs");
    List<String> Datetime ;
    public Availability(Doctor doc1, List<String> datetime){
        doc = doc1;
        Datetime = datetime;
        User.usersCounter=User.usersCounter-1;
    }
}
