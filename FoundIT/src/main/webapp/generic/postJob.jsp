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
		<div role="tabpanel" class="tab-pane active">
			<form id="postJob" action="job" method="post" novalidate>
				<input type="hidden" name="action" value="post_job" />
				<div class="col-md-12">
					<h3>Job Details</h3>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Position</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Position"
								id="postJob_position" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Job Description</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Description"
								id="postJob_description" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Location</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Location"
								id="postJob_location" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Salary Rate</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Salary rate"
								id="postJob_salary" />
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label">Company</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter company"
								id="postJob_company" />
						</div>
					</div>
					<button id="search-jobs"
						class="btn btn-lg btn-primary nextBtn pull-right" type="submit">Post</button>
				</div>
			</form>
		</div>
	</div>
</div>