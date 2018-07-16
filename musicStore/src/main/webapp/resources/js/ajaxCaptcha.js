$(document).ready(function () {
    $("#changeCaptcha").click(function () {
            var id = generateRandom();
            $("#captchaImg").prop("src", "captcha?captchaID=" + id);
            $('#captchaID').attr('value', id)
        })
});

function generateRandom() {
    return '_' + Math.random().toString(36).substr(2, 11) + Math.random().toString(36).substr(2, 11) ;
  };

    