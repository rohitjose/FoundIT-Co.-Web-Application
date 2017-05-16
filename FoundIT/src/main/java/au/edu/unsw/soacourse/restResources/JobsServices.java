package au.edu.unsw.soacourse.restResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import au.edu.unsw.soacourse.dto.DTOApplications;
import au.edu.unsw.soacourse.dto.DTOJobPostings;
import au.edu.unsw.soacourse.dto.DTOReviews;

public class JobsServices {

	static final String REST_URI = "http://localhost:8080/JobsServiceRest";
	static final String POST = "post";
	static final String GET = "get";

/*
	//	testing
	public static void main(String[] args) {
		JobsDAO obj = new JobsDAO();
		obj.run();
	}
	
	private void run() {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("security_key", "foundit");
		params.put("short_key", "app-candidate");
		params.put("candidate_name", "app-candidate");
		params.put("resume", "app-remuse");
		params.put("status", "in review");

		
		params.put("job_id", "3");
		
		System.out.println("testing the client");
		
		this.submitApplication(params);
	}
	//	testing
	*/
	
	
	
	
	
	/*	Candidate fucntions	*/
	
	public ArrayList<DTOJobPostings> searchJobs(DTOJobPostings params) {
		
		String json = this.getWebClient("/jobs", params.hashMap(), GET).get(String.class);
		
		ArrayList<DTOJobPostings> response = null;
		
		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			
			list = (ArrayList<HashMap<String, Object>>) result.get("data");
						
			if (result.get("code").equals("200")) {
				//successfully retrieved results
				response = new ArrayList<DTOJobPostings>();
				for (HashMap<String, Object> map: list) {
					
					DTOJobPostings job = new DTOJobPostings(map);
					response.add(job);
				}
			}		
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}
	
	public Integer submitApplication(HashMap<String, Object> params) {
		Integer appId = null;
		
		Form form = new Form();
		for (Entry<String, Object> entry : params.entrySet()) { 
			form.param(entry.getKey(), (String) entry.getValue());
	    }
		
		Response response = this.getWebClient("/applications", params, POST).post(form);
		String json = response.readEntity(String.class);
		System.out.print("Response:"+json);

		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			
			if (result.get("code").equals("200")) {
				HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
				appId = (Integer) data.get("app_id");
			}
			System.out.print("Response:"+result);
			System.out.print("\napp_id:"+appId);


		} 
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appId;
	}
	
	public Integer updateApplication(DTOApplications params) {
		Integer code = null;
		
		Form form = new Form();
		
		for (Entry<String, Object> entry : params.hashMap().entrySet()) {
	        
			form.param(entry.getKey(), (String) entry.getValue());
	    }
		
		Response response = this.getWebClient("/applications", params.hashMap(), POST).put(form);
		String json = response.readEntity(String.class);

		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			if (result.get("code").equals("200")) {
				code = 200;
			}
		} 
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.print(code);
		
		return code;
	}
	
	public ArrayList<DTOApplications> getApplication(DTOApplications params) {
		
		String json = this.getWebClient("/jobs", params.hashMap(), GET).get(String.class);
		
		ArrayList<DTOApplications> response = null;
		
		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			
			list = (ArrayList<HashMap<String, Object>>) result.get("data");
						
			if (result.get("code").equals("200")) {
				//successfully retrieved results
				response = new ArrayList<DTOApplications>();
				for (HashMap<String, Object> map: list) {
					
					DTOApplications app = new DTOApplications(map);
					response.add(app);
				}
			}		
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	public Integer deleteApplication(DTOApplications params) {
		Integer code = null;
		
		Response response = this.getWebClient("/applications", params.hashMap(), GET).delete();
		String json = response.readEntity(String.class);

		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			if (result.get("code").equals("200")) {
				code = 200;
			}
		} 
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.print(code);
		return code;
	}
	
	
	
	/*	Manager	*/
	
	public Integer createJob(DTOJobPostings params) {
		
		Integer jid = null;
		
		Form form = new Form();
		
		for (Entry<String, Object> entry : params.hashMap().entrySet()) {
	        
			form.param(entry.getKey(), (String) entry.getValue());
	    }
		
		Response response = this.getWebClient("/jobs", params.hashMap(), POST).post(form);
		String json = response.readEntity(String.class);
		
		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			
			if (result.get("code").equals("200")) {
				HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
				jid = (Integer) data.get("job_id");
			}
			System.out.print("Response:"+result);
			System.out.print("\napp_id:"+jid);


		} 
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jid;
	}
	
	public Integer updateJob(DTOJobPostings params) {
		
		Integer code = null;
		
		Form form = new Form();
		
		for (Entry<String, Object> entry : params.hashMap().entrySet()) {
	        
			form.param(entry.getKey(), (String) entry.getValue());
	    }
		
		Response response = this.getWebClient("/jobs", params.hashMap(), POST).put(form);
		String json = response.readEntity(String.class);

		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			if (result.get("code").equals("200")) {
				code = 200;
			}
		} 
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.print(code);
		
		return code;
	}
	
	public Integer deleteJob(DTOJobPostings params) {
		
		Integer code = null;
		
		Response response = this.getWebClient("/applications", params.hashMap(), GET).delete();
		String json = response.readEntity(String.class);

		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			if (result.get("code").equals("200")) {
				code = 200;
			}
		} 
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.print(code);
		return code;
		
	}
	
	
	
	/*	Reviewer	*/
	
	public ArrayList<DTOReviews> getReviews(DTOApplications params) {
		
		String json = this.getWebClient("/jobs", params.hashMap(), GET).get(String.class);
		
		ArrayList<DTOReviews> response = null;
		
		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			
			list = (ArrayList<HashMap<String, Object>>) result.get("data");
						
			if (result.get("code").equals("200")) {
				//successfully retrieved results
				response = new ArrayList<DTOReviews>();
				for (HashMap<String, Object> map: list) {
					
					DTOReviews app = new DTOReviews(map);
					response.add(app);
				}
			}		
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}


	public Integer addReview(DTOReviews params){
		
		Integer jid = null;
		
		Form form = new Form();
		
		for (Entry<String, Object> entry : params.hashMap().entrySet()) {
	        
			form.param(entry.getKey(), (String) entry.getValue());
	    }
		
		Response response = this.getWebClient("/reviews", params.hashMap(), POST).post(form);
		String json = response.readEntity(String.class);
		
		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			
			if (result.get("code").equals("200")) {
				HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
				jid = (Integer) data.get("job_id");
			}
			System.out.print("Response:"+result);
			System.out.print("\napp_id:"+jid);


		} 
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jid;
	}
	
	
	
	public Integer updateApplicationStatus(DTOApplications params) {
		
		Integer code = null;
		
		Form form = new Form();
		
		for (Entry<String, Object> entry : params.hashMap().entrySet()) {
	        
			form.param(entry.getKey(), (String) entry.getValue());
	    }
		
		Response response = this.getWebClient("/applications", params.hashMap(), POST).put(form);
		String json = response.readEntity(String.class);

		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			if (result.get("code").equals("200")) {
				code = 200;
			}
		} 
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.print(code);
		
		return code;
	}
	
	
	
	/*	Reviewer+Manager*/
	
	public ArrayList<DTOApplications> getjobApplications(DTOApplications params) {

		String json = this.getWebClient("/applications", params.hashMap(), GET).get(String.class);
		
		ArrayList<DTOApplications> response = null;
		
		try {
			
			HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			
			list = (ArrayList<HashMap<String, Object>>) result.get("data");
						
			if (result.get("code").equals("200")) {
				//successfully retrieved results
				response = new ArrayList<DTOApplications>();
				for (HashMap<String, Object> map: list) {
					
					DTOApplications app = new DTOApplications(map);
					response.add(app);
				}
			}		
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	private WebClient getWebClient(String path, HashMap<String, Object> params, String method) {
		
		WebClient client = WebClient.create(REST_URI);
		
		client.path(path);
		client.accept(MediaType.APPLICATION_JSON);
		
		if (method.equals(GET)) {
			
			for (Entry<String, Object> entry : params.entrySet()) {
		        
				client.query(entry.getKey(), entry.getValue());
		    }
		}
		
		return client;
	}
	
}
