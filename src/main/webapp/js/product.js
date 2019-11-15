$(function(){
    $("#add-form").submit(function () {
        if(!checkEmpty(this)){
            return false;
        }
    });
    $(".delete-button").click(function () {
        return !!confirm("确实删除？");
    });
    $(".detail-btn").click(function () {
        $(this).parents("tr").next().toggle();
    });
    $("img.sm-image").mouseenter(function () {
        var bigImage = $(this).attr("big-image");
        $("#big-show").attr("src", bigImage);
        $("img.sm-image").removeClass("selected");
        $(this).addClass("selected")
    });
});

