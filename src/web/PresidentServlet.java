package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		req.getRequestDispatcher("/PresDisplay.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in dopost");
		HttpSession session = req.getSession();
		//TODO add validation on PresLookup
		int termNumber = Integer.parseInt(req.getParameter("termSelect"));
		session.setAttribute("president", presidents.get(termNumber-1));
		getServletContext().getRequestDispatcher("/PresDisplay.jsp").forward(req, resp);
		// Term lookup works, previous fails
		if (session.getAttribute("president") == null) {
			session.setAttribute("president", presidents.get(0));
		}
		
		if (session.getAttribute("previous") != null) {
			session.setAttribute("president" , presidents.get(termNumber-1));
		}
		
		if (termNumber > 44) {
			termNumber -= 44;
		} // TODO add error notification for incorrect usage
		else if (termNumber < 1) {
			termNumber = 1;
		}
		
	}
	
	
}
