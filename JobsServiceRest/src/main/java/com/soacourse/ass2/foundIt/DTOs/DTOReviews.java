package com.soacourse.ass2.foundIt.DTOs;

import java.util.HashMap;

public class DTOReviews {
	
	private static final String REVIEW_ID = "rid";
	private static final String APP_ID = "appid";
	private static final String COMMENTS = "comments";
	private static final String DECISION = "decision";
	//private static final String SECURITY_KEY = "security_key";

	private String rid;
	private String appid;
	private String comments;
	private String decision;
	//private String securityKey;
	
	public DTOReviews(String rid, String appid, String comments, String decision) {
		
		this.rid = rid;
		this.appid = appid;
		this.comments = comments;
		this.decision = decision;
		//this.securityKey = securityKey;
	}
	
	/*	Getters	*/
	public String getRid() {
		return rid;
	}
	public String getAppid() {
		return appid;
	}
	public String getComments() {
		return comments;
	}
	public String getDecision() {
		return decision;
	}

	/*public String getSecurityKey() {
		return securityKey;
	}*/
	
	
	public HashMap<String, Object> hashMap() {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		if (this.getRid() != null && !this.getRid().isEmpty()) {
			params.put(REVIEW_ID, this.getRid());
		}
		if (this.getAppid() != null && !this.getAppid().isEmpty()) {
			params.put(APP_ID, this.getAppid());
		}
		if (this.getComments() != null && !this.getComments().isEmpty()) {
			params.put(COMMENTS, this.getComments());
		}
		if (this.getDecision() != null && !this.getDecision().isEmpty()) {
			params.put(DECISION, this.getDecision());
		}
		/*if (this.getSecurityKey() != null && !this.getSecurityKey().isEmpty()) {
			params.put(SECURITY_KEY, this.getSecurityKey());
		}*/
		
		
		if (params.size() > 0) {
			return params;
		}
		
		return null;
	}
}
