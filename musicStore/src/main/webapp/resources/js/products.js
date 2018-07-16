function showBrands(){
    if(!$('#brands').hasClass("w3-show")){
        localStorage.setItem("brands-drop-down", "opened")
    } else {
        localStorage.setItem("brands-drop-down", "closed");
    }
    $('#brands').toggleClass("w3-show");
    $('#brands-drop-down').toggleClass("fa-caret-down fa-caret-right");
}

function showGuitars(){
    if(!$('#guitars').hasClass("w3-show")){
        localStorage.setItem("guitars-drop-down", "opened")
    } else {
        localStorage.setItem("guitars-drop-down", "closed");
    }
    $('#guitars').toggleClass("w3-show");
    $('#guitars-drop-down').toggleClass("fa-caret-down fa-caret-right")
}

// keep drop down menu in the same condition in used to be 
$(document).ready(function(){
    var brandsDropDown = localStorage.getItem("brands-drop-down");
    var guitarsDropDown = localStorage.getItem("guitars-drop-down");
    if(brandsDropDown == "opened" && !$('#brands').hasClass("w3-show")){
        $('#brands').toggleClass("w3-show");
        $('#brands-drop-down').toggleClass("fa-caret-down fa-caret-right");
    }
    if(guitarsDropDown == "opened" && !$('#guitars').hasClass("w3-show")){
        $('#guitars').toggleClass("w3-show");
        $('#guitars-drop-down').toggleClass("fa-caret-down fa-caret-right");
    }
});

//set fields from parameters
$(document).ready(function(){
    var prices = ["lowerPrice", "upperPrice", "productName"];
    prices.forEach(function(el, index) {
        var results = new RegExp('[\?&]' + el + '=([^&#]*)').exec(window.location.href);
        var s = document.getElementById(el);
        s.value = parseInt(results[1]) || results[1];
    });
});

//check selected products per page and sort by
$(document).ready(function() {
    var results = new RegExp('[\?&]' + "productsCount" + '=([^&#]*)').exec(window.location.href);
    $("#sel1").val(results[1]);
    var results = new RegExp('[\?&]' + "sortBy" + '=([^&#]*)').exec(window.location.href);
    var result = results[1].replace("+"," ");
    $("#sort").val(result);
});

function getparamNameMultiValues(paramName, paramValue){
    var sURL = window.document.URL.toString();
    var value =[];
    if (sURL.indexOf("?") > 0){
        var arrParams = sURL.split("?");
        var arrURLParams = arrParams[1].split("&");
        for (var i = 0; i<arrURLParams.length; i++){
            var sParam =  arrURLParams[i].split("=");
            console.log(sParam);
            if(sParam){
                if(sParam[0] == paramName && sParam[0] == paramValue){
                    if(sParam.length>0){
                        value.push(sParam[1].trim());
                    }
                }
            }
        }
    }
    return value.toString();
}




