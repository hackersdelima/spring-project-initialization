<%--
  Created by IntelliJ IDEA.
  User: shishir
  Date: 9/4/19
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>
<ui:header/>
<section class="panel">
    <header class="panel-heading">
        <div class="panel-actions">
            <a href="#" class="fa fa-caret-down"></a>
        </div>

        <h2 class="panel-title">Transactions &nbsp;&nbsp;&nbsp;<button type="button" id="currentUserDatasbtn" class="btn btn-primary">Current User Datas</button>&nbsp;&nbsp;&nbsp;<button type="button" id="allDatasbtn" class="btn btn-primary">All Datas</button></h2>
    </header>
    <div class="panel-body">
        <table class="table table-bordered table-striped mb-none"
               id="transactionDatatable">
            <thead>
            <tr>
                <th>Soft. Transaction Id</th>
                <th>Amount</th>
                <th>Cr Account No</th>
                <th>Dr Account No</th>
                <th>Narrative</th>
                <th>Transaction Date</th>
                <th>Inputter</th>
                <th>ACTION</th>
            </tr>
            </thead>
        </table>
    </div>
</section>
<ui:footer/>
<!-- Modal for transactions -->
<div id="transactionModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <form class="form-horizontal form-bordered" id="transctionform">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">TRANSACTION DETAIL</h4>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="digiTransactionId">Soft. Transaction Id</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="digiTransactionId" name="digiTransactionId"
                                   readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="drAccountNo">Debit Account Number</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="drAccountNo" name="drAccountNo">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="crAccountNo">Credit Account Number</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="crAccountNo" name="crAccountNo">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="transactionDate">Transaction Date</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="transactionDate" name="transactionDate">
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

                </div>
                <div class="modal-footer">
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-sm-9 col-sm-offset-3">

                                <button type="button" id="editbtn" class="btn btn-primary">Edit</button>
                                <button type="button" id="authorizebtn" class="btn btn-success">Authorize</button>
                                <a type="button" id="deletebtn" onclick="" class="btn btn-danger">Delete</a>
                                <button type="button" class="btn btn-info"
                                        data-dismiss="modal">Close
                                </button>

                            </div>
                        </div>
                    </footer>
                </div>
            </form>
        </div>

    </div>
</div>
<ui:footer/>
<script>
    $(document).ready(function () {
        var url = "${pageContext.request.contextPath }/transactions";
        loaddatatable(url);
    });

    function loaddatatable(url) {

        $.get(url, function (data, status) {
            var json = data.datas;
            var table = $('#transactionDatatable').DataTable({
                "data": json,
                "columns": [
                    {data: "digiTransactionId"},
                    {data: "amount"},
                    {data: "crAccountNo"},
                    {data: "drAccountNo"},
                    {data: "narrative"},
                    {data: "transactionDate"},
                    {data: "inputter"},
                    {
                        "data": "Action",
                        "orderable": false,
                        "searchable": false,
                        "render": function (data, type, row, meta) { // render event defines the markup of the cell text
                            var showOneTransaction = "showOneTransaction('" + row.digiTransactionId + "')";
                            var view = '<a class="modal-with-form btn btn-default input-sm" onclick="' + showOneTransaction + '"><i class="fa fa-edit"></i> View</a>';
                            var a = view; // row object contains the row data
                            return a;

                        },

                    }
                ],
                "destroy": true
            });
        })
            .done(function (data) {
            })
            .fail(function (jqxhr, settings, ex) {
                alert('No Data Found!');
                var table = $('#transactionDatatable').DataTable();
            });
    }

    //ON VIEW BUTTON CLICK
    function showOneTransaction(digiTransactionId) {
        var url = "${pageContext.request.contextPath }/transactions/" + digiTransactionId;
        var authorizeUrl = "${pageContext.request.contextPath }/transactions/authorize/" + digiTransactionId;
        $.get(url, function (data, status) {
            var json = data.datas;
            $("#digiTransactionId").val(json.digiTransactionId);
            $("#amount").val(json.amount);
            $("#crAccountNo").val(json.crAccountNo);
            $("#drAccountNo").val(json.drAccountNo);
            $("#narrative").val(json.narrative);
            $("#transactionDate").val(json.transactionDate);
            $("#deletebtn").attr("onclick", "deleteOneTransaction('" + url + "')");
            $("#authorizebtn").attr("onclick", "authorizeOneTransaction('" + authorizeUrl + "')");
            $("#transactionModal").modal('show');
        })
            .done(function (data) {
            })
            .fail(function (jqxhr, settings, ex) {
                alert('No Data Found!');
            });

    }

    $("#editbtn").click(function () {
        var formData = {
            "digiTransactionId": $("#digiTransactionId").val(),
            "drAccountNo": $("#drAccountNo").val(),
            "crAccountNo": $("#crAccountNo").val(),
            "transactionDate": $("#transactionDate").val(),
            "narrative": $("#narrative").val(),
            "amount": $("#amount").val()
        };
        var url = "${pageContext.request.contextPath }/transactions";
        ajaxPost(url, formData);
        loaddatatable(url);
    });

    function deleteOneTransaction(url) {
        deletedata(url);
    }

    function authorizeOneTransaction(url) {
        ajaxPostOnlyUrl(url);
    }

    $("#currentUserDatasbtn").click(function () {
      var url = "${pageContext.request.contextPath }/transactions/currentUserDatas";
       loaddatatable(url);
    });
    $("#allDatasbtn").click(function () {
        var url = "${pageContext.request.contextPath }/transactions";
        loaddatatable(url);
    });

</script>