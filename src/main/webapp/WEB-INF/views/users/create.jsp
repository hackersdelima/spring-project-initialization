<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<ui:header />
<div class="row">
	<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading">
				<div class="panel-actions">
					<a href="#" class="fa fa-caret-down"></a>
				</div>

				<h2 class="panel-title">Create User</h2>
			</header>
			<div class="panel-body">
				<form class="form-horizontal form-bordered" method="post" name="userform" id="userform">
					<input type="hidden" id="id" value="0">
					<div class="form-group">
						<label class="col-md-3 control-label" for="staffid">Staff ID</label>
						<div class="col-md-6">
							<select class="form-control" id="staffid" name="staffid" required>
							<c:forEach items="${staffs }" var="s">
							<option value="${s.code }">${s.code }-${s.firstName } ${s.lastName }</option>
							</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label" for="uname">User Name</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="uname" name="uname">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label" for="uname">Roles</label>
						<div class="col-md-6">
							<select class="form-control" id="roles" name="roles" multiple>
								<option value="ADMIN">ADMIN</option>
								<option value="INPUTTER">INPUTTER</option>
								<option value="AUTHORIZER">AUTHORIZER</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label" for="password">Password</label>
						<div class="col-md-6">
							<input type="password" class="form-control" id="password" name="password">
						</div>
					</div>
					
					 <div class="form-group">
						<label class="col-md-3 control-label" for="repass">Confirm Password</label>
						<div class="col-md-6">
							<input type="password" class="form-control" id="repass" name="repass">
						</div>
					</div>
					
					<br />
					
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
$(document).ready(function() {
	$('#staffid').select2();
	$('#roles').select2();
	  
	  // SUBMIT FORM
	    $("#userform").submit(function(event) {
	    // Prevent the form from submitting via the browser.
	    event.preventDefault();
	    var password=$('#password').val();
	    var repass=$('#repass').val();
	    //pass values only when the passwords match
	    
	    
	    if(password==repass){
	    	//get multiple values from roles select option
	    	var roles_list=$("#roles").val();
		    var roles_length=roles_list.length;
	    	var array=[];
	    	
	    	//convert roles selected values to json array object
	    	$(roles_list).each(function(index, element){
	    		var data={"id":element};
	    		array.push(data);
	    	});
	    	
	    var formData = {
	        "username" : $("#uname").val(),
	        "name" :  $("#name").val(),
	        "password" : password,
	        "confirmpassword" : repass,
	        "status" : true,
	        "staffs":{
	        	"code":$('#staffid').val()
	        }
	      };
	    formData['roles']=array;
	    //formDara.roles=array;
	    var url="${pageContext.request.contextPath }/users";
	    ajaxPost(url, formData);
	     }
	    else{
	    	alert('Password Mismatch!');
	    } 
	  });
	
});
</script> 
