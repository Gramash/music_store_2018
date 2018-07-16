<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/productImage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    
</head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<body class="w3-content" style="max-width:1200px">


<!--sidebar-->
<nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px;overflow: hidden" id="mySidebar">
    
     <myTag:sideBar/>

 <form method="GET" action="products">
 <%-- Filter --%>
 
    <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">    
        <%-- Product type --%>
        <a onclick="showGuitars()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align"
           id="myBtn">
            Products <i class="fa fa-caret-right" id="guitars-drop-down"></i>
        </a>
        <div id="guitars" class="w3-bar-block w3-hide w3-padding-large w3-medium">
        <c:forEach items="${productTypeList}" var="type" varStatus="loop">
            <input type="checkbox" name="type" value="${type.productType}"
             <c:forEach var="pageParameter" items="${paramValues.type}">
             <c:if test="${pageParameter == type.productType }">checked</c:if>
            </c:forEach>
            > ${type.productType} 
            <br>
        </c:forEach>
        </div>
        <%-- Brands --%>
        <a onclick="showBrands()" href="javascript:void(0)" class="w3-button w3-block w3-white w3-left-align"
           id="myBtn">
            Brands <i class="fa fa-caret-right" id="brands-drop-down"></i>
        </a>
        <div id="brands" class="w3-bar-block w3-hide w3-padding-large w3-medium ">
        <c:forEach items="${brandList}" var="brand" varStatus="loop">
            <input type="checkbox" name="brand" value="${brand.brand}"
            <c:forEach var="pageParameter" items="${paramValues.brand}">
             <c:if test="${pageParameter == brand.brand }">checked</c:if>
            </c:forEach>
            > ${brand.brand}
            <br>
        </c:forEach>
        </div>
        <br>
        <div id="filter">
        <%-- Price Range Slider --%>
        <input type="number" min="0" step="0.1" id="lowerPrice" name="lowerPrice" placeholder="price from"/>
        <br>
        <input type="number" min="0" step="0.1" id="upperPrice" name="upperPrice" placeholder="price to" />
        <%-- Product name filter --%>
        <i class="fa fa-search">
        <input type="text" name="productName" id="productName" placeholder="Filter by name"/>
        </i>
        <%-- number of products --%>
        <label for="productsCount">products per page</label>
        <select id="sel1" name="productsCount">
        <option value=1>1</option>
        <option value=2>2</option>
        <option value=3>3</option>
        <option value=4>4</option>
        <option value=5>5</option>
        <option value=10 selected>10</option>
        <option value=20>20</option>
        </select>
         <%-- sort by --%>
        <label for="sortBy">sort by</label>
        <select id="sort" name="sortBy">
        <option value="price asc">price ↑</option>
        <option value="price desc">price ↓</option>
        <option value="name asc">name ↑</option>
        <option value="name desc">name ↓</option>
        <option value="brand asc">brand ↑</option>    
        <option value="brand desc">brand ↓</option>
        </select>
        </div>
</div>
    <button type="submit" class="w3-button w3-padding-large w3-green w3-margin-bottom submit">
                    Apply
        </button>
    </form>
</nav>

<!-- Top menu on small screens -->
<header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
  <div class="w3-bar-item w3-padding-24 w3-wide">LOGO</div>
  <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding-24 w3-right" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!--page content-->
<div class="w3-main" style="margin-left:250px">

    <!--header-->
    <header class="w3-container w3-xlarge">
        <p class="w3-right">
            <form action="cart" method="GET">
            <button type="submit" class="cart">
            <i class="fa fa-shopping-cart w3-margin-right" id="cartPrice" value="${totalPrice}"> <c:if test="${totalPrice != null}">${totalPrice}$</c:if>
            </i>
            </button>
            </form>

        </p>
    </header>

    <!--products grid-->

    <c:if test="${empty products}">
        <h2>There are no products that meet Your preferences</h2>
        <h2>Please try again</h2>
    </c:if>

    <c:forEach items="${products}" var="product" varStatus="loop">
        <div class="w3-col m3 l3 s3">
                <figure>
                    <img src="image?productImage=${product.image}" alt="${product.name}, $${product.price}" onclick="zoomImage(this) "
                         style="width:100%" onerror="this.src='https://images.reverb.com/image/upload/s--g860nSO5--/a_exif,c_limit,e_unsharp_mask:80,f_auto,fl_progressive,g_south,h_620,q_90,w_620/v1516533549/h0zdxvyzq45tkzkuqzo4.jpg'">
                </figure>
                <p>${product.name}<b><br>$${product.price}</b></p>
                <button type="submit" onclick="addToCart('${product.id}', '${product.price}','${product}')" class="w3-button w3-black buy-button">Buy now<i class="fa fa-shopping-cart"></i></button>
        </div>
    </c:forEach>
</div>

<%-- Pagination --%>
<div class="w3-white w3-center w3-padding-24">
<div class="fixed">
    <ul class="pagination justify-content-center" id="pagination">
    <c:forEach var="i" begin="1" end="${numberOfPages}">
    <li class="${currentPage!=i ? "page-item" : "page-item active"}"><a onclick="changeActive()" value="${i}" href="${currentRequest}currentPage=${i}">${i}</a></li>
    </c:forEach>
    </ul>
    </div>
    <input type="hidden" value="${currentPage}" name="currentPage">
</div>

<%-- imageModal --%>
<div id="myModal" class="modal" onclick="hide()">
  <span class="close" onclick="hide()">&times;</span>
  <img class="modal-content" id="img01" onclick="hide()">
  <div id="caption"></div>
</div>

<script src="resources/js/validation.js"></script>
<script src="resources/js/home.js"></script>
<script src="resources/js/jQueryValidation.js"></script>
<script src="resources/js/products.js"></script>
<script src="resources/js/cartAJAX.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="resources/js/productImage.js"></script>

</body>
</html>