package userServlets;

import java.io.IOException;
import java.sql.SQLException;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import previousResources.Admin;
import previousResources.User;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/AdminServlet", "/admin" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String requestType;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String doctorID = request.getParameter("doctorID");
		Admin admin2 = new Admin("adminDEL","TEMP");
		User.usersCounter--;
		//PrintWriter out = response.getWriter();
		try {
			boolean alert = admin2.deletedoctor(doctorID);
			request.getRequestDispatcher("diagrafigiatrou.jsp").forward(request, response);
		}
		catch(Exception e) {
			request.getRequestDispatcher("diagrafigiatrou.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String adminName = request.getParameter("adminusername");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String surname = request.getParameter("surname");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			String specialty = request.getParameter("specialty");
			Admin admin1 = new Admin(adminName,"");
			User.usersCounter--;
				try {
					admin1.doctorregistration(username,password,firstName,surname,address,phoneNumber,specialty);
					request.getRequestDispatcher("registerdoc.jsp").forward(request, response);
				} 
				catch (SQLException esql) {
					esql.printStackTrace();
				}
			}
		
}
