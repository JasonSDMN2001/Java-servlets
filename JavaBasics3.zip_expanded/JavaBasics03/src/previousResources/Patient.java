package previousResources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient extends User {
    /*final*/protected String x;  //� ��������� ���� ����� ���������� �� ���� ��� �� ��� ���������������� ���� constructor.
    final String amka;


   /* public String getAmka() {
        return amka;
    }

    // public void setAmka(int amka) { ���� ��� ����������� �� ������������,��� ���� ���� �� set?
    //     this.amka = amka;
    // }

    public boolean registration() {        //������� ������
        return true;
    } //������

    public boolean searchappointmentbydoctor() {  //��������� ���������� �������� ��� ������������ �����
        return true;
    } //������ �������� ��� ������������ ������

    public boolean searchappointmentbyspecialty() {  //��������� �������� ��� ������������ ��������� ����� ���� �����������
        return true;
    } //������� ��� ���������� ���

    public boolean scheduledapointments(){ //���������������� ��������
        return true;
    } //���������������� ��������
    public boolean allapointments(){ //�������� ��� ��������
        return true;
    } //������� ��������� ��������*/

    //��������� ��������� ����������� ��� �������� ��� ������� makeappoitment ��� �� ������� ���� class Appointment
    public boolean makeappointment(Doctor doc,String datetime,String datetime2) throws SQLException {     //�������� �������� ������
        Appointment app = new Appointment(this,doc,datetime,datetime2);
        return true;
    }

    //�������� �������� ������ ��� ��� ����
    public boolean cancelappointment(String date, String time, String doctorID) throws SQLException {    //������� �������� ������
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
			//������ �������������� ������ �� ������, ���� ���������� ��� ��� ��� ���������� ��������
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