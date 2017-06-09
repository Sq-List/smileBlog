$(function()
{
    $(".normal-button").click(function()
    {
        $(".smiley").addClass("normal", 500);
        $(".smiley").removeClass("happy", 500);
    });
});

$(function()
{
    $(".happy-button").click(function()
    {
        $(".smiley").addClass("happy", 500);
        $(".smiley").removeClass("normal", 500);
    });
});
