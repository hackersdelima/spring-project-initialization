<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ui:header />
<section class="panel">
	<header class="panel-heading">
		<div class="panel-actions">
			<a href="#" class="fa fa-caret-down"></a>
		</div>

		<h2 class="panel-title">User Details</h2>
	</header>
	<div class="panel-body">
		<table class="table table-bordered table-striped mb-none"
			id="userTable">
			<thead>
				<tr>
					<th>User name</th>
					<th>Status</th>
				</tr>
			</thead>
		</table>
	</div>
</section>

<ui:footer />
<script>
$(document).ready(function(){
	var url="${pageContext.request.contextPath }/users/specificUsers";
	$.get(url, function(data, status){
	    var json=data.datas;
	    var table = $('#userTable').DataTable({
	        "data":json,
	        "columns": [
	            {data:"username"},
	            {data:"status"},
	  		]
	 	}); 
	  })        
	  .done(function (data) { })
      .fail(function (jqxhr,settings,ex) { alert('No Data Found!'); 
      });
	  ;
});

</script>