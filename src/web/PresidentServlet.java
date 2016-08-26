package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PresDisplay")
@SuppressWarnings("serial")
public class PresidentServlet extends HttpServlet {
	List<President> presidents;
	
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		ParsePresidents parse = new ParsePresidents(context);
		presidents = parse.getPresidents();
		System.out.println("in init");
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doget");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in dopost");
		req.setAttribute("president", presidents.get(0));
		getServletContext().getRequestDispatcher("/PresDisplay.jsp").forward(req, resp);
	
	}
	
	
}
