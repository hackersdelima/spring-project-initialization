<%--
  Created by IntelliJ IDEA.
  User: shishir
  Date: 9/4/19
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags"%>
<ui:header />
<div class="row">
	<div class="col-lg-12">
		<section class="panel">
			<header class="panel-heading">
				<div class="panel-actions">
					<a href="#" class="fa fa-caret-down"></a>
				</div>

				<h2 class="panel-title">Enter Transaction Details</h2>
			</header>
			<div class="panel-body">
				<form class="form-horizontal form-bordered">


					<div class="form-group">
						<label class="col-md-3 control-label" for="debitaccountnumber">Debit Account Number</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="debitaccountnumber" name="debitaccountnumber">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label" for="creditaccountnumber">Credit Account Number</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="creditaccountnumber" name="creditaccountnumber">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label" for="transactiondate">Transaction Date</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="transactiondate" name="transactiondate">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label" for="narrative">Narrative</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="narrative" name="narrative">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label" for="amount">Amount</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="amount" name="amount">
						</div>
					</div>
					<br />
					<footer class="panel-footer">
						<div class="row">
							<div class="col-sm-9 col-sm-offset-3">
								<button type="submit" class="btn btn-primary">Save
									Transaction</button>
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

		// SUBMIT Transaction FORM
		$("form").submit(function(event) {
			// Prevent the form from submitting via the browser.

			event.preventDefault();
			var formData = {
				"drAccountNo" : $("#debitaccountnumber").val(),
				"crAccountNo" : $("#creditaccountnumber").val(),
				"transactionDate" : $("#transactiondate").val(),
				"narrative" : $("#narrative").val(),
				"amount" : $("#amount").val()
			};
			var url = "${pageContext.request.contextPath }/transactions";
			ajaxPost(url, formData);
		});
	});
</script>
