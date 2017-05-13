<!DOCTYPE html>
<html lang="en">

<head>

<!-- Header Import -->
<jsp:include page="header.jsp" />

</head>

<body id="page-top" class="index">
	<div id="skipnav">
		<a href="#maincontent">Skip to main content</a>
	</div>

	<!-- Navigation -->
	<jsp:include page="seekerNav.jsp" />
	<section id="seeker">
		<div class="container">
			<div class="row"></div>
		</div>
	</section>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />


	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div
		class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
		<a class="btn btn-primary" href="#page-top"> <i
			class="fa fa-chevron-up"></i>
		</a>
	</div>

	<!-- Portfolio Modals -->
	<jsp:include page="seekerModals.jsp" />

	<!-- Script Imports -->
	<jsp:include page="scriptImportFooter.jsp" />

</body>

</html>
