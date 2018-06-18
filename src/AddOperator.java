

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddOperator
 */
@WebServlet("/AddOperator")
public class AddOperator extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	String driver="com.mysql.jdbc.Driver", url="jdbc:mysql://localhost/ehospital", user="root", password="", query;
	    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOperator() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("Op_username");
		String pswd = request.getParameter("Op_password");
		String email = request.getParameter("Op_email");
		String phone = request.getParameter("Op_phone_number");

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
				PreparedStatement prepstmt=conn.prepareStatement("INSERT into Operator values(?,?,?,?)");
				 
				prepstmt.setString(1,username);  
				prepstmt.setString(2,pswd);
				prepstmt.setString(3,email);
				prepstmt.setString(4,phone);
				  
				PrintWriter out = response.getWriter();
				int status = prepstmt.executeUpdate();
				if(status > 0)
				{
					out.println("<script type=\"text/javascript\">");
					out.println("alert(' Operator details added successfully. ');");
					out.println("location='add-operator.html';");
				    out.println("</script>");
				}
				else
				{
					out.println("Sorry! Unable to add operator details.");
				}  
			}
		}
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
	}

}
