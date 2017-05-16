package au.edu.unsw.soacourse.reviewer;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.edu.unsw.soacourse.dao.foundITDao;

public class ReviewerRequestHandler {

	public void signUp(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("recruiter_username");
		String default_password = request.getParameter("recruiter_default_password");
		String password = request.getParameter("recruiter_password");
		String confirm_password = request.getParameter("recruiter_confirm_password");
		String first_name = request.getParameter("recruiter_first_name");
		String last_name = request.getParameter("recruiter_last_name");
		String phone = request.getParameter("recruiter_phone");
		String company = request.getParameter("recruiter_company");
		String position = request.getParameter("recruiter_position");
		String summary = request.getParameter("recruiter_summary");

		foundITDao dao = new foundITDao();
		HashMap<String, String> queryParams = new HashMap<String, String>();

		try {
			dao.updateDefaultPassword(username, default_password, password);
			// Profile Details
			queryParams.put("first_name", first_name);
			queryParams.put("last_name", last_name);
			queryParams.put("phone", phone);
			queryParams.put("position", position);
			queryParams.put("summary", summary);
			queryParams.put("company", company);
			
			dao.insertProfile(queryParams, dao.getUserId(username));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
