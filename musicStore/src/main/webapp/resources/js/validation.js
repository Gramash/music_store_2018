    var passCheck = document.getElementById("pswCheck");
var emailCheck = document.getElementById("emailCheck");
var pass2Check = document.getElementById("psw2Check");
var nameCheck = document.getElementById("nameCheck");
var lastNameCheck = document.getElementById("lastNameCheck");

var checkArray = [passCheck, emailCheck, pass2Check, nameCheck, lastNameCheck];
var messagesArray = [document.getElementById("passinfo"), document.getElementById("emailinfo"), document.getElementById("psw2info"), 
document.getElementById("nameinfo"), document.getElementById("lastnameinfo")];


//check all fields for validity
document.getElementById("knopka").onclick = function(e){
    e.preventDefault();
    if(passCheck.classList.contains("valid") && emailCheck.classList.contains("valid") && pass2Check.classList.contains("valid") &&
        nameCheck.classList.contains("valid") && lastNameCheck.classList.contains("valid")){
        document.getElementById("signUpForm").submit();
} else {
    for (var i = 0; i <= 4 ; i++) {
       if(checkArray[i].classList.contains("invalid")){
        messagesArray[i].style.display = "block";
    }
}
}
}

var passField =document.getElementById("psw");
var repeatPassField = document.getElementById("psw2")
var emailField = document.getElementById("email");
var nameField = document.getElementById("name");
var lastNameField = document.getElementById("lastName");


//show requirement messages for each field
passField.onfocus = function(){
    document.getElementById("passinfo").style.display = "block";
}
passField.onblur = function(){
    document.getElementById("passinfo").style.display = "none";
}

emailField.onfocus = function(){
    document.getElementById("emailinfo").style.display = "block";
}
emailField.onblur = function(){
    document.getElementById("emailinfo").style.display = "none";
}

repeatPassField.onfocus = function(){
    document.getElementById("psw2info").style.display = "block";
}
repeatPassField.onblur = function(){
    document.getElementById("psw2info").style.display = "none";
}

nameField.onfocus = function() {
    document.getElementById("nameinfo").style.display = "block";
}
nameField.onblur = function() {
    document.getElementById("nameinfo").style.display = "none";
}

lastNameField.onfocus = function() {
    document.getElementById("lastnameinfo").style.display = "block";
}
lastNameField.onblur = function() {
    document.getElementById("lastnameinfo").style.display = "none";
}

//validate password
passField.onkeyup = validatePass;
function validatePass(){
    var passPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;

    if(passField.value.match(passPattern))   {
        passCheck.classList.remove("invalid");
        passCheck.classList.add("valid");
    } else {
        passCheck.classList.remove("valid");
        passCheck.classList.add("invalid");
    }
}

// validate email

$("#email")
.keyup(emailValidation)
.ready(emailValidation)


function emailValidation(){
    var emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
    if(emailField.value.match(emailPattern)){
        emailCheck.classList.remove("invalid");
        emailCheck.classList.add("valid");
    } else {
        emailCheck.classList.remove("valid");
        emailCheck.classList.add("invalid");
    }
}

// repeat password
repeatPassField.onkeyup = function() {
    if (repeatPassField.value == passField.value) {
        psw2Check.classList.remove("invalid");
        psw2Check.classList.add("valid");
    } else {
        psw2Check.classList.remove("valid");
        psw2Check.classList.add("invalid");
    }
}

var namePattern = /^[A-Z]([a-z]+)$/;
//validate Name


$("#name")
.keyup(nameValidation)
.ready(nameValidation);

function nameValidation(){
    if(nameField.value.match(namePattern)){
        nameCheck.classList.remove("invalid");
        nameCheck.classList.add("valid");
    } else{
        nameCheck.classList.remove("valid");
        nameCheck.classList.add("invalid");
    }
}

//last name validation
$("#lastName")
.keyup(lastNameValidation)
.ready(lastNameValidation);

function lastNameValidation(){
    if(lastNameField.value.match(namePattern)){
        lastNameCheck.classList.remove("invalid");
        lastNameCheck.classList.add("valid");
    } else{
        lastNameCheck.classList.remove("valid");
        lastNameCheck.classList.add("invalid");
    }
}