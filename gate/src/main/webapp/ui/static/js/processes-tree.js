var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var selectedNode = null;
var canEdit = false;
var modeEdit = "edit";
var modeAdd = "add";
var mode = modeEdit;
var isSure = false;

function getProcessesForAddProcess(user) {
    $('#processes').bind('ready.jstree', function (e, data) {
        $("#btn-add").show();
    }).jstree({
        'core': {
            'data': {
                url: "/api/process/all/admin",
                'data': function (node) {
                    return {'id': node.id};
                }
            },
            "multiple": false,
            "themes": {
                "variant": "large"
            },
            "plugins": ["wholerow"]
        }
    }).on('activate_node.jstree', function (e, data) {
        if (selectedNode !== null && selectedNode.id === data.node.original.id) {
            reset(data);
            return;
        }
        selectedNode = data.node.original;
        if (data.node.original.primitive) {
            getInfo("/api/process/process/" + selectedNode.id);
            $("#btn-add").prop("disabled", false);
            $("#btn-add").text("Add activity");
        } else if (data.node.original.activity) {
            getInfo("/api/process/activity/" + selectedNode.id);
            $("#btn-add").prop("disabled", true);
        } else {
            getInfo("/api/process/process/" + selectedNode.id);
            $("#btn-add").text("Add process");
            $("#btn-add").prop("disabled", false);
        }
    });
}

function getInfo(url) {
    $.ajax({
        type: "GET",
        url: url,
        // beforeSend: function (request) {
        //     request.setRequestHeader(header, token);
        // },
        dataType: 'json',
        success: function (data) {
            $('#id').val(data.id);
            $('#name').val(data.name);
            canEdit = false;
            if (selectedNode !== null && selectedNode.activity) {
                $("#form-primitive").hide();
                $("#input_document_types > option").attr("selected", false);
                for (var i = 0; i < data.inputListDocumentTypes.length; i++) {
                    $("#input_document_types").find("option[value=" + data.inputListDocumentTypes[i].id + "]").prop("selected", "selected");
                }
                $("#output_document_types > option").attr("selected", false);
                for (var i = 0; i < data.outputListDocumentTypes.length; i++) {
                    $("#output_document_types").find("option[value=" + data.outputListDocumentTypes[i].id + "]").prop("selected", "selected");
                }
                $("#form_output_document_types").show();
                $("#form_input_document_types").show();
            } else {
                $("#form_output_document_types").hide();
                $("#form_input_document_types").hide();
                $("#form-primitive").show();
                $('#primitive').prop("checked", data.primitive);
            }
            $("#register_form").show();
            disableForm();
            $("#message-box-container").hide();
            $('#info').show();
        },
        error: function (textStatus, errorThrown) {
            alert(textStatus);
        }
    });
}

function checkData() {
    if (mode === modeEdit) {
        $("#btn-edit").prop("type", "button");
        if (!canEdit) {
            $('#name').prop("disabled", false);
            $('#primitive').prop("disabled", false);
            $("#input_document_types").prop("disabled", false);
            $("#output_document_types").prop("disabled", false);
//            $("#primitive").prop("disabled", true);
            canEdit = true;
            $("#btn-edit").text("Save");
        } else {
            var params = {};
            if (!isSure && !selectedNode.activity && !selectedNode.primitive && $("#primitive").prop('checked')) {
                showPopUp("Setting process to primitive will delete all child nodes of this process, are you sure?");
            } else if (!isSure && !selectedNode.activity && selectedNode.primitive && !$("#primitive").prop('checked')) {
                showPopUp("Setting process to non primitive will delete all documents from activities, are you sure?");
            } else {
                canEdit = false;
                if (selectedNode.activity) {
                    //     var selected = [];
                    //     $('#input_document_types :selected').each(function () {
                    //         selected[$(this).val()] = $(this).val();
                    //     });
                    // editActivity("/api/process/activity/" + selectedNode.id);
                } else {
                    //     params.primitive = $("#primitive").prop('checked');
                    editProcess("/api/process/process/" + selectedNode.id);
                }
            }
        }
    } else if (mode === modeAdd) {
        // $("#isActivity").val(selectedNode !== null ? selectedNode.primitive : false);
        // $("#parent").val(selectedNode !== null ? selectedNode.id : null);
        if (selectedNode != null && selectedNode.primitive) {
            addActivity();
        } else {
            addProcess();
        }
    }
}

function addProcess() {
    $.ajax({
        type: "POST",
        url: "/api/process/process",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify({
            name: $("#name").val(),
            user: "admin",
            parentId: selectedNode !== null ? selectedNode.id : null,
            primitive: $("#primitive").prop('checked')
        }),
        // beforeSend: function (request) {
        //     request.setRequestHeader(header, token);
        // },
        success: function (data) {
            $("#register_form").hide();
            $('#processes').jstree(true).refresh();
        },
        error: function (textStatus, errorThrown) {
            alert(textStatus);
        }
    });
}

function addActivity() {
    $.ajax({
        type: "POST",
        url: "/api/process/activity",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify({
            name: $("#name").val(),
            processId: selectedNode !== null ? selectedNode.id : null,
            inputListDocumentTypes: null,
            outputListDocumentTypes: null
        }),
        // beforeSend: function (request) {
        //     request.setRequestHeader(header, token);
        // },
        success: function (data) {
            $('#processes').jstree(true).refresh();
        },
        error: function (textStatus, errorThrown) {
            alert(textStatus);
        }
    });
}

function showPopUp(text) {
    $("#modal-question-text").text(text);
    $("#modal").modal({
        show: true,
        backdrop: 'static',
        keyboard: false
    });
}

function sendRequest() {
    isSure = true;
    $('#modal').modal('hide');
    checkData();
}

function closeModal() {
    isSure = false;
    $('#modal').modal('hide');
}

function editProcess(url) {
    $.ajax({
        type: "PUT",
        url: url,
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify({
            name: $("#name").val(),
            user: "admin",
            parentId: selectedNode !== null ? selectedNode.id : null,
            primitive: $("#primitive").prop('checked')
        }),
        // beforeSend: function (request) {
        //     request.setRequestHeader(header, token);
        // },
        success: function (data) {
            canEdit = false;
            isSure = false;
            disableForm();
            $('#processes').jstree(true).refresh();
            selectedNode.name = $("#name").val();
            if (!selectedNode.activity) {
                selectedNode.primitive = $("#primitive").prop('checked');
                if (selectedNode.primitive) {
                    $("#btn-add").prop("disabled", false);
                } else {
                    $("#btn-add").prop("disabled", false);
                }
            } else {
                $("#btn-add").prop("disabled", true);
            }
        },
        error: function (textStatus, errorThrown) {
            alert(textStatus);
        }
    });
}

function add() {
    mode = modeAdd;
    showFormForAdding();
}

function reset(data) {
    data.instance.deselect_node(data.node, true);
    selectedNode = null;
    $('#id').val(selectedNode);
    $('#info').hide();
    $("#btn-add").prop("disabled", false);
}

function disableForm() {
    $("#name").prop("disabled", true);
    $("#input_document_types").prop("disabled", true);
    $("#output_document_types").prop("disabled", true);
    $("#primitive").prop("disabled", true);
    $("#btn-edit").text("Edit");
    hideErrorForName();
    mode = modeEdit;
}

function showMessage(data, messageType) {
    $("#message-box").removeClass("alert-success");
    $("#message-box").removeClass("alert-danger");
    $("#message-box").addClass(messageType);
    $("#message-text").html(data);
    $("#message-box-container").show();
}

function showFormForAdding() {
    hideErrorForName();
    $("#name").prop("disabled", false);
    $("#name").val("");
    if (selectedNode !== null && selectedNode.primitive) {
        $("#input_document_types").prop("disabled", false);
        $("#output_document_types").prop("disabled", false);
        $("#form-primitive").hide();
        $("#form_output_document_types").show();
        $("#form_input_document_types").show();
        $("#form_input_document_types option:selected").removeAttr("selected");
        $("#form_output_document_types option:selected").removeAttr("selected");
    } else {
        $("#form-primitive").show();
        $("#form_output_document_types").hide();
        $("#form_input_document_types").hide();
        $("#primitive").prop("disabled", false);
        $('#primitive').prop("checked", false);
    }
    $("#btn-edit").text("Add");
    $('#info').show();
}

function hideErrorForName() {
    if ($("label[for='name']").hasClass("error")) {
        $("label[for='name']").eq(1).hide();
        $("label[for='name']").eq(1).removeClass("error");
        $("#name").removeClass("error");
    }
}