

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
 * Servlet implementation class AddVisitor
 */
@WebServlet("/AddVisitor")
public class AddVisitor extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	String driver="com.mysql.jdbc.Driver", url="jdbc:mysql://localhost/ehospital", user="root", password="", query;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVisitor() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int vID = Integer.parseInt(request.getParameter("Vid"));
		String name = request.getParameter("Vname");
		String specialization = request.getParameter("Vspec");
		String email = request.getParameter("Vemail");
		String phone = request.getParameter("Vphone");
		String address = request.getParameter("Vaddress");
		String vdatetime = request.getParameter("Vdate");
		
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
				PreparedStatement prepstmt=conn.prepareStatement("INSERT into Visitor values(?,?,?,?,?,?,?)");
				
				prepstmt.setInt(1,vID);
				prepstmt.setString(2,name);
				prepstmt.setString(3,address);
				prepstmt.setString(4,email);
				prepstmt.setString(5,phone);
				prepstmt.setString(6,specialization);
				prepstmt.setString(7,vdatetime); //Need to insert as datetime
				
				PrintWriter out = response.getWriter();
				int status = prepstmt.executeUpdate();
				if(status > 0)
				{
					out.println("<script type=\"text/javascript\">");
					out.println("alert(' Visitor details added successfully. ');");
					out.println("location='add-visitor.html';");
				    out.println("</script>");
				}
				else
				{
					out.println("Sorry! Unable to add visitor details.");
				}  
			}
		}
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
	}

}
