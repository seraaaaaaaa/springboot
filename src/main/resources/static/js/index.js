$(document).ready(function () {

    // Add task 
    $('#taskForm').on('submit', function (e) {
        e.preventDefault();
        var formData = {
            name: $('#name').val(),
            description: $('#description').val(),
            status: $('#status').val()
        };

        $.ajax({
            url: '/task/add',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                if (response == 'OK') {
                    refreshTable();
                    toastr.success("Task Added");

                    $('#taskForm')[0].reset();
                } else {
                    toastr.error('Failed to add task');
                }
            },
            error: function (response) {
                toastr.error('Error adding task');
            }
        });
    });

    //Update task status
    $(document).on('change', 'select[name="updateStatus"]', function () {
        var taskId = $(this).data('id'); // Get task ID
        var newStatus = $(this).val();   // Get the selected status

        $.ajax({
            url: '/task/updateStatus/' + taskId,
            type: 'POST',
            data: {
                status: newStatus,
            },
            success: function (response) {
                if (response == 'OK') {
                    refreshTable();
                    toastr.success("Status Updated");
                } else {
                    toastr.error('Failed to update task status');
                }
            },
            error: function (response) {
                toastr.error('Error updating task status');
            }
        });
    });

    // Delete task
    $(document).on('click', '.deleteTask', function () {
        var taskId = $(this).data('id');

        $.ajax({
            url: '/task/delete/' + taskId,
            type: 'DELETE',
            success: function (response) {
                if (response == 'OK') {
                    refreshTable();
                    toastr.success("Task Deleted");
                } else {
                    toastr.error('Failed to delete task');
                }
            },
            error: function (response) {
                toastr.error('Error deleting task');
            }
        });
    });


    function refreshTable() {
        $.get("/task/refresh", function (fragment) {
            $('#taskPanel').replaceWith(fragment);
        });
    }

});      