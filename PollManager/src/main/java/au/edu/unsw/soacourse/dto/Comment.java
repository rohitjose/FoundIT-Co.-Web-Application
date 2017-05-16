package au.edu.unsw.soacourse.dto;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	int commentId;
	int pollId;
	String content;
	String participant_name;
	int associated_comment_id;
	String date;
	
	public void checkCommentInput() throws NullPointerException{
		if(this.pollId==0){
			throw new NullPointerException("No pollId provided");
		}
		if(this.content==null){
			throw new NullPointerException("No content provided");
		}
		if(this.participant_name==null){
			throw new NullPointerException("No participant_name provided");
		}
		if(this.date==null){
			throw new NullPointerException("No date provided");
		}
		
	}
	/**
	 * @return the commentId
	 */
	public int getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the associated_comment_id
	 */
	public int getAssociated_comment_id() {
		return associated_comment_id;
	}
	/**
	 * @param associated_comment_id the associated_comment_id to set
	 */
	public void setAssociated_comment_id(int associated_comment_id) {
		this.associated_comment_id = associated_comment_id;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
