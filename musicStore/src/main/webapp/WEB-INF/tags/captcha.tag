<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<input type="hidden" id="captchaID" name="captchaID" value="${captchaID}">
<img class ="captcha-class" id="captchaImg" src ="captcha?captchaID=${captchaID}"></img>