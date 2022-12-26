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
    <a class="noneActiveA" href="allPath?counter=0">
        <h2 class="header__title">Railwai</h2>
    </a>
    <div class="header__nav">
        <a href="addTrain.jsp" method="get" class="active">
            <fmt:message key="add_Train"/>
        </a>
        <a href="adminProf.jsp" method="get" class="active">
            <c:out value="${admin.getName()}"/>
        </a>
    </div>
</header>

<main>

    <form class="" action="submit" method="get">


        <div class="usercart">
            <h2>
                <fmt:message key="base_info"/>
            </h2>

            <c:forEach var="wagon" items="${numberW}">
                <div class="block">
                    <label class="radio">${wagon.getNumber()}
                        <input required name="CarriagePrice${wagon.getNumber()}" class="input-text" type="number"
                               placeholder="100">
                        <select required class="select" name="Carriage${wagon.getNumber()}">

                            <c:forEach var="types" items="${allCarriageTypes}">
                                <option value="${types.getId()}" }>${types.getName()}</option>
                            </c:forEach>

                        </select>
                    </label>
                </div>
            </c:forEach>

            <button class="login">
                <fmt:message key="finish"/>
            </button>

    </form>

</main>
</body>
</html>
