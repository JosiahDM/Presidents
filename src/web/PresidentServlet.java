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

		String choice = req.getParameter("search");
		System.out.println("Choice: " + choice);

		// Term lookup works, previous fails
		String destination = "";
		switch (choice) {
		case "Term Lookup":
			destination = termLookup(session, req, resp);
			break;
		case "previous":
			destination = prevLookup(session, req);
			break;
		case "next":
			destination = nextLookup(session, req);
			break;
		default:
			destination = "/error.html";
			break;
		}
		

		getServletContext().getRequestDispatcher(destination).forward(req, resp);
		
		
	}
	
	public String termLookup (HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int termNumber = 0;
		try {
			termNumber = Integer.parseInt(req.getParameter("termSelect"));
		} catch (NumberFormatException nfe) {
			return "/error.html";
		}
		if (termNumber > 44 || termNumber < 1) {
			return "/error.html";
		}
		else {
		session.setAttribute("president", presidents.get(termNumber - 1));
		return "/PresDisplay.jsp";
		}
		
	}
	
	public String nextLookup (HttpSession session, HttpServletRequest req) {
		int termNumber = 0;
		if (session.getAttribute("president") == null) {
			session.setAttribute("president", presidents.get(1));
		}
		President currentPres = (President) session.getAttribute("president");
		termNumber = currentPres.getTermNumber() + 1;
		if (termNumber > 44) {
			termNumber = termNumber - 44;
		}
		session.setAttribute("president", presidents.get(termNumber - 1));
		return "/PresDisplay.jsp";
	}
	
	public String prevLookup (HttpSession session, HttpServletRequest req) {
		int termNumber = 0;
		if(session.getAttribute("president") == null) {
			session.setAttribute("president",  presidents.get(1));
		}
		President currentPres = (President) session.getAttribute("president");
		termNumber = currentPres.getTermNumber() - 1;
		if (termNumber < 1) {
			termNumber = termNumber + 44;
		}
		session.setAttribute("president", presidents.get(termNumber - 1));
		return "/PresDisplay.jsp";

	}

}
