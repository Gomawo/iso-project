(function ($) {
    $(function () {
        var pb = $(".progress-bar");
        var ps = $(".percentage");
        var iem = $(".import-error-message");
        var _ip = $(".import-progress");
        var isProcessingBefore = false;
        var lastMessage = "";
        var percentage = 0;
        var timeOutCntr;

        var getImportStatus = function(checkStatusUrl) {
            $.get( checkStatusUrl, function( data ) {
                if (data.isProcessing=true) {
                    isProcessingBefore = true;

                    if (data.currentRow && data.currentRow > 0) {
                        var percentage = data.totalRows ? (data.currentRow/data.totalRows) * 100 : 0;
                        if (percentage < 10) percentage = 10;
                        pb.prop("style", "width:"+percentage+"%");
                        ps.html(data.currentRow + " of " + data.totalRows + " imported.");
                    } else {
                        pb.prop("style", "width:10%");
                        ps.html("Processing data...");
                    }

                    clearTimeout(timeOutCntr);
                    timeOutCntr = setTimeout(function() {
                        getImportStatus(checkStatusUrl);
                    }, 3000);
                } else {
                    if (isProcessingBefore) {
                        isProcessingBefore = false;
                        pb.prop("style", "width:100%");
                        ps.html("Import data completed");
                        clearTimeout(timeOutCntr);
                    }
                }

                if (data.errorMessage && lastMessage !== data.errorMessage) {
                    iem.append(data.errorMessage + "\n");
                    lastMessage = data.errorMessage;
                }
            }, "json" );
        };

        var $excelImportForm = $(".js-excel-upload__form");
        if ($excelImportForm.length > 0){
            getImportStatus($excelImportForm.data("status-url")); // First landing check
        }
        $(".btn-generate").click(function() {
            _ip.show();
        });
    });
})(jQuery);