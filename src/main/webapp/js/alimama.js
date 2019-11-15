$(function () {
    var stock = $("#inventory").attr("stock");
    $("#button-1").click(function () {
        $(this).addClass("selected");
        $("#button-2").removeClass("selected");
        $("#main-2").hide();
        $("#main-1").show();
    });
    $("#button-2").click(function () {
        $(this).addClass("selected");
        $("#button-1").removeClass("selected");
        $("#main-1").hide();
        $("#main-2").show();
    });
    $("img.sm-image").mouseenter(function () {
        var bigImage = $(this).attr("big-image");
        $("#big-show").attr("src", bigImage);
        $("img.sm-image").removeClass("selected");
        $(this).addClass("selected")
    });
});

