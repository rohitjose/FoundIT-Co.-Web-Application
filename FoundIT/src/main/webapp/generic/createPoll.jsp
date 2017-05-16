<div class="row">
	<div class="board">
		<ul class="nav nav-tabs" id="seeker-nav">
			<div class="liner"></div>
			<li rel-index="0" class="active"><a href="#step-1" class="btn"
				aria-controls="step-1" role="tab" data-toggle="tab"> <span><i
						class="glyphicon glyphicon-user"></i></span>
			</a></li>
			<li rel-index="1"><a href="#step-2" class="btn disabled"
				aria-controls="step-2" role="tab" data-toggle="tab"> <span><i
						class="glyphicon glyphicon-wrench"></i></span>
			</a></li>
			<li rel-index="2"><a href="#step-3" class="btn disabled"
				aria-controls="step-3" role="tab" data-toggle="tab"> <span><i
						class="glyphicon glyphicon-ok-sign"></i></span>
			</a></li>
		</ul>
	</div>
	<form id="postPoll" action="job" method="post">
		<input type="hidden" name="action" value="post_poll" />
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="step-1">
				<div class="col-md-12">
					<h3>Select Job</h3>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label">Job Id</label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Job Id" name="poll_jobId" />
						</div>
					</div>
					<h3>Select Date</h3>
					<%
						for (int i = 1; i <= 5; i++) {
					%>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label">Option <%=i%></label> <input
								maxlength="10" type="text" required="required"
								class="form-control" placeholder="Enter Option <%=i%>"
								name="poll_date_option_<%=i%>" />
						</div>
					</div>
					<%
						}
					%>
					<a id="step-1-next"
						class="btn btn-lg btn-primary nextBtn pull-right">Next</a>
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="step-2">
				<div class="col-md-12">
					<h3>Select Location</h3>
					<%
						for (int i = 1; i <= 5; i++) {
					%>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label">Option <%=i%></label> <input
								maxlength="100" type="text" required="required"
								class="form-control" placeholder="Enter Option <%=i%>"
								name="poll_location_option_<%=i%>" />
						</div>
					</div>
					<%
						}
					%>
					<a id="step-2-next"
						class="btn btn-lg btn-primary nextBtn pull-right">Next</a>
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="step-3">
				<div class="col-md-12">
					<br> <br>
					<p>Please click on the below button to Schedule the interview</p>
					<button id="step-3-next" class="btn btn-lg btn-primary pull-right"
						data-dismiss="modal" type="submit" onclick="createPoll()">Schedule</button>
				</div>
			</div>
		</div>
	</form>
</div>