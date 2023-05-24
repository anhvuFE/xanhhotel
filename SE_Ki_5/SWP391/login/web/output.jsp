<%-- 
    Document   : output
    Created on : May 23, 2023, 2:59:09 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String captchaEntered = request.getParameter("captchaInput");
            String captchaExpected = (String) request.getSession().getAttribute("captcha");
            if (captchaEntered != null && captchaExpected != null && captchaEntered.equalsIgnoreCase(captchaExpected)) {
                out.println("You have successfully entered the correct CAPTCHA.");
            } else {
                out.println("You have entered the wrong CAPTCHA. Please try again.");
            }
        %>
    </body>
</html>
