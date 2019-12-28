<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<ui:header />
<div class="row">
	<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading">
				<div class="panel-actions">
					<a href="#" class="fa fa-caret-down"></a>
				</div>

				<h2 class="panel-title">Enter Office Details</h2>
			</header>
			<div class="panel-body">
				<form class="form-horizontal form-bordered" method="post" name="officeform" id="officeform">
					<input type="hidden" id="id" value="0">
					<div class="form-group">
						<label class="col-md-3 control-label" for="ro_code">RO
							Code</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="ro_code" name="ro_code">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label" for="name">Name</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="name" name="name">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label" for="address">Address</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="address" name="address">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="office_level">Office
							Level</label>
						<div class="col-md-6">
							<select class="form-control input-sm mb-md" name="office_level" id="office_level">
								<option value="ho">Head</option>
								<option value="ro">Regional</option>
								<option value="br">Branch</option>
							</select>
						</div>
					</div>
					
					<footer class="panel-footer">
										<div class="row">
											<div class="col-sm-9 col-sm-offset-3">
												<button type="submit" class="btn btn-primary">Submit</button>
												<button type="reset" class="btn btn-default">Reset</button>
											</div>
										</div>
									</footer>



				</form>
			</div>
		</section>
	</div>
</div>
<ui:footer />
<script>
$( document ).ready(function() {
	  
	  // SUBMIT FORM
	    $("#officeform").submit(function(event) {
	    // Prevent the form from submitting via the browser.
	    
	    event.preventDefault();
	    var formData = {
				"id":$('#id').val(),
	        "ro_code" : $("#ro_code").val(),
	        "name" :  $("#name").val(),
	        "address" : $("#address").val(),
	        "office_level": $("#office_level").val()
	      };
	    var url="${pageContext.request.contextPath }/office";
	    ajaxPost(url, formData);
	  });
	
});
</script>