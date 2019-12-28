<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>
<ui:header/>
<section class="panel">
    <div class="panel-heading">
        <div class="panel-actions">
            <a href="#" class="fa fa-caret-down"></a>
        </div>

        <h2 class="panel-title">Search Statement</h2>
        <br>
    </div>
    <div class="panel-body">
        <div class="form-group">
            <div class="col-md-8">
                <form class="form-horizontal form-bordered"
                      id="searchStatementForm">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="accountnumber">
                            Account Number</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="accountnumber"
                                   name="accountnumber">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="datefrom">Date
                            From</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="datefrom"
                                   name="datefrom">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="dateto">Date To</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="dateto" name="dateto">
                        </div>
                    </div>
                    <br/>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-sm-9 col-sm-offset-3">
                                <input type="button" class="btn btn-primary" value="Search"
                                       id="searchbtn">
                            </div>
                        </div>
                    </footer>
                </form>
            </div>
        </div>

    </div>
</section>
<section class="panel">
    <header class="panel-heading">
        <div class="panel-actions">
            <a href="#" class="fa fa-caret-down"></a>
        </div>

        <h2 class="panel-title">Mini Statement Table</h2>
    </header>
    <div class="panel-body">
        <table class="table table-bordered table-striped mb-none"
               id="statementTable">
            <thead>
            <tr>
                <th>Booking Date</th>
                <th>Transaction Id</th>
                <th>Transaction Type</th>
                <th>Cheque No</th>
                <th>Amount</th>
                <th>Narrative</th>
            </tr>
            </thead>
        </table>
    </div>
</section>
<ui:footer/>
<script>
    $(document).ready(function () {
        $('#statementTable').DataTable();
    });
    $("#searchbtn").click(function () {
		loaddatatable();
	});

    function loaddatatable() {
        var accountNumber = $("#accountnumber").val();
        var dateFrom = $("#datefrom").val();
        var dateTo = $("#dateto").val();
        var param = "?accountNumber=" + accountNumber + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo;
        var url = "${pageContext.request.contextPath }/statements/telnetStatement"+param;
        $.get(url, function (data, status) {
            var json = data.datas;
            var table = $('#statementTable').DataTable({
                "data": json,
                "columns": [
                    {data: "bookingDateEn"},
                    {data: "transactionId"},
                    {data: "transactionType"},
                    {data: "chequeNo"},
                    {data: "amount"},
                    {data: "narrative"},
                ],
                "destroy": true
            });
        })
            .done(function (data) {
            })
            .fail(function (jqxhr, settings, ex) {
                alert('No Data Found!');
                var table = $('#statementTable').DataTable();
            });
    }
</script>