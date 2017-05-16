package com.soacourse.ass2.foundIt.DTOs;

import java.util.HashMap;

public class DTOApplications {
	
	private static final String APP_ID = "app_id";
	private static final String JOB_ID = "job_is";
	private static final String CANDIDATE_NAME = "candidate_id";
	private static final String EMAIL = "email";
	private static final String COVER_LETTER = "cover_letter";
	private static final String RESUME = "resume";
	private static final String APP_STATUS = "application_status";
	private static final String CANDIDATE_ID = "candidate_id";
	private static final String SECURITY_KEY = "security_key";

	
	private String appId;
	private String jid;
	private String cid;
	private String candidateName;
	private String email;
	private String coverLetter;
	private String resume;
	private String status;
	private String securityKey;

	
	public DTOApplications(String appId, String jid, String cid, String candidateName, String email, String coverLetter, String resume, String status, String securityKey) {
		this.appId = appId;
		this.jid = jid;
		this.cid = cid;
		this.candidateName = candidateName;
		this.email = email;
		this.coverLetter = coverLetter;
		this.resume = resume;
		this.status = status;
		this.securityKey = securityKey;
	}

	/*	Getters	*/
	public String getSecurityKey() {
		return securityKey;
	}
	public String getAppId() {
		return appId;
	}
	public String getJid() {
		return jid;
	}
	public String getCid() {
		return cid;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public String getEmail() {
		return email;
	}
	public String getCoverLetter() {
		return coverLetter;
	}
	public String getResume() {
		return resume;
	}
	public String getStatus() {
		return status;
	}
	
	public HashMap<String, Object> hashMap() {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		if (this.getJid() != null && !this.getJid().isEmpty()) {
			params.put(JOB_ID, this.getJid());
		}
		if (this.getAppId() != null && !this.getAppId().isEmpty()) {
			params.put(APP_ID, this.getAppId());
		}
		if (this.getCid() != null && !this.getCid().isEmpty()) {
			params.put(CANDIDATE_ID, this.getCid());
		}
		if (this.getCandidateName() != null && !this.getCandidateName().isEmpty()) {
			params.put(CANDIDATE_NAME, this.getCandidateName());
		}
		if (this.getEmail() != null && !this.getEmail().isEmpty()) {
			params.put(EMAIL, this.getEmail());
		}
		if (this.getCoverLetter() != null && !this.getCoverLetter().isEmpty()) {
			params.put(COVER_LETTER, this.getCoverLetter());
		}
		if (this.getResume() != null && !this.getResume().isEmpty()) {
			params.put(RESUME, this.getResume());
		}
		if (this.getStatus() != null && !this.getStatus().isEmpty()) {
			params.put(APP_STATUS, this.getStatus());
		}
		if (this.getSecurityKey() != null && !this.getSecurityKey().isEmpty()) {
			params.put(SECURITY_KEY, this.getSecurityKey());
		}
		
		if (params.size() > 0) {
			return params;
		}
		
		return null;
	}

}
