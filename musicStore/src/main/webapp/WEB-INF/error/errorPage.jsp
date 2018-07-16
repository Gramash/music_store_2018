<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>Simple Error Page</title>
	<link href='//fonts.googleapis.com/css?family=Source+Sans+Pro:400,200italic,200,300,300italic,400italic,600,600italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
	<!-- For-Mobile-Apps-and-Meta-Tags -->
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	    <link rel="stylesheet" href="/resources/css/error.css">

</head>
<body>
	<div class="main w3l">
		<h2>OOPS</h2>
		<h1> ${requestScope['javax.servlet.error.status_code']}</h1>
		<h3>${pageContext.exception.message}</h3>
		<a href="${pageContext.request.contextPath}/home" class="back">BACK TO HOME</a>
		<div class="social-icons w3">
			<ul>
				<li><a class="twitter" href="#"></a></li>
				<li><a class="facebook" href="#"></a></li>
				<li><a class="pinterest" href="#"></a></li>
			</ul>
		</div>
		<div class="footer agileits">
			<p>Copyright © 2016 Simple Error Page. All Rights Reserved | Design by <a href="http://w3layouts.com" target="_blank">W3layouts</a></p>
		</div>
	</div>

</body>
</html>