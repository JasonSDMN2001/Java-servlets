package previousResources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//import static previousResources.User.usersCounter;

//import java.util.Date;
public class Appointment {

    protected String appoint,time;
    Patient mypatient = new Patient("00022424242","gdgd","fgdgd");
    Doctor mydoctor = new Doctor("34535235","sege");

    //��������� �� ������ �� ����� �� ��������
    public Appointment(Patient patient, Doctor doctor,String date,String time1) throws SQLException {
        mypatient = patient;
        mydoctor = doctor;
        appoint = date;
        time=time1;
        User.usersCounter=User.usersCounter-2;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		int rscreate = stmt.executeUpdate("INSERT INTO mydb.appointment(patientID,Availability_date,Availability_time,Availability_doctorID) VALUES(\""+mypatient.username+"\",\""+appoint+"\",\""+time+"\",\""+mydoctor.username+"\")");
		int rsbook = stmt2.executeUpdate("UPDATE mydb.availability SET available = false WHERE available = true AND availability.date = \""+appoint+"\"  AND availability.time =\""+time+"\"  AND availability.doctorID=\""+mydoctor.username+"\"");
		stmt.close();
		stmt2.close();
		con.close();
        //�������� ��� ������� ��� ��� ������� usersCounter ����� ������������ ��� �����������
        //����� Doctor ��� ��� ����������� ����� Patient ��� �������������� �� ����� (��� ����� �������� �������)
    }
}