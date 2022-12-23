<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="content"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>train</title>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel='stylesheet'
          href='https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap'>
    <link rel="stylesheet" href="indexStyle.css">

</head>
<body>
<!-- partial:index.partial.html -->
<form class="" action="enter" method="post">
    <div class="screen-1">


        <div class="email">
            <label for="email">Login</label>
            <div class="sec-2">
                <ion-icon name="mail-outline"></ion-icon>
                <input name="email" placeholder="Login"/>
            </div>
        </div>
        <div class="password">
            <label for="password">Password</label>
            <div class="sec-2">
                <ion-icon name="lock-closed-outline"></ion-icon>
                <input class="pas" type="password" name="password" placeholder="***********"/>

            </div>
        </div>


        <input type="submit" class="login" value="login"></input>


        <div onclick="location.href='registration.jsp';" class="footer"><span>Signup</span></div>

    </div>
</form>
<!-- partial -->

</body>
</html>