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
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}

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

		try {
			PollDao dao = new PollDao();
			dao.insertPoll(poll);
			poll.setPollId(dao.getLastPollId());
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}

		return Response.status(Response.Status.CREATED).entity(poll).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/polls")
	public Response updatePoll(PollQuery query, @HeaderParam("SecurityKey") String securityKey,
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
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e1.getMessage()).build();
		} catch (IOException e2) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1005");
			error.setErrorDescription("Request cannot be processed - Poll has already been voted on");
			return Response.status(Response.Status.NOT_MODIFIED).entity(error).build();
		}

		try {
			poll = dao.getPoll(pollId);
			dao.updateSQL(query);
		} catch (SQLException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1003");
			error.setErrorDescription("Server Error - " + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		} catch (IOException e) {
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode("1004");
			error.setErrorDescription("Resource not found - " + e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}

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

		try {
			polls = dao.searchPolls(query);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.ok().entity(polls).build();
	}

	@DELETE
	@Path("/polls/{input}")
	@Produces("application/json")
	public Response deletePoll(@PathParam("input") String input, @HeaderParam("SecurityKey") String securityKey,
			@HeaderParam("ShortKey") String shortKey) {
		int pollId = Integer.parseInt(input);
		Poll poll = new Poll();

		PollDao dao = new PollDao();

		try {
			poll = dao.getPoll(pollId);
			dao.deletePoll(pollId);
		} catch (SQLException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for pollId: " + pollId).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.ok().entity(poll).build();
	}

	/* SERVICES FOR THE VOTES */
	@GET
	@Path("/vote/{input}")
	@Produces("application/json")
	public Response getVote(@PathParam("input") String input) {
		int voteId = Integer.parseInt(input);
		Vote vote = new Vote();

		PollDao dao = new PollDao();

		try {
			vote = dao.getVote(voteId);
		} catch (SQLException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for pollId: " + voteId).build();
		}

		return Response.ok().entity(vote).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/vote")
	public Response addVote(Vote vote) {
		PollDao dao = new PollDao();

		try {
			dao.insertVote(vote);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.ok().entity("SUCCESS").build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/vote")
	public Response updateVote(PollQuery query) {
		PollDao dao = new PollDao();

		try {
			dao.updateSQL(query);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.ok().entity("SUCCESS").build();
	}

	/* SERVICES FOR THE COMMENTS */
	@GET
	@Path("/comments/{input}")
	@Produces("application/json")
	public Response getComments(@PathParam("input") String input) {
		int pollId = Integer.parseInt(input);
		List<Comment> comments = new ArrayList<Comment>();

		PollDao dao = new PollDao();

		try {
			comments = dao.getCommentsOnPoll(pollId);
		} catch (SQLException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for pollId: " + pollId).build();
		}

		return Response.ok().entity(comments).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/comments")
	public Response addComment(Comment comment) {
		PollDao dao = new PollDao();

		try {
			dao.insertComment(comment);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.ok().entity("SUCCESS").build();
	}

}
