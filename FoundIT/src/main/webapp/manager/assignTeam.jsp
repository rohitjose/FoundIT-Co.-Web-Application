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
			<form id="assignTeam" action="job" method="post" novalidate>
				<input type="hidden" name="action" value="assign_team" />
				<div class="col-md-12">
					<h3>Application</h3>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label">Application Id</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Position"
								name="assignTeam_applicationId" />
						</div>
					</div>
					<h3>Review Team</h3>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Reviewer 1</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter username"
								name="assignTeam_reviewer_1" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Reviewer 2</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter username"
								name="assignTeam_reviewer_2" />
						</div>
					</div>

					<button id="assign-team"
						class="btn btn-lg btn-primary nextBtn pull-right" type="submit">Assign</button>
				</div>
			</form>
		</div>
	</div>
</div>