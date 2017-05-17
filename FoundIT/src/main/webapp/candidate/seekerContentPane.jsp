<%@ page import="au.edu.unsw.soacourse.dto.DTOJobPostings,java.util.*,au.edu.unsw.soacourse.dto.DTOApplications"%>
<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<%
					if (session.getAttribute("job_results") != null) {
				%>
				<h2>Search Results</h2>
				<%
					} else {
				%><h2>Candidate Dashboard</h2>
				<%
					}
				%>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<%
					if (session.getAttribute("job_results") != null) {
				%>
				<table class="table">
					<tr>
						<th>Position</th>
						<th>Description</th>
						<th>Location</th>
						<th>Salary</th>
						<th>Apply</th>
					</tr>
					<tbody>
						<%
							ArrayList<DTOJobPostings> jobs = (ArrayList<DTOJobPostings>) session.getAttribute("job_results");
								for (DTOJobPostings job : jobs) {
						%>
						<tr>
							<td><%=job.getJobPosition()%></td>
							<td><%=job.getJobDescription()%></td>
							<td><%=job.getLocation()%></td>
							<td><%=job.getSalaryRate()%></td>
							<td>
								<form id="applyJob" action="job" method="post" novalidate>
									<input type="hidden" name="action" value="apply_job" /><input
										type="hidden" name="jobId" value="<%=job.getJid()%>" />
									<button type="submit" class="btn btn-success btn-sm">Apply</button>
								</form>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
				%>
				<%
					if (session.getAttribute("app_values") != null) {
				%>
				<table class="table">
					<tr>
						<th>Application Id</th>
						<th>Job Id</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
					<tbody>
						<%
							ArrayList<DTOApplications> apps = (ArrayList<DTOApplications>) session.getAttribute("app_values");
								for (DTOApplications app : apps) {
						%>
						<tr>
							<td><%=app.getAppId()%></td>
							<td><%=app.getJid()%></td>
							<td>
								<form id="updateApplication" action="job" method="post" novalidate>
									<input type="hidden" name="action" value="update_app" /><input
										type="hidden" name="jobId" value="<%=app.getAppId()%>" />
									<button type="submit" class="btn btn-success btn-sm">Update</button>
								</form>
							</td>
							<td>
								<form id="deleteApplication" action="job" method="post" novalidate>
									<input type="hidden" name="action" value="delete_app" /><input
										type="hidden" name="jobId" value="<%=app.getAppId()%>" />
									<button type="submit" class="btn btn-danger btn-sm">Delete</button>
								</form>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
				%>
			</div>
		</div>
	</div>
</section>