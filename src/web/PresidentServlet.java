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
	public static final int LAST_PRESIDENT = 44;
	public static final int FIRST_PRESIDENT = 1;

	public void init() throws ServletException {
		ServletContext context = getServletContext();
		ParsePresidents parse = new ParsePresidents(context);
		presidents = parse.getPresidents();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int term = 0;
		HttpSession session = req.getSession();
		
		String dest = "/PresDisplay.jsp";
		try { 
			term = Integer.parseInt(req.getParameter("termSelect"));
		} catch(NumberFormatException nfe) {
			term = 0;
		}
		if (term > 44 || term < 1) {
			term = 0;
		}
		session.setAttribute("president", presidents.get(term));
		getServletContext().getRequestDispatcher(dest).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String choice = req.getParameter("search");
		String destination = "/PresDisplay.jsp";

		switch (choice) {
		case "Term Lookup":
			termLookup(session, req);
			break;
		case "<":
			prevLookup(session);
			break;
		case ">":
			nextLookup(session);
			break;
		default:
			destination = "/error.html";
			break;
		}
		getServletContext().getRequestDispatcher(destination).forward(req, resp);
	}

	public void termLookup(HttpSession session, HttpServletRequest req)
			throws IOException, ServletException {
		int termNumber = 0;
		try {
			termNumber = Integer.parseInt(req.getParameter("termSelect"));
		} catch (NumberFormatException nfe) {
			termNumber = 0;
		}
		if (termNumber > 44 || termNumber < 1) {
			termNumber = 0;
		}
		session.setAttribute("president", presidents.get(termNumber));
	}

	public void nextLookup(HttpSession session) {
		int termNumber = 0;
		President currentPres = (President) session.getAttribute("president");
		if (currentPres == null) {
			session.setAttribute("president", presidents.get(1));
		}
		termNumber = currentPres.getTermNumber() + 1;
		if (termNumber > LAST_PRESIDENT) {
			termNumber = FIRST_PRESIDENT;
		}
		session.setAttribute("president", presidents.get(termNumber));
	}

	public void prevLookup(HttpSession session) {
		int termNumber = 0;
		President currentPres = (President) session.getAttribute("president");
		if (currentPres == null) {
			session.setAttribute("president", presidents.get(1));
		}
		termNumber = currentPres.getTermNumber() - 1;
		if (termNumber < FIRST_PRESIDENT) {
			termNumber = LAST_PRESIDENT;
		}
		session.setAttribute("president", presidents.get(termNumber));
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
