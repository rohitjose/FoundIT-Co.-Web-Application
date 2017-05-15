<%@ page import="au.edu.unsw.soacourse.dto.User"%>
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
<jsp:include page="../candidate/candidateProfileEdit.jsp" />
<%
	}

	else if (role.equals("app-manager")) {
%>
<jsp:include page="../manager/managerProfileEdit.jsp" />
<%
	}

	else if (role.equals("app-reviewer")) {
%>
<jsp:include page="../reviewer/reviewerProfileEdit.jsp" />
<%
	}
%>