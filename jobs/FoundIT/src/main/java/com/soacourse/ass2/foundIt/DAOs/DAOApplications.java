package com.soacourse.ass2.foundIt.DAOs;

import com.soacourse.ass2.foundIt.DBManager;
import com.soacourse.ass2.foundIt.DTOs.DTOApplications;

import java.util.ArrayList;
import java.util.HashMap;

public class DAOApplications {
	
	private static DBManager manager = new DBManager();
	private static final String table = "job_applications";
	
	public static ArrayList<HashMap<String,Object>> getApplications(DTOApplications app) {
		
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hm = new HashMap<String, Object>();

		if ((app.getJid() == null && app.getAppId() == null) || app.getSecurityKey() == null
			|| (app.getJid().isEmpty() && app.getAppId().isEmpty()) || app.getSecurityKey().isEmpty()) {
			
			//incomplete parameters
			hm.put("Error", "Incomplete request parameters");
			response.add(hm);
		}
		else {
			
			response = manager.selectAll(table, app.hashMap());
			
			if (response == null || response.size() < 1) {
				
				response = new ArrayList<HashMap<String, Object>>();
				hm.put("Error", "No applications found for give search parameters");
				response.add(hm);
			}
		}
		return response;		
	}
	
	public static HashMap<String, Object> createApplication(DTOApplications app) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if (app.getJid() == null || app.getCandidateName() == null || app.getStatus() == null || app.getResume() == null || app.getSecurityKey() == null
			|| app.getJid().isEmpty() || app.getCandidateName().isEmpty() || app.getStatus().isEmpty() || app.getResume().isEmpty() || app.getSecurityKey().isEmpty()) {
			
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.insert(table, app.hashMap());
			
			if (rs == null || rs == 0) {
				//no rows inserted
				result.put("Error", "Application could not be posted at this time. Make sure you entered the required parameters");
			}
			else {
				result.put("Response", "application posted");
				result.put("app_id", rs);
			}
		}
		
		return result;
	}
	
	public static HashMap<String, Object> updateApplication(DTOApplications app, DTOApplications filters) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//cannot be allowed to query the table without giving any unique id
		if (filters.getAppId() == null || filters.getAppId().isEmpty()
			|| filters.getSecurityKey() == null || filters.getSecurityKey().isEmpty()) {
			
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.update(table, app.hashMap(), filters.hashMap());

			if (rs == null || rs == 0) {
				
				//no rows inserted
				result.put("Error", "No application updated. Make sure you entered the required parameters");
			}
			else {
				
				result.put("Response", "application updated");
			}
		}
		return result;
	}
	
	public static HashMap<String, Object> removeApplication(DTOApplications app) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ((app.getJid() == null && app.getAppId() == null) || app.getSecurityKey() == null
			|| (app.getJid().isEmpty() && app.getAppId().isEmpty()) || app.getSecurityKey().isEmpty()) {
			
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.delete(table, app.hashMap());
			
			if (rs == 0) {
				
				//no rows inserted
				result.put("Error", "No application removed. Make sure you entered the required parameters");
			}
			else {
				
				result.put("Response", "application removed");
			}
		}
		return result;
	}
}
