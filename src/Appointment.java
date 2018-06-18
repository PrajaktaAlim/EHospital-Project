

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Appointment
 */
@WebServlet("/Appointment")
public class Appointment extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	String driver="com.mysql.jdbc.Driver", url="jdbc:mysql://localhost/ehospital", user="root", password="", query;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Appointment() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name = request.getParameter("txt_a_name");
		String a_date = request.getParameter("Adate");
		String email = request.getParameter("txt_a_email");
		String phone = request.getParameter("txt_a_phone");
		String doctor_name = request.getParameter("txt_doctor_name");
		
		try
		{
		Class.forName(driver);
		conn=DriverManager.getConnection(url,user,password);
		stmt=conn.createStatement();
		
		if(conn==null)
			System.out.print("Connection failed!");
		else
			{
				System.out.print("Connection successful!");
				/*response.sendRedirect("admin-home.html");
				RequestDispatcher rd = request.getRequestDispatcher("/admin-home.html");
				rd.forward(request, response);*/
				PreparedStatement prepstmt=conn.prepareStatement("INSERT into Appointment values(?,?,?,?,?)");
				
				prepstmt.setString(1,name);
				prepstmt.setString(2,email);
				prepstmt.setString(3,phone);
				prepstmt.setString(4,doctor_name);
				prepstmt.setString(5,a_date);
				
				PrintWriter out = response.getWriter();
				int status = prepstmt.executeUpdate();
				if(status > 0)
				{
					out.println("<script type=\"text/javascript\">");
					out.println("alert(' Appointment details submitted successfully. ');");
					out.println("location='appointment.html';");
				    out.println("</script>");
				}
				else
				{
					out.println("Sorry! Unable to submit appointment details.");
				}  
			}
		}
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
	}

}
