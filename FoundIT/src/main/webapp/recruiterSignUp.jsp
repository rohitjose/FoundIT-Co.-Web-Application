<div class="row">
    <div class="board">
        <ul class="nav nav-tabs" id="recruiter-nav">
            <div class="liner"></div>
            <li rel-index="0" class="active"><a href="#recruiterstep-1" class="btn"
                aria-controls="recruiterstep-1" role="tab" data-toggle="tab"> <span><i
                        class="glyphicon glyphicon-lock"></i></span>
            </a></li>
            <li rel-index="1"><a href="#recruiterstep-2" class="btn disabled"
                aria-controls="recruiterstep-2" role="tab" data-toggle="tab"> <span><i
                        class="glyphicon glyphicon-user"></i></span>
            </a></li>
            <li rel-index="2"><a href="#recruiterstep-3" class="btn disabled"
                aria-controls="recruiterstep-3" role="tab" data-toggle="tab"> <span><i
                        class="glyphicon glyphicon-ok-sign"></i></span>
            </a></li>
        </ul>
    </div>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="recruiterstep-1">
            <div class="col-md-12">
                <h3>Login Credentials</h3>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">User Name</label> <input
                            maxlength="100" type="text" required="required"
                            class="form-control" placeholder="Enter Name" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Default Password</label> <input
                            maxlength="100" type="password" required="required"
                            class="form-control" placeholder="Enter password" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">New Password</label> <input
                            maxlength="100" type="password" required="required"
                            class="form-control" placeholder="Enter password" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Confirm Password</label> <input
                            maxlength="100" type="password" required="required"
                            class="form-control" placeholder="Confirm Password" />
                    </div>
                </div>
                <button id="recruiterstep-1-next"
                    class="btn btn-lg btn-primary nextBtn pull-right">Next</button>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="recruiterstep-2">
            <div class="col-md-12">
                <h3>Profile</h3>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">First Name</label> <input
                            maxlength="100" type="text" required="required"
                            class="form-control" placeholder="Enter first name" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Last Name</label> <input
                            maxlength="10" type="text" required="required"
                            class="form-control" placeholder="Enter first name" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Phone Number</label> <input
                            maxlength="100" type="tel" class="form-control"
                            placeholder="Enter phone number" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Company Name</label> <input
                            maxlength="100" type="text" class="form-control"
                            placeholder="Enter company name" />
                    </div>
                </div>
                <h3>Company Details</h3>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="control-label">Position</label> <input
                            maxlength="34" type="text" required="required"
                            class="form-control" placeholder="Enter Position" />
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="control-label">Summary</label>
                        <textarea required="required" class="form-control"
                            placeholder="Career description" rows="3"></textarea>
                    </div>
                </div>
                <button id="recruiterstep-2-next"
                    class="btn btn-lg btn-primary nextBtn pull-right">Next</button>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="recruiterstep-3">
            <div class="col-md-12">
                <br> <br>
                <p>Please click on the below button to sign up into Found IT Co.</p>
                <button id="recruiterstep-3-next" class="btn btn-lg btn-primary pull-right"
                    data-dismiss="modal">Sign Up</button>
            </div>
        </div>
    </div>
</div>