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