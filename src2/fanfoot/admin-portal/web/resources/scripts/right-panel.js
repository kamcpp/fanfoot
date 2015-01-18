$(document).ready(function () {
    $("#right-panel").css("height", getDocumentHeight());
    $(".right-panel-item").mouseenter(function () {
        $(this).find("a").css("color", "white");
        $(this).css("background-color", "#282828");
        $(this).css("cursor", "pointer");
    }).mouseleave(function () {
        $(this).find("a").css("color", "white");
        $(this).css("background-color", "black");
    });
});