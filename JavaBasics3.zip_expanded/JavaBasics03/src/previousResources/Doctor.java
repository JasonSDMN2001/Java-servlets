package previousResources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;*/

public class Doctor extends User{
    protected String specialty; //���������� ��� �������

    /*public String getSpecialty() { //�� ���������� ����� � �������
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty=specialty;
    }*/

    public boolean availability(String date,String time,String doctorID) throws SQLException {  //���������� ���������� ����������� ��� �� ����� ��� ������.
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
		Statement stmt = con.createStatement();
		try {
			int availabilityUpdate = stmt.executeUpdate("INSERT INTO mydb.availability VALUES(\""+date+"\",\""+time+"\",\""+doctorID+"\",TRUE);");
			stmt.close();
			con.close();
	        return true;
		}catch(SQLException e) {
			stmt.close();
			con.close();
			//e.printStackTrace();
	        return false;
		}
		
    }

    public boolean programview() {   //������� ������������ �������� ��� �������
        return true;
    } //�������� ������� ��� �� �������� �� ��������� ��� �������

    public boolean cancelappointment(String date,String time,String doctorID) throws SQLException{  //������� �������������� ��� ������ �������.
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
		Statement stmt = con.createStatement();
		int cancel = stmt.executeUpdate("DELETE FROM mydb.appointment WHERE \""+date+"\">current_date() + INTERVAL 3 DAY AND Availability_date = \""+date+"\"  AND Availability_time =\""+time+"\"  AND Availability_doctorID=\""+doctorID+"\"");
		stmt.close();
		con.close();
		if (cancel==0) {
			return false;
		}
		else {
			return true;
		}
    } //������� ��������

    public Doctor(String name,String pass) {

        super(name,pass);

    }
}