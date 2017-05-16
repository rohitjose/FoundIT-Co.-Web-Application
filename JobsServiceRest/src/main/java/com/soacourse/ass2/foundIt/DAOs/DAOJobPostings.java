package com.soacourse.ass2.foundIt.DAOs;

import com.soacourse.ass2.foundIt.DBManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.soacourse.ass2.foundIt.ResponseObj;

import com.soacourse.ass2.foundIt.DTOs.DTOJobPostings;

public class DAOJobPostings {
	
	private static DBManager manager = new DBManager();
	private static final String table = "job_postings";
	
	public static ResponseObj getJobs(DTOJobPostings job) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();
		
		if(job.getSecurityKey() == null || job.getSecurityKey().isEmpty()) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();			
			result = manager.selectAll(table, job.hashMap());
			
			if (result != null && result.size() > 0) {
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
			}
			else {
				res.setCode("404");
				res.setDescription("No jobs found");
			}
		}
		return res;
	}
	
	
	public static ResponseObj createJob(DTOJobPostings job) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();		
		
		if (job.getCompanyName() == null || job.getJobPosition() == null || job.getJobDescription() == null || job.getStatus() == null || job.getSecurityKey() == null
			|| job.getCompanyName().isEmpty() || job.getJobPosition().isEmpty() || job.getJobDescription().isEmpty() || job.getStatus().isEmpty() || job.getSecurityKey().isEmpty()) {
		
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.insert(table, job.hashMap());

			if (rs == null || rs == 0) {
				//no rows inserted
				res.setCode("404");
				res.setDescription("No jobs Inserted");
			}
			else {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("job_id", rs);
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
			}
		}
		return res;
	}
	
	
	public static ResponseObj removeJob(DTOJobPostings job) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();
		
		if (job.getJid() == null || job.getJid().isEmpty() || job.getSecurityKey() == null || job.getSecurityKey().isEmpty()) {
		
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.delete(table, job.hashMap());

			if (rs == null || rs == 0) {
				//no rows deleted
				res.setCode("404");
				res.setDescription("No jobs found");
			}
			else {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("Response", "Job removed");
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
			}
		}
		return res;
	}
	
	
	public static ResponseObj updateJob(DTOJobPostings job, DTOJobPostings filters) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();
		
		if (filters.getJid() == null || filters.getSecurityKey() == null
			|| filters.getJid().isEmpty() || filters.getSecurityKey().isEmpty()) {
		
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.update(table, job.hashMap(), filters.hashMap());
			
			if (rs == null || rs == 0) {
				//no rows inserted
				res.setCode("404");
				res.setDescription("Could not be updated");
			}
			else {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("job_id", rs);
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
			}
		}
		return res;
	}

}
