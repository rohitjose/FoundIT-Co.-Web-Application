package au.edu.unsw.soacourse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.edu.unsw.soacourse.candidate.CandidateRequestHandler;
import au.edu.unsw.soacourse.dao.foundITDao;
import au.edu.unsw.soacourse.dto.User;
import au.edu.unsw.soacourse.manager.ManagerRequestHandler;
import au.edu.unsw.soacourse.reviewer.ReviewerRequestHandler;

public class RouterServlet extends HttpServlet {

	protected String getRelativePath() {
		return getServletContext().getRealPath("/WEB-INF/classes/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("POST method");
		String action = request.getParameter("action");

		if (action.equals("manager_signup")) {
			ManagerRequestHandler manager = new ManagerRequestHandler();
			manager.signUp(request, response);
		} else if (action.equals("seeker_signup")) {
			CandidateRequestHandler candidate = new CandidateRequestHandler();
			candidate.signUp(request, response);
		} else if (action.equals("recruiter_signup")) {
			ReviewerRequestHandler reviewer = new ReviewerRequestHandler();
			reviewer.signUp(request, response);
		} else if (action.equals("user_login")) {
			try {
				loginUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("post_job")) {
			// Post Job
			ManagerRequestHandler manager = new ManagerRequestHandler();
			try {
				manager.createJobPost(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/userHome.jsp");
			rd.forward(request, response);

			System.out.println(getServletContext());

		} else if (action.equals("post_poll")) {
			// Post Poll
			ManagerRequestHandler manager = new ManagerRequestHandler();
			manager.createPoll(request, response);

			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/userHome.jsp");
			rd.forward(request, response);

			System.out.println(getServletContext());
		} else if (action.equals("user_logout")) {
			HttpSession session = request.getSession();
			if (session.getAttribute("user") != null) {
				session.setAttribute("user", null);
			}

			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else if (action.equals("assign_team")) {
			// Assign a team
			ManagerRequestHandler manager = new ManagerRequestHandler();
			manager.assignTeam(request, response);

			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/userHome.jsp");
			rd.forward(request, response);
		} else if (action.equals("search_job")) {
			// Search for jobs
			CandidateRequestHandler candidate = new CandidateRequestHandler();
			candidate.searchJobs(request, response);
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/userHome.jsp");
			rd.forward(request, response);
		} else if (action.equals("apply_job")) {
			String jobId = request.getParameter("jobId");
			HttpSession session = request.getSession();
			session.setAttribute("apply-job-id", jobId);
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/applyJob.jsp");
			rd.forward(request, response);

			session.setAttribute("job_results", null);
		} else if (action.equals("apply_job_form")) {
			
			
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/userHome.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello from GET method");
		PrintWriter printer = response.getWriter();
		printer.println("<h1>hello</h1>");
	}

	protected void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// String landing_page = new String();
		User user = new User();
		HttpSession session = request.getSession();

		foundITDao dao = new foundITDao();

		try {
			user = dao.login(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				user = dao.login(username, "default_" + password);
			} catch (SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		dao.closeConnection();

		if (user.getUsername() != null) {
			session.setAttribute("user", user);
			if(user.getRole().equals("app-manager")){
				ManagerRequestHandler manager = new ManagerRequestHandler();
				manager.mapManagerJobs(request, response);
			}
			if(user.getRole().equals("app-candidate")){
				CandidateRequestHandler candidate = new CandidateRequestHandler();
				candidate.mapApplications(request, response);
			}

			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/userHome.jsp");
			rd.forward(request, response);
		}

	}

}
