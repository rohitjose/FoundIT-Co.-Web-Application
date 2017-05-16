package com.soacourse.ass2.foundIt;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.soacourse.ass2.foundIt.DAOs.DAOJobPostings;
import com.soacourse.ass2.foundIt.DAOs.DAOApplications;
import com.soacourse.ass2.foundIt.DAOs.DAOReviews;
import com.soacourse.ass2.foundIt.DTOs.DTOJobPostings;
import com.soacourse.ass2.foundIt.DTOs.DTOApplications;
import com.soacourse.ass2.foundIt.DTOs.DTOReviews;

public class FoundIT {
	
	/*	Jobs	*/
	@GET
	@Path("/jobs")
	@Produces("application/json")
	public Response getJobs(
			@QueryParam("job_id") String jid, 
			@QueryParam("company_name") String companyName,
			@QueryParam("salary_rate") String salaryRate,
			@QueryParam("job_position") String jobPosition,
			@QueryParam("location") String location,
			@QueryParam("job_description") String jobDescription,
			@QueryParam("status") String status,
			@QueryParam("security_key") String securityKey)
	{
		DTOJobPostings job = new DTOJobPostings(jid, companyName, salaryRate, jobPosition, location, jobDescription, status, securityKey);
		return Response.ok().entity(DAOJobPostings.getJobs(job)).build();
	}
	
	@POST
	@Path("/jobs")
	@Produces("application/json")
	public Response createJob( 
			@FormParam("company_name") String companyName,
			@FormParam("salary_rate") String salaryRate,
			@FormParam("job_position") String jobPosition,
			@FormParam("location") String location,
			@FormParam("job_description") String jobDescription,
			@FormParam("status") String status,
			@FormParam("security_key") String securityKey)
	{
		DTOJobPostings job = new DTOJobPostings(null, companyName, salaryRate, jobPosition, location, jobDescription, status, securityKey);
		return Response.ok().entity(DAOJobPostings.createJob(job)).build();
	}
	
	@PUT
	@Path("/jobs")
	@Produces("application/json")
	public Response updateJobPosting(
			@FormParam("job_id") String jid, 
			@FormParam("company_name") String companyName,
			@FormParam("salary_rate") String salaryRate,
			@FormParam("job_position") String jobPosition,
			@FormParam("location") String location,
			@FormParam("job_description") String jobDescription,
			@FormParam("status") String status,
			@FormParam("security_key") String securityKey)
	{
		DTOJobPostings job = new DTOJobPostings(null, companyName, salaryRate, jobPosition, location, jobDescription, status, null);
		DTOJobPostings filters = new DTOJobPostings(jid, null, null, null, null, null, null, securityKey);

		return Response.ok().entity(DAOJobPostings.updateJob(job, filters)).build();
	}
	
	@DELETE
	@Path("/jobs")
	@Produces("application/json")
	public Response removeJobPosting(
			@FormParam("job_id") String jid,
			@FormParam("security_key") String securityKey)
	{
		DTOJobPostings job = new DTOJobPostings(jid, null, null, null, null, null, null, securityKey);

		return Response.ok().entity(DAOJobPostings.removeJob(job)).build();
	}
	
	
	/*	Applications	*/
	@GET
	@Path("/applications")
	@Produces("application/json")
	public Response getApplications(
			@QueryParam("job_id") String jid,
			@QueryParam("app_id") String appid,
			@QueryParam("status") String status,
			@QueryParam("candidate_name") String candidateName,
			@QueryParam("email") String email,
			@QueryParam("security_key") String securityKey) 
	{
		DTOApplications app = new DTOApplications(appid, jid, null, candidateName, email, null, null, status, securityKey);
		return Response.ok().entity(DAOApplications.createApplication(app)).build();
	}
	
	@POST
	@Path("/applications")
	@Produces("application/json")
	public Response createApplication( 
			@FormParam("job_id") String jid,
			@FormParam("candidate_name") String candidateName,
			@FormParam("candidate_id") String cid,
			@FormParam("cover_letter") String coverLetter,
			@FormParam("resume") String resume,
			@FormParam("email") String email,
			@FormParam("application_status") String status,
			@FormParam("security_key") String securityKey)
	{
		DTOApplications app = new DTOApplications(null, jid, null, candidateName, email, coverLetter, resume, "received", securityKey);
		return Response.ok().entity(DAOApplications.createApplication(app)).build();
	}
	
	@PUT
	@Path("/applications")
	@Produces("application/json")
	public Response updateApplication( 
			@FormParam("app_id") String appId,
			@FormParam("job_id") String jid,
			@FormParam("candidate_name") String candidateName,
			@FormParam("cover_letter") String coverLetter,
			@FormParam("resume") String resume,
			@FormParam("status") String status,
			@FormParam("email") String email,
			@FormParam("security_key") String securityKey)
	{
		DTOApplications app = new DTOApplications(null, null, null, candidateName, email, coverLetter, resume, status, null);
		DTOApplications filters = new DTOApplications(appId, jid, null, null, null, null, null, null, securityKey);

		return Response.ok().entity(DAOApplications.updateApplication(app, filters)).build();
	}
	
	@DELETE
	@Path("/applications")
	@Produces("application/json")
	public Response removeApplication() {
		/*	this method is not supported	*/
		return null;
	}
	
	/*	Reviews	*/
	@POST
	@Path("/reviews")
	@Produces("application/json")
	public Response createReview(
			@FormParam("app_id") String appid,
			@FormParam("comments") String comments,
			@FormParam("decision") String decision) {
		
		DTOReviews review = new DTOReviews(null, appid, comments, decision);
		
		return Response.ok().entity(DAOReviews.createReview(review)).build();
	}
	
	@GET
	@Path("/reviews")
	@Produces("application/json")
	public Response getReviews(
			@QueryParam("review_id") String rid,
			@QueryParam("app_id") String appid,
			@QueryParam("comments") String comments,
			@QueryParam("decision") String decision) 
	{
		DTOReviews review = new DTOReviews(rid, appid, comments, decision);

		return Response.ok().entity(DAOReviews.getReviews(review)).build();
	}
	
	@PUT
	@Path("/reviews")
	@Produces("application/json")
	public Response updateReview(
			@FormParam("review_id") String rid,
			@FormParam("app_id") String appid,
			@FormParam("comments") String comments,
			@FormParam("decision") String decision) 
	{
		DTOReviews review = new DTOReviews(null, null, comments, decision);
		DTOReviews filters = new DTOReviews(rid, appid, null, null);

		return Response.ok().entity(DAOReviews.updateReview(review, filters)).build();
	}
	
	@DELETE
	@Path("/reviews")
	@Produces("application/json")
	public Response removeReview(
			@FormParam("review_id") String rid,
			@FormParam("app_id") String appid,
			@FormParam("comments") String comments,
			@FormParam("decision") String decision)
	{
		DTOReviews review = new DTOReviews(rid, appid, comments, decision);

		return Response.ok().entity(DAOReviews.removeReview(review)).build();
	}
}
