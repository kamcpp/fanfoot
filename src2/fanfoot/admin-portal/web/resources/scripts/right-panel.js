$(document).ready(function () {
    $(".right-panel-item").mouseenter(function () {
        $(this).find("a").css("color", "seagreen");
        $(this).css("background-color", "darkseagreen");
        $(this).css("cursor", "pointer");
    }).mouseleave(function () {
        $(this).find("a").css("color", "white");
        $(this).css("background-color", "seagreen");
    });
});