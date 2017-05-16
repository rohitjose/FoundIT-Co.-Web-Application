$(function() {
	// Nav Tab stuff

	$('#seeker-nav > li > a').click(function() {
		if ($(this).hasClass('disabled')) {
			return false;
		} else {
			var linkIndex = $(this).parent().index() - 1;
			$('#seeker-nav > li').each(function(index, item) {
				$(this).attr('rel-index', index - linkIndex);
			});
		}
	});
	$('#manager-nav > li > a').click(function() {
		if ($(this).hasClass('disabled')) {
			return false;
		} else {
			var linkIndex = $(this).parent().index() - 1;
			$('#manager-nav > li').each(function(index, item) {
				$(this).attr('rel-index', index - linkIndex);
			});
		}
	});
	$('#recruiter-nav > li > a').click(function() {
		if ($(this).hasClass('disabled')) {
			return false;
		} else {
			var linkIndex = $(this).parent().index() - 1;
			$('#recruiter-nav > li').each(function(index, item) {
				$(this).attr('rel-index', index - linkIndex);
			});
		}
	});
	// Job Seeker
	$('#step-1-next').click(
			function() {
				// Check values here
				var isValid = true;

				if (isValid) {
					$('#seeker-nav > li:nth-of-type(2) > a').removeClass(
							'disabled').click();
				}
			});
	$('#step-2-next').click(
			function() {
				// Check values here
				var isValid = true;

				if (isValid) {
					$('#seeker-nav > li:nth-of-type(3) > a').removeClass(
							'disabled').click();
				}
			});
	$('#step-3-next').click(
			function() {
				// Check values here
				var isValid = true;

				if (isValid) {
					$('#seeker-nav > li:nth-of-type(4) > a').removeClass(
							'disabled').click();
				}
			});

	// Recruiter
	$('#recruiterstep-1-next').click(
			function() {
				// Check values here
				var isValid = true;

				if (isValid) {
					$('#recruiter-nav > li:nth-of-type(2) > a').removeClass(
							'disabled').click();
				}
			});
	$('#recruiterstep-2-next').click(
			function() {
				// Check values here
				var isValid = true;

				if (isValid) {
					$('#recruiter-nav > li:nth-of-type(3) > a').removeClass(
							'disabled').click();
				}
			});

	// Manager
	$('#managerstep-1-next').click(
			function() {
				// Check values here
				var isValid = true;

				if (isValid) {
					$('#manager-nav > li:nth-of-type(2) > a').removeClass(
							'disabled').click();
				}
			});
	$('#managerstep-2-next').click(
			function() {
				// Check values here
				var isValid = true;

				if (isValid) {
					$('#manager-nav > li:nth-of-type(3) > a').removeClass(
							'disabled').click();
				}
			});
	$('#managerstep-3-next').click(
			function() {
				// Check values here
				var isValid = true;

				if (isValid) {
					$('#manager-nav > li:nth-of-type(4) > a').removeClass(
							'disabled').click();
				}
			});
});

// Candidate Signup fucntion
function seekerSubmit() {
	var seeker_skills = $("#seeker_skills").val();
	var seeker_education = $("#seeker_education").val();
	var seeker_experience = $("#seeker_experience").val();
	var seeker_summary = $("#seeker_summary").val();
	var seeker_position = $("#seeker_position").val();
	var seeker_headline = $("#seeker_headline").val();
	var seeker_state = $("#seeker_state").val();
	var seeker_zip = $("#seeker_zip").val();
	var seeker_city = $("#seeker_city").val();
	var seeker_streetAddress = $("#seeker_streetAddress").val();
	var seeker_site = $("#seeker_site").val();
	var seeker_phone = $("#seeker_phone").val();
	var seeker_last_name = $("#seeker_last_name").val();
	var seeker_first_name = $("#seeker_first_name").val();
	var seeker_confirm_password = $("#seeker_confirm_password").val();
	var seeker_password = $("#seeker_password").val();
	var seeker_email = $("#seeker_email").val();
	var seeker_username = $("#seeker_username").val();

	$.ajax({
		type : 'POST',
		url : 'signup',
		data : {
			'action' : 'seeker_signup',
			'seeker_skills' : seeker_skills,
			'seeker_education' : seeker_education,
			'seeker_experience' : seeker_experience,
			'seeker_summary' : seeker_summary,
			'seeker_position' : seeker_position,
			'seeker_headline' : seeker_headline,
			'seeker_state' : seeker_state,
			'seeker_zip' : seeker_zip,
			'seeker_city' : seeker_city,
			'seeker_streetAddress' : seeker_streetAddress,
			'seeker_site' : seeker_site,
			'seeker_phone' : seeker_phone,
			'seeker_last_name' : seeker_last_name,
			'seeker_first_name' : seeker_first_name,
			'seeker_confirm_password' : seeker_confirm_password,
			'seeker_password' : seeker_password,
			'seeker_email' : seeker_email,
			'seeker_username' : seeker_username,
		},
		success : function(data) {
			console.log('Done');

		}
	}

	);

}

// Recruiter signup function
function recruiterSubmit() {

	var recruiter_username = $("#recruiter_username").val();
	var recruiter_default_password = $("#recruiter_default_password").val();
	var recruiter_password = $("#recruiter_password").val();
	var recruiter_confirm_password = $("#recruiter_confirm_password").val();
	var recruiter_first_name = $("#recruiter_first_name").val();
	var recruiter_last_name = $("#recruiter_last_name").val();
	var recruiter_phone = $("#recruiter_phone").val();
	var recruiter_company = $("#recruiter_company").val();
	var recruiter_position = $("#recruiter_position").val();
	var recruiter_summary = $("#recruiter_summary").val();

	$.ajax({
		type : 'POST',
		url : 'signup',
		data : {
			'action' : 'recruiter_signup',
			'recruiter_username' : recruiter_username,
			'recruiter_default_password' : recruiter_default_password,
			'recruiter_password' : recruiter_password,
			'recruiter_confirm_password' : recruiter_confirm_password,
			'recruiter_first_name' : recruiter_first_name,
			'recruiter_last_name' : recruiter_last_name,
			'recruiter_phone' : recruiter_phone,
			'recruiter_company' : recruiter_company,
			'recruiter_position' : recruiter_position,
			'recruiter_summary' : recruiter_summary,
		},
		success : function(data) {
			console.log('Done');

		}
	}

	);

}

// Manager Signup function
function managerSubmit() {

	var manager_username = $("#manager_username").val();
	var manager_email = $("#manager_email").val();
	var manager_password = $("#manager_password").val();
	var manager_confirm_password = $("#manager_confirm_password").val();
	var manager_first_name = $("#manager_first_name").val();
	var manager_last_name = $("#manager_last_name").val();
	var manager_phone = $("#manager_phone").val();
	var manager_company = $("#manager_company").val();
	var manager_street_address = $("#manager_street_address").val();
	var manager_city = $("#manager_city").val();
	var manager_zip = $("#manager_zip").val();
	var manager_state = $("#manager_state").val();
	var manager_headline = $("#manager_headline").val();
	var manager_business_domain = $("#manager_business_domain").val();
	var manager_summary = $("#manager_summary").val();

	var manager_team_username_1 = $("#manager_team_username_1").val();
	var manager_team_password_1 = $("#manager_team_password_1").val();
	var manager_team_username_2 = $("#manager_team_username_2").val();
	var manager_team_password_2 = $("#manager_team_password_2").val();
	var manager_team_username_3 = $("#manager_team_username_3").val();
	var manager_team_password_3 = $("#manager_team_password_3").val();
	var manager_team_username_4 = $("#manager_team_username_4").val();
	var manager_team_password_4 = $("#manager_team_password_4").val();
	var manager_team_username_5 = $("#manager_team_username_5").val();
	var manager_team_password_5 = $("#manager_team_password_5").val();

	$.ajax({
		type : 'POST',
		url : 'signup',
		data : {
			'action' : 'manager_signup',
			'manager_username' : manager_username,
			'manager_email' : manager_email,
			'manager_password' : manager_password,
			'manager_confirm_password' : manager_confirm_password,
			'manager_first_name' : manager_first_name,
			'manager_last_name' : manager_last_name,
			'manager_phone' : manager_phone,
			'manager_company' : manager_company,
			'manager_street_address' : manager_street_address,
			'manager_city' : manager_city,
			'manager_zip' : manager_zip,
			'manager_state' : manager_state,
			'manager_headline' : manager_headline,
			'manager_business_domain' : manager_business_domain,
			'manager_summary' : manager_summary,
			'manager_team_username_1' : manager_team_username_1,
			'manager_team_password_1' : manager_team_password_1,
			'manager_team_username_2' : manager_team_username_2,
			'manager_team_password_2' : manager_team_password_2,
			'manager_team_username_3' : manager_team_username_3,
			'manager_team_password_3' : manager_team_password_3,
			'manager_team_username_4' : manager_team_username_4,
			'manager_team_password_4' : manager_team_password_4,
			'manager_team_username_5' : manager_team_username_5,
			'manager_team_password_5' : manager_team_password_5,
		},
		success : function(data) {
			console.log('Done');

		}
	}

	);

}

// User login
function loginUser() {
	var username = $("#username").val();
	var password = $("#password").val();

	$.ajax({
		type : 'POST',
		url : 'signup',
		data : {
			'action' : 'user_login',
			'username' : username,
			'password' : password,
		},
		success : function(data) {
			console.log('Done');
			window.location = "../FoundITClient/userHome.jsp";

		}
	}

	);

}

// Search jobs
function searchJobs() {
	var search_position = $("#search_position").val();
	var search_description = $("#search_description").val();
	var search_location = $("#search_location").val();
	var search_salary = $("#search_salary").val();
	var search_company = $("#search_company").val();

	$.ajax({
		type : 'POST',
		url : 'search',
		data : {
			'action' : 'job_search',
			'search_position' : search_position,
			'search_description' : search_description,
			'search_location' : search_location,
			'search_salary' : search_salary,
			'search_company' : search_company,
		},
		success : function(data) {
			console.log('Done');

		}
	}

	);
}



function createPoll(){
	document.getElementById("postPoll").submit();
}






