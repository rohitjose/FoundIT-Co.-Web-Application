package au.edu.unsw.soacourse.jobseeker;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.edu.unsw.soacourse.dao.foundITDao;

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
			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
