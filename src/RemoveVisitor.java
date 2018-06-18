

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveVisitor
 */
@WebServlet("/RemoveVisitor")
public class RemoveVisitor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveVisitor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		int status = 1;
		if(status > 0)
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert(' Visitor Doctor details removed successfully. ');");
		    out.println("</script>");
		}
		else
		{
			out.println("Sorry! Unable to remove Visitor Doctor details.");
		}
	}

}
