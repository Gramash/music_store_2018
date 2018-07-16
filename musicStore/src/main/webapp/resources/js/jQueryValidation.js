$("#loginButton").click(function(e){
	e.preventDefault();
	if($("#checkemail").hasClass("valid") && $("#checkpass").hasClass("valid")){
		$("#signInForm").submit();
	} else {
		alert("Nope");
	}
});


//email validation

//hide requirements message when email input bar isn't active
$("#login").blur(function(){
	$("#emailmessage").hide();
});

//validate email
$("#login").keyup(function(){
	var emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
	$("#emailmessage").show();
	if(emailPattern.test($("#login").val())){
		$("#checkemail").removeClass("invalid").addClass("valid");
	} else {
		$("#checkemail").removeClass("valid").addClass("invalid");
	}
});

//password validation

//hide pass bar when inactive
$("#password").blur(function(){
	$("#passmessage").hide();
});
//validate pass input
$("#password").keyup(function(){
	var lowerCaseLetters = /[a-z]+/;
	var upperCaseLetters = /[A-Z]/;
	var numbers = /[0-9]/g;
	$("#passmessage").show();
	if($("#password").val().match(lowerCaseLetters) && upperCaseLetters.test($("#password").val()) &&
		$("#password").val().length>=8 && $("#password").val().match(numbers)) {
		$("#checkpass").removeClass("invalid").addClass("valid");
} else {
	$("#checkpass").removeClass("valid").addClass("invalid");
}
});



