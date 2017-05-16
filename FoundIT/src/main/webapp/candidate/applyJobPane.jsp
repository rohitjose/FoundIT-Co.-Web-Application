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
			<form id="applyJobForm" action="job" method="post" novalidate>
				<input type="hidden" name="action" value="apply_job_form" />
				<div class="col-md-12">
					<h3>Candidate Details</h3>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Name</label> <input maxlength="100"
								type="text" required="required" class="form-control"
								placeholder="Enter name" name="apply_job_name" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Email</label> <input maxlength="100"
								type="text" required="required" class="form-control"
								placeholder="Enter email" name="apply_job_email" />
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label">Cover Letter</label>
							<textarea required="required" class="form-control"
								placeholder="Cover Letter" rows="3" name="apply_job_coverLetter"></textarea>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label">Resume</label>
							<textarea required="required" class="form-control"
								placeholder="Resume" rows="3" name="apply_job_resume"></textarea>
						</div>
					</div>
					<button id="apply_job-form"
						class="btn btn-lg btn-primary nextBtn pull-right" type="submit">Apply</button>
				</div>
			</form>
		</div>
	</div>
</div>