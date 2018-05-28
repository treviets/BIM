$().ready(function(){
    $('[data-toggle="tooltip"]').tooltip();

    // collapse for call-for-tender
    $('.collapse').on('shown.bs.collapse', function(){
        $(this).parent().find(".glyphicon-menu-down").removeClass("glyphicon-menu-down").addClass("glyphicon-menu-up");
    })
    $('.collapse').on('hidden.bs.collapse', function(){
        $(this).parent().find(".glyphicon-menu-up").removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
    });

    // file upload in call for tender
    $(document).on('change', ':file', function() {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [numFiles, label]);
    });

    $(document).ready( function() {
        $(':file').on('fileselect', function(event, numFiles, label) {
            var input = $(this).parents('.input-group').find(':text'),
                log = numFiles > 1 ? numFiles + ' files selected' : label;
            if(input.length ) {
                input.val(log);

            }
        });
    });

    /*window.setTimeout(function () {
        $(".alert-info").fadeTo(500, 0).slideUp(500, function () {
            $(this).alert('close');
        });
    }, 5000);

    window.setTimeout(function () {
        $(".alert-danger").fadeTo(500, 0).slideUp(500, function () {
            $(this).alert('close');
        });
    }, 5000);*/

});
