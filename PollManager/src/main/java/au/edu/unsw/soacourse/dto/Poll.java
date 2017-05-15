package au.edu.unsw.soacourse.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Poll {
	int pollId;
	String poll_title;
	String description;
	String poll_option_type;
	List<String> options;
	int final_choice;
	List<Vote> votes;
	List<Comment> comments;

	/**
	 * @return the pollId
	 */
	public int getPollId() {
		return pollId;
	}

	/**
	 * @param pollId
	 *            the pollId to set
	 */
	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	/**
	 * @return the poll_title
	 */
	public String getPoll_title() {
		return poll_title;
	}

	/**
	 * @param poll_title
	 *            the poll_title to set
	 */
	public void setPoll_title(String poll_title) {
		this.poll_title = poll_title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the poll_option_type
	 */
	public String getPoll_option_type() {
		return poll_option_type;
	}

	/**
	 * @param poll_option_type
	 *            the poll_option_type to set
	 */
	public void setPoll_option_type(String poll_option_type) {
		this.poll_option_type = poll_option_type;
	}

	/**
	 * @return the options
	 */
	public List<String> getOptions() {
		return options;
	}

	/**
	 * @param list
	 *            the options to set
	 */
	public void setOptions(List<String> list) {
		this.options = list;
	}

	/**
	 * @return the final_choice
	 */
	public int getFinal_choice() {
		return final_choice;
	}

	/**
	 * @param final_choice
	 *            the final_choice to set
	 */
	public void setFinal_choice(int final_choice) {
		this.final_choice = final_choice;
	}

	public void setOptionsList(String optionString) {

		setOptions(Arrays.asList(optionString.split("\\|")));
	}
	
	public void checkPollInput() throws NullPointerException{
		if(this.poll_title==null){
			throw new NullPointerException("No title provided for the Poll");
		}
		if(this.description==null){
			throw new NullPointerException("No description provided for the Poll");
		}
		if(this.poll_option_type==null){
			throw new NullPointerException("No type provided for the Poll");
		}
		if(this.options==null || this.options.size()==0){
			throw new NullPointerException("No option provided for the Poll");
		}
	}

}
