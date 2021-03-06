<div class="row">
	<div class="board">
		<ul class="nav nav-tabs" id="manager-nav">
			<div class="liner"></div>
			<li rel-index="0" class="active"><a href="#managerstep-1"
				class="btn" aria-controls="managerstep-1" role="tab"
				data-toggle="tab"> <span><i
						class="glyphicon glyphicon-lock"></i></span>
			</a></li>
			<li rel-index="1"><a href="#managerstep-2" class="btn disabled"
				aria-controls="managerstep-2" role="tab" data-toggle="tab"> <span><i
						class="glyphicon glyphicon-user"></i></span>
			</a></li>
			<li rel-index="2"><a href="#managerstep-3" class="btn disabled"
				aria-controls="managerstep-3" role="tab" data-toggle="tab"> <span><i
						class="glyphicon glyphicon-wrench"></i></span>
			</a></li>
			<li rel-index="3"><a href="#managerstep-4" class="btn disabled"
				aria-controls="managerstep-4" role="tab" data-toggle="tab"> <span><i
						class="glyphicon glyphicon-ok-sign"></i></span>
			</a></li>
		</ul>
	</div>
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="managerstep-1">
			<div class="col-md-12">
				<h3>Login Credentials</h3>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">User Name</label> <input
							maxlength="100" type="text" required="required"
							class="form-control" placeholder="Enter Name"
							id="manager_username" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Email</label> <input maxlength="100"
							type="email" required="required" class="form-control"
							placeholder="Enter Email" id="manager_email" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Password</label> <input
							maxlength="100" type="password" required="required"
							class="form-control" placeholder="Enter password"
							id="manager_password" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Confirm Password</label> <input
							maxlength="100" type="password" required="required"
							class="form-control" placeholder="Confirm Password"
							id="manager_confirm_password" />
					</div>
				</div>
				<button id="managerstep-1-next"
					class="btn btn-lg btn-primary nextBtn pull-right">Next</button>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="managerstep-2">
			<div class="col-md-12">
				<h3>Profile</h3>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">First Name</label> <input
							maxlength="100" type="text" required="required"
							class="form-control" placeholder="Enter first name"
							id="manager_first_name" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Last Name</label> <input
							maxlength="10" type="text" required="required"
							class="form-control" placeholder="Enter last name"
							id="manager_last_name" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Phone Number</label> <input
							maxlength="100" type="tel" class="form-control"
							placeholder="Enter phone number" id="manager_phone" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Company Name</label> <input
							maxlength="100" type="text" class="form-control"
							placeholder="Enter company name" id="manager_company" />
					</div>
				</div>
				<h3>Company Location</h3>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Street Address</label> <input
							maxlength="100" type="text" required="required"
							class="form-control" placeholder="Enter Address"
							id="manager_street_address" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">City</label> <input maxlength="40"
							type="text" required="required" class="form-control"
							placeholder="Enter City" id="manager_city" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">ZIP</label> <input maxlength="5"
							type="text" required="required" class="form-control"
							placeholder="Enter ZIP Code" id="manager_zip" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">State</label> <input maxlength="2"
							type="text" required="required" class="form-control"
							placeholder="Enter State" id="manager_state" />
					</div>
				</div>
				<h3>Company Details</h3>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Headline</label> <input
							maxlength="34" type="text" required="required"
							class="form-control" placeholder="Enter headline"
							id="manager_headline" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Business Domain</label> <input
							maxlength="100" type="text" required="required"
							class="form-control" placeholder="Enter Business Domain"
							id="manager_business_domain" />
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label">Summary</label>
						<textarea required="required" class="form-control"
							placeholder="Career description" rows="3" id="manager_summary"></textarea>
					</div>
				</div>
				<button id="managerstep-2-next"
					class="btn btn-lg btn-primary nextBtn pull-right">Next</button>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="managerstep-3">
			<div class="col-md-12">
				<h3>Hiring Team</h3>
				<%
					for (int i = 1; i <= 5; i++) {
				%>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Team Member Username</label> <input
							maxlength="100" type="text" required="required"
							class="form-control" placeholder="Enter Username"
							id="manager_team_username_<%=i%>" />
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label">Default Password</label> <input
							maxlength="100" type="password" required="required"
							class="form-control" placeholder="Enter password"
							id="manager_team_password_<%=i%>" />
					</div>
				</div>
				<%
					}
				%>
				<button id="managerstep-3-next"
					class="btn btn-lg btn-primary nextBtn pull-right">Next</button>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="managerstep-4">
			<div class="col-md-12">
				<br> <br>
				<p>Please click on the below button to sign up into Found IT Co.</p>
				<button id="managerstep-4-next"
					class="btn btn-lg btn-primary pull-right" data-dismiss="modal"
					onclick="managerSubmit()">Sign Up</button>
			</div>
		</div>
	</div>
</div>