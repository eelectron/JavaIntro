package simple;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServletA
 */
@WebServlet(description = "a simple website", urlPatterns = { "/MyServletA" })

public class MyServletA extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest rq,HttpServletResponse rs)throws ServletException,IOException{
		rs.setContentType("text/html");
		PrintWriter out=rs.getWriter();
		String msg="If you see this,its working";
		out.println("<html>" +
						"<body>"+
							"<h1>"+msg+"</h1>"+
						"</body>" +
					"</html>");
		out.close();
	}
}