package au.edu.unsw.soacourse.dto;

import java.util.HashMap;

public class DTOReviews {
	
	private static final String REVIEW_ID = "review_id";
	private static final String APP_ID = "app_id";
	private static final String COMMENTS = "comments";
	private static final String DECISION = "decision";
	private static final String SHORT_KEY = "short_key";

	private String rid;
	private String appid;
	private String comments;
	private String decision;
	private String shortKey;
	
	public DTOReviews(String rid, String appid, String comments, String decision, String shortKey) {
		
		this.rid = rid;
		this.appid = appid;
		this.comments = comments;
		this.decision = decision;
		this.shortKey = shortKey;
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

	public String getShortKey() {
		return shortKey;
	}
	
	
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
		if (this.getShortKey() != null && !this.getShortKey().isEmpty()) {
			params.put(SHORT_KEY, this.getShortKey());
		}
		
		
		if (params.size() > 0) {
			return params;
		}
		
		return null;
	}
	
	public DTOReviews (HashMap<String, Object> hm) {
		
		if (hm.get(REVIEW_ID) != null) {
			this.rid = hm.get(REVIEW_ID).toString();
		}
		if (hm.get(APP_ID) != null) {
			this.appid = hm.get(APP_ID).toString();
		}
		if (hm.get(COMMENTS) != null) {
			this.comments = hm.get(COMMENTS).toString();
		}
		if (hm.get(DECISION) != null) {
			this.decision = hm.get(DECISION).toString();
		}
		if (hm.get(SHORT_KEY) != null) {
			this.shortKey = hm.get(SHORT_KEY).toString();
		}
	}
}
