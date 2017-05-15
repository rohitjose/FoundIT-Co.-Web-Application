package com.soacourse.ass2.foundIt.DAOs;

import com.soacourse.ass2.foundIt.DBManager;

import java.util.ArrayList;
import java.util.HashMap;

import com.soacourse.ass2.foundIt.DTOs.DTOJobPostings;

public class DAOJobPostings {
	
	private static DBManager manager = new DBManager();
	private static final String table = "job_postings";
	
	
	public static ArrayList<HashMap<String,Object>> getJobs(DTOJobPostings job) {
		
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		if(job.getSecurityKey() == null || job.getSecurityKey().isEmpty()) {
			
			hm.put("Error", "Incomplete request parameters");
			result.add(hm);
		}
		else {
			
			result = manager.selectAll(table, job.hashMap());
			
			if (result == null || result.size() < 1) {
				
				hm.put("Error", "No jobs found for given parameters");
				result = new ArrayList<HashMap<String, Object>>();
				result.add(hm);
			}
		}
		return result;
	}
	
	
	public static HashMap<String, Object> createJob(DTOJobPostings job) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if (job.getCompanyName() == null || job.getJobPosition() == null || job.getJobDescription() == null || job.getStatus() == null || job.getSecurityKey() == null
			|| job.getCompanyName().isEmpty() || job.getJobPosition().isEmpty() || job.getJobDescription().isEmpty() || job.getStatus().isEmpty() || job.getSecurityKey().isEmpty()) {
		
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.insert(table, job.hashMap());

			if (rs == null || rs == 0) {
				//no rows inserted
				result.put("Error", "No job post created. Make sure you entered the required parameters");
			}
			else {
				result.put("Response", "job post created");
				result.put("job_id", rs);
			}
		}
		return result;
	}
	
	
	public static HashMap<String, Object> removeJob(DTOJobPostings job) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if (job.getJid() == null || job.getJid().isEmpty() || job.getSecurityKey() == null || job.getSecurityKey().isEmpty()) {
		
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.delete(table, job.hashMap());

			if (rs == null || rs == 0) {
				//no rows deleted
				result.put("Error", "No jobs found for given job_id");
			}
			else {
				
				result.put("Response", "job post removed");
			}
		}
		return result;
	}
	
	
	public static HashMap<String, Object> updateJob(DTOJobPostings job, DTOJobPostings filters) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if (filters.getJid() == null || filters.getSecurityKey() == null
			|| filters.getJid().isEmpty() || filters.getSecurityKey().isEmpty()) {
		
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.update(table, job.hashMap(), filters.hashMap());
			
			if (rs == null || rs == 0) {
				//no rows inserted
				result.put("Error", "No jobs found for given job_id");
			}
			else {
			
				result.put("Response", "job post updated");
			}
		}
		return result;
	}

}
