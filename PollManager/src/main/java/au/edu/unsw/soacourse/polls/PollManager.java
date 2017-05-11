package au.edu.unsw.soacourse.polls;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import au.edu.unsw.soacourse.dao.PollDao;
import au.edu.unsw.soacourse.dto.Comment;
import au.edu.unsw.soacourse.dto.Poll;
import au.edu.unsw.soacourse.dto.PollQuery;
import au.edu.unsw.soacourse.dto.Vote;

@Path("/polls")
public class PollManager {

	/* SERVICES FOR THE POLLS*/
	@GET
	@Path("/poll/{input}")
	@Produces("application/json")
	public Response getPoll(@PathParam("input") String input) {
		int pollId = Integer.parseInt(input);
		Poll poll = new Poll();

		PollDao dao = new PollDao();

		try {
			poll = dao.getPoll(pollId);
		} catch (SQLException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for pollId: " + pollId).build();
		}

		return Response.ok().entity(poll).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/addPoll")
	public Response addPoll(Poll poll) {
		PollDao dao = new PollDao();

		try {
			dao.insertPoll(poll);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.ok().entity("SUCCESS").build();
	}
	
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/updatePoll")
	public Response updatePoll(PollQuery query) {
		PollDao dao = new PollDao();
		int pollId = query.getPollId();

		try {
			dao.updateSQL(query);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.ok().entity("SUCCESS").build();
	}
	
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/updatePoll")
	public Response searchPoll(PollQuery query) {
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
	@Path("/deletePoll/{input}")
	@Produces("application/json")
	public Response deletePoll(@PathParam("input") String input) {
		int pollId = Integer.parseInt(input);
		Poll poll = new Poll();

		PollDao dao = new PollDao();

		try {
			poll = dao.getPoll(pollId);
			dao.deletePoll(pollId);
		} catch (SQLException e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for pollId: " + pollId).build();
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
	@Path("/addVote")
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
	@Path("/updateVote")
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
	@Path("/addComment")
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
