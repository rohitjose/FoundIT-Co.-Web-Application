package com.soacourse.ass2.foundIt.DAOs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.soacourse.ass2.foundIt.DBManager;
import com.soacourse.ass2.foundIt.ResponseObj;
import com.soacourse.ass2.foundIt.DTOs.DTOReviews;

public class DAOReviews {
	
	private static DBManager manager = new DBManager();
	private static final String table = "application_reviews";
	
	public static ResponseObj getReviews(DTOReviews review) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();

		if((review.getAppid() == null || review.getAppid().isEmpty()) && (review.getRid() == null || review.getRid().isEmpty())) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {

			response = manager.selectAll(table, review.hashMap()); 
			
			if (response != null && response.size() > 0) {
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(response);
			}
			else {
				
				res.setCode("404");
				res.setDescription("No reviews found");
			}
		}
		
		return res;
	}
	
	public static ResponseObj createReview(DTOReviews review) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();

		if(review.getAppid() == null || review.getDecision() == null
			|| review.getAppid().isEmpty() || review.getDecision().isEmpty()) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.insert(table, review.hashMap());

			if (rs == null || rs == 0) {
				//no rows inserted
				res.setCode("404");
				res.setDescription("No review created");
			}
			else {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("review_id", rs);

				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
				
			}
		}
		
		return res;
	}
	
	public static ResponseObj updateReview(DTOReviews review, DTOReviews filters) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();

		if (filters.getRid() == null || filters.getRid().isEmpty()) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.update(table, review.hashMap(), filters.hashMap());
			
			if (rs == null || rs == 0) {
				
				res.setCode("404");
				res.setDescription("Review not found");
			}
			else {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("Response", "Review Updated");

				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
			}
		}
		
		return res;
	}

	public static ResponseObj removeReview(DTOReviews review) throws SQLException, ClassNotFoundException {
		
		ResponseObj res = new ResponseObj();

		if ((review.getAppid() == null || review.getAppid().isEmpty()) && (review.getRid() == null || review.getRid().isEmpty())) {
			
			res.setCode("400");
			res.setDescription("Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.delete(table, review.hashMap());
			
			if (rs == null || rs == 0) {
				//no rows removed
				res.setCode("404");
				res.setDescription("Review not found");
			}
			else {
				
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("Response", "Review removed");
				
				res.setCode("200");
				res.setDescription("Success");
				res.setData(result);
				
			}
		}
		return res;
	}
}
