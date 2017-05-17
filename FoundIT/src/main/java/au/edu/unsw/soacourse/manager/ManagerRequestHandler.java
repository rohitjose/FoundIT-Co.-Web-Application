package au.edu.unsw.soacourse.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.edu.unsw.soacourse.dao.EntityMappingDao;
import au.edu.unsw.soacourse.dao.foundITDao;
import au.edu.unsw.soacourse.dto.DTOJobPostings;
import au.edu.unsw.soacourse.dto.Poll;
import au.edu.unsw.soacourse.dto.User;
import au.edu.unsw.soacourse.restResources.JobsServices;
import au.edu.unsw.soacourse.restResources.PollServices;

public class ManagerRequestHandler {

	public void signUp(HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("manager_username");
		String email = request.getParameter("manager_email");
		String password = request.getParameter("manager_password");
		String confirm_password = request.getParameter("manager_confirm_password");
		String first_name = request.getParameter("manager_first_name");
		String last_name = request.getParameter("manager_last_name");
		String phone = request.getParameter("manager_phone");
		String company = request.getParameter("manager_company");
		String street_address = request.getParameter("manager_street_address");
		String city = request.getParameter("manager_city");
		String zip = request.getParameter("manager_zip");
		String state = request.getParameter("manager_state");
		String headline = request.getParameter("manager_headline");
		String business_domain = request.getParameter("manager_business_domain");
		String summary = request.getParameter("manager_summary");

		foundITDao dao = new foundITDao();

		HashMap<String, String> queryParams = new HashMap<String, String>();

		// Login Details
		queryParams.put("username", username);
		queryParams.put("email", email);
		queryParams.put("password", password);
		queryParams.put("role", "app-manager");
		// Profile Details
		queryParams.put("first_name", first_name);
		queryParams.put("last_name", last_name);
		queryParams.put("phone", phone);
		queryParams.put("street_address", street_address);
		queryParams.put("city", city);
		queryParams.put("zip", zip);
		queryParams.put("state", state);
		queryParams.put("headline", headline);
		queryParams.put("business_domain", business_domain);
		queryParams.put("summary", summary);
		queryParams.put("company", company);

		int manager_id = dao.signUpUser(queryParams);

		String teamMemberList = new String();
		for (int i = 1; i <= 5; i++) {
			String team_user = request.getParameter("manager_team_username_" + i);
			String team_password = request.getParameter("manager_team_password_" + i);

			if (team_user != null && !team_user.equals("")) {
				dao.insertUser(team_user, "default_" + team_password, "app-reviewer");
				teamMemberList += team_user + "|";
			}
		}

		if (teamMemberList.length() > 2) {
			teamMemberList = teamMemberList.substring(0, teamMemberList.length() - 1);

			try {
				dao.insertHiringTeam(teamMemberList, manager_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			dao.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createPoll(HttpServletRequest request, HttpServletResponse response) {

		// Extract input
		String poll_jobId = request.getParameter("poll_jobId");

		// Get the date options
		List<String> date_options = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			String option = request.getParameter("poll_date_option_" + i);
			if (option != null && !option.isEmpty()) {
				date_options.add(option);
			}
		}

		// Get the location options
		List<String> location_options = new ArrayList<String>();
		for (int i = 1; i <= 5; i++) {
			String option = request.getParameter("poll_location_option_" + i);
			if (option != null && !option.isEmpty()) {
				location_options.add(option);
			}
		}

		PollServices poller = new PollServices();
		// Create Date poll
		Poll datePoll = new Poll();
		datePoll.setPoll_title("Date Poll for Job - " + poll_jobId);
		datePoll.setDescription("Interview date poll for Job" + poll_jobId);
		datePoll.setPoll_option_type("date");
		datePoll.setOptions(date_options);
		datePoll = poller.createPoll(datePoll, "app-manager");
		System.out.println(datePoll.getPollId());

		// Create Location poll
		Poll locationPoll = new Poll();
		locationPoll.setPoll_title("Location Poll for Job - " + poll_jobId);
		locationPoll.setDescription("Interview Location poll for Job" + poll_jobId);
		locationPoll.setPoll_option_type("text");
		locationPoll.setOptions(location_options);
		locationPoll = poller.createPoll(locationPoll, "app-manager");
		System.out.println(locationPoll.getPollId());

		String polls = datePoll.getPollId() + "|" + locationPoll.getPollId();

		// Update entity mapping
		try {
			EntityMappingDao dao = new EntityMappingDao();
			dao.scheduleInterview(polls, poll_jobId);

			dao.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createJobPost(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String postJob_position = request.getParameter("postJob_position");
		String postJob_description = request.getParameter("postJob_description");
		String postJob_location = request.getParameter("postJob_location");
		String postJob_salary = request.getParameter("postJob_salary");
		String postJob_company = request.getParameter("postJob_company");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int manager_id = user.getUser_id();

		DTOJobPostings job = new DTOJobPostings(null, postJob_company, postJob_salary, postJob_position,
				postJob_location, postJob_description, "open", "found-it", "app-manager");

		// Create posting REST call
		JobsServices jobb = new JobsServices();
		int job_id = jobb.createJob(job);
		
		

		try {
			EntityMappingDao dao = new EntityMappingDao();
			dao.insertMapping(job_id, manager_id, "", "", "");

			dao.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void assignTeam(HttpServletRequest request, HttpServletResponse response) {
		String applicationId = request.getParameter("assignTeam_applicationId");
		String reviewer1 = request.getParameter("assignTeam_reviewer_1");
		String reviewer2 = request.getParameter("assignTeam_reviewer_2");
		String reviewers = reviewer1 + "|" + reviewer2;

		try {
			EntityMappingDao dao = new EntityMappingDao();
			dao.assignTeam(applicationId, reviewers);

			dao.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<DTOJobPostings> getManagerJobs(int manager_id) {
		EntityMappingDao dao = new EntityMappingDao();
		ArrayList<String> jobIds = new ArrayList<String>();
		ArrayList<DTOJobPostings> manager_jobs = new ArrayList<DTOJobPostings>();
		JobsServices jobb = new JobsServices();

		jobIds = dao.getManagerJobIds(manager_id);

		for (String jobId : jobIds) {
			DTOJobPostings job = new DTOJobPostings(jobId, "", "", "", "", "", "open", "found-it", "app-manager");
			job = jobb.searchJobs(job).get(0);

			if (job != null) {
				manager_jobs.add(job);
			}
		}
		
		try {
			dao.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return manager_jobs;

	}

	public void mapManagerJobs(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user.getRole().equals("app-manager")) {
			int manager_id = user.getUser_id();

			session.setAttribute("manager_jobs", getManagerJobs(manager_id));
		}
	}

}
