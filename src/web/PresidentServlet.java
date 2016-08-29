package web;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

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
	List<President> filteredPresidents;

	public void init() throws ServletException {
		ServletContext context = getServletContext();
		ParsePresidents parse = new ParsePresidents(context);
		presidents = parse.getPresidents();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int term = 0;
		String dest = "/PresDisplay.jsp";
		try { 
			term = Integer.parseInt(req.getParameter("termSelect"));
		} catch(NumberFormatException nfe) {
			dest = "/error.html";
		}
		if (term > 44 || term < 1) {
			term = 0;
		}
		
		req.setAttribute("president", presidents.get(term));
		req.getRequestDispatcher(dest).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String choice = req.getParameter("search");
		String destination = "";

		switch (choice) {
		case "Term Lookup":
			destination = termLookup(session, req, resp);
			break;
		case "<":
			destination = prevLookup(session, req);
			break;
		case ">":
			destination = nextLookup(session, req);
			break;
		default:
			destination = "/error.html";
			break;
		}
		getServletContext().getRequestDispatcher(destination).forward(req, resp);
	}

	public String termLookup(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int termNumber = 0;
		try {
			termNumber = Integer.parseInt(req.getParameter("termSelect"));
		} catch (NumberFormatException nfe) {
			return "/error.html";
		}
		if (termNumber > 44 || termNumber < 1) {
			session.setAttribute("president", presidents.get(0));
			return "/PresDisplay.jsp";
		} else {
			session.setAttribute("president", presidents.get(termNumber));
			return "/PresDisplay.jsp";
		}

	}

	public String nextLookup(HttpSession session, HttpServletRequest req) {
		int termNumber = 0;
		if (session.getAttribute("president") == null) {
			session.setAttribute("president", presidents.get(1));
		}
		President currentPres = (President) session.getAttribute("president");
		termNumber = currentPres.getTermNumber() + 1;
		if (termNumber > 44) {
			termNumber = termNumber - 44;
		}
		session.setAttribute("president", presidents.get(termNumber));
		return "/PresDisplay.jsp";
	}

	public String prevLookup(HttpSession session, HttpServletRequest req) {
		int termNumber = 0;
		if (session.getAttribute("president") == null) {
			session.setAttribute("president", presidents.get(1));
		}
		President currentPres = (President) session.getAttribute("president");
		termNumber = currentPres.getTermNumber() - 1;
		if (termNumber < 1) {
			termNumber = termNumber + 44;
		}
		session.setAttribute("president", presidents.get(termNumber));
		return "/PresDisplay.jsp";
	}
	
	// Method to allow lambda filtering.
	// example usage for later implementation (names starting with R):
	// filteredPresidents = filter(presidents, (p) -> p.getLastName().charAt(0) == 'R');
	public void filter(Predicate<President> predicate) {
		for (President president : presidents) {
			if (predicate.test(president)) {
				filteredPresidents.add(president);
			}
		}
	}
}
