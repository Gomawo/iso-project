$(document).ready(function() {
    const $urlInput = $("#websiteUrl");
    const $fileInput = $("#fileExcel");
    const $submitButton = $(".btn-generate");

    $urlInput.on("input", function() {
        if ($urlInput.val().trim() !== "") {
            $submitButton.prop("disabled", false);
        } else {
            $submitButton.prop("disabled", true);
        }
    });

    $fileInput.on("input", function() {
        if ($fileInput.prop("files") && $fileInput.prop("files").length > 0) {
            $submitButton.prop("disabled", false);
        }
    });
});