<%@ page import="au.edu.unsw.soacourse.dto.User"%>
<%
	User user = new User();
	String role = new String();
	String username = new String();
	if (session.getAttribute("user") != null) {
		user = (User) session.getAttribute("user");
		role = user.getRole();
		username = user.getUsername();
	} else {
		role = "app-candidate";
		username = "Guest";
	}
%>
<nav id="mainNav"
	class="navbar navbar-default navbar-fixed-top navbar-custom">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> Menu <i
					class="fa fa-bars"></i>
			</button>
			<a class="navbar-brand" href="#page-top">Found IT Co.</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<li class="page-scroll"><a href="#job-list" data-toggle="modal">Jobs</a></li>
				<li class="page-scroll"><a href="#polls-list" data-toggle="modal">Interviews</a></li>
				<li class="page-scroll"><a href="#post-jobs"
					data-toggle="modal">Post Jobs</a></li>
				<li class="page-scroll"><a href="#schedule-interview"
					data-toggle="modal">Schedule Interview</a></li>
				<li class="page-scroll"><a href="#profile" data-toggle="modal"><%=username%></a>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>