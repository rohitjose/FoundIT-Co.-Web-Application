package com.soacourse.ass2.foundIt.DAOs;

import java.util.ArrayList;
import java.util.HashMap;

import com.soacourse.ass2.foundIt.DBManager;
import com.soacourse.ass2.foundIt.DTOs.DTOReviews;

public class DAOReviews {
	
	private static DBManager manager = new DBManager();
	private static final String table = "application_reviews";
	
	public static ArrayList<HashMap<String,Object>> getReviews(DTOReviews review) {
		
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hm = new HashMap<String, Object>();

		if((review.getAppid() == null && review.getRid() == null)
			|| (review.getAppid().isEmpty() && review.getRid().isEmpty())) {
			
			hm.put("Error", "Incomplete request parameters");
			response.add(hm);
		}
		else {
			
			response = manager.selectAll(table, review.hashMap()); 
			
			if (response == null || response.size() < 1) {
				
				response = new ArrayList<HashMap<String, Object>>();
				hm.put("Error", "No reviews found for give search parameters");
				response.add(hm);
			}
		}
		
		return response;
	}
	
	public static HashMap<String, Object> createReview(DTOReviews review) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();

		if(review.getAppid() == null || review.getDecision() == null
			|| review.getAppid().isEmpty() || review.getDecision().isEmpty()) {
			
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.insert(table, review.hashMap());

			if (rs == null || rs == 0) {
				//no rows inserted
				result.put("Error", "Application could not be posted at this time. Make sure you entered the required parameters");
			}
			else {
				
				result.put("Response", "application posted");
				result.put("review_id", rs);
			}
		}
		
		return result;
	}
	
	public static HashMap<String, Object> updateReview(DTOReviews review, DTOReviews filters) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();

		if (filters.getRid() == null || filters.getRid().isEmpty()) {
			
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.update(table, review.hashMap(), filters.hashMap());
			
			if (rs == null || rs == 0) {
				
				//no rows inserted
				result.put("Error", "No Reviews found against given search parameters");
			}
			else {
				
				result.put("Response", "Review(s) updated");
			}
		}
		
		return result;
	}

	public static HashMap<String, Object> removeReview(DTOReviews review) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		if ((review.getAppid() == null && review.getRid() == null)
				|| (review.getAppid().isEmpty() && review.getRid().isEmpty())) {
			
			result.put("Error", "Incomplete request parameters");
		}
		else {
			
			Integer rs = manager.delete(table, review.hashMap());
			
			if (rs == null || rs == 0) {
				//no rows removed
				result.put("Error", "No Reviews found against given parameters");
			}
			else {
				
				result.put("Response", "Review removed");
			}
		}
		return result;
	}
}
