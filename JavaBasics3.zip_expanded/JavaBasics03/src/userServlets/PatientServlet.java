package userServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class PatientServlet
 */
@WebServlet({ "/PatientServlet", "/patient" })
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String user, requestType;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DataSource datasource = null;
	
	public void init() throws ServletException{
		try {
	
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} 
		catch(Exception e) {
			throw new ServletException(e.toString());}
		}
	
    public PatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		requestType = request.getParameter("requestType");
		//επιστροφή στοιχείων ασθενή
		if (requestType.equalsIgnoreCase("Στοιχεία Χρήστη")) {
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
				Statement stmt = con.createStatement();
				//δημιουργία πίνακα html
				out.println("<table style=\"background-color:#4acf6e\" border=\"1\">");
				out.println("<tr>");
				out.println("<th>Username:</th>");
				out.println("<th>First name:</th>");
				out.println("<th>Surname:</th>");
				out.println("<th>Phone Number:</th>");
				out.println("<th>Address:</th>");
				out.println("</tr>");
				//προετοιμασία στοιχείων ασθενή
				ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE username='"+user+"'");
				while(rs.next()) {
					String name = rs.getString("firstName");
					String surName = rs.getString("surname");
					String phone = rs.getString("phoneNumber");
					String address = rs.getString("address");
					String htmlRow = createHTMLRow(user, name, surName, phone, address);
					//εκτύπωση στοιχείων στον πίνακα με μορφή στήλης
					out.println(htmlRow);
					out.println("<form action=\"epilogi.jsp\"><input style=\"padding-right:25px;\" type=\"Submit\" value=\"Έπιστροφή\" /></form>");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch(Exception e) {
				out.println("Database connection problem");
			}
		}
		else if (requestType.equalsIgnoreCase("Προβολή Ιστορικών Ασθενή")) {
			try {
				//ανακατεύθυνση στοιχείων ασθενή (username) στο /istoriko.jsp
				request.setAttribute("user",user);
				request.getRequestDispatcher("/istoriko.jsp").forward(request, response);
			}
			catch(Exception e) {
				out.println("Database connection problem");
			}
		}
			}
	
	//δημιουργία στήλης html
	private String createHTMLRow(String user, String name, String surName, String phone, String address) {
		String row = "<tr>";
		row  += "<td>" + user + "</td>";
		row  += "<td>" + name + "</td>";
		row  += "<td>" + surName + "</td>";
		row  += "<td>" + phone + "</td>";
		row  += "<td>" + address + "</td>";
		row +="</tr>";
		return row;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SecureRandom random = new SecureRandom();
		byte bytes[]= new byte[20];
		 random.nextBytes(bytes);
		 
		String username = request.getParameter("name"); 
		user = username;
		String password = getHashMD5(request.getParameter("pwd"),random.toString());
		String address = "";
		//δημιουργία session για την εισαγωγή του username ως μεταβλητή για την διευκόλυνση σε επόμενες λειτουργίες
		HttpSession session = request.getSession();
	
		requestType= request.getParameter("requestType");
		
		if (requestType == null) {
			//response.sendRedirect("/JavaBasics03/patientlogin.jsp");
			address = "/error.jsp";	
		}
		
		if (requestType.equalsIgnoreCase("login")) {
		try {
			//σύνδεση στην βάση
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false","root","test123");
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();
			Statement stmt3 = con.createStatement();
			//3 Query για την εύρεση ποιάς κατηγορίας χρήστη είναι το άτομο που κάνει login 
			ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE username='"+username+"'AND password='"+password+"'");
			ResultSet rsd = stmt2.executeQuery("SELECT * FROM doctor WHERE username='"+username+"'AND password='"+password+"'");
			ResultSet rsa = stmt3.executeQuery("SELECT * FROM admin WHERE username='"+username+"'AND password='"+password+"'");
			//εάν είναι ασθενής
			if(rs.next()) {
				session.setAttribute("username",username); 			
				address = "/epilogi.jsp";
			}
			//εάν είναι ιατρός
			else if(rsd.next()) {
				session.setAttribute("username",username); 			
				address = "/doctorEpilogi.jsp";
			}
			//εάν είναι διαχειριστής
			else if(rsa.next()) {
					session.setAttribute("username",username); 			
					address = "/adminEpilogi.jsp";
			}
			//λανθασμένα στοιχεία στο login
			else {
				//response.sendRedirect("/JavaBasics03/patientlogin.jsp");				
				address = "/error.jsp";
			}
			rs.close();
			rsd.close();
			rsa.close();
			stmt.close();
			stmt2.close();
			stmt3.close();
			con.close();
			}
		catch(Exception e) {
			//response.sendRedirect("/JavaBasics03/patientlogin.jsp");			
			address = "/patientlogin.jsp";
			}
		}
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	//διαδικασία hashing + salting 
	public static String getHashMD5(String unhashed, String salt) {
        // Hash the password.
        final String toHash = salt + unhashed + salt;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            return "00000000000000000000000000000000";
        }
        messageDigest.update(toHash.getBytes(), 0, toHash.length());
        String hashed = new BigInteger(1, messageDigest.digest()).toString(16);
        if (hashed.length() < 32) {
            hashed = "0" + hashed;
        }
        return hashed.toUpperCase();
    }

}
