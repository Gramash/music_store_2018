<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>

  <div class="w3-container w3-display-container w3-padding-16">
        <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-button w3-display-topright"></i>
        <h3 class="w3-wide"> <a href="home">music shop</a></h3>
    </div>

    <c:choose>
       <c:when test ="${sessionScope.signed_in_user==null}">
         <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding"
       onclick="document.getElementById('sign_up').style.display='block'">Join</a>
        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-padding"
       onclick="document.getElementById('sign_in').style.display='block'">Sign-In</a>
       </c:when>
        <c:otherwise>
        <div class="card">
        <div>
             <img src="image" alt="photo" style="width:50%">
             </div>
                <h1>${sessionScope.signed_in_user.name} ${sessionScope.signed_in_user.lastName}</h1>
        </div>
        <form action="logout" method="post">
            <button type="submit" class="w3-button w3-grey buy-button" >Sign Out</button>
        </form>
        </c:otherwise>
    </c:choose>

    <%@ include file="/WEB-INF/registration.jspf" %>
    <%@ include file="/WEB-INF/login.jspf"%>