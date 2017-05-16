package au.edu.unsw.soacourse.dto;

import java.util.HashMap;

public class DTOJobPostings {
	
	private static final String JID = "jid";
	private static final String COMPANY_NAME = "company_name";
	private static final String SALARY_RATE = "salary_rate";
	private static final String JOB_POSITION = "job_position";
	private static final String LOCATION = "location";
	private static final String JOB_DESCRIPTION = "job_description";
	private static final String STATUS = "status";
	private static final String SECURITY_KEY = "security_key";
	private static final String SHORT_KEY = "short_key";

	
	private String jid;
	private String companyName;
	private String salaryRate;
	private String jobPosition;
	private String location;
	private String jobDescription;
	private String status;
	private String securityKey;
	private String shortKey;

	
	//Default constructor
	public DTOJobPostings () {
		
	}
	
	public DTOJobPostings(String jid, String companyName, String salaryRate, String jobPosition, String location, String jobDescription, String status, String securityKey, String shortKey) {
	
		this.jid = jid;
		this.companyName = companyName;
		this.salaryRate = salaryRate;
		this.jobPosition = jobPosition;
		this.location = location;
		this.jobDescription = jobDescription;
		this.status = status;
		this.securityKey = securityKey;
		this.shortKey = shortKey;

	}
	

	/* Getters */
	public String getShortKey() {
		return shortKey;
	}
	public String getSecurityKey() {
		return securityKey;
	}
	public String getJid() {
		return jid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getSalaryRate() {
		return salaryRate;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public String getLocation() {
		return location;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public String getStatus() {
		return status;
	}

	public HashMap<String, Object> hashMap() {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		if (this.getJid() != null && !this.getJid().isEmpty()) {
			params.put(JID, this.getJid());
		}
		if (this.getCompanyName() != null && !this.getCompanyName().isEmpty()) {
			params.put(COMPANY_NAME, this.getCompanyName());
		}
		if (this.getSalaryRate() != null && !this.getSalaryRate().isEmpty()) {
			params.put(SALARY_RATE, this.getSalaryRate());
		}
		if (this.getJobPosition() != null && !this.getJobPosition().isEmpty()) {
			params.put(JOB_POSITION, this.getJobPosition());
		}
		if (this.getLocation() != null && !this.getLocation().isEmpty()) {
			params.put(LOCATION, this.getLocation());
		}
		if (this.getJobDescription() != null && !this.getJobDescription().isEmpty()) {
			params.put(JOB_DESCRIPTION, this.getJobDescription());
		}
		if (this.getStatus() != null && !this.getStatus().isEmpty()) {
			params.put(STATUS, this.getStatus());
		}
		if (this.getSecurityKey() != null && !this.getSecurityKey().isEmpty()) {
			params.put(SECURITY_KEY, this.getSecurityKey());
		}
		if (this.getShortKey() != null && !this.getShortKey().isEmpty()) {
			params.put(SHORT_KEY, this.getShortKey());
		}
		
		if (params.size() > 0) {
			return params;
		}
		
		return null;
	}
	
	public DTOJobPostings(HashMap<String, Object> hm) {
		
		if (hm.get(JID) != null) {
			this.jid = hm.get(JID).toString();
		}
		if (hm.get(COMPANY_NAME) != null) {
			this.companyName = hm.get(COMPANY_NAME).toString();
		}
		if (hm.get(SALARY_RATE) != null) {
			this.salaryRate = hm.get(SALARY_RATE).toString();
		}
		if (hm.get(JOB_POSITION) != null) {
			this.jobPosition = hm.get(JOB_POSITION).toString();
		}
		if (hm.get(LOCATION) != null) {
			this.location = hm.get(LOCATION).toString();
		}
		if (hm.get(JOB_DESCRIPTION) != null) {
			this.jobDescription = hm.get(JOB_DESCRIPTION).toString();
		}
		if (hm.get(STATUS) != null) {
			this.status = hm.get(STATUS).toString();
		}
		if (hm.get(SECURITY_KEY) != null) {
			this.securityKey = hm.get(SECURITY_KEY).toString();
		}
		if (hm.get(SHORT_KEY) != null) {
			this.shortKey = hm.get(SHORT_KEY).toString();
		}
	}
}

