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
        <a href="ticketsPage" method="get" class="active">tickets</a>

        <a href="userProf.jsp" method="get" class="active">
            <c:out value="${user.getName()}"/>
        </a>


    </div>
</header>

<main>


    <div class="usercart">
        <h2>
            <c:out value="${paths.getTrain().getName()}"/>
        </h2>
        <div class="block">
            <p class="four_elements">
                <fmt:message key="arrival"/>
            </p>
            <p class="four_elements">
                <c:out value="${paths.getArrival().getName()}"/>
            </p>
            <p style="text-align: center;" class="four_elements">
                <c:out value="${paths.getArrival_number()}"/>
                <fmt:message key="stations"/>
            </p>
            <p style="text-align: right;" class="four_elements">
                <c:out value="${paths.getArrival_time()}"/>
            </p>
        </div>
        <div class="block">
            <p class="four_elements">
                <fmt:message key="departure"/>
                :
            </p>
            <p class="four_elements">
                <c:out value="${paths.getDispatch().getName()}"/>
            </p>
            <p style="text-align: center;" class="four_elements">
                <c:out value="${paths.getDispatch_number()}"/>
                <fmt:message key="stations"/>
            </p>
            <p style="text-align: right;" class="four_elements">
                <c:out value="${paths.getDispatch_time()}"/>
            </p>
        </div>
        <hr class="style-two">

        <h2><fmt:message key="your_info"/></h2>

        <div class="placesList">
            <label class="checkbox">
                <fmt:message key="wagon"/>
                <label class="info_label">
                    <c:out value="${carriage.getNumber()}"/>
                </label>
            </label>

            <label class="checkbox">
                <fmt:message key="place"/>
                <label class="info_label">
                    <c:out value="${ticket.getPlace()}"/>
                </label>
            </label>
            <label class="checkbox">
                <fmt:message key="name"/>
                <label class="info_label">
                    <c:out value="${ticket.getName()}"/>
                </label>
            </label>
            <label class="checkbox">
                <fmt:message key="lastname"/>
                <label class="info_label">
                    <c:out value="${ticket.getSurname()}"/>
                </label>
            </label>

        </div>

    </div>


</main>
</body>
</html>
