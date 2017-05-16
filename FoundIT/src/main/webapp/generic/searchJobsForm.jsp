<div class="row">
	<div class="board">
		<ul class="nav nav-tabs">
			<div class="liner"></div>
			<li rel-index="0" class="active"><a href="" class="btn"
				aria-controls="managerstep-1" role="tab" data-toggle="tab"><span><i
						class="glyphicon glyphicon-search"></i></span></a></li>
		</ul>
	</div>
	<div class="tab-content">
		<form id="postJob" action="job" method="post" novalidate>
			<input type="hidden" name="action" value="search_job" />
			<div role="tabpanel" class="tab-pane active">
				<div class="col-md-12">
					<h3>Job Details</h3>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Position</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Position"
								name="search_position" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Job Description</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Description"
								name="search_description" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Location</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Location"
								name="search_location" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Salary Rate</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Salary rate"
								name="search_salary" />
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label">Company</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter company"
								name="search_company" />
						</div>
					</div>
					<button id="search-jobs"
						class="btn btn-lg btn-primary nextBtn pull-right" type="submit">Search</button>
				</div>
			</div>
		</form>
	</div>
</div>