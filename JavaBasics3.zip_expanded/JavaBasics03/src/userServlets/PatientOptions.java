package userServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import previousResources.Doctor;
import previousResources.Patient;
import previousResources.User;

/**
 * Servlet implementation class PatientOptions
 */
@WebServlet("/PatientOptions")
public class PatientOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String requestParam;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientOptions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestParam = request.getParameter("patientcancel");
		String split[] = requestParam.split(",",0);
		Patient canceling = new Patient("0000","canceling","appointment"); 
		try {
			boolean canceled = canceling.cancelappointment(split[0],split[1],split[2]);
			if (canceled) {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<h3>Επιτυχής διαγραφή ραντεβού </h3>");
				out.println("<form action=\"epilogi.jsp\"><input style=\"padding-right:25px;\" type=\"Submit\" value=\"Έπιστροφή\" /></form>");
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<h3>Λυπούμαστε, αλλά δεν μπορούμε να διαγράψουμε ραντεβού στα οποία οι ημερομηνίες τους έχουν περάσει, ");
				out.println("ή πρόκειται να διεξαχθούν στο διάστημα των επόμενων 3 ημερών </h3>");
				out.println("<form action=\"epilogi.jsp\"><input style=\"padding-right:25px;\" type=\"Submit\" value=\"Έπιστροφή\" /></form>");
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
		requestParam = request.getParameter("patientusername");
		String split[] = requestParam.split(",",0);	
		Patient appointmentMaker = new Patient("0",split[0],"temp");
		Doctor appointmentReciever = new Doctor(split[3],"temp");
		User.usersCounter=User.usersCounter-2;
		try {
			appointmentMaker.makeappointment(appointmentReciever, split[1], split[2]);
			request.getRequestDispatcher("searchbydoctor.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
