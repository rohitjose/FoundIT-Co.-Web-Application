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