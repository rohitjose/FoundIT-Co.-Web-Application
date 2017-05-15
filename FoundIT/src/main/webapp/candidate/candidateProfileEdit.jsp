<div class="row">
    <div class="board">
        <ul class="nav nav-tabs" id="seeker-nav">
            <div class="liner"></div>
            <li rel-index="1"><a href="#step-2" class="btn disabled"
                aria-controls="step-2" role="tab" data-toggle="tab"> <span><i
                        class="glyphicon glyphicon-user"></i></span>
            </a></li>
            <li rel-index="2"><a href="#step-3" class="btn disabled"
                aria-controls="step-3" role="tab" data-toggle="tab"> <span><i
                        class="glyphicon glyphicon-wrench"></i></span>
            </a></li>
            <li rel-index="3"><a href="#step-4" class="btn disabled"
                aria-controls="step-4" role="tab" data-toggle="tab"> <span><i
                        class="glyphicon glyphicon-ok-sign"></i></span>
            </a></li>
        </ul>
    </div>
        <div role="tabpanel" class="tab-pane active" id="step-2">
            <div class="col-md-12">
                <h3>Profile</h3>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">First Name</label> <input
                            maxlength="100" type="text" required="required"
                            class="form-control" placeholder="Enter first name" id="seeker_first_name"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Last Name</label> <input
                            maxlength="10" type="text" required="required"
                            class="form-control" placeholder="Enter last name" id="seeker_last_name"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Phone Number</label> <input
                            maxlength="100" type="tel" class="form-control"
                            placeholder="Enter phone number" id="seeker_phone"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Website (option)</label> <input
                            maxlength="100" type="text" class="form-control" id="seeker_site"
                            placeholder="URL" />
                    </div>
                </div>
                <h3>Address</h3>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Street Address</label> <input
                            maxlength="100" type="text" required="required"
                            class="form-control" placeholder="Enter Address" id="seeker_streetAddress"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">City</label> <input maxlength="40"
                            type="text" required="required" class="form-control"
                            placeholder="Enter City" id="seeker_city"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">ZIP</label> <input maxlength="5"
                            type="text" required="required" class="form-control"
                            placeholder="Enter ZIP Code" id="seeker_zip"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">State</label> <input maxlength="2"
                            type="text" required="required" class="form-control"
                            placeholder="Enter State" id="seeker_state"/>
                    </div>
                </div>
                <h3>Career Summary</h3>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Headline</label> <input
                            maxlength="34" type="text" required="required"
                            class="form-control" placeholder="Enter headline" id="seeker_headline"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Current Position</label> <input
                            maxlength="100" type="text" required="required"
                            class="form-control" placeholder="Enter current position" id="seeker_position"/>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="control-label">Summary</label>
                        <textarea required="required" class="form-control"
                            placeholder="Career description" rows="3" id="seeker_summary"></textarea>
                    </div>
                </div>
                <button id="step-2-next"
                    class="btn btn-lg btn-primary nextBtn pull-right">Next</button>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="step-3">
            <div class="col-md-12">
                <h3>Career Background</h3>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="control-label">Work Experience</label>
                        <textarea required="required" class="form-control"
                            placeholder="Work Experience" rows="3" id="seeker_experience"></textarea>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="control-label">Education</label>
                        <textarea required="required" class="form-control"
                            placeholder="Education" rows="3" id="seeker_education"></textarea>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="control-label">Skills</label>
                        <textarea required="required" class="form-control"
                            placeholder="Professional Skills" rows="3" id="seeker_skills"></textarea>
                    </div>
                </div>
                <button id="step-3-next"
                    class="btn btn-lg btn-primary nextBtn pull-right">Next</button>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="step-4">
            <div class="col-md-12">
                <br> <br>
                <p>Please click on the below button to update your profile</p>
                <button id="step-4-next" class="btn btn-lg btn-primary pull-right"
                    data-dismiss="modal" onclick="updateSeekerProfile()">Update</button>
            </div>
        </div>
    </div>
</div>