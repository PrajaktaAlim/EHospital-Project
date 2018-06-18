

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
 * Servlet implementation class AddPatient
 */
@WebServlet("/AddPatient")
public class AddPatient extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	String driver="com.mysql.jdbc.Driver", url="jdbc:mysql://localhost/ehospital", user="root", password="", query;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPatient() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int pID = Integer.parseInt(request.getParameter("txt_Pid"));
		String name = request.getParameter("txt_Pname");
		String address = request.getParameter("txt_Paddress");
		String phone = request.getParameter("txt_Pphone");
		String type = request.getParameter("Ptype");
		String admit = request.getParameter("Padmit");
		String disease = request.getParameter("txt_Pdisease");
		int age = Integer.parseInt(request.getParameter("txt_P_age"));
		String gender = request.getParameter("Pgender");
		String bloodgroup = request.getParameter("txtPbloodgroup");
		float weight = Float.parseFloat(request.getParameter("txtPweight"));
		String pdate = request.getParameter("Pdate");
		int roomno = Integer.parseInt(request.getParameter("ProomNo"));
		String dietadvice = request.getParameter("PdietAdvice");
		
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
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				PreparedStatement prepstmt=conn.prepareStatement("INSERT into Patient values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				prepstmt.setInt(1,pID);
				prepstmt.setString(2,name);
				prepstmt.setString(3,address);
				prepstmt.setString(4,phone);
				prepstmt.setString(5,type);
				prepstmt.setString(6,admit);
				prepstmt.setString(7,disease);
				prepstmt.setInt(8,age);
				prepstmt.setString(9,gender);
				prepstmt.setString(10,bloodgroup);
				prepstmt.setFloat(11,weight);
				prepstmt.setString(12,pdate);
				prepstmt.setInt(13,roomno);
				prepstmt.setString(14,dietadvice);
				
				int status = prepstmt.executeUpdate();
				if(status > 0)
				{
					out.println("<script type=\"text/javascript\">");
					out.println("alert(' Patient record added successfully. ');");
					out.println("location='add-patient.html';");
				    out.println("</script>");
				}
				else
				{
					out.println("Sorry! Unable to save patient record.");
				}
			}
		}
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
	}

}
