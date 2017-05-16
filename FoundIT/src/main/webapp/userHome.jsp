<%@ page import="au.edu.unsw.soacourse.dto.User"%>
<!DOCTYPE html>
<html lang="en">

<head>

<!-- Header Import -->
<jsp:include page="common/header.jsp" />

</head>

<body id="page-top" class="index">
	<div id="skipnav">
		<a href="#maincontent">Skip to main content</a>
	</div>

	<!-- Navigation -->
	<%
		User user = new User();
		String role = new String();
		if (session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
			role = user.getRole();
		} else {
			role = "app-candidate";
		}
	%>

	<%
		if (role.equals("app-candidate")) {
	%>
	<jsp:include page="candidate/seekerNav.jsp" />
	<%
		} else

		if (role.equals("app-manager")) {
	%>
	<jsp:include page="manager/manager_nav.jsp" />
	<%
		}
	%>

	<!-- Navigation End -->

	<!-- Content pane begin -->
	<section id="content_pane">
		<div class="container">
			<div class="row">
				<%
					if (role.equals("app-candidate")) {
				%>
				<jsp:include page="candidate/seekerContentPane.jsp" />
				<%
					} else if (role.equals("app-manager")) {
				%>
				<jsp:include page="manager/managerContentPane.jsp" />
				<%
					}
				%>
			</div>
		</div>
	</section>
	<!-- Content pane end -->


	<!-- Footer -->
	<jsp:include page="common/footer.jsp" />


	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div
		class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
		<a class="btn btn-primary" href="#page-top"> <i
			class="fa fa-chevron-up"></i>
		</a>
	</div>

	<!-- Portfolio Modals -->
	<%
		if (role.equals("app-candidate")) {
	%>
	<jsp:include page="candidate/seekerModals.jsp" />
	<%
		} else if (role.equals("app-manager")) {
	%>
	<jsp:include page="manager/managerModals.jsp" />
	<%
		}
	%>

	<!-- Script Imports -->
	<jsp:include page="common/scriptImportFooter.jsp" />

</body>

</html>
