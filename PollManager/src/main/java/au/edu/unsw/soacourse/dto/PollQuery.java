package au.edu.unsw.soacourse.dto;

import java.util.HashMap;

public class PollQuery {
	String component;
	int pollId;
	HashMap<String,String> match_conditions;
	HashMap<String,String> update_values;
	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}
	/**
	 * @param component the component to set
	 */
	public void setComponent(String component) {
		this.component = component;
	}
	
	
	
	/**
	 * @return the pollId
	 */
	public int getPollId() {
		return pollId;
	}
	/**
	 * @param pollId the pollId to set
	 */
	public void setPollId(int pollId) {
		this.pollId = pollId;
	}
	/**
	 * @return the match_conditions
	 */
	public HashMap<String, String> getMatch_conditions() {
		return match_conditions;
	}
	/**
	 * @param match_conditions the match_conditions to set
	 */
	public void setMatch_conditions(HashMap<String, String> match_conditions) {
		this.match_conditions = match_conditions;
	}
	/**
	 * @return the update_values
	 */
	public HashMap<String, String> getUpdate_values() {
		return update_values;
	}
	/**
	 * @param update_values the update_values to set
	 */
	public void setUpdate_values(HashMap<String, String> update_values) {
		this.update_values = update_values;
	}
	
	

}
