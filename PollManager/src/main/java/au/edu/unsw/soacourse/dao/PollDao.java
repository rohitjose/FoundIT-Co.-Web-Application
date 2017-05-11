package au.edu.unsw.soacourse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import au.edu.unsw.soacourse.dto.Comment;
import au.edu.unsw.soacourse.dto.Poll;
import au.edu.unsw.soacourse.dto.PollQuery;
import au.edu.unsw.soacourse.dto.Vote;

public class PollDao {

	Connection c;
	String sql_connector = "','";

	// Initialize the connection
	public PollDao() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + this.getClass().getResource("/").getPath() + "/polls");
			c.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	/* INSERT OPERATIONS FOR THE TABLES */

	public void insertSQL(String sql) throws SQLException {
		Statement stmt = c.createStatement();

		System.out.println(sql);

		stmt.executeUpdate(sql);
		stmt.close();
		c.commit();
	}

	// Insert the poll into the table
	public void insertPoll(Poll poll) throws SQLException {

		StringBuilder options = new StringBuilder();
		for (String s : poll.getOptions()) {
			options.append(s);
			options.append("|");
		}

		options.setLength(options.length() - 1);

		String sql = "INSERT INTO polls_info (poll_title, description, poll_option_type, options) " + "VALUES ('"
				+ poll.getPoll_title() + "','" + poll.getDescription() + "','" + poll.getPoll_option_type() + "','"
				+ options.toString() + "')";

		System.out.println(sql);

		insertSQL(sql);
	}

	// Insert into the comment table
	//
	public void insertVote(Vote vote) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO votes_info (pId, participant_name, chosen_option) VALUES ('");
		sql.append(vote.getPollId());
		sql.append(sql_connector);
		sql.append(vote.getParticipant_name());
		sql.append(sql_connector);
		sql.append(vote.getChosen_option());
		sql.append("')");

		insertSQL(sql.toString());
	}

	public void insertComment(Comment comment) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO comments_info (pId, content, participant_name, associated_commentId, date) VALUES ('");
		sql.append(comment.getPollId());
		sql.append(sql_connector);
		sql.append(comment.getContent());
		sql.append(sql_connector);
		sql.append(comment.getParticipant_name());
		sql.append(sql_connector);
		sql.append(comment.getAssociated_comment_id());
		sql.append(sql_connector);
		sql.append(comment.getDate());
		sql.append("')");

		insertSQL(sql.toString());

	}

	/* RETRIEVE OPERATIONS FOR THE TABLES */

	// Get a poll using the pollId
	public Poll getPoll(int pollId) throws SQLException {
		Poll poll = new Poll();

		Statement stmt = c.createStatement();

		String sql = "SELECT * FROM polls_info WHERE pId=" + pollId;
		ResultSet rs = stmt.executeQuery(sql);

		System.out.println(sql);

		while (rs.next()) {
			poll.setPollId(pollId);
			poll.setDescription(rs.getString("description"));
			poll.setPoll_title(rs.getString("poll_title"));
			poll.setPoll_option_type(rs.getString("poll_option_type"));
			poll.setFinal_choice(rs.getInt("final_choice"));
			poll.setOptionsList(rs.getString("options"));
		}
		rs.close();
		stmt.close();

		return poll;
	}
	
	// Get the vote
		public Vote getVote(int voteId) throws SQLException {

			Vote vote = new Vote();

			Statement stmt = c.createStatement();

			String sql = "SELECT * FROM votes_info WHERE voteId=" + voteId;
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println(sql);

			while (rs.next()) {
				vote.setPollId(rs.getInt("pId"));
				vote.setVoteId(rs.getInt("voteId"));
				vote.setParticipant_name(rs.getString("participant_name"));
				vote.setChosen_option(rs.getInt("chosen_option"));
				
			}

			rs.close();
			stmt.close();
			return vote;

		}

	// Get the votes associated to a poll
	public List<Vote> getVotesOnPoll(int pollId) throws SQLException {
		List<Vote> votes = new ArrayList<Vote>();

		Statement stmt = c.createStatement();

		String sql = "SELECT * FROM votes_info WHERE pId=" + pollId;
		ResultSet rs = stmt.executeQuery(sql);

		System.out.println(sql);

		while (rs.next()) {
			Vote vote = new Vote();
			vote.setPollId(pollId);
			vote.setVoteId(rs.getInt("voteId"));
			vote.setParticipant_name(rs.getString("participant_name"));
			vote.setChosen_option(rs.getInt("chosen_option"));
			votes.add(vote);
		}

		rs.close();
		stmt.close();
		return votes;

	}

	// Get the comments associated to a poll
	public List<Comment> getCommentsOnPoll(int pollId) throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();

		Statement stmt = c.createStatement();

		String sql = "SELECT * FROM comments_info WHERE pId=" + pollId;
		ResultSet rs = stmt.executeQuery(sql);

		System.out.println(sql);

		while (rs.next()) {
			Comment comment = new Comment();
			comment.setPollId(pollId);
			comment.setCommentId(rs.getInt("commentId"));
			comment.setContent(rs.getString("content"));
			comment.setAssociated_comment_id(rs.getInt("associated_comment_id"));
			comment.setDate(rs.getString("date"));
			comments.add(comment);
		}

		rs.close();
		stmt.close();
		return comments;

	}
	
	public int getVoteCount(int pollId) throws SQLException {
		String deleteCheck = "select count(*) as count from votes_info where pId=" + pollId;
		int count = 0;

		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(deleteCheck);

		while (rs.next()) {
			count = rs.getInt("count");
		}

		return count;
	}

	/* UPDATE OPERATIONS FOR THE TABLES */
	public void updateSQL(PollQuery query) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE " + query.getComponent() + " SET "); // update segment
		HashMap<String, String> match_conditions = query.getMatch_conditions();
		HashMap<String, String> update_values = query.getUpdate_values();

		for (String key : update_values.keySet()) {
			sql.append(key + "=" + update_values.get(key) + ", ");
		}

		sql.setLength(sql.length() - 2);

		sql.append(" WHERE ");

		for (String key : match_conditions.keySet()) {
			sql.append(key + "=" + match_conditions.get(key) + ", ");
		}

		sql.setLength(sql.length() - 2);

		Statement stmt = c.createStatement();

		System.out.println(sql);

		stmt.executeUpdate(sql.toString());
		stmt.close();
		c.commit();
	}

	/* SEARCH OPERATIONS FOR THE TABLES */

	public String buildSearchQuery(PollQuery query) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM " + query.getComponent() + " WHERE ");
		HashMap<String, String> match_conditions = query.getMatch_conditions();

		for (String key : match_conditions.keySet()) {
			sql.append(key + "LIKE '%" + match_conditions.get(key) + "%', ");
		}

		sql.setLength(sql.length() - 2);

		return sql.toString();

	}

	public List<Poll> searchPolls(PollQuery query) throws SQLException {
		String sql = buildSearchQuery(query);

		List<Poll> polls = new ArrayList<Poll>();

		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		System.out.println(sql);

		while (rs.next()) {
			Poll poll = new Poll();
			poll.setPollId(rs.getInt("pId"));
			poll.setDescription(rs.getString("description"));
			poll.setPoll_title(rs.getString("poll_title"));
			poll.setPoll_option_type(rs.getString("poll_option_type"));
			poll.setFinal_choice(rs.getInt("final_choice"));
			poll.setOptionsList(rs.getString("options"));

			polls.add(poll);
		}
		rs.close();
		stmt.close();

		return polls;

	}

	

	/* DELETE TABLE VALUES */
	public void deletePoll(int pollId) throws SQLException {
		int count = getVoteCount(pollId);
		
		if (count == 0) {
			String sql = "DELETE FROM polls_info WHERE pId=" + pollId;
			Statement stmnt = c.createStatement();
			stmnt.executeUpdate(sql);
			c.commit();
		}

	}

	public void closeConnection() throws SQLException {

		c.close();

	}

	public static void main(String[] args) {
//		PollDao p = new PollDao();
//
//		Poll poll = new Poll();
//		poll.setPoll_title("title");
//		poll.setDescription("description");
//		poll.setPoll_option_type("text");
//		ArrayList<String> options = new ArrayList<String>();
//		options.add("option1");
//		options.add("option2");
//		poll.setOptions(options);
//
//		// try {
//		// p.insertPoll(poll);
//		//
//		// } catch (SQLException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//
//		try {
//			Poll x = p.getPoll(2);
//			p.closeConnection();
//
//			System.out.println(x.getPoll_title() + ":" + x.getDescription());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String test = "options1|options2";
		
		List<String> list = Arrays.asList(test.split("\\|"));
		
		for(String t: list){
			System.out.println(t);
		}

	}

}
