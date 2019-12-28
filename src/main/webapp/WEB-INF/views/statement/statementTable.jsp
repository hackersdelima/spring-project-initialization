<%--
  Created by IntelliJ IDEA.
  User: shishir
  Date: 9/4/19
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<ui:header />
<section class="panel">
	<header class="panel-heading">
		<div class="panel-actions">
			<a href="#" class="fa fa-caret-down"></a>
		</div>

		<h2 class="panel-title">Statement Table</h2>
	</header>
	<div class="panel-body">
		<table class="table table-bordered table-striped mb-none"
			id="statementTable">
			<thead>
				<tr>
					<th>ACCOUNT NUMBER</th>
					<th>ACCOUNT NAME</th>
					<th>BOOKING DATE ENG</th>
					<th>BOOKING DATE NEP</th>
					<th>VALUE DATE</th>
					<th>TRANSACTION ID</th>
					<th>DIGI TRANSACTION ID</th>
					<th>NARRATIVE</th>
					<th>DEBIT AMOUNT</th>
					<th>CREDIT AMOUNT</th>
					<th>BALANCE</th>
					<!-- <th>ACTION</th> -->
				</tr>
			</thead>
		</table>
	</div>
</section>
<ui:footer />
<script>
$(document).ready(function(){
	loaddatatable();
});
function loaddatatable(){
	var url="${pageContext.request.contextPath }/statement/";
	$.get(url, function(data, status){
	    var json=data.datas;
	    var table = $('#statementTable').DataTable({
	        "data":json,
	        "columns": [
	            {data:"accountNumber"},
	            {data:"accountName"},
	            {data:"bookingDateEn"},
	            {data:"bookingDateNp"},
	            {data:"valueDateEn"},
	            {data:"transactionId"},
	            {data:"digiTransactionId"},
	            {data:"narrative"},
	            {data:"debitAmount"},
	            {data:"creditAmount"},
	            {data:"balance"}
	  		],
	  		"destroy": true
	 	}); 
	  })        
	  .done(function (data) { })
      .fail(function (jqxhr,settings,ex) { alert('No Data Found!');
		  var table = $('#statementTable').DataTable();
      });
	}
	
</script>