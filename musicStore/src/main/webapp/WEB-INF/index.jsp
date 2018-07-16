<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>

<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<title>Music Shop</title>
<meta name="viewport" content="width=device-width, initial-scale=1">


<body class="w3-content" style="max-width:1200px">

<%-- Side Bar --%>
  <nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
    <myTag:sideBar/>
</nav>

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
  <div class="w3-bar-item w3-padding-24 w3-wide">LOGO</div>
  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

    <!-- !PAGE CONTENT! -->
    <div class="w3-main" style="margin-left:250px">

        <!-- Top header -->
        <header class="w3-container w3-xlarge">
            <p class="w3-right">
                <a href="cart" <i class="fa fa-shopping-cart w3-margin-right"></i> </a>
                <i class="fa fa-search"></i>
            </p>
        </header>

        <!-- Image header -->
        <div class="w3-display-container w3-container">
            <img src="http://www.prsguitars.com/images/content_images/silver_sky_home.jpg" alt="PRS Silver Sky"
            style="width:100%">
            <div class="w3-display-topleft w3-text-white" style="padding:24px 48px">
                <p><a href="products" class="w3-button w3-white w3-padding-large w3-large">SHOP NOW</a></p>
            </div>
        </div>

        <div class="w3-container w3-text-grey" id="jeans">
            <p></p>
        </div>

        <!-- Footer -->
        <footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
            <div class="w3-row-padding">
                <div class="w3-col s4">
                    <h4>Contact</h4>
                    <p>Questions? Go ahead.</p>
                    <form action="/action_page.php" target="_blank">    
                        <p><input class="w3-input w3-border" type="text" placeholder="Name" name="Name" required></p>
                        <p><input class="w3-input w3-border" type="text" placeholder="Email" name="Email" required></p>
                        <p><input class="w3-input w3-border" type="text" placeholder="Subject" name="Subject" required>
                        </p>
                        <p><input class="w3-input w3-border" type="text" placeholder="Message" name="Message" required>
                        </p>
                        <button type="submit" class="w3-button w3-block w3-black">Send</button>
                    </form>
                </div>
                <div class="w3-col s4 w3-justify">
                    <h4></h4>
                </div>

                <div class="w3-col s4 w3-justify">
                    <p><i class="fa fa-fw fa-map-marker"></i> Company Name</p>
                    <p><i class="fa fa-fw fa-phone"></i> 0044123123</p>
                    <p><i class="fa fa-fw fa-envelope"></i> ex@mail.com</p>
                    <br>
                    <i class="fa fa-facebook-official w3-hover-opacity w3-large"></i>
                    <i class="fa fa-instagram w3-hover-opacity w3-large"></i>
                    <i class="fa fa-twitter w3-hover-opacity w3-large"></i>
                </div>
            </div>
        </footer>

        <div class="w3-black w3-center w3-padding-24">Powered by <a href="https://www.w3schools.com/w3css/default.asp"
            title="W3.CSS" target="_blank"
            class="w3-hover-opacity">w3.css</a>
        </div>

        <!-- End page content -->
    </div>



<script src="resources/js/validation.js"></script>
<script src="resources/js/home.js"></script>
<script src="resources/js/jQueryValidation.js"></script>
<script src="resources/js/ajaxCaptcha.js"></script>
<script src="resources/js/animations.js"></script>


</body>
</html>