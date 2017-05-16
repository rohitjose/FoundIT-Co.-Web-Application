package com.soacourse.ass2.foundIt.DAOs;

import com.soacourse.ass2.foundIt.DBManager;
import com.soacourse.ass2.foundIt.DTOs.DTOApplications;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.soacourse.ass2.foundIt.ResponseObj;

public class DAOApplications {
	
	private static DBManager manager = new DBManager();
	private static final String table = "job_applications";
	
	public static ResponseObj getApplications(DTOApplications app) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();

		if ((app.getJid() == null && app.getAppId() == null) || app.getSecurityKey() == null
			|| (app.getJid().isEmpty() && app.getAppId().isEmpty()) || app.getSecurityKey().isEmpty()) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {

			response = manager.selectAll(table, app.hashMap());
			
			if (response != null && response.size() > 0) {
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(response);
			}

			else {
				
				res.setCode("404");
				res.setDescription("No applications found");
			}
		}
		return res;		
	}
	
	public static ResponseObj createApplication(DTOApplications app) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if (app.getJid() == null || app.getCandidateName() == null || app.getStatus() == null || app.getResume() == null || app.getSecurityKey() == null
			|| app.getJid().isEmpty() || app.getCandidateName().isEmpty() || app.getStatus().isEmpty() || app.getResume().isEmpty() || app.getSecurityKey().isEmpty()) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			System.out.print(app.hashMap());

			Integer rs = manager.insert(table, app.hashMap());
			
			if (rs == null || rs == 0) {
				//no rows inserted
				res.setCode("404");
				res.setDescription("No application created");
			}
			else {
				result.put("Response", "application posted");
				result.put("app_id", rs);
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
			}
		}
		
		return res;
	}
	
	public static ResponseObj updateApplication(DTOApplications app, DTOApplications filters) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//cannot be allowed to query the table without giving any unique id
		if (filters.getAppId() == null || filters.getAppId().isEmpty()
			|| filters.getSecurityKey() == null || filters.getSecurityKey().isEmpty()) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.update(table, app.hashMap(), filters.hashMap());

			if (rs == null || rs == 0) {
				
				//no rows inserted
				res.setCode("404");
				res.setDescription("Application not updated");
			}
			else {
				
				result.put("Response", "application updated");
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
			}
		}
		return res;
	}
	
	public static ResponseObj removeApplication(DTOApplications app) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ((app.getJid() == null && app.getAppId() == null) || app.getSecurityKey() == null
			|| (app.getJid().isEmpty() && app.getAppId().isEmpty()) || app.getSecurityKey().isEmpty()) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.delete(table, app.hashMap());
			
			if (rs == null || rs == 0) {
				
				//no rows inserted
				res.setCode("404");
				res.setDescription("No application removed");			}
			else {
				
				result.put("Response", "application removed");
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
			}
		}
		return res;
	}
}
