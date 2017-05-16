<%@ page import="au.edu.unsw.soacourse.dto.DTOJobPostings,java.util.*"%>
<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Manager Dashboard</h2>
				<hr class="star-primary">
			</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
				<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
				<%
					if (session.getAttribute("manager_jobs") != null) {
				%>
				<table class="table">
					<tr>
						<th>Position</th>
						<th>Description</th>
						<th>Location</th>
						<th>Salary</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
					<tbody>
						<%
							ArrayList<DTOJobPostings> jobs = (ArrayList<DTOJobPostings>) session.getAttribute("manager_jobs");
								for (DTOJobPostings job : jobs) {
						%>
						<tr>
							<td><%=job.getJobPosition()%></td>
							<td><%=job.getJobDescription()%></td>
							<td><%=job.getLocation()%></td>
							<td><%=job.getSalaryRate()%></td>
							<td>
								<form id="updateJob" action="job" method="post" novalidate>
									<input type="hidden" name="action" value="update_job" /><input
										type="hidden" name="jobId" value="<%=job.getJid()%>" />
									<button type="submit" class="btn btn-success btn-sm">Update</button>
								</form>
							</td>
							<td>
								<form id="deleteJob" action="job" method="post" novalidate>
									<input type="hidden" name="action" value="delete_job" /><input
										type="hidden" name="jobId" value="<%=job.getJid()%>" />
									<button type="submit" class="btn btn-danger btn-sm">Delete</button>
								</form>
							</td>
						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</section>