package userServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import previousResources.Doctor;

/**
 * Servlet implementation class DoctorServlet
 */
@WebServlet({ "/DoctorServlet", "/doctor" })
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //��� ��� �������� ��� �������� ���� ������
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestParam = request.getParameter("doctorcancel");
		String split[] = requestParam.split(",",0);//δεν εχει σημασια το 0 ειναι οτι να ναι νουμερο εκει
		Doctor canceling = new Doctor("canceling","appointment"); 
		try {
			boolean canceled = canceling.cancelappointment(split[0],split[1],split[2]);
			if (canceled) {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<h3>�������� �������� �������� ��� �������������� </h3>");
				out.println("<form action=\"doctorEpilogi.jsp\"><input style=\"padding-right:25px;\" type=\"Submit\" value=\"���������\" /></form>");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<h3>����������, ���� ��� �������� �� ����������� �������� ��� ����� �� ����������� ���� ����� �������, ");
				out.println("� ��������� �� ���������� ��� �������� ��� �������� 3 ������ </h3>");
				out.println("<form action=\"doctorEpilogi.jsp\"><input style=\"padding-right:25px;\" type=\"Submit\" value=\"���������\" /></form>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String doctorUsername = request.getParameter("doctorusername");
		String docIDQuery = "SELECT doctorID from mydb.doctor WHERE username = '"+doctorUsername+"'";
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(docIDQuery);
			Doctor docavailable = new Doctor(doctorUsername,"temp");
			boolean updated = false;
			if(rs.next()) {
				updated = docavailable.availability(date,time,rs.getString("doctorID"));
			}
			rs.close();
			stmt.close();
			con.close();
			if (updated) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/doctorsavailability.jsp");
				dispatcher.forward(request, response);
			}
			else{
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<h3>����� ��� ����� ��� ������������� ��� ������ ��� ����� ��� ��� � ������ �� ������� ����� ��� �����</h3>");
				out.println("<form action=\"doctorEpilogi.jsp\"><input style=\"padding-right:25px;\" type=\"Submit\" value=\"���������\" /></form>");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
