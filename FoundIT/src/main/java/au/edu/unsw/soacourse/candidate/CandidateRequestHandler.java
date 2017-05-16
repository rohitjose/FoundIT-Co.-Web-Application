package au.edu.unsw.soacourse.candidate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.edu.unsw.soacourse.dao.foundITDao;
import au.edu.unsw.soacourse.dto.DTOJobPostings;
import au.edu.unsw.soacourse.restResources.JobsServices;

public class CandidateRequestHandler {

	public void signUp(HttpServletRequest request, HttpServletResponse response) {

		String skills = request.getParameter("seeker_skills");
		String education = request.getParameter("seeker_education");
		String experience = request.getParameter("seeker_experience");
		String summary = request.getParameter("seeker_summary");
		String position = request.getParameter("seeker_position");
		String headline = request.getParameter("seeker_headline");
		String state = request.getParameter("seeker_state");
		String zip = request.getParameter("seeker_zip");
		String city = request.getParameter("seeker_city");
		String streetAddress = request.getParameter("seeker_streetAddress");
		String site = request.getParameter("seeker_site");
		String phone = request.getParameter("seeker_phone");
		String last_name = request.getParameter("seeker_last_name");
		String first_name = request.getParameter("seeker_first_name");
		String confirm_password = request.getParameter("seeker_confirm_password");
		String password = request.getParameter("seeker_password");
		String email = request.getParameter("seeker_email");
		String username = request.getParameter("seeker_username");

		foundITDao dao = new foundITDao();

		HashMap<String, String> queryParams = new HashMap<String, String>();
		// Login Details
		queryParams.put("username", username);
		queryParams.put("email", email);
		queryParams.put("password", password);
		queryParams.put("role", "app-candidate");
		// Profile Details
		queryParams.put("first_name", first_name);
		queryParams.put("last_name", last_name);
		queryParams.put("phone", phone);
		queryParams.put("site", site);
		queryParams.put("street_address", streetAddress);
		queryParams.put("city", city);
		queryParams.put("zip", zip);
		queryParams.put("state", state);
		queryParams.put("headline", headline);
		queryParams.put("position", position);
		queryParams.put("summary", summary);
		queryParams.put("experience", experience);
		queryParams.put("education", education);
		queryParams.put("skills", skills);

		int user_id = dao.signUpUser(queryParams);

		try {
			dao.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchJobs(HttpServletRequest request, HttpServletResponse response) {
		String search_position = request.getParameter("search_position");
		String search_description = request.getParameter("search_description");
		String search_location = request.getParameter("search_location");
		String search_salary = request.getParameter("search_salary");
		String search_company = request.getParameter("search_company");

		DTOJobPostings job = new DTOJobPostings("0", search_company, search_salary, search_position, search_location,
				search_description, "open", "found-it", "app-candidate");
		JobsServices jobber = new JobsServices();
		ArrayList<DTOJobPostings> jobs = jobber.searchJobs(job);

		HttpSession session = request.getSession();
		session.setAttribute("job_results", jobs);
	}
}
