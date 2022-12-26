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
    <link rel="stylesheet" href="userMainStyle.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500;700&display=swap" rel="stylesheet">
</head>
<body>
<header class="header">
    <h2 onclick="location.href='index.jsp';" class="header__title">Railwai</h2>

</header>

<main>

    <form class="" action="registr" method="post">


        <div class="usercart">
            <h2>
                <fmt:message key="Registration"/>

            </h2>
            <div class="block">
                <label class="radio">
                    <fmt:message key="name"/>
                    <input name="name" class="input-text" type="text" required placeholder="Name">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="lastname"/>
                    <input name="lastname" class="input-text" type="text" required placeholder="Lastname">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="email"/>
                    <input name="email" type="email" class="input-text ${emailError}" type="text" required placeholder="Email">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="bank_Card"/>
                    <input name="card" class="input-text ${cardError}" pattern="[0-9\s]{16}" required type="text" placeholder="Card">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="login"/>
                    <input name="login" class="input-text" type="text" required placeholder="Login">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="password"/>
                    <input name="password" class="input-text" type="text" required placeholder="Password">
                </label>
            </div>
             <c:if test="${error}">
                          <div class="bar error">
                                invalid input or user was registered
                          </div>
             </c:if>
            <input type="submit" class="login" value="Register"></input>
        </div>

    </form>

</main>
</body>
</html>
