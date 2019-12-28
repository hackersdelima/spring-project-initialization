<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ui:header />
<section class="panel">
	<header class="panel-heading">
		<div class="panel-actions">
			<a href="#" class="fa fa-caret-down"></a>
		</div>

		<h2 class="panel-title">Office Details</h2>
	</header>
	<div class="panel-body">
		<table class="table table-bordered table-striped mb-none"
			id="officeTable">
			<thead>
				<tr>
					<th>RO CODE</th>
					<th>NAME</th>
					<th>ADDRESS</th>
					<th>OFFICE LEVEL</th>
					<th>Action</th>
				</tr>
			</thead>
		</table>
	</div>
</section>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
		<form class="form-horizontal form-bordered" id="officeform">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">EDIT OFFICE</h4>
			</div>
			
			<div class="modal-body">
				
					<input type="hidden" id="id" value="0">
					<div class="form-group">
						<label class="col-md-3 control-label" for="ro_code">RO
							Code</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="ro_code"
								name="ro_code">
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
							<input type="text" class="form-control" id="address"
								name="address">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="office_level">Office
							Level</label>
						<div class="col-md-6">
							<select class="form-control input-sm mb-md" name="office_level"
								id="office_level">
								<option value="ho">Head</option>
								<option value="ro">Regional</option>
								<option value="br">Branch</option>
							</select>
						</div>
					</div>
				
			</div>
			<div class="modal-footer">
				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-9 col-sm-offset-3">
							<a type="button" id="deletebtn" onclick="" class="btn btn-danger">Delete</a>
							<button type="submit" class="btn btn-primary">Submit</button>
							<button type="reset" class="btn btn-default">Reset</button>
							<button type="button" class="btn btn-info"
								data-dismiss="modal">Close</button>

						</div>
					</div>
				</footer>
			</div>
			</form>
		</div>

	</div>
</div>
<ui:footer />
<script>
$(document).ready(function(){
	loaddatatable();
});
function loaddatatable(){
	var url="${pageContext.request.contextPath }/office/";
	$.get(url, function(data, status){
	    var json=data.datas;
	    var table = $('#officeTable').DataTable({
	        "data":json,
	        "columns": [
	            {data:"ro_code"},
	            {data:"name"},
	            {data:"address"},
	            {data:"office_level"},
	            { "data": "Action",
                    "orderable": false,
                    "searchable": false,
                    "render": function(data,type,row,meta) { // render event defines the markup of the cell text 
                        var a = '<a class="modal-with-form btn btn-default" onclick="edit('+row.id+')"><i class="fa fa-edit"></i> EDIT</a>'; // row object contains the row data
                        return a;
                    }
                }
	            
	  		],
	  		"destroy": true
	 	}); 
	  })        
	  .done(function (data) { })
      .fail(function (jqxhr,settings,ex) { alert('No Data Found!'); 
      });
	}
	
//ON EDIT BUTTON CLICK
function edit(id){
	var url="${pageContext.request.contextPath }/office/"+id;
	$.get(url, function(data, status){
		var json=data.datas;
		$("#id").val(json.id);
		$("#ro_code").val(json.ro_code);
		$("#name").val(json.name);
		$("#address").val(json.address);
		$("#office_level").val(json.office_level);
		$("#deletebtn").attr("onclick","deletedata('"+url+"')");
		$("#myModal").modal('show');
	})        
	  .done(function (data) { })
    .fail(function (jqxhr,settings,ex) { alert('No Data Found!'); 
    });
	
}

//SUBMIT FORM
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

//RELOAD ON CLOSE MODAL
$("#myModal").on("hidden.bs.modal", function () {
    location.reload(true);
});



</script>