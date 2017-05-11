package au.edu.unsw.soacourse.dto;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vote {
	int voteId;
	int pollId;
	String participant_name;
	int chosen_option;
	/**
	 * @return the voteId
	 */
	public int getVoteId() {
		return voteId;
	}
	/**
	 * @param voteId the voteId to set
	 */
	public void setVoteId(int voteId) {
		this.voteId = voteId;
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
	 * @return the participant_name
	 */
	public String getParticipant_name() {
		return participant_name;
	}
	/**
	 * @param participant_name the participant_name to set
	 */
	public void setParticipant_name(String participant_name) {
		this.participant_name = participant_name;
	}
	/**
	 * @return the chosen_option
	 */
	public int getChosen_option() {
		return chosen_option;
	}
	/**
	 * @param chosen_option the chosen_option to set
	 */
	public void setChosen_option(int chosen_option) {
		this.chosen_option = chosen_option;
	}
	
	
	

}
