<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Products In Cart Template Flat Responsive Widget Template | Home :: w3layouts</title>
<link href="/resources/css/cartStyle.css" rel="stylesheet" type="text/css" media="all"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Flash Registration Form  Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!--web-fonts-->
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'></head>
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Yanone+Kaffeesatz:400,700,300,200' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<body onload="getTotalSumm(${totalPrice})" >

		<%-- <!---header---> --%>
		<div class="navbar2">
		<a href="home">Home</a>
			<a href="products">Products</a>		
			<a type="btn" data-toggle="modal" data-target="#myModal2" >Checkout</a>
		</div>
		
			<div class="main">
				<div class="main-section">
					
						<br>
						<h3 id="summary">summary : <p id="totalPrice" value="${totalPrice}"> </p> </h3>
						<h1><c:if test="${fn:length(cart) == 0}"> Your cart is empty</c:if></h2>
						<div class="clear">
					
					<c:forEach items="${cart}" var="entry">
					<div class="${entry.key.id}" >
					<div class="product1" id="${entry.key.id}">
						<div class="product-top">
							<div class="product-left">
								<img src="image?productImage=${entry.key.image}">
							</div>
							<div class="product-right">
								<h2>${entry.key.name}</h2>
									<h5>Adispree White Running Shoes</h5>
									<ul class="size">										
										<li>Quantity ${entry.value}</li>
									</ul>
							</div>
						</div>
						<div class="product-middle">
							<input class="input" id="count${entry.key.id}" name="count" type="number" value="${entry.value}" min=1 
							onchange="patchCart(${entry.key.id}, this.value, ${entry.key.price})" onmouseover="getOldVal(this.value)"> 
							</input>
						</div>
						<div class="product-right1">
							<p>$${entry.key.price}<p>
							<input type="hidden" name="productPrice" value="${entry.key.price}"/>
							<button onclick="removeFromCart(${entry.key.id}, ${entry.key.price})"> Remove </button></div>
							<div>
						</div>
						<div class="clear"></div>
					</div>
					</div>

				</c:forEach>
						
					
				</div>
			</div>
			<div class="footer">
			<p>&copy 2016 Products In Cart . All rights reserved | Design by <a href="http://w3layouts.com">W3layouts</a></p>

		
    </div>
  </div>
</div>

 <!-- Sign in request modal -->
  		<div class="modal fade" id="myModal" role="dialog">
  	  		<div class="modal-dialog">
    		<!-- Modal content-->
      		<div class="modal-content">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title">Modal Header</h4>
        		</div>
        		<div class="modal-body">
          		<p>You have to Sign in to create and order.</p>
        		</div>
        	<div class="modal-footer">
          	<button type="button" class="btn btn-default" onclick="closeModal()">Close</button>
          	<button type="button" class="btn btn-primary" onclick="location.href ='home?login=open'">Sign In</button>
        	</div>
      	</div>
      	</div>
  		</div>

  		<div class="modal fade" id="myModal3" role="dialog">
  	  		<div class="modal-dialog">
    		<!-- Modal content-->
      		<div class="modal-content">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title">Modal Header</h4>
        		</div>
        		<div class="modal-body">
          		<p>You have successfuly made an order!</p>
        		</div>
        		<div class="modal-footer">
        	<button type="button" onclick="window.location.href='products'" class="btn btn-primary">Go for more!</button>
          	<button type="button" class="btn btn-default" onclick="closeModal3()" >Close</button>
        	</div>
      	</div>
      	</div>
  		</div>


<div class="modal fade" id="myModal2" role="dialog">
<div class="modal-dialog">
    		<!-- Modal content-->
<div class="modal-content">
<div class="row">
  
    <div class="container">
      <form action="order" method="POST">
            <h3>Billing Address</h3>
            <div class="row">
              <div class="col-50">
                <label for="state">Address</label>
                <br>
                <input type="text" id="address" name="address" placeholder="Country, City, Street,ZIP">
              	<br>
              </div>
            </div>
          
            <h3>Payment</h3>
            <label for="fname">Accepted Cards</label>
            <div class="row">
              <div class="col-50">
            <div class="icon-container">
              <i class="fa fa-cc-visa" style="color:navy;"></i>
              <i class="fa fa-cc-amex" style="color:blue;"></i>
              <i class="fa fa-cc-mastercard" style="color:red;"></i>
              <i class="fa fa-cc-discover" style="color:orange;"></i>
            </div>
            <label for="cname">Name on Card</label>
            <br>
            <input type="text" id="cname" name="cardname" placeholder="John More Doe">
            <br>
            <label for="ccnum">Credit card number</label>
            <br>
            <input type="number" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
            <br>
            <label for="expmonth">Exp Month</label>
            <br>
            <input type="text" id="expmonth" name="expmonth" placeholder="September">
            <br>
                <label for="expyear">Exp Year</label>
                <br>
                <input type="number" id="expyear" name="expyear" placeholder="2018">
              	<br>
                <label for="cvv">CVV</label>
                <br>
                <input type="number" id="cvv" name="cvv" placeholder="352" max=999>
              </div>
            </div>
        <label>
        <button type="submit" class="btn btn-primary" >checkout</button>  
        </label>
        
       
        
      </form>
    </div>
  </div>
</div>

</div>
</div>




<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="resources/js/cartAJAX.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>