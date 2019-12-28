<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:header/>
<div class="row">
    <div class="col-lg-12">
        <section class="panel">
            <header class="panel-heading">
                <div class="panel-actions">
                    <a href="#" class="fa fa-caret-down"></a>
                </div>

                <h2 class="panel-title">Enter Staff Details</h2>
            </header>
            <div class="panel-body">
                <form class="form-horizontal form-bordered" method="post"
                      id="staffform">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="code"> Code</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="code" name="code">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="fname">First
                            Name</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="fname" name="fname">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="lname">Last
                            Name</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="lname" name="lname">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="gender">Gender</label>
                        <div class="col-md-6">
                            <select class="form-control" id="gender" name="gender">
                                <option value="">Select Gender</option>
                                <option value="m">Male</option>
                                <option value="f">Female</option>
                                <option value="o">Other</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="phone">Phone
                            Number</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="phone" name="phone">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="post">Post</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="post" name="post">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="office">Office</label>
                        <div class="col-md-6">
                            <select class="form-control" id="office" name="office">
                                <option value="1">Select Office</option>
                                <c:forEach items="${offices }" var="o">
                                    <option value="${o.id }">${o.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br/>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-sm-9 col-sm-offset-3">
                                <button type="submit" class="btn btn-primary">Save
                                    Staffs
                                </button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </div>
                        </div>
                    </footer>
                </form>
            </div>
        </section>
    </div>
</div>

<%--<!-- import excel file -->
<div class="row">
	<form action="<c:url value="/staffs/import"/>" method="post"
		enctype="multipart/form-data" target="_blank">

		<input type="file" name="file"> <input type="submit"
			value="Import" />
	</form>--%>

<div class="row">
    <div class="col-lg-12">
        <section class="panel">
            <div class="panel-body">
                <form id="imgform">
                    <div class="form-group">
                        <label class="col-md-3 control-label" for="pic">Upload Image</label>
                        <div class="col-md-6">
                            <input type="file" class="form-control" id="pic" name="pic">
                        </div>
                    </div>
                    <br>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-sm-9 col-sm-offset-3">
                                <button type="submit" class="btn btn-primary">Upload</button>
                            </div>
                        </div>
                    </footer>
                </form>
            </div>
        </section>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <section class="panel">
            <header class="panel-heading">
                <div class="panel-actions">
                    <a href="#" class="fa fa-caret-down"></a>
                </div>

                <h2 class="panel-title">Enter Staffs Family Details</h2>
            </header>
            <div class="panel-body">
                <form class="form-horizontal form-bordered" method="post"
                      id="familyform">
                    <input type="hidden" id="fid" value="0">

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="ffname">First
                            Name</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="ffname" name="ffname">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="flname">Last
                            Name</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="flname" name="flname">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="fgender">Gender</label>
                        <div class="col-md-6">
                            <select class="form-control" id="fgender" name="fgender">
                                <option value="">Select Gender</option>
                                <option value="m">Male</option>
                                <option value="f">Female</option>
                                <option value="o">Other</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="fphone">Phone
                            Number</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="fphone" name="fphone">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="foccupation">Occupation</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="foccupation"
                                   name="foccupation">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label" for="relation">Relation</label>
                        <div class="col-md-6">
                            <select class="form-control" id="relation" name="relation">
                                <option value="">Select Relation</option>
                                <option value="father">Father</option>
                                <option value="mother">Mother</option>
                                <option value="sibling">Sibling</option>
                                <option value="grand father">Grand Father</option>
                                <option value="grand mother">Grand Mother</option>
                                <option value="spouse">Spouse</option>
                            </select>
                        </div>
                    </div>

                    <br/>
                    <footer class="panel-footer">
                        <div class="row">
                            <div class="col-sm-9 col-sm-offset-3">
                                <button type="submit" class="btn btn-primary">Save
                                    Family
                                </button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </div>
                        </div>
                    </footer>
                </form>
            </div>
        </section>
    </div>
</div>

<ui:footer/>
<!-- manage script to save values -->
<script>
    $(document).ready(function () {

        // SUBMIT First FORM
        $("#staffform").submit(function (event) {
            // Prevent the form from submitting via the browser.

            event.preventDefault();
            var formData = {
                "code": $('#code').val(),
                "firstName": $("#fname").val(),
                "lastName": $("#lname").val(),
                "gender": $("#gender").val(),
                "phoneNumber": $("#phone").val(),
                "post": $("#post").val(),
                "office": {
                    "id": $("#office").val()
                }
            };
            var url = "${pageContext.request.contextPath }/staffs";
            ajaxPost(url, formData);
        });

        //submit second form
        $("#familyform").submit(function (event) {
            // Prevent the form from submitting via the browser.

            event.preventDefault();
            var formData = {
                "id": $('#id').val(),
                "firstName": $('#ffname').val(),
                "lastName": $('#flname').val(),
                "gender": $("#fgender").val(),
                "occupation": $('#foccupation').val(),
                "phoneNumber": $('#fphone').val(),
                "relation": $('#relation').val(),
                "staffs": {
                    "code": $('#code').val()
                }
            };
            var url = "${pageContext.request.contextPath }/staffsFamily";
            ajaxPost(url, formData);
        });
    });

    //handle image upload separately
    //change file to base64 and send JSON
    $("#imgform").submit(function (event) {
        event.preventDefault();
        var data = new FormData();
        var json = {
            "code": $('#code').val()
        };
        var jsonData = JSON.stringify(json);
        data.append("file", $("input[name=pic]")[0].files[0]);
        data.append("jsondata", jsonData);
        url = "${pageContext.request.contextPath }/staffs/image";
        imagePost(url, data);
    });
</script>