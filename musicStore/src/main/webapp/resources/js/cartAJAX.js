//add to cart 
function addToCart(productId, price, product){
    $.ajax
        ({
            type: "POST",           
            data: 'productId='+ productId + '&price='+ price + "&product=" + product,
            url: "cart",
        success:function(content){
                $('#center').html(content);           
            }           
        });
        totalPrice(price)
}

//set total price for product page
function totalPrice(price) {
    var currPrice = $("#cartPrice").text();
    var temp = parseFloat(currPrice) || 0.0;
    var result = parseFloat(price) + parseFloat(temp);
    cartPrice = cartPrice + result || 0.0;
    $("#cartPrice").text(result.toFixed(1) + '$');
}

var oldValue;
//patch cart
function patchCart (id, value, price) {
    tempVal = value;
    var id2 = id;
    $.ajax
        ({
            type: "PUT",           
            data: 'productId=' + id + "&count=" + value,
            url: "cart",
        success:function(content){
                $('#center').html(content);           
            }           
        });
        setPriceCartPage (price, oldValue, value);
        oldValue=value;
}

//remove from cart
function removeFromCart(id, price){
    var count = document.getElementById('count'+id).value;
    $.ajax({
        type: "DELETE",
        data: 'productId=' + id,
        url: 'cart',
        success:function(content){
            $("#center").html(content);
        }
    });
    var currentTotal = $('#totalPrice').text();
    var newTotal = parseFloat(currentTotal) - price*count;
    $('#totalPrice').text(newTotal+'$');
    $('#' + id ).fadeOut("slow", function(){
        document.getElementById(id).remove();
       $('#'+'hz'+id).remove();
    });
    
}

//remove 

//get previous product count
function getOldVal(value){
    oldValue = value;
}

//calculate total sum for cart page onload
function getTotalSumm (price){
    var total = price || 0.0;
    $("#totalPrice").text(total + '$');
}

//calculates price for cart page    
function setPriceCartPage (price, oldValue, currentValue) {
    var modalValue = currentValue - oldValue;
    var price = price;
    var current = $('#totalPrice').text();
    var temp1 = parseFloat(price) * modalValue;
    var temp2 = parseFloat(current)
    var result = temp1 + temp2;
    $('#totalPrice').text(result + '$');
}


window.onscroll = function() {myFunction()};


var summaryPrice = document.getElementById("summary");


var sticky = summaryPrice.offsetTop;

function myFunction() {
  if (window.pageYOffset >= sticky) {
    summaryPrice.classList.add("sticky1")
  } else {
    summaryPrice.classList.remove("sticky1");
  }
}


//open modal
$(document).ready(function (){
    var modalParam = getUrlParameter("modal");
    if (modalParam == "open"){
        $('#myModal').toggleClass("in");
        $('#myModal').show();
        $(document.body).toggleClass('modal-open')
    }
    modalParam = getUrlParameter("order");
    if (modalParam == "success"){
        $('#myModal3').toggleClass("in");
        $('#myModal3').show();
        $(document.body).toggleClass('modal-open')
    }
}); 
//close success order modal
function closeModal3(){
    $("#myModal3").hide();
}
//close login modal
function closeModal(){
    $("#myModal").hide();
}
//get param by name
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};


