package au.edu.unsw.soacourse.restResources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import au.edu.unsw.soacourse.dto.Comment;
import au.edu.unsw.soacourse.dto.ErrorResponse;
import au.edu.unsw.soacourse.dto.Poll;
import au.edu.unsw.soacourse.dto.PollQuery;
import au.edu.unsw.soacourse.dto.Vote;

public class PollServices {

	static final String REST_URI = "http://localhost:8080/PollManagerRest/";

	// Polls
	public Poll getPoll(int pollId, String role) {
		Poll poll = new Poll();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);

		client.path("/polls/" + pollId).accept(MediaType.APPLICATION_JSON);
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.get();
		int status = res.getStatus();

		if (status == 200) {
			poll = res.readEntity(Poll.class);
			System.out.println(poll.getPollId());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return poll;

	}

	public Poll updatePoll(PollQuery query, String role) {
		Poll poll = new Poll();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);
		client.path("/polls").accept("application/json").type("application/json");
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.put(query);

		int status = res.getStatus();
		if (status == 200) {
			poll = res.readEntity(Poll.class);
			System.out.println(poll.getPollId());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return poll;
	}

	public Poll deletePoll(int pollId, String role) {
		Poll poll = new Poll();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);

		client.path("/polls/" + pollId).accept(MediaType.APPLICATION_JSON);
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.delete();
		int status = res.getStatus();

		if (status == 200) {
			poll = res.readEntity(Poll.class);
			System.out.println(poll.getPollId());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return poll;

	}

	public Poll createPoll(Poll poll, String role) {
		Poll return_poll = new Poll();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);
		client.path("/polls").accept("application/json").type("application/json");
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.post(poll);

		int status = res.getStatus();

		if (status == 201) {
			return_poll = res.readEntity(Poll.class);
			System.out.println(poll.getPollId());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return return_poll;
	}

	// Votes
	public List<Vote> getVotes(int pollId, String role) {
		List<Vote> votes = new ArrayList<Vote>();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);

		client.path("/vote/pollId/" + pollId).accept(MediaType.APPLICATION_JSON);
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.get();
		int status = res.getStatus();

		if (status == 200) {
			votes = res.readEntity(votes.getClass());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return votes;

	}

	public Vote getVote(int voteId, String role) {
		Vote vote = new Vote();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);

		client.path("/vote/" + voteId).accept(MediaType.APPLICATION_JSON);
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.get();
		int status = res.getStatus();

		if (status == 200) {
			vote = res.readEntity(Vote.class);
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return vote;

	}

	public Vote createVote(Vote vote, String role) {
		Vote return_vote = new Vote();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);
		client.path("/polls").accept("application/json").type("application/json");
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.post(vote);

		int status = res.getStatus();

		if (status == 201) {
			return_vote = res.readEntity(Vote.class);
			System.out.println(vote.getVoteId());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return return_vote;
	}

	public Vote updateVote(PollQuery query, String role) {
		Vote return_vote = new Vote();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);
		client.path("/polls").accept("application/json").type("application/json");
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.put(query);

		int status = res.getStatus();
		if (status == 200) {
			return_vote = res.readEntity(Vote.class);
			System.out.println(return_vote.getVoteId());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return return_vote;
	}

	// Comments

	public List<Comment> getComments(int pollId, String role) {
		List<Comment> comments = new ArrayList<Comment>();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);

		client.path("/comments/" + pollId).accept(MediaType.APPLICATION_JSON);
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.get();
		int status = res.getStatus();

		if (status == 200) {
			comments = res.readEntity(comments.getClass());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return comments;

	}

	public Comment createComment(Comment comment, String role) {
		Comment return_comment = new Comment();
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJsonProvider());
		WebClient client = WebClient.create(REST_URI, providers);
		client.path("/polls").accept("application/json").type("application/json");
		client.header("SecurityKey", "i-am-foundit");
		client.header("ShortKey", role);
		Response res = client.post(comment);

		int status = res.getStatus();

		if (status == 201) {
			return_comment = res.readEntity(Comment.class);
			System.out.println(comment.getParticipant_name());
		} else {
			ErrorResponse error = res.readEntity(ErrorResponse.class);
			System.out.println(error.getErrorCode() + " : " + error.getErrorDescription());
		}

		return return_comment;
	}

	public static void main(String[] args) {
		// List<Object> providers = new ArrayList<Object>();
		// providers.add(new JacksonJsonProvider());
		// WebClient client = WebClient.create(REST_URI, providers);
		// String s = new String();
		//
		// client.path("/polls/5").accept(MediaType.APPLICATION_JSON);
		// client.header("SecurityKey", "i-am-foundit");
		// client.header("ShortKey", "app-manager");
		// s = client.get(String.class);
		// System.out.println("Get all books --");
		// System.out.println(s);
		//
		// WebClient clientPut = WebClient.create(REST_URI, providers);
		//
		// clientPut.path("/polls").accept("application/json").type("application/json");
		// clientPut.header("SecurityKey", "i-am-foundit");
		// clientPut.header("ShortKey", role);
		// PollQuery query = new PollQuery();
		// query.setComponent("poll");
		// query.setPollId(8);
		// HashMap<String, String> map = new HashMap<String, String>();
		// map.put("poll_title", "'title'");
		// map.put("pId", "8");
		// HashMap<String, String> update = new HashMap<String, String>();
		// update.put("poll_title", "'Updated Title'");
		// query.setMatch_conditions(map);
		// query.setUpdate_values(update);
		// Response res = clientPut.put(query);
		// int status = res.getStatus();
		// if (status == 200) {
		// Poll poll = res.readEntity(Poll.class);
		// System.out.println(poll.getPollId());
		// } else {
		// ErrorResponse error = res.readEntity(ErrorResponse.class);
		// System.out.println(error.getErrorCode() + " : " +
		// error.getErrorDescription());
		// }

		//
		// Poll p = poller.getPoll(5);
		// System.out.println(p.getDescription());
		//
		// p = poller.updatePoll(query);
		// System.out.println(p.getDescription());

		PollServices poller = new PollServices();
		// Poll insertPoll = new Poll();
		// insertPoll.setDescription("Interview date for Sales Job");
		// insertPoll.setPoll_title("interview date");
		// insertPoll.setOptionsList("10/5/2017|6/5/2017|23/5/2017");
		// insertPoll.setPoll_option_type("date");
		// insertPoll = poller.createPoll(insertPoll);

//		Poll ps = poller.getPoll(12);
//		System.out.println(ps.getPoll_title());
//		poller.deletePoll(12);
//		ps = poller.getPoll(12);
//		System.out.println(ps.getPoll_title());

	}

}
