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

    <form class="" action="addTrain" method="get">


        <div class="usercart">
            <h2>
                <fmt:message key="base_info"/>
            </h2>
            <div class="block">
                <label class="radio">
                    <fmt:message key="train_id"/>
                    <input required name="TrainId" class="input-text" type="number" placeholder="id">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="departure_Station"/>
                    <select class="select" name="Departure" required>

                        <c:forEach var="station" items="${stations}">
                            <option value="${station.getName()}" }>${station.getName()}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="arrival_Station"/>
                    <select required class="select" name="Arrival">

                        <c:forEach var="station" items="${stations}">
                            <option value="${station.getName()}" }>${station.getName()}</option>
                        </c:forEach>

                    </select>
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="departure_Time"/>
                    <input required name="DepartureT" class="input-text" type="datetime-local" placeholder="time">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="departure_Station"/>
                    <fmt:message key="number"/>
                    <input required name="DepartureNumber" class="input-text" type="number" placeholder="number">
                </label>
            </div>

            <div class="block">
                <label class="radio">
                    <fmt:message key="arrival_Time"/>
                    <input required name="ArrivalT" class="input-text" type="datetime-local" placeholder="time">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="arrival_Station"/>
                    <fmt:message key="number"/>
                    <input required name="ArrivalNumber" class="input-text" type="number" placeholder="number">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="number_of_Wagon"/>
                    <input required name="numberW" class="input-text" type="number" placeholder="number of wagons">
                </label>
            </div>
            <div class="block">
                <label class="radio">
                    <fmt:message key="way_back_id"/>
                    <input name="return" class="input-text" type="number" placeholder="way id">
                </label>
            </div>
            <button class="login">
                <fmt:message key="next"/>
            </button>
        </div>

    </form>

</main>
</body>
</html>
