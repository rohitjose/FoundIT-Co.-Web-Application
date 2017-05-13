<!DOCTYPE html>
<html lang="en">

<head>

   <!-- Header Import -->
   <jsp:include page="header.jsp" />

</head>

<body id="page-top" class="index">
<div id="skipnav"><a href="#maincontent">Skip to main content</a></div>

    <!-- Navigation -->
    <jsp:include page="welcomeNav.jsp" />

    <!-- Header -->
    <header>
        <div class="container" id="maincontent" tabindex="-1">
            <div class="row">
                <div class="col-lg-12">
                    <img class="img-responsive" src="img/profile.png" alt="">
                    <div class="intro-text">
                        <h1 class="name">Found IT Co.</h1>
                        <hr class="star-light">
                        <span class="skills">Your Dream Job Is Waiting to Apply To You. Get Hired on Hired. Sign Up Now!</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Portfolio Grid Section -->
    <jsp:include page="signupGrid.jsp" />

    <!-- About Section -->
    <jsp:include page="aboutGrid.jsp" />

    <!-- Contact Section -->
    <jsp:include page="loginGrid.jsp" />
    

    <!-- Footer -->
    <jsp:include page="footer.jsp" />
    

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
        <a class="btn btn-primary" href="#page-top">
            <i class="fa fa-chevron-up"></i>
        </a>
    </div>

    <!-- Portfolio Modals -->
    <jsp:include page="signupModals.jsp" />

   <!-- Script Imports -->
   <jsp:include page="scriptImportFooter.jsp" />

</body>

</html>
