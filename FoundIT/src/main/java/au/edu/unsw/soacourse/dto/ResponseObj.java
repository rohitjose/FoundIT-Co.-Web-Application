package au.edu.unsw.soacourse.dto;

public class ResponseObj {
	
	public String code;
	public String description;
	public Object data;
	
	public ResponseObj() {
		// TODO Auto-generated constructor stub
	}
	
	public ResponseObj(String code, String description, Object data) {
		
		this.code = code;
		this.description = description;
		this.data = data;
	}
	
	/*	Getters/Setters*/
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
