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

<main class="main">
    <form class="" action="allPath" method="get">
        <nav class="nav">
            <h2>Filters</h2>


            <label class="checkbox">
                <fmt:message key="departure"/>
                <select class="select sameInput" name="departure">

                    <c:forEach var="station" items="${stationsName}">
                        <option value="${station}" }>${station}</option>
                    </c:forEach>
                </select>
            </label>

            <label class="checkbox">
                <fmt:message key="arrival"/>
                <select class="select sameInput" name="arrival">

                    <c:forEach var="station" items="${stationsName}">
                        <option value="${station}" }>${station}</option>
                    </c:forEach>
                </select>
            </label>


            <div class="stroke"></div>

            <label class="checkbox">
                <fmt:message key="departure"/>
                <input class="input" type="date" name="departureT" required value="">
            </label>

            <div class="stroke"></div>

            <label class="radio">
                <fmt:message key="sleeping"/>
                <input class="input" type="radio" name="placeT" value="sleeping car" required>
            </label>
            <label class="radio">
                <fmt:message key="luxury"/>
                <input class="input" type="radio" name="placeT" value="luxury carriage">
            </label>
            <label class="radio">
                <fmt:message key="compartment"/>
                <input class="input" type="radio" name="placeT" value="compartment car">
            </label>
            <label class="radio">
                <fmt:message key="reserved"/>
                <input class="input" type="radio" name="placeT" value="reserved carriage">
            </label>
            <label class="radio">
                <fmt:message key="sitting"/>
                <input class="input" type="radio" name="placeT" value="sitting carriage">
            </label>
            <input type="submit" class="login find" value="Search"> </input>

        </nav>
    </form>

    <div class="main__catalog">


        <div class="catalog">

            <c:forEach var="path" items="${paths}">
                <a class="noneActiveA" href="buyTicket?id=${path.getId()}">
                    <div class="catalog__card">
                        <h3>${path.getTrain().getName()}</h3>

                        <div class="block">
                            <p>
                                <fmt:message key="departure"/>
                            </p>
                            <p>${path.getNormalDispatch_time()}</p>
                        </div>
                        <div class="block">
                            <p>
                                <fmt:message key="arrival"/>
                            </p>
                            <p> ${path.getNormalArrival_time()}</p>
                        </div>
                        <div class="block">
                            <p>
                                <fmt:message key="free_place"/>
                            </p>
                            <p> ${path.getNumberOfFreePlaces()}</p>
                        </div>
                    </div>
                </a>
            </c:forEach>


        </div>

        <c:if test="${counter>0}">
            <input type="submit" class="login mini_btn" onclick="window.location.href='allPathA?counter=${counter-8}'"
                   value="previous"></input>
        </c:if>
        <c:if test="${counter+8<allPaths.size()}">
            <input type="submit" class="login mini_btn" onclick="window.location.href='allPathA?counter=${counter+8}'"
                   value="Next"></input>
        </c:if>
    </div>
</main>
</body>
</html>
