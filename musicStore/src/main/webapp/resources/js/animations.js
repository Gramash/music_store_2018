$("#changeCaptcha").click(function(e){
    e.preventDefault();
})

$("#changeCaptcha").mouseenter(function(){
    $("#refresh-icon").addClass("w3-spin");
});

$("#changeCaptcha").mouseleave(function(){
    $("#refresh-icon").removeClass("w3-spin");
});