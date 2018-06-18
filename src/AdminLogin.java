

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	String driver="com.mysql.jdbc.Driver", url="jdbc:mysql://localhost/ehospital", user="root", password="", query;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("adminID");
		String pswd = request.getParameter("adminPswd");

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
				
				PreparedStatement prepstmt=conn.prepareStatement("SELECT admin_pswd FROM admin WHERE admin_id = (?)");
				
				prepstmt.setString(1,username);
				
				PrintWriter out = response.getWriter();
				
				 rs=prepstmt.executeQuery();
				 if(rs.next()!= false)
				 {
					 /*String pswdDB=rs.getString(1);
					 RequestDispatcher rd = request.getRequestDispatcher("/admin-home.html");
						rd.forward(request, response);*/
					 String pswdDB=rs.getString(1);
					   if(pswd.equals(pswdDB)) //Successful login
					   {
						   RequestDispatcher rd = request.getRequestDispatcher("/admin-home.html");
						   rd.forward(request, response);
					   }
					   else
					   { 
							out.println("<script type=\"text/javascript\">");
							out.println("alert(' Incorrect username or password! ');");
							out.println("location='admin-login.html';");
						    out.println("</script>");
					   }		   
				 }		   
				 else
				 {
					 	out.println("<script type=\"text/javascript\">");
						out.println("alert(' Incorrect username or password! ');");
						out.println("location='admin-login.html';");
					    out.println("</script>");
				 }
			}
		}
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
	}

}
