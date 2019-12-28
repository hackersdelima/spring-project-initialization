<!-- Vendor -->
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery/jquery.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/nanoscroller/nanoscroller.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/magnific-popup/magnific-popup.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
		
		<!-- Specific Page Vendor -->
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-ui-touch-punch/jquery.ui.touch-punch.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-appear/jquery.appear.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-easypiechart/jquery.easypiechart.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/flot/jquery.flot.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/flot-tooltip/jquery.flot.tooltip.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/flot/jquery.flot.pie.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/flot/jquery.flot.categories.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/flot/jquery.flot.resize.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-sparkline/jquery.sparkline.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/raphael/raphael.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/morris/morris.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/gauge/gauge.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/snap-svg/snap.svg.js"></script>
		<!-- Specific Page Vendor -->
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/select2/select2.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>
		
		
		<!-- Theme Base, Components and Settings -->
		<script src="${pageContext.request.contextPath }/resources/assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="${pageContext.request.contextPath }/resources/assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="${pageContext.request.contextPath }/resources/assets/javascripts/theme.init.js"></script>


		<!-- Examples -->
		<script src="${pageContext.request.contextPath }/resources/assets/javascripts/dashboard/examples.dashboard.js"></script>
		<!-- Examples -->
		<script src="${pageContext.request.contextPath }/resources/assets/javascripts/tables/examples.datatables.editable.js"></script>
		<script>
			function ajaxPost(url, formData) {
				// PREPARE FORM DATA
				$.ajax({
					url: url,
					method: "post",
					data: JSON.stringify(formData),
					contentType: "application/json",
					beforeSend: function () {
						return confirm("Are you sure?");
					},
					success: function (data) {
						alert('message:' + data.message);
					},
					error: function (err) {
						alert(err.status);
					}
				});
			}

			function ajaxPostOnlyUrl(url) {
				$.ajax({
					url: url,
					method: "post",
					contentType: "application/json",
					beforeSend: function () {
						return confirm("Are you sure?");
					},
					success: function (data) {
						alert('message:' + data.message);
					},
					error: function (err) {
						alert(err.status);
					}
				});
			}

			function imagePost(url, data) {

				// PREPARE FORM DATA

				$.ajax({
					url: url,
					method: "post",
					data: data,
					enctype: "multipart/form-data",
					processData: false,
					contentType: false,
					timeout: 600000,
					cache: false,
					beforeSend: function () {
						return confirm("Are you sure?");
					},
					success: function (data) {
						alert('message:' + data.message);
					},
					error: function (err) {
						alert(err.status);
					}
				});
			}


			//DELETE DATA

			function deletedata(url) {
				$.ajax({
					url: url,
					type: 'DELETE',
					beforeSend: function () {
						return confirm("Are you sure?");
					},
					success: function (data) {
						alert(data.message);
						location.reload(true);
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						alert("Status: " + textStatus);
					}
				});
			}

		</script>