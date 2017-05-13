$(function() {
    // Nav Tab stuff

    
    
    $('#seeker-nav > li > a').click(function() {
        if($(this).hasClass('disabled')) {
            return false;
        } else {
            var linkIndex = $(this).parent().index() - 1;
            $('#seeker-nav > li').each(function(index, item) {
                $(this).attr('rel-index', index - linkIndex);
            });
        }
    });
    $('#manager-nav > li > a').click(function() {
        if($(this).hasClass('disabled')) {
            return false;
        } else {
            var linkIndex = $(this).parent().index() - 1;
            $('#manager-nav > li').each(function(index, item) {
                $(this).attr('rel-index', index - linkIndex);
            });
        }
    });
    $('#recruiter-nav > li > a').click(function() {
        if($(this).hasClass('disabled')) {
            return false;
        } else {
            var linkIndex = $(this).parent().index() - 1;
            $('#recruiter-nav > li').each(function(index, item) {
                $(this).attr('rel-index', index - linkIndex);
            });
        }
    });
    //Job Seeker
    $('#step-1-next').click(function() {
        // Check values here
        var isValid = true;
        
        if(isValid) {
            $('#seeker-nav > li:nth-of-type(2) > a').removeClass('disabled').click();
        }
    });
    $('#step-2-next').click(function() {
        // Check values here
        var isValid = true;
        
        if(isValid) {
            $('#seeker-nav > li:nth-of-type(3) > a').removeClass('disabled').click();
        }
    });
    $('#step-3-next').click(function() {
        // Check values here
        var isValid = true;
        
        if(isValid) {
            $('#seeker-nav > li:nth-of-type(4) > a').removeClass('disabled').click();
        }
    });
    
    //Recruiter
    $('#recruiterstep-1-next').click(function() {
        // Check values here
        var isValid = true;
        
        if(isValid) {
            $('#recruiter-nav > li:nth-of-type(2) > a').removeClass('disabled').click();
        }
    });
    $('#recruiterstep-2-next').click(function() {
        // Check values here
        var isValid = true;
        
        if(isValid) {
            $('#recruiter-nav > li:nth-of-type(3) > a').removeClass('disabled').click();
        }
    });
    
  //Manager
    $('#managerstep-1-next').click(function() {
        // Check values here
        var isValid = true;
        
        if(isValid) {
            $('#manager-nav > li:nth-of-type(2) > a').removeClass('disabled').click();
        }
    });
    $('#managerstep-2-next').click(function() {
        // Check values here
        var isValid = true;
        
        if(isValid) {
            $('#manager-nav > li:nth-of-type(3) > a').removeClass('disabled').click();
        }
    });
    $('#managerstep-3-next').click(function() {
        // Check values here
        var isValid = true;
        
        if(isValid) {
            $('#manager-nav > li:nth-of-type(4) > a').removeClass('disabled').click();
        }
    });
});

function seekerSubmit(){
	var seeker_skills=$("#seeker_skills").val();
	var seeker_education=$("#seeker_education").val();
	var seeker_experience=$("#seeker_experience").val();
	var seeker_summary=$("#seeker_summary").val();
	var seeker_position=$("#seeker_position").val();
	var seeker_headline=$("#seeker_headline").val();
	var seeker_state=$("#seeker_state").val();
	var seeker_zip=$("#seeker_zip").val();
	var seeker_city=$("#seeker_city").val();
	var seeker_streetAddress=$("#seeker_streetAddress").val();
	var seeker_site=$("#seeker_site").val();
	var seeker_phone=$("#seeker_phone").val();
	var seeker_last_name=$("#seeker_last_name").val();
	var seeker_first_name=$("#seeker_first_name").val();
	var seeker_confirm_password=$("#seeker_confirm_password").val();
	var seeker_password=$("#seeker_password").val();
	var seeker_email=$("#seeker_email").val();
	var seeker_username=$("#seeker_username").val();
	
	$.ajax({
		type : 'POST',
		url : 'signup',
		data : {
			'action' : 'seeker_signup',
			'seeker_skills':seeker_skills,
			'seeker_education':seeker_education,
			'seeker_experience':seeker_experience,
			'seeker_summary':seeker_summary,
			'seeker_position':seeker_position,
			'seeker_headline':seeker_headline,
			'seeker_state':seeker_state,
			'seeker_zip':seeker_zip,
			'seeker_city':seeker_city,
			'seeker_streetAddress':seeker_streetAddress,
			'seeker_site':seeker_site,
			'seeker_phone':seeker_phone,
			'seeker_last_name':seeker_last_name,
			'seeker_first_name':seeker_first_name,
			'seeker_confirm_password':seeker_confirm_password,
			'seeker_password':seeker_password,
			'seeker_email':seeker_email,
			'seeker_username':seeker_username,
		},
		success : function(data) {
			console.log('Done');

		}
	}

	);
	
}













































