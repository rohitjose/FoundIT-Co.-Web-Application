package au.edu.unsw.soacourse.polls;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import au.edu.unsw.soacourse.dao.PollDao;
import au.edu.unsw.soacourse.dto.Comment;
import au.edu.unsw.soacourse.dto.ErrorResponse;
import au.edu.unsw.soacourse.dto.Poll;
import au.edu.unsw.soacourse.dto.PollQuery;
import au.edu.unsw.soacourse.dto.Vote;

public class PollManager {

	public void checkFoundITClient(String securityKey) throws IOException {
		if (!securityKey.equals("i-am-foundit")) {
			throw new IOException("Authentication Error - Invalid securityKey");
		}
	}

	public void handleConnectionClose(PollDao dao) {
		try {
			dao.closeConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/* SERVICES FOR THE POLLS */
	@GET
	@Path("/polls/{input}")
	@Produces("application/json")
	public Response getPoll(@PathParam("input") String input, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		int pollId = 0;

		Poll poll = new Poll();

		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		// Validate Input
		try {
			pollId = Integer.parseInt(input);
		} catch (NumberFormatException e2) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid pollId - " + e2.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		PollDao dao = new PollDao();

		try {
			poll = dao.getPoll(pollId);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}

		handleConnectionClose(dao);
		return Response.ok().entity(poll).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/polls")
	public Response addPoll(Poll poll, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		// Check for authorization
		if (shortKey == null || !shortKey.equals("app-manager")) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription("Request cannot be processed - User not authorized to perform the action");
			return Response.status(Response.Status.FORBIDDEN).entity(error).build();
		}

		// Validate input
		try {
			poll.checkPollInput();
		} catch (NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid Request - " + e1.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}
		PollDao dao = new PollDao();
		try {

			dao.insertPoll(poll);
			poll.setPollId(dao.getLastPollId());
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
		handleConnectionClose(dao);
		return Response.status(Response.Status.CREATED).entity(poll).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/polls")
	public Response updatePoll(PollQuery query, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {

		System.out.println("Update called");

		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		// Check for authorization
		if (shortKey == null || !shortKey.equals("app-manager")) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription("Request cannot be processed - User not authorized to perform the action");
			return Response.status(Response.Status.FORBIDDEN).entity(error).build();
		}

		PollDao dao = new PollDao();
		int pollId = query.getPollId();
		Poll poll = new Poll();
		// Validate input
		if (pollId == 0) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid pollId - The pollId specified in the request is invalid");
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		try {
			int count = dao.getVoteCount(pollId);
			if (count > 0) {
				throw new IOException();
			}
		} catch (SQLException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e1.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e2) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1005");
			error.setErrorDescription("Request cannot be processed - Poll has already been voted on");
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_MODIFIED).entity(error).build();
		}

		try {
			poll = dao.getPoll(pollId);
			dao.updateSQL(query);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}

		handleConnectionClose(dao);
		return Response.ok().entity(poll).build();
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/polls")
	public Response searchPoll(PollQuery query, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		PollDao dao = new PollDao();
		List<Poll> polls = new ArrayList<Poll>();

		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		// Check for authorization
		if (shortKey == null || !shortKey.equals("app-manager")) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription("Request cannot be processed - User not authorized to perform the action");
			return Response.status(Response.Status.FORBIDDEN).entity(error).build();
		}

		try {
			polls = dao.searchPolls(query);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}

		handleConnectionClose(dao);
		return Response.ok().entity(polls).build();
	}

	@DELETE
	@Path("/polls/{input}")
	@Produces("application/json")
	public Response deletePoll(@PathParam("input") String input, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {

		int pollId = 0;
		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		// Check for authorization
		if (shortKey == null || !shortKey.equals("app-manager")) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription("Request cannot be processed - User not authorized to perform the action");
			return Response.status(Response.Status.FORBIDDEN).entity(error).build();
		}

		// Validate Input
		try {
			pollId = Integer.parseInt(input);
		} catch (NumberFormatException e2) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid pollId - " + e2.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}
		Poll poll = new Poll();

		PollDao dao = new PollDao();

		try {
			poll = dao.getPoll(pollId);
			dao.deletePoll(pollId);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}

		handleConnectionClose(dao);
		return Response.ok().entity(poll).build();
	}

	/* SERVICES FOR THE VOTES */
	@GET
	@Path("/vote/{input}")
	@Produces("application/json")
	public Response getVote(@PathParam("input") String input, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		int voteId = 0;

		// Validate Input
		try {
			voteId = Integer.parseInt(input);
		} catch (NumberFormatException e2) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid voteId - " + e2.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}
		Vote vote = new Vote();

		PollDao dao = new PollDao();

		try {
			vote = dao.getVote(voteId);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
		handleConnectionClose(dao);
		return Response.ok().entity(vote).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/vote")
	public Response addVote(Vote vote, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {

		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		PollDao dao = new PollDao();

		// Validate input
		try {
			vote.checkVoteInput();
		} catch (NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid Request - " + e1.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		try {
			dao.insertVote(vote);
			vote.setVoteId(dao.getLastVoteId());
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
		handleConnectionClose(dao);
		return Response.status(Response.Status.CREATED).entity(vote).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/vote")
	public Response updateVote(PollQuery query, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		PollDao dao = new PollDao();
		int voteId = query.getVoteId();

		// Validate input
		if (voteId == 0) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid voteId - The voteId specified in the request is invalid");
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		// Check if the vote exists
		Vote vote = new Vote();
		try {
			vote = dao.getVote(voteId);
		} catch (SQLException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e1.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e1.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}

		Poll poll = new Poll();
		try {
			poll = dao.getPoll(vote.getPollId());
		} catch (SQLException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e1.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e1.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}

		if (poll.getFinal_choice() > 0) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid voteId - The poll is closed");
			handleConnectionClose(dao);
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		try {
			dao.updateSQL(query);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
		handleConnectionClose(dao);
		return Response.ok().entity(vote).build();
	}

	@GET
	@Path("/vote/pollId/{input}")
	@Produces("application/json")
	public Response getPollIdVotes(@PathParam("input") String input, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		List<Vote> votes = new ArrayList<Vote>();

		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		// Check for authorization
		if (shortKey == null || !shortKey.equals("app-manager")) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription("Request cannot be processed - User not authorized to perform the action");
			return Response.status(Response.Status.FORBIDDEN).entity(error).build();
		}

		int pollId = 0;

		// Validate Input
		try {
			pollId = Integer.parseInt(input);
		} catch (NumberFormatException e2) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid voteId - " + e2.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		PollDao dao = new PollDao();

		try {
			votes = dao.getVotesOnPoll(pollId);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
		handleConnectionClose(dao);
		return Response.ok().entity(votes).build();
	}

	/* SERVICES FOR THE COMMENTS */
	@GET
	@Path("/comments/{input}")
	@Produces("application/json")
	public Response getComments(@PathParam("input") String input, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		int pollId = Integer.parseInt(input);
		List<Comment> comments = new ArrayList<Comment>();

		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		PollDao dao = new PollDao();

		try {
			comments = dao.getCommentsOnPoll(pollId);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
		handleConnectionClose(dao);
		return Response.ok().entity(comments).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/comments")
	public Response addComment(Comment comment, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		PollDao dao = new PollDao();

		// Authenticate the client
		try {
			checkFoundITClient(securityKey);
		} catch (IOException | NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1002");
			error.setErrorDescription(e1.getMessage());
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
		}

		// Validate input
		try {
			comment.checkCommentInput();
		} catch (NullPointerException e1) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1001");
			error.setErrorDescription("Invalid Request - " + e1.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		try {
			dao.insertComment(comment);
			comment.setCommentId(dao.getLastCommentId());
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			handleConnectionClose(dao);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
		handleConnectionClose(dao);
		return Response.status(Response.Status.CREATED).entity(comment).build();
	}

}
