$(document).ready(function () {
    $('.table-light').click(function () {
        var medicineId = $(this).data('medicine-id');
        $.ajax({
            url: '/principle/medicine/detail/' + medicineId,
            type: 'GET',
            success: function (response) {
                var medicine = response.medicine;
                $('#medicineId').val(medicine.thuocId);
                $('#medicineName').val(medicine.tenThuoc);
                $('#medicinePrice').val(medicine.donGia);
                // $('#medicineScrip').val(medicine.mieuTaThuoc);
                $('#editForm').show();
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    });
    $('#editForm').submit(function(event) {
        event.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            url: '/principle/medicine/update',
            type: 'POST',
            data: formData,
            success: function(response) {
                console.log(response);
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });
})